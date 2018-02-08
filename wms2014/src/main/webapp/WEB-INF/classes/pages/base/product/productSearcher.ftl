<#--$Id: productSearcher.ftl 11319 2008-03-14 08:25:24Z wzou $ -->
<@inputTable>
   <#-- <@ww.hidden name="'includeValid'" value="true"/>-->
    <tr>
        <@ww.textfield label="'${action.getText('product.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('product.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
        <@eam2008_onlySearchInvalid_checkBox/>     
    </tr>
</@inputTable>
<script language="JavaScript" type="text/JavaScript"> 
  function checkInvalidParms() {
   <#-- if (document.getElementById("includeDisabled").checked) {
      document.getElementById("includeValid").value="";
	  document.getElementById("includeDisabled").value='true';
    }-->
    return true;
  }
 
</script>