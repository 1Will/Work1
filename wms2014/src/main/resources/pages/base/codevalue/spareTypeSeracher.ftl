<@inputTable>	
	<tr>
		<@ww.textfield label="'${action.getText('spareType.No')}'" name="'spareTypeNo'" value="'${req.getParameter('spareTypeNo')?if_exists}'" cssClass="'underline'" />	 
		<@ww.textfield label="'${action.getText('spareType.name')}'" name="'spareTypeName'" value="'${req.getParameter('spareTypeName')?if_exists}'" cssClass="'underline'" />	
	</tr>
	<tr>
	   	<@ww.select label="'${action.getText('SpareSort')}'" required="false" name="'toolingDevFlag'" 
		    		value="'${toolingDevFlag?if_exists}'" listKey="value" listValue="label"
		            list="spareSort" emptyOption="false" disabled="false">
		</@ww.select>
		<@eam2008_onlySearchInvalid_checkBox/>
	</tr>

</@inputTable>	
<script language="javascript" type="text/JavaScript">
	var selector = document.all("toolingDevFlag");
  	groups = selector.options.length;
  	for (i=0; i<groups; i++) {
    <#if req.getParameter('toolingDevFlag')?exists>
    	if (selector.options[i].value == "${req.getParameter('toolingDevFlag')?if_exists}") {
      	selector.options[i].selected="true";
    	}
    </#if>
    }
    
    function checkInvalidParms(){
	  if (getObjByNameRe("toolingDevFlag").value == -1) {
	  	getObjByNameRe("toolingDevFlag").value = '';
	  } 
	} 
</script>