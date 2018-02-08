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
<@htmlPage title="${action.getText('techAlterDetailDetailProfile.title')}">
 <base target="_self">
   <@ww.form namespace="'/tooling/prophase/business'" name="'repairMaintenanceDetail'" action="'savePurchaseTeachAlterDetail'" method="'post'" validate="true">
     <@ww.token name="savePurchaseRepairMaintenanceDetailToken"/>
     <@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<#if subscribe.id?exists>
		<@ww.hidden name="'subscribe.id'" value="#{subscribe.id}"/>
	</#if>
	<#if subscribeDtl.id?exists>
	 	<@ww.hidden name="'subscribeDtl.id'" value="#{subscribeDtl.id}"/>  
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
    	 <@ww.textfield label="'${action.getText('allPrice')}'" name="'subscribeDtl.totalPrice'" value="'${(subscribeDtl.totalPrice?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'" disabled="false"/>
	     <@pp.datePicker label="'${action.getText('requestDate')}'" name="'subscribeDtl.requireDate'"
 						 value="'${(subscribeDtl.requireDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
 						 required="true" maxlength="10"/>
	   </tr>
	   <tr>
	     <@ww.textarea label="'${action.getText('requestReason')}'" 
					   name="'subscribeDtl.reqReason'" 
					   value="'${subscribeDtl.reqReason?if_exists}'" rows="'3'" cols="'50'"
					   disabled="false" />
 	  	 <@ww.textarea label="'${action.getText('comment')}'" 
		               name="'subscribeDtl.comment'" 
		               value="'${subscribeDtl.comment?if_exists}'" rows="'3'" cols="'50'"
		               disabled="false" />
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
	   if(isNotEmpty(document.forms[0],"subscribeDtl.totalPrice")) {
	     	if (!isDoubleNumber("subscribeDtl.totalPrice")){
		    	alert("${action.getText('subscribeDtl.totalPrice.isNotNumber')}");
		      		return false;
	      	 } else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["subscribeDtl.totalPrice"].value, 10000000001, 0)){  //验证范围
		     	alert("${action.getText('subscribeDtl.totalPrice.isNotNumber')}");
		     	return false;
	      	}
       }
       //验证计划完成日期
       if (!validateFinishedDate()) {
         return false;
       }
       //验证需求原因和备注
       if (!validateReasonAndComment()) {
         return false;
       }
       return true;
     }
     /*
      * 验证计划完成日期是否为空,以及格式
     */
     function validateFinishedDate() {
       //验证计划完成日期是否为空
	   if(document.forms["repairMaintenanceDetail"].elements["subscribeDtl.requireDate"].value ==""){
		 alert("${action.getText('requestDate.required')}");
		 return false;
	   }
	   //验证计划完成日期是否为日期型
	   if(!isDate("subscribeDtl.requireDate")){
	     alert("${action.getText('select.right.repairMaintenanceDetail.requestDate')}");
		 return false;
	   }
	   return true;
     }
     /*
      * 验证原因,备注的长度
     */
     function validateReasonAndComment() {
        //验证需求原因长度
       if ('' != document.forms["repairMaintenanceDetail"].elements["subscribeDtl.reqReason"].value) {
	     if (!isValidLength(document.forms["repairMaintenanceDetail"],"subscribeDtl.reqReason",0,250)) {
	       alert("${action.getText("repairMaintenanceDetail.reason.maxLength")}");
		   return false;
	     }
	   }
	   //验证备注长度
	   if ('' != document.forms["repairMaintenanceDetail"].elements["subscribeDtl.comment"].value) {
	     if (!isValidLength(document.forms["repairMaintenanceDetail"],"subscribeDtl.comment",0,250)) {
	       alert("${action.getText("repairMaintenanceDetail.comment.maxLength")}");
		   return false;
	     }
	   }
	   return true;
     }
   </script>
</@htmlPage>