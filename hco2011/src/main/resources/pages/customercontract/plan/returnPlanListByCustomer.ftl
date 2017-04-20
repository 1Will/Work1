
<#include "../../includes/hco2011.ftl" />
<@framePage title="">
	<@ww.form name="'listFrom'" namespace="'/contractManagement'" action="'searchReturnPlan'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchReturnPlanToken"/>
        <@list title="" 
            includeParameters="contractManagement.code|returnPlan.contractManagement|returnPlan.paytime_start|returnPlan.paytime_end
            |returnPlan.planDate_start|returnPlan.planDate_end|customerInfo.code|customerInfo.name|readOnly|onlyInvalid|onlyValid|customerInfo.id|contactArchives.id|" 
        	fieldMap="like:,date:" >
            <@vcolumn title="${action.getText('contractManagement.code')}" property="contractManagement.code" sortable="desc">
                <a href="javascript:contract_OpenDialog('#{object.id}')">${object.contractManagement.code}</a>
				<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('returnPlan.contractManagement')}" property="contractManagement.contractName" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.code')}" property="customerInfo.code" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.name')}" property="customerInfo.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('returnPlan.contactArchives')}" property="contactArchives.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('returnPlan.phone')}" property="phone" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('returnPlan.planDate')}" property="planDate" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter/><#-- attributes="width:110;"-->
            </@vcolumn>
            <@vcolumn title="${action.getText('returnPlan.sum')}" property="sum" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('returnPlan.batch')}" property="batch.name" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
             <#assign isOrNot=""/>
            <#if (object.isOrNot)=='0'>
            	<#assign isOrNot="${action.getText('是')}">
            <#else>
           	 	<#assign isOrNot="${action.getText('否')}">
		    </#if>
            <@vcolumn title="${action.getText('returnPlan.isOrNot')}"sortable="desc">
            	${isOrNot?if_exists}
            	<@alignLeft/>
            </@vcolumn>
             <#assign notOrIs=""/>
            <#if (object.notOrIs)=='0'>
            	<#assign notOrIs="${action.getText('是')}">
            <#else>
           	 	<#assign notOrIs="${action.getText('否')}">
		    </#if>
            <@vcolumn title="${action.getText('returnPlan.notOrIs')}" sortable="desc">
            	${notOrIs?if_exists}
            	<@alignLeft/>
            </@vcolumn>
              <#assign billingOrNot=""/>
            <#if (object.billingOrNot)=='0'>
            	<#assign billingOrNot="${action.getText('是')}">
            <#else>
           	 	<#assign billingOrNot="${action.getText('否')}">
		    </#if>
            <@vcolumn title="${action.getText('returnPlan.billingOrNot')}" property="billingOrNot" sortable="desc">
            	${billingOrNot?if_exists}
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('returnPlan.paytime')}" property="paytime" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter/><#-- attributes="width:110;"-->
            </@vcolumn>
             <@vcolumn title="${action.getText('returnPlan.factSum')}" property="factSum" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
             <@vcolumn title="${action.getText('returnPlan.payee')}" property="payee.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
    </@ww.form>
</@framePage>
<script language="javascript">
	function contract_OpenDialog(id){
			 var url = "${req.contextPath}/contractManagement/editReturnPlan.html?returnPlan.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=popWindowFlag";
	  		 //popupModalDialog(url, 800, 600);
	  		 openNewWindow(url);
	 }
</script>