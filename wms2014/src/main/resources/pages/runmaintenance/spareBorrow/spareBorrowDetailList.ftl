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
<#--$Id: subscribeDetails.ftl 11311 2008-03-13 13:19:59Z mwei $ -->

<#include "../../includes/eam2008.ftl" />
  <style type="text/css">
    .noBorderLine{
       border-width :0px;
       border-style : none;
       outline-style : none;
       text-align:right;
       width:80%;
       }
    .definedLength{
        border-width: 1px;
        border-style: solid;
        border-color: white white black;
        text-align:right;
        width:80%;
    }
  </style>
  <base target="_self">
<@framePage title="${action.getText('')}">	
	<@ww.form namespace="'/runmaintenance/spareBorrow'" name="'listForm'" action="'searchSpareBorrowDetails'" method="'post'">
		<@ww.token name="searchSpareInBillDetailToken"/>
		  <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
		  <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		  <@ww.hidden name="'addSpareDetailIds'" value=""/>
		 <@ww.hidden name="'spareAccountSelector'" value=""/>
		  <@ww.hidden name="'allSpareBorrowDtlIds'" value=""/>
		 <@ww.hidden name="'allSpareBorrowAmountValue'" value=""/>
		<#if spareBorrow.id?exists>
		<@ww.hidden name="'spareBorrow.id'" value="#{spareBorrow.id}"/>
		</#if>
		<#assign itemNo=1/>
		 <@list title="" excel=false setupTable=false includeParameters="spareBorrow.id" fieldMap="">
			<@vlh.checkbox property="id" name="spareBorrowDtlIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
           	<@vcolumn title="${action.getText('itemNo')}">
                ${itemNo}
             <@alignCenter />
           </@vcolumn>
           <#assign itemNo = itemNo + 1/>
            <@vcolumn title="${action.getText('spare.code')}" property="spareNo">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.name')}" property="spareName">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.model')}" property="model">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('spare.specification')}" property="specification">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('spare.unit')}" property="unit" format="${action.getText('currencyFormat')}">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('unitPrice')}" property="price" format="${action.getText('currencyFormat')}">
            	<@alignRight />
            </@vcolumn>
             <@vcolumn title="${action.getText('borrowNum')}">
              <input type="text" name="amount" value="${object.amount?if_exists}" size="10" class="underline"  style="text-align:right"/>
            </@vcolumn>
             <@vcolumn title="${action.getText('totalPrice')}" property="totalPrice" format="${action.getText('currencyFormat')}">
            	<@alignRight />
            </@vcolumn>
             <@vcolumn title="${action.getText('nowStock')}" property="totalStock" >
            	<@alignRight />
            </@vcolumn>
          <#assign spareBorrowStatus = ''/>
            <#if object.status?exists>
              <#if '${object.status}' == 'UNBORROW'>
               <#assign spareBorrowStatus = "${action.getText('UNBORROW')}"/>
               </#if>
              <#if '${object.status}' == 'BORROWED'>
              <#assign spareBorrowStatus = "${action.getText('BORROWED')}"/>
            </#if>
            </#if>
               <@vcolumn title="${action.getText('status')}" attributes="width='50'">
           ${spareBorrowStatus}
                 <@alignLeft/>
            </@vcolumn>
		</@list>
        <@buttonBar>
        	<#if !first>
        	<#if !(action.isReadOnly())>
            <@vbutton name="'selectSpare'"  class="button" value="${action.getText('spareAccountSelect')}" onclick="open_selectSpareAccountDialog()"/>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return submitDetailIds()'">
            	<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('spareborrowDetail')}?" />
           <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"spareBorrowDtlIds\", \"${confirmMessage}\")'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            </#if>
            </#if>
        </@buttonBar>
	</@ww.form>
