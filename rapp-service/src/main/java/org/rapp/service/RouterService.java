package org.rapp.service;

import java.util.List;

import org.rapp.pojo.account.User;
import org.rapp.pojo.dto.param.RouterParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.Router;

/**
 * 设备服务接口
 * @author 张芳
 *
 */
public interface RouterService {

	
	/**
	 * 设备注册
	 * @param router
	 * @return
	 */
	BaseResult<Router> register(User user, Router router);
	
	/**
	 * 根据设备ID 查询设备
	 * @param devNo
	 * @return
	 */
	BaseResult<Router> queryRouterByDevNo(String devNo);
	
	/**
	 * 根据设备号查询设备详情
	 * @param devNo
	 * @param user
	 * @return
	 */
	BaseResult<Router> queryRouterDetailByDevNo(String devNo, User user);
	
	/**
	 * 查询名下的所有设备
	 * @param user
	 * @return
	 */
	BaseResult<List<Router>> queryRouterAll(User user);
	
	
	/**
	 * 根据MAC地址获取设备
	 * @param mac
	 * @return
	 */
	BaseResult<Router> queryRouterByMac(String mac);

	/**
	 * 表格查询设备信息
	 * @param user
	 * @param param
	 * @return
	 */
	TableResult<Router> queryRouter4Table(User user, RouterParam param);
	
	/**
	 * 查询为绑定的设备
	 * @param user
	 * @return
	 */
	TableResult<Router> queryFreeRouter(RouterParam param);
	
	/**
	 * 注册回调，更新设备编码
	 * @param devNo
	 * @param mac
	 * @return
	 */
	BaseResult<Router> callback (String devNo, String mac);

	/**
	 * 删除设备
	 * @param routers
	 * @return
	 */
	BaseResult<String> deleteRouter(List<Router> routers, String createBy);
	BaseResult<String> deleteRouterByNo(String devNo, String createBy);

	/**
	 * 更新设备的所属渠道
	 * @param routers
	 * @param channelId
	 * @return
	 */
	BaseResult<String> updateRouter4Channel(List<Router> routers, String channelId);
	
	/**
	 * 解绑设备
	 * @param routers
	 * @param channelId
	 * @return
	 */
	BaseResult<String> removeRouterChannel(List<Router> routers, String channelId);
	
	/**
	 * 设备绑定渠道
	 * @param devNo
	 * @param channelId
	 * @return
	 */
	BaseResult<String> bindingRouter4Channel(String devNo, String channelId);

	/**
	 * 更新设备的地址信息
	 * @param router
	 * @return
	 */
	BaseResult<String> updateRouterAddress(Router router);

	/**
	 * 更新设备的固件版本信息
	 * @param router
	 * @return
	 */
	BaseResult<String> updateRouterFirmware(Router router);

	/**
	 * 查询指定渠道下的设备
	 * @param user
	 * @param channelId
	 * @return
	 */
	List<Router> queryRouterByChannel(User user, String channelId);
	
}
