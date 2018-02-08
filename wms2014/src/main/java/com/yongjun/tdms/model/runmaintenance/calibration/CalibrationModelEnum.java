package com.yongjun.tdms.model.runmaintenance.calibration;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;

public class CalibrationModelEnum extends EnumType {

	@Override
	protected Class getEnumClass() {
		return PreRepairModel.class;
	}

}
