<#include "../../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('regional.searcher')}">
	 <@ww.form name="'listForm'" namespace="'/regionalInfo'" action="'searchRegional'" method="'post'">
	 	 <@ww.token name="searchRegionalToken"/>
	 	<#include "regionalSearcher.ftl"/>
	 	 <@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
        	<@redirectButton value="${action.getText('new')}" url="editRegional.html"/>
        </@buttonBar>
         <@list title="${action.getText('regional.list')}"
         			includeParameters="code|name|storageGrade.id|warehouse.id|onlyValid|onlyInvalid" 
                   fieldMap="like:code|name" >
         	<@vlh.checkbox property="id" name="regionalIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('regional.code')}" property="code" sortable="asc"  >
		         <#if !object.disabled>
		            <a href="editRegional.html?regional.id=#{object.id}">${object.code}</a>
		            <#else>
		            ${object.code}
		         </#if>
                <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('regional.name')}" property="name"  sortable="asc" >
                <@alignLeft />
            </@vcolumn>
            
             <@vcolumn title="${action.getText('regional.storageGrade')}" property="storageGrade.value"  sortable="desc" >
                <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('regional.warehouse')}" property="warehouse.name"  sortable="desc" >
                <@alignLeft />
            </@vcolumn>
	  	</@list>
		<@buttonBar>
			<#if !first>
			<@eam2008_disabledOrEnabled_button message="${action.getText('regional')}" boxName="regionalIds" jsFunctionName="checkInvalidParms()"/> 
	       </#if>
		</@buttonBar>
	 </@ww.form>
</@htmlPage>