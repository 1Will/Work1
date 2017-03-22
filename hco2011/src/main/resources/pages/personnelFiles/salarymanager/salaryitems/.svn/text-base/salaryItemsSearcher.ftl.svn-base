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

<#include "../../../includes/macros.ftl" />
<@inputTable>
<tr>
	<@ww.textfield label="'${action.getText('salaryItems.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
	<@ww.textfield label="'${action.getText('salaryItems.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
</tr>
<tr>
	<@ww.select label="'${action.getText('salaryItems.type')}'" 
       name="'type.id'" 
       value="'${req.getParameter('type.id')?if_exists}'"
       listKey="id" 
       listValue="name"
       list="allTypes" 
       emptyOption="false" 
       disabled="false" 
       required="false"
       >
    </@ww.select>
    <@ww.select label="'${action.getText('salaryItems.order')}'" 
       name="'orders.id'" 
       value="'${req.getParameter('orders.id')?if_exists}'"
       listKey="id" 
       listValue="name"
       list="allOrders" 
       emptyOption="false" 
       disabled="false" 
       required="false"
       >
   </@ww.select>
</tr>
<tr>
	<@crm_onlySearchInvalid_checkBox/>
</tr>
</@inputTable>
<SCRIPT>

	function checkInvalidParms() {
		if (-1 == getObjByName('type.id').value) {
			getObjByName('type.id').value = '';
	    }
	    if (-1 == getObjByName('orders.id').value) {
			getObjByName('orders.id').value = '';
	    }
    	return true;
	}
</SCRIPT>