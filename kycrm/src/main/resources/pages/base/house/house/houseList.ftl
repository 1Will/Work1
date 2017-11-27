<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="房源查询">
	<@ww.form name="'listForm'" namespace="'/house'" action="'searchHouse'" method="'post'">
		<@ww.token name="seacherHouseToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#include "./houseSearcher.ftl" />
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'checkInvalidParms()'" />
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/house/editHouse.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			<@redirectButton value="${action.getText('房源导入')}" url="${req.contextPath}/house/toImportHouse.html"/>
        </@buttonBar>
        
		<@list title="${action.getText('房源列表')}" includeParameters="house.code|building.name|floor.name|house.name|onlyInvalid|onlyValid"
		 fieldMap="like:house.code|building.name|floor.name|house.name|" >
			<@vlh.checkbox property="id" name="houseIds">
			<@vlh.attribute name="width" value="30" />
			</@vlh.checkbox>
			<@vlh.column title="${action.getText('房源编码')}"  property="code" sortable="desc">
				 <a href="${req.contextPath}/house/editHouse.html?house.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
				<@alignLeft/>
			</@vlh.column>
			
			<@vlh.column title="${action.getText('大楼名称')}"  property="building.name" sortable="desc">
				<@alignLeft/>
			</@vlh.column>
			
			<@vlh.column title="${action.getText('楼层名称')}"  property="floor.name" sortable="desc">
				<@alignLeft/>
			</@vlh.column>
			
			<@vlh.column title="${action.getText('房源名称')}"  property="name" sortable="desc">
				<@alignLeft/>
			</@vlh.column>
			
			<@vlh.column title="${action.getText('房源状态')}"  property="state.name" sortable="desc">
				<@alignLeft/>
			</@vlh.column>
			
			<@vlh.column title="${action.getText('房源类别')}"  property="category.name" sortable="desc">
				<@alignLeft/>
			</@vlh.column>
			<#--
			<@vlh.column title="${action.getText('供应商')}"  property="customerInfo.name" sortable="desc">
				<@alignLeft/>
			</@vlh.column>
			-->
			
			<@vlh.column title="${action.getText('面积')}"  property="square" format="0.00" sortable="desc">
				<@alignRight/>
			</@vlh.column>
			
			<@vlh.column title="${action.getText('单价')}"  property="price" format="0.00" sortable="desc">
				<@alignRight/>
			</@vlh.column>
			
			<@vlh.column title="${action.getText('总价')}"  property="total" format="0.00" sortable="desc">
				<@alignRight/>
			</@vlh.column>
			
			<@vlh.column title="${action.getText('电费类型')}"  property="eType.name" sortable="desc">
				<@alignLeft/>
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
        
        <#if !(action.isReadOnly())>
        <@buttonBar>
	  		<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('房源')}?" />	 
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
               <@ww.param name="'onclick'" value="'return confirmDeletes(\"houseIds\", \"${confirmMessage}\");'"/>
            </@vsubmit>
		</@buttonBar>	
		</#if>
    </@ww.form>
    
</@htmlPage>
<script language="javascript">
</script>