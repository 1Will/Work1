
<#include "../../includes/hco2011.ftl" />
<@framePage title="">
	<@ww.form name="'listFrom'" namespace="'/contractManagement'" action="'searchBillingRecord'" method="'post'">
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchBillingRecordToken"/>
        <@list title="" 
            includeParameters="contractManagement.code|customerInfo.code|billingRecord.customerInfo
            |billingRecord.contractManagement|billingRecord.billingTime_start|billingRecord.billingTime_end
            |billingRecord.code|contractManagement.id|readOnly|onlyInvalid|onlyValid|customerInfo.id|" 
        	fieldMap="like:" >
            <@vcolumn title="${action.getText('批次')}" property="payee" sortable="desc">
				<a href="javascript:contract_OpenDialog('#{object.id}')">${object.batch.name}</a>
				<@alignRight/>
            </@vcolumn>
            <#--
             <@vcolumn title="${action.getText('billingRecord.contractManagement')}" property="contractManagement.contractName" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('customerInfo.code')}" property="customerInfo.code" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('billingRecord.customerInfo')}" property="customerInfo.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('billingRecord.code')}" property="code" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('billingRecord.invoiceTitle')}" property="invoiceTitle" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            -->
            
           	<@vcolumn title="${action.getText('计划开票金额')}" property="planSum" sortable="desc">
				#{object.planSum}
            	<@alignRight/>
            </@vcolumn>
            
           	<@vcolumn title="${action.getText('已开票金额')}" property="hasBillSum" sortable="desc">
				#{object.hasBillSum}
            	<@alignRight/>
            </@vcolumn>
            
           	<@vcolumn title="${action.getText('本次开票金额')}" property="sum" sortable="desc">
				#{object.sum}
            	<@alignRight/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('剩余开票金额')}" property="restSum" sortable="desc">
				#{object.restSum}
            	<@alignRight/>
            </@vcolumn>
            
            <#assign isPay=""/>
            <#if (object.isPay)=='0'>
            	<#assign isPay="${action.getText('是')}">
            <#else>
           	 	<#assign isPay="${action.getText('否')}">
		    </#if>
            <@vcolumn title="${action.getText('收款')}" sortable="desc">
            	${isPay?if_exists}
            	<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('billingRecord.payee')}" property="payee.name" sortable="desc">
				<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('billingRecord.billingTime')}" property="billingTime" format="yyyy-MM-dd" sortable="desc">
            	<@alignLeft/><#-- attributes="width:110;"-->
            </@vcolumn>
        </@list>
    </@ww.form>
</@framePage>
<script language="javascript">
	function contract_OpenDialog(id){
			 var url = "${req.contextPath}/contractManagement/editBillingRecord.html?billingRecord.id="+ id +"&readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=popWindowFlag";
	  		 //popupModalDialog(url, 800, 600);
	  		 openNewWindow(url);
	 }
</script>