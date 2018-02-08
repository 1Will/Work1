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
			<@textfield id="count" label="${action.getText('productList.count')}" maxlength="5"  name="productList.count"  value="#{productList.count?if_exists}"  required="false" onblur="getDiscount()"/>
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
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<label class="label"><font color="red">*</font>${action.getText('productList.model')}:</label>
	     	</td>
	     	<td>
	     		<#if productList.product?exists>
		   		<input type="text" id="productModel" name="productModel" class="underline"  value="${productList.product.model?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" id="productModel" name="productModel" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
			</td>
			
			<td align="right" valign="top">
	       		<label class="label">${action.getText('productList.oldPrice')}:</label>
	     	</td>
	     	<td>
	     		<#if productList.product?exists>
		   		<input type="text" id="salePrice" name="salePrice" class="underline"  value="#{productList.product.salePrice?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" id="salePrice" name="salePrice" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
			</td>
			<@textfield id="unitPrice" label="${action.getText('productList.unitPrice')}" maxlength="10"  name="productList.unitPrice"  value="#{productList.unitPrice?if_exists}"  required="false" onblur="getDiscount()"/>
		</tr>
		<tr>
		<@textfield id="totalPrice" label="${action.getText('productList.totalPrice')}" maxlength="10"  name="productList.totalPrice"  value="#{productList.totalPrice?if_exists}"  required="false"  readonly="true" />
		<@textfield id="discount" label="${action.getText('productList.discount')}" maxlength="10"  name="productList.discount"  value="#{productList.discount?if_exists}"  required="false"  readonly="true" />
		<@pp.datePicker label="'${action.getText('productList.plannedDeliveryDate')}'" 
				name="'productList.plannedDeliveryDate'" 
	   			value="'${(productList.plannedDeliveryDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
			
	   </tr>
	   <tr>
	   		
		<@select label="${action.getText('productList.qualityControl')}" 
	   	   anothername="selectCheckQualityControl"
	       name="qualityControl.id" 
	       value="${req.getParameter('qualityControl.id')?if_exists}"
	       listKey="id" 
	       listValue="name"
	       list="AllQualityControl" 
	       emptyOption="false" 
	       disabled="false" >
	    </@select>
	    <@textfield id="deliveryedCount" label="${action.getText('productList.deliveryedCount')}" maxlength="10"  name="productList.deliveryedCount"  value="#{productList.deliveryedCount?if_exists}"  required="false"  readonly="true" anothername="checkDeliveryedCount"/>
	   <tr>
		<tr>
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('productList.remark')}:
        		</label>
        	</td>
	        <td colspan="5">
	        	<textarea name="productList.remark" rows="4" >${productList.remark?if_exists}</textarea>
	        </td>
	         <script language="javascript">
			 var width=document.body.clientWidth/9;
						getObjByName("productList.remark").cols =width;
		   </script>
		</tr>
		
		</@inputTable>
	<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		
		<#-- 继续新建按钮   -->
		<#if productList.id?exists>
		<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/productList/editProductList.html?contractManagementid=${contractManagementid?if_exists}"/>
		<#else>
		<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/productList/editProductList.html?contractManagementid=${contractManagementid?if_exists}"/>
		<script language="JavaScript" type="text/JavaScript"> 
		getObjByName("newNext").disabled="true";
		</script>
		</#if>
		<@vbutton class="button" value="${action.getText('close')}" onclick="closeAndRefresh();"/>
    </@buttonBar>
</@ww.form>
<script language="JavaScript" type="text/JavaScript"> 
	window.onload=function(){
		<#if productList.product?exists>
			getObjByName('product.id').value='#{productList.product.id?if_exists}';
		</#if>
		<#if productList.unit?exists>
			getObjByName('unit.id').value='#{productList.unit.id?if_exists}';
		</#if>
		<#if productList.qualityControl?exists>
			getObjByName('qualityControl.id').value='#{productList.qualityControl.id?if_exists}';
		</#if>
	}
		function getDiscount(){
		var discount='';
		var count =getObjByName("count").value;
		var unitPrice =getObjByName("unitPrice").value;
		var salePrice =getObjByName("salePrice").value;
		if(!isNumber("count")){
			alert("${action.getText('validation.count')}");
			getObjByName("count").focus();
			return false;
		}
		if(!isDoubleNumber("unitPrice")){
			alert("${action.getText('validation.unitPrice')}");
			getObjByName("unitPrice").focus();
			return false;
		}
		getObjByName("totalPrice").value=unitPrice*count;
        if(salePrice!=''&&parseFloat(salePrice)!=0){
        discount =(parseFloat(unitPrice) *100) /parseFloat(salePrice);
				discount = discount.toFixed(2);
				getObjByName("discount").value=discount;
        
        }		
	}
	<#-- 提交验证-->
	function storeValidation(){
	 if(getObjByName("productList.deliveryedCount").value!=""){
	    var count_1=getObjByName("productList.count").value;
	    var deliveryedCount_1=getObjByName("productList.deliveryedCount").value;
	    if(parseInt(count_1) < parseInt(deliveryedCount_1)){
	    return false;
	    }
	    }
		if(getObjByName("productid").value==""){
			alert("${action.getText('validation.productid')}");
		    return false;
		}
		if(getObjByName("count").value==""){
			alert("请输入数量");
		    return false;
		}
		if(!isNumber("count")){
			alert("${action.getText('validation.count')}");
			getObjByName("count").focus();
			return false;
		}
		if(getObjByName("unitPrice").value==""){
			alert("请输入实际单价");
		    return false;
		}
		if(!isDoubleNumber("unitPrice")){
			alert("${action.getText('validation.unitPrice')}");
			getObjByName("unitPrice").focus();
			return false;
		}
		if(!isDoubleNumber("discount")){
			alert("${action.getText('validation.discount0')}");
			getObjByName("discount").focus();
			return false;
		}
		if(parseInt(getObjByName("count").value)<=0){
			alert("${action.getText('validation.count')}");
			getObjByName("count").focus();
			return false;
		}
		if(parseInt(getObjByName("unitPrice").value)<=0){
			alert("${action.getText('validation.unitPrice')}");
			getObjByName("unitPrice").focus();
			return false;
		}
		<#--
		if(getObjByName('productList.discount').value<0 || getObjByName('productList.discount').value>100){
			alert("${action.getText('validation.discount1')}");
			getObjByName("discount").focus();
			return false;
		}else{
			getObjByName("totalPrice").value = getObjByName("count").value * (getObjByName("unitPrice").value) * (getObjByName("discount").value/100);
		}	
		
		if(!isDoubleNumber("totalPrice")){
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
	   var url = "${req.contextPath}/com/listProductsWindow.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorPrincipalHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorPrincipalHandler(result) {
		if (null != result) {
			getObjByName("productid").value= result[0];
			getObjByName("productName").value= result[2];
			getObjByName("salePrice").value= result[3];
			getObjByName("productModel").value= result[4];
		}
	}
</script>

</@htmlPage>
<#if productList.id?exists>
<ul id="beautytab">
	<#--
	<li>
		<a id="invoices " onclick="activeTab(this);" href='${req.contextPath}/contractManagement/listInvoices.html?contractManagement.id=#{contractManagementid?if_exists}&productList.id=#{productList.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('出库单明细')}</a>
	</li>
	-->
</ul>
<#--
<iframe name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="50%"/>
-->
</#if>