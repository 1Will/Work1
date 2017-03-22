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

<@htmlPage title="${action.getText('expenseApplyAction.search')}">
	<@ww.form name="'listForm'" action="'searchExpenseApplyAction'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchExpenseApplyActionToken"/>
		<#include "./expenseApplySearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms()'"/>
        </@buttonBar>
        <@list title="${action.getText('expenseApplyAction.list')}" 
            includeParameters="expenseApply.code|expenseApply.expenseName|expenseApply.applyPerson.name|expenseApply.deparment.name|expenseApply.expenseType.id|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:expenseApply.code|expenseApply.expenseName|expenseApply.applyPerson.name|expenseApply.deparment.name" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="expenseApplyIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
          	<@vcolumn title="${action.getText('expenseApply.code')}" property="code" sortable="desc" >
				 <a href="javascript: returnDialog(new Array(#{object.id?if_exists},'${object.code?if_exists}','${object.expenseName?if_exists}','${object.applyPerson.name?if_exists}','${object.deparment.id?if_exists}','<#if object.expenseType?exists>${object.expenseType.id?if_exists}</#if>'));">
		            	${object.code?if_exists}
		            </a>
				<@alignLeft/>
            </@vcolumn>
           <@vcolumn title="${action.getText('expenseApply.expenseName')}" property="expenseName" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('expenseApply.applyPerson.name')}" property="applyPerson.name" sortable="desc">
            	<#if object.applyPerson?exists>
	            	 ${object.applyPerson.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('expenseApply.deparment.name')}" property="deparment.name" sortable="desc">
           	 <#if object.deparment?exists>
	            	 ${object.deparment.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
           
            <@vcolumn title="${action.getText('expenseApply.expenseType')}" property="expenseType.id" sortable="desc">
           	 <#if object.contractType?exists>
	            	 ${object.expenseType.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('expenseApply.applyMoney')}" property="applyMoneye" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('expenseApply.residualMoney')}" property="residualMoney" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
	        <@buttonBar>
	        	
			</@buttonBar>
    </@ww.form>
</@htmlPage>
