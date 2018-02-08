package com.yongjun.tdms.model.base.financeType;

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.pluto.model.tracking.OrganizationTracking;
import com.yongjun.tdms.model.BaseInfoEntity;

public class FinanceType extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking, OrganizationTracking {
	private static final long serialVersionUID = -1033966662025213471L;
	private String code;                                //财务分类代码
	private String name;                                //财务分类名称
    private Double yearDeprecitionScale;                //年折旧率
	private Integer deprecitionYearLimit;                //折旧年限
	private Double netSalvageScale;                     //净残值比率
	private String comment;                              //备注
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getNetSalvageScale() {
		return netSalvageScale;
	}

	public void setNetSalvageScale(Double netSalvageScale) {
		this.netSalvageScale = netSalvageScale;
	}

	@Override
	public int hashCode() {
		return this.getCode().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {return true;}
		if (!(o instanceof FinanceType)) {return false;}
		
		FinanceType financeType = (FinanceType)o;
		if (!this.getCode().equals(financeType.getCode())){
			return false;
		}
		return true;
	}

	public Integer getDeprecitionYearLimit() {
		return deprecitionYearLimit;
	}

	public void setDeprecitionYearLimit(Integer deprecitionYearLimit) {
		this.deprecitionYearLimit = deprecitionYearLimit;
	}

	public Double getYearDeprecitionScale() {
		return yearDeprecitionScale;
	}

	public void setYearDeprecitionScale(Double yearDeprecitionScale) {
		this.yearDeprecitionScale = yearDeprecitionScale;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
