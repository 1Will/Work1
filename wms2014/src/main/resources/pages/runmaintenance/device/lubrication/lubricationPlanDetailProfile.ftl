<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('lubricationPlanDetail.title')}">
 <base target="_self">
	 <@ww.form name="'listForm'" action="'saveLubricationPlanDetail'" method="'post'">
	 	  <@ww.token name="saveLubricationPlanDetailToken"/>
	 	  <@inputTable>
		    <#if lubrication.id?exists>
		      <@ww.hidden name="'lubrication.id'" value="#{lubrication.id}"/>
		    </#if>
		     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
            <#if lubricationDetail.id?exists>
              <@ww.hidden name="'lubricationDetail.id'" value="#{lubricationDetail.id}"/>
		 	</#if>
		 	<tr>
				<@eam2008_DeviceSelector flag="DeviceLubrication"/>
		 	</tr>
		 	<tr>
		 		<@ww.textfield label="'${action.getText('lubricationRules.position')}'" name="'lubricationDetail.position'" value="'${lubricationDetail.position?if_exists}'" cssClass="'underline'" required="true"/>
		 	    <#--
		 	    <@ww.select label="'${action.getText('lubricationRules.methodDsp')}'" required="false" name="'lubricationMethod.id'" 
    			            listKey="id" listValue="value" list="lubricationMethods" 
    			            emptyOption="true" disabled="false" required="fasle">
    	        </@ww.select>
    	        -->
    	        <@ww.textfield label="'${action.getText('lubricationRules.methodDsp')}'" name="'lubricationDetail.methodDsp'" value="'${lubricationDetail.methodDsp?if_exists}'" cssClass="'underline'"  />
		 	</tr>
	 	  	<tr>
	 	  		<@eam2008_LubricationOilSelector/>        
	 	  	</tr>
	 	    <tr>
	 	  		<@ww.textfield label="'${action.getText('lubricationDetail.lubricationOilQty')}'" name="'lubricationDetail.planLubricatingOilQty'" value="'${lubricationDetail.planLubricatingOilQty?if_exists}'" cssClass="'underline'"  />
		 	    <td align="right" valign="top"><span class="required"></span><label class="label">${action.getText('lubricationDetail.planDutyPeople')}:</label></td>
		 		<td>
		 			<input type="text" name="manager.name" class="underline"  value="${lubricationDetail.planExePeople?if_exists}"  maxlength="150" disabled/>
		 			<a onClick='user_OpenDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>
		 		<@ww.hidden name="'planDutyPeople.name'" value="'${lubricationDetail.planExePeople?if_exists}'"/>
	 	  	</tr>
	 	  	<tr>
	 	  		<@ww.textfield label="'${action.getText('lubricationDetail.planExectPeople')}'" name="'lubricationDetail.planExectPeople'" value="'${lubricationDetail.planExectPeople?if_exists}'" cssClass="'underline'"  />
	 	  		<@pp.datePicker label="'${action.getText('lubricationDetail.estimateExecDate')}'" name="'lubricationDetail.estimateExecDate'"
	     			            value="'${(lubricationDetail.estimateExecDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15"  maxlength="10"/>
	 	  	</tr>
	 	  	<tr>
	 	  	    <@ww.textarea  label="'${action.getText('lubricationRules.ruleDsp')}'" 
	        	         name="'lubricationDetail.ruleDsp'" 
	        	         value="'${lubricationDetail.ruleDsp?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" />
	           <@ww.textarea  label="'${action.getText('lubricationDetail.comment')}'" 
	        	         name="'lubricationDetail.comment'" 
	        	         value="'${lubricationDetail.comment?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" />
	 	  	</tr>
	 	  </@inputTable>
	 	  <@buttonBar>
	 	   <#if !(action.isReadOnly())>
	        <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	        <input type="button" value="关闭" class="button" onclick="window.close();"
	        </#if>
	     </@buttonBar>
	</@ww.form>
	<script language="JavaScript">
	  function user_OpenDialog() {
	    var url = "${req.contextPath}/popup/userSelector.html";
	  	popupModalDialog(url, 800, 600, userSelectorHandler);
	  }	
	  function userSelectorHandler(result) {
	    if(null != result) {
	  	  document.forms[0].elements["planDutyPeople.name"].value  = result[1];
	  	  document.forms[0].elements["manager.name"].value = result[1];
	  	} 
	  }
	  function validate() {
	    if (!eam2008_device_validate("${action.getText('select.device.no')}")){
		  return false;
	    }
	 
	    /*
	     * 验证润滑部位
	    */
	    if ('' == document.forms[0].elements["lubricationDetail.position"].value) {
	      alert("${action.getText('select.lubricationDetail.position')}");
	      return false;
	    } else if (!isValidLength(document.forms[0],"lubricationDetail.position",0,150)){
          alert("${action.getText('lubricationDetail.position.maxlength')}");
          return false;
       }
        
       /*
	    * 验证润滑方法
	   */
	   if ('' != document.forms[0].elements["lubricationDetail.methodDsp"].value) {
	     if (!isValidLength(document.forms[0],"lubricationDetail.methodDsp",0,50)){
           alert("${action.getText('lubricationDetail.methodDsp.maxlength')}");
           return false;
         }
	   }
	  
       /*
	    * 验证润滑计量
	   */
	   if ('' != document.forms[0].elements["lubricationDetail.planLubricatingOilQty"].value) {
	   	 if (!isDoubleNumber("lubricationDetail.planLubricatingOilQty")) {
	       alert("${action.getText('lubricationDetail.planLubricatingOilQty.Integer.error')}");
	       return false;
	     }
	     var lubricatingOilQty = document.forms[0].elements["lubricationDetail.planLubricatingOilQty"].value;
	     if (!isDoubleNumberBetweenBoolean(lubricatingOilQty, 10000000001, 0)) {
	       alert("${action.getText('lubricationDetail.planLubricatingOilQty.maxlength')}");
	       return false;
	     }
	   }
	   if ('' != document.forms[0].elements["lubricationDetail.estimateExecDate"].value) {
		 if(!isDate("lubricationDetail.estimateExecDate")){
		   alert("${action.getText('select.right.lubricationDetail.estimateExecDate')}");
		   return false;
		 }
	   }
	   /*
	    * 验证润滑标准
	   */
	   if ('' != document.forms[0].elements["lubricationDetail.ruleDsp"].value) {
	     if (!isValidLength(document.forms[0],"lubricationDetail.ruleDsp",0,250)){
           alert("${action.getText('lubricationDetail.ruleDsp.maxlength')}");
           return false;
         }
	   }
	   /*
	    * 验证备注
	   */
	   if ('' != document.forms[0].elements["lubricationDetail.comment"].value) {
	     if (!isValidLength(document.forms[0],"lubricationDetail.comment",0,250)){
           alert("${action.getText('lubricationDetail.comment.maxlength')}");
           return false;
         }
	   }
	    return true;
	  }
	</script>
</@htmlPage>