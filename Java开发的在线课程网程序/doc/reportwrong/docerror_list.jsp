<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<title>申请报错</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<%@page import="com.util.search.PageList"%>
<%@page import="java.util.List"%>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="/docFileErrorAction.do?method=list&type=1";
  document.pageForm.submit();
}
function handleReport(errorid,fileid){
  document.pageForm.action="/docFileErrorAction.do?method=beforeUpdate&errorid="+errorid+"&fileid="+fileid+"&type=1";
  document.pageForm.submit();
}

</SCRIPT>
</head>
<body leftMargin=0 topMargin=0>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">文档报错</TD>
 </TR>
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>文档报错</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">文档名称:</td>
                    <td><input type="text" style="width:180px;" name="titles" value="<bean:write name="title"/>"/></td>
                    <td width="80" height="25" align="right">审核状态:</td>
                    <td>
                    <select name="state">
                    <option value="">请选择…</option>
                    <option value="0" <logic:equal value="0" name="state">selected="selected"</logic:equal>>待处理</option>
                    <option value="1" <logic:equal value="1" name="state">selected="selected"</logic:equal>>已处理</option>
                    <option value="2" <logic:equal value="2" name="state">selected="selected"</logic:equal>>不予处理</option>
                    </select>
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
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title">文档名称</TD>
    <TD class="table_title" width="60">审核状态</TD>
    <TD class="table_title" width="80">操作</TD>
</tr>
<!--循环列出所有数据-->
<%
  PageList pagelist=(PageList)request.getAttribute("pagelist");
  List list=pagelist.getDatalist();
  Object[] obj=null;
  for(int i=0;i<list.size();i++){
     obj=(Object[])list.get(i);
 %>

     <tr onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
     <td align="center"><%=obj[3] %></td>
     <td align="center">
    <%=obj[2].equals("0")?"<span style='color:red;'>待处理</span>":"" %>
    <%=obj[2].equals("1")?"已处理":"" %>
    <%=obj[2].equals("2")?"不予处理":"" %>
     </td>
     <td align="center">
     <a title="处理申报"  href="javascript:handleReport(<%=obj[0] %>,<%=obj[1] %>);" ><img title="处理申报" src="/public/images/main/edit.gif" border="0"></a>
     </td>
     </tr>
      <%}%>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="docFileErrorAction.do?method=list&type=1" name="pagelist" />
        <input type="hidden" id="startcount" name="startcount" value="<bean:write name="startcount"/>" />
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
