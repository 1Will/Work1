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

<@htmlPage title="${action.getText('rejection.profile')}">
<@ww.form namespace="'/com'" name="'com'" action="'saveRejection'" method="'post'">
	<@ww.token name="saveRejectionToken"/>
    <@inputTable>
    	<@ww.hidden name="'customer.id'" value="''"/>
    	<@ww.hidden name="'customerType.id'" value="''"/>
    	<@ww.hidden name="'saleMan.id'" value="'${req.getParameter('saleMan.id')?if_exists}'"/>
    	<@ww.hidden name="'co.id'" value="'${req.getParameter('co.id')?if_exists}'"/>
    	<@ww.hidden name="'products.id'" value="'${req.getParameter('products.id')?if_exists}'"/>
    	<#if rejection.id?exists>
    		<@ww.hidden name="'rejection.id'" value="#{rejection.id?if_exists}"/>
	 	</#if>
	 	<tr>
            <@ww.textfield label="'${action.getText('rejection.code')}'" name="'rejection.code'" value="'${rejection.code?if_exists}'" cssClass="'underline'" required="true" readonly="true" />
			<@ww.textfield label="'${action.getText('rejection.company')}'" name="'rejection.company'" value="'${rejection.company?if_exists}'" cssClass="'underline'" required="true" />
			<#assign custName = ''/>
		 	<#if rejection.customerInfo?exists>
		   		<#assign custName = "${rejection.customerInfo}" />
		 	</#if>
	     	<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('rejection.customerInfo')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="rejection.customerInfo" class="underline"  value="${rejection.customerInfo?if_exists}" maxlength="140" size="20" disabled="true"/>
				<a onClick="customer_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		<tr>
			<#--客户订单弹出框-->
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('rejection.co')}:</label>
	     	</td>
	     	<td>
	     		<#if rejection.co?exists>
		   			<input type="text" name="rejection.co" class="underline"  value="${rejection.co.code?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" name="rejection.co" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="co_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<#--业务员弹出框-->
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('rejection.salesman')}:</label>
	     	</td>
	     	<td>
	     		<#if rejection.saleMan?exists>
		   		<input type="text" name="rejection.saleMan" class="underline"  value="${rejection.saleMan.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="rejection.saleMan" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="salesman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<#--退货产品的弹出框-->
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('rejection.products')}:</label>
	     	</td>
	     	<td>
	     		<#if rejection.products?exists>
		   		<input type="text" name="rejection.products" class="underline"  value="${rejection.products.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="rejection.products" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="products_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('rejection.sum')}'" name="'rejection.sum'" value="'${rejection.sum?if_exists}'" cssClass="'underline'" required="true" />
			<@pp.datePicker 
				label="'${action.getText('rejection.rejectionDate')}'" 
				name="'rejection.rejectionDate'" 
	   			value="'${(rejection.rejectionDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
				<#--
			<script language="javascript">
					var date = new Date();
					if(getObjByName("rejection.createDate").value==''){
						getObjByName("rejection.createDate").value = date.format("yyyy-MM-dd");
					}
			</script>-->
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('rejection.reason')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="rejection.reason" rows="4" cols="150" >${rejection.reason?if_exists}</textarea>
			</td>
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('rejection.remark')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="rejection.remark" rows="4" cols="150" >${rejection.remark?if_exists}</textarea>
			</td>
		</tr>
    </@inputTable>
    <@buttonBar>
    <#if !(action.isReadOnly())>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
	</#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/com/listRejection.html"/>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
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
		 	document.forms[0].elements["rejection.customerInfo"].value = result[1];
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
	   		getObjByName('rejection.products').value=result[2];
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
			document.forms[0].elements["saleMan.id"].value = result[0];
	   		getObjByName('rejection.saleMan').value=result[2];
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
	   		getObjByName('rejection.co').value=result[1];
		}
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		if(getObjByName('rejection.company').value ==''){
			alert("${action.getText('rejection.company.requiredstring')}");
			getObjByName('rejection.company').focus();
			return false;
		}
		if(getObjByName('rejection.customerInfo').value ==''){
			alert("${action.getText('rejection.customerInfo.requiredstring')}");
			return false;
		}
		if(getObjByName('rejection.co').value ==''){
			alert("${action.getText('rejection.co.requiredstring')}");
			return false;
		}
		if(getObjByName('rejection.saleMan').value ==''){
			alert("${action.getText('rejection.salesman.requiredstring')}");
			return false;
		}
		if(getObjByName('rejection.products').value ==''){
			alert("${action.getText('rejection.products.requiredstring')}");
			return false;
		}
		if(getObjByName('rejection.sum').value ==''){
			alert("${action.getText('rejection.sum.requiredstring')}");
			getObjByName('rejection.sum').focus();
			return false;
		}
		
		 //验证费用为double类型
		if(getObjByName('rejection.sum').value!=''){
	     	if(!isDoubleNumber("rejection.sum")){
				alert("${action.getText('number.must.be.double')}");
				getObjByName('rejection.sum').value="";
				getObjByName('rejection.sum').focus();
				return false;
			}
	     }
		if(getObjByName('rejection.rejectionDate').value ==''){
		 		alert("${action.getText('rejection.rejectionDate.requiredstring')}");
		 		getObjByName('rejection.rejectionDate').focus();
		      	return false;   
		}else{
		 	if(!isDate('rejection.rejectionDate')){
				alert("${action.getText('rejection.rejectionDate.dateFormate.error')}");
				getObjByName('rejection.rejectionDate').value="";
	      	    getObjByName('rejection.rejectionDate').focus();
				return false;
			} 
		}
		
		return true;
	}
</script>
</@htmlPage>
