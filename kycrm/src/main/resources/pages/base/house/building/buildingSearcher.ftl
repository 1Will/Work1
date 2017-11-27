
<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('大楼编码')}'" name="'building.code'" value="'${req.getParameter('building.code')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('大楼名称')}'" name="'building.name'" value="'${req.getParameter('building.name')?if_exists}'" cssClass="'underline'"/>
	</tr>
</@inputTable>
<script>
function checkInvalidParms(){
	return true;
}
</script>