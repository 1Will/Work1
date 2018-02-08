<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('deviceMaintenanceDetail.title')}">
 <base target="_self">
	<@ww.form name="'deviceMaintenanceDetail'" action="'saveDeviceMaintenanceDetail'" method="'post'" validate="true">
		<@ww.token name="saveDeviceMaintenanceDetailToken"/>
		<@ww.hidden name="'detail.id'" value="'#{detail.id?if_exists}'"/>
		<@ww.hidden name="'device.id'" value="${device.id?if_exists}"/>
		 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#if deviceMaintenanceDetail?exists>
			<@ww.hidden name="'deviceMaintenanceDetail.id'" value="'${deviceMaintenanceDetail.id?if_exists}'"/>
		</#if>
		<@inputTable>
			<tr>    
	 	  		<@ww.textfield label="'${action.getText('deviceMaintenanceDetail.position')}'" name="'deviceMaintenanceDetail.part'" value="'${deviceMaintenanceDetail.part?if_exists}'" cssClass="'underline'" required="true"/>
	 	  		<@ww.textfield label="'${action.getText('deviceMaintenanceDetail.method')}'" name="'deviceMaintenanceDetail.method'" value="'${deviceMaintenanceDetail.method?if_exists}'" cssClass="'underline'" />
	 	  	</tr>
	 	  	<tr>
	 	  	    <@ww.textarea  label="'${action.getText('deviceMaintenanceDetail.appeal')}'" 
	        	         name="'deviceMaintenanceDetail.appeal'" 
	        	         value="'${deviceMaintenanceDetail.appeal?if_exists}'"  
	        	         rows="5" cols="50" cssClass="'underline'" />
	 	  	</tr>
	 	  	<@buttonBar>
	 	  	<#if !(action.isReadOnly())>
	        	<@vsubmit value="'${action.getText('save')}'" onclick="'return storeValidate()'" />
	        	</#if>
	        	<input type="button" value="${action.getText('close')}" class="button" onclick="window.close();"
	     </@buttonBar>
	     </@inputTable>
		</@ww.form>
	 
	 <script>
	 	function storeValidate(){
	   /*
	    * 验证保养部位
	   */
       if ('' == document.forms[0].elements["deviceMaintenanceDetail.part"].value) {
         alert("${action.getText('deviceMaintenanceDetail.part.requiredstring')}");
         return false;
       } else if (!isValidLength(document.forms[0],"deviceMaintenanceDetail.part",0,250)){
         alert("${action.getText('deviceMaintenanceDetail.part.maxlength')}");
         return false;
       }
       /*
	    * 验证保养方法字符长度
	   */
       if ('' != document.forms[0].elements["deviceMaintenanceDetail.method"].value) {
       		if (!isValidLength(document.forms[0],"deviceMaintenanceDetail.method",0,250)){
		         alert("${action.getText('deviceMaintenanceDetail.method.maxlength')}");
		         return false;
		    }
       }
       /*
	    * 验证保养要求字符长度
	   */
       if ('' != document.forms[0].elements["deviceMaintenanceDetail.appeal"].value) {
       		if (!isValidLength(document.forms[0],"deviceMaintenanceDetail.appeal",0,250)){
		         alert("${action.getText('deviceMaintenanceDetail.appeal.maxlength')}");
		         return false;
		    }
       }
       
	   return true;
	 }
	 </script>
</@htmlPage>