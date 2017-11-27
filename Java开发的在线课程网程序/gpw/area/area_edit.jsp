<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<%@ page import="com.bzt.gpw.bo.GpwAreaInfo" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>地区管理</title>
<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<script language="JavaScript"  src="/public/js/prototype.js">
</script>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<SCRIPT language="javascript" src="/public/DatePicker/WdatePicker.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/comm.js"></Script>
<script language=javascript>
function saveRecord(){
  obj = document.gpwAreaInfoActionForm;
  if(autoCheckForm(obj)==false){
    return false;
  }
  if(mobileflag == "1"){
	  	alert("此行政编码已经存在!");
	  	document.getElementById("gpwAreaInfo.rgno").focus();
	  	return false;
	  }
  document.getElementById('gpwAreaInfo.areano').value=document.getElementById('gpwAreaInfo.parentno').value+ document.getElementById('curno').value;
  obj.action="gpwAreaInfoAction.do?method=<bean:write name="act"/>";
  obj.submit();
}
function returnRecord() {
  obj = document.gpwAreaInfoActionForm;
  obj.action="gpwAreaInfoAction.do?method=list";
  obj.submit();
}
var mobileflag = "2";
function checkRgno(value){
	if(value != ""&& value != "<bean:write name="model" property="rgno"/>"){
	 var url = "/gpwAreaInfoAction.do?method=checkRgno&ram=" + Math.random();
     var myAjax = new Ajax.Request(url, {method: 'post', parameters: 'rgno='+value, onComplete: showRgno});
    }else{
    	mobileflag = "0";
      	document.getElementById("ic_cz").style.display = "none";
      	document.getElementById("ic_ky").style.display = "none";
    }
}
function showRgno(originalRequest)
{
  var returnValue = originalRequest.responseText;
  if(returnValue == "0"){
  	mobileflag = "0";
  	document.getElementById("ic_cz").style.display = "none";
  	document.getElementById("ic_ky").style.display = "block";
  }else if (returnValue == "1"){
	  mobileflag = "1";	
	  document.getElementById("ic_cz").style.display = "block";
	  document.getElementById("ic_ky").style.display = "none";
  }
}
</script>

</head>
<body >
<table class="page_maintable">
  <tr>
    <td class="page_title">地区管理</td>
  </tr>
  <tr>
    <td valign=top align="center">
     <html:form action="/gpwAreaInfoAction.do" method="post" >
       <table width="90%" class="page_table" cellspacing=1 cellpadding=1 border=0>
          <tr>
            <td class="table_edit_right" width="100">上级单位：</td>
            <td class="table_edit_left" >
              <select name="gpwAreaInfo.parentno" id="gpwAreaInfo.parentno">
                        <option value="00"  selected>根节点</option>
                        <%
                          String parentno=(String)request.getAttribute("parentno");
                          List lst=(List)request.getAttribute("arealist");
                           for(int i=0;i<lst.size();i++) {
                        	   GpwAreaInfo model=(GpwAreaInfo)lst.get(i);
                        %>
                          <option value="<%=model.getAreano()%>" <%if(model.getAreano().equals(parentno)){%> selected <%}%>><%for(int j=0;j<(model.getAreano().length()-2)/2;j++){%>&nbsp;&nbsp;<%}%>├<%=model.getAreaname()%></option>
                        <%}%>
              </select>
            </td>
          </tr>
          <tr>
            <td class="table_edit_right" width="100">地区名称：</td>
            <td class="table_edit_left" ><input type="text" CK_NAME="地区名称" CK_TYPE="NotEmpty,MaxLen_100" name="gpwAreaInfo.areaname"  id="gpwAreaInfo.areaname" size="25" title="最多为25个汉字"  lenght="25"  value="<bean:write property="areaname"  name="model"/>" title="地区名称"/>&nbsp;（必填）</td>
          </tr>
          <tr>
            <td class="table_edit_right">地区编号：</td>
            <td class="table_edit_left">
              <bean:write property="parentno"  name="model"/>
              <input type="text" name="curno" id="curno" CK_NAME="地区编号" CK_TYPE="NotEmpty,MaxLen_2"   title="请输入2位数字的编号" value="<bean:write  name="curno"/>" maxlength="2" size="2" title="地区编号" />&nbsp;（必填）
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">邮编：</td>
            <td class="table_edit_left"><input type="text" CK_NAME="邮编" CK_TYPE="Postcode" name="gpwAreaInfo.postcode"  id="gpwAreaInfo.postcode" size="25" title="最多为25个汉字"  lenght="25"  value="<bean:write property="postcode"  name="model"/>" title="格式【100000】"/>&nbsp;</td>
          </tr> 
          <tr>
            <td class="table_edit_right">区号：</td>
            <td class="table_edit_left"><input type="text" CK_NAME="区号" CK_TYPE="MaxLen_10" name="gpwAreaInfo.telecode"  id="gpwAreaInfo.telecode" size="25" title="最多为25个汉字"  lenght="25"  value="<bean:write property="telecode"  name="model"/>" title="区号"/>&nbsp;</td>
          </tr>
          <tr>
            <td class="table_edit_right">行政编码：</td>
            <td class="table_edit_left" ><input type="text" style="float:left" onblur="checkRgno(this.value)" CK_NAME="行政编码" CK_TYPE="NotEmpty,MaxLen_6" name="gpwAreaInfo.rgno"  id="gpwAreaInfo.rgno" size="25" title="最多为25个汉字"  lenght="25"  value="<bean:write property="rgno"  name="model"/>" title="行政编码"/>&nbsp;（必填）
            <div id="ic_cz" style="display: none;color: #FF0000;float:left;margin-left: 10px" >此行政编码已经存在</div>
       <div id="ic_ky" style="display: none;color: #32CD32;float:left;margin-left: 10px" >该行政编码可用</div>
       </td>
          </tr>          
       </table>
       <input type="hidden" name="gpwAreaInfo.areaid"  id="gpwAreaInfo.areaid" value="<bean:write property="areaid" name="model"/>"/>
       <input type="hidden" name="gpwAreaInfo.areano"  id="gpwAreaInfo.areano" value="<bean:write property="areano" name="model"/>"/>
       <input type="hidden" name="gpwAreaInfo.extprop1" id="gpwAreaInfo.extprop1" value="<bean:write property="extprop1" name="model"/>"/>
       <input type="hidden" name="gpwAreaInfo.province" id="gpwAreaInfo.province" value="<bean:write name="model" property="province"/>"/>
       
       <input type="hidden" name="province"  id="province" value="<bean:write name="province"/>"/>
       <input type="hidden" name="parentno"  id="parentno" value="<bean:write name="parentno"/>"/>
       <input type="hidden" name="areaname"  id="areaname" value="<bean:write name="areaname"/>"/>
       <input type="hidden" name="startcount"  id="startcount" value="<bean:write name="startcount"/>"/>
</html:form>
        <tr>
          <td align="center" height="50">
              <input type="button" class="btn_save" onclick="javascript:saveRecord()" value="保存" />
              <input type="button" class="btn_cancel" onclick="javascript:returnRecord()" value="返回" />
          </td>
        </tr>

</table>
</body>
</html>
