<%@page import="java.util.List"%>
<%@page import="com.util.search.PageList"%>
<%@page import="com.bzt.vod.bo.VodFilmInfo"%>
<%@page import="com.bzt.sys.bo.SysUserInfo"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<title>视频管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="/vodUserFileAction.do?method=list";
  document.pageForm.submit();
}
</SCRIPT>
</head>
<body leftMargin=0 topMargin=0>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">微课管理</TD>
 </TR>
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>微课信息</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">微课名称:</td>
                    <td><input type="text" style="width:180px;" name="title" value="<bean:write name="title"/>"/></td>
                    <td width="80" height="25" align="right">主讲教师:</td>
                    <td><input type="text" style="width:80px;" name="actors" value="<bean:write name="actors"/>"/></td>
                  </tr>
                </table>
              </td>
              <td width="88" class="bg_basecolor"><input type="button" value="搜索" onclick="searchRecord()" class="btn_search" /></td>
              </tr>
        </table>
   </td>
 </tr>
 <TR>
    <TD class="page_blank"></TD>
  </TR>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title">微课名称<a style="color:#40A9FF;font-weight:normal;">(点击微课名称在线预览)</a></TD>
    <TD class="table_title" width="70">主讲教师</TD>
    <TD class="table_title" width="60">播放次数</TD>
    <TD class="table_title" width="60">操作</TD>
</tr>
<!--循环列出所有数据-->
     <%
     List hasfileids = (List)request.getAttribute("hasfileids");
     PageList pagelist = (PageList)request.getAttribute("pagelist");
     List list = pagelist.getDatalist();
     VodFilmInfo model = null;
     for(int i=0, size=list.size(); i<size; i++){
    	 model = (VodFilmInfo)list.get(i);
     %>
     <tr onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
	     <TD align="left" style="white-space:nowrap;overflow:hidden;" title="<%=model.getTitle() %>">&nbsp;<a href="/<%=Constants.getDefaultTemplate() %>-play-<%=model.getUnitid() %>-<%=model.getFilmid() %>.htm" target="_blank"><%=model.getTitle() %></a><%if(hasfileids.contains(model.getFilmid())){ %><font color="red">【已处理】</font><%} %></TD>
	     <td align="center"><%=model.getActors() %></td>
	     <td align="center"><%=model.getCounts() %></td>
	     <td align="center">
	       <img border="0" src="/public/images/main/edit.gif" onclick="javascript:editThisRecord('vodUserFileAction.do','<%=model.getFilmid() %>')" style="cursor:pointer;" title="修改" alt="修改">
	     </td>
     </tr>
     <%} %>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="vodUserFileAction.do?method=list" name="pagelist" />
	  <input type="hidden" id="startcount" name="startcount" value="<bean:write name="startcount"/>" />
	  <input type="hidden" name="searchGradeid" value="<bean:write name="searchGradeid"/>" />
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
