<#--
	Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All
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


<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('contractAdministrators.list')}">
	<@ww.form name="'listFrom'" namespace="'/personnelFile'" action="'searchContractAdministrator'" method="'post'" >
		<@ww.token name="searchContractAdministratorToken"/>
		<#include "./contractAdministratorSearcher.ftl" />
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@buttonBar>
			<@vsubmit name="'search'" cssClass="'button'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/personnelFile/editContractAdministrator.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
       		</#if>
        </@buttonBar>
	<@list title="${action.getText('contractAdministratorList')}" includeParameters="personnelCode|personnelName|contractCode|contractType.id|becomes.id|contractAdministrator.signingDate_start|contractAdministrator.signingDate_end
	|contractAdministrator.contractEndDate_start|contractAdministrator.contractEndDate_end|readOnly|onlyInvalid|onlyValid"
	 fieldMap="like:personnelCode|personnelName|contractCode,date:contractAdministrator.signingDate_start|contractAdministrator.signingDate_end
	|contractAdministrator.contractEndDate_start|contractAdministrator.contractEndDate_end" >
		<#if !(action.isReadOnly())>
			<@vlh.checkbox property="id" name="contractAdministratorIds">
	            <@vlh.attribute name="width" value="30" />
	        </@vlh.checkbox>
        </#if>
	    <@vcolumn title="${action.getText('contractAdministrator.personnelCode')}" property="personnelCode" sortable="asc">
	     <#if !object.disabled>
            <a href="${req.contextPath}/personnelFile/editContractAdministrator.html?contractAdministrator.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.personnelCode}</a>
            <#else>
            ${object.personnelCode}
         </#if>
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('contractAdministrator.personnelName')}" property="personnelName.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('contractAdministrator.contractCode')}" property="contractCode" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('contractAdministrator.contractType')}" property="contractType.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <#--
        <@vcolumn title="${action.getText('contractAdministrator.becomes')}" property="becomes.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>-->
        <@vcolumn title="${action.getText('contractAdministrator.signingDate')}" property="signingDate" format="yyyy-MM-dd" sortable="desc">
        	<@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('contractAdministrator.contractEndDate')}" property="contractEndDate" format="yyyy-MM-dd" sortable="desc">
        	<@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('contractAdministrator.duty')}" property="duty.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('contractAdministrator.dept')}" property="dept.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
         <@vcolumn title="${action.getText('contractAdministrator.expectationStipend')}" property="expectationStipend" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
       <#-- <@vcolumn title="${action.getText('contractAdministrator.crft')}" property="crft.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('contractAdministrator.level')}" property="level.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('contractAdministrator.vntu')}" property="vntu.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
         <@vcolumn title="${action.getText('contractAdministrator.trialPeriods')}" property="trialPeriods" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
         <@vcolumn title="${action.getText('contractAdministrator.effectDate')}" property="effectDate" format="yyyy-MM-dd" sortable="desc">
        	<@alignCenter/>
        </@vcolumn>
         <@vcolumn title="${action.getText('contractAdministrator.trialWage')}" property="trialWage" sortable="asc">
            <@alignLeft/>
        </@vcolumn>-->
	</@list>
	<#if !first>
	  <#if !(action.isReadOnly())>
       <@buttonBar>
		   <@crm_disabledOrEnabled_button message="${action.getText('contractAdministrators.info')}" boxName="contractAdministratorIds" jsFunctionName="checkInvalidParms()"/>
	   </@buttonBar>
       </#if>
	</#if>
</@ww.form>
</@htmlPage>
