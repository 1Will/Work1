/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.model.base.codevalue;

/**
 * <p>Title: CodeConstants
 * <p>Description: 系统代码类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: CodeConstants.java 28143 2010-10-26 09:29:36Z hjia $
 */
public abstract class CodeConstants {
	/** 设备属性 */
	public static final String DEVICE_PROP = "001";
	/** 设备特种属性 */
	public static final String DEVICE_SPECIFICATION_PROF = "002";
	/** 设备状态 */
	public static final String DEVICE_STATUS = "003";
	/** 点检标准 */
	public static final String CHECK_POINT_TYPE = "004";
	/** 生产用途 */
	public static final String PRODUCT_USED_TYPE = "005"; 
	/** 管理状态 */
	public static final String MANAGE_STATUS = "006";
	/** 供应商级别 */
	public static final String SUPPLIER_LEVEL = "007";
	/** 供应商所属行业 */
	public static final String TRADE_TYPE = "008";
	/** 供应商类型 */ 
	public static final String SUPPLIER_TYPE = "009";
	/** 公司性质 */
	public static final String COMPANY_TYPE = "010";
	/** 计量单位 */
	public static final String PRICKLE  = "011";
	/** 文档种类 */
	public static final String DOCUMENT_TYPE = "012";
	/** 备件种类 */
	public static final String SPARE_TYPE = "013";
	/** 车型 */
	public static final String VEHICLE_TYPE = "014";
	/** 工装状态 */
	public static final String TOOLING_STATUS = "015";
	/** 润滑油种类 */
	public static final String LUBRICATION_OIL_TYPE = "016";
	/** 容量计量 */
	public static final String CAPACITY_MEASURE = "017";
	/** 维修级别 */
	public static final String REPAIR_LEVEL = "018";
	/** 大项修种类 */
	public static final String YEAR_REPAIR_CATEGORY = "019";
	/** 大项修技术等级 */
	public static final String YEAR_TECHNICAL_LEVEL = "020";
	/** 润滑方法 */
	public static final String LUBRICATION_METHOD = "021";
	/** 报废类别 */
	public static final String DISCARD_CATEGORY = "022";
	/** 注册资金 */
	/**public static final String REGISTERED_FUNDS="023";
	/**外币种类 */
	public static final String FOREIGN_CURRENCY_CATEGORY="023";
	/** 精大稀 */
	public static final String EXCELLENT_BIG_SPARSE="024";
	/** 保养类别 */
	public static final String MAINTENANCE_CATEGORY="025";
	/** 费用类别 */
	public static final String FEE_SOURCE_TYPE="026";
	/** 分公司 */
	public static final String FILIALE="027";
	/** 故障分类 */
	public static final String FAULT_CATEGORY="028";
	/** 库位类型 */
	public static final String LOCAL_TYPE = "029";
	/** 承载类型 */
	public static final String BEARLOAD = "030";
	/** 混放模式 */
	public static final String MIXMODE = "031";
	/** 相同备件存放 */
	public static final String SAMESTORAGE = "032";
	/**单据类型*/
	public static final String DETAIL_KIND = "033";
	/**库存级别*/
	public static final String STORAGE_GRADE  = "034";
	
	/** 设备正常状态 */
	public static final String DEVICE_NORMAL_STATUS = "DEVICE_NORMAL";
	/** 设备维修状态 */
	public static final String DEVICE_REPAIR_STATUS = "DEVICE_REPAIR";
	/** 设备报废状态 */
	public static final String DEVICE_DISCARD_STATUS = "DEVICE_DISCARD";
	/** 设备半报废状态 */
	public static final String DEVICE_DISCARDING_STATUS = "DEVICE_DISCARDING";
	/** 设备封存状态 */
	public static final String DEVICE_DEMARCATE_STATUS = "DEVICE_DEMARCATE";
	/** 设备闲置状态 */
	public static final String DEVICE_UNUSED_STATUS = "DEVICE_UNUSED";
	/** 设备托管状态 */
	public static final String DEVICE_TRUSTEESHIP_STATUS = "DEVICE_TRUSTEESHIP";
	/** 工装正常状态 */
	public static final String TOOLING_NORMAL_STATUS = "TOOLING_NORMAL";
	/** 工装领用状态 */
	public static final String TOOLING_BORROW_STATUS = "TOOLING_BORROW";
	/** 工装维修状态 */
	public static final String TOOLING_REPAIR_STATUS = "TOOLING_REPAIR";
	/** 工装的托管状态 */
	public static final String TOOLING_TRUSTEESHIP_STATUS = "TOOLING_TRUSTEESHIP";
	/** 工装报废状态 */
	public static final String TOOLING_DISCARD_STATUS = "TOOLING_DISCARD";
	/** 工装[变更]整修状态 */
	public static final String TOOLING_CHANGE_STATUS = "TOOLING_CHANGE";
	/** 工装封存状态 */
	public static final String TOOLING_DEMARCATE_STATUS = "TOOLING_DEMARCATE";
	/** 工装闲置状态 */
	public static final String TOOLING_UNUSED_STATUS = "TOOLING_UNUSED";
	/** 工装失效状态 */
	public static final String TOOLING_DISABLED_STATUS = "TOOLING_DISABLED";
	/** 供应商外协类型 */
	public static final String SUPPLIER_EXTERNAL_HELP_TYPE = "SUPPLIER_EXTERNAL_HELP";
	/** 供应商优秀等级 */
	public static final String SUPPLIER_LEVEL_A = "SUPPLIER_LEVEL_A";
    /** 供应商良好等级 */
	public static final String SUPPLIER_LEVEL_B = "SUPPLIER_LEVEL_B";
	/** 供应商合格等级 */
	public static final String SUPPLIER_LEVEL_C = "SUPPLIER_LEVEL_C";
	/** 供应商不合格等级 */
	public static final String SUPPLIER_LEVEL_D = "SUPPLIER_LEVEL_D";
	/** 预算内 */
	public static final String BUDGET_IN = "IN_BUDGET";
	/** 预算外 */
	public static final String BUDGET_OUT = "OUT_BUDGET";
	
}
