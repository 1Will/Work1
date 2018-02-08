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
<#-- $Id: lubricationRecordSelector.ftl 2009-09-29 17:10:50Z wliu $-->
<#include "../../../includes/eam2008.ftl" />

<@framePage title="">
<@ww.form  name="'listForm'" action="'listLubricationRecord'" method="'post'">
	<@ww.token name="listLubricationRecordToken"/>
	<#assign itemNo=1/>
	<@list title="" 
		includeParameters="deviceId" fieldMap="like:" excel=false setupTable=false >
		<@vcolumn title="${action.getText('lubricationRecord.itemNo')}">
			${itemNo}
			<@alignCenter/>
		</@vcolumn>
		<#assign itemNo = itemNo+1/>
		<@vcolumn title="${action.getText('lubricationRecord.plan.planNo')}" property="plan.planNo" >
        	<#--${object.plan.planNo}-->
        	<@alignLeft />
		</@vcolumn>
		<@vcolumn title="${action.getText('lubricationRecord.position')}" property="position">
			<@alignLeft />
		</@vcolumn>
		<@vcolumn title="${action.getText('lubricationRecord.methodDsp')}" property="methodDsp">
			<@alignLeft />
		</@vcolumn>
		<@vcolumn title="${action.getText('lubricationRecord.lubricatingOil.name')}" property="lubricatingOil.name">
			<@alignLeft />
		</@vcolumn>
		<@vcolumn title="${action.getText('lubricationRecord.actualLubricatingOilQty')}" property="actualLubricatingOilQty">
			<@alignLeft />
		</@vcolumn>
		<@vcolumn title="${action.getText('lubricationRecord.actualExecDate')}" property="actualExecDate" format="yyyy-MM-dd">
			<@alignCenter/>
		</@vcolumn>
		<@vcolumn title="${action.getText('lubricationRecord.planExePeople')}" property="planExePeople">
			<@alignLeft />
		</@vcolumn>
		<@vcolumn title="${action.getText('lubricationRecord.actualExePeople')}" property="actualExePeople">
			<@alignLeft />
		</@vcolumn>
		<@vcolumn title="${action.getText('lubricationRecord.comment')}" property="comment">
			<@alignLeft />
		</@vcolumn>
		
	</@list>
</@ww.form>
</@framePage>