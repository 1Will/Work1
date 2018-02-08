package com.yongjun.tdms.dao.runmaintenance.wash.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.runmaintenance.wash.WashDetailResult;

public class WashDetailEnum extends EnumType {

	@Override
	protected Class getEnumClass() {
		return WashDetailResult.class;
	}

}
