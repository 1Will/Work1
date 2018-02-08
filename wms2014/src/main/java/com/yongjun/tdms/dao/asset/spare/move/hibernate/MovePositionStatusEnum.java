package com.yongjun.tdms.dao.asset.spare.move.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.asset.spare.move.MovePositionStatus;

public class MovePositionStatusEnum extends EnumType{

	@Override
	protected Class getEnumClass() {
		// TODO Auto-generated method stub
		return MovePositionStatus.class;
	}

}
