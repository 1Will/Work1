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

<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('acceptBill.title')}">
	<@ww.form namespace="'/accept'" name="'Bill'" action="'saveAcceptBill'" method="'post'">
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
		<tr>
		  <@vbutton name="'choose'"  class="button" value="${action.getText('choosePurchasesub')}" onclick="open_purchaseBillDtlDialog()"/>
		</tr>
		<tr>
			 <@ww.textfield label="'${action.getText('acceptBill.billNo')}'" name="'acceptBill.acceptBillNo'" value="'${acceptBill.acceptBillNo?if_exists}'"  cssClass="'underline'" readonly="true"/>
			 <@ww.textfield label="'${action.getText('acceptBill.name')}'" name="'acceptBill.acceptBilName'" value="'${acceptBill.acceptBilName?if_exists}'"  cssClass="'underline'" required="true" size="15"/>
		</tr>
		<tr>
			    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="true" required="true" disabled="false">
		        </@ww.select> 
		       <#--   <#assign acceptPeoplePersonName = ''/>
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
	        <tr>
			    <@ww.textfield label="'${action.getText('name')}'" name="'acceptBill.merchandiseName'" value="'${acceptBill.merchandiseName?if_exists}'"  cssClass="'underline'" required="true"/>
			    <@ww.textfield label="'${action.getText('model')}'" name="'acceptBill.model'" value="'${acceptBill.model?if_exists}'"  cssClass="'underline'" />
			</tr>
			<tr>
			    <@ww.textfield label="'${action.getText('specification')}'" name="'acceptBill.specification'" value="'${acceptBill.specification?if_exists}'"  cssClass="'underline'" />
			    <@ww.textfield label="'${action.getText('amount')}'" name="'acceptBill.amounts'" value="'${acceptBill.amounts?if_exists}'"  cssClass="'underline'" required="true">
				</@ww.textfield>
			</tr>
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
		    <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/accept/listAcceptBills.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
		</@buttonBar>
	</@ww.form>
  <script language="javascript">	
  window.onload=function(){
      <#if loginUser.department?exists>
	     getObjByNameRe("department.id").value = #{loginUser.department.id};
	   </#if>
	   
      <#if acceptBill.id?exists>
		   var url = 'listacceptBillDetails.html?readOnly=${req.getParameter('readOnly')?if_exists}&acceptBill.id=#{acceptBill.id}';
	          document.all.frame.src = url;
		      getObjByNameRe("accept").className = "selectedtab";
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
	function open_purchaseBillDtlDialog(){
	   var url = '${req.contextPath}/accept/listPurchaseBillSelector.html?toolingDevFlag=${toolingDevFlag?if_exists}';
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
       if(getObjByNameRe("acceptBill.acceptBilName").value=='' ){
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
          
     <#-- if(getObjByNameRe("acceptBill.name").value=='' ){
         alert('${action.getText('acceptBill.name.not.null')}');
         return false;
       }else{
		   if(!isValidLength(document.forms[0], "acceptBill.name", null, 50)){//验证长度
				alert("${action.getText('acceptBill.name.length')}");
				return  false;
			   }
			
       }-->
              	//验证采购日期
	    if(getObjByNameRe("acceptBill.acceptDate").value==''){

	    }else{
	      var date=getObjByNameRe("acceptBill.acceptDate").value;
		  if(!isDate("acceptBill.acceptDate")){
		    alert("${action.getText('select.right.acceptBill.acceptDate')}");
			return false;
			}
		}
       //验证品名
       if(getObjByNameRe("acceptBill.merchandiseName").value=='' ){
         alert('${action.getText('acceptBill.merchandiseName.not.null')}');
         return false;
       }else{
		   if(!isValidLength(document.forms[0], "acceptBill.merchandiseName", null, 50)){
				alert("${action.getText('acceptBill.merchandiseName.length')}");
				return  false;
			   }
       }
       //验证型号
       if(getObjByNameRe("acceptBill.model").value!='' ){
           if(!isValidLength(document.forms[0], "acceptBill.model", null, 50)){
				alert("${action.getText('acceptBill.acceptBill.model.length')}");
				return  false;
			   }
       }
       <#--
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

      if(getObjByNameRe("supplier.supplierNo").value=='' ){
         alert('${action.getText('supplier.supplierNo.not.null')}');
         return false;
       } 
      if(getObjByNameRe("acceptBill.memo").value!=''){
		if(!isValidLength(document.forms[0], "acceptBill.memo", null, 250)){
			alert("${action.getText('acceptBill.memo.length')}");
			return  false;
			 }
		}
       return true;
      
   }
   
   </script>     	
	<#if acceptBill.id?exists>
		<ul id="beautytab">
			<li><a id="accept" onclick="activeTab(this);"  
				href="listacceptBillDetails.html?acceptBill.id=#{acceptBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('acceptBillDetail')}</a>
			</li>
			<li><a id="attachments" onclick="activeTab(this);"  
				href="listAcceptBillEnclosure.html?acceptBill.id=#{acceptBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('acceptBillfile')}</a>
			</li>
			<li><a id="inspectstandard" onclick="activeTab(this);"
				href="listSpartParts.html?acceptBill.id=#{acceptBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('spartPart')}</a>
			</li>
			
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>
  