<#--
	Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: contactArchivesProfile.ftl 2009-12-08 14:50:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('receivedPayments.profile')}">
<@ww.form namespace="'/contractManagement'" name="'contractManagement'" action="'saveReceivedPayments'" method="'post'">
<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="saveReceivedPaymentsToken"/>
    <@inputTable>
    	<@ww.hidden name="'customer.id'" value="''"/>
    	<@ww.hidden name="'customerType.id'" value="''"/>
    	<@ww.hidden name="'payee.id'" value="'${req.getParameter('payee.id')?if_exists}'"/>
    	<@ww.hidden name="'contactArchives.id'" value="'${req.getParameter('contactArchives.id')?if_exists}'"/>
    	<@ww.hidden name="'contractManagement.id'" value="'${req.getParameter('contractManagement.id')?if_exists}'"/>
    	<#if receivedPayments.id?exists>
    		<@ww.hidden name="'receivedPayments.id'" value="#{receivedPayments.id?if_exists}"/>
	 	</#if>
	 	<tr>
	 		<#--收款人弹出框-->
	 		<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('receivedPayments.payee')}:</label>
	     	</td>
	     	<td>
	     		<#if receivedPayments.payee?exists>
		   		<input type="text" name="receivedPayments.payee" class="underline"  value="${receivedPayments.payee.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="receivedPayments.payee" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="payee_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
				<#--相关合同弹出框-->
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('receivedPayments.contractManagement')}:</label>
	     	</td>
	     	<td>
	     		<#if receivedPayments.contractManagement?exists>
		   		<input type="text" name="receivedPayments.contractManagement" class="underline"  value="${receivedPayments.contractManagement.contractName?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="receivedPayments.contractManagement" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="contractManagement_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
				<#--相关客户弹出框弹出框-->
				<#assign custName = ''/>
		 	<#if receivedPayments.customerInfo?exists>
		   		<#assign custName = "${receivedPayments.customerInfo}" />
		 	</#if>
	     	<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('receivedPayments.customerInfo')}:</label>
	     	</td>
	     	<td>
	     		<#if receivedPayments.customerInfo?exists>
		   			<input type="text" name="receivedPayments.customerInfo" class="underline"  value="${receivedPayments.customerInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" name="receivedPayments.customerInfo" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="customer_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		<tr>
			<#--相关联系人弹出框-->
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('receivedPayments.contactArchives')}:</label>
	     	</td>
	     	<td>
	     		<#if receivedPayments.contactArchives?exists>
		   		<input type="text" name="receivedPayments.contactArchives" class="underline"  value="${receivedPayments.contactArchives.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="receivedPayments.contactArchives" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="contactArchives_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<@pp.datePicker 
				label="'${action.getText('receivedPayments.paytime')}'" 
				name="'receivedPayments.paytime'" 
	   			value="'${(receivedPayments.paytime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				dateFormat="'%Y-%m-%d'"
				required="true"
				maxlength="10"/>
			<@ww.select label="'${action.getText('receivedPayments.payment')}'" 
				name="'payment.id'" 
				value="${req.getParameter('payment.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allPayments"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('receivedPayments.batch')}'" name="'receivedPayments.batch'" value="'${receivedPayments.batch?if_exists}'" cssClass="'underline'" required="true"/>
			<@ww.textfield label="'${action.getText('receivedPayments.sum')}'" name="'receivedPayments.sum'" value="'${receivedPayments.sum?if_exists}'" cssClass="'underline'" required="true"/>
			<@ww.select label="'${action.getText('receivedPayments.currency')}'" 
				name="'currency.id'" 
				value="${req.getParameter('currency.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allCurrencys"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
		</tr>
		<tr>
			<td align="right"><label for="" class="label">${action.getText('receivedPayments.isOrNot')}:</label></td>
	        <td align="left">
	        	<input type="radio" id="isOrNot0" name="isOrNot" value="0" />是
	        	<input type="radio" id="isOrNot1" name="isOrNot" value="1" />否
			</td>
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('receivedPayments.remark')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="receivedPayments.remark" rows="3" cols="120" >${receivedPayments.remark?if_exists}</textarea>
			</td>
		</tr>
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/contractManagement/listReceivedPayments.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
		<#if receivedPayments.payment?exists>
			getObjByName('payment.id').value='${receivedPayments.payment.id?if_exists}';
		</#if>
		<#if receivedPayments.currency?exists>
			getObjByName('currency.id').value='${receivedPayments.currency.id?if_exists}';
		</#if>
		<#if receivedPayments.isOrNot?exists>
			<#if receivedPayments.isOrNot=="0">
				getObjByName('isOrNot0').checked=true;
			<#else>
				getObjByName('isOrNot1').checked=true;
			</#if>
		</#if>
	}
	
	//弹出客户档案查询模态窗体
	function customer_OpenDialog(){
	   var url = "${req.contextPath}/customerRelationship/listCustInfo.html";
	   popupModalDialog(url, 800, 600, creatorSelector1Handler);
	 }
	 //获得模态窗体返回值
	function creatorSelector1Handler(result) {
		if (null != result) {
		 	document.forms[0].elements["customer.id"].value = result[0];	
		 	document.forms[0].elements["receivedPayments.customerInfo"].value = result[1];
		 	document.forms[0].elements["customerType.id"].value = result[2];
		}
	}
	
	//弹出收款人查询模态窗体
	function payee_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorPrincipalHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorPrincipalHandler(result) {
		if (null != result) {
			document.forms[0].elements["payee.id"].value = result[0];
	   		getObjByName('receivedPayments.payee').value=result[2];
		}
	}
	
	//联系人查询模态窗体
	function contactArchives_OpenDialog(){
	   var url = "${req.contextPath}/com/listContactArchivesWindow.html";
	   popupModalDialog(url, 800, 600, creatorSelector2Handler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelector2Handler(result) {
		if (null != result) {
			document.forms[0].elements["contactArchives.id"].value = result[0];
	   		getObjByName('receivedPayments.contactArchives').value=result[2];
		}
	}
	
		//合同管理模态窗体
	function contractManagement_OpenDialog(){
	   var url = "${req.contextPath}/contractManagement/listContractManagementWindowAction.html";
	   popupModalDialog(url, 800, 600, creatorSelector3Handler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelector3Handler(result) {
		if (null != result) {
			document.forms[0].elements["contractManagement.id"].value = result[0];
	   		getObjByName('receivedPayments.contractManagement').value=result[1];
	   		getObjByName('receivedPayments.customerInfo').value=result[2];
	   		getObjByName('receivedPayments.contactArchives').value=result[3];
	   		document.forms[0].elements["customer.id"].value = result[4];
	   		document.forms[0].elements["contactArchives.id"].value=result[5];
		}
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		if(getObjByName('receivedPayments.payee').value==''){
			alert("${action.getText('receivedPayments.payee.requiredstring')}");
			return false;
		}
		if(getObjByName('receivedPayments.contractManagement').value==''){
			alert("${action.getText('receivedPayments.contractManagement.requiredstring')}");
			return false;
		}
		if(getObjByName('receivedPayments.customerInfo').value==''){
			alert("${action.getText('receivedPayments.customerInfo.requiredstring')}");
			return false;
		}
		if(getObjByName('receivedPayments.contactArchives').value==''){
			alert("${action.getText('receivedPayments.contactArchives.requiredstring')}");
			return false;
		}
		
		if(getObjByName('receivedPayments.paytime').value==''){
			alert("${action.getText('receivedPayments.paytime.requiredstring')}");
			getObjByName('receivedPayments.paytime').focus();
			return false;
		}
		
		if(getObjByName('receivedPayments.paytime').value !=''){
		 	if(!isDate('receivedPayments.paytime')){
				alert("${action.getText('receivedPayments.paytime.dateFormate.error')}");
				getObjByName('receivedPayments.paytime').value="";
	      	    getObjByName('receivedPayments.paytime').focus();
				return false;
			}
		}
		if(getObjByName('payment.id').value==''){
			alert("${action.getText('receivedPayments.payment.requiredstring')}");
			getObjByName('payment.id').focus();
			return false;
		}
	
		if(!isNumber("receivedPayments.batch")){
			alert("${action.getText('receivedPayments.batch.must.be.long')}");
			getObjByName('receivedPayments.batch').value="";
			getObjByName('receivedPayments.batch').focus();
			return false;
		}
		if(getObjByName('receivedPayments.sum').value==''){
			alert("${action.getText('receivedPayments.sum.requiredstring')}");
			getObjByName('receivedPayments.sum').focus();
			return false;
		}
		
		 //验证费用为double类型
		if(getObjByName('receivedPayments.sum').value!=''){
	     	if(!isDoubleNumber("receivedPayments.sum")){
				alert("${action.getText('number.must.be.double')}");
				getObjByName('receivedPayments.sum').value="";
				getObjByName('receivedPayments.sum').focus();
				return false;
			}
	     }
		return true;
	}
</script>
</@htmlPage>
