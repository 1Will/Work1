<#--
	Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->

<#-- $Id: custContactReportList.ftl 2010-04-06 wliu $ -->

<#include "../../includes/macros.ftl" />

<@htmlPage title="${action.getText('ReportTitle')}">
	<@ww.form name="'listForm'" action="'searchCustContactReport'" method="'post'">
		<@ww.token name="searchCustContactReportToken"/>
		<#include "./custContactReportSearch.ftl"/>
		<@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/> 
        </@buttonBar>
        
        <@list title="${action.getText('CustContactReport_list')}" 
			includeParameters="day_start|day_end|month|pioneer|servingPeople" 
			fieldMap="like:pioneer|servingPeople,date:day_start|day_end" >
			
			<@vlh.checkbox property="id" name="contactIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
			<@vcolumn title="${action.getText('CustContactReport_custCode')}" property="custCode" sortable="desc">
				<@alignLeft />
			</@vcolumn>
			<@vcolumn title="${action.getText('CustContactReport_custName')}" property="custName" sortable="desc">
				<@alignLeft />
			</@vcolumn>
			<@vcolumn title="${action.getText('CustContactReport_custType')}" property="custType" sortable="desc">
				<@alignLeft />
			</@vcolumn>
			<@vcolumn title="${action.getText('CustContactReport_country')}" property="country" sortable="desc">
				<@alignLeft />
			</@vcolumn>
			<@vcolumn title="${action.getText('CustContactReport_province')}" property="province" sortable="desc">
				<@alignLeft />
			</@vcolumn>
			<@vcolumn title="${action.getText('CustContactReport_city')}" property="city" sortable="desc">
				<@alignLeft />
			</@vcolumn>
			<@vcolumn title="${action.getText('CustContactReport_industry')}" property="industry" sortable="desc">
				<@alignLeft />
			</@vcolumn>
			<@vcolumn title="${action.getText('CustContactReport_companyNature')}" property="companyNature" sortable="desc">
				<@alignLeft />
			</@vcolumn>
			<@vcolumn title="${action.getText('CustContactReport_content')}" property="content">
				<@alignLeft />
			</@vcolumn>
			<@vcolumn title="${action.getText('CustContactReport_attention')}" property="attention">
				<@alignLeft />
			</@vcolumn>
			<@vcolumn title="${action.getText('CustContactReport_pioneer')}" property="pioneer" sortable="desc">
				<@alignLeft />
			</@vcolumn>
			<@vcolumn title="${action.getText('CustContactReport_servingPeople')}" property="servingPeople" sortable="desc">
				<@alignLeft />
			</@vcolumn>
			<@vcolumn title="${action.getText('CustContactReport_visitdate')}" property="visitDate" sortable="desc" format="yyyy-MM-dd">
				<@alignLeft />
			</@vcolumn>
			<@vcolumn title="${action.getText('CustContactReport_nextvisitDate')}" property="nextVisitDate" sortable="desc" format="yyyy-MM-dd">
				<@alignLeft />
			</@vcolumn>
    	</@list>  
        <#if !first>
        <#if !(action.isReadOnly())>
        <@buttonBar>  
	        <@blurbutton name="batchPrinting" class="button" value="${action.getText('batchPrinting')}" onclick="showPrint()" onblur="onBlur('bp')"/>
	   		<div id="bp" style="display:none;position:relative;left:0px;top:0px;width:20px;">
				<@vbutton name="printPdf" class="button" value="${action.getText('pdfPrint')}" onclick="return open_Pdf()"/>
				<@vbutton name="printXls" class="button" value="${action.getText('xlsPrint')}" onclick="return open_Xls()"/>
	   		</div>
   		</@buttonBar>
        </#if>
		</#if>
    </@ww.form>
<script language="javascript">
     
/**
 * 获得客户联系报表单复选框，判断是否包含选中复选框
 */
function getCheckboxValue(checkbox) {    
	
	if (!checkbox.length && checkbox.type.toLowerCase()=='checkbox'){ 
		return (checkbox.checked)?checkbox.value:'';  
	}          
 	if (checkbox[0].tagName.toLowerCase() != 'input' || checkbox[0].type.toLowerCase() != 'checkbox') { 
 		return ''; 
 	} 
	var val = [];     
	var len = checkbox.length;     
	for(i=0; i<len; i++){         
		if (checkbox[i].checked){
			val[val.length] = checkbox[i].value;         
		}     
 	}          
 	return (val.length)?val:''; 
 } 
//客户联系报表单PDF打印
function open_Pdf(){
	var checkboxTest = document.forms['listForm'].elements['contactIds'];
	var contactIds = getCheckboxValue(checkboxTest);
	if(contactIds == ""){
  		alert("${action.getText('pleaseCheckRecord')}");
  		return false;
	}
	var url='${req.contextPath}/reports/custcontact/Cust_Contact_report.pdf?contactIds='+contactIds;
	url = encodeURI(url);
	window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
	return true;
}
//客户联系报表单EXCEL打印
function  open_Xls(){
	var checkboxTest = document.forms['listForm'].elements['contactIds'];
	var contactIds = getCheckboxValue(checkboxTest);
	if(contactIds == ""){
		alert("${action.getText('pleaseCheckRecord')}");
	  	return false;
	}
	var url='${req.contextPath}/reports/custcontact/Cust_Contact_report.xls?contactIds='+contactIds;
	window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
	return true;
}
//点击批量打印按钮控制层的显示和隐藏
function showPrint(){
	var op = getObjByName('bp');
	if(op.style.display=="none"){
		op.style.display="block";
	}else if(op.style.display=="block"){
		op.style.display="none";
	}
}
//失去焦点隐藏导航层
function onBlur(op){
	getObjByName(op).style.display="none";
}
</script>
</@htmlPage>