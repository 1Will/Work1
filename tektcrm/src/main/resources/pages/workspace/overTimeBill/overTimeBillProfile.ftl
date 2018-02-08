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

<@htmlPage title="${action.getText('overTimeBill.profile')}">
<@ww.form namespace="'/overTimeBill'" name="'overTimeBill'" action="'saveOverTimeBill'" method="'post'">
<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="saveOverTimeBillToken"/>
	<@ww.hidden name="'isSaved'" value=""/>
	<#if overTimeBill.contractManagement?exists>
		<@ww.hidden  name="'contractManagement.id'" value="#{overTimeBill.contractManagement.id?if_exists}"/>
	<#else>
		<@ww.hidden  name="'contractManagement.id'" value=""/>
	</#if>
	<#if overTimeBill.projectInfo?exists>
		<@ww.hidden name="'projectInfo.id'" value="#{overTimeBill.projectInfo.id?if_exists}"/>
	<#else>
		<@ww.hidden  name="'projectInfo.id'" value=""/>
	</#if>
	
    <@inputTable>
    	<#if overTimeBill.id?exists>
    		<@ww.hidden name="'overTimeBill.id'" value="#{overTimeBill.id?if_exists}"/>
	 	</#if>
	 	<tr>
            <@ww.textfield label="'${action.getText('overTimeBill.code')}'" name="'overTimeBill.code'" value="'${overTimeBill.code?if_exists}'" cssClass="'underline'" required="false" disabled="true"/>
			<@pp.datePicker 
				label="'${action.getText('overTimeBill.crateDate')}'" 
				name="'overTimeBill.createDate'" 
	   			value="'${(overTimeBill.createDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
			<script language="javascript">
					var date = new Date();
					if(getObjByName("overTimeBill.createDate").value==''){
						getObjByName("overTimeBill.createDate").value = date.format("yyyy-MM-dd");
					}
			</script>
		</tr>
		<tr>
			<#-- 以下td为添加内容(项目名称) -->
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('项目名称')}:</label>
	     	</td>
			<td>
				<#if overTimeBill.projectInfo?exists>
					<input type="text" id="projectName" name="projectName" class="underline"  value="${overTimeBill.projectInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" id="projectName"  name="projectName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
					<a onClick="projectName_OpenDialog();">
							<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
					</a>
			</td>
	     	
	     	<td align="right" valign="top">
	       		<label class="label">${action.getText('合同名称')}:</label>
	     	</td>
	     	<td>
	     		<#if overTimeBill.contractManagement?exists>
		   			<input type="text" name="overTimeBill.contractManagement" class="underline"  value="${overTimeBill.contractManagement.contractName?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" name="overTimeBill.contractManagement" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
					<a onClick="contractManagement_OpenDialog();">
						<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
					</a>
			</td>
		</tr>
		<tr>
			<#if overTimeBill.applyPerson?exists>
				<@ww.textfield label="'${action.getText('overTimeBill.applyPerson')}'" name="'overTimeBill.applyPerson'" value="'${overTimeBill.applyPerson.name?if_exists}'" cssClass="'underline'" required="true" readonly="true"/>
			<#else>
				<@ww.textfield label="'${action.getText('overTimeBill.applyPerson')}'" name="'overTimeBill.applyPerson'" value="''" cssClass="'underline'" required="true" readonly="true"/>
			</#if>
			<@ww.select label="'${action.getText('overTimeBill.dept')}'" 
				name="'dept.id'" 
				value="${req.getParameter('dept.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allDepts"
				emptyOption="true" 
				disabled="true">
			</@ww.select>
		</tr>
		<tr>
			<@pp.datePicker 
					label="'${action.getText('overTimeBill.startTime')}'" 
					name="'overTimeBill.startTime'" 
		   			value="'${(overTimeBill.startTime?string('yyyy-MM-dd HH:mm'))?if_exists}'"
					cssClass="'underline'" 
					required="true"
					dateFormat="datetime"
					showsTime="true"
					maxlength="20"/>
				<script language="javascript">
					var date = new Date();
					if(getObjByName("overTimeBill.startTime").value==''){
						getObjByName("overTimeBill.startTime").value = date.format("yyyy-MM-dd hh:mm");
					}
				</script>
				<#--
			<@pp.datePicker 
					label="'${action.getText('overTimeBill.endTime')}'" 
					name="'overTimeBill.endTime'" 
		   			value="'${(overTimeBill.endTime?string('yyyy-MM-dd'))?if_exists}'"
					cssClass="'underline'" 
					required="true"
					dateFormat="'%Y-%m-%d'"
					maxlength="10"/>
        </tr>-->      	
			<@pp.datePicker 
					label="'${action.getText('overTimeBill.endTime')}'" 
					name="'overTimeBill.endTime'" 
		   			value="'${(overTimeBill.endTime?string('yyyy-MM-dd HH:mm'))?if_exists}'"
					cssClass="'underline'" 
					required="true"
					dateFormat="datetime"
					showsTime="true"
					maxlength="20"/>
        	
		</tr>
        <tr>
        	<@ww.textfield label="'${action.getText('overTimeBill.manHour')}'" name="'overTimeBill.manHour'" value="'${overTimeBill.manHour?if_exists}'" cssClass="'underline'" required="false" readonly="true"/>
        	<#if overTimeBill.id?exists>
	        	<@ww.select label="'${action.getText('overTimeBill.status')}'" 
					name="'status.id'" 
					value="${req.getParameter('status.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allStatus"
					required="true"
					emptyOption="true" 
					disabled="true">
				</@ww.select>
			<#else>
				<@ww.select label="'${action.getText('overTimeBill.status')}'" 
					name="'status.id'" 
					value="${req.getParameter('status.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allStatus"
					required="true"
					disabled="true">
				</@ww.select>
			</#if>
        </tr>
        <tr>
       <#if overTimeBill.status?exists && overTimeBill.status.code != '02000'>
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
		<script type="text/javascript" >
		<#if overTimeBill.flow?exists>
			getObjByName('flow.id').value='${overTimeBill.flow.id?if_exists}';
		</#if>
		</script>
        </tr>
		<tr>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('overTimeBill.betreffzeile')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="overTimeBill.betreffzeile" rows="4" cols="150">${overTimeBill.betreffzeile?if_exists}</textarea>
			</td>
		</tr>
		
		<#--
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('overTimeBill.failReason')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="overTimeBill.failReason" rows="4" cols="150" readonly="true">${overTimeBill.failReason?if_exists}</textarea>
			</td>
		</tr>
		-->
    </@inputTable>
    <@buttonBar>
     <#if activitiFLow?exists>
    <input type="button" name="close" value="关闭" onclick="window.close()">
    <#else>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
		
			<#if overTimeBill.isSaved?exists && overTimeBill.isSaved=='0' >
		    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'"/>
		    <#else>
		    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'" disabled="true"/>
		    </#if>
			
			<#-- 继续新建按钮   -->
			<#if overTimeBill.id?exists>
				<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/overTimeBill/editOverTimeBill.html"/>
			<#else>
				<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/overTimeBill/editOverTimeBill.html"/>
					<script language="JavaScript" type="text/JavaScript"> 
					getObjByName("newNext").disabled="true";
					</script>
			</#if>
		</#if>
		
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/overTimeBill/listOverTimeBill.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
</#if>   
</@buttonBar>

