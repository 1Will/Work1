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

<@htmlPage title="${action.getText('projectInfo.proPer')}">
<@ww.form namespace="'/projectInfo'" name="'projectInfo'" action="'saveProPer'" method="'post'">
	<@ww.token name="saveProjectInfoToken"/>
    <@inputTable>
    	<@ww.hidden name="'businessTypeId'" value="''"/>
    	<@ww.hidden name="'projectInfo.id'" value="'${projectInfoId?if_exists}'"/>
    	<@ww.hidden name="'proPerson.id'" value="''"/>
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	<#if projectInfoPersonnels.id?exists>
    		<@ww.hidden name="'projectInfoPersonnels.id'" value="#{projectInfoPersonnels.id}"/>
	 	</#if>
	
    
  
    
    <tr>
    <!--项目组成员-->
    <td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('projectInfoPersonnels.proPerson')}:</label>
	     	</td>
	     	<td>
	     	<#if projectInfoPersonnels.proPerson?exists>
	     	<input type="text" name="proPerson.name" class="underline"  value="${projectInfoPersonnels.proPerson.name?if_exists}" maxlength="140" size="20" disabled="true"/>
	     	<#else>
	     	<input type="text" name="proPerson.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
	     	</#if>
		   		
				<a onClick="salesman_OpenDialog();">
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
			<#if projectInfoPersonnels.businessType?exists>
				getObjByName('businessType.id').value = '${projectInfoPersonnels.businessType.id?if_exists}';
			</#if>
		</script>
    </tr>
    <tr>
			<!--备注-->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('proPersonOutline')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="projectInfoPersonnels.outline" rows="3" cols="120" >${projectInfoPersonnels.outline?if_exists}</textarea>
	        </td>
			<!---->
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
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
			document.forms[0].elements["proPerson.id"].value = result[0];
	   		document.forms[0].elements["proPerson.name"].value = result[2];		 	
		}
	}
	
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		/* 验证项目组成员*/
		if('' == getObjByName('proPerson.name').value){
			alert("${action.getText('proPerson.name.required')}");
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
