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
<@ww.form name="'listForm'" action="'saveMyRunTask'" method="'post'">
	<@ww.token name="saveMyRunTaskToken"/>
    <@ww.hidden name="'runTask.id'" value="${runTask.id?if_exists}"/>
	<@inputTable>
		<tr>
		           <!--提交ren -->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('提交人')}:
        		</label>
        	</td>
			<td ><label class="label">
					<#if runTask.submitPer?exists>
						${runTask.submitPer.name?if_exists}
					</#if>
	        	</label>
	        </td>
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
		           <!--提交ren -->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('任务名称')}:
        		</label>
        	</td>
		<td ><label class="label">
		<#if runTask.linkHref?exists>
		<a href="javascript: openNewWindow('${req.contextPath}/${runTask.linkHref?if_exists}')">${runTask.name?if_exists}</a>
		<#else>
			<#if runTask.submitPer?exists>
				${runTask.submitPer.name?if_exists}
			</#if>
		</#if>
        			
        		</label></td>
		       <!--l流程类型-->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('流程类型')}:
        		</label>
        	</td>
		<td ><label class="label">
			<#if runTask.flow?exists>
				<a href="javascript: flow_Dialog(#{runTask.flow.id?if_exists});"">
        		${runTask.flow.name?if_exists}
        		</a>
        	</#if>
        		</label></td>
		</tr>
		<tr>
			           
			 <!--审批意见-->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('提交内容')}:
        		</label>
        	</td>
	        <td colspan="3">
	        	<textarea name="valueRemark" rows="4" cols="150" disabled="disabled" >${runTask.content?if_exists}</textarea>
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
			    onchange="'changeTransfer()'"
		    	disabled="false"/>
		    <td align="right" valign="top" id="transferLabel" style="display:none">
				<span class="required">*</span>
	       		<label class="label">${action.getText('接受人')}:</label>
	     	</td>
	     	<td  id="transferTd" style="display:none">
	     	    <@ww.hidden name="'transferId'"  value=""/>
					<input type="text" id="transferName" name="transferName" class="underline" 
					       value="" maxlength="140" size="20" readonly="readonly"/>
				<a onClick="persons_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>       
		</tr>
		<tr>
			<!--审批意见-->
			<td align="right" valign="top">
        		<label class="label">
        			<font color='red'></font>${action.getText('审批意见')}:
        		</label>
        	</td>
	        <td colspan="4">
	        	<textarea name="remark" rows="4" cols="150" >${req.getParameter('remark')?if_exists}</textarea>
	        </td>
		</tr>
	</@inputTable>
	<@buttonBar>
		<!--isSaved用以区分结果是否保存-->
		<@ww.hidden name="'isSaved'" value=""/>
		<@vsubmit name="save" value="'${'保存'}'" onclick="'savee();'"/>	
		<#if req.getParameter('isSaved')?exists && req.getParameter('isSaved')=='0' >
	    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'"/>
	    	<#else>
		    <@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'" disabled="true"/>
	    </#if>
		<@redirectButton value="${'返回'}" url="${req.contextPath}/activitiFlow/listMyRunTask.html"/>
    </@buttonBar>
</@ww.form>
<script language="javascript">
<#if req.getParameter('result.id')?exists>
var resultId = "${req.getParameter('result.id')?if_exists}";
var transferName = "${req.getParameter('transferName')?if_exists}";
var transferId = "${req.getParameter('transferId')?if_exists}";

if(resultId == 667){//判断如果选择的结果为转交 
getObjByName('transferLabel').style.display="inline";
getObjByName('transferTd').style.display="inline";
}else{
getObjByName('transferLabel').style.display="none";
getObjByName('transferTd').style.display="none";
}

getObjByName('transferName').value = transferName;
getObjByName('transferId').value = transferId;
getObjByName('result.id').value = resultId;

</#if>



function submitt(){
		getObjByName('isSaved').value="1";
		return storeValidation();
    }
    function savee(){
		getObjByName('isSaved').value="0";
     	return storeValidation();
    }


function storeValidation(){
	//审批结果
    if(getObjByName('result.id').value == ""){
        getObjByName('result.id').focus();
        alert("请选择审批结果！");
		return false;
	}
	var result=getObjByName('result.id').value;
	if(result == 667){//判断如果选择的结果为转交  则接收人必须填
	if(getObjByName('transferId').value == ""){
        alert("请选择接受人！");
		return false;
	}
	}
    //审批意见
    if(getObjByName('remark').value != ""){
    	if(getObjByName('remark').value.length > 255 ){
    		getObjByName('remark').focus();
	        alert("审批意见长度超出限制！");
			return false;
    	}
	}
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
		getObjByName('transferId').value = result[0];
   		getObjByName("transferName").value = result[2];
	}
}
function changeTransfer(){
var result=getObjByName('result.id').value;
if(result==667){//判断如果选择的结果为转交 
getObjByName('transferLabel').style.display="inline";
getObjByName('transferTd').style.display="inline";
}else{
getObjByName('transferLabel').style.display="none";
getObjByName('transferTd').style.display="none";
}

}
function flow_Dialog(id){
  var url =  "${req.contextPath}/flow/editFlow.html?readOnly=${req.getParameter('readOnly')?if_exists}&flow.id="+id+"&backvist=backvist";
  popupModalDialog(url, 1100, 600);
  } 
</script>
</@htmlPage>
<ul id="beautytab">
	<li>
		<a id="runPoint" onclick="activeTab(this);"  href='${req.contextPath}/activitiFlow/listRunPoint.html?historyTask=historyTask&bussnessId=#{runTask.bussnessId}&flow.id=#{runTask.flow.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('审批人')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/activitiFlow/listRunPoint.html?historyTask=historyTask&bussnessId=#{runTask.bussnessId}&flow.id=#{runTask.flow.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="60%"/>
