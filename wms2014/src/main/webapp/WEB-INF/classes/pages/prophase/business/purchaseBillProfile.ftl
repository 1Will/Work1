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
<#--$Id: purchaseBillProfile.ftl 11328 2008-03-15 09:39:30Z mwei $ -->

<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('purchaseBill.title')}">
	<@ww.form namespace="'/prophase'" name="'purchaseBill'" action="'savePurchaseBill'" method="'post'">
		<@ww.token name="savePurchaseBillToken"/>
		<@ww.hidden name="'purchaseBill.supplierName'" value=""/>
		<@ww.hidden name="'purchaseBill.telphone'" value=""/>
		<#if purchaseBill.id?exists> 
		<@ww.hidden name="'purchaseBill.id'" value="#{purchaseBill.id}"/>
		</#if>
         <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
         <@ww.hidden name="'purchaseType'" value=""/>
         <#if purchaseBill.status?exists>
         <@ww.hidden name="'status'" value="'${purchaseBill.status}'"/>
         </#if>
		<@inputTable>
			<tr>
			    <@ww.textfield label="'${action.getText('purchaseBill.billNo')}'" name="'purchaseBill.billNo'" value="'${purchaseBill.billNo?if_exists}'"  cssClass="'underline'" readonly="true"/>
			    <@ww.textfield label="'${action.getText('purchaseBill.name')}'" name="'purchaseBill.name'" value="'${purchaseBill.name?if_exists}'"  cssClass="'underline'" required="true"/>
			</tr>
			<tr>
			  <@eam2008_SupplierSelectorLessTr toolingDevFlag="${toolingDevFlag?if_exists}"/>
			    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false" required="true">
		        </@ww.select> 
			</tr>
			<tr>
		          <#assign buyerPersonName = ''/>
		        	<#if purchaseBill.buyer?exists>
					 <#assign buyerPersonName = "${purchaseBill.buyer.name}" />
					  <#elseif loginUser?exists>
			          <#assign buyerPersonName = "${loginUser.name}" />
					</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('buy.requestpeople')}:</label></td>
	        	<td>
	        		<input type="text" name="buyer.name" 
	        			class="underline"  value="${buyerPersonName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
		    		    <a onClick="buyUser_OpenDialog();">
		        		    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		        </td>
		        <#assign buyerPersonNameId = ''/>
				<#if purchaseBill.buyer?exists>
				 <#assign buyerPersonNameId = "${purchaseBill.buyer.id}" />
				  <#elseif loginUser?exists>
			          <#assign buyerPersonNameId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="buyer.id" value="${buyerPersonNameId}" />
				<@pp.datePicker label="'${action.getText('purchaseDate')}'" name="'purchaseBill.purchaseDate'"
					value="'${(purchaseBill.purchaseDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" required="true" size="15"/>
			</tr>
			<tr>
				<@eam2008_FeeSourceTypeSelector toolingDevFlag="${toolingDevFlag?if_exists}"/>
				</tr>
			<tr>
			    <@ww.select label="'${action.getText('typeStatus')}'"  
			                name="'typeStatus'" 
	    			        listKey="value" 
	    			        listValue="label" 
	    			        value="'${purchaseBill.typeStatus?if_exists}'"
	                        list="purchaseTypes"
	                        required="false" 
	                        emptyOption="true" 
	                        disabled="false" 
	                        required="true">
	    		</@ww.select>
	    		     	<#assign status=''/>
        	<#if purchaseBill.status?exists>
		       <#if '${purchaseBill.status}' == 'NEWSTATUS'>
		       <#assign status = "${action.getText('NEWSTATUS')}"/>
		       <#elseif '${purchaseBill.status}' == 'INSPECTING'>
		       <#assign status = "${action.getText('INSPECTING')}"/>
		       <#elseif '${purchaseBill.status}' == 'INSPECTED'>
		       <#assign status = "${action.getText('INSPECTED')}"/>
        	</#if>        	
        	</#if>
	    		<@ww.textfield label="'${action.getText('status')}'" name="'purchaseBill.status'" value="'${status}'" cssClass="'underline'" readonly="true"/>
		
			
				
	           </tr>
	       </tr>
	        <tr>
	           <@ww.textfield label="'${action.getText('supplierName')}'" name="'supplier.supplierName'" value="'${purchaseBill.supplierName?if_exists}'" cssClass="'underline'" />	
			    <@ww.textfield label="'${action.getText('supplierTelphone')}'" name="'supplier.telphone'" value="'${purchaseBill.telphone?if_exists}'"  cssClass="'underline'" />
	        </tr>
			<tr>
			    <@ww.textfield label="'${action.getText('contractMoney')}'" name="'totalPrice'" value="'${purchaseBill.totalPrice?if_exists}'" cssClass="'underline'"   readonly="true" disabled="true" />	
			    <@ww.textfield label="'${action.getText('alreadyPayOut')}'" name="'alreadyPayOut'" value="'${purchaseBill.alreadyPayOut?if_exists}'"  cssClass="'underline'"  readonly="true"/>
			</tr>
			<tr>
	        <@ww.textarea label="'${action.getText('contractMainClause')}'"  name="'purchaseBill.contractMainClause'" value="'${purchaseBill.contractMainClause?if_exists}'" rows="'3'" cols="'50'" />
	        <@ww.textarea label="'${action.getText('paymentWay')}'"  name="'purchaseBill.paymentWay'" value="'${purchaseBill.paymentWay?if_exists}'" rows="'3'" cols="'50'" />
	        </tr>
	           <tr>
			 	<tr><td colspan="6"><HR align="middle" size="1" width="95%" color="#999999" noshade /></td>
			 </tr>
			 
			      <tr>
	           <@ww.textfield label="'${action.getText('consigneeAdd')}'" name="'purchaseBill.consigneeAdd'" value="'${purchaseBill.consigneeAdd?if_exists}'" cssClass="'underline'" />	
			    <@ww.textfield label="'${action.getText('consigneeName')}'" name="'purchaseBill.consigneeName'" value="'${purchaseBill.consigneeName?if_exists}'"  cssClass="'underline'" />
	        </tr>
	             <tr>
	           <@ww.textfield label="'${action.getText('consigneeTel')}'" name="'purchaseBill.consigneeTel'" value="'${purchaseBill.consigneeTel?if_exists}'" cssClass="'underline'" />	
			    <@ww.textfield label="'${action.getText('consigneeFax')}'" name="'purchaseBill.consigneeFax'" value="'${purchaseBill.consigneeFax?if_exists}'"  cssClass="'underline'" />
	        </tr>
			 
	        <tr>
	           <@ww.textarea label="'${action.getText('comment')}'"  name="'purchaseBill.comment'" value="'${purchaseBill.comment?if_exists}'" rows="'3'" cols="'50'" />
	        </tr>
		</@inputTable>
		<@buttonBar>
		   <#if !(action.isReadOnly())>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validateSavePurchase();'"/>
		    <#if (purchaseBill.id)?exists>
		    <#--
	   	    	<@vbutton name="saveContinue"  class="button" value="${action.getText('saveContinue')}" onclick="return encodeUrl() "/>-->
	   	    	<@redirectButton value="${action.getText('saveContinue')}" url="${req.contextPath}/prophase/editPurchaseBill.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
	   	    </#if>
		  
		   </#if>
		   <#if report?if_exists=="report">
		      <@redirectButton name="back" value="${action.getText('back')}" 
		          url="${req.contextPath}/prophase/listPurchaseBillReoprt.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
		   <#else>
		      <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/prophase/listPurchaseBills.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
		   </#if>
		    <#if (purchaseBill.id)?exists>
	      	     <@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_purchaseBillPdf('#{purchaseBill.id}')"/>
		         <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_purchaseBillXls('#{purchaseBill.id}')"/>
	             <#if !(action.isReadOnly())>
	             <@vsubmit name="'submitRecord'" value="'${action.getText('sendsubmit')}'" />
	             </#if>
	        </#if>
		</@buttonBar>
	</@ww.form>
	  <script language="javascript">	
	  window.onload=function(){
	   <#if !(action.isReadOnly()) && purchaseBill.status!='NEWSTATUS'>
      	 	 document.forms[0].elements["save"].disabled="true";      	 	
        </#if>
	  
	   <#if loginUser.department?exists>
	     document.getElementById("department.id").value = #{loginUser.department.id};
	   </#if>
	   <#if purchaseBill.department?exists>
	     document.forms[0].elements["department.id"].value=#{purchaseBill.department.id?if_exists};
	   </#if>
	   <#if purchaseBill.status?exists>
	      document.forms[0].elements["status"].value='${purchaseBill.status}';
	   </#if>
	   
	   <#if purchaseBill.feeSource?exists> 
	   		<#else>
	   		document.getElementById("sourceType").value='IN_BUDGET';
	   		$('budgetNoTitle').style.display='inline';
			 $('budgetNoContent').style.display='inline';
	    </#if>
        <#if purchaseBill.typeStatus?exists>          
          document.forms[0].elements["typeStatus"].value='${purchaseBill.typeStatus}';   
           <#else>	       
	        document.forms[0].elements["typeStatus"].value='SPARE';        
        </#if>
	    <#if purchaseBill.id?exists>
		   var url = 'listPurchaseBillDetails.html?purchaseBill.id=#{purchaseBill.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
	       document.all.frame.src = url;
		   document.getElementById("details").className = "selectedtab";
	    </#if>
	    <#--<#if purchaseBill.submit>
		document.forms[0].elements["submitRecord"].disabled="true";
	    </#if>-->
	    <#if !(action.isReadOnly())>
	    <#if purchaseBill.id?exists>
	    <#if purchaseBill.submit==false>
		 document.forms[0].elements["submitRecord"].disabled="true";
		<#else>
		document.forms[0].elements["submitRecord"].disabled="false";
		</#if>
		</#if>
		</#if>
	   <#-- <#if !purchaseBill.id?exists>
		document.forms[0].elements["submitRecord"].disabled="true";
	   </#if>-->
	  }
	     
      //选择采购人
      function buyUser_OpenDialog() {
	    var url = "${req.contextPath}/popup/userSelector.html";
	    popupModalDialog(url, 800, 600, buyUserSelectorHandler);
	  }
	  function buyUserSelectorHandler(result) {
	    if(null != result) {
	      document.forms["purchaseBill"].elements["buyer.id"].value = result[0];
	      document.forms["purchaseBill"].elements["buyer.name"].value = result[1];
	    } 
	  }
		//保存时的验证
		function validateSavePurchase(){
		 if(document.getElementById("department.id").value=='-1'){//如果当前用户不属于任何部门，把部门Id至为空
             document.getElementById("department.id").value='';
             return true;	     
	      } 
	      //验证采购单名称
	     if(document.getElementById("purchaseBill.name").value==''){
	        alert('${action.getText('input.purchaseBill.name')}');
	        return false;
	     }
	     //验证采购单类型
	     if(document.getElementById("typeStatus").value==''){
	        alert('${action.getText('inputBill.typeStatus')}');
	        return false;
	     }
	      //验证费用来源
	       if(document.getElementById("sourceType").value=='') {
	           alert('${action.getText('select.sourceType')}');
	           return false;
	       }
	       //获取供应商姓名和电话
	      if('' != document.getElementById("supplier.supplierName").value){
	          document.getElementById("purchaseBill.supplierName").value=document.getElementById("supplier.supplierName").value;
	      } 	
	      if('' !=document.getElementById("supplier.telphone").value){
	        
	         document.getElementById("purchaseBill.telphone").value=document.getElementById("supplier.telphone").value;
	        }
	       if(document.getElementById("purchaseBill.name").value=='') {
	           alert('${action.getText('purchase.not.null')}');
	           return false;
	       }
	      if ('' != document.forms[0].elements["purchaseBill.name"].value) {
            if (!isValidLength(document.forms[0], "purchaseBill.name", null, 50)) {
             alert("${action.getText('purchaseBill.name.maxLength')}");
            return false;
            }  
         }
          if(document.getElementById("department.id").value=='') {
	           alert('${action.getText('department.not.null')}');
	           return false;
	       }
          
	    //验证采购日期
	    if(document.getElementById("purchaseBill.purchaseDate").value==''){
	        alert('${action.getText('purchaseBill.purchaseDate.not.null')}');
	        return false;
	      }
	  
	      var date=document.getElementById("purchaseBill.purchaseDate").value;
		  if(!isDate("purchaseBill.purchaseDate")){
		    alert("${action.getText('select.right.purchaseDate')}");
			return false;
		  }
		  <#--
		if(!isDateLessEqualThenCurrent(date)){
			alert("${action.getText('afresh.purchaseDate')}");
		    return false;
		  }
		-->
	        if(document.getElementById("supplier.name").value=='') {
	           alert('${action.getText('supplier.not.null')}');
	           return false;
	       }
	 
	       if(document.getElementById("purchaseBill.contractMainClause").value!=''){
		   if(!isValidLength(document.forms[0], "purchaseBill.contractMainClause", null, 250)){
				alert("${action.getText('purchaseBill.contractMainClause.length')}");
				return  false;
			   }
			}
		 if(document.getElementById("purchaseBill.paymentWay").value!=''){
		   if(!isValidLength(document.forms[0], "purchaseBill.paymentWay", null, 250)){
				alert("${action.getText('purchaseBill.paymentWay')}");
				return  false;
			   }
			}
		 if(document.getElementById("purchaseBill.comment").value!=''){
		   if(!isValidLength(document.forms[0], "purchaseBill.comment", null, 250)){
				alert("${action.getText('purchaseBill.comment')}");
				return  false;
			   }
			}
	       
	       return true;
	     }	    
		function open_purchaseBillPdf(id){
	       var purchaseBillid = id;
	       var url='${req.contextPath}/reports/device/listPurchaseBillDetail.pdf?purchaseBillid='+purchaseBillid;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	     }	
	     function open_purchaseBillXls(id){
	       var purchaseBillid = id;
	       var url='${req.contextPath}/reports/device/listPurchaseBillDetail.xls?purchaseBillid='+purchaseBillid;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	     }    
	</script> 
	<#if purchaseBill.id?exists>
		<ul id="beautytab">
			<li><a id="details" onclick="activeTab(this);"  
				href="listPurchaseBillDetails.html?purchaseBill.id=#{purchaseBill.id}&purchaseType=${purchaseBill.typeStatus?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('purchaseBillDetail')}</a>
			</li>
			<li><a id="attachments" onclick="activeTab(this);"  
				href="listPurchaseEnclosure.html?purchaseBill.id=#{purchaseBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('attachments')}</a>
			</li>
			<li><a id="inspectstandard" onclick="activeTab(this);"
				href="listInspectStandards.html?purchaseBill.id=#{purchaseBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('checkProcess')}</a>
			</li>
			<li><a id="middleCheck" onclick="activeTab(this);"  
				href="listMiddleCheck.html?purchaseBill.id=#{purchaseBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('middleCheck')}</a>
			</li>
			<li><a id="payDetails" onclick="activeTab(this);"  
				href="listPayDetails.html?purchaseBill.id=#{purchaseBill.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('payDetails')}</a>
			</li>
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>
  