<#--$Id: checkPointPlanProfile.ftl 7923 2007-10-22 02:36:38Z qsun $ -->
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('checkPiontPlanProfile.title')}">
    <@ww.form namespace="'/runmaintenance/checkPoint'" name="'plan'" action="'saveCheckPointPlan'" method="'post'" validate="true">
    	<@ww.hidden name="'plan.id'" value="'${req.getParameter('plan.id')?if_exists}'"/>
    	<@ww.hidden name="'plan.docStatus'" value="'${req.getParameter('plan.docStatus')?if_exists}'"/>
    	<@ww.hidden name="'docview'" value="'${req.getParameter('view')?if_exists}'"/>
        <@ww.token name="savePlanToken"/>
        <@buttonBar>
        	<input type="button" class="button" name="selectRule" value="${action.getText('checkPoint.selectRule')}" onclick="checkPointRule_openDialog()" />
    	</@buttonBar>
        <@inputTable>
            <tr>
                <@ww.textfield label="'${action.getText('plan.no')}'"   name="'plan.planNo'" value="'${plan.planNo?if_exists}'" cssClass="'underline'" readonly="true" disabled="true"/>
                <@ww.textfield label="'${action.getText('plan.name')}'" name="'plan.name'"   value="'${plan.name?if_exists}'" cssClass="'underline'"  required="true"/>
            </tr>
			<tr>
			 	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('rule.ruleNo')}:</label></td>
			 	<td>
			 		<#if rule?exists>
				 		<input type="text" name="rule.ruleNo" class="underline"  value="${rule.ruleNo?if_exists}"  maxlength="150" disabled/>
				 	<#else>
				 		<input type="text" name="rule.ruleNo" class="underline"  value=""  maxlength="150" />
				 		</label>
				 	</#if>	
			 	</td>
			 	<#if rule?exists>
			 		<@ww.textfield label="'${action.getText('rule.name')}'" name="'rule.name'"   value="'${rule.name?if_exists}'" cssClass="'underline'"  disabled="true" readonly="true"/>
			 	<#else>
			 		<@ww.textfield label="'${action.getText('rule.name')}'" name="'rule.name'"   value="" cssClass="'underline'"  disabled="true" readonly="true"/>
			 	</#if>
			</tr>
			 <#if rule?exists>
 				<@ww.hidden name="'rule.id'" value="'${rule.id?if_exists}'"/>
			<#else>
				<@ww.hidden name="'rule.id'" value=""/>
			</#if>
			<tr>
			<@eam2008_DeviceSelector/>
			</tr>
			<tr>
        		<#assign manager = ''/>
		 		<#if plan.manager?exists>
	     			<#assign manager = "${plan.manager.name}" />
	     		</#if>
		 		<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('manager')}:</label></td>
		 		<td>
		 			<input type="text" name="manager.name" class="underline"  value="${manager}"  maxlength="150" />
		 			<a onClick='user_OpenDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>
		 		<#assign managerId = ''/>
		 		<#if plan.manager?exists>
	     			<#assign managerId = "${plan.manager.id}" />
	     		</#if>
		 		<@ww.hidden name="'manager.id'" value="'${managerId}'"/>	 		
        		
        		<@ww.textfield label="'${action.getText('checkPoint.type')}'" name="'plan.ruleType'"   value="'${plan.ruleType?if_exists}'" cssClass="'underline'"  readonly="true"  />
		 	</tr>
            <tr>
            	 <@pp.datePicker label="'${action.getText('plan.scheduleTime')}'" name="'plan.scheduleTime'" 
            	 		 value="'${(plan.scheduleTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15"  required="true"/>
            	 <@ww.textfield label="'${action.getText('plan.allFee')}'" name="'plan.fee'" value="'${plan.fee?if_exists}'" cssClass="'underline'" readonly="true" />
            </tr>
            <#--
            <tr>
            	<@ww.textfield label="'${action.getText('plan.content')}'" name="'plan.content'" value="'${plan.content?if_exists}'" cssClass="'underline'" />
            </tr>
            -->
            <tr>
            	<@ww.textarea label="'${action.getText('plan.request')}'" 
					         name="'plan.request'" 
					         value="'${plan.request?if_exists}'" rows="'3'" cols="'60'" 
							 />
				<#--
                <@ww.textarea label="'${action.getText('plan.comment')}'" 
					         name="'plan.comment'" 
					         value="'${plan.comment?if_exists}'" rows="'3'" cols="'60'" 
							 />
				-->
            </tr>
            
            <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
            <@eam2008_RecordLog creator="${plan.creator?if_exists}" createdTime="${(plan.createdTime?string('yyyy-MM-dd HH:mm'))?if_exists}" 
		 			   							lastOperator="${plan.lastOperator?if_exists}" lastModifiedTime="${(plan.lastModifiedTime?string('yyyy-MM-dd HH:mm'))?if_exists}"/>
		 	<tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
		 	<tr>
		 		<#assign docText = ""/>
		 		<#assign docState = ""/>
		 		<#if job?exists>
		 			<#assign docText = "${job.comment?if_exists}" />
		 			<#if job.docState?exists>
		 				<#assign docState = "${job.docState.value?if_exists}"/>
		 			</#if>
		 		<#else>
		 			<#assign docText = "${plan.submitComment?if_exists}" />
		 			<#assign docState = "${plan.status?if_exists}"/>
		 		</#if>
            	<@ww.textfield label="'${action.getText('audit.explain')}'" name="'approv.comment'" value="'${docText}'" cssClass="'underline'" />
		 		<@ww.textfield label="'${action.getText('state')}'" name="'state'" value="'${docState}'" cssClass="'underline'" readonly="true"/>
		 	</tr>
		 	<tr>
	        	<td align="right" valign="top"><label class="label">${action.getText('audit.people')}</label></td>
	        	<#assign wfDocId = "${plan.class.name}" />
	        	<td>
	        		<select name="approverIds" multiple="multiple" style="display:none" />
	        		<input type="text" name="approver.names" 
	        			class="underline"  value="${approvers?if_exists}"  maxlength="150" />
		    		<a onClick="approver_OpenDialog('${wfDocId}');">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        	<@ww.select label="'${action.getText('last.audit.people')}'"
	                    	name="'finalApproverId'"
	                   	 	listKey="id" 
	                   	 	listValue="name"
	                    	list="docFinalApprovers"
	                    	emptyOption="true" disabled="false"
	                    	required="true"
	        		/>
		        </td>
        	</tr>
        </@inputTable>
       	<@buttonBar>
       		<input type="button" name="close" value="${action.getText('close')}" class="button" onclick="window.close()" style="display:none">
		 	<@vsubmit name="save" value="'${action.getText('save')}'" onclick="'return validate()'"/>
		 	<#if plan.id?exists>
			 	<#if docSubmitted>
			 		<@vsubmit name="'cancelSubmitDoc'" value="'${action.getText('cancel')}'" onclick="'return validate()'"/>
			 	<#else>
			 		<@vsubmit name="'submitDoc'" value="'${action.getText('submit')}'" onclick="'return submitDocValidate()'"/>
			 	</#if>
		 	</#if>
        	<@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/checkPoint/listCheckPointPlans.html"/>	
        </@buttonBar>
    </@ww.form>
    <script language="JavaScript" type="text/JavaScript"> 
       var view = document.forms[0].elements['docview'].value;
		if(view=='1') {
	  		eam2008_hiddenButtonElements(document.forms[0],new Array("close"))
	  	}
	<#if finalApprovers?exists>
				selector = document.forms[0].elements["finalApproverId"];
				var groups=selector.options.length;  
				
				for (i=0; i<groups; i++){
					if (selector.options[i].value== "${finalApprovers}"){
						selector.options[i].selected="true";
					}
				}
	</#if>
	
	<#if docSubmitted>
			disableAllFormElements();
	</#if>
	
	<#if approvers?exists>
			var as = "${approvers}";
			eam2008_setWFApproversInfo(as, document.forms[0], "finalApproverId", approverSelectorHandler);
	</#if>
	
	function disableAllFormElements() {
			
			__disableAllElements__(document.forms[0], new Array("cancelSubmitDoc", "back","close"));
			
			var x = document.forms[0].name + "_" + "plan.scheduleTime" + "_trigger";
			document.forms[0].elements[x].disabled="true";
		}
	
	function approver_OpenDialog(wfDocId) {
	  		var url = "${req.contextPath}/popup/approverSelector.html?wfDoc.id="+wfDocId;
	  		popupModalDialog(url, 800, 600, approverSelectorHandler);
	  	}
	  	
	function approverSelectorHandler(result) {
			if (result.legnth == 0) {
	  			return ;
	  		}
	  		
	  		document.forms["plan"].elements["approver.names"].value = "";
	  		var ary = new Array();
	  		
	  		var selector = document.forms["plan"].elements["approverIds"];
	  		for (i = 0; i < selector.options.length; i ++) {
				selector.options[i] = null;
			}
	  		for (i = 0; i < result.length; i ++) {
				var opt = new Option(result[i][1], result[i][0]);
				opt.selected = "true";
				eval("selector.options[selector.options.length]=opt");
			}	  		
	  		for (i = 0; i < result.length; i ++) {
	  			document.forms["plan"].elements["approver.names"].value += result[i][1];
	  			if (i + 1 < result.length) {
	  				document.forms["plan"].elements["approver.names"].value += ", ";
	  			}
	  		}
	  	}
	 <#--
	window.onload = function () {
	    document.getElementById('selectRule').disabled;
	}
	-->
	 
	function user_OpenDialog() {
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userSelectorHandler);
	  	}
	  	
	function userSelectorHandler(result) {
	  		document.forms["plan"].elements["manager.id"].value  = result[0];
	  		document.forms["plan"].elements["manager.name"].value = result[1];
	  	}  
	  
	function validate(){
		if(!validateTime()){
			return false;
		}else if(!ifNullRuleNo()){
			return false;
		}else if(!ifNullManager()){
			return false;
		}else if(!length_plan_name()){
			return false;
		}
	}
	function submitDocValidate() {
		if ('' == document.forms["plan"].elements["finalApproverId"].value) {
				alert("${action.getText('workflow.final.approver')}");
				return false;
		}
		return validate();
	}
	
	function ifNullManager(){
	  		if ('' == document.forms["plan"].elements["manager.id"].value) {
	  			alert("${action.getText('manager.id.required')}");
	  			return false;
	  		}
	  		return true;
	  	}
	

	function ifNullRuleNo(){
 		var type=document.getElementById("rule.ruleNo").value;
 		s=new String(type);
 		if(s==''){
 			alert("${action.getText('please.select.RuleNo')}");
 			return false;
 		}
 		return true;
 	}	
 	
 	function length_plan_name(){
			var name=document.forms["plan"].elements["plan.name"].value;
			if(!(name=='')){
				var length=name.length;
				if(length>20){
					alert("${action.getText('plan.name.length')}");
					return false;
				}
				return true;
			}
			return true;
		}

	function validateTime(){
		if(document.forms["plan"].elements["plan.scheduleTime"].value ==""){
			alert("${action.getText('please.input.scheduleTime')}");
			return false;
		}
		
		var date=document.getElementById("plan.scheduleTime").value;
		s=new String(date);
		var regu="(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
		var re =new RegExp(regu);
		if (!(re.test(s))){
			alert("${action.getText('please.input.right.scheduleTime')}");
			return false;
		}
		
	    var time = new Date();
	    var year=time.getYear();
	    var month=time.getMonth()+1;
	    var day=time.getDate();
	    var hour=time.getHours();
	    var second=time.getSeconds();
	    
		var scheduleTime=document.forms["plan"].elements["plan.scheduleTime"].value;
		
		var scheduleYear=scheduleTime.substr(0,4);
		var scheduleMonth=scheduleTime.substr(5,2);
		var scheduleDay=scheduleTime.substr(8,2);
		var scheduleHour=scheduleTime.substr(11,2);
		var scheduleSecond=scheduleTime.substr(14,2);
	
		if(year>scheduleYear){
			alert("${action.getText('afresh.input.scheduleTime')}");
			return false;
		}else if(year==scheduleYear&&month>scheduleMonth){
			alert("${action.getText('afresh.input.scheduleTime')}");
			return false;
		}else if(year==scheduleYear&&month==scheduleMonth&&day>scheduleDay){
			alert("${action.getText('afresh.input.scheduleTime')}");
			return false;
		}
		return true;
	}
	function checkPointRule_openDialog() {
	<#--
		//alert("plan.docStatus=="+${req.getParameter('plan.docStatus')?if_exists});
		-->
		popupModalDialog('${req.contextPath}/popup/checkPointRuleSelector.html',700,600,checkPointRuleSelectorHandler);
		
	}
	function checkPointRuleSelectorHandler(result) {
		document.forms["plan"].elements["rule.id"].value = result[0];
		document.forms["plan"].elements["rule.ruleNo"].value = result[1];
		document.forms["plan"].elements["rule.name"].value = result[2];
		document.forms["plan"].elements["device.id"].value = result[3];
		document.forms["plan"].elements["device.name"].value = result[4];
		document.forms["plan"].elements["device.deviceNo"].value = result[5];
		document.forms["plan"].elements["device.department.name"].value = result[6];
		document.forms["plan"].elements["plan.request"].value = result[7];
		document.forms["plan"].elements["manager.name"].value = result[8];
		document.forms["plan"].elements["plan.ruleType"].value = result[9];
		document.forms["plan"].elements["manager.id"].value = result[10];
		
	}
	</script>
    <#if plan.id?exists>
	<script language="JavaScript" type="text/JavaScript"> 
		window.onload = function () {
			var url = '${req.contextPath}/runmaintenance/checkPoint/listCheckPointPlanDetail.html?plan.id=' + ${plan.id}+'&plan.docStatus='+${plan.docStatus};
	 		document.all.frame.src= url;
	 		document.getElementById("planItems").className = "selectedtab";
	 		if(${plan.docStatus}==2){
	 			__disableAllElements__(document.forms[0], new Array("back"));
	 		}
	 	}
	</script>
	</#if>

	<#if plan.id?exists>
		<ul id="beautytab">
			<li><a id="planItems" href="listCheckPointPlanDetail.html?plan.id=#{plan.id}&plan.docStatus=#{plan.docStatus}" target="frame" class="selectedtab">${action.getText('plan.details')}</a></li>
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>