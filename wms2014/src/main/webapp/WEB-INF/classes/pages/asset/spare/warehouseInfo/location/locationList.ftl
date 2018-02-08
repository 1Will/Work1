<#include "../../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('title')}">
	<@ww.form name="'listForm'" action="'searchLocation'" method="'post'">
	 <@ww.token name="searchLocationToken"/>
	<#include "locationSearcher.ftl"/>
	 	 <@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
        	<@redirectButton value="${action.getText('new')}" url="editLocation.html"/>
        </@buttonBar>
     <@list title="${action.getText('list')}"
         			includeParameters="code|warehouse|regional|locationType|bearload|status|onlyValid|onlyInvalid" 
                   fieldMap="like:code" >
         	<@vlh.checkbox property="id" name="locationIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('code')}" property="code" sortable="asc"  >
		         <#if !object.disabled>
		            <a href="editLocation.html?location.id=#{object.id}">${object.code}</a>
		            <#else>
		            ${object.code}
		         </#if>
                <@alignLeft />
            </@vcolumn>
              <@vcolumn title="${action.getText('仓库级别')}" property="storageGrade.value"  sortable="asc" >
                <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('warehouse')}" property="warehouse.name"  sortable="asc" >
                <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('regional')}" property="regional.name"  sortable="desc" >
                <@alignLeft />
            </@vcolumn>
            
            <#-- 仓库类型 -->
            <#assign locationType=""/>
            <#if object.locationType?exists>
              <#assign locationType=object.locationType.value/>
            </#if>
            <@vcolumn title="${action.getText('locationType')}" property="locationType"  sortable="desc" >
                ${locationType}
                <@alignLeft />
            </@vcolumn>
            
            <#-- 承载类型 -->
            <#assign bearload=""/>
            <#if object.bearload?exists>
              <#assign bearload=object.bearload.value/>
            </#if>
            <@vcolumn title="${action.getText('bearload')}" property="bearload"  sortable="desc" >
                ${bearload}
                <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('maxWeight')}" property="maxWeight"  sortable="desc" >
                <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('status')}" property="status"  sortable="desc" >
            	<#if object.status=="USED">
            		${action.getText('USED')}
            		<#else>
            		${action.getText('NON_USE')}
            	</#if>
                <@alignLeft />
            </@vcolumn>
	  	</@list>
		<@buttonBar>
			<#if !first>
			<@eam2008_disabledOrEnabled_button message="${action.getText('location')}" boxName="locationIds" jsFunctionName="checkInvalidParms()"/> 
	       </#if>
		</@buttonBar>
     </@ww.form>
</@htmlPage>