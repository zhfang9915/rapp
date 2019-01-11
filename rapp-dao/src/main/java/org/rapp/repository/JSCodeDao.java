package org.rapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.rapp.pojo.dto.param.JSCodeParam;
import org.rapp.pojo.entry.JSCode;

/**
 * JS源码持久化接口
 * @author 张芳
 *
 */
public interface JSCodeDao {

	/**
	 * 查询JS源码列表信息
	 * @param params
	 * @return
	 */
	List<JSCode> listJSCodes(JSCodeParam param);
	
	/**
	 * JS源码列表总数
	 * @param params
	 * @return
	 */
	int countJSCodes(JSCodeParam param);
	
	/**
	 * 根据ID获取JS源码
	 * @param codeId
	 * @return
	 */
	JSCode queryJSCodeById(@Param("codeId")String codeId);
	
	/**
	 * 查询所有的JS代码信息
	 * @return
	 */
	List<JSCode> queryJSCodeAll();
	
	/**
	 * 新增JS源码
	 * @param code
	 * @return
	 */
	int insertJSCode(JSCode code);
	
	/**
	 * 删除JS源码
	 * @param code
	 * @return
	 */
	int deleteJSCode(@Param("list")List<JSCode> codes);
	
	/**
	 * 更新JS源码
	 * @param code
	 * @return
	 */
	int updateJSCode(JSCode code);
	
	/**
	 * 查询默认的JS模版
	 * @return
	 */
	JSCode defaultJsCode();
	
	/**
	 * 设置默认或取消
	 * @return
	 */
	int cancelDefault(JSCode code);
	
	/**
	 * 根据渠道ID获取JS信息
	 * @param channelId
	 * @return
	 */
	JSCode getJscodeByChannelId(@Param("channelId")String channelId);
}
