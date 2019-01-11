package org.rapp.service.statistical.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.rapp.comm.util.CalendarUtil;
import org.rapp.pojo.account.User;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.echart.BarSeries;
import org.rapp.pojo.echart.EChartsData;
import org.rapp.pojo.statistical.AdvertStatistical;
import org.rapp.pojo.statistical.StatisticalParam;
import org.rapp.repository.ApiDao;
import org.rapp.repository.StatisticalDao;
import org.rapp.service.statistical.DataStatisticalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataStatisticalServiceImpl implements DataStatisticalService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StatisticalDao statisticalDao;
	
	@Autowired ApiDao apiDao;
	
	@Override
	public BaseResult<List<AdvertStatistical>> statisticalAdvertByAdvert(User user, StatisticalParam param) {
		try {
			param.setCreateBy(user.getUsername());
			List<AdvertStatistical> statisticals = statisticalDao.statisticalAdvertByAdvert(param);
			if (statisticals.size() == 0) {
				return new BaseResult<List<AdvertStatistical>>(false, "暂无数据");
			}
			
			return new BaseResult<List<AdvertStatistical>>(statisticals);
		} catch (Exception e) {
			logger.info(e.getMessage());
			return new BaseResult<List<AdvertStatistical>>(false, "系统异常");
		}
	}
	
	
	@Override
	public BaseResult<List<AdvertStatistical>> statisticalAdvertByDate(StatisticalParam param) {
		try {
			//判断日期 如果没有输入日期则查询最近15天的数据
			if (StatisticalParam.DAY.equals(param.getStatisticalType())) {//按天统计
				if (StringUtils.isBlank(param.getStartTime()) || StringUtils.isBlank(param.getEndTime())) {
					param.setStartTime(CalendarUtil.getAssignDate(-16, "yyyy-MM-dd"));
					param.setEndTime(CalendarUtil.getAssignDate(-1, "yyyy-MM-dd"));
				}
			}else if (StatisticalParam.HOUR.equals(param.getStatisticalType())) {//按日统计
				if (StringUtils.isBlank(param.getPushTime())) {
					param.setPushTime(CalendarUtil.getAssignDate(-1, "yyyy-MM-dd"));
				}
			}else if (StatisticalParam.MONTH.equals(param.getStatisticalType())) {
				if (StringUtils.isBlank(param.getStartTime()) || StringUtils.isBlank(param.getEndTime())) {
					Calendar cal = Calendar.getInstance();
				    int year = cal.get(Calendar.YEAR);
					param.setStartTime(year + "-01");
					param.setEndTime(year + "-12");
				}
			}else if (StatisticalParam.YEAR.equals(param.getStatisticalType())) {
				if (StringUtils.isBlank(param.getStartTime()) || StringUtils.isBlank(param.getEndTime())) {
					Calendar cal = Calendar.getInstance();
				    int year = cal.get(Calendar.YEAR);
					param.setStartTime(year + "");
					param.setEndTime(year + "");
				}
			}
			
			//查询统计数据
			List<AdvertStatistical> statisticals = statisticalDao.statisticalAdvertByDate(param);
			if (statisticals.size() == 0) {
				return new BaseResult<List<AdvertStatistical>>(false, "暂无数据");
			}
			return new BaseResult<List<AdvertStatistical>>(statisticals);
		} catch (Exception e) {
			logger.info(e.getMessage());
			return new BaseResult<List<AdvertStatistical>>(false, "系统异常");
		}
	}
	
