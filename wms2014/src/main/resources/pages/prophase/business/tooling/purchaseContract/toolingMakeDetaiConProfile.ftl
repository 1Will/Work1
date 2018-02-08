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

	<@ww.form namespace="'/toooling/purchaseContract'" name="'listForm'" action="'saveToolingMakeDetailPurchaseConcat'"  method="'post'">
		<@ww.token name="saveToolingMakeDetailPurchaseConcatToken"/>
		<@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
		<#if purchaseBill.id?exists>
			<@ww.hidden name="'purchaseBill.id'" value="#{purchaseBill.id}"/>
		</#if>
		
		<#if purchaseBillDetail.id?exists>
		 	<@ww.hidden name="'toolingMakeDetail.id'" value="#{purchaseBillDetail.id}"/>  
		</#if>
		<@inputTable>
		<tr>
		    <@ww.textfield label="'${action.getText('graphNo')}'" name="'purchaseBillDetail.graphNo'" value="'${purchaseBillDetail.graphNo?if_exists}'"  cssClass="'underline'" />
		    <@ww.textfield label="'${action.getText('name')}'" name="'purchaseBillDetail.name'" value="'${purchaseBillDetail.name?if_exists}'"  cssClass="'underline'" required="true"/>
		</tr>
		<tr>
	       	<@ww.select label="'${action.getText('category')}'" required="false" name="'category.id'" 
             	listKey="id" listValue="value" list="categorys" 
             	emptyOption="true" disabled="false" required="true">
		    </@ww.select>
		    <@ww.textfield label="'${action.getText('specification')}'" name="'purchaseBillDetail.specification'" value="'${purchaseBillDetail.specification?if_exists}'"  cssClass="'underline'" />
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('model')}'" name="'purchaseBillDetail.model'" value="'${purchaseBillDetail.model?if_exists}'"  cssClass="'underline'" />
			<@ww.textfield label="'${action.getText('unitPrice')}'" name="'purchaseBillDetail.unitPrice'"  value="'${(purchaseBillDetail.unitPrice?string('#,###,##0.##'))?if_exists}'" onchange="'calAllPrice();'" cssClass="'underline'" required="true"/>
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('amount')}'" name="'purchaseBillDetail.amount'" value="'${purchaseBillDetail.amount?if_exists}'"  cssClass="'underline'" onchange="'calAllPrice();'"/>
			<@ww.select label="'${action.getText('tooling.calUnit')}'" required="false" name="'calUnit.id'" 
		             listKey="id" listValue="value" list="calUnits" 
		             emptyOption="true" disabled="false" required="true">
		             </@ww.select>
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('totalPrice')}'" name="'totalPrice'" value="'${purchaseBillDetail.totalPrice?string('#,###,##0.00')?if_exists}'" readonly="true" cssClass="'underline'" />
			<@ww.hidden name="'purchaseBillDetail.totalPrice'" value="'${purchaseBillDetail.totalPrice?if_exists}'"/> 
			<@pp.datePicker label="'${action.getText('requestDate')}'" name="'purchaseBillDetail.reqDeliveryDate'"
 				value="'${(purchaseBillDetail.reqDeliveryDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
 				required="true" maxlength="10"/>
 		</tr>
		<tr>
 			<@ww.textarea label="'${action.getText('comment')}'" 
		              name="'purchaseBillDetail.comment'" 
		              value="'${purchaseBillDetail.comment?if_exists}'" rows="'3'" cols="'50'"
		              disabled="false" />
		</tr>

		</@inputTable>
	   <@buttonBar>
	   <#if !(action.isReadOnly())>
       <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validateToolingMake()'">
       </@vsubmit>
        </#if>
       <input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
     </@buttonBar>
	</@ww.form>
	<script language="javascript">
		window.onload=function(){
	   		<#if purchaseBillDetail.toolingCategory?exists>
	    	 getObjByNameRe("category.id").value = #{purchaseBillDetail.toolingCategory.id};
	   		</#if>
	   		<#if purchaseBillDetail.calUnit?exists>
         		document.forms[0].elements["calUnit.id"].value = #{purchaseBillDetail.calUnit.id?if_exists};
       		</#if>
	 	}
	 	function validateToolingMake(){
			if ('' != document.forms[0].elements["purchaseBillDetail.graphNo"].value) {
              if (!isValidLength(document.forms[0], "purchaseBillDetail.graphNo", null, 50)) {
                  alert("${action.getText('purchaseBillDtl.graphNo.maxLength')}");
                 return false;
              }  
             }
			 if(getObjByNameRe("purchaseBillDetail.name").value==''){
				 alert('${action.getText('EnculodeName.not.null')}');
				 return false;
				}
		    if ('' != document.forms[0].elements["purchaseBillDetail.name"].value) {
              if (!isValidLength(document.forms[0], "purchaseBillDetail.name", null, 50)) {
                  alert("${action.getText('purchaseBillDtl.name.maxLength')}");
                 return false;
              }  
             }
             if(getObjByNameRe("category.id").value==''){
				 alert('${action.getText('category.not.null')}');
				 return false;
				}
              if ('' != document.forms[0].elements["purchaseBillDetail.model"].value) {
              if (!isValidLength(document.forms[0], "purchaseBillDetail.model", null, 50)) {
                  alert("${action.getText('purchaseBillDtl.model.maxLength')}");
                 return false;
              }  
             }
              if ('' != document.forms[0].elements["purchaseBillDetail.specification"].value) {
              if (!isValidLength(document.forms[0], "purchaseBillDetail.specification", null, 50)) {
                  alert("${action.getText('purchaseBillDtl.specification.maxLength')}");
                 return false;
              }  
             }
             //验证单价是否为空,以及格式
     		if(isNotEmpty(document.forms[0],"purchaseBillDetail.unitPrice")) {
	     		if (!isDoubleNumber("purchaseBillDetail.unitPrice")){
		    		alert("${action.getText('purchaseBillDtl.unitPrice.isNotNumber')}");
		      		 return false;
	      		 } else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["purchaseBillDetail.unitPrice"].value, 10000000001, 0)){  //验证范围
		     		alert("${action.getText('purchaseBillDtl.unitPrice.maxLength')}");
		     		 return false;
	      	}
     		}
			 //验证数量是否为空,以及格式
      		 var number = document.forms[0].elements["purchaseBillDetail.amount"].value;
       		if ('' == number) {            //验证是否为空
         		alert("${action.getText('input.purchaseBillDtl.amount')}");
        		 return false;
       		} else if (!isDoubleNumber("purchaseBillDetail.amount")){   //验证格式
         		alert("${action.getText('format.error')}");
         		return false;
      		} else if (!isNumberBetweenBoolen(number, 1000000001, 0)){ //验证范围
        		 alert("${action.getText('purchaseBillDtl.amount.maxLength')}");
        		 return false;
      		}
      		
       		if(getObjByNameRe("purchaseBillDetail.amount").value==''){
		 		alert('${action.getText('input.number')}');
		 		return false;
				}
	 		if(getObjByNameRe("purchaseBillDetail.unitPrice").value==''){
				alert('${action.getText('purchaseBillDtl.unitPrice')}');
				return false;
				}
			//验证计量单位
        	if ('' == document.forms["listForm"].elements["calUnit.id"].value) {
         		alert("${action.getText('calUnit.id.requried')}");
         		return false;
      		 }
	 		if(getObjByNameRe("purchaseBillDetail.reqDeliveryDate").value==''){ //验证日期
	    		alert('${action.getText('purchaseBillDtl.reqDeliveryDate.not.null')}');
	    		return false;
	   		 }
	   		 
	   		 var date=getObjByNameRe("purchaseBillDetail.reqDeliveryDate").value;
				if(!isDate("purchaseBillDetail.reqDeliveryDate")){
		 		 alert("${action.getText('select.right.purchaseBillDtl.reqDeliveryDate')}");
		 		return false;
		 		}
	       	if(getObjByNameRe("purchaseBillDetail.comment").value!=''){//验证字符长度
		   	if(!isValidLength(document.forms[0], "purchaseBillDetail.comment", null, 250)){
				alert("${action.getText('purchaseBill.purchaseBillDtl.comment.length')}");
				return  false;
			   }
			}
		 return true;
         }
	 //计算总价
	 function calAllPrice() {  
	   if ('' != document.forms[0].elements["purchaseBillDetail.unitPrice"].value && isDoubleNumber("purchaseBillDetail.unitPrice")) {
	     var unitPrice =parseFloat(formatDigital(document.forms[0].elements["purchaseBillDetail.unitPrice"].value));
	     if ('' != document.forms[0].elements["purchaseBillDetail.amount"].value && isNumber("purchaseBillDetail.amount")) {
	       var number =parseInt(formatDigital(document.forms[0].elements["purchaseBillDetail.amount"].value));
	       document.forms[0].elements["totalPrice"].value =unitPrice*number;
	       document.forms[0].elements["purchaseBillDetail.totalPrice"].value =unitPrice*number;
	     } else {
	       document.forms[0].elements["totalPrice"].value = 0;
	       document.forms[0].elements["purchaseBillDetail.totalPrice"].value = 0;
	     }
	   }
	 }
	 /* 判断数量格式 */
	  function checkInputNumber(){
	  	var number = getObjByNameRe("purchaseBillDetail.amount").value;
	  		if (!isDoubleNumberCheck(number)){   			//验证输入数量的格式
			 alert("${action.getText('toolingMake.number.formate.error')}");
			 return false;
	       	}else if(!isNumberBetweenBoolen(number, 1000000001, 0)){		//验证输入数量的范围
		     alert("${action.getText('toolingMake.number.maxLength')}");
		     return false;
	       	}
	       	return true;
		}
	    
	     /* 判断单价格式 */
	  	function checkInputUnitPrice(){
	      	var unitPrice = getObjByNameRe("purchaseBillDetail.unitPrice").value;
	  		if (!isDoubleNumberCheck(unitPrice)){   			//验证输入数量的格式
			 alert("${action.getText('toolingMake.unitPrice.formate.error')}");
			 return false;
	       	}
	       	return true;	         
		}
	</script>
</@htmlPage>