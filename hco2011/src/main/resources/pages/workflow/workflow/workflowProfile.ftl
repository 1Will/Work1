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
<@htmlPage title="${action.getText('工作流维护')}">
<@ww.form name="'listForm'" action="'saveWorkflow'" method="'post'">
<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="editWorkflowToken"/>
    <@ww.hidden name="'workflow.id'" value="${workflow.id?if_exists}"/>
	<@inputTable>
		<tr>
			<@textfield label="${action.getText('工作流编码')}" name="workflow.code" value="${workflow.code?if_exists}" anothername="code"
			            required="true"/>
			<@textfield label="${action.getText('工作流名称')}" name="workflow.name" value="${workflow.name?if_exists}"  anothername="name"
			            required="true"/>
			<@ww.select label="'${action.getText('流程选择')}'" 
						name="'flow.id'" 
						value="'${req.getParameter('flow.id')?if_exists}'"
						listKey="id"
						listValue="name"
						list="allFlow"
						emptyOption="false" 
						disabled="false">
			</@ww.select>
		</tr>
		<tr>
			<@ww.select label="'${action.getText('适用类型')}'" 
						name="'affectObject'" 
						value="'${req.getParameter('affectObject')?if_exists}'"
						listKey="id"
						listValue="name"
						list="allAffectObject"
						emptyOption="false" 
						disabled="false">
			</@ww.select>

		    <td align="right">
		       <label for="" class="label">
		           ${action.getText('是否启用')}:
		       </label>
		    </td>
			<td align="left">
	        	<input type="radio" id="openOrNot0" name="workflow.openOrNot" value="0"/>是
	        	<input type="radio" id="openOrNot1" name="workflow.openOrNot" value="1"/>否
			</td>
			<script language="javascript">
        		<#if workflow.openOrNot?exists>
        			if(${workflow.openOrNot}=='0')
        			{
        				getObjByName('openOrNot0').checked = true;
        			}
        			else if(${workflow.openOrNot}=='1')
        			{
        				getObjByName('openOrNot1').checked = true;
        			}
		     	</#if>
        	</script>
		</tr>
		<tr>
			<@textarea label="备注" name="remark" colspan="5" rows="4" cols="150" 
			           value="${workflow.remark?if_exists}"/>
		</tr>
	</@inputTable>
	<@buttonBar>
	<#if !(action.isReadOnly())>
		<@vsubmit name="save" value="'${'保存'}'"  onclick="'return storeValidation();'"/>
		</#if>
		<@redirectButton value="${'返回'}" url="${req.contextPath}/workflow/listWorkflow.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </@buttonBar>
</@ww.form>
<script language="javascript">
	window.onload=function()
	{
		<#if workflow.affectObject?exists>
			getObjByName('affectObject').value='${workflow.affectObject.id?if_exists}';
		</#if>
		<#if workflow.flow?exists>
			getObjByName('flow.id').value='${workflow.flow.id?if_exists}';
		</#if>
	}
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
	    //工作流编码
	    if(!textfieldCheck_code()){
	        getObjByName('workflow.code').focus();
			return false;
		}
	    //工作流名称
	    if(!textfieldCheck_name()){
	        getObjByName('workflow.name').focus();
			return false;
		}
	}
</script>
</@htmlPage>
