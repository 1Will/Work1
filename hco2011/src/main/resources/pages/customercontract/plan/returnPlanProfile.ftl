<#--
	Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: contactArchivesProfile.ftl 2009-12-08 14:50:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('returnPlan.profile')}">
<@ww.form namespace="'/contractManagement'" name="'contractManagement'" action="'saveReturnPlan'" method="'post'">
<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="saveReturnPlanToken"/>
    <@inputTable>
    	<@ww.hidden name="'customer.id'" value="''"/>
    	<@ww.hidden name="'customerType.id'" value="''"/>
    	<@ww.hidden name="'payee.id'" value="'${req.getParameter('payee.id')?if_exists}'"/>
    	<@ww.hidden name="'contactArchives.id'" value="'${req.getParameter('contactArchives.id')?if_exists}'"/>
    	<@ww.hidden name="'contractManagement.id'" value="'${req.getParameter('contractManagement.id')?if_exists}'"/>
    	<#if returnPlan.id?exists>
    		<@ww.hidden name="'returnPlan.id'" value="'#{returnPlan.id?if_exists}'"/>
    		<@ww.hidden name="'contractManagement2.id'" value="'${returnPlan.contractManagement.id?if_exists}'"/>
	 	</#if>
	 	<tr>
				<#--相关合同弹出框-->
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('returnPlan.contractManagement')}:</label>
	     	</td>
	     	<td>
	     		<#if returnPlan.contractManagement?exists>
		   		<input type="text" name="returnPlan.contractManagement" class="underline"  value="${returnPlan.contractManagement.contractName?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="returnPlan.contractManagement" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="contractManagement_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
				<#--相关客户弹出框弹出框-->
				<#assign custName = ''/>
		 	<#if returnPlan.customerInfo?exists>
		   		<#assign custName = "${returnPlan.customerInfo}" />
		 	</#if>
	     	<td align="right" valign="top">
	       		<label class="label">${action.getText('customerInfo.name')}:</label>
	     	</td>
	     	<td>
	     		<#if returnPlan.customerInfo?exists>
		   			<input type="text" name="returnPlan.customerInfo" class="underline"  value="${returnPlan.customerInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" name="returnPlan.customerInfo" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<#--
				<a onClick="customer_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>-->
			</td>
				<#--相关联系人弹出框-->
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('returnPlan.contactArchives')}:</label>
	     	</td>
	     	<td>
	     		<#if returnPlan.contactArchives?exists>
		   		<input type="text" name="returnPlan.contactArchives" class="underline"  value="${returnPlan.contactArchives.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="returnPlan.contactArchives" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="contactArchives_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('returnPlan.phone')}'" name="'returnPlan.phone'" value="'${returnPlan.phone?if_exists}'" cssClass="'underline'" required="true"/>
			<@pp.datePicker 
				label="'${action.getText('returnPlan.planDate')}'" 
				name="'returnPlan.planDate'" 
	   			value="'${(returnPlan.planDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				dateFormat="'%Y-%m-%d'"
				required="true"
				maxlength="10"/>
			<script language="javascript">
					var date = new Date();
					if(getObjByName("returnPlan.planDate").value==''){
						getObjByName("returnPlan.planDate").value = date.format("yyyy-MM-dd");
					}
			</script>
			<@ww.select label="'${action.getText('returnPlan.payment')}'" 
				name="'payment.id'" 
				value="${req.getParameter('payment.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allPayments"
				required="true"
				disabled="false">
			</@ww.select>
		</tr>
		<tr>
		<span id="id_select">
		<@ww.select label="'${action.getText('returnPlan.batch')}'" 
				name="'batch.id'" 
				value="${req.getParameter('batch.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allBatchs"
				required="true"
				emptyOption="false" 
				disabled="false">
			</@ww.select>
		</span>
			<span id="ab_id">
			<@ww.textfield label="'${action.getText('returnPlan.sum')}'" name="'returnPlan.sum'" value="'#{returnPlan.sum?if_exists}'" cssClass="'underline'" required="true" onblur="'checkSum()'"/>
			</span>
			<span id="sum_id" name=""></span>
			<@ww.textfield label="'${action.getText('returnPlan.currency')}'" name="'returnPlan.currency'" value="'${returnPlan.currency?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
			<td align="right"><label for="" class="label">${action.getText('returnPlan.notOrIs')}:</label></td>
	        <td align="left">
	        	<input type="radio" id="notOrIs0" name="notOrIs" value="0" />是
	        	<input type="radio" id="notOrIs1" name="notOrIs" value="1" />否
			</td>
			
			<td align="right"><label for="" class="label">${action.getText('returnPlan.billingOrNot')}:</label></td>
	        <td align="left">
	        	<input type="radio" id="billingOrNot0" name="billingOrNot" value="0" />是
	        	<input type="radio" id="billingOrNot1" name="billingOrNot" value="1" />否
			</td>
			<td align="right"><label for="" class="label">${action.getText('returnPlan.isOrNot')}:</label></td>
	        <td align="left">
	        	<input type="radio" id="isOrNot0" name="isOrNot" value="0" disabled/>是
	        	<input type="radio" id="isOrNot1" name="isOrNot" value="1" disabled/>否
			</td>
			<#--
			<@pp.datePicker 
				label="'${action.getText('returnPlan.paytime')}'" 
				name="'returnPlan.paytime'" 
	   			value="'${(returnPlan.paytime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>-->
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('returnPlan.factSum')}'" name="'returnPlan.factSum'" value="'#{returnPlan.factSum?if_exists}'" cssClass="'underline'" readonly="true"/>
			<@ww.textfield label="'${action.getText('returnPlan.paytime')}'" name="'returnPlan.paytime'" value="'${returnPlan.paytime?if_exists}'" cssClass="'underline'" readonly="true"/>
			<#--收款人弹出框-->
	 		<td align="right" valign="top">
	       		<label class="label">${action.getText('returnPlan.payee')}:</label>
	     	</td>
	     	<td>
	     		<#if returnPlan.payee?exists>
		   		<input type="text" name="returnPlan.payee" class="underline"  value="${returnPlan.payee.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="returnPlan.payee" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<#--
				<a onClick="payee_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>-->
			</td>
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('returnPlan.remark')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="returnPlan.remark" rows="3" cols="120" >${returnPlan.remark?if_exists}</textarea>
			</td>
		</tr>
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/contractManagement/listReturnPlan.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	function checkSum(){
			if(getObjByName('returnPlan.sum').value==''){
				return false;
			}
			if(getObjByName('returnPlan.sum').value!=''){
		     	if(!isDoubleNumber("returnPlan.sum")){
					alert("${action.getText('number.must.be.double')}");
					getObjByName('returnPlan.sum').value="";
					getObjByName('returnPlan.sum').focus();
					return false;
				}
	     	}
			//验证预付款金额
	    	DWREngine.setAsync(false); 
	    	//失去焦点出发改函数  
	    	if(getObjByName('contractManagement.id').value !=''){
				checkSumDWR("contractManagement.id","returnPlan.sum","sum_id","${action.getText('')}",false); 
			}else{
				checkSumDWR("contractManagement2.id","returnPlan.sum","sum_id","${action.getText('')}",false);
			}
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true); 
	    	}

	window.onload=function(){
	<#if returnPlan.batch?exists>
		<#if  returnPlan.batch.id ==368>
			init();
		</#if>
	</#if>
		<#if returnPlan.payment?exists>
			getObjByName('payment.id').value='${returnPlan.payment.id?if_exists}';
		</#if>
		<#--<#if returnPlan.currency?exists>
			getObjByName('currency.id').value='${returnPlan.currency.id?if_exists}';
		</#if>-->
		<#if returnPlan.batch?exists>
			getObjByName('batch.id').value='${returnPlan.batch.id?if_exists}';
		</#if>
		<#if returnPlan.isOrNot?exists>
			<#if returnPlan.isOrNot=="0">
				getObjByName('isOrNot0').checked=true;
			<#else>
				getObjByName('isOrNot1').checked=true;
			</#if>
		<#else>
			getObjByName('isOrNot1').checked=true;
		</#if>
		
		<#if returnPlan.notOrIs?exists>
			<#if returnPlan.notOrIs=="0">
				getObjByName('notOrIs0').checked=true;
			<#else>
				getObjByName('notOrIs1').checked=true;
			</#if>
		<#else>
			getObjByName('notOrIs0').checked=true;
		</#if>
		
		<#if returnPlan.billingOrNot?exists>
			<#if returnPlan.billingOrNot=="0">
				getObjByName('billingOrNot0').checked=true;
			<#else>
				getObjByName('billingOrNot1').checked=true;
			</#if>
		<#else>
			getObjByName('billingOrNot0').checked=true;
		</#if>
	}
	
	//弹出客户档案查询模态窗体
	function customer_OpenDialog(){
	   var url = "${req.contextPath}/customerRelationship/listCustInfo.html";
	   popupModalDialog(url, 800, 600, creatorSelector1Handler);
	 }
	 //获得模态窗体返回值
	function creatorSelector1Handler(result) {
		if (null != result) {
		 	document.forms[0].elements["customer.id"].value = result[0];
		 	document.forms[0].elements["returnPlan.customerInfo"].value = result[1];
		 	document.forms[0].elements["customerType.id"].value = result[2];
		}
	}
	
	//弹出收款人查询模态窗体
	function payee_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorPrincipalHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorPrincipalHandler(result) {
		if (null != result) {
			document.forms[0].elements["payee.id"].value = result[0];
	   		getObjByName('returnPlan.payee').value=result[2];
		}
	}
	
	//联系人查询模态窗体
	function contactArchives_OpenDialog(){
	   var url = "${req.contextPath}/com/listContactArchivesWindow.html";
	   popupModalDialog(url, 800, 600, creatorSelector2Handler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelector2Handler(result) {
		if (null != result) {
			document.forms[0].elements["contactArchives.id"].value = result[0];
	   		getObjByName('returnPlan.contactArchives').value=result[2];
	   		if(null != result[3]){
	   			getObjByName('returnPlan.phone').value=result[3];
	   		}else{
	   			getObjByName('returnPlan.phone').value="";
	   		}
		}
	}
	
		//合同管理模态窗体
	function contractManagement_OpenDialog(){
		var over ="no";
	   var url = "${req.contextPath}/contractManagement/listContractManagementWindowAction.html?flag2="+over;
	   popupModalDialog(url, 800, 600, creatorSelector3Handler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelector3Handler(result) {
		if (null != result) {
			document.forms[0].elements["contractManagement.id"].value = result[0];
	   		getObjByName('returnPlan.contractManagement').value=result[1];
	   		getObjByName('returnPlan.customerInfo').value=result[2];
	   		getObjByName('returnPlan.contactArchives').value=result[3];
	   		getObjByName('returnPlan.phone').value=result[6];
	   		document.forms[0].elements["customer.id"].value = result[4];
	   		document.forms[0].elements["contactArchives.id"].value=result[5];
	   		var s;
	   		var m;
	   			s=result[7];
	   			m=result[8];
	   		if(s == '06500'){
	   			getObjByName('batch.id').value='366';
	   			getObjByName('returnPlan.sum').value=m;
	   			init("id_select");
	   			init("ab_id");
	   		}
		}
	}
	//将下来菜单设为只读
	function selectReadOnly(selectedId){
	  var obj = document.getElementById(selectedId);
	     obj.onmouseover = function(){
	     obj.setCapture();
	    }
	    obj.onmouseout = function(){
	     obj.releaseCapture();
	    }
	    obj.onfocus = function(){
	     obj.blur();
	    }
	    obj.onbeforeactivate = function(){
	     return false;
	    }
 	}

	function init(m){
      selectReadOnly(m);
    }
	
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		if(getObjByName('returnPlan.contractManagement').value==''){
			alert("${action.getText('returnPlan.contractManagement.requiredstring')}");
			return false;
		}
		if(getObjByName('returnPlan.contactArchives').value==''){
			alert("${action.getText('returnPlan.contactArchives.requiredstring')}");
			return false;
		}
		
		if(getObjByName('returnPlan.phone').value==''){
			alert("${action.getText('returnPlan.phone.requiredstring')}");
			getObjByName('returnPlan.phone').focus();
			return false;
		}
		
		if(getObjByName('returnPlan.planDate').value==''){
			alert("${action.getText('returnPlan.paytime.requiredstring')}");
			getObjByName('returnPlan.planDate').focus();
			return false;
		}
		
		if(getObjByName('returnPlan.planDate').value !=''){
		 	if(!isDate('returnPlan.planDate')){
				alert("${action.getText('returnPlan.paytime.dateFormate.error')}");
	      	    getObjByName('returnPlan.planDate').focus();
				return false;
			}
		}
		
		if(getObjByName('payment.id').value==''){
			alert("${action.getText('returnPlan.payment.requiredstring')}");
			getObjByName('payment.id').focus();
			return false;
		}
		
		if(getObjByName('batch.id').value==''){
			alert("${action.getText('returnPlan.batch.requiredstring')}");
			getObjByName('batch.id').focus();
			return false;
		}
		
		if(getObjByName('returnPlan.sum').value==''){
			alert("${action.getText('returnPlan.sum.requiredstring')}");
			getObjByName('returnPlan.sum').focus();
			return false;
		}
		
		 //验证费用为double类型
		if(getObjByName('returnPlan.sum').value!=''){
	     	if(!isDoubleNumber("returnPlan.sum")){
				alert("${action.getText('number.must.be.double')}");
				getObjByName('returnPlan.sum').value="";
				getObjByName('returnPlan.sum').focus();
				return false;
			}
	     }
		
		if(getObjByName('returnPlan.paytime').value !=''){
		 	if(!isDate('returnPlan.paytime')){
				alert("${action.getText('returnPlan.paytime.dateFormate.error')}");
	      	    getObjByName('returnPlan.paytime').focus();
				return false;
			}
		}
		return true;
	}
</script>
</@htmlPage>