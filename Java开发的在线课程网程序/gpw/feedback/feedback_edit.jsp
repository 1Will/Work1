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
  var replycontent = document.getElementById("replycontent").value;
  if(replycontent == ""){
	  alert("回复内容不能为空！");
	  return;
  }

  document.getElementById("btnsave").disabled = true;
  obj = document.all("gpwFeedbackInfoActionForm");
  obj.action="gpwFeedbackInfoAction.do?method=<bean:write name="act"/>";
  obj.submit();
}
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable" width="98%">
  <TR>
    <TD class="page_title">意见反馈</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/gpwFeedbackInfoAction.do" method="post" >
       <TABLE width="600" align=center border=0>
          <tr>
            <td class="table_edit_right" width="70">反馈内容：</td>
            <td class="table_edit_left"><bean:write property="content" name="model"/></td>
          </tr>
          <tr>
            <td class="table_edit_right" width="70">联系方式：</td>
            <td class="table_edit_left"><bean:write property="contact" name="model"/></td>
          </tr>
          <tr>
            <td class="table_edit_right" width="70">反馈时间：</td>
            <td class="table_edit_left"><bean:write property="createdate" name="model"/></td>
          </tr>
          <tr>
            <td class="table_edit_right">是否显示：</td>
            <td class="table_edit_left"><java2code:option name="state" codetype="boolean"/></td>
          </tr>
          <tr>
             <td class="table_edit_right">回复内容：</td>
            <td class="table_edit_left" colspan="2">
               <textarea rows="5" name="replycontent" id="replycontent" class="inputtextarea" cols="40" wrap="physical"><bean:write property="replycontent" name="model"/></textarea>
              </td>
           </tr>
          <tr>
            <td colspan="2" align="center">
                <input type="button" value="回复" id="btnsave" name="btnsave" class="btn_save" onClick="javascript:saveRecord()">
                <input type="button" value="返回" name="btnreturn" class="btn_cancel" onClick="javascript:history.go(-1)">
            </td>
          </tr>
       </TABLE>
       <input type="hidden" name="gpwFeedbackInfo.feedbackid" value="<bean:write property="feedbackid" name="model"/>"/>

       <input type="hidden" name="startcount" value="<bean:write name="startcount"/>" />
       <input type="hidden" name="content" value="<bean:write name="content"/>" />
       <input type="hidden" name="createdate" value="<bean:write name="createdate"/>" />
       <input type="hidden" name="state" value="<bean:write name="state"/>" />
       <input type="hidden" name="isreply" value="<bean:write name="isreply"/>" />
       </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
