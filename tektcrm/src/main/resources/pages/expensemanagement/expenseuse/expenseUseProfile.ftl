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
<@htmlPage title="${action.getText('expenseUseAction.edit')}">
<@ww.form name="'listForm'" action="'saveExpenseUseAction'" method="'post'">
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="saveExpenseUseActionToken"/>
	<#if expenseUse.id?exists>
		<@ww.hidden name="'expenseUse.id'" value="#{expenseUse.id?if_exists}"/>
	</#if>
	
	<#if expenseUse.expenseApply?exists>
		<@ww.hidden id="expenseApplyid" name="'expenseApply.id'" value="#{expenseUse.expenseApply.id?if_exists}"/>
	<#else>
			<@ww.hidden id="expenseApplyid" name="'expenseApply.id'" value=""/>		
	</#if>
	<#if expenseUse.customerInfo?exists>
		<@ww.hidden id="customerInfoid" name="'customerInfo.id'" value="#{expenseUse.customerInfo.id?if_exists}"/>
	<#else>
		<@ww.hidden id="customerInfoid" name="'customerInfo.id'" value=""/>
	</#if>
	<#if expenseUse.linkman?exists>
		<@ww.hidden id="linkmanid" name="'linkman.id'" value="#{expenseUse.linkman.id?if_exists}"/>
	<#else>
		<@ww.hidden id="linkmanid" name="'linkman.id'" value=""/>
	</#if>
	
	<#if expenseUse.contractManagement?exists>
		<@ww.hidden id="contractManagementid" name="'contractManagement.id'" value="#{expenseUse.contractManagement.id?if_exists}"/>
	<#else>
		<@ww.hidden id="contractManagementid" name="'contractManagement.id'" value=""/>
	</#if>
	<@inputTable>
		
		<#if expenseUse.expenseApply?exists>
			<tr>
				<td align="right" valign="top">
		       		<span class="required">*</span>
		       		<label class="label">${action.getText('expenseUse.expenseApply.code')}:</label>
		     	</td>
		     	<td>
					<#if expenseUse.expenseApply?exists>
						<input type="text" id="expenseApplyCode" name="expenseApplyCode" class="underline"  value="${expenseUse.expenseApply.code?if_exists}" maxlength="140" size="20" disabled="true"/>
					<#else>
						<input type="text" id="expenseApplyCode" name="expenseApplyCode" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
					</#if>
					<a onClick="expenseApply_OpenDialog();">
						<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
					</a>
				</td>
				<@textfield id="expenseName" label="${action.getText('expenseUse.expenseApply.expenseName')}" maxlength="15"  name="expenseApply.expenseName"  value="${expenseUse.expenseApply.expenseName?if_exists}"  required="false" disabled="true"/>
				<#if expenseUse.expenseApply.applyPerson?exists>
					<@textfield id="applyPerson" label="${action.getText('expenseUse.expenseApply.applyPerson.name')}" maxlength="15"  name="expenseApply.applyPerson"  value="${expenseUse.expenseApply.applyPerson.name?if_exists}"  required="false" disabled="true"/>
				<#else>
					<@textfield id="applyPerson" label="${action.getText('expenseUse.expenseApply.applyPerson.name')}" maxlength="15"  name="expenseApply.applyPerson"  value=""  required="false" disabled="true"/>
				</#if>
			</tr>
			<tr>
				<#if expenseUse.expenseApply.deparment?exists>
					<@select label="${action.getText('expenseUse.expenseApply.deparment.name')}" 
				   	   anothername="selectDepartment"
				   	   id="department"
				       name="departmentid" 
				       value="${req.getParameter('department.id')?if_exists}"
				       listKey="id" 
				       listValue="name"
				       list="allDepartment" 
				       emptyOption="true" 
				       disabled="true" 
				       required="false">
				    </@select>
				     <script>
				    	getObjByName("#department").value='${expenseUse.expenseApply.deparment.id?if_exists}');
				    </script>
				<#else>
					<@select label="${action.getText('expenseUse.expenseApply.deparment.name')}" 
				   	   anothername="selectDepartment"
				   	   id="department"
				       name="departmentid" 
				       value="${req.getParameter('department.id')?if_exists}"
				       listKey="id" 
				       listValue="name"
				       list="allDepartment" 
				       emptyOption="true" 
				       disabled="true" 
				       required="false">
				    </@select>
				</#if>
				<#if expenseUse.expenseApply.expenseType?exists>
					<@select label="${action.getText('expenseUse.expenseApply.expenseType')}" 
				   	   anothername="selectexpenseType"
				   	   id="expenseType"
				       name="expenseType.id" 
				       value="${req.getParameter('expenseType.id')?if_exists}"
				       listKey="id" 
				       listValue="name"
				       list="allExpenseType" 
				       emptyOption="true" 
				       disabled="true" 
				       required="false">
				    </@select>
				     <script>
				    	getObjByName("#expenseType").value='${expenseUse.expenseApply.expenseType.id?if_exists}');
				    </script>
				<#else>
					<@select label="${action.getText('expenseUse.expenseApply.expenseType')}" 
				   	   anothername="selectexpenseType"
				   	   id="expenseType"
				       name="expenseType.id" 
				       value="${req.getParameter('expenseType.id')?if_exists}"
				       listKey="id" 
				       listValue="name"
				       list="allExpenseType" 
				       emptyOption="true" 
				       disabled="true" 
				       required="false">
				    </@select>
				</#if>
				<@textfield id="usePerson" label="${action.getText('expenseUse.usePerson')}" maxlength="10"  name="expenseUse.usePerson"  value="${expenseUse.usePerson?if_exists}"  required="false" anothername="checkusePerson" readonly="false"/>
			
				</tr>
			<#else>
					<tr>
						<td align="right" valign="top">
				       		<span class="required">*</span>
				       		<label class="label">${action.getText('expenseUse.expenseApply.code')}:</label>
				     	</td>
			     		<td>
							<#if expenseUse.expenseApply?exists>
								<input type="text" id="expenseApplyCode" name="expenseApplyCode" class="underline"  value="${expenseUse.expenseApply.code?if_exists}" maxlength="140" size="20" disabled="true"/>
							<#else>
								<input type="text" id="expenseApplyCode" name="expenseApplyCode" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
							</#if>
							<a onClick="expenseApply_OpenDialog();">
								<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
							</a>
						</td>
							<@textfield id="expenseName" label="${action.getText('expenseUse.expenseApply.expenseName')}" maxlength="15"  name="expenseApply.expenseName"  value=""  required="false" disabled="true"/>
							<@textfield id="applyPerson" label="${action.getText('expenseUse.expenseApply.applyPerson.name')}" maxlength="15"  name="expenseApply.applyPerson"  value=""  required="false" disabled="true"/>
					</tr>
					<tr>
						<@select label="${action.getText('expenseUse.expenseApply.deparment.name')}" 
					   	   anothername="selectDepartment"
					   	   id="department"
					       name="departmentid" 
					       value="${req.getParameter('department.id')?if_exists}"
					       listKey="id" 
					       listValue="name"
					       list="allDepartment" 
					       emptyOption="true" 
					       disabled="true" 
					       required="false">
					    </@select>
						<@select label="${action.getText('expenseUse.expenseApply.expenseType')}" 
					   	   anothername="selectexpenseType"
					   	   id="expenseType"
					       name="expenseType.id" 
					       value="${req.getParameter('expenseType.id')?if_exists}"
					       listKey="id" 
					       listValue="name"
					       list="allExpenseType" 
					       emptyOption="true" 
					       disabled="true" 
					       required="false">
					    </@select>
					    <@textfield id="usePerson" label="${action.getText('expenseUse.usePerson')}" maxlength="10"  name="expenseUse.usePerson"  value="${expenseUse.usePerson?if_exists}"  required="false" anothername="checkusePerson" readonly="false"/>
			
					</tr>
		</#if>
		<tr>
			<@textfield id="usedMoney" label="${action.getText('expenseUse.usedMoney')}" maxlength="10"  name="expenseUse.usedMoney"  value="#{expenseUse.usedMoney?if_exists}"  required="true" anothername="checkusedMoney" readonly="false"/>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('expenseUse.customerInfo')}:</label>
	     	</td>
	     	<td>
				<#if expenseUse.customerInfo?exists>
					<input type="text" id="customerInfoName" name="customerInfoName" class="underline"  value="${expenseUse.customerInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" id="customerInfoName" name="customerInfoName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="customer_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('expenseUse.linkman')}:</label>
	     	</td>
	     	<td>
	     		<#if expenseUse.linkman?exists>
		   		<input type="text" id="linkmanName" name="linkmanName" class="underline"  value="${expenseUse.linkman.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" id="linkmanName"  name="linkmanName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="linkman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		<tr>
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('expenseUse.contractManagement')}:</label>
	     	</td>
	     	<td>
	     		<#if expenseUse.contractManagement?exists>
		   		<input type="text" id="contractManagementName" name="contractManagementName" class="underline"  value="${expenseUse.contractManagement.contractName?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" id="contractManagementName" name="contractManagementName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="contractManagement_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		<tr>
		    <@textarea name="expenseUse.remark" label="${action.getText('expenseUse.remark')}" anothername="remark" rows="4" cols="150" maxLength="500" required="false" value="${expenseUse.remark?if_exists}"/>	
	    </tr>
		</@inputTable>
	<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/expenseUse/listExpenseUseAction.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </@buttonBar>
