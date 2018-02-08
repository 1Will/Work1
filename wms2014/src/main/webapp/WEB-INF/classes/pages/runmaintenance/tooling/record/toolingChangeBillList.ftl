<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('toolingChangeBillSearch.title')}">
  <@ww.form namespace="'/runmaintenance/tooling/record'" name="'toolingChangeBill'" action="'searchToolingChangeBills'" method="'post'">
  <@ww.token name="searchToolingChangeBillsToken"/>
  	     <#include "toolingChangeBillSearcher.ftl"/>
  	     <@ww.hidden name="'toolingState'" value=""/>
  	     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <@buttonBar>        
        	
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>  
          
           <#if !(action.isReadOnly())>
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/tooling/record/editToolingChangeBill.html?readOnly=${req.getParameter('readOnly')?if_exists}"/> 
           </#if>
         </@buttonBar>   
         <@list title="${action.getText('changeBill.list')}" 
            includeParameters="billNo|billName|readOnly|toolingNo|toolingName|toolingGraphNo|planCompleteTime_start|planCompleteTime_end|actualCompleteTime_start|actualCompleteTime_end|createdDateTime_start|createdDateTime_end|onlyValid|onlyInvalid" 
        	fieldMap="like:billNo|billName|toolingNo|toolingName|toolingGraphNo,date:planCompleteTime_start|planCompleteTime_end|actualCompleteTime_start|actualCompleteTime_end|createdDateTime_start|createdDateTime_end" >
        	<@vlh.checkbox property="id" name="toolingChangeBillIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('tooling.changeBillNo')}" property="billNo" sortable="desc">
           
                <a href="editToolingChangeBill.html?toolingChangeBill.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.billNo}</a>
              
                <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('tooling.changeBillName')}" property="billName" sortable="desc">
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
            <@vcolumn title="${action.getText('changBill.acceptDepartment')}" property="acceptDepartment.name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('changBill.acceptManager')}" property="acceptor.name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('tooling.planCompleteTime')}" property="planCompleteTime" format="yyyy-MM-dd" sortable="desc">
                 <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('tooling.actualCompleteTime')}" property="actualCompleteTime" format="yyyy-MM-dd" sortable="desc">
                 <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('toolingChange.createdTime')}" property="createdDateTime" format="yyyy-MM-dd" sortable="desc">
                 <@alignCenter/>
            </@vcolumn>
            <#if (object.changeBillFlag?string)=='true'>
              <#assign status="${action.getText('changeBill.changed')}"/>
            <#else>
              <#assign status="${action.getText('changeBill.changeing')}"/>
            </#if>
            <@vcolumn title="${action.getText('tooling.changeBillStatus')}">
            ${status}
            <@alignLeft/>
            </@vcolumn>
      
        </@list>  
	    <#if !first>
        <@buttonBar>
        <#--
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('tooling.changeBill')}" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return validateDelete(confirmDeletes(\"toolingChangeBillIds\", \"${confirmMessage}\"), checkInvalidParms())'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            -->
            <#if !(action.isReadOnly())>
              <@eam2008_disabledOrEnabled_button message="${action.getText('tooling.changeBill')}" boxName="toolingChangeBillIds" jsFunctionName="checkInvalidParms()"/>
            </#if>
        </@buttonBar>
        </#if>
	 <script language="JavaScript" type="text/JavaScript"> 
	  function validateDelete(delFun, checkFun) {
         if (delFun) {
         	checkFun;
         	return true;
         }
         return false;
       }
	</script>
  </@ww.form>
</@htmlPage>