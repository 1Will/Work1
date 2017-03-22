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

<#-- $Id: contactArchivesList.ftl 2009-12-08 13:50:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('title')}">
	<@ww.form name="'listFrom'" namespace="'/activity'" action="'searchActivity'" method="'post'">
		<@ww.token name="searchActivityToken"/>
		<#include "./activitySearcher.ftl" />
        <@buttonBar>
			<@vsubmit name="'search'" cssClass="'button'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/activity/editActivity.html"/>
        </@buttonBar>
        <@list title="${action.getText('list.title')}" 
            includeParameters="activity.theme|activityType.id|progress.id|priority.id|activity.beginTime|activity.beginTime_start|activity.beginTime_end|activity.endTime|activity.endTime_start|activity.endTime_end|activity.place|status.id|onlyInvalid|onlyValid" 
        	fieldMap="like:activity.theme|activity.place,date:activity.beginTime_start|activity.beginTime_end|activity.endTime_start|activity.endTime_end" >
        	<@vlh.checkbox property="id" name="activityIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            
            <@vcolumn title="${action.getText('activity.code')}" property="code" sortable="desc">
                <a href="editActivity.html?activity.id=#{object.id}">${object.code}</a>
				<@alignLeft/>
            </@vcolumn>
            <#--<@vcolumn title="${action.getText('activity.theme')}" property="theme" sortable="desc">
                <a href="editActivity.html?activity.id=#{object.id}">${object.theme}</a>
				<@alignLeft/>
            </@vcolumn>-->
            <@vcolumn title="${action.getText('activity.theme')}" property="theme" sortable="desc">
            ${object.theme}
            	<@alignLeft/>
            </@vcolumn>

            <@vcolumn title="${action.getText('activity.activityType')}" property="activityType.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('activity.progress')}" property="progress.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('activity.priority')}" property="priority.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('activity.beginTime')}" property="beginTime" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('activity.endTime')}" property="endTime" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter/>
            </@vcolumn>
             <@vcolumn title="${action.getText('activity.status')}" property="status.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('activity.place')}" property="place" sortable="desc">
            	<@alignCenter/>
            </@vcolumn>
        </@list>
         <#if !first>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('activity.info')}" boxName="activityIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
		 </#if>
    </@ww.form>
</@htmlPage>