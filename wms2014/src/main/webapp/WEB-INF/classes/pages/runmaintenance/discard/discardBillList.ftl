<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('dicardBill.list')}">
  <@ww.form namespace="'/runmaintenance/discard'" name="'discardBill'" action="'searchDiscardBills'" method="'post'">
  <@ww.token name="discardBillToken"/>
  	     <#include "discardBillSearcher.ftl"/>
      <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/> 
         <#if !(action.isReadOnly())>
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/discard/editDiscardBill.html?readOnly=${req.getParameter('readOnly')?if_exists}"/> 
        </#if>
         </@buttonBar>   
         <@list title="${action.getText('discardBilllist')}" 
            includeParameters="discardBillNo|readOnly|discardBillName|department.id|reportName|reportDate_start|reportDate_end|onlyValid|onlyInvalid" 
        	fieldMap="like:discardBillNo|discardBillName|reportName,date:reportDate_start|reportDate_end">
	          <#if (object.disabled)>
	          <@vlh.checkbox property="id" name="discardBillIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
             <@vcolumn title="${action.getText('discardBillNo')}" property="billNo" sortable="desc">
               ${object.billNo}
                 <@alignLeft/>
            </@vcolumn>
             <#else>
             <@vlh.checkbox property="id" name="discardBillIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
             <@vcolumn title="${action.getText('discardBillNo')}" property="discardBillNo" sortable="desc">
                 <a href="editDiscardBill.html?&discardBill.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.billNo}</a>
                 <@alignLeft/>
            </@vcolumn>
         </#if>
            <@vcolumn title="${action.getText('discardBillName')}" property="discardBillName" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('department')}" property="department.name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('reportUser')}" property="reportUser.name"  sortable="desc">
                 <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('reportDate')}" property="reportDate"  format="yyyy-MM-dd" sortable="desc">
                 <@alignCenter/>
            </@vcolumn>
             <@vcolumn title="${action.getText('discard.reason')}" property="discardReason" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
        </@list>  
	     <#if !first>
       
        <@buttonBar>
        <#if !(action.isReadOnly())>
	            <@eam2008_disabledOrEnabled_button message="${action.getText('discard')}" boxName="discardBillIds" jsFunctionName="checkInvalidParms()"/>
	   </#if>
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
