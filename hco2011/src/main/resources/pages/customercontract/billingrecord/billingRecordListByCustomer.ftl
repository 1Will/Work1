
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
            <@vcolumn title="${action.getText('contractManagement.code')}" property="payee" sortable="desc">
				<a href="javascript:contract_OpenDialog('#{object.id}')">${object.contractManagement.code}</a>
				<@alignLeft/>
            </@vcolumn>
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
            <@vcolumn title="${action.getText('billingRecord.sum')}" property="sum" sortable="desc">
            #{object.sum?if_exists}
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('billingRecord.billingTime')}" property="billingTime" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter/><#-- attributes="width:110;"-->
            </@vcolumn>
              <@vcolumn title="${action.getText('billingRecord.payee')}" property="payee.name" sortable="desc">
				<@alignLeft/>
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