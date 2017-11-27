<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html>
<%@ include file="../../public/jsp/style.jsp"%>
 
<head>
<title>用户组成员</title>
<base target="_self">
<%@ include file="../../public/jsp/meta.jsp"%>
</head>

<body scrolling="no">
<TABLE cellpadding="0" cellspacing="0" width="100%" height="450px" align="center" style="overflow:hidden;">
<tr>
  <td colspan="3" class="page_title">
    [<bean:write name="groupname"/>]成员
  </td>
</tr>
<tr>
	<td width="48%" align="center">
		<IFRAME border="0" frameborder="no" height="450px" id="left" name="left" scrolling="no" src="sysUserGroupMemberAction0.do?method=outmember&groupid=<bean:write name="groupid"/>" width="100%"></IFRAME>
	</td>
	<td width="4%" align="center">
	   <table height="200px" cellpadding="0" cellspacing="0" border="0">
	     <tr valign="middle" height="50">
	       <td>
	         <INPUT class="btn_blank" onclick="doAddAll()" type="button" value="全部添加" name="btnadd">
	       </td>
	     </tr>
	      <tr valign="middle" height="50">
	       <td>
	         <INPUT class="btn_add"  onclick="doAdd()" type="button" value="添加" name="btnadd">
	       </td>
	     </tr>
	     <tr valign="middle" height="50">
	       <td>
	         <INPUT class="btn_del"  onclick="doDelete()" type="button" value="删除" name="btnadd">
	       </td>
	     </tr>
	      <tr valign="middle" height="50">
	       <td>
	         <INPUT class="btn_blank"  onclick="doDeleteAll()" type="button" value="全部删除" name="btnadd">
	       </td>
	     </tr>
	   </table>		
	</td>
	<td width="48%" align="center">
		<IFRAME border="0" frameborder="no" height="450px" id="right" name="right" scrolling="no" src="sysUserGroupMemberAction0.do?method=inmember&groupid=<bean:write name="groupid"/>" width="100%"></IFRAME>
	</td>
</tr>
</TABLE>

</body>	
</html>

<script language="javascript">
function doAdd(){
	left.addUser();
}
function doAddAll(){
	left.addUserAll();
}
function doDelete(){
	right.delUser();
}
function doDeleteAll(){
	right.delUserAll();
}
</script>

