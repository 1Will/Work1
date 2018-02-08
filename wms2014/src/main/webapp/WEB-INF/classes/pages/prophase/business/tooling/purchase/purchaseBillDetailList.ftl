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

<@framePage title="${action.getText('subscribeDetails.title')}">	
   <link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
   <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
   <script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
   <script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script> 
	<@ww.form namespace="'/tooling/prophase/business'" name="'listForm'" action="'searchPurchaseBillDetails'" method="'post'">
		<@ww.token name="searchSubscribeDetailToken"/>
		<#if subscribe.id?exists>
			<@ww.hidden name="'subscribe.id'" value="'#{subscribe.id}'"/>
		</#if>
		<@ww.hidden name="'addquarterPlanIds'" value=""/>
		<@ww.hidden name="'addquarterPlan'" value=""/>
		<@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
		<@ww.hidden name="'addSpareDetailIds'" value=""/>
		<@ww.hidden name="'spareAccountSelector'" value=""/>
		 <input type="hidden" name="allSubscribeDetailIds" value=""/>
		 <input type="hidden" name="allSubscribeDetailAmountNumber" value=""/>
		 <input type="hidden" name="allSubscribeDetailUnitPrice" value=""/>
		 <input type="hidden" name="allSubscribeDetailRequestDate" value=""/>
		 <input type="hidden" name="allSubscribeDetailSupplierName" value=""/>
		 <input type="hidden" name="allSubscribeDetailComment" value=""/>
		<#assign itemNo=1/>
		<#assign requestDateIdentityName = 'request.Date' + '${itemNo}'/>
		<#assign requestDateImgIdentity = 'listForm_' + '${requestDateIdentityName}' + '_trigger'/>
		 <@list title="" includeParameters="subscribe.id" fieldMap="">
			<@vlh.checkbox property="id" name="subscribeDtlIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <#if subscribe.typeStatus?exists>
            <#if subscribe.typeStatus='TOOLING'>
            <@vcolumn title="${action.getText('itemNo')}">
                <a href="#" onclick="return open_subscribeDtlDialog(#{subscribe.id}, #{object.id})">${itemNo}</a>
                <@alignCenter />
           </@vcolumn>
           <#elseif subscribe.typeStatus='SPARE'>
           	<@vcolumn title="${action.getText('itemNo')}">
                ${itemNo}
             <@alignCenter />
           </@vcolumn>
           </#if>
           </#if>
           <#assign itemNo = itemNo + 1/>
            <@vcolumn title="${action.getText('graphNo')}" property="graphNo">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('name')}" property="name">
            	${object.name}
            	<script language="javascript">
            	 	var toggleV = true;
        			if (toggleV) {
						window.parent.document.forms[0].elements["subscribe.totalPrice"].value = '${object.subscribe.totalPrice}';
						toggleV = false;
					}
				</script>
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('model')}" property="model">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('specification')}" property="specification">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('amount')}">
              <input type="text" name="amount" 
	    		   class="underline"  value="${object.amount?if_exists}"  maxlength="50" size="10" style="text-align:right"/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('unitPrice')}">
             <input type="text" name="unitPrice" 
	    		   class="underline"  value="${(object.unitPrice?string('#,###,##0.##'))?if_exists}"  maxlength="50" size="10" style="text-align:right"/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('totalPrice')}" property="totalPrice" format="#,###,##0.##">
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('交货日期')}">
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
            <@vcolumn title="${action.getText('supplier')}">
            <input type="text" name="supplierName" 
	    		   class="underline"  value="${object.supplierName?if_exists}"  maxlength="60" size="10"/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('comment')}">
             <input type="text" name="comment" 
	    		   class="underline"  value="${object.comment?if_exists}"  maxlength="250" size="10"/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
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
		
        <@buttonBar>
        	<#if !first>
        	 <#if !(action.isReadOnly())>
        	 <#if subscribe.typeStatus?exists>
        	 <#if subscribe.typeStatus == 'TOOLING'>
        	<@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="open_subscribeDtlDialog(#{subscribe.id}, null)"/>
            <#elseif subscribe.typeStatus == 'SPARE'>
            <@vbutton name="'select'"  class="button" value="${action.getText('deviceAccountSelect')}" onclick="open_selectToolingAccountDialog()"/>
            </#if>
            </#if>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return submitDetailIds()'">
            	<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('detelePurchaseDetail')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"subscribeDtlIds\", \"${confirmMessage}\")'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
              <@vbutton name="'new'"  class="button" value="从季度计划选择" onclick="open_quarterPlanDialog()"/>
            </#if>
            </#if>
        </@buttonBar>
        
        <script language="javascript">
        window.onload=function(){
         <#if subscribe.id?exists>
             parent.document.getElementById("subscribe.totalPrice").value = '${subscribe.totalPrice?if_exists}';
          </#if>
   	    <#if req.getParameter('errorFlag')?has_content>
            alert("${action.getText('delete.PurchaseDtl.failure')}");
        </#if>
        <#if subscribe.typeStatus?exists>
        <#if subscribe.subscribeDetails?exists>
   			<#if '${subscribe.subscribeDetails.size()}'!= '0'>
  				parent.document.all("typeStatus").disabled = true;
  			<#else>
  				parent.document.all("typeStatus").disabled = false;
  			</#if>
   		</#if>
   		<#else>
   			parent.document.all("typeStatus").disabled = false;
   		</#if>
   		//更新当前移位单状态
   		<#if subscribe.status?exists>
   		    <#assign subscribeStatus = ''/>
   			<#if '${subscribe.status}' == 'NEWPURCHASE'>
   			<#assign subscribeStatus = "${action.getText('NEWPURCHASE')}"/>
   			<#elseif '${subscribe.status}' == 'PURCHASING'>
   			<#assign subscribeStatus = "${action.getText('PURCHASING')}"/>
   			<#elseif '${subscribe.status}' == 'PURCHASEED'>
   			<#assign subscribeStatus = "${action.getText('PURCHASEED')}"/>
   			</#if>
   			parent.document.forms["subscribe"].elements["status"].value = '${subscribeStatus}';
   		</#if>
        }
        //弹出从季度计划选择页面 
        function open_quarterPlanDialog(){
          <#if subscribe.typeStatus?exists>
          var url ='${req.contextPath}/tooling/prophase/business/listQuarterPlanchooses.html';
          choose_quarterPlan(url,choose_quarter_Plan_information);
          <#else>
          alert("${action.getText('please.select.purchase.typeStatus')}");
          return false;
          </#if>
        }
        function choose_quarter_Plan_information(reslut){
              if (null != result) {
		       var addquarterPlanIds = result.substring(0, result.lastIndexOf(","));
		       document.forms[0].elements["addquarterPlanIds"].value =addquarterPlanIds;
		       document.forms[0].elements["addquarterPlan"].value = "addquarterPlans";
		       document.forms[0].submit();
		      }
                }
        	function open_subscribeDtlDialog(subscribeId, subscribeDtlId) {
        		var url = '${req.contextPath}/tooling/prophase/business/editSubscribeDetail.html?subscribe.id=' + subscribeId + '&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
        		
        		if (null != subscribeDtlId) {
        			url = url+ '&subscribeDtl.id=' + subscribeDtlId;
        		}
	      		popupModalDialog(url, Config.popW, Config.popH);
	      		var reloadUrl = '${req.contextPath}/tooling/prophase/business/listSubscribeDetails.html?subscribe.id=' + subscribeId+'&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
	            //如果是点击项目号进入维护页面，保存当前页，已使在维护页面点击关闭，会回到当前页
	            if (null != subscribeDtlId) {
	            	reloadUrl = reloadUrl + '&pagingPage=${pagingPage?if_exists}'
	            }
	            self.location.href = reloadUrl;
	      		return false;
        	}
        	
            //从备件台帐选择
        	function open_selectToolingAccountDialog(){
        		var url = '${req.contextPath}/popup/spareSelector.html?toolingDevFlag=${toolingDevFlag?if_exists}&inOutFlag=subscribe&spareBillId='+#{subscribe.id};
        		popupModalDialog(url, 800, 600,choose_toolingSpareAccount_information)
        	}
        	function choose_toolingSpareAccount_information(result){
	        	  if (null != result) {
		          var spareDetailIds = result.substring(0, result.lastIndexOf(","));
		          document.forms[0].elements["addSpareDetailIds"].value = spareDetailIds;
		          document.forms[0].elements["spareAccountSelector"].value = "spareAccount";
		          document.forms[0].submit();
	        	  }
        	}
			//保存申购单明细
        	function submitDetailIds(){
        		if(0!=document.getElementsByName("subscribeDtlIds").length){ //判断申购单明细中是否有记录
        		  <#if subscribe.typeStatus?exists>
        		  if(informationValidate()==true){
        			retrieveSubscribeDetailIdText();						//获得申购单明细的所有ID
        			retrieveSubscribeDetailAmountText();					//获得申购单明细的所有数量
        			retrieveSubscribeDetailUnitPriceText();					//获得申购单明细的所有单价
        			retrieveSubscribeDetailRequestDateText();				//获得申购单明细的所有需要日期
        			retrieveSubscribeDetailSupplierNameText();				//获得申购单明细的所有供应商名称
        			retrieveSubscribeDetailCommentText();					//获得申购单明细的所有备注
        			return true;
        		  }else{
        			return false;
        		  }
        		  <#else>
        			alert("${action.getText('please.select.purchase.typeStatus')}");
        			return false;
        		  </#if>
        		}
        	}
         /*
	      *  获取申购单明细所有的id的值
	      */
	      function retrieveSubscribeDetailIdText(){
	        var allSubscribeDtlId = document.getElementsByName("subscribeDtlIds");
	        var ary = new Array();
	        for (var i=0; i<allSubscribeDtlId.length; i++) {
	          ary.push(allSubscribeDtlId[i].value);
	        }
	        document.forms[0].elements["allSubscribeDetailIds"].value = ary;
	     }
	     /*
	      *  获取申购单明细数量的所有值
	      */
	     function retrieveSubscribeDetailAmountText(){
	        var allSubscribeDtlId = document.getElementsByName("subscribeDtlIds");
	        var allAmountNumberValue = document.getElementsByName("amount");
	        var ary = new Array();
           for (var i=0; i<allSubscribeDtlId.length; i++) {
              if ('' != allAmountNumberValue[i].value) {
              var amount = parseInt(formatDigital(allAmountNumberValue[i].value));
               ary.push(allSubscribeDtlId[i].value);
               ary.push(amount);
             }
           }
          document.forms[0].elements["allSubscribeDetailAmountNumber"].value=ary;
        }
        /*
	     *  获取申购单明细单价的所有值
	     */
	    function  retrieveSubscribeDetailUnitPriceText(){
	        var allSubscribeDtlId = document.getElementsByName("subscribeDtlIds");
	        var allUnitPriceValue = document.getElementsByName("unitPrice");
	        var ary = new Array();
           for (var i=0; i<allSubscribeDtlId.length; i++) {
              if ('' != allUnitPriceValue[i].value) {
              var unitPrice = parseFloat(formatDigital(allUnitPriceValue[i].value));
               ary.push(allSubscribeDtlId[i].value);
               ary.push(unitPrice);
             }
           }
          document.forms[0].elements["allSubscribeDetailUnitPrice"].value=ary;
	    }
	    /*
	    *获取申购单明细需要日期的所有值
	    */ 
	    function retrieveSubscribeDetailRequestDateText(){
	    	 var allSubscribeDtlId = document.getElementsByName("subscribeDtlIds");
	    	 var ary = new Array();
	    	 for (var i=0; i<allSubscribeDtlId.length; i++) {
	    	  	var inDetailRequestDateTagName='request.Date';
	    	  	inDetailRequestDateTagName = inDetailRequestDateTagName + (i+1);
	    	  	if ('' != document.forms["listForm"].elements[inDetailRequestDateTagName].value) {
               	ary.push(allSubscribeDtlId[i].value);
               	ary.push(document.forms[0].elements[inDetailRequestDateTagName].value);
             	}
	    	 }
	    	document.forms[0].elements["allSubscribeDetailRequestDate"].value=ary; 
	    }
	    /*
	     *  获取申购单明细供应商的所有值
	     */
	    function  retrieveSubscribeDetailSupplierNameText(){
	       var allSubscribeDtlId = document.getElementsByName("subscribeDtlIds");
	        var allSupplierNameValue = document.getElementsByName("supplierName");
	        var ary = new Array();
           for (var i=0; i<allSubscribeDtlId.length; i++) {
              if ('' != allSupplierNameValue[i].value) {
               ary.push(allSubscribeDtlId[i].value);
               ary.push(allSupplierNameValue[i].value);
             }
           }
          document.forms[0].elements["allSubscribeDetailSupplierName"].value=ary;
	    }
	    /*
	    *获取申购明细备注的所有值
	    */
	    function retrieveSubscribeDetailCommentText(){
	    	var allSubscribeDtlId = document.getElementsByName("subscribeDtlIds");
	    	var allCommentValue = document.getElementsByName("comment");
	     	var ary = new Array();
	     	for(var i=0;i<allSubscribeDtlId.length;i++){
	     	   if ('' != allCommentValue[i].value) {
               ary.push(allSubscribeDtlId[i].value);
               ary.push(allCommentValue[i].value);
             }
	     	}
	      document.forms[0].elements["allSubscribeDetailComment"].value=ary;
	    }
	    
		//信息安全验证
	    function informationValidate(){
		  var allSubscribeDtlId = document.getElementsByName("subscribeDtlIds");
	      var allAmountNumberValue = document.getElementsByName("amount");
	      var allUnitPriceValue = document.getElementsByName("unitPrice");
	      var allSupplierNameValue = document.getElementsByName("supplierName");
	      var allCommentValue = document.getElementsByName("comment");
		  for(var i=0;i<allSubscribeDtlId.length;i++){
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
		  	if(''!= allSupplierNameValue[i].value){
		  		var supplierName = allSupplierNameValue[i].value;
		  		if (!isValidLengthCheck(document.forms[0],supplierName,null,50)) {
	       			alert("${action.getText('row')}"+(i+1)+"${action.getText("supplierName.maxLength")}");
		   			return false;
	     		}
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