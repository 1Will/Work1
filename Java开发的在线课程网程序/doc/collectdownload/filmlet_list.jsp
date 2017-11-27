<%@page import="com.bzt.sys.util.Constants"%>
<%@page import="java.util.List"%>
<%@page import="com.util.search.PageList"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="docCollectDownloadAction.do?method=filmletList";
  document.pageForm.submit();
}
</SCRIPT>
<title>视频下载</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<body>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2"><logic:equal value="1" name="type">我的下载</logic:equal><logic:equal value="2" name="type">我的下载</logic:equal></TD>
 </TR>
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>视频 </td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">微课名称</td>
                    <td><input type="text" size="20" name="title" value='${title }' /></td>
                    <td width="80" height="25" align="right">视频名称</td>
                    <td><input type="tel" size="20" name="filmletname" value="${filmletname }"/> </td>
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
 <tr>
   <td>
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title">微课名称</TD>
    <TD class="table_title" >视频名称</TD>
    <TD class="table_title" width="130">
                 下载时间
    </TD>
    <TD class="table_title" width="40">操作</TD>
</tr>
<!--循环列出所有数据-->
  	 <%
  	 PageList pagelist = (PageList)request.getAttribute("pagelist");
  	 List list = pagelist.getDatalist();
  	 Object[] obj = null;
  	 for(int i=0, size=list.size(); i<size; i++){
  		 obj = (Object[])list.get(i);
  	 %>
     <tr onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
         <td align="center"><%=obj[2] %></td>
         <td align="center"><%=obj[1] %></td>
         <td align="center"><%=obj[0] %></td>
         <td align="center"><a href="/<%=Constants.getDefaultTemplate() %>-play1-<%=obj[3] %>-<%=obj[4] %>.htm" target="_blank" title="在线预览"><img border="0" src="/public/images/main/preview.gif"/></a></td>
     </tr>
     <%} %>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="docCollectDownloadAction.do?method=filmletList" name="pagelist" />
      <input type="hidden" name="startcount" id="startcount" value="<bean:write name="startcount"/>">
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
