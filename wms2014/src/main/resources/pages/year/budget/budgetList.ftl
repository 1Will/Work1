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
<#-- $Id: -->
<#include "../../includes/eam2008.ftl" />
<#assign budgetSeacherTitle = ''/>
<#if toolingDevFlag?exists>
  <#if toolingDevFlag == 'DEVICE'>
    <#assign budgetSeacherTitle = "${action.getText('deviceBudgetSeacher.title')}"/>
  <#else>
    <#assign budgetSeacherTitle = "${action.getText('toolingBudgetSeacher.title')}"/>
  </#if>
</#if>
<@htmlPage title="${budgetSeacherTitle}">
  <@ww.form namespace="'/year/budget'" name="'listBudget'" action="'searchYearBudgets'" method="'post'">
    <@ww.token name="searchBudgetsToken"/>   
	<#include "budgetSearcher.ftl"/>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
 	<@buttonBar>
      <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
      <#if toolingDevFlag?exists>
        <#if toolingDevFlag == 'DEVICE'>
          <#if !(action.isReadOnly())>
           <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/year/budget/editYearBudget.html?toolingDevFlag=${toolingDevFlag?if_exists}"/>  
       	  </#if>	
       </#if>
     </#if>
    </@buttonBar>
 	<@list title="${action.getText('budget.list')}" 
           includeParameters="yearBudgetNo|readOnly|name|planCreator.name|planCreatedDate_start|planCreatedDate_end|onlyValid|onlyInvalid|toolingDevFlag" 
           fieldMap="like:planCreator.name|yearBudgetNo|name|,date:planCreatedDate_start|planCreatedDate_end" >
	  <@vlh.checkbox property="id" name="budgetIds">
        <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <@vcolumn title="${action.getText('budget.yearBudgetNo')}" property="yearBudgetNo" sortable="desc">
        <#if !(action.isOnlyInvalid())>
          <a href="editYearBudget.html?budget.id=#{object.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.yearBudgetNo}</a>
        </#if>
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('budget.name')}" property="name" sortable="desc">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('budget.year')}" property="year" format="yyyy" sortable="desc">
        <@alignCenter/>
      </@vcolumn>
      <#if toolingDevFlag=='DEVICE'>
      <@vcolumn title="${action.getText('budget.planCreator')}" property="planCreator.name" sortable="desc">
	    <@alignLeft/>
      </@vcolumn>
      <#else>
      <@vcolumn title="${action.getText('budget.planCreator')}" property="planCreator.name">
	    <@alignLeft/>
      </@vcolumn>
      </#if>
	  <@vcolumn title="${action.getText('budget.planCreatedDate')}" property="planCreatedDate" format="yyyy-MM-dd" sortable="desc">
	    <@alignCenter/>
      </@vcolumn>
      <@vcolumn title="${action.getText('budget.allFee')}" property="allFee" format="${action.getText('currencyFormat')}" sortable="desc">
	    <@alignRight/>
      </@vcolumn>
    </@list>
    <#if !first>
      <#if !(action.isReadOnly())>
       <#if toolingDevFlag?exists>
        <#if toolingDevFlag == 'DEVICE'>
        <@buttonBar>
	      <@eam2008_disabledOrEnabled_button message="${action.getText('yearBudget')}" boxName="budgetIds" jsFunctionName="checkInvalidParms()"/>
	    </@buttonBar>
	    </#if>
	   </#if>
	  </#if>
    </#if>
  </@ww.form>
</@htmlPage>