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

<@framePage title="${action.getText('subscribeDetails.title')}">	
   <link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
   <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
   <script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
   <script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script>  
	<@ww.form namespace="'/prophase/business'" name="'listForm'" action="'searchSubscribeDetails'" method="'post'">
		<@ww.token name="searchSubscribeDetailToken"/>
		<#if subscribe.id?exists>
			<@ww.hidden name="'subscribe.id'" value="'#{subscribe.id}'"/>
		</#if>
	
		 <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
		 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		 <@ww.hidden name="'addSpareDetailIds'" value=""/>
		 <@ww.hidden name="'spareAccountSelector'" value=""/>
		 <@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
		 <input type="hidden" name="allSubscribeDetailIds" value=""/>
		 <input type="hidden" name="allSubscribeDetailAmountNumber" value=""/>
		 <input type="hidden" name="allSubscribeDetailUnitPrice" value=""/>
		 <input type="hidden" name="allSubscribeDetailRequestDate" value=""/>
		 <input type="hidden" name="allSubscribeDetailSupplierName" value=""/>
		 <input type="hidden" name="allSubscribeDetailComment" value=""/>
		 <input type="hidden" name="allSubscribeDetailBuyeAmount" value=""/>
		 <input type="hidden" name="allBeizhu" value=""/>
		 <@ww.hidden name="'addSpareWareHouseIds'" value=""/>
		 <@ww.hidden name="'spareWareHouseSelector'" value=""/>
		 
		<#assign itemNo=1/>
		<#assign requestDateIdentityName = 'request.Date' + '${itemNo}'/>
		<#assign requestDateImgIdentity = 'listForm_' + '${requestDateIdentityName}' + '_trigger'/>
		
		 <@list title="" excel=true setupTable=true includeParameters="subscribe.id|toolingDevFlag" fieldMap="">
			<@vlh.checkbox property="id" name="subscribeDtlIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <#if subscribe.typeStatus?exists>
	            <#if subscribe.typeStatus='DEVICE'>
		            <@vcolumn title="${action.getText('itemNo')}">
		                <a href="#" onclick="return open_subscribeDtlDialog(#{subscribe.id}, #{object.id})">${itemNo}</a>
		                <@alignCenter />
		           </@vcolumn>
	            <#elseif subscribe.typeStatus='SPARE'>
		           	<@vcolumn title="${action.getText('itemNo')}">
		               <a id="#{object.id}" href="javascript: open_subscribeDetailSpareDialog(#{subscribe.id}, #{object.id},'${object.status}')"> ${itemNo}</a>
		             <@alignCenter />
		           </@vcolumn>
	           </#if>
           </#if>
       
           <#assign itemNo = itemNo + 1/>
            <@vcolumn title="${action.getText('graphNo')}" property="graphNo" attributes="width='85'">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('name')}" property="name" attributes="width='100'">
            ${object.name}
            <#--
            	<script language="javascript">
            	 	var toggleV = true;
        			if (toggleV) {
						window.parent.document.forms[0].elements["subscribe.totalPrice"].value = '${object.subscribe.totalPrice}';
						toggleV = false;
					}
				</script>
				-->
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('型号')}" property="model" attributes="width='100'">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('订货号')}" property="orderNo" attributes="width='50'">
            	<@alignLeft />
            </@vcolumn>            
             <@vcolumn title="${action.getText('生产厂家')}" property="factory.name"/>
            <@vcolumn title="${action.getText('种类')}" property="category.name">
            	<@alignLeft />
            </@vcolumn>
            <#--
             <@vcolumn title="${action.getText('明细种类')}" property="detailCategory.name">
            	<@alignLeft />
            </@vcolumn>
            -->
             <@vcolumn title="${action.getText('unit')}" property="calUnit.value">
        		<@alignLeft />
       		</@vcolumn>
       		<@vcolumn title="${action.getText('总库存')}" property="stocks">
       		    <#if object.spare?exists>
       		       <a href="#" onclick="javascript:open_selectDialog_stocks('${object.spare.spareNo}')">${object.spare.stocks?if_exists}</a>
       		    <#else>
       		    </#if>
               
            	<@alignCenter/>
             </@vcolumn>
       		<@vcolumn title="${action.getText('amount')}">
                <input type="text" name="amount" 
	    		   class="underline"  value="${object.amount?if_exists}"  maxlength="50" size="5" style="text-align:right"/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('unitPrice')}">
            	 <input type="text" name="unitPrice" 
	    		   class="underline"  value="${(object.unitPrice?string('#,###,##0.00'))?if_exists}"  maxlength="50" size="10" style="text-align:right"/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
            	<@alignRight />
            </@vcolumn>
            <#--
             <@vcolumn title="${action.getText('unitPrice')}" property="unitPrice" format="${action.getText('currencyFormat')}"> 
                 <@alignRight />
             </@vcolumn>
             -->
             <@vcolumn title="${action.getText('totalPrice')}" property="totalPrice" format="${action.getText('currencyFormat')}">
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('所属设备')}" property="ownedEquipment"/>
            <@vcolumn title="${action.getText('设备厂家')}" property="equFactoryStr"/>
            <@vcolumn title="${action.getText('所属设备总数量')}" property="sssbzsl"/>
            <@vcolumn title="${action.getText('损坏频次')}" property="shpc"/>
            <@vcolumn title="${action.getText('供应商')}" property="supplierName">
           		<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('交货日期')}" property="requireDate" format="yyyy-MM-dd">
            </@vcolumn>
          
            <@vcolumn title="${action.getText('采购数量')}" property="buyeAmount">              
            	<@alignRight />
            </@vcolumn>
       		<@vcolumn title="${action.getText('到货数量')}" property="arrivalAmount">
        		<@alignRight />
       		</@vcolumn>
       		<@vcolumn title="${action.getText('实到日期')}" property="arrivalDate" format="yyyy-MM-dd">
        		<@alignRight />
       		</@vcolumn>
    		
             <@vcolumn title="${action.getText('status')}" attributes="width='50'">
             		<#if '${object.status}' == 'NEW'>
             			新建
             		<#elseif '${object.status}' == 'COLLECTED'>
             			已汇总
             		<#elseif '${object.status}' == 'UNPURCHASE'>
             			未采购
             		<#elseif '${object.status}' == 'INPURCHASE'>
             			采购中	
             		<#elseif '${object.status}' == 'PURCHASEED'>
             			已采购
             		<#elseif '${object.status}' == 'INSPECTING'>
             			入库中/验收中
             		<#elseif '${object.status}' == 'INSPECTED'>
             			已入库/已验收
             	   <#elseif '${object.status}' == 'NOTPURCHASEED'>
             			不采购
             		</#if>
                 <@alignLeft/>
            </@vcolumn>
              <div style="display:none">
            	  <input   name= "status" type= "checkbox"  value= "${object.status}" style="visibility:hidden"/> 
            </div>
            
           <@vcolumn title="申购理由">
            	 <input type="text" name="beizhu" 
	    		   class="underline"  value="${object.beizhu?if_exists}"  maxlength="50" size="10" style="text-align:right"/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
            	<@alignRight />
            </@vcolumn>
            
            
		</@list>
		
	        <@buttonBar>
	        	<#if !first>
	        	<#if !(action.isReadOnly())>
	        	<#if subscribe.typeStatus?exists>
	        	<#--
		        	<#if subscribe.typeStatus == 'LOWLOSS'>        	
		        		<@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="open_subscribeDtlDialog(#{subscribe.id}, null)"/>
		            <#elseif subscribe.typeStatus == 'SPARE'>          
		            	<@vbutton name="'new'"  class="button" value="${action.getText('新建')}" onclick="open_newDeviceAccountDialog()"/>
		            	<@vbutton name="'select'"  class="button" value="${action.getText('从低于备件库存选择')}" onclick="open_selectDialog()"/>
		            </#if>
		        -->
		        	<@vbutton name="'new'"  class="button" value="${action.getText('新建')}" onclick="open_newDeviceAccountDialog()"/>
		            <@vbutton name="'select'"  class="button" value="${action.getText('从低于安全库存选择')}" onclick="open_selectDialog()"/>
	            </#if>
	            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return submitDetailIds()'">
	            	<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            </@vsubmit>
	            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('subscribedtl')}?" />
	            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	                <@ww.param name="'onclick'" value="'return confirmDeletes(\"subscribeDtlIds\", \"${confirmMessage}\")'"/>
	                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            </@vsubmit>
	            </#if>
	            </#if>
	        </@buttonBar>
	      
        
        <script language="javascript">
        window.onload=function(){     
       
        <#if subscribe.status!='NEWPURCHASE'>
      	 	 document.forms[0].elements["'new'"].disabled="true";
      	 	 document.forms[0].elements["'select'"].disabled="true";
      	 	 document.forms[0].elements["save"].disabled="true";
      	 	 document.forms[0].elements["delete"].disabled="true";    
      	 	 //if(null!= document.forms[0].elements["save"]){
      	 	     //document.forms[0].elements["save"].disabled="true";
      	 	     //document.forms[0].elements["delete"].disabled="true";    
      	 	 //}
        </#if>
      	 	 <#--
      	 	var selector = document.getElementsByName("subscribeDtlIds");//获取所有id
      		var status = document.getElementsByName("status");//获取所有状态
			var st="";	
		    var spareIds = "";
		    if (selector.length) {
		        for (i = 0; i < selector.length; i++) {		        
		            if (status[i].value=='NEW') {
		                 if(null!=document.forms[0].elements["save"]){
		                    document.forms[0].elements["save"].disabled=null;
      	 				    document.forms[0].elements["delete"].disabled=null; 
		                 }
		            	 
		            }
		        }
		    }		
      	 	-->
       		  
		    <#--
         <#if subscribe.id?exists>
             parent.getObjByNameRe("subscribe.totalPrice").value = '${subscribe.totalPrice?if_exists}';
          </#if>
          -->
   	    <#if req.getParameter('errorFlag')?has_content>
   	       alert("${action.getText('delete.subscribeDtl.failure')}");
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
   			parent.document.forms["subscribe"].elements["subscribe.status"].value = '${subscribeStatus}';
   			</#if>
        }
        	function open_subscribeDtlDialog(subscribeId, subscribeDtlId) {
        		var url = '${req.contextPath}/popup/editSubscribeDetail.html?subscribe.id=' + subscribeId + '&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
        		if (null != subscribeDtlId) {
        			url = url+ '&subscribeDtl.id=' + subscribeDtlId;
        		}
	      		popupModalDialog(url, Config.popW, Config.popH);
	      		//self.location.reload();
	      		var reloadUrl = '${req.contextPath}/prophase/business/listSubscribeDetails.html?subscribe.id=' + subscribeId+'&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
	            //如果是点击项目号进入维护页面，保存当前页，已使在维护页面点击关闭，会回到当前页
	            if (null != subscribeDtlId) {
	            	reloadUrl = reloadUrl + '&pagingPage=${pagingPage?if_exists}'
	            }
	            self.location.href = reloadUrl;
	      		return false;
        	}
        	
        	//从备件台帐选择
        	//function open_selectDeviceAccountDialog(){
        		//var url = '${req.contextPath}/popup/spareSelector.html?toolingDevFlag=${toolingDevFlag?if_exists}&inOutFlag=subscribe&spareBillId='+#{subscribe.id};
        		//popupModalDialog(url, 800, 600,choose_deviceSpareAccount_information)
        	//}
        	//function choose_deviceSpareAccount_information(result){
	        	 // if (null != result) {
		         // var spareDetailIds = result.substring(0, result.lastIndexOf(","));
		        //  document.forms[0].elements["addSpareDetailIds"].value = spareDetailIds;
		         // document.forms[0].elements["spareAccountSelector"].value = "spareAccount";
		        //  document.forms[0].submit();
	        	 // }
        	//}
        	//新建
        	function open_newDeviceAccountDialog(){
        		//var url = '${req.contextPath}/prophase/business/editSubscribeDetailsTest.html';
        		var url = '${req.contextPath}/prophase/business/editSubscribeDetailFrame.html?subscribe.id=#{subscribe.id}&toolingDevFlag=${toolingDevFlag?if_exists}';
        		popupModalDialog(url, 1024, 900)
        		self.location.reload();
        	}
        	
        	//申购单明细维护页面 zzb
        	function open_subscribeDetailSpareDialog(subscribeId,subscribeDtlId,subscribeDtlStatus){
	        	   // var url = 'editSubscribeDetail.html?subscribe.id=#{subscribe.id}&fromDetail=true&toolingDevFlag=${toolingDevFlag?if_exists}&subscribeDtl.id='+subscribeDtlId;	
	        		 var url = '${req.contextPath}/prophase/business/editSubscribeDetailFrame.html?subscribe.id=#{subscribe.id}&fromDetail=true&toolingDevFlag=${toolingDevFlag?if_exists}&subscribeDtl.id='+subscribeDtlId;
	        		popupModalDialog(url, 1024, 900)
	        		self.location.reload();
	        		
	        		var reloadUrl = '${req.contextPath}/prophase/business/listSubscribeDetails.html?subscribe.id=' + subscribeId+'&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
		            //如果是点击项目号进入维护页面，保存当前页，已使在维护页面点击关闭，会回到当前页
		            if (null != subscribeDtlId) {
		            	reloadUrl = reloadUrl + '&pagingPage=${pagingPage?if_exists}'
		            }
		            self.location.href = reloadUrl;
		      		return false;
        	     
          }
        	
         
        	
        	
        	
        	
        	
        	
        	//保存申购单明细
        	function submitDetailIds(){
        		if(0!=document.getElementsByName("subscribeDtlIds").length){ 	//判断申购单明细中是否有记录
	        		<#if subscribe.typeStatus?exists>
	        		if(informationValidate()){							//验证信息安全
	        			retrieveSubscribeDetailIdText();						//获得申购单明细的所有ID
	        			retrieveSubscribeDetailAmountText();					//获得申购单明细的所有数量
	        			retrieveSubscribeDetailUnitPriceText();					//获得申购单明细的所有单价
	        			retrieveSubscribeDetailBeizhuText();                    //获得备注的值 zzb
	        			//retrieveSubscribeDetailRequestDateText();				//获得申购单明细的所有需要日期
	        			//retrieveSubscribeDetailSupplierNameText();				//获得申购单明细的所有供应商名称
	        			//retrieveSubscribeDetailCommentText();					//获得申购单明细的所有备注
	        			//retrieveSubscribeDetailBuyeAmountText();				//获得申购单明细的所有采购数量
	        			return true;
	        		}else{
						return false;
        			}
        			<#else>
        			alert("${action.getText('please.select.subscribe.typeStatus')}");
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
               ary.push(allAmountNumberValue[i].value);
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
	     *  获取申购单明细备注的所有值 zzb 备注（beizhu）
	     */
	    function  retrieveSubscribeDetailBeizhuText(){
	       var allSubscribeDtlId = document.getElementsByName("subscribeDtlIds");
	       var allBeizhuValue = document.getElementsByName("beizhu");
	       var ary = new Array();
           for (var i=0; i<allSubscribeDtlId.length; i++) {
              if ('' != allBeizhuValue[i].value) {
               ary.push(allSubscribeDtlId[i].value);
               ary.push( allBeizhuValue[i].value);
             }
           }
          document.forms[0].elements["allBeizhu"].value=ary;
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
	    *获取申购单明细备注的所有值
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
	   
	  <#--
	   /*
	    * allComments表示所有备注,用双#号分割，‘##’，其目的是备注中可能出现逗号，造成数据错误！
	   */ 
	  function retrieveSubscribeDetailCommentText(){
	    	var allSubscribeDtlId = document.getElementsByName("subscribeDtlIds");
	    	var allCommentValue = document.getElementsByName("comment");
	    	//var AllComments="";
	     	var ary = new Array();
	     	for(var i=0;i<allSubscribeDtlId.length;i++){
	     	//AllComments = allCommentValue.substring(0,allCommentValue.lastIndexOf("##"));
	     	   if ('' != allCommentValue[i].value) {
               ary.push(allSubscribeDtlId[i].value);
               ary.push(allCommentValue[i].value);
             }
	     	}
	      document.forms[0].elements["allSubscribeDetailComment"].value=ary;
	      alert('pp'+document.forms[0].elements["allSubscribeDetailComment"].value);
	    }  
	    --> 
	    /*
	    * 信息安全验证
	    */
	    function informationValidate(){
		  var allSubscribeDtlId = document.getElementsByName("subscribeDtlIds");
	      var allAmountNumberValue = document.getElementsByName("amount");
	      var allUnitPriceValue = document.getElementsByName("unitPrice");
	      var allSupplierNameValue = document.getElementsByName("supplierName");
	      var allCommentValue = document.getElementsByName("comment");
	      var allBuyeAmountValue = document.getElementsByName("buyeAmount");
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
		  	//if(''!= allSupplierNameValue[i].value){
		  	//	var supplierName = allSupplierNameValue[i].value;
		  	//	if (!isValidLengthCheck(document.forms[0],supplierName,null,50)) {
	       	//		alert("${action.getText('row')}"+(i+1)+"${action.getText("supplierName.maxLength")}");
		   	//		return false;
	     	//	}
		  	//}
		  	//验证备注长度
		  	//if(''!= allCommentValue[i].value){
		  	//	var comment = allCommentValue[i].value;
		  	//	if (!isValidLengthCheck(document.forms[0],comment,null,250)) {
	       	//		alert("${action.getText('row')}"+(i+1)+"${action.getText("comment.maxLength")}");
		   	//		return false;
	     	//	}
		  	//}
		  } 
		  return true;           	
	    }
	    //从低于安全库存选择
	 	function open_selectDialog() {
	 		var url = '${req.contextPath}/spare/spareWareHouse/listSpareWareHouseOfLessMinStocksSELECT.html';
	 		popupModalDialog(url,1024,900,choose_spareWareHouse_information);
	 	} 
	 	function choose_spareWareHouse_information(result){
	 	      if (null != result) {
		        var spareWareHouseIds = result.substring(0, result.lastIndexOf(","));
		        document.forms[0].elements["addSpareWareHouseIds"].value = spareWareHouseIds;
		        document.forms[0].elements["spareWareHouseSelector"].value = "spareWareHouseOfLessMinStocks";
		        document.forms[0].submit();
     		 }
	 	}
	 	
	 	function open_selectDialog_stocks(spareNo) {
	 		var url = '${req.contextPath}/asset/spare/listSpareDetailSearcherCommon.html?readOnly=true&spareNo='+spareNo;
	 		popupModalDialog(url,1024,900,callback);
	 	} 
	 	function callback(){}
        </script>
	</@ww.form>
	
</@framePage>