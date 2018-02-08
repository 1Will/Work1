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
<@htmlPage title="${action.getText('quarterPlanSeacher.title')}">
  <@ww.form namespace="'/year/tooling/quarterPlan'" name="'listQuarterPlan'" action="'searchQuarterPlans'" method="'post'">
    <@ww.token name="searchQuarterPlansToken"/>   
	<#include "quarterPlanSearcher.ftl"/>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
 	<@buttonBar>
      <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
      <#if !(action.isReadOnly())>
        <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/year/tooling/quarterPlan/editQuarterPlan.html?readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}"/>  
      </#if>
    </@buttonBar>
 	<@list title="${action.getText('quarterPlan.list')}" 
           includeParameters="planNo|name|readOnly|eamAuthentication|planCreator.name|department.id|qarterType.id|year|planCreatedDate_start|planCreatedDate_end|onlyValid|onlyInvalid" 
           fieldMap="like:planNo|name|planCreator.name,date:planCreatedDate_start|planCreatedDate_end">
	  <@vlh.checkbox property="id" name="quarterPlanIds">
        <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <@vcolumn title="${action.getText('quarterPlan.planNo')}" property="planNo" sortable="desc">
        <#if !(action.isOnlyInvalid())>
          <a href="editQuarterPlan.html?quarterPlan.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}">${object.planNo}</a>
        </#if>
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('quarterPlan.name')}" property="name" sortable="desc">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('department')}" property="department.name" sortable="desc">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('quarterPlan.year')}" property="year" format="yyyy" sortable="desc">
        <@alignCenter/>
      </@vcolumn>
      <#assign qarter=''/>
      <#if '${object.qarterType}' == 'FIRST_QUARTER'>
        <#assign qarter="${action.getText('firstQuarter')}"/>
      <#elseif '${object.qarterType}' == 'SECOND_QUARTER'>
        <#assign qarter="${action.getText('secondQuarter')}"/>
      <#elseif '${object.qarterType}' == 'THIRD_QUARTER'>
        <#assign qarter="${action.getText('thirdQuarter')}"/>
      <#elseif '${object.qarterType}' == 'FOURTH_QUARTER'>
        <#assign qarter="${action.getText('fourthQuarter')}"/>
      </#if>
      <@vcolumn title="${action.getText('quarterPlan.qarterType')}" property="qarterType" sortable="desc">
        ${qarter}
        <@alignCenter/>
      </@vcolumn>
      <@vcolumn title="${action.getText('quarterPlan.planCreator')}" property="planCreator.name" sortable="desc">
	    <@alignLeft/>
      </@vcolumn>
	  <@vcolumn title="${action.getText('quarterPlan.planCreatedDate')}" property="planCreatedDate" format="yyyy-MM-dd" sortable="desc">
	    <@alignCenter/>
      </@vcolumn>
      <@vcolumn title="${action.getText('quarterPlan.planAllFee')}" property="planAllFee" sortable="desc" format="${action.getText('currencyFormat')}">
	    <@alignRight/>
      </@vcolumn>
    </@list>
    <#if !first>
      <@buttonBar>
       <#if !(action.isReadOnly())>
	    <@eam2008_disabledOrEnabled_button message="${action.getText('quarterPlan')}" boxName="quarterPlanIds" jsFunctionName="checkInvalidParms()"/>
	   </#if>
	    <#if '${eamAuthentication?if_exists}' == 'Collect'>
	      <@vbutton name="printPdf"  class="button" value="${action.getText('pdfCollectPrint')}" onclick="open_quarterPlanPdf()"/>
	      <@vbutton name="printXls"  class="button" value="${action.getText('xlsCollectPrint')}" onclick="open_quarterrPlanXls()"/>
	    </#if>
	  </@buttonBar>
    </#if>
  </@ww.form>
</@htmlPage>
 <script language="JavaScript" type="text/JavaScript">
      <#if !first && valueListNoRecords>
        <#if '${eamAuthentication?if_exists}' == 'Collect'>
         document.forms[0].elements["printPdf"].disabled="true";
         document.forms[0].elements["printXls"].disabled="true";
       </#if>
     </#if>
    function open_quarterPlanPdf(){
      var year=document.forms[0].elements["year"].value;
      var url='${req.contextPath}/reports/quarter/quarterPlanColectReportList.pdf?year='+document.forms[0].elements["year"].value+'&department.id='+ document.forms[0].elements["department.id"].value
      +'&qarterType.id='+document.forms[0].elements["qarterType.id"].value;
        if(year==''){
         alert("${action.getText('year.not.null')}");
         return false;
      }
      url = encodeURI(url);
      window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
     }
     function  open_quarterrPlanXls(){
      var year=document.forms[0].elements["year"].value;
      var url='${req.contextPath}/reports/quarter/quarterPlanColectReportList.xls?year='+document.forms[0].elements["year"].value+'&department.id='+ document.forms[0].elements["department.id"].value
       +'&qarterType.id='+document.forms[0].elements["qarterType.id"].value;
        if(year==''){
         alert("${action.getText('year.not.null')}");
         return false;
      }
      url = encodeURI(url);
      window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
     }
 
 
 
 
 </script>