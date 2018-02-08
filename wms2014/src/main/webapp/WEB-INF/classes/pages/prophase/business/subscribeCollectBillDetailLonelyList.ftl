<#--$Id: extInfoList.ftl  2011-03-02 zzb $ -->
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="申购汇总单明细查询">
	 <@ww.form name="'listForm'" action="'searchSubscribeCollectBillDetaiLonely'" method="'post'">
	  <@ww.token name="searchSubscribeCollectBillDetaiLonelyToken"/>
	 	<#include "subscribeCollectBillDetailLonelySearch.ftl"/>
	 	
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
            <#--
             <#if !(action.isReadOnly())>
          	  <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/devicesubscribeSummary/editSubscribeCollectBill.html"/>
       		</#if>
       		-->
        </@buttonBar>
     
        <@list title="${action.getText('申购总汇单明细列表')}" 
               includeParameters="id|name|modelSpace|dept.id|subDate|subDate_start|subDate_end|purDate_start|purDate_end|billStatus" 
               fieldMap="like:name|modelSpace">
            <#--
            <@vlh.checkbox property="id" name="subscribeCollectBillIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            -->
   
           <@vcolumn title="名称" property="name" sortable="asc">
              <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="型号" property="model" sortable="asc">
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="申购部门" property="subscribe.department.name" sortable="asc">
             <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="申购日期" property="subscribe.subscribeDate"  format="yyyy-MM-dd" sortable="asc">
              <@alignCenter/>
           </@vcolumn>
    
           <#assign status = ''/>
            <#if object.status?exists>
       			<#if '${object.status}' == 'NEW'>
       			<#assign status = "${action.getText('NEWPURCHASE')}"/>
       			<#elseif '${object.status}' == 'PURCHASING'>
       			<#assign status = "${action.getText('PURCHASING')}"/>
       			<#elseif '${object.status}' == 'PURCHASEED'>
       			<#assign status = "${action.getText('PURCHASEED')}"/>
       			
       			<#elseif '${object.status}' == 'COLLECTED'>
       			<#assign status = "${action.getText('已汇总')}"/>
       			<#elseif '${object.status}' == 'NOTPURCHASEED'>
       			<#assign status = "${action.getText('不采购')}"/>
       			<#elseif '${object.status}' == 'INSPECTING'>
       			<#assign status = "${action.getText('INSPECTING')}"/>
       				<#elseif '${object.status}' == 'INSPECTED'>
       			<#assign status = "${action.getText('已入库')}"/>
       			</#if>
       		</#if>
            <@vcolumn title="${action.getText('subscribeCollectBill.billStatus')}" attributes="width='50'" sortable="asc">
            	${status}
            	<@alignLeft />
            </@vcolumn>    
            
            <@vcolumn title="采购数量" property="buyeAmount">
               <@alignRight/>
           </@vcolumn> 
           <@vcolumn title="采购日期" property="purchaseDate"  format="yyyy-MM-dd" >
              <@alignCenter/>
           </@vcolumn>
            
            
                
        </@list>
	 	
       
     </@ww.form>
</@htmlPage>
 