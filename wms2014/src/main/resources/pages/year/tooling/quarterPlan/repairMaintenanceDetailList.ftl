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

<#include "../../../includes/eam2008.ftl" />
<#--$Id: lubricationRuleItemList.ftl 11192 2008-03-04 01:46:57Z zbzhang $ -->


<@framePage title="${action.getText('repairMaintenanceDetail.title')}">
  <@ww.form namespace="'/year/tooling/quarterPlan'" name="'listRepairMaintenanceDetails'" action="'searchRepairMaintenanceDetails'" method="'post'">
    <@ww.token name="searchRepairMaintenanceDetailsToken"/>
    <#if quarterPlan?exists>
      <@ww.hidden name="'quarterPlan.id'" value="'#{quarterPlan.id?if_exists}'"/>
    </#if>
    <@ww.hidden name="'yearQuarterFlag'" value="'${yearQuarterFlag?if_exists}'"/>
	<@ww.hidden name="'yearRepairMaintenanceDetailIds'" value="''"/>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
	<#assign itemNo=1/>
    <@list title="" excel=false setupTable=false 
     	   includeParameters="yearPlan.id|quarterPlan.id"  fieldMap="like:" >
 	  <@vlh.checkbox property="id" name="repairMaintenanceDetailIds">
        <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <@vcolumn title="${action.getText('serialNo')}">
        <#if !(action.isReadOnly())>
          <a href="#" onclick="open_detailDialog(#{quarterPlan.id}, #{object.id});return false;">#{itemNo}</a>
        <#else>
          #{itemNo}
        </#if>
        <@alignCenter />
      </@vcolumn>
      <#assign itemNo = itemNo + 1/>
      <@vcolumn title="${action.getText('tooling.no')}" property="graphNo">
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
      <@vcolumn title="${action.getText('unit')}" property="calUnit.value" >
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
     <#assign createPurchaseFlagValue=''/>
     <#if object.createPurchaseFlag>
       <#assign createPurchaseFlagValue="${action.getText('yes')}"/>
     <#else>
       <#assign createPurchaseFlagValue="${action.getText('no')}"/>
     </#if>
      <@vcolumn title="${action.getText('createPurchaseBill')}">
        ${createPurchaseFlagValue}
        <@alignLeft />
      </@vcolumn>
    </@list>
    <#if !first>
      <#if !(action.isReadOnly())>
 	  <@buttonBar>
        <input type="button" class="button" name="new" value="${action.getText('new')}" onclick="open_detailDialog(#{quarterPlan.id}, null)"/>
        <#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('yearPlan.repairMaintenanceDetail')}?" />
        <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
          <@ww.param name="'onclick'" value="'return confirmDeletes(\"repairMaintenanceDetailIds\", \"${confirmMessage}\");'"/>
          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
        </@vsubmit>
        <@vsubmit name="'copy'" value="'${action.getText('select.yearPlanDetail')}'" onclick="'return yearRepairMaintenanceDetail_OpenDialog();'"/>
      </@buttonBar>
      </#if>
    </#if>
    </@ww.form>
    <script language="javascript">
      window.onload = function () {
        <#if quarterPlan?exists>
          parent.document.forms["quarterPlan"].elements["quarterPlan.planAllFee"].value='${(quarterPlan.planAllFee?string('#,###,##0.00'))?if_exists}';
        </#if>
        <#if req.getParameter('errorFlag')?has_content>
          alert("${action.getText('delete.repairMaintenanceDetail.failure')}");
        </#if>
        <#if overBudgetFee?exists>
          if (#{overBudgetFee} > 0) {
            parent.getObjByNameRe("warnningMessage").innerText="";
            parent.getObjByNameRe("warnningMessage").innerText='${quarterPlan.department.name?if_exists}' + "维修保养费用已超出预算" + '#{overBudgetFee}' + "元";
          }
        <#else>
          parent.getObjByNameRe("warnningMessage").innerText="";
        </#if>
	  }
      function open_detailDialog(planId, repairMaintenanceDetailId) {
	    var url = '${req.contextPath}/popup/editQuarterRepairMaintenanceDetail.html?quarterPlan.id='+planId;	      		  		
	    if (repairMaintenanceDetailId != null) {
	      url = url + "&repairMaintenanceDetail.id=" + repairMaintenanceDetailId;
	    }
	    popupModalDialog(url,800,600);
	    var reloadUrl = "${req.contextPath}/year/tooling/quarterPlan/listRepairMaintenanceDetails.html?quarterPlan.id=#{quarterPlan.id}&yearQuarterFlag=QUARTER_PLAN";
	    //如果是点击项目号进入维护页面，保存当前页，已使在维护页面点击关闭，会回到当前页
        if (null != repairMaintenanceDetailId) {
        	reloadUrl = reloadUrl + '&pagingPage=${pagingPage?if_exists}'
        }
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