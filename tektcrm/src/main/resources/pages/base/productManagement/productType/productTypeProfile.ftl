<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('productType.info.profile')}">
<@ww.form name="'listForm'"  namespace="'/productTypeManager'" action="'saveProductTypeAction'" method="'post'">
	<@ww.token name="saveProductTypeActionToken"/>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@inputTable>
		<#if productType.id?exists>
            <@ww.hidden name="'productType.id'" value="#{productType.id}"/>
        </#if>
         <@ww.hidden name="'productType.parentPTId'" value="'${req.getParameter('productType.parentPTId')?if_exists}'"/>
         <@ww.hidden name="'parentPTID'" value="'${req.getParameter('parentPTID')?if_exists}'"/>
		<tr>
            <@ww.textfield label="'${action.getText('productType.code')}'" name="'productType.code'" value="'${productType.code?if_exists}'" cssClass="'underline'" required="true" disabled="true"/>
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
		    	required="false"/>
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
            <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/productTypeManager/listProductType.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
	</@buttonBar>
</@ww.form>
</@htmlPage>
<script language="javascript">
	if(0== getObjByName('productType.parentPT').value){
		if(null != getObjByName('parentPTID').value && "" != getObjByName('parentPTID').value){
			getObjByName('productType.parentPT').value = getObjByName('parentPTID').value;
		}
 	}
	function storeValidation(){
		var name = getObjByName('productType.name').value;
		if(name==""){
			alert('${action.getText('productType.name.not.null')}');
			getObjByName('productType.name').focus();
			return false;
		}
		if(!isValidLength(document.forms[0], "productType.name", null, 20)){
			alert('${action.getText('productType.name.length')}');
			getObjByName('productType.name').value="";
			getObjByName('productType.name').focus();
			return  false;
		}
		return true;
	}
</script>