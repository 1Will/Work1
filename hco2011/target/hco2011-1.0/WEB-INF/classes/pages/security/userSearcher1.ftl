<@inputTable>
            <tr>
                <@ww.textfield label="'${action.getText('user.loginName')}'" name="'loginName'" value="'${req.getParameter('loginName')?if_exists}'" cssClass="'underline'"/>
                <@ww.textfield label="'${action.getText('user.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
            </tr>
            <tr>
               <@ww.textfield label="'${action.getText('telNumber')}'" name="'telphoneNumber'" value="'${req.getParameter('telphoneNumber')?if_exists}'" cssClass="'underline'"/>
               <@ww.select label="'${action.getText('department')}'" 
                           name="'department.id'" 
                           value="'${req.getParameter('department.id')?if_exists}'"
                           listKey="id" listValue="name"
                           list="departments" emptyOption="false" 
                           disabled="false" required="false">
               </@ww.select>
            </tr>
            <#--<tr>
                <@ww.checkbox label="'${action.getText('excludeDisabled')}'" name="'exclude_disabled'" value="'true'" fieldValue="'true'"/>
            </tr>-->
</@inputTable>
<script language="javascript" type="text/JavaScript">
  var selector = document.all("department.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('department.id')?exists>
    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
  function checkInvalidParms() {
    if (-1 == document.getElementById("department.id").value) {
		document.getElementById("department.id").value = '';
    }
    return true;
  }
</script>