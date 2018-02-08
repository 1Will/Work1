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
<#include "../../../../includes/eam2008.ftl" />
<base target="_self">
<@htmlPage title="${action.getText('quarterPlanSeacher.title')}">
  <@ww.form  name="'listQuarterPlanChoose'" action="'searchQuarterPlanchooses'" method="'post'">
    <@ww.token name="searchQuarterPlansToken"/>   
	<#include "quarterPlanChooseSearcher.ftl"/>
	<#--<#if quarterPlan.id?exists>
		<@ww.hidden name="'quarterPlan.id'" value="'#{quarterPlan.id}'"/>
	</#if>-->
 	<@buttonBar>
      <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>
 	<@list title="" excel=false setupTable=false
           includeParameters="planNo|name|department.id|qarterType.id|detailType.id|requestDate_start|requestDate_end" 
           fieldMap="like:planNo|name|,date:requestDate_start|requestDate_end" >
	  <@vlh.checkbox property="id" name="quarterPlanIds">
        <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <@vcolumn title="${action.getText('planNo')}" property="quarterPlan.planNo">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('name')}" property="quarterPlan.name">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('department')}" property="quarterPlan.department.name">
        <@alignLeft/>
      </@vcolumn>
        <#assign PlanQuarterType = ''/>
          <#if object.quarterPlan?exists>
             <#if '${object.quarterPlan.qarterType}' == 'FIRST_QUARTER'>
               <#assign PlanQuarterType = "${action.getText('FIRST.QUARTER')}"/>
               </#if>
           </#if>
            <#if object.quarterPlan?exists>
              <#if '${object.quarterPlan.qarterType}' == 'SECOND_QUARTER'>
                  <#assign PlanQuarterType = "${action.getText('SECOND.QUARTER')}"/>
              </#if>
            </#if>
           <#if object.quarterPlan?exists>
             <#if '${object.quarterPlan.qarterType}' == 'THIRD_QUARTER'>
                  <#assign PlanQuarterType = "${action.getText('THIRD.QUARTER')}"/>
                  </#if>
            </#if>  
            <#if object.quarterPlan?exists>
              <#if '${object.quarterPlan.qarterType}' == 'FOURTH_QUARTER'>
                  <#assign PlanQuarterType = "${action.getText('FOURTH.QUARTER')}"/>
            </#if>
          </#if>
             <@vcolumn title="${action.getText('Quarter')}">
             ${PlanQuarterType}
                 <@alignLeft/>
            </@vcolumn>
        <#assign yearPlanCategory = ''/>
             <#if '${object.detailType}' == 'TOOLING_MAKE'>
               <#assign yearPlanCategory = "${action.getText('TOOLING.MAKE')}"/>
               </#if>
              <#if '${object.detailType}' == 'SPARE_PURCHASE'>
                  <#assign yearPlanCategory = "${action.getText('SPARE.PURCHASE')}"/>
              </#if>
             <#if '${object.detailType}' == 'REPAIR_MAINTENANCE'>
                  <#assign yearPlanCategory = "${action.getText('REPAIR.MAINTENANCE')}"/>
                  </#if>
              <#if '${object.detailType}' == 'TECH_ALTER'>
                  <#assign yearPlanCategory = "${action.getText('TECH.ALTER')}"/>
            </#if>
             <@vcolumn title="${action.getText('detailCategoryName')}">
             ${yearPlanCategory}
                 <@alignLeft/>
            </@vcolumn>
      <@vcolumn title="${action.getText('quarterPlan.year')}" property="quarterPlan.year">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('planNo1')}" property="graphNo">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('quarterPlan1.name')}" property="name">
        <@alignLeft/>
      </@vcolumn>
        <@vcolumn title="${action.getText('Category')}" property="categoryName">
        <@alignLeft/>
      </@vcolumn>
     <#-- <@vcolumn title="${action.getText('detailCategoryName')}" property="detailCategoryName">
        <@alignLeft/>
      </@vcolumn>-->
      <@vcolumn title="${action.getText('specification')}" property="specification">
        <@alignCenter/>
      </@vcolumn>
      <@vcolumn title="${action.getText('modelr')}" property="model">
	    <@alignLeft/>
      </@vcolumn>
	  <@vcolumn title="${action.getText('unitPrice')}" property="unitPrice">
	    <@alignCenter/>
      </@vcolumn>
      <@vcolumn title="${action.getText('number')}" property="number">
	    <@alignRight/>
      </@vcolumn>
      <@vcolumn title="${action.getText('allPrice')}" property="allPrice">
	    <@alignRight/>
      </@vcolumn>
      <@vcolumn title="${action.getText('requestDate')}" property="requestDate" format="yyyy-MM-dd">
	    <@alignRight/>
      </@vcolumn>
      <@vcolumn title="${action.getText('requestReason')}" property="requestReason">
	    <@alignRight/>
      </@vcolumn>
   
      <@vcolumn title="${action.getText('comment')}" property="comment">
	    <@alignRight/>
      </@vcolumn>
      <#--<#assign lockedFlag1 = ''/>
       <#if object.lockedFlag>
         <#assign lockedFlag1 = "${action.getText('unlocked')}"/>
       <#else>
           <#assign lockedFlag1 = "${action.getText('locked')}"/>
       </#if>
        <@vcolumn title="${action.getText('lockedFlag1')}">
           ${lockedFlag1}
                 <@alignCenter/>
        </@vcolumn>-->
    </@list>
    <#if !first>
      <@buttonBar>
	   <@vsubmit name="'choose'" value="'${action.getText('choose')}'" onclick="'return confirmSelects(\"quarterPlanIds\");'">
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
	  </@buttonBar>
    </#if>
  </@ww.form>
</@htmlPage>

 <script language="javascript">
        function confirmSelects(boxname) {
	      if (!hasChecked(boxname)) {
	      	return false;
	      }
	      chooseSubscribes(boxname);
	      return true;
	    }
	    	      	
	   function chooseSubscribes(boxname) {
	     var selector = document.getElementsByName(boxname);
	     if (!selector) {
	       return false;
	     }
		 var quarterPlanIds= "";
		 if (selector.length) {
		   for (i = 0; i < selector.length; i++) {
		     if (selector[i].checked) {
			   quarterPlanIds += selector[i].value + ",";
			 }
		   }
		 }
		 returnDialog(quarterPlanIds,false);
	   }
        </script>
