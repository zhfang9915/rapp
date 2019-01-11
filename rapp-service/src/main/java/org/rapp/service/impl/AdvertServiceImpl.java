package org.rapp.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.rapp.comm.exception.BaseServiceException;
import org.rapp.pojo.account.User;
import org.rapp.pojo.dto.param.AdvertParam;
import org.rapp.pojo.dto.param.PushDetailParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.Advert;
import org.rapp.pojo.entry.AdvertItem;
import org.rapp.pojo.entry.Channel;
import org.rapp.pojo.entry.PushDetail;
import org.rapp.pojo.log.AdvertCheckLog;
import org.rapp.repository.AdvertCheckLogDao;
import org.rapp.repository.AdvertDao;
import org.rapp.repository.AdvertLogDao;
import org.rapp.repository.ChannelDao;
import org.rapp.service.AdvertCheckService;
import org.rapp.service.AdvertService;
import org.rapp.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 广告配合服务类
 * @author 张芳
 *
 */
@Service
public class AdvertServiceImpl implements AdvertService,AdvertCheckService {
	
	@Autowired
	AdvertDao advertDao;
	
	@Autowired
	AdvertLogDao advertLogDao;
	
	@Autowired
	ChannelDao channelDao;
	
	@Autowired
	AdvertCheckLogDao checkLogDao;
	
	@Autowired
	BaseService service;
	
	@Override
	public BaseResult<String> getUUID(){
		String uuid = advertDao.getUUID();
		if (uuid == null) {
			return new BaseResult<>(false, "获取主键失败");
		}
		uuid.replaceAll("-", "");
		return new BaseResult<String>(uuid);
	}

	@Override
	@Transactional
	public BaseResult<Advert> configurationAdvert(Advert advert) {
		//插入广告记录
		advertDao.insertAdvert(advert);
		//广告明细
		advertDao.insertAdvertItem(advert.getItems());
		//插入广告渠道关系
		String[] channelArr = advert.getChannelsId().split(",");
		advertDao.insertAdvertChannel(advert.getAdvertId(), channelArr);
		//获取渠道详情
		List<Channel> channels = channelDao.queryChannelByIds(channelArr);
		advert.setChannels(channels);
		return new BaseResult<Advert>(advert);
	}

	@Override
	public TableResult<Advert> queryAdvertForTable(AdvertParam param) {
		List<Advert> adverts = advertDao.tableAdverts(param);
		int total = advertDao.tableAdvertsCount(param);
		return new TableResult<>(total, adverts);
	}
	
	@Override
	public BaseResult<List<Advert>> queryAdvertForList(AdvertParam param) {
		List<Advert> adverts = advertDao.listAdverts(param);
		return new BaseResult<List<Advert>>(adverts);
	}

