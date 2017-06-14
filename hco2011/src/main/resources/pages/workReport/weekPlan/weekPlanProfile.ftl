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
     	  	
     	  	<#if weekPlan.id?exists>
     	  		<@ww.hidden id="weekly.id" name="'weekly.id'" value="#{weekPlan.weekly.id}"/>
     	  	<#else>
     	  		<@ww.hidden id="weekly.id" name="'weekly.id'" value="${req.getParameter('weekly.id')?if_exists}"/>
     	  	</#if>
     	  	
     	  	<#if weekPlan.id?exists>
     	  		<@ww.hidden id="projectInfo.id" name="'projectInfo.id'" value="#{weekPlan.projectInfo.id?if_exists}"/>
     	  	<#else>
     	  		<@ww.hidden id="projectInfo.id" name="'projectInfo.id'" value="${req.getParameter('projectInfo.id')?if_exists}"/>
     	  	</#if>
  			<@inputTable>
  			
  			<tr>
  			<#if req.getParameter('weekly.id')?exists||weekPlan.id?exists>
	  			<td align="right" valign="top">
		       		<label class="label">项目名称:</label>
		     	</td>
		     	<td>
		     	<#if weekPlan.id?exists>
			   		<input type="text" id="weekPlan.projectName" name="weekPlan.projectName" class="underline"  value="${weekPlan.projectInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>	
			   		<input type="text" id="weekPlan.projectName" name="weekPlan.projectName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
					<a href="javascript:pj_OpenDialog()">
						<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
					</a>
				</td>
     	  	</#if>
     	  	
     	  	<#if req.getParameter('projectInfo.id')?exists||weekPlan.id?exists>
     	  		<td align="right" valign="top">
     	  			<label class="label">周报:</label>
     	  		</td>
     	  		<td>
     	  			<#if weekPlan.id?exists>
			   			<input type="text" id="weekPlan.weeklyCode" name="weekPlan.weeklyCode" class="underline"  value="${weekPlan.weekly.code?if_exists}" maxlength="140" size="20" disabled="true"/>
					<#else>	
			   			<input type="text" id="weekPlan.weeklyCode" name="weekPlan.weeklyCode" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
					</#if>
						<a href="javascript:weekly_OpenDialog()">
							<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
						</a>
     	  		</td>
     	  	</#if>
     	  	</tr>
     	  	
     	  	
		    <tr>
		    	<td align="right" valign="top">
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
	        		<label class="label">
	        			${action.getText('本周计划')}:
	        		</label>
	        	</td>
		    	<td colspan="10">
			        <textarea id="weekPlan.thisPlan" name="weekPlan.thisPlan" rows="4" cols="140" ><#if weekPlan.id?exists>${weekPlan.thisPlan?if_exists}</#if></textarea>
		        </td>
			</tr>
			<tr>
				<td align="right" valign="top">
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
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return saveCheck();'"/>
	         <@vbutton value="${action.getText('close')}" onclick="closeThis()"/>
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
		
		function setProjectInfo(result) {
			if (null != result) {
			 	getObjByName('projectInfo.id').value = result[0];	
			 	getObjByName('weekPlan.projectName').value = result[1];	
			}
//			var wklyId = getObjByName('weekly.id').value;
//			DWREngine.setAsync(false);
//			GetLastPlan.getLastPlanMethod(result[0],wklyId,setLastPlan);
//			DWREngine.setAsync(true);
		}
		
		function setLastPlan(data){
			getObjByName('weekPlan.lastPlan').value = data;
		}
		
		//弹出周报选择
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
		
		function saveCheck(){
		
			<#if req.getParameter('weekly.id')?exists>
			if(getObjByName('weekPlan.projectName').value==''){
				alert('请选择项目！');
				return false;
			}
			</#if>
			<#if req.getParameter('projectInfo.id')?exists>
			if(getObjByName('weekPlan.weeklyCode').value==''){
				alert('请选择周报！');
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

