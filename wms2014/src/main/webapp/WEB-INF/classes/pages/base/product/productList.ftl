<#--$Id: productList.ftl 11319 2008-03-14 08:25:24Z wzou $ -->
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('productList.title')}">
    <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
        <@ww.token name="searchProductsToken"/>
        <#include "./productSearcher.ftl" />
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/base/product/editProduct.html"/>
        </@buttonBar>
        <@list title="${action.getText('product.list')}" includeParameters="code|name|onlyValid|onlyInvalid" fieldMap="like:code|name" >

            <@vlh.checkbox property="id" name="productIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('product.productNo')}" property="productNo" sortable="desc">
                <a href="editProduct.html?product.id=#{object.id}">${object.productNo}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('product.name')}" property="name" sortable="desc">
            	<@alignLeft/>
         </@vcolumn>            
        </@list>
        <#if !first>
        <@buttonBar>
	         <@eam2008_disabledOrEnabled_button message="${action.getText('product')}" boxName="productIds" jsFunctionName="checkInvalidParms()"/>
	       </@buttonBar>
        </#if>
    </@ww.form>
    <script language="JavaScript" type="text/JavaScript"> 
      window.onload = function () {
	  }
	    function validateInvalid(delFun, checkFun) {
         if (delFun) {
         	checkFun;
         	return true;
         }
         return false;
      }
    </script>
</@htmlPage>