<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->
<#-- $Id: faultRepairSelector.ftl 2009-09-30 09:34:50Z wliu $-->

<#include "../../../includes/eam2008.ftl" />

<@framePage title="">
<@ww.form  name="'listForm'" action="'listFaultRepair'" method="'post'">
	<@ww.token name="listFaultRepairToken"/>
	<#assign itemNo=1/>
	<@list title="" 
		includeParameters="deviceId" fieldMap="like:" excel=false setupTable=false >
		<@vcolumn title="${action.getText('faultRepair.itemNo')}">
			${itemNo}
			<@alignCenter/>
		</@vcolumn>
		<#assign itemNo = itemNo+1/>
		<@vcolumn title="${action.getText('faultRepair.faultBill.billNo')}" property="faultBill.billNo" >
        	<#--${object.faultBill.billNo}-->
        	<@alignLeft />
		</@vcolumn>
		<@vcolumn title="${action.getText('faultRepair.department.name')}" property="department.name" >
			<@alignLeft />
		</@vcolumn>
		<#if object.externalHelpFlag == 'true'>
             <#assign externalHelpFlag="${action.getText('externalHelpFlag.true')}"/>
		<#else>
	         <#assign externalHelpFlag="${action.getText('externalHelpFlag.false')}"/>
		</#if>
		<@vcolumn title="${action.getText('faultRepair.externalHelpFlag')}" >
			${externalHelpFlag}
			<@alignLeft />
		</@vcolumn>
		<@vcolumn title="${action.getText('faultRepair.repairLevel.value')}" property="repairLevel.value" >
			<@alignCenter/>
		</@vcolumn>
		<@vcolumn title="${action.getText('faultRepair.finishedDate')}" property="finishedDate" format="yyyy-MM-dd">
			<@alignCenter/>
		</@vcolumn>
		<@vcolumn title="${action.getText('faultRepair.dutyPeople.name')}" property="dutyPeople.name">
			<@alignLeft />
		</@vcolumn>
		<@vcolumn title="${action.getText('faultRepair.allFee')}" property="allFee">
			<@alignRight />
		</@vcolumn>
		<#--
		<@vcolumn title="${action.getText('preRepair.content')}" property="content">
			<@alignLeft />
		</@vcolumn>
		-->
	</@list>
</@ww.form>
</@framePage>