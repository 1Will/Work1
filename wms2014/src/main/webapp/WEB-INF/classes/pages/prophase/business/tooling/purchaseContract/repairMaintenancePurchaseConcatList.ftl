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
<#--$Id: toolingMakeDetailConList.ftl 11279 2008-03-12 01:12:13Z mwei $ -->

<#include "../../../../includes/eam2008.ftl" />

<@framePage title="${action.getText('ToolingMakeDetailPurchaseConcat')}">	
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
	<link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
   <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
   <script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
   <script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script>  
	<@ww.form name="'listForm'" namespace="'/toooling/purchaseContract'"  action="'searchRepairMaintenancePurchaseConcat'" method="'post'">
		<@ww.token name="searchRepairMaintenancePurchaseConcatToken"/>
		<@ww.hidden name="'allPurchaseContractDtlIds'" value=""/>
		<@ww.hidden name="'allPurchaseContractDetailAmountNumber'" value=""/>
		<@ww.hidden name="'allPurchaseContractDetailUnitPrice'" value=""/>
		<@ww.hidden name="'allPurchaseContractDetailRequestDate'" value=""/>
		<@ww.hidden name="'addPurchaseBillDetailIds'" value=""/>
		<@ww.hidden name="'addPurchaseBill'" value=""/>
		<@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
		<#if purchaseBill.id?exists>
			<@ww.hidden name="'purchaseBill.id'" value="#{purchaseBill.id}"/>
		</#if>
		 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		 <#assign itemNo=1/>
		 <#assign requestDateIdentityName = 'request.Date' + '${itemNo}'/>
		 <#assign requestDateImgIdentity = 'listForm_' + '${requestDateIdentityName}' + '_trigger'/>
		 <@list title="" excel=false setupTable=false includeParameters="purchaseBill.id" fieldMap="">
			<@vlh.checkbox property="id" name="purchaseContractToolingMakeIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('itemNo')}">
                <a href="#" onclick="return open_toolingMakeDtlDialog(#{purchaseBill.id}, #{object.id})">${itemNo}</a>
                <@alignCenter />
           </@vcolumn>
           <#assign itemNo = itemNo + 1/>
            <@vcolumn title="${action.getText('graphNo')}" property="graphNo">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('name')}" property="name" attributes="width='100'">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('department')}" property="department">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('category')}" property="categoryName">
            	<@alignLeft />
            </@vcolumn>
            <#assign detailType = ''/>
			<#if '${object.detailType}' == 'TOOLING_MAKE'>
           		<#assign detailType = "${action.getText('TOOLING_MAKE')}"/>
          	<#elseif '${object.detailType}' == 'SPARE_PURCHASE'>
              <#assign detailType = "${action.getText('SPARE_PURCHASE')}"/>
            <#elseif '${object.detailType}' == 'REPAIR_MAINTENANCE'>
              <#assign detailType = "${action.getText('REPAIR_MAINTENANCE')}"/>
            <#elseif '${object.detailType}' == 'TECH_ALTER'>
              <#assign detailType = "${action.getText('TECH_ALTER')}"/>
            <#else>
              <#assign detailType = ''/>
       		</#if>
            <@vcolumn title="${action.getText('detailType')}" property="detailCategoryName">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('model')}" property="model">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('specification')}" property="specification">
            	<@alignLeft />
            </@vcolumn>
        <@vcolumn title="${action.getText('unit')}" property="calUnit.value">
        	<@alignLeft />
      </@vcolumn>
             <#assign unitPrice = ''/>
        <#assign amount = ''/>
        <#if '${object.unitPrice}'=='0'>
            <#assign unitPrice = ''/>
        <#else>
            <#assign unitPrice = '${object.unitPrice?if_exists}'/>
        </#if>
        <#if '${object.amount}'=='0'>
            <#assign amount = ''/>
        <#else>
            <#assign amount = '${object.amount?if_exists}'/>
        </#if>
        <@vcolumn title="${action.getText('unitPrice')}">
        	${unitPrice}
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('amount')}">
        	${amount}
        	<@alignRight />
        </@vcolumn>
            <@vcolumn title="${action.getText('totalPrice')}" format="${action.getText('currencyFormat')}" property="totalPrice">
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('reqDeliveryDate')}" format="yyyy-MM-dd" property="reqDeliveryDate">
            	<@alignCenter />
            </@vcolumn>
            	<#assign status = ''/>
			<#if '${object.status}' == 'NEW'>
           		<#assign status = "${action.getText('NEW')}"/>
          	<#elseif '${object.status}' == 'unSPECT'>
              <#assign status = "${action.getText('unSPECT')}"/>
            <#elseif '${object.status}' == 'INSPECTED'>
              <#assign status = "${action.getText('INSPECTED')}"/>
            <#else>
              <#assign status = "${action.getText('INWAREHOUSEED')}"/>
       		</#if>
            <@vcolumn title="${action.getText('status')}" >
            	${status}
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('comment')}" property="comment" attributes="width='100'">
            	<@alignLeft />
            </@vcolumn>

		</@list>
        <@buttonBar>
        	<#if !first>
        	<#if !(action.isReadOnly())>
       <@buttonBar>
    	 	<input type="button" class="button" name="new" value="${action.getText('new')}" onclick="open_toolingMakeDtlDialog(#{purchaseBill.id}, null)"/>
			<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('repairMaintenancePurchaseConcat')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"purchaseContractToolingMakeIds\", \"${confirmMessage}\")'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            <@vbutton name="'new'"  class="button" value="${action.getText('fromPurchaseBill')}" onclick="open_PurchaseBillDialog(#{purchaseBill.id}, null)"/>
       </@buttonBar>
            </#if>
            </#if>
        </@buttonBar>
	</@ww.form>
<script language="javascript">
	window.onload=function(){
        <#if req.getParameter('errorFlag')?has_content>
   	       alert("${action.getText('delete.repairMantanceDtl.failure')}");
        </#if>
          <#if purchaseBill.id?exists> 
             parent.document.getElementById("totalPrice").value = '${(purchaseBill.totalPrice?string('##,###,##0.00'))?if_exists}';
        </#if>
         <#if !(action.isReadOnly())>
   			<#if purchaseBill.submit==true>
		      parent.document.forms[0].elements["submitRecord"].disabled=false;
		     <#if purchaseBill.purchaseBillDetails.size()==0>
		    parent.document.forms[0].elements["submitRecord"].disabled=true;
		    </#if>
	      </#if>
	     </#if>
         //更新当前采购单状态
   		<#if purchaseBill.status?exists>
   		    <#assign purchaseBillStatus = ''/>
   			<#if '${purchaseBill.status}' == 'NEWSTATUS'>
   			<#assign purchaseBillStatus = "${action.getText('PURCHASEBILL_NEWSTATUS')}"/>
   			<#elseif '${purchaseBill.status}' == 'INSPECTING'>
   			<#assign purchaseBillStatus = "${action.getText('PURCHASEBILL_INSPECTING')}"/>
   			<#elseif '${purchaseBill.status}' == 'INSPECTED'>
   			<#assign purchaseBillStatus = "${action.getText('PURCHASEBILL_INSPECTED')}"/>
   			</#if>
   			parent.document.forms["purchaseBill"].elements["purchaseBill.status"].value = '${purchaseBillStatus}';
   		</#if>
        }
	 function open_toolingMakeDtlDialog(purchaseBillId,toolingMakeDtlId){
		 var url = '${req.contextPath}/toooling/purchaseContract/editRepairMaintenancePurchaseConcat.html?readOnly=${req.getParameter('readOnly')?if_exists}&purchaseBill.id='+purchaseBillId;	
		 if(toolingMakeDtlId != null){
		 	url = url + "&toolingMakeDetail.id=" + toolingMakeDtlId
		 }
		 popupModalDialog(url,800,600);
		 var reloadUrl = "${req.contextPath}/toooling/purchaseContract/listRepairMaintenancePurchaseConcat.html?readOnly=${req.getParameter('readOnly')?if_exists}&purchaseBill.id=#{purchaseBill.id}";
		//如果是点击项目号进入维护页面，保存当前页，已使在维护页面点击关闭，会回到当前页
        if (null != toolingMakeDtlId) {
        	reloadUrl = reloadUrl + '&pagingPage=${pagingPage?if_exists}'
        }
		self.location.href = reloadUrl;
	 }
	
	 //弹出采购单明细查询页面
        function open_PurchaseBillDialog(){
        		var url = '${req.contextPath}/toooling/purchaseContract/listPurchaseBillchooses.html?detailType=REPAIR_MAINTENANCE&purchaseBill.id='+#{purchaseBill.id};
        		popupModalDialog(url, 800, 600,choose_toolingPurchaseBill_information)
        	}
        	function choose_toolingPurchaseBill_information(result){
	        	  if (null != result) {
		          var purchaseBillDetailIds = result.substring(0, result.lastIndexOf(","));
		          document.forms[0].elements["addPurchaseBillDetailIds"].value = purchaseBillDetailIds;
		          document.forms[0].elements["addPurchaseBill"].value = "addPurchaseBills";
		          document.forms[0].submit();
	        	  }
        	}
	     //保存采购单明细
        function savePurchaseDtl(){
        	/* 判断是否数量为整数 */
	        if(!checkInputNumber()){
	           //alert("${action.getText('spareOutBill.checkInputNumber')}");
	           return false;
	        }
	        /* 判断是否输入的价格为数字 */
	        if(!checkInputUnitPrice()){
	           //alert("${action.getText('spareOutBill.checkInputNumber')}");
	           return false;
	        }
          	if(0!=document.getElementsByName("purchaseContractToolingMakeIds").length){ //判断采购单明细中是否有记录
        		if(true){							//验证信息安全
        			retrievePurchaseContractDetailIdText();							//获得采购单明细的所有ID
        			retrievePurchaseContractDetailAmountText();						//获得采购单明细的所有数量
        			retrievePurchaseContractDetailUnitPriceText();					//获得采购单明细的所有单价
        			retrievePurchaseContractDetailRequestDateText();				//获得采购单明细的期望到货日期
        			return true;
        		}else{
        			return false;
        		}
        	}
        }
         function retrievePurchaseContractDetailIdText(){
	        var allPurchaseBillConDtlId = document.getElementsByName("purchaseContractToolingMakeIds");
	        var ary = new Array();
	        for (var i=0; i<allPurchaseBillConDtlId.length; i++) {
	          ary.push(allPurchaseBillConDtlId[i].value);
	        }
	        document.forms[0].elements["allPurchaseContractDtlIds"].value = ary;
	     }
	     /*
	      *  获取申购单明细数量的所有值
	      */
	     function retrievePurchaseContractDetailAmountText(){
	        var allPurchaseBillConDtlId = document.getElementsByName("purchaseContractToolingMakeIds");
	        var allAmountNumberValue = document.getElementsByName("amount");
	        var ary = new Array();
           for (var i=0; i<allPurchaseBillConDtlId.length; i++) {
              if ('' != allAmountNumberValue[i].value) {
              var amount = parseInt(formatDigital(allAmountNumberValue[i].value));
               ary.push(allPurchaseBillConDtlId[i].value);
               ary.push(amount);
             }
           }
          document.forms[0].elements["allPurchaseContractDetailAmountNumber"].value=ary;
        }
        /*
	     *  获取申购单明细单价的所有值
	     */
	    function  retrievePurchaseContractDetailUnitPriceText(){
	       var allPurchaseBillConDtlId = document.getElementsByName("purchaseContractToolingMakeIds");
	        var allUnitPriceValue = document.getElementsByName("unitPrice");
	        var ary = new Array();
           for (var i=0; i<allPurchaseBillConDtlId.length; i++) {
              if ('' != allUnitPriceValue[i].value) {
              var unitPrice = parseFloat(formatDigital(allUnitPriceValue[i].value));
               ary.push(allPurchaseBillConDtlId[i].value);
               ary.push(unitPrice);
             }
           }
          document.forms[0].elements["allPurchaseContractDetailUnitPrice"].value=ary;
	    }
	    /*
	    *获取申购单明细需要日期的所有值
	    */ 
	    function retrievePurchaseContractDetailRequestDateText(){
	    	 var allPurchaseBillConDtlId = document.getElementsByName("purchaseContractToolingMakeIds");
	    	 var ary = new Array();
	    	 for (var i=0; i<allPurchaseBillConDtlId.length; i++) {
	    	  	var inDetailRequestDateTagName='request.Date';
	    	  	inDetailRequestDateTagName = inDetailRequestDateTagName + (i+1);
	    	  	if ('' != document.forms["listForm"].elements[inDetailRequestDateTagName].value) {
               	ary.push(allPurchaseBillConDtlId[i].value);
               	ary.push(document.forms[0].elements[inDetailRequestDateTagName].value);
             	}
	    	 }
	    	document.forms[0].elements["allPurchaseContractDetailRequestDate"].value=ary; 
	    }	
	    /* 判断数量格式 */
	  	function checkInputNumber(){
	      	var selector = document.getElementsByName("purchaseContractToolingMakeIds");
	        var valueselector = document.getElementsByName("amount");
	       	if (selector.length) {
		        for (var i = 1; i < selector.length+1; i++) {
		           if(valueselector[i-1].value!=""){
	            	  	if (!isDoubleNumberCheck(valueselector[i-1].value)){   			//验证输入数量的格式
			         		alert("${action.getText('di')}"+(i)+"${action.getText('toolingMake.number.formate.error')}");
			         		return false;
	       				}else if(!isNumberBetweenBoolen(valueselector[i-1].value, 1000000001, 0)){		//验证输入数量的范围
		       				alert("${action.getText('di')}"+(i)+"${action.getText('toolingMake.number.maxLength')}");
		       				return false;
	       				}
	               }else if(valueselector[i-1].value==""){
	               alert("${action.getText('di')}"+(i)+"${action.getText('purchaseContractToolingMake.number')}");
	              	    return false;
	               }
		        }
		        return true;
			}			         
		}
	    
	     /* 判断单价格式 */
	  	function checkInputUnitPrice(){
	      	var selector = document.getElementsByName("purchaseContractToolingMakeIds");
	        var valueselector = document.getElementsByName("unitPrice");
	       	if (selector.length) {
		        for (var i = 1; i < selector.length+1; i++) {
		           if(valueselector[i-1].value!=""){
	            	  	if (!isDoubleNumberCheck(valueselector[i-1].value)){   			//验证输入的单价的格式
			         		alert("${action.getText('di')}"+(i)+"${action.getText('toolingMake.unitPrice.formate.error')}");
			         		return false;
	       				}
	               }else if(valueselector[i-1].value==""){
	               alert("${action.getText('di')}"+(i)+"${action.getText('purchaseContractToolingMake.unitPrice')}");
	              	    return false;
	               }
		        }
		        return true;
			}			         
		}
</script>
</@framePage>