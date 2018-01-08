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

<#include "../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('待审批')}">
<@ww.form name="'listForm'" action="'saveMyRunTask'" method="'post'">
	<@ww.token name="saveMyRunTaskToken"/>
	<@ww.hidden name="'idStr'" value=""/>
	<@ww.hidden name="'batchFinished'" value=""/>
	<@inputTable>
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
	     	    <@ww.hidden name="'transferId'"  />
					<input type="text" id="transferName" name="transferName" class="underline" 
					       value="" maxlength="140" size="20" disabled="true"/>
				<a onClick="persons_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>       
		</tr>
		<tr>
			<!--审批意见-->
			<td align="right" valign="top">
        		<label class="label">
        			<font color='red'>*</font>${action.getText('审批意见')}:
        		</label>
        	</td>
	        <td colspan="4">
	        	<textarea name="remark" rows="4" cols="100" >${req.getParameter('batchFinished')?if_exists}</textarea>
	        </td>
		</tr>
	</@inputTable>
	<@buttonBar>
		<@vsubmit name="save" value="'${'保存'}'" onclick="'return storeValidation();'"/>	
		<@vbutton class="button" name="${action.getText('close')}" value="${action.getText('close')}" onclick="closeThis();"/>
    </@buttonBar>
</@ww.form>
<script language="javascript">
//设置初始化数据
getObjByName('idStr').value = "${req.getParameter('idStr')?if_exists}";
getObjByName('batchFinished').value = "${req.getParameter('batchFinished')?if_exists}";
if(getObjByName('batchFinished').value == "1"){
	alert("批量审批成功！");
	closeThis();
}

function storeValidation(){
	//审批结果
    if(getObjByName('result.id').value == ""){
        getObjByName('result.id').focus();
        alert("请选择审批结果！");
		return false;
	}
	var result=getObjByName('result.id').value;
	if(result==585){//判断如果选择的结果为转交  则接收人必须填
	if(getObjByName('transferId').value == ""){
        alert("请选择接受人！");
		return false;
	}
	}
    //审批意见
    if(getObjByName('remark').value == ""){
        getObjByName('remark').focus();
        alert("请填写审批意见！");
		return false;
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
if(result==585){//判断如果选择的结果为转交 
		getObjByName('transferLabel').style.display="inline";
		getObjByName('transferTd').style.display="inline";
	}else{
		getObjByName('transferLabel').style.display="none";
		getObjByName('transferTd').style.display="none";
	}
}

</script>
</@htmlPage>