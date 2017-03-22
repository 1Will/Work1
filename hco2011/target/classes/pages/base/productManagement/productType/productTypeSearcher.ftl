<#include "../../../includes/hco2011.ftl" />
<@inputTable>
	<@ww.hidden name="'nonParentPT'" value="${req.getParameter('nonParentPT')?if_exists}"/>
	<tr>
		<@ww.textfield label="'${action.getText('productType.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('productType.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
	    <#assign showNone=""/>
        <#assign showNone="${req.getParameter('nonParentPT')?if_exists}"/>
	       <@ww.select label="'${action.getText('productType.parentType')}'" 
	                   name="'parentPT.id'" 
	                   value="'${req.getParameter('parentPT.id')?if_exists}'"
	                   listKey="id" listValue="name"
	                   list="allParentPT" emptyOption="false" 
	                   disabled="false" required="false">
	       </@ww.select>
	</tr>
	<tr>
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
 	   <#if showNone != 'true'>
			  var selector = document.all("parentPT.id");
			  groups = selector.options.length;
			  for (i=0; i<groups; i++) {
			    <#if req.getParameter('parentPT.id')?exists>
				    if (selector.options[i].value == "${req.getParameter('parentPT.id')?if_exists}") {
				      selector.options[i].selected="true";
				    }
			    </#if>
			  }
	   <#else>
	    	  getObjByName('parentPT.id').value = 0 ;
			  var selector = document.all("parentPT.id");
			  groups = selector.options.length;
			  for (i=0; i<groups; i++) {
			    <#if req.getParameter('parentPT.id')?exists>
				    if (selector.options[i].value == "${req.getParameter('parentPT.id')?if_exists}") {
				      selector.options[i].selected="true";
				    }
			    </#if>
			  } 
	   </#if> 
  
	function checkInvalidParms() {
	    document.forms[0].elements["nonParentPT"].value = '';
		//上级组选"所有"
		if (-1 == document.forms[0].elements["parentPT.id"].value) {
		  document.forms[0].elements["parentPT.id"].value = '';
		}else if (0 == document.forms[0].elements["parentPT.id"].value) {  //上级组选"无"
		  document.forms[0].elements["nonParentPT"].value = 'true';
		  document.forms[0].elements["parentPT.id"].value = 'false';
		}
		return true;
	}
</script>
