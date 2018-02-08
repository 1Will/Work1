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

<#include "../../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('purchaseContractProfile.title')}">
	<@ww.form namespace="'/toooling/purchaseContract'" name="'purchaseBill'" action="'savePurchaseBill'" method="'post'">
		<@ww.token name="savePurchaseBillToken"/>
		<@ww.hidden name="'purchaseBill.supplierName'" value=""/>
		<@ww.hidden name="'purchaseBill.telphone'" value=""/>
		<@ww.hidden name="'purchaseContractType'" value=""/>
		<#if purchaseBill.id?exists> 
		<@ww.hidden name="'purchaseBill.id'" value="#{purchaseBill.id}"/>
		</#if>
		 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
         
         <#if purchaseBill.status?exists>
         <@ww.hidden name="'status'" value="'${purchaseBill.status}'"/>
         </#if>
		<@inputTable>
			<tr>
			    <@ww.textfield label="'${action.getText('purchaseContract.billNo')}'" name="'purchaseBill.billNo'" value="'${purchaseBill.billNo?if_exists}'"  cssClass="'underline'" readonly="true"/>
			    <@ww.textfield label="'${action.getText('purchaseContract.name')}'" name="'purchaseBill.name'" value="'${purchaseBill.name?if_exists}'"  cssClass="'underline'" required="true"/>
			</tr>
			<tr>
			    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false" required="true">
		        </@ww.select> 
		          <#assign buyerPersonName = ''/>
		        	<#if purchaseBill.buyer?exists>
					 <#assign buyerPersonName = "${purchaseBill.buyer.name}" />
					  <#elseif loginUser?exists>
			          <#assign buyerPersonName = "${loginUser.name}" />
					</#if>
		        <@pp.remotePicker label="'${action.getText('buy.requestpeople')}'" name="'buyer'"
                    selectorName="'userSelectorAjax'" cssClass="'underline'" value="purchaseBill.buyer"
                    popup="'${req.contextPath}/popup/userSelector.html'" size="15" codeName="'loginName'" required="true"/>
		      <#--  <#assign buyerPersonNameId = ''/>
				<#if purchaseBill.buyer?exists>
				 <#assign buyerPersonNameId = "${purchaseBill.buyer.id}" />
				  <#elseif loginUser?exists>
			          <#assign buyerPersonNameId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="buyer.id" value="${buyerPersonNameId}" />
			</tr>
			
			<tr>
				<@ww.select label="'${action.getText('feeSourceType')}'"  name="'feeSource'"  
	    			     listKey="realCode"  listValue="value" list="feeSourceTypes"  emptyOption="true"  onchange="'changeBudgeNo()'"  disabled="false" required="true">
	    		</@ww.select>
	             <td id="budgetNoTitle" align="right" valign="top" style="display:none">
	 	  	        <span class="required"></span>
		 	  	<label class="label">${action.getText('subscrible.budgetNo')}:</label></td>
		        <td id="budgetNoContent" style="display:none">
		        	<input type="text" name="purchaseBill.budgetNo" 
		        			class="underline"  value="${purchaseBill.budgetNo?if_exists}"  maxlength="150" size="20"/>
			    </td>
			</tr>
			-->
			<tr>
			<tr>
		</tr>
			</tr>
			 <@eam2008_FeeSourceTypeSelector toolingDevFlag="${toolingDevFlag?if_exists}"/>
			<tr>
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
		
				<@pp.datePicker label="'${action.getText('purchaseDate')}'" name="'purchaseBill.purchaseDate'"
					value="'${(purchaseBill.purchaseDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" required="true" size="15"/>
	        </tr>
	        <tr>
	           <@pp.remotePicker label="'${action.getText('supplier.no')}'" name="'supplier'"
                    selectorName="'supplierSelectorAjax'" cssClass="'underline'" value="purchaseBill.supplier"
                    popup="'${req.contextPath}/popup/supplierSelector.html?toolingDevFlag=TOOLING'" size="15" codeName="'supplierNo'"  required="true"/>
	           <@ww.textfield label="'${action.getText('supplierName')}'" name="'supplier.supplierName'" value="'${purchaseBill.supplierName?if_exists}'" cssClass="'underline'" />	
			<tr>
			<tr>
			    <@ww.textfield label="'${action.getText('supplierTelphone')}'" name="'supplier.telphone'" value="'${purchaseBill.telphone?if_exists}'"  cssClass="'underline'" />
	        
			    <@ww.textfield label="'${action.getText('contractMoney')}'" name="'totalPrice'" value="'${(purchaseBill.totalPrice?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'"   readonly="true" disabled="true" />	
			</tr>
			<tr>
			    <@ww.textfield label="'${action.getText('alreadyPayOut')}'" name="'alreadyPayOut'" value="'${(purchaseBill.alreadyPayOut?string('#,###,##0.00'))?if_exists}'"  cssClass="'underline'"  readonly="true"/>
			
	        <@ww.textarea label="'${action.getText('contractMainClause')}'"  name="'purchaseBill.contractMainClause'" value="'${purchaseBill.contractMainClause?if_exists}'" rows="'3'" cols="'50'" />
	       </tr>
	       <tr>
	        <@ww.textarea label="'${action.getText('paymentWay')}'"  name="'purchaseBill.paymentWay'" value="'${purchaseBill.paymentWay?if_exists}'" rows="'3'" cols="'50'" />
	       	<@ww.textarea label="'${action.getText('comment')}'"  name="'purchaseBill.comment'" value="'${purchaseBill.comment?if_exists}'" rows="'3'" cols="'50'" />
	        </tr>
		</@inputTable>
		
		<@buttonBar>
		 <#if !(action.isReadOnly())>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validateSavePurchase();'"/>
		  </#if>
		    <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/toooling/purchaseContract/listPurchaseBills.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
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
	   <#if loginUser.department?exists>
	     getObjByNameRe("department.id").value = #{loginUser.department.id};
	   </#if>
	    <#if purchaseBill.department?exists>
	       document.forms[0].elements["department.id"].value=#{purchaseBill.department.id?if_exists};
	     </#if>
	    <#if purchaseBill.status?exists>
	       document.forms[0].elements["status"].value='${purchaseBill.status}';
	    </#if>
         <#if purchaseBill.typeStatus?exists>
          document.forms[0].elements["purchaseContractType"].value='${purchaseBill.typeStatus}';
        </#if>
        <#if !(action.isReadOnly())>
	   	<#if purchaseBill.id?exists>
	    <#if purchaseBill.submit==false>
		 document.forms[0].elements["submitRecord"].disabled="true";
		<#else>
		document.forms[0].elements["submitRecord"].disabled="false";
		</#if>
		</#if>
		</#if>
	     <#if purchaseBill.id?exists>
	      initNav(4);
		   var url = '${req.contextPath}/toooling/purchaseContract/listToolingMakeDetailPurchaseConcat.html?purchaseBill.id=#{purchaseBill.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
	          document.all.frame.src = url;
		      getObjByNameRe("toolingMakeDetail").className = "selectedtab";
		       getObjByNameRe("toolingMakeDetail").className = "selectedtab";
		      try{getObjByNameRe("navl").style.visibility="hidden"}catch(e){};
	    </#if>

	 }
		function open_purchaseBillPdf(id){
	       var purchaseBillid = id;
	       var url='${req.contextPath}/reports/tooling/listToolingPurchaseBillDetail.pdf?purchaseBillid='+purchaseBillid;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	     }	
	     function open_purchaseBillXls(id){
	       var purchaseBillid = id;
	       var url='${req.contextPath}/reports/tooling/listToolingPurchaseBillDetail.xls?purchaseBillid='+purchaseBillid;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
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
		 if(getObjByNameRe("department.id").value=='-1'){//如果当前用户不属于任何部门，把部门Id至为空
             getObjByNameRe("department.id").value='';
             return true;	     
	      } 
	      if(getObjByNameRe("purchaseBill.name").value=='') {
	           alert('${action.getText('purchaseContract.not.null')}');
	           return false;
	       }
	       if ('' != document.forms[0].elements["purchaseBill.name"].value) {
            if (!isValidLength(document.forms[0], "purchaseBill.name", null, 50)) {
             alert("${action.getText('purchaseContract.name.maxLength')}");
            return false;
            }  
         }
         if(getObjByNameRe("department.id").value=='') {
	           alert('${action.getText('department.not.null')}');
	           return false;
	       }
	       if(getObjByNameRe("buyer.id").value=='') {
	           alert('${action.getText('purchasePerson.not.null')}');
	           return false;
	       }
	      
	      //获取供应商姓名和电话
	      if('' != getObjByNameRe("supplier.supplierName").value){
	          getObjByNameRe("purchaseBill.supplierName").value=getObjByNameRe("supplier.supplierName").value;
	      } 	
	      if('' !=getObjByNameRe("supplier.telphone").value){
	        
	         getObjByNameRe("purchaseBill.telphone").value=getObjByNameRe("supplier.telphone").value;
	        }
	       <#--
         if(getObjByNameRe("feeSource").value=='') {
	           alert('${action.getText('input.select.feeSource')}');
	           return false;
	       }
	       -->
	      //验证费用来源
	      //验证费用来源
	   if (!eam2008_budgetDetail_sourceType_validate("${action.getText('select.sourceType')}")) {
	     return false;
	   }
	   
	   	//验证日期
	      if(getObjByNameRe("purchaseBill.purchaseDate").value==''){
           alert('${action.getText('input.prucahsebill.date')}');
           return false;	     
	      }  
	      var date=getObjByNameRe("purchaseBill.purchaseDate").value;
	      if(!isDate("purchaseBill.purchaseDate")){
	        alert('${action.getText('select.right.purchaseDate')}');
	        return false; 
	      }	   
	      
	        if(getObjByNameRe("supplier.id").value=='') {
	           alert('${action.getText('supplier.not.null')}');
	           return false;
	       }
	 	
	       if(getObjByNameRe("purchaseBill.contractMainClause").value!=''){
		   if(!isValidLength(document.forms[0], "purchaseBill.contractMainClause", null, 250)){
				alert("${action.getText('purchaseBill.contractMainClause.length')}");
				return  false;
			   }
			}
		 if(getObjByNameRe("purchaseBill.paymentWay").value!=''){
		   if(!isValidLength(document.forms[0], "purchaseBill.paymentWay", null, 250)){
				alert("${action.getText('purchaseBill.paymentWay')}");
				return  false;
			   }
			}
		 if(getObjByNameRe("purchaseBill.comment").value!=''){
		   if(!isValidLength(document.forms[0], "purchaseBill.comment", null, 250)){
				alert("${action.getText('purchaseBill.comment')}");
				return  false;
			   }
			}
	       
	       return true;
	     }	
	     
