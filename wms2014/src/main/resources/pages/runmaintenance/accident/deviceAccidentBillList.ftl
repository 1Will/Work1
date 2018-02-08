<#include "../../includes/eam2008.ftl" />
<#include "./common.ftl" />
<@htmlPage title="${action.getText('deviceAccidentBillSearch.title')}">
  <@ww.form namespace="'/runmaintenance/accident'" name="'deviceAccidentBill'" action="'searchDeviceAccidentBills'" method="'post'">
  <@ww.token name="searchDeviceAccidentBillsToken"/>
  	     <#include "deviceAccidentBillSearcher.ftl"/>
  	     <@ww.hidden name="'toolingState'" value=""/>
         <@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>  
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/accident/editDeviceAccidentBill.html?toolingDevFlag=DEVICE"/> 
         </@buttonBar>   
         <@list title="${action.getText('deviceAccidentBill.list')}" 
            includeParameters="billNo|billName|deviceNo|deviceName|toolingDevFlag|accidentOccurDateTm_start|accidentOccurDateTm_end|department.id" 
        	fieldMap="like:billNo|billName|deviceNo|deviceName|toolingDevFlag,date:accidentOccurDateTm_start|accidentOccurDateTm_end" >
        	<@vlh.checkbox property="id" name="accidentBillIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('accidentBillNo')}" property="billNo" sortable="desc">
                <a href="editDeviceAccidentBill.html?accidentBill.id=#{object.id}&&toolingDevFlag=DEVICE">${object.billNo}</a>
                <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('accidentBillName')}" property="billName" sortable="desc">
               <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('device.no')}" property="toolingDev.deviceNo">
               <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('device.name')}" property="toolingDev.name">
               <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('device.model')}" property="toolingDev.model">
               <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('department')}" property="toolingDev.department.name">
               <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('accidentManager')}" property="manager.name">
               <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('accidentOccurDateTime')}" property="accidentOccurDateTm" format="yyyy-MM-dd">
               <@alignCenter/>
            </@vcolumn>
        </@list>  
	    <#if !first>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('tooling.accidentBill')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return validateDelete(confirmDeletes(\"accidentBillIds\", \"${confirmMessage}\"), checkInvalidParms())'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
        </#if>
  </@ww.form>
</@htmlPage>