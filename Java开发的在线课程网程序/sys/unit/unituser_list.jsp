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

function addRecord(){
  document.pageForm.action="/sysUnitUserInfoAction.do?method=beforeAdd";
  document.pageForm.submit();
}
function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="/sysUnitUserInfoAction.do?method=list";
  document.pageForm.submit();
}
function doEdit(userid){
  document.pageForm.action="/sysUnitUserInfoAction.do?method=beforeUpdate&&objid="+userid;
  document.pageForm.submit();
}
function addUserRole(userid){
	var diag = new top.Dialog();
	diag.Title = "角色权限";
	diag.URL = 'sysUnitUserInfoAction.do?method=rolemain&unitid=<bean:write name="unitid"/>&userid='+userid;
	diag.Width = 400;
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
                  	 <!-- 
                  	 <td width="80" align="right">类型:</td>
                     <td>
                     <select name="usertype">
                     	<option value="1">教师</option>
                     	<option value="2" <logic:equal value="2" name="usertype">selected="selected"</logic:equal>>学生</option>
                     </select>
                     </td>
                      -->
                     <td width="80" height="25" align="right">登录名:</td>
                     <td><input type="text" value='<bean:write name="loginname" />'  size="15" name="loginname"/></td>
                     <td width="100" align="right">真实姓名:</td>
                     <td><input type="text" value='<bean:write name="username" />'  size="15" name="username"/></td>
                     <td width="80" align="right">性别:</td>
                     <td><java2code:option name="sex" codetype="sex" valuename="sex" ckname="性别"/></td>
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
  
  <TR>
     <TD vAlign=top align="left">
       <INPUT class="btn_all" onClick="setState(true)" type="button" value="全选" name="selectall">
       <INPUT class="btn_none"  onclick="setState(false)" type="button" value="全不选" name="selectnone">
       <INPUT class="btn_add"  onclick="addRecord()" type="button" value="新增" name="btnadd">
       <INPUT class="btn_del"  onclick="delRecord('sysUnitUserInfoAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
        
      </TD>
   </TR>
   <%-- --%>
</table>

 <TABLE class="page_table" cellSpacing=1 cellPadding=1 width="98%" align="center">
	<tr>
      <TD align="center" class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
      <TD class="table_title">登录名</TD>
      <TD align="center" class="table_title" width="150">真实姓名</TD>
      <TD align="center" class="table_title" width="40">性别</TD>
      <TD align="center" class="table_title" width="80">出生年月</TD>
      <TD align="center" class="table_title" width="140">注册时间</TD>
      <TD align="center" width="80" class="table_title">操作</TD>
	</tr>
	<!--循环列出所有数据-->
    <logic:iterate id="model" property="datalist" name="pagelist" >
    <TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
      <TD align="center"><input type="checkbox" name="checkid" value='<bean:write property="userid" name="model"/>'></TD>
      <TD align="left">&nbsp;<bean:write name="model" property="loginname"/></TD>
      <TD align="center"><bean:write name="model" property="username"/></TD>
      <TD align="center"><java2code:value codetype="sex" property="sex"/></TD>
      <TD align="center"><bean:write name="model" property="birthday"/></TD>
      <TD align="center"><bean:write name="model" property="createdate"/></TD>
      <td align="center">
        <a style="cursor:pointer;" onclick="doEdit('<bean:write property="userid" name="model"/>')" title="编辑"><img alt="编辑" border="0" src="/public/images/main/edit.gif"/></a>
        <a style="cursor:pointer;" onclick="addUserRole('<bean:write name="model" property="userid"/>')" title="人员角色"><img alt="人员角色" border="0" src="/public/images/main/icon2.gif"/></a>  
      </td>
     </TR>
     </logic:iterate>
 </TABLE>
 <TABLE width="98%">
  <TR>
    <TD vAlign=top borderColor=#ffffff align="center">
       <java2page:pager url="sysUnitUserInfoAction.do?method=list" name="pagelist"/>
       <input type="hidden" id="startcount" name="startcount" value="<bean:write name="startcount"/>" />
       <input type="hidden" id="unitid" name="unitid" value="<bean:write name="unitid"/>" />
   </TD>
  </TR>
  <!--page end-->
</TABLE>

</form>
</BODY>
</html:html>


