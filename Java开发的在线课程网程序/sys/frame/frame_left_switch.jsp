<HTML>
<HEAD>
<TITLE>控制左菜单显隐</TITLE>
<LINK href="/public/css/style.css" type="text/css" rel="stylesheet">
<STYLE type="text/css">BODY {
	MARGIN: 0px; BACKGROUND-COLOR: #c9c9c9
}
</STYLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<SCRIPT language=JavaScript>
var LEFT_MENU_VIEW=0;
function leftmenu_open()
{
   LEFT_MENU_VIEW=0;
   leftmenu_ctrl();
}
function leftmenu_ctrl()
{
   if(LEFT_MENU_VIEW==0){
      parent.frame2.cols="184,9,*";
      LEFT_MENU_VIEW=1;
     // myarrow.src="/public/images/frame/arrow_l.gif";
   }
   else{
      parent.frame2.cols="0,9,*";
      LEFT_MENU_VIEW=0;
    //  myarrow.src="/public/images/frame/arrow_1.gif";
   }
}
function leftmenu_close()
{
      parent.frame2.cols="0,9,*";
      LEFT_MENU_VIEW=0;
 }
function setPointer(theRow, thePointerColor){
    if (typeof(theRow.style) == 'undefined' || typeof(theRow.cells) == 'undefined')
    {
        return false;
    }

    var row_cells_cnt=theRow.cells.length;
    for (var c = 0; c < row_cells_cnt; c++){
        theRow.cells[c].bgColor = thePointerColor;
    }
    return true;
}

</SCRIPT>
<META content="MSHTML 6.00.3790.94" name=GENERATOR></HEAD>
<BODY leftMargin=0 topMargin=0 onload=leftmenu_ctrl()>
<TABLE height="100%" cellSpacing=0   border="0" cellPadding=0 width="10"
align=center background="/public/images/frame/left005.gif">
  <TBODY>
  <TR>
          <td align="left"  height="27" valign="top" ><img src="/public/images/frame/left00302.gif" width="9" height="27" border="0"></td>
  </TR>
  <TR>
    <TD>
      <TABLE height=50 cellSpacing=0 cellPadding=0 width="100%">
        <TBODY>

        <TR onclick=leftmenu_ctrl()>
          <td width="9" align="left" valign="middle" ><img src="/public/images/frame/left008.gif" width="9" height="64" border="0"></td>
        </TR>
        </TBODY>
      </TABLE>
     </TD>
    </TR>
      <tr>
          <td align="left" height="13" valign="bottom"><img src="/public/images/frame/left00702.gif" width="9" height="13"></td>
        </tr>
  </TBODY>
</TABLE>
</BODY>
</HTML>
