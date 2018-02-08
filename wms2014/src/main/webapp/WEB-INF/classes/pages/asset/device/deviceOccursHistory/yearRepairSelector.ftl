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
<#-- $Id: yearRepairSelector.ftl 2009-09-29 10:20:50Z wliu $-->
<#include "../../../includes/eam2008.ftl" />

<@framePage title="">
<@ww.form  name="'listForm'" action="'listYearRepair'" method="'post'">
	<@ww.token name="listYearRepairToken"/>
	<#assign itemNo=1/>
	<@list title="" 
		includeParameters="deviceId" fieldMap="like:" excel=false setupTable=false >
		<@vcolumn title="${action.getText('yearRepair.itemNo')}">
			${itemNo}
			<@alignCenter/>
		</@vcolumn>
		<#assign itemNo = itemNo+1/>
		<@vcolumn title="${action.getText('yearRepair.proc.planNo')}" property="proc.planNo" >
        	<#--${object.proc.planNo}-->
        	<@alignLeft />
		</@vcolumn>
		<@vcolumn title="${action.getText('yearRepair.department.name')}" property="department.name" >
			<@alignLeft />
		</@vcolumn>
		<#if object.externalHelpFlag == 'true'>
             <#assign externalHelpFlag="${action.getText('externalHelpFlag.true')}"/>
		<#else>
	         <#assign externalHelpFlag="${action.getText('externalHelpFlag.false')}"/>
		</#if>
		<@vcolumn title="${action.getText('yearRepair.externalHelpFlag')}" >
			${externalHelpFlag}
			<@alignLeft />
		</@vcolumn>
		<#assign complexCoefficient=''/>
		<#if (object.machineFlag) && (object.electricalFlag) >
			<#assign complexCoefficient="${action.getText('complexCoefficient.machineFlag')}" + ',' + "${action.getText('complexCoefficient.electricalFlag')}"/>
		<#elseif object.machineFlag >
			<#assign complexCoefficient="${action.getText('complexCoefficient.machineFlag')}"/>
		<#elseif object.electricalFlag >
			<#assign complexCoefficient="${action.getText('complexCoefficient.electricalFlag')}"/>
		</#if>
		<@vcolumn title="${action.getText('yearRepair.complexCoefficient')}">
			${complexCoefficient}
			<@alignLeft/>
		</@vcolumn>
		<@vcolumn title="${action.getText('yearRepair.procBeginDate')}" property="procBeginDate" format="yyyy-MM-dd">
			<@alignCenter/>
		</@vcolumn>
		<@vcolumn title="${action.getText('yearRepair.procExecPeople.name')}" property="procExecPeople.name">
			<@alignLeft />
		</@vcolumn>
		<@vcolumn title="${action.getText('yearRepair.procDetailAllFee')}" property="procDetailAllFee">
			<@alignRight />
		</@vcolumn>
		<#--
		<@vcolumn title="${action.getText('yearRepair.content')}" property="content">
			<@alignLeft />
		</@vcolumn>
		-->
	</@list>
</@ww.form>
</@framePage>