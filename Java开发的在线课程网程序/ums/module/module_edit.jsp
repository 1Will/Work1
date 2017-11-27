<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<TITLE>模块管理</TITLE>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>

<SCRIPT language=javascript>
function saveRecord(){
  obj = document.all("sysModuleInfoActionForm");
  if(autoCheckForm(obj)==false){
    return false;
  }

  document.getElementById("btnsave").disabled = true;

  document.getElementById('sysModuleInfo.moduleno').value='<bean:write property="parentno"  name="model"/>' + document.getElementById('curno').value;
  obj.action="sysModuleInfoAction.do?method=<bean:write name="act"/>";
  obj.submit();
}

function goBack(){
  obj = document.all("sysModuleInfoActionForm");
  obj.action="sysModuleInfoAction.do?method=list";
  obj.submit();
}
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable" width="98%">
  <TR>
    <TD class="page_title">模块管理</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/sysModuleInfoAction.do" method="post" >
       <TABLE width="480" align=center border=0>
          <tr>
            <td class="table_edit_right" width="70">模块编号：</td>
            <td class="table_edit_left"><bean:write property="parentno"  name="model"/><input type="text" CK_NAME="模块编号" CK_TYPE="NotEmpty,MaxLen_4,MinLen_4"  maxlength="4" name="curno" id="curno" size="10" class=input value="<bean:write  name="curno"/>">&nbsp;*(建议用4位数字编码)</td>
          </tr>
          <tr>
            <td class="table_edit_right">模块名称：</td>
            <td class="table_edit_left"> <input type="text" CK_NAME="模块名称" CK_TYPE="NotEmpty" name="sysModuleInfo.modulename" size="30" class=input value="<bean:write property="modulename"  name="model"/>">&nbsp;*</td>
          </tr>
          <tr>
            <td class="table_edit_right">模块状态：</td>
            <td class="table_edit_left">
               <java2code:option  name="sysModuleInfo.state" codetype="view" property="state" />
            </td>
          </tr>
           <tr>
            <td class="table_edit_right">模块链接：</td>
            <td class="table_edit_left"> <input type="text"  name="sysModuleInfo.linkurl" size="50" class=input value="<bean:write property="linkurl"  name="model"/>">&nbsp;*</td>
          </tr>
            <tr>
            <td class="table_edit_right">模块图片：</td>
            <td class="table_edit_left"> <input type="text"  name="sysModuleInfo.moduleicon" size="50" class=input value="<bean:write property="moduleicon"  name="model"/>">&nbsp;*</td>
          </tr>
            <tr>
            <td class="table_edit_right">关闭菜单：</td>
            <td class="table_edit_left">
             <java2code:option  name="sysModuleInfo.autoopen" codetype="boolean" property="autoopen" />
            </td>
          </tr>
           <tr>
            <td class="table_edit_right">模块说明：</td>
            <td class="table_edit_left">
              <textarea rows="5" name="sysModuleInfo.descript" class="inputtextarea" cols="50" wrap="physical"><bean:write property="descript"  name="model"/></textarea></td>
          </tr>
          <tr>
                <td colspan="2" align="center">
                    <input type="button" value="保存" id="btnsave" name="btnsave" class="btn_save" onClick="javascript:saveRecord()">
                  <input type="button" value="返回" name="btnreturn" class="btn_cancel" onClick="javascript:goBack()">
                    </td>
              </tr>
       </TABLE>
       <input type="hidden" name="sysModuleInfo.moduleid" value="<bean:write property="moduleid"  name="model"/>"/>
       <input type="hidden" name="sysModuleInfo.moduleno" id="sysModuleInfo.moduleno" value="<bean:write property="moduleno"  name="model"/>"/>
       <input type="hidden" name="sysModuleInfo.parentno" value="<bean:write property="parentno"  name="model"/>"/>
       <input type="hidden" name="sysModuleInfo.productid" value="<bean:write property="productid"  name="model"/>"/>
       <input type="hidden" name="sysModuleInfo.mproduct" value="<bean:write property="mproduct"  name="model"/>"/>

       <input type="hidden" name="modulename" value="<bean:write name="modulename"/>"/>
       <input type="hidden" name="startcount" value="<bean:write name="startcount"/>" />
       <input type="hidden" name="parentno" value="<bean:write name="parentno"/>" />
       <input type="hidden" name="productid" value="<bean:write name="productid"/>" />
       <input type="hidden" name="mproduct" value="<bean:write name="mproduct"/>" />
      </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
