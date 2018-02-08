<#--库位查询条件-->
<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('code')}'" 
					   name="'locationCode'" 
					   value="'${req.getParameter('locationCode')?if_exists}'" 
					   cssClass="'underline'"
					   required="false"
					   />  
	    <@ww.select label="'${action.getText('status')}'" 
				required="false" 
				name="'locationStatus'" 
				value="'${status?if_exists}'" 
				listKey="value" 
				listValue="label"
				list="locationStatus" 
				emptyOption="false" 
				disabled="false">
		</@ww.select>
	</tr>
	<tr>
		<@eam2008_onlySearchInvalid_checkBox/>
	</tr>
</@inputTable>
<script language="javascript" type="text/JavaScript">
 var locationStatusSelector = document.all("locationStatus");
  	locationStatusGroups = locationStatusSelector.options.length;
  	for (i=0; i<locationStatusGroups; i++) {
    <#if req.getParameter('locationStatus')?exists>
    	if (locationStatusSelector.options[i].value == "${req.getParameter('locationStatus')?if_exists}") {
      	    locationStatusSelector.options[i].selected="true";
    	}
    </#if>
    }
  function checkInvalidParms(){
    if (getObjByNameRe("locationStatus").value == -1) {
	  getObjByNameRe("locationStatus").value = '';
	}
      return true;
  }
 </script>