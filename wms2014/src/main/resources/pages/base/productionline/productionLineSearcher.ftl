<@inputTable>
    <tr>
        <@ww.textfield label="'${action.getText('productionLine.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('productionLine.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
    	<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		    </@ww.select>
    </tr>
    <tr>
        <#--<@ww.checkbox label="'${action.getText('excludeDisabled')}'" name="'exclude_disabled'" value="'true'" fieldValue="'true'"/>-->
        <@eam2008_onlySearchInvalid_checkBox/>
    </tr>
</@inputTable>
<script language="javascript">
  selector = document.all("department.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('department.id')?exists>
    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
	function checkInvalidParms() {
		if (getObjByNameRe("department.id").value == -1) {
		  getObjByNameRe("department.id").value = '';
		}
		return true;
	}
</script>