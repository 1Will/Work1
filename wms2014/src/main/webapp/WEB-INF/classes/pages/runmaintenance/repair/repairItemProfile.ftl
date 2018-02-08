<#include "../../includes/eam2008.ftl" />
<#assign disabled = ''/>
<#if planProcFlag?exists>
	<#if (planProcFlag=='PLAN')>
		<#assign disabled = 'false'/>
	<#else>
		<#assign disabled = 'true'/>
	</#if>
</#if>
<@htmlPage title="${action.getText('preRepairPlanDetailItem.title')}">
 <base target="_self">
	 <@ww.form name="'repairItem'" action="'saveRepairItem'" method="'post'" validate="true">
	 	  <@ww.token name="saveRepairItemToken"/>
	 	  <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	 	  <#if preYearFlag?exists>
     		<#if preYearFlag=='YEAR'>
     			<@ww.hidden name="'repairPlanOrProcDetail.id'" value="'#{repairPlanOrProcDetail.id?if_exists}'"/>
     		<#elseif preYearFlag=='PRE'>
     			<@ww.hidden name="'preRepairPlanDetail.id'" value="'#{preRepairPlanDetail.id?if_exists}'"/>
     		</#if>
     	</#if>
     	  <#if repairItem.id?exists> 
	 	    <@ww.hidden name="'repairItem.id'" value="'#{repairItem.id}'"/>
	 	  </#if>
	 	  <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
	 	  <@ww.hidden name="'preYearFlag'" value="'${preYearFlag?if_exists}'"/>
	 	  <@inputTable>
	 	  	<tr>
	 	  		<@ww.textfield label="'${action.getText('repairItem.position')}'" name="'repairItem.position'" value="'${repairItem.position?if_exists}'" cssClass="'underline'" required="true" disabled="${disabled}"/>
	 	  		<@ww.textfield label="'${action.getText('repairItem.execPeople')}'" 
	 	  			name="'repairItem.execPeopleString'"   value="'${repairItem.execPeopleString?if_exists}'" 
	 	  			cssClass="'underline'" />	
	 	  		<#--
	 	  		<#assign execPeopleName = ''/>
				<#if repairItem.execPeople?exists>
				 <#assign execPeopleName = "${repairItem.execPeople.name}" />
				</#if>
				<td align="right" valign="top"><label class="label">${action.getText('repairItem.execPeople')}:</label></td>
	        	<td>
	        		<input type="text" name="execPeople.name" 
	        			class="underline"  value="${execPeopleName}"  maxlength="150" size="10" disabled="true"/>
	        		<label id=""></label>
		    		<a onClick="execPeople_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		        <#assign execPeopleId = ''/>
				<#if repairItem.execPeople?exists>
				 	<#assign execPeopleId = "${repairItem.execPeople.id}" />
				</#if>
				<input type="hidden" name="execPeople.id" value="${execPeopleId}" /> 
				-->
	 	  	</tr>
	 	  	<tr>
	 	  		<@pp.datePicker label="'${action.getText('repairItem.planCompleteDate')}'" name="'repairItem.planCompleteDate'"
		     							  value="'${(repairItem.planCompleteDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
		     							 disabled="${disabled}" maxlength="10"/>	
	 	  		<@ww.textarea label="'${action.getText('repairItem.content')}'" 
					         name="'repairItem.content'" 
					         value="'${repairItem.content?if_exists}'" rows="'3'" cols="'50'" 
					         disabled="${disabled}"
							 />
	 	  		
	 	  	</tr>
	 	  	<tr>
	 	  		<@ww.textarea label="'${action.getText('repairItem.aimRequire')}'" 
					         name="'repairItem.aimRequire'" 
					         value="'${repairItem.aimRequire?if_exists}'" rows="'3'" cols="'50'" 
					         disabled="${disabled}"
							 />
		 	  	<#if planProcFlag?exists>
		        <#if planProcFlag=='PROC'>
		          	<@ww.textarea label="'${action.getText('repairItem.comment')}'" 
						          name="'repairItem.comment'" 
						          value="'${repairItem.comment?if_exists}'" 
						          rows="'3'" cols="'50'" 
								 />
		        </#if>
		        </#if>
	        </tr>
	 	  </@inputTable>
	 	  <@buttonBar>
               <#if !(action.isReadOnly())>
	        	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	        	</#if>
	        	<input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
	     </@buttonBar>
	 </@ww.form>
	 <script language="javascript">
	 	function validate(){
	 		if(!repairItem_planCompleteDate()){
	 			return false;
	 		}
			if(!repairItem_position()){
		  		return false;
		  	}
		  	if(!repairItem_execPeople()){
		  		return false;
		  	}
		  	if(!repairItem_aimRequire()){
		  		return false;
		  	}
		  	if(!repairItem_content()){
		  		return false;
		  	}	
	 	}
	 	function repairItem_planCompleteDate(){
	 		if(!document.forms["repairItem"].elements["repairItem.planCompleteDate"].value ==""){
				var date=document.getElementById("repairItem.planCompleteDate").value;
				if(!isDate("repairItem.planCompleteDate")){
					alert("${action.getText('select.right.repairItem.planCompleteDate')}");
					return false;
				}
			}
			return true;
		}
		function repairItem_execPeople(){
			if ('' != document.getElementById("repairItem.execPeopleString").value) {
		        if(!isValidLength(document.forms["repairItem"], "repairItem.execPeopleString", null, 50)){
					alert("${action.getText('execPeopleString.length')}");
					return  false;
				}
	       }
	       return true;
		}	
	 	<#--function execPeople_OpenDialog() {
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, execPeopleSelectorHandler);
		  }
		  function execPeopleSelectorHandler(result) {
		    if(null != result) {
		      document.forms["repairItem"].elements["execPeople.id"].value = result[0];
		      document.forms["repairItem"].elements["execPeople.name"].value = result[1];
		    }
		  }-->
	 	function repairItem_position(){
	 		if (document.forms["repairItem"].elements["repairItem.position"].value == '') {
		      alert("${action.getText('input.repairItem.position')}");
		      return false;
		    }
		    var name = document.forms["repairItem"].elements["repairItem.position"].value;
		    if(!(name=='')){
			  	if(!isValidLength(document.forms[0], "repairItem.position", null, 50)){
			  		alert("${action.getText('repairItem.position.length')}");
			  		return  false;
			  	}
			}
			return true;
	 	}
	 	function repairItem_content() {
		    var name = document.forms["repairItem"].elements["repairItem.content"].value;
		    if(!(name=='')){
			  	if(!isValidLength(document.forms[0], "repairItem.content", null, 250)){
			  		alert("${action.getText('repairItem.content.length')}");
			  		return  false;
			  	}
			}
			if (document.forms[0].elements["planProcFlag"].value == 'PROC') {
			  if ('' != document.forms["repairItem"].elements["repairItem.comment"].value) {
			    if(!isValidLength(document.forms[0], "repairItem.comment", null, 250)){
			  	  alert("${action.getText('repairItem.comment.length')}");
			  	  return  false;
			  	}
			  }
			}
			return true;
	 	}
	 	function repairItem_aimRequire() {
		    var name = document.forms["repairItem"].elements["repairItem.aimRequire"].value;
		    if(!(name=='')){
			  	if(!isValidLength(document.forms[0], "repairItem.aimRequire", null, 250)){
			  		alert("${action.getText('repairItem.aimRequire.length')}");
			  		return  false;
			  	}
			}
			return true;
	 	}
	</script>
</@htmlPage>