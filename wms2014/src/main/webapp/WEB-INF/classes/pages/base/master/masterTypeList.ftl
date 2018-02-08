<#include "../../includes/macros.ftl" />
<@htmlPage  title="${action.getText('masterType.title')}">
    <@ww.form name="'listForm'" action="'searchMasterType'" method="'post'">
        <@ww.token name="searchMasterTypeToken"/>
        <#include "./masterSearcher.ftl" />
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" />
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/base/master/editMasterType.html"/>
        </@buttonBar>
		<@list title="${action.getText('masterType.list')}" includeParameters="id|name" fieldMap="like:id|name" >
            <@vcolumn title="${action.getText('masterType.code')}" sortable="desc">
                <a href="editMasterType.html?master.id=#{object.id}">${object.code}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('masterType.name')}" property="name" sortable="desc"/>
            <@vcolumn title="${action.getText('masterType.comment')}" property="comment" />
        </@list>
        <#--
        <#if !first>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.delete')}" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"masterTypeIds\", \"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
        </#if>
        -->
     </@ww.form> 
</@htmlPage>