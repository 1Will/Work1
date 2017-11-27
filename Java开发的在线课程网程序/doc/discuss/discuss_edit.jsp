<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<TITLE>视频评论</TITLE>
<base target="_self"/>
<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<SCRIPT LANGUAGE="Javascript">
function saveRecord(){
  obj = document.all("vodFilmDiscussActionForm");
  document.getElementById("btnsave").disabled = true;
  obj.action="docFileDiscussAction.do?method=<bean:write name="act"/>";
  obj.submit();
}
function returnback(){
	obj = document.all("vodFilmDiscussActionForm");
	obj.action="docFileDiscussAction.do?method=list";
    obj.submit();
}
</SCRIPT>
</HEAD>
<BODY>
<TABLE width="100%">
  <TR>
    <TD class="page_title">文档评论</TD>
  </TR>
  <TR>
    <TD vAlign=top align=middle width="100%">
      <html:form action="/docFileDiscussAction.do" method="post" >
     <TABLE width="100%">
     <TR>
      <TD  align="center" valign="middle" width="100%">
        <table width="630" border=0 cellpadding=2 cellspacing=2 align="center">
          <tr>
            <td class="table_edit_right" width="100">评论的文档：</td>
            <td class="table_edit_left" >
              <bean:write name="fileInfo" property="title"/>
            </td>
           </tr>
           <tr>
            <td class="table_edit_right" width="100">评论者：</td>
            <td class="table_edit_left" >
              <bean:write name="model" property="username"/>
            </td>
           </tr>
           <tr>
            <td class="table_edit_right" width="100">评论IP：</td>
            <td class="table_edit_left" >
              <bean:write name="model" property="discussip"/>
            </td>
           </tr>
           <tr>
            <td class="table_edit_right" width="100">评论时间：</td>
            <td class="table_edit_left" >
              <bean:write name="model" property="createdate"/>
            </td>
           </tr>
           <tr>
            <td class="table_edit_right" width="100">评分：</td>
            <td class="table_edit_left" >
              <bean:write name="model" property="score"/>(5分满分)
            </td>
           </tr>
           <tr>
            <td class="table_edit_right" width="100">评论类型：</td>
            <td class="table_edit_left" >
              <java2code:value codetype="commenttype" property="type"></java2code:value>
            </td>
           </tr>
           <tr>
            <td class="table_edit_right" width="100">评论内容：</td>
            <td class="table_edit_left" >
              <bean:write name="model" property="htmlcontent" filter="false"/>
            </td>
           </tr>
        </table>
      </TD>
    </TR>

    <TR>
      <TD  height="40" align="center">
      	<logic:equal value="0" name="model" property="status">
        <input type="button" id="btnsave" name="btnsave" class="btn_save" title="审核评论" value="审&nbsp;核" onclick="saveRecord()"/>&nbsp;&nbsp;
        </logic:equal>
        <INPUT name="gotoback" type="button" class="btn_cancel" title="返回上一页" onclick="returnback()" value="返&nbsp;回">
      </TD>
    </TR>
</TABLE>
<input type="hidden" name="vodFilmDiscuss.discussid" value="<bean:write property="discussid" name="model"/>"/>

<input type="hidden" name="startcount" value="<bean:write name="startcount"/>" />
<input type="hidden" name="createdate" value="<bean:write name="createdate"/>" />
<input type="hidden" name="status" value="<bean:write name="status"/>" />
<input type="hidden" name="filmid" value="<bean:write name="filmid"/>" />
<input type="hidden" name="type" value="<bean:write name="type"/>" />
<input type="hidden" name="ftag" value="<bean:write name="ftag"/>" />
</html:form>
    </TD>
    </TR>
</TABLE>
</body>
</html:html>
