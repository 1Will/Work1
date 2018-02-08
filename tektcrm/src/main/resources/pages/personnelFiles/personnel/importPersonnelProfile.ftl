<#include "../../includes/hco2011.ftl" />

<@htmlPage title="人事档案导入">
  <@ww.form  name="'importPersonnelForm'" action="'importPersonnel'" enctype="'multipart/form-data'" method="'post'" validate="true">
  <@ww.token name="importPersonnelToken"/>
    <@inputTable>
      <tr>
        <@ww.file label="'${action.getText('Personnel.upload')}'" size="60" name="'file'"  required="true" />
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