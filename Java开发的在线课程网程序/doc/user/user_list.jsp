<%@page import="com.bzt.sys.util.Constants"%>
<%@page import="com.bzt.sys.bo.SysUnitInfo"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>

<HEAD>
<TITLE>管理员设置</TITLE>
<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />;

function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="/docUserGradeAction.do?method=userList";
  document.pageForm.submit();
}

function setUserGrade(userid, type){
	var diag = new top.Dialog();
	diag.Title = "用户年级设置";
	diag.URL = '/docUserGradeAction.do?method=beforeUpdate&userid=' + userid + '&type=' + type;
	diag.Width = 500;
	diag.Height = 520;
	diag.CancelEvent = function(){
		diag.close();
	};
	diag.show();
}
</SCRIPT>
</HEAD>

<BODY leftMargin=0 topMargin=0 scroll=auto>
<FORM name="pageForm" method=post>
<TABLE width="98%" align="center">
  <TR>
    <TD class="page_title">用户管理</TD>
  </TR>
  <TR>
    <TD height="30">
      <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
               <td >查询>>用户信息</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                     <td width="80" height="25" align="right">登录名:</td>
                     <td><input type="text" value='<bean:write name="loginname" />'  size="15" name="loginname"/></td>
                     <td width="80" align="right">真实姓名:</td>
                     <td><input type="text" value='<bean:write name="username" />'  size="15" name="username"/></td>
                  </tr> 
                </table>
              </td>
              <td width="88" class="bg_basecolor"><input type="button" value="搜索" onclick="searchRecord()" class="btn_search" /></td>
            </tr>
        </table>
    </TD>
  </TR>
  <TR>
    <TD class="page_blank"></TD>
  </TR>
</table>

 <TABLE class="page_table" cellSpacing=1 cellPadding=1 width="98%" align="center">
	<tr>
      <TD align="center" class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
      <TD class="table_title">登录名</TD>
      <TD align="center" class="table_title" width="150">真实姓名</TD>
      <TD align="center" class="table_title" width="80">性别</TD>
      <TD align="center" class="table_title" width="150">注册时间</TD>
      <TD align="center" width="60" class="table_title">操作</TD>
	</tr>
	<!--循环列出所有数据-->
    <logic:iterate id="model" property="datalist" name="pagelist" >
    <TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
      <TD align="center"><input type="checkbox" name="checkid" value='<bean:write property="sysUserInfo.userid" name="model"/>'></TD>
      <TD align="left">&nbsp;<bean:write name="model" property="sysUserInfo.loginname"/></TD>
      <TD align="center"><bean:write name="model" property="sysUserInfo.username"/></TD>
      <TD align="center"><java2code:value codetype="sex" property="sysUserInfo.sex"/></TD>
      <TD align="center"><bean:write name="model" property="sysUserInfo.createdate"/></TD>
      <td align="center">
        <a style="cursor:pointer;" onclick="setUserGrade('<bean:write name="model" property="sysUserInfo.userid"/>','2')" title="微课转移权限设置"><img border="0" src="/public/images/main/set.gif"/></a>
        <a style="cursor:pointer;" onclick="setUserGrade('<bean:write name="model" property="sysUserInfo.userid"/>','1')" title="文档转移权限设置"><img border="0" src="/public/images/main/module.gif"/></a>
      </td>
     </TR>
     </logic:iterate>
 </TABLE>
 <TABLE width="98%">
  <TR>
    <TD vAlign=top borderColor=#ffffff align="center">
       <java2page:pager url="docUserGradeAction.do?method=userList" name="pagelist"/>
       <input type="hidden" id="startcount" name="startcount" value="<bean:write name="startcount"/>" />
   </TD>
  </TR>
  <!--page end-->
</TABLE>

</form>
</BODY>
</html:html>