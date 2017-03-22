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

<#-- $Id: customerInfoList.ftl 2009-12-11 8:48:35Z wliu $ -->

<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('salaryStandard.search')}">
	<@ww.form name="'listForm'" action="'searchSalaryStandardAction'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchSalaryStandardActionToken"/>
		<#include "./salaryStandardSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms()'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/salaryStandard/editSalaryStandardAction.html"/>
       		</#if>
        </@buttonBar>
        <@list title="${action.getText('salaryStandard.list')}" 
            includeParameters="salaryStandard.code|salaryStandard.positionName|salaryStandard.salaryName|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:salaryStandard.code|salaryStandard.positionName|salaryStandard.salaryName" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="salaryStandardIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
         	<@vcolumn title="${action.getText('salaryStandard.code')}" property="code" sortable="desc" >
                <a href="editSalaryStandardAction.html?salaryStandard.id=#{object.id?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code?if_exists}</a>
				<@alignLeft/>
            </@vcolumn>
           <@vcolumn title="${action.getText('salaryStandard.positionName')}" property="positionName" sortable="desc">
            ${object.positionName?if_exists}
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('salaryStandard.salaryName')}" property="salaryName" sortable="desc">
	            	 ${object.salaryName?if_exists}            	
     			<@alignLeft/>
            </@vcolumn>
        </@list>
        <#if !first>
        <#if !(action.isReadOnly())>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('salaryStandard')}" boxName="salaryStandardIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
		</#if>
		</#if>
    </@ww.form>
</@htmlPage>
