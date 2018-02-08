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

<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('title')}">
<@ww.form namespace="'/survey'" name="'surveyProfile'" action="'saveSurvey'" method="'post'">
	<@ww.token name="saveSurveyToken"/>
    <@inputTable>
		<@ww.hidden name="'persons.id'" value="'${req.getParameter('persons.id')?if_exists}'"/>
    	<#if survey.id?exists>
    		<@ww.hidden name="'survey.id'" value="#{survey.id?if_exists}"/>
	 	</#if>
	 	<tr>
            <@textfield label="${action.getText('survey.code')}" name="survey.code" anothername="code" value="${survey.code?if_exists}" required="false" cssClass="underline" maxlength="20" disabled="true"/>
 			<@pp.datePicker 
				label="'${action.getText('survey.surveyTime')}'" 
				id="'survey.surveyTime'" 
				name="'survey.surveyTime'" 
	   			value="'${(survey.surveyTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>	
			<script language="javascript">
					var date = new Date();
					if(getObjByName("survey.surveyTime").value==''){
						getObjByName("survey.surveyTime").value = date.format("yyyy-MM-dd");
					}
			</script>     
            <@textfield label="${action.getText('survey.surveyTarget')}" name="survey.surveyTarget" anothername="surveyTarget" value="${survey.surveyTarget?if_exists}" required="true" cssClass="underline" maxlength="20"/> 
		</tr>	
		<tr>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('survey.persons')}:</label>
	     	</td>
	     	<td>
	     		<#if survey.persons?exists>
		   		<input type="text" name="survey.persons" class="underline"  value="${survey.persons.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="survey.persons" class="underline"  value="${req.getParameter('survey.persons')?if_exists}" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="persons_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td> 	
		</tr>
		<tr>
		    <@textarea name="survey.theme" label="${action.getText('survey.theme')}" anothername="theme" maxLength="500" required="true" value="${survey.theme?if_exists}"/>
		</tr>
		<tr>
		    <@textarea name="survey.content" label="${action.getText('survey.content')}" anothername="content" maxLength="500" required="true" value="${survey.content?if_exists}"/>
		</tr>
		<tr>
		    <@textarea name="survey.feedBack" label="${action.getText('survey.feedBack')}" anothername="feedBack" maxLength="500" required="false" value="${survey.feedBack?if_exists}"/>
		</tr>
		<tr>
		    <@textarea name="survey.summary" label="${action.getText('survey.summary')}" anothername="summary" maxLength="500" required="false" value="${survey.summary?if_exists}"/>
		</tr>
		<tr>
		    <@textarea name="survey.remark" label="${action.getText('survey.remark')}" anothername="remark" maxLength="500" required="false" value="${survey.remark?if_exists}"/>
		</tr>
    </@inputTable>
    <@buttonBar>
    <#if !(action.isReadOnly())>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
    </#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/survey/listSurvey.html"/>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
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
	   		document.forms[0].elements["survey.persons"].value = result[2];
		}
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
        //调查时间
		if(getObjByName('survey.surveyTime').value ==''){
		 		alert("${action.getText('survey.surveyTime.not.null')}");
		 		getObjByName('survey.surveyTime').focus();
		      	return false;   
		}else{
			if(!isDate('survey.surveyTime')){
					alert("${action.getText('survey.surveyTime.dateFormate.error')}");
					getObjByName('survey.surveyTime').value="";
		      	    getObjByName('survey.surveyTime').focus();
					return false;
				} 
		}
        //调查对象 
        if(!textfieldCheck_surveyTarget()){
	        getObjByName('survey.surveyTarget').focus();
			return false;
		}
		//负责人
	    if(getObjByName('survey.persons').value==""){
	        alert('${action.getText('survey.persons.not.null')}');
	        return false;
	    }
        //调查主题 
        if(!textareaCheck_theme()){
	        getObjByName('survey.theme').focus();
			return false;
		}
		//活动内容
	    if(!textareaCheck_content()){
		    getObjByName('survey.content').focus();
		    return false;
	    }
		return true;
	}
</script>
</@htmlPage>
