<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('cerfitication.title1')}">
 <base target="_self">
	<@ww.form name="'newProduct'" action="'saveSupplierCerfitication'" method="'post'">
		<@ww.token name="saveSupplierCerfitication"/>
		<#if supplier.id?exists>
		<@ww.hidden name="'supplier.id'" value="'#{supplier.id}'"/>
		</#if>
		<#if cerfitication.id?exists>
	 	<@ww.hidden name="'cerfitication.id'" value="'#{cerfitication.id}'"/>
	 	</#if>
		<@inputTable>
			<tr>
			    <@ww.textfield readonly="false" label="'${action.getText('cerfications.name')}'" name="'cerfitication.name'" value="'${cerfitication.name?if_exists}'"  cssClass="'underline'" required="true"/>
			   
			</tr>
			
		</@inputTable>
		<@buttonBar>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
		    <@vbutton class="button" value="${action.getText('close')}" onclick="window.close();"/>
		</@buttonBar>
	</@ww.form>
	</@htmlPage>
	<script language="JavaScript" type="text/JavaScript"> 
	  function validate(){
	  if(document.getElementById("cerfitication.name").value==''){
	        alert('${action.getText('cerfitication.name.not.null')}');
	        return false;
	    }else{
	      if(!isValidLength(document.forms[0], "cerfitication.name", null, 50)){
				alert("${action.getText('cerfitication.name.MaxLength')}");
				return  false;
			   }   
	    }
	    return true;
	  }
	</script>