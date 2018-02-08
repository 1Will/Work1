package com.yongjun.tdms.dao.notice.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.notice.Read;

public class ReadEnum extends EnumType{

	@Override
	protected Class getEnumClass() {
		// TODO Auto-generated method stub
		return Read.class;
	}

}
