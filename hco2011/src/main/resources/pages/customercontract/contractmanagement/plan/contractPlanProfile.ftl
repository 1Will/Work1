<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('合同计划')}">
<@ww.form namespace="'/contractManagement'" name="'contractPlan'" action="'saveContractPlan'" method="'post'">
	<@ww.token name="saveContractPlanToken"/>
    <@inputTable>
    	
    	<#if contractPlan.id?exists>
    		<@ww.hidden id="contractPlan.id" name="'contractPlan.id'" value="'#{contractPlan.id}'"/>
    		<@ww.hidden id="executor.id" name="'executor.id'" value="'#{contractPlan.executor.id}'"/>
    		<@ww.hidden name="'contractManagement.id'" value="'${contractPlan.contractManagement.id?if_exists}'"/>
    	<#else>
    		<@ww.hidden name="'contractManagement.id'" value="'${contractManagement.id?if_exists}'"/>
    		<@ww.hidden name="'executor.id'" value="''"/>
    	</#if>
    	
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	
		<tr>
		<!--预计开始时间-->
			<@pp.datePicker 
				label="'${action.getText('开始时间')}'" 
				name="'contractPlan.startTime'" 
	   			value="'${(contractPlan.startTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
			<script language="javascript">
				<#if contractPlan.id?exists>
				<#else>
					var date = new Date();
					if(getObjByName("contractPlan.startTime").value==''){
						getObjByName("contractPlan.startTime").value = date.format("yyyy-MM-dd");
					}
				</#if>
		   </script>
		<!--预计完成时间-->
			<@pp.datePicker 
				label="'${action.getText('结束时间')}'" 
				name="'contractPlan.endTime'" 
	   			value="'${(contractPlan.endTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
			<script language="javascript">
				<#if contractPlan.id?exists>
				<#else>
					var date = new Date();
					if(getObjByName("contractPlan.endTime").value==''){
						getObjByName("contractPlan.endTime").value = date.format("yyyy-MM-dd");
					}
				</#if>
		   </script>
		   
		   
			<!--负责人-->
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('负责人')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="contractPlan.executor.name" class="underline"  value="<#if contractPlan.executor?exists>${contractPlan.executor.name?if_exists}</#if>" maxlength="140" size="20" disabled="true"/>
				<a onClick="salesman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			
		</tr>
			<!--工作内容-->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('工作内容')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="contractPlan.content" rows="4" cols="150" >${contractPlan.content?if_exists}</textarea>
	        </td>
		</tr>
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		
		<#-- 继续新建按钮   
			<#if projectInfoPlan.id?exists>
			<@redirectButton value="${action.getText('newNext')}" 
				url="${req.contextPath}/projectInfo/editProPlan.html?projectInfo.id=${projectInfoId?if_exists}"/>
			<#else>
			<@redirectButton name="newNext" value="${action.getText('newNext')}" 
				url="${req.contextPath}/projectInfo/editProPlan.html"/>
			<script language="JavaScript" type="text/JavaScript"> 
			getObjByName("newNext").disabled="true";
			</script>
			</#if>
		 -->
		<input class="button" type="button" value="${action.getText('close')}"  onclick="closeThis();">
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
	}
	
	//弹出业务员查询模态窗体
	function salesman_OpenDialog(){
	   var url =  "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
			document.forms[0].elements["executor.id"].value = result[0];
	   		document.forms[0].elements["contractPlan.executor.name"].value = result[2];		 	
		}
	}
	
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		/* 验证责任人*/
		if('' == getObjByName('contractPlan.startTime').value){
			alert("${action.getText('请选择开始时间！')}");
			getObjByName('contractPlan.startTime').focus();
     		return false;
		}
		if('' == getObjByName('contractPlan.endTime').value){
			alert("${action.getText('请选择结束时间！')}");
			getObjByName('contractPlan.endTime').focus();
     		return false;
		}
		
		if('' == getObjByName('contractPlan.executor.name').value){
			alert("${action.getText('请选择负责人！')}");
			getObjByName('contractPlan.executor.name').focus();
     		return false;
		}
		return true;
	}
	
</script>
</@htmlPage>
