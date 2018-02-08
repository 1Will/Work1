<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('preRepairPlanDetailItem.title')}">
 <base target="_self">
	 <@ww.form name="'repairItem'" action="'saveRepairItem'" method="'post'" validate="true">
	 	  <@ww.token name="saveRepairItemItemToken"/>
     	   <@ww.hidden name="'faultRepair.id'" value="'#{faultRepair.id?if_exists}'"/>
     	  <#if repairItem.id?exists> 
	 	    <@ww.hidden name="'repairItem.id'" value="'#{repairItem.id}'"/>
	 	  </#if>
	 	  <@ww.hidden name="'preYearFlag'" value="'${preYearFlag?if_exists}'"/>
	 	  <@inputTable>
	 	  	<tr>
	 	  		<@ww.textfield label="'${action.getText('repairItem.position')}'" name="'repairItem.position'" value="'${repairItem.position?if_exists}'" cssClass="'underline'" required="true" disabled="false"/>
	 	  		<#assign execPeopleName = ''/>
				<#if repairItem.execPeople?exists>
				 <#assign execPeopleName = "${repairItem.execPeople.name}" />
				</#if>
				<td align="right" valign="top"><label class="label">${action.getText('repairItem.procExecPeople')}:</label></td>
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
	 	  	</tr>
	 	  	<tr>
	 	  		<@ww.textarea label="'${action.getText('repairItem.content')}'" 
					         name="'repairItem.content'" 
					         value="'${repairItem.content?if_exists}'" rows="'3'" cols="'50'" 
					         disabled="false"
							 />
				<@ww.textarea label="'${action.getText('repairItem.comment')}'" 
						          name="'repairItem.comment'" 
						          value="'${repairItem.comment?if_exists}'" 
						          rows="'3'" cols="'50'" 
								 />
							 <#--
			   <@ww.textarea label="'${action.getText('repairItem.aimRequire')}'" 
					         name="'repairItem.aimRequire'" 
					         value="'${repairItem.aimRequire?if_exists}'" rows="'3'" cols="'50'" 
					         disabled="false"
							 />
							 -->
	 	  		
	 	  	</tr>
	 	  </@inputTable>
	 	  <@buttonBar>
	        	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	        	<input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
	     </@buttonBar>
	 </@ww.form>
	 <script language="javascript">
	 	function validate(){
			if(!repairItem_position()){
		  		return false;
		  	}
		  	<#--
		  	if(!repairItem_aimRequire()){
		  		return false;
		  	}
		  	-->
		  	if(!repairItem_content()){
		  		return false;
		  	}	
		  	if(!repairItem_comment()){
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
	 	function execPeople_OpenDialog() {
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, execPeopleSelectorHandler);
		  }
		  function execPeopleSelectorHandler(result) {
		    if(null != result) {
		      document.forms["repairItem"].elements["execPeople.id"].value = result[0];
		      document.forms["repairItem"].elements["execPeople.name"].value = result[1];
		    }
		  }
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
			return true;
	 	}
	 	function repairItem_comment() {
		    var name = document.forms["repairItem"].elements["repairItem.comment"].value;
		    if(!(name=='')){
			  	if(!isValidLength(document.forms[0], "repairItem.comment", null, 250)){
			  		alert("${action.getText('repairItem.comment.length')}");
			  		return  false;
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