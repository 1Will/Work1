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

<@htmlPage title="${action.getText('preRepairPlanSearcher.title')}">
<base target="_self">
<@ww.form name="'ListPreRepairPlan'" action="'preRepairPlanSelector'" method="'post'">
    <@ww.token name="preRepairPlanSelectorToken"/>   
	 	 <#include "preRepairPlanSearcher.ftl"/>
	 	 <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
	 	 <@buttonBar>
	 		<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
	     </@buttonBar>
	 	 <@list title="${action.getText('preRepairPlan.list')}" 
        		includeParameters="planNo|planName|department.id|planProcFlag|toolingDevFlag" 
        		fieldMap="like:planNo|planName" >
        	<#assign department=""/>
        	<#assign planCreator=""/>
        	<#assign planAuditor=""/>
        	<#if object.department?exists>
        	  <#assign department="${object.department.id?if_exists}"/>
        	</#if>
        	<#if object.planCreator?exists>
        	  <#assign planCreator="${object.planCreator.name?if_exists}"/>
        	</#if>
        	<#if object.planAuditor?exists>
        	  <#assign planAuditor="${object.planAuditor.name?if_exists}"/>
        	</#if>
            <@vcolumn title="${action.getText('preRepairPlanNo')}" property="planNo" sortable="desc">
            	<a href="javascript: returnDialog(new Array(#{object.id}, '${object.planNo}', '${object.name}', '${department}', '${object.beginDate}', '${planCreator}', '${planAuditor}','${object.planCreatedTime}'));">${object.planNo}</a>
        	</@vcolumn>
            <@vcolumn title="${action.getText('preRepairPlanName')}" property="name" sortable="desc"/>
        	<@vcolumn title="${action.getText('department')}" property="department.name"/>
      </@list>
</@ww.form>
</@htmlPage>
