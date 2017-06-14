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

<@htmlPage title="${action.getText('title')}">
<@ww.form namespace="'/activity'" name="'activityProfile'" action="'saveActivity'" method="'post'">
	<@ww.token name="saveActivityToken"/>
    <@inputTable>
		<@ww.hidden name="'persons.id'" value="'${req.getParameter('persons.id')?if_exists}'"/>
    	<#if activity.id?exists>
    		<@ww.hidden name="'activity.id'" value="#{activity.id?if_exists}"/>
	 	</#if>
	 	<tr>
            <@textfield label="${action.getText('activity.code')}" name="activity.code" anothername="code" value="${activity.code?if_exists}" required="false" cssClass="underline" maxlength="20" disabled="true"/>
            <@textfield label="${action.getText('activity.theme')}" name="activity.theme" anothername="theme" value="${activity.theme?if_exists}" required="true" cssClass="underline" maxlength="20"/> 
            <@textfield label="${action.getText('activity.customers')}" name="activity.customers" anothername="customers" value="${activity.customers?if_exists}" required="true" cssClass="underline" maxlength="20"/>  
		</tr>	
		<tr>
            <@textfield label="${action.getText('activity.target')}" name="activity.target" anothername="target" value="${activity.target?if_exists}" required="true" cssClass="underline" maxlength="20"/>  
			<@pp.datePicker 
				label="'${action.getText('activity.beginTime')}'" 
				name="'activity.beginTime'" 
	   			value="'${(activity.beginTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>	
			<script language="javascript">
					var date = new Date();
					if(getObjByName("activity.beginTime").value==''){
						getObjByName("activity.beginTime").value = date.format("yyyy-MM-dd");
					}
			</script>
			<@pp.datePicker 
				label="'${action.getText('activity.endTime')}'" 
				name="'activity.endTime'" 
	   			value="'${(activity.endTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>	
		</tr>
	 	<tr>
            <@textfield label="${action.getText('activity.place')}" name="activity.place" anothername="place" value="${activity.place?if_exists}" required="true" cssClass="underline" maxlength="20"/>  
		    <@select label="${action.getText('activity.activityType')}" 
		   	   anothername="selectCheckActivityType"
		       name="activityType.id" 
		       value="${req.getParameter('activityType.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allActivityType" 
		       emptyOption="true" 
		       disabled="false" 
		       required="true">
		    </@select>
		    <@select label="${action.getText('activity.status')}" 
		   	   anothername="selectCheckActivityStatus"
		       name="status.id" 
		       value="${req.getParameter('status.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allStatus" 
		       emptyOption="true" 
		       disabled="false" 
		       required="true">
		    </@select>
		</tr>	
	 	<tr>
		    <@select label="${action.getText('activity.progress')}" 
		   	   anothername="selectCheckActivityProgress"
		       name="progress.id" 
		       value="${req.getParameter('progress.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allProgress" 
		       emptyOption="true" 
		       disabled="false" 
		       required="true">
		    </@select>
		    <@select label="${action.getText('activity.priority')}" 
		   	   anothername="selectCheckActivityPriority"
		       name="priority.id" 
		       value="${req.getParameter('priority.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allPriority" 
		       emptyOption="true" 
		       disabled="false" 
		       required="true">
		    </@select>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('activity.persons')}:</label>
	     	</td>
	     	<td>
	     		<#if activity.persons?exists>
		   		<input type="text" name="activity.persons" class="underline"  value="${activity.persons.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="activity.persons" class="underline"  value="${req.getParameter('activity.persons')?if_exists}" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="persons_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>  
		</tr>	
        <tr>
            <@textfield label="${action.getText('activity.fee')}" name="activity.fee" anothername="fee" value="${activity.fee?if_exists}" required="false" cssClass="underline" maxlength="20"/>  
        </tr>
		<tr>
		    <@textarea name="activity.content" label="${action.getText('activity.content')}" anothername="content" maxLength="500" required="true" value="${activity.content?if_exists}"/>
		</tr>
		<tr>		
			<td align="right" valign="top">
	       		<label class="label">${action.getText('activity.summary')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="activity.summary" rows="4" cols="150" >${activity.summary?if_exists}</textarea>
			</td>
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('activity.comment')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="activity.comment" rows="4" cols="150" >${activity.comment?if_exists}</textarea>
			</td>
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('activity.remark')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="activity.remark" rows="4" cols="150" >${activity.remark?if_exists}</textarea>
			</td>
		</tr>
    </@inputTable>
    <@buttonBar>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/activity/listActivity.html"/>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
		<#if activity.activityType?exists>
			getObjByName('activityType.id').value='${activity.activityType.id?if_exists}';
		</#if>
		<#if activity.status?exists>
			getObjByName('status.id').value='${activity.status.id?if_exists}';
		</#if>
		<#if activity.progress?exists>
			getObjByName('progress.id').value='${activity.progress.id?if_exists}';
		</#if>
		<#if activity.priority?exists>
			getObjByName('priority.id').value='${activity.priority.id?if_exists}';
		</#if>
	}
	
	//弹出负责人查询模态窗体
	function persons_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
			document.forms[0].elements["persons.id"].value = result[0];
	   		document.forms[0].elements["activity.persons"].value = result[2];
		}
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
        //主题
        if(!textfieldCheck_theme()){
	        getObjByName('activity.theme').focus();
			return false;
		}
        //对象客户 
        if(!textfieldCheck_customers()){
	        getObjByName('activity.customers').focus();
			return false;
		}
        //活动目的  
        if(!textfieldCheck_target()){
	        getObjByName('activity.target').focus();
			return false;
		}
		//活动开始结束时间
		if(getObjByName('activity.beginTime').value ==''){
		 		alert("${action.getText('activity.beginTime.not.null')}");
		 		getObjByName('activity.beginTime').focus();
		      	return false;   
		}else{
		 	if(!isDate('activity.beginTime')){
				alert("${action.getText('activity.beginTime.dateFormate.error')}");
				getObjByName('activity.beginTime').value="";
	      	    getObjByName('activity.beginTime').focus();
				return false;
			} 
			if(getObjByName('activity.endTime').value != ''){
			 		if(!isDate('activity.endTime')){
						alert("${action.getText('activity.endTime.dateFormate.error')}");
						getObjByName('activity.endTime').value="";
			      	    getObjByName('activity.endTime').focus();
						return false;
				}else{
					if(isDateLessThenOldDate(getObjByName('activity.beginTime').value,getObjByName('activity.endTime').value)){
						alert('${action.getText('activity.endTime.earlyError')}');
						getObjByName('activity.endTime').value="";
			       		getObjByName('activity.endTime').focus();
						return false;
					}//isDateLessThenCurrent
				}//else
			}//getObjByName('activity.endTime').value 
		}
        //活动地点
        if(!textfieldCheck_place()){
	        getObjByName('activity.place').focus();
			return false;
		}
		//活动类别
		if(!selectCheck_selectCheckActivityType()){
		getObjByName('activityType.id').focus();
		    return false;
		}
		//状态
		if(!selectCheck_selectCheckActivityStatus()){
		getObjByName('status.id').focus();
		    return false;
		}
		//进展情况(阶段)
		if(!selectCheck_selectCheckActivityProgress()){
		getObjByName('progress.id').focus();
		    return false;
		}
		//优先级别
		if(!selectCheck_selectCheckActivityPriority()){
		getObjByName('priority.id').focus();
		    return false;
		}
		//负责人
	    if(getObjByName('activity.persons').value==""){
	        alert('${action.getText('activity.persons.not.null')}');
	        return false;
	    }
		//验证费用为double类型
		if(getObjByName('activity.fee').value!=''){
	     	if(!isDoubleNumber("activity.fee")){
				alert("${action.getText('activity.fee.must.be.double')}");
				return false;
			}
	     }
		//活动内容
	    if(!textareaCheck_content()){
		    getObjByName('activity.content').focus();
		    return false;
	    }
		return true;
	}
</script>
</@htmlPage>
