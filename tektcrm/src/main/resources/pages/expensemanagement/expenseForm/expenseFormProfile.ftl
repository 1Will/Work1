<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('expenseForm.edit')}">
<@ww.form name="'listForm'" action="'saveExpenseFormAction'" method="'post'">
	<@ww.token name="saveExpenseFormActionToken"/>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.hidden name="'expenseForm.isSaved'" value="''"/>
	<#if popWindowFlag?exists&&popWindowFlag=='popWindowFlag' >
    	<@ww.hidden  name="popWindowFlag"  value="${popWindowFlag}"/>
    </#if>
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
				<input type="text" id="expenseForm.code" name="expenseForm.code" class="underline"  value="${expenseForm.code?if_exists}" maxlength="140" size="20"/>
			</td>
			
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('expenseForm.projectInfo.name')}:</label>
	     	</td>
			<td>
				<#if expenseForm.projectInfo?exists>
					<input type="text" id="projectInfo.name" name="projectInfo.name
					" class="underline"  value="${expenseForm.projectInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
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
			
			
			<@textfield id="expenseForm.money" label="${action.getText('expenseForm.money')}" maxlength="15"  name="expenseForm.money"  value="#{expenseForm.money?if_exists}"  required="false" />
			
			<@pp.datePicker 
				label="'${action.getText('expenseForm.applyDate')}'" 
				name="'expenseForm.applyDate'" 
	   			value="'${(expenseForm.applyDate?string('yyyy-MM-dd hh:mm:ss'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				readonly="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('附单据张数')}:</label>
	     	</td>
			<td>
				<input type="text" name="expenseForm.formNum" class="underline"  value="${expenseForm.formNum?if_exists}" maxlength="140" size="20"/>
			</td>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('附件张数')}:</label>
	     	</td>
			<td>
				<input type="text" name="expenseForm.attachmentNum" class="underline"  value="${expenseForm.attachmentNum?if_exists}" maxlength="140" size="20"/>
			</td>
		
		<#if expenseForm.status?exists && expenseForm.status.code != '02000'>
				<@ww.select label="'${action.getText('流程类型')}'" 
					name="'flow.id'" 
					value="${req.getParameter('flow.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allFlows"
					required="true"
					emptyOption="true"
					disabled="true">
				</@ww.select>
				<#else>
				<@ww.select label="'${action.getText('流程类型')}'" 
					name="'flow.id'" 
					value="${req.getParameter('flow.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allFlows"
					required="true"
					emptyOption="true"
					disabled="false">
				</@ww.select>
			</#if>
			<script language="JavaScript" type="text/JavaScript">
			<#if expenseForm.flow?exists>
			getObjByName('flow.id').value='${expenseForm.flow.id?if_exists}';
		</#if>
			</script>
		</tr>
		<tr>
	        	<@ww.select label="'${action.getText('状态')}'" 
					name="'status.id'" 
					value="${req.getParameter('status.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allStatus"
					emptyOption="true" 
					disabled="true">
				</@ww.select>
				<script language="JavaScript" type="text/JavaScript">
			<#if expenseForm.status?exists>
			getObjByName('status.id').value='${expenseForm.status.id?if_exists}';
		</#if>
			</script>
		</tr>
		<tr>
			<@textarea name="expenseForm.remark" rows="4" cols="150" label="${action.getText('expenseForm.remark')}" anothername="remark" maxLength="500" required="false" value="${expenseForm.remark?if_exists}"/>				
		</tr>
	</@inputTable>
	<@buttonBar>
	<#if activitiFLow?exists>
    <input type="button" name="close" value="关闭" onclick="window.close()">
    <#else>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
		
			<#if expenseForm.isSaved?exists && expenseForm.isSaved=='0' >
		    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'"/>
		    <#else>
		    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'" disabled="true"/>
		    </#if>
		
			<#-- 继续新建按钮   -->
			<#if expenseForm.id?exists>
				<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/expenseForm/editExpenseFormAction.html?readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=${popWindowFlag?if_exists}"/>
			<#else>
				<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/expenseForm/editExpenseFormAction.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
					<script language="JavaScript" type="text/JavaScript"> 
					getObjByName("newNext").disabled="true";
					</script>
			</#if>
		</#if>
		
		<#if popWindowFlag?exists&&popWindowFlag=='popWindowFlag'>
			<@vbutton class="button" value="${action.getText('close')}" onclick="closeThis()"/>
		<#else>
			<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/expenseForm/listExpenseFormAction.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
   		</#if>
   		</#if>
    </@buttonBar>
