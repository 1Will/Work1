<#include "../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('projectInfo')}">
	<@ww.form name="'listForm'" namespace="'/projectInfo'" action="'searchProjectInfo'" method="'post'">
		<@ww.token name="searchProjectInfoToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#include "./projectInfoSearcher.ftl" />
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'"  />
			<#if !backVisitCheckBox?exists>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/projectInfo/editProjectInfo.html"/>
			</#if>
			</#if>
        </@buttonBar>
        <@list title="${action.getText('projectInfoList')}" 
        includeParameters="code|name|customer.name|customer.id|contact.name|controller.name|state.id|readOnly|onlyInvalid|onlyValid" 
        fieldMap="like:code|name|customer.name|contact.name|controller.name" >
          <#if !contactArchivesFlag?exists>
            <#if !(action.isReadOnly())>
          <#if !backVisitCheckBox?exists>
	            <@vlh.checkbox property="id" name="projectInfoIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
	      </#if>
            </#if>
             </#if>
             <#if contactArchivesFlag?exists>
              <@vcolumn title="${action.getText('code')}" property="code" sortable="desc">
             <a href="javascript: returnDialog(new Array(#{object.id}, '${object.name}',#{object.contact.id},'${object.contact.name}'));"
                 title="${object.code}%">${object.code}</a>
            <@alignLeft/>
            </@vcolumn>
            <#else>
              <@vcolumn title="${action.getText('code')}" property="code" sortable="desc">
              <#if !backVisitCheckBox?exists>
             	<a href="editProjectInfo.html?projectInfo.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}"
                 title="${object.code}%">${object.code}</a>
                <#else> 
                <a href="javascript: returnDialog(new Array(#{object.id}, '${object.name}',#{object.contact.id},'${object.contact.name}'));"
                 title="${object.code}%">${object.code}</a>
                </#if>
            <@alignLeft/>
            </@vcolumn>
             </#if>
           
            <@vcolumn title="${action.getText('name')}" property="name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('projectInfo.customerInfoName')}" property="customer.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('projectInfo.contact')}" property="contact.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
           
            <@vcolumn title="${action.getText('projectInfo.controller')}" property="controller.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('projectInfo.createUser')}" property="createUser.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('outline')}" property="outline" sortable="desc"  >
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('state.name')}" property="state.name" sortable="desc"  >
            <@alignCenter attributes="width:110;"/>
            </@vcolumn>
          
        </@list>
       <#if !contactArchivesFlag?exists>
	    <#if !first>
	        <#if !(action.isReadOnly())>
	        <#if !backVisitCheckBox?exists>
		        <@buttonBar>
		        <@crm_disabledOrEnabled_Nodelete_button message="${action.getText('项目管理')}" boxName="projectInfoIds" jsFunctionName="checkInvalidParms()"/>
		        </@buttonBar>
	        </#if>
	        </#if>
	          </#if>
        </#if>
    </@ww.form>
</@htmlPage>