//	@Override
//	public BaseResult<List<AdvertStatistical>> pushAdvertByHours(StatisticalParam param) {
//		//初始数据
//		List<AdvertStatistical> statisticals = new ArrayList<>();
//		for (int i = 0; i < 24; i++) {
//			AdvertStatistical as = new AdvertStatistical(0, 0 , i+"");
//			statisticals.add(as);
//		}
//		//查询统计数据
//		List<AdvertStatistical> temp = statisticalDao.pushAdvertByHours(param);
//		if (statisticals.size() > 0) {
//			for (AdvertStatistical as : statisticals) {
//				for (AdvertStatistical ast : temp) {
//					if (Integer.valueOf(ast.getName()) == Integer.valueOf(as.getName())) {
//						as.setPushCount(ast.getPushCount());
//						as.setClickCount(ast.getClickCount());
//					}
//				}
//			}
//			
//		}
//		return new BaseResult<List<AdvertStatistical>>(statisticals);
//	}
	
	
	@Override
	public BaseResult<List<AdvertStatistical>> statisticalRouterByDate(StatisticalParam param) {
		try {
			//判断日期 如果没有输入日期则查询最近15天的数据
			if (StatisticalParam.DAY.equals(param.getStatisticalType())) {//按天统计
				if (StringUtils.isBlank(param.getStartTime()) || StringUtils.isBlank(param.getEndTime())) {
					param.setStartTime(CalendarUtil.getAssignDate(-16, "yyyy-MM-dd"));
					param.setEndTime(CalendarUtil.getAssignDate(-1, "yyyy-MM-dd"));
				}
			}else if (StatisticalParam.HOUR.equals(param.getStatisticalType())) {//按日统计
				if (StringUtils.isBlank(param.getPushTime())) {
					param.setPushTime(CalendarUtil.getAssignDate(-1, "yyyy-MM-dd"));
				}
			}else if (StatisticalParam.MONTH.equals(param.getStatisticalType())) {
				if (StringUtils.isBlank(param.getStartTime()) || StringUtils.isBlank(param.getEndTime())) {
					Calendar cal = Calendar.getInstance();
				    int year = cal.get(Calendar.YEAR);
					param.setStartTime(year + "-01");
					param.setEndTime(year + "-12");
				}
			}else if (StatisticalParam.YEAR.equals(param.getStatisticalType())) {
				if (StringUtils.isBlank(param.getStartTime()) || StringUtils.isBlank(param.getEndTime())) {
					Calendar cal = Calendar.getInstance();
				    int year = cal.get(Calendar.YEAR);
					param.setStartTime(year + "");
					param.setEndTime(year + "");
				}
			}
			//查询统计数据
			List<AdvertStatistical> statisticals = statisticalDao.statisticalRouterByDate(param);
			if (statisticals.size() == 0) {
				return new BaseResult<List<AdvertStatistical>>(false, "暂无数据");
			}
			return new BaseResult<List<AdvertStatistical>>(statisticals);
		} catch (Exception e) {
			logger.info(e.getMessage());
			return new BaseResult<List<AdvertStatistical>>(false, "系统异常");
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public BaseResult<EChartsData> setEChartsData(List<AdvertStatistical> list) {
		List<String> category = new ArrayList<String>();
		List<Long> pushData=new ArrayList<Long>();
		List<Long> clickData=new ArrayList<Long>();
		for (AdvertStatistical ads : list) {
			category.add(ads.getName());
			pushData.add(ads.getPushCount());
			clickData.add(ads.getClickCount());
		}
		List<String> legend = new ArrayList<String>(Arrays.asList(new String[] { "推送量", "点击量" }));// 数据分组
		List<BarSeries> series = new ArrayList<BarSeries>();// 纵坐标
		series.add(new BarSeries(legend.get(0), pushData));
		series.add(new BarSeries(legend.get(1), clickData));
		EChartsData data = new EChartsData(legend, category, series);
		
		return new BaseResult<EChartsData>(data);
	}
	
	/**
	 * 统计在线设备和活跃设备
	 * @param type
	 * @param createBy
	 * @return
	 */
	@Override
	public int statisticalRouterOnlineOrActive(String statisticalDate, int type, String createBy) {
		Integer count = apiDao.statisticalOnlineOrActive(statisticalDate, type, createBy);
		if (count == null) {
			return 0;
		}
		return count;
	}

}
