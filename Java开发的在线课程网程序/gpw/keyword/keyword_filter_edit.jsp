<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="com.util.string.encode.Encode" %>
<%@page import="com.bzt.gpw.bo.GpwKeywordFilter"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<TITLE>关键词过滤</TITLE>
<%@ include file="/public/jsp/style.jsp"%>
<%@ include file="/public/jsp/meta.jsp"%>

<SCRIPT language=javascript>

function saveRecord(){
  obj = document.all("gpwKeywordFilterActionForm");

  obj.action="gpwKeywordFilterAction.do?method=updateSave";
  obj.submit();
}

function changeValue(){
	var status = document.getElementById('status');
	if(status.checked == true){
		document.getElementById('gpwKeywordFilter.status').value = "1";
	}else{
		document.getElementById('gpwKeywordFilter.status').value = "0";
	}
}
</SCRIPT>
<%
	GpwKeywordFilter model = (GpwKeywordFilter)request.getAttribute("model");
%>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable" width="100%">
  <TR>
    <TD class="page_title">关键词过滤</TD>
  </TR>
  <TR>
    <TD class="page_title"></TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/gpwKeywordFilterAction.do" method="post" >
       <TABLE width="588" align=center border=0 cellpadding="1" cellspacing="0">
		  <tr>
            <td align="left" width="80%">
            	<input type="checkbox" name="status" id="status" onclick="changeValue()" <logic:equal value="1" name="model" property="status">checked="checked"</logic:equal>>网站启用关键词过滤器
            </td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2" height="5">&nbsp;</td>
          </tr>
          <tr>
            <td align="left" width="80%"><font color="green">格式：关键词|关键词(说明：用英文中的'|'字符)</font></td>
            <td>&nbsp;</td>
          </tr>
           <tr>
            <td align="center"  colspan="2">
           	   <textarea rows="12" cols="80" name="gpwKeywordFilter.filtercontent"><%=Encode.nullToBlank(model.getFiltercontent()) %></textarea>
            </td>
           </tr>

          <tr bgcolor="#ffffff">
                <td colspan="2" align="center">
                  <input type="button" value="保存" id="btnsave" name="btnsave" class=btn_save onClick="javascript:saveRecord()">
                </td>
           </tr>
       </TABLE>
       <input type="hidden" name="gpwKeywordFilter.keywordid" value="<bean:write property="keywordid"  name="model"/>"/>
       <input type="hidden" name="gpwKeywordFilter.status" id="gpwKeywordFilter.status" value="<bean:write property="status"  name="model"/>"/>
       <input type="hidden" name="gpwKeywordFilter.unitid" value="<bean:write property="unitid"  name="model"/>"/>
       <input type="hidden" name="keywordid" value="<bean:write property="keywordid"  name="model"/>"/>
      </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
