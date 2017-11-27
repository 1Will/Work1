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

<#-- $Id: customerInfoProfile.ftl 2009-12-14 8:48:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('paymentDetails.edit')}">
<@ww.form name="'listForm'" action="'savePaymentDetailsAction'" method="'post'">
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="savePaymentDetailsActionToken"/>
	<#if paymentDetails.id?exists>
		<@ww.hidden name="'paymentDetails.id'" value="#{paymentDetails.id?if_exists}"/>
	</#if>
	<@ww.hidden id="paymentorderid" name="'paymentorderid'" value="#{paymentorderid?if_exists}"/>
	
	<@inputTable>
		<tr>
		
			<@select label="${action.getText('paymentDetails.paymentMode')}" 
		   	   anothername="selectPaymentMode"
		       name="paymentMode.id" 
		       value="${req.getParameter('paymentMode.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allPaymentMode" 
		       emptyOption="false" 
		       disabled="false" 
		       required="true">
		    </@select>
		    
		    <@select label="${action.getText('paymentDetails.stipple')}" 
		   	   anothername="selectStipple"
		       name="stipple.id" 
		       value="${req.getParameter('stipple.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allStipple" 
		       emptyOption="false" 
		       disabled="false" 
		       required="true">
		    </@select>
		    
			<@textfield id="account" label="${action.getText('paymentDetails.account')}" maxlength="5"  name="paymentDetails.account"  value="${paymentDetails.account?if_exists}"  required="true" anothername="checkaccount"/>
		</tr>
			<@textfield id="paymentMoney" label="${action.getText('paymentDetails.paymentMoney')}" maxlength="10"  name="paymentDetails.paymentMoney"  value="#{paymentDetails.paymentMoney?if_exists}" type="N"  required="true" anothername="checkpaymentMoney"/>
			<@pp.datePicker 
				label="'${action.getText('paymentDetails.paymentTime')}'" 
				name="'paymentDetails.paymentTime'" 
	   			value="'${(paymentDetails.paymentTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="false"
				readonly="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>

		    <@select label="${action.getText('paymentDetails.stipple')}" 
		   	   anothername="selectStipple"
		       name="moneyType.id" 
		       value="${req.getParameter('moneyType.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allMoneyType" 
		       emptyOption="false" 
		       disabled="false" 
		       required="true">
		    </@select>
		</tr>
		<tr>
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('paymentDetails.remark')}:
        		</label>
        	</td>
	        <td colspan="5">
	        	<textarea name="paymentDetails.remark" rows="6"  >${paymentDetails.remark?if_exists}</textarea>
	        </td>
	        <script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("paymentDetails.remark").cols =width;
			</script>
		</tr>
		</@inputTable>
	<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		<@vbutton class="button" value="${action.getText('close')}" onclick="window.close();"/>
    </@buttonBar>
</@ww.form>
<script language="JavaScript" type="text/JavaScript"> 
	window.onload=function(){
		<#if paymentDetails.paymentMode?exists>
			getObjByName('paymentMode.id').value='${paymentDetails.paymentMode.id?if_exists}';
		</#if>
		<#if paymentDetails.stipple?exists>
			getObjByName('stipple.id').value='${paymentDetails.stipple.id?if_exists}';
		</#if>
		<#if paymentDetails.moneyType?exists>
			getObjByName('moneyType.id').value='${paymentDetails.moneyType.id?if_exists}';
		</#if>
		
	}
	<#-- 提交验证-->
	function storeValidation(){
		if(!selectCheck_selectPaymentMode()){
			getObjByName('paymentMode.id').focus();
		    return false;
		  }
		 if(!selectCheck_selectStipple()){
			getObjByName('stipple.id').focus();
		    return false;
		  } 
		 
		  if(!textfieldCheck_checkaccount()){
				jgetObjByName("#account").focus();
				return false;
		}
		 if(!textfieldCheck_checkpaymentMoney()){
				jgetObjByName("#paymentMoney").focus();
				return false;
			}
		
		if(parseInt(jgetObjByName("#paymentMoney").val())<=0){
			alert("${action.getText('validation.paymentMoney')}");
			jgetObjByName("#paymentMoney").focus();
			return false;
		}
		<#-- if(isNaN(jgetObjByName("#paymentMoney").val())){
		 	alert("${action.getText('validation.paymentMoney')}");
			jgetObjByName("#paymentMoney").focus();
			return false;
		}-->
		if(!textfieldCheck_checkpaymentMoney()){
				jgetObjByName("#paymentMoney").focus();
				return false;
		}
		
		
		  
		  
		  
	<#--	if(jgetObjByName("#productid").val()==""){
			alert("${action.getText('validation.productid')}");
		    return false;
		}
		if(!isNumber("count")){
			alert("${action.getText('validation.count')}");
			jgetObjByName("#count").focus();
			return false;
		}
		if(!isDoubleNumber("unitPrice")){
			alert("${action.getText('validation.unitPrice')}");
			jgetObjByName("#unitPrice").focus();
			return false;
		}
		if(!isDoubleNumber("discount")){
			alert("${action.getText('validation.discount0')}");
			jgetObjByName("#discount").focus();
			return false;
		}
		if(parseInt(jgetObjByName("#count").val())<=0){
			alert("${action.getText('validation.count')}");
			jgetObjByName("#count").focus();
			return false;
		}
		if(parseInt(jgetObjByName("#unitPrice").val())<=0){
			alert("${action.getText('validation.unitPrice')}");
			jgetObjByName("#unitPrice").focus();
			return false;
		}
		
		if(parseInt(jgetObjByName("#discount").val())<0 || parseInt(jgetObjByName("#discount").val())>100){
			alert("${action.getText('validation.discount1')}");
			jgetObjByName("#discount").focus();
			return false;
		}else{
			jgetObjByName("#totalPrice").val(jgetObjByName("#count").val()*jgetObjByName("#unitPrice").val()*(jgetObjByName("#discount").val()/100));
		}	
		-->
		<#--if(!isDoubleNumber("totalPrice")){
			alert("总价是数字!");
			jgetObjByName("#totalPrice").focus();
			return false;
		}-->
	<#--	if(!selectCheck_selectCheckUnit()){
			getObjByName('unit.id').focus();
		    return false;
		}-->
		return true;
	}
</script>

</@htmlPage>
