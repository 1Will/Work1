<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<%@ page import="com.bzt.vod.bo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>年级分册</title>
<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript"  src="/public/js/comm.js"></Script>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<script language=javascript>
 function saveRecord(){
  var gradename=document.getElementById("gradename");
  var orderindex=document.getElementById("orderindex");
  var xueduanid=document.getElementById("xueduanid"); 
  var cxueduanid=document.getElementById("cxueduanid");
  obj=document.eduGradeInfoActionForm;
    if(gradename.value==""){
     top.Dialog.alert("请填写分册名称！");
     return false;
   }
   if(gradename.value.length>15){
      top.Dialog.alert("分册名称字数长度不得超过15！");
      return false;
   }
   if(orderindex.value==""){
       top.Dialog.alert("请填写排序！");
       return false;
   }
   if(isNaN(orderindex.value)){
      top.Dialog.alert("排序必须为数字！");
      return false;
   }
   if(xueduanid.value==""){
      top.Dialog.alert("请选择所属学段！");
      return false;
   }
   if(cxueduanid.value==""){
      top.Dialog.alert("请选择所属年级！");
      return false;
   }
   obj.action="/eduGradeInfoAction.do?method=${act}";
   obj.submit();
 }

  function doselect(temp){	
   var cxueduanid=document.getElementById("cxueduanid");
    cxueduanid.options.length = 0; 
    cxueduanid.options.add(new Option("请选择",""));
   if(temp!=""){
    $.ajax({
      type:"post",
      url:"eduGradeInfoAction.do?method=select",
      data:"parentid="+temp,
      success:function(msg){
       if(msg!=""){
         var arr=msg.split(";");
         for(var i=0;i<arr.length;i++){
                 var str=arr[i].split("_");
            cxueduanid.options.add(new Option(str[1],str[0]));
          }
        }
       }
    });
   }
  }
</script>
</head>
  
  <body>
     <html:form action="/eduGradeInfoAction.do" method="post" >
      <input type="hidden" name="subjectid" value='<bean:write property="subjectid"  name="model"/>'/>
      <input type="hidden" name="gradeid" value='<bean:write property="gradeid"  name="model"/>'/>
    <table class="page_maintable" align="center">
    <tr>
     <td class="page_title"><bean:write name="showtitle" /></td>
    </tr>
    <tr>
    <td valign="top" align="center">
    <table width="650" border="0" align="center" cellpadding=0 cellspacing=0>
      <tr>
       <td class="table_edit_right" width="150">分册名称：</td>
       <td class="table_edit_left" width="100">
       <input type="text" style="width: 200px;" name="gradenames"  id="gradename" size="60" tabindex="1" maxlength="50" title="请输入分册名称" value='<bean:write property="gradename"  name="model"/>'/>&nbsp;*
     </td>
          </tr>
             <tr>
               <td  class="table_edit_right">排&nbsp;&nbsp;&nbsp;&nbsp;序：</td>
              <td class="table_edit_left" width="240">
                <input type="text" name="orderindex"  id="orderindex" size="10"  maxlength="10"  value='<bean:write property="orderindex"  name="model"/>'/>越小越靠前*
               </td>
             </tr>
             <tr>
            <td  class="table_edit_right">状&nbsp;&nbsp;&nbsp;&nbsp;态：</td>
            <td class="table_edit_left" width="240">
            	<select name="status">
                 <option value="1" <logic:equal property="status" name="model" value="1">selected</logic:equal>>正常</option>
                 <option value="0" <logic:equal property="status" name="model" value="0">selected</logic:equal>>禁用</option>
              </select>
            </td>          
           </tr>
           <tr>
             <td class="table_edit_right">所属学段:</td>
             <td class="table_edit_left" width="240">
             <select id="xueduanid" name="xuduanid" onchange="doselect(this.value)">
              <option value="">请选择</option>
              <c:forEach items="${xueduanlist}" var="xue"> 
              <option value="${xue.xueduanid }">${xue.name }</option>
              </c:forEach >
             </select>
             <select name="cxueduanid" id="cxueduanid">
               <option value="">请选择</option>
               <c:forEach items="${cxueduanlist}" var="cxue">
               <option value="${cxue.xueduanid }">${cxue.name }</option>
               </c:forEach>
             </select>
             </td>
           </tr>
         <tr>
           <td height="20">&nbsp;</td>
         </tr>
       </table>
    </td>
    </tr>
    <tr>
          <td  align="center" colspan="4" height="40">
            <input  type="button" class="btn_save" onclick="javascript:saveRecord()" value="保存" />
          <input  type="button" class="btn_cancel" onclick="javascript:history.go(-1)" value="返回" />
		   </td>
         </tr>
    </table>
   </html:form>
  </body>
</html>
<script language="javascript">
var xue = "<bean:write property='xueduanid'  name='model'/>";

for(var i=0;i<document.getElementById("xueduanid").length;i++){
	if(document.getElementById("xueduanid")[i].value == xue){
		document.getElementById("xueduanid")[i].selected = true;
		break;
	}
}

var cxue = "<bean:write property='cxueduanid'  name='model'/>";
for(var i=0;i<document.getElementById("cxueduanid").length;i++){
	if(document.getElementById("cxueduanid")[i].value == cxue){
		document.getElementById("cxueduanid")[i].selected = true;
		break;
	}
}

</script>
