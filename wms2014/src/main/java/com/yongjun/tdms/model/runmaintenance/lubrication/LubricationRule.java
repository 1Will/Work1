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
package com.yongjun.tdms.model.runmaintenance.lubrication;


/**
 * <p>Title: LubricationRule
 * <p>Description: 润滑标准类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: LubricationRule.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class LubricationRule extends BaseLubrication {

	private static final long serialVersionUID = -3220477595020987388L;
	private String preExePeople;	//上次执行人
	private String lubricatingOilMeasureUnit;
	
	public LubricationRule() {
		
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getLubricatingOilMeasureUnit() {
		return lubricatingOilMeasureUnit;
	}

	public void setLubricatingOilMeasureUnit(String lubricatingOilMeasureUnit) {
		this.lubricatingOilMeasureUnit = lubricatingOilMeasureUnit;
	}

	public String getPreExePeople() {
		return preExePeople;
	}

	public void setPreExePeople(String preExePeople) {
		this.preExePeople = preExePeople;
	}
}
