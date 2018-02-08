<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
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

<#include "../../../../includes/eam2008.ftl" />
<#--$Id: lubricationRuleItemList.ftl 11192 2008-03-04 01:46:57Z zbzhang $ -->
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

<@framePage title="${action.getText('toolingPurchaseMakeDetail.title')}">
  <@ww.form name="'sparePurchaseDetails'" namespace="'/tooling/acceptBillSelector'" action="'searchsparePurchaseDetail'" method="'post'">
    <@ww.token name="searchToolingMakeDetailsToken"/>
    <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
     <#if acceptBill.id?exists>
		  <@ww.hidden name="'acceptBill.id'" value="#{acceptBill.id}"/>
	 </#if>
	<#assign itemNo=1/>
	 <input type="hidden" name="estalishAccountFlag" value=""/>
	<@ww.hidden name="'addSpareDetailIds'" value=""/>
	<@ww.hidden name="'spareAccountSelector'" value=""/>
	<@ww.hidden name="'addPurchaseContractIds'" value=""/>
	<@ww.hidden name="'purchaseContractDtlSelector'" value=""/>
	<#assign execResultIdentityName = 'execResult' + '${itemNo}'/>
	 <input type="hidden" name="allSparePurchaseDtlIds" value=""/>
	 <input type="hidden" name="allSparePurchaseDtlUnitPriceValue" value=""/>
	 <input type="hidden" name="allSparePurchaseDtlAmountValue" value=""/>
	 <input type="hidden" name="allPreRepairProcExecResult" value=""/>
	 <input type="hidden" name="allSparePurchaseDtlMemoValue" value=""/>
    <@list title="" excel=false setupTable=false 
     	   includeParameters="acceptBill.id"  fieldMap="like:" >
 	  <@vlh.checkbox property="id" name="sparePurchaseDetailIds">
        <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <@vcolumn title="${action.getText('toolingMakeDetail.serialNo')}">
         #{itemNo}
          <@alignCenter />
      </@vcolumn>
      <#assign itemNo = itemNo + 1/>
     <@vcolumn title="${action.getText('graphNo')}" property="graphNo">
        <#-- <input type="text" name="graphNo" 
	    		class="underline"  value="${object.graphNo?if_exists}"  maxlength="50" size="8"  style="text-align:right"/>-->
           <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('name')}" property="name">
        <@alignLeft />
      </@vcolumn>
     <#--<@vcolumn title="${action.getText('department')}" property="department">
        <@alignLeft />
      </@vcolumn>-->
      <@vcolumn title="${action.getText('category')}" property="categoryName">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('detailCategory')}" property="detailCategoryName">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('specification')}" property="specification">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('model')}" property="model">
        <@alignLeft />
      </@vcolumn>
       <@vcolumn title="${action.getText('unit')}" property="calUnit.value">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('unitPrice')}" property="unitPrice" format="${action.getText('currencyFormat')}">
            	<@alignRight />
            </@vcolumn>
       <@vcolumn title="${action.getText('number')}" property="amount">
            	<@alignRight />
       </@vcolumn>
         <@vcolumn title="${action.getText('allPrice')}" property="totalPrice" format="${action.getText('currencyFormat')}">
            	<@alignRight />
       </@vcolumn>
       <#--<@vcolumn title="${action.getText('unitPrice')}">
         <input type="text" name="unitPrice" 
    		   class="underline"  value="${(object.unitPrice?string('#,###,##0.00'))?if_exists}"  maxlength="50" size="10" style="text-align:right"/>
    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
        	<@alignRight />
        </@vcolumn>
      <@vcolumn title="${action.getText('number')}">
       	<input type="text" name="amount" 
	    		class="underline"  value="${object.amount?if_exists}"  maxlength="50" size="8"  style="text-align:right"/>
            	
            	<@alignRight />
        <@alignRight />
      </@vcolumn>
      <@vcolumn title="${action.getText('allPrice')}">
             	<input type="text" name="totalPrice" 
	    		class="underline"  value="${(object.totalPrice?string('#,###,##0.00'))?if_exists}"  maxlength="50" disabled="true" size="8"  style="text-align:right"/>
            	<@alignRight />
        <@alignRight />
      </@vcolumn>-->
      <@vcolumn title="${action.getText('result')}">
				      <select name="${execResultIdentityName}" valign="center">
					    <@ww.iterator value="procResults" id="procResult">
					      <option value="<@ww.property value="value"/>"><@ww.property value="label"/></option>
					    </@ww.iterator>
				      </select>
				      <script language="javascript">
			            <#if object.result?exists>
			              document.forms[0].elements["${execResultIdentityName}"].value='${object.result?if_exists}';
			            </#if>
			          </script>
			          <#assign execResultIdentityName = 'execResult' + '${itemNo}'/>
				    </@vcolumn>
		<#--<#assign establish = ''/>
            <#if object.establish?exists>
              <#if '${object.establish}' == 'UNESTABLISH'>
               <#assign establish = "${action.getText('UNESTABLISH')}"/>
              <#else>
              <#assign establish = "${action.getText('ESTABLISHED')}"/>
            </#if>
            </#if>
         	<@vcolumn title="${action.getText('establishAccount')}" attributes="width='50'">
           		${establish}
                 <@alignLeft/>
            </@vcolumn>
            <input type="hidden" name="establishAccount" value="${object.establish?if_exists}"/>-->			    
      <@vcolumn title="${action.getText('comment')}">
      <input type="text" name="memo" 
	    		   class="underline"  value="${object.memo?if_exists}"  maxlength="250" size="10"/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
        <@alignLeft />
      </@vcolumn>
    </@list>
    <#if !first>
    <#if !(action.isReadOnly())>
 	  <@buttonBar>
      <input type="button" class="button" name="new" value="${action.getText('new')}" onclick="open_toolingMakeDtlDialog()"/>
         <@vsubmit name="'save'" value="'${action.getText('save')}'">
		          <@ww.param name="'onclick'" value="'return customize_validate();'"/>
		          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		        </@vsubmit>
        <#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('sparePurchaseDetailList')}?" />
        <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
          <@ww.param name="'onclick'" value="'return confirmDeletes(\"sparePurchaseDetailIds\", \"${confirmMessage}\");'"/>
          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
        </@vsubmit>
        <@vbutton name="'new'"  class="button" value="${action.getText('fromPurchaseContnactSelect')}" onclick="open_purchaseContractDtlDialog()"/>
        <#-- <@vbutton name="'establish'"  class="button" value="${action.getText('创建台帐')}" onclick="open_establishAccountDialog(sparePurchaseDetailIds)"/>-->
      </@buttonBar>
    </#if>
    </#if>
    </@ww.form>
    <script language="javascript">	
   //从备件台帐选择
	function open_toolingMakeDtlDialog(){
	  var url = '${req.contextPath}/popup/spareSelector.html?inOutFlag=ACCEPT_SPARE_PURCHASE&toolingDevFlag=TOOLING&spareBillId='+#{acceptBill.id};
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
	
	function open_purchaseContractDtlDialog(){
		var url = '${req.contextPath}/tooling/acceptBillSelector/listPurchaseContractDtlOfSparePurchaseSelector.html?detailType=SPARE_PURCHASE&subscribeId='+#{acceptBill.id};
		popupModalDialog(url, gw, gh, refresh_purchaseContractDtl_information);
	}
	function  refresh_purchaseContractDtl_information(result){
	  if(null!=result){
	     var accpetBillToolingMakeDtlIds = result.substring(0, result.lastIndexOf(","));
	     document.forms[0].elements["addPurchaseContractIds"].value = accpetBillToolingMakeDtlIds;
         document.forms[0].elements["purchaseContractDtlSelector"].value = "purchaseContract";
         document.forms[0].submit();
	  }
	}	
  //创建台帐
       function  open_establishAccountDialog(boxName){
        //隐藏一个变量”estalishAccountFlag“ 标识创建台帐的标识  目的是在从新出发页面的时候执行listAcceptToolingAction
        //的prepare()方法来执行保存验收单明细的store方法,因为是利用的Ajax，所以在我点击创建台帐时如果要执行了store方法就此改变
        //创建台帐的值为"已创建台帐" 那么在页面提示某行已创建台帐不容易控制
        document.forms[0].elements["estalishAccountFlag"].value="estalishFlag";
        var checkBoxIds = document.getElementsByName("sparePurchaseDetailIds");
        var allGraphNoValue = document.getElementsByName("graphNo");
        var allEstablishAccount = document.getElementsByName("establishAccount");
        //标识明细循环的次数,如果loop的值等于所选中的checkbox的长度并且checkBox的checked的值为false,则提示“请选择要创建台帐的记录”
        var loop=0;
          if(checkBoxIds.length){
               for(var i=0;i<checkBoxIds.length;i++){
               loop++;
                 var length=checkBoxIds.length;
                    if(allGraphNoValue[i].value!=""){
                       if(checkBoxIds[i].checked){
                          if(allEstablishAccount[i].value=='ESTABLISHED'){
                              alert("${action.getText('row')}"+(i+1)+"${action.getText('行验收单明细已经创建台帐')}");
                              return false;
                           }else{
                              groupNoAjax(allGraphNoValue[i].value,i,checkBoxIds[i].value,length,allEstablishAccount[i].value);
                            }  
                         }
                         if(checkBoxIds[i].checked==false&&loop==checkBoxIds.length){
                            alert("${action.getText('请选择要创建台帐的记录')}");
                            break;
                          }
                     }
                    else{
                       alert("${action.getText('row')}"+(i+1)+"${action.getText('groupNo.not.null')}");
	   		           return false;
                    }
                    
                 }
            }
       }
       //判断从验收单明细中传来的图号在工装台帐中是否存在,如果存在则创建不成功,否则创建台帐置工装台帐中	
       function groupNoAjax(groupNo,i,id,length,establish){
		 		createxmlhttprequest();
		 		var url = '${req.contextPath}/tooling/acceptBillSelector/checkGroupNo.html?row='+i+'&groupNo='+groupNo+'&id='+id+'&length='+length+'&establish='+establish;
		 		xmlhttpreq.open("get",url,true);
		 		xmlhttpreq.onreadystatechange = processresponse;
		 		xmlhttpreq.send(null);
       }
       function createxmlhttprequest(){
		 	if (window.XMLHttpRequest) {
		 		xmlhttpreq = new XMLHttpRequest();
		 	}else if (window.ActiveXObject) {
		 		try {
		 			xmlhttpreq = new ActiveXObject("Msxml2.XMLHTTP");
		 		}catch (e) {
		 			try {
		 				xmlhttpreq = new ActiveXObject("microsoft.XMLHTTP");
		 			}catch (e) {}
		 		}
		 	}
		 }
	  function processresponse() {
		 	if (xmlhttpreq.readystate == 4) {
		 		if (xmlhttpreq.status == 200){
		 			var res = xmlhttpreq.responseXML.getElementsByTagName("res");
		 			if (res.length==0){
		 			      //从xml文件中获得标识为len的第一个孩子节点
		 				var flag = xmlhttpreq.responseXML.getElementsByTagName("len")[2].firstChild.data;
		 				if(flag=='false'){
		 				   alert('已成功创建台帐');
		 				   document.forms[0].submit();
		 				}
		 				  
		 			}else{
		 			   //从xml文件中获得标识为res的第一个孩子节点
		 			  var res = xmlhttpreq.responseXML.getElementsByTagName("res")[0].firstChild.data;
		 			   //从xml文件中获得标识为res的第二个孩子节点
		 			  var groupNo = xmlhttpreq.responseXML.getElementsByTagName("res")[1].firstChild.data;
		 			  //从xml文件中获得标识为res的第三个孩子节点
		 			  var length = xmlhttpreq.responseXML.getElementsByTagName("res")[2].firstChild.data;
		 			  //从xml文件中获得标识为res的第四个孩子节点
		 			  var estalish = xmlhttpreq.responseXML.getElementsByTagName("res")[3].firstChild.data;
		 			  var i=parseInt(res);
		 			}
		 		}else {
		 			window.alert("页面请求有异常")
		 		}
		 	}
		 	}
	//点击保存按钮时的验证
	<#--function customize_validate() {
	if(informationValidate()==true){
	   if (0 != document.getElementsByName("sparePurchaseDetailIds").length) {    //判断明细列表是否有记录
	         retrieveAcceptToolingMakeDetailIdText();                           //获取明细列表中的id集合
	         retrieveAcceptToolingMakeDetailNumberUnitPrice();                  //获取明细列表中的单价id集合
		  	 retrieveAcceptToolingMakeDetailNumberText();                       //获取明细中是数量的集合
	         retrieveAcceptToolingMakeDetailexecResultText();                   //获取明细中执行结果的集合
	         retrieveAcceptToolingMakeDetailComentText();                       //获取明细中所有的备注
	      }
	   }else{
	   return false;
	   }
	   return true;	 
	 }-->
	 function customize_validate(){
	  if (0 != document.getElementsByName("sparePurchaseDetailIds").length) {    //判断明细列表是否有记录
	         retrieveAcceptToolingMakeDetailIdText();                           //获取明细列表中的id集合
	        <#-- retrieveAcceptToolingMakeDetailNumberUnitPrice();                  //获取明细列表中的单价id集合
		  	 retrieveAcceptToolingMakeDetailNumberText();    -->                   //获取明细中是数量的集合
	         retrieveAcceptToolingMakeDetailexecResultText();                   //获取明细中执行结果的集合
	         retrieveAcceptToolingMakeDetailComentText();                       //获取明细中所有的备注
	      }
	      return true;
	 }
	 //获取所有明细的ids
	 function retrieveAcceptToolingMakeDetailIdText(){
	
	   var allSparePurchaseDtlId = document.getElementsByName("sparePurchaseDetailIds");
        var ary = new Array();
        for (var i=0; i<allSparePurchaseDtlId.length; i++) {
          ary.push(allSparePurchaseDtlId[i].value);
        }
        document.forms[0].elements["allSparePurchaseDtlIds"].value = ary;
	 }
	 //获取所有的明细单价
	 function retrieveAcceptToolingMakeDetailNumberUnitPrice(){
	
	    var allSparePurchaseDtlId = document.getElementsByName("sparePurchaseDetailIds");
        var allUnitPriceValue = document.getElementsByName("unitPrice");
        var ary = new Array();
       for (var i=0; i<allSparePurchaseDtlId.length; i++) {
          if ('' != allUnitPriceValue[i].value) {
           var unitPrice = parseFloat(formatDigital(allUnitPriceValue[i].value));
           ary.push(allSparePurchaseDtlId[i].value);
           ary.push(unitPrice);
         }
       }
      document.forms[0].elements["allSparePurchaseDtlUnitPriceValue"].value=ary;
	 }
	 //获得所有明细的数量
	 function retrieveAcceptToolingMakeDetailNumberText(){
	    var allSparePurchaseDtlId = document.getElementsByName("sparePurchaseDetailIds");
        var allAmount = document.getElementsByName("amount");
        var ary = new Array();
       for (var i=0; i<allSparePurchaseDtlId.length; i++) {
          if ('' != allAmount[i].value) {
           var amount = parseInt(formatDigital(allAmount[i].value));
           ary.push(allSparePurchaseDtlId[i].value);
           ary.push(amount);
         }
       }
      document.forms[0].elements["allSparePurchaseDtlAmountValue"].value=ary;
	 }
	 //获得所有明细的验收结果
	 function retrieveAcceptToolingMakeDetailexecResultText(){
	    var allSparePurchaseDtlId = document.getElementsByName("sparePurchaseDetailIds");
        var ary = new Array();
       for (var i=0; i<allSparePurchaseDtlId.length; i++) {
         var execResultTagName = 'execResult';
         execResultTagName = execResultTagName + (i+1); 
         ary.push(allSparePurchaseDtlId[i].value);
         ary.push(document.forms[0].elements[execResultTagName].value);
       }
       document.forms[0].elements["allPreRepairProcExecResult"].value = ary; 
	 }
	 //获取所有的明细备注
	  function retrieveAcceptToolingMakeDetailComentText(){
	    var allSparePurchaseDtlId = document.getElementsByName("sparePurchaseDetailIds");
        var allComment = document.getElementsByName("memo");
        var ary = new Array();
       for (var i=0; i<allSparePurchaseDtlId.length; i++) {
          if ('' != allComment[i].value) {
           ary.push(allSparePurchaseDtlId[i].value);
           ary.push(allComment[i].value);
         }
       }
      document.forms[0].elements["allSparePurchaseDtlMemoValue"].value=ary;
	 }
	 //验证明细的单价和数量
	 function informationValidate(){
    	var allToolingMakeDtlId = document.getElementsByName("sparePurchaseDetailIds");
	    var allUnitPriceValue = document.getElementsByName("unitPrice");
	     var allNumberValue = document.getElementsByName("amount");
	    for(var i=0;i<allToolingMakeDtlId.length;i++){
		  if(''!= allUnitPriceValue[i].value){
			  	var unitPrice = allUnitPriceValue[i].value;
			   if(!isDoubleNumberBetweenBoolean(unitPrice,1000000001,0)){
	       			alert("${action.getText('row')}"+(i+1)+"${action.getText('subscribeDtl.unitPrice.maxLength')}");
	       			return false;
	       		}
			 }else{
		  		alert("${action.getText('row')}"+(i+1)+"${action.getText('subscribeDtl.unitPrice.maxLength')}");
	   			return false;
			 }
		    if(''!=allNumberValue[i].value){
			 	var number = allNumberValue[i].value;
			  	if (!isNumber(number)){  
		         	alert("${action.getText('di')}"+(i+1)+"${action.getText('amount.format.error')}");
		         	return false;
	           }else if(!isDoubleNumberBetweenBoolean(number,1000000001,0)){
	       			alert("${action.getText('di')}"+(i+1)+"${action.getText('amount.format.error')}");
	       			return false;
	       		 }
			  }else{
			        alert("${action.getText('di')}"+(i+1)+"${action.getText('amount.format.error')}");
	       			return false;
			  }
	       }
	    return true;
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
</@framePage>