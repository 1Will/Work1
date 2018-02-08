<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('toolingDemarcatePlanSearch.title')}">
  <@ww.form namespace="'/runmaintenance/tooling/record'" name="'toolingDemarcatePlan'" action="'searchToolingDemarcatePlans'" method="'post'">
  <@ww.token name="searchToolingDemarcatePlansToken"/>
  	     <#include "toolingDemarcatePlanSearcher.ftl"/>
         <@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>  
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/tooling/record/editToolingDemarcatePlan.html"/> 
         </@buttonBar>   
         <@list title="${action.getText('ToolingDemarcatePlan.list')}" 
            includeParameters="planNo|planName|demarcate.status|department.id|planStartTime_start|planStartTime_end" 
        	fieldMap="like:planNo|planName|,date:planStartTime_start|planStartTime_end" >
        	<@vlh.checkbox property="id" name="demarcatePlanIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('demarcate.planNo')}" property="planNo" sortable="desc">
                <a href="editToolingDemarcatePlan.html?demarcatePlan.id=#{object.id}">${object.planNo}</a>
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('demarcate.planName')}" property="planName" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('department')}" property="department.name">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('demarcate.planStartTime')}" property="planStartTime" format="yyyy-MM-dd">
                 <@alignCenter/>
            </@vcolumn>
            <#assign status = ''/>
            <#if object.planStatus?exists>
              <#if object.planStatus=='NONIMPLEMENT'>
                <#assign status = "${action.getText('plan.nonimplement')}"/>
              <#elseif object.planStatus=='IMPLEMENTING'>
                <#assign status = "${action.getText('plan.implementing')}"/>
              <#elseif object.planStatus=='IMPLEMENTED'>
                <#assign status = "${action.getText('plan.implemented')}"/>
              </#if>
            </#if>
            <@vcolumn title="${action.getText('demarcate.status')}">
            ${status}
            <@alignLeft/>
            </@vcolumn>
        </@list>  
	    <#if !first>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('tooling.demarcatePlan')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return validateDelete(confirmDeletes(\"demarcatePlanIds\", \"${confirmMessage}\"), checkInvalidParms())'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
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