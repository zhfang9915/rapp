package org.rapp.repository;

import java.util.List;

import org.rapp.pojo.statistical.AdvertStatistical;
import org.rapp.pojo.statistical.StatisticalParam;

/**
 * 推送查询
 * @author 张芳
 *
 */
public interface StatisticalDao {

	
	/**
	 * 广告统计量
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<AdvertStatistical> statisticalAdvertByAdvert(StatisticalParam param);
	
	/**
	 * 最近半月推送情况
	 * @param createBy
	 * @return
	 */
//	List<AdvertStatistical> statisticalAdvertDays(StatisticalParam param);
	
	
	/**
	 * 统计广告当日推送详情
	 * @param param
	 * @return
	 */
//	List<AdvertStatistical> pushAdvertByHours(StatisticalParam param);
	
	
	/**
	 * 按时间统计广告的推送量
	 * @param param
	 * @return
	 */
	List<AdvertStatistical> statisticalAdvertByDate(StatisticalParam param);
	
	/**
	 * 按时间统计路由器推送情况
	 * @param param
	 * @return
	 */
	List<AdvertStatistical> statisticalRouterByDate(StatisticalParam param);
	
}
