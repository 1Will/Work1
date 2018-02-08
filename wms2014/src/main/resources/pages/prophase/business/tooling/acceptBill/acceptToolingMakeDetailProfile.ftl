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
<@htmlPage title="${action.getText('toolingMakeDetailProfile.title')}">
 <base target="_self">
   <@ww.form namespace="'/tooling/acceptBillSelector'" name="'toolingMakeDetail'" action="'saveAcceptToolingMakeDetail'" method="'post'">
     <@ww.token name="saveToolingMakeDetailToken"/>
      <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
      <@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
     	<#if acceptBill.id?exists>
			<@ww.hidden name="'acceptBill.id'" value="#{acceptBill.id}"/>
		</#if>
		<#if toolingMakeDetail.id?exists>
			<@ww.hidden name="'toolingMakeDetail.id'" value="#{toolingMakeDetail.id}"/>
		</#if>
     <@inputTable>
	   <tr>
	     <@ww.textfield label="'${action.getText('graphNo')}'" name="'toolingMakeDetail.graphNo'" value="'${toolingMakeDetail.graphNo?if_exists}'" cssClass="'underline'" disabled="false"  required="false" />
		 <@ww.textfield label="'${action.getText('name')}'" name="'toolingMakeDetail.name'" value="'${toolingMakeDetail.name?if_exists}'" cssClass="'underline'" disabled="false"  required="true" />
	   </tr>
       <tr>
       <@ww.select label="'${action.getText('department_sub')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="true" disabled="false" required="true">
		       </@ww.select> 
       	 <@ww.select label="'${action.getText('category')}'" required="false" name="'category.id'" 
		             listKey="id" listValue="value" list="categorys" 
		             emptyOption="true" disabled="false" required="true">
		 </@ww.select>
	   </tr>
	   <tr>
	     <@ww.textfield label="'${action.getText('specification')}'" name="'toolingMakeDetail.specification'" value="'${toolingMakeDetail.specification?if_exists}'" cssClass="'underline'" disabled="false"/>
	     <@ww.textfield label="'${action.getText('model')}'" name="'toolingMakeDetail.model'" value="'${toolingMakeDetail.model?if_exists}'" cssClass="'underline'" disabled="false"/>
       </tr>
       <tr>
         <@ww.textfield label="'${action.getText('unitPrice')}'" name="'toolingMakeDetail.unitPrice'" value="'${(toolingMakeDetail.unitPrice?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'" disabled="false" required="true" onchange="'calAllPrice();'"/>
         <@ww.textfield label="'${action.getText('number')}'" name="'toolingMakeDetail.amount'" value="'${toolingMakeDetail.amount?if_exists}'" cssClass="'underline'" disabled="false" required="true" onchange="'calAllPrice();'"/>
		</tr>
	   <tr>
	    <@ww.select label="'${action.getText('tooling.calUnit')}'" required="false" name="'calUnit.id'" 
		             listKey="id" listValue="value" list="calUnits" 
		             emptyOption="true" disabled="false" required="true">
		             </@ww.select> 
		 <@ww.textfield label="'${action.getText('allPrice')}'" name="'totalPrice'" value="'${(toolingMakeDetail.totalPrice?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'" disabled="true"/>
    	 <@ww.hidden name="'toolingMakeDetail.totalPrice'" value="'${toolingMakeDetail.totalPrice?if_exists}'"/>
	   </tr>
	   <tr>
	      <@ww.select label="'${action.getText('result')}'" 
                             required="true" name="'result'" 
                             value="'${result?if_exists}'"
                             listKey="value" listValue="label"
                             list="procResults" 
                              emptyOption="true" 
                              disabled="false"
                            >
          </@ww.select>
	       <@ww.textarea label="'${action.getText('comment')}'" 
		              name="'toolingMakeDetail.memo'" 
		              value="'${toolingMakeDetail.memo?if_exists}'" rows="'3'" cols="'50'"
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
     window.onload = function() {
       <#if toolingMakeDetail.toolingCategory?exists>
         document.forms[0].elements["category.id"].value = #{toolingMakeDetail.toolingCategory.id?if_exists};
       </#if>
       <#if toolingMakeDetail.calUnit?exists>
         document.forms[0].elements["calUnit.id"].value = #{toolingMakeDetail.calUnit.id?if_exists};
       	</#if>
       	<#if toolingMakeDetail.department?exists>
	     document.forms[0].elements["department.id"].value=#{toolingMakeDetail.department.id?if_exists};
	     </#if>
	    <#if  toolingMakeDetail.id?exists>
	      <#if toolingMakeDetail.result?exists>
	        <#if toolingMakeDetail.result=='QUALIFIED'>
              document.forms[0].elements["result"].value ='QUALIFIED';
            </#if>
            <#if toolingMakeDetail.result=='DISQUALIFICATION'>
              document.forms[0].elements["result"].value ='DISQUALIFICATION';
            </#if>
	     </#if>
	     </#if>
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
       //验证部门
       if(!validateDepartment()){
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
       //验证计量单位
        if ('' == document.forms["toolingMakeDetail"].elements["calUnit.id"].value) {
         alert("${action.getText('calUnit.id.requried')}");
         return false;
       }
     
       //验证验收结果
       if ('' == document.forms["toolingMakeDetail"].elements["result"].value) {
         alert("${action.getText('result.requried')}");
         return false;
       }
       //验证计划完成日期
    <#--   if (!validateFinishedDate()) {
         return false;
       }-->
       //验证需求原因和备注
       if (!validateReasonAndComment()) {
         return false;
       }
       return true;
     }
     /*
      * 验证图号长度 
     */
     function validateGraphNo() {
     <#--
       var graphNo = document.forms["toolingMakeDetail"].elements["toolingMakeDetail.graphNo"].value;
       if (graphNo == '') {
         alert("${action.getText('toolingMakeDetail.graphNo.requried')}");
         return false;
       } else if(!isValidLength(document.forms["toolingMakeDetail"], "toolingMakeDetail.graphNo", null, 50)) {
         alert("${action.getText('toolingMakeDetail.graphNo.maxLength')}");
         return false;
       }
       -->
      var graphNo = document.forms["toolingMakeDetail"].elements["toolingMakeDetail.graphNo"].value;
       if ('' != graphNo) {
         if(!isValidLength(document.forms["toolingMakeDetail"], "toolingMakeDetail.graphNo", null, 50)) {
           alert("${action.getText('toolingMakeDetail.graphNo.maxLength')}");
           return false;
         }
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
     //验证部门
     function validateDepartment(){
       var depart = document.forms["toolingMakeDetail"].elements["department.id"].value;
       if(depart==''){
         alert("${action.getText('toolingMakeDetail.department')}");
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
       var number = document.forms["toolingMakeDetail"].elements["toolingMakeDetail.amount"].value;
       if ('' == number) {            //验证是否为空
         alert("${action.getText('toolingMakeDetail.number.requried')}");
         return false;
       } else if (!isDoubleNumber("toolingMakeDetail.amount")){   //验证格式
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
	   //验证备注长度
	   if ('' != document.forms["toolingMakeDetail"].elements["toolingMakeDetail.memo"].value) {
	     if (!isValidLength(document.forms["toolingMakeDetail"],"toolingMakeDetail.memo",0,250)) {
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
	     if ('' != document.forms["toolingMakeDetail"].elements["toolingMakeDetail.amount"].value && isNumber("toolingMakeDetail.amount")) {
	       var number = parseInt(formatDigital(document.forms["toolingMakeDetail"].elements["toolingMakeDetail.amount"].value),10);
	       document.forms["toolingMakeDetail"].elements["toolingMakeDetail.totalPrice"].value = unitPrice*number;
	       document.forms["toolingMakeDetail"].elements["totalPrice"].value = unitPrice*number;
	     } else {
	       document.forms["toolingMakeDetail"].elements["toolingMakeDetail.totalPrice"].value = 0;
	       document.forms["toolingMakeDetail"].elements["totalPrice"].value = 0;
	     }
	   }
	 }
   </script>
</@htmlPage>