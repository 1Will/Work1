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
<#--$Id: purchaseBillDetailProfile.ftl 11279 2008-03-12 01:12:13Z mwei $ -->

<#include "../../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('purchaseContract.title')}">
<base target="_self">
	
	<@ww.form namespace="'/toooling/purchaseContract'" name="'purchaseBillDtl'" action="'savePurchaseBillDetail'" method="'post'" validate="true">
		<@ww.token name="savePurchaseBillDetailToken"/>
		 <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
		<@ww.hidden name="'purchaseBill.id'" value="'${req.getParameter('purchaseBill.id')?if_exists}'"/>
		<@ww.hidden name="'purchaseBillDtl.id'" value="'${req.getParameter('purchaseBillDtl.id')?if_exists}'"/>
		<@inputTable>
			<tr>
				<@ww.textfield label="'${action.getText('graphNo')}'" name="'purchaseBillDtl.graphNo'" value="'${purchaseBillDtl.graphNo?if_exists}'"  cssClass="'underline'" />
			    <@ww.textfield label="'${action.getText('nameBill')}'" name="'purchaseBillDtl.name'" value="'${purchaseBillDtl.name?if_exists}'"  cssClass="'underline'" required="true"/>
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('model')}'" name="'purchaseBillDtl.model'" value="'${purchaseBillDtl.model?if_exists}'"  cssClass="'underline'" />
			    <@ww.textfield label="'${action.getText('specification')}'" name="'purchaseBillDtl.specification'" value="'${purchaseBillDtl.specification?if_exists}'"  cssClass="'underline'" />
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('amount')}'" name="'purchaseBillDtl.amount'" value="'${purchaseBillDtl.amount?if_exists}'" cssClass="'underline'" disabled="false" required="true" onchange="'calAllPrice();'"/>
			    <@ww.textfield label="'${action.getText('unitPrice')}'" name="'purchaseBillDtl.unitPrice'" value="'${(purchaseBillDtl.unitPrice?string('#,###,##0.##'))?if_exists}'" cssClass="'underline'" disabled="false" required="true" onchange="'calAllPrice();'"/>
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('total.Price')}'" name="'totalPrice'" value="'${(purchaseBillDtl.totalPrice?string('#,###,##0.##'))?if_exists}'"  cssClass="'underline'"   readonly="true"/>
			    <@ww.hidden name="'purchaseBillDtl.totalPrice'" value="'${purchaseBillDtl.totalPrice?if_exists}'"/>
				<@pp.datePicker label="'${action.getText('reqDeliveryDate')}'" name="'purchaseBillDtl.reqDeliveryDate'"
					value="'${(purchaseBillDtl.reqDeliveryDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="10"/>
			</tr>
			<tr>
			   	<@ww.textarea label="'${action.getText('comment')}'"  name="'purchaseBillDtl.comment'" value="'${purchaseBillDtl.comment?if_exists}'" rows="'3'" cols="'60'" />
			</tr>
 <script language="javascript">
 
	/*
	 * 根据单价和数量,计算总价
	*/
	 function calAllPrice() {  
	   if ('' != document.forms[0].elements["purchaseBillDtl.unitPrice"].value && isDoubleNumber("purchaseBillDtl.unitPrice")) {
	     var unitPrice = formatDigital(document.forms[0].elements["purchaseBillDtl.unitPrice"].value);
	     if ('' != document.forms[0].elements["purchaseBillDtl.amount"].value && isNumber("purchaseBillDtl.amount")) {
	       var number = formatDigital(document.forms[0].elements["purchaseBillDtl.amount"].value);
	       document.forms[0].elements["totalPrice"].value = parseFloat(unitPrice)*parseInt(number);
	       document.forms[0].elements["purchaseBillDtl.totalPrice"].value = parseFloat(unitPrice)*parseInt(number);
	     } else {
	       document.forms[0].elements["totalPrice"].value = 0;
	       document.forms[0].elements["purchaseBillDtl.totalPrice"].value = 0;
	     }
	   }
	 }			
	function validatePurchase(){
		if ('' != document.forms[0].elements["purchaseBillDtl.graphNo"].value) {
              if (!isValidLength(document.forms[0], "purchaseBillDtl.graphNo", null, 50)) {
                  alert("${action.getText('purchaseBillDtl.graphNo.maxLength')}");
                 return false;
              }  
         }
           if(getObjByNameRe("purchaseBillDtl.name").value==''){
	        alert('${action.getText('purchaseBillDtl.name.not.null')}');
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "purchaseBillDtl.name", null, 50)){
				alert("${action.getText('purchaseBillDtl.name.maxLength')}");
				return  false;
			   }
	     }
              if ('' != document.forms[0].elements["purchaseBillDtl.model"].value) {
              if (!isValidLength(document.forms[0], "purchaseBillDtl.model", null, 50)) {
                  alert("${action.getText('purchaseBillDtl.model.maxLength')}");
                 return false;
              }  
             }
              if ('' != document.forms[0].elements["purchaseBillDtl.specification"].value) {
              if (!isValidLength(document.forms[0], "purchaseBillDtl.specification", null, 50)) {
                  alert("${action.getText('purchaseBillDtl.specification.maxLength')}");
                 return false;
              }  
             }
       
     //验证单价是否为空,以及格式
     if(isNotEmpty(document.forms[0],"purchaseBillDtl.unitPrice")) {
	     if (!isDoubleNumber("purchaseBillDtl.unitPrice")){
		    alert("${action.getText('purchaseBillDtl.unitPrice.isNotNumber')}");
		       return false;
	       } else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["purchaseBillDtl.unitPrice"].value, 10000000001, 0)){  //验证范围
		     alert("${action.getText('purchaseBillDtl.unitPrice.maxLength')}");
		      return false;
	      }
     }
	 //验证数量是否为空,以及格式
       var number = document.forms[0].elements["purchaseBillDtl.amount"].value;
       if ('' == number) {            //验证是否为空
         alert("${action.getText('input.purchaseBillDtl.amount')}");
         return false;
       } else if (!isDoubleNumber("purchaseBillDtl.amount")){   //验证格式
         alert("${action.getText('purchaseBillDtl.amount.maxLength')}");
         return false;
       } else if (!isNumberBetweenBoolen(number, 1000000001, 0)){ //验证范围
         alert("${action.getText('purchaseBillDtl.amount.maxLength')}");
         return false;
       }
       
       
	 if(getObjByNameRe("purchaseBillDtl.amount").value==''){
		 alert('${action.getText('input.number')}');
		 return false;
		}
	 if(getObjByNameRe("purchaseBillDtl.unitPrice").value==''){
		alert('${action.getText('purchaseBillDtl.unitPrice')}');
		return false;
		}
	 if(getObjByNameRe("purchaseBillDtl.reqDeliveryDate").value==''){ //验证日期
	    alert('${action.getText('purchaseBillDtl.reqDeliveryDate.not.null')}');
	    return false;
	    }
	 var date=getObjByNameRe("purchaseBillDtl.reqDeliveryDate").value;
		if(!isDate("purchaseBillDtl.reqDeliveryDate")){
		  alert("${action.getText('select.right.purchaseBillDtl.reqDeliveryDate')}");
		 return false;
		 }
		   if(!isDateLessEqualThenCurrent(date)){
			  alert("${action.getText('afresh.purchaseBillDtl.reqDeliveryDate')}");
		      return false;
		    }
	       if(getObjByNameRe("purchaseBillDtl.comment").value!=''){//验证字符长度
		   if(!isValidLength(document.forms[0], "purchaseBillDtl.comment", null, 250)){
				alert("${action.getText('purchaseBill.purchaseBillDtl.comment.length')}");
				return  false;
			   }
			}
		 return true;
	}
			</script>			
		</@inputTable>
		<@buttonBar>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validatePurchase()'"/>
		    <@vbutton name="'close'"  class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
		</@buttonBar>
		
	</@ww.form>
	
</@htmlPage>