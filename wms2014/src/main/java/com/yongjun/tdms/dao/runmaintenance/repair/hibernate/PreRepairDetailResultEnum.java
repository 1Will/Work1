package com.yongjun.tdms.dao.runmaintenance.repair.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairDetailResult;

public class PreRepairDetailResultEnum extends EnumType {

	@Override
	protected Class getEnumClass() {
		return PreRepairDetailResult.class;
	}

}
