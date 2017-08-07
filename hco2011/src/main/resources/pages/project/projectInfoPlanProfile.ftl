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

<#include "../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('projectInfo.proPlan')}">
<@ww.form namespace="'/projectInfo'" name="'projectInfo'" action="'saveProPlan'" method="'post'">
	<@ww.token name="saveProjectInfoPlanToken"/>
	<#assign returnUrl='${req.contextPath}/projectInfo/editProPlan.html??yesUrl=yesUrl'/>
    <@inputTable>
    	<#if projectInfoId?exists>
    		<@ww.hidden name="'projectInfo.id'" value="'${projectInfoId}'"/>
    		<#assign returnUrl=returnUrl + '&projectInfo.id=${projectInfoId}'/>
    	</#if>
    	<#if contractManagementId?exists>
    		<@ww.hidden name="'contractManagement.id'" value="'${contractManagementId}'"/>
    		<#assign returnUrl=returnUrl + '&contractManagement.id=${contractManagementId}'/>
    	</#if>
    	
    	<@ww.hidden name="'editFlag'" value="'${editFlag?if_exists}'"/>
    	<@ww.hidden name="'projectInfoPlan.isSaved'" value="''"/>
    	<#if projectInfoPlan.id?exists>
    	<@ww.hidden name="'personnelFiles.id'" value="'#{projectInfoPlan.personnelFiles.id}'"/>
    	<#else>
    	<@ww.hidden name="'personnelFiles.id'" value="''"/>
    	</#if>
    	
    	<@ww.hidden name="'projectInfoPlan.assist'" value="'${projectInfoPlan.assist?if_exists}'"/>
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	<#if projectInfoPlan.id?exists>
    		<@ww.hidden name="'projectInfoPlan.id'" value="#{projectInfoPlan.id}"/>
	 	</#if>
	
    
  <tr>
		<!--任务名称-->
		<td align="right" valign="top">
	       		<!---->
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('projectInfoPlan.name')}:</label>
	     	</td>
			<td>
				<input type="text" name="projectInfoPlan.name" class="underline"  value="${projectInfoPlan.name?if_exists}" maxlength="100" size="80" />
			</td>
		<!---->
		<@ww.select label="'${action.getText('计划状态')}'" 
				name="'planState.id'" 
				value="'${req.getParameter('planState.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allPlanState"
				required="true"
				emptyOption="false" 
				>
			</@ww.select>
	</tr>
    
   <tr>
			<!--负责人-->
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('projectInfoPlan.personnelFiles')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="projectInfoPlan.personnelFiles" class="underline"  value="<#if projectInfoPlan.personnelFiles?exists>${projectInfoPlan.personnelFiles.name?if_exists}</#if>" maxlength="140" size="20" disabled="true"/>
				<a onClick="salesman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<!--参与人-->
			<td align="right" valign="top">
	       		<label class="label">${action.getText('projectInfoPlan.assist')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="projectInfoPlan.assist_" class="underline"  value="<#if projectInfoPlan.assist?exists>${projectInfoPlan.assist?if_exists}</#if>" maxlength="140" size="20" disabled="true"/>
				<a onClick="salesmans_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		<tr>
		<!--预计开始时间-->
			<@pp.datePicker 
				label="'${action.getText('projectInfoPlan.startDate')}'" 
				name="'projectInfoPlan.startDate'" 
	   			value="'${(projectInfoPlan.startDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
			<script language="javascript">
				<#if projectInfoPlan.id?exists>
				<#else>
					var date = new Date();
					if(getObjByName("projectInfoPlan.startDate").value==''){
						getObjByName("projectInfoPlan.startDate").value = date.format("yyyy-MM-dd");
					}
				</#if>
		   </script>
		<!--预计完成时间-->
			<@pp.datePicker 
				label="'${action.getText('projectInfoPlan.endDate')}'" 
				name="'projectInfoPlan.endDate'" 
	   			value="'${(projectInfoPlan.endDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
			<script language="javascript">
				<#if projectInfoPlan.id?exists>
				<#else>
					var date = new Date();
					if(getObjByName("projectInfoPlan.endDate").value==''){
						getObjByName("projectInfoPlan.endDate").value = date.format("yyyy-MM-dd");
					}
				</#if>
		   </script>
		<tr>
		<td align="right" valign="top">
			<label class="label">完成百分比:</label>
		</td>
		<td>
			<input type="text" style="width:60px" value="${projectInfoPlan.percentt?if_exists}" id="projectInfoPlan.percentt" name="projectInfoPlan.percentt" onblur="checkPercentt();">%
		</td>
		<td align="right" valign="top">
			<label class="label">优先级:</label>
		</td>
		<td>
			<select id="projectInfoPlan.priority" name ="projectInfoPlan.priority">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			</select><font color="red">10级为最高优先级</font>
		</td>
		<script>
			getObjByName("projectInfoPlan.priority").value=${projectInfoPlan.priority?if_exists};
		</script>
		</tr>
		</tr>
		
     <tr>
			<!--描述-->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('proPlanOutline')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="projectInfoPlan.outline" rows="4" cols="150" >${projectInfoPlan.outline?if_exists}</textarea>
	        </td>
		</tr>
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
		</#if>
		<#-- 
		<#if projectInfoPlan.isSaved?exists &&projectInfoPlan.isSaved=='0' >
	    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'"/>
	    <#else>
	    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'" disabled="true"/>
	    </#if>
		 -->
		
		<#if (editFlag?exists&&editFlag=='editFlag')>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/projectInfo/listMyPlan.html"/>
		<#else>
		<#-- 继续新建按钮   -->
			<#if projectInfoPlan.id?exists>
			<@redirectButton value="${action.getText('newNext')}" 
				url="${returnUrl}"/>
			<#else>
			<@redirectButton name="newNext" value="${action.getText('newNext')}" 
				url="${req.contextPath}/projectInfo/editProPlan.html"/>
			<script language="JavaScript" type="text/JavaScript"> 
			getObjByName("newNext").disabled="true";
			</script>
			</#if>
		
		<input class="button" type="button" value="${action.getText('close')}"  onclick="closeThis();">
		</#if>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
		 <#if projectInfoPlan.planState?exists>
			getObjByName('planState.id').value='${projectInfoPlan.planState.id?if_exists}';
			<#elseif req.getParameter('planState.id')?exists>
			getObjByName('planState.id').value='${req.getParameter('planState.id')}';
		</#if>
		
	}
	//弹出业务员查询模态窗体
	function salesman_OpenDialog(){
	   var url =  "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	   //window.open(url);
	 }
	 //弹出业务员同行者查询模态窗体
	 function salesmans_OpenDialog(){
	   var url =  "${req.contextPath}/personnelFile/listPersonByUser.html?backVisitCheckBox=backVisitCheckBox";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler_);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
			document.forms[0].elements["personnelFiles.id"].value = result[0];
	   		document.forms[0].elements["projectInfoPlan.personnelFiles"].value = result[2];		 	
		}
	}
	 //获得模态窗体返回值
	function creatorSelectorHandler_(result) {
		if (null != result) {
		var tempEmployees =document.forms[0].elements["projectInfoPlan.assist"].value;
		var tempEmployees_ =document.forms[0].elements["projectInfoPlan.assist_"].value;
		if(tempEmployees==""){
		tempEmployees=result[0];
		}else{
		
		tempEmployees+=","+result[0];
		}
		
		if(tempEmployees_==""){
		tempEmployees_=result[0];
		}else{
		
		tempEmployees_+=","+result[0];
		}
			document.forms[0].elements["projectInfoPlan.assist"].value = tempEmployees;
	   		document.forms[0].elements["projectInfoPlan.assist_"].value = tempEmployees_;		 	
		}
	}
	
	function checkPercentt(){
	var  percentt =getObjByName("projectInfoPlan.percentt").value;
	if(!isNumber("projectInfoPlan.percentt")){
	alert("完成百分比请输入数字");
	getObjByName("projectInfoPlan.percentt").fcous();
	}
	if(percentt<0||percentt>100){
	alert("完成百分比只能是1到100之间的数字数字");
	getObjByName("projectInfoPlan.percentt").fcous();
	}
	//当完成百分比为100的时候。就是自动更新状态为已完成
	if(percentt==100){
	getObjByName('planState.id').value="545";//已完成
	}else if(percentt==0){
	getObjByName('planState.id').value="542";//待执行
	}else{
	getObjByName('planState.id').value="543";//执行中
	}
	}
	
	function savee(){
     	getObjByName('projectInfoPlan.isSaved').value=0;
		return storeValidation();
	}
	function submitt(){
     	getObjByName('projectInfoPlan.isSaved').value=1;
		return storeValidation();
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		/* 验证责任人*/
		if('' == getObjByName('projectInfoPlan.name').value){
			alert("${action.getText('projectInfoPlan.name.required')}");
     		return false;
		}
		if('' == getObjByName('personnelFiles.id').value){
			alert("${action.getText('personnelFiles.name.required')}");
     		return false;
		}
		if('' == getObjByName('projectInfoPlan.startDate').value){
			alert("${action.getText('projectInfoPlan.startDate.required')}");
     		return false;
		}
		if('' == getObjByName('projectInfoPlan.endDate').value){
			alert("${action.getText('projectInfoPlan.endDate.required')}");
     		return false;
		}
		var  percentt =getObjByName("projectInfoPlan.percentt").value;
	if(!isNumber("projectInfoPlan.percentt")){
	alert("完成百分比请输入数字");
	return false;
	}
	if(percentt<0||percentt>100){
	alert("完成百分比只能是1到100之间的数字数字");
	return false;
	}
		/* 验证项目概要 */
     		if(''!= getObjByName('projectInfoPlan.outline').value&&!isValidLengthValue(getObjByName('projectInfoPlan.outline').value,0,500)){
     			alert("${action.getText('planOutline.maxLength')}");
     			getObjByName('projectInfoPlan.outline').value="";
     			getObjByName('projectInfoPlan.outline').focus();
     			return false;
     		}
	
		return true;
	}
	
</script>
</@htmlPage>
