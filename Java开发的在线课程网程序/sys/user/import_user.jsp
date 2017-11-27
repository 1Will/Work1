<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html>

<HEAD>
<TITLE>大附件上传</TITLE>

<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<script type="text/javascript">
function saveRecord(){
  obj = document.all("fileForm");
  if(autoCheckForm(obj)==false){
    return false;
  }
  
  obj.action="/sysUserInfoAction.do?method=importFile";
  obj.submit();
}
</script>
<script type="text/javascript">
var FileObj,FileExt;//全局变量
var DenyExt=".xls|.xlsx|"//允许上传格式

function CheckProperty(obj){
  var ErrMsg="";
  FileObj=obj;
  if(obj.value=="")
   return false;

  FileExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
  if(DenyExt.indexOf(FileExt+"|")==-1)  //判断文件类型是否允许上传
  {
    alert("不允许上传"+FileExt+"类型的文件,请上传 "+DenyExt+" 类型的文件。");
    document.getElementById("uploadfile").value = "";
    return false;
  }
  return true;
}

function CheckUploadFile(theobj){
 //检测文件属性
 if (!CheckProperty(theobj)){
    theobj.focus();
    document.fileForm.btnsave.disabled=true;
 }
 else{
     document.fileForm.btnsave.disabled=false;
 }
}
function selectSchool(){
	var diag = new top.Dialog();
	diag.Title = "选择学校";
	diag.URL = '/v.bo?method=selectSchoolList';
	diag.Width = 810;
	diag.Height = 600;
	diag.CancelEvent = function(){
		if(diag.innerFrame.contentWindow.document.getElementById('id')){
			var id = diag.innerFrame.contentWindow.document.getElementById('id').value;
			var name = diag.innerFrame.contentWindow.document.getElementById('name').value;
			document.getElementById("unitid").value=id;
			document.getElementById("unitname").value=name;
		}
		diag.close();
	};
	diag.show();
}
</script>
</HEAD>

<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">批量导入用户</TD>
  </TR>
  <TR>
    <TD vAlign=top align=middle>
      <form action="/sysUserInfoAction.do" name="fileForm" method="post" enctype="multipart/form-data">
       <TABLE width="500" border=0 cellpadding=0 cellspacing=0 align="center">
          <tr><td colspan="2" height="20"></td></tr>
          <logic:equal value="12" name="s_unitid" scope="session">
          <tr>
              <td class="table_edit_right">所属学校：</td>
              <td class="table_edit_left">
              <input name="unitname" id="unitname" type="text" value="" onclick="selectSchool()" style="width:310px;" readonly="readonly"/>&nbsp;<a href="javascript:selectSchool()">[选择学校]</a>
			  <input name="unitid" id="unitid" type="hidden" value="" />
              </td>
          </tr>
          </logic:equal>
          <tr>
            <td class="table_edit_right">选择文件：</td>
            <td class="table_edit_left">
            	<input type="file" name="formFile" id="uploadfile" CK_NAME="上传文件" CK_TYPE="NotEmpty" style="cursor:hand;background-color:#f5f5f5;color:#777777;width:310px;" onchange="javascript:CheckUploadFile(this)">&nbsp;<a href="/sys/user/user.xls" target="_blank">下载模板</a>
            </td>
          </tr>
          <tr><td colspan="2" height="30"></td></tr>
          <tr>
          	<td colspan="2" height="40" class="table_edit_left" style="text-align:center;"><input type="button" value="&nbsp;导&nbsp;入&nbsp;" id="btnsave" name="btnsave" class="btn_save" onClick="saveRecord()"></td>
          </tr>
       </TABLE>
      </form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html>
