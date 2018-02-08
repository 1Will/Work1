<@inputTable>
    <tr>
        <@ww.textfield label="'${action.getText('toolingType.code')}'" 
        	name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('toolingType.value')}'" 
        	name="'value'" value="'${req.getParameter('value')?if_exists}'" cssClass="'underline'"/>
    </tr>   
    <@eam2008_onlySearchInvalid_checkBox/>
</@inputTable>
    <script language="javascript">
       function checkInvalidParms(){
          return true;
       }
    </script>