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
	<@ww.form name="'listForm'" action="'searchRunPoint'" method="'post'">
		<@ww.token name="searchRunPointToken"/>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'flow.id'" value="'${req.getParameter('flow.id')?if_exists}'"/>
		<@ww.hidden name="'flow'" value="'${req.getParameter('flow')?if_exists}'"/>
		<@ww.hidden name="'historyTask'" value="'${req.getParameter('historyTask')?if_exists}'"/>
		<@ww.hidden name="'bussnessId'" value="'${req.getParameter('bussnessId')?if_exists}'"/>
		<#assign itemNo=1/>
        <@list title="${action.getText('审批人列表')}" excel=false setupTable=false
        	   includeParameters="flow.id" 
        	   fieldMap="" >
        	<#if !(req.getParameter('historyTask')?exists && req.getParameter('historyTask')=="historyTask")>
        		<#if !(action.isReadOnly())>
    				<@vlh.checkbox property="id" name="runPointIds">
		                <@vlh.attribute name="width" value="30" />
		            </@vlh.checkbox>
	            </#if>
        	</#if>
        	
			<@vcolumn title="序号" property="myNum">
			<a href="###" onclick="openPage('editRunPoint.html?historyTask=${req.getParameter('historyTask')?if_exists}&runPoint.id=#{object.id}&flow.id=${req.getParameter('flow.id')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}')">
			#{object.myNum}</a>
				<@alignCenter />
			</@vcolumn>
			
			<#assign itemNo=itemNo + 1/>
            <@vcolumn title="${action.getText('操作名称')}" property="name" >
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('审批人')}" property="inspectPser.name" >
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('审核时间/操作时间')}" property="inspectTime" format="yyyy-MM-dd HH:mm:ss" >
            	<@alignLeft/>
            </@vcolumn>
            <#if object.duration?exists && object.duration != 0>
            	<@vcolumn title="${action.getText('用时')}" property="duration">
            	<@alignLeft/>
            	 </@vcolumn>
            	<#else>
            	<@vcolumn title="${action.getText('用时')}">
	            	<@alignLeft/>
	            </@vcolumn>
            </#if>
             <@vcolumn title="${action.getText('审核意见')}" property="remark" >
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('状态')}" property="result.name" >
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
		               <@ww.param name="'onclick'" value="'return confirmDeletes(\"runPointIds\", \"${confirmMessage}\");'"/>
		                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		            </@vsubmit>
		            <#--
		            <@crm_disabledOrEnabled_Nodelete_button message="${action.getText('娴佺▼鑺傜偣鏄庣粏')}" boxName="pointIds" jsFunctionName="checkInvalidParms()"/>-->
			</@buttonBar>	
			</#if>
			</#if>
        </#if>
    </@ww.form>
<script language="javascript">
//window.onload=function(){
//}
<#if !(req.getParameter('historyTask')?exists && req.getParameter('historyTask')=="historyTask")>
	//初始化数据
	var oTable = document.getElementById("vltable");
	var oTr = oTable.getElementsByTagName('tbody')[0].children;
	for(var i = 0;i<oTr.length;i++){
		var text = oTr[i].children[oTr[i].children.length-1].innerText.replace(/\s/g,"");
		if(text != "新建"){
			oTr[i].children[0].children[0].disabled = "true";
		}
	}
</#if>
//处理用时
var oTr = document.getElementById("vltable").getElementsByTagName("tbody")[0].getElementsByTagName("tr");
for(var i=1;i<oTr.length;i++){
	var td_arr = oTr[i].getElementsByTagName("td");
	var needIndex = td_arr.length-3;
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

function openPage(url){
    popupModalDialog(url, 800, 600);
    if(isIE()){self.location.reload();};
}

function newPoint(){
	var url = "${req.contextPath}/activitiFlow/editRunPoint.html?bussnessId=${req.getParameter('bussnessId')?if_exists}&flow.id=${req.getParameter('flow.id')?if_exists}";
	openNewWindow(url, 800, 600);
	if(isIE()){self.location.reload();};
}
</script>
</@framePage>