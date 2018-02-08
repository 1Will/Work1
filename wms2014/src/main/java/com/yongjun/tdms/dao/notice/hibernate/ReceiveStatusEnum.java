package com.yongjun.tdms.dao.notice.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.notice.Receive;

public class ReceiveStatusEnum extends EnumType{

	@Override
	protected Class getEnumClass() {
		// TODO Auto-generated method stub
		return Receive.class;
	}

}
