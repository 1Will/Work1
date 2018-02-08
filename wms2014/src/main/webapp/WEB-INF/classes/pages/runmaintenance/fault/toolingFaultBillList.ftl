<#include "../../includes/eam2008.ftl" />
<#include "./common.ftl" />
<@htmlPage title="${action.getText('toolingFaultBillSearch.title')}">
  <@ww.form namespace="'/runmaintenance/fault'" name="'toolingFaultBill'" action="'searchToolingFaultBills'" method="'post'">
    <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
  <@ww.token name="searchToolingFaultBillsToken"/>
  	     <#include "toolingFaultBillSearcher.ftl"/>
         <@buttonBar>        
        	<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>  
        	<#if !(action.isReadOnly())>
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/fault/editToolingFaultBill.html?toolingDevFlag=TOOLING&readOnly=${req.getParameter('readOnly')?if_exists}"/> 
         </#if>
         </@buttonBar>   
         <@list title="${action.getText('faultBill.list')}" 
            includeParameters="billNo|billName|toolingNo|readOnly|toolingName|toolingGraphNo|toolingDevFlag|faultOccurDateTm_start|faultOccurDateTm_end|department.id|onlyValid|onlyInvalid" 
        	fieldMap="like:billNo|billName|toolingNo|toolingName|toolingDevFlag|toolingGraphNo,date:faultOccurDateTm_start|faultOccurDateTm_end" >
        	<@vlh.checkbox property="id" name="faultBillIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('faultBillNo')}" property="billNo" sortable="desc">
                <a href="editToolingFaultBill.html?faultBill.id=#{object.id}&&toolingDevFlag=TOOLING&readOnly=${req.getParameter('readOnly')?if_exists}">${object.billNo}</a>
                <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('faultBillName')}" property="billName" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('tooling.no')}" property="toolingDev.deviceNo" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('tooling.name')}" property="toolingDev.name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('tooling.name')}" property="toolingDev.name" sortable="desc">
              <#if object.toolingDev?exists>
                ${object.toolingDev.name}
              <#else>
                ${object.toolingName}
              </#if>
              <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('tooling.graphNo')}" property="toolingDev.graphNo" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('department')}" property="department.name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('faultManager')}" property="manager.name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('faultOccurDateTime')}" property="faultOccurDateTm" format="yyyy-MM-dd" sortable="desc">
                 <@alignCenter/>
            </@vcolumn>
            <#if (object.result?string)=='FAULT'>
              <#assign category="${action.getText('faultBillType.FAULT')}"/>
            <#else>
              <#assign category="${action.getText('faultBillType.ACCIDENT')}"/>
            </#if>
            <@vcolumn title="${action.getText('faultBillCategory')}" property="result" sortable="desc">
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
         	<#if !(action.isReadOnly())>
	         <@eam2008_disabledOrEnabled_button message="${action.getText('tooling.faultBill')}" boxName="faultBillIds" jsFunctionName="checkInvalidParms()"/>
	   		 </#if>
	   		 <#--
	   		 <@vbutton value="打印" class="button" />
	   		 -->
	    </@buttonBar> 
        </#if>
  </@ww.form>
</@htmlPage>