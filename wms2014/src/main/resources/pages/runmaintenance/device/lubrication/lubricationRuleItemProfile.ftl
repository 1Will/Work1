<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('lubricationRules.title')}">
 <base target="_self" />
	 <@ww.form name="'lubricationRule'" action="'saveLubricationRules'" method="'post'" validate="true">
	 	  <@ww.token name="saveLubricationRulesToken"/>
	 	  <@ww.hidden name="'device.id'" value="'#{device.id?if_exists}'"/>
	 	  <#if lubricationRule.id?exists>
	 	      <@ww.hidden name="'lubricationRule.id'" value="'#{lubricationRule.id?if_exists}'"/>
	 	  <#else>
	 	      <@ww.hidden name="'lubricationRule.id'" value=""/>
	 	  </#if>
	 	  <@inputTable>
	 	  	<tr>
	 	  		<@ww.textfield label="'${action.getText('lubricationRules.position')}'" name="'lubricationRule.position'" value="'${lubricationRule.position?if_exists}'" cssClass="'underline'" required="true"/>
	 	  		<@ww.textfield label="'${action.getText('lubricationRules.cycle')}'" name="'lubricationRule.cycle'" value="'${lubricationRule.cycle?if_exists}'" cssClass="'underline'"  required="true"/>
	 	  	</tr>
	 	  	<tr>
	 	  		<@pp.datePicker label="'${action.getText('lubricationRules.lastLubricationDate')}'" name="'lubricationRule.lastLubricationDate'"
	     			            value="'${(lubricationRule.lastLubricationDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15"  maxlength="10"/>
		 		<@ww.textfield label="'${action.getText('lubricationRules.dutyPeople')}'" name="'lubricationRule.dutyPeople'" value="'${lubricationRule.dutyPeople?if_exists}'" cssClass="'underline'"  />
	 	  	</tr>
	 	  	<tr>
	 	  		<@eam2008_LubricationOilSelector/>        
	 	  	</tr>
	 	  	<tr>
	 	  		<@ww.textfield label="'${action.getText('lubricationRules.preExePeople')}'" name="'lubricationRule.preExePeople'" value="'${lubricationRule.preExePeople?if_exists}'" cssClass="'underline'"  />
	 	  		<@ww.textfield label="'${action.getText('lubricationRules.lubricatingOilQty')}'" name="'lubricationRule.lubricatingOilQty'" value="'${lubricationRule.lubricatingOilQty?if_exists}'" cssClass="'underline'"  />
	 	  	</tr>
	 	  	<tr>
	 	  		<td align="right" valign="top"><label class="label">${action.getText('lubricationRules.methodDsp')}:</label></td>
	 	  		<td>
		 			<#assign methodDsp = ''/>
		 			<#if lubricationRule?exists>
		 				<#assign methodDsp = "${lubricationRule.methodDsp?if_exists}"/>
		 			</#if>
		 			<input type="text" name="lubricationRules.methodDsp" class="underline"  value="${methodDsp}"  maxlength="150" disabled="true"/>
		 			<a onClick='lubricationRule_OpenDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
	 				<@ww.hidden name="'lubricationRules.name'" value=""/>
		 		</td>
	 	  	    <@ww.textarea  label="'${action.getText('lubricationRules.ruleDsp')}'" 
	        	         name="'lubricationRule.ruleDsp'" 
	        	         value="'${lubricationRule.ruleDsp?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" />
	 	  	</tr>
	 	  </@inputTable>
	 	  <@buttonBar>
	        	<@vsubmit value="'${action.getText('save')}'" onclick="'return storeValidate()'" />
	        	<input type="button" value="${action.getText('close')}" class="button" onclick="window.close();"
	     </@buttonBar>
	 </@ww.form>
	 
	 <script>
	 window.onload=function(){
	     var time=getObjByNameRe("lubricationRule.lastLubricationDate").value;
	      if(time==""){
	         time=new Date();
	         var currentTime=time.format("yyyy-MM-dd");
	         getObjByNameRe("lubricationRule.lastLubricationDate").value=currentTime;
	      }
	 }
	 
	 function validateDate(){
	    if(isNotEmpty(lubricationRule,"lubricationRule.lastLubricationDate")){
	      return isDate("lubricationRule.lastLubricationDate");
	    }
	      return true;
	 }
	 
	 function lubricationOil_OpenDialog(url){
	    popupModalDialog(url, 800, 600, refresh_lubricationRuleOil_information);
	 }
	 
	 function refresh_lubricationRuleOil_information(result){
	      if(result==null){
	        return ;
	      }
	      document.forms[0].elements["lubricationOil.id"].value = result[0]; 
	      document.forms[0].elements["lubricationOil.name"].value = result[2]; 
	 }
     function storeValidate(){
	   /*
	    * 验证润滑部位
	   */
       if ('' == document.forms[0].elements["lubricationRule.position"].value) {
         alert("${action.getText('lubricationRule.position.requiredstring')}");
         return false;
       } else if (!isValidLength(document.forms[0],"lubricationRule.position",0,150)){
         alert("${action.getText('lubricationRule.position.maxlength')}");
         return false;
       }
       /*
        * 验证润滑周期
       */
       if(isNotEmpty(lubricationRule,"lubricationRule.cycle")) {
		      if (!isNumber("lubricationRule.cycle")) {
				alert("${action.getText('lubricationRule.cycle.Integer.error')}");
				return false;
		      } else if (!isNumberBetweenBoolen(document.forms[0].elements["lubricationRule.cycle"].value, 1000001, 0)){
				alert("${action.getText('lubricationRule.cycle.maxlength')}");
				return false;
		      }
		}else {
			alert("${action.getText('lubricationRule.cycle.requiredInteger')}");
	     	return false;
		}
		
	   /*
	    * 验证上次润滑日期
	   */
	   if(!validateDate()){
	     alert("${action.getText('lubricationRule.date.notValid')}");
	     return false;
	   }
	   //验证责任人
	    if ('' != document.forms[0].elements["lubricationRule.dutyPeople"].value) {
	     if (!isValidLength(document.forms[0],"lubricationRule.dutyPeople",0,50)){
           alert("${action.getText('lubricationRule.lubricationRule.dutyPeople.maxlength')}");
           return false;
         }
	   }
	   if(isNotEmpty(lubricationRule,"lubricationRule.lubricatingOilQty")) {
			if (!isDoubleNumber("lubricationRule.lubricatingOilQty")){
				alert("${action.getText('lubricationRule.lubricatingOilQty.isNotNumber')}");
				return false;
			} else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["lubricationRule.lubricatingOilQty"].value, 1000000001, 0)){  //验证范围
				alert("${action.getText('lubricationRule.lubricatingOilQty.maxLength')}");
				return false;
			}
		}
	   
	   /*
	    * 验证润滑方法
	   */
	   if ('' != document.forms[0].elements["lubricationRules.methodDsp"].value) {
	     if (!isValidLength(document.forms[0],"lubricationRules.methodDsp",0,50)){
           alert("${action.getText('lubricationRule.methodDsp.maxlength')}");
           return false;
         }
	   }
	   /*
	    * 验证润滑标准
	   */
	   if ('' != document.forms[0].elements["lubricationRule.ruleDsp"].value) {
	     if (!isValidLength(document.forms[0],"lubricationRule.ruleDsp",0,250)){
           alert("${action.getText('lubricationRule.ruleDsp.maxlength')}");
           return false;
         }
	   }
	   return true;
	 }
	 
	 
	 function lubricationRule_OpenDialog() {
		var url = "${req.contextPath}/popup/lubricationRuleSelector.html";
	  	popupModalDialog(url, 800, 600, lubricationRuleSelectorHandler);
	}
			
	function lubricationRuleSelectorHandler(result) {
		document.forms["lubricationRule"].elements["lubricationRules.name"].value = result[1];
	  	document.forms["lubricationRule"].elements["lubricationRules.methodDsp"].value = result[1];
	  		
	}
	 </script>
</@htmlPage>