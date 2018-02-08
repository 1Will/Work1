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
<#include "../../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('techAlterDetailProfile.title')}">
 <base target="_self">
   <@ww.form namespace="'/tooling/acceptBillSelector'" name="'repairMaintenanceDetail'" action="'saveAcceptTechAlterDetail'" method="'post'" validate="true">
     <@ww.token name="savePurchaseRepairMaintenanceDetailToken"/>
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
     <@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
	<#if acceptBill.id?exists>
		<@ww.hidden name="'acceptBill.id'" value="#{acceptBill.id}"/>
	</#if>
	<#if acceptBillDetail.id?exists>
	 	<@ww.hidden name="'acceptBillDetail.id'" value="#{acceptBillDetail.id}"/>  
	</#if>
     <@inputTable>
	   <@eam2008_ToolingSelector/>
	   <tr>
	   	 <#assign toolingDepartment=''/>
	   	 <#assign toolingType=''/>
	   	 <#assign specification=""/>
	   	 <#assign model=""/>
	   	 <#if tooling?exists>
	       <#if tooling.department?exists>
	         <#assign toolingDepartment="${tooling.department.name?if_exists}"/>
	       </#if>
	       <#if tooling.toolingType?exists>
	         <#assign toolingType="${tooling.toolingType.value?if_exists}"/>
	       </#if>
	       <#if tooling.specification?exists>
	         <#assign specification="${tooling.specification?if_exists}"/>
	       </#if>
	       <#if tooling.model?exists>
	         <#assign model="${tooling.model?if_exists}"/>
	       </#if>
	     </#if>
	     <@ww.textfield label="'${action.getText('department')}'" name="'tooling.department'" value="'${toolingDepartment?if_exists}'" cssClass="'underline'" disabled="true"/>

	     <@ww.textfield label="'${action.getText('category')}'" name="'tooling.toolingType'" value="'${toolingType?if_exists}'" cssClass="'underline'" disabled="true"/>
	   </tr>
	     <@ww.textfield label="'${action.getText('specification')}'" name="'tooling.specification'" value="'${specification?if_exists}'" cssClass="'underline'" disabled="true"/>
	     <@ww.textfield label="'${action.getText('model')}'" name="'tooling.model'" value="'${model?if_exists}'" cssClass="'underline'" disabled="true"/>
	   <tr>
       <tr>
    	 <@ww.textfield label="'${action.getText('allPrice')}'" name="'acceptBillDetail.totalPrice'" value="'${(acceptBillDetail.totalPrice?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'" disabled="false"/>
	     <#--<@pp.datePicker label="'${action.getText('requestDate')}'" name="'acceptBillDetail.requireDate'"
 						 value="'${(acceptBillDetail.requireDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
 						 required="true" maxlength="10"/>-->
 		<@ww.textarea label="'${action.getText('memo')}'" 
	              name="'acceptBillDetail.memo'"
		          value="'${acceptBillDetail.memo?if_exists}'" rows="'3'" cols="'50'"
		          disabled="false" />
	   </tr>
	   <tr>
	    <#-- <@ww.textarea label="'${action.getText('requestReason')}'" 
					   name="'acceptBillDetail.reqReason'" 
					   value="'${subscribeDtl.reqReason?if_exists}'" rows="'3'" cols="'50'"
					   disabled="false" />-->
 	  	 
	   </tr>
     </@inputTable>
     <@buttonBar>
        <#if !(action.isReadOnly())>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'">
          </@vsubmit>
       </#if>
       <input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
     </@buttonBar>
   </@ww.form>
   <script language="JavaScript" type="text/JavaScript">
    function validate() {
       if (!eam2008_tooling_validate("${action.getText('select.grapNo')}")) {
	     return false;
	   }
	   /*
      *验证总价
      */
       if(isNotEmpty(document.forms[0],"acceptBillDetail.totalPrice")) {
	     	if (!isDoubleNumber("acceptBillDetail.totalPrice")){
		    	alert("${action.getText('acceptBillDetail.totalPrice.format.error')}");
		      		return false;
	      	 } else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["acceptBillDetail.totalPrice"].value, 10000000001, 0)){  //验证范围
		     	alert("${action.getText('acceptBillDetail.totalPrice.format.error')}");
		     	return false;
	      	}
     	}
	   return true;
     }
   </script>
</@htmlPage>