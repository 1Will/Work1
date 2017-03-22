<#--
	Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: custContactReportSearch.ftl 2010-04-06 wliu $ -->

<@inputTable>
	<tr>
        <@textfield label="${action.getText('编码')}" name="historyTask.code" anothername="code" value="${req.getParameter('historyTask.code')?if_exists}" required="false" cssClass="underline" maxlength="20"/>
        <@textfield label="${action.getText('名称')}" name="historyTask.name" anothername="name" value="${req.getParameter('historyTask.name')?if_exists}" required="false" cssClass="underline" maxlength="20"/>
        <#--<@textfield label="${action.getText('审批人')}" name="historyTask.personnelFilesName" anothername="personnelFilesName" value="${req.getParameter('historyTask.personnelFilesName')?if_exists}" required="false" cssClass="underline" maxlength="20"/>-->	
 	</tr>
	<tr>
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
function checkInvalidParms()
{
	return true;
}
</script>