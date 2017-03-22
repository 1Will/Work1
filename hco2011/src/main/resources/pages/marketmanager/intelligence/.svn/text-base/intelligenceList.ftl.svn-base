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

<#-- $Id: intelligenceList.ftl 2011-2-24 hmguan $ -->

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('title')}">
	<@ww.form name="'listFrom'" namespace="'/intelligence'" action="'searchIntelligence'" method="'post'">
		<@ww.token name="searchIntelligenceToken"/>
		<#include "./intelligenceSearcher.ftl" />
        <@buttonBar>
			<@vsubmit name="'search'" cssClass="'button'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/intelligence/editIntelligence.html"/>
        </@buttonBar>
        <@list title="${action.getText('list.title')}" 
            includeParameters="intelligence.code|intelligence.theme|intelligence.customerInfo|intelligence.persons|intelligence.contactArchives|intelligence.analysisTime|intelligence.analysisTime_start|intelligence.analysisTime_end|important.id|onlyInvalid|onlyValid" 
        	fieldMap="like:intelligence.code|intelligence.theme|intelligence.customerInfo|intelligence.persons|intelligence.contactArchives,date:intelligence.analysisTime_start|intelligence.analysisTime_end" >
        	<@vlh.checkbox property="id" name="intelligenceIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('intelligence.code')}" property="code" sortable="desc">
                <a href="editIntelligence.html?intelligence.id=#{object.id}">${object.code}</a>
				<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('intelligence.theme')}" property="theme" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('intelligence.customerInfo')}" property="customerInfo.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('intelligence.persons')}" property="persons.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('intelligence.contactArchives')}" property="contactArchives.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('intelligence.analysisTime')}" property="analysisTime" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter/>
            </@vcolumn>
             <@vcolumn title="${action.getText('intelligence.important')}" property="important.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
         <#if !first>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('intelligence.info')}" boxName="intelligenceIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
		 </#if>
    </@ww.form>
</@htmlPage>