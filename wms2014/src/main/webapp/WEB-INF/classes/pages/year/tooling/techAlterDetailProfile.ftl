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
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('techAlterDetailDetailProfile.title')}">
 <base target="_self">
   <@ww.form namespace="'/popup'" name="'techAlterDetail'" action="'saveTechAlterDetail'" method="'post'" validate="true">
     <@ww.token name="saveTechAlterDetailToken"/>
     <#if yearPlan?exists>
       <@ww.hidden name="'yearPlan.id'" value="'#{yearPlan.id?if_exists}'"/>
     </#if>
     <#if quarterPlan?exists>
       <@ww.hidden name="'quarterPlan.id'" value="'#{quarterPlan.id?if_exists}'"/>
     </#if>
     <@ww.hidden name="'yearQuarterFlag'" value="'${yearQuarterFlag?if_exists}'"/>
     <#if techAlterDetail.id?exists>
       <@ww.hidden name="'techAlterDetail.id'" value="#{techAlterDetail.id?if_exists}"/>
     </#if>
     <@inputTable>
     <#--
	   <tr>
	 	 <@ww.select label="'${action.getText('techAlterDetail.category')}'" required="false" name="'category.id'" 
		             listKey="id" listValue="value" list="categorys" 
		             emptyOption="true" disabled="false" required="true">
		 </@ww.select>
	     
	   </tr>
	 -->
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
         <@ww.textfield label="'${action.getText('allPrice')}'" name="'techAlterDetail.allPrice'" value="'${techAlterDetail.allPrice?if_exists}'" cssClass="'underline'" disabled="false"/>
	     <@pp.datePicker label="'${action.getText('techAlterDetail.planFinishedDate')}'" name="'techAlterDetail.requestDate'"
 						 value="'${(techAlterDetail.requestDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
 						 required="true" maxlength="10"/>
	   </tr>
	   <tr>
	   	 <@ww.textarea label="'${action.getText('requestReason')}'" 
					   name="'techAlterDetail.requestReason'" 
					   value="'${techAlterDetail.requestReason?if_exists}'" rows="'3'" cols="'50'"
					   disabled="false" required="true"/>
	   <#--
	     <@ww.textarea label="'${action.getText('techAlterDetail.reason')}'" 
					   name="'techAlterDetail.reason'" 
					   value="'${techAlterDetail.reason?if_exists}'" rows="'3'" cols="'50'"
					   disabled="false" />
	   -->
 	  	 <@ww.textarea label="'${action.getText('comment')}'" 
		               name="'techAlterDetail.comment'" 
		               value="'${techAlterDetail.comment?if_exists}'" rows="'3'" cols="'50'"
		               disabled="false" />
	   </tr>
     </@inputTable>
     <@buttonBar>
       <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'">
         <#if yearPlan.lockedFlag>
           <@ww.param name="'disabled'" value="true"/>
         </#if>
       </@vsubmit>
       <input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
     </@buttonBar>
   </@ww.form>
   <script language="JavaScript" type="text/JavaScript">
     window.onload = function() {
       <#if techAlterDetail.toolingType?exists>
         document.forms[0].elements["category.id"].value = #{techAlterDetail.toolingType.id?if_exists};
       </#if>
     }
     function validate() {
      if (!eam2008_tooling_validate("${action.getText('select.graphNo')}")) {
	     return false;
	   }
	   <#--
       //验证种类
       if (!validateCategory()) {
         return false;
       }
       -->
       //验证计划费用
       if (!validatePlanFee()) {
         return false;
       }
       //验证计划完成日期
       if (!validateFinishedDate()) {
         return false;
       }
       
       //验证技术改造内容,原因,备注
       if (!validateReasonAndComment()) {
         return false;
       }
       return true;
     }
     /*
      * 验证种类是否为空
     */
     function validateCategory() {
       if ('' == document.forms["techAlterDetail"].elements["category.id"].value) {
         alert("${action.getText('category.id.requried')}");
         return false;
       }
       return true;
     }
     /*
      * 验证计划费用格式是否正确
     */
     function validatePlanFee() {
       //验证单价是否为空,以及格式
       var planFee = document.forms["techAlterDetail"].elements["techAlterDetail.allPrice"].value;
       if ('' != planFee) {
         if (!isDoubleNumber("techAlterDetail.allPrice")){  //验证格式
           alert("${action.getText('techAlterDetail.planFee.format.error')}");
           return false;
         } else if (!isDoubleNumberBetweenBoolean(planFee, 10000000001, 0)){  //验证范围
           alert("${action.getText('techAlterDetail.planFee.format.error')}");
           return false;
         }
       }
       return true;
     }
     /*
      * 验证计划完成日期是否为空,以及格式
     */
     function validateFinishedDate() {
       //验证计划完成日期是否为空
	   if(document.forms["techAlterDetail"].elements["techAlterDetail.requestDate"].value ==""){
		 alert("${action.getText('select.techAlterDetail.planFinishedDate')}");
		 return false;
	   }
	   //验证计划完成日期是否为日期型
	   if(!isDate("techAlterDetail.requestDate")){
	     alert("${action.getText('select.right.techAlterDetail.planFinishedDate')}");
		 return false;
	   }
	   return true;
     }
     /*
      * 验证技术改造内容,需求原因,备注的长度
     */
     function validateReasonAndComment() {
       //验证技术改造内容是否为空,以及技术改造内容长度
       if ('' == document.forms["techAlterDetail"].elements["techAlterDetail.requestReason"].value) {
         alert("${action.getText("techAlterDetail.techAlterContent.requried")}");
		 return false;
       } else if (!isValidLength(document.forms["techAlterDetail"],"techAlterDetail.requestReason",0,250)) {
         alert("${action.getText("techAlterDetail.techAlterContent.maxLength")}");
		 return false;
       }
       <#--
        //验证需求原因长度
       if ('' != document.forms["techAlterDetail"].elements["techAlterDetail.reason"].value) {
	     if (!isValidLength(document.forms["techAlterDetail"],"techAlterDetail.reason",0,250)) {
	       alert("${action.getText("techAlterDetail.reason.maxLength")}");
		   return false;
	     }
	   }
	   -->
	   //验证备注长度
	   if ('' != document.forms["techAlterDetail"].elements["techAlterDetail.comment"].value) {
	     if (!isValidLength(document.forms["techAlterDetail"],"techAlterDetail.comment",0,250)) {
	       alert("${action.getText("techAlterDetail.comment.maxLength")}");
		   return false;
	     }
	   }
	   return true;
     }
   </script>
</@htmlPage>