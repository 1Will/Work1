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

<#include "../../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('title')}">
	<@ww.form name="'listFrom'" namespace="'/survey'" action="'searchSurvey'" method="'post'">
		<@ww.token name="searchSurveyToken"/>
		<#include "./surveySearcher.ftl" />
        <@buttonBar>
			<@vsubmit name="'search'" cssClass="'button'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/survey/editSurvey.html"/>
			</#if>
        </@buttonBar>
        <@list title="${action.getText('list.title')}" 
            includeParameters="survey.code|survey.surveyTime|survey.surveyTime_start|survey.surveyTime_end|survey.surveyTarget|survey.persons|onlyInvalid|onlyValid" 
        	fieldMap="like:survey.code|survey.persons,date:survey.surveyTime_start|survey.surveyTime_end" >
        	<@vlh.checkbox property="id" name="surveyIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('survey.code')}" property="code" sortable="desc">
                <a href="editSurvey.html?survey.id=#{object.id}">${object.code}</a>
				<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('survey.surveyTime')}" property="surveyTime" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('survey.surveyTarget')}" property="surveyTarget"  sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('survey.persons')}" property="persons.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
         <#if !first>
         <#if !(action.isReadOnly())>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('survey.info')}" boxName="surveyIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
         </#if>
		 </#if>
    </@ww.form>
</@htmlPage>