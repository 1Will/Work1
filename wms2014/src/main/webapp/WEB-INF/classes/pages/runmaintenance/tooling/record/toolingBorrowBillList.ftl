<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('toolBorrowSearch.title')}">
  <@ww.form namespace="'/runmaintenance/tooling/record'" name="'borrowBill'" action="'searchToolingBorrowBills'" method="'post'">
  <@ww.token name="searchToolingBorrowBillsToken"/>
  	     <#include "toolingBorrowBillSearcher.ftl"/>
  	     <@ww.hidden name="'toolingState'" value=""/>
  	     <@ww.hidden name="'toolingDev_Flag'" value="'${toolingDev_Flag?if_exists}'"/>
  	     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>  
        	  <#if !(action.isReadOnly())>
              <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/tooling/record/editToolingBorrowBill.html?toolingDev_Flag=${toolingDev_Flag?if_exists}"/> 
             </#if>
         </@buttonBar>   
         <@list title="${action.getText('borrowBill.list')}" 
         	url="listToolingBorrowBills.html?toolingDev_Flag=TOOLING&"
            includeParameters="billNo|billName|readOnly|toolingNo|toolingName|toolingGraphNo|borrowTime_start|borrowTime_end|toolingType.id|department.id|toolingStatus.code|onlyValid|onlyInvalid" 
        	fieldMap="like:billNo|billName|toolingNo|toolingName|toolingGraphNo,date:borrowTime_start|borrowTime_end" >
        	<@vlh.checkbox property="id" name="borrowBillIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
             <#if (object.disabled)>
              <@vcolumn title="${action.getText('billNo')}" property="billNo" sortable="desc">
               ${object.billNo}
                <@alignLeft/>
            </@vcolumn>
            <#else>
            <@vcolumn title="${action.getText('billNo')}" property="billNo" sortable="desc">
                <a href="editToolingBorrowBill.html?toolingBorrowBill.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}&&toolingDev_Flag=${req.getParameter('toolingDev_Flag')?if_exists}">${object.billNo}</a>
                <@alignLeft/>
            </@vcolumn>
            </#if>
            <@vcolumn title="${action.getText('billName')}" property="billName" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('tooling.no')}" property="tooling.deviceNo" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('tooling.graphNo')}" property="tooling.graphNo" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('tooling.name')}" property="tooling.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('toolingCategory')}" property="tooling.toolingType.value" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('department')}" property="tooling.department.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('tooling.borrower')}" property="borrower.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('borrowDateTime')}" property="borrowReturnDateTm" format="yyyy-MM-dd" sortable="desc">
            <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('checker')}" property="checker.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('checkDateTime')}" property="checkDateTm" format="yyyy-MM-dd" sortable="desc">
            <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('storeman')}" property="storeman.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <#if (object.returnFlag?string)=='true'>
              <#assign status="${action.getText('tooling.returned')}"/>
            <#else>
              <#assign status="${action.getText('tooling.borrowing')}"/>
            </#if>
            <@vcolumn title="${action.getText('borrowBill.status')}">
            ${status}
            <@alignLeft/>
            </@vcolumn>
      
        </@list>  
	    <#if !first>
        <@buttonBar>
           <#-- <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('tooling.borrowBill')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return validateDelete(confirmDeletes(\"borrowBillIds\", \"${confirmMessage}\"), checkInvalidParms())'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>-->
              <#if !(action.isReadOnly())>
              <@eam2008_disabledOrEnabled_button message="${action.getText('subscribesBill')}" boxName="borrowBillIds" jsFunctionName="checkInvalidParms()"/>
             </#if>
        </@buttonBar>
        </#if>
	 <script language="JavaScript" type="text/JavaScript"> 
	  function validateDelete(delFun, checkFun) {
         if (delFun) {
         	checkFun;
         	document.forms[0].elements["toolingState"].value = 'TOOLING_NORMAL';
         	return true;
         }
         return false;
       }
	</script>
  </@ww.form>
</@htmlPage>