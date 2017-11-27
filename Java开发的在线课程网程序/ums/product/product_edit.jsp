<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@page import="com.bzt.sys.bo.SysProductInfo"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<TITLE>产品管理</TITLE>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>

<SCRIPT language=javascript>
function saveRecord(){
  obj = document.all("sysProductInfoActionForm");
  if(autoCheckForm(obj)==false){
    return false;
  }

  document.getElementById("btnsave").disabled = true;

  obj.action="sysProductInfoAction.do?method=<bean:write name="act"/>";
  obj.submit();
}
function uploadPhoto(){
  var retValue=showModalDialog('/sysImageUploadAction.do?method=uploadimage&savepath=product&pathtype=D','缩略图',"dialogWidth:350px;dialogHeight:200px;scroll=no;border=thin;help=0;status=no");
  if(retValue!=null){
     if(retValue[0] != ''){
  	 	alert(retValue[0]);
  		return false;
  	 }
     document.sysProductInfoActionForm.topreview.src="/upload_dir/"+retValue[1];
     document.getElementById('sysProductInfo.sketch').value=retValue[1];
  }
}
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable" width="98%">
  <TR>
    <TD class="page_title">产品管理</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/sysProductInfoAction.do" method="post" >
       <TABLE width="450" align=center border=0>
          <tr>
            <td class="table_edit_right">产品名称：</td>
            <td class="table_edit_left"> <input type="text" CK_NAME="产品名称" CK_TYPE="NotEmpty" name="sysProductInfo.productname" size="50" maxlength="50" class=input value="<bean:write property="productname"  name="model"/>">&nbsp;*</td>
          </tr>
          <tr>
            <td class="table_edit_right">缩略图：</td>
            <td class="table_edit_left">
            <img src="/upload_dir/<bean:write property="sketch"  name="model"/>" alt="点击修改照片" width="58" height="62" border="1" id=topreview onclick="uploadPhoto()">
              <input type="hidden" id="sysProductInfo.sketch" name="sysProductInfo.sketch"  value='<bean:write property="sketch"  name="model"/>'>
            </td>
          </tr>
          <tr id="tr_mproduct">
            <td class="table_edit_right">产品标示：</td>
            <td class="table_edit_left">
            	<select name="sysProductInfo.mproduct">
            	<%
            		SysProductInfo productInfo = (SysProductInfo)request.getAttribute("model");
            		String[] mproductids = Constants.getCodeTypeid("mproduct");
            		String[] mproductnames = Constants.getCodeTypename("mproduct");
            		for(int i=0; i<mproductids.length; i++){
            	%>
            		<option value="<%=mproductids[i] %>" <%if(productInfo.getMproduct().equals(mproductids[i])){ %>selected="selected"<%} %>><%=mproductnames[i] %></option>
            	<%} %>
            		<option value="xxx" <%if(productInfo.getMproduct().equals("xxx")){ %>selected="selected"<%} %>>第三方产品</option>
            	</select>
            </td>
          </tr>
          <logic:equal value="updateSave" name="act">
          <tr>
            <td class="table_edit_right">产品状态：</td>
            <td class="table_edit_left">
            	<java2code:option name="sysProductInfo.state" codetype="pstate" property="state"></java2code:option>
            </td>
          </tr>
          </logic:equal>
          <logic:notEqual value="updateSave" name="act">
          <input type="hidden" name="sysProductInfo.state" value="<bean:write property="state"  name="model"/>"/>
          </logic:notEqual>
          <tr>
            <td class="table_edit_right">排序：</td>
            <td class="table_edit_left"><input type="text" CK_NAME="排序" CK_TYPE="NotEmpty,Number" name="sysProductInfo.orderindex" size="10" maxlength="4" class=input value="<bean:write property="orderindex"  name="model"/>">&nbsp;*</td>
          </tr>
          <tr>
            <td class="table_edit_right">链接地址：</td>
            <td class="table_edit_left"><input type="text" CK_NAME="链接地址" CK_TYPE="NotEmpty" name="sysProductInfo.url" size="50" maxlength="200" class=input value="<bean:write property="url"  name="model"/>">&nbsp;*</td>
          </tr>
           <tr>
            <td class="table_edit_right">产品说明：</td>
            <td class="table_edit_left">
              <textarea rows="8" name="sysProductInfo.descript" class="inputtextarea" cols="50" wrap="physical"><bean:write property="descript"  name="model"/></textarea></td>
          </tr>
          <tr>
                <td colspan="2" align="center">
                    <input type="button" value="保存" id="btnsave" name="btnsave" class="btn_save" onClick="javascript:saveRecord()">
                  <input type="button" value="返回" name="btnreturn" class="btn_cancel" onClick="javascript:history.go(-1)">
               </td>
              </tr>
       </TABLE>
       <input type="hidden" name="sysProductInfo.productid" value="<bean:write property="productid"  name="model"/>"/>
       <input type="hidden" name="sysProductInfo.productno" value="<bean:write property="productno"  name="model"/>"/>
       <input type="hidden" name="sysProductInfo.type" value="3"/>
       
       <input type="hidden" name="productname" value="<bean:write name="productname"/>" />
       <input type="hidden" name="startcount" value="<bean:write name="startcount"/>" />
      </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
