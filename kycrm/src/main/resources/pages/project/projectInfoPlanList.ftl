<#include "../includes/hco2011.ftl" />
<@framePage>
	<@ww.form name="'listForm'" namespace="'/projectInfo'" action="'searchProPlan'" method="'post'">
		<@ww.token name="searchProjectInfoPlanToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
			<@ww.hidden name="'projectInfo.id'" value="'${projectInfoId?if_exists}'"/>
			<@ww.hidden name="'contractManagement.id'" value="'${contractManagementId?if_exists}'"/>
			 <#assign itemNo=1/>
		<@buttonBar>
         <#if backCheckBox?exists>
         <input class="button" type="button" value="保存" onclick="return_ProPlan()"/>
          </#if>
        </@buttonBar>
 	        <@list title="" 
        includeParameters="projectInfo.id|readOnly|onlyInvalid|onlyValid|contractManagement.id|" 
        fieldMap="" >
            <#if !(action.isReadOnly())>
	            <@vlh.checkbox property="id" name="projectInfoPlanIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
              <@vcolumn title="${action.getText('id')}" property="id" >
              <#if backCheckBox?exists>
              #{itemNo}
              <#else>
	          <a href="javascript:editProPlan_OpenDialog_update('#{object.id}');">#{itemNo}</a> 
             </#if>
            <@alignLeft/>
            </@vcolumn>
            <#assign itemNo=itemNo + 1/>
             <@vcolumn title="${action.getText('projectInfoPlan.name')}"  property="name">
             ${object.name}
             <input style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px;display:none' name="#{object.id}" value="${object.name}" readonly="true"/>
           <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('projectInfoPlan.personnelFiles')}" property="personnelFiles.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('projectInfoPlan.assist')}" property="assist"   >
            <@alignLeft/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('projectInfoPlan.startDate')}" property="startDate" sortable="desc"  format="yyyy-MM-dd"  >
            <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('projectInfoPlan.endDate')}" property="endDate" sortable="desc" format="yyyy-MM-dd"  >
            <@alignLeft/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('projectInfoPlan.startFactDate')}" property="startFactDate" sortable="desc"  format="yyyy-MM-dd"  >
            <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('projectInfoPlan.endFactDate')}" property="endFactDate" sortable="desc" format="yyyy-MM-dd"  >
            <@alignLeft/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('proPlanPercentage')}" property="percentt"   >
            <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('proPlanState')}" property="planState.name"   >
            <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('proPlanOutline')}" property="outline"   >
            <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('创建者')}" property="creator"   >
            <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('前置任务')}" property="projectInfoPlan_2.name"   >
             <#if backCheckBox?exists>
	            	<#if object.projectInfoPlan_2?exists>${object.projectInfoPlan_2.name?if_exists}</#if>
	          <#else>
	          <a href="javascript:editProPlan_OpenDialog_update('<#if object.projectInfoPlan_2?exists>#{object.projectInfoPlan_2.id?if_exists}</#if>');"><#if object.projectInfoPlan_2?exists>${object.projectInfoPlan_2.name?if_exists}</#if></a> 
	        </#if>
            <@alignLeft/>
            </@vcolumn>
            <#if !backCheckBox?exists>
             <@vcolumn title="${action.getText('adjustSerialNum')}"  >
             <a href="javascript:void();" onclick="adjustSerialNum1('up',this)"><span style="font-size:20px;padding-left:5px;">△</span></a>
             <a href="javascript:void();" onclick="adjustSerialNum1('down',this)"><span style="font-size:20px;">▽</span></a>
            <@alignLeft/>
            </@vcolumn>
            </#if>
        </@list>
         <#if !first>
			<@buttonBar>
			<#if backCheckBox?exists && backCheckBox=='backCheckBox'>
			
			 <#else>
				<#if !(action.isReadOnly())>
					<@vbutton class="button" name="${action.getText('new')}" value="${action.getText('new')}" onclick="editProPlan_OpenDialog();"/>
					<#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('工作计划')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDeletes(\"projectInfoPlanIds\", \"${confirmMessage}\");'"/>
		                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		            </@vsubmit>
		            <#--
		            <@vbutton class="button" name="${action.getText('projectInfoPlanOrder')}" value="${action.getText('projectInfoPlanOrder')}" onclick="editProPlan_Order();"/>
                     -->	           
	            </#if>
	            </#if>
			</@buttonBar>
		</#if>
    </@ww.form>
