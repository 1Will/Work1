package com.yongjun.tdms.dao.runmaintenance.repair.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;

public class PreRepairModelEnum extends EnumType {

	@Override
	protected Class getEnumClass() {
		return PreRepairModel.class;
	}

}
