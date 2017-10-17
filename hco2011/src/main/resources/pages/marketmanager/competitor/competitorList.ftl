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

<#-- $Id: competitorList.ftl 2011-2-24 hmguan $ -->

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('title')}">
	<@ww.form name="'listFrom'" namespace="'/competitor'" action="'searchCompetitor'" method="'post'">
		<@ww.token name="searchCompetitorToken"/>
		<#include "./competitorSearcher.ftl" />
        <@buttonBar>
			<@vsubmit name="'search'" cssClass="'button'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/competitor/editCompetitor.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			</#if>
        </@buttonBar>
        <@list title="${action.getText('list.title')}" 
            includeParameters="competitor.code|competitor.companyName|industry.id|nature.id|ompetitor.business|competitor.targetMarket|competitor.customerInfo|competitor.products|onlyInvalid|onlyValid" 
        	fieldMap="like:competitor.code|competitor.companyName|competitor.business|competitor.targetMarket|competitor.customerInfo|competitor.products" >
        	<@vlh.checkbox property="id" name="competitorIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('competitor.code')}" property="code" sortable="desc">
                <a href="editCompetitor.html?competitor.id=#{object.id}">${object.code}</a>
				<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('competitor.companyName')}" property="companyName" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('competitor.industry')}" property="industry.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('competitor.nature')}" property="nature.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('competitor.business')}" property="business" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('competitor.targetMarket')}" property="targetMarket" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('competitor.customerInfo')}" property="customerInfo.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('competitor.products')}" property="products.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
         <#if !first>
         <#if !(action.isReadOnly())>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('competitor.info')}" boxName="competitorIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
         </#if>
		 </#if>
    </@ww.form>
</@htmlPage>