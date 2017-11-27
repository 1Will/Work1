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
<@ww.form name="'listForm'" action="'saveHistoryTask'" method="'post'">
	<@ww.token name="saveHistoryTaskToken"/>
	<#if historyTask.id?exists>
		<@ww.hidden name="'historyTask.id'" value="#{historyTask.id?if_exists}"/>
 	</#if>
	<@inputTable>
	<tr>
        <@textfield label="${action.getText('任务编码')}" name="historyTask.code" anothername="code" value="${historyTask.code?if_exists}" required="false" cssClass="underline" maxlength="20"/>
        <@textfield label="${action.getText('任务名称')}" name="historyTask.name" anothername="name" value="${historyTask.name?if_exists}" required="false" cssClass="underline" maxlength="20"/>
        <@textfield label="${action.getText('审批人')}" name="historyTask.personnelFilesName" anothername="personnelFilesName" value="${historyTask.personnelFilesName?if_exists}" required="false" cssClass="underline" maxlength="20"/>	
 	</tr>
	<tr>
		<@pp.datePicker 
			label="'${action.getText('审批日期')}'" 
			name="'historyTask.approveDate'" 
   			value="'${(historyTask.approveDate?string('yyyy-MM-dd'))?if_exists}'"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"
			maxlength="10"/>
		<td align="right">
	       <label for="" class="label">
	           ${action.getText('是否同意')}:
	       </label>
	    </td>
		<td align="left">
        	<input type="radio" id="agreeOrNot0" name="historyTask.agreeOrNot" value="1"/>是
        	<input type="radio" id="agreeOrNot1" name="historyTask.agreeOrNot" value="0"/>否
		</td>
		<script language="javascript">
    		<#if historyTask.agreeOrNot?exists>
    			if(${historyTask.agreeOrNot}=='1')
    			{
    				getObjByName('agreeOrNot0').checked = true;
    			}
    			else if(${historyTask.agreeOrNot}=='0')
    			{
    				getObjByName('agreeOrNot1').checked = true;
    			}
    		<#else>
    		   getObjByName('agreeOrNot0').checked = true;
	     	</#if>
    	</script>
	</tr>
	<tr>
		<@textarea label="不同意原因" name="notAgreeReason" colspan="5" rows="3" 
		           value="${historyTask.notAgreeReason?if_exists}"/>
		    <script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("notAgreeReason").cols =width;
			</script>
	</tr>
	</@inputTable>
	<@buttonBar>
		<@redirectButton value="${'返回'}" url="${req.contextPath}/historyTask/listHistoryTask.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>
