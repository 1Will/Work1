<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('productList.title')}">
	<@ww.form name="'listForm'" namespace="'/productsManager'" action="'seacherProducts'" method="'post'">
		<@ww.token name="seacherProductsToken"/>
		<#include "./productSearcher.ftl" />
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'productCheckBox'" value="'${productCheckBox?if_exists}'"/>
        <@buttonBar>
         <#if productCheckBox?exists>
          <@vsubmit value="'${action.getText('search')}'" onclick="'checkInvalidParms()'" />
         <input class="button" type="button" value="选择" onclick="return_product()"/>
         <#else>
           <@vsubmit value="'${action.getText('search')}'" onclick="'checkInvalidParms()'" />
			<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/productsManager/editProducts.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			<@redirectButton value="${action.getText('importProduct.button')}" url="${req.contextPath}/productsManager/toImportProduct.html"/>
			</#if>
          </#if>
        </@buttonBar>
        <@list title="${action.getText('productsList')}" includeParameters="products.code|products.name|products.model|productCheckBox|products.standard|pt.id|product_source_ID.id|supplier.id|onlyInvalid|onlyValid" fieldMap="like:products.code|products.name|products.model|products.standard" >
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
                <a href="editProducts.html?products.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
                <@alignLeft/>
            </@vcolumn>
            </#if>
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
        </@list>
	     <#if !first>
        <#if !(action.isReadOnly())>
        <@buttonBar>
         <#if !productCheckBox?exists>
          <@crm_disabledOrEnabled_button message="${action.getText('products')}" boxName="productsIds" jsFunctionName="checkInvalidParms()"/>
         </#if>
        </@buttonBar>
        </#if>
        </#if>
    </@ww.form>
    
</@htmlPage>
<script language="javascript">
 function return_product(){
 var name_="";
  var selector = document.getElementsByName("productsIds");
   var length = selector.length;
   if(length){
           for(var i=0;i<length;i++){
           if(selector[i].checked == true){
              var tempName = selector[i].value;
              if(name_==""){
              name_ =tempName;
              }else{
              name_+=","+tempName;
              }
              
              }
              }
              }
    
    returnDialog(new Array(name_));
    
    }
</script>