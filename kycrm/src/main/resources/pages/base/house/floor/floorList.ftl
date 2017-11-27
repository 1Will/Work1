<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="楼层">
	<@ww.form name="'listForm'" namespace="'/house'" action="'searchFloor'" method="'post'">
		<@ww.token name="seacherFloorToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#include "./floorSearcher.ftl" />
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'checkInvalidParms()'" />
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/house/editFloor.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
        </@buttonBar>
        
		<@list title="${action.getText('楼层列表')}" includeParameters="floor.code|building.name|floor.name|onlyInvalid|onlyValid" fieldMap="like:floor.code|building.name|floor.name|" >
			<@vlh.checkbox property="id" name="floorIds">
                <@vlh.attribute name="width" value="30" />
			</@vlh.checkbox>
			<@vlh.column title="${action.getText('楼层编码')}"  property="code" sortable="desc">
				 <a href="${req.contextPath}/house/editFloor.html?floor.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
				<@alignLeft/>
			</@vlh.column>
			
			<@vlh.column title="${action.getText('大楼名称')}"  property="building.name" sortable="desc">
				<@alignLeft/>
			</@vlh.column>
			
			<@vlh.column title="${action.getText('楼层名称')}"  property="name" sortable="desc">
				<@alignLeft/>
			</@vlh.column>
			
			
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