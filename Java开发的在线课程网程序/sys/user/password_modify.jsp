<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<TITLE>修改密码</TITLE>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript"  src="/public/js/calendar.js"></Script>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>
<SCRIPT language=javascript>
function saveRecord(){  
  obj = document.all("sysUserInfoActionForm");
  if(autoCheckForm(obj)==false){
     return false;
  }   
  if(document.getElementById('sysUserInfo.password').value!=document.getElementById('passwordagain').value){
    alert("两次输入的密码不一致，请重试!");
    return false;
  } 
  document.getElementById("btnsave").disabled = true;
  obj.action="/sysUserInfoAction.do?method=modifySave";
  obj.submit();   
}
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable" width="100%">
  <TR>
    <TD class="page_title">修改密码</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/sysUserInfoAction.do" method="post" >
       <TABLE width="260" align=center border="0">

		  <tr>
            <td class="table_edit_right">原&nbsp;密&nbsp;码：</td>
            <td class="table_edit_left">
                <input CK_NAME="新密码" CK_TYPE="NotEmpty,MaxLen_25" type="password" id="password1" name="password1" maxlength="25" value='' />
                <font style="color:red">*</font>              
            </td>
          </tr> 
          
		  <tr>
            <td class="table_edit_right">新&nbsp;密&nbsp;码：</td>
            <td class="table_edit_left">
                 <input CK_NAME="新密码" CK_TYPE="NotEmpty,MaxLen_25" type="password" id="sysUserInfo.password" name="sysUserInfo.password" maxlength="25" value='' />
                 <font style="color:red">*</font>          
            </td>
          </tr>                

          <tr>
            <td class="table_edit_right">确认密码：</td>
            <td class="table_edit_left">
                 <input CK_NAME="确认密码" CK_TYPE="NotEmpty,MaxLen_25" type="password" id="passwordagain" name="passwordagain" maxlength="25" value='' />
                  <font style="color:red">*</font>              
            </td>
          </tr>  

           <tr>
               <td class="table_edit_right"></td>
               <td class="table_edit_left">
                   <span id="rightinfo" style="color:red"></span> 
               </td>
           </tr>  
                     
           <tr>
               <td class="table_edit_right"></td>
               <td class="table_edit_left">
                   <input type="button" value="保存" id="btnsave" name="btnsave" class="btn_save" onClick="javascript:saveRecord()">
               </td>
           </tr>  
       </TABLE>
       <input type="hidden" id="sysUserInfo.userid" name="sysUserInfo.userid" value="<bean:write property="userid"  name="model"/>"/>
        </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>                   
