<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="楼层">
	<@ww.form name="'listForm'" namespace="'/house'" action="'searchHouseWindow'" method="'post'">
		<@ww.token name="seacherHouseToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'customerInfo.id'" value="'${req.getParameter('customerInfo.id')?if_exists}'"/>
		<@ww.hidden name="'getRest'" value="'${req.getParameter('getRest')?if_exists}'"/>
		<#include "./houseSearcher.ftl" />
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'checkInvalidParms()'" />
        </@buttonBar>
        
		<@list title="${action.getText('房源列表')}" includeParameters="house.code|house.name|getRest|customerInfo.id|onlyInvalid|onlyValid" fieldMap="like:house.code|house.name|" >
			<@vlh.column title="${action.getText('房源编码')}"  property="code" sortable="desc">
					<#if req.getParameter('customerInfo.id')?exists && '' != req.getParameter('customerInfo.id') >
					<#--
						<a href="${req.contextPath}/house/editHouse.html?house.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
					-->
					<#else>
						<a href="javascript: returnDialog(new Array(#{object.id},'${object.code}','${object.name}','#{object.price}','#{object.square}','#{object.waterdisplay}','#{object.aedisplay}','#{object.bedisplay}','#{object.airdisplay}'));">${object.code}</a>
		            </#if>
				<@alignLeft/>
			</@vlh.column>
			
			<@vlh.column title="${action.getText('大楼名称')}"  property="building.name" sortable="desc">
				<@alignLeft/>
			</@vlh.column>
			
			<@vlh.column title="${action.getText('楼层名称')}"  property="floor.name" sortable="desc">
				<@alignLeft/>
			</@vlh.column>
			
			<@vlh.column title="${action.getText('房间名称')}"  property="name" sortable="desc">
				<@alignLeft/>
			</@vlh.column>
			<#--
			<@vlh.column title="${action.getText('房源状态')}"  property="state.name" sortable="desc">
				<@alignLeft/>
			</@vlh.column>
			-->
			
			<@vlh.column title="${action.getText('房源类别')}"  property="category.name" sortable="desc">
				<@alignLeft/>
			</@vlh.column>
			<#--
			<@vlh.column title="${action.getText('供应商')}"  property="customerInfo.name" sortable="desc">
				<@alignLeft/>
			</@vlh.column>
			-->
			
			<@vlh.column title="${action.getText('面积')}"  property="square" sortable="desc">
				<@alignRight/>
			</@vlh.column>
			
			<@vlh.column title="${action.getText('单价')}"  property="price" sortable="desc">
				<@alignRight/>
			</@vlh.column>
			
			<@vlh.column title="${action.getText('总价')}"  property="total" sortable="desc">
				<@alignRight/>
			</@vlh.column>
			
			<@vcolumn title="${action.getText('水表有否')}" property="hasWaterMeter" sortable="desc">
				<#if object.hasWaterMeter>
					${action.getText('是')}
				<#else>
					${action.getText('否')}
				</#if>
				<@alignLeft/>
            </@vcolumn>
			
			<@vcolumn title="${action.getText('电表有否')}" property="hasWaterMeter" sortable="desc">
				<#if object.hasElectricMeter>
					${action.getText('是')}
				<#else>
					${action.getText('否')}
				</#if>
				<@alignLeft/>
            </@vcolumn>
			
			<@vcolumn title="${action.getText('空调有否')}" property="hasWaterMeter" sortable="desc">
				<#if object.hasAirMeter>
					${action.getText('是')}
				<#else>
					${action.getText('否')}
				</#if>
				<@alignLeft/>
            </@vcolumn>
			
			<@vcolumn title="${action.getText('网络有否')}" property="hasWaterMeter" sortable="desc">
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