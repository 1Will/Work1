<#--$Id:  $ -->
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('lubricationOilList.title')}">
    <@ww.form name="'listForm'" action="'searchLubricationOils'" method="'post'">
        <@ww.token name="searchLubricationOilsToken"/>
        <#include "./lubricationOilSearcher.ftl" />
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>  
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/base/lubricationOil/editLubricationOil.html"/>
        </@buttonBar>
        <@list title="${action.getText('lubricationOil.list')}" includeParameters="code|name|category.code" fieldMap="like:code|name" >
            <@vlh.checkbox property="id" name="lubricationOilIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('lubricationOil.code')}" property="code" sortable="desc">
                <a href="editLubricationOil.html?lubricationOil.id=#{object.id}">${object.code}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('lubricationOil.name')}" property="name" sortable="desc">
            	<@alignLeft/>
         	</@vcolumn>
            <@vcolumn title="${action.getText('lubricationOil.category')}" property="category.value">
            	<@alignLeft/>
         	</@vcolumn>
            <@vcolumn title="${action.getText('lubricationOil.measureUnit')}" property="measureUnit.value">
            	<@alignLeft/>
         	</@vcolumn>   
        </@list>
        <#if !first>
	        <#--<@buttonBar>
	            <#assign confirmMessage = "${action.getText('confirm.inValid')}${action.getText('lubricationOil')}?" />
	            <@vsubmit name="'disabled'" value="'${action.getText('invalidation')}'">
	                <@ww.param name="'onclick'" value="'return confirmInvalids(\"lubricationOilIds\", \"${confirmMessage}\");'"/>
	                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            </@vsubmit>
	        </@buttonBar>-->
	        <@buttonBar>
	         	<@eam2008_disabledOrEnabled_button message="${action.getText('lubricationOil')}" boxName="lubricationOilIds" jsFunctionName="checkInvalidParms()"/>
	       </@buttonBar>
        </#if>
    </@ww.form>
    <script language="JavaScript" type="text/JavaScript"> 
      window.onload = function () {
	    if ('${includeDisabled?string}' == 'true') {
	      document.getElementById("includeDisabled").checked=true;
	    }
	  }
    </script>
</@htmlPage>