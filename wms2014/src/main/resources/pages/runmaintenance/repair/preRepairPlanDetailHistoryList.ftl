<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
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
<#-- $Id: deviceSelector.ftl 7932 2007-10-22 04:11:59Z qsun $ -->

<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('preRepairPlanDetailHistorySearcher.title')}">
<base target="_self">
<@ww.form name="'preRepairPlanDetail'" action="'preRepairPlanDetailHistorySelector'" method="'post'">
    <@ww.token name="preRepairPlanDetailHistorySelectorToken"/>   
	 	 <#include "preRepairPlanDetailHistorySearcher.ftl"/>
	 	 <@buttonBar>
	 		<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
	     </@buttonBar>
	 	 <@list title="${action.getText('preRepairPlanDetailHistory.list')}" 
        		includeParameters="procStatus|planCreatedTime_start|planCreatedTime_end|toolingDevFlag|department.id|preRepairPlan.id" 
        		fieldMap="like:,date:planCreatedTime_start|planCreatedTime_end" >
        	<@vlh.checkbox property="id" name="preRepairPlanDetailHistoryIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <#if toolingDevFlag?exists>
             <#if toolingDevFlag == 'DEVICE'>
            <@vcolumn title="${action.getText('PreRepairPlanDetail.toolingDevNo')}" property="asset.deviceNo">
              <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('PreRepairPlanDetail.toolingDevName')}">
               <#if object.asset?exists>
                ${object.asset.name?if_exists}
               <#else>
                ${object.toolingName?if_exists}
               </#if>
	          <@alignLeft/>
            </@vcolumn>
            <#else>
            <@vcolumn title="${action.getText('PreRepairPlanDetail.toolingNo')}" property="asset.deviceNo">
              <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('PreRepairPlanDetail.toolingName')}">
               <#if object.asset?exists>
                ${object.asset.name?if_exists}
               <#else>
                ${object.toolingName?if_exists}
               </#if>
	          <@alignLeft/>
            </@vcolumn>
            </#if>
           </#if>
        	<@vcolumn title="${action.getText('PreRepairPlanDetail.content')}" property="content">
	          <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('PreRepairPlanDetail.planCreatedTime')}" property="plan.planCreatedTime" format="${action.getText('dateFormat')}">
        	  <@alignCenter/>
            </@vcolumn>
            <#assign procStatus=''/>
        	<#if object.procResult?exists>
        	  <#if (object.procResult)?string=='UNFINISHED'>
        		    <#assign procStatus="${action.getText('PreRepairPlanDetail.unfinished')}"/>
        	  <#elseif (object.procResult)?string=='FINISHED'>
        		    <#assign procStatus="${action.getText('PreRepairPlanDetail.finished')}"/>
        	  <#elseif (object.procResult)?string=='CANCEL'>
        		    <#assign procStatus="${action.getText('PreRepairPlanDetail.cancel')}"/>
        	  <#else>
        	        <#assign procStatus="${action.getText('PreRepairPlanDetail.shift')}"/>
        	  </#if>
        	</#if>
            <@vcolumn title="${action.getText('procStatus')}">
              ${procStatus}
        	  <@alignLeft/>
            </@vcolumn>
      </@list>
      <#if !first>
        <@buttonBar>
            <@vsubmit name="'choose'" value="'${action.getText('choose')}'" onclick="'return confirmSelects(\"preRepairPlanDetailHistoryIds\");'">
	      		<#--<@ww.param name="'onclick'" value="'return confirmSelects(\"deviceIds\");'"/>-->
              <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
      </#if>
</@ww.form>
<script language="JavaScript" type="text/JavaScript"> 
  function confirmSelects(boxname) {
    if (!hasChecked(boxname)) {
      alert("${action.getText('tooling.noSelect')}");
  	  return false;
    }
    choosePreRepairPlanDetailHistorys(boxname);
    return true;
  }
  function choosePreRepairPlanDetailHistorys(boxname) {
    var selector = document.getElementsByName(boxname);
	if (!selector) {
	  return false;
	}
    var preRepairPlanDetailHistoryIds = "";
	if (selector.length) {
	  for (i = 0; i < selector.length; i++) {
	    if (selector[i].checked) {
		  preRepairPlanDetailHistoryIds += selector[i].value + ",";
		}
	  }
	}
	returnDialog(preRepairPlanDetailHistoryIds,false);
  }
</script>
</@htmlPage>
