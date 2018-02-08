package com.yongjun.tdms.dao.prophase.business.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.prophase.business.SubscribeTypeStatus;


public class SubscribeTypeEnum extends EnumType{

	@Override
	protected Class getEnumClass() {
		// TODO Auto-generated method stub
		return SubscribeTypeStatus.class;
	}

}
