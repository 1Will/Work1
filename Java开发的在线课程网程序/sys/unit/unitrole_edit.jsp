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
  obj = document.all("sysRoleInfoActionForm");
  if(autoCheckForm(obj)==false){
    return false;
  }

  document.getElementById("btnsave").disabled = true;

  obj.action="sysUnitRoleInfoAction.do?method=<bean:write name="act"/>";
  obj.submit();
}
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable" width="98%">
  <TR>
    <TD class="page_title">角色管理</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/sysUnitRoleInfoAction.do" method="post" >
       <TABLE width="300" align=center border=0>
          <tr>
            <td class="table_edit_right" width="70">角色编号：</td>
            <td class="table_edit_left"> <input type="text" CK_NAME="角色编号" CK_TYPE="NotEmpty,Len_4" name="sysRoleInfo.roleno" maxlength="4" size="5" class=input value="<bean:write property="roleno"  name="model"/>">&nbsp;*(建议用四位数字编码)</td>
          </tr>
          <tr>
            <td class="table_edit_right" width="70">角色名称：</td>
            <td class="table_edit_left"> <input type="text" CK_NAME="角色名称" CK_TYPE="NotEmpty" name="sysRoleInfo.rolename" size="23" class=input value="<bean:write property="rolename"  name="model"/>">&nbsp;*</td>
          </tr>
          <tr>
            <td class="table_edit_right">角色状态：</td>
            <td class="table_edit_left">
             <select name="sysRoleInfo.state"  style="width:60">
                <logic:equal property="state" name="model" value="0">
                   <option value=0 selected>禁用</option>
                   <option value=1 >在用</option>
                </logic:equal>
                <logic:notEqual property="state" name="model" value="0">
                   <option value=0 >禁用</option>
                   <option value=1 selected>在用</option>
                </logic:notEqual>
              </select>
            </td>
          </tr>
          <tr>
                <td colspan="2" align="center">
                    <input type="button" value="保存" id="btnsave" name="btnsave" class="btn_save" onClick="javascript:saveRecord()">
                  <input type="button" value="返回" name="btnreturn" class="btn_cancel" onClick="javascript:history.go(-1)">
                    </td>
              </tr>
       </TABLE>
       <input type="hidden" name="sysRoleInfo.roleid" value="<bean:write property="roleid" name="model"/>"/>
       <input type="hidden" name="sysRoleInfo.productid" value="<bean:write property="productid" name="model"/>"/>
       <input type="hidden" name="sysRoleInfo.unitid" value="<bean:write property="unitid" name="model"/>"/>

       <input type="hidden" name="startcount" value="<bean:write name="startcount"/>" />
       <input type="hidden" name="rolename" value="<bean:write name="rolename"/>" />
       <input type="hidden" name="unitid" value="<bean:write name="unitid"/>" />
       </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
