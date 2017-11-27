<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>

<HEAD>
<TITLE>学校管理</TITLE>

<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>

<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />;
function addRecord(){
  document.pageForm.action="sysUnitInfoAction.do?method=beforeAdd";
  document.pageForm.submit();
}
function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="sysUnitInfoAction.do?method=list";
  document.pageForm.submit();
}
function userManager(unitid){
	var path = "sysUnitUserInfoAction.do?method=main&unitid="+unitid;
    var result =window.showModalDialog(path,"单位用户","dialogWidth:800px;dialogheight:550px;toolbar:no;status:no;toolbar:no;")
}
function roleManager(unitid){
	var path = "sysUnitRoleInfoAction.do?method=main&unitid="+unitid;
    var result =window.showModalDialog(path,"单位角色","dialogWidth:800px;dialogheight:550px;toolbar:no;status:no;toolbar:no;")
}
</SCRIPT>
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">单位管理</TD>
 </TR>
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>单位管理</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">单位名称：</td>
                    <td><input type="text" value='<bean:write name="unitname" />'  size="30" name="unitname"/></td>
                    <%-- 
                    <td width="80" height="25" align="right">所属地区：</td>
                    <td><java2code:option codetype="area" name="area" valuename="area" ckname="所属地区"></java2code:option></td>
                    --%>
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
      <INPUT class="btn_add"  onclick="addRecord()" type="button" value="新增" name="btnadd">
      <INPUT class="btn_del"  onclick="delRecord('sysUnitInfoAction.do?method=delBatchRecord')" type="button" value="禁用" name="btndel">
      <%-- 
      <logic:equal value="administrator" name="s_sysuserinfo" property="loginname">
      <INPUT class="btn_import"  onclick="importUnit()" type="button" value="批量导入" name="btnadd">
      </logic:equal>
      --%>
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
    <TD class="table_title" width="150">单位简称</TD>
    <!-- 
    <TD class="table_title" width="80">所属地区</TD>
    <TD class="table_title" width="80">类别</TD>
    <TD class="table_title" width="50">性质</TD>
     -->
    <TD class="table_title" width="80">负责人</TD>
    <TD class="table_title" width="100">联系电话</TD>
    <TD class="table_title" width="80">单位状态</TD>
    <TD class="table_title" width="80">操作</TD>
</tr>
<!--循环列出所有数据-->
  <%Integer startcount = (Integer)request.getAttribute("startcount"); %>
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <TR onMouseover="chgTRbg(this,'on')" class="table_list" onMouseout="chgTRbg(this,'off')" bgcolor="#ffffff">
     <TD align="center">
       <input type="checkbox" name="checkid" value="<bean:write property="unitid" name="model"/>" <bean:write property="flags" name="model"/>>
     </TD>
     <TD align="left"><bean:write name="model" property="unitname"/></TD>
     <TD align="center"><bean:write name="model" property="shortname"/></TD>
     <%-- 
     <TD align="center"><java2code:value codetype="area" property="area"/></TD>
     <TD align="center"><java2code:value codetype="unittype" property="type"/></TD>
     <TD align="center"><java2code:value codetype="ctype" property="ctype"/></TD>
     --%>
     <TD align="center"><bean:write name="model" property="linkman"/></TD>
     <TD align="center"><bean:write name="model" property="telephone"/></TD>
     <TD align="center">
     	<logic:equal value="1" name="model" property="state">正常</logic:equal>
     	<logic:equal value="2" name="model" property="state"><FONT color="red">禁用</FONT></logic:equal>
     </TD>
      <td align="center">
        <java2page:button url="sysUnitInfoAction.do" property="unitid" readonly="E"/>
        <a style="cursor:pointer;" title="单位角色" onclick="javascript:roleManager('<bean:write name="model" property="unitid"/>')"><img border="0"  src="/public/images/main/icon2.gif"/></a>&nbsp;
        <a style="cursor:pointer;" title="单位用户" onclick="javascript:userManager('<bean:write name="model" property="unitid"/>')"><img border="0"  src="/public/images/main/icon7.gif"/></a>&nbsp;  
      </td>
     </TR>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="sysUnitInfoAction.do?method=list" name="pagelist" />
      <input type="hidden" name="startcount" id="startcount" value="<bean:write name="startcount"/>">
    </TD>
  </TR>
</TABLE>
</FORM>
</BODY>
</html:html>
