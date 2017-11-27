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
<@htmlPage title="${action.getText('expenseApplyAction.edit')}">
<@ww.form name="'listForm'" action="'saveExpenseApplyAction'" method="'post'">
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="saveExpenseApplyActionToken"/>
	<#if expenseApply.id?exists>
		<@ww.hidden name="'expenseApply.id'" value="#{expenseApply.id?if_exists}"/>
	</#if>
	
	<#if expenseApply.applyPerson?exists>
		<@ww.hidden id="applyPersonid" name="'applyPerson.id'" value="#{expenseApply.applyPerson.id?if_exists}"/>
	<#else>
		<#if personnelF?exists>
			<@ww.hidden id="applyPersonid" name="'applyPerson.id'" value="#{personnelF.id?if_exists}"/>
		<#else>
			<@ww.hidden id="applyPersonid" name="'applyPerson.id'" value=""/>		
		</#if>
		
	</#if>
	
	<#if expenseApply.deparment?exists>
		<@ww.hidden id="deparmentid" name="'deparment.id'" value="#{expenseApply.deparment.id?if_exists}"/>
	<#else>
		<#if personnelF?exists>
			<@ww.hidden id="deparmentid" name="'deparment.id'" value="#{personnelF.dept.id?if_exists}"/>
		<#else>
			<@ww.hidden id="deparmentid" name="'deparment.id'" value=""/>		
		</#if>
	</#if>
	
	<@inputTable>
		<tr>
			<@textfield id="code" label="${action.getText('expenseApply.code')}" maxlength="10"  name="expenseApply.code"  value="${expenseApply.code?if_exists}"  required="false" anothername="checkcode" readonly="true"/>
			<@textfield id="expenseName" label="${action.getText('expenseApply.expenseName')}" maxlength="15"  name="expenseApply.expenseName"  value="${expenseApply.expenseName?if_exists}"  required="true" anothername="checkexpenseName"/>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('expenseApply.applyPerson.name')}:</label>
	     	</td>
	     	<td>
		     	<#if expenseApply.applyPerson?exists>
					<input type="text" id="applyPersonName" name="applyPersonName" class="underline"  value="${expenseApply.applyPerson.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<#if personnelF?exists>
						<input type="text" id="applyPersonName" name="applyPersonName" class="underline"  value="${personnelF.name?if_exists}" maxlength="140" size="20" disabled="true"/>
					<#else>
						<input type="text" id="applyPersonName" name="expenseApplyName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
					</#if>
				</#if>
				<a onClick="salesman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		
		<tr>
		
			<@select label="${action.getText('expenseApply.deparment.name')}" 
		   	   anothername="selectDepartment"
		   	   id="department"
		       name="departmentid" 
		       value="${req.getParameter('department.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allDepartment" 
		       emptyOption="true" 
		       disabled="true" 
		       required="true">
		    </@select>
		    <@select label="${action.getText('expenseApply.expenseType')}" 
		   	   anothername="selectexpenseType"
		   	   id="expenseType"
		       name="expenseType.id" 
		       value="${req.getParameter('expenseType.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allExpenseType" 
		       emptyOption="false" 
		       disabled="false" 
		       required="true">
		    </@select>
		    
			<@textfield id="applyMoney" label="${action.getText('expenseApply.applyMoney')}" maxlength="10"  name="expenseApply.applyMoney"  value="${expenseApply.applyMoney?if_exists}"  required="true" anothername="checkapplyMoney" readonly="false"/>
		</tr>
		<tr>
		<@pp.datePicker 
				label="'${action.getText('expenseApply.applyTime')}'" 
				name="'expenseApply.applyTime'" 
	   			value="'${(expenseApply.applyTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="false"
				readonly="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
		<@select label="${action.getText('expenseApply.applyState')}" 
		   	   anothername="selectapplyState"
		   	   id="applyState"
		       name="applyState.id" 
		       value="${req.getParameter('applyState.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allApplyState" 
		       emptyOption="true" 
		       disabled="false" 
		       required="false">
		    </@select>
		<@textfield id="residualMoney" label="${action.getText('expenseApply.residualMoney')}" maxlength="10"  name="expenseApply.residualMoney"  value="${expenseApply.residualMoney?if_exists}"  required="false" anothername="checkresidualMoney" disabled="true"/>
		</tr>
		<tr>
		    <@textarea name="expenseApply.description" label="${action.getText('expenseApply.description')}" anothername="content" rows="4"  maxLength="500" required="false" value="${expenseApply.description?if_exists}"/>	
	    </tr>
	    <script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("expenseApply.description").cols =width;
			</script>
		</@inputTable>
	<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/expenseApply/listExpenseApplyAction.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </@buttonBar>
</@ww.form>
<script language="JavaScript" type="text/JavaScript"> 
	//弹出业务员查询模态窗体
	function salesman_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
			jgetObjByName("#applyPersonid").val(result[0]);
			jgetObjByName("#applyPersonName").val(result[2]);
			jgetObjByName("#department").val(result[4]);
			jgetObjByName("#deparmentid").val(result[4]);
			
		}
	}
		<#-- 提交验证-->
		function storeValidation(){
			if(!textfieldCheck_checkexpenseName()){
				jgetObjByName("#expenseName").focus();
				return false;
			}
			if(jgetObjByName("#applyPersonid").val()==""){
				alert("validation.applyPersonid");
				return false;
			}
			if(isNaN(jgetObjByName("#applyMoney").val())){
				alert("validation.applyMoney");
				jgetObjByName("#applyMoney").focus();
				return false;
			}
			if(isNaN(jgetObjByName("#residualMoney").val())){
				alert("validation.residualMoney");
				jgetObjByName("#residualMoney").focus();
				return false;
			}
			
			return true;
		}
	jgetObjByName(function(){
		<#if expenseApply.deparment?exists>
			jgetObjByName("#department").val("${expenseApply.deparment.id?if_exists}");
		<#else>
			<#if personnelF?exists>
				jgetObjByName("#department").val("#{personnelF.dept.id?if_exists}");
			</#if>
		</#if>
		
		<#if expenseApply.expenseType?exists>
			jgetObjByName("#expenseType").val("${expenseApply.expenseType.id?if_exists}");
		</#if>
		<#if expenseApply.applyState?exists>
			jgetObjByName("#applyState").val("${expenseApply.applyState.id?if_exists}");
		</#if>
		
	});
</script>

</@htmlPage>
