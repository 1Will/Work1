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

<#include "../../../../includes/eam2008.ftl" />
<@framePage title="${action.getText('sparePurchaseDetail.title')}">
	<link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
    <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
   	<script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
   	<script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script> 
	<@ww.form namespace="'/tooling/prophase/business'" name="'listForm'" action="'searchPurchaseSparePurchaseDetails'" method="'post'">
		<@ww.token name="searchPurchaseSparePurchaseDetailToken"/>
		<#--
		<@ww.hidden name="'detailType'" value="'${req.getParameter('detailType')?if_exists}'"/>
		-->
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'addQuarterPlanDetailIds'" value=""/>
		<@ww.hidden name="'quarterPlanDetailSelector'" value=""/>
		<@ww.hidden name="'addSpareDetailIds'" value=""/>
		<@ww.hidden name="'spareAccountSelector'" value=""/>
		<input type="hidden" name="allPurToolingMakeDetailIds" value=""/>
		<input type="hidden" name="allPurToolingMakeDetailUnitPrice" value=""/>
		<input type="hidden" name="allPurToolingMakeDetailAmount" value=""/>
		<input type="hidden" name="allPurToolingMakeDetailRequestDate" value=""/>
		<input type="hidden" name="allPurToolingMakeDetailReqReason" value=""/>
		<input type="hidden" name="allPurToolingMakeDetailComment" value=""/>
		<input type="hidden" name="valueAry" value=""/>
		<@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
		<#if subscribe.id?exists>
			<@ww.hidden name="'subscribe.id'" value="'#{subscribe.id}'"/>
		</#if>
		<#assign itemNo=1/>
		<#assign requestDateIdentityName = 'request.Date' + '${itemNo}'/>
		<#assign requestDateImgIdentity = 'listForm_' + '${requestDateIdentityName}' + '_trigger'/>
		<@list title="" excel=false setupTable=false includeParameters="subscribe.id" fieldMap="">
		<@vlh.checkbox property="id" name="purToolingPurchaseMakeDtlIds">
             <@vlh.attribute name="width" value="30" />
        </@vlh.checkbox>
	    <@vcolumn title="${action.getText('itemNo')}">
            ${itemNo}
            <@alignCenter />
        </@vcolumn>
        <#assign itemNo = itemNo + 1/>
        <@vcolumn title="${action.getText('graphNo')}" property="graphNo">
        	<@alignLeft />
        </@vcolumn>
        <@vcolumn title="${action.getText('name')}" property="name" attributes="width='100'">
        	<@alignLeft />
        </@vcolumn>
        <@vcolumn title="${action.getText('categoryName')}" property="categoryName">
        	<@alignLeft />
        </@vcolumn>
        <@vcolumn title="${action.getText('detailCategoryName')}" property="detailCategoryName">
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
        <@vcolumn title="${action.getText('unitPrice')}">
         <input type="text" name="unitPrice" 
    		   class="underline"  value="${(object.unitPrice?string('#,###,##0.00'))?if_exists}"  maxlength="50" size="10" style="text-align:right"/>
    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('amount')}">
          <input type="text" name="amount" 
    		   class="underline"  value="${object.amount?if_exists}"  maxlength="50" size="10" style="text-align:right"/>
    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('totalPrice')}" property="totalPrice" format="${action.getText('currencyFormat')}">
        	<@alignRight />
        </@vcolumn>
		<@vcolumn title="${action.getText('reqDate')}">
 			<#assign requirementDate = ''/>
    		<#if object.requireDate?exists>
	          <#assign requirementDate = "${(object.requireDate?string('yyyy-MM-dd'))}"/>
	        </#if>
    		<@eam2008_dataPicker inputName="${requestDateIdentityName}" 
    			inputId="${requestDateIdentityName}" 
    			inputValue="${requirementDate}" 
    			imgId="${requestDateImgIdentity}" 
    			formName="listForm"/>
    		<#assign requestDateIdentityName = 'request.Date' + '${itemNo}'/>
			<#assign requestDateImgIdentity = 'listForm_' + '${requestDateIdentityName}' + '_trigger'/>	
       </@vcolumn>
       <@vcolumn title="${action.getText('reqReason')}">
        	 <input type="text" name="reqReason" 
    		   class="underline"  value="${object.reqReason?if_exists}"  maxlength="250" size="10"/>
    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
       </@vcolumn>
       <@vcolumn title="${action.getText('comment')}">
        	 <input type="text" name="comment" 
    		   class="underline"  value="${object.comment?if_exists}"  maxlength="250" size="10"/>
    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
       </@vcolumn>
       <#assign surscribeToPurchaseStatus = ''/>
       <#if '${object.status}' == 'PURCHASEED'>
           <#assign surscribeToPurchaseStatus = "${action.getText('PURCHASEED')}"/>
          <#else>
              <#assign surscribeToPurchaseStatus = "${action.getText('UNRCHASEED')}"/>
       </#if>
         <@vcolumn title="${action.getText('status')}" attributes="width='50'">
         ${surscribeToPurchaseStatus}
             <@alignLeft/>
       </@vcolumn>
       </@list>
         <#if !first>
    	 <#if !(action.isReadOnly())>
       <@buttonBar>
    	 	<input type="button" class="button" name="new" value="${action.getText('new')}" onclick="open_toolingMakeDtlDialog()"/>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return submitDetailIds()'">
            	<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
			<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('detelePurchaseDetail')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return deleteSubscribeDtlValue(\"purToolingPurchaseMakeDtlIds\", \"${confirmMessage}\")'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            <@vbutton name="'new'"  class="button" value="${action.getText('fromQuanterPlanSelect')}" onclick="open_quarterPlanToolingMakeDtlDialog()"/>
       </@buttonBar>
        </#if>
        </#if>
	</@ww.form>
</@framePage>
<script language="javascript">
	window.onload = function(){
		<#if req.getParameter('errorFlag')?has_content>
   	       alert("${action.getText('delete.sparePurchase.failure')}");
        </#if>
   		//更新当前采购单状态
   		<#if subscribe.status?exists>
   		    <#assign subscribeStatus = ''/>
   			<#if '${subscribe.status}' == 'NEWPURCHASE'>
   			<#assign subscribeStatus = "${action.getText('NEWPURCHASE')}"/>
   			<#elseif '${subscribe.status}' == 'PURCHASING'>
   			<#assign subscribeStatus = "${action.getText('PURCHASING')}"/>
   			<#elseif '${subscribe.status}' == 'PURCHASEED'>
   			<#assign subscribeStatus = "${action.getText('PURCHASEED')}"/>
   			</#if>
   			parent.document.forms["subscribe"].elements["subscribe.status"].value = '${subscribeStatus}';
   		</#if>
   		<#if subscribe.id?exists>
             parent.getObjByNameRe("subscribe.totalPrice").value = '${(subscribe.totalPrice?string('#,###,##0.00'))?if_exists}';
        </#if>
		//显示原输入的信息
		<#if valueAry?exists>
	       var valueAry="${valueAry}";
	       if(valueAry!=""){
	          var ary=valueAry.split("##");
	          for(var i=0;i<ary.length;i=i+5){
	              setSubscribeDtlValue(ary[i],ary[i+1],ary[i+2],ary[i+3],ary[i+4]);
	          }
	       }
	    </#if>
	}
	
	function setSubscribeDtlValue(subscribeDtlId,subscribeDtlUntiPriceValue,subscribeDtlAmountValue,subscribeDtlReqSeasonValue,subscribeDtlCommentValue){
   	  var selector = document.getElementsByName("purToolingPurchaseMakeDtlIds");
	  var allUnitPriceValue = document.getElementsByName("unitPrice");
	  var allAmountValue = document.getElementsByName("amount");
	  var allReqReasonValue = document.getElementsByName("reqReason");
	  var allCommentValue = document.getElementsByName("comment");
       if (selector.length) {
	        for (var i = 0; i < selector.length; i++) {
	             if(selector[i].value==subscribeDtlId){
	                 allUnitPriceValue[i].value=subscribeDtlUntiPriceValue;
	                 allAmountValue[i].value=subscribeDtlAmountValue;
	                 allReqReasonValue[i].value=subscribeDtlReqSeasonValue;
	                 allCommentValue[i].value=subscribeDtlCommentValue;
	             }
	        }
	   }
	}
	
	//从备件台帐选择
	function open_toolingMakeDtlDialog(){
	  var url = '${req.contextPath}/popup/spareSelector.html?inOutFlag=SPARE_PURCHASE&toolingDevFlag=TOOLING&spareBillId='+#{subscribe.id};
	  popupModalDialog(url, 800, 600,choose_spareAccount_information)
	}
	
	function choose_spareAccount_information(result){
		callBackSubscribeDtlInformation();
      if (null != result) {
        var spareDetailIds = result.substring(0, result.lastIndexOf(","));
        document.forms[0].elements["addSpareDetailIds"].value = spareDetailIds;
        document.forms[0].elements["spareAccountSelector"].value = "spareAccount";
        document.forms[0].submit();
      }
	}
	
	//从季度计划选择
	function open_quarterPlanToolingMakeDtlDialog(){
		var url = '${req.contextPath}/tooling/prophase/business/listPurchaseSparePurchaseDetailFromQuarterPlanDetail.html?detailType=SPARE_PURCHASE&subscribeId='+#{subscribe.id};
		popupModalDialog(url, gw, gh, refresh_purchaseToolingMakeDtl_information);
	}
	function  refresh_purchaseToolingMakeDtl_information(result){
		callBackSubscribeDtlInformation();
	  if(null!=result){
	     var purBillToolingMakeDtlIds = result.substring(0, result.lastIndexOf(","));
	     document.forms[0].elements["addQuarterPlanDetailIds"].value = purBillToolingMakeDtlIds;
         document.forms[0].elements["quarterPlanDetailSelector"].value = "quartePlanDetail";
         document.forms[0].submit();
	  }
	}
	//保存记录
	function submitDetailIds(){
		if(0!=document.getElementsByName("purToolingPurchaseMakeDtlIds").length){
			if(informationValidate()==true){
				retrieveToolingMakeDetailIdText();	
				retrieveToolingMakeDetailUnitPriceText();	//保存单价
				retrieveToolingMakeDetailAmountText();		//保存数量
				retrieveToolingMakeDetailRequestDateText();	//需求日期
				retrieveToolingMakeDetailReqReasonText()
				retrieveToolingMakeDetailCommentText()
				return true;
			}else{
				return false;
        	}
		}
	}
	
	function retrieveToolingMakeDetailIdText(){
		var allSubscribeDtlId = document.getElementsByName("purToolingPurchaseMakeDtlIds");
	    var ary = new Array();
	    for (var i=0; i<allSubscribeDtlId.length; i++) {
	      ary.push(allSubscribeDtlId[i].value);
	    }
	    document.forms[0].elements["allPurToolingMakeDetailIds"].value = ary;
	}
	
	function retrieveToolingMakeDetailUnitPriceText(){
		var allSubscribeDtlId = document.getElementsByName("purToolingPurchaseMakeDtlIds");
        var allUnitPriceValue = document.getElementsByName("unitPrice");
        var ary = new Array();
        for (var i=0; i<allSubscribeDtlId.length; i++) {
          if ('' != allUnitPriceValue[i].value) {
          var unitPrice = parseFloat(formatDigital(allUnitPriceValue[i].value));
           ary.push(allSubscribeDtlId[i].value);
           ary.push(unitPrice);
          }
        }
        document.forms[0].elements["allPurToolingMakeDetailUnitPrice"].value=ary;
	}
	
	function retrieveToolingMakeDetailAmountText(){
		var allSubscribeDtlId = document.getElementsByName("purToolingPurchaseMakeDtlIds");
        var allAmountNumberValue = document.getElementsByName("amount");
        var ary = new Array();
        for (var i=0; i<allSubscribeDtlId.length; i++) {
          if ('' != allAmountNumberValue[i].value) {
          var amount = parseFloat(formatDigital(allAmountNumberValue[i].value));
           ary.push(allSubscribeDtlId[i].value);
           ary.push(amount);
          }
        }
        document.forms[0].elements["allPurToolingMakeDetailAmount"].value=ary;
	}
	function retrieveToolingMakeDetailRequestDateText(){
		var allSubscribeDtlId = document.getElementsByName("purToolingPurchaseMakeDtlIds");
	    var ary = new Array();
	    for (var i=0; i<allSubscribeDtlId.length; i++) {
	    	 var inDetailRequestDateTagName='request.Date';
	    	 inDetailRequestDateTagName = inDetailRequestDateTagName + (i+1);
	    	 if ('' != document.forms["listForm"].elements[inDetailRequestDateTagName].value) {
             	ary.push(allSubscribeDtlId[i].value);
             	ary.push(document.forms[0].elements[inDetailRequestDateTagName].value);
             }
	    }
	    document.forms[0].elements["allPurToolingMakeDetailRequestDate"].value=ary;
	}
	
	function retrieveToolingMakeDetailReqReasonText(){
		var allSubscribeDtlId = document.getElementsByName("purToolingPurchaseMakeDtlIds");
        var allReqReasonNumberValue = document.getElementsByName("reqReason");
        var ary = new Array();
        for (var i=0; i<allSubscribeDtlId.length; i++) {
          if ('' != allReqReasonNumberValue[i].value) {
           ary.push(allSubscribeDtlId[i].value);
           ary.push(allReqReasonNumberValue[i].value);
          }
        }
        document.forms[0].elements["allPurToolingMakeDetailReqReason"].value=ary;
	}
	
	function retrieveToolingMakeDetailCommentText(){
		var allSubscribeDtlId = document.getElementsByName("purToolingPurchaseMakeDtlIds");
        var allCommentValue = document.getElementsByName("comment");
        var ary = new Array();
        for (var i=0; i<allSubscribeDtlId.length; i++) {
          if ('' != allCommentValue[i].value) {
           ary.push(allSubscribeDtlId[i].value);
           ary.push(allCommentValue[i].value);
          }
        }
        document.forms[0].elements["allPurToolingMakeDetailComment"].value=ary;
	}
	
	//信息安全验证
	function informationValidate(){
		var allSubscribeDtlId = document.getElementsByName("purToolingPurchaseMakeDtlIds");
	    var allUnitPriceValue = document.getElementsByName("unitPrice");
	    var allNumberValue = document.getElementsByName("amount");
	    var allReqReasonNumberValue = document.getElementsByName("reqReason");
	     var allCommentValue = document.getElementsByName("comment");
	    for(var i=0;i<allSubscribeDtlId.length;i++){
	    	//验证单价
			if(''!= allUnitPriceValue[i].value){
			  	var unitPrice = allUnitPriceValue[i].value;
			  	if (!isDoubleNumberCheck(unitPrice)){   			//验证格式
		         	alert("${action.getText('row')}"+(i+1)+"${action.getText('subscribeDtl.unitPrice.maxLength')}");
		         	return false;
	       		}else if(!isDoubleNumberBetweenBoolean(unitPrice,1000000001,0)){
	       			alert("${action.getText('row')}"+(i+1)+"${action.getText('subscribeDtl.unitPrice.maxLength')}");
	       			return false;
	       		}
			 }else{
		  		alert("${action.getText('row')}"+(i+1)+"${action.getText('subscribeDtl.unitPrice.maxLength')}");
	   			return false;
			 }
			 if(''!=allNumberValue[i].value){
			 	var number = allNumberValue[i].value
			  	if (!isNumber(number)){  
		         	alert("${action.getText('row')}"+(i+1)+"${action.getText('subscribeDtl.amount.maxLength')}");
		         	return false;
	       		}else if(!isDoubleNumberBetweenBoolean(number,1000000001,0)){
	       			alert("${action.getText('row')}"+(i+1)+"${action.getText('subscribeDtl.amount.maxLength')}");
	       			return false;
	       		}
			 }else{
		  		alert("${action.getText('row')}"+(i+1)+"${action.getText('subscribeDtl.amount.maxLength')}");
	   			return false;
			 }
			 //验证需求原因长度
		  	if(''!= allReqReasonNumberValue[i].value){
		  		var comment = allReqReasonNumberValue[i].value;
		  		if (!isValidLengthCheck(document.forms[0],comment,null,250)) {
	       			alert("${action.getText('row')}"+(i+1)+"${action.getText("sparePurchase.reqReason.maxLength")}");
		   			return false;
	     		}
		  	}
		  	//验证备注长度
		  	if(''!= allCommentValue[i].value){
		  		var comment = allCommentValue[i].value;
		  		if (!isValidLengthCheck(document.forms[0],comment,null,250)) {
	       			alert("${action.getText('row')}"+(i+1)+"${action.getText("sparePurchase.comment.maxLength")}");
		   			return false;
	     		}
		  	}
		 }
		 return true;
	}
	
	//删除按钮
	function deleteSubscribeDtlValue(boxName,message){
	  callBackSubscribeDtlInformation();
	  if(confirmDeletes(boxName, message)==true){
		return true;
	  }else{
	  	return false;
	  }
	}
		
	//保留原有的信息
	function callBackSubscribeDtlInformation(){
   	  var selector = document.getElementsByName("purToolingPurchaseMakeDtlIds");
	  var allUnitPriceValue = document.getElementsByName("unitPrice");
	  var allAmountValue = document.getElementsByName("amount");
	  var allReqReasonValue = document.getElementsByName("reqReason");
	  var allCommentValue = document.getElementsByName("comment");
	  var valueAry="";
	  if (selector.length) {
	  	for(var i=0;i<selector.length;i++){
	  	  if (!selector[i].checked) {
	        valueAry+=selector[i].value+"##";
	        valueAry+=allUnitPriceValue[i].value+"##";
	        valueAry+=allAmountValue[i].value+"##";
	        valueAry+=allReqReasonValue[i].value+"##";
	        valueAry+=allCommentValue[i].value+"##";
	  	  }
	  	}
	  }
	  valueAry=valueAry.substring(0,valueAry.lastIndexOf("##"));
	  document.forms[0].elements["valueAry"].value=valueAry;
   	}
   		
	function isNumber(e) {
		var num=e;
		num = formatDigital(num);
		s = new String(num);
		var regu = "^[0-9]+$";
		var re = new RegExp(regu);
		if (s.search(re) != -1){
			return true;
		} else{
			return false;
		}
	}
</script>