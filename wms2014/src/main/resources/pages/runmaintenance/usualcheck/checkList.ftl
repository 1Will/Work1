<#include "../../includes/eam2008.ftl" />

<#assign checkSearcherTitle = ''/>
<#if toolingDevFlag?exists>
  <#if toolingDevFlag == 'DEVICE'>
    <#assign checkSearcherTitle = "${action.getText('deviceCheck.title')}"/>
  <#else>
    <#assign checkSearcherTitle = "${action.getText('toolingCheck.title')}"/>
  </#if>
</#if>
<@htmlPage title="${checkSearcherTitle}">
  <@ww.form namespace="'/runmaintenance/usualcheck'" name="'checkList'" action="'searchChecks'" method="'post'">
  <@ww.token name="searchChecksToken"/>
  	     <#include "checkSearcher.ftl"/>
  	     <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
  	     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <@buttonBar>        
        	<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>  
        	<#if !(action.isReadOnly())>
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/usualcheck/editCheck.html?toolingDevFlag=${toolingDevFlag?if_exists}"/> 
            </#if>
         </@buttonBar>   
         <@list title="${action.getText('check.list')}" 
                includeParameters="billNo|readOnly|toolingName|billName|checker|assetNo|graphNo|assetName|department.id|status|checkDate_start|checkDate_end|graphNo|toolingDevFlag|onlyValid|onlyInvalid" 
        	    fieldMap="like:billNo|billName|checker|toolingName|assetNo|graphNo|assetName,date:checkDate_start|checkDate_end" >
	       <@vlh.checkbox property="id" name="checkIds">
	         <@vlh.attribute name="width" value="30" />
	       </@vlh.checkbox>
	       <@vcolumn title="${action.getText('usualcheck.billNo')}" property="billNo" sortable="desc">
	         <#if !(action.isOnlyInvalid())>
	       	 	<a href="editCheck.html?check.id=#{object.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.billNo}</a>
             </#if>
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('usualcheck.name')}" property="name" sortable="desc">
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${assetNo}" property="asset.deviceNo" sortable="desc">
             <@alignLeft/>
           </@vcolumn>
           <#if toolingDevFlag?exists>
             <#if toolingDevFlag == 'TOOLING'>
               <@vcolumn title="${action.getText('tooling.graphNo')}" property="asset.graphNo" sortable="desc">
                 <@alignLeft/>
               </@vcolumn>
             </#if>
           </#if>
           <#--
           <@vcolumn title="${assetName}" property="asset.name" sortable="desc">
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('Name')}" property="toolingName" sortable="desc">
             <@alignLeft/>
           </@vcolumn>
           -->
           <@vcolumn title="${assetName}" property="asset.name" sortable="desc">
           	 <#if object.asset?exists>
           	   ${object.asset.name}
           	 <#else>
           	   ${object.toolingName}
           	 </#if>
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('department')}" property="department.name" sortable="desc">
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('checker')}" property="checker" sortable="desc">
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('checkDate')}" property="checkDate" format="yyyy-MM-dd" sortable="desc">
             <@alignCenter/>
           </@vcolumn>
           <@vcolumn title="${action.getText('checkResult')}" property="result">
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('checkHandleResult')}" property="handle">
             <@alignLeft/>
           </@vcolumn>
            <#assign checkStatus = ''/>
            <#if object.status?exists>
            <#if '${object.status}' == 'unEnrol'>
              <#assign checkStatus = "${action.getText('unEnrolcheck')}"/>
            <#else>
              <#assign checkStatus = "${action.getText('Enrolcheck')}"/>
            </#if>
            </#if>
         <@vcolumn title="${action.getText('Enrolcheckstate')}">
           ${checkStatus}
                 <@alignCenter/>
            </@vcolumn>
         </@list>  
	     <#if !first>
	     <#--
	       <@buttonBar>
	         <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('usualCheck')}?" />
	         <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	           <@ww.param name="'onclick'" value="'return validateDelete(confirmDeletes(\"checkIds\", \"${confirmMessage}\"), checkInvalidParms());'"/>
	           <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	         </@vsubmit>
	       </@buttonBar>
	       -->
	       
	       <@buttonBar>
	       <#if !(action.isReadOnly())>
	         <@eam2008_disabledOrEnabled_button message="${action.getText('usualCheck')}" boxName="checkIds" jsFunctionName="checkInvalidParms()"/>
	        </#if>
	       </@buttonBar>
         </#if>
  </@ww.form>
  <script language="JavaScript" type="text/JavaScript"> 
    function validateDelete(delFun, checkFun) {
      if (delFun) {
        checkFun;
        return true;
      } else {
        return false;
      }
    }
  </script>
</@htmlPage>