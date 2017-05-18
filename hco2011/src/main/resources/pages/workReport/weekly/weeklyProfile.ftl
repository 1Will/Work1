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
<@htmlPage title="${action.getText('weekly.profile.title')}">
     <@ww.form  name="'listFrom'" action="'saveWeekly'" namespace="'/workReport'" method="'post'" >
     	 <@ww.token name="saveWeeklyToken"/>
     	  <@inputTable>
     	  	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
     	  	<@ww.hidden name="'weekly.id'" value="${weekly.id?if_exists}"/>
     	  	<@ww.hidden name="'personId'" value="${user.id?if_exists}"/>
     	  	
     	  		<@ww.hidden name="'inst.id'" value=""/>
  	  			<@ww.hidden name="'dept.id'" value=""/>
  				<@ww.hidden name="'duty.id'" value=""/>
     	  	
		     <tr>
			<@textfield label="${action.getText('weekly.code')}" name="weekly.code" anothername="code" value="${weekly.code?if_exists}"  required="false" disabled="true" cssClass="underline" maxlength="20"/>
			<@textfield label="${action.getText('weekly.name')}" name="weekly.name" anothername="name" value="${weekly.name?if_exists}"  required="false" disabled="true"  cssClass="underline"maxlength="20"/>
			<@textfield label="${action.getText('weekly.rapporteur')}" name="user.name" anothername="rapporteur" value="${user.name?if_exists}" required="true" cssClass="underline" disabled="true" />
		  	</tr>
		  	<tr>
			<@select label="${action.getText('weekly.inst')}"
				anothername="selectCheckInst"
		       name="inst.id1"
		       value="${req.getParameter('inst.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allInsts" 
		       emptyOption="false" 
		       disabled="true" 
		       required="true"
		       onchange="InstitutionSelectDeptDWR(\"inst.id1\",\"dept.id1\",\"${action.getText('')}\",\"false\")">
		   </@select>
		   
		   <@select label="${action.getText('weekly.dept')}" 
		   		anothername="selectCheckDept"
		       name="dept.id1" 
		       value="${req.getParameter('dept.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allDepts" 
		       emptyOption="false" 
		       disabled="true" 
		       required="true"
		       onchange="DutyCascadeDWR(\"dept.id1\",\"duty.id1\",#{user.organization.id?if_exists},\"${action.getText('')}\",\"edit\")">
		   </@select>
		   <@select label="${action.getText('weekly.duty')}" 
		   		anothername="selectCheckDuty"
		       name="duty.id1" 
		       value="${req.getParameter('duty.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allDutys" 
		       emptyOption="false" 
		       disabled="true" 
		       required="true">
		   </@select>
		   	</tr>
     	  	<tr>
     	  	  	<@datePickerRanger
					anothername="startDate"
					label="${action.getText('weekly.startDate')}"
			       	name="weekly.startDate"
			 		value="${(weekly.startDate?string('yyyy-MM-dd'))?if_exists}" 
					cssClass="underline" 
					maxlength="10"
					required="true"
					flag="true">
				</@datePickerRanger>
     	  	  	<@datePickerRanger
					anothername="endDate"
					label="${action.getText('weekly.endDate')}"
			       	name="weekly.endDate"
			 		value="${(weekly.endDate?string('yyyy-MM-dd'))?if_exists}" 
					cssClass="underline" 
					maxlength="10"
					required="true"
					flag="true">
				</@datePickerRanger>
				
			</tr>
			<tr>
				<td align="right" valign="top">
	        		<label class="label">
	        			${action.getText('weekly.summary')}:
	        		</label>
	        	</td>
		        <td colspan="10">
		        	<textarea id="weekly.summary" name="weekly.summary" rows="4" cols="95" >${weekly.summary?if_exists}</textarea>
		        </td>
			</tr>
			<tr>
				<td align="right" valign="top">
	        		<label class="label">
	        			${action.getText('weekly.leaderIdea')}:
	        		</label>
	        	</td>
		        <td colspan="10">
		        	<textarea id="weekly.leaderIdea" name="weekly.leaderIdea" rows="4" cols="95" >${weekly.leaderIdea?if_exists}</textarea>
		        </td>
			</tr>
			<tr>
				<td align="right" valign="top">
	        		<label class="label">
	        			${action.getText('weekly.comment')}:
	        		</label>
	        	</td>
		        <td colspan="10">
		        	<textarea name="weekly.comment" rows="4" cols="95" >${weekly.comment?if_exists}</textarea>
		        </td>
			</tr>
     	  </@inputTable>
     	  <@buttonBar>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	         <@redirectButton value="${action.getText('back')}" url="listWeekly.html"/>
         </@buttonBar>	
     </@ww.form>
