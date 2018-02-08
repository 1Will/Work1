<#include "../../includes/eam2008.ftl" />
<#assign unusedList=""/>
<#assign unusedEditTitle=""/>
<#if toolingDevFlag?exists>
   <#if toolingDevFlag=='DEVICE'>
   <#assign unusedEditTitle="${action.getText('unusedDeviceEdit.title')}"/>
   <#assign unsedList="{action.getText('deviceBillList.title')}"/>
   <#else>
    <#assign unusedList="${action.getText('toolingBillList.title')}"/>
    <#assign unusedEditTitle="${action.getText('unusedToolingEdit.title')}"/>
   </#if>
</#if>   
<@htmlPage title="${unusedEditTitle}">
  <@ww.form namespace="'/runmaintenance/unused'" name="'unusedDevice'" action="'searchUnuseds'" method="'post'">
  <@ww.token name="searchUnusedsToken"/>
  	     <#include "unusedDeviceSearcher.ftl"/>
  	      <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
  	       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
  	         <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
         <@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>  
           <#if !(action.isReadOnly())>
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/unused/editUnused.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/> 
           </#if>
         </@buttonBar>
        <#if toolingDevFlag?exists>
          <#if toolingDevFlag=='DEVICE'>    
         <@list title="${action.getText('deviceBillList.title')}" 
            includeParameters="code|name|readOnly|asset.deviceNo|asset.name|unused.people|asset.graphNo|department.id|unUseDate_start|unUseDate_end|onlyValid|onlyInvalid|toolingDevFlag" 
        	fieldMap="like:code|name|asset.deviceNo|asset.name|unused.people|asset.graphNo|,date:|unUseDate_start|unUseDate_end|usedAprDate_start|usedAprDate_end" >
	        <@vlh.checkbox property="id" name="unusedIds">
	          <@vlh.attribute name="width" value="30" />
	        </@vlh.checkbox>
	        <#if (object.disabled)>
             <@vcolumn title="${action.getText('unusedBill_no')}" property="code" sortable="desc">
               ${object.code}
                 <@alignLeft/>
            </@vcolumn>
             <#else>
             <@vcolumn title="${action.getText('unusedBill_no')}" property="code" sortable="desc">
                 <a href="editUnused.html?toolingDevFlag=${toolingDevFlag?if_exists}&unused.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
                 <@alignLeft/>
            </@vcolumn>
         </#if>
            <@vcolumn title="${action.getText('unusedBill_Name')}" property="name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <#if toolingDevFlag?exists>
               <#if toolingDevFlag=='DEVICE'>
               <@vcolumn title="${action.getText('device.no')}" property="asset.deviceNo" sortable="desc">
                 <@alignLeft/>
               </@vcolumn>
               <@vcolumn title="${action.getText('device.Name')}" property="asset.name" sortable="desc">
                 <@alignLeft/>
                </@vcolumn>
               <#else>
               <@vcolumn title="${action.getText('tooling.no')}" property="asset.deviceNo" sortable="desc">
               <@alignLeft/>
               </@vcolumn>
                <@vcolumn title="${action.getText('tooling.graphNo')}" property="asset.graphNo" sortable="desc">
               <@alignLeft/>
               </@vcolumn>
               </#if>
               </#if>
                <@vcolumn title="${action.getText('department')}" property="asset.department.name">
	                 <@alignLeft/>
                </@vcolumn>
                 <@vcolumn title="${action.getText('unused.people')}" property="applicant.name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('unusedOccurDateTime')}" property="unUseDate" format="yyyy-MM-dd" sortable="desc">
                 <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('unusedisUsedOccurDateTime')}" property="usedAprDate" format="yyyy-MM-dd" sortable="desc">
                 <@alignCenter/>
            </@vcolumn>
           
            <@vcolumn title="${action.getText('unused.user')}" property="usedApr.name" >
                 <@alignLeft/>
            </@vcolumn>
            <#assign unusedStatus = ''/>
            <#if '${object.status}' == 'REQUEST'>
              <#assign unusedStatus = "${action.getText('unusedRequest')}"/>
            <#elseif '${object.status}' == 'UNUSED'>
              <#assign unusedStatus = "${action.getText('unused')}"/>
            <#else>
              <#assign unusedStatus = "${action.getText('normal')}"/>
            </#if>
         <@vcolumn title="${action.getText('unused_state')}">
           ${unusedStatus}
                 <@alignCenter/>
            </@vcolumn>
        </@list> 
        <#else>
            <@list title="${action.getText('toolingBillList.title')}" 
            includeParameters="code|name|readOnly|asset.deviceNo|asset.graphNo|department.id|unUseDate_start|unUseDate_end|onlyValid|onlyInvalid|toolingDevFlag" 
        	fieldMap="like:code|name|asset.deviceNo|asset.graphNo|,date:|unUseDate_start|unUseDate_end|usedAprDate_start|usedAprDate_end" >
	          <#if (object.disabled)>
	          <@vlh.checkbox property="id" name="unusedIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
             <@vcolumn title="${action.getText('unusedBill_no')}" property="code" sortable="desc">
               ${object.code}
                 <@alignLeft/>
            </@vcolumn>
             <#else>
             <@vlh.checkbox property="id" name="unusedIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
             <@vcolumn title="${action.getText('unusedBill_no')}" property="code" sortable="desc">
                 <a href="editUnused.html?toolingDevFlag=${toolingDevFlag?if_exists}&unused.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
                 <@alignLeft/>
            </@vcolumn>
         </#if>
            <@vcolumn title="${action.getText('unusedBill_Name')}" property="name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <#if toolingDevFlag?exists>
               <#if toolingDevFlag=='DEVICE'>
               <@vcolumn title="${action.getText('device.no')}" property="asset.deviceNo" sortable="desc">
                 <@alignLeft/>
               </@vcolumn>
               <@vcolumn title="${action.getText('device.Name')}" property="asset.name" sortable="desc">
                 <@alignCenter/>
                </@vcolumn>
               <#else>
               <@vcolumn title="${action.getText('tooling.no')}" property="asset.deviceNo" sortable="desc">
               <@alignLeft/>
               </@vcolumn>
                <@vcolumn title="${action.getText('tooling.graphNo')}" property="asset.graphNo" sortable="desc">
               <@alignLeft/>
               </@vcolumn>
               </#if>
               </#if>
             <@vcolumn title="${action.getText('unused.people')}" property="applicant.name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('unusedOccurDateTime')}" property="unUseDate" format="yyyy-MM-dd" sortable="desc">
                 <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('unusedisUsedOccurDateTime')}" property="usedAprDate" format="yyyy-MM-dd" sortable="desc">
                 <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('unused.user')}" property="usedApr.name" >
                 <@alignLeft/>
            </@vcolumn>
            <#assign unusedStatus = ''/>
            <#if '${object.status}' == 'REQUEST'>
              <#assign unusedStatus = "${action.getText('unusedRequest')}"/>
            <#elseif '${object.status}' == 'UNUSED'>
              <#assign unusedStatus = "${action.getText('unused')}"/>
            <#else>
              <#assign unusedStatus = "${action.getText('normal')}"/>
            </#if>
         <@vcolumn title="${action.getText('unused_state')}">
           ${unusedStatus}
                 <@alignCenter/>
            </@vcolumn>
        </@list>
       </#if>
        </#if> 
	   <#if !first>
        <@buttonBar> 
        <#if !(action.isReadOnly())>
          <@eam2008_disabledOrEnabled_button message="${action.getText('unusedBill')}" boxName="unusedIds" jsFunctionName="checkInvalidParms()"/>
        </#if>
         <#if toolingDevFlag?exists>
          <#if toolingDevFlag=='DEVICE'>
           <#-- <#if '${eamAuthentication?if_exists}' == 'Collect'></#if>-->
	        <@vbutton name="Print1"  class="button" value="${action.getText('pdfPrint')}" onclick="open_unusedDevPdf()"/>
	      	<@vbutton name="Print2"  class="button" value="${action.getText('xlsPrint')}" onclick="open_unusedDevXls()"/>
	     
	      <#--<#if toolingDevFlag=='DEVICE'>
	      	<@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_unusedDevPdf()"/>
	      	<@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_unusedDevXls()"/>
	      <#else>
	        <@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_unusedPdf()"/>
	      	<@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_unusedXls()"/>
	      </#if>-->
	      </#if>
	     </#if>
	    </@buttonBar>
       </#if>
  </@ww.form>
  <script language="JavaScript" type="text/JavaScript"> 
    <#if toolingDevFlag?exists>
      <#if toolingDevFlag=='DEVICE'>
  	   <#if !first && valueListNoRecords>
         document.forms[0].elements["Print1"].disabled="true";
         document.forms[0].elements["Print2"].disabled="true";
       </#if>
      </#if>
    </#if>
      window.onload = function () {
	    if ('${onlyInvalid?string}' == 'true') {
	      getObjByNameRe("onlyDisabled").checked=true;
	    }
	  }
	  function validateInvalid(delFun, checkFun) {
         if (delFun) {
         	checkFun;
         	return true;
         }
         return false;
      }
      /*
      *  设备的打印实现
      *
      */
      function   open_unusedDevPdf(){
     
            var url='${req.contextPath}/reports/unused/unusedReportList.pdf?code='+document.forms[0].elements["code"].value+
	       	'&name='+document.forms[0].elements["name"].value +
	       	'&asset.deviceNo='+ document.forms[0].elements["asset.deviceNo"].value+
        	'&asset.name='+ document.forms[0].elements["asset.name1"].value+
        	'&unUseDate_start='+ document.forms[0].elements["unUseDate_start"].value+
        	'&unUseDate_end='+ document.forms[0].elements["unUseDate_end"].value+
        	'&usedAprDate_start='+document.forms[0].elements["usedAprDate_start"].value+
        	'&usedAprDate_end='+document.forms[0].elements["usedAprDate_end"].value+
        	'&department.id='+ document.forms[0].elements["department.id"].value+
        	'&status='+document.forms[0].elements["status"].value+
        	'&toolingDevFlag='+document.forms[0].elements["toolingDevFlag"].value;
        	if(document.forms[0].elements["onlyDisabled"].checked){
        	    url=url+'&disabled=true';
        	} else {
        	  url = url + '&disabled=false';
        	}
        	url = encodeURI(url);
      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
      }
      function   open_unusedDevXls(){
       var url='${req.contextPath}/reports/unused/unusedReportList.xls?code='+document.forms[0].elements["code"].value+
	       	'&name='+document.forms[0].elements["name"].value +
	       	'&asset.deviceNo='+ document.forms[0].elements["asset.deviceNo"].value+
        	'&asset.name='+ document.forms[0].elements["asset.name1"].value+
        	'&unUseDate_start='+ document.forms[0].elements["unUseDate_start"].value+
        	'&unUseDate_end='+ document.forms[0].elements["unUseDate_end"].value+
        	'&usedAprDate_start='+document.forms[0].elements["usedAprDate_start"].value+
        	'&usedAprDate_end='+document.forms[0].elements["usedAprDate_end"].value+
        	'&department.id='+ document.forms[0].elements["department.id"].value+
        	'&status='+document.forms[0].elements["status"].value+
        	'&toolingDevFlag='+document.forms[0].elements["toolingDevFlag"].value;
        	if(document.forms[0].elements["onlyDisabled"].checked){
        	    url=url+'&disabled=true';
        	} else {
        	  url = url + '&disabled=false';
        	}
        url = encodeURI(url);
        window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
      }
      <#---
      /*
      * 工装打印实现
      */
        function   open_unusedPdf(){
        alert(document.forms[0].elements["asset.name1"].value);
            var url='${req.contextPath}/reports/unused/unusedReportList.pdf?code='+document.forms[0].elements["code"].value+
	       	'&name='+(document.forms[0].elements["name"].value) +
	       	'&asset.deviceNo='+ (document.forms[0].elements["asset.deviceNo"].value)+
        	'&asset.name='+ (document.forms[0].elements["asset.name1"].value)+
        	'&unUseDate_start='+ (document.forms[0].elements["unUseDate_start"].value)+
        	'&unUseDate_end='+ (document.forms[0].elements["unUseDate_end"].value)+
        	'&usedAprDate_start='+(document.forms[0].elements["usedAprDate_start"].value)+
        	'&usedAprDate_end='+(document.forms[0].elements["usedAprDate_end"].value)+
        	'&department.id='+ (document.forms[0].elements["department.id"].value)+
        	'&status='+(document.forms[0].elements["status"].checked);
        	if(document.forms[0].elements["onlyDisabled"].checked){
        	    url=url+'&disabled=true';
        	}
        	url = encodeURI(url);
      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=no,width=screen.width,height=screen.height,left=0,top=0");
      }
      function   open_unusedXls(){
        var url='${req.contextPath}/reports/unused/unusedReportList.xls?code='+document.forms[0].elements["code"].value+
	            '&name='+(document.forms[0].elements["name"].value) +
	       	    '&asset.deviceNo='+ (document.forms[0].elements["asset.deviceNo"].value)+
        	    '&asset.name='+ (document.forms[0].elements["asset.name1"].value)+
        	    '&unUseDate_start='+ (document.forms[0].elements["unUseDate_start"].value)+
        	    '&unUseDate_end='+ (document.forms[0].elements["unUseDate_end"].value)+
        	    '&usedAprDate_start='+(document.forms[0].elements["usedAprDate_start"].value)+
        	    '&usedAprDate_end='+(document.forms[0].elements["usedAprDate_end"].value)+
        	    '&department.id='+ (document.forms[0].elements["department.id"].value)+
        	    '&unused_state='+(document.forms[0].elements["status"].checked);
        if(document.forms[0].elements["onlyDisabled"].checked){
          url=url+'&disabled=true';
        }
        url = encodeURI(url);
        window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=no,width=screen.width,height=screen.height,left=0,top=0");
      }
      -->
      <#--
       function open_unuseXls() {
				//var unusedid=id;
				<#if toolingDevFlag=='DEVICE'>
			          //var url='${req.contextPath}/reports/unused/toolingunused.xls?unusedid='+unusedid;
			          var url='${req.contextPath}/reports/unused/unusedReportList.xls';
		        <#else>
			          //var url='${req.contextPath}/reports/unused/unusedReportList.xls?unusedid='+unusedid;
			          var url='${req.contextPath}/reports/unused/toolingunused.xls';
		        </#if>
	      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
	      	}
	  function open_unusePdf() {
				//var unusedid=id;
				<#if toolingDevFlag=='DEVICE'>
			          //var url='${req.contextPath}/reports/unused/toolingunused.pdf?unusedid='+unusedid;
			         var url='${req.contextPath}/reports/unused/unusedReportList.pdf';
		        <#else>
			         // var url='${req.contextPath}/reports/unused/deviceunused.pdf?unusedid='+unusedid;
			          var url='${req.contextPath}/reports/unused/toolingunused.pdf';
		        </#if>
	      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
	      	}
	      	-->
 </script>
</@htmlPage>