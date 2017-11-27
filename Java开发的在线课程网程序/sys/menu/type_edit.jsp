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
function saveRecord(){
  obj = document.all("sysMenuTypeActionForm");
  if(autoCheckForm(obj)==false){
    return false;
  }
  
  document.getElementById('sysMenuType.typeno').value=document.getElementById('sysMenuType.parentno').value+ document.getElementById('typenum').value;
  var cno = document.getElementById('sysMenuType.typeno').value;
  var cno2 = '<bean:write property="typeno"  name="model"/>';
  var flag = false;
  if(cno != cno2){
  new Ajax.Request(
		"sysMenuTypeAction.do?method=getCmsNewsColumn&typeno=" + cno + "&ram=" + Math.random(),
		{
		method:"get",
		asynchronous:false,//true为异步请求
		onComplete:function(xhr){
			var responseObj = xhr.responseText;
			if(responseObj != 'ok'){
				alert(responseObj);
				flag = true;
			}
		}
		}
	);
	}
	if(flag) return false;
	
	document.getElementById("btnsave").disabled = true;
	
  obj.action="sysMenuTypeAction.do?method=<bean:write name="act"/>";
  obj.submit();
}
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">资讯栏目</TD>
  </TR>
  <TR>
    <TD vAlign=top align=middle>
      <html:form  method="post" action="sysMenuTypeAction.do">
       <TABLE width="500" align=center border="0">
          <tr>
            <td class="table_edit_right" width="25%">分类名称：</td>
            <td class="table_edit_left" width="75%" colspan="3"><input type="text" CK_NAME="分类名称" CK_TYPE="NotEmpty,MaxLen_25" name="sysMenuType.typename" size="25" title="最多为25个汉字" lenght="25" class=input value="<bean:write property="typename"  name="model"/>">&nbsp;*</td>
          </tr>
          <tr>
            <td class="table_edit_right">分类编号：</td>
            <td class="table_edit_left" colspan="3"><a id="showparentno"><bean:write property="parentno" name="model"/></a><input type="text"  CK_NAME="分类编号" CK_TYPE="NotEmpty" name="typenum" id="typenum" class=input title="请输入4位数字的编号" value="<bean:write  name="typenum"/>" maxlength="4" size="4" >&nbsp;*
              </td>
          </tr>
          <tr>
              <td colspan="4" height="40" align="center">
                 <input type="button" value="保存" id="btnsave" name="btnsave" class=btn_save onClick="javascript:saveRecord()">
              &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" value="返回" name="btnreturn" class=btn_cancel onClick="javascript:history.go(-1)">

                 </td>
           </tr>
           <input type="hidden" name="sysMenuType.typeno" id="sysMenuType.typeno" value="<bean:write property="typeno"  name="model"/>">
           <input type="hidden" name="sysMenuType.parentno" id="sysMenuType.parentno" value="<bean:write property="parentno"  name="model"/>">
           <input type="hidden" name="sysMenuType.typeid"  value="<bean:write property="typeid"  name="model"/>">
           <input type="hidden" name="sysMenuType.flag" value="<bean:write property="flag"  name="model"/>">
           <input type="hidden" name="sysMenuType.unitid" value="<bean:write property="unitid"  name="model"/>"/>
           
           <input type="hidden" name="parentno"  value="<bean:write name="parentno"/>">
           <input type="hidden" name="startcount" value="<bean:write name="startcount"/>"/>
           </TABLE>
       </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
