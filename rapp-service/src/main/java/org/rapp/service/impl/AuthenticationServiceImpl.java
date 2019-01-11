package org.rapp.service.impl;

import java.util.Date;

import org.rapp.comm.util.CalendarUtil;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.entry.Router;
import org.rapp.pojo.entry.authentication.wechat.WechatAuth;
import org.rapp.pojo.entry.authentication.wechat.WechatAuthLog;
import org.rapp.repository.AuthenticationDao;
import org.rapp.service.AuthenticationService;
import org.rapp.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 认证服务接口
 * @author 张芳
 *
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	AuthenticationDao authenticationDao;
	
	@Autowired
	RouterService routerService;

	@Override
	public BaseResult<String> saveWechatAuthLog(WechatAuthLog wal) {
		try {
			//生成认证流水
			wal.setId(CalendarUtil.parseLongTime(new Date()) + ((int)((Math.random()*9+1)*100000)));
			//持久化流水信息
			int count = authenticationDao.insertWechatAuthLog(wal);
			if (count == 1) {
				return new BaseResult<String>(wal.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResult<String>(false, "记录流水信息异常");
		}
		return new BaseResult<String>(false, "记录流水信息失败");
	}
	
	@Override
	public BaseResult<WechatAuthLog> selectWechatAuthLogById(String id) {
		try {
			WechatAuthLog wal = authenticationDao.queryWechatAuthLogById(id);
			if (wal != null) {
				return new BaseResult<WechatAuthLog>(wal);
			}
		} catch (Exception e) {
			return new BaseResult<>(false, "获取流水信息异常：" + id);
		}
		return new BaseResult<>(false, "不存在该流水信息：" + id);
	}

	
	//TODO 此方法待优化，目前不清楚渠道与微信公众号具体绑定流程
	@Override
	public BaseResult<String> saveWechatAuth(WechatAuth wa) {
		int count = authenticationDao.insertWechatAuth(wa);
		if (count == 1) {
			return new BaseResult<String>("成功");
		}
		return new BaseResult<>(false, "失败");
	}

	@Override
	public BaseResult<WechatAuth> selectWechatAuth(String devNo) {
		try {
			//根据设备号获取设备所属区域
			BaseResult<Router> result = routerService.queryRouterByDevNo(devNo);
			if (!result.isSuccess()) {
				logger.info("设备未注册：{}" , devNo);
				return new BaseResult<>(false, "该设备未注册");
			}
			Router router = result.getData();
			WechatAuth wa = authenticationDao.queryWechatAuthByChannelId(router.getChannelId());
			if (wa == null) {
				logger.info("该区域未绑定微信公众号：{}" , devNo);
				return new BaseResult<>(false, "该区域未绑定微信公众号");
			}
			return new BaseResult<WechatAuth>(wa);
		} catch (Exception e) {
			return new BaseResult<>(false, "查询绑定微信公众号异常，请重试");
		}
	}

	@Override
	public BaseResult<Boolean> updateSecretKeyById(WechatAuth wa) {
		try {
			int count = authenticationDao.updateSecretKeyById(wa);
			if (count == 1) {
				return new BaseResult<Boolean>(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResult<>(false);
		}
		return new BaseResult<>(false);
	}
	
}
