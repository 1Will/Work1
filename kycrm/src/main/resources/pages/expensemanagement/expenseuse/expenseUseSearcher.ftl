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

<#-- $Id: customerInfoSearcher.ftl 2009-12-10 15:50:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />

<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('expenseUse.expenseApply.code')}'" name="'expenseUse.expenseApply.code'" value="'${req.getParameter('expenseUse.expenseApply.code')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('expenseUse.expenseApply.expenseName')}'" name="'expenseUse.expenseApply.expenseName'" value="'${req.getParameter('expenseUse.expenseApply.expenseName')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('expenseUse.expenseApply.applyPerson.name')}'" name="'expenseUse.expenseApply.applyPerson.name'" value="'${req.getParameter('expenseUse.expenseApply.applyPerson.name')?if_exists}'" cssClass="'underline'" />
		</tr>
	<tr>
	<@ww.textfield label="'${action.getText('expenseUse.expenseApply.deparment.name')}'" name="'expenseUse.expenseApply.deparment.name'" value="'${req.getParameter('expenseUse.expenseApply.deparment.name')?if_exists}'" cssClass="'underline'" />
	<@ww.select label="'${action.getText('expenseUse.expenseApply.expenseType')}'" 
				id="expenseType"
				name="'expenseUse.expenseApply.expenseType.id'" 
				value="'${req.getParameter('expenseUse.expenseApply.expenseType.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allExpenseType"
				emptyOption="false" 
				disabled="false">
			</@ww.select>		   
	<@ww.textfield label="'${action.getText('expenseUse.usePerson')}'" name="'expenseUse.usePerson'" value="'${req.getParameter('expenseUse.usePerson')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>		
	<@ww.textfield label="'${action.getText('expenseUse.customerInfo')}'" name="'expenseUse.customerInfo.name'" value="'${req.getParameter('expenseUse.customerInfo.name')?if_exists}'" cssClass="'underline'" />
	<@ww.textfield label="'${action.getText('expenseUse.contractManagement')}'" name="'expenseUse.contractManagement.name'" value="'${req.getParameter('expenseUse.contractManagement.name')?if_exists}'" cssClass="'underline'" />
			         
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
	
	
	
	function checkInvalidParms(){
		return true;
    }
  jgetObjByName(function(){
  
   	<#if req.getParameter('expenseUse.expenseApply.expenseType.id')?exists>
    		getObjByName("expenseType").value ="${req.getParameter('expenseUse.expenseApply.expenseType.id')?if_exists}";
    	</#if>
  });
</script>