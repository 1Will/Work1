<#include "../includes/hco2011.ftl" />

<@framePage title="${action.getText('projectInfo')}">
	<@ww.form name="'listForm'" namespace="'/projectInfo'" action="'searchProjectByCus'" method="'post'">
		<@ww.token name="ProjectByCusToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'customer.id'" value="'${customerId?if_exists}'"/>
        <#assign returnName='replaceWord'>
        <@list title="${action.getText('projectInfoList')}" 
        includeParameters="customer.id|readOnly|onlyInvalid|onlyValid|" 
        fieldMap="" >
          <#if !contactArchivesFlag?exists>
            <#if !(action.isReadOnly())>
          <#if !backVisitCheckBox?exists>
	            <@vlh.checkbox property="id" name="projectInfoIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
	      </#if>
            </#if>
             </#if>
            <@vcolumn title="${action.getText('code')}" property="code" sortable="desc">
                <a href="javascript: returnDialog(new Array(#{object.id}, '${object.name}',#{object.contact.id},'${object.contact.name}'));"
                 title="${object.code}%">${object.code}</a>
            <@alignLeft/>
            </@vcolumn>
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
		            	document.write(s.slice(0,18)+"...");
		            </script>
	            </span>
            
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('state.name')}" property="state.name" sortable="desc"  >
            <@alignCenter attributes="width:50;"/>
            </@vcolumn>
            <@vcolumn title="${action.getText('回访次数')}" property="backVisitSum" sortable="desc">
            	<a href="javascript:visitBack_OpenDialog(#{object.customer.id?if_exists})" >${object.customer.backVisitSum?if_exists}</a>
            <@alignLeft attributes="width:50;"/>
            </@vcolumn>
             <@vcolumn title="${action.getText('告警状态')}" property="customer.state.name" sortable="desc">
            <@alignLeft/>
             </@vcolumn>
            <@vcolumn title="${action.getText('最后回访时间')}" property="customer.nearestBackVisitDate" sortable="desc" format="yyyy-MM-dd" >
            <@alignCenter attributes="width:100;"/>
             </@vcolumn>
          
        </@list>
    </@ww.form>
</@framePage>