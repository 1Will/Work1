<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>
<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<SCRIPT language=JavaScript src="/public/js/comm.js"  type="text/javascript"></SCRIPT>
<Head>
  <SCRIPT language=javascript>
//刷新
function RefreshMainWindow(){
  parent.menuFrame.location.reload();
  parent.mainFrame.location.reload();
}
//退出系统
function ExitSystem(){
  str="您确定要退出系统管理么？"
  if(window.confirm(str)) {
     window.parent.close();
  }
}
//菜单跳转
function gotoUrl(thehref){
   parent.mainFrame.location=thehref
}
</SCRIPT>
</head>
<BODY style="overflow-y:hidden;overflow-x:hidden" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" height="100%" align="left" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center" valign="top">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="13" align="left" valign="top"  width="19"><img src="/public/images/frame/right00300.gif" width="19" height="13"></td>
          <td height="13" align="left" valign="top" background="/public/images/frame/right0030000.gif"><img src="/public/images/frame/right0030000.gif" width="1" height="13"></td>
          <td height="13" align="left" valign="top"  width="37"><img src="/public/images/frame/right00302.gif" width="37" height="13"></td>
          </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td height="100%">
       <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="10"> </td>
           <td width="6" background="/public/images/frame/right004.gif"></td>
        </tr>
         <tr>
          <td align="center" valign="bottom" >
            <table width="97%" height="97%" border="0"  vcellpadding="0" bgcolor="white" cellspacing="0">
              <tr>
              <td align="center" valign="bottom" >
                     <iframe name="main"  width="100%" height="100%"  scrolling="auto" frameborder="0" src="sysFrameAction.do?method=frame_welcome" > </iframe>
              </td>
             </tr>
            </table>
          </td>
          <td width="6" height="100%" background="/public/images/frame/right004.gif"></td>
          </tr>
      </table>
    </td>
  </tr>
  <tr>
   <td height="13"  width="100%" align="left" valign="bottom">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
         <tr>
          <td height="13" width="19"><img src="/public/images/frame/right00501.gif" height="13" width="19"></td>
          <td><img src="/public/images/frame/right00502.gif" height="13" width="100%"></td>
          <td width="19"><img src="/public/images/frame/right00503.gif" width="19" height="13"></td>
         </tr>
      </table>
   </td>
  </tr>
</table>
</BODY>
</html:html>
