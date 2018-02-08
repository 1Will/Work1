<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="申购单汇总查询">
	 <@ww.form name="'listForm'" action="'searchSubscribeCollectBill'" method="'post'">
	  <@ww.token name="searchListSubscribeCollectBillToken"/>
	 	<#include "subscribeCollectBillSearch.ftl"/>
	 	
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
            <#--
             <#if !(action.isReadOnly())>
          	  <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/devicesubscribeSummary/editSubscribeCollectBill.html"/>
       		</#if>
       		-->
        </@buttonBar>
     
        <@list title="${action.getText('申购总汇单列表')}" 
               includeParameters="id|code|name|code|collectDept.id|collectPerson.name|collectDate|billStatus|onlyInvalid|onlyValid" 
               fieldMap="like:code|name|collectPerson">
            <#--
            <@vlh.checkbox property="id" name="subscribeCollectBillIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            -->
            <@vcolumn title="${action.getText('subscribeCollectBill.code')}" property="code" sortable="asc" >
            <@alignLeft/>
                <a href="editSubscribeCollectBill.html?subscribeCollectBill.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('subscribeCollectBill.name')}" property="name" sortable="asc">
            <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('subscribeCollectBill.collectDept')}" property="collectDept.name" sortable="asc">
           <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('subscribeCollectBill.collectPerson')}" property="collectPerson.name" sortable="asc">
           <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('subscribeCollectBill.collectDate')}" property="collectDate"  format="yyyy-MM-dd" sortable="asc">
           <@alignCenter/>
           </@vcolumn>
           <@vcolumn title="汇总项" property="sumDetail" >
            	<@alignLeft />
           </@vcolumn>
           <@vcolumn title="取消数" property="calNum" >
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="采购项" property="purNum" >
            	<@alignLeft />
            </@vcolumn>
               <@vcolumn title="入库项" property="insNum" >
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="待确认数" property="conNum" >
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="金额" property="totalMoney" format="${action.getText('currencyFormat')}">
            	<@alignLeft />
            </@vcolumn>
            
            
           <#assign subscribeCollectBillStatus = ''/>
            <#if object.billStatus?exists>
       			<#if '${object.billStatus}' == 'NEW'>
       			<#assign subscribeCollectBillStatus = "${action.getText('NEWPURCHASE')}"/>
       			<#elseif '${object.billStatus}' == 'PURCHASING'>
       			<#assign subscribeCollectBillStatus = "${action.getText('PURCHASING')}"/>
       			<#elseif '${object.billStatus}' == 'PURCHASEED'>
       			<#assign subscribeCollectBillStatus = "${action.getText('PURCHASEED')}"/>
       			<#elseif '${object.billStatus}' == 'INSPECTING'>
       			<#assign subscribeCollectBillStatus = "${action.getText('INSPECTING')}"/>
       				<#elseif '${object.billStatus}' == 'INSPECTED'>
       			<#assign subscribeCollectBillStatus = "${action.getText('已入库')}"/>
       			</#if>
       		 </#if>
            <@vcolumn title="${action.getText('subscribeCollectBill.billStatus')}" attributes="width='50'" sortable="asc">
            	${subscribeCollectBillStatus}
            	<@alignLeft />
            </@vcolumn>         
        </@list>
	 	
       
     </@ww.form>
</@htmlPage>
 <script language="javascript">
 	function openDetail() {
 		var url = '${req.contextPath}/DevicesubscribeSummary_/listDevicesDetails.html';
 		popupModalDialog(url,800,600);
 	}
 </script>