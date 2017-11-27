<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<%@ page import="com.bzt.vod.bo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>年级分册</title>
<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript"  src="/public/js/comm.js"></Script>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
  function saveRecord(){
   var versionname=document.getElementById("versionname");
   var orderindex=document.getElementById("orderindex");
   obj=document.eduVersionInfoActionForm;
   if(versionname.value==""){
    top.Dialog.alert("请填写版本名称！");
    return false;
   }
   if(versionname.value.length>15){
    top.Dialog.alert("版本名称字数长度不得超过15！");
     return false;
   }
   if(orderindex.value==""){
      top.Dialog.alert("请填写排序！");
      return false;
   }
   if(isNaN(orderindex.value)){
     top.Dialog.alert("排序必修为数字！");
     return false;
   }
    obj.action="/eduVersionInfoAction.do?method=${act}";
    obj.submit();
  }
</script>
  </head>
  
  <body>
     <html:form action="/eduVersionInfoAction.do" method="post">
      <input type="hidden" name="versionid" value="${versionid }"/>
      <input type="hidden" name="subjectid" value="${subjectid }"/>
      <input type="hidden" name="gradeid" value="${gradeid }"/>
     <table class="page_maintable" align="center">
      <tr>
       <td class="page_title"><bean:write name="showtitle"/></td>
      </tr>
      <tr>
      <td valign="top" align="center">
        <table width="650" border="0" align="center" cellpadding="0" cellspacing="0">
         <tr>
          <td class="table_edit_right" width="150">版本名称：</td>
          <td class="table_edit_left" width="100">
            <input type="text" style="width: 200px;" name="versionname"  id="versionname" size="60" tabindex="1" maxlength="50" title="请输入分册名称" value='<bean:write property="versionname"  name="model"/>'/>&nbsp;*
          </td>
         </tr>
          <tr>
            <td class="table_edit_right">排&nbsp;&nbsp;&nbsp;&nbsp;序：</td>
            <td class="table_edit_left" width="240">
             <input type="text" name="orderindex" id="orderindex" size="10" maxlength="10" value='<bean:write property="orderindex" name="model"/>'/>越小越靠前*
            </td>
          </tr>
          <tr>
          <td class="table_edit_right">状&nbsp;&nbsp;&nbsp;&nbsp;态：</td>
          <td class="table_edit_left" width="240">
            <select name="status">
             <option value="1" <logic:equal property="status" name="model" value="1">selected</logic:equal>>正常</option>
             <option value="0" <logic:equal property="status" name="model" value="0">selected</logic:equal>>禁用</option>
            </select>
          </td>
          </tr>
          <tr>
            <td class="table_edit_right">教材版本：</td>
            <td class="table_edit_left" width="240">
             <select id="version" name="version">
             <c:forEach items="${versionlist }" var="ver">
              <option value="${ver.key }" ${versionid2 eq ver.key? 'selected':''}>${ver.value }</option>
             </c:forEach>
             </select>
            </td>
          </tr>
          <tr>
           <td height="20">&nbsp;</td>
         </tr>
        </table>
      </td>
      </tr>
       <tr>
          <td  align="center" colspan="4" height="40">
           <input  type="button" class="btn_save" onclick="javascript:saveRecord()" value="保存" />
          <input  type="button" class="btn_cancel" onclick="javascript:history.go(-1)" value="返回" />
		   </td>
         </tr>
     </table>
     </html:form>
  </body>
</html>
