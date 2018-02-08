<#include "../../includes/eam2008.ftl" />
<#include "./common.ftl" />
<@htmlPage title="${action.getText('toolingDiscardSearch.title')}">
	 <@ww.form namespace="'/runmaintenance/discard'" name="'listToolingDiscard'" action="'searchToolingDiscards'" method="'post'">
	 <@ww.token name="searchDiscardsToken"/>
	 	<#include "toolingDiscardSearcher.ftl"/>
	 	<@ww.hidden name="'flag'" value="${(toolingDevFlag?string)?if_exists}"/>
	 	 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <@buttonBar>
	 		<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
	 		<#if !(action.isReadOnly())>
	 		  <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/discard/editToolingDiscard.html?toolingDevFlag=true&readOnly=${req.getParameter('readOnly')?if_exists}"/>  
            </#if>
        </@buttonBar>
	    	 <@list title="${action.getText('toolingDiscard.list')}" 
	            includeParameters="discardNo|name|toolingNo|readOnly|toolingName|toolingGraphNo|toolingDevFlag|applyDatetime_start|applyDatetime_end|toolingType.id|department.id|checkupResult|agreeStatus|onlyValid|onlyInvalid" 
	        	fieldMap="like:discardNo|name|toolingNo|toolingName|toolingGraphNo|toolingDevFlag,date:applyDatetime_start|applyDatetime_end" >
	           
	             <#if (object.disabled)>
		        	<@vlh.checkbox property="id" name="discardIds">
		            	<@vlh.attribute name="width" value="30" />
		            </@vlh.checkbox>
		            <@vcolumn title="${action.getText('discardNo')}" property="discardNo" sortable="desc">
	                ${object.discardNo}
	                <@alignLeft/>
	            </@vcolumn>
	            <#else>
		             <@vlh.checkbox property="id" name="discardIds">
		            	<@vlh.attribute name="width" value="30" />
		             </@vlh.checkbox>
		             <@vcolumn title="${action.getText('discardNo')}" property="discardNo" sortable="desc">
	                <a href="editToolingDiscard.html?discard.id=#{object.id}&&toolingDevFlag=true&readOnly=${req.getParameter('readOnly')?if_exists}">${object.discardNo}</a>
	                <@alignLeft/>
	            </@vcolumn>
	            </#if>
	            <@vcolumn title="${action.getText('name')}" property="name" sortable="desc">
	                 <@alignLeft/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('tooling.no')}" property="tooling.deviceNo" sortable="desc">
	                 <@alignLeft/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('tooling.name')}" property="tooling.name" sortable="desc">
	                 <@alignLeft/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('tooling.graphNo')}" property="tooling.graphNo" sortable="desc">
	                 <@alignLeft/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('toolingCategory')}" property="tooling.toolingType.value" sortable="desc">
	                 <@alignLeft/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('department')}" property="department.name" sortable="desc">
	                 <@alignLeft/>
                </@vcolumn>

	            <@vcolumn title="${action.getText('applyDatetime')}" property="applyDatetime" format="yyyy-MM-dd" sortable="desc">
	                 <@alignCenter/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('manager')}" property="manager.name" sortable="desc">
	                 <@alignLeft/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('descardDatetime')}" property="descardDatetime" format="yyyy-MM-dd" sortable="desc">
	                 <@alignCenter/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('checkupResult')}" property="checkupResult">
	                 <@alignLeft/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('status')}" property="status">
	                 <@alignLeft/>
                </@vcolumn>
	            <#if (object.discardFlag?string)=='true'>
	              <#assign status="${action.getText('agreeStatus')}"/>
	            <#else>
	              <#assign status="${action.getText('dissentStatus')}"/>
	            </#if>
	            <@vcolumn title="${action.getText('discard.status')}">
	            ${status}
	            <@alignLeft/>
	            </@vcolumn>
	        </@list>
	    <#if !first>
        <@buttonBar>
           <#-- <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('tooling.Discard')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('disabled')}'">
                <@ww.param name="'onclick'" value="'return validateDelete(confirmDeletes(\"discardIds\", \"${confirmMessage}\"), checkInvalidParms())'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            -->
            
           <#if !(action.isReadOnly())>
            <@eam2008_disabledOrEnabled_button message="${action.getText('discardBillMessage')}" boxName="discardIds" jsFunctionName="checkInvalidParms()"/>
          </#if>  
            <#--
            <@vbutton name="print"  class="button" value="${action.getText('print.all')}" onclick="open_discardPdf('')"/>
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
