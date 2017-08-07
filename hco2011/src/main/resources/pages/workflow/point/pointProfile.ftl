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
<@htmlPage title="${action.getText('节点维护')}">
<@ww.form name="'editForm'" action="'savePoint'" method="'post'">
<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="savePointToken"/>
	<#if flowId?exists>
    <@ww.hidden name="'flow.id'" value="#{flowId?if_exists}"/>
    </#if>
	<#if point.id?exists>
    	<@ww.hidden name="'point.id'" value="#{point.id?if_exists}"/>
    </#if>
    <@ww.hidden name="'flag'" value="1"/>
	<@inputTable>
		<tr>
			<@textfield label="${action.getText('节点编码')}" name="point.code" value="${point.code?if_exists}" anothername="code"
			            required="true"/>
			<@textfield label="${action.getText('节点名称')}" name="point.name" value="${point.name?if_exists}" anothername="name"
			            required="true"/>
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

			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('负责人')}:</label>
	     	</td>
	     	<td>
	     	    <@ww.hidden name="'point.personnelFiles'" id="'point.personnelFiles'" value="'${point.personnelFiles?if_exists}'"/>
	     	    <#if point.personnelFiles?exists>
	     	    <@ww.hidden name="'point.personnelFiles.id'" id="'point.personnelFiles.id'" value="'${point.personnelFiles.id?if_exists}'"/>
	     	    </#if>
	     		
	     		<#if point.personnelFiles?exists>
		   			<input type="text" id="personnelFiles.name" name="personnelFiles.name" class="underline"  
		   			       value="${point.personnelFiles.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" id="personnelFiles.name" name="personnelFiles.name" class="underline" 
					       value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="persons_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>

		    <td align="right">
		       <label for="" class="label">
		           ${action.getText('是否启用')}:
		       </label>
		    </td>
			<td align="left">
	        	<input type="radio" id="openOrNot0" name="point.openOrNot" value="0"/>是
	        	<input type="radio" id="openOrNot1" name="point.openOrNot" value="1"/>否
			</td>
			<script language="javascript">
        		<#if point.openOrNot?exists>
        			if(${point.openOrNot}=='0')
        			{
        				getObjByName('openOrNot0').checked = true;
        			}
        			else if(${point.openOrNot}=='1')
        			{
        				getObjByName('openOrNot1').checked = true;
        			}
		     	</#if>
        	</script>
		</tr>
		<tr>
			<@textarea label="${action.getText('备注')}" name="point.remark" value="" colspan="5" rows="4" cols="150" 
			           value="${point.remark?if_exists}"/>
		</tr>
	</@inputTable>
	<@buttonBar>
	<#if !(action.isReadOnly())>
		<@vsubmit name="save" value="'${'保存'}'" onclick="'return storeValidation();'"/>
	</#if>
		<@vbutton class="button" name="${action.getText('close')}" value="${action.getText('close')}" onclick="javascript:window.close();"/>
    </@buttonBar>
</@ww.form>

<script type="text/javascript">
//window.onload=function(){
//	alert(getObjByName('flow.id').value);
//}


function closeThis(){
	var flowId = getObjByName('flow.id').value;
	//var url = "${req.contextPath}/point/listPoint.html?flow.id=flowId&readOnly=false";
	returnDialog(flowId,"reload");
	return true;
}
//弹出负责人查询模态窗体
function persons_OpenDialog(){
   var url = "${req.contextPath}/personnelFile/listPersonByUser.html";
   popupModalDialog(url, 800, 600, creatorSelectorHandler);
   //window.open(url);
 }
 //获得模态窗体返回值
function creatorSelectorHandler(result) {
	if (null != result) {
		getObjByName('point.personnelFiles').value = result[0];
   		getObjByName("personnelFiles.name").value = result[2];
   		getObjByName("flag").value = 0;
	}
}
//保存前给隐藏域赋值和验证字段
function storeValidation(){
    //编码
    if(!textfieldCheck_code()){
        getObjByName('point.code').focus();
		return false;
	}
    //名称
    if(!textfieldCheck_name()){
        getObjByName('point.name').focus();
		return false;
	}
	//负责人
    if(getObjByName('personnelFiles.name').value==""){
        alert('请选择负责人');
        return false;
    }
}
</script>


</@htmlPage>