</@framePage>
<script language="javascript">
	window.onload=function(){
	<#if req.getParameter('errorFlag')?has_content>
   	       alert("${action.getText('delete.spareBorrowDtl.failure')}");
        </#if>
        
    <#if spareBorrow.status?exists>
   		    <#assign spareBorrowStatus = ''/>
   			<#if '${spareBorrow.status}' == 'NEWSTATUS'>
   			<#assign spareBorrowStatus = "${action.getText('NEWSTATUS')}"/>
   			<#elseif '${spareBorrow.status}' == 'BORROWING'>
   			<#assign spareBorrowStatus = "${action.getText('BORROWING')}"/>
   			<#elseif '${spareBorrow.status}' == 'BORROWED'>
   			<#assign spareBorrowStatus = "${action.getText('BORROWED')}"/>
   			</#if>
   			parent.document.forms["spareBorrow"].elements["spareBorrow.status"].value = '${spareBorrowStatus}';
   	</#if>
	}
   //从备件台帐选择
	function open_selectSpareAccountDialog(){
	  var url = '${req.contextPath}/popup/spareSelector.html?inOutFlag=borrow&toolingDevFlag=${toolingDevFlag?if_exists}&spareBillId='+#{spareBorrow.id};
	  popupModalDialog(url, 800, 600,choose_spareAccount_information)
	}
	function choose_spareAccount_information(result){
      if (null != result) {
        var spareDetailIds = result.substring(0, result.lastIndexOf(","));
        document.forms[0].elements["addSpareDetailIds"].value = spareDetailIds;
        document.forms[0].elements["spareAccountSelector"].value = "spareAccount";
        document.forms[0].submit();
      }
	}
	//保存盘点单明细
	function submitDetailIds(){
	  if(0!=document.getElementsByName("spareBorrowDtlIds").length){
	  if(informationValidate()==true){
	     retrieveSpareInventoryBillDetailIdText();
	     retrieveSpareInventoryBillDetailInventoryNumText();
	  	return true;
	  	}else{
	     return false;
	   }
	  }
	}
	//获得所有的备件领用的ids
	function retrieveSpareInventoryBillDetailIdText(){
	  var allSpareBorrowDtlId = document.getElementsByName("spareBorrowDtlIds");
        var ary = new Array();
        for (var i=0; i<allSpareBorrowDtlId.length; i++) {
          ary.push(allSpareBorrowDtlId[i].value);
        }
        document.forms[0].elements["allSpareBorrowDtlIds"].value = ary;
	}
	//获得所有的备件领用明细的领用数量
	function retrieveSpareInventoryBillDetailInventoryNumText(){
	 var allSpareBorrowDtlId = document.getElementsByName("spareBorrowDtlIds");
        var allBorrowAmountValue = document.getElementsByName("amount");
       
        var ary = new Array();
       for (var i=0; i<allSpareBorrowDtlId.length; i++) {
          if ('' != allBorrowAmountValue[i].value) {
           ary.push(allSpareBorrowDtlId[i].value);
           ary.push(allBorrowAmountValue[i].value);
         }
       }
      document.forms[0].elements["allSpareBorrowAmountValue"].value=ary;
      }
    //验证盘点数量
  function informationValidate(){
      	var allSpareInventoryBillDtlId = document.getElementsByName("spareBorrowDtlIds");
	    var allinventoryNumberValue = document.getElementsByName("amount");
	     for(var i=0;i<allSpareInventoryBillDtlId.length;i++){
		    var number = allinventoryNumberValue[i].value;
		    if(''== number){
		    	alert("${action.getText('input.row')}"+(i+1)+"${action.getText('spareBorrowDtl.number.null')}");
		    	return false;
		    }else{
		       //验证格式
		    	if (!isDoubleNumberCheck(number)){   			
	         		alert("${action.getText('row')}"+(i+1)+"${action.getText('spareInventoryDtl.number.format.error')}");
	         		return false;
       			}else if(!isNumberBetweenBoolen(number, 1000000001, 0)){
       				alert("${action.getText('row')}"+(i+1)+"${action.getText('spareInventoryDtl.number.maxLength')}");
       				return false;
       			}
		    }
		    
		 }
		  return true;
     }
</script>