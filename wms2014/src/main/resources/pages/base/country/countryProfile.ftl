<#--$Id: productProfile.ftl 11310 2008-03-13 05:58:54Z wzou $ -->
<#include "../../includes/macros.ftl" />
<@htmlPage title="${action.getText('countryEdit.title')}">
    <@ww.form namespace="'/base/country'" name="'country'" action="'saveCountry'" method="'post'" validate="true">
        <@ww.token name="saveCountryToken"/>
        <@inputTable>
            <#if country.id?exists>
                <@ww.hidden name="'country.id'" value="#{country.id}"/>
            </#if>
            <@ww.hidden name="'country.version'" value="#{country.version?if_exists}"/>
            <@ww.hidden name="'country.codeHidden'" value="'${country.code?if_exists}'"/>
            <@ww.hidden name="'country.nameHidden'" value="'${country.name?if_exists}'" />
            <tr>
                <@ww.textfield label="'${action.getText('country.code')}'" name="'country.code'" value="'${country.code?if_exists}'" cssClass="'underline'" required="true"/>
                <@ww.textfield label="'${action.getText('country.name')}'" name="'country.name'" value="'${country.name?if_exists}'"  size="32"  cssClass="'underline'" required="true"/>
            </tr>
        </@inputTable>
        <@buttonBar>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate()'"/>
            <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/base/country/listCountries.html"/>
        </@buttonBar>
    </@ww.form>
    <script language="JavaScript" type="text/JavaScript"> 
	 function validate() {
		 /*
		   *验证国家管理编码是否为空，以及长度是否溢出
		  */
		  if ('' == document.forms["country"].elements["country.code"].value) {       
		    alert("${action.getText('country.code.not.null')}");
		    return false;
		  } else {
		    if (!isValidLengthValue(document.forms["country"].elements["country.code"].value,0,10)) {
		      alert("${action.getText('country.code.maxLength')}");
		      return false;
		    }
		  }
		  
		  /*
		   *验证国家管理名称是否为空，以及长度是否溢出
		  */
		  if ('' == document.forms["country"].elements["country.name"].value) {        
		    alert("${action.getText('country.name.not.null')}");
		    return false;
		  } else {
		    if (!isValidLengthValue(document.forms["country"].elements["country.name"].value,0,10)) {
		      alert("${action.getText('country.name.maxLength')}");
		      return false;
		    }
		  }
	  }
	</script> 
</@htmlPage>