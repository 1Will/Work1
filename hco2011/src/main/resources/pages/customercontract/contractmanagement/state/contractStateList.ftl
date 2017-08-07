<#include "../../../includes/hco2011.ftl" />
<@framePage>
	<@ww.form name="'listForm'" action="'searchContractState'" method="'post'">
		<@ww.token name="searchContractStateToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        
        <#if contractManagement?exists>
        <#if contractManagement.id?exists>
            <@ww.hidden name="'contractManagement.id'" value="#{contractManagement.id}"/>
        </#if>
        </#if>
        <#assign returnName='replaceWord'>
        <#assign itemNo=1/>
		<@list title="" excel=false setupTable=false includeParameters="advisory.id|id|applicationDoc.id|projectInfo.id" fieldMap="like:" >
			<@vcolumn title="${action.getText('序号')}">
			<#--
				<#if !(action.isReadOnly())>
				<a href="" onclick="uploadEdit(#{object.id})">#{itemNo}</a>
				<#else>
				</#if>
				-->
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
			<@vcolumn title="${action.getText('附件名称')}" property="fileName">
				<a href="downloadContractState.html?contractState.id=#{object.id}" title="${action.getText('download')}">${object.fileName}</a>
			<@alignLeft />
			</@vcolumn> 
			<@vcolumn title="${action.getText('变更人')}" property="creatorName" >
			<@alignCenter/>
			</@vcolumn>
			<@vcolumn title="${action.getText('变更日期')}"  property="uploadDate" format="yyyy-MM-dd">
        		<@alignCenter />
      		</@vcolumn>
		</@list>
    </@ww.form>
    
<script type="text/javascript">
</script>
</@framePage>