<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('maintenanceRules.title')}">
 <base target="_self" />
	 <@ww.form name="'maintenanceRule'" action="'saveMaintenanceRules'" method="'post'" validate="true">
	 	  <@ww.token name="saveMaintenanceRulesToken"/>
	 	  <@ww.hidden name="'device.id'" value="'#{device.id?if_exists}'"/>
	 	  <#if maintenanceRule.id?exists>
	 	      <@ww.hidden name="'maintenanceRule.id'" value="'#{maintenanceRule.id?if_exists}'"/>
	 	  <#else>
	 	      <@ww.hidden name="'maintenanceRule.id'" value=""/>
	 	  </#if>
	 	  <@inputTable>
	 	  	<tr>
	 	  		<@ww.select label="'${action.getText('maintenanceRules.maintenanceType')}'" required="false" name="'maintenanceType.id'" 
    			            listKey="id" listValue="value" list="maintenanceTypes" 
    			            emptyOption="false" disabled="false" required="fasle">
    	        </@ww.select>
    	    </tr>
    	    <tr>    
	 	  		<@ww.textfield label="'${action.getText('maintenanceRules.position')}'" name="'maintenanceRule.part'" value="'${maintenanceRule.part?if_exists}'" cssClass="'underline'" required="true"/>
	 	  		<@ww.textfield label="'${action.getText('maintenanceRules.method')}'" name="'maintenanceRule.method'" value="'${maintenanceRule.method?if_exists}'" cssClass="'underline'"  required="true"/>
	 	  	</tr>
	 	  	<tr>
	 	  	    <@ww.textarea  label="'${action.getText('maintenanceRules.appeal')}'" 
	        	         name="'maintenanceRule.appeal'" 
	        	         value="'${maintenanceRule.appeal?if_exists}'"  
	        	         rows="5" cols="50" cssClass="'underline'" />
	 	  	</tr>
	 	  </@inputTable>
	 	  <@buttonBar>
	        	<@vsubmit value="'${action.getText('save')}'" onclick="'return storeValidate()'" />
	        	<input type="button" value="${action.getText('close')}" class="button" onclick="window.close();"
	     </@buttonBar>
	 </@ww.form>
	 
	 <script>
	 window.onload=function(){
	     <#if maintenanceRule.maintenanceType?exists>
	         selector = document.all("maintenanceType.id");
	         groups=selector.options.length;
	         for (var i=0; i<groups; i++){
	            if (selector.options[i].value=="${maintenanceRule.maintenanceType.id?if_exists}"){
	               selector.options[i].selected="true";
	            }
         	} 
         </#if>
	 }
     function storeValidate(){
	   /*
	    * 验证保养部位
	   */
       if ('' == document.forms[0].elements["maintenanceRule.part"].value) {
         alert("${action.getText('maintenanceRule.part.requiredstring')}");
         return false;
       } else if (!isValidLength(document.forms[0],"maintenanceRule.part",0,250)){
         alert("${action.getText('maintenanceRule.part.maxlength')}");
         return false;
       }
       /*
	    * 验证保养方法字符长度
	   */
       if ('' != document.forms[0].elements["maintenanceRule.method"].value) {
       		if (!isValidLength(document.forms[0],"maintenanceRule.method",0,250)){
		         alert("${action.getText('maintenanceRule.method.maxlength')}");
		         return false;
		    }
       }else {
       		alert("${action.getText('maintenanceRule.method.requiredstring')}");
         	return false;
       }
       /*
	    * 验证保养要求字符长度
	   */
       if ('' != document.forms[0].elements["maintenanceRule.appeal"].value) {
       		if (!isValidLength(document.forms[0],"maintenanceRule.appeal",0,250)){
		         alert("${action.getText('maintenanceRule.appeal.maxlength')}");
		         return false;
		    }
       }
       
	   return true;
	 }
	 
	 </script>
</@htmlPage>