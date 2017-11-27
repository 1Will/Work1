<#include "../../../includes/macros.ftl" />

<@htmlPage title="产品导入">
  <@ww.form  name="'importProductForm'" action="'importProduct'" enctype="'multipart/form-data'" method="'post'" validate="true">
  <@ww.token name="importProductToken"/>
    <@inputTable>
      <tr>
        <@ww.file label="'${action.getText('Product.upload')}'" size="60" name="'file'"  required="true" />
      </tr>
    </@inputTable>
    <@buttonBar>
	  <@vsubmit  name="'upload'" value="'${action.getText('upload')}'"  />
	  <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/customerRelationship/listProducts.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
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