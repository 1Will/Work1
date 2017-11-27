
<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('电表编码')}'" name="'electricMeter.code'" value="'${req.getParameter('electricMeter.code')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('电表名称')}'" name="'electricMeter.name'" value="'${req.getParameter('electricMeter.name')?if_exists}'" cssClass="'underline'"/>
	</tr>
</@inputTable>
<script>
function checkInvalidParms(){
	return true;
}
</script>