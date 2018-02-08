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
<@htmlPage title="${action.getText('budgetDetailProfile.title')}">
 <base target="_self">
   <@ww.form namespace="'/popup'" name="'budgetDetail'" action="'saveYearBudgetDetail'" method="'post'" validate="true">
     <@ww.token name="saveYearBudgetDetailToken"/>
     <#if budget.id?exists>
       <@ww.hidden name="'budget.id'" value="'#{budget.id?if_exists}'"/>
     </#if>
      <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
     <#if budgetDetail.id?exists>
       <@ww.hidden name="'budgetDetail.id'" value="#{budgetDetail.id?if_exists}"/>
     </#if>
     <@inputTable>
       <tr>
	     <@ww.textfield label="'${action.getText('budgetDetail.budgetNo')}'" name="'budgetDetail.budgetNo'" value="'${budgetDetail.budgetNo?if_exists}'" cssClass="'underline'" disabled="false" required="true"/>
	     <@ww.textfield label="'${action.getText('budgetDetail.budgetName')}'" name="'budgetDetail.budgetName'" value="'${budgetDetail.budgetName?if_exists}'" cssClass="'underline'" disabled="false" required="true"/>
	   <tr>
	   <tr>
	   	 <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
    			     listKey="id" listValue="name" list="departments" 
    			     emptyOption="true" disabled="false" required="true">
    	 </@ww.select>
    	 <@ww.textfield label="'${action.getText('budgetDetail.fee')}'" name="'budgetDetail.fee'" value="'${budgetDetail.fee?if_exists}'" cssClass="'underline'" disabled="false"/>
	   </tr>
	   <tr>
	     <@ww.textarea label="'${action.getText('budgetDetail.comment')}'" 
					   name="'budgetDetail.comment'" 
					   value="'${budgetDetail.comment?if_exists}'" rows="'3'" cols="'50'"
					   disabled="false" />
	   </tr>
     </@inputTable>
     <@buttonBar>
     <#if !(action.isReadOnly())>
       <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
       </#if>
       <input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
     </@buttonBar>
   </@ww.form>
   <script language="JavaScript" type="text/JavaScript">
     window.onload = function () {
       <#if budgetDetail.department?exists>
        document.forms["budgetDetail"].elements["department.id"].value=#{budgetDetail.department.id?if_exists};
       </#if>
       
     }
     function validate() {
       //验证预算编号
       if (!validateBudgetNo()) {
         return false;
       }
       //验证预算名称
       if (!validateBudgetName()) {
         return false;
       }
       //验证部门
       if (!validateDepartment()) {
         return false;
       }
       //验证费用
       if (!validateFee()) {
         return false;
       }
       //验证需求原因和备注
       if (!validateComment()) {
         return false;
       }
       //验证预算编号是否已存在
	   if (!validateConfictOfBudgetNo()) {
	     return false;
	   }
       return true;
     }
     /*
      * 验证预算编号是否为空,以及长度验证
     */
     function validateBudgetNo() {
       var name = document.forms["budgetDetail"].elements["budgetDetail.budgetNo"].value;
       if (name == '') {
         alert("${action.getText('budgetDetail.budgetNo.requried')}");
         return false;
       } else if(!isValidLength(document.forms["budgetDetail"], "budgetDetail.budgetNo", null, 50)) {
         alert("${action.getText('budgetDetail.budgetNo.maxLength')}");
         return false;
       }
       return true;
     }
     /*
      * 验证预算名称是否为空,以及长度验证
     */
     function validateBudgetName() {
       var name = document.forms["budgetDetail"].elements["budgetDetail.budgetName"].value;
       if (name == '') {
         alert("${action.getText('budgetDetail.budgetName.requried')}");
         return false;
       } else if(!isValidLength(document.forms["budgetDetail"], "budgetDetail.budgetName", null, 50)) {
         alert("${action.getText('budgetDetail.budgetName.maxLength')}");
         return false;
       }
       return true;
     }
     /*
      * 验证部门
     */
     function validateDepartment() {
       if ('' == document.forms["budgetDetail"].elements["department.id"].value) {
         alert("${action.getText('department.id.requried')}");
         return false;
       }
       return true;
     }
     /*
      * 验证费用格式是否正确
     */
     function validateFee() {
       //验证费用格式
       var planFee = document.forms["budgetDetail"].elements["budgetDetail.fee"].value;
       if ('' == planFee) {         //验证是否为空
         if (!isDoubleNumber("budgetDetail.fee")){  //验证格式
           alert("${action.getText('budgetDetail.fee.format.error')}");
           return false;
         } else if (!isDoubleNumberBetweenBoolean(planFee, 10000000001, 0)){  //验证范围
           alert("${action.getText('budgetDetail.fee.format.error')}");
           return false;
         }
       }
       return true;
     }
     /*
      * 验证备注的长度
     */
     function validateComment() {
	   //验证备注长度
	   if ('' != document.forms["budgetDetail"].elements["budgetDetail.comment"].value) {
	     if (!isValidLength(document.forms["budgetDetail"],"budgetDetail.comment",0,250)) {
	       alert("${action.getText("budgetDetail.comment.maxLength")}");
		   return false;
	     }
	   }
	   return true;
     }
     function validateConfictOfBudgetNo() {
       var newBudgetNo = document.forms["budgetDetail"].elements["budgetDetail.budgetNo"].value;
       <#list allEnableBudgetNo as budgetNo>
         if('${budgetNo}' == newBudgetNo) {
           alert(newBudgetNo + " ${action.getText('budgetDetail.budgetNo.same.error')}");
           return false;
         }
       </#list> 
       return true;
     }
   </script>
</@htmlPage>