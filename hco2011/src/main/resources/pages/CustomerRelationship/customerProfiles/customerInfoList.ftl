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

<#-- $Id: customerInfoList.ftl 2009-12-11 8:48:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('customerInfo.title')}">
	<@ww.form name="'listForm'" action="'searchCustomerInfo'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchCustomerInfoToken"/>
		<#include "./customerInfoSearcher.ftl" />
        <@buttonBar>
				<@vsubmit value="'${action.getText('search')}'" onclick="'checkInvalidParms()'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/customerRelationship/editCustomerInfo.html"/>
       		</#if>
       		<#--
       		<@redirectButton value="${action.getText('importCustomer.button')}" url="${req.contextPath}/customerRelationship/toImportCustomerInfo.html"/>
       		-->
        </@buttonBar>
        <@list title="${action.getText('customerInfo.list.title')}" 
            includeParameters="customerInfo.code|customerInfo.name|type.id|country.id|province.id|city.id|industry.id|companyNature.id|isPartner|customerInfo.keyContacter|customerInfo.salesman|step.id|customerInfo.state|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:customerInfo.code|customerInfo.name|customerInfo.keyContacter|customerInfo.salesman|" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="customerInfoIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
            <@vcolumn title="${action.getText('customerInfo.code')}" property="code" sortable="desc" >
                <a href="editCustomerInfo.html?customerInfo.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}"
                 title="信息完整度${object.customerInfoIntegrity}%">${object.code}</a>
				<@alignLeft attributes="width:80;"/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.name')}" property="name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.type')}" property="customerType.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.step')}" property="step.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.sate')}" property="state.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('customerInfo.country')}" property="country.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('customerInfo.province')}" property="province.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
			<#if object.city?exists>
				<#assign cityName="${object.city.name}"/>
			<#else>
				<#assign cityName=""/>
			</#if>
             <@vcolumn title="${action.getText('customerInfo.city')}" property="city" sortable="desc">
     			${cityName}
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.industry')}" property="industry.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.companyNature')}" property="companyNature.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.keyContacter')}" property="keyContacter" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.mobilePhone')}" property="mobilePhone" sortable="desc">
     			<@alignLeft attributes="width:100;"/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.telphone')}" property="telphone" sortable="desc">
     			<@alignLeft attributes="width:100;"/>
			</@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.customerInfoIntegrity')}" property="customerInfoIntegrity" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.salesman')}" property="salesman.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
        <#if !first>
        	<#if !(action.isReadOnly())>
		        <@buttonBar>
		          <@crm_disabledOrEnabled_Nodelete_button message="${action.getText('customerInfo.info')}" boxName="customerInfoIds" jsFunctionName="checkInvalidParms()"/>
				</@buttonBar>
			</#if>
		</#if>
    </@ww.form>
</@htmlPage>