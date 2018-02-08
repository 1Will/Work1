package com.yongjun.tdms.dao.asset.device.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.asset.device.ManagementLevel;

public class ManagementLevelEnum extends EnumType {

	@Override
	protected Class getEnumClass() {
		return ManagementLevel.class;
	}

}
