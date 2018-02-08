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

<#include "../../includes/eam2008.ftl" />
<@framePage title="${action.getText('purchaseBillDetails.title')}">	
   <link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
   <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
   <script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
   <script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script>  
    <script language="javascript" src="${req.contextPath}/javascripts/global.js"></script>  
   <script type='text/javascript' src='${req.contextPath}/dwr/interface/updateParameterFromPageJs.js'></script>
  <@ww.form name="'listForm'" action="'searchPurchaseBillDetails'" method="'post'">
		<@ww.token name="searchPurchaseBillDetailsToken"/>
			<@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
			<@ww.hidden name="'addSubscribeIds'" value=""/>
			<@ww.hidden name="'addSubscribe'" value=""/>
		    <@ww.hidden name="'addSpareDetailIds'" value=""/>
		    <@ww.hidden name="'spareAccountSelector'" value=""/>
		    <@ww.hidden name="'allPurchaseBillDtlIds'" value=""/>
		    <@ww.hidden name="'allPurchaseBillDetailAmountNumber'" value=""/>
		    <@ww.hidden name="'allPurchaseBillDetailUnitPrice'" value=""/>
		    <@ww.hidden name="'allPurchaseBillDetailRequestDate'" value=""/>
		    <@ww.hidden name="'allPurchaseBillDetailComment'" value=""/>
		    
		    <@ww.hidden name="'allPurchaseBillDetailTaxPrice'" value=""/>
		    <@ww.hidden name="'allPurchaseBillDetailTaxTotalPrice'" value=""/>
		    
			<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
			<@ww.hidden name="'toolingDevFlag'" value="'${req.getParameter('toolingDevFlag')?if_exists}'"/>
			<@ww.hidden name="'purchaseType'" value="'${purchaseType?if_exists}'"/>
		<#if purchaseBill.id?exists>
		<@ww.hidden name="'purchaseBill.id'" value="'#{purchaseBill.id}'"/>
		</#if>
		<#assign itemNo=1/>
		<#assign requestDateIdentityName = 'request.Date' + '${itemNo}'/>
		<#assign requestDateImgIdentity = 'listForm_' + '${requestDateIdentityName}' + '_trigger'/>
		
		<#assign taxPriceIdentityName = 'taxPrice' + '${itemNo}'/>
		<#assign unitPriceIdentityName = 'unitPrice' + '${itemNo}'/>
		<#assign amountIdentityName = 'amount' + '${itemNo}'/>
		<#assign taxTotalPriceIdentityName = 'taxTotalPrice' + '${itemNo}'/>
		<#assign totalPriceIdentityName = 'totalPrice' + '${itemNo}'/>
		
		
		<@list title="" includeParameters="purchaseBill.id" fieldMap="like:purchaseBill.id">
			<@vlh.checkbox property="id" name="purchaseBillDtlIds">
                <@vlh.attribute name="width" value="30" />
                <#--
                  <@vlh.attribute name="onclick" value="return checkValue('${object.id?if_exists}','${object.status?if_exists}');" />
                  -->
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
           <@vcolumn title="${action.getText('graphNo')}" property="graphNo" attributes="width='100'">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('name')}" property="name" attributes="width='100'">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('型号')}" property="model" attributes="width='100'">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('订货号')}" property="orderNo" attributes="width='50'">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('生产厂家')}" property="factory.name"/>
            
			 <@vcolumn title="${action.getText('种类')}"  property="categoryName" >
			  <@alignLeft />
            </@vcolumn>
        <#--
            <@vcolumn title="${action.getText('明细种类')}" property="detailCategoryName" >
                <@alignLeft />
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('unit')}" property="calUnit.value">
        	   <@alignLeft />
            </@vcolumn>
            
            <@vcolumn title="${action.getText('库存明细')}">
	       		<#if object.spare?exists>
	       			<a href="javascript:open_selectDialog('${object.graphNo}');">查看</a>
	       			<#else>
	       			<a href="javascript:alert('无库存');">查看</a>
	       		</#if>
        		<@alignCenter />
       		</@vcolumn>  
            
            <@vcolumn title="${action.getText('所属设备')}" property="subscribeDetail.ownedEquipment"/>
             
           <@vcolumn title="${action.getText('设备厂家')}" property="subscribeDetail.equFactory.name"/>   
           
           
           <#--
            <@vcolumn title="${action.getText('subscriber.department')}" property="department" >
            	<@alignLeft />
            </@vcolumn>-->
            
             <@vcolumn title="${action.getText('采购数量')}">
               <#if object.subscribeDetail?exists && 0==object.amount?if_exists>
                 <input type="text" name="${amountIdentityName}" 
	    		          class="underline" 
                          value="${object.subscribeDetail.amount?if_exists}"  
                          style="text-align:right;"
                          onchange="changPrice(#{object.id},${itemNo})"
                          onclick="changPrice(#{object.id},${itemNo})"
	    		          maxlength="50" size="7"/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	    		   <#assign amountIdentityName = 'amount' + '${itemNo}'/>
	    		   <#else>
	    		      <input type="text" name="${amountIdentityName}" 
	    		          class="underline" 
                          value="${object.amount?if_exists}"
                          onchange="changPrice(#{object.id},${itemNo})"
                          onclick="changPrice(#{object.id},${itemNo})"
                          style="text-align:right;"
	    		          maxlength="50" size="7"/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	    		   <#assign amountIdentityName = 'amount' + '${itemNo}'/>
	    		 </#if>
                
            </@vcolumn>
            
             <@vcolumn title="${action.getText('申购数量')}" >
              <#if object.subscribeDetail?exists>
                  <input type="text" name="subscribeNum" 
	    		   class="underline"  value="${object.subscribeDetail.amount?if_exists}"  
	    		   maxlength="50" size="7" style="text-align:right" disabled/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	    	  <#else>
	    	      <input type="hidden" name="subscribeNum" value=""/>
              </#if>
                
            </@vcolumn>
            
                  <#--税前单价-->
             <@vcolumn title="${action.getText('税前单价(元)')}" >
                 <input type="text" name="${taxPriceIdentityName}" 
	    		       class="underline" 
	    		       style="text-align:right;"
	    		       value="${(object.taxPrice?string('#,###,##0.00'))?if_exists}" 
	    		       onchange="channgeUnitPrice(#{object.id},${itemNo})"
	    		       onclick="clickChanngeUnitPrice(#{object.id},${itemNo})"
	    		       maxlength="50" size="7" />
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	    		   <#assign taxPriceIdentityName = 'taxPrice' + '${itemNo}'/>
	
             </@vcolumn>
             
            
            <#--税后单价-->
             <@vcolumn title="${action.getText('税后单价(元)')}">
              <#-- <#if object.subscribeDetail?exists>
            	 <input type="text" name="${unitPriceIdentityName}" 
	    		       class="underline" 
	    		       style="text-align:right;"
	    		       value="${(object.subscribeDetail.unitPrice?string('#,###,##0.00'))?if_exists}" 
	    		       onchange="changeTaxPrice(${itemNo})"
	    		       onclick="changeTaxPrice(${itemNo})"
	    		       maxlength="50" size="7"/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	    		   	<#assign unitPriceIdentityName = 'unitPrice' + '${itemNo}'/>
             <#else>-->
             
	    	       <input type="text" name="${unitPriceIdentityName}" 
	    		       class="underline" 
	    		       style="text-align:right;"
	    		       value="${(object.unitPrice?string('#,###,##0.00'))?if_exists}" 
	    		       onchange="changeTaxPrice(#{object.id},${itemNo})"
	    		       onchange="clickChangeTaxPrice(#{object.id},${itemNo})"
	    		       maxlength="50" size="7" />
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	    		   	<#assign unitPriceIdentityName = 'unitPrice' + '${itemNo}'/>
              <#--</#if>-->
            </@vcolumn>
            
          
             <@vcolumn title="${action.getText('税前总价(元)')}" property="taxTotalPrice" format="${action.getText('currencyFormat')}">
               <@vlh.attribute name="id" value="${taxTotalPriceIdentityName}"/>
               <#assign taxTotalPriceIdentityName = 'taxTotalPrice' + '${itemNo}'/>
		
             </@vcolumn>
            <@vcolumn title="${action.getText('税后总价(元)')}" property="totalPrice" format="${action.getText('currencyFormat')}">
                  <@vlh.attribute name="id" value="${totalPriceIdentityName}"/>
                  <#assign totalPriceIdentityName = 'totalPrice' + '${itemNo}'/>
            	<@alignRight />
            </@vcolumn>
            
            
            
            <@vcolumn title="${action.getText('交货日期')}">
            	<#assign requirementDate = ''/>
			    		<#if object.reqDeliveryDate?exists>
				          <#assign requirementDate = "${(object.reqDeliveryDate?string('yyyy-MM-dd'))}"/>
				        </#if>
			    		<@eam2008_dataPicker inputName="${requestDateIdentityName}" 
			    			inputId="${requestDateIdentityName}" 
			    			inputValue="${requirementDate}" 
			    			imgId="${requestDateImgIdentity}" 
			    			inputMethod="changeRegDate(#{object.id},${itemNo})"
			    			formName="listForm"
			    			disable="false"/>
			    		<#assign requestDateIdentityName = 'request.Date' + '${itemNo}'/>
						<#assign requestDateImgIdentity = 'listForm_' + '${requestDateIdentityName}' + '_trigger'/>
		
            </@vcolumn>
            
             <@vcolumn title="${action.getText('申购部门')}" property="depart.name" >
                <@alignLeft />
            </@vcolumn>
           
            <@vcolumn title="${action.getText('到货数量')}" property="arrivalAmount" >
            	<@alignLeft />
            </@vcolumn>
           <@vcolumn title="${action.getText('实到日期')}" property="actualDeliveryDate" format="yyyy-MM-dd">
           </@vcolumn>
           
            <#assign purchasedtlStatus = ''/>
            <#if object.status?exists>
              <#if '${object.status}' == 'unSPECT'>
                 <#assign purchasedtlStatus = "${action.getText('unSPECT')}"/>
               </#if>
              <#if '${object.status}' == 'INSPECTED'>
                <#assign purchasedtlStatus = "${action.getText('INSPECTEDtl')}"/>
              </#if>
               <#if '${object.status}' == 'INSPECTED'>
                    <#assign purchasedtlStatus = "${action.getText('INSPECTED')}"/>
               </#if>
                <#if '${object.status}' == 'NEW'>
                    <#assign purchasedtlStatus = "${action.getText('NEW')}"/>
               </#if>
               <#if '${object.status}' == 'INSPECTING'>
                    <#assign purchasedtlStatus = "${action.getText('INSPECTING')}"/>
               </#if>
            </#if>
           
           <@vcolumn title="${action.getText('purchase.state')}" attributes="width='50'">
            <input type="hidden"  name="" id="#{object.id}" value="${object.status}"/> 
              ${purchasedtlStatus}
                 <@alignLeft/>
          </@vcolumn>
            
            
		</@list>
        <@buttonBar>
        <#if !first>
         <#if !(action.isReadOnly())>
           <#if purchaseBill.typeStatus?exists>
           <#if purchaseBill.typeStatus=='DEVICE'>
               <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="open_purchaseBillDtlDialog(#{purchaseBill.id}, null)"/>
            <#elseif purchaseBill.typeStatus=='SPARE'>
                 <#--从备件台帐选择-->
                 <@vbutton name="'select'"  class="button" value="${action.getText('deviceAccountSelect')}" onclick="open_selectDeviceAccountDialog()"/>   
            </#if>
            <#--从申购单选择-->
            <@vbutton name="'new'"  class="button" value="${action.getText('choosesub')}" onclick="open_subscribeBillDtlDialog()"/>
           </#if>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savePurchaseDtl()'">
            	<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('purchaseBillDtl')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"purchaseBillDtlIds\", \"${confirmMessage}\")'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            </#if>
            </#if>
        </@buttonBar>
        
 <script language="javascript">
        function open_selectDialog(spareNo) {
	 		var url = '${req.contextPath}/asset/spare/listSpareDetailSearcherCommon.html?readOnly=true&spareNo='+spareNo;
	 		popupModalDialog(url,800,600,callback);
	 	} 
             <#--自定义一个trim函数--> 
           String.prototype.Trim = function() {
	           return this.replace(/(^\s*)|(\s*$)/g, "");
	       }
	       
          window.onload=function(){
           <#--
          <#if !(action.isReadOnly()) && purchaseBill.status!='NEWSTATUS'>	
           
             var selector = document.getElementsByName("purchaseBillDtlIds");
             var num=0;
             if(selector.length){
              for(var i=0;i<selector.length;i++){
                  var id = selector[i].value;
                  alert("1、" +  id);
                  var statu = $(id).value;
                   alert("2、" +  statu);
                  if('NEW'==statu){
                     num++;
                  }else{
                      // $(id).parentNode.parentNode.disabled="true";
                       var inputAry = $(id).parentNode.parentNode.getElementsByTagName("input");
                       for(var a=0;a<inputAry.length;a++){
                         inputAry[a].disabled="true";
                       }
                     
                  }
             }
             
            
             if(parseInt(num)>0){
                
             }else{
              
              document.forms[0].elements["save"].disabled="true";
      	 	  document.forms[0].elements["delete"].disabled="true";
      	 	  document.forms[0].elements["'new'"].disabled="true";
      	 	  document.forms[0].elements["'select'"].disabled="true";
             }
           }	
   
   	 	
        </#if>
       -->
    
    	//如果没有盘点明细，“发送提醒”按钮状态改为不可用
			var selector = document.getElementsByName("purchaseBillDtlIds");
			<#--if (!selector.length) {
			parent.document.forms[0].elements["submitRecord"].disabled="true";
			}-->
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
          
          
          function checkValue(id, statu){
              var selector = document.getElementsByName("purchaseBillDtlIds");
              var num=0;
           if(selector.length){
              for(var i=0;i<selector.length;i++){
              if(selector[i].checked==true){
                  var id = selector[i].value;
                  var status = $(id).value;
                   if('NEW'==status){
                   document.forms[0].elements["save"].disabled="false";
      	 	       document.forms[0].elements["delete"].disabled="false";
                 }
                 }
               }
             }
             
          }
       
       //根据 采购数量改变 税前税后总价    
       function  changPrice(pId,num){
          var length = (parseInt(num)-1);
          var amount="";
          var taxPrice="";
          var price="";
          
          if(null != $('taxPrice'+length)){
            taxPrice = $('taxPrice'+length).value;   //税前单价
            taxPrice = taxPrice.Trim();
            taxPrice = replaceComma(taxPrice); 
          }
          
          if(null != $('unitPrice'+length)){
             price = $('unitPrice'+length).value; //税后单价
             price = price.Trim();
             price = replaceComma(price);
          }
          
          if(null != $('amount'+length)){
             amount = $('amount'+length).value;
             if(''==amount || 0==amount){
	            alert("第 "+length+" 行采购数量应大于0！");
	            $('amount'+length).focus();
	            return false;
             }
             if (!isDoubleNumberCheck(amount)){
	              alert("第 "+length+" 行采购数量 格式错误！");
	              $('amount'+length).focus();
	              return false;
	          }else{
	              //如果有税前单价，则生成相应的税后单价
	             if (isDoubleNumberCheck(taxPrice) ){
	                 var price =(parseFloat(taxPrice)*1.17).toFixed(2);//税后
	                 if(null != $('unitPrice'+length)){
	                        $('unitPrice'+length).value=toMoney(price,2);
	                 }
	                 if(null !=  $('totalPrice'+length)){
	                     $('totalPrice'+length).innerHTML=toMoney(parseFloat(price*amount).toFixed(2),2); 
	                 }
	                 
	                 if(null != $('taxTotalPrice'+length)){
	                        $('taxTotalPrice'+length).innerHTML=toMoney(parseFloat(taxPrice*amount).toFixed(2),2);
	                 }
	               
	             
	             }else{
	                 if(null != $('taxTotalPrice'+length)){
	                        $('taxTotalPrice'+length).innerHTML="";
	                 }
	             
	             }
	             //如果有税后单价，则生成相应的税前单价
	             if(isDoubleNumberCheck(price)){
	                   var  taxPrice = (parseFloat(price/1.17)).toFixed(2);//税前
	                   if(null != $('taxPrice'+length)){
	                        $('taxPrice'+length).value=toMoney(taxPrice,2);
	                   }
	                   if(null != $('taxTotalPrice'+length)){
	                      $('taxTotalPrice'+length).innerHTML=toMoney(parseFloat(taxPrice*amount).toFixed(2),2);
	                   }
	                   if(null !=  $('totalPrice'+length)){
	                     $('totalPrice'+length).innerHTML=toMoney(parseFloat(price*amount).toFixed(2),2); 
	                   }
	             }else{
	                   if(null !=  $('totalPrice'+length)){
	                     $('totalPrice'+length).innerHTML=""; 
	                   }
	             }
	         }
            
          }
          
              <#--保存到数据库-->
         if(''!=amount){
              var ary = new Array();
	          ary.push("N");
	          ary.push(pId);
	          ary.push(amount);
	          if(""==price){
	             price = null;
	          }if("" == taxPrice){
	            taxPrice = null;
	          }
	          ary.push(price);
	          ary.push(taxPrice);
	          updateParameterFromPageJs.updateParameterFromPage(ary);
         }
         
       } 
       
       // 转换金额的 “,”号(3,333.00)->(3333.00)
       function replaceComma(data){
          var result = "";
          var ary = data.split(",");
          for(var i =0;i<ary.length;i++){
             result = result+ary[i];
          }
          return result;
       }
       
       
       //把浮点数改为 金额显示
       function toMoney(s, n){   
			   n = n > 0 && n <= 20 ? n : 2;   
			   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
			   var l = s.split(".")[0].split("").reverse(),   
			   r = s.split(".")[1];   
			   t = "";   
			   for(i = 0; i < l.length; i ++ ){   
			      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
			   }   
			   return t.split("").reverse().join("") + "." + r;   
			} 

    
        // 根据税后价格 改变 税前的单价 
        function  changeTaxPrice(pId,num){
            var length = (parseInt(num)-1);
            var price = $('unitPrice'+length).value; //税后
            price = replaceComma(price);
            var amount ="";
            if(null != $('amount'+length)){
               if(isNumber('amount'+length)){
                   amount = $('amount'+length).value;
               }
                
            } 
           
            price = price.Trim();
            
            if(''==price || price<=0){
                alert("第 "+length+" 行请输入大于0的 税后单价！");
                $('unitPrice'+length).focus();
                return false;
           }
           if (!isDoubleNumberCheck(price)){
                      alert("第 "+length+" 行税后单价 格式错误！");
                      $('unitPrice'+length).focus();
                     return false;
            }   
            
            
            if(isDoubleNumberCheck(price)){
                var  taxPrice = (parseFloat(price/1.17)).toFixed(2);//税前
                if(null != $('taxPrice'+length)){
                    $('taxPrice'+length).value=toMoney(taxPrice,2);
                }
                
                if(null !=  $('taxTotalPrice'+length)){
                   $('taxTotalPrice'+length).innerHTML=toMoney(parseFloat(taxPrice*amount).toFixed(2),2);
                }
                if(null != $('totalPrice'+length)){
                    $('totalPrice'+length).innerHTML=toMoney(parseFloat(price*amount).toFixed(2),2);
                }
               
                
               
            }
            
             <#--保存到数据库-->
             if(''!=price){
                  var number = amount;
		          var ary = new Array();
		          ary.push("P");
		          ary.push(pId);
		          ary.push(price);
		          ary.push(number);
		          updateParameterFromPageJs.updateParameterFromPage(ary);
             
             }
             
         }
        
        // 根据税前价格 改变 税后的单价 
        function channgeUnitPrice(pId,num){
             var length = (parseInt(num)-1);
             var taxPrice = $('taxPrice'+length).value;//税前
             var amount = "";
             if(null != $('amount'+length)){
                amount = $('amount'+length).value;
             }
             
             taxPrice = taxPrice.Trim();
             taxPrice = replaceComma(taxPrice);
            
            
             if(''==taxPrice || taxPrice<=0){
                   alert("第 "+length+" 行请输入大于0的 税前单价！");
                   $('taxPrice'+length).focus();
                   return false;
                  
             }
             if (!isDoubleNumberCheck(taxPrice)){
                      alert("第 "+length+" 行税前单价 格式错误！");
                      $('unitPrice'+length).focus();
                     return false;
            }  
          
             if(isDoubleNumberCheck(taxPrice)){
                 var price =(parseFloat(taxPrice)*1.17).toFixed(2);//税后
                 if(null != $('unitPrice'+length)){
                     $('unitPrice'+length).value=toMoney(price,2);
                 }
                 if(isDoubleNumberCheck(amount)){
                    if(null !=  $('taxTotalPrice'+length)){
	                    $('taxTotalPrice'+length).innerHTML=toMoney(parseFloat(taxPrice*amount).toFixed(2),2);
	                 }
	                 if(null != $('totalPrice'+length)){
	                    $('totalPrice'+length).innerHTML=toMoney(parseFloat(price*amount).toFixed(2),2) ;
	                 }
                 }
                 
                 
                 
             }
             
              <#--保存到数据库-->
              if(''!=taxPrice){
                  var number = amount;
		          var ary = new Array();
		          ary.push("T");
		          ary.push(pId);
		          ary.push(taxPrice);
		          ary.push(number);
		          updateParameterFromPageJs.updateParameterFromPage(ary);
              }
              
            
             
         }
         //点击事件 根据税前单价改变税后单价
         function clickChanngeUnitPrice(pId,num){
             var length = (parseInt(num)-1);
             var taxPrice = $('taxPrice'+length).value;//税前
             taxPrice = taxPrice.Trim();
             if(''!=taxPrice){
                channgeUnitPrice(pId,num);
             }
         }
         //点击事件 根据税后单价改变税前单价
         function clickChangeTaxPrice(pId,num){
            var length = (parseInt(num)-1);
            var price = $('unitPrice'+length).value; //税后
            price = replaceComma(price);
            price = price.Trim();
            if(''!=price){
               changeTaxPrice(pId,num);
            }
         }
         //交货日期 change事件
         function changeRegDate(pId,num){
	    	 var regDateTagName='request.Date'+(num-1);
	    	 var date = $(regDateTagName).value;
	    	  <#--保存到数据库-->
	    	  if(''!=date){
	    	      var ary = new Array();
		          ary.push("D");
		          ary.push(pId);
		          ary.push(date);
		          updateParameterFromPageJs.updateParameterFromPage(ary);
	    	  }
	          
	    	 
         }
          
          //从备件台帐选择
        	function open_selectDeviceAccountDialog(){
        		var url = '${req.contextPath}/popup/spareSelector.html?toolingDevFlag=${toolingDevFlag?if_exists}&inOutFlag=purchase&spareBillId='+#{purchaseBill.id};
        		popupModalDialog(url, 1024, 700,choose_spareAccount_information)
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
            	<#if purchaseBill.typeStatus?exists>
             	var url = '${req.contextPath}/prophase/business/listSubscribechooses.html?purchaseType=${purchaseBill.typeStatus}&toolingDevFlag=${toolingDevFlag?if_exists}&buyUserId=#{purchaseBill.buyer.id?if_exists}&purchaseBill.id=#{purchaseBill.id?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
             	choose_subscribeBillDetail(url,choose_subscribeBill_Detail_information);
             	<#else>
             	alert("${action.getText('please.select.purchase.typeStatus')}");
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
        		var url = '${req.contextPath}/prophase/editPurchaseBillDetail.html?readOnly=${req.getParameter('readOnly')?if_exists}&purchaseBill.id=' + purchaseId;
        		if (null != purchaseBillDtlId) {
        			url = url+ '&purchaseBillDtl.id=' + purchaseBillDtlId;
        		}
	      		popupModalDialog(url, Config.popW+100, Config.popH);
	      		//self.location.reload();
	      		var reloadUrl = '${req.contextPath}/prophase/listPurchaseBillDetails.html?purchaseBill.id=' + purchaseId+'&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
	            self.location.href = reloadUrl;
	      		return false;
        	}
        //保存采购单明细
        function savePurchaseDtl(){
           
          	if(0!=document.getElementsByName("purchaseBillDtlIds").length){ //判断采购单明细中是否有记录
        		<#if purchaseBill.typeStatus?exists>
        		if(informationValidate()==true){							//验证信息安全
        			retrievePurchaseDetailIdText();							//获得采购单明细的所有ID
        			retrievePurchaseDetailAmountText();						//获得采购单明细的所有数量
        			retrievePurchaseDetailUnitPriceText();					//获得采购单明细的所有单价
        			retrievePurchaseDetailRequestDateText();				//获得采购单明细的所有需要日期
        			//retrievePurchaseDetailCommentText();					//获得采购单明细的所有备注
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
	      *  获取申购单明细id的所有值
	      */
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
	        var TagName = 'amount';
	        var ary = new Array();
	        
           for (var i=0; i<allPurchaseBillDtlId.length; i++) {
              var amount= $(TagName+(i+1)).value;
              if ('' != amount) {
               amount = parseInt(formatDigital(amount));
               ary.push(allPurchaseBillDtlId[i].value);
               ary.push(amount);
             }
           }
          document.forms[0].elements["allPurchaseBillDetailAmountNumber"].value=ary;
        }
        
         /*
	      *  获取申购单明细  税前单价 的所有值
	      */
	     function retrievePurchaseDetailTaxPriceText(){
	        var allPurchaseBillDtlId = document.getElementsByName("purchaseBillDtlIds");
	        var TagName = 'taxPrice';
	        var ary = new Array();
	        
           for (var i=0; i<allPurchaseBillDtlId.length; i++) {
              var taxPrice= $(TagName+(i+1)).value;
              if ('' != amount) {
               amount = parseFloat(formatDigital(taxPrice));
               ary.push(allPurchaseBillDtlId[i].value);
               ary.push(taxPrice);
             }
           }
          document.forms[0].elements["allPurchaseBillDetailTaxPrice"].value=ary;
        }
        
        
        /*
	     *  获取申购单明细单价的所有值
	     */
	    function  retrievePurchaseDetailUnitPriceText(){
	         var allPurchaseBillDtlId = document.getElementsByName("purchaseBillDtlIds");
	         var TagName = 'unitPrice';
	         var ary = new Array();
           for (var i=0; i<allPurchaseBillDtlId.length; i++) {
               var unitPrice=  $(TagName+(i+1)).value;
               if ('' !=unitPrice) {
               unitPrice = parseFloat(formatDigital(unitPrice));
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
	      var TagReqDate = 'request.Date';
	      var TagAmount = 'amount';
	      var TagTaxPrice = 'taxPrice';
	      var TagPrice = 'unitPrice';
	      var allSubscribeNum = document.getElementsByName("subscribeNum");
	     
	      
		  for(var i=0;i<allPurchaseBillDtlId.length;i++){
		    var amount = $(TagAmount+(i+1)).value;//数量
		    amount = amount.Trim();
		   	//验证数量
		  	if(''!= amount){
		  		if (!isDoubleNumberCheck(amount)){   			//验证格式
	         		alert("${action.getText('row')}"+(i+1)+"${action.getText('amount.format.error')}");
	         		return false;
       			}else if(!isNumberBetweenBoolen(amount, 1000000001, 0)){
       				alert("${action.getText('row')}"+(i+1)+"${action.getText('subscribeDtl.amount.maxLength')}");
       				return false;
       			}  
       			var subscribeNum = allSubscribeNum[i].value;
       			if(''!=subscribeNum){
       			//alert("采购数量：" + parseInt(formatDigital(amount)) + "申购数量："+ parseInt(formatDigital(subscribeNum)) + "  " +(parseInt(formatDigital(subscribeNum))<parseInt(formatDigital(amount))) + "  "+ (1000<500) )
       			     if(parseInt(formatDigital(amount)) > parseInt(formatDigital(subscribeNum))){
		  	            alert("${action.getText('row')}"+(i+1)+"行，采购数量应小于等于申购数量");
		  	            return false;
       			     }
		        }  
       		 	
		  	}else{
		  	    alert("${action.getText('row')}"+(i+1)+"${action.getText('subscribeDtl.amount.isNull')}");
       			return false;
		  	}
		    
		  	 //验证税后单价
		  	var unitPrice = $(TagPrice+(i+1)).value;//单价
		  	unitPrice = unitPrice.Trim();
		  	if(''!= unitPrice){
		  		if (!isDoubleNumberCheck(unitPrice)){   			//验证格式
	         		alert("${action.getText('row')}"+(i+1)+" 行税后单价格式错误！");
	         		return false;
       			}else if(unitPrice<=0){
       				alert("${action.getText('row')}"+(i+1)+" 行请输入大于0的税后单价");
       				return false;
       			}
		  	}else{
       			alert("${action.getText('row')}"+(i+1)+" 行税后单价不能为空！");
       			return false;
		  	}
		  	
		    //验证税前单价
            var taxPrice = $(TagTaxPrice+(i+1)).value;//单价
            taxPrice = taxPrice.Trim();
		  	if(''!= taxPrice){
		  		if (!isDoubleNumberCheck(taxPrice)){   			//验证格式
	         		alert("${action.getText('row')} "+(i+1)+" 行税前单价格式错误！");
	         		return false;
       			}else if(taxPrice<=0){
       				alert("${action.getText('row')} "+(i+1)+" 行请输入大于0的税前单价");
       				return false;
       			}
		  	}else{
       			alert("${action.getText('row')} "+(i+1)+" 行税前单价不能为空！");
       			return false;
		  	}
		  	
		  	var reqDate = $(TagReqDate+(i+1)).value;
		  	
		  	if('' != reqDate && !isDate(TagReqDate+(i+1))){
		  	    alert("${action.getText('row')} "+(i+1)+"行日期格式不正确，正确格式2010-12-01！");
		  	    return false;
		  	}
		  	<#--
		  	//验证备注长度
		  	if(''!= allCommentValue[i].value){
		  		var comment = allCommentValue[i].value;
		  		if (!isValidLengthCheck(document.forms[0],comment,null,250)) {
	       			alert("${action.getText('row')}"+(i+1)+"${action.getText("comment.maxLength")}");
		   			return false;
	     		}
		  	}-->
		  	
		  }
		  return true;           	
	    }
	    
	      function open_stock(spareId){		 	  
		    	var url = '${req.contextPath}/devicesubscribeSummary/listStocksDetals.html?spare.id='+spareId;
	 			popupModalDialog(url,800,600);
		    }
        </script>
	</@ww.form>
	
</@framePage>