<#include "../includes/hco2011.ftl" />
<@framePage title="">
	<@ww.form name="'listFrom'" namespace="'/financialManagement'" action="'searchFinancialManagement'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchFinancialManagementToken"/>
        <@list title="" 
            includeParameters="financialManagement.saleman|contractManagement.code|financialManagement.payee|financialManagement.contractManagement|financialManagement.collectionDate_start|financialManagement.collectionDate_end
        	|customerInfo.code|financialManagement.customerInfo|contractManagement.id|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:financialManagement.saleman|contractManagement.code|financialManagement.payee|financialManagement.contractManagement|customerInfo.code|financialManagement.customerInfo
        	,date:financialManagement.collectionDate_start|financialManagement.collectionDate_end">
            
            <@vcolumn title="${action.getText('financialManagement.code')}" property="code" sortable="desc">
                <a href="javascript:financialManagement_OpenDialog('#{object.id}')">${object.code}</a>
				<@alignLeft/>
            </@vcolumn>
            <#-- 
            <@vcolumn title="${action.getText('contractManagement.code')}" property="contractManagement.code" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('financialManagement.contractManagement')}" property="contractManagement.contractName" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('financialManagement.customerInfo')}" property="customerInfo.name" sortable="desc">
     			<@vlh.attribute name="width" value="10%" />
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('financialManagement.saleman')}" property="saleman.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('customerInfo.code')}" property="customerInfo.code" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('financialManagement.batch')}" property="batch.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('financialManagement.sumReceivable')}" property="sumReceivable" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('financialManagement.trueSum')}" property="trueSum" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('financialManagement.totalSum')}" property="totalSum" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('financialManagement.withoutGotSum')}" property="withoutGotSum" sortable="desc">
            	<@vlh.attribute name="width" value="3%" />
            	<@alignLeft/>
            </@vcolumn>
            <#assign invoice=""/>
            <#if (object.invoice)=='0'>
            	<#assign invoice="${action.getText('是')}">
            <#else>
           	 	<#assign invoice="${action.getText('否')}">
		    </#if>
            <@vcolumn title="${action.getText('financialManagement.invoice')}" sortable="desc">
            	${invoice?if_exists}
            	<@alignLeft/>
            </@vcolumn>
                <@vcolumn title="${action.getText('financialManagement.payee')}" property="payee.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
              <@vcolumn title="${action.getText('financialManagement.collectionDate')}" property="collectionDate" format="yyyy-MM-dd" sortable="desc">
              <@vlh.attribute name="width" value="9%" />
            	<@alignCenter/><#-- attributes="width:110;"-->
            </@vcolumn>
             
            <#-- 
             <@vcolumn title="${action.getText('financialManagement.collectionType')}" property="collectionType.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('financialManagement.payment')}" property="payment.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('financialManagement.accountNumber')}" property="accountNumber" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('financialManagement.invoiceCode')}" property="invoiceCode" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('financialManagement.dept')}" property="dept.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>-->
        </@list>
    </@ww.form>
    <script>
    function financialManagement_OpenDialog(id){
	    var url = "${req.contextPath}/financialManagement/editFinancialManagement.html?financialManagement.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=popWindowFlag";
	    openNewWindow(url);
    }
    </script>
</@framePage>