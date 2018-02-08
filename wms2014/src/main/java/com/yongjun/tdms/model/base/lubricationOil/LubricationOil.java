package com.yongjun.tdms.model.base.lubricationOil;

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.pluto.model.tracking.OrganizationTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.codevalue.CodeValue;

public class LubricationOil extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking, OrganizationTracking {
	private static final long serialVersionUID = 5398419828196326787L;
	private String code;                        //润滑油编码
	private String name;                        //润滑油名称
	private CodeValue measureUnit;             //计量单位
    private CodeValue category;                //润滑油分类	
    
	@Override
	public int hashCode() {
		return this.code.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {return true;}
		if (!(o instanceof LubricationOil)) {return false;}
		LubricationOil lubricationOil = (LubricationOil)o;
		if (!lubricationOil.getCode().equals(this.code)) {return false;}
		return true;
	}

	public CodeValue getCategory() {
		return category;
	}

	public void setCategory(CodeValue category) {
		this.category = category;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public CodeValue getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(CodeValue measureUnit) {
		this.measureUnit = measureUnit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
