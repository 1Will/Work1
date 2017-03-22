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
<@htmlPage title="${action.getText('onTheRoadBill.title')}">
	<@ww.form name="'listFrom'" namespace="'/onTheRoadBill'" action="'searchOnTheRoadBill'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchOnTheRoadBillToken"/>
		<#include "./onTheRoadBillSearcher.ftl" />
		<#--
		<#if backVistiFlag?exists>
			<@ww.hidden name="'backVisitFlag'" value="'${backVistiFlag?if_exists}'"/>
			<@ww.hidden name="'customer.id'" value="'#{customerId?if_exists}'"/>
		</#if>-->
        <@buttonBar>
        	<@vsubmit name="'search'"  value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
        	<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/onTheRoadBill/editOnTheRoadBill.html"/>
       		</#if>
        </@buttonBar>
        <@list title="${action.getText('onTheRoadBill.list.title')}" 
            includeParameters="onTheRoadBill.code|onTheRoadBill.crateDate_start|onTheRoadBill.crateDate_end|onTheRoadBill.applyPerson|dept.id|
            onTheRoadBill.startTime_start|onTheRoadBill.startTime_end|onTheRoadBill.endTime_start|onTheRoadBill.endTime_end|onTheRoadBill.endTime|status.id|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:onTheRoadBill.code|onTheRoadBill.applyPerson,date:onTheRoadBill.crateDate_start|onTheRoadBill.crateDate_end|onTheRoadBill.startTime_start|onTheRoadBill.startTime_end|onTheRoadBill.endTime_start|onTheRoadBill.endTime_end|onTheRoadBill.endTime" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="onTheRoadBillIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
             </#if>
            <@vcolumn title="${action.getText('onTheRoadBill.code')}" property="code" sortable="desc">
                <a href="editOnTheRoadBill.html?onTheRoadBill.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
				<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('onTheRoadBill.crateDate')}" property="createDate" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter/><#-- attributes="width:110;"-->
            </@vcolumn>
            <@vcolumn title="${action.getText('onTheRoadBill.applyPerson')}" property="applyPerson.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('onTheRoadBill.dept')}" property="dept.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('onTheRoadBill.startTime')}" property="startTime" format="yyyy-MM-dd HH:mm" sortable="desc">
            	<@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('onTheRoadBill.endTime')}" property="endTime" format="yyyy-MM-dd HH:mm" sortable="desc">
            	<@alignCenter/>
            </@vcolumn>
             <@vcolumn title="${action.getText('onTheRoadBill.status')}" property="status.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('onTheRoadBill.betreffzeile')}" property="betreffzeile" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
         <#if !first>
         	<#if !(action.isReadOnly())>
		        <@buttonBar>
		          <@crm_disabledOrEnabled_button message="${action.getText('onTheRoadBill.info')}" boxName="onTheRoadBillIds" jsFunctionName="checkInvalidParms()"/>
				</@buttonBar>
			</#if>
		</#if>
    </@ww.form>
</@htmlPage>