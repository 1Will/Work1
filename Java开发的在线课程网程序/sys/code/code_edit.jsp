<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<TITLE>码表管理</TITLE>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>

<SCRIPT language=javascript>
function saveRecord(){
  obj = document.all("sysCodeDataActionForm");
  if(autoCheckForm(obj)==false){
    return false;
  }

  document.getElementById("btnsave").disabled = true;

  obj.action="sysCodeDataAction.do?method=<bean:write name="act"/>";
  obj.submit();
}
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable" width="100%">
  <TR>
    <TD class="page_title">码表管理</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/sysCodeDataAction.do" method="post" >
       <TABLE width="260" align=center border=0>


           <tr>
            <td class="table_edit_right">名&nbsp;&nbsp;&nbsp;&nbsp;称：</td>
            <td class="table_edit_left"> <input type="text" CK_NAME="<bean:write property="codedata" name="model"/>名称" CK_TYPE="NotEmpty" name="sysCodeData.codedata" size="20" class=input value="<bean:write property="codedata"  name="model"/>">*</td>
          </tr>


         <tr>
            <td class="table_edit_right" width="30%">所属类型：</td>
            <td class="table_edit_left"><input type="text" CK_NAME="<bean:write property="codeno" name="model"/>所属类型"  name="sysCodeData.codeno" size="10" <logic:notEqual value="addSave" name="act">readonly="readonly"</logic:notEqual> class=input value="<bean:write property="codeno"  name="model"/>">*</td>
          </tr>

           <tr>
                <td colspan="2" align="center">
                   <input type="button" value="保存" id="btnsave" name="btnsave" class="btn_save" onClick="javascript:saveRecord()">
                   <input type="button" value="返回" name="btnreturn" class="btn_cancel" onClick="javascript:history.go(-1)">
                 </td>
          </tr>
       </TABLE>
        <input type="hidden" name="sysCodeData.state" value="<bean:write property="state"  name="model"/>"/>
         <input type="hidden" name="sysCodeData.typeno" value="<bean:write property="typeno"  name="model"/>"/>
        <input type="hidden" name="sysCodeData.codeid" value="<bean:write property="codeid"  name="model"/>"/>
        <input type="hidden" name="sysCodeData.unitid" value="<bean:write property="unitid"  name="model"/>"/>
        <input type="hidden" name="sysCodeData.productid" value="<bean:write property="productid"  name="model"/>"/>
        
        <input type="hidden" name="codedata" value="<bean:write name="codedata"/>">
        <input type="hidden" name="codeno" value="<bean:write name="codeno"/>">
        <input type="hidden" name="startcount" value="<bean:write name="startcount"/>">
      </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
