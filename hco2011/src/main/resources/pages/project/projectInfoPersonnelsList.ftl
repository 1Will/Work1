<#include "../includes/hco2011.ftl" />

<@framePage>
	<@ww.form name="'listForm'" namespace="'/projectInfo'" action="'searchProPer'" method="'post'">
		<@ww.token name="searchProjectInfoPersonnelsToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
			<@ww.hidden name="'projectInfo.id'" value="'${projectInfoId?if_exists}'"/>
			<#assign itemNo=1/>
 	        <@list title="" 
        includeParameters="projectInfo.id|readOnly|onlyInvalid|onlyValid" 
        fieldMap="" >
            <#if !(action.isReadOnly())>
	            <@vlh.checkbox property="id" name="projectInfoPersonnelsIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
              <@vcolumn title="${action.getText('id')}" property="id" >
              <a href="javascript:editProPer_OpenDialog_update('#{object.id}');">#{itemNo}</a> 
            <@alignLeft/>
            </@vcolumn>
            <#assign itemNo=itemNo + 1/>
             <@vcolumn title="${action.getText('proPersonName')}" property="proPerson.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('businessType.name')}" property="businessType.name" sortable="desc"  >
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('proPersonOutline')}" property="outline" sortable="desc"  >
            <@alignLeft />
            </@vcolumn>
          
        </@list>
         <#if !first>
			<@buttonBar>
				<#if !(action.isReadOnly())>
					<@vbutton class="button" name="${action.getText('new')}" value="${action.getText('new')}" onclick="editProPer_OpenDialog();"/>
					<#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('projectInfoPersonnelsIds.info')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDeletes(\"projectInfoPersonnelsIds\", \"${confirmMessage}\");'"/>
		                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		            </@vsubmit>
	            </#if>
			</@buttonBar>
		</#if>
    </@ww.form>
</@framePage>
<script>
	function editProPer_OpenDialog(){
	   var url="";
	   url= "${req.contextPath}/projectInfo/editProPer.html?projectInfo.id=${projectInfoId?if_exists}";
	   //popupModalDialog(url, 850, 600);
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	 function editProPer_OpenDialog_update(id){
	   var url="";
	   url= "${req.contextPath}/projectInfo/editProPer.html?projectInfo.id=${projectInfoId?if_exists}&projectInfoPersonnels.id="+id;
	   //popupModalDialog(url, 850, 600);
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
</script>