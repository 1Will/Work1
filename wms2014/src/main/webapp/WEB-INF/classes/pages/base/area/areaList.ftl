<#--$Id: areaList.ftl 8696 2007-11-28 08:06:34Z qsun $ -->
<#include "../../includes/macros.ftl" />
<@htmlPage>
    <@ww.form name="'listForm'" action="'searchAreas'" method="'post'">
        <@ww.token name="searchAreasToken"/>
        <#include "./areaSearcher.ftl" />
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" />
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/base/area/editArea.html"/>
            <@redirectButton value="print" url="${req.contextPath}/reports/area/hello.html"/>
        </@buttonBar>
        <@list title="${action.getText('area.list')}" includeParameters="code|name" fieldMap="like:code|name" >
            <@vlh.checkbox property="id" name="areaIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('area.code')}">
                <a href="editArea.html?area.id=#{object.id}">${object.code}</a>
            </@vcolumn>
             <@vcolumn title="${action.getText('area.name')}" property="name" sortable="desc"/>
             <@vcolumn title="${action.getText('creator')}" property="creator" />
             <@vcolumn title="${action.getText('createdTime')}" property="createdTime" />
             <@vcolumn title="${action.getText('lastOperator')}" property="lastOperator" />
             <@vcolumn title="${action.getText('lastModifiedTime')}" property="lastModifiedTime" />
        </@list>
        <#if !first>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.invalid')}${action.getText('area')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('invalidation')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"areaIds\", \"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
        </#if>
    </@ww.form>
</@htmlPage>