</@framePage>
<script type='text/javascript' src='${req.contextPath}/dwr/interface/AdjustSerialNum.js'></script>

<script>
window.onload=function(){
  <#if backCheckBox?exists>
   projectInfoPlan_2Id='${projectInfoPlan_2Id?if_exists}';
  var objs =document.getElementsByName('projectInfoPlanIds');
  if(projectInfoPlan_2Id!=''){
     for(var i=0;i<objs.length;i++){
     if(objs[i].value==projectInfoPlan_2Id){
        objs[i].checked=true;
     }
     }
  }
  </#if>
   var table = document.getElementById("vltable");
	var upLinkPosition = table.children["0"].children[1].children.length;
	var firstTrUpLink = table.children["0"].children[1].children[upLinkPosition-1].children[0];
	var lastTrIndex = table.children["0"].children.length-1;
	var downLinkPosition = table.children["0"].children[lastTrIndex].children.length;
	var lastTrDownLink = table.children["0"].children[lastTrIndex].children[downLinkPosition-1].children[1];
	firstTrUpLink.style.display = "none";
	lastTrDownLink.style.display = "none";
}
	function editProPlan_OpenDialog(){
	   var url="";
	   <#if projectInfoId?exists>
	   url= "${req.contextPath}/projectInfo/editProPlan.html?projectInfo.id=${projectInfoId?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}";
	   <#elseif contractManagementId?exists>
	   url= "${req.contextPath}/projectInfo/editProPlan.html?contractManagement.id=${contractManagementId?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}";
	   </#if>
	   //popupModalDialog(url, 850, 600);
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	 function editProPlan_OpenDialog_update(id){
	   var url="";
	   <#if projectInfoId?exists>
	   url= "${req.contextPath}/projectInfo/editProPlan.html?projectInfo.id=${projectInfoId?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}&projectInfoPlan.id="+id;
	    <#elseif contractManagementId?exists>
	   url= "${req.contextPath}/projectInfo/editProPlan.html?contractManagement.id=${contractManagementId?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}&projectInfoPlan.id="+id;
	   </#if>
	   //popupModalDialog(url, 850, 600);
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	 <#--
	 function editProPlan_Order(){
	 var ids=[];
	 var objs =document.getElementsByName('projectInfoPlanIds');
	 for(var i=0;i<objs.length;i++){
	   if(objs[i].checked==true){
	      ids.push(objs[i].value);
	   }
	 }
	 if(ids.length<2){
	    alert("至少选择两个工作计划进行自定义排序！");
	    return;
	 }
	   var url="";
	   <#if projectInfoId?exists>
	   url= "${req.contextPath}/projectInfo/listProPlanOrder.html?projectInfo.id=${projectInfoId?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}&ids="+ids;
	   <#elseif contractManagementId?exists>
	   url= "${req.contextPath}/projectInfo/listProPlanOrder.html?contractManagement.id=${contractManagementId?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}&ids="+ids;
	   </#if>
	   //popupModalDialog(url, 850, 600);
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	 -->
	function return_ProPlan(){
      var ProPlanIds="";
      var ProPlanNames="";
	  var selector = document.getElementsByName("projectInfoPlanIds");
	   var length = selector.length;
	   var chooseNum=0;
	   if(length){
       for(var i=0;i<length;i++){
       if(selector[i].checked == true){
          chooseNum+=1;
          var tempName = selector[i].value;
          if(ProPlanIds==""){
          ProPlanIds =tempName;
          ProPlanNames=getObjByName(tempName).value;
          }else{ 
          ProPlanIds+=","+tempName;
          ProPlanNames+=";"+getObjByName(tempName).value
         }
         if(chooseNum>1){
            alert("只能选择一个前置任务");
            return;
         } 
          }
          }
          }
    
    returnDialog(new Array(ProPlanIds,ProPlanNames));
    
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
	 		AdjustSerialNum.saveOrderNum(currentTrId,previousTrId);
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
	 		AdjustSerialNum.saveOrderNum(currentTrId,nextTrId);
	 		DWREngine.setAsync(true);
	 		window.location.reload(true);
	 	}
	 }
</script>