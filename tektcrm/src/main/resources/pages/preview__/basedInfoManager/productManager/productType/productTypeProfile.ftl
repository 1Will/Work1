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

<#-- $Id: productTypeProfile.ftl 2009-11-27 10:40:35Z wliu $ -->

<#include "../../../includes/macros.ftl" />

<@htmlPage title="${action.getText('productType.info.profile')}">
<@ww.form name="'listForm'"  namespace="'/productTypeManager'" action="'saveProductTypeAction'" method="'post'">
	<@ww.token name="saveProductTypeActionToken"/>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@inputTable>
		<#if productType.id?exists>
            <@ww.hidden name="'productType.id'" value="#{productType.id}"/>
        </#if>
         <@ww.hidden name="'productType.parentPTId'" value="'${req.getParameter('productType.parentPTId')?if_exists}'"/>
        <#--
        <@ww.hidden name="'keepPTValue'" value="'${req.getParameter('keepPTValues')?if_exists}'" />
        -->
		<tr>
			<#if productType.new>
            	<@ww.textfield label="'${action.getText('productType.code')}'" name="'productType.code'" value="'${productType.code?if_exists}'" cssClass="'underline'" required="true"/>
            	<#else>
            	<@ww.textfield label="'${action.getText('productType.code')}'" name="'productType.code'" value="'${productType.code?if_exists}'" cssClass="'underline'" readonly="true" disabled="true" required="true"/>
            </#if>
			<@ww.textfield label="'${action.getText('productType.name')}'" name="'productType.name'" value="'${productType.name?if_exists}'" cssClass="'underline'" required="true"/>
			<@ww.select 
	    		label="'${action.getText('productType.parentType')}'"
				required="false"
				name="'productType.parentPT'" 
				value="'${req.getParameter('productType.parentPT')?if_exists}'" 
				listKey="id"
				listValue="name"
			    list="allParentPT"
		    	emptyOption="false" 
		    	disabled="false"
		    	required="true"/>
      		 <script language="javascript">
				if('' != getObjByName('productType.parentPTId').value){
					getObjByName('productType.parentPT').value = getObjByName('productType.parentPTId').value;
				}
      			<#if productType.parentPT?exists>
      				document.forms[0].elements["productType.parentPT"].value = #{productType.parentPT.id};
      		    <#elseif !productType.new>
      		        document.forms[0].elements["productType.parentPT"].value = '0';
      			</#if>
      		 </script>
		</tr>
	</@inputTable>
	<@buttonBar>
        <#if !(action.isReadOnly())>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'">
            </@vsubmit>
        </#if>
            <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/productTypeManager/listProductType.html"/>
	</@buttonBar>
</@ww.form>
</@htmlPage>
<script language="javascript">
	function storeValidation(){
		var code = getObjByName('productType.code').value;
		var name = getObjByName('productType.name').value;
		if(code==""){
			alert('${action.getText('productType.code.not.null')}');
			return false;
		}
		if(!isValidLength(document.forms[0], "productType.code", null, 20)){
			alert('${action.getText('productType.code.length')}');
			return  false;
		}
		if(name==""){
			alert('${action.getText('productType.name.not.null')}');
			return false;
		}
		if(!isValidLength(document.forms[0], "productType.name", null, 20)){
			alert('${action.getText('productType.name.length')}');
			return  false;
		}
		return true;
	}
</script>