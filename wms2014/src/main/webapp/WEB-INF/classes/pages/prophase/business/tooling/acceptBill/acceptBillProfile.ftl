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
<#--$Id: purchaseBillProfile.ftl 11311 2008-03-13 13:19:59Z mwei $ -->

<#include "../../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('acceptBill.title')}">
	<@ww.form namespace="'/tooling/acceptBillSelector'" name="'Bill'" action="'saveAcceptBill'" method="'post'">
	<@ww.token name="saveAcceptBillToken"/>
		<#if acceptBill.id?exists> 
		  <@ww.hidden name="'acceptBill.id'" value="#{acceptBill.id}"/>
		</#if>
		 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#if acceptBill.purchaseBillDtl?exists>
		  <@ww.hidden name="'purchaseBillDtl.id'" value="#{acceptBill.purchaseBillDtl.id?if_exists}"/>
		<#else>
		  <@ww.hidden name="'purchaseBillDtl.id'" value=""/>
		</#if>
        <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
		<@inputTable>
		<#--<tr>
		 <@vbutton name="'choose'"  class="button" value="${action.getText('chooseCantPurchasesub')}" onclick="open_purchaseBillDtlDialog()"/>
		 <#if acceptBill.id?exists>
		 <@redirectButton value="${action.getText('stablishAccount')}" url="${req.contextPath}/asset/tooling/editToolingAccount.html?acceptBill.id=#{acceptBill.id}"/>
		  </#if>
		</tr>-->
		<tr>
			 <@ww.textfield label="'${action.getText('acceptBill.billNo')}'" name="'acceptBill.acceptBillNo'" value="'${acceptBill.acceptBillNo?if_exists}'"  cssClass="'underline'" readonly="true"/>
			 <@ww.textfield label="'${action.getText('acceptBill.name')}'" name="'acceptBill.acceptBilName'" value="'${acceptBill.acceptBilName?if_exists}'"  cssClass="'underline'" required="true" size="15"/>
		</tr>
		<tr>
			    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="true" required="true" disabled="false">
		        </@ww.select> 
		       <#-- <#assign acceptPeoplePersonName = ''/>
		        	<#if acceptBill.acceptPeople?exists>
					 <#assign acceptPeoplePersonName = "${acceptBill.acceptPeople.name}" />
					   <#elseif loginUser?exists>
					    <#assign acceptPeoplePersonName = "${loginUser.name}"/>
					</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('buy.requestpeople')}:</label></td>
	        	<td>
	        		<input type="text" name="acceptPeople.name" 
	        			class="underline"  value="${acceptPeoplePersonName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
		    		    <a onClick="buyUser_OpenDialog();">
		        		    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		        </td>
		        <#assign acceptPeoplePersonNameId = ''/>
				<#if acceptBill.acceptPeople?exists>
				 <#assign acceptPeoplePersonNameId = "${acceptBill.acceptPeople.id}" />
				 <#elseif loginUser?exists>
				<#assign acceptPeoplePersonNameId = "${loginUser.id}"/>
				</#if>
				<input type="hidden" name="acceptPeople.id" value="${acceptPeoplePersonNameId}" />-->
		          <@pp.remotePicker label="'${action.getText('buy.requestpeople')}'" name="'acceptBill.people'"
                    selectorName="'userSelectorAjax'" cssClass="'underline'" value="acceptBill.acceptPeople"
                    popup="'${req.contextPath}/popup/userSelector.html'" size="15" codeName="'loginName'" required="true"/>
		</tr>
			<tr>
			     <@ww.textfield label="'${action.getText('acceptPlace')}'" name="'acceptBill.acceptPlace'" value="'${acceptBill.acceptPlace?if_exists}'" cssClass="'underline'" />	
				 <@pp.datePicker label="'${action.getText('acceptBill.acceptDate')}'" name="'acceptBill.acceptDate'"
				  value="'${(acceptBill.acceptDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15"/>
	        </tr>
	       <#-- <tr>
			    <@ww.textfield label="'${action.getText('name')}'" name="'acceptBill.merchandiseName'" value="'${acceptBill.merchandiseName?if_exists}'"  cssClass="'underline'" required="true"/>
			    <@ww.textfield label="'${action.getText('model')}'" name="'acceptBill.model'" value="'${acceptBill.model?if_exists}'"  cssClass="'underline'" />
			</tr>
			<tr>
			    <@ww.textfield label="'${action.getText('specification')}'" name="'acceptBill.specification'" value="'${acceptBill.specification?if_exists}'"  cssClass="'underline'" />
			    <@ww.textfield label="'${action.getText('amount')}'" name="'acceptBill.amounts'" value="'${acceptBill.amounts?if_exists}'"  cssClass="'underline'" required="true">
				</@ww.textfield>
			</tr>-->
	        <tr>
	           <@eam2008_SupplierSelectorLessTr toolingDevFlag="${toolingDevFlag?if_exists}"/>
	           <@ww.textfield label="'${action.getText('devicesupplier')}'"  name="'acceptBill.supplierRepresentative'" value="'${acceptBill.supplierRepresentative?if_exists}'"  cssClass="'underline'" />
	        </tr>
	        <tr>
	         <@ww.textarea  label="'${action.getText('acceptMemo')}'" 
	        	         name="'acceptBill.memo'" 
	        	         value="'${acceptBill.memo?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'"/>
        </tr>
	        </tr>
		</@inputTable>
		<@buttonBar>
		 <#if !(action.isReadOnly())>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return saveVlidate()'"/>
		 </#if>
		    <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/tooling/acceptBillSelector/listAcceptBills.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
	        <#if (acceptBill.id)?exists>
	      	<@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_toolingAcceptBillDetailPdf('#{acceptBill.id}')"/>
		    <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_toolingAcceptBillDetailXls('#{acceptBill.id}')"/>
	         <#if !(action.isReadOnly())>
	             <@vsubmit name="'submitRecord'" value="'${action.getText('sendsubmit')}'" />
	        </#if>
	       </#if>
		</@buttonBar>
	</@ww.form>
  <script language="javascript">	
  window.onload=function(){
      <#if loginUser.department?exists>
	     document.getElementById("department.id").value = #{loginUser.department.id};
	   </#if>
	    <#if  acceptBill.department?exists>
	     document.getElementById("department.id").value = #{ acceptBill.department.id};
	   </#if>
      <#if acceptBill.id?exists>
		   var url = 'listAcceptToolingMakeDetails.html?acceptBill.id=#{acceptBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}';
	          document.all.frame.src = url;
		      document.getElementById("toolingMakeDetail").className = "selectedtab";
	  </#if>
	 
	   	<#if acceptBill.id?exists>
	    <#if acceptBill.submit==false>
		 document.forms[0].elements["submitRecord"].disabled="true";
		<#else>
		document.forms[0].elements["submitRecord"].disabled="false";
		</#if>
		</#if>
		
  }
  
  
  
    function buyUser_OpenDialog() {//选择用户的查询页面 
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, buyUserSelectorHandler);
		  }
		  function buyUserSelectorHandler(result) {
		    if(null != result) {
		      document.forms[0].elements["acceptPeople.id"].value = result[0];
		      document.forms[0].elements["acceptPeople.name"].value = result[1];
		    } 
		}
	<#--function open_purchaseBillDtlDialog(){
	   var url = '${req.contextPath}/tooling/acceptBillSelector/listPurchaseBillSelector.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
	   popupModalDialog(url, gw, gh, refresh_purchase_information);
	}
	function  refresh_purchase_information(result){
	
	  if(null!=result){
	      document.forms[0].elements["purchaseBillDtl.id"].value = result[0]; 
          document.forms[0].elements["acceptBill.merchandiseName"].value = result[1]; 
          document.forms[0].elements["acceptBill.model"].value = result[2]; 
          document.forms[0].elements["acceptBill.specification"].value = result[3]; 
          document.forms[0].elements["acceptBill.amounts"].value = result[4];
          document.forms[0].elements["supplier.id"].value = result[5]; 
          document.forms[0].elements["supplier.name"].value = result[6]; 
          document.forms[0].elements["supplier.supplierNo"].value = result[7]; 
	  }
	}-->
