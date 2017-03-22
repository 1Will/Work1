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

<@htmlPage title="${action.getText('contractCustomer.profile')}">
<@ww.form namespace="'/com'" name="'com'" action="'saveContractCustomer'" method="'post'">
	<@ww.token name="saveContractCustomerToken"/>
    <@inputTable>
    	<@ww.hidden name="'customer.id'" value="''"/>
    	<@ww.hidden name="'customerType.id'" value="''"/>
    	<@ww.hidden name="'salesman.id'" value="'${req.getParameter('salesman.id')?if_exists}'"/>
    	<@ww.hidden name="'products.id'" value="'${req.getParameter('products.id')?if_exists}'"/>
    	<@ww.hidden name="'co.id'" value="'${req.getParameter('co.id')?if_exists}'"/>
    	<#if contractCustomer.id?exists>
    		<@ww.hidden name="'contractCustomer.id'" value="#{contractCustomer.id?if_exists}"/>
	 	</#if>
	 	<tr>
            <@ww.textfield label="'${action.getText('contractCustomer.code')}'" name="'contractCustomer.code'" value="'${contractCustomer.code?if_exists}'" cssClass="'underline'" required="true" readonly="true" />
			<@ww.textfield label="'${action.getText('contractCustomer.name')}'" name="'contractCustomer.name'" value="'${contractCustomer.name?if_exists}'" cssClass="'underline'" required="true" />
			<@pp.datePicker 
				label="'${action.getText('contractCustomer.affixTime')}'" 
				name="'contractCustomer.affixTime'" 
	   			value="'${(contractCustomer.affixTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				dateFormat="'%Y-%m-%d'"
				required="true"
				maxlength="10"/>
		</tr>
		<tr>
			<#assign custName = ''/>
		 	<#if contractCustomer.customerInfo?exists>
		   		<#assign custName = "${contractCustomer.customerInfo}" />
		 	</#if>
	     	<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('contractCustomer.customerInfo')}:</label>
	     	</td>
	     	<td>
	     		<#if contractCustomer.customerInfo?exists>
		   		<input type="text" name="contractCustomer.customerInfo" class="underline"  value="${contractCustomer.customerInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="contractCustomer.customerInfo" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="customer_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<@ww.textfield label="'${action.getText('contractCustomer.affixPerson')}'" name="'contractCustomer.affixPerson'" value="'${contractCustomer.affixPerson?if_exists}'" cssClass="'underline'" required="true" />
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('contractCustomer.salesman')}:</label>
	     	</td>
	     	<td>
	     		<#if contractCustomer.saleman?exists>
		   		<input type="text" name="contractCustomer.saleman" class="underline"  value="${contractCustomer.saleman.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="contractCustomer.saleman" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="salesman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('contractCustomer.phone')}'" name="'contractCustomer.phone'" value="'${contractCustomer.phone?if_exists}'" cssClass="'underline'" required="true" />
			<@ww.textfield label="'${action.getText('contractCustomer.email')}'" name="'contractCustomer.email'" value="'${contractCustomer.email?if_exists}'" cssClass="'underline'"/>
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('contractCustomer.products')}:</label>
	     	</td>
	     	<td>
	     		<#if contractCustomer.products?exists>
		   		<input type="text" name="contractCustomer.products" class="underline"  value="${contractCustomer.products.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="contractCustomer.products" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="products_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		<tr>
		<@ww.textfield label="'${action.getText('contractCustomer.place')}'" name="'contractCustomer.place'" value="'${contractCustomer.place?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('contractCustomer.sum')}'" name="'contractCustomer.sum'" value="'${contractCustomer.sum?if_exists}'" cssClass="'underline'" required="true"/>
		<@ww.select label="'${action.getText('contractCustomer.payment')}'" 
				name="'payment.id'" 
				value="${req.getParameter('payment.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allPayments"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
		</tr>
		<tr>
			<@ww.select label="'${action.getText('contractCustomer.type')}'" 
				name="'type.id'" 
				value="${req.getParameter('type.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allTypes"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			<@ww.select label="'${action.getText('contractCustomer.status')}'" 
				name="'status.id'" 
				value="${req.getParameter('status.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allStatus"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('contractCustomer.co')}:</label>
	     	</td>
	     	<td>
	     		<#if contractCustomer.co?exists>
		   			<input type="text" name="contractCustomer.co" class="underline"  value="${contractCustomer.co.code?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" name="contractCustomer.co" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="co_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
        </tr>
		<tr>
			<@ww.textfield label="'${action.getText('contractCustomer.area')}'" name="'contractCustomer.area'" value="'${contractCustomer.area?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('contractCustomer.content')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contractCustomer.content" rows="3" cols="120" >${contractCustomer.content?if_exists}</textarea>
			</td>
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('contractCustomer.remark')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contractCustomer.remark" rows="3" cols="120" >${contractCustomer.remark?if_exists}</textarea>
			</td>
		</tr>
    </@inputTable>
    <@buttonBar>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/com/listContractCustomer.html"/>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
		<#if contractCustomer.payment?exists>
			getObjByName('payment.id').value='${contractCustomer.payment.id?if_exists}';
		</#if>
		<#if contractCustomer.status?exists>
			getObjByName('status.id').value='${contractCustomer.status.id?if_exists}';
		</#if>
		<#if contractCustomer.type?exists>
			getObjByName('type.id').value='${contractCustomer.type.id?if_exists}';
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
		 	document.forms[0].elements["contractCustomer.customerInfo"].value = result[1];
		 	document.forms[0].elements["customerType.id"].value = result[2];
		}
	}
	
	
	//弹出业务员查询模态窗体
	function salesman_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
			document.forms[0].elements["salesman.id"].value = result[0];
	   		getObjByName('contractCustomer.saleman').value=result[2];
		}
	}
	
	
	//弹出产品查询模态窗体
	function products_OpenDialog(){
	   var url = "${req.contextPath}/com/listProductsWindow.html";
	   popupModalDialog(url, 800, 600, creatorPrincipalHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorPrincipalHandler(result) {
		if (null != result) {
			document.forms[0].elements["products.id"].value = result[0];
	   		getObjByName('contractCustomer.products').value=result[2];
		}
	}
	
	
	//弹出客户订单查询模态窗体
	function co_OpenDialog(){
	   var url = "${req.contextPath}/com/listCoWindow.html";
	   popupModalDialog(url, 800, 600, creatorCoHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorCoHandler(result) {
		if (null != result) {
			document.forms[0].elements["co.id"].value = result[0];
	   		getObjByName('contractCustomer.co').value=result[1];
		}
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		if(getObjByName('contractCustomer.name').value ==''){
			alert("${action.getText('contractCustomer.name.requiredstring')}");
			getObjByName('contractCustomer.name').focus();
			return false;
		}
		if(getObjByName('contractCustomer.affixTime').value ==''){
		 		alert("${action.getText('contractCustomer.affixTime.requiredstring')}");
		 		getObjByName('contractCustomer.affixTime').focus();
		      	return false;   
		}else{
		 	if(!isDate('contractCustomer.affixTime')){
				alert("${action.getText('contractCustomer.affixTime.dateFormate.error')}");
				getObjByName('contractCustomer.affixTime').value="";
	      	    getObjByName('contractCustomer.affixTime').focus();
				return false;
			} 
		}
		if(getObjByName('contractCustomer.customerInfo').value ==''){
			alert("${action.getText('contractCustomer.customerInfo.requiredstring')}");
			return false;
		}
		if(getObjByName('contractCustomer.affixPerson').value ==''){
			alert("${action.getText('contractCustomer.affixPerson.requiredstring')}");
			getObjByName('contractCustomer.affixPerson').focus();
			return false;
		}
		if(getObjByName('contractCustomer.saleman').value ==''){
			alert("${action.getText('contractCustomer.salesman.requiredstring')}");
			return false;
		}
		if(getObjByName('contractCustomer.phone').value ==''){
			alert("${action.getText('contractCustomer.phone.requiredstring')}");
			getObjByName('contractCustomer.phone').focus();
			return false;
		}
		if(getObjByName('contractCustomer.products').value ==''){
			alert("${action.getText('contractCustomer.products.requiredstring')}");
			return false;
		}
		if(getObjByName('contractCustomer.sum').value ==''){
			alert("${action.getText('contractCustomer.sum.requiredstring')}");
			getObjByName('contractCustomer.sum').focus();
			return false;
		}
		
		 //验证费用为double类型
		if(getObjByName('contractCustomer.sum').value!=''){
	     	if(!isDoubleNumber("contractCustomer.sum")){
				alert("${action.getText('number.must.be.double')}");
				getObjByName('contractCustomer.sum').value="";
				getObjByName('contractCustomer.sum').focus();
				return false;
			}
	     }
	     
		if(getObjByName('payment.id').value ==''){
			alert("${action.getText('contractCustomer.payment.requiredstring')}");
			getObjByName('payment.id').focus();
			return false;
		}
		if(getObjByName('type.id').value ==''){
			alert("${action.getText('contractCustomer.type.requiredstring')}");
			getObjByName('type.id').focus();
			return false;
		}
		if(getObjByName('status.id').value ==''){
			alert("${action.getText('contractCustomer.status.requiredstring')}");
			getObjByName('status.id').focus();
			return false;
		}
		if(getObjByName('contractCustomer.co').value ==''){
			alert("${action.getText('contractCustomer.co.requiredstring')}");
			return false;
		}
		if(getObjByName('contractCustomer.content').value ==''){
			alert("${action.getText('contractCustomer.content.requiredstring')}");
			getObjByName('contractCustomer.content').focus();
			return false;
		}
		return true;
	}
</script>
</@htmlPage>
