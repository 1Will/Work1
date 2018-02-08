package com.yongjun.tdms.dao.runmaintenance.tooling.record.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.runmaintenance.tooling.record.DemarcateStatus;

public class DemarcateStatusEnum extends EnumType {

	@Override
	protected Class getEnumClass() {
		return DemarcateStatus.class;
	}

}
