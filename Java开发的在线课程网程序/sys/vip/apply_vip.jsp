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

  obj.action="sysVipCardAction.do?method=saveApply";
  obj.submit();
}
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable" width="98%">
  <TR>
    <TD class="page_title">申请VIP会员</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/sysVipCardAction.do" method="post" >
       <TABLE width="500" align=center border=0>
       	  <logic:present name="msg">
       	  <tr>
            <td class="table_edit_right" width="70">&nbsp;</td>
            <td class="table_edit_left" style="color:red;"><bean:write name="msg"/></td>
          </tr>
          </logic:present>
          <tr>
            <td class="table_edit_right" width="80">VIP会员卡号：</td>
            <td class="table_edit_left">
            <input type="text" CK_NAME="会员卡号" CK_TYPE="NotEmpty,Number" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" name="cardno1" maxlength="4" size="5" class=input value="">
            <input type="text" CK_NAME="会员卡号" CK_TYPE="NotEmpty,Number" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" name="cardno2" maxlength="4" size="5" class=input value="">
            <input type="text" CK_NAME="会员卡号" CK_TYPE="NotEmpty,Number" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" name="cardno3" maxlength="4" size="5" class=input value="">
            <input type="text" CK_NAME="会员卡号" CK_TYPE="NotEmpty,Number" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" name="cardno4" maxlength="4" size="5" class=input value="">
            </td>
          </tr>
          <tr>
                <td colspan="2" align="center">
                    <input type="button" value="提交" id="btnsave" name="btnsave" class="btn_save" onClick="javascript:saveRecord()">
                  <input type="button" value="返回" name="btnreturn" class="btn_cancel" onClick="javascript:history.go(-1)">
                    </td>
              </tr>
       </TABLE>
       <input type="hidden" name="userid" value='<bean:write name="userid"/>'/>
       </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
