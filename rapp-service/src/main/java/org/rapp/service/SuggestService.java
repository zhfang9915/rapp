package org.rapp.service;

import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.entry.Suggest;

/**
 * 建议服务接口
 * @author 张芳
 *
 */
public interface SuggestService {

	/**
	 * 保存建议
	 * @param suggest
	 * @return
	 */
	BaseResult<Suggest> saveSuggest(Suggest suggest);
}
