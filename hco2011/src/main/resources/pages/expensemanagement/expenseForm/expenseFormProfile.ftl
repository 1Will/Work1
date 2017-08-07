<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('expenseForm.edit')}">
<@ww.form name="'listForm'" action="'saveExpenseFormAction'" method="'post'">
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="saveExpenseFormActionToken"/>
	<#if expenseForm.id?exists>
		<@ww.hidden name="'expenseForm.id'" value="#{expenseForm.id?if_exists}"/>
	</#if>
	
	<#if expenseForm.projectInfo?exists>
		<@ww.hidden id="projectInfo.id" name="'projectInfo.id'" value="#{expenseForm.projectInfo.id?if_exists}"/>
	<#else>
		<@ww.hidden id="projectInfo.id" name="'projectInfo.id'" value=""/>		
	</#if>
	
	<#if expenseForm.applyPeople?exists>
		<@ww.hidden id="applyPeople.id" name="'applyPeople.id'" value="#{expenseForm.applyPeople.id?if_exists}"/>
	<#else>
		<@ww.hidden id="applyPeople.id" name="'applyPeople.id'" value=""/>
	</#if>
	
	<#if expenseForm.contractManagement?exists>
		<@ww.hidden id="contractManagement.id" name="'contractManagement.id'" value="#{expenseForm.contractManagement.id?if_exists}"/>
	<#else>
		<@ww.hidden id="contractManagement.id" name="'contractManagement.id'" value=""/>
	</#if>
	
	<@inputTable>
		<tr>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('expenseForm.code')}:</label>
	     	</td>
	     	<td>
				<#if expenseForm.code?exists>
					<input type="text" id="expenseForm.code" name="expenseForm.code" class="underline"  value="${expenseForm.code?if_exists}" maxlength="140" size="20"/>
				<#else>
					<input type="text" id="expenseForm.code" name="expenseForm.code" class="underline"  value="" maxlength="140" size="20"/>
				</#if>
			</td>
			
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('expenseForm.projectInfo.name')}:</label>
	     	</td>
			<td>
				<#if expenseForm.projectInfo?exists>
					<input type="text" id="projectInfo.name" name="projectInfo.name" class="underline"  value="${expenseForm.projectInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" id="projectInfo.name"  name="projectInfo.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
					<a onClick="projectName_OpenDialog();">
							<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
					</a>
			
			<td align="right" valign="top">
	       		<label class="label">${action.getText('expenseForm.contractManagement.name')}:</label>
	     	</td>
	     	<td>
			<#if expenseForm.contractManagement?exists>
		   		<input type="text" name="contractManagement.name" class="underline"  value="${expenseForm.contractManagement.contractName?if_exists}" maxlength="140" size="20" disabled="true"/>
			<#else>
				<input type="text" name="contractManagement.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
			</#if>
				<a onClick="contractManagement_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>	
		<tr>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('expenseForm.applyPeople.name')}:</label>
	     	</td>
	     	<td>
	     	<#if expenseForm.applyPeople?exists>
	     		<input type="text" name="applyPeople.name" class="underline"  value="${expenseForm.applyPeople.name?if_exists}" maxlength="140" size="20" disabled="true"/>
	     	<#else>
	     		<input type="text" name="applyPeople.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
	     	</#if>
				<a onClick="applyPeople_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			
			
			<@textfield id="expenseForm.money" label="${action.getText('expenseForm.money')}" maxlength="15"  name="expenseForm.money"  value="${expenseForm.money?if_exists}"  required="false" />
			
			<@pp.datePicker 
				label="'${action.getText('expenseForm.applyDate')}'" 
				name="'expenseForm.applyDate'" 
	   			value="'${(expenseForm.applyDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				readonly="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
			</tr>
		<tr>
			<@textarea name="expenseForm.remark" rows="4" cols="150" label="${action.getText('expenseForm.remark')}" anothername="remark" maxLength="500" required="false" value="${expenseForm.remark?if_exists}"/>				
		</tr>
	</@inputTable>
	<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/expenseForm/listExpenseFormAction.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </@buttonBar>
</@ww.form>

<script language="JavaScript" type="text/JavaScript"> 
	 //项目名称查询模态窗体(添加)
	function projectName_OpenDialog(){
   		var url = "${req.contextPath}/projectInfo/listProjectInfo.html?backVisitCheckBox=backVisitCheckBox";
   		popupModalDialog(url, 800, 600, setProject);
	 }
	 //项目名称-获得模态窗体返回值
	function setProject(result) {
		if (null != result) {
			getObjByName("projectInfo.id").value=(result[0]);
			getObjByName("projectInfo.name").value=(result[1]);
		}
	}
	
	//合同管理模态窗体
	function contractManagement_OpenDialog(){
	   var url = "${req.contextPath}/contractManagement/listContractManagementWindowAction.html";
	   popupModalDialog(url, 800, 600, setContractManagement);
	 }
	 //获得模态窗体返回值
	function setContractManagement(result) {
		if (null != result) {
			getObjByName('contractManagement.id').value = result[0];
	   		getObjByName('contractManagement.name').value=result[1];
		}
	}

	//弹出业务员查询模态窗体
	function applyPeople_OpenDialog(){
	   var url =  "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, setApplyPeople);
	 }
	 //获得模态窗体返回值
	function setApplyPeople(result) {
		if (null != result) {
			getObjByName('applyPeople.id').value = result[0];
	   		getObjByName('applyPeople.name').value = result[2];		 	
		}
	}

	<#-- 提交验证-->
	function storeValidation(){
		
		if(getObjByName("expenseForm.code").value==""){
			alert("${action.getText('请输入报销单编号!')}");
			getObjByName("expenseForm.code").focus();
			return false;
		}
		
		if(getObjByName("projectInfo.name").value==""){
			alert("${action.getText('请选择项目!')}");
			getObjByName("projectInfo.name").focus();
			return false;
		}
		
		if(getObjByName("expenseForm.money").value==""){
			alert("${action.getText('请输入金额！')}");
			getObjByName("expenseForm.money").focus();
			return false;
		}
		
		if(isNaN(getObjByName("expenseForm.money").value)){
			alert("${action.getText('金额不是数字，请重新输入')}");
			getObjByName("expenseForm.money").focus();
			return false;
		}
		
		if(getObjByName("expenseForm.applyDate").value==""){
			alert("${action.getText('请选择申请日期！')}");
			return false;
		}
		return true;
	}
</script>

</@htmlPage>
