package org.rapp.service.statistical;

import java.util.List;

import org.rapp.pojo.account.User;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.echart.EChartsData;
import org.rapp.pojo.statistical.AdvertStatistical;
import org.rapp.pojo.statistical.StatisticalParam;

/**
 * 数据统计
 * @author 张芳
 *
 */
public interface DataStatisticalService {

	/**
	 * 统计名下广告推送次数
	 * @param param
	 */
	BaseResult<List<AdvertStatistical>> statisticalAdvertByAdvert(User user, StatisticalParam param);

	/**
	 * 进半个月推送情况
	 * @param user
	 * @return
	 */
	BaseResult<List<AdvertStatistical>> statisticalAdvertByDate(StatisticalParam param);

	/**
	 * 近半月路由器推送情况
	 * @param param
	 * @return
	 */
	BaseResult<List<AdvertStatistical>> statisticalRouterByDate(StatisticalParam param);
	
	/**
	 * 广告当日每小时的推送量
	 * @param param
	 * @return
	 */
//	BaseResult<List<AdvertStatistical>> pushAdvertByHours(StatisticalParam param);

	/**
	 * 统计数据封装
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	BaseResult<EChartsData> setEChartsData(List<AdvertStatistical> list);

	/**
	 * 统计在线设备/活跃设备
	 * @param type
	 * @param createBy
	 * @return
	 */
	int statisticalRouterOnlineOrActive(String statisticalDate, int type, String createBy);
	
	
}
