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

<@htmlPage title="${action.getText('合作伙伴项目组成员')}">
<@ww.form namespace="'/projectInfo'" name="'projectInfo'" action="'saveProjectContactArchives'" method="'post'">
	<@ww.token name="saveProjectContactArchivesToken"/>
    <@inputTable>
    	<@ww.hidden name="'projectInfo.id'" value="'${projectInfo.id?if_exists}'"/>
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	<#if projectContactArchives.id?exists>
    		<@ww.hidden name="'projectContactArchives.id'" value="#{projectContactArchives.id}"/>
	    	<@ww.hidden name="'contactArchives.id'" value="'#{projectContactArchives.contactArchives.id?if_exists}'"/>
	 	<#else>
    		<@ww.hidden name="'contactArchives.id'" value="''"/>
	 	</#if>
	
    
    <tr>
    <!--项目组成员-->
		<td align="right" valign="top">
       		<span class="required">*</span>
       		<label class="label">${action.getText('项目组成员')}:</label>
     	</td>
     	<td>
	     	<#if projectContactArchives.contactArchives?exists>
	     	<input type="text" name="contactArchives.name" class="underline"  value="${projectContactArchives.contactArchives.name?if_exists}" maxlength="140" size="20" disabled="true"/>
	     	<#else>
	     	<input type="text" name="contactArchives.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
	     	</#if>
	   		
			<a onClick="contactArchive_OpenDialog();">
				<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
			</a>
		</td>
		<@ww.select label="'${action.getText('业务属性')}'" 
			name="'businessType.id'" 
			value="'${req.getParameter('businessType.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allBusinessType"
			required="true"
			emptyOption="false" 
			>
		</@ww.select>
		<script language="javascript">
			<#if projectContactArchives.businessType?exists>
				getObjByName('businessType.id').value = '${projectContactArchives.businessType.id?if_exists}';
			</#if>
		</script>
    </tr>
    <tr>
			<!--备注-->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('职责说明')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="projectContactArchives.outline" rows="4" cols="150" >${projectContactArchives.outline?if_exists}</textarea>
	        </td>
			<!---->
		</tr>
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
			<#if projectContactArchives.id?exists>
			<@redirectButton value="${action.getText('newNext')}" 
				url="${req.contextPath}/projectInfo/editProjectContactArchives.html?projectInfo.id=${projectInfo.id?if_exists}"/>
			<#else>
			<@redirectButton name="newNext" value="${action.getText('newNext')}" 
				url="${req.contextPath}/projectInfo/editProjectContactArchives.html?projectInfo.id=${projectInfo.id?if_exists}"/>
				<script language="JavaScript" type="text/JavaScript"> 
					getObjByName("newNext").disabled="true";
				</script>
			</#if>
		
		<input class="button" type="button" value="${action.getText('close')}"  onclick="closeThis();">
    </@buttonBar>
    

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
		
		
	}
	
	
	
	
	function contactArchive_OpenDialog(){
		var  url = "${req.contextPath}/customerRelationship/listContactArchives.html?backVisitFlag=backVisit&con_Project.id="+getObjByName('projectInfo.id').value;
		popupModalDialog(url, 800, 600, setContactArchives);
	}
	function setContactArchives(result) {
		if (null != result) {
		 	document.forms[0].elements["contactArchives.id"].value = result[0];	
		 	document.forms[0].elements["contactArchives.name"].value = result[1];
		}
	}
	
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		/* 验证项目组成员*/
		if('' == getObjByName('contactArchives.name').value){
			alert("${action.getText('请选择项目组成员')}");
     		return false;
		}
		/* 业务属性*/
		if('' == getObjByName('businessType.id').value){
			alert("${action.getText('请选择业务属性')}");
     		return false;
		}
	
		return true;
	}
	
</script>
</@htmlPage>
