<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html>
<HEAD>
<TITLE>知识点目录</TITLE>
<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript"  src="/public/js/comm.js"></Script>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>
<script type="text/javascript">
function updateKnopoint(){
 obj=document.eduColumnInfoActionForm;
 obj.action="/eduColumnInfoAction.do?method=addknopoint&columnid=${columnid}&type=2";
 obj.submit();
}
</script>
  </head>
  <body>
    <table class="page_maintable">
     <tr>
      <td class="page_title">关联知识点详情</td>
     </tr>
     <tr>
       <td valign="top" align="middle">
       <html:form method="post" action="eduColumnInfoAction.do">
       <table width="500" align=center border=0>
       <tr>
         <td class="table_edit_right" width="25%">知识点名称：</td>
          <td class="table_edit_left" width="75%" colspan="3">
          <bean:write property="title" name="model"/>
          </td>
       </tr>
       <tr>
        <td class="table_edit_right">知识点编号：</td>
        <td class="table_edit_left" colspan="3"><bean:write property="knopointno" name="model"/>
       </tr>
       <tr>
        <td class="table_edit_right">类&nbsp;&nbsp;&nbsp;&nbsp;型：</td>
        <td class="table_edit_left" width="240">
        <logic:equal property="type" name="model" value="0">知识点大纲</logic:equal>
        <logic:equal property="type" name="model" value="1">具体知识点</logic:equal>
        </td>
       </tr>
       <tr>
       <td class="table_edit_right">状&nbsp;&nbsp;&nbsp;&nbsp;态：</td>
       <td class="table_edit_left" width="240">
           <logic:equal property="status" name="model" value="1">正常</logic:equal>
            <logic:equal property="status" name="model" value="0">禁用</logic:equal>
       </td>
       </tr>
         <td colspan="4" height="40" align="center">
          <input style="width: 100px;" type="button" value="修改关联知识点" id="btnsave" name="btnsave" class=btn_save onClick="javascript:updateKnopoint()">
       </tr>
       </table>
       </html:form>
       </td>
     </tr>
    </table>
  </body>
</html>
