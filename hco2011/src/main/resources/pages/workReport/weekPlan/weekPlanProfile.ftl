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
<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('周计划维护')}" >
     <@ww.form  name="'saveWeekPlan'" action="'saveWeekPlan'" namespace="'/workReport'" method="'post'" >
     <@ww.token name="saveWeekPlanToken"/>
     	  	<#if weekPlan.id?exists>
     	  		<@ww.hidden name="'weekPlan.id'" value="#{weekPlan.id}"/>
     	  	</#if>
     	  	
     	  	<#if weekPlan.weekly?exists>
     	  		<@ww.hidden id="weekly.id" name="'weekly.id'" value="#{weekPlan.weekly.id}"/>
     	  	<#else>
     	  		<@ww.hidden id="weekly.id" name="'weekly.id'" value="${req.getParameter('weekly.id')?if_exists}"/>
     	  	</#if>
     	  	
     	  	<#if weekPlan.week?exists>
     	  		<@ww.hidden id="week.id" name="'week.id'" value="#{weekPlan.week.id}"/>
     	  	<#else>
     	  		<@ww.hidden id="week.id" name="'week.id'" value=""/>
     	  	</#if>
     	  	
     	  	<#if weekPlan.id?exists>
     	  		<@ww.hidden id="projectInfo.id" name="'projectInfo.id'" value="#{weekPlan.projectInfo.id?if_exists}"/>
     	  	<#else>
     	  		<@ww.hidden id="projectInfo.id" name="'projectInfo.id'" value="${req.getParameter('projectInfo.id')?if_exists}"/>
     	  	</#if>
     	  	
     	  	<@ww.hidden name="'weekPlan.isSaved'" id="'weekPlan.isSaved'" value="''"/>
  			<@inputTable>
  			<tr>
	     	  	<td align="right" valign="top">
					<span class="required">*</span>
		       		<label class="label">${action.getText('周')}:</label>
		     	</td>
				<td>
			<#if weekPlan.week?exists>
				<input type="text" id="week.name" name="week.name" class="underline"  value="${weekPlan.week.name?if_exists}" maxlength="140" size="20" disabled="true"/>
			<#else>
				<input type="text" id="week.name" name="week.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
	     	  	<a onClick="week_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
     	  	</#if>
	     	  	</td>
  			
	  			<td align="right" valign="top">
	  				<span class="required">*</span>
		       		<label class="label">项目名称:</label>
		     	</td>
		     	<td>
	     	<#if weekPlan.projectInfo?exists>
		   		<input type="text" id="weekPlan.projectName" name="weekPlan.projectName" class="underline"  value="${weekPlan.projectInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
			<#else>	
		   		<input type="text" id="weekPlan.projectName" name="weekPlan.projectName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				<a href="javascript:pj_OpenDialog()">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</#if>
				</td>

     	  	</tr>
     	  	
     	  	
		    <tr>
		    	<td align="right" valign="top">
		    	<span class="required">*</span>
	        		<label class="label">
	        			${action.getText('上周计划')}:
	        		</label>
	        	</td>
		    	<td colspan="10">
			        <textarea id="weekPlan.lastPlan" name="weekPlan.lastPlan" rows="4" cols="140" readOnly="readOnly"><#if weekPlan.id?exists>${weekPlan.lastPlan?if_exists}<#else>${lastPlan?if_exists}</#if></textarea>
		        </td>
			</tr>
			<tr>
				<td align="right" valign="top">
				<span class="required">*</span>
	        		<label class="label">
	        			${action.getText('本周实绩')}:
	        		</label>
	        	</td>
		    	<td colspan="10">
			        <textarea id="weekPlan.thisPlan" name="weekPlan.thisPlan" rows="4" cols="140" ><#if weekPlan.id?exists>${weekPlan.thisPlan?if_exists}</#if></textarea>
		        </td>
			</tr>
			<tr>
				<td align="right" valign="top">
				<span class="required">*</span>
	        		<label class="label">
	        			${action.getText('下周计划')}:
	        		</label>
	        	</td>
		    	<td colspan="10">
			        <textarea id="weekPlan.nextPlan" name="weekPlan.nextPlan" rows="4" cols="140" ><#if weekPlan.id?exists>${weekPlan.nextPlan?if_exists}</#if></textarea>
		        </td>
		  	</tr>
		  	<tr>
     	  </@inputTable>
     	  
     	  <@buttonBar>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
	         
		    <#if weekPlan.isSaved?exists &&weekPlan.isSaved=='0' >
		    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'"/>
		    <#else>
		    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'" disabled="true"/>
		    </#if>
	         
	         <@vbutton class="button" value="${action.getText('close')}" onclick="closeThis()"/>
         </@buttonBar>	
     </@ww.form>