	@Override
	@Transactional
	public BaseResult<String> deleteAdvertConfig(List<Advert> adverts) {
		try {
			// 1.删除广告渠道关系
			advertDao.deleteAdvertChannelByAdvertId(adverts);
			// 2.删除广告明细
			advertDao.deleteAdvertItem(adverts);
			// 3.删除广告配置信息
			advertDao.deleteAdvert(adverts);
			
			//广告删除成功时删除文件系统的广告
			for (Advert advert : adverts) {
				for (AdvertItem item : advert.getItems()) {
					if (item.getLocalUrl() != null) {
						//本地文件
						File file = new File(item.getLocalUrl());
						if (file.exists()) {
							file.delete();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseServiceException("删除广告配置失败，请联系管理员！");
		}
		return new BaseResult<String>("删除广告配置信息成功");
	}

	@Override
	@Transactional
	public BaseResult<Advert> updateAdvertConfig(Advert advert) {
		try {
			Advert dbAD = advertDao.queryAdvertById(advert.getAdvertId(), advert.getCreateBy());
			advert.setState(dbAD.getState());
			//判断是否更新了广告内容和url
			loop : for (AdvertItem dbItem : dbAD.getItems()) {
				for (AdvertItem item : advert.getItems()) {
					if (!dbItem.getToUrl().equals(item.getToUrl()) || !dbItem.getAdvertUrl().equals(item.getAdvertUrl())) {
						advert.setState(1);
						break loop;
					}
				}
			}
			// 1.更新广告配置信息
			advertDao.updateAdvert(advert);
			if (1 == advert.getState()) {//有更新广告明细
				//删除原有广告明细
				List<Advert> adverts = new ArrayList<>();
				adverts.add(advert);
				advertDao.deleteAdvertItem(adverts);
				
				//插入更新后的广告明细
				advertDao.insertAdvertItem(advert.getItems());
			}
			
			// 2.查询广告渠道信息
			//数据库中的渠道关系
			List<String> oldChannelIds = advertDao.queryAdvertChannelIds(advert.getAdvertId());
			//更新后的渠道信息
//			List<Channel> channles = advert.getChannels();
			List<String> newChannelIds = Arrays.asList(advert.getChannelsId().split(","));
//			for (Channel channel : channles) {
//				newChannelIds.add(channel.getChannelId());
//			}
			//新的渠道信息中 与老的渠道信息中有的则不变，没有的则删除，多余的则新增
			Collection<String> exists=new ArrayList<String>(oldChannelIds);
			Collection<String> notexists=new ArrayList<String>(newChannelIds);
			exists.removeAll(newChannelIds);
			System.out.println("已有的渠道："+exists);
			
			notexists.removeAll(oldChannelIds);
			System.out.println("新增的渠道："+notexists);
			// 3.更新广告渠道信息
			if (!exists.isEmpty()) {
				//删除已有
				advertDao.deleteAdvertChannel(advert.getAdvertId(), exists);
			}
			if (!notexists.isEmpty()) {
				//新增新加的
				advertDao.insertAdvertChannel(advert.getAdvertId(), notexists.toArray());
			}
		} catch (Exception e) {
			throw new BaseServiceException("更新广告配置失败，请联系管理员！");
		}
		return new BaseResult<Advert>(advert);
	}

	@Override
	public BaseResult<Advert> queryAdvertById(String advertId, User user) {
		try {
			String createBy = service.getCreateBy(user);
			//1.查询广告主题信息
			Advert advert = advertDao.queryAdvertById(advertId, createBy);
			//2.查询广告所属的渠道信息
//			List<String> channelIds = advertDao.queryAdvertChannelIds(advertId);
			//放入结果集
//			List<Channel> channels = new ArrayList<>();
//			for (String channelId : channelIds) {
//				Channel channel = new Channel();
//				channel.setChannelId(channelId);
//				channels.add(channel);
//			}
//			advert.setChannels(channels);
			return new BaseResult<Advert>(advert);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseServiceException(advertId + "配置信息查询失败，请联系管理员！");
		}
	}

	@Override
	public int totalAdvertExist(String username) {
		return advertDao.totalAdvertExist(username);
	}

	@Override
	@Transactional
	public BaseResult<String> checkAdvert(Map<String, Object> params) {
		try {
			//1. 更新广告状态表
			advertDao.updateState((String)params.get("advertId"), (String)params.get("state"));
			//2. 插入审核日志(消息)
			AdvertCheckLog log = new AdvertCheckLog();
			log.setAdvertId((String)params.get("advertId"));
			log.setCreateBy((String)params.get("checkBy"));
			log.setMsg((String)params.get("text"));
			log.setReviewer((String)params.get("createBy"));
			String title = (String)params.get("advertName");
			switch ((String)params.get("state")) {
			case "2":
				title += " 通过审核";
				break;
			default:
				title += " 未通过审核";
				break;
			}
			log.setTitle(title);
			checkLogDao.insertCheckLog(log);
			return new BaseResult<String>("提交审核成功");
		} catch (Exception e) {
			throw new BaseServiceException("提交审核出错，请联系系统管理员");
		}
	}

	@Override
	public BaseResult<String> enableOrDisableAdvert(String advertId, String state) {
		try {
			advertDao.updateState(advertId, state);
			return new BaseResult<String>("操作成功");
		} catch (Exception e) {
			return new BaseResult<String>(false, "操作失败，请联系管理员");
		}
	}

	@Override
	public int totalUnreadAdvertMsg(String username) {
		return checkLogDao.queryUnreadCount(username);
	}
	
	@Override
	public BaseResult<List<AdvertCheckLog>> queryUnreadLog(String username) {
		List<AdvertCheckLog> unreads = checkLogDao.queryUnreadCheckLog(username);
		if (unreads==null || unreads.size()==0) {
			return new BaseResult<List<AdvertCheckLog>>(false, "没有新消息");
		}
		//更新状态为已读
		checkLogDao.updateReadState(unreads);
		return new BaseResult<List<AdvertCheckLog>>(unreads);
	}

	@Override
	public int tableUncheckAdverts() {
		AdvertParam param = new AdvertParam();
		param.setState("1");
		return advertDao.tableAdvertsCount(param);
	}

	@Override
	public TableResult<PushDetail> queryPushDetailByAdvert(PushDetailParam param) {
		List<PushDetail> pushDetails = advertLogDao.queryPushDetailByAdvert(param);
		int total = advertLogDao.queryPushDetailByAdvertCount(param);
		return new TableResult<>(total, pushDetails);
	}
	

}
