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
            <#--
            <@vcolumn title="${action.getText('contractManagement.code')}" property="payee" sortable="desc">
                <a href="editBillingRecord.html?billingRecord.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.contractManagement.code}</a>
				<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('billingRecord.contractManagement')}" property="contractManagement.contractName" sortable="desc">
            	<a href="javascript:contractManagement_OpenDialog(<#if (object.contractManagement?exists)>#{object.contractManagement.id?if_exists}</#if>)"><#if (object.contractManagement?exists)>${object.contractManagement.contractName?if_exists}</#if></a>
            	<@vlh.attribute name="width" value="12%" />
            	<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('客户名称')}" property="customerInfo.name" sortable="desc">
     			<a href="javascript:customer_OpenDialog(#{object.customerInfo.id?if_exists})" title="完整度：${object.customerInfo.customerInfoIntegrity?if_exists}%; 熟悉程度：${object.customerInfo.customerType.name?if_exists}">${object.customerInfo.name?if_exists}</a>
     			<@vlh.attribute name="width" value="10%" />
     			<@alignLeft/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('项目名称')}" property="contractManagement.project.name" sortable="desc">
     			<a href="javascript:editProjectInfo_OpenDialog(<#if (object.contractManagement?exists)>#{object.contractManagement.project.id}</#if>)" ><#if (object.contractManagement?exists)>${object.contractManagement.project.name}</#if></a>
     			<@vlh.attribute name="width" value="10%" />
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('批次')}" property="batch.name" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('开票记录编码')}" property="myCode" sortable="desc">
            	<a href="editBillingRecord.html?billingRecord.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.myCode?if_exists}</a>
            	<@alignLeft/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('计划开票金额')}" property="planSum" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('前期已收金额')}" property="hasBillSum" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('本次实收金额')}" property="sum" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('未收金额')}" property="restSum" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
            
            <#assign pay=""/>
            <#if object.isPay?exists && (object.isPay)=='0'>
            	<#assign pay="${action.getText('是')}">
            <#else>
           	 	<#assign pay="${action.getText('否')}">
		    </#if>
            <@vcolumn title="${action.getText('是否收款')}" sortable="desc">
            	${pay?if_exists}
            	<@alignLeft/>
            </@vcolumn>
            
              <@vcolumn title="${action.getText('billingRecord.payee')}" property="payee.name" sortable="desc">
				<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('billingRecord.billingTime')}" property="billingTime" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter/><#-- attributes="width:110;"-->
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