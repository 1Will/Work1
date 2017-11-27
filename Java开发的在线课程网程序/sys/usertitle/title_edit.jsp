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
	  obj = document.all("sysUserTitleActionForm");
	  if(autoCheckForm(obj)==false){
	    return false;
	  }
	  
	  obj.action="/sysUserTitleAction.do?method=<bean:write name="act"/>";
	  obj.submit();
	}
</script>
</HEAD>

<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">用户头衔</TD>
  </TR>
  <TR>
    <TD vAlign=top align=middle>
      <html:form action="/sysUserTitleAction.do" method="post" >
       <TABLE width="300" border=0 cellpadding=2 cellspacing=2 align="center">
          <tr>
            <td class="table_edit_right">头衔名称：</td>
            <td class="table_edit_left"><input name="sysUserTitle.name" type="text" size="20" maxlength="15" CK_NAME="头衔名称" CK_TYPE="NotEmpty" value='<bean:write name="model" property="name"/>'>*</td>
          </tr>
          <tr>
            <td class="table_edit_right">最小积分值：</td>
            <td class="table_edit_left"><input name="sysUserTitle.minvalue" type="text" size="20" CK_NAME="最小积分值" CK_TYPE="NotEmpty,Number" value='<bean:write name="model" property="minvalue"/>' readonly="readonly">*</td>
          </tr>
          <tr>
            <td class="table_edit_right">最大积分值：</td>
            <td class="table_edit_left"><input name="sysUserTitle.maxvalues" type="text" size="20" CK_NAME="最大积分值" CK_TYPE="NotEmpty,Number" value='<bean:write name="model" property="maxvalues"/>' readonly="readonly">*</td>
          </tr>
          <tr>
              <td colspan="2" height="40" align="center">
                 <input type="button" value="保存" id="btnsave" name="btnsave" class="btn_save" onClick="saveRecord()">
              &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" value="返回" name="btnreturn" class="btn_cancel" onClick="history.go(-1);">
         </td>
        </tr>

       </TABLE>
       <input type="hidden" name="sysUserTitle.id" value="<bean:write property="id"  name="model"/>"/>
       <input type="hidden" name="sysUserTitle.unitid" value="<bean:write property="unitid"  name="model"/>"/>
      </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
