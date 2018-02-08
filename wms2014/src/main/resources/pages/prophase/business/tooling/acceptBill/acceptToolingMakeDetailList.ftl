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
  <@ww.form name="'listToolingMakeDetails'" namespace="'/tooling/acceptBillSelector'" action="'searchAcceptToolingMakeDetails'" method="'post'">
    <@ww.token name="searchToolingMakeDetailsToken"/>
    <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
     <#if acceptBill.id?exists>
		  <@ww.hidden name="'acceptBill.id'" value="#{acceptBill.id}"/>
	 </#if>
	<#assign itemNo=1/>
	<#assign execResultIdentityName = 'execResult' + '${itemNo}'/>
	<#assign establishAccountIdentityName = 'establish' + '${itemNo}'/>
	<#assign departmentIdentityName = 'department' + '${itemNo}'/>
	 <input type="hidden" name="estalishAccountFlag" value=""/>
	 <input type="hidden" name="addPurchaseContractIds" value=""/>
	 <input type="hidden" name="purchaseContractDtlSelector" value=""/>
	 <input type="hidden" name="allToolingMakeDtlIds" value=""/>
	 <input type="hidden" name="allListToolingMakeDetailDepartment" value=""/>
	 <input type="hidden" name="allToolingMakeDtlGroupNoValue" value=""/>
	 <input type="hidden" name="allToolingMakeDtlUnitPriceValue" value=""/>
	 <input type="hidden" name="allToolingMakeDtlAmountValue" value=""/>
	 <input type="hidden" name="allPreRepairProcExecResult" value=""/>
	 <input type="hidden" name="allAcceptToolingMakeEstablishAccount" value=""/>
	 <@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
    <@list title="" excel=false setupTable=false 
     	   includeParameters="acceptBill.id"  fieldMap="like:" >
 	  <@vlh.checkbox property="id" name="toolingMakeDetailIds">
        <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <@vcolumn title="${action.getText('toolingMakeDetail.serialNo')}">
         <a href="#" onclick="open_detailDialog(#{acceptBill.id},#{object.id});return false;">#{itemNo}</a>
          <@alignCenter />
      </@vcolumn>
      <#assign itemNo = itemNo + 1/>
      <@vcolumn title="${action.getText('graphNo')}">
       <input type="text" name="graphNo" 
	    		class="underline"  value="${object.graphNo?if_exists}"  maxlength="50" size="8"  style="text-align:left"/>
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('name')}" property="name">
        <@alignLeft />
      </@vcolumn>
       <@vcolumn title="${action.getText('department_sub')}">
				      <select name="${departmentIdentityName}">
					    <@ww.iterator value="departments" id="department">
					      <option value="<@ww.property value="id"/>"><@ww.property value="name"/></option>
					    </@ww.iterator>
				      </select>
				      <script language="javascript">
			            <#if object.department?exists>
			              document.forms[0].elements["${departmentIdentityName}"].value='${object.department.id?if_exists}';
			            </#if>
			          </script>
			          <#assign departmentIdentityName = 'department' + '${itemNo}'/>
			         
	   </@vcolumn>
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
		<#assign establish = ''/>
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
            <input type="hidden" name="establishAccount" value="${object.establish?if_exists}"/>
      <@vcolumn title="${action.getText('comment')}" property="memo">
        <@alignLeft />
      </@vcolumn>
    </@list>
    <#if !first>
    <#if !(action.isReadOnly())>
 	  <@buttonBar>
        <input type="button" class="button" name="new" value="${action.getText('new')}" onclick="open_detailDialog(#{acceptBill.id}, null)"/>
         <@vsubmit name="'save'" value="'${action.getText('save')}'">
		          <@ww.param name="'onclick'" value="'return customize_validate();'"/>
		          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		        </@vsubmit>
        <#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('toolingMakeDetail')}?" />
        <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
          <@ww.param name="'onclick'" value="'return confirmDeletes(\"toolingMakeDetailIds\", \"${confirmMessage}\");'"/>
          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
        </@vsubmit>
        <@vbutton name="'new'"  class="button" value="${action.getText('fromPurchaseContactSelect')}" onclick="open_purchaseContractDtlDialog()"/>
        <@vbutton name="establish"  class="button" value="${action.getText('创建台帐')}" onclick="validateInformation(toolingMakeDetailIds)">
         </@vbutton>
      </@buttonBar>
    </#if>
    </#if>
    </@ww.form>
     <script language="javascript">	
     window.onload=function(){
      <#if !(action.isReadOnly())>
   			<#if acceptBill.submit==true>
		      parent.document.forms[0].elements["submitRecord"].disabled=false;
		     <#if acceptBill.acceptBillDtl.size()==0>
		      parent.document.forms[0].elements["submitRecord"].disabled=true;
		    </#if>
	      </#if>
	     </#if>
	     <#if req.getParameter('errorFlag')?has_content>
   	       alert("${action.getText('delete.acceptBillDetail.failure')}");
        </#if>
        <#if (acceptBill.acceptBillDtl.size())==0>
          document.forms[0].elements["establish"].disabled=true;
        </#if>
     }
     	 
	function open_purchaseContractDtlDialog(){
		var url = '${req.contextPath}/tooling/acceptBillSelector/listPurchaseContractDtlSelector.html?detailType=TOOLING_MAKE&acceptId='+#{acceptBill.id};
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
        function open_detailDialog(acceptId,acceptBillDtlId) {
        		var url = '${req.contextPath}/tooling/acceptBillSelector/editAcceptBillToolingMakeDetail.html?acceptBill.id=' + acceptId;
        		if (null != acceptBillDtlId) {
        			url = url+ '&toolingMakeDetail.id=' + acceptBillDtlId;
        		}
	      		popupModalDialog(url, Config.popW+100, Config.popH);
	      		var reloadUrl = '${req.contextPath}/tooling/acceptBillSelector/listAcceptToolingMakeDetails.html?acceptBill.id='+acceptId+'&readOnly=${req.getParameter('readOnly')?if_exists}';
	      		if (null != acceptBillDtlId) {
        			reloadUrl = reloadUrl + '&pagingPage=${pagingPage?if_exists}'
        		}
	      		self.location.href = reloadUrl; 
        	}
       function  validateInformation(boxName){
           document.forms[0].elements["estalishAccountFlag"].value="estalishFlag";
           var checkBoxIds = document.getElementsByName("toolingMakeDetailIds");
           var allGraphNoValue = document.getElementsByName("graphNo");
           var allEstablishAccount = document.getElementsByName("establishAccount");
           var ary = new Array();
           var j=0;
           var checked=0;
           for(var i=0;i<checkBoxIds.length;i++){
              var departmentTagName = 'department';
              departmentTagName = departmentTagName + (i+1); 
            if(checkBoxIds[i].checked==false){
                j++;
                 continue;
              }else{
                checked++;
              }
              //验证图号
              if(''==allGraphNoValue[i].value){
                  alert("${action.getText('row')}"+(i+1)+"${action.getText('groupNo.not.null')}");
	   		      return false;
              }else{
                if(checkBoxIds[i].checked){
                    ary.push(allGraphNoValue[i].value); //把图号放入数组中  目的是根据图号判断此图号在工装台帐中是否存在
                    ary.push(checkBoxIds[i].value);     //把验收单明细的id放入数组  目的是根据此id获得验收单明细的对象
                    ary.push(i);                        //把行数放入数组中 目的是根据此行数判断哪一行的图号在工装台帐中已经存在
                    ary.push(document.forms[0].elements[departmentTagName].value);
                  }
               }
              var departmentTagName='department'
              departmentTagName = departmentTagName + (i+1); 
              //验证部门
              if(-1== document.forms[0].elements[departmentTagName].value){
                  alert("${action.getText('please.select')}"+(i+1)+"${action.getText('sub.department')}");
	   		      return false;
              }
              var resultTagName = 'execResult'
              resultTagName = resultTagName + (i+1);
              if(document.forms[0].elements[resultTagName].value == 'DISQUALIFICATION'){
              	alert("${action.getText('row')}"+(i+1)+"${action.getText('行验收单明细验收结果不合格，不允许创建台帐')}");
              	return false;
              }
           <#--  <#if acceptBill.acceptBillDtl.size()!=0>
              <#if object.judageSave==false>
                    alert("${action.getText('row')}"+(i+1)+"${action.getText('行验收单明细还没有保存')}");
                     return false;
              </#if>
              </#if>-->
              //验证创建台帐
              if(allEstablishAccount[i].value=='ESTABLISHED'){
                  alert("${action.getText('row')}"+(i+1)+"${action.getText('行验收单明细已经创建台帐')}");
                  return false;
              }
           }
            if(j==checkBoxIds.length){
                     alert("${action.getText('请选择要创建台帐的记录')}");
                     //jac人员非常规操作
                     document.forms[0].elements["estalishAccountFlag"].value="";
                     return false;
              }else{
              	 document.forms[0].elements["estalishAccountFlag"].value="estalishFlag";
                 groupNoAjax(ary);
            }
           return true;
       }
       <#--	
       //创建台帐
       function open_establishAccountDialog(boxName){
            var checkBoxIds = document.getElementsByName("toolingMakeDetailIds");
            var allGraphNoValue = document.getElementsByName("graphNo");
            
            if(validateInformation()==true){
               for(var i=0;i<checked;i++){
                 groupNoAjax(allGraphNoValue[i].value,i);
               
               }
            }
       }
       -->
       //判断从验收单明细中传来的图号在工装台帐中是否存在,如果存在则创建不成功,否则创建台帐置工装台帐中	
		  function groupNoAjax(ary){
		 		createxmlhttprequest();
		 		var url = '${req.contextPath}/tooling/acceptBillSelector/checkGroupNo.html?ary='+ary;
		 		xmlhttpreq.open("get",encodeURI(url),true);
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
		 			  //当创建台帐失败时，获取status标签值
		 				var status = xmlhttpreq.responseXML.getElementsByTagName("status");
		 				//如果status标签值的长度不为0时,获取此标签的flag属性值
		 				    if(status.length!=0){
		 				        var flag = status[0].getAttribute("flag");
		 				        if(flag=='true'){
		 				            var row =xmlhttpreq.responseXML.getElementsByTagName("status")[0].firstChild.data;
		 				            var groupNo =xmlhttpreq.responseXML.getElementsByTagName("status")[1].firstChild.data;
		 				            var row=parseInt(row);
		 				            alert("${action.getText('row')}"+(row+1)+"${action.getText('行图号')}"+groupNo+"${action.getText('在工装台帐中已经存在')}");
		 				        }
		 				     }else{
		 				        //当已成功创建台帐时,获取type标签值
		 				     	var status = xmlhttpreq.responseXML.getElementsByTagName("type");
		 				     	//如果此标签值得值为false时   提示“已创建台帐”
		 				            var flag = status[0].getAttribute("flag");
		 				            if(flag=='false'){
		 				                 alert('已成功创建台帐');
		 				                 document.forms[0].submit();
		 				            }
		 				     }
		 			}else{
		 			   //从xml文件中获得标识为res的第一个孩子节点
		 			   //从xml文件中获得标识为res的第二个孩子节点
		 			  var groupNo = xmlhttpreq.responseXML.getElementsByTagName("res")[0].firstChild.data;
		 		      var row = xmlhttpreq.responseXML.getElementsByTagName("res")[1].firstChild.data;
		 		      var i=parseInt(row);
		 		      alert("${action.getText('row')}"+(i+1)+groupNo+"${action.getText('在工装台帐中已经存在')}");
		 			  //从xml文件中获得标识为res的第三个孩子节点
		 			}
		 		}else {
		 			window.alert("页面请求有异常")
		 		}
		 	}
		 }	 
        //改变金额
	    function changValue(){
	  		var number;
	  		var unitPrice;
	  		var sumFee;
	  		var selector = document.getElementsByName("toolingMakeDetailIds");
	  		var unitSelector=document.getElementsByName("unitPrice");
	  		var valueselector = document.getElementsByName("amount");
	  		var totalPriceSelector = document.getElementsByName("totalPrice");
	  		if (selector.length) {
				for (var i = 0; i < selector.length; i++) {
					if(valueselector[i].value!=""){
			         number=valueselector[i].value;
			         unitPrice=unitSelector[i].value;
			         sumFee=number*unitPrice;
			         totalPriceSelector[i].value=sumFee;
			        }
		   	    }
	  		}
		}
	//点击保存按钮时的验证
	<#--function customize_validate() {
	if(informationValidate()==true){
	   if (0 != document.getElementsByName("toolingMakeDetailIds").length) {    //判断明细列表是否有记录
	         retrieveAcceptToolingMakeDetailIdText();                           //获取明细列表中的id集合
	         retrieveAcceptToolingMakeDetailNumberUnitPrice();                  //获取明细列表中的单价id集合
		  	 retrieveAcceptToolingMakeDetailNumberText();                       //获取明细中是数量的集合
	         retrieveAcceptToolingMakeDetailexecResultText();                   //获取明细中执行结果的集合
	      }
	   }else{
	   return false;
	   }
	   return true;	 
	 }-->
	function customize_validate(){
	  if (0 != document.getElementsByName("toolingMakeDetailIds").length) {     //判断明细列表是否有记录
	         if(informationValidate()==true){
		         retrieveAcceptToolingMakeDetailIdText();                           //获取明细列表中的id集合
		         retrieveAcceptToolingMakeDetailIdGraphNo();                        //获取明细列表中的工装图号
		         retrieveAcceptToolingMakeDetailexecResultText();                   //获取明细中执行结果的集合
		         retrieveAcceptToolingMakeDetailDepartmentText();
	      	return true;
	      	}else{
	      	return false;
	      	}
	   }
	 }
	 //获取所有明细的ids
	 function retrieveAcceptToolingMakeDetailIdText(){
	   var allToolingMakeDtlId = document.getElementsByName("toolingMakeDetailIds");
        var ary = new Array();
        for (var i=0; i<allToolingMakeDtlId.length; i++) {
          ary.push(allToolingMakeDtlId[i].value);
        }
        document.forms[0].elements["allToolingMakeDtlIds"].value = ary;
	 }
	 //获取所有明细的工装图号
	 function retrieveAcceptToolingMakeDetailIdGraphNo(){
	     var allToolingMakeDtlId = document.getElementsByName("toolingMakeDetailIds");
	     var allToolingMakeGroupNo= document.getElementsByName("graphNo");
	     var ary =new Array();
	     for(var i=0;i<allToolingMakeDtlId.length;i++){
	        if(''!=allToolingMakeGroupNo[i].value){
	           ary.push(allToolingMakeDtlId[i].value);
	           ary.push(allToolingMakeGroupNo[i].value);
	        }
	     }
	     document.forms[0].elements["allToolingMakeDtlGroupNoValue"].value=ary; 
	 }
	 //获得所有明细的验收结果
	 function retrieveAcceptToolingMakeDetailexecResultText(){
	    var allToolingMakeDtlId = document.getElementsByName("toolingMakeDetailIds");
        var ary = new Array();
       for (var i=0; i<allToolingMakeDtlId.length; i++) {
         var execResultTagName = 'execResult';
         execResultTagName = execResultTagName + (i+1); 
         ary.push(allToolingMakeDtlId[i].value);
         ary.push(document.forms[0].elements[execResultTagName].value);
       }
       document.forms[0].elements["allPreRepairProcExecResult"].value = ary; 
	 }
	 //获取所有明细的部门的值 
	function retrieveAcceptToolingMakeDetailDepartmentText(){
	   var allToolingMakeDtlId = document.getElementsByName("toolingMakeDetailIds");
	   var ary = new Array();
       for (var i=0; i<allToolingMakeDtlId .length; i++) {
         var departmentTagName = 'department';
         departmentTagName = departmentTagName + (i+1); 
         if(-1 != document.forms["listToolingMakeDetails"].elements[departmentTagName].value) {
           ary.push(allToolingMakeDtlId[i].value);
           ary.push(document.forms["listToolingMakeDetails"].elements[departmentTagName].value);
         }
       }
       document.forms["listToolingMakeDetails"].elements["allListToolingMakeDetailDepartment"].value = ary;
	}
	 //验证明细的单价和数量
	 function informationValidate(){
    	var allToolingMakeDtlId = document.getElementsByName("toolingMakeDetailIds");
	    for(var i=0;i<allToolingMakeDtlId.length;i++){
	    	var departmentTagName = 'department';
	    	departmentTagName = departmentTagName + (i+1);
		 	if(-1== document.forms[0].elements[departmentTagName].value){
              	alert("${action.getText('please.select')}"+(i+1)+"${action.getText('sub.department')}");
	   		  	return false;
          	}
	    }
	    return true;
    }
    <#--
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
	-->
   </script>
</@framePage>