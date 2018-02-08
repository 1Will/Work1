<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
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
<#--$Id: subscribeList.ftl 11279 2008-03-12 01:12:13Z mwei $ -->

<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('spareInBillList.title')}">
	<@ww.form namespace="'/spare'" name="'spareInBillList'" action="'searchSpareInBill'" method="'post'">
		<@ww.token name="searchSpareInBillToken"/>
		<#include "spareInBillSearcher.ftl"/>
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
			<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/spare/editSpareInBill.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
	        </#if>
		</@buttonBar>
		<@list title="${action.getText('spareInBillList')}" 
		includeParameters="spareInBillNo|spareInBillName|inPeople|checkPeople|supplierName|inDate_start|inDate_end|onlyValid|onlyInvalid|inStatus|storageGrade.id|warehouse.id" 
		fieldMap="like:spareInBillNo|spareInBillName|inPeople|supplierName|checkPeople,date:inDate_start|inDate_end">
            <@vlh.checkbox property="id" name="spareInBillIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
             <#if (object.disabled)>
            	<@vcolumn title="${action.getText('sib.spareInBillNo')}" property="code" sortable="desc">
            	<@alignLeft />
            </@vcolumn>
            <#else>
            	<@vcolumn title="${action.getText('sib.spareInBillNo')}" property="code" sortable="desc">
            	<a href="editSpareInBill.html?spareInBill.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
            	<@alignLeft />
            </@vcolumn>
            </#if>
            <@vcolumn title="${action.getText('sib.spareInBillName')}" property="name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <#-- 仓库级别 -->
            <@vcolumn title="${action.getText('storageGrade')}" property="storageGrade.value" sortable="asc">
				<@alignLeft />
            </@vcolumn>       
            <#-- 仓库 -->
            <@vcolumn title="${action.getText('warehouse')}" property="warehouse.name" sortable="asc">
            	<@alignLeft />
            </@vcolumn> 
            <@vcolumn title="${action.getText('sib.inPeople')}" property="inPeople.name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('sib.checkPeople')}" property="checkPeople.name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('sib.supplierName')}" property="supplier.name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>  
            <@vcolumn title="${action.getText('sib.inDate')}" property="inDate" format="yyyy-MM-dd" sortable="asc">
            	<@alignCenter />
            </@vcolumn>           
            <@vcolumn title="${action.getText('sib.totalPrice')}" property="totalPrice" format="${action.getText('currencyFormat')}">
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('sib.comment')}" property="comment">
            	<@alignCenter />
            </@vcolumn>
             <#assign status=''/>
        	<#if object.status?exists>
		       <#if '${object.status}' == 'NEWSTATUS'>
		       <#assign status = "${action.getText('NEWSTATUS')}"/>
		       <#elseif '${object.status}' == 'INWAREHOUSEING'>
		       <#assign status = "${action.getText('INWAREHOUSEING')}"/>
		       <#elseif '${object.status}' == 'INWAREHOUSEED'>
		       <#assign status = "${action.getText('INWAREHOUSEED')}"/>
        	   </#if>
        	</#if>
         <@vcolumn title="${action.getText('status')}" attributes="width='50'">
           ${status}
                 <@alignLeft/>
            </@vcolumn>
		</@list>
		<#-- EAM1.0
		<#if !first>
		<#if !(action.isReadOnly())>
		<@buttonBar>
        <@eam2008_disabledOrEnabled_button message="${action.getText('spareInBill')}" boxName="spareInBillIds" jsFunctionName="checkInvalidParms()"/>
        <@vbutton name="printPdf"  class="button" value="${action.getText('pdfspareInBillReportPrint')}" onclick="open_SpareInBillReportPdf()"/>
	    <@vbutton name="printXls"  class="button" value="${action.getText('xlsspareInBillReportPrint')}" onclick="open_SpareInBillReportXls()"/>
        </@buttonBar>
        </#if>
        </#if>
        -->
        <#if !first>
			<tr class="input">
			<td align='right'>
				<label class="label">
					${action.getText('sib.totalPrice')}:
				</label>
				<input type="text" name="totalPrice" 
				value="${(showTotalPrice?string('######0.00'))?if_exists}"  
				class="noBorderLine" maxlength="20" size="10" style="text-align:left" readonly="true"/>  			
			</td>
			</tr>
			<#if !(action.isReadOnly())>
			<tr class="input">
			<td>
	        	<@eam2008_disabledOrEnabled_button message="${action.getText('spareInBill')}" boxName="spareInBillIds" jsFunctionName="checkInvalidParms()"/>   
	   	    	<#--
	   	    	<@blurbutton name="excelPrint" class="button" value="${action.getText('pdfspareInBillCheckReportPrint')}" onclick="excelButtonManager('pdf')" onblur="onBlur('excel')"/>
		   		<div id="excel" style="display:none;position:relative;left:40px;top:0px;width:20px;">
					<@vbutton name="printPdf"  class="button" value="${action.getText('pdfYanShouCollectPrint')}" onclick="return open_SpareInBillCheckReportPdf()"/>
					<@vbutton name="printXls"  class="button" value="${action.getText('xlsYanShouCollectPrint')}" onclick="return open_SpareInBillCheckReportXls()"/>
		   		</div>
		   		-->
		   		<@vbutton name="printPdf"  class="button" value="验收单批量打印PDF" onclick="return open_SpareInBillCheckReportPdf()"/>
			    <@vbutton name="printXls"  class="button" value="验收单批量导出XLS" onclick="return open_SpareInBillCheckReportXls()"/>
            </td> 
            </tr>             	 		        
	        </#if>	
       </#if>
	</@ww.form>
