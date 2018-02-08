<#include "../../includes/eam2008.ftl" />
<#include "./common.ftl" />
<@htmlPage title="${action.getText('deviceFaultBillSearch.title')}">
  <@ww.form namespace="'/runmaintenance/fault'" name="'deviceFaultBill'" action="'searchDeviceFaultBills'" method="'post'">
  <@ww.token name="searchDeviceFaultBillsToken"/>
  	     <#include "deviceFaultBillSearcher.ftl"/>
  	     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <@buttonBar>        
        	<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>  
        	
          <#if !(action.isReadOnly())>
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/fault/editDeviceFaultBill.html?toolingDevFlag=false&readOnly=${req.getParameter('readOnly')?if_exists}"/> 
         </#if>
         </@buttonBar>   
         <@list title="${action.getText('faultBill.list')}" 
            includeParameters="billNo|billName|readOnly|deviceNo|deviceName|toolingDevFlag|faultOccurDateTm_start|faultOccurDateTm_end|department.id|onlyValid|onlyInvalid|faultBill.faultCatorgy|faultBill.devicePosition|faultBill.deviceCatorgy" 
        	fieldMap="like:billNo|billName|deviceNo|deviceName|toolingDevFlag,date:faultOccurDateTm_start|faultOccurDateTm_end" >
        	<@vlh.checkbox property="id" name="faultBillIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('faultBillNo')}" property="billNo" sortable="desc">
                <#if !(action.isOnlyInvalid())>
                <a href="editDeviceFaultBill.html?readOnly=${req.getParameter('readOnly')?if_exists}&faultBill.id=#{object.id}&&toolingDevFlag=false">${object.billNo}</a>
                </#if>
                <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('faultBillName')}" property="billName" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('device.no')}" property="toolingDev.deviceNo" sortable="desc">
             <@alignLeft/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('device.name')}" property="toolingDev.name" sortable="desc">
             <@alignLeft/>
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('device.name')}" property="toolingDev.name" sortable="desc">
            <#if object.toolingDev?exists>
              ${object.toolingDev.name}
            <#else>
              ${object.toolingName}
            </#if>
             <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('device.model')}" property="toolingDev.model">
             <@alignLeft/>
            </@vcolumn>
      <#-- 故障类别   -->
            <@vcolumn title="${action.getText('faultCatorgy')}" property="faultCatorgy.value">
             <@alignLeft/>
            </@vcolumn>
           
      <#--	设备部位 
            <@vcolumn title="${action.getText('device.devicePosition')}" property="devicePosition">
             <@alignLeft/>
            </@vcolumn>
       -->     
      <#--  设备类别    
            <@vcolumn title="${action.getText('device.category')}" property="deviceCatorgy">
             <@alignLeft/>
            </@vcolumn>
       -->    
            
            <@vcolumn title="${action.getText('department')}" property="department.name" sortable="desc">
             <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('faultManager')}" property="manager.name">
             <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('faultOccurDateTime')}" property="faultOccurDateTm" format="yyyy-MM-dd">
             <@alignCenter/>
            </@vcolumn>
            <#if (object.result?string)=='FAULT'>
              <#assign category="${action.getText('faultBillType.FAULT')}"/>
            <#else>
              <#assign category="${action.getText('faultBillType.ACCIDENT')}"/>
            </#if>
            <@vcolumn title="${action.getText('faultBillCategory')}">
            ${category}
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('stopManchineBeginTime')}" property="stopTimeBegin" format="yyyy-MM-dd HH:mm">
             <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('stopManchineEndTime')}" property="stopTimeEnd" format="yyyy-MM-dd HH:mm">
             <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('fault.lossTime')}" property="faultLossTime">
            <@alignLeft/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('repareBeginTime')}" property="repairTimeBegin" format="yyyy-MM-dd HH:mm">
             <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('repareEndTime')}" property="repairTimeEnd" format="yyyy-MM-dd HH:mm">
             <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('fault.repareTime')}" property="faultRepairTime">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('faultCause')}" property="faultCause">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('faultSolution')}" property="faultSolution">
              <@alignLeft/>
            </@vcolumn>
            <#if (object.faultFlag?string)=='true'>
              <#assign status="${action.getText('faultBill.solution')}"/>
            <#else>
              <#assign status="${action.getText('faultBill.noSolution')}"/>
            </#if>
            <@vcolumn title="${action.getText('faultBillstatus')}">
            ${status}
            <@alignLeft/>
            </@vcolumn>
        </@list>  
	    <#if !first>
        <#--<@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('tooling.faultBill')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return validateDelete(confirmDeletes(\"faultBillIds\", \"${confirmMessage}\"), checkInvalidParms())'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>-->
        <@buttonBar>
         <@buttonBar>
         <#if !(action.isReadOnly())>
	         <@eam2008_disabledOrEnabled_button message="${action.getText('device.faultBill')}" boxName="faultBillIds" jsFunctionName="checkInvalidParms()"/>
	    </#if>
	    </@buttonBar> 
	    </@buttonBar> 
        </#if>
  </@ww.form>
</@htmlPage>