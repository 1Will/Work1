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

<@htmlPage title="${action.getText('repairs.search')}">
	<@ww.form name="'listForm'" action="'searchRepairsAction'" method="'post'">
		<@ww.token name="searchRepairsActionToken"/>
		<#include "./repairsServiceSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms()'"/>
			<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/repairs/editRepairsAction.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			</#if>
        </@buttonBar>
        <@list title="${action.getText('repairs.list')}" 
            includeParameters="repairs.code|repairs.customerInfo.name|repairs.repairsTime|onlyInvalid|onlyValid" 
        	fieldMap="like:repairs.code|repairs.customerInfo.name" >
        	
        	<@vlh.checkbox property="id" name="repairsIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
          <@vcolumn title="${action.getText('repairs.code')}" property="code" sortable="desc" >
                <a href="editRepairsAction.html?repairs.id=#{object.id?if_exists}">${object.code?if_exists}</a>
				<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('repairs.customerInfo')}" property="customerInfo.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
           <#-- <@vcolumn title="${action.getText('客户')}" property="customerInfo.id" sortable="desc">
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
            -->
        </@list>
        <#if !first>
        <#if !(action.isReadOnly())>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('repairs')}" boxName="repairsIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
        </#if>
		</#if>
    </@ww.form>
</@htmlPage>
