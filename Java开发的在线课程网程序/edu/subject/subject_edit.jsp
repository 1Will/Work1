<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<%@ page import="com.bzt.vod.bo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新建学科</title>
<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript"  src="/public/js/comm.js"></Script>
<script language="Javascript">
function saveRecord(){
     var subjectname=document.getElementById("subjectname");
     var fullname=document.getElementById("fullname");
     var orderindex=document.getElementById("orderindex");
      obj=document.eduSubjectInfoActionForm;
      if(subjectname.value==""){
        top.Dialog.alert("请填写学科名称！");
        return false;
       }
      if(subjectname.value.length>15){
        top.Dialog.alert("学科名称字数长度不得超过15！");
        return false;
      }
      if(fullname.value==""){
        top.Dialog.alert("请填写学科全称！");
        return false;
      }
      if(fullname.value.length>15){
        top.Dialog.alert("学科全称字数长度不得超过15！");
        return false;
      }
      if(orderindex.value==""){
        top.Dialog.alert("请填写排序！");
        return false;
      }
      if(isNaN(orderindex.value)){
          top.Dialog.alert("排序必须为数字！");
          return false;
      }
      obj.action="/eduSubjectInfoAction.do?method=${act}";
      obj.submit();
}

function showDialog(url,name){
	var diag = new top.Dialog();
	diag.Title = "上传图片";
	diag.URL = '/sysImageUploadAction.do?method=uploadimageframe&savepath=vod&pathtype=ID';
	diag.Width = 350;
	diag.Height = 180;
	diag.CancelEvent = function(){
		if(diag.innerFrame.contentWindow.document.getElementById('uploadimageurl')){
			var uploadtip = diag.innerFrame.contentWindow.document.getElementById('uploadtip').value;
			if(uploadtip != ''){
		  	 	alert(uploadtip);
		  		return false;
		  	}else{
		  		var uploadimageurl = diag.innerFrame.contentWindow.document.getElementById('uploadimageurl').value;
				document.getElementById("topreview").src="/upload_dir/"+uploadimageurl;
			    document.getElementById('banner').value=uploadimageurl;
		  	}
		}
		diag.close();
	};
	diag.show();

}
</script>
  <head>
  <body>
     <html:form action="/eduSubjectInfoAction.do" method="post" >
      <input type="hidden" name="subjectid" value='<bean:write property="subjectid"  name="model"/>'/>
    <table class="page_maintable">
    <tr>
     <td class="page_title"><bean:write name="showtitle" /></td>
    </tr>
    <tr>
    <td valign="top" align="center">
    <table width="650" border="0" align="center" cellpadding=0 cellspacing=0>
      <tr>
       <td class="table_edit_right" width="150">学科名称：</td>
       <td class="table_edit_left" width="100">
       <input type="text" style="width: 200px;" name="subjectname"  id="subjectname" size="60" tabindex="1" maxlength="50" title="请输入学科名称" value='<bean:write property="subjectname"  name="model"/>'/>&nbsp;*
     </td>
          </tr>
        <tr>
        <td class="table_edit_right" width="150">学科全称：</td>
        <td class="table_edit_left" width="100">
         <input type="text" style="width: 200px;" name="fullname" id="fullname" size="60" tabindex="1" maxlength="50" title="请输入学科全称" value='<bean:write property="fullname"  name="model"/>' />&nbsp;*
        </td>
        </tr>  
             <tr>
               <td  class="table_edit_right">排&nbsp;&nbsp;&nbsp;&nbsp;序：</td>
              <td class="table_edit_left" width="240">
                <input type="text" name="orderindex"  id="orderindex" size="10"  maxlength="10"  value='<bean:write property="orderindex"  name="model"/>'/>越小越靠前*
               </td>
             </tr>
             <tr>
            <td  class="table_edit_right">状&nbsp;&nbsp;&nbsp;&nbsp;态：</td>
            <td class="table_edit_left" width="240">
            	<select name="status">
                 <option value="1" <logic:equal property="status" name="model" value="1">selected</logic:equal>>正常</option>
                 <option value="0" <logic:equal property="status" name="model" value="0">selected</logic:equal>>禁用</option>
              </select>
            </td>          
           </tr>
           
             <tr>
                <td  class="table_edit_right">banner图：</td>
                <td class="table_edit_left" width="240">
                  <img src="/upload_dir/<bean:write property="banner"  name="model"/>" alt="点击修改图片,建议图片格式(1003*385)" width="200" height="40" border="1" id="topreview" onclick="showDialog()"/>
                <input type="hidden" name="banner"  id="banner"   value='<bean:write property="banner"  name="model"/>'/>
              	<br />
              </td>
             </tr>
         <tr>
           <td height="20">&nbsp;</td>
         </tr>
       </table>
    </td>
    </tr>
    <tr>
          <td  align="center" colspan="4" height="40">
            <input  type="button" class="btn_save" onclick="javascript:saveRecord()" value="保存" />
          <input  type="button" class="btn_cancel" onclick="javascript:history.go(-1)" value="返回" />
		   </td>
         </tr>
    </table>
   </html:form>
  </body>
</html>
