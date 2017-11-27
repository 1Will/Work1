<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<TITLE>角色管理</TITLE>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>

<SCRIPT language=javascript>
function saveRecord(){
  obj = document.all("registerForm");
  if(autoCheckForm(obj)==false){
    return false;
  }

  document.getElementById("btnsave").disabled = true;

  obj.action="/sysRegisterNoAction.do?method=getRegisterNo";
  obj.submit();
}
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable" width="98%">
  <TR>
    <TD class="page_title">获取注册码</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <form name="registerForm" method="post" >
       <TABLE width="300" align=center border=0>
          <tr>
            <td class="table_edit_right" width="80">学校域名：</td>
            <td class="table_edit_left"> <input type="text" name="xxym" size="20" class=input value="<bean:write name="xxym"/>">&nbsp;</td>
          </tr>
          <tr>
            <td class="table_edit_right" width="80">学校单位ID：</td>
            <td class="table_edit_left"> <input type="text" name="xxid" size="20" class=input value="<bean:write name="xxid"/>">&nbsp;</td>
          </tr>
          <tr>
            <td class="table_edit_right" width="80">区县域名：</td>
            <td class="table_edit_left"> <input type="text" name="qxym" size="20" class=input value="<bean:write name="qxym"/>">&nbsp;</td>
          </tr>
          <tr>
            <td class="table_edit_right" width="80">区县单位ID：</td>
            <td class="table_edit_left"> <input type="text" name="qxid" size="20" class=input value="<bean:write name="qxid"/>">&nbsp;</td>
          </tr>
          <tr>
            <td class="table_edit_right" width="80">学校注册码：</td>
            <td class="table_edit_left">
            <logic:present name="xx_registerno">
            <bean:write name="xx_registerno"/>
            </logic:present>
            </td>
          </tr>
          <tr>
            <td class="table_edit_right" width="80">区县注册码：</td>
            <td class="table_edit_left">
            <logic:present name="qx_registerno">
            <bean:write name="qx_registerno"/>
            </logic:present>
            </td>
          </tr>
          <tr>
                <td colspan="2" align="center">
                    <input type="button" value="保存" id="btnsave" name="btnsave" class="btn_save" onClick="javascript:saveRecord()">
                    </td>
              </tr>
       </TABLE>
       </form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
