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
<@htmlPage title="${action.getText('repairMantanceDetail.title')}">
<base target="_self">
	<@ww.form namespace="'/toooling/purchaseContract'" name="'listForm'" action="'saveRepairMaintenancePurchaseConcat'" method="'post'">
		<@ww.token name="saveRepairMaintenancePurchaseConcatToken"/>
		<@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
		<#if purchaseBill.id?exists>
			<@ww.hidden name="'purchaseBill.id'" value="#{purchaseBill.id}"/>
		</#if>
		<#if purchaseBillDetail.id?exists>
		 	<@ww.hidden name="'toolingMakeDetail.id'" value="#{purchaseBillDetail.id}"/>  
		</#if>
		<@inputTable>
	   <@eam2008_ToolingSelector/>
	   <tr>
	   	 <#assign toolingDepartment=''/>
	   	 <#assign toolingType=''/>
	   	 <#assign specification=""/>
	   	 <#assign model=""/>
	   	 <#if tooling?exists>
	       <#if tooling.department?exists>
	         <#assign toolingDepartment="${tooling.department.name?if_exists}"/>
	       </#if>
	       <#if tooling.toolingType?exists>
	         <#assign toolingType="${tooling.toolingType.value?if_exists}"/>
	       </#if>
	       <#if tooling.specification?exists>
	         <#assign specification="${tooling.specification?if_exists}"/>
	       </#if>
	       <#if tooling.model?exists>
	         <#assign model="${tooling.model?if_exists}"/>
	       </#if>
	     </#if>
	     <@ww.textfield label="'${action.getText('department')}'" name="'tooling.department'" value="'${toolingDepartment?if_exists}'" cssClass="'underline'" disabled="true"/>

	     <@ww.textfield label="'${action.getText('category')}'" name="'tooling.toolingType'" value="'${toolingType?if_exists}'" cssClass="'underline'" disabled="true"/>
	   </tr>
	     <@ww.textfield label="'${action.getText('specification')}'" name="'tooling.specification'" value="'${specification?if_exists}'" cssClass="'underline'" disabled="true"/>
	     <@ww.textfield label="'${action.getText('model')}'" name="'tooling.model'" value="'${model?if_exists}'" cssClass="'underline'" disabled="true"/>
	   <tr>
       <tr>
    	 <@ww.textfield label="'${action.getText('totalPrice')}'" name="'purchaseBillDetail.totalPrice'" value="'${(purchaseBillDetail.totalPrice?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'" disabled="false"/>
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
       <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validateRepairMantance()'">
       </@vsubmit>
       <input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
     </@buttonBar>
	</@ww.form>
	<script language="javascript">
	 function validateRepairMantance(){
		//验证单价是否为空,以及格式
     	if(isNotEmpty(document.forms[0],"purchaseBillDetail.totalPrice")) {
	     	if (!isDoubleNumber("purchaseBillDetail.totalPrice")){
		    	alert("${action.getText('purchaseBillDtl.totalPrice.isNotNumber')}");
		      		return false;
	      	 } else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["purchaseBillDetail.totalPrice"].value, 10000000001, 0)){  //验证范围
		     	alert("${action.getText('purchaseBillDtl.totalPrice.maxLength')}");
		     	return false;
	      	}
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
		 
		 //验证备注长度
		 if(getObjByNameRe("purchaseBillDetail.comment").value!=''){//验证字符长度
		   	if(!isValidLength(document.forms[0], "purchaseBillDetail.comment", null, 250)){
				alert("${action.getText('purchaseBill.purchaseBillDtl.comment.length')}");
				return  false;
			   }
			}
		 return true;
	 }
	</script>
</@htmlPage>