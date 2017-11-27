<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@page import="java.util.List"%>
<%@page import="com.util.search.PageList"%>
<html>
  <head>
    <SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
   <title>教材管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<script type="text/javascript">
var num=<bean:write name="pagelist" property="pageCount" />
  function addRecord(){
    document.pageForm.action="eduVersionInfoAction.do?method=beforeAdd";
    document.pageForm.submit();
   }


  function searchRecord(){
    document.pageForm.startcount.value="0";
    document.pageForm.action="eduVersionInfoAction.do?method=list";
    document.pageForm.submit();
  }
 
</script>
<body>
  <form name="pageForm" method="post">
  <input type="hidden" name="subjectid" value="${subjectid }">
  <input type="hidden" name="gradeid" value="${gradeid }" >
   <table width="98%" align="center">
    <tr>
     <td class="page_title" colspan="2">教材管理</td>
    </tr>
     <tr height="30">
     <td>
      <table class="table_search_title" width="100%" cellSpacing=1 cellPadding=1>
        <tr>
         <td>查询>>教材管理</td>
        </tr>
      </table>
      <table class="table_search" width="100%" cellSpacing=1 cellPadding=1>
      <tr>
       <td class="bg_basecolor" align="left">
        <table cellpadding="0" cellspacing="0">
         <tr>
          <td width="80" height="25" align="right">版本名称:</td>
          <td><input type="text" size="20" name="versionnames" value="${versionname}"/> </td>
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
      <INPUT class="btn_all" onclick="setState(true)" type="button" value="全选" name="selectall">
      <INPUT class="btn_none"  onclick="setState(false)" type="button" value="全不选" name="selectnone">
      <INPUT class="btn_add"  onclick="addRecord()" type="button" value="新增" name="btnadd">
      <INPUT class="btn_del"  onclick="delRecord('eduVersionInfoAction.do?method=delBatchRecord&subjectid=${subjectid}&gradeid=${gradeid }')" type="button" value="删除" name="btndel">
    </td>
     <TD align="right">
     </TD>
    </tr>
   </table>
    <TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title">版本名称</TD>
    <TD class="table_title" width="120">教材版本</TD>
    <TD class="table_title" width="120">状态</TD>
    <td class="table_title" width="120">排序</td>
    <TD class="table_title" width="70">操作</TD>
</tr>
<!--循环列出所有数据-->
<logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <tr onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
        <TD align="center"><input type="checkbox" name="checkid" value="<bean:write property="versionid" name="model"/>" <bean:write property="flags" name="model"/> /> </TD>
        <td align="left">&nbsp;<bean:write property="versionname" name="model" /></a></td>
         <td>&nbsp;<bean:write property="versionedname" name="model" /></td>
         <td align="center">
         <logic:equal value="1" property="status" name="model">&nbsp;正常</logic:equal>
         <logic:equal value="0" property="status" name="model">&nbsp;禁用</logic:equal>
         </td>
         <td align="center">&nbsp;<bean:write property="orderindex" name="model" /></td>
         <td align="center">
         <java2page:button url="eduVersionInfoAction.do" property="versionid" readonly="E"/>
         </td>
     </tr>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="eduVersionInfoAction.do?method=list&subjectid=${subjectid}&gradeid=${gradeid }" name="pagelist" />
      <input type="hidden" name="startcount" id="startcount" value="<bean:write name="startcount"/>">
    </TD>
  </TR>
</TABLE>
  </form>
</body>
</html>
