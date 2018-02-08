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
<@htmlPage title="${action.getText('purchasePlanDetailProfile.title')}">
 <base target="_self">
   <@ww.form namespace="'/popup'" name="'purchasePlanDetail'" action="'savePurchasePlanDetail'" method="'post'" validate="true">
     <@ww.token name="savePurchasePlanDetailToken"/>
     <#if purchasePlan.id?exists>
       <@ww.hidden name="'purchasePlan.id'" value="'#{purchasePlan.id?if_exists}'"/>
     </#if>
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
     <#if purchasePlanDetail.id?exists>
       <@ww.hidden name="'purchasePlanDetail.id'" value="#{purchasePlanDetail.id?if_exists}"/>
     </#if>
     <@inputTable>
	   <tr>
		 <@ww.textfield label="'${action.getText('purchasePlanDetail.name')}'" name="'purchasePlanDetail.name'" value="'${purchasePlanDetail.name?if_exists}'" cssClass="'underline'" disabled="false"  required="true"/>
		 <@ww.textfield label="'${action.getText('purchasePlanDetail.specification')}'" name="'purchasePlanDetail.specification'" value="'${purchasePlanDetail.specification?if_exists}'" cssClass="'underline'" disabled="false"  required="flase" />
	   </tr>
       <tr>
         <@ww.textfield label="'${action.getText('purchasePlanDetail.model')}'" name="'purchasePlanDetail.model'" value="'${purchasePlanDetail.model?if_exists}'" cssClass="'underline'" disabled="false"/>
    	 <@ww.textfield label="'${action.getText('purchasePlanDetail.unitPrice')}'" name="'purchasePlanDetail.unitPrice'" value="'${purchasePlanDetail.unitPrice?if_exists}'" cssClass="'underline'" disabled="false" required="true" onchange="'calAllPrice();'"/>
	   </tr>
	   <tr>
	     <@ww.textfield label="'${action.getText('purchasePlanDetail.number')}'" name="'purchasePlanDetail.number'" value="'${purchasePlanDetail.number?if_exists}'" cssClass="'underline'" disabled="false" required="true" onchange="'calAllPrice();'"/>
    	 <@ww.textfield label="'${action.getText('purchasePlanDetail.allPrice')}'" name="'allPrice'" value="'${purchasePlanDetail.allPrice?if_exists}'" cssClass="'underline'" disabled="true" required="false" />
         <@ww.hidden name="'purchasePlanDetail.allPrice'" value="'${purchasePlanDetail.allPrice?if_exists}'"/>
       </tr>
       <tr>
    	 <@pp.datePicker label="'${action.getText('purchasePlanDetail.planPurchaseDate')}'" name="'purchasePlanDetail.planPurchaseDate'"
 						 value="'${(purchasePlanDetail.planPurchaseDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
 						 required="true" maxlength="10"/>
 		 <@ww.textarea label="'${action.getText('purchasePlanDetail.comment')}'" 
		              name="'purchasePlanDetail.comment'" 
		              value="'${purchasePlanDetail.comment?if_exists}'" rows="'3'" cols="'50'"
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
     function validate() {
       //验证品名
       if (!validateName()) {
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
       //验证计划采购日期
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
       var name = document.forms["purchasePlanDetail"].elements["purchasePlanDetail.name"].value;
       if (name == '') {
         alert("${action.getText('purchasePlanDetail.name.requried')}");
         return false;
       } else if(!isValidLength(document.forms["purchasePlanDetail"], "purchasePlanDetail.name", null, 50)) {
         alert("${action.getText('purchasePlanDetail.name.maxLength')}");
         return false;
       }
       return true;
     }
     /*
      * 验证规格和型号的长度
     */
     function validateSpecificationAndModel() {
       //验证规格长度
       if ('' != document.forms["purchasePlanDetail"].elements["purchasePlanDetail.specification"].value) {
         if (!isValidLength(document.forms["purchasePlanDetail"], "purchasePlanDetail.specification", null, 50)) {
           alert("${action.getText('purchasePlanDetail.specification.maxLength')}");
           return false;
         }
       }
       //验证型号长度
       if ('' != document.forms["purchasePlanDetail"].elements["purchasePlanDetail.model"].value) {
         if (!isValidLength(document.forms["purchasePlanDetail"], "purchasePlanDetail.model", null, 50)) {
           alert("${action.getText('purchasePlanDetail.model.maxLength')}");
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
       var unitPrice = document.forms["purchasePlanDetail"].elements["purchasePlanDetail.unitPrice"].value;
       if ('' == unitPrice) {         //验证是否为空
         alert("${action.getText('purchasePlanDetail.unitPrice.requried')}");
         return false;
       } else if (!isDoubleNumber("purchasePlanDetail.unitPrice")){  //验证格式
         alert("${action.getText('purchasePlanDetail.unitPrice.format.error')}");
         return false;
       }else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["purchasePlanDetail.unitPrice"].value, 10000000001, 0)){ //验证范围
         alert("${action.getText('purchasePlanDetail.unitPrice.format.error')}");
         return false;
       }
       //验证数量是否为空,以及格式
       var number = document.forms["purchasePlanDetail"].elements["purchasePlanDetail.number"].value;
       if ('' == number) {            //验证是否为空
         alert("${action.getText('purchasePlanDetail.number.requried')}");
         return false;
       } else if (!isDoubleNumber("purchasePlanDetail.number")){   //验证格式
         alert("${action.getText('purchasePlanDetail.number.format.error')}");
         return false;
       } else if (!isNumberBetweenBoolen(number, 1000000001, 0)){ //验证范围
         alert("${action.getText('purchasePlanDetail.number.format.error')}");
         return false;
       }
       return true;
     }
     /*
      * 验证计划采购日期是否为空,以及格式
     */
     function validateFinishedDate() {
       //验证计划采购日期是否为空
	   if(document.forms["purchasePlanDetail"].elements["purchasePlanDetail.planPurchaseDate"].value ==""){
		 alert("${action.getText('select.purchasePlanDetail.planPurchaseDate')}");
		 return false;
	   }
	   //验证计划采购日期是否为日期型
	   if(!isDate("purchasePlanDetail.planPurchaseDate")){
	     alert("${action.getText('select.right.purchasePlanDetail.planPurchaseDate')}");
		 return false;
	   }
	   return true;
     }
     /*
      * 验证备注的长度
     */
     function validateReasonAndComment() {
	   //验证备注长度
	   if ('' != document.forms["purchasePlanDetail"].elements["purchasePlanDetail.comment"].value) {
	     if (!isValidLength(document.forms["purchasePlanDetail"],"purchasePlanDetail.comment",0,250)) {
	       alert("${action.getText("purchasePlanDetail.comment.maxLength")}");
		   return false;
	     }
	   }
	   return true;
     }
 	/*
	 * 根据单价和数量,计算总价
	*/
	 function calAllPrice() {   
	   if ('' != document.forms["purchasePlanDetail"].elements["purchasePlanDetail.unitPrice"].value && isDoubleNumber("purchasePlanDetail.unitPrice")) {
	     var unitPrice = parseFloat(formatDigital(document.forms["purchasePlanDetail"].elements["purchasePlanDetail.unitPrice"].value));
	     if ('' != document.forms["purchasePlanDetail"].elements["purchasePlanDetail.number"].value && isNumber("purchasePlanDetail.number")) {
	       var number = parseInt(formatDigital(document.forms["purchasePlanDetail"].elements["purchasePlanDetail.number"].value));
	       document.forms["purchasePlanDetail"].elements["purchasePlanDetail.allPrice"].value = unitPrice*number;
	       document.forms["purchasePlanDetail"].elements["allPrice"].value = unitPrice*number;
	     } else {
	       document.forms["purchasePlanDetail"].elements["purchasePlanDetail.allPrice"].value = 0;
	       document.forms["purchasePlanDetail"].elements["allPrice"].value = 0;
	     }
	   }
	 }
   </script>
</@htmlPage>