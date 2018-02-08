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

<#-- $Id: faultAnalysisByDepartmentList.ftl 11122 2009-09-10 15:50:35Z wliu $ -->

<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('faultAnalysisByDepartment.title')}">
	<@ww.form  name="'listForm'" action="'listFaultAnalysisByDepartment'" method="'post'" validate="true">
		<@ww.token name="listFaultAnalysisByDepartmentToken"/>
		<#include "faultAnalysisByDepartmentSearcher.ftl"/>
		<@buttonBar>
	 		<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
	 	</@buttonBar>
		<@list title="${action.getText('faultAnalysisByDepartment.list.title')}" excel=false setupTable=false
			includeParameters="departmentId|month" fieldMap="like:" >
			<#--
			<@vcolumn title="${action.getText('serialNo')}" property="">
				#{itemNo}
			<@alignCenter/>
	        </@vcolumn>
	        <#assign itemNo = itemNo+1/>
	        -->
	        <@vcolumn title="${action.getText('faultAnalysisByDepartment.department')}" property="department.name">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('faultAnalysisByDepartment.totalFaultCount')}" property="totalFaultCount">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('faultAnalysisByDepartment.totalStopTime')}" property="totalStopTime" format="${action.getText('currencyFormat')}">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('faultAnalysisByDepartment.faultFrequency')}" property="faultFrequency" format="${action.getText('currencyFormat')}">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('faultAnalysisByDepartment.faultStopFrequency')}" property="faultStopFrequency" format="${action.getText('currencyFormat')}">
	        </@vcolumn>
	        <@vcolumn title="${action.getText('faultAnalysisByDepartment.month')}" property="month" format="yyyy-MM">
	        </@vcolumn>
	        <#if (object.upOrDown?string)=='UP'>
              <#assign upOrDown="${action.getText('faultAnalysisByDepartment.up')}"/>
            </#if>
            <#if (object.upOrDown?string)=='DOWN'>
              <#assign upOrDown="${action.getText('faultAnalysisByDepartment.down')}"/>
            </#if>
            <#if (object.upOrDown?string)=='EQUAL'>
              <#assign upOrDown="${action.getText('faultAnalysisByDepartment.equal')}"/>
            </#if>
            <@vcolumn title="${action.getText('faultAnalysisByDepartment.upOrDown')}">
            	${upOrDown}
	        </@vcolumn>

		</@list>
	</@ww.form>

</@htmlPage>