<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@page import="java.util.List"%>
<%@page import="com.util.search.PageList"%>
<html>
<head>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="sysMyFavoritesAction.do?method=list&filetype=${filetype}&fid=${fid}";
  document.pageForm.submit();
}


</SCRIPT>
<title>收藏管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<body >
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">收藏管理</TD>
 </TR>
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>我的收藏</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">${type }名称:</td>
                    <td><input type="text" size="20" name="title" value='<bean:write name="title" />' /></td>
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
      <INPUT class="btn_all" onclick="setState(true)" type="button" value="全选" name="selectall">
      <INPUT class="btn_none"  onclick="setState(false)" type="button" value="全不选" name="selectnone">
      <INPUT class="btn_del"  onclick="delRecord('sysMyFavoritesAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title">${type }名称</TD>
    <TD class="table_title">${type }评分</TD>
    <TD class="table_title" width="120">收藏时间</TD>
    <TD class="table_title" width="70">操作</TD>
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
        <TD align="center"><input type="checkbox" name="checkid" value="<%=obj[0] %>" /> </TD>
        <td align="left">&nbsp;<a href="/<%=Constants.getDefaultTemplate() %>-${playtype }-<%=obj[7] %>-<%=obj[2] %>.htm" target="_blank"><%=obj[3] %></a></td>
         <td align="center"><%=obj[5] %>分(<%=obj[6] %>人评)</td>
         <td align="center"><%=obj[1] %></td>
         <td align="center"><a href="/<%=Constants.getDefaultTemplate() %>-${playtype }-<%=obj[7] %>-<%=obj[2] %>.htm" target="_blank" title="在线预览"><img border="0" src="/public/images/main/preview.gif"/></a></td>
     </tr>
      <%} %>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="sysMyFavoritesAction.do?method=list" name="pagelist" />
      <input type="hidden" name="startcount" id="startcount" value="<bean:write name="startcount"/>">
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
