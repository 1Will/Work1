<#include "../includes/hco2011.ftl" />

<@htmlPage title="${pageTitle?if_exists}${action.getText('查询页面')}">
	<@ww.form name="'listForm'" namespace="'/productionOperation'" action="'searchProductionOperation'" method="'post'">
		<@ww.token name="searchProductionOperationToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'managerType'" value="'${managerType?if_exists}'"/>
		<#include "./productionOperationSearcher.ftl" />
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/productionOperation/editProductionOperation.html?readOnly=${req.getParameter('readOnly')?if_exists}&managerType=${managerType?if_exists}"/>
			</#if>
        </@buttonBar>
         <#assign itemNo=1/>
        <@list title="${pageTitle?if_exists}${action.getText('列表')}" 
            includeParameters="code|name|makeUpPerson.name|managerType|auditingPerson.name|productionOperation.makeUpDate_start|productionOperation.makeUpDate_end|productionOperation.auditingDate_start|productionOperation.auditingDate_end|" 
        	fieldMap="like:code|name|makeUpPerson.name|auditingPerson.name,date:productionOperation.makeUpDate_start|productionOperation.makeUpDate_end|productionOperation.auditingDate_start|productionOperation.auditingDate_end|">
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="productionOperationIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
          	<@vcolumn title="${pageTitle?if_exists}${action.getText('编码')}" property="code" sortable="desc" >
          		<a href="editProductionOperation.html?productionOperation.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}&managerType=${managerType?if_exists}">${object.code}</a>
				<@alignLeft/>
            </@vcolumn>
           <@vcolumn title="${pageTitle?if_exists}${action.getText('名称')}" property="name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('编制人')}" property="makeUpPerson.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('编制日期')}" property="makeUpDate" sortable="desc" format="yyyy-MM-dd">
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('审核人')}" property="auditingPerson.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('审核日期')}" property="auditingDate" sortable="desc" format="yyyy-MM-dd">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
          <#if !first>
        	<#if !(action.isReadOnly())>
		        <@buttonBar>
		          <@crm_disabledOrEnabled_Nodelete_button message="${action.getText('经营计划')}" boxName="productionOperationIds" />
				</@buttonBar>
			</#if>
		</#if>
    </@ww.form>

</@htmlPage>
