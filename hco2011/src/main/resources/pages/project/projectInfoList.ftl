<#include "../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('projectInfo')}">
	<@ww.form name="'listForm'" namespace="'/projectInfo'" action="'searchProjectInfo'" method="'post'">
		<@ww.token name="searchProjectInfoToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'backVisitCheckBox'" value="'${backVisitCheckBox?if_exists}'"/>
		<#include "./projectInfoSearcher.ftl" />
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'"  />
			<#if backVisitCheckBox?exists&&backVisitCheckBox=='backVisitCheckBox'>
				<@ww.hidden name="'customer.id'" value="'${req.getParameter('customer.id')?if_exists}'"/>
			<#else>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/projectInfo/editProjectInfo.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			</#if>
			</#if>
        </@buttonBar>
        <#assign returnName='replaceWord'>
        <@list title="${action.getText('projectInfoList')}" 
        includeParameters="code|name|customer.name|customer.id|contact.name|backVisitCheckBox|controller.name|state.id|readOnly|onlyInvalid|onlyValid" 
        fieldMap="like:code|name|customer.name|contact.name|controller.name" >
          <#if !contactArchivesFlag?exists>
            <#if !(action.isReadOnly())>
          <#if backVisitCheckBox?exists&&backVisitCheckBox=='backVisitCheckBox'>
	      <#else>
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
            <@alignCenter/>
            </@vcolumn>
            <#else>
              <@vcolumn title="${action.getText('code')}" property="code" sortable="desc">
              <#if backVisitCheckBox?exists && backVisitCheckBox=='backVisitCheckBox'>
                <a href="javascript: returnDialog(new Array(#{object.id}, '${object.name}',#{object.contact.id},'${object.contact.name}'));"
                 title="${object.code}%">${object.code}</a>
                <#else> 
             	<a href="editProjectInfo.html?projectInfo.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}"
                 title="${object.code}%">${object.code}</a>
                </#if>
            <@alignLeft/>
            </@vcolumn>
             </#if>
           
            <@vcolumn title="${action.getText('name')}" property="name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('projectInfo.customerInfoName')}" property="customer.name" sortable="desc">
             <a href="javascript:customer_OpenDialog(#{object.customer.id?if_exists})" title="完整度：${object.customer.customerInfoIntegrity?if_exists}%; 熟悉程度：${object.customer.customerType.name?if_exists}">${object.customer.name?if_exists}</a>
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('projectInfo.contact')}" property="contact.name" sortable="desc">
            <a href="javascript:contactArchives_OpenDialog(<#if (object.contact?exists)>#{object.contact.id?if_exists}</#if>)"><#if (object.contact?exists)>${object.contact.name}</#if></a>
            <@alignLeft/>
            </@vcolumn>
           
            <@vcolumn title="${action.getText('projectInfo.controller')}" property="controller.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('projectInfo.createUser')}" property="createUser.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('outline')}" property="outline" sortable="desc"  >
            
            <#assign returnName=returnName +'replaceWord'>
             <@ww.hidden name="'${returnName}'" value="'${object.outline?if_exists}'"/>
	            <span title="${object.outline?if_exists}">
		            <script>
		            	var s = getObjByName('${returnName}').value;
		            	s=s.replace(/[\r\n]/g, "");
		            	if(s.length>18){
		            	document.write(s.slice(0,18)+"...");
		            	}else if(s.length >0){
		            	document.write(s);
		            	}else{
		            	}
		            </script>
	            </span>
            
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('state.name')}" property="state.name" sortable="desc"  >
            <@alignLeft attributes="width:50;"/>
            </@vcolumn>
            <@vcolumn title="${action.getText('回访次数')}" property="backVisitSum" sortable="desc">
            	<a href="javascript:visitBack_OpenDialog(#{object.customer.id?if_exists})" >${object.customer.backVisitSum?if_exists}</a>
            <@alignRight attributes="width:50;"/>
            </@vcolumn>
             <@vcolumn title="${action.getText('告警状态')}" property="customer.state.name" sortable="desc">
            <@alignLeft/>
             </@vcolumn>
            <@vcolumn title="${action.getText('最后回访时间')}" property="customer.nearestBackVisitDate" sortable="desc" format="yyyy-MM-dd" >
            <@alignLeft attributes="width:100;"/>
             </@vcolumn>
          
        </@list>
        <#--
       <#if !contactArchivesFlag?exists>
	    <#if !first>
	        <#if !(action.isReadOnly())>
	        <#if backVisitCheckBox?exists&&backVisitCheckBox=='backVisitCheckBox'>
	        <#else>
		        <@buttonBar>
		        <@crm_disabledOrEnabled_Nodelete_button message="${action.getText('项目管理')}" boxName="projectInfoIds" jsFunctionName="checkInvalidParms()"/>
		        </@buttonBar>
	        </#if>
	        </#if>
	          </#if>
        </#if>
        -->
    </@ww.form>
</@htmlPage>