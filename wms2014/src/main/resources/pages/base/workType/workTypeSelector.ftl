<#--$Id: productSelector.ftl 8463 2007-11-20 08:14:36Z qsun $ -->
<#include "../../includes/macros.ftl" />
<@htmlPage title="${action.getText('workTypeSearcher.list')}">
<base target="_self">
    <@ww.form name="'listForm'" action="'workTypeSelector'" method="'post'">
        <@ww.token name="searchProductsToken"/>
        <#include "./workTypeSearcher.ftl" />
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" />
        </@buttonBar>
        <@list title="${action.getText('product.list')}" includeParameters="id|code|name" fieldMap="like:code|name" >
            <@vcolumn title="${action.getText('workType.code')}" property="code" sortable="desc">
            	<a href="javascript: returnDialog(new Array(#{object.id}, '${object.code}', '${object.name}', '${object.unitPrice}'));">${object.code}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('workType.name')}" property="name" sortable="desc"/>   
            <@vcolumn title="${action.getText('workType.unitPrice')}" property="unitPrice" sortable="desc"/>          
        </@list>
    </@ww.form>
</@htmlPage>