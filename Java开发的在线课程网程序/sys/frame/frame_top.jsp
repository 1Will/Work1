<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<%@ page import="java.util.List" %>
<html>
<head>
<title>系统管理</title>
<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<style type="text/css">
#switchbutton {
float:left;
width:100%;
font-size:100%;
line-height:15px;
border-bottom:0px solid #666666;
background-image: none;
}
#switchbutton ul {
margin:0;
list-style:none;
}
#switchbutton li {
display:inline;
margin:0;
padding:0;
}
#switchbutton a {
float:left;
background:url("/public/images/frame/switchbuttonleft.gif") no-repeat left top;
margin:0;
padding:0 0 0 4px;
text-decoration:none;
}
#switchbutton a span {
float:left;
display:block;
background:url("/public/images/frame/switchbuttonright.gif") no-repeat right top;
padding:5px 15px 4px 6px;
color:#000000;
font-size: 9pt;
font-weight: bold;
}
/* Commented Backslash Hack hides rule from IE5-Mac \*/
#switchbutton a span {float:none;}
/* End IE5-Mac hack */
#switchbutton a:hover span {
color:#FFF;
}
#switchbutton a:hover {
background-position:0% -42px;
}
#switchbutton a:hover span {
background-position:100% -42px;
}

#switchbutton #current a {
        background-position:0% -42px;
}
#switchbutton #current a span {
        background-position:100% -42px;
}
</style>
<SCRIPT language=javascript>
//刷新
function RefreshMainWindow(){
  parent.leftFrame.location.reload();
  parent.mainFrame.location.reload();
}
</SCRIPT>
</head>
<body>
<table width="100%" height="86" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#ddeeff">
  <tr>
    <td width="305"><img src="/public/images/frame/top_logo.gif" width="305" height="86"></td>
    <td >
      <table width="100%" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="55">&nbsp;</td>
        </tr>
        <tr>
           <td  height="31"  width="100%" background="/public/images/frame/top_bg_01.gif">&nbsp;</td>
        </tr>
      </table>
    </td>
    <td valign="top" width="510">
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="22" colspan="2" align="right">今天是：<%=com.util.date.DateTime.getDateYMDWCN()%>&nbsp;&nbsp;</td>
      </tr>
      <tr>
        <td height="33" colspan="2" valign="bottom" align="right">
          <div id="switchbutton">

          </div>
          </td>
      </tr>
      <tr>
        <td width="39" height="31"><img src="/public/images/frame/top_bg_02.gif" width="39" height="31"></td>
        <td width="510" height="31" background="/public/images/frame/top_bg_03.gif" align="right">
          <table width="100%" height="31" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="35" align="center"><a href="index.jsp" target="_blank"><img src="/public/images/frame/top_biao_01.gif" width="22" height="31" border="0"></a></td>
            <td width="50"><a href="index.jsp" target="_blank">首 页</a></td>
            <td width="35" align="center"><a href="javascript:history.go(-1)"><img src="/public/images/frame/top_biao_02.gif" width="22" height="31" border="0"></a></td>
            <td width="50"><a href="javascript:history.go(-1)">后 退</a></td>
            <td width="35" align="center"><a href="javascript:history.go(1)"><img src="/public/images/frame/top_biao_03.gif" width="22" height="31" border="0"></a></td>
            <td width="50"><a href="javascript:history.go(1)">前 进</a></td>
            <td width="35" align="center"><img src="/public/images/frame/top_biao_04.gif" width="22" height="31"></td>
            <td width="50"><a href="javascript:RefreshMainWindow();">刷 新</a></td>
            <td width="35" align="center"><img src="/public/images/frame/top_biao_05.gif" width="22" height="31"></td>
            <td width="50"><a href="#">帮 助</a></td>
            <td width="35" align="center"><a href="sysUserLoginAction.do?method=logout" target="_top"><img src="/public/images/frame/top_biao_06.gif" width="22" height="31" border="0"></a></td>
            <td width="50"><a href="sysUserLoginAction.do?method=logout" target="_top">退 出</a></td>
            </tr>
           </table>
        </td>
      </tr>
    </table>
    </td>
  </tr>
</table>
</body>
</html>
