<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<TITLE>关键字过滤</TITLE>
<%@ include file="/public/jsp/style.jsp"%>
<%@ include file="/public/jsp/meta.jsp"%>

<SCRIPT language=javascript>

function saveRecord(){
  obj = document.all("cmsTemplateUnitActionForm");

  obj.action="cmsTemplateUnitAction.do?method=setSiteSave";
  obj.submit();
}
function changeValue(id1, id2){
	var status = document.getElementById(id1);
	if(status.checked == true){
		document.getElementById(id2).value = "1";
	}else{
		document.getElementById(id2).value = "0";
	}
}
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable" width="100%">
  <TR>
    <TD class="page_title">系统设置</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <form name="cmsTemplateUnitActionForm" method="post" >
       <TABLE width="188" align=center border=0 cellpadding="1" cellspacing="0">
          <%--
		  <tr>
            <td align="left" width="80%">
            	<input type="checkbox" id="nmly" onclick="changeValue('nmly','cmsTemplateUnit.nmly')" <logic:equal value="1" name="model" property="nmly">checked="checked"</logic:equal>>允许匿名留言
            </td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td align="left" width="80%">
            	<input type="checkbox" id="shly" onclick="changeValue('shly','cmsTemplateUnit.shly')" <logic:equal value="1" name="model" property="shly">checked="checked"</logic:equal>>需要审核留言
            </td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td align="left" width="80%">
            	<input type="checkbox" id="nmzf" onclick="changeValue('nmzf','cmsTemplateUnit.nmzf')" <logic:equal value="1" name="model" property="nmzf">checked="checked"</logic:equal>>允许匿名祝福
            </td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td align="left" width="80%">
            	<input type="checkbox" id="shzf" onclick="changeValue('shzf','cmsTemplateUnit.shzf')" <logic:equal value="1" name="model" property="shzf">checked="checked"</logic:equal>>需要审核祝福
            </td>
            <td>&nbsp;</td>
          </tr>
           
          <tr>
            <td align="left" width="80%">
            	<input type="checkbox" id="nmpl" onclick="changeValue('nmpl','cmsTemplateUnit.nmpl')" <logic:equal value="1" name="model" property="nmpl">checked="checked"</logic:equal>>允许匿名评论
            </td>
            <td>&nbsp;</td>
          </tr>
          --%>
          <tr>
            <td align="left" width="80%">
            	<input type="checkbox" id="shpl" onclick="changeValue('shpl','cmsTemplateUnit.shpl')" <logic:equal value="1" name="model" property="shpl">checked="checked"</logic:equal>>需要审核评论
            </td>
            <td>&nbsp;</td>
          </tr>
			<tr>
            <td align="left" width="80%">
            </td>
            <td>&nbsp;</td>
          </tr>
          <tr bgcolor="#ffffff">
                <td colspan="2" align="center">
                  <input type="button" value="保存" id="btnsave" name="btnsave" class=btn_save onClick="javascript:saveRecord()">
                </td>
           </tr>
       </TABLE>
       <input type="hidden" name="cmsTemplateUnit.id" value="<bean:write property="id"  name="model"/>"/>
       
       <input type="hidden" name="cmsTemplateUnit.nmly" id="cmsTemplateUnit.nmly" value="<bean:write property="nmly"  name="model"/>"/>
       <input type="hidden" name="cmsTemplateUnit.shly" id="cmsTemplateUnit.shly" value="<bean:write property="shly"  name="model"/>"/>
       <input type="hidden" name="cmsTemplateUnit.nmzf" id="cmsTemplateUnit.nmzf" value="<bean:write property="nmzf"  name="model"/>"/>
       <input type="hidden" name="cmsTemplateUnit.shzf" id="cmsTemplateUnit.shzf" value="<bean:write property="shzf"  name="model"/>"/>
       <input type="hidden" name="cmsTemplateUnit.nmpl" id="cmsTemplateUnit.nmpl" value="<bean:write property="nmpl"  name="model"/>"/>
       <input type="hidden" name="cmsTemplateUnit.shpl" id="cmsTemplateUnit.shpl" value="<bean:write property="shpl"  name="model"/>"/>
      </form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
