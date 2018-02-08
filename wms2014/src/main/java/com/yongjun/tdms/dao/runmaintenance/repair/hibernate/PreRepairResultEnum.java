package com.yongjun.tdms.dao.runmaintenance.repair.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairResult;

public class PreRepairResultEnum extends EnumType {

	@Override
	protected Class getEnumClass() {
		return PreRepairResult.class;
	}

}
