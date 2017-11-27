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

  obj.action="sysVipCardAction.do?method=exportCardno";
  obj.submit();
}
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable" width="98%">
  <TR>
    <TD class="page_title">导出会员卡</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/sysVipCardAction.do" method="post" >
       <TABLE width="450" align=center border=0>
          <tr>
            <td class="table_edit_right" width="80">会员卡数量：</td>
            <td class="table_edit_left"> <input type="text" CK_NAME="会员卡数量" CK_TYPE="Number" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" name="counts" maxlength="4" size="5" class=input value=""></td>
          </tr>
          <tr>
            <td class="table_edit_left" colspan="2">&nbsp;说明：请输入需要导出会员卡的数量，如果为空则导出系统所有未被使用的会员卡</td>
          </tr>
          <tr>
                <td colspan="2" align="center">
                    <input type="button" value="导出" id="btnsave" name="btnsave" class="btn_save" onClick="javascript:saveRecord()">
                    </td>
              </tr>
       </TABLE>
       </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
