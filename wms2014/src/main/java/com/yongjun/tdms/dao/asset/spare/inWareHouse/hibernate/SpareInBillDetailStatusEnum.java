package com.yongjun.tdms.dao.asset.spare.inWareHouse.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetailStatus;

public class SpareInBillDetailStatusEnum extends EnumType{

	@Override
	protected Class getEnumClass() {
		return SpareInBillDetailStatus.class;
	}

}
