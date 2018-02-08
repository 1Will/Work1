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
<@framePage title="${action.getText('toolingPurchaseMakeDetail.title')}">
	<link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
    <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
   	<script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
   	<script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script> 
	<@ww.form namespace="'/tooling/prophase/business'" name="'listForm'" action="'searchPurchaseToolingPurchaseMakeDetails'" method="'post'">
		<@ww.token name="searchPurchasetoolingPurchaseMakeDetailToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'detailType'" value="'${detailType?if_exists}'"/>
		<@ww.hidden name="'addQuarterPlanDetailIds'" value=""/>
		<@ww.hidden name="'quarterPlanDetailSelector'" value=""/>
		<input type="hidden" name="allPurToolingMakeDetailIds" value=""/>
		<input type="hidden" name="allPurToolingMakeDetailUnitPrice" value=""/>
		<input type="hidden" name="allPurToolingMakeDetailAmount" value=""/>
		<input type="hidden" name="allPurToolingMakeDetailRequestDate" value=""/>
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
            <a href="#" onclick="return open_toolingMakeDtlDialog(#{subscribe.id}, #{object.id})">${itemNo}</a>
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
       <@vcolumn title="${action.getText('reqReason')}" property="reqReason" attributes="width='100'">
        	<@alignLeft />
       </@vcolumn>
       <@vcolumn title="${action.getText('comment')}" property="comment" attributes="width='100'">
        	<@alignLeft />
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
    	 	<input type="button" class="button" name="new" value="${action.getText('new')}" onclick="open_toolingMakeDtlDialog(#{subscribe.id}, null)"/>
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
   	       alert("${action.getText('delete.ToolingMakeDetail.failure')}");
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
             parent.document.getElementById("subscribe.totalPrice").value = '${(subscribe.totalPrice?string('#,###,##0.00'))?if_exists}';
        </#if>
		//显示原输入的信息
		<#if valueAry?exists>
	       var valueAry="${valueAry}";
	       if(valueAry!=""){
	          var ary=valueAry.split("##");
	          for(var i=0;i<ary.length;i=i+3){
	              setSubscribeDtlValue(ary[i],ary[i+1],ary[i+2]);
	          }
	       }
	    </#if>
	}
	
	function setSubscribeDtlValue(subscribeDtlId,subscribeDtlUntiPriceValue,subscribeDtlAmountValue){
   	  var selector = document.getElementsByName("purToolingPurchaseMakeDtlIds");
	  var allUnitPriceValue = document.getElementsByName("unitPrice");
	  var allAmountValue = document.getElementsByName("amount");
       if (selector.length) {
	        for (var i = 0; i < selector.length; i++) {
	             if(selector[i].value==subscribeDtlId){
	                 allUnitPriceValue[i].value=subscribeDtlUntiPriceValue;
	                 allAmountValue[i].value=subscribeDtlAmountValue;
	             }
	        }
	   }
	}
	
	//新建、更新
	function open_toolingMakeDtlDialog(subscribeId,toolingMakeDtlId){
		 var url = '${req.contextPath}/tooling/prophase/business/editPurchaseToolingMakeDetail.html?subscribe.id='+subscribeId+'&readOnly=${req.getParameter('readOnly')?if_exists}';	
		 if(toolingMakeDtlId != null){
		 	url = url + "&subscribeDtl.id=" + toolingMakeDtlId
		 }
		 popupModalDialog(url,800,600);
		 callBackSubscribeDtlInformation();
		 var reloadUrl = "${req.contextPath}/tooling/prophase/business/listPurchaseToolingPurchaseMakeDetails.html?subscribe.id=#{subscribe.id}&readOnly=${req.getParameter('readOnly')?if_exists}";
		//如果是点击项目号进入维护页面，保存当前页，已使在维护页面点击关闭，会回到当前页
        if (null != toolingMakeDtlId) {
        	reloadUrl = reloadUrl + '&pagingPage=${pagingPage?if_exists}'
        }
		self.location.href = reloadUrl;
	}
	//从季度计划选择
	function open_quarterPlanToolingMakeDtlDialog(){
		var url = '${req.contextPath}/tooling/prophase/business/listPurchaseToolingMakeDetailFromQuarterPlanDetail.html?detailType=TOOLING_MAKE&subscribeId='+#{subscribe.id};
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
	
	//信息安全验证
	function informationValidate(){
		var allSubscribeDtlId = document.getElementsByName("purToolingPurchaseMakeDtlIds");
	    var allUnitPriceValue = document.getElementsByName("unitPrice");
	    var allNumberValue = document.getElementsByName("amount");
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
	  var valueAry="";
	  if (selector.length) {
	  	for(var i=0;i<selector.length;i++){
	  	  if (!selector[i].checked) {
	        valueAry+=selector[i].value+"##";
	        valueAry+=allUnitPriceValue[i].value+"##";
	        valueAry+=allAmountValue[i].value+"##";
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