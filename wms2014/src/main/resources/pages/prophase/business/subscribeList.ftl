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
<#--$Id: subscribeList.ftl 11279 2008-03-12 01:12:13Z mwei $ -->
<#--
<script type='text/javascript' src='${req.contextPath}/dwr/interface/loadSubscribesByIdJs.js'></script>
-->
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('subscribeList.title')}">
	<@ww.form namespace="'/prophase/business'" name="'searchSubscribe'" action="'searchSubscribes'" method="'post'">
		<@ww.token name="searchSubscribeToken"/>
		<#include "subscribeSearcher.ftl"/>
		<@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@ww.hidden name="'subscribeCollectBill'" value=""/>
        <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/prophase/business/editSubscribe.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}"/>
	        </#if>
		</@buttonBar>
		<@list title="${action.getText('subscribeList')}" 
		includeParameters="billNo|name|buyingPerson.name|readOnly|subscribeDate_start|subscribeDate_end|toolingDevFlag|onlyValid|onlyInvalid|eamAuthentication|detailKind.id" 
		fieldMap="like:billNo|name|buyingPerson.name|,date:subscribeDate_start|subscribeDate_end">
            <#if (object.disabled)>
            <@vlh.checkbox property="id" name="subscribeIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('subscribe.billNo')}" property="billNo" sortable="desc">
            	${object.billNo}
            	<@alignLeft />
            </@vcolumn>
            <#else>
            <@vlh.checkbox property="id" name="subscribeIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
        	<@ww.hidden name="'isStatus'" value="'${object.status?string}'"/>
        	<@ww.hidden name="'isRemarks'" value="'${object.remark?string}'"/>
            <@vcolumn title="${action.getText('subscribe.billNo')}" property="billNo" sortable="desc">
            	<a href="editSubscribe.html?toolingDevFlag=${toolingDevFlag}&subscribe.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}">${object.billNo}</a>
            	<@alignLeft />
            </@vcolumn>
            </#if>
            <@vcolumn title="${action.getText('subscribe.name')}" property="name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('subscribes.department')}" property="department.name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('subscriber')}" property="buyingPerson.name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('subscrible.date')}" property="subscribeDate" format="yyyy-MM-dd" sortable="asc">
            	<@alignCenter />
            </@vcolumn>
            <#assign subtypeStatus = ''/>
            <#if object.typeStatus?exists>
       			<#if '${object.typeStatus}' == 'LOWLOSS'>
       				<#assign subtypeStatus = "${action.getText('LOWLOSS')}"/>
       			<#elseif '${object.typeStatus}' == 'SPARE'>
       				<#assign subtypeStatus = "${action.getText('SPARE')}"/>
       			<#elseif '${object.typeStatus}' == 'TOOLING'>
       				<#assign subtypeStatus = "${action.getText('TOOLING')}"/>
       			</#if>
       		</#if>
            <@vcolumn title="${action.getText('subscribes.typeStatus')}" sortable="desc">
            	${subtypeStatus}
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('totalPrice')}" property="totalPrice" format="${action.getText('currencyFormat')}">
            	<@alignRight/>
            </@vcolumn>
           <@vcolumn title="单据类型" property="detailKind.value" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('reason')}" property="reason" >
            	<@alignLeft />
            </@vcolumn>
            <#--
             <@vcolumn title="明细数量" property="sumDetail" >
            	<@alignLeft />
            </@vcolumn>-->
             <@vcolumn title="申购项" property="sumDetail" >
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="采购项" property="purNum" >
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="入库项" property="insNum" >
            	<@alignLeft />
            </@vcolumn>
            
            <#assign subscribeStatus = ''/>
       		<#if object.status?exists>
       			<#if '${object.status}' == 'NEWPURCHASE'>
             		<#assign subscribeStatus = "${action.getText('新建')}"/>
                <#elseif '${object.status}' == 'AUDITING'> 		
                    <#assign subscribeStatus = "${action.getText('审核中')}"/>
             	<#elseif '${object.status}' == 'SUMMARYED'>
             		<#assign subscribeStatus = "${action.getText('已汇总')}"/>
             	<#elseif '${object.status}' == 'PURCHASING'>
             		<#assign subscribeStatus = "${action.getText('采购中')}"/>
             		<#-- 修改内容：第一种采购中状态的处理，lwc
             		
             	<#elseif '${object.status}' == 'PURCHASING' && '${object.remark}' == '1'>
             		<#assign subscribeStatus = "${action.getText('采购中')}"/>
             		<#-- 修改内容：第二种采购中状态的处理，lwc
             		
             	<#elseif '${object.status}' == 'PURCHASING' && '${object.remark}' == '0'>
             		<#assign subscribeStatus = "${action.getText('新建/采购中')}"/>
					<#-- 修改结束	-->
             	<#elseif '${object.status}' == 'PURCHASEED'>
             		<#assign subscribeStatus = "${action.getText('已采购')}"/>
             	<#elseif '${object.status}' == 'STORAGING'>
             		<#assign subscribeStatus = "${action.getText('入库中/验收中')}"/>
             	<#elseif '${object.status}' == 'STORAGED'>
             		<#assign subscribeStatus = "${action.getText('已入库/已验收')}"/>
             	</#if>
       		</#if>
            <@vcolumn title="${action.getText('status')}" attributes="width='50'">
            	${subscribeStatus}
            	<@alignLeft />
            </@vcolumn>
            <#assign subscribeStatus = ''/>
       		<#if object.status?exists>
       			<#if '${object.status}' == 'NEWPURCHASE'>
             		<#assign subscribeStatus = "${action.getText('可汇总')}"/>
             	<#elseif '${object.status}' == 'AUDITING'>
             	    <#assign subscribeStatus = "${action.getText('可汇总')}"/>
             	<#elseif '${object.remark}' == '0' && '${object.status}' == 'PURCHASING'>
             		<#assign subscribeStatus = "${action.getText('可汇总')}"/>
             	<#elseif '${object.remark}' == '1' && '${object.status}' == 'PURCHASING'>
             		<#assign subscribeStatus = "${action.getText('不可汇总')}"/>
             	<#elseif '${object.status}' == 'PURCHASEED'>
             		<#assign subscribeStatus = "${action.getText('不可汇总')}"/>
             	<#elseif '${object.status}' == 'STORAGING'>
             		<#assign subscribeStatus = "${action.getText('不可汇总')}"/>
             	<#elseif '${object.status}' == 'STORAGED'>
             		<#assign subscribeStatus = "${action.getText('不可汇总')}"/>
             	<#elseif '${object.status}' == 'SUMMARYED'>
             		<#assign subscribeStatus = "${action.getText('不可汇总')}"/>
             	</#if>
       		</#if>
            <@vcolumn title="${action.getText('汇总状态')}" attributes="width='60'">
            	${subscribeStatus}
            	<@alignLeft />
            </@vcolumn>
		</@list>
		<#if !first>
        <@buttonBar>
             <#if !(action.isReadOnly())>
              <@eam2008_disabledOrEnabled_button message="${action.getText('subscribesBill')}" boxName="subscribeIds" jsFunctionName="checkInvalidParms()"/>
              <#if !(action.isOnlyInvalid())>
               <#if '${eamAuthentication?if_exists}' == 'Collect'>
              <@vbutton value="${action.getText('summary')}" onclick="summary()" class="button"/>
              </#if>
              </#if>
              <#else>
               <@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_subscribeListPdf('subscribeIds')"/>
		       <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_subscribeListXls('subscribeIds')"/>
             </#if>
        </@buttonBar>
        </#if>
	</@ww.form>
