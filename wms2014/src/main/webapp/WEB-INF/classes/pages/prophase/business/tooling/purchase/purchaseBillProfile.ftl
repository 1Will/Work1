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
<#--$Id: subscribeProfile.ftl 11279 2008-03-12 01:12:13Z mwei $ -->

<#include "../../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('Tpurchase.title')}">
	<@ww.form namespace="'/tooling/prophase/business'" name="'subscribe'" action="'saveSubscribe'" method="'post'">
		<@ww.token name="saveSubscribeToken"/>
		<#if subscribe.id?exists>
		 <@ww.hidden name="'subscribe.id'" value="#{subscribe.id}"/>  
		 </#if>
		 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		 <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
		 <#if subscribe.status?exists>
        <@ww.hidden name="'status'" value="'${subscribe.status}'"/>
        </#if>
		<@inputTable>
		   <tr>
			<@ww.textfield label="'${action.getText('Tpurchase.billNo')}'" name="'subscribe.billNo'" value="'${subscribe.billNo?if_exists}'" cssClass="'underline'"   readonly="true" disabled="true" />	
		    <@ww.textfield label="'${action.getText('Tpurchase.name')}'" name="'subscribe.name'" value="'${subscribe.name?if_exists}'" cssClass="'underline'"    disabled="false" required="true"/>	
		   </tr>
		   <tr>
				<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		       </@ww.select> 
		       <@pp.remotePicker label="'${action.getText('Tpurchase.Person')}'" name="'buyingPerson'"
                    selectorName="'userSelectorAjax'" cssClass="'underline'" value="subscribe.buyingPerson"
                    popup="'${req.contextPath}/popup/userSelector.html'" size="15" codeName="'loginName'" required="true"/>
		       <#--
		       <#assign buyingPersonName = ''/>
					<#if subscribe.buyingPerson?exists>
					 <#assign buyingPersonName = "${subscribe.buyingPerson.name}" />
			         <#elseif loginUser?exists>
			          <#assign buyingPersonName = "${loginUser.name}" />
					</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('Tpurchase.Person')}:</label></td>
	        	<td>
	        		<input type="text" name="buyingPerson.name" 
	        			class="underline"  value="${buyingPersonName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
		    		    <a onClick="buyUser_OpenDialog();">
		        		    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		        </td>
		        <#assign buyingPersonNameId = ''/>
				<#if subscribe.buyingPerson?exists>
				 <#assign buyingPersonNameId = "${subscribe.buyingPerson.id}" />
				  <#elseif loginUser?exists>
				  <#assign buyingPersonNameId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="buyingPerson.id" value="${buyingPersonNameId}" />
				-->
		   </tr>
		   
			 <@eam2008_FeeSourceTypeSelector toolingDevFlag="${toolingDevFlag?if_exists}"/>
		   <tr>
		   	<#--
		     <@pp.remotePicker label="'${action.getText('Tpurchase.Person')}'" name="'subscribe.buyingPerson'"
                    selectorName="'userSelectorAjax'" cssClass="'underline'" value="subscribe.buyingPerson"
                    popup="'${req.contextPath}/popup/userSelector.html'" size="15" codeName="'loginName'" required="true"/>
		   	-->	

		     <@pp.datePicker label="'${action.getText('Tpurchase.subscribeDate')}'" name="'subscribe.subscribeDate'"
	     				  value="'${(subscribe.subscribeDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     				  required="true" maxlength="10"/>
	     	  <@ww.textfield label="'${action.getText('Tpurchase.totalPrice')}'" name="'subscribe.totalPrice'" value="'${(subscribe.totalPrice?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'"   readonly="true" disabled="true" />
	     	</tr>
	     	<tr>
	     	 <#assign status=''/>
        	<#if subscribe.status?exists>
		       <#if '${subscribe.status}' == 'NEWPURCHASE'>
		       <#assign status = "${action.getText('NEWPURCHASE')}"/>
		       <#elseif '${subscribe.status}' == 'PURCHASING'>
		       <#assign status = "${action.getText('PURCHASING')}"/>
		       <#elseif '${subscribe.status}' == 'PURCHASEED'>
		       <#assign status = "${action.getText('PURCHASEED')}"/>
        	   </#if>
        	</#if>
	     	<@ww.textfield label="'${action.getText('status')}'" name="'subscribe.status'" value="'${status}'" cssClass="'underline'" readonly="true"/>
	     	 <@ww.textarea  label="'${action.getText('Tpurchase.reason')}'" 
	        	         name="'subscribe.reason'" 
	        	         value="'${subscribe.reason?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'"/>	 
		   </tr>
		</@inputTable>
		<@buttonBar>
		  <#if !(action.isReadOnly())>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return saveValidate();'"/>
		   </#if>
		    <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/tooling/prophase/business/listSubscribes.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
		   <#if (subscribe.id)?exists>
	      	<@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_toolingPurchaseBillDetailPdf('#{subscribe.id}')"/>
		    <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_toolingPurchaseBillDetailXls('#{subscribe.id}')"/>
	       </#if>
		</@buttonBar>
	</@ww.form>
    <script language="javascript">	
	  window.onload = function()  {  
	    <#if subscribe.department?exists>
	       document.forms[0].elements["department.id"].value=#{subscribe.department.id?if_exists};
	    <#elseif loginUser.department?exists>
	     document.getElementById("department.id").value = #{loginUser.department.id};
	   </#if>
		<#if subscribe.id?exists>
		var url = '${req.contextPath}/tooling/prophase/business/listPurchaseToolingPurchaseMakeDetails.html?subscribe.id=#{subscribe.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
	          document.all.frame.src = url;
		      document.getElementById("toolingMakeDetail").className = "selectedtab";
	    </#if>
	  <#if subscribe.typeStatus?exists>
	       document.forms[0].elements["typeStatus"].value='${subscribe.typeStatus?if_exists}';
	    </#if>
	   <#if subscribe.status?exists>
         	document.getElementById("status").value = '${subscribe.status}';
       </#if>
	    	<#--
	    /*
	     * 如果费用来源是计划内,则显示预算编号字段
	     */	
	    if(document.forms[0].elements["feeSource"].value=='IN_BUDGET'){
	     	  document.getElementById("budgetNoTitle").style.display='inline';
	     	  document.getElementById("budgetNoContent").style.display='inline';
			}
		}	
	
	    function changeBudgeNo() {
	        if(document.forms[0].elements["feeSource"].value=='IN_BUDGET'){
	     	  document.getElementById("budgetNoTitle").style.display='inline';
	     	  document.getElementById("budgetNoContent").style.display='inline';
		    }
		    if(document.forms[0].elements["feeSource"].value=='OUT_BUDGET'){
	     	  document.getElementById("budgetNoTitle").style.display='none';
	     	  document.getElementById("budgetNoContent").style.display='none';
		    }
		    return true;
		       -->
	  }
	  <#--
	       /*
	     	 * 申购人
	     	*/	
	      function buyUser_OpenDialog() {
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, buyUserSelectorHandler);
		  }
		  function buyUserSelectorHandler(result) {
		    if(null != result) {
		      document.forms["subscribe"].elements["buyingPerson.id"].value = result[0];
		      document.forms["subscribe"].elements["buyingPerson.name"].value = result[1];
		    } 
		 }
	-->
		    /*
		    * 申购单保存时的验证
		    */		  
	function  saveValidate(){
		//验证采购单
	     if(document.getElementById("subscribe.name").value==''){
	        alert('${action.getText('toolinginput.subscribe.name')}');
	        return false;
	     }
	     //验证采购人
	     if(document.getElementById("buyingPerson.id").value==''){
	        alert('${action.getText('subscribe.buyingPerson.name')}');
	        return false;
	     }
	     <#--
	     //验证采购单类型
	     if(document.getElementById("typeStatus").value==''){
	        alert('${action.getText('toolinginput.typeStatus')}');
	        return false;
	     }
	     -->
	     //验证规格长度
       if ('' != document.forms[0].elements["subscribe.name"].value) {
         if (!isValidLength(document.forms[0], "subscribe.name", null, 50)) {
           alert("${action.getText('toolingsubscribe.name.maxLength')}");
           return false;
         }
       }
       //验证费用来源
	   if (!eam2008_budgetDetail_sourceType_validate("${action.getText('select.sourceType')}")) {
	     return false;
	   }
	   
	   	//验证日期
	      if(document.getElementById("subscribe.subscribeDate").value==''){
           alert('${action.getText('input.prucahsebill.date')}');
           return false;	     
	      }  
	      var date=document.getElementById("subscribe.subscribeDate").value;
	      if(!isDate("subscribe.subscribeDate")){
	        alert('${action.getText('select.right.purchaseDate')}');
	        return false; 
	      }
	      <#--
	      if(!isDateLessEqualThenCurrent(date)){
			alert("${action.getText('afresh.subscribeDate')}");
		    return false;
		  }
		  -->
		  if(document.getElementById("subscribe.reason").value!=''){
		     if(!isValidLength(document.forms[0], "subscribe.reason", null, 250)){
				alert("${action.getText('subscribe.reason.length')}");
				return  false;
			   }
			}
		  if(document.getElementById("department.id").value=='-1'){//如果当前用户不属于任何部门，把部门Id至为空
             document.getElementById("department.id").value='';
             return true;	     
	      }  	
	       return true;
	     }	
	     function open_toolingPurchaseBillDetailPdf(id){
	       var subscribeid = id;
	       var url='${req.contextPath}/reports/tooling/toolingPurchaseBillDetail.pdf?subscribeid='+subscribeid;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	     }	
	     function open_toolingPurchaseBillDetailXls(id){
	       var subscribeid = id;
	       var url='${req.contextPath}/reports/tooling/toolingPurchaseBillDetail.xls?subscribeid='+subscribeid;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	     }       
	</script>
	<#if subscribe.id?exists>
		<ul id="beautytab">
			<li><a id="toolingMakeDetail"   onclick="activeTab(this);" href='${req.contextPath}/tooling/prophase/business/listPurchaseToolingPurchaseMakeDetails.html?subscribe.id=#{subscribe.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" class="selectedtab">${action.getText('toolingMakeDetail')}</a></li>
			<li><a id="sparePurchaseDetail"   onclick="activeTab(this);" href='${req.contextPath}/tooling/prophase/business/listPurchaseSparePurchaseDetails.html?subscribe.id=#{subscribe.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame">${action.getText('sparePurchaseDetail')}</a></li>
			<li><a id="repairMaintenanceDetail"   onclick="activeTab(this);" href='${req.contextPath}/tooling/prophase/business/listPurchaseRepairMaintenanceDetails.html?subscribe.id=#{subscribe.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame">${action.getText('repairMaintenanceDetail')}</a></li>
			<li><a id="techAlterDetail"   onclick="activeTab(this);" href='${req.contextPath}/tooling/prophase/business/listPurchaseTechAlterDetails.html?subscribe.id=#{subscribe.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame">${action.getText('techAlterDetail')}</a></li>
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>	

	