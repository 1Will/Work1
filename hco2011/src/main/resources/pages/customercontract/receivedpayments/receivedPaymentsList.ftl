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
<@htmlPage title="${action.getText('receivedPayments.title.check')}">
	<@ww.form name="'listFrom'" namespace="'/contractManagement'" action="'searchReceivedPayments'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchReceivedPaymentsToken"/>
		<#include "./receivedPaymentsSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/contractManagement/editReceivedPayments.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
       		</#if>
        </@buttonBar>
        <@list title="${action.getText('receivedPayments.list.title')}" 
            includeParameters="receivedPayments.payee|receivedPayments.contractManagement|receivedPayments.paytime_start|receivedPayments.paytime_end|payment.id|isOrNot
            |readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:receivedPayments.payee|receivedPayments.paytime|receivedPayments.contractManagement,date:receivedPayments.paytime_start|receivedPayments.paytime_end" >
        	<#if !(action.isReadOnly())>
        	<@vlh.checkbox property="id" name="receivedPaymentsIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            </#if>
            <@vcolumn title="${action.getText('receivedPayments.payee')}" property="payee" sortable="desc">
                <a href="editReceivedPayments.html?receivedPayments.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.payee.name}</a>
				<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('receivedPayments.customerInfo')}" property="customerInfo.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('receivedPayments.contactArchives')}" property="contactArchives.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('receivedPayments.contractManagement')}" property="contractManagement.contractName" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('receivedPayments.paytime')}" property="paytime" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter/><#-- attributes="width:110;"-->
            </@vcolumn>
            <@vcolumn title="${action.getText('receivedPayments.payment')}" property="payment.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('receivedPayments.batch')}" property="batch" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('receivedPayments.sum')}" property="sum" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('receivedPayments.currency')}" property="currency.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
         <#if !first>
         <#if !(action.isReadOnly())>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('receivedPayments.info')}" boxName="receivedPaymentsIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
		</#if>
		 </#if>
    </@ww.form>
</@htmlPage>