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

<#-- $Id: productProfile.ftl 2009-11-27 11:20:35Z wliu $ -->

<#include "../../../includes/macros.ftl" />

<@htmlPage title="${action.getText('products.info.profile')}">
	<@ww.form namespace="'/productsManager'" name="'listForm'" action="'saveProductsAction'" method="'post'">
		<@ww.token name="saveProductsActionToken"/>
		<@inputTable>
			<#if products.id?exists>
                <@ww.hidden name="'products.id'" value="#{products.id}"/>
            </#if>
            <@ww.hidden name="'isNoM'" value="1"/>
			<tr>
				<#if products.new>
	            	<@ww.textfield label="'${action.getText('products.code')}'" name="'products.code'" value="'${products.code?if_exists}'" cssClass="'underline'" required="true"/>
	            	<#else>
	            	<@ww.textfield label="'${action.getText('products.code')}'" name="'products.code'" value="'${products.code?if_exists}'" cssClass="'underline'" readonly="true" disabled="true" required="true"/>
	            </#if>
				<@ww.textfield label="'${action.getText('products.name')}'" name="'products.name'" value="'${products.name?if_exists}'" cssClass="'underline'" required="true"/>
				<@ww.textfield label="'${action.getText('products.model')}'" name="'products.model'" value="'${products.model?if_exists}'" cssClass="'underline'" required="true"/>
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('products.standard')}'" name="'products.standard'" value="'${products.standard?if_exists}'" cssClass="'underline'" required="true"/>
				<@ww.textfield label="'${action.getText('products.etcPrice')}'" name="'products.etcPrice'" value="'${products.etcPrice?if_exists}'" cssClass="'underline'"/>
				<@ww.textfield label="'${action.getText('products.salePrice')}'" name="'products.salePrice'" value="'${products.salePrice?if_exists}'" cssClass="'underline'"/>
			</tr>
			
			<tr>
				<@ww.textfield label="'${action.getText('products.salelimit')}'" name="'products.salelimit'" value="'${products.salelimit?if_exists}'" cssClass="'underline'"/>
			    <@ww.select label="'${action.getText('products.pt.id')}'" 
		           name="'pt.id'" 
			       value="'${req.getParameter('pt.id')?if_exists}'" 
			       listKey="id" 
			       listValue="name"
		           list="allType" 
		           emptyOption="false" 
		           disabled="false"
		           required="true">
		           </@ww.select>
			     <script language="javascript">
			     	<#if products.pt?exists>
			     		getObjByName('pt.id').value = ${products.pt.id};
			     	</#if>
				</script>
				<@ww.select 
		    		label="'${action.getText('products.productSource')}'"
					required="true"
					name="'products.productSource'" 
					value="${req.getParameter('productSource')?if_exists}" 
					headerKey="1"
					headerValue="selectedType"
				    list="{
				    	'',
						'自主研发',
						'代理',
						'外包'
						}"
			    	emptyOption="false" 
			    	disabled="false"/>
			</tr>
			
			<tr>
				<@ww.select 
		    		label="'${action.getText('products.supplier')}'"
					required="true"
					name="'products.supplier'" 
					value="${req.getParameter('products.supplier')?if_exists}" 
					headerKey="1"
					headerValue="selectedType"
				    list="{
				    	'',
						'A供应商',
						'B供应商'
						}"
			    	emptyOption="false" 
			    	disabled="false"/>
			    	<@ww.checkbox 
			    		label="'${action.getText('products.isNoMain')}'" 
			    		name="'products.isNoMain'"
			    		value="'true'"
			    		/>
			    <script language="javascript">
			     		if(${(products.isNoMain?string)?if_exists}==true){
			     			getObjByName('products.isNoMain').checked = true;
			     		}else if(${(products.isNoMain?string)?if_exists}==false){
			     			getObjByName('products.isNoMain').checked = false;
			     		}
				</script>
			</tr>
		</@inputTable>
		<@buttonBar>
        <#if !(action.isReadOnly())>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'">
            </@vsubmit>
        </#if>
            <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/productsManager/listProducts.html"/>
	</@buttonBar>
	</@ww.form>
</@htmlPage>
<script language="javascript">
	function storeValidation(){
		var code = getObjByName('products.code').value;
		var name = getObjByName('products.name').value;
		var model = getObjByName('products.model').value;
		var standard = getObjByName('products.standard').value;
		var etcPrice = getObjByName('products.etcPrice').value;
		var salePrice = getObjByName('products.salePrice').value;
		var salelimit = getObjByName('products.salelimit').value;
		if(code==""){
			alert('${action.getText('products.code.not.null')}');
			return false;
		}
		if(!isValidLength(document.forms[0], "products.code", null, 20)){
			alert('${action.getText('products.code.length')}');
			return  false;
		}
		if(name==""){
			alert('${action.getText('products.name.not.null')}');
			return false;
		}
		if(!isValidLength(document.forms[0], "products.name", null, 20)){
			alert('${action.getText('products.name.length')}');
			return  false;
		}
		if(model==""){
			alert('${action.getText('products.model.not.null')}');
			return false;
		}
		if(!isValidLength(document.forms[0], "products.model", null, 20)){
			alert('${action.getText('products.model.length')}');
			return  false;
		}
		if(standard==""){
			alert('${action.getText('products.standard.not.null')}');
			return false;
		}
		if(!isValidLength(document.forms[0], "products.standard", null, 20)){
			alert('${action.getText('products.standard.length')}');
			return  false;
		}
		if(isNaN(etcPrice)){
			alert('${action.getText('etcPrice.not.nan')}');
			return false;
		}
		if(isNaN(salePrice)){
			alert('${action.getText('salePrice.not.nan')}');
			return false;
		}
		if(isNaN(salelimit)){
			alert('${action.getText('salelimit.not.nan')}');
			return false;
		}
		if(getObjByName('pt.id').value==-1){
			alert('${action.getText('pt.id.null')}');
			return false;
		}
		if (document.forms[0].elements["products.isNoMain"].checked) {
       		document.forms[0].elements["isNoM"].value=1;
	    } else {
	        document.forms[0].elements["isNoM"].value = 0;
	    }
		return true;
	}
</script>