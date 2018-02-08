<#include "../../includes/eam2008.ftl" />
<#assign brockenRateTitle = ''/>
<#if toolingDevFlag?exists>
  <#if toolingDevFlag == 'DEVICE'>
    <#assign brockenRateTitle = "${action.getText('deviceBrockenRate.title')}"/>
  <#else>
    <#assign brockenRateTitle = "${action.getText('toolingBrockenRate.title')}"/>
  </#if>
</#if>
<@htmlPage title="${brockenRateTitle}">
<base target="_self">
  <@ww.form namespace="'/runmaintenance/repair/plan'" name="'brockenList'" action="'searbrockens'" method="'post'">
  <@ww.token name="searchChecksToken"/>
  	     <#include "brockenRateSearcher.ftl"/>
  	     <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
         <@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>  
         </@buttonBar>   
         <@list title="" excel=false setupTable=false 
                includeParameters="assetNo|assetName|department.id|brockenRate|devBrockenRate|month_start|month_end|toolingDevFlag" 
        	    fieldMap="like:assetNo|assetName,month:month_start|month_end" >
	       <@vlh.checkbox property="id" name="brokenRateIds">
	         <@vlh.attribute name="width" value="30" />
	       </@vlh.checkbox>
	       <#--
	       <#if toolingDevFlag='DEVICE'>
	       -->
           <@vcolumn title="${action.getText('device.no')}" property="deviceNo">
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('device.name')}" property="deviceName">
             <@alignLeft/>
           </@vcolumn>
            <@vcolumn title="${action.getText('department')}" property="departName">
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('brockenDate')}" property="month" format="yyyy-MM">
           </@vcolumn>
           <#-- <@vcolumn title="${action.getText('usAgeRate')}" property="usAgeRate">
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('usAbleRate')}" property="usAbleRate">
             <@alignLeft/>
           </@vcolumn>-->
          <@vcolumn title="${action.getText('devBrockenRate')}" property="devBrockenRate" format="#,###,##0.000">
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('comment')}" property="comment">
             <@alignLeft/>
           </@vcolumn>
           <#--
           <#else>
           <@vcolumn title="${action.getText('tonngNo')}" property="deviceCard.deviceNo">
             <@alignLeft/>
           </@vcolumn>
            <@vcolumn title="${action.getText('toolingName')}" property="deviceCard.graphNo">
             <@alignLeft/>
           </@vcolumn>
            <@vcolumn title="${action.getText('departName')}" property="departName">
             <@alignLeft/>
           </@vcolumn>
            <@vcolumn title="${action.getText('usAgeRate')}" property="usAgeRate">
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('usAbleRate')}" property="usAbleRate">
             <@alignLeft/>
           </@vcolumn>
          <@vcolumn title="${action.getText('devBrockenRate')}" property="devBrockenRate" sortable="desc">
             <@alignLeft/>
           </@vcolumn>
            <@vcolumn title="${action.getText('brockenDate')}" property="month" format="yyyy-MM">
           </@vcolumn>
           <@vcolumn title="${action.getText('comment')}" property="comment">
             <@alignLeft/>
           </@vcolumn>
           </#if>
           -->
         </@list>  
	     <#if !first>
	       <@buttonBar>
	      	<@vsubmit name="'choose'" value="'${action.getText('choose')}'" onclick="'return confirmSelects(\"brokenRateIds\");'">
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
		 var brokenRateIds= "";
		 if (selector.length) {
		   for (i = 0; i < selector.length; i++) {
		     if (selector[i].checked) {
			   brokenRateIds += selector[i].value + ",";
			 }
		   }
		 }
		 returnDialog(brokenRateIds,false);
	   }
        
  </script>
</@htmlPage>