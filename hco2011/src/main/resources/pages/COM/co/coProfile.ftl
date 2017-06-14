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

<@htmlPage title="${action.getText('co.profile')}">
<@ww.form namespace="'/com'" name="'com'" action="'saveCo'" method="'post'">
	<@ww.token name="saveCoToken"/>
    <@inputTable>
    	<@ww.hidden name="'customer.id'" value="''"/>
    	<@ww.hidden name="'customerType.id'" value="''"/>
    	<@ww.hidden name="'salesman.id'" value="'${req.getParameter('salesman.id')?if_exists}'"/>
    	<@ww.hidden name="'principal.id'" value="'${req.getParameter('principal.id')?if_exists}'"/>
    	<@ww.hidden name="'linkman.id'" value="'${req.getParameter('linkman.id')?if_exists}'"/>
    	<@ww.hidden name="'products.id'" value="'${req.getParameter('products.id')?if_exists}'"/>
    	<#if co.id?exists>
    		<@ww.hidden name="'co.id'" value="#{co.id?if_exists}"/>
	 	</#if>
	 	<tr>
            <@ww.textfield label="'${action.getText('co.code')}'" name="'co.code'" value="'${co.code?if_exists}'" cssClass="'underline'" required="true" readonly="true" />
			<#assign custName = ''/>
		 	<#if co.customerInfo?exists>
		   		<#assign custName = "${co.customerInfo}" />
		 	</#if>
	     	<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('co.customerInfo')}:</label>
	     	</td>
	     	<td>
	     		<#if co.customerInfo?exists>
		   			<input type="text" name="co.customerInfo" class="underline"  value="${co.customerInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" name="co.customerInfo" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="customer_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<@ww.select label="'${action.getText('co.type')}'" 
				name="'type.id'" 
				value="${req.getParameter('type.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allTypes"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('co.products')}:</label>
	     	</td>
	     	<td>
	     		<#if co.products?exists>
		   		<input type="text" name="co.products" class="underline"  value="${co.products.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="co.products" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="products_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<@pp.datePicker 
				label="'${action.getText('co.orderTime')}'" 
				name="'co.orderTime'" 
	   			value="'${(co.orderTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
			<@pp.datePicker 
				label="'${action.getText('co.deliveryTime')}'" 
				name="'co.deliveryTime'" 
	   			value="'${(co.deliveryTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
		</tr>
		<tr>
			<@ww.select label="'${action.getText('co.deliveryWay')}'" 
				name="'deliveryWay.id'" 
				value="${req.getParameter('deliveryWay.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allDeliveryWays"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('co.linkman')}:</label>
	     	</td>
	     	<td>
	     		<#if co.linkman?exists>
		   		<input type="text" name="co.linkman" class="underline"  value="${co.linkman.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="co.linkman" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="linkman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<@ww.textfield label="'${action.getText('co.phone')}'" name="'co.phone'" value="'${co.phone?if_exists}'" cssClass="'underline'" required="true" />
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('co.salesman')}:</label>
	     	</td>
	     	<td>
	     		<#if co.salesman?exists>
		   		<input type="text" name="co.salesman" class="underline"  value="${co.salesman.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="co.salesman" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="salesman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		<tr>
			<@text label="${action.getText('co.address')}" name="co.address" value="${co.address?if_exists}" required="true" ></@text>
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('co.remark')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="co.remark" rows="4" cols="150" >${co.remark?if_exists}</textarea>
			</td>
		</tr>
    </@inputTable>
    <@buttonBar>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/com/listCo.html"/>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
		<#if co.deliveryWay?exists>
			getObjByName('deliveryWay.id').value='${co.deliveryWay.id?if_exists}';
		</#if>
		<#if co.type?exists>
			getObjByName('type.id').value='${co.type.id?if_exists}';
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
		 	document.forms[0].elements["co.customerInfo"].value = result[1];
		 	document.forms[0].elements["customerType.id"].value = result[2];
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
	   		getObjByName('co.products').value=result[2];
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
	   		getObjByName('co.salesman').value=result[2];
		}
	}
	
	//联系人查询模态窗体
	function linkman_OpenDialog(){
	   var url = "${req.contextPath}/com/listContactArchivesWindow.html";
	   popupModalDialog(url, 800, 600, creatorSelector2Handler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelector2Handler(result) {
		if (null != result) {
			document.forms[0].elements["linkman.id"].value = result[0];
	   		getObjByName('co.linkman').value=result[2];
		}
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		if(getObjByName('co.customerInfo').value ==''){
			alert("${action.getText('co.customerInfo.requiredstring')}");
			return false;
		}
		if(getObjByName('type.id').value ==''){
			alert("${action.getText('co.type.requiredstring')}");
			getObjByName('type.id').focus();
			return false;
		}
		
		if(getObjByName('co.orderTime').value !=''){
		 	if(!isDate('co.orderTime')){
				alert("${action.getText('co.deliveryTime.dateFormate.error')}");
				getObjByName('co.orderTime').value="";
	      	    getObjByName('co.orderTime').focus();
				return false;
			}
		}
		if(getObjByName('co.deliveryTime').value !=''){
		 	if(!isDate('co.deliveryTime')){
				alert("${action.getText('co.deliveryTime.dateFormate.error')}");
				getObjByName('co.deliveryTime').value="";
	      	    getObjByName('co.deliveryTime').focus();
				return false;
			}
			if(getObjByName('co.orderTime').value !=''){
				if(isDateLessThenOldDate(getObjByName('co.orderTime').value,getObjByName('co.deliveryTime').value)){
						alert('${action.getText('co.deliveryTime.earlyError')}');
						getObjByName('co.deliveryTime').value="";
			       		getObjByName('co.deliveryTime').focus();
						return false;
					}//isDateLessThenCurrent
			}  
		}
		
		
		if(getObjByName('deliveryWay.id').value ==''){
			alert("${action.getText('co.deliveryWay.requiredstring')}");
			getObjByName('deliveryWay.id').focus();
			return false;
		}
		if(getObjByName('co.linkman').value ==''){
			alert("${action.getText('co.linkman.requiredstring')}");
			return false;
		}
		if(getObjByName('co.phone').value ==''){
			alert("${action.getText('co.phone.requiredstring')}");
			getObjByName('co.phone').focus();
			return false;
		}
		if(getObjByName('co.address').value ==''){
			alert("${action.getText('co.address.requiredstring')}");
			getObjByName('co.address').focus();
			return false;
		}
	}
</script>
</@htmlPage>
