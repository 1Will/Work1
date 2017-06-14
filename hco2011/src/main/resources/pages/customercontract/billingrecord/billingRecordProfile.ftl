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

<@htmlPage title="${action.getText('billingRecord.profile')}">
<@ww.form namespace="'/contractManagement'" name="'contractManagement'" action="'saveBillingRecord'" method="'post'">
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="saveBillingRecordToken"/>
    <@inputTable>
    	<@ww.hidden name="'customer.id'" value="''"/>
    	<@ww.hidden name="'customerType.id'" value="''"/>
    	<@ww.hidden name="'payee.id'" value="'${req.getParameter('payee.id')?if_exists}'"/>
    	<@ww.hidden name="'contractManagement.id'" value="'${req.getParameter('contractManagement.id')?if_exists}'"/>
    	<@ww.hidden name="'contactArchives.id'" value="'${req.getParameter('contactArchives.id')?if_exists}'"/>
    	<#if billingRecord.id?exists>
    		<@ww.hidden name="'billingRecord.id'" value="#{billingRecord.id?if_exists}"/>
	 	</#if>
	 	<tr>
			<#--相关合同弹出框-->
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('billingRecord.contractManagement')}:</label>
	     	</td>
	     	<td>
	     		<#if billingRecord.contractManagement?exists>
		   		<input type="text" name="billingRecord.contractManagement" class="underline"  value="${billingRecord.contractManagement.contractName?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="billingRecord.contractManagement" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="contractManagement_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
				<#--相关客户-->
				<#assign custName = ''/>
		 	<#if billingRecord.customerInfo?exists>
		   		<#assign custName = "${billingRecord.customerInfo}" />
		 	</#if>
	     	<td align="right" valign="top">
	       		<label class="label">${action.getText('billingRecord.customerInfo')}:</label>
	     	</td>
	     	<td>
	     		<#if billingRecord.customerInfo?exists>
		   			<input type="text" name="billingRecord.customerInfo" class="underline"  value="${billingRecord.customerInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" name="billingRecord.customerInfo" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
			</td>
			
			<#--相关联系人弹出框-->
			<td align="right" valign="top">
	       		<label class="label">${action.getText('billingRecord.contactArchives')}:</label>
	     	</td>
	     	<td>
	     		<#if billingRecord.contactArchives?exists>
		   		<input type="text" name="billingRecord.contactArchives" class="underline"  value="${billingRecord.contactArchives.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="billingRecord.contactArchives" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="contactArchives_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		<tr>
            <@ww.textfield label="'${action.getText('billingRecord.code')}'" name="'billingRecord.code'" value="'${billingRecord.code?if_exists}'" cssClass="'underline'" required="true"/>
			<@ww.textfield label="'${action.getText('billingRecord.invoiceTitle')}'" name="'billingRecord.invoiceTitle'" value="'${billingRecord.invoiceTitle?if_exists}'" cssClass="'underline'" />
			<@ww.textfield label="'${action.getText('billingRecord.sum')}'" name="'billingRecord.sum'" value="'#{billingRecord.sum?if_exists}'" cssClass="'underline'" required="true"/>
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('billingRecord.currency')}'" name="'billingRecord.currency'" value="'${billingRecord.currency?if_exists}'" cssClass="'underline'"/>
			<@pp.datePicker 
				label="'${action.getText('billingRecord.billingTime')}'" 
				name="'billingRecord.billingTime'" 
	   			value="'${(billingRecord.billingTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				dateFormat="'%Y-%m-%d'"
				required="true"
				maxlength="10"/>
			<script language="javascript">
					var date = new Date();
					if(getObjByName("billingRecord.billingTime").value==''){
						getObjByName("billingRecord.billingTime").value = date.format("yyyy-MM-dd");
					}
			</script>
			<#--开票人弹出框-->
	 		<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('billingRecord.payee')}:</label>
	     	</td>
	     	<td>
	     		<#if billingRecord.payee?exists>
		   		<input type="text" name="billingRecord.payee" class="underline"  value="${billingRecord.payee.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="billingRecord.payee" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="payee_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		<tr>	
			<td align="right" valign="top">
	       		<label class="label">${action.getText('billingRecord.content')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="billingRecord.content" rows="4" cols="150" >${billingRecord.content?if_exists}</textarea>
			</td>
		</tr>
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		
		<#-- 继续新建按钮   -->
		<#if billingRecord.id?exists>
		<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/contractManagement/editBillingRecord.html"/>
		<#else>
		<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/contractManagement/editBillingRecord.html"/>
		<script language="JavaScript" type="text/JavaScript"> 
		getObjByName("newNext").disabled="true";
		</script>
		</#if>
		
		<#if popWindowFlag?exists&&popWindowFlag==popWindowFlag>
		<@vbutton class="button" value="${action.getText('close')}" onclick="closeThis()"/>
		<#else>
		<@redirectButton class="button" value="${action.getText('back')}" url="${req.contextPath}/contractManagement/listBillingRecord.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
   		</#if>
		
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	
	//弹出客户档案查询模态窗体
	function customer_OpenDialog(){
	   var url = "${req.contextPath}/customerRelationship/listCustInfo.html";
	   popupModalDialog(url, 800, 600, creatorSelector1Handler);
	 }
	 //获得模态窗体返回值
	function creatorSelector1Handler(result) {
		if (null != result) {
		 	document.forms[0].elements["customer.id"].value = result[0];	
		 	document.forms[0].elements["billingRecord.customerInfo"].value = result[1];
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
	   		getObjByName('billingRecord.payee').value=result[2];
		}
	}
	
	//合同管理模态窗体
	function contractManagement_OpenDialog(){
	   var url = "${req.contextPath}/contractManagement/listContractManagementWindowAction.html";
	   popupModalDialog(url, 800, 600, creatorSelector2Handler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelector2Handler(result) {
		if (null != result) {
			document.forms[0].elements["contractManagement.id"].value = result[0];
	   		getObjByName('billingRecord.contractManagement').value=result[1];
	   		getObjByName('billingRecord.customerInfo').value=result[2];
	   		document.forms[0].elements["customer.id"].value = result[4];
	   		document.forms[0].elements["contactArchives.id"].value=result[5];
	   		getObjByName('billingRecord.contactArchives').value=result[3];
	   		getObjByName('billingRecord.invoiceTitle').value=result[2]
		}
	}
	
		//联系人查询模态窗体
	function contactArchives_OpenDialog(){
	   var url = "${req.contextPath}/com/listContactArchivesWindow.html";
	   popupModalDialog(url, 800, 600, creatorSelectorContactArchivesHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorContactArchivesHandler(result) {
		if (null != result) {
			document.forms[0].elements["contactArchives.id"].value = result[0];
	   		getObjByName('billingRecord.contactArchives').value=result[2];
		}
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		if(getObjByName('billingRecord.contractManagement').value==''){
			alert("${action.getText('billingRecord.contractManagement.requiredstring')}");
			return false;
		}
		if(getObjByName('billingRecord.code').value==''){
			alert("${action.getText('billingRecord.code.requiredstring')}");
			getObjByName('billingRecord.code').focus();
			return false;
		}
		if(getObjByName('billingRecord.sum').value==''){
			alert("${action.getText('billingRecord.sum.requiredstring')}");
			getObjByName('billingRecord.sum').focus();
			return false;
		}
		 //验证费用为double类型
		if(getObjByName('billingRecord.sum').value!=''){
	     	if(!isDoubleNumber("billingRecord.sum")){
				alert("${action.getText('number.must.be.double')}");
				getObjByName('billingRecord.sum').value="";
				getObjByName('billingRecord.sum').focus();
				return false;
			}
	     }
	     
	     if(getObjByName('billingRecord.billingTime').value==''){
			alert("${action.getText('billingRecord.billingTime.requiredstring')}");
			getObjByName('billingRecord.billingTime').focus();
			return false;
		}
		if(getObjByName('billingRecord.billingTime').value !=''){
		 	if(!isDate('billingRecord.billingTime')){
				alert("${action.getText('billingRecord.billingTime.dateFormate.error')}");
	      	    getObjByName('billingRecord.billingTime').focus();
				return false;
			}
		}
		
		if(getObjByName('billingRecord.payee').value==''){
			alert("${action.getText('billingRecord.payee.requiredstring')}");
			return false;
		}
		return true;
	}
</script>
</@htmlPage>
