<#include "../../includes/hco2011.ftl" />

<@htmlPage title="人事导入">
  <@ww.form  name="'importCustomerInfoForm'" action="'importCustomerInfo'" enctype="'multipart/form-data'" method="'post'" validate="true">
  <@ww.token name="importCustomerInfoToken"/>
    <@inputTable>
      <tr>
      ${message}
      </tr>
    </@inputTable>
    <@buttonBar>
	  <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/productsManager/toImportProduct.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
	</@buttonBar>
  </@ww.form>
</@htmlPage>