<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<TITLE>资讯栏目</TITLE>
<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript"  src="/public/js/comm.js"></Script>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>

<SCRIPT language=javascript>
function showDialog(url,name){
  var retValue=showModalDialog(url,name,"dialogWidth:350px;dialogHeight:200px;scroll=no;border=thin;help=0;status=no");
  if(retValue!=null){
  	 if(retValue[0] != ''){
  	 	alert(retValue[0]);
  		return false;
  	 }
     document.sysUserMenuActionForm.topreview.src="/upload_dir/"+retValue[1];
     document.getElementById('sketch').value="/upload_dir/"+retValue[1];
  }
}
function selectMenu(){
  var url = "/sysMenuInfoAction.do?method=selectMenuFrame";
  var retValue=showModalDialog(url,"选择快捷菜单","dialogWidth:700px;dialogHeight:500px;scroll=no;border=thin;help=0;status=no");
  if(retValue!=null){
     document.sysUserMenuActionForm.topreview.src=retValue[0];
     document.getElementById('sketch').value=retValue[0];
  }
}
function saveRecord(){
  obj = document.sysUserMenuActionForm;
  if(autoCheckForm(obj)==false){
    return false;
  }
  
  document.getElementById("btnsave").disabled = true;
	
  obj.action="sysUserMenuAction.do?method=<bean:write name="act"/>";
  obj.submit();
}
function goBack(){
	obj = document.sysUserMenuActionForm;
	obj.action="sysUserMenuAction.do?method=list";
    obj.submit();
}
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">快捷菜单设置</TD>
  </TR>
  <TR>
    <TD vAlign=top align=middle>
      <html:form  method="post" action="sysUserMenuAction.do">
       <TABLE width="500" align=center border="0">
          <tr>
            <td class="table_edit_right" width="25%">菜单名称：</td>
            <td class="table_edit_left" width="75%" colspan="3"><bean:write property="sysModuleInfo.modulename" name="model"/></td>
          </tr>
          <tr >
            <td class="table_edit_right">菜单图片：</td>
            <td class="table_edit_left" colspan="3" >
              <img src="<bean:write property="sketch"  name="model"/>" width="82" height="82" border="1" id=topreview style="cursor:pointer;" onclick="selectMenu()">
              <input type="hidden" name="sysUserMenu.sketch" id="sketch"  value="<bean:write property="sketch"  name="model"/>">(点击修改图片)
            </td>
           </tr>
          <%-- 上面的图标是从系统菜单库中选择的，以下是用户可以自己上传图片
           <tr >
            <td class="table_edit_right">菜单图片：</td>
            <td class="table_edit_left" colspan="3" >
              <img src="<bean:write property="sketch"  name="model"/>" width="60" height="50" border="1" id=topreview onclick="showDialog('sysImageUploadAction.do?method=uploadimage&savepath=usermenu&pathtype=ID','缩略图')">
              <input type="hidden" name="sysUserMenu.sketch" id="sketch"  value="<bean:write property="sketch"  name="model"/>">(点击修改图片)
            </td>
           </tr>
           --%>
           <tr>
            <td class="table_edit_right">排序：</td>
            <td class="table_edit_left" colspan="3"><input type="text"  CK_NAME="排序" CK_TYPE="NotEmpty,Number" name="sysUserMenu.orderindex" class=input value="<bean:write name="model" property="orderindex"/>" maxlength="4" size="4">&nbsp;*
              </td>
          </tr>
          <tr>
              <td colspan="4" height="40" align="center">
                 <input type="button" value="保存" id="btnsave" name="btnsave" class=btn_save onClick="javascript:saveRecord()">
              &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" value="返回" name="btnreturn" class=btn_cancel onClick="javascript:goBack()">

                 </td>
           </tr>
           <input type="hidden" name="sysUserMenu.menuid" value="<bean:write property="menuid"  name="model"/>">
           <input type="hidden" name="sysUserMenu.userid" value="<bean:write property="userid"  name="model"/>">
           <input type="hidden" name="sysUserMenu.unitid" value="<bean:write property="unitid"  name="model"/>"/>
           </TABLE>
       </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
