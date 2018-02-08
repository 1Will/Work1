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
<base target="_self">
  <@ww.form namespace="'/runmaintenance/repair/plan'" name="'checkList'" action="'searchuausalChecks'" method="'post'">
  <@ww.token name="searchChecksToken"/>
  	     <#include "checkusualSearcher.ftl"/>
  	     <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
         <@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>  
         </@buttonBar>   
         <@list title="" excel=false setupTable=false 
                includeParameters="billNo|billName|assetNo|toolingName|graphNo|assetName|department.id|checkDate_start|checkDate_end|graphNo|toolingDevFlag|onlyValid|onlyInvalid" 
        	    fieldMap="like:billNo|billName|assetNo|toolingName|graphNo|assetName,date:checkDate_start|checkDate_end" >
	       <@vlh.checkbox property="id" name="checkIds">
	         <@vlh.attribute name="width" value="30" />
	       </@vlh.checkbox>
	       <@vcolumn title="${action.getText('usualcheck.billNo')}" property="billNo" sortable="desc">
	         <#if !(action.isOnlyInvalid())>
	       	 	<#--<a href="editCheck.html?check.id=#{object.id}&toolingDevFlag=${toolingDevFlag?if_exists}"></a>-->
	       	 	${object.billNo}
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
           <@vcolumn title="${assetName}" property="asset.name" sortable="desc">
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('Name')}" property="toolingName" sortable="desc">
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('department')}" property="asset.department.name">
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('checker')}" property="checker" sortable="desc">
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('checkDate')}" property="checkDate" format="yyyy-MM-dd" sortable="desc">
             <@alignCenter/>
           </@vcolumn>
           <@vcolumn title="${action.getText('checkResult')}" property="result" attributes="width='150'">
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('checkHandleResult')}" property="handle" attributes="width='150'">
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
	       <@buttonBar>
	      	<@vsubmit name="'choose'" value="'${action.getText('choose')}'" onclick="'return confirmSelects(\"checkIds\");'">
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
         </#if>
  </@ww.form>
  <script language="JavaScript" type="text/JavaScript"> 
    
        function confirmSelects(boxname) {
	      if (!hasChecked(boxname)) {
	      	return false;
	      }
	      chooseSubscribes(boxname);
	      return true;
	    }
	    	      	
	   function chooseSubscribes(boxname) {
	     var selector = document.getElementsByName(boxname);
	     if (!selector) {
	       return false;
	     }
		 var checkIds= "";
		 if (selector.length) {
		   for (i = 0; i < selector.length; i++) {
		     if (selector[i].checked) {
			   checkIds += selector[i].value + ",";
			 }
		   }
		 }
		 returnDialog(checkIds,false);
	   }
        
  </script>
</@htmlPage>