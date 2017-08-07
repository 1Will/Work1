<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('expenseForm.searchPage')}">
	<@ww.form name="'listForm'" action="'searchExpenseFormAction'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchExpenseFormActionToken"/>
		<#include "./expenseFormSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms()'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/expenseForm/editExpenseFormAction.html"/>
       		</#if>
        </@buttonBar>
        <@list title="${action.getText('expenseForm.list')}" 
            includeParameters="expenseForm.code|expenseForm.projectInfo.name|expenseForm.applyPeople.name|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:expenseForm.code|expenseForm.projectInfo.name|expenseForm.applyPeople.name|" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="expenseFormIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
          	<@vcolumn title="${action.getText('expenseForm.code')}" property="code" sortable="desc" >
                <a href="editExpenseFormAction.html?expenseForm.id=#{object.id?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">
    				${object.code?if_exists}
                </a>
				<@alignLeft/>
            </@vcolumn>
            
           <@vcolumn title="${action.getText('expenseForm.projectInfo.name')}" property="projectInfo.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('expenseForm.applyPeople.name')}" property="applyPeople.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('expenseForm.money')}" property="money" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
           
            <@vcolumn title="${action.getText('expenseForm.applyDate')}" property="applyDate" format="yyyy-MM-dd" sortable="desc">
     			<@alignCenter/>
            </@vcolumn>
            
        </@list>
        <#if !first>
        <#if !(action.isReadOnly())>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('expenseFormAction.message')}" boxName="expenseFormIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
		</#if>
		</#if>
    </@ww.form>
</@htmlPage>
