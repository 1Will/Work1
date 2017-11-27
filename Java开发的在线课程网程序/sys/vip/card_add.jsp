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
  obj = document.all("sysVipCardActionForm");
  if(autoCheckForm(obj)==false){
    return false;
  }

  document.getElementById("btnsave").disabled = true;

  obj.action="sysVipCardAction.do?method=addSave";
  obj.submit();
}
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable" width="98%">
  <TR>
    <TD class="page_title">创建会员卡</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/sysVipCardAction.do" method="post" >
       <TABLE width="400" align=center border=0>
          <tr>
            <td class="table_edit_right" width="70">建卡数量：</td>
            <td class="table_edit_left"> <input type="text" CK_NAME="建卡数量" CK_TYPE="NotEmpty,Number" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" name="counts" maxlength="3" size="5" class=input value="">&nbsp;*(请输入需要生成会员卡数量)</td>
          </tr>
          <tr>
                <td colspan="2" align="center">
                    <input type="button" value="保存" id="btnsave" name="btnsave" class="btn_save" onClick="javascript:saveRecord()">
                  <input type="button" value="返回" name="btnreturn" class="btn_cancel" onClick="javascript:history.go(-1)">
                    </td>
              </tr>
       </TABLE>
       </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
