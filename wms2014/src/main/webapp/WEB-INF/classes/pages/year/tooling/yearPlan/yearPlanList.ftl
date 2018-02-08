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
<@htmlPage title="${action.getText('yearPlanSeacher.title')}">
 <STYLE TYPE="text/css" >
 .displayRed{
   font-weight: bold;
   color: #FFFFFF;
   background-color: #FFCC66;	 
 }
 </STYLE>
  <@ww.form namespace="'/year/tooling/yearPlan'" name="'listYearPlan'" action="'searchYearPlans'" method="'post'">
    <@ww.token name="searchYearPlansToken"/>   
	<#include "yearPlanSearcher.ftl"/>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
 	<@buttonBar>
      <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
      <#if !(action.isReadOnly())>
        <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/year/tooling/yearPlan/editYearPlan.html?readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}"/>  
      </#if>
    </@buttonBar>
 	<@list title="${action.getText('yearPlan.list')}" 
           includeParameters="planNo|readOnly|eamAuthentication|year|name|planCreator.name|department.id|planCreatedDate_start|planCreatedDate_end|onlyValid|onlyInvalid" 
           fieldMap="like:planNo|name|planCreator.name,date:planCreatedDate_start|planCreatedDate_end" >
	  <@vlh.checkbox property="id" name="yearPlanIds">
        <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <@ww.hidden name="'isLocked'" value="'${object.lockedFlag?string}'"/>
      <@vcolumn title="${action.getText('yearPlan.planNo')}" property="planNo" sortable="desc">
        <#if !(action.isOnlyInvalid())>
          <a href="editYearPlan.html?yearPlan.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}">
            ${object.planNo}
          </a>
        </#if>
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('yearPlan.name')}" property="name" sortable="desc">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('department')}" property="department.name" sortable="desc">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('yearPlan.year')}" property="year" format="yyyy" sortable="desc">
        <@alignCenter/>
      </@vcolumn>
      <@vcolumn title="${action.getText('yearPlan.planCreator')}" property="planCreator.name" sortable="desc">
	    <@alignLeft/>
      </@vcolumn>
	  <@vcolumn title="${action.getText('yearPlan.planCreatedDate')}" property="planCreatedDate" format="yyyy-MM-dd" sortable="desc">
	    <@alignCenter/>
      </@vcolumn>
      <@vcolumn title="${action.getText('yearPlan.planAllFee')}" property="planAllFee" sortable="desc" format="${action.getText('currencyFormat')}">
	    <@alignRight/>
      </@vcolumn>
    </@list>
    <#if !first>
      <@buttonBar>
       <#if !(action.isReadOnly())>
	    <@eam2008_disabledOrEnabled_button_have_locked_unLocked message="${action.getText('yearPlan')}" boxName="yearPlanIds" jsFunctionName="checkInvalidParms()" jsFunctionName1="validatePrompt()"/>
	   </#if>
           <#if '${eamAuthentication?if_exists}' == 'Collect'>
	      <#assign confirmMessage = "${action.getText('confirm.locked')}${action.getText('yearPlan')}? ${action.getText('locked.result')}" />
          <@vsubmit name="'locked'" value="'${action.getText('locked')}'">
            <@ww.param name="'onclick'" value="'return validateLocked( \"${confirmMessage}\");'"/>
            <@ww.param name="'disabled'" value="${valueListNoRecords?string}||${action.isOnlyInvalid()?string}"/>
          </@vsubmit>
          <#assign confirmMessage = "${action.getText('confirm.unLocked')}${action.getText('yearPlan')}? ${action.getText('unLocked.result')}" />
          <@vsubmit name="'unLocked'" value="'${action.getText('unLocked')}'">
            <@ww.param name="'onclick'" value="'return validateUnLocked((\"${confirmMessage}\"));'"/>
            <@ww.param name="'disabled'" value="${valueListNoRecords?string}||${action.isOnlyInvalid()?string}"/>
          </@vsubmit>
          <#assign confirmMessage = "${action.getText('confirm.unLocked')}${action.getText('yearPlan')}? ${action.getText('unLocked.result')}" />
          <@vsubmit name="'generateYearBudget'" value="'${action.getText('generateYearBudget')}'">
            <@ww.param name="'onclick'" value="'return validateGenerate(\"${confirmMessage}\",checkInvalidParms());'"/>
            <@ww.param name="'disabled'" value="${valueListNoRecords?string}||${action.isOnlyInvalid()?string}"/>
          </@vsubmit>
          <#if !(action.isOnlyInvalid())>
             <@vbutton name="printPdf"  class="button"  value="${action.getText('pdfCollectPrint')}" onclick="open_yearPlanPdf()"/>
	         <@vbutton name="printXls"  class="button" value="${action.getText('xlsCollectPrint')}" onclick="open_yearPlanXls()"/>
          <#else>
             <@vdisabledbutton name="printPdf"  class="button"  disabled="true" value="${action.getText('pdfCollectPrint')}" onclick="open_yearPlanPdf()"/>
	         <@vdisabledbutton name="printXls"  class="button"  disabled="true" value="${action.getText('xlsCollectPrint')}" onclick="open_yearPlanXls()"/>
            
          </#if>
	   </#if>
	  </@buttonBar>
    </#if>
  </@ww.form>
  <script language="JavaScript" type="text/JavaScript">
     window.onload = function () {
     <#if !first && valueListNoRecords>
       <#if '${eamAuthentication?if_exists}' == 'Collect'>
         document.forms[0].elements["printPdf"].disabled="true";
         document.forms[0].elements["printXls"].disabled="true";
       </#if>
     </#if>
       addRowsColor();     //如果该年度计划已经锁定，则改变已锁定行的颜色
     }
     function open_yearPlanPdf(){
      var year=document.forms[0].elements["year"].value;
      
      var url='${req.contextPath}/reports/yearPlan/LY_YearPlan.pdf?year='+document.forms[0].elements["year"].value+'&department.id='+ document.forms[0].elements["department.id"].value;
        if(year==''){
         alert("${action.getText('year.not.null')}");
         return false;
      }
      url = encodeURI(url);
      window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
     }
     function  open_yearPlanXls(){
      var year=document.forms[0].elements["year"].value;
      var url='${req.contextPath}/reports/yearPlan/LY_YearPlan.xls?year='+document.forms[0].elements["year"].value+'&department.id='+ document.forms[0].elements["department.id"].value;
        if(year==''){
         alert("${action.getText('year.not.null')}");
         return false;
      }
      url = encodeURI(url);
      window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
     }
     //生成年度预算触发
     function validateGenerate(message, checkFun) {
      var year=document.forms[0].elements["year"].value;
      if(year==''){
        alert("${action.getText('year.not.null')}");
        return false;
      }
      if (confirmGenerate("yearPlanIds",message)) {
        checkFun;
        return true;
      }
      return false;
     }
     //点击"生成年度预算"判读是否有选中记录,以及选中的记录是否都是已锁定的 
     function confirmGenerate(yearPlanIds, message) {
       if (!hasChecked(yearPlanIds)) {
         alert("${action.getText('noSelectGenerateBudgetRecord')}");
         return false;
       }
       var selector = document.getElementsByName(yearPlanIds);       //年度计划id
       var lockedFlag = document.getElementsByName("isLocked");      //年度计划是否锁定标识
       for (i = 0; i < selector.length; i++) {
         if (selector[i].checked) {
           if(lockedFlag[i].value == "false") {
             alert("${action.getText('selectGenerateBudgetRecord.not.locked')}");
             return false;
           }     
         }
       }
       if (!confirm("${action.getText('confirm.generateBudgetRecord')}")) {
         return false;
       }
       return true;
     }
     //点击锁定按钮触发
     function validateLocked(message) {
       var selector = document.getElementsByName("yearPlanIds");       //年度计划id
       var lockedFlag = document.getElementsByName("isLocked");      //年度计划是否锁定标识
       for (i = 0; i < selector.length; i++) {
         if (selector[i].checked) {
           if(lockedFlag[i].value == "true") {
               alert("${action.getText('select.record.have.locked')}");
               return false;
           }     
         }
       }
       if (confirmLockeds("yearPlanIds",message)) {
         checkInvalidParms();
         return true;
       }
       return false;
     }
     function validateUnLocked(message) {
       var selector = document.getElementsByName("yearPlanIds");       //年度计划id
       var lockedFlag = document.getElementsByName("isLocked");      //年度计划是否锁定标识
       for (i = 0; i < selector.length; i++) {
         if (selector[i].checked) {
           if(lockedFlag[i].value == "false") {
               alert("${action.getText('select.record.have.unLocked')}");
               return false;
           }     
         }
       }
       if (confirmUnLockeds("yearPlanIds",message)) {
         checkInvalidParms();
         return true;
       }
       return false;
     }
     //如果该年度计划已经锁定，则改变已锁定行的颜色
     function addRowsColor(){
       var lockedFlagRow = document.getElementsByName("isLocked");      //年度计划是否锁定标识
       var table = document.getElementById("vltable");
           if (typeof(table) == undefined || null == table) {
           	return ;
           }
       var objRows = table.getElementsByTagName("tr")
       for (var i = 1; i < objRows.length; i ++) {
           if( lockedFlagRow[i-1].value == "true" ) {
             objRows[i].className = "displayRed";
           }
       }
     }
     //锁定的记录不能被失效
     function validatePrompt() {
       var selector = document.getElementsByName("yearPlanIds");       //年度计划id
       var lockedFlag = document.getElementsByName("isLocked");      //年度计划是否锁定标识
       for (i = 0; i < selector.length; i++) {
         if (selector[i].checked) {
           if(lockedFlag[i].value == "true") {
               alert("${action.getText('locked.record.not.validate')}");
               return false;
           }     
         }
       }
       return true;
     }
   </script>
</@htmlPage>