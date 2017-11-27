<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<%@ page import="com.util.string.encode.Encode" %>
<%@ page import="com.bzt.gpw.bo.*" %>
<html:html>
<HEAD>
<TITLE>系统公告</TITLE>
<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<SCRIPT language="javascript" src="/public/DatePicker/WdatePicker.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/comm.js"></Script>
<SCRIPT LANGUAGE="Javascript">
function saveRecord(){
  obj = document.gpwBulletinInfoActionForm;
  if(autoCheckForm(obj)==false){
    return false;
  }
  
  //判断结束日期是否大于开始日期
  var startdate = document.getElementById('gpwBulletinInfo.startdate').value;
  var enddate = document.getElementById('gpwBulletinInfo.enddate').value;
  //转换为日期格式   
  startdate=startdate.replace(/-/g,"/");   
  enddate=enddate.replace(/-/g,"/");   
  //如果起始日期大于结束日期   
  if(Date.parse(startdate)-Date.parse(enddate)>0){   
    alert("起始日期要在结束日期之前!");   
    return false;   
  }

  document.getElementById("btnsave").disabled = true;

  document.getElementById("gpwBulletinInfo.htmlcontent").value=document.getElementById("editor0").contentWindow.getHTML();
  obj.action="vodBulletinInfoAction.do?method=<bean:write name="act"/>";
  obj.submit();
}
</SCRIPT>
<%
GpwBulletinInfo model=(GpwBulletinInfo)request.getAttribute("model");
%>
</HEAD>
<BODY>
<TABLE width="100%">
  <TR>
    <TD class="page_title">微课公告</TD>
  </TR>
  <TR>
    <TD vAlign=top align=middle width="100%">
      <html:form action="/vodBulletinInfoAction.do" method="post" >
     <TABLE width="100%">
     <TR>
      <TD  align="center" valign="middle" width="100%">
        <table width="650" border=0 cellpadding=2 cellspacing=2 align="center">
          <tr>
            <td class="table_edit_right" width="100">公告标题：</td>
            <td class="table_edit_left" >
              <input type="text" name="gpwBulletinInfo.title" size="50" CK_NAME="公告标题" CK_TYPE="NotEmpty" class=input value="<bean:write property="title"  name="model"/>">&nbsp;*
            </td>
           </tr>
           <logic:notEqual value="addSave" name="act">
           <tr>
             <td class="table_edit_right">是否显示：</td>
             <td class="table_edit_left">
               <java2code:option  name="gpwBulletinInfo.status" codetype="view" property="status"/>
              </td>
           </tr>
           </logic:notEqual>
           <logic:equal value="addSave" name="act">
           	<input type="hidden" name="gpwBulletinInfo.status" value="<bean:write name="model" property="status"/>"/>
           </logic:equal>
           <tr>
             <td class="table_edit_right">开始日期：</td>
             <td class="table_edit_left" >
              <input type="text" name="gpwBulletinInfo.startdate" id="gpwBulletinInfo.startdate" CK_NAME="开始日期" CK_TYPE="NotEmpty" readonly="readonly" size="20" class=input value="<bean:write property="startdate"  name="model"/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})">&nbsp;*
            </td>
           </tr>
           <tr>
             <td class="table_edit_right">结束日期：</td>
              <td class="table_edit_left" >
              <input type="text" name="gpwBulletinInfo.enddate" id="gpwBulletinInfo.enddate" CK_NAME="结束日期" CK_TYPE="NotEmpty" readonly="readonly" size="20" class=input value="<bean:write property="enddate"  name="model"/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})">&nbsp;*
            </td>
           </tr>
           <tr>
             <td class="table_edit_right">排&nbsp;&nbsp;&nbsp;&nbsp;序：</td>
              <td class="table_edit_left" >
              <input type="text" name="gpwBulletinInfo.orderindex" CK_NAME="结束日期" CK_TYPE="NotEmpty,Number" size="10" class=input value="<bean:write property="orderindex"  name="model"/>">&nbsp;
            </td>
           </tr>
           <tr>
             <td class="table_edit_right">公告内容：</td>
            <td class="table_edit_left" colspan="2">
               <IFRAME ID="editor0" src="/ewebeditor/ewebeditor.htm?id=gpwBulletinInfo.htmlcontent&style=blue&cusdir=<%=Encode.nullToBlank(session.getAttribute("cusdir")) %>" frameborder="0" scrolling="no" width="550" height="300"></IFRAME>
               <input type="hidden" name="gpwBulletinInfo.htmlcontent" id="gpwBulletinInfo.htmlcontent" value="<%=Encode.convertQuot(Encode.nullToBlank(model.getHtmlcontent()))%>"/>
            </td>
           </tr>

        </table>
      </TD>
    </TR>

    <TR>
      <TD  height="40" align="center">
        <input type="button" id="btnsave" name="btnsave" class="btn_save" title="保存本记录" value="保&nbsp;存" onclick="saveRecord()"/>&nbsp;&nbsp;
        <INPUT name="gotoback" type="button" class="btn_cancel" title="返回上一页" onclick="javascript:history.go(-1)" value="返&nbsp;回">
      </TD>
    </TR>
</TABLE>
<input type="hidden" name="gpwBulletinInfo.bulletinid" value="<bean:write property="bulletinid" name="model"/>"/>
<input type="hidden" name="gpwBulletinInfo.unitid" value="<bean:write name="model" property="unitid"/>"/>
<input type="hidden" name="gpwBulletinInfo.mproduct" value="<bean:write name="model" property="mproduct"/>"/>
<input type="hidden" name="gpwBulletinInfo.htmlpath" value="<bean:write name="model" property="htmlpath"/>"/>

<input type="hidden" name="title" value="<bean:write name="title"/>" />
<input type="hidden" name="startdate" value="<bean:write name="startdate"/>" />
<input type="hidden" name="enddate" value="<bean:write name="enddate"/>" />
<input type="hidden" name="status" value="<bean:write name="status"/>" />
<input type="hidden" name="startcount" value="<bean:write name="startcount"/>" />
</html:form>
    </TD>
    </TR>
</TABLE>
</body>
</html:html>
