<#include "../../includes/hco2011.ftl" />

<@framePage>
	<@ww.form name="'listForm'" namespace="'/projectInfo'" action="'searchProjectPartner'" method="'post'">
		<@ww.token name="searchProjectPartnerToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
			<@ww.hidden name="'projectInfo.id'" value="'${req.getParameter('projectInfo.id')?if_exists}'"/>
			<#assign itemNo=1/>
 	        <@list title="" 
        includeParameters="projectInfo.id|readOnly|onlyInvalid|onlyValid" 
        fieldMap="" >
            <#if !(action.isReadOnly())>
	            <@vlh.checkbox property="id" name="projectPartnerIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
              <@vcolumn title="${action.getText('序号')}" property="id" >
              <a href="javascript:editProPar_OpenDialog_update('#{object.id}');">#{itemNo}</a> 
            <@alignLeft/>
            </@vcolumn>
            <#assign itemNo=itemNo + 1/>
            
             <@vcolumn title="${action.getText('客户名称')}" property="customerInfo.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('备注')}" property="remarks" sortable="desc"  >
            <@alignLeft />
            </@vcolumn>
          
        </@list>
         <#if !first>
			<@buttonBar>
				<#if !(action.isReadOnly())>
					<@vbutton class="button" name="${action.getText('new')}" value="${action.getText('从客户列表中选择')}" onclick="editProPar_OpenDialog();"/>
					
					<#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('合作客户')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDeletes(\"projectPartnerIds\", \"${confirmMessage}\");'"/>
		                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		            </@vsubmit>
	            </#if>
			</@buttonBar>
		</#if>
    </@ww.form>
</@framePage>
<script>
	function editProPar_OpenDialog(){
	   var url="";
	   url= "${req.contextPath}/projectInfo/editProjectPartner.html?readOnly=${req.getParameter('readOnly')?if_exists}&projectInfo.id=${req.getParameter('projectInfo.id')?if_exists}";
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	 function editProPar_OpenDialog_update(id){
	   var url="";
	   url= "${req.contextPath}/projectInfo/editProjectPartner.html?readOnly=${req.getParameter('readOnly')?if_exists}&projectInfo.id=${req.getParameter('projectInfo.id')?if_exists}&projectPartner.id="+id;
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
</script>