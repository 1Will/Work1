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
    <@inputTable>
    	<@ww.hidden name="'projectInfo.id'" value="'${projectInfoId?if_exists}'"/>
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
			<td colspan="3">
				<input type="text" name="projectInfoPlan.name" class="underline"  value="${projectInfoPlan.name?if_exists}" maxlength="140" size="120" />
			</td>
		<!---->
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
		
		</tr>
     <tr>
			<!--描述-->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('proPlanOutline')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="projectInfoPlan.outline" rows="3" cols="120" >${projectInfoPlan.outline?if_exists}</textarea>
	        </td>
		</tr>
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		<input type="button" value="${action.getText('back')}"  onclick="window.close();">
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
