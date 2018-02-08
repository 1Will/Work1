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


<@framePage title="${action.getText('deviceChangeList.title')}">
  <@ww.form name="'listDeviceChanges'" action="'searchDeviceChanges'" method="'post'">
    <@ww.token name="searchDeviceChangesToken"/>
    <#if runmaintainPlanDetail.id?exists>
      <@ww.hidden name="'runmaintainPlanDetail.id'" value="'#{runmaintainPlanDetail.id?if_exists}'"/>
    </#if>
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<#assign itemNo=1/>
    <@list title="" excel=false setupTable=false 
     	   includeParameters="runmaintainPlanDetail.id|readOnly"  fieldMap="like:" >
 	  <@vlh.checkbox property="id" name="deviceChangeIds">
        <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <@vcolumn title="${action.getText('deviceChange.serialNo')}">
        <a href="#" onclick="open_detailDialog(#{runmaintainPlanDetail.id}, #{object.id});return false;">#{itemNo}</a>
        <@alignCenter />
      </@vcolumn>
      <#assign itemNo = itemNo + 1/>
      <@vcolumn title="${action.getText('deviceChange.content')}" property="content">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('deviceChange.fee')}" property="fee">
        <@alignRight />
      </@vcolumn>
      <@vcolumn title="${action.getText('deviceChange.comment')}" property="comment">
        <@alignLeft />
      </@vcolumn>
    </@list>
    <#if !first>
 	  <@buttonBar>
 	  <#if !(action.isReadOnly())>
        <input type="button" class="button" name="new" value="${action.getText('new')}" onclick="open_detailDialog(#{runmaintainPlanDetail.id},null)"/>
        <#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('deviceChange')}?" />
        <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
          <@ww.param name="'onclick'" value="'return confirmDeletes(\"deviceChangeIds\", \"${confirmMessage}\");'"/>
          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
        </@vsubmit>
        </#if>
      </@buttonBar>
    </#if>
    </@ww.form>
    <script language="javascript">
      window.onload = function () {
        <#--刷新总费用-->
        parent.document.forms["runmaintainPlanDetail"].elements["runmaintainPlanDetail.allFee"].value='${runmaintainPlanDetail.allFee?if_exists}';
        <#--刷新设备改造总费用-->
	    parent.document.forms["runmaintainPlanDetail"].elements["runmaintainPlanDetail.changeFee"].value='${runmaintainPlanDetail.changeFee?if_exists}';
	  }
      function open_detailDialog(planId, deviceChangeId) {
	    var url = '${req.contextPath}/popup/editDeviceChange.html?runmaintainPlanDetail.id='+planId;	      		 		
	    if (deviceChangeId != null) {
	      url = url + "&deviceChange.id=" + deviceChangeId;
	    }
	    popupModalDialog(url,800,600);
	    var reloadUrl = "${req.contextPath}/year/device/runmaintainPlan/listDeviceChanges.html?runmaintainPlanDetail.id=#{runmaintainPlanDetail.id}";
	    self.location.href = reloadUrl;
	  }
    </script>
     
</@framePage>