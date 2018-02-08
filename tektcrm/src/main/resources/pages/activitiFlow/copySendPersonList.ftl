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

<#-- $Id: pointList.ftl 2010-04-06 wliu $ -->

<#include "../includes/hco2011.ftl" />

<@framePage>
	<@ww.form name="'listForm'" action="'searchCopySendPerson'" method="'post'">
		<@ww.token name="searchCopySendPersonToken"/>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'flow.id'" value="'#{flowId?if_exists}'"/>
		<@ww.hidden name="'bussnessId'" value="'#{bussnessId?if_exists}'"/>
		<#assign itemNo=1/>
        <@list title="${action.getText('抄送人列表')}" excel=false setupTable=false
        	   includeParameters="flow.id" 
        	   fieldMap="" >
        	<#if !(req.getParameter('historyTask')?exists && req.getParameter('historyTask')=="historyTask")>
        		<#if !(action.isReadOnly())>
    				<@vlh.checkbox property="id" name="copySendPersonIds">
		                <@vlh.attribute name="width" value="30" />
		            </@vlh.checkbox>
	            </#if>
        	</#if>
        	
			<@vcolumn title="序号">
			#{itemNo}
				<@alignCenter />
			</@vcolumn>
			
			<#assign itemNo=itemNo + 1/>
            <@vcolumn title="${action.getText('姓名')}" property="person.name" >
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('部门')}" property="person.dept.name" >
            	<@alignLeft/>
            </@vcolumn>
        </@list>
        <#if !(req.getParameter('historyTask')?exists && req.getParameter('historyTask')=="historyTask")>
        	<#if !first>
	        <#if !(action.isReadOnly())>
	        <@buttonBar>
			    <@vbutton class="button" value="${action.getText('new')}" onclick="newPoint()"/>
			<#assign confirmMessage = "${action.getText('确认删除')}?" />	 
			  	<#assign confirmMessage = "${action.getText('确认删除')}?" />	 
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		               <@ww.param name="'onclick'" value="'return confirmDeletes(\"copySendPersonIds\", \"${confirmMessage}\");'"/>
		                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		            </@vsubmit>
			</@buttonBar>	
			</#if>
			</#if>
        </#if>
    </@ww.form>
<script type='text/javascript' src='${req.contextPath}/dwr/interface/newCopySendPerson.js'></script>
<script language="javascript">
//window.onload=function(){
//}


function openPage(url){
    popupModalDialog(url, 800, 600);
    if(isIE()){self.location.reload();};
}

function newPoint(){
	var url = "${req.contextPath}/personnelFile/listPersonByUser.html?backVisitCheckBox=backVisitCheckBox";
	 popupModalDialog(url, 800, 600, creatorSelectorHandler);
	if(isIE()){self.location.reload();};
}
//获得模态窗体返回值
function creatorSelectorHandler(result) {
	if (null != result) {
		//新建抄送人
		var id_str = "";
		var flowId = getObjByName('flow.id').value;
		var bussnessId = getObjByName('bussnessId').value;
		var res_arr = result[0].split(",");
		for(var i = 0;i<res_arr.length;i++){
			var _temp = res_arr[i].split(":");
			id_str += _temp[0]+"\'";
		}
		id_str = id_str.substring(0,id_str.length-2);
		DWREngine.setAsync(false); 
		newCopySendPerson.saveCopySendPerson(id_str,flowId,bussnessId);
		DWREngine.setAsync(true); 
	}
	window.location.reload(true);
	if(isIE()){self.location.reload();};
}
</script>
</@framePage>