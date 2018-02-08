package com.yongjun.tdms.dao.runmaintenance.checkpoint.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointProcResultType;

/**
 * @author qs
 * @version $Id: CheckPointProcResultEnum.java 7392 2007-09-15 09:25:57Z qsun $
 */
public class CheckPointProcResultEnum extends EnumType{

	@Override
	protected Class getEnumClass() {
		return CheckPointProcResultType.class;
	}

}
