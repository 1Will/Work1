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
<@ww.form name="'listForm'" action="'saveToDoTask'" method="'post'">
	<@ww.token name="saveToDoTaskToken"/>
    <@ww.hidden name="'toDoTask.id'" value="${toDoTask.id?if_exists}"/>
	<@inputTable>
		<tr>
			<@textfield label="${action.getText('编码')}" name="toDoTask.code" value="${toDoTask.code?if_exists}" 
			            required="true"/>
			<@textfield label="${action.getText('名称')}" name="toDoTask.name" value="${toDoTask.name?if_exists}" 
			            required="true"/>
		</tr>
		<tr>
			<td align="right">
		       <label for="" class="label">
		           ${action.getText('任务状态')}:
		       </label>
		    </td>
		    <td>
            <select name="statue"  style="width:100px" disabled="true">
              <option value="0">未审核</option>
              <option value="1">已审核</option>
            </select>
            </td>
			<script language="javascript">
        		<#if toDoTask.statue?exists>
        			if(${toDoTask.statue}=='1')
        			{
        				getObjByName('statue').value = 1;
        			}
        			else if(${toDoTask.statue}=='0')
        			{
        				getObjByName('statue').value = 0;
        			}
        			<#else>
        		  getObjByName('statue').value = 0;
		     	</#if>
        	</script>
		   <@textfield label="${action.getText('审批人')}" name="toDoTask.point.personnelFiles.name" 
		               value="${toDoTask.point.personnelFiles.name?if_exists}" required="true"/>
		        <script language="javascript">
		        	<#if toDoTask?exists>
		        		getObjByName('toDoTask.point.personnelFiles.name').value = ${toDoTask.point.personnelFiles.name?if_exists};
		        	<#else>
		        		<#if req.getParameter('toDoTask.point.personnelFiles.name')?exists>
		        			getObjByName('toDoTask.point.personnelFiles.name').value = '${req.getParameter('toDoTask.point.personnelFiles.name')?if_exists}';
		        		</#if>		
		        	</#if>
		        </script>
		</tr>
		<tr>
			<@pp.datePicker 
				label="'${action.getText('审批日期')}'" 
				name="'toDoTask.approveDate'" 
	   			value="'${(toDoTask.approveDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"/>
			<script language="javascript">
				var date = new Date();
				if(getObjByName("toDoTask.approveDate").value=='')
				{
					getObjByName("toDoTask.approveDate").value = date.format("yyyy-MM-dd");
				}
			</script>
			 <td align="right">
		       <label for="" class="label">
		           ${action.getText('是否同意')}:
		       </label>
		    </td>
			<td align="left">
	        	<input type="radio" id="agreeOrNot0" name="toDoTask.agreeOrNot" value="1"/>是
	        	<input type="radio" id="agreeOrNot1" name="toDoTask.agreeOrNot" value="0"/>否
			</td>
			<script language="javascript">
        		<#if toDoTask.agreeOrNot?exists>
        			if(${toDoTask.agreeOrNot}=='1')
        			{
        				getObjByName('agreeOrNot0').checked = true;
        			}
        			else if(${toDoTask.agreeOrNot}=='0')
        			{
        				getObjByName('agreeOrNot1').checked = true;
        			}
        		<#else>
        		   getObjByName('agreeOrNot0').checked = true;
		     	</#if>
        	</script>
		</tr>
		<tr>
			<@textarea label="不同意原因" name="toDoTask.notAgreeReason" colspan="5" rows="3" cols="100" 
			           value="${toDoTask.notAgreeReason?if_exists}"/>
			<td align="right">
		       <label for="" class="label">
		           ${action.getText('提交并完成审核')}:
		       </label>
		    </td>
		    <td align="left">
	            <input type="radio" id="completeReview0" name="toDoTask.completeReview" value="1"/>是
	        	<input type="radio" id="completeReview1" name="toDoTask.completeReview" value="0"/>否
            </td>
            <script language="javascript">
        		<#if toDoTask.completeReview?exists>
        			if(${toDoTask.completeReview}== 1)
        			{
        				getObjByName('completeReview0').checked = true;
        			}
        			else if(${toDoTask.completeReview}=='0')
        			{
        				getObjByName('completeReview1').checked = true;
        			}
        		<#else>
        		  	getObjByName('completeReview0').checked = true;
		     	</#if>
        	</script>
		</tr>
	</@inputTable>
	<@buttonBar>
		<#if toDoTask.completeReview?exists>
			<#if toDoTask.statue == 1>
					<@vsubmit name="save" value="'${'保存'}'" onclick="'return storeValidation();'" disabled="true"/>
			<#else>
					<@vsubmit name="save" value="'${'保存'}'" onclick="'return storeValidation();'"/>
			</#if>
		<#else>
			<@vsubmit name="save" value="'${'保存'}'" onclick="'return storeValidation();'"/>	
		</#if>
		<@redirectButton value="${'返回'}" url="${req.contextPath}/toDoTask/listToDoTask.html"/>
    </@buttonBar>
</@ww.form>
<script language="javascript">
//window.onload=function(){
//alert(111111111111111);
//	alert(${toDoTask.point.personnelFiles.name});
//}
	function storeValidation(){
		return true;
	}
</script>
</@htmlPage>
<#if !first>
<ul id="beautytab">
	<li>
		<a id="theDetailOfTask" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/${toDoTask.workflowCase.url}&readOnly=true' target="frame" >${action.getText('任务明细')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/${toDoTask.workflowCase.url}&readOnly=true" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>
</#if>
