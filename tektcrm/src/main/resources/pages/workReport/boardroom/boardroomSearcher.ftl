<#include "../../includes/macros.ftl" />
<@inputTable>
<tr>
	<@ww.textfield label="'${action.getText('会议室编码')}'" name="'boardroom.code'" value="'${req.getParameter('boardroom.code')?if_exists}'" cssClass="'underline'"/>
	<@ww.textfield label="'${action.getText('会议室名称')}'" name="'boardroom.name'" value="'${req.getParameter('boardroom.name')?if_exists}'" cssClass="'underline'"/>
	
	<@select 
		label="${action.getText('会议室状态')}" 
		name="state.id" 
		id="state.id" 
		value="${req.getParameter('state.id')?if_exists}"
		listKey="id"
		listValue="name"
		list="allState"
		required="true"
		emptyOption="false" 
		disabled="false">
	</@select>
</tr>
<tr>

</tr>
</@inputTable>
<script>
<#if req.getParameter('state.id')?exists>
	getObjByName('state.id').value = '${req.getParameter('state.id')?if_exists}';
</#if>
	function checkInvalidParms() {
		return true;
	}
</script>