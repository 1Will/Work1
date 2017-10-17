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

<@htmlPage title="${action.getText('remit.profile')}">
<@ww.form namespace="'/com'" name="'com'" action="'saveRemit'" method="'post'">
	<@ww.token name="saveRemitToken"/>
    <@inputTable>
    	<@ww.hidden name="'customer.id'" value="''"/>
    	<@ww.hidden name="'salesman.id'" value="'${req.getParameter('salesman.id')?if_exists}'"/>
    	<@ww.hidden name="'co.id'" value="'${req.getParameter('co.id')?if_exists}'"/>
    	<@ww.hidden name="'cc.id'" value="'${req.getParameter('cc.id')?if_exists}'"/>
    	<#if remit.id?exists>
    		<@ww.hidden name="'remit.id'" value="#{remit.id?if_exists}"/>
	 	</#if>
	 	<tr>
            <@ww.textfield label="'${action.getText('remit.code')}'" name="'remit.code'" value="'${remit.code?if_exists}'" cssClass="'underline'" required="true" readonly="true"/>
			<@pp.datePicker 
				label="'${action.getText('remit.remitDate')}'" 
				name="'remit.remitDate'" 
	   			value="'${(remit.remitDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
				<#--
			<script language="javascript">
					var date = new Date();
					if(getObjByName("remit.createDate").value==''){
						getObjByName("remit.createDate").value = date.format("yyyy-MM-dd");
					}
			</script>-->
			<@ww.select label="'${action.getText('remit.type')}'" 
				name="'type.id'" 
				value="${req.getParameter('type.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allTypes"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
		</tr>
		<tr>
			<#--客户弹出框-->
			<#assign custName = ''/>
		 	<#if remit.customerInfo?exists>
		   		<#assign custName = "${remit.customerInfo}" />
		 	</#if>
	     	<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('remit.customerInfo')}:</label>
	     	</td>
	     	<td>
	     		<#if remit.customerInfo?exists>
		   			<input type="text" name="remit.customerInfo" class="underline"  value="${remit.customerInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" name="remit.customerInfo" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="customer_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<#--经办人弹出框-->
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('remit.salesman')}:</label>
	     	</td>
	     	<td>
	     		<#if remit.saleman?exists>
		   		<input type="text" name="remit.salesman" class="underline"  value="${remit.saleman.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="remit.salesman" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="salesman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			
			<#--客户订单弹出框-->
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('remit.co')}:</label>
	     	</td>
	     	<td>
	     		<#if remit.co?exists>
		   			<input type="text" name="remit.co" class="underline"  value="${remit.co.code?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" name="remit.co" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="co_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		<tr>
			<#--客户合同弹出框-->
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('remit.cc')}:</label>
	     	</td>
	     	<td>
	     		<#if remit.cc?exists>
		   		<input type="text" name="remit.cc" class="underline"  value="${remit.cc.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="remit.cc" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="cc_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<@ww.select label="'${action.getText('remit.billing')}'" 
				name="'billing.id'" 
				value="${req.getParameter('billing.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allBillings"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
        </tr>
    </@inputTable>
    <@buttonBar>
	    <#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
	    </#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/com/listRemit.html"/>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
		<#if remit.billing?exists>
			getObjByName('billing.id').value='${remit.billing.id?if_exists}';
		</#if>
		<#if remit.type?exists>
			getObjByName('type.id').value='${remit.type.id?if_exists}';
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
		 	document.forms[0].elements["remit.customerInfo"].value = result[1];
		}
	}
	
	//弹出客户合同查询模态窗体
	function cc_OpenDialog(){
	   var url = "${req.contextPath}/com/listContractCustomerWindow.html";
	   popupModalDialog(url, 800, 600, creatorPrincipalHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorPrincipalHandler(result) {
		if (null != result) {
			document.forms[0].elements["cc.id"].value = result[0];
	   		getObjByName('remit.cc').value=result[1];
		}
	}
	
	
	
	//弹出业务员查询模态窗体
	function salesman_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
			document.forms[0].elements["salesman.id"].value = result[0];
	   		getObjByName('remit.salesman').value=result[2];
		}
	}
	
	//弹出客户订单查询模态窗体
	function co_OpenDialog(){
	   var url = "${req.contextPath}/com/listCoWindow.html";
	   popupModalDialog(url, 800, 600, creatorCoHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorCoHandler(result) {
		if (null != result) {
			document.forms[0].elements["co.id"].value = result[0];
	   		getObjByName('remit.co').value=result[1];
		}
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		if(getObjByName('remit.remitDate').value ==''){
		 		alert("${action.getText('remit.remitDate.requiredstring')}");
		 		getObjByName('remit.remitDate').focus();
		      	return false;   
		}else{
		 	if(!isDate('remit.remitDate')){
				alert("${action.getText('remit.remitDate.dateFormate.error')}");
				getObjByName('remit.remitDate').value="";
	      	    getObjByName('remit.remitDate').focus();
				return false;
			} 
		}
		
		if(getObjByName('type.id').value ==''){
			alert("${action.getText('remit.type.requiredstring')}");
			getObjByName('type.id').focus();
			return false;
		}
		if(getObjByName('remit.customerInfo').value ==''){
			alert("${action.getText('remit.customerInfo.requiredstring')}");
			return false;
		}
		if(getObjByName('remit.salesman').value ==''){
			alert("${action.getText('remit.salesman.requiredstring')}");
			return false;
		}
		if(getObjByName('remit.co').value ==''){
			alert("${action.getText('remit.co.requiredstring')}");
			return false;
		}
		if(getObjByName('remit.cc').value ==''){
			alert("${action.getText('remit.cc.requiredstring')}");
			return false;
		}
		if(getObjByName('billing.id').value ==''){
			alert("${action.getText('remit.billing.requiredstring')}");
			getObjByName('billing.id').focus();
			return false;
		}
		return true;
	}
</script>
</@htmlPage>
