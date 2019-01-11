package org.rapp.service;

import java.util.List;

import org.rapp.pojo.dto.param.JSCodeParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.JSCode;

/**
 * JS源码服务接口
 * @author 张芳
 *
 */
public interface JSCodeService {

	/**
	 * 查询JS源码列表信息
	 * @param params
	 * @return
	 */
	TableResult<JSCode> queryJSCodes(JSCodeParam param);
	
	/**
	 * 根据ID获取JS源码
	 * @param codeId
	 * @return
	 */
	BaseResult<JSCode> queryJSCodeById(String codeId);
	
	/**
	 * 查询所有的JS信息
	 * @return
	 */
	BaseResult<List<JSCode>> queryJSCodeAll();
	
	/**
	 * 新增JS源码
	 * @param code
	 * @return
	 */
	BaseResult<JSCode> addJSCode(JSCode code);
	
	/**
	 * 删除JS源码
	 * @param code
	 * @return
	 */
	BaseResult<String> removeJSCode(List<JSCode> codes);
	
	/**
	 * 更新JS源码
	 * @param code
	 * @return
	 */
	BaseResult<JSCode> editJSCode(JSCode code);
}
