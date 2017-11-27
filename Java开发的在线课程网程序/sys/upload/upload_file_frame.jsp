<%@ page contentType = "text/html;charset=utf-8"%>
<html>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>批量导入</title>
<script language="javascript">
var FileObj,FileExt;//全局变量 图片相关属性
var DenyExt=".exe|.bat|"//禁用格式
var AllFileExt=".doc|.docx|.ppt|.pptx|.xlsx|.xls|.pdf|.txt|"//全部图片格式类型
function CheckProperty(obj){
  var ErrMsg="";
  FileObj=obj;
  if(obj.value=="")
   return false;

  FileExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
  if(AllFileExt.indexOf(FileExt+"|")==-1)  //判断文件类型是否允许上传
  {
    alert("不允许上传"+FileExt+"类型的文件,请上传其它格式的文件。");
    document.getElementById("uploadfile").value = "";
    return false;
  }
  /*else{
    //获取文件大小
  	var image=new Image();
	image.dynsrc=obj.value;
	var size = image.fileSize || obj.files[0].fileSize;
	if(size > 50 * 1024 * 1024){
		alert("请上传不大于50MB的资源,超过50MB请用'大文件上传'功能!");
		document.getElementById("uploadfile").value = "";
		return false;
	}
  }*/
  return true;
}

function CheckUploadFile(theobj){
 //检测文件属性
 if (!CheckProperty(theobj)){
    theobj.focus();
    document.pageForm.btnsave.disabled=true;
 }else{
     document.pageForm.btnsave.disabled=false;
 }
}

function SubmitForm(){
    document.pageForm.action="sysFileUploadAction.do?method=uploadfiledeal&savepath=<bean:write name="savepath"/>&foldertype=<bean:write name="foldertype"/>";
    document.pageForm.submit();
}
</script>
<body leftmargin="0" topmargin="20">
<table class="bg_basecolor" width="100%" valign="middle" border="0" cellpadding="0" cellspacing="0" align="center" height="100%">
  <tr>
    <td  align="center">
      <form method="POST" ENCTYPE="multipart/form-data" name="pageForm">
      <table width="95%" border="0" cellspacing="0" cellpadding="0">

       <tr>
          <td>目前只支持<font color="red">doc|docx|ppt|pptx|xlsx|xls|pdf|txt</font>等格式的附件.
          </td>
        </tr>
        <tr>
          <td height="5"></td>
        </tr>
           <tr>
          <td align="left"><b>请选择附件:</b></td>
       </tr>

       <tr>
          <td align="left"><input type="FILE" id="uploadfile" class="file" size="38" name="thefile" style="cursor:hand" onchange="javascript:CheckUploadFile(this)"  accept=".doc,.docx,.ppt,.pptx,.xlsx,.xls,.pdf,.txt" /></td>
        </tr>
         <tr  height="30">
            <td align="center">
             <input type="button" name="btnsave"  disabled="disabled" value="上传文件" class="button" onclick="javascript:return SubmitForm()">
             &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="Button" value="关闭窗口" class="button" onclick="javascript:window.parent.window.close();">
             </td>
          </tr>

      </table>
     </form>
    </td>
  </tr>
</table>
</html>
