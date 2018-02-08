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
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('preRepairRuleSelector.title')}">
<base target="_self">
  <@ww.form name="'listPreRepairRule'" action="'preRepairRuleSelector'" method="'post'">
    <@ww.token name="preRepairRuleSelectorToken"/>   
    <#include "preRepairDeviceSearcher.ftl" />
 	<@buttonBar>
      <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>
    <#assign itemNo = 1/>
 	<@list title="${action.getText('preRepairRuleSelector.list')}" 
           includeParameters="deviceNo|deviceName|category.id|device.emphasis|device.standard|device.managementLevel|cardCreatedTime_start|cardCreatedTime_end" 
           fieldMap="like:deviceNo|deviceName|,date:cardCreatedTime_start|cardCreatedTime_end" >
	  <@vlh.checkbox property="id" name="preRepairRuleIds">
        <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <#--<@vcolumn title="${action.getText('serialNo')}">
        #{itemNo}
        <@alignCenter />
      </@vcolumn>-->
      <#assign itemNo = itemNo + 1/>
      <@vcolumn title="${action.getText('device.no')}" property="asset.deviceNo" sortable="desc">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('device.name')}" property="asset.name" sortable="desc">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('department')}" property="asset.department.name" attributes="width='110'">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('device.category')}"  property="asset.deviceType.name" sortable="desc">
    	<@alignLeft />
      </@vcolumn>
      <#if (object.asset.emphasis?string)=='true'>
        <#assign emphasis="${action.getText('YES')}"/>
      <#else>
        <#assign emphasis="${action.getText('NO')}"/>
      </#if>
      <@vcolumn title="${action.getText('device.emphasis')}">
        ${emphasis}
        <@alignLeft/>
      </@vcolumn>
      <#if (object.asset.standard?string)=='true'>
          <#assign standard="${action.getText('YES')}"/>
      <#else>
          <#assign standard="${action.getText('NO')}"/>
      </#if>
      <@vcolumn title="${action.getText('device.standard')}">
        ${standard}
        <@alignLeft/>
      </@vcolumn>
      <#assign level=''/>
      <#if object.asset.managementLevel?exists>
        <#if (object.asset.managementLevel)=='MAJOR_DEVICES'>
          <#assign level="${action.getText('device.majorDevice')}"/>
        <#elseif (object.asset.managementLevel)=='GENERAL_DEVICES'>
          <#assign level="${action.getText('device.generalDevice')}"/>
        <#elseif (object.asset.managementLevel)=='IMPORTANT_DEVICES'>
          <#assign level="${action.getText('device.importantDevice')}"/>
        </#if>
      </#if>
      <@vcolumn title="${action.getText('device.managementLevel')}">
    	${level}
    	<@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('device.cardCreatedTime')}" property="asset.cardCreatedTime" format="${action.getText('dateFormat')}" sortable="desc"/>    
      <@vcolumn title="${action.getText('device.position')}" property="position">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('device.content')}" property="content" attributes="width='200'">
        <@alignLeft/>
      </@vcolumn>
      <#--<@vcolumn title="${action.getText('device.lastRepairDate')}" property="lastRepairDate" format="${action.getText('dateFormat')}" sortable="desc"/>-->    
      <@vcolumn title="${action.getText('device.maxRunHour')}" property="maxRunHour" sortable="desc">
        <@alignRight/>
      </@vcolumn>
      <@vcolumn title="${action.getText('device.preRepairTime')}" property="preRepairTime" sortable="desc">
        <@alignRight/>
      </@vcolumn>
    </@list>
    <#if !first>
      <@buttonBar>
        <@vsubmit name="'choose'" value="'${action.getText('choose')}'" onclick="'return confirmSelects(\"preRepairRuleIds\");'">
          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
        </@vsubmit>
      </@buttonBar>
    </#if>
  </@ww.form>
  <script language="JavaScript" type="text/JavaScript"> 
    function confirmSelects(boxname) {
      if (!hasChecked(boxname)) {
        alert("${action.getText('preRepairRuleIds.noSelect')}");
  	    return false;
      }
      choosePreRepairRules(boxname);
      return true;
    }
    function choosePreRepairRules(boxname) {
      var selector = document.getElementsByName(boxname);
	  if (!selector) {
	    return false;
	  }
      var preRepairRuleIds = "";
	  if (selector.length) {
	    for (i = 0; i < selector.length; i++) {
	      if (selector[i].checked) {
		    preRepairRuleIds += selector[i].value + ",";
		  }
	  }
	}
	returnDialog(preRepairRuleIds,false);
  }

</script>
</@htmlPage>