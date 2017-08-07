<#include "../includes/hco2011.ftl" />

<@framePage>
	<@ww.form name="'listForm'" namespace="'/projectInfo'" action="'searchProCus'" method="'post'">
		<@ww.token name="searchProjectInfoCustomerToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
			<@ww.hidden name="'projectInfo.id'" value="'${projectInfoId?if_exists}'"/>
			 <#assign itemNo=1/>
			 <#assign returnName='replaceWord'>
 	        <@list title="" 
        includeParameters="projectInfo.id|readOnly|onlyInvalid|onlyValid|" 
        fieldMap="" >
            <#if !(action.isReadOnly())>
	            <@vlh.checkbox property="id" name="projectInfoCustomerIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
	             <@vcolumn title="${action.getText('id')}" property="id"  >
	              <a href="javascript:editProCus_OpenDialog_update('#{object.id}');">#{itemNo}</a> 
	            <@alignLeft attributes="width:100;"/>
	            </@vcolumn >
	            <#assign itemNo=itemNo + 1/>
	             <@vcolumn title="${action.getText('projectInfo.customerInfoName')}" property="customerInfo.name" sortable="desc">
	            <@alignLeft/>
	            </@vcolumn>
	             <@vcolumn title="${action.getText('cusOutline')}" property="outline"   >
	            <@alignLeft/>
	            </@vcolumn>
        </@list>
         <#if !first>
          <#if projectInfoId?exists>
			<@buttonBar>
				<#if !(action.isReadOnly())>
					<@vbutton class="button" name="${action.getText('从客户列表中选择')}" value="${action.getText('从客户列表中选择')}" onclick="editProCus_OpenDialog();"/>
					<#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('projectInfoContractIds.info')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDeletes(\"projectInfoCustomerIds\", \"${confirmMessage}\");'"/>
		                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		            </@vsubmit>
	            </#if>
			</@buttonBar>
			</#if>
		</#if>
    </@ww.form>
</@framePage>
<script>
	function editProCus_OpenDialog(){
	   var url="";
	   url= "${req.contextPath}/projectInfo/editProCus.html?projectInfo.id=${projectInfoId?if_exists}";
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	 function editProCus_OpenDialog_update(id){
	   var url="";
	   url= "${req.contextPath}/projectInfo/editProCus.html?projectInfo.id=${projectInfoId?if_exists}&projectInfoCustomer.id="+id;
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	 
</script>