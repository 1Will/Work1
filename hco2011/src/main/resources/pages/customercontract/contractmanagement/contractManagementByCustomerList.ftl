<#include "../../includes/hco2011.ftl" />

<@framePage title="">
	<@ww.form name="'listForm'" action="'searchContractManagementAction'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchContractManagementActionToken"/>
        <@list title="" 
            includeParameters="contractManagement.code|contractManagement.contractName|contractManagement.customerInfo.name
            |contractManagement.linkman.name|contractManagement.saleman.name|contractManagement.project.name|contractManagement.deparment.name
            |contractManagement.applyProduc|contractManagement.type.id|contractManagement.state.id|contractManagement.severityDegree.id|contractManagement.question
            |contractManagement.resolveProject|contractManagement.innerPrompt|contractManagement.remark|readOnly|onlyInvalid|onlyValid|customerInfo.id|contactArchives.id|" 
        	fieldMap="like:" >
        	
          	<@vcolumn title="${action.getText('contractManagement.code')}" property="code" sortable="desc" >
          	<a href="javascript:contract_OpenDialog('#{object.id}')">
          		${object.code?if_exists}
				<@alignLeft/>
			</a>
            </@vcolumn>
            
           <@vcolumn title="${action.getText('contractManagement.contractName')}" property="contractName" sortable="desc">
            ${object.contractName?if_exists}
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contractManagement.customerInfo.name')}" property="customerInfo.name" sortable="desc">
            	<#if object.customerInfo?exists>
	            	 ${object.customerInfo.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
            <#-- 项目名称添加项 -->
			<@vcolumn title="${action.getText('contractManagement.project.name')}" property="project.name" sortable="desc">
				<#if object.project?exists>
	            	 ${object.project.name?if_exists}
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('contractManagement.linkman.name')}" property="linkman.name" sortable="desc">
				<#if object.linkman?exists>
	            	 ${object.linkman.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contractManagement.saleman.name')}" property="saleman.name" sortable="desc">
            	<#if object.saleman?exists>
	            	 ${object.saleman.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('contractManagement.deparment.name')}" property="deparment.name" sortable="desc">
           	 <#if object.deparment?exists>
	            	 ${object.deparment.name?if_exists}            	
            </#if>
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contractManagement.ciemdinghTime')}" property="ciemdinghTime" format="yyyy-MM-dd" sortable="desc">
     			<@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contractManagement.contractType')}" property="contractType.name" sortable="desc">
           	 <#if object.contractType?exists>
	            	 ${object.contractType.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contractManagement.state')}" property="state.name" sortable="desc">
           	 <#if object.state?exists>
	            	 ${object.state.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
        </@list>
    </@ww.form>
</@framePage>
<script language="javascript">
	function contract_OpenDialog(id){
			 var url = "${req.contextPath}/contractManagement/editContractManagementAction.html?contractManagement.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=popWindowFlag";
	  		 //popupModalDialog(url, 800, 600);
	  		 openNewWindow(url);
	 }
</script>