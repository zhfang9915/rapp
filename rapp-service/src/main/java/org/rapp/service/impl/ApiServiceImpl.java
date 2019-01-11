package org.rapp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.math3.util.Pair;
import org.rapp.comm.util.CalendarUtil;
import org.rapp.comm.util.MD5Utils;
import org.rapp.comm.util.WeightRandomUtil;
import org.rapp.pojo.dto.param.RouterParam;
import org.rapp.pojo.entry.Advert;
import org.rapp.pojo.entry.JSCode;
import org.rapp.pojo.entry.Router;
import org.rapp.pojo.log.RouterState;
import org.rapp.repository.AdvertDao;
import org.rapp.repository.ApiDao;
import org.rapp.repository.JSCodeDao;
import org.rapp.repository.RouterDao;
import org.rapp.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * API接口服务类
 * @author 张芳
 *
 */
@Service
public class ApiServiceImpl implements ApiService {
	
	@Autowired
	RouterDao routerDao;
	
	@Autowired
	JSCodeDao codeDao;
	
	@Autowired
	AdvertDao advertDao;
	
	@Autowired
	ApiDao apiDao;

	@Override
	public boolean authentication(String token, Router router) {
		//通过设备ID和MAC地址计算md5
		String md5 = MD5Utils.getMD5(router.getDevNo() + router.getMac());
		if (md5.equals(token)) {
			return true;
		}
		return false;
	}

	@Override
	public JSCode getJSByChannelId(String channelId) {
		//查询当前渠道是否配置了JS
		JSCode code = codeDao.getJscodeByChannelId(channelId);
		if (code != null) {
			return code;
		}
		//未配置JS则查询默认的JS模版
		code = codeDao.defaultJsCode();
		if (code == null) {
			return null;
		}
		return code;
	}

	@Override
	public Router getRouter(RouterParam param) {
		return routerDao.queryRouterByDevNo(param);
	}
	
	@Override
	public List<Advert> queryAdvertsByChannel(String channelId) {
		List<Advert> adverts = advertDao.queryAdvertsByChannelId(channelId, CalendarUtil.getLongDate(new Date()));
		return adverts;
	}

	@Override
	public Advert getRandomAdvertByWeight(List<Advert> adverts) {
		//将广告信息放入算法
		List<Pair<Advert, Integer>> list = new ArrayList<>();
		for (Advert advert : adverts) {
			list.add(new Pair<Advert, Integer>(advert, advert.getWeight()));
		}
		WeightRandomUtil<Advert, Integer> random = new WeightRandomUtil<>(list);
		return random.random();
	}
	
	
	@Async
	@Override
	public void saveRouterState(RouterState rs) {
		apiDao.insertRouterState(rs);
	}
}
