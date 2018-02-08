<#include "../../includes/eam2008.ftl" />
 <#assign toolingDevName=""/>
 <#assign migrateBillListName=""/>
<#if toolingDevFlag?exists>
   <#if toolingDevFlag=='DEVICE'>
   <#assign toolingDevName="${action.getText('migrateBillSearcher.title')}"/>
   <#assign migrateBillListName="${action.getText('migrateBill.list')}"/>
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
   <#else>
    <#assign toolingDevName="${action.getText('migrateBillTooingSearcher.title')}"/>
    <#assign migrateBillListName="${action.getText('toolingMigrateBill.list')}"/>
   </#if>
</#if>
<@htmlPage title="${toolingDevName}">
  <@ww.form namespace="'/runmaintenance/migrate'" name="'migrateDevice'" action="'searchMigrates'" method="'post'">
  <@ww.token name="migrateDeviceToken"/>
  	     <#include "migrateSearcher.ftl"/>
  	       <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
         <@buttonBar>    
          <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>    
          <@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/> 
        <#if !(action.isReadOnly())> 
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/migrate/editMigrate.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/> 
          </#if>
         </@buttonBar>   
         <@list title="${migrateBillListName}" 
            includeParameters="billNo|readOnly|billName|requestDate_start|requestDate_end|department.id|requester.name|toolingDevFlag|onlyValid|onlyInvalid" 
        	fieldMap="like:billNo|billName|requester.name,date:requestDate_start|requestDate_end">
	          <#if (object.disabled)>
	          <@vlh.checkbox property="id" name="migrateIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
             <@vcolumn title="${action.getText('migrateBillNo')}" property="billNo" sortable="desc">
               ${object.billNo}
                 <@alignLeft/>
            </@vcolumn>
             <#else>
             <@vlh.checkbox property="id" name="migrateIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
             <@vcolumn title="${action.getText('migrateBillNo')}" property="billNo" sortable="desc">
                 <a href="editMigrate.html?toolingDevFlag=${toolingDevFlag}&migrate.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.billNo}</a>
                 <@alignLeft/>
            </@vcolumn>
         </#if>
            <@vcolumn title="${action.getText('migrateBillName')}" property="billName" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('migrate.department')}" property="requestDepartment.name">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('migrate.requestPeople')}" property="requester.name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('migrate.aplicationDate')}" property="requestDate"  format="yyyy-MM-dd" sortable="desc">
                 <@alignCenter/>
            </@vcolumn>
             <@vcolumn title="${action.getText('migrate.reason')}" property="migrateReason">
                 <@alignLeft/>
            </@vcolumn>
        </@list>  
	     <#if !first>
       
        <@buttonBar>
	    	<#-- <#if '${onlyInvalid?string}' == 'true'>
	             <#assign confirmMessage1 = "${action.getText('confirm.valid')}${action.getText('inventoryBill')}?" />
	            <@vsubmit name="'enabled'" value="'${action.getText('validation')}'">
	                <@ww.param name="'onclick'" value="'return validateInvalid(confirmInvalids(\"migrateIds\", \"${confirmMessage1}\"),checkInvalidParms());'"/>
	               
	                 <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	           </@vsubmit>
	          <#else>
	             <#assign confirmMessage = "${action.getText('confirm.inValid')}${action.getText('inventoryBill')}?" />
	            <@vsubmit name="'disabled'" value="'${action.getText('invalidation')}'">
	                <@ww.param name="'onclick'" value="'return validateInvalid(confirmInvalids(\"migrateIds\", \"${confirmMessage}\"),checkInvalidParms());'"/>
	                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            </@vsubmit>
	            </#if>-->
	            <#if !(action.isReadOnly())>
	            <@eam2008_disabledOrEnabled_button message="${action.getText('trusteeshipDevice')}" boxName="migrateIds" jsFunctionName="checkInvalidParms()"/>
	    		</#if>
	    		<#--
	    		<@vbutton value="打印" class="button" />
	    		-->
	    </@buttonBar> 
        </#if>
  </@ww.form>
  <script language="JavaScript" type="text/JavaScript"> 
      window.onload = function () {
      
	    if ('${onlyInvalid?string}' == 'true') {
	      document.getElementById("onlyDisabled").checked=true;
	    }
	  }
	  	  function validateInvalid(delFun, checkFun) {
         if (delFun) {
         	checkFun;
         	return true;
         }
         return false;
       }
    </script>
</@htmlPage>
