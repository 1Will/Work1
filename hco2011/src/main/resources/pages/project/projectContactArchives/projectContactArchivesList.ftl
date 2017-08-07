<#include "../../includes/hco2011.ftl" />

<@framePage>
	<@ww.form name="'listForm'" namespace="'/projectInfo'" action="'searchProjectContactArchives'" method="'post'">
		<@ww.token name="searchProjectContactArchivesToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
			<@ww.hidden name="'projectInfo.id'" value="'${req.getParameter('projectInfo.id')?if_exists}'"/>
			<#assign itemNo=1/>
 	        <@list title="" 
        includeParameters="projectInfo.id|readOnly|onlyInvalid|onlyValid" 
        fieldMap="" >
            <#if !(action.isReadOnly())>
	            <@vlh.checkbox property="id" name="projectContactArchivesIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
              <@vcolumn title="${action.getText('序号')}" property="id" >
              <a href="javascript:editProPer_OpenDialog_update('#{object.id}');">#{itemNo}</a> 
            <@alignLeft/>
            </@vcolumn>
            <#assign itemNo=itemNo + 1/>
             <@vcolumn title="${action.getText('成员名称')}" property="contactArchives.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('业务属性')}" property="businessType.name" sortable="desc"  >
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('职责说明')}" property="outline" sortable="desc"  >
            <@alignLeft />
            </@vcolumn>
          
        </@list>
         <#if !first>
			<@buttonBar>
				<#if !(action.isReadOnly())>
					<@vbutton class="button" name="${action.getText('new')}" value="${action.getText('new')}" onclick="editProPer_OpenDialog();"/>
					<#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('改成员')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDeletes(\"projectContactArchivesIds\", \"${confirmMessage}\");'"/>
		                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		            </@vsubmit>
	            </#if>
			</@buttonBar>
		</#if>
    </@ww.form>
</@framePage>
<script>
	function editProPer_OpenDialog(){
	   var url= "${req.contextPath}/projectInfo/editProjectContactArchives.html?projectInfo.id=${req.getParameter('projectInfo.id')?if_exists}";
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	 function editProPer_OpenDialog_update(id){
	   var url= "${req.contextPath}/projectInfo/editProjectContactArchives.html?projectInfo.id=${req.getParameter('projectInfo.id')?if_exists}&projectContactArchives.id="+id;
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
</script>