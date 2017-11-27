<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<TITLE>系统公告</TITLE>
<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript"  src="/public/js/comm.js"></Script>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>
<SCRIPT LANGUAGE="Javascript">
function saveRecord(){
  obj = document.sysUserGroupActionForm;
  if(autoCheckForm(obj)==false){
    return false;
  }
  
  	//检查栏目编号是否重复
  var groupno = '<bean:write property="parentno"  name="model"/>' + document.getElementById('groupnum').value;
  if(groupno != '' && groupno != '<bean:write property="groupno" name="model"/>'){
	  var url2 = 'sysUserGroupAction.do?method=checkGroupno&groupno='+groupno+'&mproduct=<bean:write name="mproduct"/>&ram=' + Math.random();
	  var temp2 = '';
	  new Ajax.Request(
	  	url2,
	  	{
	  		method: 'get',
	  		asynchronous:false,//true为异步请求
	  		onComplete:function(xhr){
				var responseObj = xhr.responseText;
				temp2 = responseObj;
			}
	  	});
	  	if(temp2 == '1'){
	  		alert("部门编号已存在，请更换编号再试!");
	  		return false;
	  	}
  	}

  document.getElementById("sysUserGroup.groupno").value = document.getElementById("sysUserGroup.parentno").value + document.getElementById("groupnum").value;
  document.getElementById("btnsave").disabled = true;
  obj.action="sysUserGroupAction.do?method=<bean:write name="act"/>";
  obj.submit();
}
</SCRIPT>
</HEAD>
<BODY>
<TABLE width="100%">
  <TR>
    <TD class="page_title"><logic:equal value="baoxiu" name="mproduct">部门</logic:equal><logic:notEqual value="baoxiu" name="mproduct">用户组</logic:notEqual>管理</TD>
  </TR>
  <TR>
    <TD vAlign=top align=middle width="100%">
      <html:form action="/sysUserGroupAction.do" method="post" >
     <TABLE width="100%">
     <TR>
      <TD  align="center" valign="middle" width="100%">
        <table width="580" border=0 cellpadding=2 cellspacing=2 align="center">
          <tr>
            <td class="table_edit_right" width="100"><logic:equal value="baoxiu" name="mproduct">部门</logic:equal><logic:notEqual value="baoxiu" name="mproduct">用户组</logic:notEqual>管理名称：</td>
            <td class="table_edit_left" >
              <input type="text" name="sysUserGroup.groupname" size="40" CK_NAME="名称" CK_TYPE="NotEmpty" maxlength="50" class=input value="<bean:write property="groupname"  name="model"/>">&nbsp;*
            </td>
           </tr>
           <tr>
             <td class="table_edit_right"><logic:equal value="baoxiu" name="mproduct">部门</logic:equal><logic:notEqual value="baoxiu" name="mproduct">用户组</logic:notEqual>管理编号：</td>
             <td class="table_edit_left">
               <bean:write name="model" property="parentno"/><input type="text" name="groupnum" id="groupnum" size="6" maxlength="4" CK_NAME="编号" CK_TYPE="NotEmpty" class=input value="<bean:write name="groupnum"/>">&nbsp;*(<a style="color:red;">建议用四位数字</a>)
              </td>
           </tr>
           <logic:equal value="vod" name="mproduct">
           <tr>
             <td class="table_edit_right">权限数值：</td>
             <td class="table_edit_left" >
              <input type="text" name="sysUserGroup.power" CK_NAME="权限数值" CK_TYPE="NotEmpty" size="4" maxlength="2" class=input value="<bean:write property="power" name="model"/>" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">&nbsp;*(<a style="color:green;">数值越大权限越大</a>)
            </td>
           </tr>
           </logic:equal>
           <logic:notEqual value="baoxiu" name="mproduct">
           <input type="hidden" name="sysUserGroup.power" value="<bean:write name="model" property="power"/>"/>
           </logic:notEqual>
           
           <tr>
             <td class="table_edit_right">状态：</td>
             <td class="table_edit_left" >
              <java2code:option name="sysUserGroup.state" codetype="pstate" property="state"></java2code:option>
            </td>
           </tr>
           <tr>
             <td class="table_edit_right">描述：</td>
             <td class="table_edit_left" >
              <textarea rows="5" cols="50" name="sysUserGroup.descript"><bean:write name="model" property="descript"/></textarea>
            </td>
           </tr>
        </table>
      </TD>
    </TR>

    <TR>
      <TD  height="40" align="center">
        <input type="button" id="btnsave" name="btnsave" class="btn_save" title="保存本记录" value="保&nbsp;存" onclick="saveRecord()"/>&nbsp;&nbsp;
        <INPUT name="gotoback" type="button" class="btn_cancel" title="返回上一页" onclick="javascript:history.go(-1)" value="返&nbsp;回">
      </TD>
    </TR>
</TABLE>
<input type="hidden" name="sysUserGroup.groupid" value="<bean:write property="groupid" name="model"/>"/>
<input type="hidden" name="sysUserGroup.unitid" value="<bean:write name="model" property="unitid"/>"/>
<input type="hidden" name="sysUserGroup.mproduct" value="<bean:write name="model" property="mproduct"/>"/>
<input type="hidden" name="sysUserGroup.parentno" id="sysUserGroup.parentno" value="<bean:write name="model" property="parentno"/>"/>
<input type="hidden" name="sysUserGroup.groupno" id="sysUserGroup.groupno" value="<bean:write name="model" property="groupno"/>"/>

<input type="hidden" name="startcount" value="<bean:write name="startcount"/>" />
<input type="hidden" name="groupname" value="<bean:write name="groupname"/>" />
<input type="hidden" name="parentno" value="<bean:write name="parentno"/>" />
<input type="hidden" name="mproduct" value="<bean:write name="mproduct"/>" />
</html:form>
    </TD>
    </TR>
</TABLE>
</body>
</html:html>
