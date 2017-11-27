<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<TITLE>视频评论</TITLE>
<base target="_self"/>
<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
</HEAD>
<BODY>
<TABLE width="100%">
  <TR>
    <TD class="page_title">查看评论</TD>
  </TR>
  <TR>
    <TD vAlign=top align=middle width="100%">
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
            <td class="table_edit_right" width="100">文档评分：</td>
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
        <INPUT name="gotoback" type="button" class="btn_cancel" title="返回上一页" onclick="javascript:history.go(-1)" value="返&nbsp;回">
      </TD>
    </TR>
</TABLE>
    </TD>
    </TR>
</TABLE>
</body>
</html:html>
