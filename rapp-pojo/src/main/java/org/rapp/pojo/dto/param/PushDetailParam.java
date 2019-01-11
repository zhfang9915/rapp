package org.rapp.pojo.dto.param;
/**
 * 推送明细查询
 * @author 张芳
 *
 */
public class PushDetailParam extends BaseTableParam{

	private String createBy;
	
	private String advertId;

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getAdvertId() {
		return advertId;
	}

	public void setAdvertId(String advertId) {
		this.advertId = advertId;
	}
	
	
}
