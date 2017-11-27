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

<#include "../../includes/hco2011.ftl" />

<@inputTable>
	<tr>
   	    <@ww.textfield label="'${action.getText('工作流名称')}'" 
		                name="'workflow.name'" 
		                value="'${req.getParameter('workflow.name')?if_exists}'" 
		                cssClass="'underline'"/>
		
   	    <td align="right"><label for="" class="label">${action.getText('是否启用')}:</label></td>
		<td align="left">
        	<input type="radio" id="openOrNot0" name="workflow.openOrNot" value="0">是
        	<input type="radio" id="openOrNot1" name="workflow.openOrNot" value="1">否
        	<script language="javascript">
      			<#if req.getParameter('workflow.openOrNot')?exists>
      			    <#if req.getParameter('workflow.openOrNot')=='0'>
	 					getObjByName('openOrNot0').checked = true;
	 				<#else>
	 				    getObjByName('openOrNot1').checked = true;
	      			</#if>
      			<#else>
 				    getObjByName('openOrNot0').checked = true;
      			</#if>
			</script>
		</td>
	</tr>
	<tr>
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
<#-- 检查收索条件中是否有下拉列表，如果有且值为-1，将其值清空 -->
<#-- 使收索条件中去除该条件 -->
function checkInvalidParms()
{
	return true;
}

<#-- 当仅查询失效被选中时，强制选中不启用 
jgetObjByName("#searchWorkflow_onlyDisabled").click(function()
{
    if(jgetObjByName("#searchWorkflow_onlyDisabled").attr("checked") == true)
    {
        jgetObjByName("#openOrNot1").attr("checked",true);
    }
});
-->
<#-- 当启用被选中时，强制取消仅查询失效的选中状态 
jgetObjByName("#openOrNot0").click(function()
{
    jgetObjByName("#searchWorkflow_onlyDisabled").attr("checked",false);
});-->
</script>