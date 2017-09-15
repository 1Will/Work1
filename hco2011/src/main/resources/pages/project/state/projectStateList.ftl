<#include "../../includes/hco2011.ftl" />
<@framePage>
	<@ww.form name="'listForm'" action="'searchContractState'" method="'post'">
		<@ww.token name="searchContractStateToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        
        <#if projectInfo?exists>
        <#if projectInfo.id?exists>
            <@ww.hidden name="'projectInfo.id'" value="#{projectInfo.id}"/>
        </#if>
        </#if>
        <#assign returnName='replaceWord'>
        <#assign itemNo=1/>
		<@list title="" excel=false setupTable=false includeParameters="advisory.id|projectInfo.id" fieldMap="like:" >
			<@vcolumn title="${action.getText('序号')}">
				#{itemNo}
			<@alignCenter />
			</@vcolumn>
			<#assign itemNo=itemNo + 1/>
			
	 
			<@vcolumn title="${action.getText('变更前状态')}" property="beforeState.name" >
			<@alignCenter/>
			</@vcolumn>  
			<@vcolumn title="${action.getText('变更后状态')}" property="newState.name" >
			<@alignCenter/>
			</@vcolumn>  
			<@vcolumn title="${action.getText('变更说明')}" property="explain" >
			<@alignLeft attributes="width:500;"/>
			
			<#assign returnName=returnName +'replaceWord'>
            <@ww.hidden name="'${returnName}'" value="'${object.explain?if_exists}'"/>
            <span title="${object.explain?if_exists}">
	            <script>
	            	var s = getObjByName('${returnName}').value;
	            	s=s.replace(/[\r\n]/g, "");
	            	document.write(s.slice(0,18)+"...");
	            </script>
            </span>
			</@vcolumn>
		</@list>
    </@ww.form>
    
</@framePage>