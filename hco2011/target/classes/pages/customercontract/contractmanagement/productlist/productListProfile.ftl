<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: customerInfoProfile.ftl 2009-12-14 8:48:35Z wliu $ -->

<#include "../../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('productList.edit')}">
<@ww.form name="'listForm'" action="'saveProductList'" method="'post'">
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="saveProductListToken"/>
	<#if productList.id?exists>
		<@ww.hidden name="'productList.id'" value="#{productList.id?if_exists}"/>
	</#if>
	
	<#if productList.product?exists>
		<@ww.hidden id="productid" name="'product.id'" value="#{productList.product.id?if_exists}"/>
	<#else>
		<@ww.hidden id="productid" name="'product.id'" value=""/>
	</#if>
	<@ww.hidden id="contractManagementid" name="'contractManagementid'" value="#{contractManagementid?if_exists}"/>
	<@inputTable>
		<tr>
		
			<td align="right" valign="top">
	       		<label class="label"><font color="red">*</font>${action.getText('productList.product')}:</label>
	     	</td>
	     	<td>
	     		<#if productList.product?exists>
		   		<input type="text" id="productName" name="productName" class="underline"  value="${productList.product.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" id="productName" name="productName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="products_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<@textfield id="count" label="${action.getText('productList.count')}" maxlength="5"  name="productList.count"  value="#{productList.count?if_exists}"  required="false" anothername="checkcount"/>
			<@textfield id="unitPrice" label="${action.getText('productList.unitPrice')}" maxlength="10"  name="productList.unitPrice"  value="#{productList.unitPrice?if_exists}"  required="false" anothername="checkunitPrice"/>
		</tr>
		<tr>
			<@select label="${action.getText('productList.unit')}" 
		   	   anothername="selectCheckUnit"
		       name="unit.id" 
		       value="${req.getParameter('unit.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allUnit" 
		       emptyOption="false" 
		       disabled="false" 
		       required="true">
		    </@select>
			<@textfield id="discount" label="${action.getText('productList.discount')}" maxlength="10"  name="productList.discount"  value="#{productList.discount?if_exists}"  required="false" anothername="checkdiscount"/>
			<@textfield id="totalPrice" label="${action.getText('productList.totalPrice')}" maxlength="10"  name="productList.totalPrice"  value="#{productList.totalPrice?if_exists}"  required="false"  readonly="true" anothername="checkdiscount"/>
		    
		</tr>
		<tr>
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('productList.remark')}:
        		</label>
        	</td>
	        <td colspan="5">
	        	<textarea name="productList.remark" rows="6" cols="120" >${productList.remark?if_exists}</textarea>
	        </td>
		</tr>
		</@inputTable>
	<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		<@vbutton class="button" value="${action.getText('close')}" onclick="window.close();"/>
    </@buttonBar>
</@ww.form>
<script language="JavaScript" type="text/JavaScript"> 
	window.onload=function(){
		<#if productList.product?exists>
			getObjByName('product.id').value='${productList.product.id?if_exists}';
		</#if>
		<#if productList.unit?exists>
			getObjByName('unit.id').value='${productList.unit.id?if_exists}';
		</#if>
	}
	<#-- 提交验证-->
	function storeValidation(){
		if(jgetObjByName("#productid").val()==""){
			alert("${action.getText('validation.productid')}");
		    return false;
		}
		if(!isNumber("count")){
			alert("${action.getText('validation.count')}");
			jgetObjByName("#count").focus();
			return false;
		}
		if(!isDoubleNumber("unitPrice")){
			alert("${action.getText('validation.unitPrice')}");
			jgetObjByName("#unitPrice").focus();
			return false;
		}
		if(!isDoubleNumber("discount")){
			alert("${action.getText('validation.discount0')}");
			jgetObjByName("#discount").focus();
			return false;
		}
		if(parseInt(jgetObjByName("#count").val())<=0){
			alert("${action.getText('validation.count')}");
			jgetObjByName("#count").focus();
			return false;
		}
		if(parseInt(jgetObjByName("#unitPrice").val())<=0){
			alert("${action.getText('validation.unitPrice')}");
			jgetObjByName("#unitPrice").focus();
			return false;
		}
		
		if(getObjByName('productList.discount').value<0 || getObjByName('productList.discount').value>100){
			alert("${action.getText('validation.discount1')}");
			jgetObjByName("#discount").focus();
			return false;
		}else{
			jgetObjByName("#totalPrice").val(jgetObjByName("#count").val()*jgetObjByName("#unitPrice").val()*(jgetObjByName("#discount").val()/100));
		}	
		
		<#--if(!isDoubleNumber("totalPrice")){
			alert("总价是数字!");
			jgetObjByName("#totalPrice").focus();
			return false;
		}-->
		if(!selectCheck_selectCheckUnit()){
			getObjByName('unit.id').focus();
		    return false;
		}
		return true;
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
			jgetObjByName("#productid").val(result[0]);
			jgetObjByName("#productName").val(result[2]);
			jgetObjByName("#unitPrice").val(result[3]);
		}
	}
</script>

</@htmlPage>
