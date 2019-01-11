package org.rapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.rapp.pojo.dto.param.ChannelParam;
import org.rapp.pojo.dto.result.ChannelReuslt;
import org.rapp.pojo.entry.Channel;

/**
 * 渠道持久化接口
 * @author 张芳
 *
 */
public interface ChannelDao {

	/**
	 * 查询渠道列表信息
	 * @param params
	 * @return@Param("offset")String offset, @Param("limit")String limit,
			@Param("channelNameEn")String channelNameEn, @Param("channelNameCh")String channelNameCh,
			@Param("state")String state, @Param("channelId")String channelId
	 */
	List<Channel> listChannels(ChannelParam param);
	
	/**
	 * 渠道列表总数
	 * @param params
	 * @return
	 */
	int countChannels(ChannelParam param);
	
	/**
	 * 根据ID获取渠道
	 * @param channelId
	 * @return
	 */
	Channel queryChannelById(@Param("channelId")String channelId);
	
	/**
	 * 根据ID查多个渠道
	 * @param channelIsd
	 * @return
	 */
	List<Channel> queryChannelByIds(@Param("channels")String[] channels);
	
	/**
	 * 查询所有的渠道信息
	 * @return
	 */
	List<Channel> queryChannelAll(@Param("createBy")String createBy);
	
	/**
	 * 新增渠道
	 * @param channel
	 * @return
	 */
	int insertChannel(Channel channel);
	
	/**
	 * 删除渠道
	 * @param channel
	 * @return
	 */
	int deleteChannel(@Param("list")List<Channel> channels);
	
	/**
	 * 更新渠道
	 * @param channel
	 * @return
	 */
	int updateChannel(Channel channel);
	
	/**
	 * 根据用户并根据ID获取渠道信息
	 * @param createBy
	 * @param channelId
	 * @return
	 */
	Channel showChannelById(@Param("createBy")String createBy, @Param("channelId")String channelId);
	
	/**
	 * 查询我的区域信息
	 * @param onlineDate
	 * @param createBy
	 * @return
	 */
	List<ChannelReuslt> queryMyChannel(
			@Param("onlineDate")String onlineDate, @Param("createBy")String createBy,
			@Param("keyword")String keyword, @Param("offset")String offset, @Param("limit")String limit);
}
