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

<#-- $Id: additionalInfoProfile.ftl 2009-12-08 10:00:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />
<@framePage>
<@ww.form name="'listForm'" namespace="'/customerRelationship'" action="'saveAdditionalInfo'" method="'post'">
	<@ww.token name="saveAdditionalInfoToken"/>
	<@ww.hidden name="'customerInfo.id'" value="'#{customerInfo.id?if_exists}'" />
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@inputTable>
 	<tr>
 	    <@ww.textfield label="'${action.getText('cusAdditionalInfo.licenseNumber')}'" name="'cusAdditionalInfo.licenseNumber'" value="'${cusAdditionalInfo.licenseNumber?if_exists}'" cssClass="'underline'" required="false"/>
        <@ww.textfield label="'${action.getText('cusAdditionalInfo.taxNumber')}'" name="'cusAdditionalInfo.taxNumber'" value="'${cusAdditionalInfo.taxNumber?if_exists}'" cssClass="'underline'" required="false"/>
    </tr>
    <tr>
    	 <@ww.textfield label="'${action.getText('cusAdditionalInfo.turnover')}'" name="'cusAdditionalInfo.turnover'" value="'${cusAdditionalInfo.turnover?if_exists}'" cssClass="'underline'" required="false"/>
   		<@ww.textfield label="'${action.getText('cusAdditionalInfo.bank')}'" name="'cusAdditionalInfo.bank'" value="'${cusAdditionalInfo.bank?if_exists}'" cssClass="'underline'" required="false"/>
	</tr>
    <tr>
    	<@ww.textfield label="'${action.getText('cusAdditionalInfo.bankAccount')}'" name="'cusAdditionalInfo.bankAccount'" value="'${cusAdditionalInfo.bankAccount?if_exists}'" cssClass="'underline'" required="false"/>
    	<@ww.textfield label="'${action.getText('cusAdditionalInfo.website')}'" name="'cusAdditionalInfo.website'" value="'${cusAdditionalInfo.website?if_exists}'" cssClass="'underline'" required="false"/>
    </tr>   
    <tr>
    	<td align="right" valign="top">
       		<!--<span class="required">*</span>-->
       		<label class="label">${action.getText('cusAdditionalInfo.comment')}:</label>
     	</td>
		<td colspan="10">
			<textarea name="cusAdditionalInfo.comment" rows="4" cols="150" >${cusAdditionalInfo.comment?if_exists}</textarea>
		</td>
    </tr>
	</@inputTable>
	<#if !(action.isReadOnly())>
		<@buttonBar>
	        <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
	    </@buttonBar>
    </#if>
</@ww.form>
<script>
function storeValidation(){
	if(getObjByName('cusAdditionalInfo.licenseNumber').value !=''){
        if(!isValidLength(document.forms[0], "cusAdditionalInfo.licenseNumber", null, 50)){
			alert("${action.getText('cusAdditionalInfo.licenseNumber.length')}");
			return false;
		}
	}
	if(getObjByName('cusAdditionalInfo.taxNumber').value !=''){
        if(!isValidLength(document.forms[0], "cusAdditionalInfo.taxNumber", null, 50)){
			alert("${action.getText('cusAdditionalInfo.taxNumber.length')}");
			return false;
		}
	}
	if(getObjByName('cusAdditionalInfo.turnover').value!=''){
     	if(!isDoubleNumber("cusAdditionalInfo.turnover")){
			alert("${action.getText('cusAdditionalInfo.turnover.type')}");
			return false;
		}
     }
     if(getObjByName('cusAdditionalInfo.bank').value !=''){
        if(!isValidLength(document.forms[0], "cusAdditionalInfo.bank", null, 50)){
			alert("${action.getText('cusAdditionalInfo.bank.length')}");
			return false;
		}
	}
	if(getObjByName('cusAdditionalInfo.bankAccount').value !=''){
        if(!isValidLength(document.forms[0], "cusAdditionalInfo.bankAccount", null, 50)){
			alert("${action.getText('cusAdditionalInfo.bankAccount.length')}");
			return false;
		}
	}
	if(getObjByName('cusAdditionalInfo.website').value !=''){
		var urlreg=/^[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/
		if (!urlreg.test(getObjByName('cusAdditionalInfo.website').value)){
			alert('${action.getText('cusAdditionalInfo.website.type')}');
			return false;
     	}  
	}
	if(getObjByName('cusAdditionalInfo.comment').value !=''){
        if(!isValidLength(document.forms[0], "cusAdditionalInfo.comment", null, 500)){
			alert("${action.getText('cusAdditionalInfo.comment.length')}");
			return false;
		}
	}
	return true;
}
</script>
</@framePage>
