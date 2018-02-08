<#--$Id: countrySelector.ftl 7375 2007-09-14 09:45:20Z zbzhang $ -->
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('country.title')}">
<base target="_self">
    <@ww.form name="'listForm'" action="'countrySelector'" method="'post'">
        <@ww.token name="searchCountriesToken"/>
        <#include "./countrySearcher.ftl" />
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" />
        </@buttonBar>
        <@list title="${action.getText('country.list')}" includeParameters="id|code|name" fieldMap="like:code|name" >
            <#--
            <@vcolumn>
                <@vlh.attribute name="width" value="30" />
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('country.code')}" property="code" sortable="desc">
                <a href="javascript: returnDialog(new Array(#{object.id}, '${object.name}', '${object.code}'));">${object.code}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('country.name')}" property="name" sortable="desc"/>
        </@list>
    </@ww.form>
</@htmlPage>