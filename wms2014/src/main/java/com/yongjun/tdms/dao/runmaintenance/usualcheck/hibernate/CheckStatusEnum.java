package com.yongjun.tdms.dao.runmaintenance.usualcheck.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.runmaintenance.usualcheck.CheckStatus;
public class CheckStatusEnum extends EnumType{
	@Override
	protected Class getEnumClass() {
		
		return CheckStatus.class;
	}

}
