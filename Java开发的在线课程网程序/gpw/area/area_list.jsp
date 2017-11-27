<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>地区管理</title>

<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>
<script language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function addRecord(){
  document.pageForm.areaname.value = document.pageForm.areaname.value;
  document.pageForm.action="gpwAreaInfoAction.do?method=beforeAdd&parentno=<bean:write name="parentno"/>";
  document.pageForm.submit();
}

function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="gpwAreaInfoAction.do?method=list";
  document.pageForm.submit();
}
function updateRecord(areaid){
  document.pageForm.action="/gpwAreaInfoAction.do?method=beforeUpdate&objid="+areaid;
  document.pageForm.submit();
}
function reloadTree(){
  <logic:present name="reloadtree">
    parent.tree.location ='/gpwAreaInfoAction.do?method=tree';
  </logic:present>  
}
</script>
</head>
<body onload="javascript:reloadTree()">
<form name="pageForm" method=post>
<table width="98%" align="center">
  <tr>
    <td class="page_title" colspan="2">地区管理</td>
 </tr>
 <tr height="30">
   <td>
     <table class="table_search_title" width="100%" cellspacing=1 cellpadding=1  >
          <tr>
              <td >查询&gt;&gt;地区管理</td>
            </tr>
       </table>
       <table class="table_search" width="100%" cellspacing=1 cellpadding=1  >
            <tr>
              <td class="bg_basecolor" align="left" height="30">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">地区名称：</td>
                    <td><input type="text" value='<bean:write name="areaname" />' size="30" name="areaname"/></td>
                  </tr>
                </table>
              </td>
              <td width="88" class="bg_basecolor">
                 <input class="btn_search"  onclick="searchRecord()" type="button" value="搜索" name="btnsearch"/>
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
	  <input class="btn_all" onclick="setState(true)" type="button" value="全选" name="selectall"/>
      <input class="btn_none" onclick="setState(false)" type="button" value="全不选" name="selectnone"/>
      <input class="btn_add" onclick="addRecord()" type="button" value="新增" name="btnadd"/>
      <input class="btn_del" onclick="batchRecord2('/gpwAreaInfoAction.do?method=delBatchRecord','你真的要批量删除地区？')" type="button" value="删除" name="btndel"/>
    </td>
    <td align="right">
    </td>
 </tr>
  <tr>
    <td class="page_blank"></td>
  </tr>
</table>
<table class="page_table" cellspacing=1 cellpadding=1 width="97%" align="center">
<tr >
    <td class="table_title" width="35" >标识</td>
    <td class="table_title" width="120">地区编号</td>
    <td class="table_title">地区名称</td>
    <td class="table_title" width="80">邮编</td>
    <td class="table_title" width="70">区号</td>
    <td class="table_title" width="70">行政编码</td>
    <td class="table_title" width="40">操作</td>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <TR onmouseover="chgTDbg(this,'on')" class="table_list" onmouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
     <td align="center">
       <input type="checkbox" name="checkid" value="<bean:write property="areaid" name="model"/>" <bean:write property="flags" name="model"/>>
     </td>
     <td align="center"><bean:write name="model" property="areano"/></td>
     <td align="left">&nbsp;<bean:write name="model" property="areaname"/></td>
     <td align="center"><bean:write name="model" property="postcode"/></td>
     <td align="center"><bean:write name="model" property="telecode"/></td>
      <td align="center"><bean:write name="model" property="rgno"/></td>
      <td align="center">
        <a onclick="updateRecord(<bean:write property="areaid" name="model"/>)" title="编辑"><img alt="编辑" border="0" src="public/images/main/edit.gif"/></a>
      </td>
     </tr> 
     </logic:iterate>
</table>
<table width="98%" border=0  align="center">
  <tr>
    <td align=center>
      <java2page:pager url="gpwAreaInfoAction.do?method=list" name="pagelist" />
      <input type="hidden" name="parentno" value="<bean:write name="parentno"/>" />
      <input type="hidden" name="province" value="<bean:write name="province"/>" />
      <input type="hidden" name="startcount" id="startcount" value="<bean:write name="startcount"/>" />
    </td>
  </tr>
</table>
</form>
</body>
</html>
