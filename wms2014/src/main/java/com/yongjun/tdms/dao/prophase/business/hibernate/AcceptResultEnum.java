package com.yongjun.tdms.dao.prophase.business.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.prophase.business.AcceptResult;

public class AcceptResultEnum extends EnumType{

	@Override
	protected Class getEnumClass() {
		return AcceptResult.class;
	}

}
