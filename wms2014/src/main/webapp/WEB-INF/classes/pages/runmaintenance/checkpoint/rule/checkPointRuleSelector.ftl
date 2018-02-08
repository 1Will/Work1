<#include "../../../includes/macros.ftl" />
<@htmlPage title="${action.getText('checkPiontRule.title')}">
<base target="_self">
	 <@ww.form name="'listForm'" action="'searchCheckPointRuleSelector'" method="'post'">
	 <@ww.token name="searchCheckPointRuleSelectorToken"/>
	 	<#include "./checkPointRuleSearcher.ftl"/>
         <@buttonBar> 
	 		<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>     
        </@buttonBar>
        <@list title="${action.getText('checkPointRule.list')}" includeParameters="id|name|deviceNo|deviceName|ruleNo|ruleType.id|department.id|docState.code" fieldMap="like:id|name|deviceNo|deviceName|ruleNo|ruleType.id" >
            <@vcolumn title="${action.getText('rule.no')}" property="ruleNo" sortable="desc">
                <a href="javascript: returnDialog(new Array(#{object.id},'${object.ruleNo}', '${object.name}','${object.device.id}', '${object.device.name}','${object.device.deviceNo}', '${object.device.department.name}' ,'${object.request}','${object.manager.name}','${object.ruleType.value}','${object.manager.id}'));">${object.ruleNo}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('rule.name')}" property="name" sortable="desc"/>
            <@vcolumn title="${action.getText('rule.device.no')}" property="device.deviceNo"/>
            <@vcolumn title="${action.getText('rule.device.name')}" property="device.name"/>
            <@vcolumn title="${action.getText('rule.device.dept')}" property="device.department.name"/>
            <@vcolumn title="${action.getText('rule.ruleType.value')}" property="ruleType.value"/>
            <@vcolumn title="${action.getText('rule.manager')}" property="manager.name"/>
            <@vcolumn title="${action.getText('rule.comment')}" property="comment"/>
            <#assign status = "${object.status}"/>
            <#if object.job?exists>
            	<#assign status="${object.job.docState.value}"/>
            </#if>
            <@vcolumn title="${action.getText('rule.status')}">${status}</@vcolumn>
        </@list>
	</@ww.form>
	 <script language="javascript">
	      window.onload=function(){
	      document.forms[0].elements["docState.code"].disabled=true;
	      document.forms[0].elements["docState.code"].value = 'DOC_APPROVED';
	      }
	</script>
</@htmlPage>