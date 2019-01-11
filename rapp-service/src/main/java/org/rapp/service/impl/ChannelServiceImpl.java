package org.rapp.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.rapp.comm.exception.BaseServiceException;
import org.rapp.comm.util.CalendarUtil;
import org.rapp.pojo.account.User;
import org.rapp.pojo.dto.param.ChannelParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.ChannelReuslt;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.Channel;
import org.rapp.pojo.entry.JSCode;
import org.rapp.repository.ChannelDao;
import org.rapp.repository.JSCodeDao;
import org.rapp.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Resource
	ChannelDao channelDao;
	
	@Autowired
	JSCodeDao codeDao;

	@Override
	public TableResult<Channel> queryChannels(ChannelParam param) {
		List<Channel> channels = channelDao.listChannels(param);
		int total = channelDao.countChannels(param);
		return new TableResult<>(total, channels);
	}
	
	@Override
	public TableResult<ChannelReuslt> queryMyChannels(ChannelParam param) {
		List<ChannelReuslt> cr = channelDao.queryMyChannel(CalendarUtil.getAssignDate(0, "yyyy-MM-dd"), 
				param.getCreateBy(), param.getChannelNameCh(), param.getOffset(), param.getLimit());
		int total = channelDao.countChannels(param);
		return new TableResult<>(total, cr);
	}
	

	@Override
	public BaseResult<Channel> queryChannelById(String channelId) {
		Channel channel = channelDao.queryChannelById(channelId);
		if (channel == null) {
			return new BaseResult<Channel>(false, "未查询到该渠道信息");
		}
		return new BaseResult<Channel>(channel);
	}

	@Override
	public BaseResult<List<Channel>> queryChannelOfEnable(User user) {
		String createBy = null;
		if (user.getRole().getRoleId() > 1) {
			// 非系统管理员
			createBy = user.getUsername();
		}
		ChannelParam param = new ChannelParam();
		param.setCreateBy(createBy);
		param.setState("1");
		List<Channel> channels = channelDao.listChannels(param);
		if (channels == null || channels.size()==0) {
			return new BaseResult<List<Channel>>(false, "无可用的渠道信息");
		}
		return new BaseResult<List<Channel>>(channels);
	}
	
	@Override
	public BaseResult<List<Channel>> queryChannelAll(User user) {
		String createBy = null;
		if (user.getRole().getRoleId() > 1) {
			// 非管理员
			createBy = user.getUsername();
		}
		List<Channel> channels = channelDao.queryChannelAll(createBy);
		if (channels == null || channels.size() == 0) {
			return new BaseResult<List<Channel>>(false, "无可用的渠道信息");
		}
		return new BaseResult<List<Channel>>(channels);
	}

	@Override
	public BaseResult<Channel> addChannel(Channel channel) {
		channel.setCreateTime(new Date());
		if (channel.getCodeId() == null) {
			JSCode code = codeDao.defaultJsCode();
			channel.setCodeId(code.getCodeId());
		}
		int insertCount = channelDao.insertChannel(channel);
		if (insertCount == 1) {
			return new BaseResult<Channel>(channel);
		}
		return new BaseResult<Channel>(false, "新增渠道失败");
	}

	@Override
	public BaseResult<String> removeChannel(List<Channel> channels) {
		try {
			int updateCount = channelDao.deleteChannel(channels);
			if (updateCount > 0) {
				return new BaseResult<String>("删除渠道成功");
			}
			return new BaseResult<String>(false, "删除渠道失败");
		} catch (Exception e) {
			throw new BaseServiceException("删除失败，该渠道正在被使用！");
		}
	}

	@Override
	public BaseResult<Channel> editChannel(Channel channel) {
		int updateCoutn = channelDao.updateChannel(channel);
		if (updateCoutn == 1) {
			return new BaseResult<Channel>(channel);
		}
		return new BaseResult<Channel>(false, "更新渠道失败");
	}

	@Override
	public BaseResult<Channel> showDetail(User user, String channelId) {
		String createBy = null;
		if (user.getRole().getRoleId() > 2) {
			// 非管理员
			createBy = user.getUsername();
		}
		Channel channel = channelDao.showChannelById(createBy, channelId);
		if (channel == null) {
			return new BaseResult<>(false, "未查询到该渠道的信息");
		}
		return new BaseResult<Channel>(channel);
	}

	
	@Override
	public BaseResult<List<ChannelReuslt>> queryMyChannel(String onlineDate, String createBy,String keyword, String offset, String limit) {
		List<ChannelReuslt> cr = channelDao.queryMyChannel(onlineDate, createBy, keyword, offset, limit);
		if (cr == null || cr.size() == 0) {
			return new BaseResult<>(false, "未查询到区域");
		}
		return new BaseResult<List<ChannelReuslt>>(cr);
	}
}
