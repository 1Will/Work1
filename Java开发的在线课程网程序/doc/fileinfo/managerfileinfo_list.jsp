<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.bo.SysUnitInfo"%>
<%@page import="com.bzt.sys.bo.SysUserInfo"%>
<%@page import="com.bzt.doc.bo.DocFileInfo"%>
<%@page import="com.util.search.PageList"%>
<%@page import="java.util.List"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<title>文档管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="/managerDocFileInfoAction.do?method=list";
  document.pageForm.submit();
}
function getFilmDiscuss(filmid){
	var diag = new top.Dialog();
	diag.Title = "文档评论";
	diag.URL = '/docFileDiscussAction.do?method=list&ftag=1&filmid=' + filmid;
	diag.Width = 800;
	diag.Height = 520;
	diag.CancelEvent = function(){
		diag.close();
	};
	diag.show();
}

function preview(fileid){
	var diag = new top.Dialog();
	diag.Title = "在线预览";
	diag.URL = '/docFileInfoAction.do?method=preview&fileid=' + fileid;
	diag.Width = 800;
	diag.Height = 520;
	diag.CancelEvent = function(){
		diag.close();
	};
	diag.show();
}
</SCRIPT>
</head>
<body leftMargin=0 topMargin=0>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">文档管理</TD>
 </TR>
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>文档信息</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">文档名称:</td>
                    <td>
                    <input type="text" style="width:200px;" name="title" value="<bean:write name="title"/>"/>
                   	 上传者:<input type="text" style="width:80px;" name="username" value="<bean:write name="username"/>"/>
                    </td>

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
      <INPUT class="btn_del"  onclick="delRecord('/managerDocFileInfoAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title">文档名称<a style="color:#40A9FF;font-weight:normal;">(点击文档名称在线预览)</a></TD>
    <TD class="table_title" width="70">上传者</TD>
    <TD class="table_title" width="60">浏览次数</TD>
    <TD class="table_title" width="60">是否推荐</TD>
    <TD class="table_title" width="60">操作</TD>
</tr>
<!--循环列出所有数据-->
<%
PageList pagelist = (PageList)request.getAttribute("pagelist");
List alllist =pagelist.getDatalist();
if(alllist != null && alllist.size() >0) {
	Object[] obj=null;
	DocFileInfo doc = null;
	SysUserInfo userinfo = null;
    for(int i=0;i<alllist.size();i++){
		obj=(Object[])alllist.get(i);
    	doc = (DocFileInfo)obj[0];
    	userinfo = (SysUserInfo)obj[1];
%>
     <tr onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
        <TD align="center">
       <input type="checkbox" name="checkid" value='<%=doc.getFileid()%>'>
     </TD>
     <TD align="left" style="white-space:nowrap;overflow:hidden;" title="<%=doc.getTitle()%>"><a href="/<%=Constants.getDefaultTemplate() %>-doc-<%=doc.getUnitid()%>-<%=doc.getFileid()%>.htm" target="_blank"><%=doc.getTitle()%></a></TD>
     <td align="center"><%=userinfo.getUsername()%></td>
     <td align="center"><%=doc.getDownloads()%></td>
     <td align="center"><% if(doc.getRecommand().equals("0") ){%>否<%} else {%>是<%}%></td>
     <td align="center">
       <img border="0" src="/public/images/main/edit.gif" onclick="javascript:editThisRecord('managerDocFileInfoAction.do','<%=doc.getFileid()%>')" style="cursor:pointer;" title="修改" alt="修改">
       <a style="cursor:pointer;" onclick="getFilmDiscuss('<%=doc.getFileid()%>')" title="文档评论管理"><img border="0" src="/public/images/main/icon6.gif"/></a>
     </td>
     </tr>
 <%}}%>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="managerDocFileInfoAction.do?method=list" name="pagelist" />
	  <input type="hidden" id="startcount" name="startcount" value="<bean:write name="startcount"/>" />
	  <input type="hidden" name="typeno" value="<bean:write name="typeno"/>" />
	  <input type="hidden" name="searchSubjectid" value="<bean:write name="searchSubjectid"/>" />
	  <input type="hidden" name="searchGradeid" value="<bean:write name="searchGradeid"/>" />
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
