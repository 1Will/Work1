<#include "../../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('warehouse.searcher')}">
	 <@ww.form name="'listForm'" action="'searchWarehouse'" method="'post'">
	 	 <@ww.token name="searchWarehouseToken"/>
	 	<#include "warehouseSearcher.ftl"/>
	 	 <@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
        	<@redirectButton value="${action.getText('new')}" url="editWarehouse.html"/>
        </@buttonBar>
         <@list title="${action.getText('warehouse.list')}"
         			includeParameters="code|name|user|onlyValid|onlyInvalid" 
                   fieldMap="like:code|name|user" >
         	<@vlh.checkbox property="id" name="warehouseIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('warehouse.code')}"property="code" sortable="desc" >
		         <#if !object.disabled>
		            <a href="editWarehouse.html?warehouse.id=#{object.id}">${object.code}</a>
		            <#else>
		            ${object.code}
		         </#if>
                <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('warehouse.name')}" property="name" sortable="desc">
                <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('级别')}" property="storageGrade.value" sortable="desc">
				<@alignLeft />
            </@vcolumn>     
            <@vcolumn title="${action.getText('warehouse.user')}" property="user.name" sortable="desc">
                <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('warehouse.tel')}" property="tel" sortable="desc">
                <@alignLeft />
            </@vcolumn>
	  	</@list>
		<@buttonBar>
			<#if !first>
			<@eam2008_disabledOrEnabled_button message="${action.getText('warehouse')}" boxName="warehouseIds" jsFunctionName="checkInvalidParms()"/> 
	       </#if>
		</@buttonBar>
	 </@ww.form>
</@htmlPage>