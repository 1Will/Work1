/**
 * 
 */
package com.yongjun.tdms.dao.runmaintenance.fault.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.runmaintenance.fault.UpOrDown;

/**
 * @author Administrator
 *
 */
public class UpOrDownEnum extends EnumType {

	@Override
	protected Class getEnumClass() {
		return UpOrDown.class;
	}

}
