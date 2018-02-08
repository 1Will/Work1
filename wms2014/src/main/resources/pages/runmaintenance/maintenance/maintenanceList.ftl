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
<#assign maintenanceTitle = ''/>
<#assign maintenanceList = ''/>
<#if planProcFlag?exists>
	<#if (planProcFlag=='PLAN')>
		<#assign maintenanceTitle = "${action.getText('maintenancePlanSearch.title')}"/>
		<#assign maintenanceList = "${action.getText('maintenancePlan.list')}"/>
	<#else>
		<#assign maintenanceTitle = "${action.getText('maintenanceProcSearch.title')}"/>
		<#assign maintenanceList = "${action.getText('maintenanceProc.list')}"/>
	</#if>
</#if>
<@htmlPage title="${maintenanceTitle}">
	<@ww.form namespace="'/runmaintenance/maintenance'" name="'ListMaintenance'" action="'searchMaintenance'" method="'post'">
	 <@ww.token name="searchMaintenanceToken"/>   
	 	 <#include "maintenanceSearcher.ftl"/>
	 	 <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
	 	 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	     <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
	 	 <@buttonBar>
	 		<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
	 		<#--<#if planProcFlag?exists>
		        <#if (planProcFlag=='PLAN')>
		 		  <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/maintenance/editMaintenance.html?planProcFlag=${planProcFlag?if_exists}"/>  
	            </#if>
            </#if>-->
          </@buttonBar>
          <@list title="${maintenanceList}" 
        		includeParameters="planNo|readOnly|reporter|eamAuthentication|planName|department.id|writer|makeDate_start|makeDate_end|planProcFlag|scheduleDate_start|scheduleDate_end|reportDate_start|reportDate_end|maintenanceType_one|maintenanceType_two|onlyValid|onlyInvalid" 
        		fieldMap="like:planNo|reporter|planName|writer|scheduleDate,date:makeDate_start|makeDate_end|scheduleDate_start|scheduleDate_end|reportDate_start|reportDate_end|maintenanceType_one|maintenanceType_two" >
        	    <#if planProcFlag?exists>
	            	<#if (planProcFlag=='PLAN')>
		        		<@vlh.checkbox property="id" name="maintenanceIds">
			            	<@vlh.attribute name="width" value="30" />
			            </@vlh.checkbox>
		         	</#if>
		        </#if>
		        <#if object.disabled>
		        <@vcolumn title="${action.getText('maintenance.planNo')}" property="planNo" sortable="desc">
	                ${object.planNo}
	                 <@alignLeft/>
	            </@vcolumn>
	            <#else>
	              <@vcolumn title="${action.getText('maintenance.planNo')}" property="planNo" sortable="desc">
	                <a href="editMaintenance.html?maintenance.id=#{object.id}&planProcFlag=${planProcFlag?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.planNo}</a>
	                 <@alignLeft/>
	            </@vcolumn>
		        </#if>
	            <@vcolumn title="${action.getText('maintenance.planName')}" property="planName" sortable="desc">
	                 <@alignLeft/>
                </@vcolumn>
                <@vcolumn title="${action.getText('department')}" property="department.name" sortable="desc">
        		     <@alignLeft/>
                </@vcolumn>
                <@vcolumn title="${action.getText('maintenance.scheduleDate')}" property="month" sortable="desc">
        		     <@alignCenter/>
                </@vcolumn>
                <@vcolumn title="${action.getText('maintenance.writer')}" property="writer.name">
        		     <@alignLeft/>
                </@vcolumn>
        		<@vcolumn title="${action.getText('maintenance.makeDate')}" property="makeDate" format="yyyy-MM-dd" sortable="desc">
        		     <@alignCenter/>
                </@vcolumn>
                <#if planProcFlag?exists>
	            	<#if (planProcFlag=='PROC')>
	            		<@vcolumn title="${action.getText('maintenance.reporter')}" property="reporter.name">
		        		     <@alignLeft/>
		                </@vcolumn>
		        		<@vcolumn title="${action.getText('maintenance.reportDate')}" property="reportDate" format="yyyy-MM-dd" sortable="desc">
		        		     <@alignCenter/>
		                </@vcolumn>
	            	</#if>
		        </#if>
                </@list>
             <#if !first>
		            <@buttonBar>
		            <#if planProcFlag?exists>
			    		<#if (planProcFlag=='PLAN')>
			    		<#if !(action.isReadOnly())>
			        		<@eam2008_disabledOrEnabled_button message="${action.getText('deviceMaintenance')}" boxName="maintenanceIds" jsFunctionName="checkInvalidParms()"/>
			        	</#if>
			        	 </#if>
		        	</#if>
		        	<#if '${eamAuthentication?if_exists}' == 'Collect'>
			           <@vbutton name="printPdf"  class="button" value="${action.getText('pdfMaintenanceReportPrint')}" onclick="open_MaintenanceReportPdf()"/>
	                   <@vbutton name="printXls"  class="button" value="${action.getText('xlsMaintenanceReportPrint')}" onclick="open_MaintenanceReportXls()"/>
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
         function open_MaintenanceReportPdf(){
	        var month=document.forms[0].elements["month"].value;
            var deptId=document.forms[0].elements["department.id"].value;
            if(month==''){
               alert("${action.getText('month.not.null')}");
               return false;
           }
            <#if (planProcFlag=='PLAN')>
                var url='${req.contextPath}/reports/device/listAllMaintenance.pdf?month='+month+'&department.id='+ deptId+'&flag=PLAN';
            
             <#else>
                var url='${req.contextPath}/reports/device/listAllMaintenanceProc.pdf?month='+month+'&department.id='+ deptId+'&flag=PROC';
            </#if>       
            url = encodeURI(url);
            window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
        }
        function  open_MaintenanceReportXls(){
            var month=document.forms[0].elements["month"].value;
            var deptId=document.forms[0].elements["department.id"].value;
            if(month==''){
              alert("${action.getText('month.not.null')}");
              return false;
          }
          <#if (planProcFlag=='PLAN')>
                var url='${req.contextPath}/reports/device/listAllMaintenance.xls?month='+month+'&department.id='+ deptId+'&flag=PLAN';
            <#else>
                var url='${req.contextPath}/reports/device/listAllMaintenanceProc.xls?month='+month+'&department.id='+ deptId+'&flag=PROC';
          </#if>
          url = encodeURI(url);
          window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
       }
	 </script>
</@htmlPage>