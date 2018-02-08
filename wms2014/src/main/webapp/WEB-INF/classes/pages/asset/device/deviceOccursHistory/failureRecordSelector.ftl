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
<#-- $Id: failureRecordSelector.ftl 2009-09-28 15:13:50Z wliu $-->
<#include "../../../includes/eam2008.ftl" />

<@framePage title="">
	<@ww.form  name="'listForm'" action="'listFailureRecord'" method="'post'">
		<@ww.token name="listFailureRecordToken"/>
		<#assign itemNo=1/>
		<@list title=""
			includeParameters="device.id" fieldMap="like:" excel=false setupTable=false >
			<@vcolumn title="${action.getText('failureRecord.itemNo')}" >
				#{itemNo}
				<@alignCenter/>
	        </@vcolumn>
	        <#assign itemNo = itemNo+1/>
	        <@vcolumn title="${action.getText('failureRecord.billName')}" property="billNo">
	        	<@alignLeft/>
	        </@vcolumn>
	        <@vcolumn title=" ${action.getText('failureRecord.devicePosition')} " property="devicePosition">
	        	<@alignLeft/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('failureRecord.faultOccurDateTm')}" property="faultOccurDateTm" format="yyyy-MM-dd HH:mm">
	        	<@alignCenter/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('failureRecord.stopTimeBegin')}" property="stopTimeBegin" format="yyyy-MM-dd HH:mm">
	        	<@alignCenter/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('failureRecord.stopTimeEnd')}" property="stopTimeEnd" format="yyyy-MM-dd HH:mm">
	        	<@alignCenter/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('failureRecord.faultTimeConsuming')}" property="faultTimeConsuming">
	        	<@alignRight/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('failureRecord.manager.name')}" property="manager.name">
	        	<@alignLeft/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('failureRecord.repairPeople')}" property="repairPeople">
	        	<@alignLeft/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('failureRecord.faultCause')}" property="faultCause">
	        	<@alignLeft/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('failureRecord.faultSolution')}" property="faultSolution">
	        	<@alignLeft/>
	        </@vcolumn>
	        <#if (object.faultFlag?string)=='true'>
              <#assign status="${action.getText('failureRecord.solution')}"/>
            <#else>
              <#assign status="${action.getText('failureRecord.noSolution')}"/>
            </#if>
            <@vcolumn title="${action.getText('failureRecord.faultFlag')}">
            	${status}
            <@alignLeft/>
            <@vlh.attribute name="width" value="50" />
	        </@vcolumn>
		</@list>
	</@ww.form>

</@framePage>