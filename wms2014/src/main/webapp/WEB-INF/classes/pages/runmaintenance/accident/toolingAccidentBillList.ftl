<#include "../../includes/eam2008.ftl" />
<#include "./common.ftl" />
<@htmlPage title="${action.getText('toolingAccidentBillSearch.title')}">
  <@ww.form namespace="'/runmaintenance/accident'" name="'toolingAccidentBill'" action="'searchToolingAccidentBills'" method="'post'">
  <@ww.token name="searchToolingAccidentBillsToken"/>
  	     <#include "toolingAccidentBillSearcher.ftl"/>
  	     <@ww.hidden name="'toolingState'" value=""/>
         <@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>  
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/accident/editToolingAccidentBill.html?toolingDevFlag=TOOLING"/> 
         </@buttonBar>   
         <@list title="${action.getText('toolingAccidentBill.list')}" 
            includeParameters="billNo|billName|toolingNo|toolingName|toolingDevFlag|toolingGraphNo|accidentOccurDateTm_start|accidentOccurDateTm_end|department.id" 
        	fieldMap="like:billNo|billName|toolingNo|toolingName|toolingGraphNo|toolingDevFlag,date:accidentOccurDateTm_start|accidentOccurDateTm_end" >
        	<@vlh.checkbox property="id" name="accidentBillIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('accidentBillNo')}" property="billNo" sortable="desc">
                <a href="editToolingAccidentBill.html?accidentBill.id=#{object.id}&&toolingDevFlag=TOOLING">${object.billNo}</a>
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('accidentBillName')}" property="billName" sortable="desc">
              <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('tooling.no')}" property="toolingDev.deviceNo" >
              <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('tooling.graphNo')}" property="toolingDev.graphNo">
              <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('tooling.name')}" property="toolingDev.name">
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
	 <script language="JavaScript" type="text/JavaScript"> 
  </@ww.form>
</@htmlPage>