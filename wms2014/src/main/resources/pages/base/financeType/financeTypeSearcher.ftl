<@inputTable>
    <tr>
        <@ww.textfield label="'${action.getText('financeType.code')}'" 
        	name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('financeType.name')}'" 
        	name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
    </tr>   
    <@eam2008_onlySearchInvalid_checkBox/>
</@inputTable>
    <script language="javascript">
       function checkInvalidParms(){
          return true;
       }
    </script>