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
		<@ww.textfield label="'${action.getText('expenseApply.code')}'" name="'expenseApply.code'" value="'${req.getParameter('expenseApply.code')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('expenseApply.expenseName')}'" name="'expenseApply.expenseName'" value="'${req.getParameter('expenseApply.expenseName')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('expenseApply.applyPerson.name')}'" name="'expenseApply.applyPerson.name'" value="'${req.getParameter('expenseApply.applyPerson.name')?if_exists}'" cssClass="'underline'" />
		
		
	</tr>
	<tr>
	<@ww.textfield label="'${action.getText('expenseApply.deparment.name')}'" name="'expenseApply.deparment.name'" value="'${req.getParameter('expenseApply.deparment.name')?if_exists}'" cssClass="'underline'" />
	<@ww.select label="'${action.getText('expenseApply.expenseType')}'" 
				id="expenseType"
				name="'expenseApply.expenseType.id'" 
				value="'${req.getParameter('expenseApply.expenseType.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allExpenseType"
				emptyOption="false" 
				disabled="false">
			</@ww.select>		            
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
	
	
	
	function checkInvalidParms(){
	
		return true;
    }
  jgetObjByName(function(){
  
   	<#if req.getParameter('expenseApply.expenseType.id')?exists>
    		jgetObjByName("#expenseType").val("${req.getParameter('expenseApply.expenseType.id')?if_exists}");
    	</#if>
    <#--	<#if req.getParameter('contractManagement.state.id')?exists>
    		jgetObjByName("#state").val("${req.getParameter('contractManagement.state.id')?if_exists}");
    	</#if>
    	-->
  });
</script>