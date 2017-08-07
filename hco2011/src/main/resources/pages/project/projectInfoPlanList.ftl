<#include "../includes/hco2011.ftl" />

<@framePage>
	<@ww.form name="'listForm'" namespace="'/projectInfo'" action="'searchProPlan'" method="'post'">
		<@ww.token name="searchProjectInfoPlanToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
			<@ww.hidden name="'projectInfo.id'" value="'${projectInfoId?if_exists}'"/>
			<@ww.hidden name="'contractManagement.id'" value="'${contractManagementId?if_exists}'"/>
			 <#assign itemNo=1/>
 	        <@list title="" 
        includeParameters="projectInfo.id|readOnly|onlyInvalid|onlyValid|contractManagement.id|" 
        fieldMap="" >
            <#if !(action.isReadOnly())>
	            <@vlh.checkbox property="id" name="projectInfoPlanIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
              <@vcolumn title="${action.getText('id')}" property="id" >
              <a href="javascript:editProPlan_OpenDialog_update('#{object.id}');">#{itemNo}</a> 
            <@alignLeft/>
            </@vcolumn>
            <#assign itemNo=itemNo + 1/>
             <@vcolumn title="${action.getText('projectInfoPlan.name')}" property="name"   >
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
         <#if !first>
			<@buttonBar>
				<#if !(action.isReadOnly())>
					<@vbutton class="button" name="${action.getText('new')}" value="${action.getText('new')}" onclick="editProPlan_OpenDialog();"/>
					<#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('projectInfoPersonnelsIds.info')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDeletes(\"projectInfoPlanIds\", \"${confirmMessage}\");'"/>
		                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		            </@vsubmit>
	            </#if>
			</@buttonBar>
		</#if>
    </@ww.form>
</@framePage>
<script>
	function editProPlan_OpenDialog(){
	   var url="";
	   <#if projectInfoId?exists>
	   url= "${req.contextPath}/projectInfo/editProPlan.html?projectInfo.id=${projectInfoId?if_exists}";
	   <#elseif contractManagementId?exists>
	   url= "${req.contextPath}/projectInfo/editProPlan.html?contractManagement.id=${contractManagementId?if_exists}";
	   </#if>
	   //popupModalDialog(url, 850, 600);
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	 function editProPlan_OpenDialog_update(id){
	   var url="";
	   url= "${req.contextPath}/projectInfo/editProPlan.html?projectInfo.id=${projectInfoId?if_exists}&projectInfoPlan.id="+id;
	   //popupModalDialog(url, 850, 600);
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	 
</script>