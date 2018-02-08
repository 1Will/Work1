<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('发货单查询页面')}">
	<@ww.form name="'listForm'" namespace="'/delivery'" action="'searchInvoices'" method="'post'">
		<@ww.token name="searchProjectInfoToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'backVisitCheckBox'" value="'${backVisitCheckBox?if_exists}'"/>
		<#include "./invoicesSearcher.ftl" />
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'"  onclick="'return checkInvalidParms()'"/>
			<#if backVisitCheckBox?exists&&backVisitCheckBox=='backVisitCheckBox'>
				<@ww.hidden name="'customer.id'" value="'${req.getParameter('customer.id')?if_exists}'"/>
			<#else>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/delivery/editDelivery.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			</#if>
			</#if>
        </@buttonBar>
         <#assign itemNo=1/>
        <@list title="${action.getText('发货单列表')}" 
            includeParameters="deliveryNum|customerInfo.name|contacter|deliveryPerson.name|department.name|deliveryWay.id|deliveryStatus.id|invoices.receiptDate_start|invoices.receiptDate_end|" 
        	fieldMap="like:deliveryNum|customerInfo.name|contacter|deliveryPerson.name|department.name,date:invoices.receiptDate_start|invoices.receiptDate_end|">
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="contractManagementIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
	            
          	<@vcolumn title="${action.getText('发货单编码')}" property="deliveryNum" sortable="desc" >
          		<a href="editDelivery.html?invoices.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.deliveryNum}</a>
				<@alignLeft/>
            </@vcolumn>
           <@vcolumn title="${action.getText('客户名称')}" property="customerInfo.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('联系人')}" property="contacter" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('部门')}" property="department.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('负责人')}" property="deliveryPerson.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('发货方式')}" property="deliveryWay.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('发货日期')}" property="deliveryDate" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('发货数量')}" property="deliveryCount" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('发货金额')}" property="deliveryPrice" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('回单日期')}" property="receiptDate" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('状态')}" property="deliveryStatus.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
    </@ww.form>
<script language="javascript">
function checkInvalidParms(){
	var beginDateMsg="发货日期：${action.getText('dateTimeFormat.error')}";
    if(queryDate("invoices.receiptDate_start","invoices.receiptDate_end",
       beginDateMsg,null)==false){
   	   return false;
    }	 
    var endDateMsg="发货日期：${action.getText('dateTimeFormat.error')}";
    if(queryDate("invoices.receiptDate_start","invoices.receiptDate_end",
       endDateMsg,null)==false){   
   	   return false;
    }
    if(getObjByName('invoices.receiptDate_start').value<getObjByName('invoices.receiptDate_end').value){
			alert('结束日期不能大于开始日期！');
			getObjByName('invoices.receiptDate_end').value="";
       		getObjByName('invoices.receiptDate_end').focus();
			return false;
	}
	return true;
}
</script>
</@htmlPage>
