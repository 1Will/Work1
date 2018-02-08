<#--$Id:  -->
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('lubricationOilSelector.title')}">
<base target="_self">
	 <@ww.form name="'listForm'" action="'lubricationOilSelector'" method="'post'">
	 	<@ww.token name="lubricationOilSelectorToken"/>
	 	<#include "./lubricationOilSearcher.ftl"/>
	 	<@buttonBar>
	 		<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
        </@buttonBar>
        <@list title="${action.getText('lubricationOil.list')}" includeParameters="code|name|category.code" fieldMap="like:code|name|category.code" >
            <#assign category=''/>
            <#if object.category?exists>
              <#assign category="${object.category.value?if_exists}"/>
            </#if>
            <#assign measureUnit=''/>
            <#if object.measureUnit?exists>
              <#assign measureUnit="${object.measureUnit.value?if_exists}"/>
            </#if>
            <@vcolumn title="${action.getText('lubricationOil.code')}" property="code" sortable="desc">
            	<a href="javascript: returnDialog(new Array(#{object.id}, '${object.code}','${object.name}','${measureUnit}','${category}'));">${object.code}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('lubricationOil.name')}" property="name" sortable="desc"/>
            <@vcolumn title="${action.getText('lubricationOil.category')}" property="category.value"/>
            <@vcolumn title="${action.getText('lubricationOil.measureUnit')}" property="measureUnit.value"/>      
        </@list>         
     </@ww.form>
</@htmlPage>