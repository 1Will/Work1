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
<@htmlPage title="${action.getText('节点维护')}">
<@ww.form name="'editForm'" action="'saveRunPoint'" method="'post'">
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="editRunPointToken"/>
	<#if flowId?exists>
    <@ww.hidden name="'flow.id'" value="#{flowId?if_exists}"/>
    </#if>
	<#if runPoint.id?exists>
    	<@ww.hidden name="'runPoint.id'" value="#{runPoint.id?if_exists}"/>
    	<#else>
    	<@ww.hidden name="'runPoint.bussnessId'" value="'${req.getParameter('bussnessId')?if_exists}'"/>
    </#if>
    <@ww.hidden name="'flag'" value="1"/>
	<@inputTable>
		<tr>
			<@textfield label="${action.getText('操作名称')}" name="runPoint.name" value="${runPoint.name?if_exists}" anothername="code"
			             disabled="true"/>
			<#--
			<@textfield label="${action.getText('运行时节点名称')}" name="runPoint.name" value="${runPoint.name?if_exists}" anothername="name"
			            required="true"/>-->
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('审批人')}:</label>
	     	</td>
	     	<td>
	     	    <#if runPoint.inspectPser?exists>
	     	    <@ww.hidden name="'runPoint.inspectPser.id'" id="'runPoint.inspectPser.id'" value="'${runPoint.inspectPser.id?if_exists}'"/>
	     	    <#else>
	     	    <@ww.hidden name="'runPoint.inspectPser.id'" id="'runPoint.inspectPser.id'" value="''"/>
	     	    </#if>
	     		
	     		<#if runPoint.inspectPser?exists>
		   			<input type="text" id="inspectPser.name" name="inspectPser.name" class="underline"  
		   			       value="${runPoint.inspectPser.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" id="inspectPser.name" name="inspectPser.name" class="underline" 
					       value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="persons_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>            
		</tr>
		<tr>
		    <#--
		    <@ww.select label="'${action.getText('上一步骤')}'"
					 required="true"
					 name="'previousPoint.id'" 
					 value="${req.getParameter('previousPoint.id')?if_exists}" 
					 listKey="id"
					 listValue="name"
				     list="allPreviousPoint"
				     emptyOption="true" 
				     disabled="false">
		    </@ww.select>
		    <@ww.select label="'${action.getText('下一步骤')}'"
					 required="true"
					 name="'rearPoint.id'" 
					 value="${req.getParameter('rearPoint.id')?if_exists}" 
					 listKey="id"
					 listValue="name"
				     list="allRearPoint"
				     emptyOption="true" 
				     disabled="false">
		    </@ww.select>
		    -->
			<#--
		    <td align="right">
		       <label for="" class="label">
		           ${action.getText('是否启用')}:
		       </label>
		    </td>
			<td align="left">
	        	<input type="radio" id="openOrNot0" name="runPoint.openOrNot" value="0"/>是
	        	<input type="radio" id="openOrNot1" name="runPoint.openOrNot" value="1"/>否
			</td>
			<script language="javascript">
        		<#if runPoint.openOrNot?exists>
        			if(${runPoint.openOrNot}=='0')
        			{
        				getObjByName('openOrNot0').checked = true;
        			}
        			else if(${runPoint.openOrNot}=='1')
        			{
        				getObjByName('openOrNot1').checked = true;
        			}
		     	</#if>
        	</script>
        	-->
		</tr>
		<#--
		<tr>
			<@textarea label="${action.getText('备注')}" name="runPoint.remark" value="" colspan="5" rows="4" cols="100" 
			           value="'${runPoint.remark?if_exists}'"/>
		</tr>-->
	</@inputTable>
	<@buttonBar>
		<#if !(req.getParameter('historyTask')?exists && req.getParameter('historyTask')=="historyTask")>
			<#if runPoint.result?exists>
				<#if runPoint.result.code == "21401">
					<#if !(action.isReadOnly())>
						<@vsubmit name="save" value="'${'保存'}'" onclick="'return storeValidation();'"/>
					</#if>
				</#if>
				<#else>
				<@vsubmit name="save" value="'${'保存'}'" onclick="'return storeValidation();'"/>
			</#if>
		</#if>
		<@vbutton class="button" name="'${action.getText('close')}'" value="${action.getText('close')}" onclick="closeThis()"/>
	</@buttonBar>
</@ww.form>

<script type="text/javascript">
//window.onload=function(){
//	alert(getObjByName('flow.id').value);
//}
//弹出负责人查询模态窗体
function persons_OpenDialog(){
   var url = "${req.contextPath}/personnelFile/listPersonByUser.html";
   popupModalDialog(url, 800, 600, creatorSelectorHandler);
   //window.open(url);
 }
 //获得模态窗体返回值
function creatorSelectorHandler(result) {
	if (null != result) {
		getObjByName('runPoint.inspectPser.id').value = result[0];
   		getObjByName("inspectPser.name").value = result[2];
   		getObjByName("flag").value = 0;
	}
}
//保存前给隐藏域赋值和验证字段
function storeValidation(){
    //名称
	//负责人
    if(getObjByName('inspectPser.name').value==""){
        alert('请选择审批人！');
        return false;
    }
}
</script>


</@htmlPage>