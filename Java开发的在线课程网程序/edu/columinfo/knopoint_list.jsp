<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@page import="java.util.List"%>
<%@page import="com.util.search.PageList"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<html>
  <head>
    <SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
   <title>知识点管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
  <script type="text/javascript">
  
   function searchRecord(){
    document.pageForm.startcount.value="0";
    document.pageForm.action="eduColumnInfoAction.do?method=addknopoint";
    document.pageForm.submit();
  }
  function relation(kid){
    document.pageForm.action="eduColumnInfoAction.do?method=updatekno&columnid=${columnid}&kid="+kid;
    document.pageForm.submit();
  }
  
  </script>
  <body>
  <form name="pageForm" method="post">
  <input type="hidden" name="columnid" value="${columnid }" >
   <table width="98%" align="center">
    <tr>
     <td class="page_title" colspan="2">知识点管理</td>
    </tr>
     <tr height="30">
     <td>
      <table class="table_search_title" width="100%" cellSpacing=1 cellPadding=1>
        <tr>
         <td>查询>>知识点管理</td>
        </tr>
      </table>
      <table class="table_search" width="100%" cellSpacing=1 cellPadding=1>
      <tr>
       <td class="bg_basecolor" align="left">
        <table cellpadding="0" cellspacing="0">
         <tr>
          <td width="80" height="25" align="right">知识点名称:</td>
          <td><input type="text" size="20" name="title" value="${title}"/> </td>
         </tr>
        </table>
        <td width="88" class="bg_basecolor"><input type="button" value="搜索" onclick="searchRecord()" class="btn_search" /></td>
       </td>
      </tr>
      </table>
     </td>
     </tr> 
      <tr>
   <td class="page_blank"></td>
     </tr>
        <tr>
   <td>
    </td>
     <TD align="right">
     </TD>
    </tr>
   </table>
    <TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <!--  <TD class="table_title" width="35" ></TD>-->
    <TD class="table_title">知识点名称</TD>
    <TD class="table_title" width="70">操作</TD>
</tr>
<!--循环列出所有数据-->
<logic:iterate id="model" name="pagelist" indexId="index">
     <tr onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
       <!--  <TD align="center"><input type="radio" name="checkid" value="<bean:write property="knopointid" name="model"/>" <bean:write property="flags" name="model"/> /> </TD>-->
        <td align="left">
               <c:forEach begin="1" end="${fn:length(model.knopointno) }" step="4">
            &nbsp;&nbsp;&nbsp;&nbsp;
          </c:forEach>      
        
        <bean:write property="title" name="model" /></a></td>
         <td align="center">
            <a title="知识点管理" style="cursor:pointer;" onclick="relation('<bean:write property="knopointid" name="model"/>'	);"><img title="关联知识点" border="0" src="/public/images/main/008.gif"/></a>
         </td>
     </tr>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
    </TD>
  </TR>
</TABLE>
  </form>
</body>
</html>
