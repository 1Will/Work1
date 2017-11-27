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

<@htmlPage title="${action.getText('客户投诉查询页面')}">
	<@ww.form name="'listForm'" action="'searchCustomerComplaintAction'" method="'post'">
		<@ww.token name="searchCustomerComplaintActionToken"/>
		<#include "./customerComplaintSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms()'"/>
			<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/customerComplaint/editCustomerComplaintAction.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			</#if>
        </@buttonBar>
        <@list title="${action.getText('客户投诉列表')}" 
            includeParameters="customerComplaint.code|customerComplaint.complaintTitle|customerComplaint.telephone|customerComplaint.complaintType.id|customerComplaint.urgencyDegree.id|onlyInvalid|onlyValid" 
        	fieldMap="like:customerComplaint.code|customerComplaint.complaintTitle|customerComplaint.telephone" >
        	<@vlh.checkbox property="id" name="customerComplaintIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            
          <@vcolumn title="${action.getText('编号')}" property="code" sortable="desc" >
                <a href="editCustomerComplaintAction.html?customerComplaint.id=#{object.id?if_exists}">${object.code?if_exists}</a>
				<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('投诉主题')}" property="complaintTitle" sortable="desc">
            ${object.complaintTitle?if_exists}
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
            
            <@vcolumn title="${action.getText('投诉类型')}" property="complaintType.name" sortable="desc">
            	<#if object.complaintType?exists>
	            	 ${object.complaintType.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('紧急程度')}" property="urgencyDegree.name" sortable="desc">
            	<#if object.urgencyDegree?exists>
	            	 ${object.urgencyDegree.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('处理状态')}" property="disposeState.name" sortable="desc">
            	<#if object.disposeState?exists>
	            	 ${object.disposeState.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
        </@list>
        <#if !first>
        <#if !(action.isReadOnly())>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('客户投诉信息')}" boxName="customerComplaintIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
        </#if>
		</#if>
    </@ww.form>
</@htmlPage>