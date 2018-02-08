<#include "../../includes/macros2.ftl" />

<@htmlPage  title='产品列表'>
 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
 <script language="JavaScript" type="text/JavaScript"> 
	function supplier_OpenDialog() {
		popupModalDialog('${req.contextPath}/pro/supplier/supplierLevelBaseInfo.html');
	}

</script>
<@inputTable>
<tr>
<@ww.textfield label="'项目号'" name="'suppliersDevice.lx'" value="" cssClass="'underline'" required="true"/>	
<@deviceLb />
</tr>
<tr>
<@ww.textfield label="'产品名称'" name="'suppliersDevice.name'" value="" cssClass="'underline'" required="true"/>	
<@ww.textfield label="'订货日期'" name="'suppliers.xz'" value="" cssClass="'underline'" required="true"/>	
</tr>
<tr>	
<@ww.textfield label="'保质期'" name="'suppliers.xz'" value="" cssClass="'underline'" required="true"/>	
</tr>
</@inputTable>
 <@buttonBar>
     	<@vbutton class="button" value="保存" onclick="javascript:void(0);"/>
     	<@vbutton class="button" value="关闭" onclick="javascript:window.close();"/>
     </@buttonBar>
 </@ww.form>
</@htmlPage>
