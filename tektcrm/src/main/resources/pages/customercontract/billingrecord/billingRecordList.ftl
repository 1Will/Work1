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
	<@ww.hidden name="'personnelFiles.id'" value="'${req.getParameter('personnelFiles.id')?if_exists}'"/>
	<@ww.hidden name="'month'" value="'${req.getParameter('month')?if_exists}'"/>
		<@ww.token name="searchBillingRecordToken"/>
		<#include "./billingRecordSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/contractManagement/editBillingRecord.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
        	</#if>
        </@buttonBar>
        <@list title="${action.getText('billingRecord.list.title')}" 
            includeParameters="contractManagement.code|customerInfo.code|billingRecord.customerInfo|billingRecord.contractManagement|billingRecord.billingTime_start|billingRecord.billingTime_end|billingRecord.myCode|readOnly|onlyInvalid|onlyValid|billingRecord.contractManagement|contractManagement.project.name|billingRecord.customerInfo|billingRecord.payee|billingRecord.code|personnelFiles.id|month" 
        	fieldMap="like:contractManagement.code|customerInfo.code|billingRecord.customerInfo|billingRecord.contractManagement|billingRecord.myCode|billingRecord.contractManagement|contractManagement.project.name|billingRecord.customerInfo|billingRecord.payee|billingRecord.code|month,date:billingRecord.billingTime_start|billingRecord.billingTime_end" >
        	<#if !(action.isReadOnly())>
        	<@vlh.checkbox property="id" name="billingRecordIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
             </#if>
             <@vcolumn title="${action.getText('开票记录编码')}" property="myCode" sortable="desc">
            	<a href="editBillingRecord.html?billingRecord.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.myCode?if_exists}</a>
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contractManagement.code')}" property="payee" sortable="desc">
                <a href="javascript:contractManagement_OpenDialog(<#if (object.contractManagement?exists)>#{object.contractManagement.id?if_exists}</#if>)"><#if (object.contractManagement?exists)>${object.contractManagement.code}</#if></a>
				<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('billingRecord.contractManagement')}" property="contractManagement.contractName" sortable="desc">
            	${object.contractManagement.contractName?if_exists}
            	<@vlh.attribute name="width" value="12%" />
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('客户编码')}" property="customerInfo.code" sortable="desc">
     			<a href="javascript:customer_OpenDialog(#{object.customerInfo.id?if_exists})" title="完整度：${object.customerInfo.customerInfoIntegrity?if_exists}%; 熟悉程度：${object.customerInfo.customerType.name?if_exists}">${object.customerInfo.code?if_exists}</a>
     			<@vlh.attribute name="width" value="10%" />
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('客户名称')}" property="customerInfo.name" sortable="desc">
     			${object.customerInfo.name?if_exists}
     			<@vlh.attribute name="width" value="10%" />
     			<@alignLeft/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('项目名称')}" property="contractManagement.project.name" sortable="desc">
     			<a href="javascript:editProjectInfo_OpenDialog(<#if (object.contractManagement?exists)>#{object.contractManagement.project.id}</#if>)" ><#if (object.contractManagement?exists)>${object.contractManagement.project.name}</#if></a>
     			<@vlh.attribute name="width" value="10%" />
     			<@alignLeft/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('批次')}" property="batch.name" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
            -->
              <@vcolumn title="${action.getText('发票编码')}" property="code" sortable="desc">
            	${object.code?if_exists}
            	<@alignLeft/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('计划开票')}" property="planSum" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('前期已开')}" property="hasBillSum" sortable="desc">
            	#{object.hasBillSum}
            	<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('本次开票')}" property="sum" sortable="desc">
            	#{object.sum}
            	<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('未开金额')}" property="restSum" sortable="desc">
            	#{object.restSum}
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