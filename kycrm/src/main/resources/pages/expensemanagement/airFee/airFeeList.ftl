<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('空调费')}">
	<@ww.form name="'listForm'" action="'searchAirFee'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchAirFeeToken"/>
		<@ww.hidden name="'saveAll'" value="'saveAll'"/>
		<#include "./airFeeSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms()'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/fee/editAirFee.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
       		</#if>
        </@buttonBar>
        <@list title="${action.getText('空调费列表')}" 
            includeParameters="building.code|building.name|airFee.starTime_start|airFee.starTime_end|airFee.endTime_start|airFee.endTime_end|readOnly|onlyInvalid|onlyValid|" 
        	fieldMap="like:house.code|house.name|" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="airFeeIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
           </#if>
           <@vcolumn title="${action.getText('空调费编码')}" property="code" sortable="desc">
				<a href="editAirFee.html?airFee.id=#{object.id?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">
    				${object.code?if_exists}
                </a>
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
        </@list>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('确认删除空调费信息？')}" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"airFeeIds\", \"${confirmMessage}\");'"/>
            </@vsubmit>
        </@buttonBar>
    </@ww.form>
</@htmlPage>
