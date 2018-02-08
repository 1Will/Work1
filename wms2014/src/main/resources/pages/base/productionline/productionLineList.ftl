<#--$Id: productionLineList.ftl 11319 2008-03-14 08:25:24Z wzou $ -->
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('productionLineList.title')}">
    <@ww.form name="'listForm'" action="'searchProductionlines'" method="'post'">
        <@ww.token name="searchProductlinesToken"/>
        <#include "./productionLineSearcher.ftl" />
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>  
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/base/productionLine/editProductionLine.html"/>
        </@buttonBar>
        <@list title="${action.getText('productionLine.list')}" includeParameters="exclude_disabled|code|name|department.id" fieldMap="like:code|name" >
            <@vlh.checkbox property="id" name="productionLineIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('productionLine.code')}" property="code" sortable="desc">
                <a href="editProductionLine.html?productionLine.id=#{object.id}">${object.code}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('productionLine.name')}" property="name" sortable="desc">
            	<@alignLeft/>
         	</@vcolumn>  
            <@vcolumn title="${action.getText('department')}" property="department.name">
            	<@alignLeft/>
         	</@vcolumn>          
        </@list>
        <#if !first>
        <#--<@buttonBar>
            <@vsubmit name="'disable'" value="'${action.getText('disable')}'">
                <@ww.param name="'onclick'" value="'return validateDelete(confirmInvalids(\"productionLineIds\",\"${action.getText('confirm.invalid')}\"), checkInvalidParms())'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>-->
        <@buttonBar>
	         <@eam2008_disabledOrEnabled_button message="${action.getText('productionLineList')}" boxName="productionLineIds" jsFunctionName="checkInvalidParms()"/>
	       </@buttonBar>
        </#if>
    </@ww.form>
    <script language="javascript">
    </script>    
</@htmlPage>