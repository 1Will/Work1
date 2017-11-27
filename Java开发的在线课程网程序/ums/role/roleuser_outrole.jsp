<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html>

<head>
<TITLE>待选用户</TITLE>
<base target="_self">
<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
</head>

<SCRIPT language=javascript>
  var num=<bean:write name="pagelist" property="pageCount" />;
  
function doOnLoad(){
 	window.parent.frames["right"].location = "/sysUserRoleAction1.do?method=inroleuser&productid=<bean:write name="productid"/>&unitid=<bean:write name="unitid"/>&roleid=<bean:write name="roleid"/>";
}
function addUser(){
  if(num>0){
	  var str="此操作将给选中用户授权，真的要继续么？";
	  var checkids="";
	  if (num>1){
	  for(i=0;i<num;i++){
	   if (document.pageForm.checkid[i].checked==true){
	      checkids=checkids+document.pageForm.checkid[i].value+",";
	   }
	 }
	}
	else{
	   if (document.pageForm.checkid.checked==true)	{
		checkids=document.pageForm.checkid.value;
	   }
	 }
	if (checkids=="") {
	     alert("您还没有选择待授权用户呢!")
	}
	else{
	 if(confirm(str)){
	   document.pageForm.action="/sysUserRoleAction1.do?method=addroleuser";
	   document.pageForm.submit();
	 }
	}
	}
	else{
	  alert("未选择待授权用户!")
	}
}

function addUserAll(){
  if(num>0){
	  var str="此操作将给左侧所有用户授权，真的要继续么？";
	  if(confirm(str)){
	    document.pageForm.action="/sysUserRoleAction1.do?method=addroleuserall";
	    document.pageForm.submit();
	  }
  }
}
function searchRecord(){
  document.getElementById("startcount").value="0";
  document.pageForm.action="/sysUserRoleAction1.do?method=outroleuser";
  document.pageForm.submit();
}
</SCRIPT>
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>

<BODY leftMargin=0 topMargin=0 scroll=auto <logic:present name="reload">onload="javascript:doOnLoad()"</logic:present>>
<FORM name="pageForm" method=post>
<TABLE class="page_maintable">
  
	  <TR>
	    <TD class="page_title" style="font-size:14px;">待选用户</TD>
	  </TR>  
  
  <tr height="30">
   <td>
       <TABLE class="table_search" width="98%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="50" height="25" align="right">登录名:</td>
                    <td><input type="text" value='<bean:write name="loginname" />' size="20" name="loginname"/></td>
                    <td>&nbsp;</td>
                    <td><input type="button" value="搜索" onclick="searchRecord()" class="btn_search" /></td>
                  </tr>
                </table>
              </td>              
            </tr>
        </table>
   </td>
 </tr>
  
  <TR>
    <TD vAlign=top align="center">
       <TABLE width="100%" align=center border=0><!--serach end-->
              <TR>
                <TD vAlign=top align="center">
                  <TABLE class="page_table" cellpadding="1" cellspacing="1" bgcolor="#CECFCE" width="100%">
                    <tr class="table_title">
                       <TD align="center" width="10%" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
                       <TD align="center" width="40%">登录名</TD>
                       <TD align="center" width="40%">真实姓名</TD>
                       <TD align="center" width="10%">性别</TD>
                    </tr>
                      <logic:iterate id="model" property="datalist" name="pagelist" >
                       <TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
                         <TD align="center"><input type="checkbox" name="checkid" value='<bean:write property="sysUserInfo.userid" name="model"/>'></TD>
                         <TD align="left">&nbsp;<bean:write name="model" property="sysUserInfo.loginname"/></TD>
                         <TD align="center"><bean:write name="model" property="sysUserInfo.username"/></TD>
                         <TD align="center"><java2code:value codetype="sex" property="sysUserInfo.sex"/></TD>
                       </TR>
                     </logic:iterate>
                 </TABLE>
                </TD>
              </TR>
              
              <!--page start-->
              <TR>
                <TD vAlign=top borderColor=#ffffff align="center">
                   <java2page:pager url="sysUserRoleAction1.do?method=outroleuser" style="2" name="pagelist"/>
                   <input type="hidden" id="startcount" name="startcount" value='<bean:write name="startcount"/>' />
                   <input type="hidden" name="roleid" value='<bean:write name="roleid"/>' />
                   <input type="hidden" name="productid" value='<bean:write name="productid"/>' />
                   <input type="hidden" name="unitid" value='<bean:write name="unitid"/>' />
               </TD>
              </TR>                          
              <!--page end-->
            </TABLE>
    </TD>
    </TR>
</TABLE>
</FORM>
</BODY>
</html>