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
<#--$Id: subscribeDetailProfile.ftl 11279 2008-03-12 01:12:13Z mwei $ -->

<#include "../../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('toolingPurchaseDetail.title')}">
<base target="_self">
	<@ww.form namespace="'/tooling/prophase/business'" name="'subscribeDtl'" action="'saveSubscribeDetail'" method="'post'" validate="true">
		<@ww.token name="saveSubscribeDtlToken"/>
		<#if subscribe.id?exists>
			<@ww.hidden name="'subscribe.id'" value="#{subscribe.id}"/>
		</#if>
		<#if subscribeDtl.id?exists>
		 <@ww.hidden name="'subscribeDtl.id'" value="#{subscribeDtl.id}"/>  
		 </#if>
       <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
		<@inputTable>
			<tr>
			    <@ww.textfield label="'${action.getText('graphNo')}'" name="'subscribeDtl.graphNo'" value="'${subscribeDtl.graphNo?if_exists}'"  cssClass="'underline'" />
			    <@ww.textfield label="'${action.getText('name')}'" name="'subscribeDtl.name'" value="'${subscribeDtl.name?if_exists}'"  cssClass="'underline'" required="true"/>
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('model')}'" name="'subscribeDtl.model'" value="'${subscribeDtl.model?if_exists}'"  cssClass="'underline'" />
			   <@ww.textfield label="'${action.getText('specification')}'" name="'subscribeDtl.specification'" value="'${subscribeDtl.specification?if_exists}'"  cssClass="'underline'" />
               	
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('amount')}'" name="'subscribeDtl.amount'" value="'${subscribeDtl.amount?if_exists}'" cssClass="'underline'" disabled="false" required="true" onchange="'calAllPrice();'"/>
				<@ww.textfield label="'${action.getText('unitPrice')}'" name="'subscribeDtl.unitPrice'" value="'${subscribeDtl.unitPrice?if_exists}'" cssClass="'underline'" disabled="false" required="true" onchange="'calAllPrice();'"/>			
    	   		
	        </tr>
	        <tr>
	          <@ww.textfield label="'${action.getText('totalPrice')}'" name="'totalPrice'" value="'${subscribeDtl.totalPrice?if_exists}'"  cssClass="'underline'"   readonly="true"/>
	          <@ww.hidden name="'subscribeDtl.totalPrice'" value="'${subscribeDtl.totalPrice?if_exists}'"/>  
	          <@pp.datePicker label="'${action.getText('requestDate')}'" name="'subscribeDtl.requireDate'"
	     				  value="'${(subscribeDtl.requireDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     				  required="true" maxlength="10"/>
	        
	        </tr>
	        <tr>
	        <@ww.textfield label="'${action.getText('supplier')}'" name="'subscribeDtl.supplierName'" value="'${subscribeDtl.supplierName?if_exists}'"  cssClass="'underline'"/>
	        </tr>
	        <tr>
	         <@ww.textarea label="'${action.getText('comment')}'" 
					         name="'subscribeDtl.comment'" 
					         value="'${subscribeDtl.comment?if_exists}'" rows="'3'" cols="'60'" 
				 />   
	        </tr>
		</@inputTable>
		<@buttonBar>
		<#if !(action.isReadOnly())>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return subscribeValidate();'"/>
		 </#if>
		    <@vbutton name="'close'"  class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
		</@buttonBar>
	</@ww.form>
	<script language="javascript">
	window.onload=function(){
	   a=new Date();
	   var time=a.format("yyyy-MM-dd");
	   document.getElementById("subscribeDtl.requireDate").value=time;
	    
	}
	
	 //根据单价和数量,计算总价
	
	 function calAllPrice() {  
	 
	   if ('' != document.forms[0].elements["subscribeDtl.unitPrice"].value && isDoubleNumber("subscribeDtl.unitPrice")) {
	     var unitPrice =parseFloat(formatDigital(document.forms[0].elements["subscribeDtl.unitPrice"].value));
	     if ('' != document.forms[0].elements["subscribeDtl.amount"].value && isNumber("subscribeDtl.amount")) {
	       var number =parseInt(formatDigital(document.forms[0].elements["subscribeDtl.amount"].value));
	       document.forms[0].elements["totalPrice"].value =unitPrice*number;
	       document.forms[0].elements["subscribeDtl.totalPrice"].value =unitPrice*number;
	     } else {
	       document.forms[0].elements["totalPrice"].value = 0;
	       document.forms[0].elements["subscribeDtl.totalPrice"].value = 0;
	     }
	   }
	 }
	 
	
	 
	  function subscribeValidate(){
	       if(document.getElementById("subscribeDtl.name").value==''){//验证品名是否为空
              alert('${action.getText('input.proudctName')}');
              return false;	       
	       }
	      if(document.getElementById("subscribeDtl.name").value!=''){//如果品名不为空,验证品名的输入长度
	        if(!isValidLength(document.forms[0],"subscribeDtl.name",null,50)){
	          alert('${action.getText('subscribeDtl.name.length')}');
	          return false;
	         }
	      }
	       if(document.getElementById("subscribeDtl.model").value!=''){//验证型号的输入长度
	        if(!isValidLength(document.forms[0],"subscribeDtl.model",null,50)){
	          alert('${action.getText('subscribeDtl.model.length')}');
	          return false;
	         }
	      }
	      if(document.getElementById("subscribeDtl.specification").value!=''){//验证规格的输入长度
	        if(!isValidLength(document.forms[0],"subscribeDtl.specification",null,50)){
	          alert('${action.getText('subscribeDtl.specification.length')}');
	          return false;
	         }
	      }
	     //验证单价是否为空,以及格式
       if(isNotEmpty(document.forms[0],"subscribeDtl.unitPrice")) {
	     if (!isDoubleNumber("subscribeDtl.unitPrice")){
		     alert("${action.getText('subscribeDtl.unitPrice.isNotNumber')}");
		       return false;
	       } else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["subscribeDtl.unitPrice"].value, 10000000001, 0)){  //验证范围
		     alert("${action.getText('subscribeDtl.unitPrice.maxLength')}");
		      return false;
	      }
     }
	       //验证数量是否为空,以及格式
       var number = document.forms[0].elements["subscribeDtl.amount"].value;
       if ('' == number) {            //验证是否为空
         alert("${action.getText('input.subscribeDtl.amount')}");
         return false;
       } else if (!isDoubleNumber("subscribeDtl.amount")){   //验证格式
         alert("${action.getText('format.error')}");
         return false;
       } else if (!isNumberBetweenBoolen(number, 1000000001, 0)){ //验证范围
         alert("${action.getText('subscribeDtl.amount.maxLength')}");
         return false;
       }
	  if(document.getElementById("subscribeDtl.requireDate").value==''){//验证日期
	         alert('${action.getText('requiredate.not.null')}');
	         return false; 
	       }
	   var date=document.getElementById("subscribeDtl.requireDate").value;
	     if(!isDate("subscribeDtl.requireDate")){
	         alert('${action.getText('date.format.error')}');
	         return false;
	       }
	    if(!isDateLessEqualThenCurrent(date)){
	              alert('${action.getText('afresh.requireDate')}');
	              return false;
	        }
	    if(document.getElementById("subscribeDtl.comment").value!=''){//如果备注的长度不为空  验证备注的长度
	         if(!isValidLength(document.forms[0],"subscribeDtl.comment",null,250)){
	          alert('${action.getText('text.subscribeDtl.comment.length')}');
	          return false;
	         }
	       }
	       return true;
	  }		
	</script>
</@htmlPage>