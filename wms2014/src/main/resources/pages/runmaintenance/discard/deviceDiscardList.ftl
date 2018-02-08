<#include "../../includes/eam2008.ftl" />
<#include "./common.ftl" />
<@htmlPage title="${action.getText('deviceDiscardSearch.title')}">
	 <@ww.form namespace="'/runmaintenance/discard'" name="'listDeviceDiscard'" action="'searchDeviceDiscards'" method="'post'">
	 <@ww.token name="searchDiscardsToken"/>
	 	<#include "deviceDiscardSearcher.ftl"/>
	 	<@ww.hidden name="'flag'" value="${(toolingDevFlag?string)?if_exists}"/>
         <@buttonBar>
	 		<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
        	<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/discard/editDeviceDiscard.html?&&toolingDevFlag=false"/>
        </@buttonBar>
	    	 <@list title="${action.getText('deviceDiscard.list')}" 
	            includeParameters="discardNo|name|deviceNo|deviceName|device.specification|toolingDevFlag|applyDatetime_start|applyDatetime_end|toolingType.id|department.id|checkupResult|agreeStatus" 
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
		       <@vcolumn title="${action.getText('discardNo')}" property="discardNo" sortable="desc">
	                <a href="editDeviceDiscard.html?discard.id=#{object.id}&&toolingDevFlag=false">${object.discardNo}</a>
	                <@alignLeft/>
	            </@vcolumn>
		      </#if>  
		       
		       <#-- <#if (object.discardFlag?string)=='false'>
		        	
	            <#else>
		             <@vlh.checkbox property="id" name="disableddiscardIds" attributes="id=disabledToolingIds onclick=\"notSelectedInvalid('disableddiscardIds','discardIds');\"">
		            	<@vlh.attribute name="width" value="30" />
		            	<@vlh.attribute name="disabled" value="true" />
		             </@vlh.checkbox>
	            </#if>-->
	            <@vcolumn title="${action.getText('discardNo')}" property="discardNo" sortable="desc">
	                <a href="editDeviceDiscard.html?discard.id=#{object.id}&&toolingDevFlag=false">${object.discardNo}</a>
	                <@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('name')}" property="name" sortable="desc">
	                 <@alignLeft/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('device.no')}" property="device.deviceNo">
	                 <@alignLeft/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('device.name')}" property="device.name">
	                 <@alignLeft/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('device.specification')}" property="device.specification">
	                 <@alignLeft/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('department')}" property="device.department.name">
	                 <@alignLeft/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('applyDatetime')}" property="applyDatetime" format="yyyy-MM-dd">
	                 <@alignCenter/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('manager')}" property="manager.name">
	                 <@alignLeft/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('descardDatetime')}" property="descardDatetime" format="yyyy-MM-dd">
	                 <@alignCenter/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('checkupResult')}" property="checkupResult">
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
	            <#assign confirmMessage = "${action.getText('confirm.invalid')}${action.getText('device.Discard')}?" />
	            <@vsubmit name="'delete'" value="'${action.getText('disabled')}'">
	                <@ww.param name="'onclick'" value="'return validateDelete(confirmDeletes(\"discardIds\", \"${confirmMessage}\"), checkInvalidParms())'"/>
	                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            </@vsubmit>
	            
	            <@vbutton name="print"  class="button" value="${action.getText('print.all')}" onclick="open_discardPdf('')"/>
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