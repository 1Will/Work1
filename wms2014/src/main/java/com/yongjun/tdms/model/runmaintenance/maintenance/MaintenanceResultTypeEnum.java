package com.yongjun.tdms.model.runmaintenance.maintenance;

import com.yongjun.pluto.dao.hibernate.EnumType;

public class MaintenanceResultTypeEnum extends EnumType {

	@Override
	protected Class getEnumClass() {
		return MaintenanceResultType.class;
	}

}
