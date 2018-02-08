<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('checkPointRule.title1')}">
	<@ww.form  namespace="'/runmaintenance/checkPoint'" name="'rule'" action="'saveCheckPointRule'" method="'post'" validate="'true'">
	  <@ww.hidden name="'rule.id'" value="'${req.getParameter('rule.id')?if_exists}'"/>
		 <@ww.token name="saveRuleToken"/>
		 <@inputTable> 
		 	<tr>
		 		<@ww.textfield label="'${action.getText('rule.no')}'" name="'rule.ruleNo'" value="'${rule.ruleNo?if_exists}'" cssClass="'underline'" readonly="true" disabled="true"/>
		 		<@ww.textfield label="'${action.getText('rule.name')}'" name="'rule.name'" value="'${rule.name?if_exists}'" cssClass="'underline'" readonly="true" disabled="true"/>
		 	</tr>
		 	<@eam2008_DeviceSelector/>
		 	<tr>
        		<#assign manager = ''/>
		 		<#if rule.manager?exists>
	     			<#assign manager = "${rule.manager.name}" />
	     		</#if>
		 		<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('manager')}:</label></td>
		 		<td>
		 			<input type="text" name="manager.name" class="underline"  value="${manager}"  maxlength="150" disabled/>
		 			<a onClick='user_OpenDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>
		 		<#assign managerId = ''/>
		 		<#if rule.manager?exists>
	     			<#assign managerId = "${rule.manager.id}" />
	     		</#if>
		 		<@ww.hidden name="'manager.id'" value="'${managerId}'"/>	 		
        		
        		<@ww.select label="'${action.getText('checkPoint.type')}'" required="true" name="'ruleType.id'" 
        			listKey="id" listValue="value"
        			list="ruleTypes" emptyOption="true" disabled="true">
       			</@ww.select>
		 	</tr>
		 	<@oneLine>
		 	<tr>
				<td align="right" vlign="middle" rowspan="3">
                	<label  class="label" >${action.getText('checkpiont.request')}:</label>
            	</td>
            	<td rowspan="3" colspan="3">
                	<textarea name="rule.request" rows="3" cols="60" readonly="true" disabled="true">${rule.request?if_exists}</textarea>
            	</td> 
           	</tr>
           	<tr>
           		<td align="right" vlign="middle" rowspan="3">
                	<label  class="label">${action.getText('rule.comment')}:</label>
            	</td>
            	<td rowspan="3" colspan="3">
                	<textarea name="rule.comment" rows="3" cols="60" readonly="true" disabled="true">${rule.comment?if_exists}</textarea>
            	</td>
            </tr>
           </@oneLine>
           
	        <tr>
	        	<@ww.textfield label="'${action.getText('rule.period')}'" name="'rule.period'" value="'${rule.period?if_exists}'" cssClass="'underline'" readonly="true" disabled="true"/>
	        	<@pp.datePicker label="'${action.getText('rule.lastCheckTime')}'" name="'rule.lastCheckTime'"
	     							value="'${(rule.lastCheckTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" readonly="true" disabled="true"/>
	     	</tr>
	     	<tr>
	        	<@ww.textfield label="'${action.getText('rule.allFee')}'" name="'rule.fee'" value="'${rule.fee?if_exists}'" cssClass="'underline'" readonly="true" disabled="true"/>
	        </tr>
		 	<tr>
	       		<@eam2008_RecordLog creator="${rule.creator?if_exists}" createdTime="${rule.createdTime?if_exists?datetime}" 
		 			   							lastOperator="${rule.lastOperator?if_exists}" lastModifiedTime="${rule.lastModifiedTime?if_exists?datetime}"/>
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
            	<@ww.textfield label="'${action.getText('audit.explain')}'" name="'approv.comment'" value="'${docText}'" cssClass="'underline'" readonly="true" disabled="true"/>
		 		<@ww.textfield label="'${action.getText('state')}'" name="'state'" value="'${docState}'" cssClass="'underline'" readonly="true" disabled="true"/>
		 	</tr>
		 	<tr>
	        	<td align="right" valign="top" ><label class="label">${action.getText('audit.people')}</label></td>
	        	<#assign wfDocId = "${rule.class.name}" />
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
	<#if rule.id?exists>
	<script language="JavaScript" type="text/JavaScript"> 

		window.onload = function () {
			<#if rule.ruleType?exists>
	 			document.forms["rule"].elements["ruleType.id"].value = #{rule.ruleType.id?if_exists};
	 		</#if>
			var url = '${req.contextPath}/runmaintenance/checkPoint/listCheckPointRuleDetail.html?rule.id=' + ${rule.id};
	 		document.all.frame.src= url;
	 		getObjByNameRe("ruleItems").className = "selectedtab";
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
			
			var x = document.forms[0].name + "_" + "rule.lastCheckTime" + "_trigger";
			document.forms[0].elements[x].disabled="true";
	</script>
	</#if>

	<#if rule.id?exists>
		<ul id="beautytab">
			<li><a id="ruleItems" href="${req.contextPath}/runmaintenance/checkPoint/listCheckPointRuleDetail.html?rule.id=#{rule.id}" target="frame" class="selectedtab">${action.getText('rule.details')}</a></li>
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
	
</@htmlPage>