</@ww.form>

<script type="text/javascript">
//初始化页面
<#if overTimeBill.status?exists>
var statusCode = "${overTimeBill.status.code?if_exists}";
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
//	window.onload=function(){
		<#if overTimeBill.dept?exists>
			getObjByName('dept.id').value='${overTimeBill.dept.id?if_exists}';
		<#else>
			<#if overTimeBill.applyPerson?exists>
				<#if overTimeBill.applyPerson.dept?exists>
					getObjByName('dept.id').value='${overTimeBill.applyPerson.dept.id}';
				</#if>
			</#if>
		</#if>
		<#if overTimeBill.status?exists>
			getObjByName('status.id').value='${overTimeBill.status.id?if_exists}';
		</#if>
//	}
	
	
	function getDate(dt){
		var tem = dt.split(" ");
		var date = tem[0].split("-");
		var mon = parseInt(date[1])-1;
		
		var time =tem[1].split(":");
		var newDate=new Date(date[0],mon,date[2],time[0],time[1]);
		return newDate;
	}
	
	document.onclick = function (){
		var star = getObjByName("overTimeBill.startTime").value;
		var end = getObjByName("overTimeBill.endTime").value;
		var c= getDate(end)-getDate(star);
		getObjByName("overTimeBill.manHour").value = (c/3600000).toFixed(1);
	}
	
	
			//合同管理模态窗体
	function contractManagement_OpenDialog(){
		var pjId = getObjByName("projectInfo.id").value;
		var url = "${req.contextPath}/contractManagement/listContractManagementWindowAction.html?readOnly=${req.getParameter('readOnly')?if_exists}&project.id="+pjId;
	   	popupModalDialog(url, 800, 600, creatorSelector3Handler);
	 }
	 //获得模态窗体返回值
	function creatorSelector3Handler(result) {
		if (null != result) {
			getObjByName("contractManagement.id").value = result[0];
	   		getObjByName('overTimeBill.contractManagement').value=result[1];
		}
	}
	 //项目名称查询模态窗体(添加)
	function projectName_OpenDialog(){
	   		var url = "${req.contextPath}/projectInfo/listProjectInfo.html?readOnly=${req.getParameter('readOnly')?if_exists}&backVisitCheckBox=backVisitCheckBox";
	   		popupModalDialog(url, 800, 600, creatorSelector_Handler);
	 }
	 //项目名称-获得模态窗体返回值
	function creatorSelector_Handler(result) {
		if (null != result) {
			getObjByName("projectInfo.id").value=(result[0]);
			getObjByName("projectName").value=(result[1]);
		}
	}
	
	
	function submitt(){
		if(getObjByName("submit").value == "重新提交"){
			getObjByName('isSaved').value="2";
		}else{
			getObjByName('isSaved').value="1";
		}
		return storeValidation();
    }
    
    function savee(){
		getObjByName('isSaved').value="0";
     	return storeValidation();
    }
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		<#-- 咨询时间 20 -->
		if(getObjByName('overTimeBill.createDate').value ==''){
		 		alert("${action.getText('overTimeBill.createDate.not.null')}");
		 		getObjByName('overTimeBill.createDate').focus();
		      	return false;   
		}else{
		 	if(!isDate('overTimeBill.createDate')){
				alert("${action.getText('overTimeBill.createDate.dateFormate.error')}");
	      	    getObjByName('overTimeBill.createDate').focus();
				return false;
			} 
			if(isDateLessThenCurrent(getObjByName('overTimeBill.createDate').value)){
				alert('${action.getText('overTimeBill.createDate.earlyError')}');
	       		getObjByName('overTimeBill.createDate').focus();
				return false;
			}
		}
		if(getObjByName('projectName').value==''){
			alert("${action.getText('请选择项目')}");
			getObjByName('projectName').focus();
			return false;
		}
		if(getObjByName('overTimeBill.applyPerson').value==''){
			alert("${action.getText('overTimeBill.applyPerson.requiredstring')}");
			getObjByName('overTimeBill.applyPerson').focus();
			return false;
		}
		if(getObjByName('dept.id').value==''){
			alert("${action.getText('dept.id.requiredstring')}");
			getObjByName('dept.id').focus();
			return false;
		}
		
		if(getObjByName('overTimeBill.startTime').value ==''){
		 		alert("${action.getText('overTimeBill.startTime.not.null')}");
		 		getObjByName('overTimeBill.startTime').focus();
		      	return false;   
		}else{
		 	if(!isDate_Hour_Min('overTimeBill.startTime')){
				alert("${action.getText('overTimeBill.startTime.dateFormate.error')}");
	      	    getObjByName('overTimeBill.startTime').focus();
				return false;
			} 
		}
		
		if(getObjByName('overTimeBill.endTime').value ==''){
		 		alert("${action.getText('overTimeBill.endTime.not.null')}");
		 		getObjByName('overTimeBill.endTime').focus();
		      	return false;   
		}else{
		 	if(!isDate_Hour_Min('overTimeBill.endTime')){
				alert("${action.getText('overTimeBill.endTime.dateFormate.error')}");
	      	    getObjByName('overTimeBill.endTime').focus();
				return false;
			} 
			if(getObjByName('overTimeBill.startTime').value>getObjByName('overTimeBill.endTime').value){
				alert('${action.getText('overTimeBill.endTime.earlyError')}');
	       		getObjByName('overTimeBill.endTime').focus();
				return false;
			}
		}
		if(getObjByName('status.id').value==''){
			alert("${action.getText('status.id.requiredstring')}");
			getObjByName('status.id').focus();
			return false;
		}
		if(getObjByName('flow.id').value==''){
			alert("请选择流程类型！");
			getObjByName('flow.id').focus();
			return false;
		}
		if(getObjByName('overTimeBill.betreffzeile').value==''){
			alert("${action.getText('overTimeBill.betreffzeile.requiredstring')}");
			getObjByName('overTimeBill.betreffzeile').focus();
			return false;
		}
		return true;
	}
</script>
</@htmlPage>
<#if overTimeBill.id?exists>
<ul id="beautytab">
	<li>
		<a id="runPoint" onclick="activeTab(this);"  href='${req.contextPath}/activitiFlow/listRunPoint.html?flow.id=${overTimeBill.flow.id?if_exists}&bussnessId=#{overTimeBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('审批人')}</a>
	</li>
	<li>
		<a id="CopySendPerson" onclick="activeTab(this);"  href='${req.contextPath}/activitiFlow/listCopySendPerson.html?flow.id=${overTimeBill.flow.id?if_exists}&bussnessId=#{overTimeBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('抄送人')}</a>
	</li>
	<li>
		<a id="additionalInformation" onclick="activeTab(this);"  href='${req.contextPath}/applicationDocManager/listApplicationDoc.html?overTimeBill.id=#{overTimeBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('附件资料')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/activitiFlow/listRunPoint.html?flow.id=${overTimeBill.flow.id?if_exists}&bussnessId=#{overTimeBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="60%"/>
</#if>