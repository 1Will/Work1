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
<#assign lubricationTitle=''/>
<#assign lubricationListTitle=''/>
<#if planProcFlag?exists>
  <#if planProcFlag == 'PLAN'>
    <#assign lubricationTitle = "${action.getText('lubricationPlanSearch.title')}"/>
    <#assign lubricationListTitle = "${action.getText('lubricationPlan.list')}"/>
  <#else>
    <#assign lubricationTitle = "${action.getText('lubricationProcSearch.title')}"/>
    <#assign lubricationListTitle = "${action.getText('lubricationProc.list')}"/>
  </#if>
</#if>
<@htmlPage title="${lubricationTitle}">
  <@ww.form name="'lubricationPlan'" action="'searchLubricationPlan'" method="'post'">
	    <@ww.token name="searchLubricationPlanToken"/>
	 	 <#include "lubricationSearcher.ftl" />
	 	 <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
	 	 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	     <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
         <@buttonBar>        
        	<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'" /> 
        	
        </@buttonBar>
         <@list title="${lubricationListTitle}" includeParameters="id|readOnly|eamAuthentication|planNo|name|planMonth|department.id|planProcFlag|planCreator.name|reportor.name" 
         	fieldMap="like:planNo|name|reportor.name|planCreator.name|planMonth,date:planCreatedTime_start|planCreatedTime_end|reportDate_start|reportDate_end" >
            <#if planProcFlag?exists>
              <#if planProcFlag == 'PLAN'>
                <@vlh.checkbox property="id" name="lubricationPlanIds">
            	  <@vlh.attribute name="width" value="30" />
               </@vlh.checkbox> 
              </#if>
            </#if>  
	        <@vcolumn title="${action.getText('lubricationPlan.planNo')}" property="planNo" sortable="desc">
	          <a href="editLubricationPlan.html?lubrication.id=#{object.id}&planProcFlag=${planProcFlag?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.planNo}</a>
              <@alignLeft/>	
	        </@vcolumn>
	        <@vcolumn title="${action.getText('lubricationPlan.name')}" property="name" sortable="desc">
              <@alignLeft/>	
	        </@vcolumn>
	        <@vcolumn title="${action.getText('department')}" property="department.name" sortable="desc">
              <@alignLeft/>	
	        </@vcolumn>
	        <@vcolumn title="${action.getText('month')}" property="month" sortable="desc">
        		     <@alignCenter/>
                </@vcolumn>
	        <@vcolumn title="${action.getText('planCreator')}" property="planCreator.name" sortable="desc">
              <@alignLeft/>	
	        </@vcolumn>
	        <@vcolumn title="${action.getText('planCreatedTime')}" property="planCreatedTime" format="yyyy-MM-dd" sortable="desc">
              <@alignCenter/>	
	        </@vcolumn>
	        <#if planProcFlag?exists>
              <#if planProcFlag == 'PROC'>
              	<@vcolumn title="${action.getText('reporter')}" property="reportor.name" sortable="desc">
                  <@alignLeft/>	
	            </@vcolumn>
	            <@vcolumn title="${action.getText('reportCreatedTime')}" property="reportDate" format="yyyy-MM-dd" sortable="desc">
                  <@alignCenter/>	
	            </@vcolumn>
	          </#if>
	        </#if>
        </@list>
        <#if !first>
        <@buttonBar>
          <#if planProcFlag?exists>
            <#if planProcFlag == 'PLAN'>
            <#if !(action.isReadOnly())>
              <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('lubricationPlan')}?" />
              <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return validateDelete(confirmDeletes(\"lubricationPlanIds\", \"${confirmMessage}\"),checkInvalidParms());'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
              </@vsubmit>
              </#if>
              
            </#if>
          </#if>
          <#if '${eamAuthentication?if_exists}' == 'Collect'>
              <@vbutton name="printPdf"  class="button" value="${action.getText('pdfLubricationPlanPrint')}" onclick="open_LubricationPlanPdf()"/>
	          <@vbutton name="printXls"  class="button" value="${action.getText('xlsLubricationPlanPrint')}" onclick="open_LubricationPlanXls()"/>
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
	    function validateDelete(delFun, checkFun) {
		  if (delFun) {
		    checkFun;
		    return true;
		  }
          return false;
        }
         function open_LubricationPlanPdf(){
	        var month = document.forms[0].elements["month"].value;
            var deptId=document.forms[0].elements["department.id"].value;
            if(month==''){
               alert("${action.getText('month.not.null')}");
               return false;
           }
            <#if (planProcFlag=='PLAN')>
                var url='${req.contextPath}/reports/lubrication/listAllLubricationPlan.pdf?month='+month+'&department.id='+ deptId+'&flag=PLAN';
             <#else>
                var url='${req.contextPath}/reports/lubrication/listAllLubricationProc.pdf?month='+month+'&department.id='+ deptId+'&flag=PROC';
            </#if>   
            url = encodeURI(url);
          window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
        }
        function  open_LubricationPlanXls(){
            var month=document.forms[0].elements["month"].value;
            var deptId=document.forms[0].elements["department.id"].value;
            if(month==''){
              alert("${action.getText('month.not.null')}");
              return false;
          }
          <#if (planProcFlag=='PLAN')>
                var url='${req.contextPath}/reports/lubrication/listAllLubricationPlan.xls?month='+month+'&department.id='+ deptId+'&flag=PLAN';
            <#else>
                var url='${req.contextPath}/reports/lubrication/listAllLubricationProc.xls?month='+month+'&department.id='+ deptId+'&flag=PROC';
          </#if>
          url = encodeURI(url);
          window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
       }
      </script>
</@htmlPage>