</@htmlPage>
<script type='text/javascript' src='${req.contextPath}/dwr/interface/GetLastPlan.js'></script>
<script>
		//弹出项目选择
		function pj_OpenDialog(){
			var  url = "${req.contextPath}/projectInfo/listProjectInfo.html?contactArchivesFlag=contactArchivesFlag";
			popupModalDialog(url, 900, 600, setProjectInfo);
		}
		var flag = true;
		function setProjectInfo(result) {
			<#if req.getParameter('week.id')?exists>
				var weekId = ${req.getParameter('week.id')?if_exists};
			</#if>
			<#if user?exists>
				var userId = ${user.id?if_exists};
			</#if>
			getLastPlan(result[0],weekId,userId);
			if (null != result&&flag) {
			 	getObjByName('projectInfo.id').value = result[0];	
			 	getObjByName('weekPlan.projectName').value = result[1];	
			}
		}
		
		//弹出周选择
		function week_OpenDialog(){
			 var url = "${req.contextPath}/workReport/listWeek.html";
			 popupModalDialog(url, 800, 600, setDate);
		}
		function setDate(data){
			<#if user?exists>
				var userId = ${user.id?if_exists};
			</#if>
			var pjid = getObjByName('projectInfo.id').value;
			getLastPlan(pjid,data[0],userId)
			if(flag){
				getObjByName('week.id').value =data[0];
				getObjByName('week.name').value =data[1];
			}
		}
		
		function getLastPlan(projectId,weekId,userId){
			DWREngine.setAsync(false);
			GetLastPlan.getLastPlanMethod(projectId,weekId,userId,setLastPlan);
			DWREngine.setAsync(true);
		}
		
		function setLastPlan(data){
			if(data!=""){
				getObjByName('weekPlan.lastPlan').value = data;
				flag = true ;
			}else{
				alert("周计划已存在！");
				flag =false;
			}
		}
		
		//弹出周计划选择
		function weekly_OpenDialog(){
			var  url = "${req.contextPath}/workReport/listWeekly.html?projectFlag=projectFlag";
			popupModalDialog(url, 900, 600, setWeekly);
		}
		
		function setWeekly(result){
			if (null != result) {
			 	getObjByName('weekly.id').value = result[0];	
			 	getObjByName('weekPlan.weeklyCode').value = result[1];	
			}
		}
		
		function savee(){
		 	getObjByName('weekPlan.isSaved').value=0;
			return saveCheck();
		}
		function submitt(){
		 	getObjByName('weekPlan.isSaved').value=1;
			return saveCheck();
		}
		
		function saveCheck(){
		
			<#if req.getParameter('weekly.id')?exists>
			if(getObjByName('weekPlan.projectName').value==''){
				alert('请选择项目！');
				return false;
			}
			</#if>
			<#if req.getParameter('projectInfo.id')?exists>
			if(getObjByName('week.name').value==''){
				alert('请选择周！');
				return false;
			}
			
			</#if>
			
			if(getObjByName('weekPlan.thisPlan').value==''){
				alert('请填写本周计划！');
				return false;
			}
			if(getObjByName('weekPlan.nextPlan').value==''){
				alert('请填写下周计划！');
				return false;
			}
			if(getObjByName('weekPlan.thisPlan').value.length>500){
				alert('本周计划字数过多，请控制在500字以内！');
				return false;
			}
			if(getObjByName('weekPlan.nextPlan').value.length>500){
				alert('下周计划字数过多，请控制在500字以内！');
				return false;
			}
			
		}
</script>

