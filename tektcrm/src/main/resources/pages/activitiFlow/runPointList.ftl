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
        <#if flow?exists>
        <@ww.hidden name="'flow.id'" value="'#{flow.id?if_exists}'"/>
        </#if>
		<@ww.hidden name="'historyTask'" value="'${req.getParameter('historyTask')?if_exists}'"/>
		<@ww.hidden name="'bussnessId'" value="'#{bussnessId?if_exists}'"/>
		<#assign itemNo=1/>
        <@list title="${action.getText('审批人列表')}" excel=false setupTable=false
        	   includeParameters="flow.id|bussnessId|readOnly|" 
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
			#{object.myNum?if_exists}</a>
				<@alignCenter />
			</@vcolumn>
			
			<#assign itemNo=itemNo + 1/>
            <@vcolumn title="${action.getText('操作名称')}" property="name" >
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('审批人')}" property="inspectPser.name" >
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('提交时间')}" property="submitTime" format="yyyy-MM-dd HH:mm:ss" >
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('审核时间')}" property="inspectTime" format="yyyy-MM-dd HH:mm:ss" >
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
            <#if !(req.getParameter('historyTask')?exists && req.getParameter('historyTask')=="historyTask")>
             	<@vcolumn title="${action.getText('调整顺序')}"  >
            	<#if object.result?exists && object.result.code == '21401'>
            	<a href="javascript:void();" onclick="adjustSerialNum1('up',this)"><span style="font-size:20px;padding-left:5px;">△</span></a>
                <a href="javascript:void();" onclick="adjustSerialNum1('down',this)"><span style="font-size:20px;">▽</span></a>
            	</#if>
	            <@alignLeft/>
	            </@vcolumn>
            </#if>
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
    
<script type='text/javascript' src='${req.contextPath}/dwr/interface/AdjustSerialNum_runpoint.js'></script>
<script language="javascript">
//window.onload=function(){
//}
<#if !(req.getParameter('historyTask')?exists && req.getParameter('historyTask')=="historyTask")>
	//初始化数据
	var oTable = document.getElementById("vltable");
	if(oTable != null && oTable != undefined){
		var oTr = oTable.getElementsByTagName('tbody')[0].children;
		for(var i = 0;i<oTr.length;i++){
			var text = oTr[i].children[oTr[i].children.length-2].innerText.replace(/\s/g,"");
			if(text != "新建"){
				oTr[i].children[0].children[0].disabled = "true";
			}
		}
		//获取第一个需要排序的td
		var firstTrUpLink;
		for(var i=1;i<oTable.children["0"].children.length;i++){
			var eachTr = oTable.children["0"].children[i];
			var td_length = eachTr.children.length;
			var last_td_chil_length = eachTr.children[td_length-1].children.length;
			if(last_td_chil_length == 2){
				firstTrUpLink = eachTr.children[td_length-1].children[0];
				break;
			}
		}
		var lastTrIndex = oTable.children["0"].children.length-1;
		var downLinkPosition = oTable.children["0"].children[lastTrIndex].children.length;
		var lastTrDownLink = oTable.children["0"].children[lastTrIndex].children[downLinkPosition-1].children[1];
		if(firstTrUpLink != undefined && lastTrDownLink != undefined){
			firstTrUpLink.style.display = "none";
			lastTrDownLink.style.display = "none";
		}
	}
</#if>
//处理用时
var oTr = document.getElementById("vltable").getElementsByTagName("tbody")[0].getElementsByTagName("tr");
for(var i=1;i<oTr.length;i++){ 
	var td_arr = oTr[i].getElementsByTagName("td");
	<#if !(req.getParameter('historyTask')?exists && req.getParameter('historyTask')=="historyTask")>
	var needIndex = td_arr.length-4;
	<#else>
	var needIndex = td_arr.length-3;
	</#if>
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
function adjustSerialNum1(flag,obj){
	 	var currentObj = obj;
 		var currentTr = currentObj.parentNode.parentNode;
 		var tBodyObj = currentObj.parentNode.parentNode.parentNode;
 		var currentTrId = currentTr.children[0].children[0].defaultValue;
	 	if(flag == "up"){
	 		if(currentTr.rowIndex == 1){
	 			return;
	 		}
	 		var previousTr = tBodyObj.children[currentTr.rowIndex-1];
	 		var previousTrId = previousTr.children[0].children[0].defaultValue;
	 		//更新上一个的排序号，和当前的排序号
	 		//alert(previousTrId+"----"+currentTrId);
	 		DWREngine.setAsync(false); 
	 		AdjustSerialNum_runpoint.saveOrderNum(currentTrId,previousTrId);
	 		DWREngine.setAsync(true); 
	 		window.location.reload(true);
	 	}else{
	 		if(currentTr.rowIndex == tBodyObj.children.length-1){
	 			return;
	 		}
	 		var nextTr = tBodyObj.children[currentTr.rowIndex+1];
	 		var nextTrId = nextTr.children[0].children[0].defaultValue;
	 		//更新下一个的排序号，和当前的排序号
	 		//alert(nextTrId+"----"+currentTrId);
	 		DWREngine.setAsync(false); 
	 		AdjustSerialNum_runpoint.saveOrderNum(currentTrId,nextTrId);
	 		DWREngine.setAsync(true); 
	 		window.location.reload(true);
	 	}
	 }
</script>
</@framePage>