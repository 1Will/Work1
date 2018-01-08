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

<#-- $Id: ceProfile.ftl 2009-11-27 14:49:35Z wliu $ -->

<#include "../../includes/macros.ftl" />
<@htmlPage title="${action.getText('流程维护')}">
<@ww.form name="'listForm'" action="'saveFlow'" method="'post'">
<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="editFlowToken"/>
	<#if flow.id?exists>
		<@ww.hidden name="'flow.id'" value="#{flow.id?if_exists}"/>
 	</#if>
	<@inputTable>
		<tr>
			<@ww.select label="'${action.getText('流程编码')}'" 
				name="'flow.flowCode'" 
				value="'${flow.flowCode?if_exists}'"
				listKey="id"
				listValue="name"
				list="allFlowCode"
				emptyOption="true"
				required="true" 
				disabled="false">
			</@ww.select>
			<#--
			<@textfield label="${action.getText('流程编码')}" name="flow.code" value="${flow.flowCode?if_exists}" 
			            required="true"/>-->
			
			<@textfield label="${action.getText('流程名称')}" name="flow.name" value="${flow.name?if_exists}" anothername="name"
			            required="true"/>
			<td align="right">
		       <label for="" class="label">
		           ${action.getText('是否启用')}:
		       </label>
		    </td>
			<td align="left">
	        	<input type="radio" id="openOrNot0" name="flow.openOrNot" value="0"/>是
	        	<input type="radio" id="openOrNot1" name="flow.openOrNot" value="1"/>否
			</td>
			<script language="javascript">
        		<#if flow.openOrNot?exists>
        			if(${flow.openOrNot}=='0')
        			{
        				getObjByName('openOrNot0').checked = true;
        			}
        			else if(${flow.openOrNot}=='1')
        			{
        				getObjByName('openOrNot1').checked = true;
        			}
		     	</#if>
        	</script>
		</tr>
		<tr>
			<@textarea label="备注" name="flow.remark" colspan="5" rows="4" cols="150"
			           value="${flow.remark?if_exists}"/>
		</tr>
	</@inputTable>
	<@buttonBar>
	<#if !(action.isReadOnly())>
		<@vsubmit name="save" value="'${'保存'}'" onclick="'return storeValidation();'"/>
	</#if>
		<@redirectButton value="${'返回'}" url="${req.contextPath}/flow/listFlow.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </@buttonBar>
</@ww.form>
<script language="javascript">
//window.onload=function(){
//}
<#if flow.department?exists>
	getObjByName('flow.department').value='${flow.department.id?if_exists}';
</#if>
<#if flow.flowCode?exists>
	getObjByName('flow.flowCode').value='${flow.flowCode.id?if_exists}';
<#else>
	<#if req.getParameter('flow.flowCode.id')?exists>
	getObjByName('flow.flowCode').value='${req.getParameter('flow.flowCode.id')?if_exists}';
	</#if>
</#if>
//保存前给隐藏域赋值和验证字段
function storeValidation(){
    //流程编码
    if(getObjByName('flow.flowCode').value == ""){
        getObjByName('flow.flowCode').focus();
        alert("请选择流程编码！");
		return false;
	}
    //流程名称
    if(getObjByName('flow.name').value == ""){
        getObjByName('flow.name').focus();
        alert("请填写流程名称！");
		return false;
	}
	
}
</script>
<#if flow.id?exists>
<ul id="beautytab">
	<li>
		<a id="id" onclick="activeTab(this);" class="selectedtab" 
		           href='${req.contextPath}/point/listPoint.html?flow.id=${flow.id?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >
		    ${action.getText('流程节点')}
		</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/point/listPoint.html?flow.id=${flow.id?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" 
        marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>
 </#if>
</@htmlPage>
