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
<#assign washTitle=''/>
<#assign washListTitle=''/>
<#if planProcFlag?exists>
  <#if planProcFlag == 'PLAN'>
    <#assign washTitle = "${action.getText('washPlanSearcher.title')}"/>
    <#assign washListTitle = "${action.getText('washPlan.list')}"/>
  <#else>
    <#assign washTitle = "${action.getText('washProcSearcher.title')}"/>
    <#assign washListTitle = "${action.getText('washProc.list')}"/>
  </#if>
</#if>
<@htmlPage title="${washTitle}">
     <@ww.form namespace="'/runmaintenance/wash'" name="'wash'" action="'searchWashs'" method="'post'">
	 <@ww.token name="searchWashsToken"/>   
	 	 <#include "washSearcher.ftl"/>
	 	  <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
	 	  <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	      <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
	 	  <@buttonBar>
	 		<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
	 		<#if planProcFlag?exists>
              <#if planProcFlag == 'PLAN'>
                <#if !(action.isReadOnly())>
	 		    <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/wash/editWash.html?planProcFlag=${planProcFlag?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}"/>  
                </#if>
              </#if>
            </#if>
          </@buttonBar>
	 		<@list title="${washListTitle}" 
        		includeParameters="planNo|readOnly|eamAuthentication|planName|planCreator|department.id|palnBeginDate_start|palnBeginDate_end|planCreateDate_start|planCreateDate_end||reportDate_start|reportDate_end|planProcFlag" 
        		fieldMap="like:planNo|planName|planCreator,date:palnBeginDate_start|palnBeginDate_end|planCreateDate_start|planCreateDate_end|reportDate_start|reportDate_end" >
        		<#if planProcFlag?exists>
                  <#if planProcFlag == 'PLAN'>
        		    <@vlh.checkbox property="id" name="washPlanIds">
	            	  <@vlh.attribute name="width" value="30" />
	                </@vlh.checkbox>
	              </#if>
	            </#if>
	            <@vcolumn title="${action.getText('wash.planNo')}" property="planNo" sortable="desc">
	                <a href="editWash.html?wash.id=#{object.id}&planProcFlag=${planProcFlag?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.planNo}</a>
	                    <@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('wash.palnName')}" property="name" sortable="desc">
	                 <@alignLeft/>
                </@vcolumn>
        		<@vcolumn title="${action.getText('department')}" property="department.name" sortable="desc">
        		     <@alignLeft/>
                </@vcolumn>
                <@vcolumn title="${action.getText('wash.planBeginDate')}" property="planBeginDate" format="yyyy-MM-dd" sortable="desc">
        		     <@alignCenter/>
                </@vcolumn>
                <@vcolumn title="${action.getText('wash.planCreator')}" property="planCreator.name" sortable="desc">
        		     <@alignLeft/>
                </@vcolumn>
        		<@vcolumn title="${action.getText('wash.planCreateDate')}" property="planCreatedTime" format="yyyy-MM-dd" sortable="desc">
        		     <@alignCenter/>
                </@vcolumn>
                <#if planProcFlag?exists>
                  <#if planProcFlag == 'PROC'>
                    <@vcolumn title="${action.getText('wash.reportor')}" property="reportor.name" >
        		      <@alignLeft/>
                    </@vcolumn>
                    <@vcolumn title="${action.getText('wash.reportDate')}" property="reportDate" format="yyyy-MM-dd" sortable="desc">
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
		            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('washPlan')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return validateDelete(confirmDeletes(\"washPlanIds\", \"${confirmMessage}\"), checkInvalidParms())'"/>
		                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		            </@vsubmit>
		            </#if>
	            </#if>
	          </#if>
	          <#if '${eamAuthentication?if_exists}' == 'Collect'>
	            <@vbutton name="printPdf"  class="button" value="${action.getText('pdfWashReportPrint')}" onclick="open_WashReportPdf()"/>
	            <@vbutton name="printXls"  class="button" value="${action.getText('xlsWashReportPrint')}" onclick="open_WashReportXls()"/>
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
	      	function open_WashReportPdf(){
	         var palnBeginDate_start=document.forms[0].elements["palnBeginDate_start"].value;
	         var palnBeginDate_end=document.forms[0].elements["palnBeginDate_end"].value;
             var deptId=document.forms[0].elements["department.id"].value;
             if(palnBeginDate_start==''){
               alert("${action.getText('planBeginDate.not.null')}");
               return false;
           }
             palnBeginDateFormatMsg="${action.getText('select.right.wash.planBeginDate')}";
		     palnBeginDateOrderMsg="${action.getText('planBeginDate.order.error')}";
	         if(!queryDate("palnBeginDate_start","palnBeginDate_end",
	         palnBeginDateFormatMsg,null)){
	    	 return false;
	        }
            
            <#if (planProcFlag=='PLAN')>
                var url='${req.contextPath}/reports/tooling/listAllWashPlan.pdf?palnBeginDate_start='+palnBeginDate_start+'&palnBeginDate_end='+palnBeginDate_end+'&department.id='+ deptId+'&flag=PLAN';
             <#else>
                var url='${req.contextPath}/reports/tooling/listAllWashPlanProc.pdf?palnBeginDate_start='+palnBeginDate_start+'&palnBeginDate_end='+palnBeginDate_end+'&department.id='+ deptId+'&flag=PLAN';
            </#if>   
            url = encodeURI(url);
        
          window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
        }
        function  open_WashReportXls(){
            var palnBeginDate_start=document.forms[0].elements["palnBeginDate_start"].value;
	        var palnBeginDate_end=document.forms[0].elements["palnBeginDate_end"].value;
            var deptId=document.forms[0].elements["department.id"].value;
            if(palnBeginDate_start==''){
              alert("${action.getText('planBeginDate.not.null')}");
              return false;
          }
             palnBeginDateFormatMsg="${action.getText('select.right.wash.planBeginDate')}";
		     palnBeginDateOrderMsg="${action.getText('planBeginDate.order.error')}";
	         if(!queryDate("palnBeginDate_start","palnBeginDate_end",
	         palnBeginDateFormatMsg,null)){
	    	 return false;
	        }
          
          <#if (planProcFlag=='PLAN')>
                var url='${req.contextPath}/reports/tooling/listAllWashPlan.xls?palnBeginDate_start='+palnBeginDate_start+'&palnBeginDate_end='+palnBeginDate_end+'&department.id='+ deptId+'&flag=PLAN';
            <#else>
                var url='${req.contextPath}/reports/tooling/listAllWashPlanProc.xls?palnBeginDate_start='+palnBeginDate_start+'&palnBeginDate_end='+palnBeginDate_end+'&department.id='+ deptId+'&flag=PLAN';
          </#if>
          url = encodeURI(url);
          window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
       }
	    </script>
	 </@ww.form>
</@htmlPage>