package org.rapp.service.impl;

import java.util.List;

import org.rapp.comm.util.MD5Utils;
import org.rapp.pojo.account.User;
import org.rapp.pojo.dto.param.RouterParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.Router;
import org.rapp.repository.RouterDao;
import org.rapp.service.BaseService;
import org.rapp.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouterServiceImpl implements RouterService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired RouterDao routerDao;
	
	@Autowired
	BaseService service;

	@Override
	public BaseResult<Router> register(User user, Router router) {
		//生成设备编码devNo
		String devNo = MD5Utils.getMD5(router.getMac());
		logger.info(router.getMac() + "设备注册的编码为："+ devNo);
		router.setDevNo(devNo);
		router.setCreateBy(user.getUsername());
		
		//保存设备
		int count = routerDao.insertRouter(router);
		if (count == 1) {
			//保存成功
			return new BaseResult<>(router);
		}
		return new BaseResult<>(false, "注册失败");
	}

	@Override
	public BaseResult<Router> queryRouterByDevNo(String devNo) {
		RouterParam param = new RouterParam();
		param.setDevNo(devNo);
		Router router = routerDao.queryRouterByDevNo(param);
		if (router == null) {
			return new BaseResult<>(false, "未查询到该设备信息");
		}
		return new BaseResult<Router>(router);
	}
	
	@Override
	public BaseResult<Router> queryRouterDetailByDevNo(String devNo, User user) {
		RouterParam param = new RouterParam();
		param.setCreateBy(service.getCreateBy(user));
		param.setDevNo(devNo);
		Router router = routerDao.queryRouter4Table(param).get(0);
		if (router == null) {
			return new BaseResult<>(false, "未查询到该设备信息");
		}
		return new BaseResult<Router>(router);
	}
	
	@Override
	public BaseResult<List<Router>> queryRouterAll(User user) {
		RouterParam param = new RouterParam();
		param.setCreateBy(service.getCreateBy(user));
		List<Router> routers = routerDao.queryRouter4Table(param);
		if (routers == null || routers.size() == 0) {
			return new BaseResult<>(false, "未查询到该设备信息");
		}
		return new BaseResult<List<Router>>(routers);
	}
	
	@Override
	public BaseResult<Router> queryRouterByMac(String mac) {
		Router router = routerDao.queryRouterByMac(mac);
		if (router == null) {
			return new BaseResult<>(false, "未查询到该设备信息");
		}
		return new BaseResult<Router>(router);
	}
	
	@Override
	public TableResult<Router> queryRouter4Table(User user, RouterParam param) {
		if (param.getCreateBy() == null) {
			String createBy = service.getCreateBy(user);
			param.setCreateBy(createBy);
		}
		List<Router> routers = routerDao.queryRouter4Table(param);
		int total = routerDao.queryRouter4TableCount(param);
		return new TableResult<Router>(total, routers);
	}

	@Override
	public TableResult<Router> queryFreeRouter(RouterParam param) {
		List<Router> routers = routerDao.queryFreeRouter(param);
		int total = routerDao.queryFreeRouterCount(param);
		return new TableResult<Router>(total, routers);
	}
	
	@Override
	public BaseResult<Router> callback(String devNo, String mac) {
		int updateCount = routerDao.updateRouterByMac(devNo, mac);
		if (updateCount == 0) {
			return new BaseResult<>(false, "设备注册失败，请联系管理员");
		}
		RouterParam param = new RouterParam();
		param.setDevNo(devNo);
		Router router = routerDao.queryRouterByDevNo(param);
		if (router == null) {
			return new BaseResult<>(false, devNo + ": 设备不存在");
		}
		return new BaseResult<Router>(router);
	}
	
	@Override
	public BaseResult<String> deleteRouter(List<Router> routers, String createBy) {
		int count = routerDao.deleteRouterByMac(routers, createBy);
		if (count > 0) {
			return new BaseResult<String>("设备删除成功");
		}
		return new BaseResult<String>(false, "设备删除失败");
	}
	
	@Override
	public BaseResult<String> deleteRouterByNo(String devNo, String createBy) {
		int count = routerDao.deleteRouterByNo(devNo, createBy);
		if (count > 0) {
			return new BaseResult<String>("设备删除成功");
		}
		return new BaseResult<String>(false, "设备删除失败");
	}
	
	@Override
	public BaseResult<String> updateRouter4Channel(List<Router> routers, String channelId) {
		int count = routerDao.updateRouterChannel(routers, channelId);
		if (count > 0) {
			return new BaseResult<String>("设备绑定成功");
		}
		return new BaseResult<String>(false, "设备绑定失败");
	}
	
	@Override
	public BaseResult<String> removeRouterChannel(List<Router> routers, String channelId) {
		int count = routerDao.removeRouterChannel(routers, channelId);
		if (count > 0) {
			return new BaseResult<String>("设备解绑成功");
		}
		return new BaseResult<String>(false, "设备解绑失败");
	}
	
	@Override
	public BaseResult<String> bindingRouter4Channel(String devNo, String channelId) {
		int count = routerDao.updateRouterChannelByNo(devNo, channelId);
		if (count > 0) {
			return new BaseResult<String>("设备绑定成功");
		}
		return new BaseResult<String>(false, "设备绑定失败");
	}
	
	@Override
	public BaseResult<String> updateRouterAddress(Router router) {
		int count = routerDao.updateRouterAddress(router);
		if (count > 0) {
			return new BaseResult<String>(router.getDevNo() + "修改成功");
		}
		return new BaseResult<String>(false, router.getDevNo() + "修改失败");
	}
	
	@Override
	public BaseResult<String> updateRouterFirmware(Router router) {
		int count = routerDao.updateRouterFirmware(router);
		if (count > 0) {
			return new BaseResult<String>(router.getMac() + "修改成功");
		}
		return new BaseResult<String>(false, router.getMac() + "修改失败");
	}
	
	@Override
	public List<Router> queryRouterByChannel(User user, String channelId) {
		RouterParam param = new RouterParam();
		String createBy = service.getCreateBy(user);
		param.setCreateBy(createBy);
		param.setChannelId(channelId);
		List<Router> routers = routerDao.queryRouter4Table(param);
		return routers;
	}

}
