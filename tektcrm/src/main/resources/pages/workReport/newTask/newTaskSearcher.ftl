<#include "../../includes/macros.ftl" />
<@inputTable>
<tr>
	<@ww.textfield label="'${action.getText('日程编码')}'" name="'newTask.code'" value="'${req.getParameter('newTask.code')?if_exists}'" cssClass="'underline'"/>
	<@ww.textfield label="'${action.getText('日程名称')}'" name="'newTask.name'" value="'${req.getParameter('newTask.name')?if_exists}'" cssClass="'underline'"/>
	
	<@select 
		label="${action.getText('日程状态')}" 
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