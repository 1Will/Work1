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
<@htmlPage title="${action.getText('billingRecord.title.check')}">
	<@ww.form name="'listFrom'" namespace="'/contractManagement'" action="'searchBillingRecord'" method="'post'">
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchBillingRecordToken"/>
		<#include "./billingRecordSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/contractManagement/editBillingRecord.html"/>
        	</#if>
        </@buttonBar>
        <@list title="${action.getText('billingRecord.list.title')}" 
            includeParameters="contractManagement.code|customerInfo.code|billingRecord.customerInfo|billingRecord.contractManagement|billingRecord.billingTime_start|billingRecord.billingTime_end|billingRecord.code|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:contractManagement.code|customerInfo.code|billingRecord.customerInfo|billingRecord.contractManagement|billingRecord.code,date:billingRecord.billingTime_start|billingRecord.billingTime_end" >
        	<#if !(action.isReadOnly())>
        	<@vlh.checkbox property="id" name="billingRecordIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
             </#if>
            <@vcolumn title="${action.getText('contractManagement.code')}" property="payee" sortable="desc">
                <a href="editBillingRecord.html?billingRecord.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.contractManagement.code}</a>
				<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('billingRecord.contractManagement')}" property="contractManagement.contractName" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('customerInfo.code')}" property="customerInfo.code" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('billingRecord.customerInfo')}" property="customerInfo.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('billingRecord.code')}" property="code" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('billingRecord.invoiceTitle')}" property="invoiceTitle" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('billingRecord.sum')}" property="sum" sortable="desc">
            #{object.sum?if_exists}
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('billingRecord.billingTime')}" property="billingTime" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter/><#-- attributes="width:110;"-->
            </@vcolumn>
              <@vcolumn title="${action.getText('billingRecord.payee')}" property="payee.name" sortable="desc">
				<@alignLeft/>
            </@vcolumn>
        </@list>
         <#if !first>
         	<#if !(action.isReadOnly())>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('billingRecord.info')}" boxName="billingRecordIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
			</#if>
		 </#if>
    </@ww.form>
</@htmlPage>