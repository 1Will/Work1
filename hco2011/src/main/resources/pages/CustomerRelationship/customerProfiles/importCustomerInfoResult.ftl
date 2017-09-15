<#include "../../includes/macros.ftl" />

<@htmlPage title="客户导入">
  <@ww.form  name="'importCustomerInfoForm'" action="'importCustomerInfo'" enctype="'multipart/form-data'" method="'post'" validate="true">
  <@ww.token name="importCustomerInfoToken"/>
    <@inputTable>
      <tr>
      ${message}
      </tr>
    </@inputTable>
    <@buttonBar>
	  <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/customerRelationship/toImportCustomerInfo.html"/>
	</@buttonBar>
  </@ww.form>
</@htmlPage>