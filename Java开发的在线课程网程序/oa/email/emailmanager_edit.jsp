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
	  obj = document.all("oaEmailManagerActionForm");
	  if(autoCheckForm(obj)==false){
	    return false;
	  }
	  
	  obj.action="/oaEmailManagerAction.do?method=<bean:write name="act"/>";
	  obj.submit();
	}
	function getStmpType(type){
		if(type == '0'){
			document.getElementById('oaEmailManager.smtp').value = '';
		}
		if(type == '163'){
			document.getElementById('oaEmailManager.smtp').value = 'smtp.163.com';
		}
		if(type == '126'){
			document.getElementById('oaEmailManager.smtp').value = 'smtp.126.com';
		}
		if(type == 'yeah'){
			document.getElementById('oaEmailManager.smtp').value = 'smtp.yeah.net';
		}
		if(type == 'sina'){
			document.getElementById('oaEmailManager.smtp').value = 'smtp.sina.com';
		}
		if(type == 'qq'){
			document.getElementById('oaEmailManager.smtp').value = 'smtp.qq.com';
		}
		if(type == 'exmail'){
			document.getElementById('oaEmailManager.smtp').value = 'smtp.exmail.qq.com';
		}
		if(type == 'sohu'){
			document.getElementById('oaEmailManager.smtp').value = 'smtp.sohu.com';
		}
	}
</script>
</HEAD>

<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">邮箱管理</TD>
  </TR>
  <TR>
    <TD vAlign=top align=middle>
      <html:form action="/oaEmailManagerAction.do" method="post" >
       <TABLE width="469" border=0 cellpadding=2 cellspacing=2 align="center">
          <tr>
            <td class="table_edit_right">发件人：</td>
            <td class="table_edit_left">
            <input name="oaEmailManager.showname" type="text" size="25" value='<bean:write name="model" property="showname"/>'>(在对方邮件的"发件人"显示。)
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">邮箱：</td>
            <td class="table_edit_left">
            <input name="oaEmailManager.email" type="text" size="50" CK_NAME="邮箱" CK_TYPE="NotEmpty,EMail" value='<bean:write name="model" property="email"/>'>*
            </td>
          </tr>
          <logic:equal value="addSave" name="act">
		  <tr>
            <td class="table_edit_right">邮箱密码：</td>
            <td class="table_edit_left">
            <input name="oaEmailManager.password" type="password" size="25" maxlength="25" CK_NAME="邮箱密码" CK_TYPE="NotEmpty" value=''>*
            </td>
          </tr>
          </logic:equal>
          <logic:notEqual value="addSave" name="act">
          <tr>
            <td class="table_edit_right">邮箱密码：</td>
            <td class="table_edit_left">
            <input name="password" type="password" size="25" maxlength="25" value=''>(如果密码不修改请保持为空!)
            <input type="hidden" name="oaEmailManager.password" value="<bean:write property="password"  name="model"/>"/>
            </td>
          </tr>
          </logic:notEqual>
          <tr>
            <td class="table_edit_right">邮箱类型：</td>
            <td class="table_edit_left">
            <select name="stmptype" onchange="getStmpType(this.value)">
            	<option value="0">其他邮箱</option>
            	<option value="163">163邮箱</option>
            	<option value="exmail">qq企业邮箱</option>
            	<!--option value="sina">sian邮箱</option-->
            	<option value="sohu">sohu邮箱</option>
            	<option value="qq">qq邮箱</option>
            	<option value="126">126邮箱</option>
            	<option value="yeah">yeah邮箱</option>
            </select>(说明：暂不支持新浪邮箱！)
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">smtp设置：</td>
            <td class="table_edit_left">
            <input name="oaEmailManager.smtp" id="oaEmailManager.smtp" type="text" size="25" maxlength="50" CK_NAME="SMTP设置" CK_TYPE="NotEmpty" value='<bean:write name="model" property="smtp"/>'>*
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">端口：</td>
            <td class="table_edit_left">
            <input name="oaEmailManager.port" type="text" size="10" maxlength="6" CK_NAME="端口" CK_TYPE="NotEmpty,Number" value='<bean:write name="model" property="port"/>'>*(默认端口都为25)
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">排序：</td>
            <td class="table_edit_left">
            <input name="oaEmailManager.orderindex" type="text" size="10" maxlength="4" CK_NAME="排序" CK_TYPE="NotEmpty,Number" value='<bean:write name="model" property="orderindex"/>'>*
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
       <input type="hidden" name="oaEmailManager.id" value="<bean:write property="id"  name="model"/>"/>
       <input type="hidden" name="oaEmailManager.userid" value="<bean:write property="userid"  name="model"/>"/>
      </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
