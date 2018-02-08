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

<#-- $Id: additionalInfoProfile.ftl 2009-12-08 10:00:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />
<@framePage>
<@ww.form name="'listForm'" namespace="'/orgstructure'" action="'saveEditDepartmentLader'" method="'post'">
	<@ww.token name="saveDepartmentProfileToken"/>
	<@ww.hidden id="'isSaved'" name="'isSaved'" value=""/>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	 <#if additionalInformation.department?exists >
            <@ww.hidden name="'department.id'" value="#{additionalInformation.department.id}"/>
        </#if>
         <#if additionalInformation.id?exists>
            <@ww.hidden name="'additionalInformation.id'" value="#{additionalInformation.id}"/>
         </#if>
         <@ww.hidden name="'leader.id'" value="'${req.getParameter('leader.id')?if_exists}'"/>
         <@ww.hidden name="'manageLeader.id'" value="'${req.getParameter('manageLeader.id')?if_exists}'"/>
	<@inputTable>
     
 	<tr>
 	    <td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('部门负责人')}:</label>
	     	</td>
	     	<td>
	     		<#if additionalInformation.leader?exists>
		   		<input type="text" name="additionalInformation.leader" class="underline"  value="${additionalInformation.leader.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="additionalInformation.leader" class="underline"  value="${req.getParameter('additionalInformation.leader')?if_exists}" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="leader_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			
		 <td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('公司业务分管领导')}:</label>
	     	</td>
	     	<td>
	     		<#if additionalInformation.manageLeader?exists>
		   		<input type="text" name="additionalInformation.manageLeader" class="underline"  value="${additionalInformation.manageLeader.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="additionalInformation.manageLeader" class="underline"  value="${req.getParameter('additionalInformation.manageLeader')?if_exists}" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="manageLeader_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
    </tr>
    
	</@inputTable>
	<#if !(action.isReadOnly())>
		<@buttonBar>
	        <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
	    </@buttonBar>
    </#if>
</@ww.form>
<script>
//弹出业务员查询模态窗体
	function leader_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
		    getObjByName('leader.id').value=result[0];
	   		getObjByName('additionalInformation.leader').value=result[2];
		}
	}
	
	
  function manageLeader_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorSelectorHandlerOne);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandlerOne(result) {
		if (null != result) {
		    getObjByName('manageLeader.id').value=result[0];
	   		getObjByName('additionalInformation.manageLeader').value=result[2];
		}
	}


function storeValidation(){
	if(getObjByName('additionalInformation.leader').value !=''){
        if(!isValidLength(document.forms[0], "additionalInformation.leader", null, 50)){
			alert("${action.getText('部门负责人最大长度为50个字符')}");
			return false;
		}
	}
	if(getObjByName('additionalInformation.manageLeader').value !=''){
        if(!isValidLength(document.forms[0], "additionalInformation.manageLeader", null, 50)){
			alert("${action.getText('部门负责人最大长度为50个字符')}");
			return false;
		}
	}
    
	parent.frames["dTreeFrame"].location.reload();
	return true;
}
</script>
</@framePage>
