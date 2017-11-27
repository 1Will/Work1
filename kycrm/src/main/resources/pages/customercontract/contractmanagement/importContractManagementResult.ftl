<#include "../../includes/hco2011.ftl" />

<@htmlPage title="合同导入">
  <@ww.form  name="'importContractManagementForm'" action="'importContractManagement'" enctype="'multipart/form-data'" method="'post'" validate="true">
  <@ww.token name="importContractManagementToken"/>
    <@inputTable>
      <tr>
      ${message}
      </tr>
    </@inputTable>
    <@buttonBar>
	  <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/contractManagement/toImportContractManagement.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
	</@buttonBar>
  </@ww.form>
</@htmlPage>