package com.yongjun.tdms.dao.asset.spare.outWareHouse.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillStatus;

public class SpareOutBillStatusEnum extends EnumType{

	@Override
	protected Class getEnumClass() {
		return SpareOutBillStatus.class;
	}

}
