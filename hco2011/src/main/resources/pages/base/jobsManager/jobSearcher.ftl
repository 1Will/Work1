<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('job.code')}'" name="'code'" cssClass="'underline'" value="'${req.getParameter('code')?if_exists}'" />
		<@ww.textfield label="'${action.getText('job.name')}'" name="'name'" cssClass="'underline'" value="'${req.getParameter('name')?if_exists}'"/>
	</tr>
	<tr>
		<@crm_onlySearchInvalid_checkBox/>
	</tr>
</@inputTable>
		  <script language="JavaScript" type="text/JavaScript"> 
		  				function checkInvalidParms(){
					return true;
				}
			</script>