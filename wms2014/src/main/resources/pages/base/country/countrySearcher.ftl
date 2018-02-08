<@inputTable>
    <tr>
        <@ww.textfield label="'${action.getText('country.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('country.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
    	<@eam2008_onlySearchInvalid_checkBox/>  
    </tr>
</@inputTable>
<script language="JavaScript" type="text/JavaScript">
	function checkInvalidParms(){
		return true;
	}
</script>