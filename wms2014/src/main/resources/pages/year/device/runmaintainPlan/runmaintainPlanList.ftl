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
<#-- $Id: -->
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('runmaintainPlanSeacher.title')}">
  <@ww.form namespace="'/year/device/runmaintainPlan'" name="'listRunmaintainPlan'" action="'searchRunmaintainPlans'" method="'post'">
    <@ww.token name="searchRunmaintainPlansToken"/>   
	<#include "runmaintainPlanSearcher.ftl"/>
	 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
 	<@buttonBar>
      <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
      <#if !(action.isReadOnly())>
      <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/year/device/runmaintainPlan/editRunmaintainPlan.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>  
     </#if>
    </@buttonBar>
 	<@list title="${action.getText('runmaintainPlan.list')}" 
           includeParameters="planNo|name|readOnly|department.id|year|planCreator.name|planCreatedDate_start|planCreatedDate_end|onlyValid|onlyInvalid" 
           fieldMap="like:planCreator.name|planNo|name|,date:planCreatedDate_start|planCreatedDate_end" >
	  <@vlh.checkbox property="id" name="runmaintainPlanIds">
        <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <@vcolumn title="${action.getText('runmaintainPlan.planNo')}" property="planNo" sortable="desc">
        <#if !(action.isOnlyInvalid())>
          <a href="editRunmaintainPlan.html?runmaintainPlan.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.planNo}</a>
        </#if>
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('runmaintainPlan.name')}" property="name" sortable="desc">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('department')}" property="department.name" sortable="desc">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('runmaintainPlan.year')}" property="year" format="yyyy" sortable="desc">
        <@alignCenter/>
      </@vcolumn>
      <@vcolumn title="${action.getText('runmaintainPlan.planCreator')}" property="planCreator.name" sortable="desc">
	    <@alignLeft/>
      </@vcolumn>
	  <@vcolumn title="${action.getText('runmaintainPlan.planCreatedDate')}" property="planCreatedDate" format="yyyy-MM-dd" sortable="desc">
	    <@alignCenter/>
      </@vcolumn>
      <@vcolumn title="${action.getText('runmaintainPlan.planAllFee')}" property="planAllFee" sortable="desc">
	    <@alignRight/>
      </@vcolumn>
      <@vcolumn title="${action.getText('runmaintainPlan.comment')}" property="comment">
	    <@alignLeft/>
      </@vcolumn>
    </@list>
    <#if !first>
      <@buttonBar>
      <#if !(action.isReadOnly())>
	    <@eam2008_disabledOrEnabled_button message="${action.getText('runmaintainPlan')}" boxName="runmaintainPlanIds" jsFunctionName="checkInvalidParms()"/>
	  </#if>
	  </@buttonBar>
    </#if>
  </@ww.form>
</@htmlPage>