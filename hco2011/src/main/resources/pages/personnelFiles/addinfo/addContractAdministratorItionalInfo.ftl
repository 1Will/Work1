<#--
	Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All
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


<#include "../../includes/hco2011.ftl" />

<@framePage title="">
<@ww.form action="'saveAddContractAdministratorItionalInfo'" namespace="'/personnelFile'" method="'post'">
	<@ww.token name="saveAdditionalInfoToken"/>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@inputTable>
		<#if contractAdministrator.id?exists>
                <@ww.hidden name="'contractAdministrator.id'" value="#{contractAdministrator.id}"/>
        </#if>
	<tr>
		<@textfield anothername="height" name="contractAdministrator.payAccount" type="D" label="${action.getText('payAccount')}"  value="${contractAdministrator.payAccount?if_exists}" cssClass="underline"/>
		<@textfield anothername="weight" name="contractAdministrator.socialInsuranceAccount"  type="D" label="${action.getText('socialInsuranceAccount')}"  value="${contractAdministrator.socialInsuranceAccount?if_exists}" cssClass="underline"/>
	</tr> 
	<tr>
		<@textfield  anothername="sight" name="contractAdministrator.capitalReserveAccount"  type="D" label="${action.getText('capitalReserveAccount')}"  value="${contractAdministrator.capitalReserveAccount?if_exists}" cssClass="underline"/>
	</tr>
	</@inputTable>
		    <#if !(action.isReadOnly())>
		         <@buttonBar>
		            <@vsubmit value="'${action.getText('save')}'" onclick="'return bbb();'"/>
		         </@buttonBar>
	        </#if>
	</@ww.form >
</@framePage >
<script  language="JavaScript" type="text/JavaScript">
	function bbb(){
		if(getObjByName('contractAdministrator.payAccount').value.length>50){
			alert('${action.getText('contractAdministrator.payAccount.alert')}')
			return false;
		}
		if(getObjByName('contractAdministrator.socialInsuranceAccount').value.length>50){
			alert('${action.getText('contractAdministrator.socialInsuranceAccount.alert')}')
			return false;
		}
		if(getObjByName('contractAdministrator.capitalReserveAccount').value.length>500){
			alert('${action.getText('contractAdministrator.capitalReserveAccount.alert')}')
			return false;
		}
		return true;
	}
    window.onload=function(){
  		}
</script>