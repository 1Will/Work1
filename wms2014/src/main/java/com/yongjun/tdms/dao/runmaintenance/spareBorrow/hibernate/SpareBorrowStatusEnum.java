package com.yongjun.tdms.dao.runmaintenance.spareBorrow.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrowStatus;

public class SpareBorrowStatusEnum extends EnumType {
	@Override
	protected Class getEnumClass() {
		return SpareBorrowStatus.class;
	}

}
