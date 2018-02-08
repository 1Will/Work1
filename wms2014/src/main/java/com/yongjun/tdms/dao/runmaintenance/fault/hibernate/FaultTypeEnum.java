package com.yongjun.tdms.dao.runmaintenance.fault.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.runmaintenance.fault.FaultType;

public class FaultTypeEnum extends EnumType {
	@Override
	protected Class getEnumClass() {
		return FaultType.class;
	}
}
