<#include "../../includes/macros.ftl" />
<@inputTable>
<tr>
	<@ww.textfield label="'${action.getText('会议室名称')}'" name="'boardroom.name'" value="'${req.getParameter('boardroom.name')?if_exists}'" cssClass="'underline'"/>
	
	<@select 
		label="${action.getText('预订状态')}" 
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
		<!--开始日期-->
	<@datePickerRanger
			label="${action.getText('开始时间')}"
			name="bookBoardroom.startDate"
			value="${req.getParameter('bookBoardroom.startDate')?if_exists}" 
			cssClass="underline" 
			maxlength="10" 
			required="true"
			flag="true">
	</@datePickerRanger>
		<!--开始日期-->
	<@datePickerRanger
			label="${action.getText('结束时间')}"
			name="bookBoardroom.endDate"
			value="${req.getParameter('bookBoardroom.endDate')?if_exists}" 
			cssClass="underline" 
			maxlength="10" 
			required="true"
			flag="true">
	</@datePickerRanger>
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