<#if  purchaseBill.id?exists>
    var defNavItem=4;
	var navItem = new Array();
	navItem[1] = '<a id="toolingMakeDetail" onclick="activeTab(this);getCurrentNavItem(1)"  href="${req.contextPath}/toooling/purchaseContract/listToolingMakeDetailPurchaseConcat.html?purchaseBill.id=#{purchaseBill.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('工装制作明细')}</a>';
	navItem[2] = '<a id="sparePurchaseDetail" onclick="activeTab(this);getCurrentNavItem(2)" href="${req.contextPath}/toooling/purchaseContract/listSparePurchaseDetailPurchaseConcat.html?purchaseBill.id=#{purchaseBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('备件采购明细')}</a>';
	navItem[3] = '<a id="repairMaintenanceDetail" onclick="activeTab(this);getCurrentNavItem(3)"   href="${req.contextPath}/toooling/purchaseContract/listRepairMaintenancePurchaseConcat.html?purchaseBill.id=#{purchaseBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('维修保养明细')}</a>';
	navItem[4] = '<a id="techAlterDetail" onclick="activeTab(this);getCurrentNavItem(4)"  href="${req.contextPath}/toooling/purchaseContract/listTechAlterDetailsPurchaseConcat.html?purchaseBill.id=#{purchaseBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('技术改造明细')}</a>';	 
	navItem[5] = '<a id="attachments" onclick="activeTab(this);getCurrentNavItem(5)" href="${req.contextPath}/toooling/purchaseContract/listPurchaseEnclosure.html?purchaseBill.id=#{purchaseBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('attachmentss')}</a>';  
	navItem[6] = '<a id="inspectstandard" onclick="activeTab(this);getCurrentNavItem(6)" href="${req.contextPath}/toooling/purchaseContract/listInspectStandards.html?purchaseBill.id=#{purchaseBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('checkProcess')}</a>';	 
	navItem[7] = '<a id="middleCheck" onclick="activeTab(this);getCurrentNavItem(7)"  href="${req.contextPath}/toooling/purchaseContract/listMiddleCheck.html?purchaseBill.id=#{purchaseBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('middleCheck')}</a>';
    navItem[8] = '<a id="payDetails" onclick="activeTab(this);getCurrentNavItem(8)"  href="${req.contextPath}/toooling/purchaseContract/listPayDetails.html?purchaseBill.id=#{purchaseBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('payDetails')}</a>';
	var navItemId = new Array();
	navItemId[1] = "toolingMakeDetail";
	navItemId[2] = "sparePurchaseDetail";
	navItemId[3] = "repairMaintenanceDetail";
	navItemId[4] = "techAlterDetail";
	navItemId[5] = "attachments";
	navItemId[6] = "inspectstandard";
	navItemId[7] = "middleCheck";
	navItemId[8] = "payDetails";
	function getCurrentNavItem(item) {
		 curPos = item;
     }
	function initNav(num) {
		for (var i = 1; i <= num; i ++) {
			getObjByNameRe(i).innerHTML = navItem[i];
		}
	}
	var curPos=1;
	function scrollNav(pos) {
		var c = 1;	
		var temp =0;
		 for(var id = pos;id<=12;id++, c++){
			  if(c<=4){
				  if (c==1){
				  	if (pos>=5){
				  	  temp = id-3;
					  getObjByNameRe(c).innerHTML = navItem[id-3];
					}else {
					  getObjByNameRe(c).innerHTML = navItem[c];	
					}
				  }else{
				  	if (pos>=5){
				  	  temp = temp+1;
					  getObjByNameRe(c).innerHTML = navItem[temp];
					}else {
					  getObjByNameRe(c).innerHTML = navItem[c];	
					}
				  }
			  }
		 }  
		 <#if purchaseBill.id?exists>
     		document.all.frame.src = getObjByNameRe(navItemId[pos]).href;
	 		getObjByNameRe(navItemId[pos]).className = "selectedtab";
	   	  </#if>
		 if (pos<=1){
			 pos=1; 
			 try{getObjByNameRe("navl").style.visibility="hidden"}catch(e){};
			 try{getObjByNameRe("navr").style.visibility="visible"}catch(e){};
		 }else if(pos>=8){
			 try{getObjByNameRe("navl").style.visibility="visible"}catch(e){};
			 try{getObjByNameRe("navr").style.visibility="hidden"}catch(e){};
		 }else{
			 try{getObjByNameRe("navl").style.visibility="visible"}catch(e){};
			 try{getObjByNameRe("navr").style.visibility="visible"}catch(e){};
		 }
	 
	 	curPos = pos;
	 	}
	  </#if>    
	</script> 
	<#if purchaseBill.id?exists>
	<ul id="beautytab">
	<li id="navl"><a href="javascript:scrollNav(curPos-1);" target="_self"><img src="${req.contextPath}/stylesheets/images/return_left.gif" alt="${action.getText('lastPage')}" border="0" /></a></li>
    <li id="1"/>
    <li id="2"/>
    <li id="3"/>
    <li id="4"/>
    <li id="navr"><a href="javascript:scrollNav(curPos+1);" target="_self"><img src="${req.contextPath}/stylesheets/images/return_right.gif" alt="${action.getText('nextPage')}" border="0" /></a></li>
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>
  