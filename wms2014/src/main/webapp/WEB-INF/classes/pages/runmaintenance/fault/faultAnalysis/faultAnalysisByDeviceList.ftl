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

<#-- $Id: faultAnalysisByDeviceList.ftl 11122 2009-09-11 10:04:35Z wliu $ -->

<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('faultAnalysisByDevice.title')}">
	<@ww.form  name="'listForm'" action="'listFaultAnalysisByDevice'" method="'post'" validate="true">
		<@ww.token name="listFaultAnalysisByDeviceToken"/>
		<#include "faultAnalysisByDeviceSearcher.ftl"/>
		<@buttonBar>
	 		<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
	 	</@buttonBar>
		<#assign itemNo=1/>
		<@list title="${action.getText('faultAnalysisByDevice.list.title')}" excel=false setupTable=false
			includeParameters="deviceNo|month" fieldMap="like:deviceName|deviceNo" >
			<#--
			<@vcolumn title="${action.getText('serialNo')}" property="">
				#{itemNo}
			<@alignCenter/>
	        </@vcolumn>
	        <#assign itemNo = itemNo+1/>
	        -->
	        <@vcolumn title="${action.getText('faultAnalysisByDevice.device.deviceNo')}" property="deviceCard.deviceNo">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('faultAnalysisByDevice.device.name')}" property="deviceCard.name">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('faultAnalysisByDevice.totalFaultCount')}" property="totalFaultCount">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('faultAnalysisByDevice.totalStopTime')}" property="totalStopTime" format="${action.getText('currencyFormat')}">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('faultAnalysisByDevice.faultFrequency')}" property="faultFrequency" format="${action.getText('currencyFormat')}">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('faultAnalysisByDevice.faultStopFrequency')}" property="faultStopFrequency" format="${action.getText('currencyFormat')}">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('faultAnalysisByDevice.month')}" property="month" format="yyyy-MM">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('MTBF(时)')}" property="mtbf" format="${action.getText('currencyFormat')}">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('MTTF(时)')}" property="mttf" format="${action.getText('currencyFormat')}">
	        </@vcolumn>
	        <#if (object.upOrDown?string)=='UP'>
              <#assign upOrDown="${action.getText('faultAnalysisByDevice.up')}"/>
            </#if>
            <#if (object.upOrDown?string)=='DOWN'>
              <#assign upOrDown="${action.getText('faultAnalysisByDevice.down')}"/>
            </#if>
            <#if (object.upOrDown?string)=='EQUAL'>
              <#assign upOrDown="${action.getText('faultAnalysisByDevice.equal')}"/>
            </#if>
            <@vcolumn title="${action.getText('faultAnalysisByDevice.upOrDown')}">
            	${upOrDown}
	        </@vcolumn>

		</@list>
	</@ww.form>

</@htmlPage>