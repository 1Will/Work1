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
<#-- $Id: maintenanceRecordSelector.ftl 2009-09-29 16:00:50Z wliu $-->
<#include "../../../includes/eam2008.ftl" />

<@framePage title="">
<@ww.form  name="'listForm'" action="'listMaintenanceRecord'" method="'post'">
	<@ww.token name="listMaintenanceRecordToken"/>
	<#assign itemNo=1/>
	<@list title="" 
		includeParameters="deviceId" fieldMap="like:" excel=false setupTable=false >
		<@vcolumn title="${action.getText('maintenanceRecord.itemNo')}">
			${itemNo}
			<@alignCenter/>
		</@vcolumn>
		<#assign itemNo = itemNo+1/>
		<@vcolumn title="${action.getText('maintenanceRecord.plan.planNo')}" property="plan.planNo" >
        	<#--${object.plan.planNo}-->
        	<@alignLeft />
		</@vcolumn>
		<@vcolumn title="${action.getText('maintenanceRecord.plan.resultType.value')}" property="plan.resultType.value" >
			<@alignCenter/>
		</@vcolumn>
		<@vcolumn title="${action.getText('maintenanceRecord.actualDate')}" property="actualDate" format="yyyy-MM-dd">
			<@alignCenter/>
		</@vcolumn>
		<@vcolumn title="${action.getText('maintenanceRecord.dutyPeople.name')}" property="dutyPeople.name">
			<@alignLeft />
		</@vcolumn>
		<@vcolumn title="${action.getText('maintenanceRecord.comment')}" property="comment">
			<@alignRight/>
		</@vcolumn>
		
	</@list>
</@ww.form>
</@framePage>