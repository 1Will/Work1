<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('deviceeaseyBrockenRate.title')}">
<base target="_self">
  <@ww.form namespace="'/runmaintenance/repair/plan'" name="'brockenList'" action="'seardamagedSpart'" method="'post'">
  <@ww.token name="searchChecksToken"/>
  	     <#include "preRepairDeviceSearcher.ftl"/>
  	     <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
         <@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>  
         </@buttonBar>   
         <@list title="" excel=false setupTable=false 
                includeParameters="assetNo|assetName|department.id|category.id|device.emphasis|device.standard|device.managementLevel|cardCreatedTime" 
        	    fieldMap="like:assetNo|assetName|,date:cardCreatedTime_start|cardCreatedTime_end" >
	       <@vlh.checkbox property="id" name="damagedSpartIds">
	         <@vlh.attribute name="width" value="30" />
	       </@vlh.checkbox>
           <@vcolumn title="${action.getText('devicenumber')}" property="asset.deviceNo">
             <@alignLeft/>
           </@vcolumn>
            <@vcolumn title="${action.getText('devicename')}" property="asset.name">
             <@alignLeft/>
           </@vcolumn>
            <@vcolumn title="${action.getText('spareNo')}" property="spare.spareNo">
             <@alignLeft/>
           </@vcolumn>
            <@vcolumn title="${action.getText('spareName')}" property="spare.name">
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('departmentName')}" property="asset.department.name">
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('device.category')}"  property="asset.deviceType.name">
    	<@alignLeft />
      </@vcolumn>
      <#if (object.asset.emphasis?string)=='true'>
        <#assign emphasis="${action.getText('YES')}"/>
      <#else>
        <#assign emphasis="${action.getText('NO')}"/>
      </#if>
      <@vcolumn title="${action.getText('device.emphasis')}">
        ${emphasis}
        <@alignLeft/>
      </@vcolumn>
      <#if (object.asset.standard?string)=='true'>
          <#assign standard="${action.getText('YES')}"/>
      <#else>
          <#assign standard="${action.getText('NO')}"/>
      </#if>
      <@vcolumn title="${action.getText('device.standard')}">
        ${standard}
        <@alignLeft/>
      </@vcolumn>
      <#assign level=''/>
      <#if object.asset.managementLevel?exists>
        <#if (object.asset.managementLevel)=='MAJOR_DEVICES'>
          <#assign level="${action.getText('device.majorDevice')}"/>
        <#elseif (object.asset.managementLevel)=='GENERAL_DEVICES'>
          <#assign level="${action.getText('device.generalDevice')}"/>
        <#elseif (object.asset.managementLevel)=='IMPORTANT_DEVICES'>
          <#assign level="${action.getText('device.importantDevice')}"/>
        </#if>
      </#if>
      <@vcolumn title="${action.getText('device.managementLevel')}">
    	${level}
    	<@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('device.cardCreatedTime')}" property="asset.cardCreatedTime" format="${action.getText('dateFormat')}"/>    
      <@vcolumn title="${action.getText('device.maxRunHour')}" property="amount">
        <@alignRight/>
      </@vcolumn>
      <@vcolumn title="${action.getText('device.preRepairTime')}" property="useTime">
        <@alignRight/>
      </@vcolumn>
         </@list>  
	     <#if !first>
	       <@buttonBar>
	      	<@vsubmit name="'choose'" value="'${action.getText('choose')}'" onclick="'return confirmSelects(\"damagedSpartIds\");'">
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
		 var damagedSpartIds= "";
		 if (selector.length) {
		   for (i = 0; i < selector.length; i++) {
		     if (selector[i].checked) {
			   damagedSpartIds += selector[i].value + ",";
			 }
		   }
		 }
		 returnDialog(damagedSpartIds,false);
	   }
        
  </script>
</@htmlPage>