</@htmlPage>
<script type="text/javascript">
		//弹出人事档案
		function user_OpenDialog(){
		   var url = "${req.contextPath}/personnelFile/listPersonByUser.html";
		   popupModalDialog(url, 800, 600, creatorSelectorHandler);
		   //window.open(url, 800, 600);
		 }
		//获得模态窗体返回值
		function creatorSelectorHandler(result) {
			if (null != result) {
				document.forms[0].elements["personId"].value = result[0];
		   		document.forms[0].elements["user.name"].value = result[2];
			}
		}

</script>
<SCRIPT>
	function checkInvalidParms() {
  
		if (-1 == getObjByName('inst.id1').value) {
			getObjByName('inst.id1').value = '';
	    }
	    if (-1 == getObjByName('dept.id1').value) {
			getObjByName('dept.id1').value = '';
	    }
	    if (-1 == getObjByName('duty.id1').value) {
			getObjByName('duty.id1').value = '';
	    }
    	return true;
	}
	window.onload=function(){
		//报告人
		<#if weekly.rapporteur?exists>
			getObjByName('personId').value='${weekly.rapporteur.id?if_exists}';
			getObjByName('user.name').value='${weekly.rapporteur.name?if_exists}';
		</#if>
		//单位
		<#if weekly.inst?exists>
			getObjByName('inst.id1').value='${weekly.inst.id?if_exists}';
			getObjByName('inst.id').value='${weekly.inst.id?if_exists}';
	    	//设置同步
	    	DWREngine.setAsync(false); 
	    	//回调单位的值后触发DWR 级联部门  
	    	InstitutionSelectDeptDWR("inst.id1","dept.id1","${action.getText('')}","false");
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true);
	    <#else>
    	    <#if req.getParameter('inst.id')?exists>
    	        getObjByName('inst.id1').value='${req.getParameter('inst.id')?if_exists}';
    	        getObjByName('inst.id').value='${req.getParameter('inst.id')?if_exists}';
		    	//设置同步
		    	DWREngine.setAsync(false); 
		    	//回调单位的值后触发DWR 级联部门 
		    	InstitutionSelectDeptDWR("inst.id1","dept.id1","${action.getText('')}","false"); 
		    	//重新设置为异步方式
		    	DWREngine.setAsync(true); 
		    	 <#else>
		    	 	<#if user.inst?exists>
			    	 		getObjByName('inst.id1').value='${user.inst.id?if_exists}';
			    	 		getObjByName('inst.id').value='${user.inst.id?if_exists}';
					    	//设置同步
					    	DWREngine.setAsync(false); 
					    	//回调单位的值后触发DWR 级联部门 
					    	InstitutionSelectDeptDWR("inst.id1","dept.id1","${action.getText('')}","false"); 
					    	//重新设置为异步方式
					    	DWREngine.setAsync(true); 
		    	 	</#if>
    	    </#if>
    	    getObjByName('inst.id1').value='${user.institustion.id?if_exists}';
    	    getObjByName('inst.id').value='${user.institustion.id?if_exists}';
	    	//设置同步
	    	DWREngine.setAsync(false); 
	    	//回调单位的值后触发DWR 级联部门  
	    	InstitutionSelectDeptDWR("inst.id1","dept.id1","${action.getText('')}","false");
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true);
    	    getObjByName('dept.id1').value='${user.department.id?if_exists}';
    	    getObjByName('dept.id').value='${user.department.id?if_exists}';
	    	//设置同步
	    	DWREngine.setAsync(false); 
	    	//回调单位的值后触发DWR 级联职务 
			DutyCascadeDWR('dept.id1','duty.id1',#{user.organization.id?if_exists},'${action.getText('')}','edit');
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true); 
	    	<#if peronnelF?exists && peronnelF.duty?exists>
	    		getObjByName('duty.id1').value='#{peronnelF.duty.id?if_exists}';	 
	    		getObjByName('duty.id').value='#{peronnelF.duty.id?if_exists}';	   	
	    	</#if>

	    	
	    </#if>
		//部门
		<#if weekly.dept?exists>
			getObjByName('dept.id1').value='${weekly.dept.id?if_exists}';
			getObjByName('dept.id').value='${weekly.dept.id?if_exists}';
	    	//设置同步
	    	DWREngine.setAsync(false); 
	    	//回调单位的值后触发DWR 级联职务 
			DutyCascadeDWR('dept.id1','duty.id1',#{user.organization.id?if_exists},'${action.getText('')}','edit');
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true); 
	    <#else>
    	     <#if req.getParameter('dept.id')?exists>
		    	 getObjByName('dept.id1').value='${req.getParameter('dept.id')?if_exists}';
		    	 getObjByName('dept.id').value='${req.getParameter('dept.id')?if_exists}';
		    	 //设置同步
		    	 DWREngine.setAsync(false); 
		    	 //回调单位的值后触发DWR 级联职务 
				 DutyCascadeDWR('dept.id1','duty.id1',#{user.organization.id?if_exists},'${action.getText('')}','edit');
		    	 //重新设置为异步方式
		    	 DWREngine.setAsync(true); 
		    	 <#else>
		    	 	<#if user.dept?exists>
			    	 	getObjByName('dept.id1').value='${user.dept.id?if_exists}';
			    	 	getObjByName('dept.id').value='${user.dept.id?if_exists}';
				    	 //设置同步
				    	 DWREngine.setAsync(false); 
				    	 //回调单位的值后触发DWR 级联职务 
						 DutyCascadeDWR('dept.id1','duty.id1',#{user.organization.id?if_exists},'${action.getText('')}','edit');
				    	 //重新设置为异步方式
				    	 DWREngine.setAsync(true); 
		    	 	</#if>
    	     </#if> 
	    </#if>
		 //职位
		<#if weekly.duty?exists>
			getObjByName('duty.id1').value='${weekly.duty.id?if_exists}';
			getObjByName('duty.id').value='${weekly.duty.id?if_exists}';
		<#else>
			<#if req.getParameter('duty.id')?exists>
				getObjByName('duty.id1').value='${req.getParameter('duty.id')?if_exists}';
				getObjByName('duty.id').value='${req.getParameter('duty.id')?if_exists}';
				<#else>
		    	 	<#if user.duty?exists>
		    	 		getObjByName('duty.id1').value='${user.duty.id?if_exists}';
		    	 		getObjByName('duty.id').value='${user.duty.id?if_exists}';
		    	 	</#if>
			</#if>    
		</#if>
	}
	<#-- 注册事件-->
	function addEvent(){
		j$("#weekly\\.leaderIdea").change(function(){
			perType();
		});
		j$("#weekly\\.summary").change(function(){
			perType();
		});	
	}
	function perType(){
		<#if perType=='0'>
			j$("#weekly\\.summary").attr("disabled",false);
			j$("#weekly\\.leaderIdea").attr("disabled",true);
		</#if>
		<#if perType=='1'>
			j$("#weekly\\.summary").attr("disabled",true);
			j$("#weekly\\.leaderIdea").attr("disabled",false);
		</#if>
		<#if perType=='2'>
			j$("#weekly\\.summary").attr("disabled",true);
			j$("#weekly\\.leaderIdea").attr("disabled",false);
		</#if>
	}
	//j$(function(){
		perType();
		addEvent();
	//});
	//验证各属性是否必填、格式
  function validate(){
		//报告人
		if(getObjByName('personId').value==''){
			alert('${action.getText('weekly.rapporteur.required')}');
			return false;
		}
		//单位
		if(!selectCheck_selectCheckInst()){
	//	getObjByName('inst.id1').focus();
		    return false;
		}
		//部门
		if(!selectCheck_selectCheckDept()){
	//	getObjByName('dept.id1').focus();
		    return false;
		}
		//职位
		if(!selectCheck_selectCheckDuty()){
		//getObjByName('duty.id1').focus();
		    return false;
		}
		//起始日期
		if(!dateCheck_startDate()){
			getObjByName('weekly.startDate').focus();
			return false;
		}
		//终止日期
		if(!dateCheck_endDate()){
			getObjByName('weekly.endDate').focus();
			return false;
		}
		//验证起始日期是否大于终止日期
	    var star = getObjByName('weekly.startDate').value;
	    var end = getObjByName('weekly.endDate').value;
	    if(isDateLessThenOldDate(star,end)){
			alert('${action.getText('weekly.date.error')}');
			getObjByName('weekly.startDate').value="";
			getObjByName('weekly.endDate').value="";
			getObjByName('weekly.startDate').focus();
			return false;
		}
		if(getObjByName('weekly.summary').value.length>500){
			alert('${action.getText('weekly.summary.alert')}')
			getObjByName('weekly.startDate').value="";
			getObjByName('weekly.endDate').value="";
			getObjByName('weekly.startDate').focus();
			return false;
		}
		if(getObjByName('weekly.leaderIdea').value.length>500){
			alert('${action.getText('weekly.summary.alert')}')
			getObjByName('weekly.startDate').value="";
			getObjByName('weekly.endDate').value="";
			getObjByName('weekly.startDate').focus();
			return false;
		}
		if(getObjByName('weekly.comment').value.length>500){
			alert('${action.getText('weekly.comment.alert')}')
			getObjByName('weekly.startDate').value="";
			getObjByName('weekly.endDate').value="";
			getObjByName('weekly.startDate').focus();
			return false;
		}
		return true;
  }
</script>
<#if !first>
<ul id="beautytab">
	<li>
		<a id="dailyInfo" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/workReport/listDailyTab.html?weekly.id=${weekly.id}&org.id=${weekly.organization.id}&rapporteur.id=${weekly.rapporteur.id}' target="frame" >日报信息</a>
	</li>
	<li>
		<a id="nextWeekPlanInfo" onclick="activeTab(this);"  href='listNextWeekPlan.html?weekly.id=${weekly.id}' target="frame" >${action.getText('下周计划')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/workReport/listDailyTab.html?weekly.id=${weekly.id}&org.id=${weekly.organization.id}&rapporteur.id=${weekly.rapporteur.id}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="40%"/>
</#if>