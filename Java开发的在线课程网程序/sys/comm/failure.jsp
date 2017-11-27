<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<HTML>
<HEAD>
<TITLE>操作失败</TITLE>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<script type="text/javascript">
function dback(){
	window.history.back();
}
</script>
</HEAD>
<BODY>
<P>&nbsp;</P>
<P>&nbsp;</P>
<TABLE cellSpacing=0 cellPadding=0 width=450 align=center border=0>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <TABLE height=43 cellSpacing=0 cellPadding=0 width="100%" border="0">
        <TR>
          <TD width=142><IMG height=43 src="/public/images/comm/userlogin.gif"  width=142></TD>
          <TD background="/public/images/comm/left_lm_01.gif">&nbsp;</TD>
          <TD width=17><IMG height=43 src="/public/images/comm/left_lm_02.gif"  width=17></TD>
        </TR>
      </TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" border="0">
        <TR>
          <TD width=15><IMG height=8 src="/public/images/comm/left_lm_03.gif"  width=15></TD>
          <TD background="/public/images/comm/left_lm_04.gif"><IMG height=8 src="/public/images/comm/left_lm_04.gif" width=1></TD>
          <TD width=17><IMG height=8 src="/public/images/comm/left_lm_05.gif" width=17></TD>
        </TR>
      </TABLE>
      <TABLE height=135 cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TR>
          <TD width=15 background="/public/images/comm/left_lm_06.gif">&nbsp;</TD>
           <TD vAlign="middle" align="right" width=100 bgColor=#f8fcff><IMG
            src="/public/images/comm/mess_f.gif"></TD>
          <TD align="center" bgColor=#f8fcff>
            <P><STRONG><FONT
            color=#ff0000><br /><bean:write name="promptinfo"/><br />
            </FONT>
            <br />
            <a href="javascript:" onclick="dback()">[点这里返回上一页] </a> <br />
            </STRONG></P>
          </TD>
           <TD vAlign=top width=60 bgColor=#f8fcff><IMG height=54
            src="/public/images/comm/mess_02.gif" width=56></TD>
          <TD width=17 background="/public/images/comm/left_lm_07.gif">&nbsp;</TD>
        </TR>
      </TABLE>
      <TABLE height=15 cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TR>
          <TD width=15>
            <IMG height=15 src="/public/images/comm/left_lm_08.gif"  width=15>
          </TD>
          <TD background="/public/images/comm/left_lm_09.gif">
            <IMG height=15 src="/public/images/comm/left_lm_09.gif" width=1>
          </TD>
          <TD width=17>
            <IMG height=15 src="/public/images/comm/left_lm_010.gif" width=17>
          </TD>
        </TR>
       </TABLE>
      </TD>
     </TR>
     </TABLE>
</BODY>
</HTML>
