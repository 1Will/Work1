package com.yongjun.tdms.model.runmaintenance.maintenance;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;

public class MaintenanceModelEnum extends EnumType {
	
	@Override
	protected Class getEnumClass() {
		return PreRepairModel.class;
	}
}
