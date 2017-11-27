<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html>
<HEAD>
<TITLE>资源目录</TITLE>
<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript"  src="/public/js/comm.js"></Script>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>
 <script type="text/javascript">
   function saveRecord(){
    var columnname=document.getElementById("columnname");
    var columnno=document.getElementById("columnno");
    var parentno="<bean:write property="parentno" name="model"/>";
    var ccolumnno=""+parentno+""+columnno.value;
    obj=document.eduColumnInfoActionForm;
    var flag=false;
    if(columnname.value==""){
       top.Dialog.alert("请填写栏目名称！");
       return false;
     }
     if(columnname.value.length>25){
        top.Dialog.alert("栏目名称的字数长度不得超过25！");
        return false; 
     }
     if(columnno.value==""){
        top.Dialog.alert("请输入栏目编号！");
        return false;
     }
     if(isNaN(columnno.value)){
       top.Dialog.alert("栏目编号必须为数字！");
       return false;
     }
     if(columnno.value.length!=4){
       top.Dialog.alert("栏目编号长度必须为4位！");
       return false;
     }
     new Ajax.Request(
     "eduColumnInfoAction.do?method=ishavecolumno&versionid="+<bean:write property="versionid"  name="vmodel"/>+"&parentno=<bean:write property='parentno'  name='model'/>&id=<bean:write property="columnid"  name="model"/>&ccolumnno="+ccolumnno,
      {
       method:"post",
       asynchronous:false,//true为异步请求
        onComplete:function(msg){
        var result=msg.responseText;
        if(result!='ok'){
           top.Dialog.alert(result);
           flag=true;
         }
       }
      }
     );
     if(flag)return false;
     document.getElementById("btnsave").disabled = true;
     obj.action="/eduColumnInfoAction.do?method=${act}&versionid="+<bean:write property="versionid"  name="vmodel"/>+"&parentno=<bean:write property='parentno'  name='model'/>";
     obj.submit();
   }
 
 </script> 
  </head>
  <body>
    <table class="page_maintable">
     <tr>
      <td class="page_title">${showtitle }</td>
     </tr>
     <tr>
       <td valign="top" align="middle">
       <html:form method="post" action="eduColumnInfoAction.do">
       <table width="500" align=center border=0>
       <tr>
         <td class="table_edit_right" width="25%">栏目名称：</td>
          <td class="table_edit_left" width="75%" colspan="3"><input type="text" CK_NAME="栏目名称" CK_TYPE="NotEmpty,MaxLen_25" name="eduColumnInfo.columnname" size="25" id="columnname" title="最多为25个汉字" lenght="25" class=input value="<bean:write property="columnname"  name="model"/>">&nbsp;*</td>
       </tr>
       <tr>
        <td class="table_edit_right">栏目编号：</td>
        <td class="table_edit_left" colspan="3"><bean:write property="parentno" name="model"/></a><input  type="text"  CK_NAME="栏目编号" CK_TYPE="NotEmpty" name="eduColumnInfo.columnno" id="columnno" class=input title="请输入4位数字的编号" value="<bean:write property="columnno"  name="model"/>" maxlength="4" size="4" >&nbsp;*
       </tr>
       <tr>
        <td class="table_edit_right">栏目类型：</td>
        <td class="table_edit_left" width="240">
           <select name="eduColumnInfo.columntype">
           <option value="0" <logic:equal property="columntype" name="model" value="0">selected</logic:equal>>章</option>
           <option value="1" <logic:equal property="columntype" name="model" value="1">selected</logic:equal>>课</option>
           <option value="2" <logic:equal property="columntype" name="model" value="2">selected</logic:equal>>节</option>
           <option value="3" <logic:equal property="columntype" name="model" value="3">selected</logic:equal>>综合</option>
           </select>
        </td>
       </tr>
       <tr>
       <td class="table_edit_right">状&nbsp;&nbsp;&nbsp;&nbsp;态：</td>
       <td class="table_edit_left" width="240">
         <select name="eduColumnInfo.status">
          <option value="1" <logic:equal property="status" name="model" value="1">selected</logic:equal>>正常</option>
          <option value="0" <logic:equal property="status" name="model" value="0">selected</logic:equal>>禁用</option>
         </select>
       </td>
       </tr>
       <tr>
         <td colspan="4" height="40" align="center">
          <input type="button" value="保存" id="btnsave" name="btnsave" class=btn_save onClick="javascript:saveRecord()">
           &nbsp;&nbsp;&nbsp;&nbsp;
          <input type="button" value="返回" name="btnreturn" class=btn_cancel onClick="javascript:history.go(-1)">
         </td>
       </tr>
        <input type="hidden" name="eduColumnInfo.columnid" id="eduColumnInfo.columnid" value="<bean:write property="columnid"  name="model"/>">
        <input type="hidden" name="eduColumnInfo.cxueduanid" id="eduColumnInfo.cxueduanid" value="<bean:write property="cxueduanid"  name="gmodel"/>">
        <input type="hidden" name="eduColumnInfo.xueduanid" id="eduColumnInfo.xueduanid" value="<bean:write property="xueduanid"  name="gmodel"/>">
        <input type="hidden" name="eduColumnInfo.subjectid" id="eduColumnInfo.subjectid" value="<bean:write property="subjectid"  name="gmodel"/>">
        <input type="hidden" name="eduColumnInfo.gradeid" id="eduColumnInfo.gradeid" value="<bean:write property="gradeid"  name="gmodel"/>" >
        <input type="hidden" name="eduColumnInfo.version" id="eduColumnInfo.version" value="<bean:write property="version"  name="vmodel"/>" >
        <input type="hidden" name="eduColumnInfo.versionid" id="eduColumnInfo.versionid" value="<bean:write property="versionid"  name="vmodel"/>"> 
        <input type="hidden" name="eduColumnInfo.parentno" id="eduColumnInfo.parentno" value="<bean:write property="parentno"  name="model"/>"> 
       </table>
       </html:form>
       </td>
     </tr>
    </table>
  </body>
</html>
