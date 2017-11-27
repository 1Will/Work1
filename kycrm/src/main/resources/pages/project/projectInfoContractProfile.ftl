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

<@htmlPage title="${action.getText('projectInfo.proCon')}">
<@ww.form namespace="'/projectInfo'" name="'projectInfo'" action="'saveProCon'" method="'post'">
	<@ww.token name="saveProjectInfoConToken"/>
    <@inputTable>
    	<@ww.hidden name="'businessTypeId'" value="''"/>
    	<@ww.hidden name="'projectInfo.id'" value="'${projectInfoId?if_exists}'"/>
    	<@ww.hidden name="'customerInfo.id'" value="'${customerInfoId?if_exists}'"/>
    	<@ww.hidden name="'contactArchives.id'" value="''"/>
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	<#if projectInfoContract.id?exists>
    		<@ww.hidden name="'projectInfoContract.id'" value="#{projectInfoContract.id}"/>
	 	</#if>
	
    
  
    
    <tr>
    <!--项目联系人-->
    <td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('projectInfoContract.proCon')}:</label>
	     	</td>
	     	<td>
	     	<#if projectInfoContract.contactArchives?exists>
	     	<input type="text" name="contactArchives.name" class="underline"  value="${projectInfoContract.contactArchives.name?if_exists}" maxlength="140" size="20" disabled="true"/>
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
			<#if projectInfoContract.businessType?exists>
				getObjByName('businessType.id').value = '${projectInfoContract.businessType.id?if_exists}';
			<#else>
				getObjByName('businessType.id').value = '523';
			</#if>
		</script>
    </tr>
     <tr>
			<!--备注-->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('proConOutline')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="projectInfoContract.outline" rows="4" cols="150" >${projectInfoContract.outline?if_exists}</textarea>
	        </td>
		</tr>
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		
		<#-- 继续新建按钮   -->
			<#if projectInfoContract.id?exists>
			<@redirectButton value="${action.getText('newNext')}" 
				url="${req.contextPath}/projectInfo/editProCon.html?projectInfo.id=${projectInfoId?if_exists}&customerInfo.id=${customerInfoId?if_exists}"/>
			<#else>
			<@redirectButton name="newNext" value="${action.getText('newNext')}" 
				url="${req.contextPath}/projectInfo/editProCon.html"/>
			<script language="JavaScript" type="text/JavaScript"> 
			getObjByName("newNext").disabled="true";
			</script>
			</#if>
		</#if>
		
		<input class="button" type="button" value="${action.getText('close')}"  onclick="closeThis();">
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
		
		
	}
	
	
	
	function contactArchive_OpenDialog(){
			var  url = "${req.contextPath}/customerRelationship/listContactArchives.html?readOnly=${req.getParameter('readOnly')?if_exists}&projectId=${projectInfoId?if_exists}&backVisitFlag=backVisit&projectInfoCus=${projectInfoCus?if_exists}";
			popupModalDialog(url, 800, 600, creatorSelectorHandlerContactArchives);
	}
	function creatorSelectorHandlerContactArchives(result) {
		if (null != result) {
		 	document.forms[0].elements["contactArchives.id"].value = result[0];	
		 	document.forms[0].elements["contactArchives.name"].value = result[1];
		}
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		/* 验证项目组成员*/
		if('' == getObjByName('contactArchives.name').value){
			alert("${action.getText('contactArchives.name.required')}");
     		return false;
		}
		/* 业务属性*/
		if('' == getObjByName('businessType.id').value){
			alert("${action.getText('businessType.name.required')}");
     		return false;
		}
	
		return true;
	}
	
</script>
</@htmlPage>