function open_stablishAccount(){
var url = '${req.contextPath}/asset/tooling/editToolingAccount.html';
}	
 function validateDepartment() {
       var dept = document.forms[0].elements["department.id"].value;
       if (dept =='' || dept == '-1') {
         alert("${action.getText('department.id.requried')}");
         return false;
       }
      return true;
     }	
   function saveVlidate(){
       if(document.getElementById("acceptBill.acceptBilName").value=='' ){
         alert('${action.getText('acceptBill.acceptBilName.not.null')}');
         return false;
       }else{
		   if(!isValidLength(document.forms[0], "acceptBill.acceptBilName", null, 50)){
				alert("${action.getText('acceptBill.acceptBilName.length')}");
				return  false;
			   }
       }
          if(!validateDepartment()){
             return false;
          }
          
     <#-- if(document.getElementById("acceptBill.name").value=='' ){
         alert('${action.getText('acceptBill.name.not.null')}');
         return false;
       }else{
		   if(!isValidLength(document.forms[0], "acceptBill.name", null, 50)){//验证长度
				alert("${action.getText('acceptBill.name.length')}");
				return  false;
			   }
       }
       if(document.getElementById("acceptBill.merchandiseName").value=='' ){
         alert('${action.getText('acceptBill.merchandiseName.not.null')}');
         return false;
       }else{
		   if(!isValidLength(document.forms[0], "acceptBill.merchandiseName", null, 50)){
				alert("${action.getText('acceptBill.merchandiseName.length')}");
				return  false;
			   }
       }
      var number = document.forms[0].elements["acceptBill.amounts"].value;
       if ('' == number) {            //验证是否为空
         alert("${action.getText('number.requried')}");
         return false;
       } else if (!isDoubleNumber("acceptBill.amounts")){   //验证格式
         alert("${action.getText('number.format.error')}");
         return false;
       } else if (!isNumberBetweenBoolen(number, 1000000001, 0)){ //验证范围
         alert("${action.getText('number.format.error')}");
         return false;
       }

         -->
        //验证采购人
      if(document.forms[0].elements["acceptBill.people.id"].value==""){
         alert('${action.getText('buyPeople.name.null')}');
         return false;
      }
 	   	//验证日期
 	  if(document.getElementById("acceptBill.acceptDate").value==''){

	  }else{
      var date=document.getElementById("acceptBill.acceptDate").value;
      if(!isDate("acceptBill.acceptDate")){
        alert('${action.getText('select.right.acceptBill.acceptDate')}');
        return false; 
      	}	 
      } 
            
      if(document.getElementById("supplier.supplierNo").value=='' ){
         alert('${action.getText('supplier.supplierNo.not.null')}');
         return false;
       } 
      if(document.getElementById("acceptBill.memo").value!=''){
		if(!isValidLength(document.forms[0], "acceptBill.memo", null, 250)){
			alert("${action.getText('acceptBill.memo.length')}");
			return  false;
			 }
		}
       return true;
      
   }
   	     function open_toolingAcceptBillDetailPdf(id){
	       var acceptid = id;
	       var url='${req.contextPath}/reports/tooling/toolingAcceptBillDetail.pdf?acceptid='+acceptid;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	     }	
	     function open_toolingAcceptBillDetailXls(id){
	       var acceptid = id;
	       var url='${req.contextPath}/reports/tooling/toolingAcceptBillDetail.xls?acceptid='+acceptid;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	     }    
	     
   </script>     	
	<#if acceptBill.id?exists>
		<ul id="beautytab">
			<li><a id="toolingMakeDetail" onclick="activeTab(this);"  
				href="listAcceptToolingMakeDetails.html?acceptBill.id=#{acceptBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('工装制作明细')}</a>
			</li>
			<li><a id="sparePurchaseDetail" onclick="activeTab(this);"  
				href="listSparePurchaseDetails.html?acceptBill.id=#{acceptBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('备件采购明细')}</a>
			</li>
			<li><a id="repareManintenDetail" onclick="activeTab(this);"  
				href="listRepairMaintenanceDetails.html?acceptBill.id=#{acceptBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('维修保养明细')}</a>
			</li>
			<li><a id="TECHALTER" onclick="activeTab(this);"  
				href="listAcceptTechAlterDetail.html?acceptBill.id=#{acceptBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('技术改造明细')}</a>
			</li>
			<li><a id="attachments" onclick="activeTab(this);"  
				href="listAcceptBillEnclosure.html?acceptBill.id=#{acceptBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('acceptBillfile')}</a>
			</li>
			<#--<li><a id="inspectstandard" onclick="activeTab(this);"
				href="listSpartParts.html?acceptBill.id=#{acceptBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('spartPart')}</a>
			</li>-->
			
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>
  