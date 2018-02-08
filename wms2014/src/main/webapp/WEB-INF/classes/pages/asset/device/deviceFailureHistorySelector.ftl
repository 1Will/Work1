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
<#-- $Id: deviceFailureHistorySelector.ftl 11436 2009-09-09 15:12:50Z wliu $-->
<#include "../../includes/eam2008.ftl" />

<@framePage title="${action.getText('failureHistory.title')}">
	<@ww.form  name="'listForm'" action="'listDeviceFailureHistory'" method="'post'">
		<@ww.token name="listDeviceFailureHistoryToken"/>
		<#if deviceId?exists>
      		<@ww.hidden name="'deviceId'" value="#{deviceId}"/>
    	</#if>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#assign itemNo=1/>
		<@list title="" excel=false setupTable=false
				includeParameters="device.id" 
        		fieldMap="like:" >
			<@vlh.checkbox property="id" name="deviceFailureHistoryIds">
				<@vlh.attribute name="width" value="30" />
			</@vlh.checkbox>
			<@vcolumn title="${action.getText('serialNo')}" property="">
				#{itemNo}
				<@alignCenter/>
	        </@vcolumn>
	        <#assign itemNo = itemNo+1/>
	        <@vcolumn title="${action.getText('failureHistory.billName')}" property="billNo">
	        <@alignLeft/>
	        </@vcolumn>
	        <@vcolumn title=" ${action.getText('failureHistory.devicePosition')} " property="devicePosition">
	        <@alignLeft/>
	        </@vcolumn>
	        <#--
	        <@vcolumn title="${action.getText('failureHistory.faultOccurDateTm')}" property="faultOccurDateTm" format="yyyy-MM-dd HH:mm">
	        </@vcolumn>
	        -->
	        <@vcolumn title="${action.getText('failureHistory.stopTimeBegin')}" property="stopTimeBegin" format="yyyy-MM-dd HH:mm">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('failureHistory.stopTimeEnd')}" property="stopTimeEnd" format="yyyy-MM-dd HH:mm">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('failureHistory.faultTimeConsuming')}" property="faultTimeConsuming">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('failureHistory.manager.name')}" property="manager.name">
	        <@alignLeft/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('failureHistory.repairPeople')}" property="repairPeople">
	        <@alignLeft/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('failureHistory.faultCause')}" property="faultCause">
	        <@alignLeft/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('failureHistory.faultSolution')}" property="faultSolution">
	        <@alignLeft/>
	        </@vcolumn>
	        <#if (object.faultFlag?string)=='true'>
              <#assign status="${action.getText('failureHistory.solution')}"/>
            <#else>
              <#assign status="${action.getText('failureHistory.noSolution')}"/>
            </#if>
            <@vcolumn title="${action.getText('failureHistory.faultFlag')}">
            	${status}
            <@alignLeft/>
            <@vlh.attribute name="width" value="50" />
	        </@vcolumn>
		</@list>
	</@ww.form>

</@framePage>