<script language="JavaScript" type="text/JavaScript">

	function summary(){
		var num=0;
		//var selector = document.forms[0].elements["subscribeIds"];
		var selector = document.getElementsByName("subscribeIds");
		var statusFlag = document.getElementsByName("isStatus");
		var remarkFlag = document.getElementsByName("isRemarks");
		for(var i=0;i<selector.length;i++){
			if(selector[i].checked){
				if(statusFlag[i].value == "NEWPURCHASE" || statusFlag[i].value=="AUDITING"){
					num=num+1;
				}else
				if(statusFlag[i].value == "PURCHASING" && remarkFlag[i].value == 0){
					num=num+1;
				}else{
					isSubmit = 0;
					alert("第"+(i+1)+"行 申购单，明细中没有新建内容可汇总，请参照汇总状态选择。");
					return false;
				}
			}
		}
		if(num>0){
			document.forms[0].elements["subscribeCollectBill"].value = "subscribeCollectBills";
			checkInvalidParms();
			document.forms[0].submit();
			return true;
		}else{
			alert("请选择要汇总的申购单");
			return false;
		}
			
		
	} 
	
	function callbackSubscribe(data){
	alert(data[0]);
		if(data.length>0){
			alert(data);
    		resultData = data;
    		alert("有数据");
	  	}else{
	  		resultData = "";
	  		alert("无数据");
	  	}
	}
    window.onload = function () {
	    if ('${onlyInvalid?string}' == 'true') {
	      getObjByNameRe("onlyDisabled").checked=true;
	    }
	}
	function validateInvalid(delFun, checkFun) {
         if (delFun) {
         	checkFun;
         	return true;
         }
         return false;
    }
    <#--   
    function   open_subscribeListPdf(){
      var url='${req.contextPath}/reports/subscribe/subscribeList.html?billNo='+document.forms[0].elements["billNo"].value+
	       	  '&name='+(document.forms[0].elements["name"].value) +
	       	  '&buyingPerson.name='+ (document.forms[0].elements["buyingPerson.name"].value)+
        	  '&department.id='+ (document.forms[0].elements["department.id"].value)+
        	  '&subscribeDate='+ (document.forms[0].elements["subscribeDate_start"].value)+
        	  '&subscribeDate='+ (document.forms[0].elements["subscribeDate_end"].value)+
        	  '&toolingDevFlag='+(document.forms[0].elements["toolingDevFlag"].value);
      if(document.forms[0].elements["onlyDisabled"].checked){
          url=url+'&disabled=true';
      }
    	  url = encodeURI(url);
    	 // alert(url);
  		  window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=no,width=screen.width,height=screen.height,left=0,top=0");
     }
     -->
    function open_subscribeListPdf(billIds){
        if (!hasChecked(billIds)) {
  			alert("请选择需要打印的申购单!");
  			return false;
  		}
  		var selector = document.getElementsByName(billIds);
  		if (!selector) {
		    return false;
		}
	    var billId = "";
	    if (selector.length) {
	        for (i = 0; i < selector.length; i++) {
	            if (selector[i].checked) {
	                billId += selector[i].value + ",";
	            }
	        }
	    }
	   if (!confirm("打印或者导出申购单,申购单将变成[审核中],将不可修改,确定打印或导出吗?")) {
         return false;
       }
	    var url='${req.contextPath}/reports/subscribe/subscribesDetailAllList.pdf?subscribeBillIds='+billId;  
        url = encodeURI(url);
        window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	}	
    function open_subscribeListXls(billIds){
        if (!hasChecked(billIds)) {
  			alert("请选择需要打印的申购单!");
  			return false;
  		}
  		var selector = document.getElementsByName(billIds);
  		if (!selector) {
		    return false;
		}
	    var billId = "";
	    if (selector.length) {
	        for (i = 0; i < selector.length; i++) {
	            if (selector[i].checked) {
	                billId += selector[i].value + ",";
	            }
	        }
	    }
	   if (!confirm("打印或者导出申购单,申购单将变成[审核中],将不可修改,确定打印或导出吗?")) {
         return false;
       }
	    var url='${req.contextPath}/reports/subscribe/subscribesDetailAllList.xls?subscribeBillIds='+billId;  
        url = encodeURI(url);
        window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	}	  
    </script>

</@htmlPage>
