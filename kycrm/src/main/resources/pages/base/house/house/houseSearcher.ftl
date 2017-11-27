
<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('房源编码')}'" name="'house.code'" value="'${req.getParameter('house.code')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('大楼名称')}'" name="'building.name'" value="'${req.getParameter('building.name')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('楼层名称')}'" name="'floor.name'" value="'${req.getParameter('floor.name')?if_exists}'" cssClass="'underline'"/>
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('房源名称')}'" name="'house.name'" value="'${req.getParameter('house.name')?if_exists}'" cssClass="'underline'"/>
		<@ww.select 
			label="'${action.getText('房源状态')}'"
			required="false"
			name="'state.id'" 
			value="${req.getParameter('state.id')?if_exists}" 
			listKey="id"
			listValue="name"
		    list="allState"
	    	emptyOption="false" 
	    	disabled="false">
    	</@ww.select>
    	
		<@ww.select 
				label="'${action.getText('房源类别')}'"
				required="false"
				name="'category.id'" 
				value="${req.getParameter('category.id')?if_exists}" 
				listKey="id"
				listValue="name"
				list="allCategory"
				emptyOption="false" 
				disabled="false">
		</@ww.select>
		<script>
			getObjByName("state.id").value="${req.getParameter('state.id')?if_exists}";
			getObjByName("category.id").value="${req.getParameter('category.id')?if_exists}";
    	</script>
	</tr>
</@inputTable>
<script>
function checkInvalidParms(){
	return true;
}
</script>