<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.List"%>
<%@page import="com.util.search.PageList"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function searchRecord(){
  document.pageForm.action="sysStatisticsAction.do?method=yhsztj";
  document.pageForm.submit();
}

function preview(userid){
	document.location = '/sysStatisticsAction.do?method=preview&userid=' + userid;
}

function getHtml(){
	document.pageForm.action = '/sysStatisticsAction.do?method=szph';
	document.pageForm.submit();
}

function showUserBK(userid){
  var path = "eduResSearchAction.do?method=userbkmain&userid="+userid;
  var result =window.showModalDialog(path,"用户备课列表","dialogWidth:800px;dialogheight:550px;toolbar:no;status:no;toolbar:no;")
}

function exportYhsztj(){
	document.pageForm.action = '/sysStatisticsAction.do?method=exportYhsztj';
	document.pageForm.submit();
}
</SCRIPT>
<title>产品管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<body>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">用户上载统计</TD>
 </TR>
 <tr height="30">
   <td colspan="2">
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>统计</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">用户名:</td>
                    <td><input type="text" value='<bean:write name="username" />' size="20" name="username"/></td>
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
   <td style="color:green;">
      说明：总首页及各<bean:write name="SC_szxysubjectname"/>首页面的上载排行榜和用户上载统计重新初始化请点击下面的按钮。<br/>
      <INPUT class="btn_download" onclick="exportYhsztj()" type="button" value="导出数据" name="selectall">
      <INPUT class="btn_html"  onclick="getHtml()" type="button" value="重新统计" name="selectnone">
    </td>
     <TD align="right" style="color:red;width:270px;">
     <logic:present name="msg" scope="request">
     <bean:write name="msg" scope="request"/>
     </logic:present>
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="160" >登录名</TD>
    <TD class="table_title">用户名</TD>
    <TD class="table_title" width="60">性别</TD>
    <TD class="table_title" width="120">总上载资源数</TD>
    <TD class="table_title" width="120">资源被下载总数</TD>
    <TD class="table_title" width="120">资源被点击总数</TD>
    <TD class="table_title" width="80">操作</TD>
</tr>
<!--循环列出所有数据-->
  <%
  	PageList pagelist = (PageList)request.getAttribute("pagelist");
  	List datalist = pagelist.getDatalist();
  	Object[] objects = null;
  	for(int i=0; i<datalist.size(); i++){
  		objects = (Object[])datalist.get(i);
  %>
     <TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
     	<TD align="center"><%=objects[1] %></TD>
        <TD align="left">&nbsp;<%=objects[2] %></td>
        <td align="center"><%if("0".equals(objects[3])){ %>男<%}else{ %>女<%} %></td>
        <td align="center"><%=objects[4] %></td>
        <td align="center"><%=objects[5] %></td>
        <td align="center"><%=objects[6] %></td>
        <td align="center">
           <a style="cursor:pointer;" onclick="preview('<%=objects[0] %>')" title="查看更多统计"><img border="0" src="/public/images/main/preview.gif"/></a>
           <a style="cursor:pointer;" onclick="showUserBK('<%=objects[0] %>')" title="查看用户详细上载"><img border="0" src="/public/images/main/bianhao.gif"/></a>
        </td>
     </TR>
   <%} %>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="sysStatisticsAction.do?method=yhsztj" name="pagelist" />
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
