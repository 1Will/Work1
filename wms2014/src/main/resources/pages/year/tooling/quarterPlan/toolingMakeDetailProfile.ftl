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
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('toolingMakeDetailProfile.title')}">
 <base target="_self">
   <@ww.form namespace="'/popup'" name="'toolingMakeDetail'" action="'saveQuarterToolingMakeDetail'" method="'post'" validate="true">
     <@ww.token name="saveQuarterToolingMakeDetailToken"/>
     <@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
     <#if quarterPlan?exists>
       <@ww.hidden name="'quarterPlan.id'" value="'#{quarterPlan.id?if_exists}'"/>
     </#if>
     <@ww.hidden name="'yearQuarterFlag'" value="'${yearQuarterFlag?if_exists}'"/>
     <#if toolingMakeDetail.id?exists>
       <@ww.hidden name="'toolingMakeDetail.id'" value="#{toolingMakeDetail.id?if_exists}"/>
     </#if>
     <@inputTable>
	   <tr>
	     <@ww.textfield label="'${action.getText('graphNo')}'" name="'toolingMakeDetail.graphNo'" value="'${toolingMakeDetail.graphNo?if_exists}'" cssClass="'underline'" disabled="false"  required="false" />
		 <@ww.textfield label="'${action.getText('name')}'" name="'toolingMakeDetail.name'" value="'${toolingMakeDetail.name?if_exists}'" cssClass="'underline'" disabled="false"  required="true" />
	   </tr>
       <tr>
       	 <@ww.select label="'${action.getText('category')}'" required="false" name="'category.id'" 
		             listKey="id" listValue="value" list="categorys" 
		             emptyOption="true" disabled="false" required="true">
		 </@ww.select>
         <@ww.textfield label="'${action.getText('specification')}'" name="'toolingMakeDetail.specification'" value="'${toolingMakeDetail.specification?if_exists}'" cssClass="'underline'" disabled="false"/>
	   </tr>
	   <tr>
	     <@ww.textfield label="'${action.getText('model')}'" name="'toolingMakeDetail.model'" value="'${toolingMakeDetail.model?if_exists}'" cssClass="'underline'" disabled="false"/>
	     <@ww.textfield label="'${action.getText('unitPrice')}'" name="'toolingMakeDetail.unitPrice'" value="'${(toolingMakeDetail.unitPrice?string('#,###,##0.##'))?if_exists}'" cssClass="'underline'" disabled="false" required="true" onchange="'calAllPrice();'"/>
       </tr>
       <tr>
         <@ww.textfield label="'${action.getText('number')}'" name="'toolingMakeDetail.number'" value="'${toolingMakeDetail.number?if_exists}'" cssClass="'underline'" disabled="false" required="true" onchange="'calAllPrice();'"/>
         <@ww.select label="'${action.getText('tooling.calUnit')}'" required="false" name="'calUnit.id'" 
		             listKey="id" listValue="value" list="calUnits" 
		             emptyOption="true" disabled="false" required="true">
		             </@ww.select>
       </tr>
       <tr>
         <@ww.textfield label="'${action.getText('allPrice')}'" name="'allPrice'" value="'${(toolingMakeDetail.allPrice?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'" disabled="true"/>
    	 <@ww.hidden name="'toolingMakeDetail.allPrice'" value="'${toolingMakeDetail.allPrice?if_exists}'"/>
       
         <@pp.datePicker label="'${action.getText('requestDate')}'" name="'toolingMakeDetail.requestDate'"
 						 value="'${(toolingMakeDetail.requestDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
 						 required="true" maxlength="10"/>
       </tr>
	   <tr>
	     <@ww.textarea label="'${action.getText('requestReason')}'" 
					   name="'toolingMakeDetail.requestReason'" 
					   value="'${toolingMakeDetail.requestReason?if_exists}'" rows="'3'" cols="'50'"
					   disabled="false" />
 	  	 <@ww.textarea label="'${action.getText('comment')}'" 
		              name="'toolingMakeDetail.comment'" 
		              value="'${toolingMakeDetail.comment?if_exists}'" rows="'3'" cols="'50'"
		              disabled="false" />
	   </tr>
     </@inputTable>
     <@buttonBar>
       <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'">
       </@vsubmit>
       <input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
     </@buttonBar>
   </@ww.form>
   <script language="JavaScript" type="text/JavaScript">
     window.onload = function() {
       <#if toolingMakeDetail.toolingCategory?exists>
         document.forms[0].elements["category.id"].value = #{toolingMakeDetail.toolingCategory.id?if_exists};
       </#if>
       <#if toolingMakeDetail.calUnit?exists>
         document.forms[0].elements["calUnit.id"].value = #{toolingMakeDetail.calUnit.id?if_exists};
       	</#if>
     }
     function validate() {
       //验证品名
       if (!validateName()) {
         return false;
       }
       //验证种类
       if (!validateCategory()) {
         return false;
       }
       //验证规格和型号
       if (!validateSpecificationAndModel()) {
         return false;
       }
       //验证单价和数量
       if (!validateUnitPriceAndNumber()) {
         return false;
       }
       if ('' == document.forms["toolingMakeDetail"].elements["calUnit.id"].value) {
         alert("${action.getText('calUnit.id.requried')}");
         return false;
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
      * 验证品名是否为空,以及长度
     */
     function validateName() {
       var name = document.forms["toolingMakeDetail"].elements["toolingMakeDetail.name"].value;
       if (name == '') {
         alert("${action.getText('toolingMakeDetail.name.requried')}");
         return false;
       } else if(!isValidLength(document.forms["toolingMakeDetail"], "toolingMakeDetail.name", null, 50)) {
         alert("${action.getText('toolingMakeDetail.name.maxLength')}");
         return false;
       }
       return true;
     }
     /*
      * 验证种类是否为空
     */
     function validateCategory() {
       if ('' == document.forms["toolingMakeDetail"].elements["category.id"].value) {
         alert("${action.getText('category.id.requried')}");
         return false;
       }
       return true;
     }
     /*
      * 验证规格和型号的长度
     */
     function validateSpecificationAndModel() {
       //验证规格长度
       if ('' != document.forms["toolingMakeDetail"].elements["toolingMakeDetail.specification"].value) {
         if (!isValidLength(document.forms["toolingMakeDetail"], "toolingMakeDetail.specification", null, 50)) {
           alert("${action.getText('toolingMakeDetail.specification.maxLength')}");
           return false;
         }
       }
       //验证型号长度
       if ('' != document.forms["toolingMakeDetail"].elements["toolingMakeDetail.model"].value) {
         if (!isValidLength(document.forms["toolingMakeDetail"], "toolingMakeDetail.model", null, 50)) {
           alert("${action.getText('toolingMakeDetail.model.maxLength')}");
           return false;
         }
       }
       return true;
     }
     /*
      * 验证单价和数量是否为空,以及格式是否正确
     */
     function validateUnitPriceAndNumber() {
       //验证单价是否为空,以及格式
       var unitPrice = document.forms["toolingMakeDetail"].elements["toolingMakeDetail.unitPrice"].value;
       if ('' == unitPrice) {         //验证是否为空
         alert("${action.getText('toolingMakeDetail.unitPrice.requried')}");
         return false;
       } else if (!isDoubleNumber("toolingMakeDetail.unitPrice")){  //验证格式
         alert("${action.getText('toolingMakeDetail.unitPrice.format.error')}");
         return false;
       } else if (!isDoubleNumberBetweenBoolean(unitPrice, 10000000001, 0)){  //验证范围
         alert("${action.getText('toolingMakeDetail.unitPrice.format.error')}");
         return false;
       }
       //验证数量是否为空,以及格式
       var number = document.forms["toolingMakeDetail"].elements["toolingMakeDetail.number"].value;
       if ('' == number) {            //验证是否为空
         alert("${action.getText('toolingMakeDetail.number.requried')}");
         return false;
       } else if (!isNumber("toolingMakeDetail.number")){   //验证格式
         alert("${action.getText('toolingMakeDetail.number.format.error')}");
         return false;
       } else if (!isNumberBetweenBoolen(number, 1000000001, 0)){ //验证范围
         alert("${action.getText('toolingMakeDetail.number.format.error')}");
         return false;
       }
       return true;
     }
     /*
      * 验证计划完成日期是否为空,以及格式
     */
     function validateFinishedDate() {
       //验证计划完成日期是否为空
	   if(document.forms["toolingMakeDetail"].elements["toolingMakeDetail.requestDate"].value ==""){
		 alert("${action.getText('select.toolingMakeDetail.planFinishedDate')}");
		 return false;
	   }
	   //验证计划完成日期是否为日期型
	   if(!isDate("toolingMakeDetail.requestDate")){
	     alert("${action.getText('select.right.toolingMakeDetail.planFinishedDate')}");
		 return false;
	   }
	   return true;
     }
     /*
      * 验证需求原因和备注的长度
     */
     function validateReasonAndComment() {
        //验证需求原因长度
       if ('' != document.forms["toolingMakeDetail"].elements["toolingMakeDetail.requestReason"].value) {
	     if (!isValidLength(document.forms["toolingMakeDetail"],"toolingMakeDetail.requestReason",0,250)) {
	       alert("${action.getText("toolingMakeDetail.requestReason.maxLength")}");
		   return false;
	     }
	   }
	   //验证备注长度
	   if ('' != document.forms["toolingMakeDetail"].elements["toolingMakeDetail.comment"].value) {
	     if (!isValidLength(document.forms["toolingMakeDetail"],"toolingMakeDetail.comment",0,250)) {
	       alert("${action.getText("toolingMakeDetail.comment.maxLength")}");
		   return false;
	     }
	   }
	   return true;
     }
 	/*
	 * 根据单价和数量,计算总价
	*/
	 function calAllPrice() {   
	   if ('' != document.forms["toolingMakeDetail"].elements["toolingMakeDetail.unitPrice"].value && isDoubleNumber("toolingMakeDetail.unitPrice")) {
	     var unitPrice = parseFloat(formatDigital(document.forms["toolingMakeDetail"].elements["toolingMakeDetail.unitPrice"].value));
	     if ('' != document.forms["toolingMakeDetail"].elements["toolingMakeDetail.number"].value && isNumber("toolingMakeDetail.number")) {
	       var number = parseInt(formatDigital(document.forms["toolingMakeDetail"].elements["toolingMakeDetail.number"].value),10);
	       document.forms["toolingMakeDetail"].elements["toolingMakeDetail.allPrice"].value = unitPrice*number;
	       document.forms["toolingMakeDetail"].elements["allPrice"].value = unitPrice*number;
	     } else {
	       document.forms["toolingMakeDetail"].elements["toolingMakeDetail.allPrice"].value = 0;
	       document.forms["toolingMakeDetail"].elements["allPrice"].value = 0;
	     }
	   }
	 }
   </script>
</@htmlPage>