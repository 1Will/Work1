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

<#-- $Id: contactArchivesList.ftl 2009-12-08 13:50:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('returnPlan.title.check')}">
	<@ww.form name="'listFrom'" namespace="'/financialManagement'" action="'searchReturnPlanWindow'" method="'post'">
		<@ww.token name="searchReturnPlanWindowToken"/>
		<#include "./returnPlanSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
        </@buttonBar>
        <@list title="${action.getText('returnPlan.list.title')}" 
            includeParameters="contractManagement.code|returnPlan.contractManagement|returnPlan.paytime_start|returnPlan.paytime_end
            |returnPlan.planDate_start|returnPlan.planDate_end|customerInfo.code|customerInfo.name|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:contractManagement.code|returnPlan.contractManagement|customerInfo.code|customerInfo.name
        	,date:returnPlan.paytime_start|returnPlan.paytime_end|returnPlan.planDate_start|returnPlan.planDate_end" >
        	
    	    <#if !object.disabled>
    	   	 	<#if object.customerInfo.additional?exists>
		        	<@vcolumn title="${action.getText('contractManagement.code')}" property="contractManagement.code" sortable="asc">
			            <a href="javascript: returnDialog(new Array(#{object.id},'${object.contractManagement.contractName}','${object.customerInfo.name}','#{object.batch.id}','#{object.customerInfo.id}','#{object.sum}','${object.customerInfo.additional.bankAccount}'));">
			            	${object.contractManagement.code}
			            </a>
			            <@alignLeft/>
			        </@vcolumn>
		        <#else>
		        	<@vcolumn title="${action.getText('contractManagement.code')}" property="contractManagement.code" sortable="asc">
			            <a href="javascript: returnDialog(new Array(#{object.id},'${object.contractManagement.contractName}','${object.customerInfo.name}','#{object.batch.id}','#{object.customerInfo.id}','${object.sum}'));">
			            	${object.contractManagement.code}
			            </a>
		            <@alignLeft/>
		        </@vcolumn>
		        </#if>
	            <@vcolumn title="${action.getText('returnPlan.contractManagement')}" property="contractManagement.contractName" sortable="desc">
	            	<@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('customerInfo.code')}" property="customerInfo.code" sortable="desc">
	     			<@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('returnPlan.customerInfo')}" property="customerInfo.name" sortable="desc">
	     			<@alignLeft/>
	            </@vcolumn>
	             <@vcolumn title="${action.getText('returnPlan.contactArchives')}" property="contactArchives.name" sortable="desc">
	     			<@alignLeft/>
	            </@vcolumn>
	             <@vcolumn title="${action.getText('returnPlan.phone')}" property="phone" sortable="desc">
	     			<@alignRight/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('returnPlan.planDate')}" property="planDate" format="yyyy-MM-dd" sortable="desc">
	            	<@alignCenter/><#-- attributes="width:110;"-->
	            </@vcolumn>
	            <@vcolumn title="${action.getText('returnPlan.sum')}" property="sum" sortable="desc">
	            	<@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('returnPlan.batch')}" property="batch.name" sortable="desc">
	            	<@alignRight/>
	            </@vcolumn>
	             <#assign isOrNot=""/>
	            <#if (object.isOrNot)=='0'>
	            	<#assign isOrNot="${action.getText('是')}">
	            <#else>
	           	 	<#assign isOrNot="${action.getText('否')}">
			    </#if>
	            <@vcolumn title="${action.getText('returnPlan.isOrNot')}"sortable="desc">
	            	${isOrNot?if_exists}
	            	<@alignLeft/>
	            </@vcolumn>
	             <#assign notOrIs=""/>
	            <#if (object.notOrIs)=='0'>
	            	<#assign notOrIs="${action.getText('是')}">
	            <#else>
	           	 	<#assign notOrIs="${action.getText('否')}">
			    </#if>
	            <@vcolumn title="${action.getText('returnPlan.notOrIs')}" sortable="desc">
	            	${notOrIs?if_exists}
	            	<@alignLeft/>
	            </@vcolumn>
	        </#if>    
        </@list>
    </@ww.form>
</@htmlPage>