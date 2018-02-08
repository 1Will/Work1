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
<#assign calibrationTitle = ''/>
<#assign calibrationList = ''/>
<#if planProcFlag?exists>
	<#if (planProcFlag=='PLAN')>
		<#assign calibrationTitle = "${action.getText('calibrationPlanSearch.title')}"/>
		<#assign calibrationList = "${action.getText('calibrationPlan.list')}"/>
	<#else>
		<#assign calibrationTitle = "${action.getText('calibrationProcSearch.title')}"/>
		<#assign calibrationList = "${action.getText('calibrationProc.list')}"/>
	</#if>
</#if>
<@htmlPage title="${calibrationTitle}">
     <@ww.form namespace="'/runmaintenance/calibration'" name="'ListCalibration'" action="'searchCalibration'" method="'post'">
	 <@ww.token name="searchCalibrationToken"/>   
	 	 <#include "calibrationSearcher.ftl"/>
	 	 <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
	 	  <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	      <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
	 	  <@buttonBar>
	 		<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
	 		<#if planProcFlag?exists>
	        <#if (planProcFlag=='PLAN')>
	          <#if !(action.isReadOnly())>
	 		    <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/calibration/editCalibration.html?planProcFlag=${planProcFlag?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}"/>  
              </#if>
            </#if>
            </#if>
          </@buttonBar>
	 		<@list title="${calibrationList}" 
        		includeParameters="readOnly|eamAuthentication|planNo|planName|department.id|writer|makeDate_start|makeDate_end|planProcFlag|reporter|reportDate_start|reportDate_end|month_start|month_end|onlyValid|onlyInvalid" 
        		fieldMap="like:planNo|planName|writer|makeDate,date:makeDate_start|makeDate_end" >
        	    <#if planProcFlag?exists>
		            <#if (planProcFlag=='PLAN')>
		        		<@vlh.checkbox property="id" name="calibrationIds">
			            	<@vlh.attribute name="width" value="30" />
			            </@vlh.checkbox>
			         </#if>
		         </#if>
	            <@vcolumn title="${action.getText('calibration.calibrationNo')}" property="planNo" sortable="desc">
	                <a href="editCalibration.html?calibration.id=#{object.id}&planProcFlag=${planProcFlag?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.planNo}</a>
	                 <@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('calibration.calibrationName')}" property="planName" sortable="desc">
	                 <@alignLeft/>
                </@vcolumn>
        		<@vcolumn title="${action.getText('department')}" property="department.name" sortable="desc">
        		     <@alignLeft/>
                </@vcolumn>
                <@vcolumn title="${action.getText('calibration.scheduleDate')}" property="month" sortable="desc">
        		     <@alignCenter/>
                </@vcolumn>
                <@vcolumn title="${action.getText('calibration.writer')}" property="writer.name" sortable="desc">
        		     <@alignLeft/>
                </@vcolumn>
        		<@vcolumn title="${action.getText('calibration.makeDate')}" property="makeDate" format="yyyy-MM-dd" sortable="desc">
        		     <@alignCenter/>
                </@vcolumn>
                <#if planProcFlag?exists>
	         		<#if (planProcFlag=='PROC')>
	         			<@vcolumn title="${action.getText('calibration.reporter')}" property="reporter.name">
		        		     <@alignLeft/>
		                </@vcolumn>
		        		<@vcolumn title="${action.getText('calibration.reportDate')}" property="reportDate" format="yyyy-MM-dd" sortable="desc">
		        		     <@alignCenter/>
		                </@vcolumn>
	         		</#if>
		       </#if>
                <@vcolumn title="${action.getText('calibration.verifyPeople')}" property="verifyPeople.name">
        		     <@alignLeft/>
                </@vcolumn>
                <@vcolumn title="${action.getText('calibration.approver')}" property="approver.name" >
        		     <@alignLeft/>
                </@vcolumn>
        	</@list>
             
             <#if !first>
		        <@buttonBar>
		        <#if !(action.isReadOnly())>
		            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('calibrationPlan')}?" />
		            <#if planProcFlag?exists>
	         		<#if (planProcFlag=='PLAN')>
						<@eam2008_disabledOrEnabled_button message="${action.getText('calibration')}" boxName="calibrationIds" jsFunctionName="checkInvalidParms()"/>
			        </#if>
			        </#if>
		        </#if>
		            <#if '${eamAuthentication?if_exists}' == 'Collect'>
		              <@vbutton name="printPdf"  class="button" value="${action.getText('pdfCalibrationReportPrint')}" onclick="open_CalibrationReportPdf()"/>
	                  <@vbutton name="printXls"  class="button" value="${action.getText('xlsCalibrationReportPrint')}" onclick="open_CalibrationReportXls()"/>
		            </#if>
		        </@buttonBar>
		       </#if>
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
         function open_CalibrationReportPdf(){
	        var month=document.forms[0].elements["month"].value;
            var deptId=document.forms[0].elements["department.id"].value;
            if(month==''){
               alert("${action.getText('month.not.null')}");
               return false;
           }
            <#if (planProcFlag=='PLAN')>
                var url='${req.contextPath}/reports/calibration/listcalibrationDetailReportList.pdf?month='+month+'&department.id='+ deptId+'&flag=PLAN';
             <#else>
                var url='${req.contextPath}/reports/calibration/listAllCalibrationProc.pdf?month='+month+'&department.id='+ deptId+'&flag=PROC';
            </#if>   
            url = encodeURI(url);
        
          window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
        }
        function  open_CalibrationReportXls(){
            var month=document.forms[0].elements["month"].value;
            var deptId=document.forms[0].elements["department.id"].value;
            if(month==''){
              alert("${action.getText('month.not.null')}");
              return false;
          }
          <#if (planProcFlag=='PLAN')>
                var url='${req.contextPath}/reports/calibration/listcalibrationDetailReportList.xls?month='+month+'&department.id='+ deptId+'&flag=PLAN';
            <#else>
                var url='${req.contextPath}/reports/calibration/listAllCalibrationProc.xls?month='+month+'&department.id='+ deptId+'&flag=PROC';
          </#if>
          url = encodeURI(url);
          window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
       }
	    </script>
	 </@ww.form>
</@htmlPage>