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

<@htmlPage title="${action.getText('客户服务查询页面')}">
	<@ww.form name="'listForm'" action="'searchCustomerServiceAction'" method="'post'">
		<@ww.token name="searchCustomerServiceActionToken"/>
		<#include "./customerServiceSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms()'"/>
			<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/customerService/editCustomerServiceAction.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			</#if>
        </@buttonBar>
        <@list title="${action.getText('客户服务列表')}" 
            includeParameters="customerService.code|customerService.serviceTitle|customerService.serviceType.id|customerService.serviceWay.id|ccustomerService.costTime|onlyInvalid|onlyValid" 
        	fieldMap="like:customerService.code|customerService.serviceTitle" >
        	
        	<@vlh.checkbox property="id" name="customerServiceIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
          <@vcolumn title="${action.getText('编号')}" property="id" sortable="desc" >
                <a href="editCustomerServiceAction.html?customerService.id=#{object.id?if_exists}">${object.code?if_exists}</a>
				<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('服务主题')}" property="serviceTitle" sortable="desc">
            ${object.serviceTitle?if_exists}
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('客户')}" property="customerInfo.id" sortable="desc">
            	<#if object.customerInfo?exists>
	            	 ${object.customerInfo.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('服务人员')}" property="salesman.id" sortable="desc">
            	<#if object.salesman?exists>
	            	 ${object.salesman.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('联系人')}" property="linkman.id" sortable="desc">
				<#if object.linkman?exists>
	            	 ${object.linkman.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('服务类型')}" property="serviceType.id" sortable="desc">
				<#if object.serviceType?exists>
	            	 ${object.serviceType.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('服务方式')}" property="serviceWay.id" sortable="desc">
				<#if object.serviceWay?exists>
	            	 ${object.serviceWay.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
        </@list>
        <#if !first>
        <#if !(action.isReadOnly())>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('客户服务信息')}" boxName="customerServiceIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
		</#if>
        </#if>
    </@ww.form>
</@htmlPage>
