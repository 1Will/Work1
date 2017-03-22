<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->

<#-- $Id: planList.ftl 2009-12-08 13:50:35Z hmguan $ -->

<#include "../../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('title')}">
	<@ww.form name="'listFrom'" namespace="'/plan'" action="'searchPlan'" method="'post'">
		<@ww.token name="searchPlanToken"/>
		<#include "./planSearcher.ftl" />
        <@buttonBar>
			<@vsubmit name="'search'" cssClass="'button'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/plan/editPlan.html"/>
        </@buttonBar>
        <@list title="${action.getText('list.title')}" 
            includeParameters="plan.code|plan.planTime|plan.planTime_start|plan.planTime_end|plan.persons|onlyInvalid|onlyValid" 
        	fieldMap="like:plan.code|plan.persons,date:plan.planTime_start|plan.planTime_end" >
        	<@vlh.checkbox property="id" name="planIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('plan.code')}" property="code" sortable="desc">
                <a href="editPlan.html?plan.id=#{object.id}">${object.code}</a>
				<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('plan.planTime')}" property="planTime" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('plan.persons')}" property="persons.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('plan.theme')}" property="theme" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
         <#if !first>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('plan.info')}" boxName="planIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
		 </#if>
    </@ww.form>
</@htmlPage>