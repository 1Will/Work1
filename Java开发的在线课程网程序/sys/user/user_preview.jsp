<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>

<HEAD>
<TITLE>学校信息</TITLE>
<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>

<SCRIPT language=javascript>
function saveRecord(){
  var selectunit = document.getElementById("selectunit").value;
  if(selectunit != '1'){
	  alert("请先调换用户所在学校再审核！");
	  return;
  }
  obj = document.all("sysUserInfoActionForm");
  obj.action='sysUserInfoCheckAction.do?method=check';
  document.getElementById("btnsave").disabled = true;
  obj.submit();
}
function selectSchool(){
	var diag = new top.Dialog();
	diag.Title = "选择学校";
	diag.URL = '/v.bo?method=selectSchoolList';
	diag.Width = 810;
	diag.Height = 600;
	diag.CancelEvent = function(){
		if(diag.innerFrame.contentWindow.document.getElementById('id')){
			var id = diag.innerFrame.contentWindow.document.getElementById('id').value;
			var name = diag.innerFrame.contentWindow.document.getElementById('name').value;
		    if(id != null && id != ""){
		    	document.getElementById("unitid").value=id;
				document.getElementById("unitname").innerHTML=name;
				document.getElementById("selectunit").value="1";
		     }
		}
		diag.close();
	};
	diag.show();
}
</SCRIPT>
<style type="text/css">
/*--友好表格样式--*/
.sAdminNice{width:600px;margin:0 auto;color:#666; font-size:12px; border-collapse:collapse; background-color:#fff;/*细线表格代码*/}
.sAdminNice td{border:1px solid #d7ebff;/*细线表格线条颜色*/ padding:8px;}
.sAdminNice th{padding:8px;background:#f7fcff;font-size:14px;border:1px solid #d7ebff;}
.sAdminNice thead, .sAdminNice th{text-align:left;}
</style>
</HEAD>
<BODY leftMargin=0 topMargin=0>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">用户信息</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/sysUserInfoCheckAction.do" method="post" >
       <TABLE width="600" border="0" align=center border=0 class="sAdminNice">
             <tr>
              <td class="table_edit_right" width="80">登录名:</td>
              <td class="table_edit_left"><bean:write property="loginname"  name="model"/></td>
            </tr>
            <tr>
              <td class="table_edit_right">真实姓名:</td>
              <td class="table_edit_left"><bean:write property="username"  name="model"/></td>
            </tr>
              <tr>
              <td class="table_edit_right">头像:</td>
              <td class="table_edit_left">
              <img src="<bean:write property="photo"  name="model"/>" width="200" height="130" border="1" id=topreview >
              </td>
            </tr>
            <tr>
              <td class="table_edit_right">用户类型:</td>
              <td class="table_edit_left">
              	<logic:equal value="0" name="model" property="usertype">系统用户</logic:equal>
              	<logic:equal value="5" name="model" property="usertype">微课教师</logic:equal>
              	<logic:equal value="6" name="model" property="usertype">微课专家</logic:equal>
              	<logic:equal value="7" name="model" property="usertype">微课大使</logic:equal>
              </td>
            </tr>
             <tr>
              <td class="table_edit_right">性别:</td>
              <td class="table_edit_left"><java2code:value codetype="sex" property="sex"/></td>
            </tr>
            <tr>
              <td class="table_edit_right">联系电话:</td>
              <td class="table_edit_left"><bean:write property="telephone"  name="model"/></td>
            </tr>
            <tr>
              <td class="table_edit_right">手机号码:</td>
              <td class="table_edit_left"><bean:write property="mobile"  name="model"/></td>
            </tr>
            <tr>
              <td class="table_edit_right">电子邮件:</td>
              <td class="table_edit_left"><bean:write property="email"  name="model"/></td>
            </tr>
            <tr>
              <td class="table_edit_right">QQ号码:</td>
              <td class="table_edit_left"><bean:write property="qq"  name="model"/></td>
            </tr>
            <tr>
              <td class="table_edit_right">职务:</td>
              <td class="table_edit_left"><bean:write property="job"  name="model"/></td>
            </tr>
           <tr>
              <td class="table_edit_right">邮政编码:</td>
              <td class="table_edit_left"><bean:write property="postcode"  name="model"/></td>
            </tr>
            <tr>
              <td class="table_edit_right">联系地址:</td>
              <td class="table_edit_left"><bean:write property="address"  name="model"/></td>
            </tr>
            <tr>
              <td class="table_edit_right">个人主页:</td>
              <td class="table_edit_left"><bean:write property="linkurl"  name="model"/></td>
            </tr>
            <logic:notEmpty name="model" property="descript">
            <tr>
              <td class="table_edit_right">所在学校:</td>
              <td class="table_edit_left" style="color:red;" id="unitname">系统没有当前用户注册所需要的学校，新申请学校：<br/><font color="blue">《<bean:write property="descript" name="model"/>》</font><br/>请先审核此学校再审核用户！&nbsp;&nbsp;<a href="javascript:selectSchool()">[调换学校]</a></td>
            </tr>
            </logic:notEmpty>
            <logic:empty name="model" property="descript">
            <tr>
              <td class="table_edit_right">所在学校:</td>
              <td class="table_edit_left" id="unitname"><bean:write property="unitname" name="unitInfo"/>&nbsp;&nbsp;<a href="javascript:selectSchool()">[调换学校]</a></td>
            </tr>
            </logic:empty>
             <tr height="40">
                <td colspan="2" align="center">
                    <input type="button" value="&nbsp;审&nbsp;核&nbsp;" id="btnsave" name="btnsave" class="btn_save" onClick="saveRecord()">
                    <input type="button" value="&nbsp;返&nbsp;回&nbsp;" name="btnreturn" class="btn_cancel" onClick="javascript:history.go(-1)">
                </td>
              </tr>
       </table>
         <input type="hidden" name="userid" id="userid" value="<bean:write property="userid" name="model"/>"/>
         <input type="hidden" name="unitid" id="unitid" value="<bean:write property="unitid" name="model"/>"/>
         <input type="hidden" name="selectunit" id="selectunit" value="0"/>
       </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
