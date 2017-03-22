<@inputTable>
    <tr>
        <@ww.textfield label="'${action.getText('codevalue.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
    	<@ww.textfield label="'${action.getText('codeValue.value')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
        <#--
        <@crm_onlySearchInvalid_checkBox/>
        -->
    </tr>
</@inputTable>
<script language="JavaScript" type="text/JavaScript"> 
  function checkInvalidParms() {
    return true;
  }
  </script>