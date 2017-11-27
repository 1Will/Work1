<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<HTML>
<HEAD>
<TITLE>操作成功</TITLE>
<STYLE type=text/css>BODY {
	FONT-SIZE: 9pt
}
TD {
	FONT-SIZE: 9pt
}
TH {
	FONT-SIZE: 9pt
}
BODY {
	BACKGROUND-IMAGE: none; MARGIN: 0px; BACKGROUND-REPEAT: repeat-x
}
</STYLE>
<logic:present name="jumpurl">
<meta http-equiv="refresh" content="5;url='<%=request.getAttribute("jumpurl")%>'">
</logic:present>
</HEAD>
<BODY>
<P>&nbsp;</P>
<P>&nbsp;</P>
<TABLE cellSpacing=0 cellPadding=0 width=450 align=center border=0>
  <TR>
    <TD vAlign=top colSpan=3>
      <TABLE height=43 cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD width=142><IMG height=43 src="/public/images/comm/mess_01.gif" width=142></TD>
          <TD background="/public/images/comm/left_lm_01.gif">&nbsp;</TD>
          <TD width=17><IMG height=43 src="/public/images/comm/left_lm_02.gif"  width=17></TD>
        </TR>
      </TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TR>
          <TD width=15><IMG height=8 src="/public/images/comm/left_lm_03.gif" width=15></TD>
          <TD background="/public/images/comm/left_lm_04.gif"><IMG height=8 src="/public/images/comm/left_lm_04.gif" width=1></TD>
          <TD width=17><IMG height=8 src="/public/images/comm/left_lm_05.gif"  width=17></TD>
        </TR>
      </TABLE>
      <TABLE  cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TR>
          <TD width=15 background="/public/images/comm/left_lm_06.gif">&nbsp;</TD>
          <TD align=middle bgColor=#f8fcff>
            <P><IMG height=60 src="/public/images/comm/mess_w.gif" width=60></P>
            <P><STRONG><FONT
            color=#ff0000><br /><bean:write name="promptinfo"/><br /></FONT>
            <logic:present name="jumpurl">
             <br /> <a href="<%=request.getAttribute("jumpurl")%>">[如果您的浏览器没有自动跳转，请点击这里] </a> <br />
             </logic:present>
             <br />
            </STRONG></P>
          </TD>
          <TD vAlign=top width=60 bgColor=#f8fcff><IMG height=54
            src="/public/images/comm/mess_02.gif" width=56></TD>
          <TD width=17
        background="/public/images/comm/left_lm_07.gif">&nbsp;</TD>
        </TR>
      </TABLE>
      <TABLE height=15 cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TR>
          <TD width=15><IMG height=15 src="/public/images/comm/left_lm_08.gif"
            width=15></TD>
          <TD background="/public/images/comm/left_lm_09.gif"><IMG height=15
            src="/public/images/comm/left_lm_09.gif" width=1></TD>
          <TD width=17><IMG height=15 src="/public/images/comm/left_lm_010.gif"
            width=17></TD></TR>
        </TABLE>
       </TD>
     </TR>
   </TABLE>
</BODY>
</HTML>
