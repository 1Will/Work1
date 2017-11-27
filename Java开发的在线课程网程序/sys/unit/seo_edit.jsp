<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>

<HEAD>
<TITLE>SEO优化</TITLE>
<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>

<SCRIPT language=javascript>
function saveRecord(){
  obj = document.all("sysUnitInfoActionForm");
  if(autoCheckForm(obj)==false){
    return false;
  }

  document.getElementById("btnsave").disabled = true;

  obj.action="sysUnitInfoAction.do?method=updateSaveSeo";
  obj.submit();
}
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">SEO优化</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/sysUnitInfoAction.do" method="post" >
       <TABLE width="100%" border="0" align=center border=0>
             <tr>
             <td class="table_edit_right">网站关键字：</td>
             <td class="table_edit_left" colspan="2">
               <input type="text" name="sysUnitInfo.keywords" class="input" value="<bean:write property="keywords"  name="model"/>" maxlength="200" size="80"/>
            </tr>
            <tr>
             <td class="table_edit_right">网站简介：</td>
             <td class="table_edit_left" colspan="2">
               <textarea rows="6" name="sysUnitInfo.descript" class="inputtextarea" cols="92" wrap="physical"><bean:write property="descript"  name="model"/></textarea></td>
            </tr>
             <tr height="40">
                <td colspan="3" align="center">
                    <input type="button" value="保存" id="btnsave" name="btnsave" class="btn_save" onClick="saveRecord()">
                     </td>
              </tr>
       </table>
        <input type="hidden" name="sysUnitInfo.unitid" value="<bean:write property="unitid"  name="model"/>"/>
       </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