</@ww.form>
<script language="JavaScript" type="text/JavaScript"> 
//弹出费用申请查询模态窗体
	function expenseApply_OpenDialog(){
	   var url = "${req.contextPath}/expenseApply/listExpenseApplyWindowAction.html";
	   popupModalDialog(url, 800, 600, creatorSelectorExpenseApplyHandler);
	 }
	 //获得模态窗体返回值
	function creatorSelectorExpenseApplyHandler(result) {
		if (null != result) {
			getObjByName("expenseApplyid").value=result[0];
			getObjByName("expenseApplyCode").value=result[1];
			getObjByName('expenseName').value=result[2];
	   		getObjByName('applyPerson').value=result[3];
	   		getObjByName('department').value=result[4];
	   		getObjByName('expenseType').value=result[5];
		}
	}


	//弹出客户档案查询模态窗体
	function customer_OpenDialog(){
	   var url = "${req.contextPath}/customerRelationship/listCustInfo.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorSelector1Handler);
	 }
	 //获得模态窗体返回值
	function creatorSelector1Handler(result) {
		if (null != result) {
			getObjByName("customerInfoid").value=result[0];
			getObjByName("customerInfoName").value=result[1];
		}
	}
	//联系人查询模态窗体
	function linkman_OpenDialog(){
		if(getObjByName('customerInfo.id').value !=''){
			var  url = "${req.contextPath}/customerRelationship/listContactArchives.html?readOnly=${req.getParameter('readOnly')?if_exists}&backVisitFlag=backVisit&customer.id="+getObjByName('customerInfo.id').value;
			popupModalDialog(url, 800, 600, creatorSelector2Handler);
		}else{
			alert('请先选择客户');
		}
	 }
	 //获得模态窗体返回值
	function creatorSelector2Handler(result) {
		if (null != result) {
			getObjByName("#linkmanid").value=result[0]);
			getObjByName("#linkmanName").value=result[1]);
		}
	}
		//合同管理模态窗体
	function contractManagement_OpenDialog(){
	   var url = "${req.contextPath}/contractManagement/listContractManagementWindowAction.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorSelector3Handler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelector3Handler(result) {
		if (null != result) {
			getObjByName('contractManagementid').value=result[0];
	   		getObjByName('contractManagementName').value=result[1];
		}
	}
		<#-- 提交验证-->
		function storeValidation(){
			if(getObjByName("expenseApplyid").value==""){
				alert("${action.getText('validation.expenseApplyid')}");
				return false;
			}
			
			if(!textfieldCheck_checkusedMoney()){
				getObjByName("#usedMoney").focus();
				return false;
			}
			if(isNaN(getObjByName("usedMoney").value)){
				alert("${action.getText('validation.usedMoney')}");
				getObjByName("usedMoney").focus();
				return false;
			}
			if(getObjByName("customerInfoid").value==""){
				alert("${action.getText('validation.customerInfoid')}");
				return false;
			}
			if(getObjByName("linkmanid").value==""){
				alert("${action.getText('validation.linkmanid')}");
				return false;
			}
			if(getObjByName("contractManagementid").value==""){
				alert("${action.getText('validation.contractManagementid')}");
				return false;
			}
			return true;
		}
	getObjByName(function(){
	});
</script>

</@htmlPage>
