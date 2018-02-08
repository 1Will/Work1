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

<@htmlPage title="${action.getText('历史审批查询')}">
	<@ww.form name="'listForm'" action="'searchMyHistoryTask'" method="'post'">
		<@ww.token name="searchMyHistoryTaskToken"/>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#include "./myHistoryTaskSearch.ftl"/>
		<@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/> 
        </@buttonBar>
        
		<#assign itemNo=1/>
        <@list title="${action.getText('历史审批列表')}" excel=false setupTable=false
        	   includeParameters="submitPer.name|flow.name|assignee.id|onlyInvalid|onlyValid" 
        	   fieldMap="like:submitPer.name|flow.name" >
        	<#--
            <@vlh.checkbox property="id" name="myHistoryTaskinstIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            -->
			<@vcolumn title="${action.getText('序号')}">
				<a href="editMyHistoryTask.html?historyTaskinst.id=#{object.id}">#{itemNo}</a>
				<@alignCenter />
			</@vcolumn>
			<#assign itemNo=itemNo + 1/>
            <@vcolumn title="${action.getText('任务名称')}" property="name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('流程名称')}" property="flow.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('提交人')}" property="submitPer.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('提交时间')}" property="submitTime"  format="yyyy-MM-dd hh:mm:ss" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('开始时间')}" property="startTime"  format="yyyy-MM-dd HH:mm:ss" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('完成时间')}" property="endTime"  format="yyyy-MM-dd HH:mm:ss" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
             <#if object.duration?exists && object.duration != 0>
             	 <@vcolumn title="${action.getText('用时')}" property="duration"   sortable="desc">
	            	<@alignLeft/>
	            </@vcolumn>
             <#else>
             	<@vcolumn title="${action.getText('用时')}"  sortable="desc">
	            	<@alignLeft/>
	            </@vcolumn>
             </#if>
        </@list>
        <#--
        <#if !first>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('customerInfo.info')}" boxName="toDoTaskIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
		</#if>
		-->
    </@ww.form>
<script language="javascript">
//处理用时
var oTr = document.getElementById("vltable").getElementsByTagName("tbody")[0].getElementsByTagName("tr");
for(var i=1;i<oTr.length;i++){
	var td_arr = oTr[i].getElementsByTagName("td");
	var needIndex = td_arr.length-1;
	var needValue = td_arr[needIndex].innerText.replace(/(^\s+)|(\s+$)/g,"");	
	var second = parseFloat(needValue);
	if(second >= 12*30*24*60*60){
		var timeValue = Math.round((second/(12*30*24*60*60))*10)/10;
		td_arr[needIndex].innerText = timeValue+"年";
	}else if(second >= 30*24*60*60){
		var timeValue = Math.round((second/(30*24*60*60))*10)/10;
		td_arr[needIndex].innerText = timeValue+"月";
	}else if(second >= 24*60*60){
		var timeValue = Math.round(second/(24*60*60));
		td_arr[needIndex].innerText = timeValue+"天";
	}else if(second >= 60*60){
		var timeValue = Math.round(second/(60*60));
		td_arr[needIndex].innerText = timeValue+"时";
	}else if(second >= 60){
		var timeValue = Math.round(second/60);
		td_arr[needIndex].innerText = timeValue+"分";
	}else if(second < 60){
		td_arr[needIndex].innerText = second+"秒";
	}
}


//失去焦点隐藏导航层
function onBlur(op){
	getObjByName(op).style.display="none";
}
</script>
</@htmlPage>