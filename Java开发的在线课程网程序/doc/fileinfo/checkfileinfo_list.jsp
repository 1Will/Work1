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
<title>文档审核</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="/checkDocFileInfoAction.do?method=list";
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
    <TD class="page_title" colspan="2">文档审核</TD>
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
                    <td><input type="text" style="width:180px;" name="title" value="<bean:write name="title"/>"/></td>
                    <td width="80" height="25" align="right">上传者:</td>
                    <td><input type="text" style="width:80px;" name="username" value="<bean:write name="username"/>"/></td>
                    <logic:equal value="skyg" name="s_sysuserinfo" property="loginname">
                    <td width="80" height="25" align="right">转码状态:</td>
                    <td>
                    <select name="convertstatus" onchange="searchRecord()">
                    	<option value="1" <logic:equal value="1" name="convertstatus">selected="selected"</logic:equal>>转码成功</option>
                    	<option value="0" <logic:equal value="0" name="convertstatus">selected="selected"</logic:equal>>未完成转码</option>
                    </select>
                    </td>
                    </logic:equal>

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
      <INPUT class="btn_del"  onclick="delRecord('/checkDocFileInfoAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
      <INPUT class="btn_enable"  onclick="delRecord('/checkDocFileInfoAction.do?method=checkBatchRecord')" type="button" value="审核" name="btndel">
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title">文档名称</TD>
    <TD class="table_title" width="70">上传者</TD>
    <TD class="table_title" width="130">上传时间</TD>
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
       <input type="checkbox" name="checkid" value='<%=doc.getFileid()%>' <logic:equal value="0" name="convertstatus">disabled="disabled"</logic:equal>>
     </TD>
     <TD align="left" style="white-space:nowrap;overflow:hidden;" title="<%=doc.getTitle()%>"><%=doc.getTitle()%></TD>
     <td align="center"><%=userinfo.getUsername()%></td>

     <td align="center"><%=doc.getCreatedate() %></td>
     <td align="center">
       <img border="0" src="/public/images/main/edit.gif" onclick="javascript:editThisRecord('checkDocFileInfoAction.do','<%=doc.getFileid()%>')" style="cursor:pointer;" title="修改" alt="修改">
       <!-- <a style="cursor:pointer;" onclick="getFilmDiscuss('<%=doc.getFileid()%>')" title="文档评论管理"><img border="0" src="/public/images/main/icon6.gif"/></a> -->
       <logic:equal value="1" name="convertstatus"><a style="cursor:pointer;" onclick="preview('<%=doc.getFileid()%>')" title="在线预览"><img border="0" src="/public/images/main/preview.gif"/></a></logic:equal>
     </td>
     </tr>


 <% } }%>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="checkDocFileInfoAction.do?method=list" name="pagelist" />
	  <input type="hidden" id="startcount" name="startcount" value="<bean:write name="startcount"/>" />
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
