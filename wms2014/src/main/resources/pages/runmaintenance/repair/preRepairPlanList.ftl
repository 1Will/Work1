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
<#assign preRepairTitle = ''/>
<#assign preRepairList = ''/>
<#if planProcFlag?exists>
	<#if (planProcFlag=='PLAN')>
		<#assign preRepairTitle = "${action.getText('preRepairPlanSearch.title')}"/>
		<#assign preRepairList = "${action.getText('preRepairPlan.list')}"/>
	<#else>
		<#assign preRepairTitle = "${action.getText('preRepairProcSearch.title')}"/>
		<#assign preRepairList = "${action.getText('preRepairProc.list')}"/>
	</#if>
</#if>
<@htmlPage title="${preRepairTitle}">
     <@ww.form namespace="'/runmaintenance/repair/plan'" name="'ListPreRepairPlan'" action="'searchPreRepairPlans'" method="'post'">
	 <@ww.token name="searchPreRepairPlansToken"/>   
	 	 <#include "preRepairPlanSearcher.ftl"/>
	 	 <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
	 	 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	     <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
	 	  <@buttonBar>
	 		<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
	 		<#if planProcFlag?exists>
	        <#if (planProcFlag=='PLAN')>
	        <#if !(action.isReadOnly())>
	 		  <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/repair/plan/editPreRepairPlan.html?planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>  
           </#if>
            </#if>
            </#if>
          </@buttonBar>
	 		<@list title="${preRepairList}" 
        		includeParameters="planNo|planName|readOnly|eamAuthentication|toolingDevFlag|department.id|planCreatedTime_start|planCreatedTime_end|beginDateTime_start|beginDateTime_end|planProcFlag|planCreator.name|reportor.name" 
        		fieldMap="like:planNo|planName|reportor.name|toolingDevFlag|planCreator.name,date:planCreatedTime_start|planCreatedTime_end|beginDateTime_start|beginDateTime_end|reportDate_start|reportDate_end" >
        	    <#if planProcFlag?exists>
	            <#if (planProcFlag=='PLAN')>
	        		<@vlh.checkbox property="id" name="preRepairPlanIds">
		            	<@vlh.attribute name="width" value="30" />
		            </@vlh.checkbox>
		         </#if>
		         </#if>
	            <@vcolumn title="${action.getText('preRepairPlanNo')}" property="planNo" sortable="desc">
	                <a href="editPreRepairPlan.html?preRepairPlan.id=#{object.id}&planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.planNo}</a>
	                    <@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('preRepairPlanName')}" property="name" sortable="desc">
	                 <@alignLeft/>
                </@vcolumn>
        		<@vcolumn title="${action.getText('department')}" property="department.name" sortable="desc">
        		     <@alignLeft/>
                </@vcolumn>
                <@vcolumn title="${action.getText('beginDateTime')}" property="beginDate" format="yyyy-MM-dd" sortable="desc">
        		     <@alignCenter/>
                </@vcolumn>
                <@vcolumn title="${action.getText('planCreator')}" property="planCreator.name" sortable="desc">
        		     <@alignLeft/>
                </@vcolumn>
        		<@vcolumn title="${action.getText('planCreatedTime')}" property="planCreatedTime" format="yyyy-MM-dd" sortable="desc">
        		     <@alignCenter/>
                </@vcolumn>
                <#if planProcFlag?exists>
	            <#if (planProcFlag=='PROC')>
	              <@vcolumn title="${action.getText('preRepairProc.reportor')}" property="reportor.name" sortable="desc">
        		    <@alignLeft/>
                  </@vcolumn>
        		  <@vcolumn title="${action.getText('preRepairProc.reportDate')}" property="reportDate" format="yyyy-MM-dd" sortable="desc">
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
		            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('preRepairPlan')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return validateDelete(confirmDeletes(\"preRepairPlanIds\", \"${confirmMessage}\"), checkInvalidParms())'"/>
		                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		            </@vsubmit>
		            </#if>
		       </#if>
		       </#if>
		       <#if '${eamAuthentication?if_exists}' == 'Collect'>
		       	 <@vbutton name="printPdf"  class="button" value="${action.getText('preRepairPlanListPrintpdf')}" onclick="open_preRepairPlanPdf()"/>
	             <@vbutton name="printXls"  class="button" value="${action.getText('preRepairPlanListPrintxls')}" onclick="open_preRepairPlanXls()"/>
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
    		function open_preRepairPlanPdf(){
	        var beginDateTime_start = document.forms[0].elements["beginDateTime_start"].value;
	        var beginDateTime_end = document.forms[0].elements["beginDateTime_end"].value;
            var deptId=document.forms[0].elements["department.id"].value;
            if(beginDateTime_start==''){
               alert("${action.getText('beginDateTime_start.not.null')}");
               return false;
           }
           beginDateFormatMsg="${action.getText('select.right.wash.planBeginDate')}";
		     beginDateOrderMsg="${action.getText('beginDate.order.error')}";
	         if(!queryDate("beginDateTime_start","beginDateTime_end",
	         beginDateFormatMsg,null)){
	    	 return false;
	        }
           <#if (toolingDevFlag=='TOOLING')>
           	<#if (planProcFlag=='PLAN')>
                var url='${req.contextPath}/reports/tooling/toolinglistAllRepairPlan.pdf?beginDateTime_start='+beginDateTime_start+'&beginDateTime_end='+beginDateTime_end+'&department.id='+ deptId+'&flag=PLAN'+'&toolingDevFlag=${toolingDevFlag?if_exists}';
             <#else>
                var url='${req.contextPath}/reports/tooling/toolinglistAllRepairProc.pdf?beginDateTime_start='+beginDateTime_start+'&beginDateTime_end='+beginDateTime_end+'&department.id='+ deptId+'&flag=PROC'+'&toolingDevFlag=${toolingDevFlag?if_exists}';
            </#if>
           <#else>
            <#if (planProcFlag=='PLAN')>
                var url='${req.contextPath}/reports/preRepair/listAllpreRepairPlan.pdf?beginDateTime_start='+beginDateTime_start+'&beginDateTime_end='+beginDateTime_end+'&department.id='+ deptId+'&flag=PLAN'+'&toolingDevFlag=${toolingDevFlag?if_exists}';
             <#else>
                var url='${req.contextPath}/reports/preRepair/listAllpreRepairProc.pdf?beginDateTime_start='+beginDateTime_start+'&beginDateTime_end='+beginDateTime_end+'&department.id='+ deptId+'&flag=PLAN'+'&toolingDevFlag=${toolingDevFlag?if_exists}';
            </#if> 
           </#if> 
            url = encodeURI(url);
          window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
        }
        function  open_preRepairPlanXls(){
            var beginDateTime_start = document.forms[0].elements["beginDateTime_start"].value;
	        var beginDateTime_end = document.forms[0].elements["beginDateTime_end"].value;
            var deptId=document.forms[0].elements["department.id"].value;
            if(beginDateTime_start==''){
              alert("${action.getText('beginDateTime_start.not.null')}");
              return false;
          }
          beginDateFormatMsg="${action.getText('select.right.wash.planBeginDate')}";
		     beginDateOrderMsg="${action.getText('beginDate.order.error')}";
	         if(!queryDate("beginDateTime_start","beginDateTime_end",
	         beginDateFormatMsg,null)){
	    	 return false;
	     }
       <#if (toolingDevFlag=='TOOLING')>
          <#if (planProcFlag=='PLAN')>
                var url='${req.contextPath}/reports/tooling/toolinglistAllRepairPlan.xls?beginDateTime_start='+beginDateTime_start+'&beginDateTime_end='+beginDateTime_end+'&department.id='+ deptId+'&flag=PLAN'+'&toolingDevFlag=${toolingDevFlag?if_exists}';
            <#else>
                var url='${req.contextPath}/reports/tooling/toolinglistAllRepairProc.xls?beginDateTime_start='+beginDateTime_start+'&beginDateTime_end='+beginDateTime_end+'&department.id='+ deptId+'&flag=PLAN'+'&toolingDevFlag=${toolingDevFlag?if_exists}';
          </#if>
         <#else>
            <#if (planProcFlag=='PLAN')>
                var url='${req.contextPath}/reports/preRepair/listAllpreRepairPlan.xls?beginDateTime_start='+beginDateTime_start+'&beginDateTime_end='+beginDateTime_end+'&department.id='+ deptId+'&flag=PLAN'+'&toolingDevFlag=${toolingDevFlag?if_exists}';
            <#else>
                var url='${req.contextPath}/reports/preRepair/listAllpreRepairProc.xls?beginDateTime_start='+beginDateTime_start+'&beginDateTime_end='+beginDateTime_end+'&department.id='+ deptId+'&flag=PLAN'+'&toolingDevFlag=${toolingDevFlag?if_exists}';
            </#if>
       </#if>
          url = encodeURI(url);
          window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
       }
	    </script>
	 </@ww.form>
</@htmlPage>