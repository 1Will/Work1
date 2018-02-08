<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('uploadDocument.title')}">
  <@ww.form namespace="'/base/document'" name="'document'" action="'saveDocument'" enctype="'multipart/form-data'" method="'post'" validate="true">
  <@ww.token name="saveModifyDocumentToken"/>
    <@inputTable>
    <#if document.id?exists>
    	<@ww.hidden name="'document.id'" value="#{document.id?if_exists}"/>
    </#if>
    <#if document.id?exists>
    <#else>
      <tr>
        <@ww.file label="'${action.getText('document.upload')}'" size="60" name="'file'" cssClass="'button'" required="true" onchange="'getName();'"/>
      </tr>
    </#if>
      <tr>
        <@ww.textfield label="'${action.getText('document.name')}'" size="50" name="'document.name'" value="'${document.name?if_exists}'" cssClass="'underline'" required="true"/>
      </tr>
      <tr>
        <@ww.select label="'${action.getText('document.category')}'"  name="'category.id'"  listKey="id" listValue="value"
    			 value="${req.getParameter('category.id')?if_exists}"
                list="categorys" emptyOption="true" disabled="false">
        </@ww.select>
      </tr>
      <tr>
	    <@ww.textarea label="'${action.getText('document.comment')}'" 
					  name="'document.description'" 
					  value="'${document.description?if_exists}'" rows="'4'" cols="'80'" 
					  required="false"/>
      </tr>
    </@inputTable>
    <@buttonBar>
    <#if document.id?exists>
      <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return chageAction();'"/>
	<#else>
	  <@vsubmit name="'upload'" value="'${action.getText('upload')}'" onclick="'return getFileName();'"/>
	</#if>
	  <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/base/document/listDocuments.html"/>
	</@buttonBar>
	<@ww.hidden name="'origFileName'" value=""/>
	  <script language="javascript">
	    function getFileName() {
	      var filename = document.forms["document"].elements["file"].value;
		  ary = filename.split("\\");
		  if (filename == '' || ary.length<=0) { 
		    alert("${action.getText('please.choose.file')}");
			return false;
		  }
		  document.forms["document"].elements["origFileName"].value=ary[ary.length-1];
		  return true;
		}
		
		function chageAction() {
		  document.forms["document"].action="saveModifyDocument.html";
		  return true;
		}
		
		/*function getName() {
		  var filename = document.forms["document"].elements["file"].value;
		  var ary = filename.split("\\");
		  var ary1 = ary[ary.length-1].split("\.");
		  document.forms["document"].elements["applicationDoc.name"].value=ary1[0];
		  return true;
		  
		}*/
		function getName() {
		  getFileNameByFile(document.forms["document"],"document.name");
		}
	 <#if document.category?exists>
	   document.forms["document"].elements["category.id"].value = #{document.category.id?if_exists};
	 </#if>
      </script>
  </@ww.form>
</@htmlPage>