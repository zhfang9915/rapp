package org.rapp.repository;

import org.rapp.pojo.entry.Suggest;

/**
 * 建议持久化
 * @author 张芳
 *
 */
public interface SuggestDao {

	/**
	 * 保存建议
	 * @param suggest
	 * @return
	 */
	int insertSuggest(Suggest suggest);
}
