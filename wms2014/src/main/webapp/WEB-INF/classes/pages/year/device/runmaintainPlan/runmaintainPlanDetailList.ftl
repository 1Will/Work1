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


<@framePage title="${action.getText('runmaintainPlanDetailProfile.title')}">
  <@ww.form name="'listRunmaintainPlanDetails'" action="'searchRunmaintainPlanDetails'" method="'post'">
    <@ww.token name="searchRunmaintainPlanDetailsToken"/>
    <#if runmaintainPlan.id?exists>
      <@ww.hidden name="'runmaintainPlan.id'" value="'#{runmaintainPlan.id?if_exists}'"/>
    </#if>
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    <@ww.hidden name="'addDeviceIds'" value=""/>
    <@ww.hidden name="'addDevice'" value=""/>
	<#assign itemNo=1/>
    <@list title="" excel=false setupTable=false 
     	   includeParameters="runmaintainPlan.id|readOnly"  fieldMap="like:" >
 	  <@vlh.checkbox property="id" name="runmaintainPlanDetailIds">
        <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <input type="hidden" name="hiddenDeviceIds" value="#{object.device.id}"/>
      <@vcolumn title="${action.getText('runmaintainPlanDetail.serialNo')}">
        <a href="#" onclick="open_detailDialog(#{runmaintainPlan.id}, #{object.id});return false;">#{itemNo}</a>
        <@alignCenter />
      </@vcolumn>
      <#assign itemNo = itemNo + 1/>
      <@vcolumn title="${action.getText('runmaintainPlanDetail.deviceNo')}" property="device.deviceNo">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('runmaintainPlanDetail.deviceName')}" property="device.name">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('runmaintainPlanDetail.deviceSpecification')}" property="device.specification">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('runmaintainPlanDetail.deviceModel')}" property="device.model">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('runmaintainPlanDetail.dailyRepairFee')}" property="dailyRepairFee">
        <@alignRight />
      </@vcolumn>
      <@vcolumn title="${action.getText('runmaintainPlanDetail.pricisionCheckFee')}" property="pricisionCheckFee">
        <@alignRight />
      </@vcolumn>
      <@vcolumn title="${action.getText('runmaintainPlanDetail.changeFee')}" property="changeFee">
        <@alignRight />
      </@vcolumn>
      <@vcolumn title="${action.getText('runmaintainPlanDetail.pivotalSpareFee')}" property="pivotalSpareFee">
        <@alignRight />
      </@vcolumn>
      <@vcolumn title="${action.getText('runmaintainPlanDetail.allFee')}" property="allFee">
        <@alignRight />
      </@vcolumn>
      <@vcolumn title="${action.getText('runmaintainPlanDetail.comment')}" property="comment">
        <@alignLeft />
      </@vcolumn>
    </@list>
    <#if !first>
 	  <@buttonBar>
 	  <#if !(action.isReadOnly())>
        <input type="button" class="button" name="new" value="${action.getText('new')}" onclick="multi_select_device_openDialog()"/>
        <#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('runmaintainPlanDetail')}?" />
        <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
          <@ww.param name="'onclick'" value="'return confirmDeletes(\"runmaintainPlanDetailIds\", \"${confirmMessage}\");'"/>
          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
        </@vsubmit>
        </#if>
      </@buttonBar>
    </#if>
    </@ww.form>
    <script language="javascript">
      window.onload = function () {
      	<#--刷新计划总费用-->
	    parent.document.forms["runmaintainPlan"].elements["runmaintainPlan.planAllFee"].value='${runmaintainPlan.planAllFee?if_exists}';
	  }
	  /*
	   * 打开运维计划明细维护页面
	  */
      function open_detailDialog(planId, runmaintainPlanDetailId) {
	    var url = '${req.contextPath}/popup/editRunmaintainPlanDetail.html?readOnly=${req.getParameter('readOnly')?if_exists}&runmaintainPlan.id='+planId;	      		 		
	    if (runmaintainPlanDetailId != null) {
	      url = url + "&runmaintainPlanDetail.id=" + runmaintainPlanDetailId;
	    }
	    popupModalDialog(url,1024,768);
	    var reloadUrl = "${req.contextPath}/year/device/runmaintainPlan/listRunmaintainPlanDetails.html?readOnly=${req.getParameter('readOnly')?if_exists}&runmaintainPlan.id=#{runmaintainPlan.id}";
	    self.location.href = reloadUrl;
	    //self.location.reload();
	  }
	 /*
      * 新建选择设备
     */
    function multi_select_device_openDialog() {
     var url = '${req.contextPath}/popup/deviceSelector.html';
     <#if !valueListNoRecords>
	   var oldDeviceIds = document.getElementsByName("hiddenDeviceIds");
	   var ary = new Array();
	   for (var i=0; i<oldDeviceIds.length; i++) {
	     ary.push(oldDeviceIds[i].value);
	   }
	   eam2008_multi_select_device_OpenDialog(url,refresh_multi_device_information,ary);
	 <#else>
	   eam2008_multi_select_device_OpenDialog(url,refresh_multi_device_information);
	 </#if>
    }
    function refresh_multi_device_information(reslut) {
      if (null != result) {
        var addDeviceIds = result.substring(0, result.lastIndexOf(","));
        document.forms[0].elements["addDeviceIds"].value = addDeviceIds;
        document.forms[0].elements["addDevice"].value = "addDevices";
        document.forms[0].submit();
      }
    }
    </script>
     
</@framePage>