<@inputTable>	
	<tr>
		<@ww.textfield label="'${action.getText('subscribe.billNo')}'" name="'billNo'" value="'${req.getParameter('billNo')?if_exists}'" cssClass="'underline'" />	 
		<@ww.textfield label="'${action.getText('subscribe.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'" />	
	</tr>
	<tr>
	    <@ww.select label="'${action.getText('subscribes.department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		</@ww.select> 
		<@ww.textfield label="'${action.getText('buying.Person')}'" name="'buyingPerson.name'" value="'${req.getParameter('buyingPerson.name')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
	    <@pp.dateRanger label="'${action.getText('subscribeDate')}'" name="'subscribeDate'" 
		       value="'${req.getParameter('subscribeDate_start')?if_exists}|${req.getParameter('subscribeDate_end')?if_exists}'"
		cssClass="'underline'" dateFormat="date"/>
		 
		<@ww.select label="'${action.getText('subscribes.typeStatus')}'" required="false" name="'typeStatus'" 
		    		value="'${typeStatus?if_exists}'" listKey="value" listValue="label"
		            list="subTypeStatus" emptyOption="false" disabled="false">
		</@ww.select>
		
	</tr>
	<tr>
 
		<@ww.select label="'${action.getText('单据类型')}'" 
	                   name="'detailKind.id'" 
    			       listKey="id" 
    			       listValue="value"
                       list="allDetailKind" 
                       emptyOption="true" 
                       disabled="false"
                       value="'${req.getParameter('detailKind.id')?if_exists}'" />
                       
        <@ww.select label="'${action.getText('subscribes.status')}'" 
             required="false" 
             name="'status'" 
    		 value="'${status?if_exists}'" 
    		 listKey="value" 
    		 listValue="label"
             list="subStatus"
            emptyOption="false" 
            disabled="false">
            
		</@ww.select>
                       
                  
	 </tr>
	 <tr>

		<@eam2008_onlySearchInvalid_checkBox/>
	</tr>
</@inputTable>	
<script language="javascript" type="text/JavaScript">
	var selector = document.all("department.id");
    groups = selector.options.length;
    for (i=0; i<groups; i++) {
    <#if req.getParameter('department.id')?exists>
    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      selector.options[i].selected="true";
       }
    </#if>
    <#if first>
    <#if loginUser.department?exists>
      document.getElementById("department.id").value = #{loginUser.department.id};
    </#if>
  	</#if>
	}
	
  	var typeStatusSelector = document.all("typeStatus");
  	typeStatusGroups = typeStatusSelector.options.length;
  	for (i=0; i<typeStatusGroups; i++) {
    <#if req.getParameter('typeStatus')?exists>
    	if (typeStatusSelector.options[i].value == "${req.getParameter('typeStatus')?if_exists}") {
      	typeStatusSelector.options[i].selected="true";
    	}
    </#if>
    }
    
  	var statusSelector = document.all("status");
  	statusGroups = statusSelector.options.length;
  	for (i=0; i<statusGroups; i++) {
    <#if req.getParameter('status')?exists>
    	if (statusSelector.options[i].value == "${req.getParameter('status')?if_exists}") {
      	statusSelector.options[i].selected="true";
      	
    	}
    </#if>
    }
    
    
    var detailKindSelector = document.all("detailKind.id");
  	var length = detailKindSelector.options.length;
  	detailKindSelector.options[0].value=-1;
  	detailKindSelector.options[0].text="所有";
  	for (i=0; i<length; i++) {
    <#if req.getParameter('detailKind.id')?exists>
    	if (detailKindSelector.options[i].value == "${req.getParameter('detailKind.id')?if_exists}") {
      	detailKindSelector.options[i].selected="true";
      	
    	}
    </#if>
    }
    
    
    
  function checkInvalidParms(){
  <#--
      var startsubscribeDate = document.getElementById("subscribeDate_start").value;
      var endsubscribeDate = document.getElementById("subscribeDate_end").value;
      var strstartsubscribeDate = new String(startsubscribeDate);
      var strendsubscribeDate = new String(endsubscribeDate);
      if (strstartsubscribeDate!='') {
	    if (!isDate("subscribeDate_start")) {
	     	alert("${action.getText('subscribeDate.start')}" + "," + "${action.getText('devicedateFormate.error')}");
	        return false;
	       }
	  }
	  if (strendsubscribeDate!='') {
	    if (!isDate("subscribeDate_end")) {
        	alert("${action.getText('subscribeDate.end')}" + "," + "${action.getText('devicedateFormate.error')}");
        	return false;
	        }
	  }
	  if ((strstartsubscribeDate!='' && strendsubscribeDate!='')) {
    	if (strstartsubscribeDate > strendsubscribeDate) {
    		alert("${action.getText('subscribe.order.error')}");
    		return false;
    	}
	  }
-->	    
   	  if (document.getElementById("department.id").value == -1) {
  	    document.getElementById("department.id").value = '';
      } 
	  if (document.getElementById("typeStatus").value == -1) {
	  	document.getElementById("typeStatus").value = '';
	  } 
	  if (document.getElementById("status").value == -1) {
	  	document.getElementById("status").value = '';
	  }
	  
	  if($("detailKind.id").value == -1){
	     $("detailKind.id").value ="";
	  }
	  if(document.forms[0].elements["subscribeDate_start"].value!=""){
          if(!validateTime(document.forms[0].elements["subscribeDate_start"].value)){
               alert("${action.getText('subscribeCollectBill.start_EndTimeMistake')}");
               return false;
          }
      }
      if(document.forms[0].elements["subscribeDate_end"].value!=""){
         if(!validateTime(document.forms[0].elements["subscribeDate_start"].value)){
               alert("${action.getText('subscribeCollectBill.start_EndTimeMistake')}");
               return false;
          }
      }
      return true;  
  }    
 </script>