</@htmlPage>
<style type="text/css">
.noBorderLine{
   border-width :0px;
   border-style : none;
   outline-style : none;
   text-align:right;
   }
}
</style>
<script language="javascript">
     window.onload = function () {
     <#if !(action.isReadOnly())>
        <#if !first && valueListNoRecords>
             document.forms[0].elements["printPdf"].disabled="true";
             document.forms[0].elements["printXls"].disabled="true";
        </#if>
     </#if>
     
     //仓库
     <#if spareInBill?exists>
		<#if spareInBill.warehouse?exists>
			getObjByName('warehouse.id').value='${spareInBill.warehouse.id?if_exists}';
	    <#else>
	    	<#if req.getParameter('warehouse.id')?exists>
	    		getObjByName('warehouse.id').value='${req.getParameter('warehouse.id')?if_exists}';
	    	</#if>
	    </#if>
	  </#if>
     }
     
    

/**
 * 获得选中的入库单，可进行批量打印
 */
function getCheckboxValue(checkbox) {    
	if (!checkbox.length && checkbox.type.toLowerCase() == 'checkbox'){ 
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
//验收单PDF打印
function open_SpareInBillCheckReportPdf(){
	var checkboxTest = document.forms['spareInBillList'].elements['spareInBillIds'];
	var spareInBillId = getCheckboxValue(checkboxTest);
	if(spareInBillId == ""){
  		alert("${action.getText('pleaseCheckRecord')}");
  		return false;
	}
	var url='${req.contextPath}/reports/spare/spareInBillAccept.pdf?list=list&spareInBillId='+spareInBillId;//list标识为列表页面打印
	url = encodeURI(url);
	window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
	return true;
}
//验收单EXCEL打印
function  open_SpareInBillCheckReportXls(){
	var checkboxTest = document.forms['spareInBillList'].elements['spareInBillIds'];
	var spareInBillId = getCheckboxValue(checkboxTest);
	if(spareInBillId == ""){
		alert("${action.getText('pleaseCheckRecord')}");
	  	return false;
	}
	var url='${req.contextPath}/reports/spare/spareInBillAccept.xls?list=list&spareInBillId='+spareInBillId;//list标识为列表页面打印
	window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
	return true;
}
//点击批量打印按钮控制层的显示和隐藏
function excelButtonManager(type){
	var op = getObjByNameRe('excel')
	if(op.style.display=="none"){
		getObjByNameRe("excelPrint").value="${action.getText('pdfspareInBillCheckReportPrint')}";
		op.style.display="block";
	}else if(op.style.display=="block"){
		getObjByNameRe("excelPrint").value="${action.getText('pdfspareInBillCheckReportPrint')}";
		op.style.display="none";
	}
}
//失去焦点隐藏导航层
function onBlur(op){
	getObjByNameRe(op).style.display="none";
}


//EAM1.0
function open_SpareInBillReportPdf(){
      var spareInBillNo=document.forms[0].elements["spareInBillNo"].value;
      var spareInBillName=document.forms[0].elements["spareInBillName"].value;
      var inPeople=document.forms[0].elements["inPeople"].value;
      var checkPeople=document.forms[0].elements["checkPeople"].value;
      var supplier=document.forms[0].elements["supplierName"].value;
      var inDate_start=document.forms[0].elements["inDate_start"].value;
      var inDate_end=document.forms[0].elements["inDate_end"].value;
      var url='${req.contextPath}/reports/spare/listAllSpareInBill.pdf?spareInBillNo='+spareInBillNo+'&spareInBillName='+spareInBillName+'&inPeople='+inPeople+'&checkPeople='+checkPeople+'&supplier='+supplier+'&inDate_start='+inDate_start+'&inDate_end='+inDate_end;
      url = encodeURI(url);
      window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
     }
     function  open_SpareInBillReportXls(){
      var spareInBillNo=document.forms[0].elements["spareInBillNo"].value;
      var spareInBillName=document.forms[0].elements["spareInBillName"].value;
      var inPeople=document.forms[0].elements["inPeople"].value;
      var checkPeople=document.forms[0].elements["checkPeople"].value;
      var supplier=document.forms[0].elements["supplierName"].value;
      var inDate_start=document.forms[0].elements["inDate_start"].value;
      var inDate_end=document.forms[0].elements["inDate_end"].value;
      var url='${req.contextPath}/reports/spare/listAllSpareInBill.xls?spareInBillNo='+spareInBillNo+'&spareInBillName='+spareInBillName+'&inPeople='+inPeople+'&checkPeople='+checkPeople+'&supplier='+supplier+'&inDate_start='+inDate_start+'&inDate_end='+inDate_end;
      url = encodeURI(url);
      window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
     }
</script>