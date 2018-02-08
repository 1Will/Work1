<#include "../../includes/eam2008.ftl" />
<#assign disabled = ''/>
<#if planProcFlag?exists>
	<#if (planProcFlag=='PLAN')>
		<#assign disabled = 'false'/>
	<#else>
		<#assign disabled = 'true'/>
	</#if>
</#if>
<@htmlPage title="${action.getText('RepairToolEdit.title')}">
 <base target="_self">
	 <@ww.form name="'repairTool'" action="'saveRepairTool'" method="'post'" validate="true">
	 	  <@ww.token name="saveRepairToolToken"/>
	 	  <#if preYearFlag?exists>
     		<#if preYearFlag=='YEAR'>
     			<@ww.hidden name="'repairPlanOrProcDetail.id'" value="'#{repairPlanOrProcDetail.id?if_exists}'"/>
     		<#else>
     			<@ww.hidden name="'preRepairPlanDetail.id'" value="'#{preRepairPlanDetail.id?if_exists}'"/>
     		</#if>
     	</#if>
          <@ww.hidden name="'repairTool.id'" value="'${repairTool.id?if_exists}'"/>
          <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
          <@ww.hidden name="'preYearFlag'" value="'${preYearFlag?if_exists}'"/>
	 	  <@inputTable>
	 	  	<tr>
	 	  		<@ww.textfield label="'${action.getText('repairTool.name')}'" name="'repairTool.name'" value="'${repairTool.name?if_exists}'" cssClass="'underline'" required="true" disabled="${disabled}"/>
	 	  	    <@ww.textfield label="'${action.getText('repairTool.specification')}'" name="'repairTool.specification'" value="'${repairTool.specification?if_exists}'" cssClass="'underline'" disabled="${disabled}"/>
	 	  	</tr>
	 	  	<tr>
	 	  	    <@ww.textfield label="'${action.getText('repairTool.model')}'" name="'repairTool.model'" value="'${repairTool.model?if_exists}'" cssClass="'underline'" disabled="${disabled}"/>
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
	 	  	    <@ww.textfield label="'${action.getText('repairTool.planUseNum')}'" name="'repairTool.planUseNum'" value="'${repairTool.planUseNum?if_exists}'" cssClass="'underline'" disabled="${disabled}"/>	
	 	  		<@ww.textarea label="'${action.getText('repairTool.comment')}'" 
					         name="'repairTool.comment'" 
					         value="'${repairTool.comment?if_exists}'" rows="'3'" cols="'50'" 
							 disabled="${disabled}"
							 />
	 	  	</tr>
	 	  	<#if planProcFlag?exists>
			  <#if (planProcFlag=='PROC')>
			  	<@ww.textfield label="'${action.getText('repairTool.procUseNum')}'" name="'repairTool.procUseNum'" value="'${repairTool.procUseNum?if_exists}'" cssClass="'underline'"/>	
			  </#if>
			</#if>
	 	  </@inputTable>
	 	  <@buttonBar>
	        	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return customize_validate();'"/>
	        	<input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
	     </@buttonBar>
		<script language="javascript">
		<#--
		 window.onload = function() {
		   alert("onload ==== repairTool.id " + document.forms["repairTool"].elements["repairTool.id"].value + " ==preRepairPlanDetail.id " + document.forms["repairTool"].elements["preRepairPlanDetail.id"].value + " == preYearFlag " + document.forms["repairTool"].elements["preYearFlag"].value);
		 } 
		 -->
		  function customize_validate() {
		    //验证工具名称
		    if (!validate_repairName()) {
		      return false;
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
		   if ('' != document.forms["repairTool"].elements["repairTool.planUseNum"].value) {
		      if (!isNumber("repairTool.planUseNum")) {
		        alert("${action.getText('repairTool.planUseNum.maxLength')}");
		        return false;
		      }
		      if (!isNumberBetweenBoolen(document.forms["repairTool"].elements["repairTool.planUseNum"].value,1000000001,0)) {
		        alert("${action.getText('repairTool.planUseNum.maxLength')}");
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
		      if ('' != document.forms[0].elements["repairTool.procUseNum"].value) {
		        if (!isNumber("repairTool.procUseNum")) {
		          alert("${action.getText('repairTool.procUseNum.maxLength')}");
		          return false;
		        }
		        if (!isNumberBetweenBoolen(document.forms["repairTool"].elements["repairTool.procUseNum"].value,1000000001,0)) {
		          alert("${action.getText('repairTool.procUseNum.maxLength')}");
		          return false;
		        }
		      }
		    }
		    <#--
		     alert("save ==== repairTool.id " + document.forms["repairTool"].elements["repairTool.id"].value + " ==preRepairPlanDetail.id " + document.forms["repairTool"].elements["preRepairPlanDetail.id"].value + " == preYearFlag " + document.forms["repairTool"].elements["preYearFlag"].value);
		    -->
		    return true;
		  }	
		  //验证工具名称function
		  function validate_repairName(){
	        var name=document.forms["repairTool"].elements["repairTool.name"].value;
	        if ('' == name) {
	          alert("${action.getText('repairTool.name.requried')}");
			  return  false;
	        }
		    if(!(name=='')){
		      if(!isValidLength(document.forms[0], "repairTool.name", null, 50)){
			    alert("${action.getText('repairTool.name.length')}");
				return  false;
			  }
			}
			return true;
		  }
		</script>
	 </@ww.form>
</@htmlPage>