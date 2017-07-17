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
	<@ww.form name="'listForm'" action="'searchContractManagementWindowAction'" method="'post'">
		<@ww.token name="searchContractManagementWindowActionToken"/>
		<#include "./contractManagementSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms()'"/>
        </@buttonBar>
        <@list title="${action.getText('contractManagementAction.list')}" 
            includeParameters="contractManagement.code|contractManagement.applyProduc|contractManagement.type.id|contractManagement.state.id|contractManagement.severityDegree.id|contractManagement.question|contractManagement.resolveProject|contractManagement.innerPrompt|contractManagement.remark|onlyInvalid|onlyValid" 
        	fieldMap="like:" >
        	<#if !object.disabled>
        		<#if object.customerInfo.additional?exists>
	        	 <@vcolumn title="${action.getText('contractManagement.code')}" property="code" sortable="asc">
		            
        			<#if object.project?exists>
		            <a href="javascript: returnDialog(new Array(#{object.id},'${object.contractName}','${object.customerInfo.name}','${object.linkman.name}','#{object.customerInfo.id}','#{object.linkman.id}','${object.telephone}','${object.payType.code}','#{object.contractMoney}','${object.customerInfo.additional.bankAccount}','${object.project.name}'));">
		            	${object.code}
		            </a>
		            <#else>
		            <a href="javascript: returnDialog(new Array(#{object.id},'${object.contractName}','${object.customerInfo.name}','${object.linkman.name}','#{object.customerInfo.id}','#{object.linkman.id}','${object.telephone}','${object.payType.code}','#{object.contractMoney}','${object.customerInfo.additional.bankAccount}',''));">
		            	${object.code}
		            </a>
		            </#if>
		            <@alignLeft/>
		        </@vcolumn>
		        <#else>
			        <@vcolumn title="${action.getText('contractManagement.code')}" property="code" sortable="asc">
			            <a href="javascript: returnDialog(new Array(#{object.id},'${object.contractName}','${object.customerInfo.name}','${object.linkman.name}','#{object.customerInfo.id}','#{object.linkman.id}','${object.telephone}','${object.payType.code}','#{object.contractMoney}','',<#if object.project?exists>'${object.project.name?if_exists}'<#else>''</#if>));">
			            	${object.code}
			            </a>
			            <@alignLeft/>
			        </@vcolumn>
		        </#if>
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
	            	 ${object.ciemdinghTime?if_exists}            	
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
            </#if>
         </@list>
    </@ww.form>
</@htmlPage>
