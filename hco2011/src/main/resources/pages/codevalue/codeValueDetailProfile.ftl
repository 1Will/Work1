<#--$Id:$ -->
<#include "../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('codeValueDetail.title')}">
 <base target="_self">
    <@ww.form name="'codeValueDetail'" action="'saveCodeValueDetail'" method="'post'">
        <@ww.token name="saveCodeValueDetailToken"/>
        <@inputTable>
        	<#if type.id?exists>
                <@ww.hidden name="'type.id'" value="#{type.id}"/>
            </#if>
            <#if codeValueDetail.id?exists>
                <@ww.hidden name="'codeValueDetail.id'" value="#{codeValueDetail.id}"/>
            </#if>
            <tr>
            	<@ww.textfield label="'${action.getText('codeValueDetail.code')}'" name="'codeValueDetail.code'" value="'${codeValueDetail.code?if_exists}'" cssClass="'underline'" readonly="true" disabled="true"/>
            	<@ww.textfield label="'${action.getText('codeValueDetail.value')}'" name="'codeValueDetail.name'" value="'${codeValueDetail.name?if_exists}'" cssClass="'underline'" required="true"/>
            </tr>
        </@inputTable>
        <@buttonBar>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate()'"/>
            <input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
        </@buttonBar>
    </@ww.form>
    <script language="JavaScript" type="text/JavaScript"> 
		 function validate() {
		 /*
		   *验证基础编码名称是否为空，以及长度是否溢出
		  */
		  if ('' == document.forms["codeValueDetail"].elements["codeValueDetail.name"].value) {       
		    alert("${action.getText('codeValueDetail.value.requiredstring')}");
		    getObjByName('codeValueDetail.name').focus();
		    return false;
		  } else {
		    if (!isValidLengthValue(document.forms["codeValueDetail"].elements["codeValueDetail.name"].value,0,20)) {
		    	alert("${action.getText('codeValueDetail.value.maxLength')}");
		    	getObjByName('codeValueDetail.name').value="";
		    	getObjByName('codeValueDetail.name').focus();
		      	return false;
		    }
		  }
		  
	  }
	</script> 
</@htmlPage>