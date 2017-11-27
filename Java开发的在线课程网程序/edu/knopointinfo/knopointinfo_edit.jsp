<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html>
<HEAD>
<TITLE>知识点目录</TITLE>
<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript"  src="/public/js/comm.js"></Script>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>
 <script type="text/javascript">
   function saveRecord(){
    var title=document.getElementById("title");
    var knopointno=document.getElementById("knopointno");
    var parentno="<bean:write property="parentno" name="model"/>";
    var nknopointno=""+parentno+""+knopointno.value;
    obj=document.eduKnopointInfoActionForm;
    var flag=false;
    if(title.value==""){
       top.Dialog.alert("请填写知识点名称！");
       return false;
     }
     if(title.value.length>25){
        top.Dialog.alert("知识点名称的字数长度不得超过25！");
        return false; 
     }
     if(knopointno.value==""){
        top.Dialog.alert("请输入知识点编号！");
        return false;
     }
     if(isNaN(knopointno.value)){
       top.Dialog.alert("知识点编号必须为数字！");
       return false;
     }
     if(knopointno.value.length!=4){
       top.Dialog.alert("知识点编号长度必须为4位！");
       return false;
     }
     new Ajax.Request(
     "eduKnopointInfoAction.do?method=ishaveKnopointno&knopointno="+nknopointno+"&gradetype="+<bean:write property="gradetype"  name="model"/>+"&subjectid="+<bean:write property="subjectid"  name="model"/>+"&id=<bean:write property="knopointid"  name="model"/>",
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
     obj.action="/eduKnopointInfoAction.do?method=${act}";
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
       <html:form method="post" action="eduKnopointInfoAction.do">
       <table width="500" align=center border=0>
       <tr>
         <td class="table_edit_right" width="25%">知识点名称：</td>
          <td class="table_edit_left" width="75%" colspan="3"><input type="text" CK_NAME="知识点名称" CK_TYPE="NotEmpty,MaxLen_25" name="eduKnopointInfo.title" size="25" id="title" title="最多为25个汉字" lenght="25" class=input value="<bean:write property="title"  name="model"/>">&nbsp;*</td>
       </tr>
       <tr>
        <td class="table_edit_right">知识点编号：</td>
        <td class="table_edit_left" colspan="3"><bean:write property="parentno" name="model"/></a><input  type="text"  CK_NAME="知识点编号" CK_TYPE="NotEmpty" name="eduKnopointInfo.knopointno" id="knopointno" class=input title="请输入4位数字的编号" value="<bean:write property="knopointno"  name="model"/>" maxlength="4" size="4" >&nbsp;*
       </tr>
       <tr>
        <td class="table_edit_right">类型：</td>
        <td class="table_edit_left" width="240">
           <select name="eduKnopointInfo.type">
           <option value="0" <logic:equal property="type" name="model" value="0">selected</logic:equal>>知识点大纲</option>
           <option value="1" <logic:equal property="type" name="model" value="1">selected</logic:equal>>具体知识点</option>
           </select>
        </td>
       </tr>
       <tr>
       <td class="table_edit_right">状&nbsp;&nbsp;&nbsp;&nbsp;态：</td>
       <td class="table_edit_left" width="240">
         <select name="eduKnopointInfo.status">
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
       <input type="hidden" name="eduKnopointInfo.knopointid" id="eduKnopointInfo.knopointid" value="<bean:write property="knopointid"  name="model"/>"/>
       <input type="hidden" name="parentno" value="${parentno}">
       <input type="hidden" name="subjectid" value="${subjectid}">
       <input type="hidden" name="gradetype" value="${gradetype}">
       <input type="hidden" name="eduKnopointInfo.parentno" id="eduKnopointInfo.parentno" value="<bean:write property="parentno"  name="model"/>"/>
       <input type="hidden" name="eduKnopointInfo.gradetype" id="eduKnopointInfo.gradetype" value="<bean:write property="gradetype"  name="model"/>"/> 
       <input type="hidden" name="eduKnopointInfo.subjectid" id="eduKnopointInfo.subjectid" value="<bean:write property="subjectid"  name="model"/>"/>
       <input type="hidden" name="eduKnopointInfo.kno" id="eduKnopointInfo.kno" value="">
       <input type="hidden" name="eduKnopointInfo.descript" id="eduKnopointInfo.descript" value="">
       </table>
       </html:form>
       </td>
     </tr>
    </table>
  </body>
</html>
