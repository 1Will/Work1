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

<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('contractManagementAction.search')}">
	<@ww.form name="'listForm'" action="'searchContractManagementAction'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchContractManagementActionToken"/>
		<#include "./contractManagementSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms()'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/contractManagement/editContractManagementAction.html"/>
       		</#if>
        </@buttonBar>
        <@list title="${action.getText('contractManagementAction.list')}" 
            includeParameters="contractManagement.code|contractManagement.applyProduc|contractManagement.type.id|contractManagement.state.id|contractManagement.severityDegree.id|contractManagement.question|contractManagement.resolveProject|contractManagement.innerPrompt|contractManagement.remark|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:contractManagement.code|contractManagement.contractName|contractManagement.customerInfo.name|contractManagement.linkman.name|contractManagement.saleman.name|contractManagement.project.name|contractManagement.deparment.name" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="contractManagementIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
          	<@vcolumn title="${action.getText('contractManagement.code')}" property="code" sortable="desc" >
                <a href="editContractManagementAction.html?contractManagement.id=#{object.id?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code?if_exists}</a>
				<@alignLeft/>
            </@vcolumn>
           <@vcolumn title="${action.getText('contractManagement.contractName')}" property="contractName" sortable="desc">
            ${object.contractName?if_exists}
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contractManagement.customerInfo.name')}" property="customerInfo.name" sortable="desc">
            	<#if object.customerInfo?exists>
	            	 ${object.customerInfo.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
            <#-- 项目名称添加项 -->
			<@vcolumn title="${action.getText('contractManagement.project.name')}" property="project.name" sortable="desc">
				<#if object.project?exists>
	            	 ${object.project.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('contractManagement.linkman.name')}" property="linkman.name" sortable="desc">
				<#if object.linkman?exists>
	            	 ${object.linkman.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contractManagement.saleman.name')}" property="saleman.name" sortable="desc">
            	<#if object.saleman?exists>
	            	 ${object.saleman.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('contractManagement.deparment.name')}" property="deparment.name" sortable="desc">
           	 <#if object.deparment?exists>
	            	 ${object.deparment.name?if_exists}            	
            </#if>
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contractManagement.ciemdinghTime')}" property="ciemdinghTime" format="yyyy-MM-dd" sortable="desc">
     			<@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contractManagement.contractType')}" property="contractType.name" sortable="desc">
           	 <#if object.contractType?exists>
	            	 ${object.contractType.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contractManagement.state')}" property="state.name" sortable="desc">
           	 <#if object.state?exists>
	            	 ${object.state.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
        </@list>
        <#if !first>
        <#if !(action.isReadOnly())>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('contractManagement')}" boxName="contractManagementIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
		</#if>
		</#if>
    </@ww.form>
</@htmlPage>
