<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('products.productType.manager')}">

	<@ww.form name="'listForm'" namespace="'/productTypeManager'" action="'seacherProductTypes'" method="'post'">
		<@ww.token name="seacherProductTypesToken"/>
		<#include "./productTypeSearcher.ftl" />
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@buttonBar>
        	<@vsubmit value="'${action.getText('search')}'" onclick="'checkInvalidParms()'" />
        	<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/productTypeManager/editProductType.html"/>
			</#if>
        </@buttonBar>
        
        <@list title="${action.getText('productTypeList')}" includeParameters="id|code|name|productType.id|parentPT.id|nonParentPT|onlyInvalid|onlyValid" fieldMap="like:code|name|nonParentPT">
            <@vlh.checkbox property="id" name="ptIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <#if (object.disabled)>
	            <@vcolumn title="${action.getText('productType.code')}"  property="code" sortable="desc">
	             ${object.code}
	             <@alignLeft/>
	            </@vcolumn>
            <#else>
            <@vcolumn title="${action.getText('productType.code')}"  property="code" sortable="desc">
                <a href="editProductType.html?productType.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
                <@alignLeft/>
            </@vcolumn>
            </#if>
            <@vcolumn title="${action.getText('productType.name')}" property="name" sortable="desc"><@alignLeft/></@vcolumn>
            <#assign parentPT=""/>
             <#if object.parentPT?exists>
               <#assign parentPT="${object.parentPT.name}"/>
             <#else>
               <#assign parentPT="${action.getText('parentPT.none')}"/>
             </#if>
             <@vcolumn title="${action.getText('parentTP.name')}" property="parentPT" sortable="desc">
               ${parentPT}
               <@alignLeft/>
             </@vcolumn>
        </@list>
	     <#if !first>
        <#if !(action.isReadOnly())>
        <@buttonBar>
          <@crm_disabledOrEnabled_button message="${action.getText('productType')}" boxName="ptIds" jsFunctionName="checkInvalidParms()"/>
        </@buttonBar>
        </#if>
        </#if>
    </@ww.form>
  
</@htmlPage>