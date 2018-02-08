<#--$Id: productList.ftl 11319 2008-03-14 08:25:24Z wzou $ -->
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('countryList.title')}">
    <@ww.form name="'listForm'" action="'searchCountries'" method="'post'">
        <@ww.token name="searchProductsToken"/>
        <#include "./countrySearcher.ftl" />
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/base/country/editCountry.html"/>
        </@buttonBar>
        <@list title="${action.getText('country.list')}" includeParameters="code|name|onlyValid|onlyInvalid" fieldMap="like:code|name" >

            <@vlh.checkbox property="id" name="countryIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('country.code')}" property="code" sortable="desc">
            	<#if onlyValid>
                	<a href="editCountry.html?country.id=#{object.id}">${object.code}</a>
            	</#if>
            	<#if onlyInvalid>
            		${object.code}
            	</#if>
            </@vcolumn>
            <@vcolumn title="${action.getText('country.name')}" property="name" sortable="desc">
            	<@alignLeft/>
         </@vcolumn>            
        </@list>
        <#if !first>
        <@buttonBar>
	         <@eam2008_disabledOrEnabled_button message="${action.getText('country')}" boxName="countryIds" jsFunctionName="checkInvalidParms()"/>
	       </@buttonBar>
        </#if>
    </@ww.form>
</@htmlPage>