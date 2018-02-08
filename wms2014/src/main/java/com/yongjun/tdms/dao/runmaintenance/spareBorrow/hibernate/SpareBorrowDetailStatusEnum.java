package com.yongjun.tdms.dao.runmaintenance.spareBorrow.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrowDetailStatus;

public class SpareBorrowDetailStatusEnum extends EnumType{

	@Override
	protected Class getEnumClass() {
		return SpareBorrowDetailStatus.class;
	}

}
