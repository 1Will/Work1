<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('checkPointProc.profileTitle')}">
 <@ww.form  name="'checkPointProcs'" action="'saveCheckPointProcs'" method="'post'" validate="true">
      <@ww.hidden name="'proc.id'" value="'${req.getParameter('proc.id')?if_exists}'"/>  
      <@ww.hidden name="'docview'" value="'${req.getParameter('view')?if_exists}'"/>
     <@ww.token name="saveCheckPointProcsToken"/>  
    	<@buttonBar>
    		<@vbutton class="button" value="${action.getText('checkPoint.selectPlan')}" onclick="eam2008_checkPointPlan_OpenDialog('${req.contextPath}/popup/checkPointPlanSelector.html');"/>
    	</@buttonBar>
        <@inputTable>
        <tr>
           <@ww.hidden name="'plan.id'" value="'${plan.id?if_exists}'"/>
        </tr>
            <tr>
            	<@ww.textfield label="'${action.getText('checkPointProc.number')}'" name="'proc.procNo'" value="'${proc.procNo?if_exists}'" cssClass="'underline'" readonly="true" disabled="true"/>
            	<@ww.textfield label="'${action.getText('checkPointProc.name')}'" name="'proc.name'" value="'${proc.name?if_exists}'" cssClass="'underline'" required="true"/>
            </tr>
            <tr>
                <@ww.textfield label="'${action.getText('checkPointPlan.number')}'" name="'proc.plan.planNo'" value="'${plan.planNo?if_exists}'" cssClass="'underline'" disabled="true" required="true" readonly="true"/>
                <@ww.textfield label="'${action.getText('checkPointPlan.name')}'" name="'proc.plan.name'" value="'${plan.name?if_exists}'" cssClass="'underline'" disabled="true" required="true" readonly="true"/>
            </tr>
            <tr>
             <@eam2008_DeviceSelector />
            </tr>
            <tr>
            	<@ww.textfield label="'${action.getText('checkPointPlan.planTime')}'" name="'proc.plan.scheduleTime'"
			     			value="'${(proc.plan.scheduleTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" disabled="true" readonly="true"/>
			    <@pp.datePicker label="'${action.getText('checkPointProc.actualTime')}'" name="'proc.procExecTime'" value="'${(proc.procExecTime?string('yyyy-MM-dd'))?if_exists}'" size="15" cssClass="'underline'"  />
            </tr>
            
            <#assign managerName = ''/>
            <#if proc.id?exists>
		 			<#assign managerName = "${proc.manager.name}"/>
		 		<#else>
		 			<#if plan.id?exists>
		 				<#assign managerName = "${plan.manager.name}"/>
		 			</#if>
		 		</#if>	
            <tr>
            	<@ww.textfield label="'${action.getText('checkPointPlan.manager')}'" name="'proc.plan.manager.name'"
			     			value="'${managerName?if_exists}'" cssClass="'underline'" size="15" disabled="true" readonly="true"/>
			    <td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('checkPointProc.manager')}:</label></td>
		 		<td>
		 			<input type="text" name="proc.manager.name" class="underline"  value="${managerName?if_exists}"  maxlength="150" disabled/>
		 			<a onClick='user_OpenDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>
		 		<#assign managerId = ''/>
		 		<#if proc.id?exists>
		 			<#assign managerId = "${proc.manager.id}"/>
		 		<#else>
		 			<#if plan.id?exists>
		 				<#assign managerId = "${plan.manager.id}"/>
		 			</#if>
		 		</#if>	
		 		<@ww.hidden name="'proc.manager.id'" value="'${managerId}'"/>  
			</tr>
			<#--
			    <@ww.textfield label="'${action.getText('checkPointProc.manager')}'" name="'proc.manager.name'"
			     			value="'${proc.manager.name?if_exists}'" cssClass="'underline'" size="15" disabled="true" readonly="true"/>
			     <@ww.hidden name="'proc.manager.id'" value="'${proc?if_exists && plan.manager.id?if_exists}'"/>  
            </tr>
            -->
            <tr>
             <#assign planSeeSum = 0/> 
              <#if (plan.planDetails.size()>0)>
		           <#list plan.planDetails as planDetail>
		            <#assign planSeeSum=planSeeSum+planDetail.fee />
		           </#list>
		      </#if>
		       
             <@ww.textfield label="'${action.getText('procDetail.planCollect')}'" name="'proc.planExpenseCollection'" value="'${planSeeSum?if_exists}'" size="15" cssClass="'underline'"  disabled="true" readonly="true"/>
             <@ww.textfield label="'${action.getText('procDetail.actualCollect')}'" name="'proc.actualExpenseCollection'" value="'${actualExpenseCollection?if_exists}'" size="15" cssClass="'underline'" disabled="true" readonly="true"/>
            </tr>
            <tr>
            	<@ww.textarea label="'${action.getText('checkPointProc.require')}'" 
					         name="'proc.plan.require'" 
					         value="'${plan.require?if_exists}'" rows="'3'" cols="'60'" 
					         disabled="true"
					         readonly="true" 
							 />
				<@ww.textarea label="'${action.getText('procDetail.result')}'" 
					         name="'proc.summary'" 
					         value="'${proc.summary?if_exists}'" rows="'3'" cols="'60'" 
							 />
            </tr>
            <#--
            <tr>
            	<@ww.textarea label="'${action.getText('checkPointPlan.content')}'" 
					         name="'proc.plan.content'" 
					         value="'${plan.content?if_exists}'" rows="'3'" cols="'60'" 
					         disabled="true"
					         readonly="true"
							 />
				<@ww.textarea label="'${action.getText('checkPointProc.comment')}'" 
					         name="'proc.comment'" 
					         value="'${proc.comment?if_exists}'" rows="'3'" cols="'60'" 
							 />
            </tr>
            -->
            <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
            <tr>
            <@eam2008_RecordLog
              creator="${proc.creator?if_exists}" 
                 createdTime="${(proc.createdTime?string('yyyy-MM-dd HH:mm'))?if_exists}" 	
                 lastOperator="${proc.lastOperator?if_exists}"
                  lastModifiedTime="${(proc.lastModifiedTime?string('yyyy-MM-dd HH:mm'))?if_exists}"/>
            </tr>
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
		 			<#assign docText = "${proc.submitComment?if_exists}" />
		 			<#assign docState = "${proc.status?if_exists}"/>
		 		</#if>
            	<@ww.textfield label="'${action.getText('audit.explain')}'" name="'approv.comment'" value="'${docText}'" cssClass="'underline'" />
		 		<@ww.textfield label="'${action.getText('state')}'" name="'state'" value="'${docState}'" cssClass="'underline'" readonly="true"/>
		 	</tr>
		 	<tr>
	        	<td align="right" valign="top"><label class="label">${action.getText('audit.people')}</label></td>
	        	<#assign wfDocId = "${proc.class.name}" />
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
       	    <@vsubmit value="'${action.getText('save')}'" onclick="'return validateTime()'"/>
       	    <#if proc.id?exists>
			 	<#if docSubmitted>
			 		<@vsubmit name="'cancelSubmitDoc'" value="'${action.getText('cancel')}'" onclick="'return validateTime()'"/>
			 	<#else>
			 		<@vsubmit name="'submitDoc'" value="'${action.getText('submit')}'" onclick="'return submitDocValidate()'"/>
			 	</#if>
		 	</#if>
        	<@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/checkPoint/listCheckPointProcs.html"/>	
        </@buttonBar>
    </@ww.form>
    <script language="JavaScript" type="text/JavaScript"> 
    	var view = document.forms[0].elements['docview'].value;
		if(view=='1') {
	  		eam2008_hiddenButtonElements(document.forms[0],new Array("close"))
	  	}
    <#if finalApprover?exists>
				selector = document.forms[0].elements["finalApproverId"];
				var groups=selector.options.length;  
				for (i=0; i<groups; i++){
					if (selector.options[i].value== "${finalApprover}"){
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
			var x = document.forms[0].name + "_" + "proc.procExecTime" + "_trigger";
			document.forms[0].elements[x].disabled="true";
		}
	 	function submitDocValidate() {
	 		if ('' == document.forms[0].elements["finalApproverId"].value) {
				alert("${action.getText('workflow.final.approver')}");
				return false;
			}
	 		return validateTime();
	 	}
	
	function validateTime(){
		if(document.forms[0].elements["proc.procExecTime"].value ==""){
			alert("${action.getText('proc.nullMessage')}");
			return false;
		} 
        var   s   =document.forms[0].elements["proc.procExecTime"].value;
        if(!(s=='')){
        		var regu="(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
        		var re =new RegExp(regu);
        		if (!re.test(s)){
        			alert("${action.getText('please.input.procExecTime_start')}");
        			return false;
        		}
        		else{
        		   if(!validateProcTime(s)){
        		     alert("${action.getText('please.input.procExecTime_start')}");
        		      return false;
        		   }
        		}
        	}
	    var time =new   Date(Date.parse(s.replace(/-/g,   "/"))); 
	    var year=time.getYear();
	    var month=time.getMonth();
	    var day=time.getDate();
	    var hour=time.getHours();
	    var second=time.getSeconds();
	    s=document.forms[0].elements["proc.plan.scheduleTime"].value;
		var scheduleTime=new   Date(Date.parse(s.replace(/-/g,   "/"))); 
		
		var scheduleYear=scheduleTime.getYear();
		var scheduleMonth=scheduleTime.getMonth();
		var scheduleDay=scheduleTime.getDate();
		var scheduleHour=scheduleTime.getHours();
		var scheduleSecond=scheduleTime.getSeconds();
		if(year<scheduleYear){
			alert("${action.getText('proc.dateMessage')}");
			return false;
		}else if(month<scheduleMonth&&year==scheduleYear){
			  alert("${action.getText('proc.dateMessage')}");
			  return false;
		}else if(day<scheduleDay&&month==scheduleMonth&&year==scheduleYear){
			  alert("${action.getText('proc.dateMessage')}");
			  return false;
		}
		     return true;
	}
	
	  function validateProcTime(validateTime){
                    var timeYear=validateTime.substring(0,validateTime.indexOf('-')).length;
                    var timeDay=validateTime.substring(validateTime.lastIndexOf('-')+1,validateTime.length).length;
                    if(timeYear>4)
                    {
                        return false;
                    }
                    if(timeDay>2)
                    {
                        return false;
                    }
                    return true;

     }
	
	function eam2008_checkPointPlan_OpenDialog(url) {
		popupModalDialog(url, 800, 600, refresh_checkPointPlan_information);
		document.forms[0].elements["proc.procNo"].value  ="undefined";
		document.forms[0].elements["proc.name"].value  = "undefined";
		
		var url = "saveCheckPointProcs.html?plan.id="+document.forms[0].elements["plan.id"].value;
		url += "&device.id="+document.forms[0].elements["device.id"].value;
		document.forms[0].elements["proc.procNo"].value  ="";
		document.forms[0].elements["proc.name"].value  = "";
		
		document.forms[0].location.href=url;	
	}
	
	 function user_OpenDialog() {
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userSelectorHandler);
	 }	
	
	function userSelectorHandler(result) {
	  		document.forms[0].elements["proc.manager.id"].value  = result[0];
	  		document.forms[0].elements["proc.manager.name"].value = result[1];
	  	}
	  	
	function approver_OpenDialog(wfDocId) {
	  		var url = "${req.contextPath}/popup/approverSelector.html?wfDoc.id="+wfDocId;
	  		popupModalDialog(url, 800, 600, approverSelectorHandler);
	  	}
	
	  	function approverSelectorHandler(result) {
	  		document.forms["checkPointProcs"].elements["approver.names"].value = "";
	  		var ary = new Array();
	  		
	  		var selector = document.forms["checkPointProcs"].elements["approverIds"];
	  		for (i = 0; i < selector.options.length; i ++) {
				selector.options[i] = null;
			}
	  		for (i = 0; i < result.length; i ++) {
				var opt = new Option(result[i][1], result[i][0]);
				opt.selected = "true";
				eval("selector.options[selector.options.length]=opt");
			}	  		
	  		for (i = 0; i < result.length; i ++) {
	  			document.forms["checkPointProcs"].elements["approver.names"].value += result[i][1];
	  			if (i + 1 < result.length) {
	  				document.forms["checkPointProcs"].elements["approver.names"].value += ", ";
	  			}
	  		}
	  	}
	
    function refresh_checkPointPlan_information (result) 
    {
	    document.forms[0].elements["plan.id"].value  = result[0];
		document.forms[0].elements["proc.plan.planNo"].value  = result[1];
		document.forms[0].elements["proc.plan.name"].value = result[2];
		document.forms[0].elements["device.deviceNo"].value = result[3];
		document.forms[0].elements["device.name"].value = result[4];
		document.forms[0].elements["device.id"].value = result[5];
		document.forms[0].elements["device.department.name"].value = result[6];

		document.forms[0].elements["proc.plan.scheduleTime"].value = result[8];
/*		document.forms[0].elements["proc.plan.content"].value = result[9];
	document.forms[0].elements["proc.plan.comment"].value = result[10]; */

		document.forms[0].elements["proc.plan.require"].value = result[11];
		
		document.forms[0].elements["proc.plan.manager.name"].value = result[12];
		document.forms[0].elements["proc.manager.name"].value  = result[12];
		document.forms[0].elements["proc.manager.id"].value  = result[13];
		document.forms[0].elements["proc.planExpenseCollection"].value  = result[14];
	}

</script>
        <#if proc.id?exists>
        <script language="JavaScript" type="text/JavaScript"> 
		window.onload = function () {
	 		document.all.frame.src='${req.contextPath}/runmaintenance/checkPoint/listCheckPointProcsDetail.html?proc.id=${proc.id?if_exists}';
	 		getObjByNameRe("checkPointProcItems").className = "selectedtab";
	 	}
	 </script>
       </#if>
    <#if proc.id?exists>
    <ul id="beautytab">
			<li><a id="checkPointProcItems"   onclick="activeTab(this);" href='${req.contextPath}/runmaintenance/checkPoint/listCheckPointProcsDetail.html?proc.id=${proc.id}' target="frame" class="selectedtab">${action.getText('checkPointProc.detail')}</a></li>
    </ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
   </#if>
</@htmlPage>