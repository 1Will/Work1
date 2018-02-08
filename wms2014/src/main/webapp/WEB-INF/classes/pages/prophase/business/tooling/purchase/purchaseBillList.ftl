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

<#include "../../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('TpurchaseBill.title')}">
	<@ww.form namespace="'/tooling/prophase/business'" name="'searchSubscribe'" action="'searchSubscribes'" method="'post'">
		<@ww.token name="searchSubscribeToken"/>
		<#include "purchaseBillSearcher.ftl"/>
		<@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			 <#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/tooling/prophase/business/editSubscribe.html?toolingDevFlag=${toolingDevFlag?if_exists}"/>
	        </#if>
		</@buttonBar>
		<@list title="${action.getText('TpurchaseBill.List')}" 
		includeParameters="billNo|readOnly|name|department.id|subscribeDate_start|subscribeDate_end|toolingDevFlag|onlyValid|onlyInvalid" 
		fieldMap="like:billNo|name|buyingPerson.name|,date:subscribeDate_start|subscribeDate_end">
          <#if (object.disabled)>
            <@vlh.checkbox property="id" name="subscribeIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('Tpurchase.billNo')}" property="billNo" sortable="desc">
            	${object.billNo}
            	<@alignLeft />
            </@vcolumn>
          <#else>
            <@vlh.checkbox property="id" name="subscribeIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('Tpurchase.billNo')}" property="billNo" sortable="desc">
            	<a href="editSubscribe.html?toolingDevFlag=${toolingDevFlag}&subscribe.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.billNo}</a>
            	<@alignLeft />
            </@vcolumn>
          </#if>
            <@vcolumn title="${action.getText('Tpurchase.name')}" property="name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('department')}" property="department.name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('Tpurchase.Person')}" property="buyingPerson.name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('Tpurchase.subscribeDate')}" property="subscribeDate" format="yyyy-MM-dd" sortable="asc">
            	<@alignCenter />
            </@vcolumn>
            <#--
            <#assign subtypeStatus = ''/>
            <#if object.typeStatus?exists>
       			<#if '${object.typeStatus}' == 'TOOLING'>
       			<#assign subtypeStatus = "${action.getText('TOOLING')}"/>
       			<#elseif '${object.typeStatus}' == 'SPARE'>
       			<#assign subtypeStatus = "${action.getText('SPARE')}"/>
       			</#if>
       		</#if>
            <@vcolumn title="${action.getText('subscribes.typeStatus')}" sortable="desc">
            	${subtypeStatus}
            	<@alignLeft/>
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('Tpurchase.totalPrice')}" property="totalPrice" format="${action.getText('currencyFormat')}" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('Tpurchase.reason')}" property="reason" >
            	<@alignLeft />
            </@vcolumn>
            <#assign subscribeStatus = ''/>
            <#if object.status?exists>
       			<#if '${object.status}' == 'NEWPURCHASE'>
       			<#assign subscribeStatus = "${action.getText('NEWPURCHASE')}"/>
       			<#elseif '${object.status}' == 'PURCHASING'>
       			<#assign subscribeStatus = "${action.getText('PURCHASING')}"/>
       			<#elseif '${object.status}' == 'PURCHASEED'>
       			<#assign subscribeStatus = "${action.getText('PURCHASEED')}"/>
       			</#if>
       		 </#if>
            <@vcolumn title="${action.getText('status')}" attributes="width='50'">
            	${subscribeStatus}
            	<@alignLeft />
            </@vcolumn>
		</@list>
		<#if !first>
        <@buttonBar>
              <#if !(action.isReadOnly())>
              <@eam2008_disabledOrEnabled_button message="${action.getText('toolingPurchaseBIll')}" boxName="subscribeIds" jsFunctionName="checkInvalidParms()"/>
             </#if>
              <#--
              <@vbutton name="print"  class="button" value="${action.getText('print')}" onclick="open_subscribeListPdf()"/>
        	  -->
        </@buttonBar>
        </#if>
	</@ww.form>
	 <script language="JavaScript" type="text/JavaScript"> 
       window.onload = function () {
	    if ('${onlyInvalid?string}' == 'true') {
	      document.getElementById("onlyDisabled").checked=true;
	    }
	  }
	function validateInvalid(delFun, checkFun) {
         if (delFun) {
         	checkFun;
         	return true;
         }
         return false;
       }
       
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
        	alert(url);
      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=no,width=screen.width,height=screen.height,left=0,top=0");
    }
       
    </script>

</@htmlPage>
