<#include "../includes/hco2011.ftl" />

<@framePage>
	<@ww.form name="'listForm'" namespace="'/projectInfo'" action="'searchProCon'" method="'post'">
		<@ww.token name="searchProjectInfoContractToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
			<@ww.hidden name="'projectInfo.id'" value="'${projectInfoId?if_exists}'"/>
			<@ww.hidden name="'customerInfoId.id'" value="'${customerInfoId?if_exists}'"/>
			<@ww.hidden name="'contactArchives.id'" value="'${contactArchivesId?if_exists}'"/>
			 <#assign itemNo=1/>
 	        <@list title="" 
        includeParameters="projectInfo.id|contactArchives.id|readOnly|onlyInvalid|onlyValid" 
        fieldMap="" >
            <#if !(action.isReadOnly())>
	            <@vlh.checkbox property="id" name="projectInfoContractIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
            <#if projectInfoId?exists>
             <@vcolumn title="${action.getText('id')}" property="id"  >
              <a href="javascript:editProCon_OpenDialog_update('#{object.id}');">#{itemNo}</a> 
            <@alignLeft/>
            </@vcolumn >
            <#assign itemNo=itemNo + 1/>
             <@vcolumn title="${action.getText('projectInfoContract.proCon')}" property="contactArchives.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('businessType.name')}" property="businessType.name" sortable="desc"  >
            <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('conOutline')}" property="outline"   >
            <@alignLeft/>
            </@vcolumn>
          
            <#else>
             <@vcolumn title="${action.getText('code')}" property="code" sortable="desc">
             <a href="javascript:editProjectInfo_OpenDialog('#{object.projectInfo.id}')"
                 title="${object.projectInfo.code}%">${object.projectInfo.code}</a>
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('name')}" property="projectInfo.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('projectInfo.customerInfoName')}" property="projectInfo.customer.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('projectInfo.controller')}" property="projectInfo.controller.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('outline')}" property="projectInfo.outline" sortable="desc"  >
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('state.name')}" property="projectInfo.state.name" sortable="desc"  >
            <@alignCenter attributes="width:110;"/>
            </@vcolumn>
            </#if>
        </@list>
         <#if !first>
          <#if projectInfoId?exists>
			<@buttonBar>
				<#if !(action.isReadOnly())>
					<@vbutton class="button" name="${action.getText('从客户联系人选择')}" value="${action.getText('从客户联系人选择')}" onclick="editProCon_OpenDialog();"/>
					<@vbutton class="button" name="${action.getText('new')}" value="${action.getText('new')}" onclick="contactArchives_OpenDialog();"/>
					<#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('projectInfoPersonnelsIds.info')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDeletes(\"projectInfoContractIds\", \"${confirmMessage}\");'"/>
		                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		            </@vsubmit>
	            </#if>
			</@buttonBar>
			</#if>
		</#if>
    </@ww.form>
</@framePage>
<script>
	function editProCon_OpenDialog(){
	   var url="";
	   url= "${req.contextPath}/projectInfo/editProCon.html?projectInfo.id=${projectInfoId?if_exists}&customerInfo.id=${customerInfoId?if_exists}";
	   popupModalDialog(url, 900, 600);
	   self.location.reload();
	 }
	 function editProCon_OpenDialog_update(id){
	   var url="";
	   url= "${req.contextPath}/projectInfo/editProCon.html?projectInfo.id=${projectInfoId?if_exists}&customerInfo.id=${customerInfoId?if_exists}&projectInfoContract.id="+id;
	   popupModalDialog(url, 900, 600);
	   self.location.reload();
	 }
	  function editProjectInfo_OpenDialog(id){
	   var url="";
	   url= "${req.contextPath}/projectInfo/editProjectInfo.html?projectInfo.id="+id+"&openFlag=openFlag&readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 900, 600);
	   self.location.reload();
	 }
	 
	 function contactArchives_OpenDialog(){
	  var url=url= "${req.contextPath}/customerRelationship/editContactArchives.html?projectInfo.id=${projectInfoId?if_exists}&customer.id=${customerInfoId?if_exists}&newContacts=newContacts";
	   popupModalDialog(url, 900, 600);
	   self.location.reload();
	 }
	function caProfile_OpenDialog(caId){
	   var url = "${req.contextPath}/customerRelationship/editContactArchives.html?contactArchives.id="+caId+"&readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 900, 600);
	   //window.open(url);
	   self.location.reload();
	 }
</script>