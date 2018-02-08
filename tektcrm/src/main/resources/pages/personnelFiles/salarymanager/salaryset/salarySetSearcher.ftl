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
	<@ww.textfield label="'${action.getText('salarySet.emplyee')}'" name="'emplyee'" value="'${req.getParameter('emplyee')?if_exists}'" cssClass="'underline'"/>
	<@ww.textfield label="'${action.getText('salarySet.standard')}'" name="'standard'" value="'${req.getParameter('standard')?if_exists}'" cssClass="'underline'"/>
	<@ww.select label="'${action.getText('salarySet.status')}'" 
       name="'status.id'" 
       value="'${req.getParameter('status.id')?if_exists}'"
       listKey="id" 
       listValue="name"
       list="allStatus" 
       emptyOption="false" 
       disabled="false" 
       required="false"
       >
    </@ww.select>
</tr>
<tr>
    	<@pp.dateRanger label="'${action.getText('salarySet.createDate')}'" 
 			name="'salarySet.createDate'" 
		    value="'${req.getParameter('salarySet.createDate_start')?if_exists}|${req.getParameter('salarySet.createDate_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/> 
   <@crm_onlySearchInvalid_checkBox/>
</tr>
</@inputTable>
<SCRIPT>

	function checkInvalidParms() {
		if (-1 == getObjByName('status.id').value) {
			getObjByName('status.id').value = '';
	    }
	    var beginDateMsg="${action.getText('salarySet.createDate.dateFormate.error')}";
	    if(queryDate("salarySet.createDate_start","salarySet.createDate_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('salarySet.createDate.dateFormate.error')}";
	    if(queryDate("salarySet.createDate_start","salarySet.createDate_end",
	       endDateMsg,null)==false){  
	   	   return false;
	    }
    	return true;
	}
</SCRIPT>