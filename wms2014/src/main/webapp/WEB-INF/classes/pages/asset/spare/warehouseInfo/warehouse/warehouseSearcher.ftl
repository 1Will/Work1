<@inputTable>
 		<tr>
 			<@ww.textfield label="'${action.getText('warehouse.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'" required="false"/>
 			<@ww.textfield label="'${action.getText('warehouse.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'" required="false"/>
 			<@ww.textfield label="'${action.getText('warehouse.user')}'" name="'user'" value="'${req.getParameter('user')?if_exists}'" cssClass="'underline'" required="false"/>
		</tr>
		<tr>
			<@eam2008_onlySearchInvalid_checkBox/>
		</tr>
</@inputTable>
<script>
	function checkInvalidParms(){
		return true;
	}
</script>