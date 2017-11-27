<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>

<HEAD>
<TITLE>学校管理</TITLE>

<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>

<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />;
function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="sysUnitInfoAction.do?method=checklist";
  document.pageForm.submit();
}
</SCRIPT>
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">审核单位</TD>
 </TR>
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>单位信息</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">单位名称：</td>
                    <td><input type="text" value='<bean:write name="unitname" />'  size="30" name="unitname"/></td>
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
  <logic:equal value="skyg" name="s_sysuserinfo" property="loginname">
 <tr>
   <td>
      <INPUT class="btn_all" onclick="setState(true)" type="button" value="全选" name="selectall">
      <INPUT class="btn_none"  onclick="setState(false)" type="button" value="全不选" name="selectnone">
      <INPUT class="btn_del"  onclick="delRecord('sysUnitInfoAction.do?method=checkDelBatchRecord')" type="button" value="删除" name="btndel">
      <INPUT class="btn_check"  onclick="delRecord('sysUnitInfoAction.do?method=checkBatchRecord')" type="button" value="批量审核" name="btndel">
    </td>
     <TD align="right">
     </TD>
 </tr>
 </logic:equal>
   <logic:equal value="admin" name="s_sysuserinfo" property="loginname">
 <tr>
   <td>
      <INPUT class="btn_all" onclick="setState(true)" type="button" value="全选" name="selectall">
      <INPUT class="btn_none"  onclick="setState(false)" type="button" value="全不选" name="selectnone">
      <INPUT class="btn_del"  onclick="delRecord('sysUnitInfoAction.do?method=checkDelBatchRecord')" type="button" value="删除" name="btndel">
      <INPUT class="btn_check"  onclick="delRecord('sysUnitInfoAction.do?method=checkBatchRecord')" type="button" value="批量审核" name="btndel">
    </td>
     <TD align="right">
     </TD>
 </tr>
 </logic:equal>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title">单位名称</TD>
    <TD class="table_title" width="60">单位性质</TD>
    <TD class="table_title" width="80">负责人</TD>
    <TD class="table_title" width="80">职务</TD>
    <TD class="table_title" width="100">联系电话</TD>
    <TD class="table_title" width="80" title="学校是否有意向建立'微课平台'来发布和管理学校自己的微课">意向建平台</TD>
    <TD class="table_title" width="80">操作</TD>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <TR onMouseover="chgTRbg(this,'on')" class="table_list" onMouseout="chgTRbg(this,'off')" bgcolor="#ffffff">
     <TD align="center">
       <input type="checkbox" name="checkid" value="<bean:write property="unitid" name="model"/>" <bean:write property="flags" name="model"/>>
     </TD>
     <TD align="left"><bean:write name="model" property="unitname"/></TD>
     <TD align="center"><java2code:value codetype="unittype" property="type"></java2code:value></TD>
     <TD align="center"><bean:write name="model" property="linkman"/></TD>
     <TD align="center"><bean:write name="model" property="job"/></TD>
     <TD align="center"><bean:write name="model" property="telephone"/></TD>
     <TD align="center" style="color:green;"><logic:equal value="1" name="model" property="product">有</logic:equal></TD>
      <td align="center">
        <java2page:button url="sysUnitInfoAction.do" property="unitid" readonly="V"/>
      </td>
     </TR>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="sysUnitInfoAction.do?method=checklist" name="pagelist" />
      <input type="hidden" name="startcount" id="startcount" value="<bean:write name="startcount"/>">
    </TD>
  </TR>
</TABLE>
</FORM>
</BODY>
</html:html>
