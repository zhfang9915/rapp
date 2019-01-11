package org.rapp.service.impl;

import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.entry.Suggest;
import org.rapp.repository.SuggestDao;
import org.rapp.service.SuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuggestServiceImpl implements SuggestService
{

	@Autowired
	SuggestDao suggestDao;
	
	@Override
	public BaseResult<Suggest> saveSuggest(Suggest suggest) {
		int count = suggestDao.insertSuggest(suggest);
		if (count == 1) {
			return new BaseResult<>(suggest);
		}
		return new BaseResult<>(false, "保存建议失败");
	}

	
}
