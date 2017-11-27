<#include "../../../includes/hco2011.ftl" />
<@framePage>
	<@ww.form name="'listForm'" namespace="'/productsManager'" action="'listSupplierProducts'" method="'post'">
		<@ww.token name="listSupplierProductsToken"/>
		<#if supplier?exists>
		<#if supplier.id?exists>
            <@ww.hidden name="'supplier.id'" value="#{supplier.id}"/>
        </#if>
        </#if>
        <@list title="" includeParameters="" fieldMap="like:" >
            <@vcolumn title="${action.getText('products.code')}" property="code">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('products.name')}" property="name">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('products.model')}" property="model">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('products.standard')}" property="standard">
            <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('products.pt.name')}" property="pt.name">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('products.etcPrice')}" property="etcPrice" format="#,###,##0.00">
            <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('products.remark')}" property="remark">
            <@alignLeft/>
            </@vcolumn>
        </@list>
    </@ww.form>
</@framePage>