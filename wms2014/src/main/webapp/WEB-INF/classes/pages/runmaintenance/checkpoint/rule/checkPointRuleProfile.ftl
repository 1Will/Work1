<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('checkPointRule.title')}">
<base target="_self" />
	<@ww.form  name="'rule'" action="'saveCheckPointRule'" method="'post'" validate="'true'">
		<@ww.token name="saveCheckPointRuleToken"/>
	  	<@ww.hidden name="'checkPointRule.id'" value="'${req.getParameter('checkPointRule.id')?if_exists}'"/>
	  	<@ww.hidden name="'toolingDev.id'" value="'${req.getParameter('toolingDev.id')?if_exists}'"/>
		 <@inputTable> 
		 	<tr>
		 		<@ww.textfield label="'${action.getText('checkPointRule.position')}'" name="'checkPointRule.position'" value="'${checkPointRule.position?if_exists}'" cssClass="'underline'" required="true"/>
		 		<@ww.textfield label="'${action.getText('checkPointRule.cycle')}'" name="'checkPointRule.cycle'" value="'${checkPointRule.cycle?if_exists}'" cssClass="'underline'"  required="true"/>
		 	</tr>
		 	<tr>
		 		<@ww.textfield label="'${action.getText('checkPointRule.ruleDsp')}'" name="'checkPointRule.ruleDsp'" value="'${checkPointRule.ruleDsp?if_exists}'" cssClass="'underline'" />
		 		<@ww.textfield label="'${action.getText('checkPointRule.methodDsp')}'" name="'checkPointRule.methodDsp'" value="'${checkPointRule.methodDsp?if_exists}'" cssClass="'underline'"/>	            
		 	</tr>
		 	<tr>
		 		<@ww.textfield label="'${action.getText('checkPointRule.requestDsp')}'" name="'checkPointRule.requestDsp'" value="'${checkPointRule.requestDsp?if_exists}'" cssClass="'underline'"/>	            
        		<@pp.datePicker label="'${action.getText('checkPointRule.lastCheckPointTime')}'" name="'checkPointRule.lastCheckPointTime'"
	     			            value="'${(checkPointRule.lastCheckPointTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15"  maxlength="10"/>
	     	</tr>
           	<tr>
           		<@ww.textarea label="'${action.getText('checkPointRule.content')}'" name="'checkPointRule.content'"
                	value="'${checkPointRule.content?if_exists}'" rows="3" cols="60" required="false"/>
           	</tr>	        
		 </@inputTable>
		 <@buttonBar>
	        	<@vsubmit value="'${action.getText('save')}'" onclick="'return storeValidate()'" />
	        	<@vbutton class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
	     </@buttonBar>
	</@ww.form>
	<script language="JavaScript" type="text/JavaScript"> 
		function storeValidate(){
			/*
			 * 验证点检部位
			*/
			if ('' == document.forms[0].elements["checkPointRule.position"].value) {
				alert("${action.getText('checkPointRule.position.requiredstring')}");
				return false;
			} else if (!isValidLength(document.forms[0],"checkPointRule.position",0,250)){		//长度为数字类型
				alert("${action.getText('checkPointRule.position.maxlength')}");
				return false;
			}
			/*
			 * 验证点检周期
			*/
			if ('' == document.forms[0].elements["checkPointRule.cycle"].value) {
				alert("${action.getText('checkPointRule.cycle.requiredstring')}");
				return false;
			}else if (!isValidLength(document.forms[0],"checkPointRule.cycle",0,50)){		//长度为数字类型
				alert("${action.getText('checkPointRule.cycle.maxlength')}");
				return false;
			}
			
			if(isNotEmpty(rule,"checkPointRule.ruleDsp")){
				if (!isValidLength(document.forms[0],"checkPointRule.ruleDsp",0,250)){
					alert("${action.getText('checkPointRule.ruleDsp.maxlength')}");
					return false;
				}
			}
			
			if(isNotEmpty(rule,"checkPointRule.methodDsp")){
				if (!isValidLength(document.forms[0],"checkPointRule.methodDsp",0,250)){
					alert("${action.getText('checkPointRule.methodDsp.maxlength')}");
					return false;
				}
			}
			
			if(isNotEmpty(rule,"checkPointRule.requestDsp")){
				if (!isValidLength(document.forms[0],"checkPointRule.requestDsp",0,250)){
					alert("${action.getText('checkPointRule.requestDsp.maxlength')}");
					return false;
				}
			}
			
			if(isNotEmpty(rule,"checkPointRule.lastCheckPointTime")) {
				if(!isDate("checkPointRule.lastCheckPointTime")){
					alert('${action.getText('checkPointRule.lastCheckPointTime')}'+'${action.getText('dateFormate.error')}');
					return false; 
				}
				if (isDateLessThenCurrent(document.forms[0].elements["checkPointRule.lastCheckPointTime"].value)) {
		          alert("${action.getText('lastCheckPointTime.later.error')}");
		          return false;
		        }
			}
			
			if(isNotEmpty(rule,"checkPointRule.content")){
				if (!isValidLength(document.forms[0],"checkPointRule.content",0,250)){
					alert("${action.getText('checkPointRule.content.maxlength')}");
					return false;
				}
			}
			
			
		    
	      return true;
	 	}
	</script>
	
</@htmlPage>