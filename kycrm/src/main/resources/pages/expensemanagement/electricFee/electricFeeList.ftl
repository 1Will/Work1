<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('电费')}">
	<@ww.form name="'listForm'" action="'searchElectricFee'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchElectricFeeToken"/>
		<#include "./electricFeeSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms()'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/fee/editElectricFee.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
       		</#if>
        </@buttonBar>
        <@list title="${action.getText('电费列表')}" 
            includeParameters="electricFee.code|building.name|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:electricFee.code|building.name|" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="electricFeeIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
          	<@vcolumn title="${action.getText('编码')}" property="code" sortable="desc" >
                <a href="editElectricFee.html?electricFee.id=#{object.id?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">
    				${object.code?if_exists}
                </a>
				<@alignLeft/>
            </@vcolumn>
            
           <@vcolumn title="${action.getText('是否为总电量')}" property="isAllBuilding" sortable="desc">
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
            
            <@vcolumn title="${action.getText('开始时间')}" property="starTime" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('结束时间')}" property="endTime" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('A上次电示数')}" property="lastElectricA" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('A本次电示数')}" property="thisElectricA" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('A实用电数')}" property="sumElectricA" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('B上次电示数')}" property="lastElectricB" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('B本次电示数')}" property="thisElectricB" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('B实用电数')}" property="sumElectricB" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
        </@list>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('确认删除电费信息？')}" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"electricFeeIds\", \"${confirmMessage}\");'"/>
            </@vsubmit>
        </@buttonBar>
    </@ww.form>
</@htmlPage>
