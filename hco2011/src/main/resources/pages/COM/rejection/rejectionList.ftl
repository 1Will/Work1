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
<@htmlPage title="${action.getText('rejection.title')}">
	<@ww.form name="'listFrom'" namespace="'/com'" action="'searchRejection'" method="'post'">
		<@ww.token name="searchRejectionToken"/>
		<#include "./rejectionSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/com/editRejection.html"/>
        </@buttonBar>
        <@list title="${action.getText('rejection.list.title')}" 
            includeParameters="rejection.code|rejection.rejectionDate_start|rejection.rejectionDate_end|rejection.products|rejection.customerInfo|
            onlyInvalid|onlyValid" 
        	fieldMap="like:rejection.code|rejection.customerInfo|rejection.products
        	,date:rejection.rejectionDate_start|rejection.rejectionDate_end" >
        	<@vlh.checkbox property="id" name="rejectionIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('rejection.code')}" property="code" sortable="desc">
                <a href="editRejection.html?rejection.id=#{object.id}">${object.code}</a>
				<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('rejection.customerInfo')}" property="customerInfo.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('rejection.co')}" property="co.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('rejection.rejectionDate')}" property="connectDate" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter/><#-- attributes="width:110;"-->
            </@vcolumn>
            <@vcolumn title="${action.getText('rejection.products')}" property="products.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('rejection.sum')}" property="sum" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
        </@list>
         <#if !first>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('rejection.info')}" boxName="rejectionIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
		 </#if>
    </@ww.form>
</@htmlPage>