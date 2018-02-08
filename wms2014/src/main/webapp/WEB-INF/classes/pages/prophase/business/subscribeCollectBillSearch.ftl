<@inputTable>
    <tr>
         <@ww.textfield label="'汇总单编码'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
          <@ww.textfield label="'汇总单名称'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
          </tr>
          <tr>
            <@ww.select label="'${action.getText('部门')}'" required="false" name="'collectDept.id'" 
		    		value="'${req.getParameter('collectDept.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		</@ww.select> 
         
       	<@ww.textfield label="'汇总人'" name="'collectPerson.name'" value="'${req.getParameter('collectPerson.name')?if_exists}'" cssClass="'underline'"/>
	</tr> 
    <tr>
       
                       
          <@pp.dateRanger label="'${action.getText('汇总日期')}'" name="'collectDate'" 
		       value="'${req.getParameter('collectDate_start')?if_exists}|${req.getParameter('collectDate_end')?if_exists}'"
		cssClass="'underline'" dateFormat="date"/>
                       
        	<@ww.select label="'${action.getText('状态')}'" required="false" name="'billStatus'" 
		    		value="'${billStatus?if_exists}'" listKey="value" listValue="label"
		            list="status" emptyOption="false" disabled="false">
		</@ww.select>
         
         </tr>

</@inputTable>
<script language="javascript" type="text/JavaScript">
	var selector = document.all("collectDept.id");
    groups = selector.options.length;
    for (i=0; i<groups; i++) {
    <#if req.getParameter('collectDept.id')?exists>
    if (selector.options[i].value == "${req.getParameter('collectDept.id')?if_exists}") {
      selector.options[i].selected="true";
       }
    </#if>
    <#if first>
    <#if loginUser.department?exists>
      document.getElementById("collectDept.id").value = #{loginUser.department.id};
    </#if>
    <#if loginUser?exists>
    	 document.getElementById("collectPerson.name").value ="${loginUser.name}";
    </#if>
  	</#if>
	}
    
  	var statusSelector = document.all("billStatus");
  	statusGroups = statusSelector.options.length;
  	for (i=0; i<statusGroups; i++) {
    <#if req.getParameter('billStatus')?exists>
    	if (statusSelector.options[i].value == "${req.getParameter('billStatus')?if_exists}") {
      	statusSelector.options[i].selected="true";
    	}
    </#if>
    
    
    }
    
    function checkInvalidParms(){
     if (document.getElementById("collectDept.id").value == -1) {
  	    document.getElementById("collectDept.id").value = '';
      } 
        if (document.getElementById("billStatus").value == -1) {
	  	document.getElementById("billStatus").value = '';
	  }
	  
	  if(document.forms[0].elements["collectDate_start"].value!=""){
          if(!validateTime(document.forms[0].elements["collectDate_start"].value)){
               alert("${action.getText('subscribe.start_EndTimeMistake')}");
               return false;
          }
      }
      if(document.forms[0].elements["collectDate_end"].value!=""){
         if(!validateTime(document.forms[0].elements["collectDate_end"].value)){
               alert("${action.getText('subscribe.start_EndTimeMistake')}");
               return false;
          }
      }
   // alert(document.getElementById("billStatus").value);
      return true;
    }
</script>