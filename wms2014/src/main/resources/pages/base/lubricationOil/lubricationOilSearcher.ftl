<@inputTable>
    <tr>
        <@ww.textfield label="'${action.getText('lubricationOil.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('lubricationOil.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
    </tr>
    <tr>
        <@ww.select label="'${action.getText('lubricationOil.category')}'" required="false" name="'category.code'" 
		    		value="'${req.getParameter('category.code')?if_exists}'" listKey="code" listValue="value"
		            list="categorys" emptyOption="false" disabled="false">
		</@ww.select>
    	<@eam2008_onlySearchInvalid_checkBox/>
    </tr>
</@inputTable>
<script language="javascript" type="text/JavaScript">
  function checkInvalidParms() {
    if (-1 == getObjByNameRe("category.code").value) {
      getObjByNameRe("category.code").value = '';
    }
  }
</script>