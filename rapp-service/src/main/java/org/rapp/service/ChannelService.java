package org.rapp.service;

import java.util.List;

import org.rapp.pojo.account.User;
import org.rapp.pojo.dto.param.ChannelParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.ChannelReuslt;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.Channel;

/**
 * 渠道服务接口
 * @author 张芳
 *
 */
public interface ChannelService {

	/**
	 * 查询渠道列表信息
	 * @param params
	 * @return
	 */
	TableResult<Channel> queryChannels(ChannelParam param);
	
	/**
	 * 查询我的渠道
	 * @param param
	 * @return
	 */
	TableResult<ChannelReuslt> queryMyChannels(ChannelParam param);
	
	/**
	 * 根据ID获取渠道
	 * @param channelId
	 * @return
	 */
	BaseResult<Channel> queryChannelById(String channelId);
	
	/**
	 * 查询所有的可用渠道信息
	 * @return
	 */
	BaseResult<List<Channel>> queryChannelOfEnable(User user);
	
	/**
	 * 查询所有的渠道信息
	 * @param user
	 * @return
	 */
	public BaseResult<List<Channel>> queryChannelAll(User user);
	
	/**
	 * 新增渠道
	 * @param channel
	 * @return
	 */
	BaseResult<Channel> addChannel(Channel channel);
	
	/**
	 * 删除渠道
	 * @param channel
	 * @return
	 */
	BaseResult<String> removeChannel(List<Channel> channels);
	
	/**
	 * 更新渠道
	 * @param channel
	 * @return
	 */
	BaseResult<Channel> editChannel(Channel channel);
	
	/**
	 * 查询渠道详情
	 * @param user
	 * @param channelId
	 * @return
	 */
	BaseResult<Channel> showDetail(User user, String channelId);

	/**
	 * 查询我的区域信息
	 * @param user
	 * @param onlineDate
	 * @return
	 */
	BaseResult<List<ChannelReuslt>> queryMyChannel(String onlineDate, String createBy,String keyword, String offset, String limit);
}
