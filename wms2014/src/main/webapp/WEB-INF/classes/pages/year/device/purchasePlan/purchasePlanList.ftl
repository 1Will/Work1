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
<@htmlPage title="${action.getText('purchasePlanSeacher.title')}">
  <@ww.form namespace="'/year/device/purchasePlan'" name="'listPurchasePlan'" action="'searchPurchasePlans'" method="'post'">
    <@ww.token name="searchPurchasePlansToken"/>   
	<#include "purchasePlanSearcher.ftl"/>
    <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
 	<@buttonBar>
      <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
      <#if !(action.isReadOnly())>
      <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/year/device/purchasePlan/editPurchasePlan.html?eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>  
     </#if>
    </@buttonBar>
 	<@list title="${action.getText('purchasePlan.list')}" 
           includeParameters="planNo|readOnly|eamAuthentication|name|planCreator.name|department.id|planCreatedDate_start|planCreatedDate_end|onlyValid|onlyInvalid" 
           fieldMap="like:planCreator.name|planNo|name|,date:planCreatedDate_start|planCreatedDate_end" >
	  <@vlh.checkbox property="id" name="purchasePlanIds">
        <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <@vcolumn title="${action.getText('purchasePlan.planNo')}" property="planNo" sortable="desc">
        <#if !(action.isOnlyInvalid())>
          <a href="editPurchasePlan.html?purchasePlan.id=#{object.id}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.planNo}</a>
        </#if>
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('purchasePlan.name')}" property="name" sortable="desc">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('department')}" property="department.name" sortable="desc">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('purchasePlan.year')}" property="year" format="yyyy" sortable="desc">
        <@alignCenter/>
      </@vcolumn>
      <@vcolumn title="${action.getText('purchasePlan.planCreator')}" property="planCreator.name" sortable="desc">
	    <@alignLeft/>
      </@vcolumn>
	  <@vcolumn title="${action.getText('purchasePlan.planCreatedDate')}" property="planCreatedDate" format="yyyy-MM-dd" sortable="desc">
	    <@alignCenter/>
      </@vcolumn>
      <@vcolumn title="${action.getText('purchasePlan.planAllFee')}" property="planAllFee" sortable="desc">
	    <@alignRight/>
      </@vcolumn>
    </@list>
    <#if !first>
      <@buttonBar>
      <#if !(action.isReadOnly())>
	    <@eam2008_disabledOrEnabled_button message="${action.getText('purchasePlan')}" boxName="purchasePlanIds" jsFunctionName="checkInvalidParms()"/>
	    </#if>
	    <#if '${eamAuthentication?if_exists}' == 'Collect'>
	      <@vbutton name="printPdf"  class="button" value="${action.getText('pdfPurchasePlanPrint')}" onclick="open_PurchasePlanPdf()"/>
	      <@vbutton name="printXls"  class="button" value="${action.getText('xlsPurchasePlanPrint')}" onclick="open_PurchasePlanXls()"/>
	    </#if>
	  </@buttonBar>
    </#if>
  </@ww.form>
  <script language="javascript">
   window.onload = function () {
     <#if !first && valueListNoRecords>
       <#if '${eamAuthentication?if_exists}' == 'Collect'>
         document.forms[0].elements["printPdf"].disabled="true";
         document.forms[0].elements["printXls"].disabled="true";
      </#if>
    </#if>
   }
  function open_PurchasePlanPdf(){
      var year=document.forms[0].elements["year"].value;
      var deptId=document.forms[0].elements["department.id"].value;
      var url='${req.contextPath}/reports/device/listAllpurchasePlan.pdf?year='+year+'&department.id='+ deptId;
        if(year==''){
         alert("${action.getText('year.not.null')}");
         return false;
      }
      url = encodeURI(url);
      window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
     }
     function  open_PurchasePlanXls(){
      var year=document.forms[0].elements["year"].value;
      var deptId=document.forms[0].elements["department.id"].value;
      var url='${req.contextPath}/reports/device/listAllpurchasePlan.xls?year='+year+'&department.id='+ deptId;
      if(year==''){
         alert("${action.getText('year.not.null')}");
         return false;
      }
      url = encodeURI(url);
      window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
     }
  </script>
</@htmlPage>