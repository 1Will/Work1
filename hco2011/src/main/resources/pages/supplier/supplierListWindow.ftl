<#include "../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('supplier.title')}">
	<@ww.form name="'listForm'" namespace="'/supplierManager'" action="'seacherSupplier'" method="'post'">
		<@ww.token name="seacherSupplierToken"/>
		<#include "./supplierSearcher.ftl" />
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'checkInvalidParms()'" />
			<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/supplierManager/editSupplier.html"/>
			</#if>
        </@buttonBar>
        <@list title="${action.getText('supplierList')}" 
        	   includeParameters="supplier.supplierNo|supplier.name|supplier.supplierType|supplier.tradeType|supplier.companyType|onlyInvalid|onlyValid" 
        	   fieldMap="like:supplier.supplierNo|supplier.name" >
        	<#-- 
            <@vlh.checkbox property="id" name="supplierIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            -->
             <#if (object.disabled)>
	            <@vlh.column title="${action.getText('supplier.supplierNo')}"  property="supplierNo" sortable="desc">
	             ${object.supplierNo}
	             <@alignLeft/>
	            </@vlh.column>
            <#else>
            <@vcolumn title="${action.getText('supplier.supplierNo')}" property="supplierNo" sortable="desc">
               <a href="javascript: returnDialog(new Array(#{object.id?if_exists},'${object.supplierNo?if_exists}','${object.name?if_exists}'));">
		            	${object.supplierNo?if_exists}
		            </a>
                <@alignLeft/>
            </@vcolumn>
            </#if>
            <@vcolumn title="${action.getText('supplier.name')}" property="name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('supplier.supplierType')}" property="supplierType.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('supplier.tradeType')}" property="tradeType.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('supplier.companyType')}" property="companyType.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
        </@list>
        <@buttonBar>
        </@buttonBar>
    </@ww.form>
    
</@htmlPage>