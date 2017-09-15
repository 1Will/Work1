<#include "../../../includes/macros.ftl" />

<@htmlPage title="产品导入">
  <@ww.form  name="'importCustomerInfoForm'" action="'importCustomerInfo'" enctype="'multipart/form-data'" method="'post'" validate="true">
  <@ww.token name="importCustomerInfoToken"/>
    <@inputTable>
      <tr>
      ${message}
      </tr>
    </@inputTable>
    <@buttonBar>
	  <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/productsManager/toImportProduct.html"/>
	</@buttonBar>
  </@ww.form>
</@htmlPage>