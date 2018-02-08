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
<#--$Id: purchaseBillDetails.ftl 11328 2008-03-15 09:39:30Z mwei $ -->

<#include "../../../../includes/eam2008.ftl" />
<@framePage title="${action.getText('purchaseBillDetails.title')}">	
   <link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
   <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
   <script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
   <script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script>  
  <@ww.form name="'listForm'" namespace="'/toooling/purchaseContract'"action="'searchPurchaseBillDetails'" method="'post'">
		<@ww.token name="searchPurchaseBillDetailsToken"/>
			<@ww.hidden name="'addSubscribeIds'" value=""/>
			<@ww.hidden name="'addSubscribe'" value=""/>
		    <@ww.hidden name="'addSpareDetailIds'" value=""/>
		    <@ww.hidden name="'spareAccountSelector'" value=""/>
		    <@ww.hidden name="'allPurchaseBillDtlIds'" value=""/>
		    <@ww.hidden name="'allPurchaseBillDetailAmountNumber'" value=""/>
		    <@ww.hidden name="'allPurchaseBillDetailUnitPrice'" value=""/>
		    <@ww.hidden name="'allPurchaseBillDetailRequestDate'" value=""/>
		    <@ww.hidden name="'allPurchaseBillDetailComment'" value=""/>
			<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
			<@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
			<@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
		<#if purchaseBill.id?exists>
		<@ww.hidden name="'purchaseBill.id'" value="'#{purchaseBill.id}'"/>
		</#if>
		<#if purchaseBillDtl?exists>
          <@ww.hidden name="'purchaseBillDtl.id'" value="'${purchaseBillDtl.id?if_exists}'"/>
         </#if>
		<#assign itemNo=1/>
		<#assign requestDateIdentityName = 'request.Date' + '${itemNo}'/>
		<#assign requestDateImgIdentity = 'listForm_' + '${requestDateIdentityName}' + '_trigger'/>
		<@list title="" excel=false setupTable=false includeParameters="purchaseBill.id" fieldMap="like:purchaseBill.id">
			<@vlh.checkbox property="id" name="purchaseBillDtlIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
           <#if purchaseBill.typeStatus?exists>
            <#if purchaseBill.typeStatus=='SPARE'>
                <@vcolumn title="${action.getText('itemNo')}">
                ${itemNo}
                <@alignCenter/>
               </@vcolumn>
               <#else>
               <@vcolumn title="${action.getText('itemNo')}">
                <a href="#" onclick="return open_purchaseBillDtlDialog(#{purchaseBill.id},#{object.id})">${itemNo}</a>
                <@alignCenter/>
               </@vcolumn>
            </#if>
            </#if>
          <#assign itemNo = itemNo + 1/>
           <@vcolumn title="${action.getText('graphNo')}" property="graphNo">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('name')}" property="name">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('model')}" property="model">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('specification')}" property="specification" >
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('subscriber.department')}" property="department" >
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('amount')}">
                <input type="text" name="amount" 
	    		   class="underline"  value="${object.amount?if_exists}"  maxlength="50" size="10" style="text-align:right"/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
            </@vcolumn>
            <@vcolumn title="${action.getText('unitPrice')}">
            	 <input type="text" name="unitPrice" 
	    		   class="underline"  value="${(object.unitPrice?string('#,###,##0.##'))?if_exists}"  maxlength="50" size="10" style="text-align:right"/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
            </@vcolumn>
            <@vcolumn title="${action.getText('total.Price')}" property="totalPrice" format="#,###,##0.##">
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('reqDeliveryDate')}">
            	<#assign requirementDate = ''/>
			    		<#if object.reqDeliveryDate?exists>
				          <#assign requirementDate = "${(object.reqDeliveryDate?string('yyyy-MM-dd'))}"/>
				        </#if>
			    		<@eam2008_dataPicker inputName="${requestDateIdentityName}" 
			    			inputId="${requestDateIdentityName}" 
			    			inputValue="${requirementDate}" 
			    			imgId="${requestDateImgIdentity}" 
			    			formName="listForm"/>
			    		<#assign requestDateIdentityName = 'request.Date' + '${itemNo}'/>
						<#assign requestDateImgIdentity = 'listForm_' + '${requestDateIdentityName}' + '_trigger'/>
            </@vcolumn>
             <@vcolumn title="${action.getText('comment')}">
            	 <input type="text" name="comment" 
	    		   class="underline"  value="${object.comment?if_exists}"  maxlength="50" size="10"/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
            </@vcolumn>
            <#assign purchasedtlStatus = ''/>
            <#if object.status?exists>
              <#if '${object.status}' == 'unSPECT'>
               <#assign purchasedtlStatus = "${action.getText('unSPECT')}"/>
              <#else>
              <#assign purchasedtlStatus = "${action.getText('INSPECTED')}"/>
            </#if>
             <#if '${object.status}' == 'INWAREHOUSEED'>
              <#assign purchasedtlStatus = "${action.getText('INWAREHOUSEED')}"/>
            </#if>
            </#if>
         	<@vcolumn title="${action.getText('purchase.state')}" attributes="width='50'">
           		${purchasedtlStatus}
                 <@alignLeft/>
            </@vcolumn>
		</@list>
        <@buttonBar>
        <#if !first>
         <#if !(action.isReadOnly())>
         <#if purchaseBill.typeStatus?exists>
           <#if purchaseBill.typeStatus=='TOOLING'>
               <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="open_purchaseBillDtlDialog(#{purchaseBill.id}, null)"/>
            <#else>
                 <@vbutton name="'select'"  class="button" value="${action.getText('deviceAccountSelect')}" onclick="open_selectDeviceAccountDialog()"/>   
            </#if>
           </#if>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savePurchaseDtl()'">
            	<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('purchaseBillDtl')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"purchaseBillDtlIds\", \"${confirmMessage}\")'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            <@vbutton name="'new'"  class="button" value="${action.getText('purchaseCouchoosesub')}" onclick="open_subscribeBillDtlDialog()"/>
            </#if>
            </#if>
            <#--
             <#if !(action.isReadOnly())>
            <@vbutton name="'new'"  class="button" value="${action.getText('choosesub')}" onclick="open_subscribeBillDtlDialog()"/>
            </#if>
            -->
        </@buttonBar>
        <script language="javascript">
    
          window.onload=function(){
         <#if purchaseBill.id?exists>
           
             parent.document.getElementById("totalPrice").value = '${purchaseBill.totalPrice?if_exists}';
           
          </#if>
          <#if req.getParameter('errorFlag')?has_content>
            alert("${action.getText('delete.purchaseDtl.failure')}");
          </#if>
          <#if purchaseBill.typeStatus?exists>
          <#if purchaseBill.purchaseBillDetails?exists>
   			<#if '${purchaseBill.purchaseBillDetails.size()}'!= '0'>
  				parent.document.all("typeStatus").disabled = true;
  			<#else>
  				parent.document.all("typeStatus").disabled = false;
  			</#if>
  			
   		  </#if>
   		  <#else>
   		  	parent.document.all("typeStatus").disabled = false;
   		  </#if>
   		  //更新当前采购单状态
       	  <#if purchaseBill.status?exists>
   		    <#assign purchaseBillStatus = ''/>
   			<#if '${purchaseBill.status}' == 'NEWSTATUS'>
   			<#assign purchaseBillStatus = "${action.getText('NEWSTATUS')}"/>
   			<#elseif '${purchaseBill.status}' == 'INSPECTING'>
   			<#assign purchaseBillStatus = "${action.getText('INSPECTING')}"/>
   			<#elseif '${purchaseBill.status}' == 'INSPECTED'>
   			<#assign purchaseBillStatus = "${action.getText('INSPECTED')}"/>
   			</#if>
   			parent.document.forms["purchaseBill"].elements["purchaseBill.status"].value = '${purchaseBillStatus}';
   		  </#if>
   		  <#if !(action.isReadOnly())>
			<#if purchaseBill.submit==true>
		      parent.document.forms[0].elements["submitRecord"].disabled=false;
		     <#if purchaseBill.purchaseBillDetails.size()==0>
		    parent.document.forms[0].elements["submitRecord"].disabled=true;
		    </#if>
	      </#if>
	      </#if>
          }
          //从备件台帐选择
        	function open_selectDeviceAccountDialog(){
        		var url = '${req.contextPath}/popup/spareSelector.html?toolingDevFlag=${toolingDevFlag?if_exists}&inOutFlag=purchase&spareBillId='+#{purchaseBill.id};
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
          //弹出申购单明细查询页面  从申购单明细中选择申购单进行采购
            function open_subscribeBillDtlDialog(){

             	<#--//var url = '${req.contextPath}/prophase/business/listSubscribechooses.html?purchaseContractType=${purchaseBill.typeStatus}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
            var url = '${req.contextPath}/tooling/prophase/contract/listSubscribechooses.html?purchaseContractType=${purchaseBill.typeStatus}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
            -->
          	<#if purchaseBill.typeStatus?exists>
             	var url = '${req.contextPath}/tooling/prophase/contract/listSubscribechooses.html?purchaseContractType=${purchaseBill.typeStatus}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
             	choose_subscribeBillDetail(url,choose_subscribeBill_Detail_information);
             	<#else>
             	alert("${action.getText('please.select.purchaseContract.typeStatus')}");
        		return false;
             	<#--var url = '${req.contextPath}/prophase/business/listSubscribechooses.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';-->

             	</#if>
             	
            }
               function choose_subscribeBill_Detail_information(reslut){
                if (null != result) {
		        var addSubscribeIds = result.substring(0, result.lastIndexOf(","));
		        document.forms[0].elements["addSubscribeIds"].value =addSubscribeIds;
		        document.forms[0].elements["addSubscribe"].value = "addSubscribes";
		        document.forms[0].submit();
		      	}
              }
        	function open_purchaseBillDtlDialog(purchaseId,purchaseBillDtlId) {
        		//var url = '${req.contextPath}/prophase/editPurchaseBillDetail.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}&purchaseBill.id=' + purchaseId;
        		var url = '${req.contextPath}/toooling/purchaseContract/editPurchaseBillDetail.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}&purchaseBill.id=' + purchaseId;
        	
        		if (null != purchaseBillDtlId) {
        			url = url+ '&purchaseBillDtl.id=' + purchaseBillDtlId;
        		}
	      		popupModalDialog(url, Config.popW+100, Config.popH);
	      		//self.location.reload();
	      		var reloadUrl = '${req.contextPath}/toooling/purchaseContract/listPurchaseBillDetails.html?purchaseBill.id=' + purchaseId+'&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
	               //如果是点击项目号进入维护页面，保存当前页，已使在维护页面点击关闭，会回到当前页
	           if (null != purchaseBillDtlId) {
	            	reloadUrl = reloadUrl + '&pagingPage=${pagingPage?if_exists}'
	            }
	            self.location.href = reloadUrl;
	      		return false;
        	}
        //保存采购单明细
        function savePurchaseDtl(){
      
          	if(0!=document.getElementsByName("purchaseBillDtlIds").length){ //判断采购单明细中是否有记录
        		<#if purchaseBill.typeStatus?exists>
        		if(informationValidate()==true){
        			retrievePurchaseDetailIdText();							//获得采购单明细的所有ID
        			retrievePurchaseDetailAmountText();						//获得采购单明细的所有数量
        			retrievePurchaseDetailUnitPriceText();					//获得采购单明细的所有单价
        			retrievePurchaseDetailRequestDateText();				//获得采购单明细的所有需要日期
        			retrievePurchaseDetailCommentText();					//获得采购单明细的所有备注
        	        return true;
        		}else{
        			return false;
        		}
        		<#else>
        			alert("${action.getText('please.select.purchaseContract.typeStatus')}");
        			return false;
        		</#if>
        		
			}
		
			
        }
         function retrievePurchaseDetailIdText(){
	        var allPurchaseBillDtlId = document.getElementsByName("purchaseBillDtlIds");
	        var ary = new Array();
	        for (var i=0; i<allPurchaseBillDtlId.length; i++) {
	          ary.push(allPurchaseBillDtlId[i].value);
	        }
	        document.forms[0].elements["allPurchaseBillDtlIds"].value = ary;
	     }
	     /*
	      *  获取申购单明细数量的所有值
	      */
	     function retrievePurchaseDetailAmountText(){
	        var allPurchaseBillDtlId = document.getElementsByName("purchaseBillDtlIds");
	        var allAmountNumberValue = document.getElementsByName("amount");
	        var ary = new Array();
           for (var i=0; i<allPurchaseBillDtlId.length; i++) {
              if ('' != allAmountNumberValue[i].value) {
              var amount = parseFloat(formatDigital(allAmountNumberValue[i].value));
               ary.push(allPurchaseBillDtlId[i].value);
               ary.push(amount);
             }
           }
          document.forms[0].elements["allPurchaseBillDetailAmountNumber"].value=ary;
        }
        /*
	     *  获取申购单明细单价的所有值
	     */
	    function  retrievePurchaseDetailUnitPriceText(){
	       var allPurchaseBillDtlId = document.getElementsByName("purchaseBillDtlIds");
	        var allUnitPriceValue = document.getElementsByName("unitPrice");
	        var ary = new Array();
           for (var i=0; i<allPurchaseBillDtlId.length; i++) {
              if ('' != allUnitPriceValue[i].value) {
              var unitPrice = parseFloat(formatDigital(allUnitPriceValue[i].value));
               ary.push(allPurchaseBillDtlId[i].value);
               ary.push(unitPrice);
             }
           }
          document.forms[0].elements["allPurchaseBillDetailUnitPrice"].value=ary;
	    }
	    /*
	    *获取申购单明细需要日期的所有值
	    */ 
	    function retrievePurchaseDetailRequestDateText(){
	    	 var allPurchaseBillDtlId = document.getElementsByName("purchaseBillDtlIds");
	    	 var ary = new Array();
	    	 for (var i=0; i<allPurchaseBillDtlId.length; i++) {
	    	  	var inDetailRequestDateTagName='request.Date';
	    	  	inDetailRequestDateTagName = inDetailRequestDateTagName + (i+1);
	    	  	if ('' != document.forms["listForm"].elements[inDetailRequestDateTagName].value) {
               	ary.push(allPurchaseBillDtlId[i].value);
               	ary.push(document.forms[0].elements[inDetailRequestDateTagName].value);
             	}
	    	 }
	    	document.forms[0].elements["allPurchaseBillDetailRequestDate"].value=ary; 
	    }
	
	    /*
	     *  获得所有的采购明细中备注的值
	    */
	    function retrievePurchaseDetailCommentText(){
	    	var allPurchaseBillDtlId = document.getElementsByName("purchaseBillDtlIds");
	    	var allCommentValue = document.getElementsByName("comment");
	     	var ary = new Array();
	     	for(var i=0;i<allPurchaseBillDtlId.length;i++){
	     	   if ('' != allCommentValue[i].value) {
               ary.push(allPurchaseBillDtlId[i].value);
               ary.push(allCommentValue[i].value);
             }
	     	}
	      document.forms[0].elements["allPurchaseBillDetailComment"].value=ary;
	    }
	    
	    /*
	    *信息安全验证
	    */
	    function informationValidate(){
	      var allPurchaseBillDtlId = document.getElementsByName("purchaseBillDtlIds");
	      var allAmountNumberValue = document.getElementsByName("amount");
	      var allUnitPriceValue = document.getElementsByName("unitPrice");
	      var allCommentValue = document.getElementsByName("comment");
		  for(var i=0;i<allPurchaseBillDtlId.length;i++){
		  
		  	//验证数量
		  	if(''!= allAmountNumberValue[i].value){
		  		var amount = allAmountNumberValue[i].value;
		  		if (!isDoubleNumberCheck(amount)){   			//验证格式
	         		alert("${action.getText('row')}"+(i+1)+"${action.getText('amount.format.error')}");
	         		return false;
       			}else if(!isNumberBetweenBoolen(amount, 1000000001, 0)){
       				alert("${action.getText('row')}"+(i+1)+"${action.getText('subscribeDtl.amount.maxLength')}");
       				return false;
       			}
		  	}else{
		  	    alert("${action.getText('row')}"+(i+1)+"${action.getText('subscribeDtl.amount.isNull')}");
		  	    return false;
		  	}
		  	
		  	//验证单价
		  	if(''!= allUnitPriceValue[i].value){
		  		var unitPrice = allUnitPriceValue[i].value;
		  		if (!isDoubleNumberCheck(unitPrice)){   			//验证格式
	         		alert("${action.getText('row')}"+(i+1)+"${action.getText('unitPrice.format.error')}");
	         		return false;
       			}else if(!isDoubleNumberBetweenBoolean(unitPrice,1000000001,0)){
       				alert("${action.getText('row')}"+(i+1)+"${action.getText('subscribeDtl.unitPrice.maxLength')}");
       				return false;
       			}
		  	}else{
		  	     alert("${action.getText('row')}"+(i+1)+"${action.getText('subscribeDtl.unitPrice.isNull')}");
		  	    return false;
		  	}

		  
		  	//验证供应商长度
		  	if(''!= allCommentValue[i].value){
		  		var comment = allCommentValue[i].value;
		  		if (!isValidLengthCheck(document.forms[0],comment,null,250)) {
	       			alert("${action.getText('row')}"+(i+1)+"${action.getText("comment.maxLength")}");
		   			return false;
	     		}
		  	}
		  }
		  return true;           	
	    }
        </script>
	</@ww.form>
	
</@framePage>