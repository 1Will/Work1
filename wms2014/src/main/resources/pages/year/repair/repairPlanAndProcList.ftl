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
<#assign repairTitle = ''/>
<#assign repairList = ''/>
<#if planProcFlag?exists>
	<#if (planProcFlag=='PLAN')>
		<#assign repairTitle = "${action.getText('repairPlanSearch.title')}"/>
		<#assign repairList = "${action.getText('repairPlan.list')}"/>
	<#else>
		<#assign repairTitle = "${action.getText('repairProcSearch.title')}"/>
		<#assign repairList = "${action.getText('repairProc.list')}"/>
	</#if>
</#if>
<@htmlPage title="${repairTitle}">
     <@ww.form namespace="'/year/repair'" name="'listRepairPlanAndProc'" action="'searchRepairPlanOrProcs'" method="'post'">
	 <@ww.token name="searchPreRepairPlansToken"/>   
	 	 <#include "repairPlanAndProcSearcher.ftl"/>
	 	 <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
	 	 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	     <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
	 	  <@buttonBar>
	 		<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
	 		<#if planProcFlag?exists>
	        <#if (planProcFlag=='PLAN')>
	        <#if !(action.isReadOnly())>
	 		  <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/year/repair/editYearRepairPlanOrProc.html?planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}"/>  
            </#if>
            </#if>
            </#if>
          </@buttonBar>
	 		<@list title="${repairList}" 
        		includeParameters="planNo|readOnly|eamAuthentication|name|toolingDevFlag|planCreator.name|reportor.name|department.id|planCreatedTime_start|planCreatedTime_end|planProcFlag" 
        		fieldMap="like:planNo|name|reportor.name|toolingDevFlag|planCreator.name,date:planCreatedTime_start|planCreatedTime_end|reportDate_start|reportDate_end" >
        	    <#if planProcFlag?exists>
	            <#if (planProcFlag=='PLAN')>
	        		<@vlh.checkbox property="id" name="repairPlanOrProcIds">
		            	<@vlh.attribute name="width" value="30" />
		            </@vlh.checkbox>
		         </#if>
		         </#if>
	            <@vcolumn title="${action.getText('repairPlanOrProc.repairPlanNo')}" property="planNo" sortable="desc">
	                <a href="editYearRepairPlanOrProc.html?repairPlanOrProc.id=#{object.id}&planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.planNo}</a>
	                    <@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('repairPlanOrProc.repairPlanName')}" property="name" sortable="desc">
	                 <@alignLeft/>
                </@vcolumn>
                <@vcolumn title="${action.getText('repairPlanOrProc.department')}" property="department.name" sortable="desc">
        		     <@alignLeft/>
                </@vcolumn>
                <@vcolumn title="${action.getText('repairPlanOrProc.year')}" property="year" format="yyyy" sortable="desc">
	                 <@alignCenter/>
                </@vcolumn>
                <@vcolumn title="${action.getText('repairPlanOrProc.planCreator')}" property="planCreator.name" sortable="desc">
        		     <@alignLeft/>
                </@vcolumn>
        		<@vcolumn title="${action.getText('repairPlanOrProc.planCreatedTime')}" property="planCreatedTime" format="yyyy-MM-dd" sortable="desc">
        		     <@alignCenter/>
                </@vcolumn>
                <#if planProcFlag?exists>
                <#if (planProcFlag=='PROC')>
	              <@vcolumn title="${action.getText('repairPlanOrProc.reportor')}" property="reportor.name">
        		    <@alignLeft/>
                  </@vcolumn>
        		  <@vcolumn title="${action.getText('repairPlanOrProc.reportDate')}" property="reportDate" format="yyyy-MM-dd" sortable="desc">
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
		            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('yearRepairPlan')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return validateDelete(confirmDeletes(\"repairPlanOrProcIds\", \"${confirmMessage}\"), checkInvalidParms())'"/>
		                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		            </@vsubmit>
		            </#if>
		       </#if>
		       </#if>
		       <#if '${eamAuthentication?if_exists}' == 'Collect'>
		         <@vbutton name="printPdf"  class="button" value="${action.getText('AllRepairPlanListPrintpdf')}" onclick="open_AllRepairPlanPdf()"/>
	             <@vbutton name="printXls"  class="button" value="${action.getText('AllRepairPlanListPrintxls')}" onclick="open_AllRepairPlanXls()"/>
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
    		function open_repairPlanListPdf() {
				var url='${req.contextPath}/reports/repair/repairPlan.pdf?planNo='+document.forms[0].elements["planNo"].value+
				'&planName='+(document.forms[0].elements["planName"].value)+
				'&department.id='+ (document.forms[0].elements["department.id"].value);
	      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=no,width=screen.width,height=screen.height,left=0,top=0");
	      	}
	      	function open_AllRepairPlanPdf(){
	        var year = document.forms[0].elements["year"].value;
            var deptId=document.forms[0].elements["department.id"].value;
            if(year==''){
               alert("${action.getText('year.not.null')}");
               return false;
           }
           	<#if (planProcFlag=='PLAN')>
                var url='${req.contextPath}/reports/device/listAllRepairPlan.pdf?year='+year+'&department.id='+ deptId+'&flag=PLAN';
             <#else>
                var url='${req.contextPath}/reports/device/listAllRepairProc.pdf?year='+year+'&department.id='+ deptId+'&flag=PROC';
            </#if>
            url = encodeURI(url);
          window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
        }
        function open_AllRepairPlanXls(){
	        var year = document.forms[0].elements["year"].value;
            var deptId=document.forms[0].elements["department.id"].value;
            if(year==''){
               alert("${action.getText('year.not.null')}");
               return false;
           }
           	<#if (planProcFlag=='PLAN')>
                var url='${req.contextPath}/reports/device/listAllRepairPlan.xls?year='+year+'&department.id='+ deptId+'&flag=PLAN';
             <#else>
                var url='${req.contextPath}/reports/device/listAllRepairProc.xls?year='+year+'&department.id='+ deptId+'&flag=PROC';
            </#if>
            url = encodeURI(url);
          window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
        }
	    </script>
	 </@ww.form>
</@htmlPage>