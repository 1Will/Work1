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
<@htmlPage title="${action.getText('contactArchives.title')}">
	<@ww.form name="'listForm'" action="'searchContactArchivesWindow'" method="'post'">
		<@ww.token name="searchContactArchivesWindowToken"/>
		<#include "./contactArchivesSearcher.ftl" />
		<@ww.hidden name="'customerIsNotNull'" value="'${req.getParameter('customerIsNotNull')?if_exists}'"/>
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'checkInvalidParms()'"/>
        </@buttonBar>
        <@list title="${action.getText('contactArchives.list.title')}" 
            includeParameters="contactArchives.name|customerIsNotNull|customer.id|contactArchives.customerName|type.id|onlyInvalid|onlyValid|customer.id|backVisitFlag" 
        	fieldMap="like:contactArchives.name|contactArchives.customerName" >
        	<#if !object.disabled>
	        	<@vcolumn title="${action.getText('contactArchives.name')}" property="name" sortable="asc">
		            <a href="javascript: returnDialog(new Array(#{object.id},'<#if object.phone?exists>${object.phone}</#if>','${object.name}','<#if object.mobilePhone?exists>${object.mobilePhone}</#if>'));">
		            	${object.name}
		            </a>
		            <@alignLeft/>
		        </@vcolumn>
	            <@vcolumn title="${action.getText('contactArchives.customerName')}" property="custName" sortable="desc">
	     			<@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('contactArchives.customerType')}" property="custType" sortable="desc">
	     			<@alignLeft/>
	            </@vcolumn>
				<#assign sex=""/>
				<#if object.sex>
					<#assign sex="${action.getText('contactArchives.women')}"/>
				<#else>
					<#assign sex="${action.getText('contactArchives.man')}"/>
				</#if>
				<@vcolumn title="${action.getText('contactArchives.sex')}" property="sex" sortable="desc">
					${sex}
					<@alignLeft/>
				</@vcolumn>
				<@vcolumn title="${action.getText('contactArchives.dept')}" property="dept" sortable="desc">
	     			<@alignLeft/>
	            </@vcolumn>
				<@vcolumn title="${action.getText('contactArchives.duty')}" property="duty" sortable="desc">
	     			<@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('contactArchives.mobilePhone')}" property="mobilePhone" sortable="desc">
	     			<@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('contactArchives.phone')}" property="phone" sortable="desc">
	     			<@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('contactArchives.caType')}" property="type.name" sortable="desc">
	     			<@alignLeft/>
	            </@vcolumn>
	        </#if>
        </@list>
    </@ww.form>
</@htmlPage>