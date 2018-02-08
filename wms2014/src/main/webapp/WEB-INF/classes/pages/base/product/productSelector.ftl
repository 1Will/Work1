<#--$Id: productSelector.ftl 8463 2007-11-20 08:14:36Z qsun $ -->
<#include "../../includes/macros.ftl" />
<@htmlPage>
<base target="_self">
    <@ww.form name="'listForm'" action="'searchProductSelector'" method="'post'">
        <@ww.token name="searchProductsToken"/>
        <#include "./productSearcher.ftl" />
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" />
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/base/product/editProduct.html"/>
        </@buttonBar>
        <@list title="${action.getText('product.list')}" includeParameters="id|code|name" fieldMap="like:code|name" >
            <@vcolumn title="${action.getText('product.productNo')}">
            	<a href="javascript: returnDialog(new Array(#{object.id}, '${object.name}', '${object.productNo}'));">${object.productNo}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('product.name')}" property="name" sortable="desc"/>            
        </@list>
        <#if !first>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.invalid')}${action.getText('product')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('invalidation')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"productIds\", \"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
        </#if>
    </@ww.form>
</@htmlPage>