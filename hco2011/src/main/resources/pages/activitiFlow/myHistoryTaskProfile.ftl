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

<#include "../includes/macros.ftl" />
<@htmlPage title="${action.getText('审批维护')}">
<@ww.form name="'listForm'" action="'saveMyHistoryTask'" method="'post'">
	<@ww.token name="saveMyHistoryTaskToken"/>
    <@ww.hidden name="'historyTaskinst.id'" value="${historyTaskinst.id?if_exists}"/>
	<@inputTable>
		<tr>
		           <!--提交ren -->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('提交人')}:
        		</label>
        	</td>
		<td ><label class="label">
        			${historyTaskinst.submitPer.name?if_exists}
        		</label></td>
		       <!--所属部门-->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('所属部门')}:
        		</label>
        	</td>
		<td ><label class="label">
        		${departmentName?if_exists}
        		</label></td>
		</tr>
		<tr>
		           <!--任务名称 -->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('任务名称')}:
        		</label>
        	</td>
		<td ><label class="label">
		<#if historyTaskinst.linkHref?exists>
		<a href="javascript: openNewWindow('${req.contextPath}/${historyTaskinst.linkHref?if_exists}')">${historyTaskinst.name?if_exists}</a>
		<#else>
		${historyTaskinst.submitPer.name?if_exists}
		</#if>
        			
        		</label></td>
		       <!--l流程类型-->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('流程类型')}:
        		</label>
        	</td>
		<td ><label class="label">
        		${historyTaskinst.flow.name?if_exists}
        		</label></td>
		</tr>
		<tr>
			           
			           <!--审批意见-->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('提交内容')}:
        		</label>
        	</td>
	        <td colspan="4">
	        	<textarea name="valueRemark" rows="4" cols="150" disabled="disabled" >${historyTaskinst.content?if_exists}</textarea>
	        </td>
		</tr>
		<tr>
			<@ww.select 
	    		label="'${action.getText('审批结果')}'"
				required="true"
				name="'result.id'" 
				value="${req.getParameter('result.id')?if_exists}" 
				listKey="id"
				listValue="name"
			    list="allResultTypes"
			    emptyOption="true" 
		    	disabled="true"/>
		    	<script language="javascript">
<#if runPoint?exists>
<#if runPoint.result?exists>
getObjByName('result.id').value='${runPoint.result.id}'
</#if>
</#if>
</script>
		</tr>
		<tr>
			<!--审批意见-->
			<td align="right" valign="top">
        		<label class="label">
        			<font color='red'>*</font>${action.getText('审批意见')}:
        		</label>
        	</td>
	        <td colspan="4">
	        	<textarea name="remark" rows="4" cols="150" disabled="disabled" >${runPoint.remark?if_exists}
		        </textarea>
	        </td>
		</tr>
	</@inputTable>
	<@buttonBar>
		<@redirectButton value="${'返回'}" url="${req.contextPath}/activitiFlow/listMyHistoryTask.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>
<ul id="beautytab">
	<li>
		<a id="runPoint" onclick="activeTab(this);"  href='${req.contextPath}/activitiFlow/listRunPoint.html?historyTask=historyTask&bussnessId=#{historyTaskinst.bussnessId}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('审批人')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/activitiFlow/listRunPoint.html?historyTask=historyTask&bussnessId=#{historyTaskinst.bussnessId}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="60%"/>
