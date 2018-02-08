<#include "../../includes/hco2011.ftl" />
<@framePage>
	<@ww.form name="'listForm'" namespace="'/projectInfo'" action="'searchProPlanTemplet'" method="'post'">
		<@ww.token name="searchProPlanTempletToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'backCheckBox'" value="'${backCheckBox?if_exists}'"/>
		<#include "./proPlanTempletSearcher.ftl" />
		<@buttonBar>
		<@vsubmit value="'${action.getText('search')}'"  />
        </@buttonBar>
        <#assign itemNo=1/>
 	     <@list title="计划模板列表" 
        includeParameters="proPlanTemplet.name|proPlanTemplet.proplanName|proPlanTemplet.code|backCheckBox|" 
        fieldMap="like:proPlanTemplet.name|proPlanTemplet.proplanName|proPlanTemplet.code|" >
	            <@vlh.checkbox property="id" name="proPlanTempletIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
              <@vcolumn title="${action.getText('序号')}" property="id" >
              <#if backCheckBox?exists && backCheckBox =='backCheckBox'>
              <a href="javascript: returnDialog(new Array(<#if object.name?exists>'${object.proplanName}'</#if>,<#if object.priority?exists>'${object.priority}'</#if>,<#if object.outline?exists>'${object.outline}'</#if>));">
	            	#{itemNo}
	            </a>
              <#else>
	          <a href="${req.contextPath}/projectInfo/editProPlanTemplet.html?proPlanTemplet.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">#{itemNo}</a> 
             </#if>
            <@alignLeft/>
            </@vcolumn>
            <#assign itemNo=itemNo + 1/>
            <@vcolumn title="${action.getText('code')}"  property="code">
            <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('name')}"  property="name">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('proPlanTemplet.proplanName')}"  property="proplanName">
            <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('proPlanTemplet.priority')}"  property="priority">
            <@alignRight/>
            </@vcolumn>
            
        </@list>
         <#if !first>
         <#if backCheckBox?exists && backCheckBox =='backCheckBox'>
         <#else>
			<@buttonBar>
			         <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/projectInfo/editProPlanTemplet.html"/>
					<#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('该计划模板')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDeletes(\"proPlanTempletIds\", \"${confirmMessage}\");'"/>
		            </@vsubmit>
			</@buttonBar>
		</#if>
		</#if>
    </@ww.form>
</@framePage>
