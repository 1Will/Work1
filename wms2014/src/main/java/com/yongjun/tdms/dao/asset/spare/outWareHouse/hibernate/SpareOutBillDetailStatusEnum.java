package com.yongjun.tdms.dao.asset.spare.outWareHouse.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetailStatus;

public class SpareOutBillDetailStatusEnum extends EnumType{
	@Override
	protected Class getEnumClass() {
		return SpareOutBillDetailStatus.class;
	}

}
