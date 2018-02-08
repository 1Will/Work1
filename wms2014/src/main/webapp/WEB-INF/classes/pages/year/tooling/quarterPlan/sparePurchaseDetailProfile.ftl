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
<#include "../../../asset/commonSpareType.ftl"/>
<@htmlPage title="${action.getText('sparePurchaseDetailProfile.title')}">
 <base target="_self">
   <@ww.form namespace="'/popup'" name="'sparePurchaseDetail'" action="'saveQuarterSparePurchaseDetail'" method="'post'" validate="true">
     <@ww.token name="saveSparePurchaseDetailToken"/>
     <@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
     <#if quarterPlan?exists>
       <@ww.hidden name="'quarterPlan.id'" value="'#{quarterPlan.id?if_exists}'"/>
     </#if>
     <#if sparePurchaseDetail.id?exists>
       <@ww.hidden name="'sparePurchaseDetail.id'" value="#{sparePurchaseDetail.id?if_exists}"/>
     </#if>
     <@inputTable>
	   <tr>
	     <@ww.textfield label="'${action.getText('graphNo')}'" name="'sparePurchaseDetail.graphNo'" value="'${sparePurchaseDetail.graphNo?if_exists}'" cssClass="'underline'" disabled="false"  required="false" />
		 <@ww.textfield label="'${action.getText('name')}'" name="'sparePurchaseDetail.name'" value="'${sparePurchaseDetail.name?if_exists}'" cssClass="'underline'" disabled="false"  required="true" />
	   </tr>
	   <tr>
	   	 <@ww.select label="'${action.getText('category')}'" required="false" name="'category.id'" 
		     listKey="id" listValue="value" list="spareType" 
		     onclick="'spareTypeValueChange(\"category.id\",\"detailCategory.id\")'"
		     emptyOption="true" disabled="false" required="true">
		 </@ww.select>
	     <@ww.select label="'${action.getText('detailCategory')}'" required="false" name="'detailCategory.id'" 
		     listKey="id" listValue="name" list="spareDetailType" 
		     emptyOption="true" disabled="false" required="false">
		 </@ww.select>
	   </tr>
       <tr>
         <@ww.textfield label="'${action.getText('specification')}'" name="'sparePurchaseDetail.specification'" value="'${sparePurchaseDetail.specification?if_exists}'" cssClass="'underline'" disabled="false"/>
    	 <@ww.textfield label="'${action.getText('model')}'" name="'sparePurchaseDetail.model'" value="'${sparePurchaseDetail.model?if_exists}'" cssClass="'underline'" disabled="false"/>
	   </tr>
	   <tr>
	     <@ww.textfield label="'${action.getText('unitPrice')}'" name="'sparePurchaseDetail.unitPrice'" value="'${sparePurchaseDetail.unitPrice?if_exists}'" cssClass="'underline'" disabled="false" required="true" onchange="'calAllPrice();'"/>
    	 <@ww.textfield label="'${action.getText('number')}'" name="'sparePurchaseDetail.number'" value="'${sparePurchaseDetail.number?if_exists}'" cssClass="'underline'" disabled="false" required="true" onchange="'calAllPrice();'"/>
       </tr>
       <tr>
         <@ww.textfield label="'${action.getText('allPrice')}'" name="'allPrice'" value="'${sparePurchaseDetail.allPrice?if_exists}'" cssClass="'underline'" disabled="true"/>
    	 <@ww.hidden name="'sparePurchaseDetail.allPrice'" value="#{sparePurchaseDetail.allPrice?if_exists}"/>
    	 <@pp.datePicker label="'${action.getText('requestDate')}'" name="'sparePurchaseDetail.requestDate'"
 						 value="'${(sparePurchaseDetail.requestDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
 						 required="true" maxlength="10"/>
       </tr>
	   <tr>
	     <@ww.textarea label="'${action.getText('requestReason')}'" 
					   name="'sparePurchaseDetail.requestReason'" 
					   value="'${sparePurchaseDetail.requestReason?if_exists}'" rows="'3'" cols="'50'"
					   disabled="false" />
 	  	 <@ww.textarea label="'${action.getText('comment')}'" 
		              name="'sparePurchaseDetail.comment'" 
		              value="'${sparePurchaseDetail.comment?if_exists}'" rows="'3'" cols="'50'"
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
       <#if sparePurchaseDetail.category?exists>
         document.forms[0].elements["category.id"].value = #{sparePurchaseDetail.category.id?if_exists};
       </#if>
       <#if sparePurchaseDetail.detailCategory?exists>
         document.forms[0].elements["detailCategory.id"].value = #{sparePurchaseDetail.detailCategory.id?if_exists};
       </#if>
       toSortDetailTypeBySpareType();          //对备件明细类按备件大类进行排序分类
       if (-1 == document.forms[0].elements["category.id"].value) {
	     document.forms[0].elements["detailCategory.id"].length=1;
	   } else {
	     spareTypeValueChange("category.id", "detailCategory.id");
         <#if sparePurchaseDetail.detailCategory?exists>
           document.forms[0].elements["detailCategory.id"].value = #{sparePurchaseDetail.detailCategory.id?if_exists};
         </#if>
	   } 
     }
     function validate() {
       //验证图号
       if (!validateGraphNo()) {
         return false;
       }
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
      * 验证图号
     */
     function validateGraphNo() {
       if ('' != document.forms["sparePurchaseDetail"].elements["sparePurchaseDetail.graphNo"].value) {
         if(!isValidLength(document.forms["sparePurchaseDetail"], "sparePurchaseDetail.graphNo", null, 50)) {
           alert("${action.getText('sparePurchaseDetail.graphNo.maxLength')}");
           return false;
         }
       }
       return true;
     }
     /*
      * 验证品名是否为空,以及长度
     */
     function validateName() {
       var name = document.forms["sparePurchaseDetail"].elements["sparePurchaseDetail.name"].value;
       if (name == '') {
         alert("${action.getText('sparePurchaseDetail.name.requried')}");
         return false;
       } else if(!isValidLength(document.forms["sparePurchaseDetail"], "sparePurchaseDetail.name", null, 50)) {
         alert("${action.getText('sparePurchaseDetail.name.maxLength')}");
         return false;
       }
       return true;
     }
     /*
      * 验证种类是否为空
     */
     function validateCategory() {
       if ('' == document.forms["sparePurchaseDetail"].elements["category.id"].value) {
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
       if ('' != document.forms["sparePurchaseDetail"].elements["sparePurchaseDetail.specification"].value) {
         if (!isValidLength(document.forms["sparePurchaseDetail"], "sparePurchaseDetail.specification", null, 50)) {
           alert("${action.getText('sparePurchaseDetail.specification.maxLength')}");
           return false;
         }
       }
       //验证型号长度
       if ('' != document.forms["sparePurchaseDetail"].elements["sparePurchaseDetail.model"].value) {
         if (!isValidLength(document.forms["sparePurchaseDetail"], "sparePurchaseDetail.model", null, 50)) {
           alert("${action.getText('sparePurchaseDetail.model.maxLength')}");
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
       var unitPrice = document.forms["sparePurchaseDetail"].elements["sparePurchaseDetail.unitPrice"].value;
       if ('' == unitPrice) {         //验证是否为空
         alert("${action.getText('sparePurchaseDetail.unitPrice.requried')}");
         return false;
       } else if (!isDoubleNumber("sparePurchaseDetail.unitPrice")){  //验证格式
         alert("${action.getText('sparePurchaseDetail.unitPrice.format.error')}");
         return false;
       } else if (!isNumberBetweenBoolen(unitPrice, 10000000001, 0)){  //验证范围
         alert("${action.getText('sparePurchaseDetail.unitPrice.format.error')}");
         return false;
       }
       //验证数量是否为空,以及格式
       var number = document.forms["sparePurchaseDetail"].elements["sparePurchaseDetail.number"].value;
       if ('' == number) {            //验证是否为空
         alert("${action.getText('sparePurchaseDetail.number.requried')}");
         return false;
       } else if (!isDoubleNumber("sparePurchaseDetail.number")){   //验证格式
         alert("${action.getText('sparePurchaseDetail.number.format.error')}");
         return false;
       } else if (!isNumberBetweenBoolen(number, 10000000001, 0)){ //验证范围
         alert("${action.getText('sparePurchaseDetail.number.format.error')}");
         return false;
       }
       return true;
     }
     /*
      * 验证需求日期是否为空,以及格式
     */
     function validateFinishedDate() {
       //验证需求日期是否为空
	   if(document.forms["sparePurchaseDetail"].elements["sparePurchaseDetail.requestDate"].value ==""){
		 alert("${action.getText('select.sparePurchaseDetail.requestDate')}");
		 return false;
	   }
	   //验证需求日期是否为日期型
	   if(!isDate("sparePurchaseDetail.requestDate")){
	     alert("${action.getText('select.right.sparePurchaseDetail.requestDate')}");
		 return false;
	   }
	   return true;
     }
     /*
      * 验证使用工装,需求原因,备注的长度
     */
     function validateReasonAndComment() {
        //验证需求原因长度
       if ('' != document.forms["sparePurchaseDetail"].elements["sparePurchaseDetail.requestReason"].value) {
	     if (!isValidLength(document.forms["sparePurchaseDetail"],"sparePurchaseDetail.requestReason",0,250)) {
	       alert("${action.getText("sparePurchaseDetail.requestReason.maxLength")}");
		   return false;
	     }
	   }
	   //验证备注长度
	   if ('' != document.forms["sparePurchaseDetail"].elements["sparePurchaseDetail.comment"].value) {
	     if (!isValidLength(document.forms["sparePurchaseDetail"],"sparePurchaseDetail.comment",0,250)) {
	       alert("${action.getText("sparePurchaseDetail.comment.maxLength")}");
		   return false;
	     }
	   }
	   return true;
     }
 	/*
	 * 根据单价和数量,计算总价
	*/
	 function calAllPrice() {   
	   if ('' != document.forms["sparePurchaseDetail"].elements["sparePurchaseDetail.unitPrice"].value && isDoubleNumber("sparePurchaseDetail.unitPrice")) {
	     var unitPrice = parseFloat(document.forms["sparePurchaseDetail"].elements["sparePurchaseDetail.unitPrice"].value);
	     if ('' != document.forms["sparePurchaseDetail"].elements["sparePurchaseDetail.number"].value && isNumber("sparePurchaseDetail.number")) {
	       var number = parseInt(document.forms["sparePurchaseDetail"].elements["sparePurchaseDetail.number"].value,10);
	       document.forms["sparePurchaseDetail"].elements["sparePurchaseDetail.allPrice"].value = unitPrice*number;
	       document.forms["sparePurchaseDetail"].elements["allPrice"].value = unitPrice*number;
	     } else {
	       document.forms["sparePurchaseDetail"].elements["sparePurchaseDetail.allPrice"].value = 0;
	       document.forms["sparePurchaseDetail"].elements["allPrice"].value = 0;
	     }
	   }
	 }
   </script>
</@htmlPage>