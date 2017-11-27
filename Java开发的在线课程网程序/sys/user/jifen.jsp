<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<TITLE>修改密码</TITLE>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable" width="100%">
  <TR>
    <TD class="page_title">我的积分</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
       <TABLE width="260" align=center border="0">
		  <tr>
            <td class="table_edit_right">我的头衔：</td>
            <td class="table_edit_left" style="color:green;">
                <bean:write name="model" property="flags"/>             
            </td>
          </tr> 
		  <tr>
            <td class="table_edit_right">我的积分：</td>
            <td class="table_edit_left">
                 <input type="text" name="jifen" readonly="readonly" style="background-color:#eee;width:90px;height:26px;color:green;" value='<bean:write name="model" property="jifen"/>' />
            </td>
          </tr>                
          <tr>
            <td class="table_edit_right">我的财富：</td>
            <td class="table_edit_left">
                 <input type="text" name="caifu" readonly="readonly" style="background-color:#eee;width:90px;height:26px;color:green;" value='<bean:write name="model" property="caifu"/>' />
            </td>
          </tr>  
       </TABLE>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>                   
