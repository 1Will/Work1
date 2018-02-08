<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('RepairToolEdit.title')}">
 <base target="_self">
	 <@ww.form name="'repairTool'" action="'saveRepairTool'" method="'post'" validate="true">
	 	  <@ww.token name="saveRepairToolToken"/>
	 	  <#if faultRepair.id?exists>
     	    <@ww.hidden name="'faultRepair.id'" value="'#{faultRepair.id?if_exists}'"/>
          </#if>
          <#if repairTool.id?exists>
     	    <@ww.hidden name="'repairTool.id'" value="'#{repairTool.id?if_exists}'"/>
          </#if>
          <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
          <@ww.hidden name="'preYearFlag'" value="'${preYearFlag?if_exists}'"/>
	 	  <@inputTable>
	 	  	<tr>
	 	  		<@ww.textfield label="'${action.getText('repairTool.name')}'" name="'repairTool.name'" value="'${repairTool.name?if_exists}'" cssClass="'underline'" required="true" disabled="false"/>
	 	  	    <@ww.textfield label="'${action.getText('repairTool.specification')}'" name="'repairTool.specification'" value="'${repairTool.specification?if_exists}'" cssClass="'underline'" disabled="false"/>
	 	  	</tr>
	 	  	<tr>
	 	  	    <@ww.textfield label="'${action.getText('repairTool.model')}'" name="'repairTool.model'" value="'${repairTool.model?if_exists}'" cssClass="'underline'" disabled="false"/>
	 	  		<@ww.select label="'${action.getText('repairTool.calcUnit')}'"
	                       name="'repairTool.calcUnit'"
	                   	   listKey="value" 
	                       listValue="value"
	                       list="CalcUnit" 
	                       emptyOption="false" 
	                       disabled="false">
	                       <#if repairTool.calcUnit?exists>
                              <@ww.param name="'value'"  value="'${repairTool.calcUnit?if_exists}'" />
                          </#if>
	           </@ww.select>
	 	  	</tr>
	 	  	<tr>
	 	  	    <@ww.textfield label="'${action.getText('repairTool.procUseNum')}'" name="'repairTool.procUseNum'" value="'${repairTool.procUseNum?if_exists}'" cssClass="'underline'" disabled="false"/>	
	 	  		<@ww.textarea label="'${action.getText('repairTool.comment')}'" 
					         name="'repairTool.comment'" 
					         value="'${repairTool.comment?if_exists}'" rows="'3'" cols="'50'" 
							 disabled="false"
							 />
	 	  	</tr>
	 	  </@inputTable>
	 	  <@buttonBar>
	        	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return customize_validate();'"/>
	        	<input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
	     </@buttonBar>
		<script language="javascript">
		  function customize_validate() {
		    if ('' == document.forms["repairTool"].elements["repairTool.name"].value) {
		      alert("${action.getText('repairTool.name.requried')}");
		      return false;
		    }else{
		         if (!isValidLength(document.forms["repairTool"],"repairTool.name",0,50)) {
		        alert("${action.getText('repairTool.name.maxLength')}");
		        return false;
		      }
		    }
		    if ('' != document.forms["repairTool"].elements["repairTool.specification"].value) {
		      if (!isValidLength(document.forms["repairTool"],"repairTool.specification",0,50)) {
		        alert("${action.getText('repairTool.specification.maxLength')}");
		        return false;
		      }
		    }
		    if ('' != document.forms["repairTool"].elements["repairTool.model"].value) {
		      if (!isValidLength(document.forms["repairTool"],"repairTool.model",0,50)) {
		        alert("${action.getText('repairTool.model.maxLength')}");
		        return false;
		      }
		    }
		    if ('' != document.forms["repairTool"].elements["repairTool.calcUnit"].value) {
		      if (!isValidLength(document.forms["repairTool"],"repairTool.calcUnit",0,25)) {
		        alert("${action.getText('repairTool.calcUnit.maxLength')}");
		        return false;
		      }
		    }
		    if ('' != document.forms[0].elements["repairTool.procUseNum"].value) {
		      if (!isNumber("repairTool.procUseNum")) {
		        alert("${action.getText('repairTool.procUseNum.maxLength')}");
		        return false;
		      }
		      if (!isNumberBetweenBoolen(document.forms["repairTool"].elements["repairTool.procUseNum"].value,1000000000,0)) {
		        alert("${action.getText('repairTool.procUseNum.maxLength')}");
		        return false;
		      }
		   }
		   if ('' != document.forms["repairTool"].elements["repairTool.comment"].value) {
		      if (!isValidLength(document.forms["repairTool"],"repairTool.comment",0,250)) {
		        alert("${action.getText('repairTool.comment.maxLength')}");
		        return false;
		      }
		    }
		    if (document.forms[0].elements["planProcFlag"].value == 'PROC') {

		    }
		    return true;
		  }	
		</script>
	 </@ww.form>
</@htmlPage>