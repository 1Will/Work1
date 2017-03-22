<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('productList.title')}">
	<@ww.form name="'listForm'" namespace="'/com'" action="'seacherProductsWindow'" method="'post'">
		<@ww.token name="seacherProductsWindowToken"/>
		<#include "./productSearcher.ftl" />
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'checkInvalidParms()'" />
        </@buttonBar>
        <@list title="${action.getText('productsList')}" includeParameters="products.code|products.name|products.model|products.standard|pt.id|product_source_ID.id|supplier.id|onlyInvalid|onlyValid" fieldMap="like:products.code|products.name|products.model|products.standard" >
            <#if !object.disabled>
	        	 <@vcolumn title="${action.getText('products.code')}" property="code" sortable="asc">
		            <a href="javascript: returnDialog(new Array(#{object.id},'${object.code}','${object.name}','#{object.salePrice}'));">
		            	${object.code}
		            </a>
		            <@alignLeft/>
		        </@vcolumn>
	            <@vcolumn title="${action.getText('products.name')}" property="name" sortable="desc">
	            <@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title=" ${action.getText('products.model')} " property="model" sortable="desc">
	            <@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title=" ${action.getText('products.standard')} " property="standard" sortable="desc">
	            <@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('products.etcPrice')}" property="etcPrice" format="#,###,##0.00" sortable="desc">
	            <@alignRight/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('products.salePrice')}" property="salePrice" format="#,###,##0.00" sortable="desc">
	            <@alignRight/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('products.salelimit')}" property="salelimit" sortable="desc">
	            <@alignRight/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('products.pt.name')}" property="pt.name" sortable="desc">
	            <@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('products.productSource')}" property="productSource" sortable="desc">
	            <@alignLeft/>
	            </@vcolumn>
	            <#assign isSupplier=""/>
	            <#if object.supplier?exists>
	            	<#assign isSupplier="${object.supplier.name}">
	            </#if>
	            <@vcolumn title="${action.getText('products.supplier')}" property="supplier" sortable="desc">
	            	${isSupplier?if_exists}
	            <@alignLeft/>
	            </@vcolumn>
	            <#assign isNoM=""/>
	            <#if object.isNoMain>
	            	<#assign isNoM="${action.getText('products.isNoMain.yes')}">
	            <#else>
	           	 	<#assign isNoM="${action.getText('products.isNoMain.no')}">
			    </#if>
			    <@vcolumn title="${action.getText('products.isNoMain')}" property="isNoMain" sortable="desc">
					${isNoM?if_exists}
				<@alignLeft/>
				</@vcolumn>
			</#if>
        </@list>
    </@ww.form>
    
</@htmlPage>