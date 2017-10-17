<#include "../../../includes/hco2011.ftl" />

<@framePage>
	<@ww.form name="'listForm'" namespace="'/contractManagement'" action="'searchContractPlan'" method="'post'">
		<@ww.token name="searchContractPlanToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
			
			<@ww.hidden name="'contractManagement.id'" value="'${contractManagement.id?if_exists}'"/>
			<#assign itemNo=1/>
 	        <@list title="" 
        includeParameters="contractManagement.id|contractPlan.id|readOnly|onlyInvalid|onlyValid" 
        fieldMap="" >
            <#if !(action.isReadOnly())>
	            <@vlh.checkbox property="id" name="contractPlanIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
            
            <@vcolumn title="${action.getText('序号')}" property="id" >
              <a href="javascript:editContractPlan_OpenDialog_update('#{object.id}');">#{itemNo}</a> 
            <@alignCenter attributes="width:80;"/>
            </@vcolumn>
            <#assign itemNo=itemNo + 1/>
            
             <@vcolumn title="${action.getText('开始时间')}" property="startTime" sortable="desc"  format="yyyy-MM-dd">
            <@alignCenter attributes="width:150;"/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('结束时间')}" property="endTime" sortable="desc"  format="yyyy-MM-dd">
            <@alignCenter attributes="width:150;"/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('负责人')}" property="executor.name"   >
            <@alignCenter attributes="width:100;"/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('工作内容')}" property="content"   >
            <@alignLeft />
            </@vcolumn>
            
        </@list>
         <#if !first>
			<@buttonBar>
				<#if !(action.isReadOnly())>
					<@vbutton class="button" name="${action.getText('new')}" value="${action.getText('new')}" onclick="editContractPlan_OpenDialog();"/>
					<#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('合同计划')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDeletes(\"contractPlanIds\", \"${confirmMessage}\");'"/>
		                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		            </@vsubmit>
	            </#if>
			</@buttonBar>
		</#if>
    </@ww.form>
</@framePage>
<script>
	function editContractPlan_OpenDialog(){
	   var url="";
	   url= "${req.contextPath}/contractManagement/editContractPlan.html?readOnly=${req.getParameter('readOnly')?if_exists}&contractManagement.id=${contractManagement.id?if_exists}";
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	 function editContractPlan_OpenDialog_update(id){
	   var url="";
	   url= "${req.contextPath}/contractManagement/editContractPlan.html?readOnly=${req.getParameter('readOnly')?if_exists}&contractManagement.id=${contractManagement.id?if_exists}&contractPlan.id="+id;
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	 
</script>