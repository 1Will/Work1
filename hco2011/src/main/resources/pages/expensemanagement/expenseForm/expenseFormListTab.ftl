<#include "../../includes/hco2011.ftl" />

<@framePage title="${action.getText('expenseForm.searchPage')}">
	<@ww.form name="'listForm'" action="'searchExpenseFormAction'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchExpenseFormActionToken"/>
        <@list title="${action.getText('')}" 
            includeParameters="expenseForm.code|expenseForm.projectInfo.name|projectInfo.id|expenseForm.applyPeople.name|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:expenseForm.code|expenseForm.projectInfo.name|expenseForm.applyPeople.name|" >
          	<@vcolumn title="${action.getText('expenseForm.code')}" property="code" sortable="desc" >
                <a href="javascript:expenseForm_OpenDialog(#{object.id?if_exists})">
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
     			<@alignLeft/>
            </@vcolumn>
        </@list>
    </@ww.form>
</@framePage>
<script>
	
	//弹出付款单窗体
	function expenseForm_OpenDialog(id){
	   var url = "${req.contextPath}/expenseForm/editExpenseFormAction.html?expenseForm.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=popWindowFlag";
	   openNewWindow(url);
	 }	 
</script>
