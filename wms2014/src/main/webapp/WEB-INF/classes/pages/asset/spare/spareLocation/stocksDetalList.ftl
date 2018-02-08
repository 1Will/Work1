<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="库存明细">
	 <@ww.form name="'listForm'"  action="'searchStocksDetal'" method="'post'">
	 	<#include "stocksDetalSearcher.ftl"/>
	 	<@ww.token name="searchStocksDetalToken"/>
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'"  onclick="'return checkInvalidParms();'"/>
        </@buttonBar>
	 	 <@ww.hidden name="'spare.id'" value="'${req.getParameter('spare.id')?if_exists}'"/>	
       <@list title="" 	includeParameters="id|onlyInvalid|onlyValid" 
		fieldMap="like:">
		<#--
            <@vlh.checkbox property="id" name="spareLocationIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            -->
            <@vcolumn title="${action.getText('图号')}" property="spare.spareNo">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('名称')}" property="spare.name">
            	<@alignLeft />
            </@vcolumn>            
            <@vcolumn title="${action.getText('规格型号')}" property="spare.modelSpecs">
            	 <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('种类')}" property="spare.category.name">
            	<@alignLeft />
            </@vcolumn>            
            <@vcolumn title="${action.getText('明细种类')}" property="spare.spareDetailType.name">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('库存')}" property="stocks">
            	<@alignLeft />
            </@vcolumn>            
            <@vcolumn title="${action.getText('仓库')}" property="warehouse.name">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('库位号')}" property="locationCode">
            	<@alignLeft />
            </@vcolumn>
            
		</@list>
		
     </@ww.form>
</@htmlPage>