<#include "../../includes/eam2008.ftl" />
 <#assign toolingDevName=""/>
<#if toolingDevFlag?exists>
   <#if toolingDevFlag=='DEVICE'>
   <#assign toolingDevName="${action.getText('trusteeshipBillSearcher.title')}"/>
   <#else>
    <#assign toolingDevName="${action.getText('trusteeshipBillTooingSearcher.title')}"/>
   </#if>
</#if>
<@htmlPage title="${toolingDevName}">
  <@ww.form namespace="'/runmaintenance/trusteeship'" name="'trusteeshipDevice'" action="'searchTrusteeships'" method="'post'">
  <@ww.token name="trusteeshipDeviceToken"/>
  	     <#include "trusteeshipSearcher.ftl"/>
  	       <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
  	        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <@buttonBar>        
        	<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>  
        	<#if !(action.isReadOnly())>
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/trusteeship/editTrusteeship.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/> 
            </#if>
         </@buttonBar>   
         <@list title="${action.getText('trusteeshipBill.list')}" 
            includeParameters="billNo|billName|readOnly|trusteeshipRequestDate_start|trusteeshipRequestDate_end|department.id|trusteeshipUser|toolingDevFlag|onlyValid|onlyInvalid" 
        	fieldMap="like:billNo|billName|trusteeshipUser|,date:trusteeshipRequestDate_start|trusteeshipRequestDate_end">
	        <@vlh.checkbox property="id" name="trusteeshipIds">
	          <@vlh.attribute name="width" value="30" />
	        </@vlh.checkbox>
	        <#if (object.disabled)>
             <@vcolumn title="${action.getText('trusteeshipBillNo')}" property="billNo" sortable="desc">
               ${object.billNo}
                 <@alignLeft/>
            </@vcolumn>
             <#else>
             <@vcolumn title="${action.getText('trusteeshipBillNo')}" property="billNo" sortable="desc">
                 <a href="editTrusteeship.html?toolingDevFlag=${toolingDevFlag}&trusteeship.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.billNo}</a>
                 <@alignLeft/>
            </@vcolumn>
         </#if>
            <@vcolumn title="${action.getText('trusteeshipBillName')}" property="billName" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('department')}" property="trusteeshipRequestDepartment.name">
                 <@alignLeft/>
            </@vcolumn>
              <@vcolumn title="${action.getText('requester.Person')}" property="trusteeshipUser" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('trusteeshipDate')}" property="trusteeshipRequestDate" format="yyyy-MM-dd" sortable="desc">
                 <@alignCenter/>
            </@vcolumn>
        </@list>  
	     <#if !first>
        <@buttonBar>
               <#if !(action.isReadOnly())>
	            <@eam2008_disabledOrEnabled_button message="${action.getText('trusBill')}" boxName="trusteeshipIds" jsFunctionName="checkInvalidParms()"/>
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
    </script>
</@htmlPage>