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

<#include "../../includes/eam2008.ftl" />
<#--$Id: lubricationRuleItemList.ftl 11192 2008-03-04 01:46:57Z zbzhang $ -->


<@framePage title="${action.getText('repairMaintenanceDetail.title')}">
  <@ww.form name="'listRepairMaintenanceDetails'" action="'searchRepairMaintenanceDetails'" method="'post'">
    <@ww.token name="searchRepairMaintenanceDetailsToken"/>
    <#if yearPlan?exists>
      <@ww.hidden name="'yearPlan.id'" value="'#{yearPlan.id?if_exists}'"/>
    </#if>
    <#if quarterPlan?exists>
      <@ww.hidden name="'quarterPlan.id'" value="'#{quarterPlan.id?if_exists}'"/>
    </#if>
    <@ww.hidden name="'yearQuarterFlag'" value="'${yearQuarterFlag?if_exists}'"/>
	<@ww.hidden name="'yearRepairMaintenanceDetailIds'" value="''"/>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<#assign itemNo=1/>
    <@list title="" excel=false setupTable=false 
     	   includeParameters="yearPlan.id|quarterPlan.id"  fieldMap="like:" >
 	  <@vlh.checkbox property="id" name="repairMaintenanceDetailIds">
        <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <@vcolumn title="${action.getText('repairMaintenanceDetail.serialNo')}">
        <#if yearQuarterFlag?exists>
          <#if yearQuarterFlag=='YEAR_PLAN'>
           <#if !(action.isReadOnly())>
            <a href="#" onclick="open_detailDialog(#{yearPlan.id}, #{object.id});return false;">#{itemNo}</a>
          <#else>
            #{itemNo}
          </#if>
          <#else>
            <a href="#" onclick="open_detailDialog(#{quarterPlan.id}, #{object.id});return false;">#{itemNo}</a>
          </#if>
        </#if>
        <@alignCenter />
      </@vcolumn>
      <#assign itemNo = itemNo + 1/>
      <@vcolumn title="${action.getText('graphNo')}" property="tooling.deviceNo">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('name')}" property="name">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('category')}" property="categoryName">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('detailCategory')}" property="detailCategoryName">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('model')}" property="model">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('specification')}" property="specification">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('calUnit')}" property="calUnit.value">
        <@alignLeft />
      </@vcolumn> 
      <@vcolumn title="${action.getText('unitPrice')}" property="unitPrice" format="${action.getText('currencyFormat')}">
        <@alignRight />
      </@vcolumn>
      <@vcolumn title="${action.getText('number')}" property="number">
        <@alignRight />
      </@vcolumn>
      <@vcolumn title="${action.getText('allPrice')}" property="allPrice" format="${action.getText('currencyFormat')}">
        <@alignRight />
      </@vcolumn>
      <@vcolumn title="${action.getText('requestDate')}" property="requestDate" format="yyyy-MM-dd" attributes="width='80'">
        <@alignCenter />
      </@vcolumn>
      <@vcolumn title="${action.getText('requestReason')}" property="requestReason">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('comment')}" property="comment">
        <@alignLeft />
      </@vcolumn>
      <#if yearQuarterFlag?exists>
        <#if yearQuarterFlag=='YEAR_PLAN'>
          <#assign createQuarterFlagValue=''/>
          <#if object.createQuarterFlag>
            <#assign createQuarterFlagValue="${action.getText('yes')}"/>
          <#else>
            <#assign createQuarterFlagValue="${action.getText('no')}"/>
          </#if>
          <@vcolumn title="${action.getText('createQuarterFlag')}">
            ${createQuarterFlagValue}
            <@alignLeft />
          </@vcolumn>
        </#if>
      </#if>
    </@list>
    <#if !first>
    <#if !(action.isReadOnly())>
 	  <@buttonBar>
        <#if yearQuarterFlag?exists>
          <#if yearQuarterFlag=='YEAR_PLAN'>
 	        <input type="button" class="button" name="new" <#if yearPlan.lockedFlag>disabled</#if> value="${action.getText('new')}" onclick="open_detailDialog(#{yearPlan.id}, null)"/>
          <#else>
            <input type="button" class="button" name="new" value="${action.getText('new')}" onclick="open_detailDialog(#{quarterPlan.id}, null)"/>
          </#if>
        </#if>
        <#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('repairMaintenanceDetail')}?" />
        <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
          <@ww.param name="'onclick'" value="'return confirmDeletes(\"repairMaintenanceDetailIds\", \"${confirmMessage}\");'"/>
          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
          <#if yearPlan.lockedFlag>
            <@ww.param name="'disabled'" value="true"/>
          </#if>
        </@vsubmit>
        <#if yearQuarterFlag?exists>
          <#if yearQuarterFlag=='QUARTER_PLAN'>
            <@vsubmit name="'copy'" value="'${action.getText('select.yearRepairMaintenanceDetail')}'" onclick="'return yearRepairMaintenanceDetail_OpenDialog();'"/>
          </#if>
        </#if>
      </@buttonBar>
    </#if>
    </#if>
    </@ww.form>
    <script language="javascript">
      window.onload = function () {
        <#if yearQuarterFlag?exists>
          <#if yearQuarterFlag=='YEAR_PLAN'>
            <#if yearPlan.planAllFee?exists>
	          parent.document.forms["yearPlan"].elements["yearPlan.planAllFee"].value='${(yearPlan.planAllFee?string('#,###,##0.00'))?if_exists}';
	        </#if>
	      <#else>
	        <#if quarterPlan?exists>
	          parent.document.forms["quarterPlan"].elements["quarterPlan.planAllFee"].value='${(quarterPlan.planAllFee?string('#,###,##0.00'))?if_exists}';
	        </#if>
	      </#if>
	    </#if>
	    <#if req.getParameter('errorFlag')?has_content>
          alert("${action.getText('delete.repairMaintenanceDetail.failure')}");
        </#if>
	  }
      function open_detailDialog(planId, repairMaintenanceDetailId) {
	    <#if yearQuarterFlag?exists>
          <#if yearQuarterFlag=='YEAR_PLAN'>
	        var url = '${req.contextPath}/popup/editRepairMaintenanceDetail.html?yearPlan.id='+planId;	      		
	      <#else>
	        var url = '${req.contextPath}/popup/editRepairMaintenanceDetail.html?quarterPlan.id='+planId;	      		
	      </#if>
	    </#if>     		
	    if (repairMaintenanceDetailId != null) {
	      url = url + "&repairMaintenanceDetail.id=" + repairMaintenanceDetailId;
	    }
	    url = url + "&yearQuarterFlag=" + '${yearQuarterFlag?if_exists}';
	    popupModalDialog(url,800,600);
	    <#if yearQuarterFlag?exists>
          <#if yearQuarterFlag=='YEAR_PLAN'>
	        var reloadUrl = "${req.contextPath}/year/tooling/yearPlan/listRepairMaintenanceDetails.html?yearPlan.id=#{yearPlan.id}&yearQuarterFlag=YEAR_PLAN";
	      <#else>
	        var reloadUrl = "${req.contextPath}/year/tooling/quarterPlan/listRepairMaintenanceDetails.html?quarterPlan.id=#{quarterPlan.id}&yearQuarterFlag=QUARTER_PLAN";
	      </#if>
	    </#if>
	    self.location.href = reloadUrl;
	  }
	  //点击"从年度计划中选择"按钮,触发备件采购选择
	  function yearRepairMaintenanceDetail_OpenDialog() {
	    var url = '${req.contextPath}/popup/repairMaintenanceDetailSelector.html';
        popupModalDialog(url, 800, 600, refresh_repairMaintenanceDetail_information);
        return true;	 
	  }
	  function refresh_repairMaintenanceDetail_information(result) {
	    if (null != result) {
	      var yearRepairMaintenanceDetailIds = result.substring(0, result.lastIndexOf(","));
	      document.forms[0].elements["yearRepairMaintenanceDetailIds"].value=yearRepairMaintenanceDetailIds;
	    }
	  }
    </script>
     
</@framePage>