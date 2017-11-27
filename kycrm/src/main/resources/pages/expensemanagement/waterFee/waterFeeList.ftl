<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('水费')}">
	<@ww.form name="'listForm'" action="'searchWaterFee'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchWaterFeeToken"/>
		<#include "./waterFeeSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms()'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/fee/editWaterFee.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
       		</#if>
        </@buttonBar>
        <@list title="${action.getText('水费列表')}" 
            includeParameters="waterFee.code|building.name|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:waterFee.code|building.name|" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="waterFeeIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
          	<@vcolumn title="${action.getText('编码')}" property="code" sortable="desc" >
                <a href="editWaterFee.html?waterFee.id=#{object.id?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">
    				${object.code?if_exists}
                </a>
				<@alignLeft/>
            </@vcolumn>
            
           <@vcolumn title="${action.getText('是否为总水量')}" property="isAllBuilding" sortable="desc">
				<#if object.isAllBuilding>
					${action.getText('是')}
				<#else>
					${action.getText('否')}
				</#if>
				<@alignLeft/>
            </@vcolumn>
            
           <@vcolumn title="${action.getText('大楼名称')}" property="building.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
			<@vcolumn title="${action.getText('水价(元/吨)')}" property="building.waterPrice" >
     			<@alignRight/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('开始时间')}" property="starTime" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('结束时间')}" property="endTime" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('上次水示数')}" property="lastWater" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('本次水示数')}" property="thisWater" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
                        
            <@vlh.column title="${action.getText('公摊水数')}" property="shareWater" sortable="desc">
            	<@alignRight/>
            </@vlh.column>
            
             <@vcolumn title="${action.getText('实用水数')}" property="sumWater" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
            
        </@list>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('确认删除水费信息？')}" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"waterFeeIds\", \"${confirmMessage}\");'"/>
            </@vsubmit>
        </@buttonBar>
    </@ww.form>
</@htmlPage>
