<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('uploadManual.title')}">
  <@ww.form namespace="'/base/manual'" name="'manual'" action="'saveManual'" enctype="'multipart/form-data'" method="'post'" validate="true">
  <@ww.token name="saveManualToken"/>
    <@inputTable>
    <#if manual.id?exists>
    	<@ww.hidden name="'manual.id'" value="#{manual.id?if_exists}"/>
    </#if>

      <tr>
        <@ww.file label="'${action.getText('manual.upload')}'" size="60" name="'file'" cssClass="'button'" required="true" onchange="'getName();'"/>
      </tr>
      <tr>
        <@ww.textfield label="'${action.getText('helpManual.name')}'" size="50" name="'manual.name'" value="'${manual.name?if_exists}'" cssClass="'underline'" required="true"/>
      </tr>
      <tr>
        <@ww.textfield label="'${action.getText('helpManual.vesion')}'" size="50" name="'manual.manualVersion'" value="'${manual.manualVersion?if_exists}'" cssClass="'underline'" required="true"/>
      </tr>
      <tr>
	    <@ww.textarea label="'${action.getText('helpManual.description')}'" 
					  name="'manual.description'" 
					  value="'${manual.description?if_exists}'" rows="'4'" cols="'80'" 
					  required="false"/>
      </tr>
    </@inputTable>
    <@buttonBar>
	  <@vsubmit name="'upload'" value="'${action.getText('upload')}'" onclick="'return getFileName();'"/>
	  <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/base/manual/listManuals.html"/>
	</@buttonBar>
	<@ww.hidden name="'origFileName'" value=""/>
	  <script language="javascript">
	    function getFileName() {
	      var filename = document.forms["manual"].elements["file"].value;
		  ary = filename.split("\\");
		  //如果是新建，则要求上传文件必须选择
		  <#if manual.new>
		    if (filename == '' || ary.length<=0) { 
		      alert("${action.getText('please.choose.file')}");
			  return false;
		    }
		  <#else>                   //如果不是新建，如果上传文件选择了，则提示是否覆盖原来的文件
		    if (filename != '' && ary.length>0) { 
		      if (!confirm("${action.getText('confirm.overwrite.old.file')}")) {
		         return false;
		      }
		    }
		  </#if>
		  //验证手册名称
		  if (!validateManualDocName()) {
		    return false;
		  }
		  //验证版本号
		  if (!validateManualDocVersion()) {
		    return false;
		  }
		  //验证手册描述
		  if (!validateDescription()) {
		    return false;
		  }
		  document.forms["manual"].elements["origFileName"].value=ary[ary.length-1];
		  return true;
		}
		function getName() {
		  getFileNameByFile(document.forms["manual"],"manual.name");
		}
		//验证版本号是否为空,以及长度
		function validateManualDocVersion() {
		  if ('' == document.forms["manual"].elements["manual.manualVersion"].value) {
		     alert("${action.getText('manual.manualVersion.required')}");
		     return false;
		  } else if (!isValidLength(document.forms[0], "manual.manualVersion", null, 150)) {
		    alert("${action.getText('manual.manualVersion.length')}");
		    return false;
		  }
		  return true;
		}
		//验证手册名称是否为空,以及长度
	    function validateManualDocName() {
		  if ('' == document.forms["manual"].elements["manual.name"].value) {
		     alert("${action.getText('manual.name.required')}");
		     return false;
		  } else if (!isValidLength(document.forms[0], "manual.name", null, 150)) {
		    alert("${action.getText('manual.name.length')}");
		    return false;
		  }
		  return true;
		}
		//验证手册描述的长度
		function validateDescription() {
		  if ('' != document.forms["manual"].elements["manual.description"].value) {
		    if (!isValidLength(document.forms[0], "manual.description", null, 250)) {
		      alert("${action.getText('manual.description.length')}");
		      return false;
		    }
		  }
		  return true;
		}
      </script>
  </@ww.form>
</@htmlPage>