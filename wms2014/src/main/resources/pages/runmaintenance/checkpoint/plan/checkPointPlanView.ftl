<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('checkPiontPlanProfile.title')}">
    <@ww.form namespace="'/runmaintenance/checkPoint'" name="'plan'" action="'saveCheckPointPlan'" method="'post'" validate="true">
    	<@ww.hidden name="'plan.id'" value="'${req.getParameter('plan.id')?if_exists}'"/>
    	<@ww.hidden name="'plan.docStatus'" value="'${req.getParameter('plan.docStatus')?if_exists}'"/>
        <@ww.token name="savePlanToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'${action.getText('plan.no')}'"   name="'plan.planNo'" value="'${plan.planNo?if_exists}'" cssClass="'underline'" readonly="true" disabled="true"/>
                <@ww.textfield label="'${action.getText('plan.name')}'" name="'plan.name'"   value="'${plan.name?if_exists}'" cssClass="'underline'"  readonly="true" disabled="true"/>
            </tr>
			<tr>
			 	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('rule.ruleNo')}:</label></td>
			 	<td>
			 		<#if rule?exists>
				 		<input type="text" name="rule.ruleNo" class="underline"  value="${rule.ruleNo?if_exists}"  maxlength="150" readonly="true" disabled="true"/>
				 	<#else>
				 		<input type="text" name="rule.ruleNo" class="underline"  value=""  maxlength="150" readonly="true" disabled="true"/>
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
            	 <@pp.datePicker label="'${action.getText('plan.scheduleTime')}'" name="'plan.scheduleTime'" 
            	 		 value="'${(plan.scheduleTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15"  readonly="true" disabled="true"/>
            	<@ww.textfield label="'${action.getText('plan.content')}'" name="'plan.content'" value="'${plan.content?if_exists}'" cssClass="'underline'" readonly="true" disabled="true"/>
            </tr>
            <tr>
            	<@ww.textarea label="'${action.getText('plan.request')}'" 
					         name="'plan.request'" 
					         value="'${plan.request?if_exists}'" rows="'3'" cols="'60'" 
							 readonly="true" disabled="true"/>
                <@ww.textarea label="'${action.getText('plan.comment')}'" 
					         name="'plan.comment'" 
					         value="'${plan.comment?if_exists}'" rows="'3'" cols="'60'" 
							 readonly="true" disabled="true"/>
            </tr>
            <@eam2008_RecordLog creator="${plan.creator?if_exists}" createdTime="${plan.createdTime?if_exists?datetime}" 
		 			   							lastOperator="${plan.lastOperator?if_exists}" lastModifiedTime="${plan.lastModifiedTime?if_exists?datetime}"/>
		 	<tr>
		 		<#assign docText = ""/>
		 		<#assign docState = ""/>
		 		<#if job?exists>
		 			<#assign docText = "${job.comment?if_exists}" />
		 			<#if job.docState?exists>
		 				<#assign docState = "${job.docState.value?if_exists}"/>
		 			</#if>
		 		</#if>
            	<@ww.textfield label="'${action.getText('audit.explain')}'" name="'approv.comment'" value="'${docText}'" cssClass="'underline'" readonly="true" disabled="true"/>
		 		<@ww.textfield label="'${action.getText('state')}'" name="'state'" value="'${docState}'" cssClass="'underline'" readonly="true" disabled="true"/>
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
	                    	emptyOption="true" disabled="true"
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
			
			var x = document.forms[0].name + "_" + "plan.scheduleTime" + "_trigger";
			document.forms[0].elements[x].disabled="true";
	</script>
	<#if plan.id?exists>
	<script language="JavaScript" type="text/JavaScript"> 
		window.onload = function () {
			var url = '${req.contextPath}/runmaintenance/checkPoint/listCheckPointPlanDetail.html?plan.id=' + ${plan.id}+'&plan.docStatus='+${plan.docStatus};
	 		document.all.frame.src= url;
	 		getObjByNameRe("planItems").className = "selectedtab";
	 	}
	</script>
	</#if>
	
	<#if plan.id?exists>
		<ul id="beautytab">
			<li><a id="planItems" href="${req.contextPath}/runmaintenance/checkPoint/listCheckPointPlanDetail.html?plan.id=#{plan.id}&plan.docStatus=#{plan.docStatus}" target="frame" class="selectedtab">${action.getText('plan.details')}</a></li>
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>