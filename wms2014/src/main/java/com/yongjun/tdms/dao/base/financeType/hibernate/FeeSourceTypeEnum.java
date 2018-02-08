package com.yongjun.tdms.dao.base.financeType.hibernate;

import com.yongjun.pluto.dao.hibernate.EnumType;
import com.yongjun.tdms.model.base.financeType.FeeSourceType;

/**
 * <p>Title: FeeSourceTypeEnum
 * <p>Description: 费用来源类型枚举类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: FeeSourceTypeEnum.java 9721 2007-12-25 07:29:01Z qsun $
 */
public class FeeSourceTypeEnum extends EnumType {

	@Override
	protected Class getEnumClass() {
		return FeeSourceType.class;
	}

}
