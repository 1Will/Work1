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

<@htmlPage title="${action.getText('expenseUseAction.search')}">
	<@ww.form name="'listForm'" action="'searchExpenseUseAction'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchExpenseUseActionToken"/>
		<#include "./expenseUseSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms()'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/expenseUse/editExpenseUseAction.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
       		</#if>
        </@buttonBar>
        <@list title="${action.getText('expenseUseAction.list')}" 
            includeParameters="expenseUse.expenseApply.code|expenseUse.expenseApply.expenseName|expenseUse.expenseApply.applyPerson.name|expenseApply.deparment.name|expenseUse.customerInfo.name|expenseUse.contractManagement.name|expenseUse.expenseApply.expenseType.id|expenseUse.usePerson|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:expenseUse.expenseApply.code|expenseUse.expenseApply.expenseName|expenseUse.expenseApply.applyPerson.name|expenseUse.customerInfo.name|expenseUse.contractManagement.name" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="expenseUseIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
          	<@vcolumn title="${action.getText('expenseUse.expenseApply.code')}" property="expenseApply.code" sortable="desc" >
                <a href="editExpenseUseAction.html?expenseUse.id=#{object.id?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">
    				<#if object.expenseApply?exists>
    					${object.expenseApply.code?if_exists}
    				</#if>            
                </a>
				<@alignLeft/>
            </@vcolumn>
           <@vcolumn title="${action.getText('expenseUse.expenseApply.expenseName')}" property="expenseApply.expenseName" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('expenseUse.expenseApply.applyPerson.name')}" property="expenseApply.applyPerson.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('expenseUse.expenseApply.deparment.name')}" property="expenseApply.deparment.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
           
            <@vcolumn title="${action.getText('expenseUse.expenseApply.expenseType')}" property="expenseApply.expenseType.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('expenseUse.usePerson')}" property="usePerson" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('expenseUse.usedMoney')}" property="usedMoney" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('expenseUse.customerInfo')}" property="customerInfo.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('expenseUse.linkman')}" property="linkman.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
              <@vcolumn title="${action.getText('expenseUse.contractManagement')}" property="contractManagement.contractName" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
        <#if !first>
        <#if !(action.isReadOnly())>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('expenseUseAction.message')}" boxName="expenseUseIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
		</#if>
		</#if>
    </@ww.form>
</@htmlPage>
