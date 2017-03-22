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

<#include "../../../includes/macros.ftl" />
<@htmlPage title="${action.getText('工作流维护')}">
<@ww.form name="'listForm'" action="'editToDoTask_'" method="'post'">
	<@ww.token name="editToDoTask_Token"/>
	<@inputTable>
		<tr>
			<@ww.textfield label="'工作流编码'" name="'code'" value="'QJD'" 
			               cssClass="'underline'" required="true"/>
			<@ww.textfield label="'工作流名称'" name="'code'" value="'请假单'" 
			               cssClass="'underline'" required="true"/>
			<@ww.select label="'流程名称'"
						required="false"
						name="'taskName'" 
						value="${req.getParameter('taskName')?if_exists}" 
						headerKey="id"
						headerValue="name"
					    list="{
					    	'',
							'研发部请假单',
							'销售部请假单',
							'研发部公出单',
							'研发部加班单'
							}"
					    emptyOption="false" 
					    disabled="false"/>
		</tr>
		<tr>
		    <@ww.select label="'作用范围'"
						required="false"
						name="'taskName'" 
						value="${req.getParameter('taskName')?if_exists}" 
						headerKey="id"
						headerValue="name"
					    list="{
					    	'',
							'公出单',
							'请假单',
							'调休单'
							}"
					    emptyOption="false" 
					    disabled="false"/>
		    <td align="right">
		       <label for="" class="label">
		           ${action.getText('是否启用')}:
		       </label>
		    </td>
			<td align="left">
	        	<input type="radio" id="agree" name="advisory.isNoBack" value="0" checked />是
	        	<input type="radio" id="notAgree" name="advisory.isNoBack" value="1"/>否
			</td>
		</tr>
		<tr>
			<@textarea label="备注" name="remark" value="" colspan="5" rows="3" cols="100" 
			           value="请假单流程，提供给请假单审批使用！"/>
		</tr>
	</@inputTable>
	<@buttonBar>
		<@vsubmit name="save" value="'${'保存'}'"/>
		<@redirectButton value="${'返回'}" url="${req.contextPath}/toDoTask_/listToDoTask_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>