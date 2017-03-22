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

<#include "../../includes/macros.ftl" />
<@inputTable>
<tr>
<@ww.textfield label="'${action.getText('contractAdministrator.personnelCode')}'" name="'personnelCode'" value="'${req.getParameter('personnelCode')?if_exists}'" cssClass="'underline'"/>
</tr>
<tr>
<@ww.textfield label="'${action.getText('contractAdministrator.contractCode')}'" name="'contractCode'" value="'${req.getParameter('contractCode')?if_exists}'" cssClass="'underline'"/>
<@ww.textfield label="'${action.getText('contractAdministrator.personnelName')}'" name="'personnelName'" value="'${req.getParameter('personnelName')?if_exists}'" cssClass="'underline'"/>
</tr>
<tr>
	<@ww.select label="'${action.getText('contractAdministrator.contractType')}'" 
       name="'contractType.id'" 
       value="'${req.getParameter('contractType.id')?if_exists}'"
       listKey="id" 
       listValue="name"
       list="allContractTypes" 
       emptyOption="false" 
       disabled="false" 
       required="false"
       >
   </@ww.select>
   <@ww.select label="'${action.getText('contractAdministrator.becomes')}'" 
       name="'becomes.id'" 
       value="'${req.getParameter('becomes.id')?if_exists}'"
       listKey="id" 
       listValue="name"
       list="allBecomes" 
       emptyOption="false" 
       disabled="false" 
       required="false"
        >
   </@ww.select>
</tr>
<tr>
	<@pp.dateRanger label="'${action.getText('contractAdministrator.signingDate')}'" 
 			name="'contractAdministrator.signingDate'" 
		    value="'${req.getParameter('contractAdministrator.signingDate_start')?if_exists}|${req.getParameter('contractAdministrator.signingDate_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/> 
	<@pp.dateRanger label="'${action.getText('contractAdministrator.contractEndDate')}'" 
 			name="'contractAdministrator.contractEndDate'" 
		    value="'${req.getParameter('contractAdministrator.contractEndDate_start')?if_exists}|${req.getParameter('contractAdministrator.contractEndDate_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/> 
</tr>
<@crm_onlySearchInvalid_checkBox/>
</@inputTable>
<SCRIPT>
	function checkInvalidParms() {
		if (-1 == getObjByName('contractType.id').value) {
			getObjByName('contractType.id').value = '';
	    }
	    if (-1 == getObjByName('becomes.id').value) {
			getObjByName('becomes.id').value = '';
	    }
	    var beginDateMsg="${action.getText('contractAdministrator.signingDate.dateFormate.error')}";
	    if(queryDate("contractAdministrator.signingDate_start","contractAdministrator.signingDate_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('contractAdministrator.signingDate.dateFormate.error')}";
	    if(queryDate("contractAdministrator.signingDate_start","contractAdministrator.signingDate_end",
	       endDateMsg,null)==false){  
	   	   return false;
	    }
			
		var beginDateMsg="${action.getText('contractAdministrator.contractEndDate.dateFormate.error')}";
	    if(queryDate("contractAdministrator.contractEndDate_start","contractAdministrator.contractEndDate_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('contractAdministrator.contractEndDate.dateFormate.error')}";
	    if(queryDate("contractAdministrator.contractEndDate_start","contractAdministrator.contractEndDate_end",
	       endDateMsg,null)==false){  
	   	   return false;
	    }
    	return true;
	}
	window.onload=function(){
		<#if req.getParameter('contractType.id')?exists>
			getObjByName('contractType.id').value='${req.getParameter('contractType.id')?if_exists}';
		</#if>
		<#if req.getParameter('becomes.id')?exists>
			getObjByName('becomes.id').value='${req.getParameter('becomes.id')?if_exists}';
		</#if>
 	}
</SCRIPT>