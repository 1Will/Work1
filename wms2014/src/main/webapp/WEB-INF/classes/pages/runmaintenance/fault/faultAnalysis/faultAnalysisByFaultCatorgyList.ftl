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

<#-- $Id: faultAnalysisByFaultCatorgyList.ftl 11122 2009-09-11 16:9:35Z wliu $ -->

<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('faultAnalysisByFaultCatorgy.title')}">
	<@ww.form  name="'listForm'" action="'listFaultAnalysisByFaultCatorgy'" method="'post'" validate="true">
		<@ww.token name="listFaultAnalysisByFaultCatorgyToken"/>
		<#include "faultAnalysisByFaultCatorgySearcher.ftl"/>
		<@buttonBar>
	 		<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
	 	</@buttonBar>
		<#if deviceId?exists>
      		<@ww.hidden name="'faultCatorgyId'" value="#{faultCatorgyId}"/>
    	</#if>
		<#assign itemNo=1/>
		<@list title="${action.getText('faultAnalysisByFaultCatorgy.list.title')}" excel=false setupTable=false
			includeParameters="faultCatorgyId|month" fieldMap="like:" >
			<#--
			<@vcolumn title="${action.getText('serialNo')}" property="">
				#{itemNo}
			<@alignCenter/>
	        </@vcolumn>
	        <#assign itemNo = itemNo+1/>
	        -->
	        <@vcolumn title="${action.getText('faultAnalysisByFaultCatorgy.faultCatorgy.value')}" property="faultCatorgy.value">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('faultAnalysisByFaultCatorgy.totalFaultCount')}" property="totalFaultCount">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('faultAnalysisByFaultCatorgy.totalStopTime')}" property="totalStopTime" format="${action.getText('currencyFormat')}">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('faultAnalysisByFaultCatorgy.faultFrequency')}" property="faultFrequency" format="${action.getText('currencyFormat')}">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('faultAnalysisByFaultCatorgy.faultStopFrequency')}" property="faultStopFrequency" format="${action.getText('currencyFormat')}">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('faultAnalysisByFaultCatorgy.month')}" property="month" format="yyyy-MM">
	        </@vcolumn>
	        <#if (object.upOrDown?string)=='UP'>
              <#assign upOrDown="${action.getText('faultAnalysisByFaultCatorgy.up')}"/>
            </#if>
            <#if (object.upOrDown?string)=='DOWN'>
              <#assign upOrDown="${action.getText('faultAnalysisByFaultCatorgy.down')}"/>
            </#if>
            <#if (object.upOrDown?string)=='EQUAL'>
              <#assign upOrDown="${action.getText('faultAnalysisByFaultCatorgy.equal')}"/>
            </#if>
            <@vcolumn title="${action.getText('faultAnalysisByFaultCatorgy.upOrDown')}">
            	${upOrDown}
	        </@vcolumn>

		</@list>
	</@ww.form>

</@htmlPage>