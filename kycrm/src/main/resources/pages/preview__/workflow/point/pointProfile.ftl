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
<@htmlPage title="${action.getText('流程维护')}">
<@ww.form name="'listForm'" action="'editToDoTask_'" method="'post'">
	<@ww.token name="editToDoTask_Token"/>
	<@inputTable>
		<tr>
			<@ww.textfield label="'节点编码'" name="'code'" value="'QJD'" 
			               cssClass="'underline'" required="true"/>
			<@ww.textfield label="'节点名称'" name="'code'" value="'请假单'" 
			               cssClass="'underline'" required="true"/>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('负责人')}:</label>
	     	</td>
	     	<td>
	     		<input type="text" name="voc.salesman" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				<a onClick="persons_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		<tr>
		    <@ww.select label="'前一节点'"
						required="false"
						name="'taskName'" 
						value="${req.getParameter('taskName')?if_exists}" 
						headerKey="id"
						headerValue="name"
					    list="{
					    	'开始节点',
							'组长审核',
							'部门经理审核',
							'人事经理审核',
							'总经理审批'
							}"
					    emptyOption="false" 
					    disabled="false">
		    </@ww.select>
		    <@ww.select label="'后一节点'"
						required="false"
						name="'taskName'" 
						value="${req.getParameter('taskName')?if_exists}" 
						headerKey="id"
						headerValue="name"
					    list="{
							'组长审核',
							'部门经理审核',
							'人事经理审核',
							'总经理审批',
							'结束节点'
							}"
					    emptyOption="false" 
					    disabled="false">
		    </@ww.select>
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
			<@textarea label="备注" name="remark" value="" colspan="5" rows="3" 
			           value="请假单流程，提供给请假单审批使用！"/>
			           <script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("remark").cols =width;
			</script>
		</tr>
	</@inputTable>
	<@buttonBar>
		<@vsubmit name="save" value="'${'保存'}'"/>
		<@redirectButton value="${'返回'}" url="${req.contextPath}/toDoTask_/listToDoTask_.html"/>
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
</script>
</@htmlPage>
