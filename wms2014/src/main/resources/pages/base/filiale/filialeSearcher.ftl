<@inputTable>
    <tr>
        <@ww.textfield label="'${action.getText('filiale.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('filiale.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/> 
   		<@ww.textfield label="'${action.getText('filiale.leader')}'" name="'leader'" value="'${req.getParameter('leader')?if_exists}'" cssClass="'underline'"/>
    </tr>
    <tr>
      <@eam2008_onlySearchInvalid_checkBox/> 
    </tr>
</@inputTable>
<script language="javascript">
	function checkInvalidParms() {
		return true;
	}
</script>