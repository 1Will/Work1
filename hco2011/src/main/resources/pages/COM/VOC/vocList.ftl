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
<@htmlPage title="${action.getText('voc.title.check')}">
	<@ww.form name="'listFrom'" namespace="'/com'" action="'searchVoc'" method="'post'">
		<@ww.token name="searchVocToken"/>
		<#include "./vocSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/com/editVoc.html"/>
        </@buttonBar>
        <@list title="${action.getText('voc.list.title')}" 
            includeParameters="voc.code|voc.connectDate_start|voc.connectDate_end|voc.title|voc.customerInfo|voc.supplier|importance.id|type.id|voc.salesman|voc.principal
            |status.id|onlyInvalid|onlyValid" 
        	fieldMap="like:voc.code|voc.title|voc.customerInfo|voc.supplier|voc.salesman
        	|voc.principal,date:voc.connectDate_start|voc.connectDate_end" >
        	<@vlh.checkbox property="id" name="vocIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('voc.code')}" property="code" sortable="desc">
                <a href="editVoc.html?voc.id=#{object.id}">${object.code}</a>
				<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('voc.connectDate')}" property="connectDate" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter/><#-- attributes="width:110;"-->
            </@vcolumn>
            <@vcolumn title="${action.getText('voc.title')}" property="title" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('voc.customerInfo')}" property="customerInfo.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('voc.supplier')}" property="supplier" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('voc.importance')}" property="importance.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('voc.type')}" property="type.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('voc.salesman')}" property="salesman.name" sortable="desc">
            	<@alignCenter/>
            </@vcolumn>
             <@vcolumn title="${action.getText('voc.principal')}" property="principal.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('voc.linkman')}" property="linkman.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('voc.phone')}" property="phone" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('voc.status')}" property="status.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
         <#if !first>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('voc.info')}" boxName="vocIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
		 </#if>
    </@ww.form>
</@htmlPage>