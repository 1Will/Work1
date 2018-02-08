<#--$Id: productProfile.ftl 11310 2008-03-13 05:58:54Z wzou $ -->
<#include "../../includes/macros.ftl" />
<@htmlPage title="${action.getText('productEdit.title')}">
    <@ww.form namespace="'/base/product'" name="'product'" action="'saveProduct'" method="'post'" validate="true">
        <@ww.token name="saveProductToken"/>
        <@inputTable>
            <#if product.id?exists>
                <@ww.hidden name="'product.id'" value="#{product.id}"/>
            </#if>
            <@ww.hidden name="'product.version'" value="#{product.version?if_exists}"/>
            <tr>
                <@ww.textfield label="'${action.getText('product.productNo')}'" name="'product.productNo'" value="'${product.productNo?if_exists}'" cssClass="'underline'" required="true"/>
                <@ww.textfield label="'${action.getText('product.name')}'" name="'product.name'" value="'${product.name?if_exists}'"  size="32"  cssClass="'underline'" required="true"/>
            </tr>
        </@inputTable>
        <@buttonBar>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate()'"/>
               <#-- <#if product.id?exists>
                  <#if !(product.disabled)>
                    <#assign confirmMessage = "${action.getText('confirm.invalid')}${action.getText('product')}?" />
                    <@vsubmit name="'disabled'" value="'${action.getText('invalidation')}'">
                    <#if product.id?exists>
                        <@ww.param name="'onclick'" value="'return confirmDelete(\"${confirmMessage}\");'"/>
                    </#if>
                    </@vsubmit>
                  <#else>
                    <@vsubmit name="'enabled'" value="'${action.getText('enabled')}'" />
                  </#if>
                
                <@ww.param name="'disabled'" value="${(!product.id?exists)?string}"/>
                
               </#if>-->
            <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/base/product/listProducts.html"/>
        </@buttonBar>
    </@ww.form>
    <script language="JavaScript" type="text/JavaScript"> 
	  /*
	   *当该产品失效时，该页面所有控件，除有效、返回按钮，其他都失效
	  */
	　<#if product.disabled?exists>
	 　　<#if (product.disabled)>
	      __disableAllElements__(document.forms[0], new Array("enabled", "back"));
	    </#if>
	 </#if>
	 function validate() {
		 /*
		   *验证产品维护编码是否为空，以及长度是否溢出
		  */
		  if ('' == $('product.productNo').value) {       
		    alert("${action.getText('product.productNo.requiredstring')}");
		    return false;
		  } else {
		    if (!isValidLengthValue($('product.productNo').value,0,30)) {
		      alert("${action.getText('product.productNo.maxLength')}");
		      return false;
		    }
		  }
		  
		  /*
		   *验证产品维护名称是否为空，以及长度是否溢出
		  */
		  if ('' == $('product.name').value) {        
		    alert("${action.getText('product.name.requiredstring')}");
		    return false;
		  } else {
		    if (!isValidLengthValue($('product.name').value,0,30)) {
		      alert("${action.getText('product.name.maxLength')}");
		      return false;
		    }
		  }
	  }
	</script> 
</@htmlPage>