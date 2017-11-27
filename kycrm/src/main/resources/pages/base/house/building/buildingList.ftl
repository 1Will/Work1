<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="大楼">
	<@ww.form name="'listForm'" namespace="'/house'" action="'searchBuilding'" method="'post'">
		<@ww.token name="seacherBuildingToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#include "./buildingSearcher.ftl" />
        <@buttonBar>
        <@vsubmit value="'${action.getText('search')}'" onclick="'checkInvalidParms()'" />
		<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/house/editBuilding.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
          <#--
			<@redirectButton value="${action.getText('importProduct.button')}" url="${req.contextPath}/productsManager/toImportProduct.html"/>
          -->
        </@buttonBar>
		<@list title="${action.getText('大楼列表')}" includeParameters="building.code|building.name|onlyInvalid|onlyValid" fieldMap="like:building.code|building.name|" >
			<@vlh.checkbox property="id" name="buildingIds">
                <@vlh.attribute name="width" value="30" />
			</@vlh.checkbox>
			<@vlh.column title="${action.getText('大楼编码')}"  property="code" sortable="desc">
				 <a href="${req.contextPath}/house/editBuilding.html?building.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
				<@alignLeft/>
			</@vlh.column>
			
			<@vlh.column title="${action.getText('大楼名称')}"  property="name" sortable="desc">
				<@alignLeft/>
			</@vlh.column>
			
			<@vcolumn title="${action.getText('水表模型')}" property="waterModel.name" sortable="desc">
				<@alignLeft/>
            </@vcolumn>
			
			<@vcolumn title="${action.getText('是否有水表')}" property="hasWaterMeter" sortable="desc">
				<#if object.hasWaterMeter>
					${action.getText('是')}
				<#else>
					${action.getText('否')}
				</#if>
				<@alignLeft/>
            </@vcolumn>
			
			<@vcolumn title="${action.getText('是否有电表')}" property="hasWaterMeter" sortable="desc">
				<#if object.hasElectricMeter>
					${action.getText('是')}
				<#else>
					${action.getText('否')}
				</#if>
				<@alignLeft/>
            </@vcolumn>
			
			<@vcolumn title="${action.getText('是否有空调')}" property="hasWaterMeter" sortable="desc">
				<#if object.hasAirMeter>
					${action.getText('是')}
				<#else>
					${action.getText('否')}
				</#if>
				<@alignLeft/>
            </@vcolumn>
			
			<@vcolumn title="${action.getText('是否有网络')}" property="hasWaterMeter" sortable="desc">
				<#if object.hasNetMeter>
					${action.getText('是')}
				<#else>
					${action.getText('否')}
				</#if>
				<@alignLeft/>
            </@vcolumn>
			
            
        </@list>
    </@ww.form>
    
</@htmlPage>
<script language="javascript">
</script>