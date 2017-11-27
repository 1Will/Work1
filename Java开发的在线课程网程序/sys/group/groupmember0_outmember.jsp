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
 	window.parent.frames["right"].location = "/sysUserGroupMemberAction0.do?method=inmember&groupid=<bean:write name="groupid"/>";
}
function addUser(){
  if(num>0){
	  var str="此操作将把选中用户添加到当前机构中，真的要继续么？";
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
	     alert("您还没有选择添加到机构的用户呢!")
	}
	else{
	 if(confirm(str)){
	   document.pageForm.action="/sysUserGroupMemberAction0.do?method=addmember";
	   document.pageForm.submit();
	 }
	}
	}
	else{
	  alert("未选择添加到机构的用户!")
	}
}

function addUserAll(){
  if(num>0){
	  var str="此操作将把左侧所有用户添加到当前机构中，真的要继续么？";
	  if(confirm(str)){
	    document.pageForm.action="/sysUserGroupMemberAction0.do?method=addmemberall";
	    document.pageForm.submit();
	  }
  }
}
function searchRecord(){
  document.getElementById("startcount").value="0";
  document.pageForm.action="/sysUserGroupMemberAction0.do?method=outmember";
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
                    <td width="60" height="25" align="right">登录名:</td>
                    <td><input type="text" value='<bean:write name="loginname" />' size="8" name="loginname"/></td>
                    <td width="60" height="25" align="right">真实姓名:</td>
                    <td><input type="text" value='<bean:write name="username" />' size="6" name="username"/></td>
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
                         <TD align="center"><input type="checkbox" name="checkid" value='<bean:write property="userid" name="model"/>'></TD>
                         <TD align="left">&nbsp;<bean:write name="model" property="loginname"/></TD>
                         <TD align="center"><bean:write name="model" property="username"/></TD>
                         <TD align="center"><java2code:value codetype="sex" property="sex"/></TD>
                       </TR>
                     </logic:iterate>
                 </TABLE>
                </TD>
              </TR>
              
              <!--page start-->
              <TR>
                <TD vAlign=top borderColor=#ffffff align="center">
                   <java2page:pager url="sysUserGroupMemberAction0.do?method=outmember" style="2" name="pagelist"/>
                   <input type="hidden" id="startcount" name="startcount" value='<bean:write name="startcount"/>' />
                   <input type="hidden" name="groupid" value='<bean:write name="groupid"/>' />
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