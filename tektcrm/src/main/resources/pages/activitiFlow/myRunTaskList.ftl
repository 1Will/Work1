<#--
	Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: flowList.ftl 2010-04-06 wliu $ -->

<#include "../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('待审批查询')}">
	<@ww.form name="'listForm'" action="'searchMyRunTask'" method="'post'">
		<@ww.token name="searchMyRunTaskToken"/>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#include "./myRunTaskSearch.ftl"/>
		<@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms_seach()'" /> 
        </@buttonBar>
        <#assign returnName='replaceWord'>
		<#assign itemNo=1/>
        <@list title="${action.getText('待审批列表')}" excel=false setupTable=false
        	   includeParameters="submitPer.name|flow.name|assignee.id|onlyInvalid|onlyValid|runTask.submitTime_start|runTask.submitTime_end|" 
        	   fieldMap="like:submitPer.name|flow.name|month,date:runTask.submitTime_start|runTask.submitTime_end" >
            <@vlh.checkbox property="id" name="myRunTaskIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
			<@vcolumn title="${action.getText('序号')}">
				<a href="editMyRunTask.html?runTask.id=#{object.id}">#{itemNo}</a>
				<@alignCenter />
			</@vcolumn>
			<#assign itemNo=itemNo + 1/>
            <@vcolumn title="${action.getText('任务名称')}" property="name" sortable="desc">
            <a href="editMyRunTask.html?runTask.id=#{object.id}">${object.name}</a>
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('流程名称')}" property="flow.name" sortable="desc">
            <a href="javascript: flow_Dialog(#{object.flow.id?if_exists});"">
                ${object.flow.name? if_exists}
                </a>
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('提交人')}" property="submitPer.name" sortable="desc">
               <a href="javascript: salesman_Dialog(#{object.submitPer.id?if_exists});"">
                ${object.submitPer.name? if_exists}
                </a>
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('提交时间')}" property="submitTime"  format="yyyy-MM-dd hh:mm:ss" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('最后审批人')}" property="lastAssignPer.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('审批时间')}" property="assignTime"  format="yyyy-MM-dd HH:mm:ss" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('提交内容')}" property="content" sortable="desc">
            <#assign returnName=returnName +'replaceWord'>
            <@ww.hidden name="'${returnName}'" value="'${object.content?if_exists}'"/>
            	<span title="${object.content?if_exists}">
		            <script>
		            	var s = getObjByName('${returnName}').value;
		            	s=s.replace(/[\r\n]/g, "");
		            	if(s.length>18){
		            	document.write(s.slice(0,25)+"...");
		            	}else{
		            	document.write(s);
		            	}
		            </script>
	            </span>
     			<@alignLeft/>
            </@vcolumn>
        </@list>
        <#if !first>
	        <@buttonBar>
	        	<@vbutton class="button" name="'approve'" value="批量审批" onclick="checkInvalidParms();"/>
			</@buttonBar>
		</#if>
    </@ww.form>
<script language="javascript">
//失去焦点隐藏导航层
function onBlur(op){
	getObjByName(op).style.display="none";
}
//批量审批
function batchApproval(idStr){                
   var url = "${req.contextPath}/activitiFlow/editMyRunTaskBatch.html?idStr="+idStr;
   openNewWindow(url, 800, 400);
   if(isIE()){self.location.reload();};
}
//验证是否选择
function checkInvalidParms(){
	var checkBoxs = document.getElementsByName("myRunTaskIds");
	var idStr = "";
	for(var i=0;i<checkBoxs.length;i++){
		if(checkBoxs[i].checked){
			idStr += checkBoxs[i].value+"\'"; 	
		}
	}
	if(idStr == ""){
		alert("请选择需要审批的记录！");
		return;
	}else{
		idStr = idStr.substring(0,idStr.length-1);
	}
	batchApproval(idStr);
}
 function salesman_Dialog(id){
  var url =  "${req.contextPath}/personnelFile/editPersonnelFile.html?readOnly=${req.getParameter('readOnly')?if_exists}&personnelFile.id="+id+"&backvist=backvist";
	  popupModalDialog(url, 1100, 600);
	  }
	function flow_Dialog(id){
  	  var url =  "${req.contextPath}/flow/editFlow.html?readOnly=${req.getParameter('readOnly')?if_exists}&flow.id="+id+"&backvist=backvist";
	  popupModalDialog(url, 1100, 600);
	  }  
</script>
</@htmlPage>