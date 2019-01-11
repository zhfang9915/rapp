package org.rapp.pojo.dto.param;

/**
 * JS代码搜索参数
 * @author 张芳
 *
 */
public class JSCodeParam extends BaseTableParam {

	private String codeId;
	
	private String codeName;

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	
	
}
