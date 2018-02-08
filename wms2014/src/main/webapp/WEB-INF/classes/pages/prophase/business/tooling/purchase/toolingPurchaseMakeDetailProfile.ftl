<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
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
<#--$Id: subscribeDetails.ftl 11311 2008-03-13 13:19:59Z mwei $ -->

<#include "../../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('toolingPurchaseMakeDetail.title')}">
<base target="_self">
	<@ww.form namespace="'/tooling/prophase/business'" name="'listForm'" action="'savePurchaseToolingPurchaseMakeDetail'" method="'post'">
		<@ww.token name="savePurchaseToolingPurchaseMakeDtlToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
		<#if subscribe.id?exists>
			<@ww.hidden name="'subscribe.id'" value="#{subscribe.id}"/>
		</#if>
		<#if subscribeDtl.id?exists>
		 	<@ww.hidden name="'subscribeDtl.id'" value="#{subscribeDtl.id}"/>  
		</#if>
		<@inputTable>
		<tr>
		    <@ww.textfield label="'${action.getText('graphNo')}'" name="'subscribeDtl.graphNo'" value="'${subscribeDtl.graphNo?if_exists}'"  cssClass="'underline'" />
		    <@ww.textfield label="'${action.getText('name')}'" name="'subscribeDtl.name'" value="'${subscribeDtl.name?if_exists}'"  cssClass="'underline'" required="true"/>
		</tr>
		<tr>
	       	<@ww.select label="'${action.getText('category')}'" required="false" name="'category.id'" 
             	listKey="id" listValue="value" list="categorys" 
             	emptyOption="true" disabled="false" required="true">
		    </@ww.select>
		    <@ww.textfield label="'${action.getText('specification')}'" name="'subscribeDtl.specification'" value="'${subscribeDtl.specification?if_exists}'"  cssClass="'underline'" />
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('model')}'" name="'subscribeDtl.model'" value="'${subscribeDtl.model?if_exists}'"  cssClass="'underline'" />
			<@ww.textfield label="'${action.getText('unitPrice')}'" name="'subscribeDtl.unitPrice'" value="'${(subscribeDtl.unitPrice?string('#,###,##0.##'))?if_exists}'"  cssClass="'underline'" onchange="'calAllPrice();'" required="true"/>
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('amount')}'" name="'subscribeDtl.amount'" value="'${subscribeDtl.amount?if_exists}'"  cssClass="'underline'" onchange="'calAllPrice();'" />
			<@ww.select label="'${action.getText('tooling.calUnit')}'" required="false" name="'calUnit.id'" 
		             listKey="id" listValue="value" list="calUnits" 
		             emptyOption="true" disabled="false" required="true">
		             </@ww.select>
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('totalPrice')}'" name="'totalPrice'" value="'${(subscribeDtl.totalPrice?string('#,###,##0.##'))?if_exists}'"  cssClass="'underline'" readonly="true"/>
			<@ww.hidden name="'subscribeDtl.totalPrice'" value="'${subscribeDtl.totalPrice?if_exists}'"/>
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
</@htmlPage>
   <script language="JavaScript" type="text/JavaScript">
     window.onload = function() {
       <#if subscribeDtl.toolingCategory?exists>
         document.forms[0].elements["category.id"].value = #{subscribeDtl.toolingCategory.id?if_exists};
       </#if>
       <#if subscribeDtl.calUnit?exists>
         document.forms[0].elements["calUnit.id"].value = #{subscribeDtl.calUnit.id?if_exists};
       	</#if>
     }
     
      function validate() {
      //验证图号长度
      if(document.getElementById("subscribeDtl.graphNo").value!=''){
		 if(!isValidLength(document.forms["listForm"], "subscribeDtl.graphNo", null, 50)) {
         	alert("${action.getText('toolingMakeDetail.graohNo.maxLength')}");
         	return false;
		 }
	  }
	  //验证名称
	  	 if(document.getElementById("subscribeDtl.name").value==''){//验证品名是否为空
              alert('${action.getText('toolingMakeDetail.name.requried')}');
              return false;	       
	       }
	     if(document.getElementById("subscribeDtl.name").value!=''){//如果品名不为空,验证品名的输入长度
	        if(!isValidLength(document.forms[0],"subscribeDtl.name",null,50)){
	          alert('${action.getText('toolingMakeDetail.name.maxLength')}');
	          return false;
	         }
	      }

     /*
      * 验证种类是否为空
     */
       if ('' == document.forms["listForm"].elements["category.id"].value) {
         alert("${action.getText('category.id.requried')}");
         return false;
       }
       //验证规格长度
       if ('' != document.forms["listForm"].elements["subscribeDtl.specification"].value) {
         if (!isValidLength(document.forms["listForm"], "subscribeDtl.specification", null, 50)) {
           alert("${action.getText('toolingMakeDetail.specification.maxLength')}");
           return false;
         }
       }
       //验证型号长度
       if ('' != document.forms["listForm"].elements["subscribeDtl.model"].value) {
         if (!isValidLength(document.forms["listForm"], "subscribeDtl.model", null, 50)) {
           alert("${action.getText('toolingMakeDetail.model.maxLength')}");
           return false;
         }
       }
       //验证单价是否为空,以及格式
       var unitPrice = document.forms["listForm"].elements["subscribeDtl.unitPrice"].value;
       if ('' == unitPrice) {         //验证是否为空
         alert("${action.getText('toolingMakeDetail.unitPrice.requried')}");
         return false;
       } else if (!isDoubleNumber("subscribeDtl.unitPrice")){  //验证格式
         alert("${action.getText('toolingMakeDetail.unitPrice.format.error')}");
         return false;
       } else if (!isDoubleNumberBetweenBoolean(unitPrice, 10000000001, 0)){  //验证范围
         alert("${action.getText('toolingMakeDetail.unitPrice.format.error')}");
         return false;
       }
       //验证数量是否为空,以及格式
       var number = document.forms["listForm"].elements["subscribeDtl.amount"].value;
	   if (!isDoubleNumber("subscribeDtl.amount")){   //验证格式
         alert("${action.getText('toolingMakeDetail.number.format.error')}");
         return false;
       } else if (!isNumberBetweenBoolen(number, 1000000001, 0)){ //验证范围
         alert("${action.getText('toolingMakeDetail.number.format.error')}");
         return false;
       }
       //验证计量单位
        if ('' == document.forms["listForm"].elements["calUnit.id"].value) {
         alert("${action.getText('calUnit.id.requried')}");
         return false;
       }
       //验证计划完成日期是否为空
	   if(document.forms["listForm"].elements["subscribeDtl.requireDate"].value ==""){
		 alert("${action.getText('select.toolingMakeDetail.planFinishedDate')}");
		 return false;
	   }
	   //验证计划完成日期是否为日期型
	   if(!isDate("subscribeDtl.requireDate")){
	     alert("${action.getText('select.right.toolingMakeDetail.planFinishedDate')}");
		 return false;
	   }
       //验证需求原因长度
       if ('' != document.forms["listForm"].elements["subscribeDtl.reqReason"].value) {
	     if (!isValidLength(document.forms["listForm"],"subscribeDtl.reqReason",0,250)) {
	       alert("${action.getText("toolingMakeDetail.requestReason.maxLength")}");
		   return false;
	     }
	   }
	   //验证备注长度
	   if ('' != document.forms["listForm"].elements["subscribeDtl.comment"].value) {
	     if (!isValidLength(document.forms["listForm"],"subscribeDtl.comment",0,250)) {
	       alert("${action.getText("toolingMakeDetail.comment.maxLength")}");
		   return false;
	     }
	   }
   }

 	/*
	 * 根据单价和数量,计算总价
	*/
	 function calAllPrice() {   
	   if ('' != document.forms["listForm"].elements["subscribeDtl.unitPrice"].value && isDoubleNumber("subscribeDtl.unitPrice")) {
	     var unitPrice = parseFloat(formatDigital(document.forms["listForm"].elements["subscribeDtl.unitPrice"].value));
	     if ('' != document.forms["listForm"].elements["subscribeDtl.amount"].value && isNumber("subscribeDtl.amount")) {
	       var number = parseInt(formatDigital(document.forms["listForm"].elements["subscribeDtl.amount"].value),10);
	       document.forms["listForm"].elements["subscribeDtl.totalPrice"].value = unitPrice*number;
	       document.forms["listForm"].elements["totalPrice"].value = unitPrice*number;
	     } else {
	       document.forms["listForm"].elements["subscribeDtl.totalPrice"].value = 0;
	       document.forms["listForm"].elements["totalPrice"].value = 0;
	     }
	   }
	 }

   </script>