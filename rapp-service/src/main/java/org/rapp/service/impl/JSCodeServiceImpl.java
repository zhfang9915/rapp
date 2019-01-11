package org.rapp.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.rapp.comm.exception.BaseServiceException;
import org.rapp.pojo.dto.param.JSCodeParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.JSCode;
import org.rapp.repository.JSCodeDao;
import org.rapp.service.JSCodeService;
import org.rapp.service.util.SourceCodeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JSCodeServiceImpl implements JSCodeService {
	
	@Resource
	JSCodeDao codeDao;

	@Override
	public TableResult<JSCode> queryJSCodes(JSCodeParam param) {
		List<JSCode> codes = codeDao.listJSCodes(param);
		int total = codeDao.countJSCodes(param);
		return new TableResult<>(total, codes);
	}

	@Override
	public BaseResult<JSCode> queryJSCodeById(String codeId) {
		JSCode code = codeDao.queryJSCodeById(codeId);
		if (code == null) {
			return new BaseResult<JSCode>(false, "未查询到该JS源码信息");
		}
		return new BaseResult<JSCode>(code);
	}

	@Override
	public BaseResult<List<JSCode>> queryJSCodeAll() {
		List<JSCode> codes = codeDao.queryJSCodeAll();
		if (codes == null) {
			return new BaseResult<List<JSCode>>(false, "无可用的JS源码信息");
		}
		return new BaseResult<List<JSCode>>(codes);
	}
	
	@Override
	public BaseResult<JSCode> addJSCode(JSCode code) {
		code.setCreateTime(new Date());
		code.setCode(SourceCodeFormat.sourceCodeEncode(code.getCode()));
		int insertCount = codeDao.insertJSCode(code);
		if (insertCount == 1) {
			return new BaseResult<JSCode>(code);
		}
		return new BaseResult<JSCode>(false, "新增JS源码失败");
	}

	@Override
	public BaseResult<String> removeJSCode(List<JSCode> codes) {
		try {
			int updateCount = codeDao.deleteJSCode(codes);
			if (updateCount > 0) {
				return new BaseResult<String>(true, "删除JS源码成功");
			}
			return new BaseResult<String>(false, "删除JS源码失败");
		} catch (Exception e) {
			throw new BaseServiceException("删除失败，该JS源码正在被使用！");
		}
	}

	@Override
	@Transactional
	public BaseResult<JSCode> editJSCode(JSCode code) {
		if (code.getIsDefault()==1) {
			//查询原有的默认模版
			JSCode existDefault = codeDao.defaultJsCode();
			if (existDefault != null) {
				//更新原有的模版未非默认
				codeDao.cancelDefault(existDefault);
			}
		}
		
		//更新当前模版的状态
		code.setCode(SourceCodeFormat.sourceCodeEncode(code.getCode()));
		int updateCoutn = codeDao.updateJSCode(code);
		if (updateCoutn == 1) {
			return new BaseResult<JSCode>(code);
		}
		return new BaseResult<JSCode>(false, "更新JS源码失败");
	}


}
