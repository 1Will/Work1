<#include "../../includes/hco2011.ftl" />
<@inputTable>
	<@ww.hidden name="'nonParentDept'" value="${req.getParameter('nonParentDept')?if_exists}"/>
    <tr>
        <@ww.textfield label="'${action.getText('deparment.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('department.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
      	<#assign showNone=""/>
        <#assign showNone="${req.getParameter('nonParentDept')?if_exists}"/>
        <@ww.select label="'${action.getText('parent.department')}'" 
			required="false" 
			name="'parent.department'"  
			listKey="id" 
			listValue="name"
            list="parentDepts" 
            emptyOption="false" 
            disabled="false" 
            required="false" 
            value="'${req.getParameter('parent.department')?if_exists}'">
  		</@ww.select>
    </tr>
    <tr>
    	<@ww.select label="'${action.getText('department.inst')}'" 
			required="false" 
			name="'institution.id'"  
			listKey="id" 
			listValue="name"
            list="parentInsts" 
            emptyOption="false" 
            disabled="false" 
            required="false" 
            value="'${req.getParameter('institution.id')?if_exists}'">
  		</@ww.select>

    </tr>
</@inputTable>
<script type="text/javascript">
	<#if showNone != 'true'>
		  var selector = document.all("parent.department");
		  groups = selector.options.length;
		  for (i=0; i<groups; i++) {
		    <#if req.getParameter('parent.department')?exists>
		    if (selector.options[i].value == "${req.getParameter('parent.department')?if_exists}") {
		      selector.options[i].selected="true";
		    }
		    </#if>
		  }
    <#else>
    	  getObjByName('parent.department').value = 0 ;
		  var selector = document.all("parent.department");
		  groups = selector.options.length;
		  for (i=0; i<groups; i++) {
		    <#if req.getParameter('parent.department')?exists>
		    if (selector.options[i].value == "${req.getParameter('parent.department')?if_exists}") {
		      selector.options[i].selected="true";
		    }
		    </#if>
		  }
    </#if>
    	  var instSele = document.all("institution.id");
		  count = instSele.options.length;
		  for (i=0; i<count; i++) {
		  <#if req.getParameter('institution.id')?exists>
		    if (instSele.options[i].value == "${req.getParameter('institution.id')?if_exists}") {
		      instSele.options[i].selected="true";
		    }
		    </#if>
		  }

  function checkInvalidParms() {
  	if (-1 == document.forms[0].elements["institution.id"].value) {
	  document.forms[0].elements["institution.id"].value = '';
	}
    document.forms[0].elements["nonParentDept"].value = '';
	//上级组选"所有"
	if (-1 == document.forms[0].elements["parent.department"].value) {
	  document.forms[0].elements["parent.department"].value = '';
	} else if (0 == document.forms[0].elements["parent.department"].value) {  //上级组选"无"
	  document.forms[0].elements["nonParentDept"].value = 'true';
	  document.forms[0].elements["parent.department"].value = 'false';
	}

	return true;
  }
</script>