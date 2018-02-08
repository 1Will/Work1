<#--$Id: productionLineList.ftl 11319 2008-03-14 08:25:24Z wzou $ -->
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('checkPointReportList.title')}">
    <@ww.form name="'listForm'" action="'searchCheckPointReports'" method="'post'">
        <@ww.token name="searchCheckPointReportsToken"/>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
        <@ww.hidden name="'reportIds'" value=""/>
        <#include "./checkPointReportSearcher.ftl" />
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>  
        </@buttonBar>
        <@list title="${action.getText('checkPointReport.list')}" includeParameters="reportNo|eamAuthentication|reportName|readOnly|department.id|reportTime_start|reportTime_end" fieldMap="like:reportNo|reportName|,date:reportTime_start|reportTime_end" >
            <#--<@vlh.checkbox property="id" name="productionLineIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>-->
            <@ww.hidden name="'reportId'" value="'#{object.id?if_exists}'"/>
            <@ww.hidden name="'submit'" value="${object.submit?string}"/>
            <@vcolumn title="${action.getText('report.no')}" property="reportNo" sortable="desc">
                <a href="editCheckPointReport.html?report.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}">${object.reportNo}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('report.name')}" property="name" sortable="desc">
            	<@alignLeft/>
         	</@vcolumn>  
            <@vcolumn title="${action.getText('department')}" property="department.name">
            	<@alignLeft/>
         	</@vcolumn>  
         	<@vcolumn title="${action.getText('report.month')}" property="month" sortable="desc"> 
         		<@alignCenter/>
         	</@vcolumn>       
         	<@vcolumn title="${action.getText('report.reportTime')}" property="reportTime" format="yyyy-MM-dd" sortable="desc"> 
         		<@alignCenter/>
         	</@vcolumn>
        </@list>
        <#if !first>
           <@buttonBar>
            <#if '${eamAuthentication?if_exists}' == 'Collect'>
	         <@vbutton name="printPdf"  class="button" value="${action.getText('pdfCheckPointReportPrint')}" onclick="open_CheckPointReportPdf()"/>
	         <@vbutton name="printXls"  class="button" value="${action.getText('xlsCheckPointReportPrint')}" onclick="open_CheckPointReportXls()"/>
	         	<#if !valueListNoRecords>
	         		<@vsubmit name="'submitRecord'" value="'${action.getText('submit')}'" onclick="'return validateInvalid(storeReportIds(),checkInvalidParms())'"/>
	         	</#if>
	        </#if>
	       </@buttonBar>
        </#if>
    </@ww.form>
    <script language="javascript">
     window.onload = function () {
     	 var selector = document.all("department.id");
         var groups=selector.options.length;  
         for (i=0; i<groups; i++){
            <#if req.getParameter('department.id')?exists>
            if (selector.options[i].value=="${req.getParameter('department.id')?if_exists}"){
               selector.options[i].selected="true";
            }
            </#if>
         }
         <#if first>
         <#if loginUser.department?exists>
             document.getElementById("department.id").value = #{loginUser.department.id};
         </#if>
        </#if>
     	<#if '${eamAuthentication?if_exists}' == 'Collect'>
	        <#if !valueListNoRecords>
		     	var selector = document.getElementsByName("submit");
		     	for (i = 0; i < selector.length; i++) {
			     	if (selector[i].value=="true"){
			     		document.forms[0].elements["submitRecord"].disabled="true";
			     		return true;
			     	}
		     	}
		    </#if>
		    <#if !first && valueListNoRecords>
              document.forms[0].elements["printPdf"].disabled="true";
              document.forms[0].elements["printXls"].disabled="true";
            </#if>
	    </#if>
     	return  true;
     }
     function validateInvalid(delFun, checkFun) {
      if (delFun) {
        checkFun;
        return true;
      }
      return false;
   	}
     function storeReportIds(){
     	if(document.forms[0].elements["month"].value==""){
     		 alert("${action.getText('month.not.null')}");
     		 return false;
     	}
     	 if(confirm("${action.getText('submitRecord.messager')}")){
     	 	return true;
     	 }else {
     	 	return false;
     	 }
		 <#--var selector = document.getElementsByName("reportId");
		 var allReportIds="";
		 for (i = 0; i < selector.length; i++) {
	     	allReportIds += selector[i].value+"," 
     	 }
     	 document.forms[0].elements["month"].value = allReportIds;-->
		 return true;
	 }
     function open_CheckPointReportPdf(){
      var month=document.forms[0].elements["month"].value;
      var deptId=document.forms[0].elements["department.id"].value;
      var url='${req.contextPath}/reports/device/listAllCheckPoint.pdf?month='+month+'&department.id='+ deptId;
        if(month==''){
         alert("${action.getText('month.not.null')}");
         return false;
      }
      url = encodeURI(url);
      window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
     }
     function  open_CheckPointReportXls(){
      var month=document.forms[0].elements["month"].value;
      var deptId=document.forms[0].elements["department.id"].value;
      var url='${req.contextPath}/reports/device/listAllCheckPoint.xls?month='+month+'&department.id='+ deptId;
      if(month==''){
         alert("${action.getText('month.not.null')}");
         return false;
      }
      url = encodeURI(url);
      window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
     }
    </script>    
</@htmlPage>