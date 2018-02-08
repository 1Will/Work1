<#--$Id:  -->
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('financeTypeSelector.title')}">
<base target="_self">
	 <@ww.form name="'listForm'" action="'financeTypeSelector'" method="'post'">
	 	<@ww.token name="searchfinanceTypesToken"/>
	 	<#include "./financeTypeSearcher.ftl"/>
	 	<@buttonBar>
	 		<@vsubmit value="'${action.getText('search')}'"/>
        </@buttonBar>
        <@list title="${action.getText('financeType.list')}" 
        	includeParameters="code|name" 
        	fieldMap="like:code|name" >
            <@vcolumn title="${action.getText('financeType.code')}" property="code" sortable="desc">
            	<a href="javascript: returnDialog(new Array(#{object.id}, '${object.name}','${object.yearDeprecitionScale}','${object.netSalvageScale}'));">${object.code}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('financeType.name')}" property="name" sortable="desc"/>  
            <@vcolumn title="${action.getText('financeType.yearDeprecitionScale')}" property="yearDeprecitionScale" />
            <@vcolumn title="${action.getText('financeType.deprecitionYearLimit')}" property="deprecitionYearLimit" />         
            <@vcolumn title="${action.getText('financeType.netSalvageScale')}" property="netSalvageScale" />
            <@vcolumn title="${action.getText('financeType.comment')}" property="comment" />       
        </@list>         
     </@ww.form>
</@htmlPage>