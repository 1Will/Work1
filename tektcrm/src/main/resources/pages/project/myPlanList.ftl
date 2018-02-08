<#include "../includes/hco2011.ftl" />

<@htmlPage title="我的任务">
	<@ww.form name="'listForm'" namespace="'/projectInfo'" action="'searchMyPlan'" method="'post'">
		<@ww.token name="searchMyPlanToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
			<#include "./myPlanSearcher.ftl" />
			<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'"  />
        </@buttonBar>
			 <#assign itemNo=1/>
 	        <@list title="" 
        includeParameters="projectInfo.name|name|readOnly|onlyInvalid|onlyValid|contractManagement.contractName|planState.id|" 
        fieldMap="like:projectInfo.name|name|contractManagement.contractName|" >
            <#if !(action.isReadOnly())>
	            <@vlh.checkbox property="id" name="projectInfoPlanIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
              <@vcolumn title="${action.getText('id')}" property="id" >
              <a href="editProPlan.html?projectInfoPlan.id=${object.id}&editFlag=editFlag">#{itemNo}</a> 
            <@alignLeft/>
            </@vcolumn>
            <#assign itemNo=itemNo + 1/>
             <@vcolumn title="${action.getText('项目名称')}"   >
             <#if object.projectInfo?exists>
	             <a href="javascript:projectInfo_OpenDialog(#{object.projectInfo.id?if_exists})">
	             ${object.projectInfo.name}
	             </a>
             <#else >
             <#--
	             <#if object.contractManagement?exists&&object.contractManagement.project?exists>
	             <a href="javascript:projectInfo_OpenDialog(#{object.contractManagement.project.id?if_exists})">
	             ${object.contractManagement.project.name}
            	 </a>
             	 </#if>
             -->
             </#if>
            <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('合同名称')}">
              <#if object.contractManagement?exists>
              <a href="javascript:contractManagement_OpenDialog(#{object.contractManagement.id?if_exists})">
             ${object.contractManagement.contractName}
             </a>
             </#if>
            <@alignLeft/>
            </@vcolumn>
            
              <@vcolumn title="${action.getText('产品计划名称')}">
              <#if object.productionOperationDetail?exists>
              <a href="javascript:editProductionOperationDetail('#{object.productionOperationDetail.id}','#{object.productionOperationDetail.productionOperation.id}')">
             ${object.productionOperationDetail.product.name}
             </a>
             </#if>
            <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('projectInfoPlan.name')}" property="name"   >
              <a href="editProPlan.html?projectInfoPlan.id=${object.id}&editFlag=editFlag">${object.name}</a> 
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
        </@list>
    </@ww.form>
</@htmlPage>
<script>
	  //弹出项目信息窗体
	function projectInfo_OpenDialog(id){
	   var url = "${req.contextPath}/projectInfo/editProjectInfo.html?projectInfo.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}&openFlag=openFlag";
	   openNewWindow(url);
	 }
	   //弹出合同信息窗体
	function contractManagement_OpenDialog(id){
	   var url = "${req.contextPath}/contractManagement/editContractManagementAction.html?contractManagement.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=popWindowFlag";
	   openNewWindow(url);
	 }
	 //打开编辑c产品计划窗口
  function editProductionOperationDetail(id,productionOperationId){
  var url="${req.contextPath}/productionOperation/editProductionOperationDetail.html?productionOperationDetail.id="+id+"&productionOperation.id="+productionOperationId+"&readOnly=${req.getParameter('readOnly')?if_exists}";
	    openNewWindow(url);
  }
	 
</script>