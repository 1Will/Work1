<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<TITLE>我的收藏夹</TITLE>
<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript"  src="/public/js/comm.js"></Script>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>

<SCRIPT language=javascript>
function saveRecord(){
  obj = document.sysUserFavoritesActionForm;
  var columnumber=document.getElementById("columnnum");
  if(autoCheckForm(obj)==false){
    return false;
  }
  if(isNaN(columnumber.value)){
       top.Dialog.alert("编号只能为数字！");
       return false; 
   }
 if(columnumber.value.length<4||columnumber.value.length>4){
   top.Dialog.alert("请填写4位数字编号！");
    return false;
  }
  document.getElementById('sysUserFavorites.fno').value=document.getElementById('sysUserFavorites.parentno').value+ document.getElementById('columnnum').value;
  var cno = document.getElementById('sysUserFavorites.fno').value;
  var cno2 = '<bean:write property="fno"  name="model"/>';
  var flag = false;
  if(cno != cno2){
  new Ajax.Request(
		"sysUserFavoritesAction.do?method=getSysUserFavorites&fno=" + cno + "&ram=" + Math.random(),
		{
		method:"get",
		asynchronous:false,//true为异步请求
		onComplete:function(xhr){
			var responseObj = xhr.responseText;
			if(responseObj != 'ok'){
				top.Dialog.alert(responseObj);
				flag = true;
			}
		}
		}
	);
	}
	if(flag) return false;
	
	document.getElementById("btnsave").disabled = true;
	
  obj.action="sysUserFavoritesAction.do?method=<bean:write name="act"/>";
  obj.submit();
}
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">我的收藏夹</TD>
  </TR>
  <TR>
    <TD vAlign=top align=middle>
      <html:form  method="post" action="sysUserFavoritesAction.do">
       <TABLE width="500" align=center border="0">
          <tr>
            <td class="table_edit_right" width="25%">栏目名称：</td>
            <td class="table_edit_left" width="75%" colspan="3"><input type="text" CK_NAME="栏目名称" CK_TYPE="NotEmpty,MaxLen_25" name="sysUserFavorites.name" size="25" title="最多为25个汉字" lenght="25" class=input value="<bean:write property="name"  name="model"/>">&nbsp;*</td>
          </tr>
          <tr>
            <td class="table_edit_right">栏目编号：</td>
            <td class="table_edit_left" colspan="3"><a id="showparentno"><bean:write property="parentno" name="model"/></a><input  type="text"  CK_NAME="栏目编号" CK_TYPE="NotEmpty" name="columnnum" id="columnnum" class=input title="请输入4位数字的编号" value="<bean:write  name="columnnum"/>" maxlength="4" size="4" >&nbsp;*
            </td>
          </tr>
          <tr>
              <td colspan="4" height="40" align="center">
                 <input type="button" value="保存" id="btnsave" name="btnsave" class=btn_save onClick="javascript:saveRecord()">
              &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" value="返回" name="btnreturn" class=btn_cancel onClick="javascript:history.go(-1)">
                     
              </td>
           </tr>
           <input type="hidden" name="sysUserFavorites.fid" id="sysUserFavorites.fid" value="<bean:write property="fid"  name="model"/>">
           <input type="hidden" name="sysUserFavorites.fno" id="sysUserFavorites.fno" value="<bean:write property="fno"  name="model"/>">
           <input type="hidden" name="sysUserFavorites.parentno" id="sysUserFavorites.parentno" value="<bean:write property="parentno"  name="model"/>">
           <input type="hidden" name="sysUserFavorites.unitid" value="<bean:write property="unitid"  name="model"/>"/>
           <input type="hidden" name="sysUserFavorites.userid" value="<bean:write property="userid"  name="model"/>"/>
           <input type="hidden" name="parentno"  value="<bean:write name="parentno"/>">
           <input type="hidden" name="name" value="<bean:write name="name"/>"/>
           <input type="hidden" name="startcount" value="<bean:write name="startcount"/>"/>
           </TABLE>
       </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
