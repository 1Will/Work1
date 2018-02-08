package com.yongjun.tdms.dao.notice.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.notice.SendStatus;

public class SendStatusEnum extends EnumType{

	@Override
	protected Class getEnumClass() {
		// TODO Auto-generated method stub
		return SendStatus.class;
	}

}
