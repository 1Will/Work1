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
<@htmlPage title="${action.getText('contractCustomer.title')}">
	<@ww.form name="'listFrom'" namespace="'/com'" action="'searchContractCustomer'" method="'post'">
		<@ww.token name="searchContractCustomerToken"/>
		<#include "./contractCustomerSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/com/editContractCustomer.html"/>
			</#if>
        </@buttonBar>
        <@list title="${action.getText('contractCustomer.list.title')}" 
            includeParameters="contractCustomer.code|contractCustomer.name|contractCustomer.customerInfo|contractCustomer.affixTime_start|contractCustomer.affixTime_end|contractCustomer.products
            |onlyInvalid|onlyValid" 
        	fieldMap="like:contractCustomer.code|contractCustomer.name|contractCustomer.customerInfo|contractCustomer.products
			,date:contractCustomer.affixTime_start|contractCustomer.affixTime_end" >
        	<@vlh.checkbox property="id" name="contractCustomerIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('contractCustomer.code')}" property="code" sortable="desc">
                <a href="editContractCustomer.html?contractCustomer.id=#{object.id}">${object.code}</a>
				<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contractCustomer.name')}" property="name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contractCustomer.customerInfo')}" property="customerInfo.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contractCustomer.affixTime')}" property="affixTime" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter/><#-- attributes="width:110;"-->
            </@vcolumn>
            <@vcolumn title="${action.getText('contractCustomer.affixPerson')}" property="affixPerson" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contractCustomer.salesman')}" property="salesman.name" sortable="desc">
            	<@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contractCustomer.phone')}" property="phone" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contractCustomer.products')}" property="products.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('contractCustomer.type')}" property="type.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
         <#if !first>
         <#if !(action.isReadOnly())>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('contractCustomer.info')}" boxName="contractCustomerIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
		 </#if>
		 </#if>
    </@ww.form>
</@htmlPage>