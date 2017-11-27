<#include "../../includes/macros.ftl" />

<@htmlPage title="客户导入">
  <@ww.form  name="'importCustomerInfoForm'" action="'importCustomerInfo'" enctype="'multipart/form-data'" method="'post'" validate="true">
  <@ww.token name="importCustomerInfoToken"/>
    <@inputTable>
      <tr>
        <@ww.file label="'${action.getText('customerInfo.upload')}'" size="60" name="'file'"  required="true" />
      </tr>
    </@inputTable>
    <@buttonBar>
    <#if !(action.isReadOnly())>
	  <@vsubmit  name="'upload'" value="'${action.getText('upload')}'"  />
    </#if>
	  <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/customerRelationship/listCustomerInfo.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
	</@buttonBar>
	  <script language="javascript">
	    function getFileName() {
	      var filename = document.forms["manual"].elements["file"].value;
		  ary = filename.split("\\");
		    if (filename == '' || ary.length<=0) { 
		      alert("${action.getText('please.choose.file')}");
			  return false;
		    }
		    }
		 
      </script>
  </@ww.form>
</@htmlPage>