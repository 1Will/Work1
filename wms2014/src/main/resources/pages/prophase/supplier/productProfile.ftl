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
<#-- $Id: -->

<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('product.title1')}">
 <base target="_self">
	<@ww.form name="'newProduct'" action="'saveSupplierProduct'" method="'post'" validate="true">
		<@ww.token name="saveSupplierProduct"/>
		<@ww.hidden name="'supplier.id'" value="'${req.getParameter('supplier.id')?if_exists}'"/>
	 	<@ww.hidden name="'product.id'" value="'${product.id?if_exists}'"/>
	 	<@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
		<@inputTable>
			<tr>
			    <@ww.textfield readonly="false" label="'${action.getText('productname')}'" name="'product.name'" value="'${product.name?if_exists}'"  cssClass="'underline'" required="true"/>
			    <@ww.textfield readonly="false" label="'${action.getText('product.category')}'" name="'product.category'" value="'${product.category?if_exists}'" cssClass="'underline'"/>
			</tr>
			<tr>
			    <@ww.textfield readonly="false" label="'${action.getText('product.modelNo')}'" name="'product.modelNo'" value="'${product.modelNo?if_exists}'"  cssClass="'underline'"/>
			    <@ww.textfield readonly="false" label="'${action.getText('product.spec')}'" name="'product.spec'" value="'${product.spec?if_exists}'" cssClass="'underline'"/>
			</tr>
			<tr>
			    <@ww.textfield readonly="false" label="'${action.getText('product.qos')}'" name="'product.qos'" value="'${product.qos?if_exists}'"  cssClass="'underline'" required="true"/>
			    <@ww.textfield readonly="false" label="'${action.getText('product.preOrderDay')}'" name="'product.preOrderDay'" value="'${product.preOrderDay?if_exists}'" required="true" cssClass="'underline'"/>
			</tr>
		</@inputTable>
		<@buttonBar>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validateSmallInt();'"/>
		    <@vbutton class="button" value="${action.getText('close')}" onclick="window.close();"/>
		</@buttonBar>
	</@ww.form>
	 <script language="JavaScript" type="text/JavaScript"> 
	 function validateSmallInt(){
	 if(getObjByNameRe("product.name").value==''){
	    alert('${action.getText('product.name')}');
	    return false;
	  }else{
	      if(!isValidLength(document.forms[0], "product.name", null, 50)){
				alert("${action.getText('product.name.MaxLength')}");
				return  false;
			   }   
	    }
	if(getObjByNameRe("product.category").value!='')	{
	if(!isValidLength(document.forms[0], "product.category", null, 50)){
			alert("${action.getText('product.category.length')}");
			return  false;
			   }
	}
	if(getObjByNameRe("product.modelNo").value!='')	{
	if(!isValidLength(document.forms[0], "product.modelNo", null, 50)){
			alert("${action.getText('product.modelNo.length')}");
			return  false;
			   }
	} 
	
	if(getObjByNameRe("product.spec").value!='')	{
	if(!isValidLength(document.forms[0], "product.spec", null, 50)){
			alert("${action.getText('product.spec.length')}");
			return  false;
			   }
	}   
	 
    
    var number = document.forms[0].elements["product.qos"].value;
       if ('' == number) {            //验证是否为空
         alert("${action.getText('product.qos.required')}");
         return false;
       } else if (!isDoubleNumber("product.qos")){   //验证格式
         alert("${action.getText('number1.format.error')}");
         return false;
       } else if (!isNumberBetweenBoolen(number, 1000000001, 0)){ //验证范围
         alert("${action.getText('number1.format.error')}");
         return false;
       }
       var number = document.forms[0].elements["product.preOrderDay"].value;
       if ('' == number) {            //验证是否为空
         alert("${action.getText('product.preOrderDay.requried')}");
         return false;
       } else if (!isDoubleNumber("product.preOrderDay")){   //验证格式
         alert("${action.getText('product.preOrderDay.format.error')}");
         return false;
       } else if (!isNumberBetweenBoolen(number, 1000000001, 0)){ //验证范围
         alert("${action.getText('product.preOrderDay.format.error')}");
         return false;
       } 
      　
	   return true;
	 }
	 </script>
</@htmlPage>