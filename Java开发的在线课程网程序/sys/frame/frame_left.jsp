<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<HTML>
<HEAD>
<TITLE><bean:write name="s_sysunitinfo" property="unitname"/></TITLE>
<%@ include file="../../public/jsp/style.jsp"%>
<SCRIPT language="JavaScript" src="public/js/module_menu.js"></SCRIPT>
</HEAD>
<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<TABLE height="100%" cellSpacing=0 cellPadding=0 width=193 border="0">
  <TR>
    <TD height=20  valign="top">
      <table width="193" height="17" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="15%" height="17"><img src="/public/images/frame/left001.gif" width="30" height="27"></td>
          <td background="/public/images/frame/left002.gif">欢迎您:<font color="red"><bean:write name="s_sysuserinfo" property="username"/></font></td>
        </tr>
      </table>
    </TD>
  </TR>

  <TR>
    <TD>
     <table width="193" height="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="5" height="100%" align="left" valign="top" background="/public/images/frame/left004.gif"><img src="/public/images/left004.gif" width="5" height="1"></td>
          <td width="179" align="center" valign="top" bgcolor="#f1f5ff">

    <table width="98%" border="0" height="100%" cellpadding="0" cellspacing="0">
      <TR>
       <TD vAlign=top>
        <!--菜单开始-->
	  <TABLE width="100%" height="100%" border=0 cellPadding=0 cellSpacing=0 id="Table_menubar">
           <logic:iterate id="model"  name="datalist" indexId="index">
            <!--菜单标题-->
            <TR>
              <TD id="td_menubar_title_<%=index.intValue()+1%>"   noWrap  align="middle" width="100%"
			 style="cursor:pointer;"	onclick=javascript:switchoicqBar(<%=index.intValue()+1%>);>
		  <table width="100%" border="0" cellspacing="0" cellpadding="0" title="↓<bean:write name="model" property="modulename"/>">
                  <tr>
                    <td height="27" align="center" style="background-image:url('/public/images/frame/anniu001.gif');background-repeat:no-repeat;" valign="middle"><b><bean:write name="model" property="modulename"/></b></td>
                  </tr>
                </table>
              </TD>
            </TR>
            <!--菜单内容-->
            <TR	>
              <TD id=td_menubar_<%=index.intValue()+1%> height="100%" class=menu_bar_td_border valign="top">
                <DIV id=menubar_<%=index.intValue()+1%> style="DISPLAY: block; height:100%;">
                  <TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%"  border=0>
                      <TR>
                        <TD align="center" valign="top" class="menu_bar_td_btn_bg"><iframe name="sub_frame_menu<%=index.intValue()+1%>" width="100%" height="100%"  frameborder="0" src="sysFrameAction.do?method=frame_left_frame&parentno=<bean:write name="model" property="moduleno"/>" scrolling="no"> </iframe></TD>
                      </TR>
                  </TABLE>
                </DIV>
		</TD>
            </TR>
           </logic:iterate>
      </TABLE>
<!--记录菜单总数目的隐藏域-->
 <input id="oicqItemNum" name="oicqItemNum" type="hidden" value="<bean:write name="datalistsize"/>">
<SCRIPT language=javascript>
 switchoicqBar(1);//初始显示
</SCRIPT>
<!--菜单结束-->
    </TD>
  </TR>
            </table>
          </td>
        </tr>
      </table>
    </TD>
  </TR>
  <tr>
    <td height="13">
      <table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="left" valign="top"><img src="/public/images/frame/left00701.gif" width="193" height="13"></td>
        </tr>

      </table>
    </td>
  </tr>
</TABLE>
</BODY>
</HTML>
