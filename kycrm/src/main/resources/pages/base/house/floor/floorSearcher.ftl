
<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('楼层编码')}'" name="'floor.code'" value="'${req.getParameter('floor.code')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('大楼名称')}'" name="'building.name'" value="'${req.getParameter('building.name')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('楼层名称')}'" name="'floor.name'" value="'${req.getParameter('floor.name')?if_exists}'" cssClass="'underline'"/>
	</tr>
</@inputTable>
<script>
function checkInvalidParms(){
	return true;
}
</script>