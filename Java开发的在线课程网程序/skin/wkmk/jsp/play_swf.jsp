<%@ page contentType="text/html;charset=utf-8"%>
<table  width="810" height="460" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td align="center" valign="middle">
		<object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0' width='810' height='460'> 
		<param name='movie' value='<bean:write name="vodFilmLet" property="path"/>'>
		<param name='quality' value='high'>
		<embed src='<bean:write name="vodFilmLet" property="path"/>' quality='high' pluginspage='http://www.macromedia.com/go/getflashplayer' type='application/x-shockwave-flash' width='810' height='460'></embed> 
		</object>
	  </td>
    </tr>
</table>