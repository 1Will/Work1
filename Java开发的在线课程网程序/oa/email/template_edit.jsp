<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html:html>

<HEAD>
<TITLE>folder_edit</TITLE>

<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<script type="text/javascript">
	function saveRecord(){
	  obj = document.all("oaEmailTemplateActionForm");
	  if(autoCheckForm(obj)==false){
	    return false;
	  }
	  
	  obj.action="/oaEmailTemplateAction.do?method=<bean:write name="act"/>";
	  obj.submit();
	}
	function showContent(content){
		document.getElementById('showcontent').innerHTML = content;
	}
</script>
</HEAD>

<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">邮件模板</TD>
  </TR>
  <TR>
    <TD vAlign=top align=middle>
      <html:form action="/oaEmailTemplateAction.do" method="post" >
       <TABLE width="560" border=0 cellpadding=2 cellspacing=2 align="center">
       	  <tr>
            <td class="table_edit_right" width="80">模板名称：</td>
            <td class="table_edit_left">
            <input name="oaEmailTemplate.templatename" type="text" size="50" CK_NAME="模板名称" CK_TYPE="NotEmpty" value='<bean:write name="model" property="templatename"/>'>*
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">排序：</td>
            <td class="table_edit_left">
            <input name="oaEmailTemplate.orderindex" type="text" size="10" maxlength="4" CK_NAME="排序" CK_TYPE="NotEmpty,Number" value='<bean:write name="model" property="orderindex"/>'>*
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">设置：</td>
            <td class="table_edit_left">
            <input name="oaEmailTemplate.autosend" type="radio" value='0' <logic:equal value="0" name="model" property="autosend">checked="checked"</logic:equal>>否<input name="oaEmailTemplate.autosend" type="radio" value='1' <logic:equal value="1" name="model" property="autosend">checked="checked"</logic:equal>>是
            (设置是否为系统自动发送邮件模板。)
            </td>
          </tr>
            <tr>
             <td class="table_edit_right">模板内容：</td>
            <td class="table_edit_left">
               <textarea rows="10" cols="60" name="oaEmailTemplate.htmlcontent" CK_NAME="模板内容" CK_TYPE="NotEmpty" onblur="showContent(this.value)"><bean:write name="model" property="htmlcontent"/></textarea>
            </td>
           </tr>
		  <tr>
             <td class="table_edit_right">预览效果：</td>
            <td class="table_edit_left" id="showcontent">
               <bean:write name="model" property="htmlcontent" filter="false"/>
            </td>
           </tr>
          <tr>
              <td colspan="2" height="40" align="center">
                 <input type="button" value="保存" id="btnsave" name="btnsave" class="btn_save" onClick="saveRecord()">
              &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" value="返回" name="btnreturn" class="btn_cancel" onClick="history.go(-1);">
         </td>
        </tr>

       </TABLE>
       <input type="hidden" name="oaEmailTemplate.templateid" value="<bean:write property="templateid"  name="model"/>"/>
       <input type="hidden" name="oaEmailTemplate.type" value="<bean:write property="type"  name="model"/>"/>
       
       <input type="hidden" name="templatename" value="<bean:write name="templatename"/>"/>
       <input type="hidden" name="startcount" value="<bean:write name="startcount"/>"/>
      </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
