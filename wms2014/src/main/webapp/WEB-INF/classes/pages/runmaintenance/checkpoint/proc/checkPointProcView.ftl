<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('checkPointProc.profileTitle')}">
 <@ww.form  name="'checkPointProcs'" action="'saveCheckPointProcs'" method="'post'">
  <@ww.hidden name="'proc.id'" value="'${req.getParameter('proc.id')?if_exists}'"/>  
     <@ww.token name="saveCheckPointProcsToken"/>  
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
            <tr>
            	<@ww.textarea label="'${action.getText('checkPointProc.require')}'" 
					         name="'proc.plan.require'" 
					         value="" rows="'3'" cols="'60'" 
					         disabled="true"
					         readonly="true" 
							 />
            	<@ww.textarea label="'${action.getText('checkPointPlan.content')}'" 
					         name="'proc.plan.content'" 
					         value="'${plan.require?if_exists}'" rows="'3'" cols="'60'" 
					         disabled="true"
					         readonly="true"
							 />
            </tr>
            <tr>
            	<@ww.textarea label="'${action.getText('procDetail.result')}'" 
					         name="'proc.summary'" 
					         value="'${plan.content?if_exists}'" rows="'3'" cols="'60'" 
							 />
				<@ww.textarea label="'${action.getText('checkPointProc.comment')}'" 
					         name="'proc.comment'" 
					         value="'${proc.comment?if_exists}'" rows="'3'" cols="'60'" 
							 />
            </tr>
            <tr>
             <@ww.textfield label="'${action.getText('procDetail.planCollect')}'" name="'proc.planExpenseCollection'" value="'${planExpenseCollection?if_exists}'" size="15" cssClass="'underline'"  disabled="true" readonly="true"/>
             <@ww.textfield label="'${action.getText('procDetail.actualCollect')}'" name="'proc.actualExpenseCollection'" value="'${actualExpenseCollection?if_exists}'" size="15" cssClass="'underline'" disabled="true" readonly="true"/>
            </tr>
            <tr>
            <@eam2008_RecordLog
              creator="${proc.creator?if_exists}" 
                 createdTime="${(proc.createdTime?string('yyyy-MM-dd HH:mm'))?if_exists}" 	
                 lastOperator="${proc.lastOperator?if_exists}"
                  lastModifiedTime="${(proc.lastModifiedTime?string('yyyy-MM-dd HH:mm'))?if_exists}"/>
            </tr>
            <tr>
		 		<#assign docText = ""/>
		 		<#assign docState = ""/>
		 		<#if job?exists>
		 			<#assign docText = "${job.comment?if_exists}" />
		 			<#if job.docState?exists>
		 				<#assign docState = "${job.docState.value?if_exists}"/>
		 			</#if>
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
       	   <input type="button" name="close" value="${action.getText('close')}" class="button" onclick="window.close()">
        </@buttonBar>
    </@ww.form>
    <script language="JavaScript" type="text/JavaScript"> 	

		window.onload = function () {
			 <#if proc.id?exists>
				//		document.forms["area"].elements["ext"].click();
		 		document.all.frame.src='${req.contextPath}/runmaintenance/checkPoint/listCheckPointProcsDetail.html?proc.id=${proc.id?if_exists}';
		 		document.getElementById("checkPointProcItems").className = "selectedtab";
		   </#if>
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
			__disableAllElements__(document.forms[0], new Array("close"));
			var x = document.forms[0].name + "_" + "proc.procExecTime" + "_trigger";
			document.forms[0].elements[x].disabled="true";
</script>
    <#if proc.id?exists>
    <ul id="beautytab">
			<li><a id="checkPointProcItems"   onclick="activeTab(this);" href='${req.contextPath}/runmaintenance/checkPoint/listCheckPointProcsDetail.html?proc.id=${proc.id}' target="frame" class="selectedtab">${action.getText('checkPointProc.detail')}</a></li>
    </ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
   </#if>
</@htmlPage>