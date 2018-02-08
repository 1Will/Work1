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

<#include "../../includes/eam2008.ftl" />
<#--$Id: lubricationRuleItemList.ftl 11192 2008-03-04 01:46:57Z zbzhang $ -->


<@framePage title="${action.getText('budgetDetailList.title')}">
  <@ww.form name="'listBudgetDetails'" action="'searchYearBudgetDetails'" method="'post'">
    <@ww.token name="searchYearBudgetDetailsToken"/>
    <#if budget.id?exists>
      <@ww.hidden name="'budget.id'" value="'#{budget.id?if_exists}'"/>
    </#if>
    <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
    <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<#assign itemNo=1/>
    <@list title="" excel=false setupTable=false 
     	   includeParameters="budget.id|readOnly|toolingDevFlag"  fieldMap="like:" >
 	  <@vlh.checkbox property="id" name="budgetDetailIds">
        <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <@vcolumn title="${action.getText('budgetDetail.serialNo')}">
       <#if toolingDevFlag?exists>
         <#if toolingDevFlag == 'DEVICE'>
          <a href="#" onclick="open_detailDialog(#{budget.id}, #{object.id});return false;">#{itemNo}</a>
         <#else>
          #{itemNo}
         </#if>
       </#if>
        <@alignCenter />
      </@vcolumn>
      <#assign itemNo = itemNo + 1/>
      <@vcolumn title="${action.getText('budgetDetail.budgetNo')}" property="budgetNo">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('budgetDetail.budgetName')}" property="budgetName" attributes="width='60'">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('department')}" property="department.name">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('budgetDetail.fee')}" property="fee" format="${action.getText('currencyFormat')}">
        <@alignRight />
      </@vcolumn>
      <#if toolingDevFlag?exists>
        <#if toolingDevFlag == 'TOOLING'>
          <@vcolumn title="${action.getText('budgetDetail.quarterPlanFee')}" property="quarterPlanFee" format="${action.getText('currencyFormat')}">
            <@alignRight />
          </@vcolumn>
          <@vcolumn title="${action.getText('budgetDetail.repairFee')}" property="repairFee" format="${action.getText('currencyFormat')}">
            <@alignRight />
          </@vcolumn>
          <@vcolumn title="${action.getText('budgetDetail.toolingPurchaseFee')}" property="purchaseFee" format="${action.getText('currencyFormat')}">
            <@alignRight />
          </@vcolumn>
          <@vcolumn title="${action.getText('budgetDetail.purchaseContractFee')}" property="purchaseContractFee" format="${action.getText('currencyFormat')}">
            <@alignRight />
          </@vcolumn>
        <#elseif toolingDevFlag == 'DEVICE'>
          <@vcolumn title="${action.getText('budgetDetail.repairFee')}" property="repairFee" format="${action.getText('currencyFormat')}">
            <@alignRight />
          </@vcolumn>
          <@vcolumn title="${action.getText('budgetDetail.deviceSubscribeFee')}" property="purchaseFee" format="${action.getText('currencyFormat')}">
            <@alignRight />
          </@vcolumn>
          <@vcolumn title="${action.getText('budgetDetail.devicePurchaseFee')}" property="purchaseContractFee" format="${action.getText('currencyFormat')}">
            <@alignRight />
          </@vcolumn>
        </#if>
      </#if>
      <@vcolumn title="${action.getText('budgetDetail.occurFee')}" property="occurFee" format="${action.getText('currencyFormat')}">
      	<@alignRight />
      </@vcolumn>
      <@vcolumn title="${action.getText('budgetDetail.comment')}" property="comment">
        <@alignLeft />
      </@vcolumn>
    </@list>
    <#if toolingDevFlag?exists>
      <#if toolingDevFlag == 'DEVICE'>
	    <#if !first>
	     <#if !(action.isReadOnly())>
	 	  <@buttonBar>
	 	    <input type="button" class="button" name="new" value="${action.getText('new')}" onclick="open_detailDialog(#{budget.id}, null)"/>
	        <#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('yearBudgetDetail')}?" />
	        <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	          <@ww.param name="'onclick'" value="'return confirmDeletes(\"budgetDetailIds\", \"${confirmMessage}\");'"/>
	          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	        </@vsubmit>
	      </@buttonBar>
	      </#if>
	    </#if>
	  </#if>
	</#if>
    </@ww.form>
    <script language="javascript">
      window.onload = function () {
          parent.document.forms["yearBudget"].elements["budget.allFee"].value='${budget.allFee?if_exists}';
	  }
	  /*
	   * 弹出年度预算明细维护页面
	  */
      function open_detailDialog(budgetId, budgetDetailId) {
	    var url = '${req.contextPath}/popup/editYearBudgetDetail.html?readOnly=${req.getParameter('readOnly')?if_exists}&budget.id='+budgetId;	      		  		
	    if (budgetDetailId != null) {
	      url = url + "&budgetDetail.id=" + budgetDetailId;
	    }
	    popupModalDialog(url,800,600);
        self.location.reload();
	  }
    </script>
     
</@framePage>