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
<@htmlPage title="${action.getText('请假单主管审核')}">
<@ww.form name="'listForm'" action="'editToDoTask_'" method="'post'">
	<@ww.token name="editToDoTask_Token"/>
	<@inputTable>
		<tr>
			<@ww.textfield label="'编号'" name="'code'" value="'qjd-001'" cssClass="'underline'" required="true"/>
			<@pp.datePicker 
				label="'创建日期'" 
				name="'createTime'" 
	   			value="'2011-02-15'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"/>
			<@ww.textfield label="'经办人'" name="'applyPerson'" value="'陈晓松'" cssClass="'underline'"/>
		</tr>
		<tr>
		    <td align="right"><label for="" class="label">${action.getText('是否同意')}:</label></td>
			<td align="left">
	        	<input type="radio" id="agree" name="advisory.isNoBack" value="0">是
	        	<input type="radio" id="notAgree" name="advisory.isNoBack" value="1" checked>否
	        	<script language="javascript">
		     		if(getObjByName('agree').value=='true'){
		     			getObjByName('agree').checked = true;
		     		}else if(getObjByName('notAgree').value=='false'){
		     			getObjByName('notAgree').checked = true;
		     		}
				</script>
			</td>
		</tr>
		<tr>
			<@textarea label="不同意原因" name="notAgreeReason" value="原因不明确" colspan="5" rows="3" cols="100"/>
		</tr>
	</@inputTable>
	<@buttonBar>
		<@redirectButton value="${'返回'}" url="${req.contextPath}/toDoTask_/listToDoTask_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>
<ul id="beautytab">
	<li>
		<a id="id" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/leaveBill_/editLeaveBill_.html' target="frame" >${action.getText('申请信息')}</a>
	</li>
	<li>
		<a id="id2" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/historyTask_/listFlowHistory_.html' target="frame" >${action.getText('审批流程')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/leaveBill_/editLeaveBill_.html" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>