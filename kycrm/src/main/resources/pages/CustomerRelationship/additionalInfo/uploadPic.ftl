<#include "../../includes/macros.ftl" />

<@framePage>
  <@ww.form namespace="'/customerRelationship'" name="'cusAdditionalInfo'" action="'saveAdditionalUploadPic'" enctype="'multipart/form-data'" method="'post'" validate="true">
  <@ww.token name="savecusAdditionalInfoToken"/>
    <@inputTable>
    <#if cusAdditionalInfo.id?exists>
    	<@ww.hidden name="'cusAdditionalInfo.id'" value="#{cusAdditionalInfo.id?if_exists}"/>
    </#if>
	<@ww.hidden name="'customerInfo.id'" value="'#{customerInfo.id?if_exists}'" />
      <tr>
        <@ww.file label="'${action.getText('upload.logo')}'" size="60" name="'file'"  required="true" />
        <@ww.file label="'${action.getText('upload.background')}'" size="60" name="'file_1'"  required="true" />
      </tr>
    </@inputTable>
    <@buttonBar>
	  <@vsubmit name="'upload'" value="'${action.getText('upload')}'" onclick="'return getFileName();'"/>
	</@buttonBar>
	<@ww.hidden name="'logoFile'" value=""/>
	<@ww.hidden name="'savePicName'" value="''"/>
	<@ww.hidden name="'savePicName_1'" value="''"/>
	  <script language="javascript">
	    function getFileName() {
	      var filename = document.forms["cusAdditionalInfo"].elements["file"].value;
	      var filename1 = document.forms["cusAdditionalInfo"].elements["file_1"].value;
	      vailName(filename,0);
	      vailName(filename1,1);
		}
		
		function vailName(filename,i){
		   var ary = filename.split("\\");
		  //如果是新建，则要求上传文件必须选择
		  <#if cusAdditionalInfo.new>
		    if (filename == '' || ary.length <= 0) { 
		      alert("${action.getText('please.choose.file')}");
			  return false;
		    }
		  </#if>
		  
		  if(!validatePic(filename,i)){
		  	alert("${action.getText('upload.validate.pic')}");
		  	return false;
		  }
		  
		  document.forms["cusAdditionalInfo"].elements["logoFile"].value=ary[ary.length-1];
		  return true;
		}
		
		//验证文件类型是否为图片
	    function validatePic(filename,i) {
	    	var arr = filename.split(".");
	    	var _name = arr[arr.length-1];
	    	var savePicN="."+_name;
	    	if(i==0){
	    	  getObjByName('savePicName').value=savePicN;
	    	}
	    	if(i==1){
	    	 getObjByName('savePicName_1').value=savePicN;
	    	}
	    	 
		 	if(_name == "jpg" || _name == "png" || _name == "gif"){
		 		return true;
		 	}
		 	return false;
		}
      </script>
  </@ww.form>
</@framePage>