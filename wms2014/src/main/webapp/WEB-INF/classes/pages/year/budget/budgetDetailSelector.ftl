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

<#-- $Id: userSelector.ftl 11122 2008-02-26 12:54:35Z zbzhang $ -->

<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('budgetDetailSelector.title')}">
<base target="_self">
<@ww.form name="'listForm'" action="'budgetDetailSelector'" method="'post'">
	<@inputTable>
	  <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
	  <tr>
	    <@ww.textfield label="'${action.getText('budget.yearBudgetNo')}'" name="'yearBudgetNo'" value="'${req.getParameter('yearBudgetNo')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('budget.name')}'" name="'yearBudgetName'" value="'${req.getParameter('yearBudgetName')?if_exists}'" cssClass="'underline'"/>
	  </tr>
	  <tr>
	  	<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
    		        value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
                    list="departments" emptyOption="false" disabled="false">
        </@ww.select>
		<@pp.datePicker label="'${action.getText('budget.year')}'" name="'year'"
	 					value="'${req.getParameter('year')?if_exists}'" cssClass="'underline'" size="15" 
	 					dateFormat="'%Y'" maxlength="4"/>
	  </tr>
	</@inputTable> 
    <@buttonBar>
        <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>
    <@list title="${action.getText('budgetDetailSelector.list')}" excel=false setupTable=false
           includeParameters="yearBudgetNo|yearBudgetName|year|department.id|toolingDevFlag" 
           fieldMap="like:yearBudgetNo|yearBudgetName" >
      <@vcolumn title="${action.getText('budget.yearBudgetNo')}">
    	<a href="javascript: returnDialog(new Array(#{object.id}, '${object.budgetNo}','${object.budgetName}'));">
                ${object.budget.yearBudgetNo}
        </a>
      </@vcolumn>
      <@vcolumn title="${action.getText('budget.name')}" property="budget.name"/>
      <@vcolumn title="${action.getText('department')}" property="department.name"/>
      <@vcolumn title="${action.getText('budget.year')}" property="budget.year"/>
      <@vcolumn title="${action.getText('budgetDetail.budgetNo')}" property="budgetNo"/>
      <@vcolumn title="${action.getText('budgetDetail.budgetName')}" property="budgetName"/>
      <@vcolumn title="${action.getText('budgetDetail.fee')}" property="fee"/>
      <#if toolingDevFlag?exists>
        <#if toolingDevFlag == 'TOOLING'>
          <@vcolumn title="${action.getText('budgetDetail.quarterPlanFee')}" property="quarterPlanFee">
            <@alignRight />
          </@vcolumn>
          <@vcolumn title="${action.getText('budgetDetail.repairFee')}" property="repairFee">
            <@alignRight />
          </@vcolumn>
          <@vcolumn title="${action.getText('budgetDetail.toolingPurchaseFee')}" property="purchaseFee">
            <@alignRight />
          </@vcolumn>
          <@vcolumn title="${action.getText('budgetDetail.purchaseContractFee')}" property="purchaseContractFee">
            <@alignRight />
          </@vcolumn>
        <#elseif toolingDevFlag == 'DEVICE'>
          <@vcolumn title="${action.getText('budgetDetail.repairFee')}" property="repairFee">
            <@alignRight />
          </@vcolumn>
          <@vcolumn title="${action.getText('budgetDetail.deviceSubscribeFee')}" property="purchaseFee">
            <@alignRight />
          </@vcolumn>
          <@vcolumn title="${action.getText('budgetDetail.devicePurchaseFee')}" property="purchaseContractFee">
            <@alignRight />
          </@vcolumn>
        </#if>
      </#if>
      <@vcolumn title="${action.getText('budgetDetail.occurFee')}" property="occurFee">
      	<@alignRight />
      </@vcolumn>
    </@list>
</@ww.form>
<script language="javascript">
  var selector = document.all("department.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('department.id')?exists>
    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
  <#if first>
    <#if loginUser.department?exists>
      document.getElementById("department.id").value = #{loginUser.department.id};
    </#if>
  </#if>
  function checkInvalidParms() {
    if ('-1' == document.getElementById("department.id").value) {
      document.getElementById("department.id").value = '';
    }
    return true;
  }
</script>
</@htmlPage>