</@ww.form>

<script language="JavaScript" type="text/JavaScript"> 
//初始化页面
<#if expenseForm.status?exists>
var statusCode = "${expenseForm.status.code?if_exists}";
if(statusCode != "02000"){
	if(statusCode == "02002"){
		getObjByName("save").style.display = "none";
		getObjByName("submit").value = "重新提交";
		getObjByName("submit").disabled = false;
	}else{
		getObjByName("save").disabled = "true";
		getObjByName("submit").disabled = "true";
	}
}
</#if>
	 //项目名称查询模态窗体(添加)
	function projectName_OpenDialog(){
   		var url = "${req.contextPath}/projectInfo/listProjectInfo.html?backVisitCheckBox=backVisitCheckBox&readOnly=${req.getParameter('readOnly')?if_exists}";
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
		var pjId = getObjByName('projectInfo.id').value;
		var url = "${req.contextPath}/contractManagement/listContractManagementWindowAction.html?readOnly=${req.getParameter('readOnly')?if_exists}&project.id="+pjId;
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
	   var url =  "${req.contextPath}/personnelFile/listPersonByUser.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, setApplyPeople);
	 }
	 //获得模态窗体返回值
	function setApplyPeople(result) {
		if (null != result) {
			getObjByName('applyPeople.id').value = result[0];
	   		getObjByName('applyPeople.name').value = result[2];		 	
		}
	}
	
	function savee(){
     	getObjByName('expenseForm.isSaved').value=0;
		return storeValidation();
	}
	function submitt(){
		if(getObjByName("submit").value == "重新提交"){
			getObjByName('expenseForm.isSaved').value="2";
		}else{
			getObjByName('expenseForm.isSaved').value="1";
		}
		return storeValidation();
    }
	

	<#-- 提交验证-->
	function storeValidation(){
		
		if(getObjByName("expenseForm.code").value==""){
			alert("${action.getText('请输入报销单编号!')}");
			getObjByName("expenseForm.code").focus();
			return false;
		}else{
			var codeLength = getObjByName("expenseForm.code").value.length;
			if(codeLength > 15){
				alert("${action.getText('报销单编号长度超出限制!')}");
				getObjByName("expenseForm.code").focus();
				return false;
			}
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
		
		var formNum =getObjByName("expenseForm.formNum");
		if(formNum.value!=""){
			if(isNaN(formNum.value)){
				alert("${action.getText('附单据应该为数字！')}");
				formNum.focus();
				return false;
			}
		}
		
		var attachmentNum =getObjByName("expenseForm.attachmentNum");
		if(attachmentNum.value!=""){
			if(isNaN(attachmentNum.value)){
				alert("${action.getText('附件张数应该为数字！')}");
				attachmentNum.focus();
				return false;
			}
		}
		if(getObjByName('flow.id').value==''){
			alert("请选择流程类型！");
			getObjByName('flow.id').focus();
			return false;
		}
		return true;
	}
</script>

</@htmlPage>
<#if expenseForm.id?exists>
<ul id="beautytab">
	<li>
		<a id="runPoint" onclick="activeTab(this);"  href='${req.contextPath}/activitiFlow/listRunPoint.html?bussnessId=#{expenseForm.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('审批人')}</a>
	</li>
	<li>
		<a id="CopySendPerson" onclick="activeTab(this);"  href='${req.contextPath}/activitiFlow/listCopySendPerson.html?flow.id=${expenseForm.flow.id?if_exists}&bussnessId=#{expenseForm.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('抄送人')}</a>
	</li>
	<li>
		<a id="additionalInformation" onclick="activeTab(this);"  href='${req.contextPath}/applicationDocManager/listApplicationDoc.html?expenseForm.id=#{expenseForm.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('附件资料')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/activitiFlow/listRunPoint.html?bussnessId=#{expenseForm.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="50%"/>
</#if>
