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

<#include "../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('financialManagement.title.check')}">
	<@ww.form name="'listFrom'" namespace="'/financialManagement'" action="'searchFinancialManagement'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchFinancialManagementToken"/>
		<#include "./financialManagementSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/financialManagement/editFinancialManagement.html"/>
			</#if>
        </@buttonBar>
        <@list title="${action.getText('financialManagement.list.title')}" 
            includeParameters="financialManagement.saleman|financialManagement.code|projectInfo.name|financialManagement.payee|financialManagement.contractManagement|financialManagement.collectionDate_start|financialManagement.collectionDate_end
        	|customerInfo.code|financialManagement.customerInfo|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:financialManagement.saleman|financialManagement.code|projectInfo.name|financialManagement.payee|financialManagement.contractManagement|customerInfo.code|financialManagement.customerInfo|
        	,date:financialManagement.collectionDate_start|financialManagement.collectionDate_end">
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="financialManagementIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
            
            <@vcolumn title="${action.getText('financialManagement.code')}" property="code" sortable="desc">
                <a href="editFinancialManagement.html?financialManagement.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
				<@alignLeft/>
            </@vcolumn>
           
            <@vcolumn title="${action.getText('financialManagement.contractManagement')}" property="contractManagement.contractName" sortable="desc">
            	<a href="javascript:contractManagement_OpenDialog(<#if (object.contractManagement?exists)>#{object.contractManagement.id?if_exists}</#if>)"><#if (object.contractManagement?exists)>${object.contractManagement.contractName?if_exists}</#if></a>
            	<@vlh.attribute name="width" value="12%" />
            	<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('financialManagement.customerInfo')}" property="customerInfo.name" sortable="desc">
     			<a href="javascript:customer_OpenDialog(#{object.customerInfo.id?if_exists})" title="完整度：${object.customerInfo.customerInfoIntegrity?if_exists}%; 熟悉程度：${object.customerInfo.customerType.name?if_exists}">${object.customerInfo.name?if_exists}</a>
     			<@vlh.attribute name="width" value="10%" />
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('financialManagement.contractManagement.projectInfo.name')}" property="contractManagement.project.name" sortable="desc">
     			<a href="javascript:editProjectInfo_OpenDialog(<#if (object.contractManagement?exists)>#{object.contractManagement.project.id}</#if>)" ><#if (object.contractManagement?exists)>${object.contractManagement.project.name}</#if></a>
     			<@vlh.attribute name="width" value="10%" />
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('financialManagement.batch')}" property="batch.name" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('financialManagement.sumReceivable')}" property="sumReceivable" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('financialManagement.totalSum')}" property="totalSum" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('financialManagement.trueSum')}" property="trueSum" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('financialManagement.withoutGotSum')}" property="withoutGotSum" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
            <#assign invoice=""/>
            <#if (object.invoice)=='0'>
            	<#assign invoice="${action.getText('是')}">
            <#else>
           	 	<#assign invoice="${action.getText('否')}">
		    </#if>
            <@vcolumn title="${action.getText('financialManagement.invoice')}" sortable="desc">
            	${invoice?if_exists}
            	<@alignLeft/>
            </@vcolumn>
                <@vcolumn title="${action.getText('financialManagement.payee')}" property="payee.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
              <@vcolumn title="${action.getText('financialManagement.collectionDate')}" property="collectionDate" format="yyyy-MM-dd" sortable="desc">
              <@vlh.attribute name="width" value="9%" />
            	<@alignLeft/><#-- attributes="width:110;"-->
            </@vcolumn>
             
            <#-- 
             <@vcolumn title="${action.getText('financialManagement.collectionType')}" property="collectionType.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('financialManagement.payment')}" property="payment.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('financialManagement.accountNumber')}" property="accountNumber" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('financialManagement.invoiceCode')}" property="invoiceCode" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('financialManagement.dept')}" property="dept.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>-->
        </@list>
         <#if !first>
         	<#if !(action.isReadOnly())>
		        <@buttonBar>
		          <@crm_disabledOrEnabled_button message="${action.getText('financialManagement.info')}" boxName="financialManagementIds" jsFunctionName="checkInvalidParms()"/>
				</@buttonBar>
			</#if>
		 </#if>
    </@ww.form>
</@htmlPage>