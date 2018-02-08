<#--
	Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->

<#-- $Id: productList.ftl 2009-11-27 11:00:35Z wliu $ -->

<#include "../../../includes/crm.ftl" />

<@htmlPage title="${action.getText('products.manager')}">
	<@ww.form name="'listForm'" namespace="'/productsManager'" action="'seacherProducts'" method="'post'">
		<@ww.token name="seacherProductsToken"/>
		<#include "./productSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'checkInvalidParms()'" />
			<#if !(action.isReadOnly())>
			<@redirectButton value="${'新建'}" url="${req.contextPath}/productsManager/editProducts.html"/>
			</#if>
        </@buttonBar>
        <#--  -->
        <@list title="${action.getText('productsList')}" includeParameters="products.code|products.name|products.model|products.standard|pt.id|onlyInvalid|onlyValid" fieldMap="like:products.code|products.name|products.model|products.standard" >
            <@vlh.checkbox property="id" name="productsIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
             <#if (object.disabled)>
	            <@vlh.column title="${action.getText('products.code')}"  property="code" sortable="desc">
	             ${object.code}
	             <@alignLeft/>
	            </@vlh.column>
            <#else>
            <@vcolumn title="${action.getText('products.code')}" property="code" sortable="desc">
                <a href="editProducts.html?products.id=#{object.id}">${object.code}</a>
                <@alignLeft/>
            </@vcolumn>
            </#if>
            <@vcolumn title="${action.getText('products.name')}" property="name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('products.model')}" property="model" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('products.standard')}" property="standard" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('products.etcPrice')}" property="etcPrice" sortable="desc">
            </@vcolumn>
            <@vcolumn title="${action.getText('products.salePrice')}" property="salePrice" sortable="desc">
            </@vcolumn>
            <@vcolumn title="${action.getText('products.salelimit')}" property="salelimit" sortable="desc">
            </@vcolumn>
            <@vcolumn title="${action.getText('products.pt.name')}" property="pt.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('products.productSource')}" property="productSource" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('products.supplier')}" property="supplier" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('products.isNoMain')}" property="isNoMain" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
        </@list>
	     <#if !first>
        <#if !(action.isReadOnly())>
        <@buttonBar>
          <@police_disabledOrEnabled_button message="${action.getText('products')}" boxName="productsIds" jsFunctionName="checkInvalidParms()"/>
        </@buttonBar>
        </#if>
        </#if>
    </@ww.form>
</@htmlPage>