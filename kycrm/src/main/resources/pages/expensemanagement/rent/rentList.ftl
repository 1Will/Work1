<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('房租收费')}">
	<@ww.form name="'listForm'" action="'searchRent'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchRentToken"/>
		<#include "./rentSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms()'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/fee/editRent.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
       		</#if>
        </@buttonBar>
        <@list title="${action.getText('房租列表')}" 
            includeParameters="rent.code|rent.starTime_start|rent.starTime_end|rent.endTime_start|rent.endTime_end|readOnly|onlyInvalid|onlyValid|" 
        	fieldMap="like:rent.code|" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="rentIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
           </#if>
           <@vcolumn title="${action.getText('房租编码')}" property="code" sortable="desc">
				<a href="editRent.html?rent.id=#{object.id?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">
    				${object.code?if_exists}
                </a>
     			<@alignLeft/>
            </@vcolumn>
		
			<@vcolumn title="${action.getText('开始时间')}" property="startTime" format="yyyy-MM-dd" sortable="desc">
				<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('结束时间')}" property="endTime" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>

			<@vcolumn title="${action.getText('房间数')}" property="sumHouse" sortable="desc">
				<@alignRight/>
			</@vcolumn>

			<@vcolumn title="${action.getText('总面积')}" property="sumSquare" sortable="desc">
				<@alignRight/>
			</@vcolumn>

			<@vcolumn title="${action.getText('企业数')}" property="sumCustomer" sortable="desc">
				<@alignRight/>
			</@vcolumn>

			<@vcolumn title="${action.getText('已交金额')}" property="hasSum" sortable="desc">
				<@alignRight/>
			</@vcolumn>

			<@vcolumn title="${action.getText('总金额')}" property="sum" sortable="desc">
				<@alignRight/>
			</@vcolumn>

			<@vcolumn title="${action.getText('状态')}" property="state.name" sortable="desc">
				<@alignRight/>
			</@vcolumn>
        </@list>
    </@ww.form>
</@htmlPage>
