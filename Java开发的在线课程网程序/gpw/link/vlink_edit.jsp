<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.bzt.gpw.bo.GpwLinkInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.bo.SysCodeData"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html:html>

<HEAD>
<TITLE>友情链接</TITLE>

<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<SCRIPT language=javascript>
function saveRecord(){
  obj = document.gpwLinkInfoActionForm;
  if(autoCheckForm(obj)==false){
    return false;
  }

  document.getElementById("btnsave").disabled = true;

  obj.action="vodLinkInfoAction.do?method=<bean:write name="act"/>";
  obj.submit();
}
function showDialog(url,name){
  var retValue=showModalDialog('sysImageUploadAction.do?method=uploadimage&savepath=linkpic&pathtype=ID','缩略图',"dialogWidth:350px;dialogHeight:200px;scroll=no;border=thin;help=0;status=no");
  if(retValue!=null){
     if(retValue[0] != ''){
  	 	alert(retValue[0]);
  		return false;
  	 }
     document.gpwLinkInfoActionForm.topreview.src="/upload_dir/"+retValue[1];
     document.getElementById('gpwLinkInfo.linkpic').value=retValue[1];
  }
}
function changeType(type){
	if(type == '0'){
		document.getElementById('tr_linkpic').style.display = 'none';
	}
	if(type == '1'){
		document.getElementById('tr_linkpic').style.display = '';
	}
}
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">友情链接</TD>
  </TR>
  <TR>
    <TD vAlign=top align=middle>
      <html:form action="/vodLinkInfoAction.do" method="post" >
       <TABLE width="100%" border=0 cellpadding=2 cellspacing=2 align="center">
          <tr>
            <td class="table_edit_right" width="40%">链接名称：</td>
            <td class="table_edit_left" ><input type="text" name="gpwLinkInfo.linkname" CK_NAME="链接名称" CK_TYPE="NotEmpty" size="35" lenght="25" class=input value="<bean:write property="linkname"  name="model"/>">&nbsp;*</td>
          </tr>
          <tr>
            <td class="table_edit_right">链接地址：</td>
            <td class="table_edit_left"><input type="text" name="gpwLinkInfo.linkurl" CK_NAME="链接地址" CK_TYPE="NotEmpty" class=input value="<bean:write property="linkurl"  name="model"/>" size="35">&nbsp;*
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">链接类型：</td>
            <td class="table_edit_left">
            <select name="gpwLinkInfo.linktype" onchange="changeType(this.value)">
           		<option value="0" <logic:equal value="0" name="model" property="linktype">selected="selected"</logic:equal>>文本链接</option>
           		<option value="1" <logic:equal value="1" name="model" property="linktype">selected="selected"</logic:equal>>图片链接</option>
           	</select>
            </td>
          </tr>
            <tr id="tr_linkpic" <logic:equal value="0" name="model" property="linktype">style="display:none;"</logic:equal>>
            <td class="table_edit_right">链接图片：</td>
            <td class="table_edit_left"  onclick="showDialog()">
              <img src="/upload_dir/<bean:write property="linkpic"  name="model"/>" width="88" height="31" border="1" id=topreview>(点击修改图片)
            </td>
           </tr>
           <tr>
                <td class="table_edit_right">显示顺序：</td>
                 <td class="table_edit_left" >
                 <input name="gpwLinkInfo.orderindex" type = "text" class="input" maxlength="3" size="5" value="<bean:write property="orderindex"  name="model"/>">
                 </td>
             </tr>
            <logic:present name="code_list" scope="request">
             <tr>
                <td class="table_edit_right">显示位置：</td>
                 <td class="table_edit_left" >
                 <select name="gpwLinkInfo.codeno">
                 	<%
                 	GpwLinkInfo model = (GpwLinkInfo)request.getAttribute("model");
	              	List codelist = (List)request.getAttribute("code_list");
	              	for(int i=0; i<codelist.size(); i++){
	              		SysCodeData scd = (SysCodeData)codelist.get(i);
	              %>
	              	<option value="<%=scd.getCodeno() %>" <%if(scd.getCodeno().equals(model.getCodeno())){ %>selected="selected"<%} %>><%=scd.getCodedata() %></option>
	              <%} %>
	             </select>
                 </td>
             </tr>
           </logic:present>
            <tr>
             <td class="table_edit_right">链接描述：</td>
            <td class="table_edit_left" colspan="2">
               <textarea rows="5" name="gpwLinkInfo.descript" class="inputtextarea" cols="40" wrap="physical"><bean:write property="descript"  name="model"/></textarea>
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
        <input type="hidden" name="gpwLinkInfo.linkid" value="<bean:write property="linkid"  name="model"/>"/>
        <input type="hidden" name="gpwLinkInfo.unitid" value="<bean:write name="model" property="unitid"/>"/>
        <input type="hidden" name="gpwLinkInfo.mproduct" value="<bean:write name="model" property="mproduct"/>"/>
        <input type="hidden" name="gpwLinkInfo.linkpic" id="gpwLinkInfo.linkpic" value="<bean:write property="linkpic"  name="model"/>">

		<input type="hidden" name="linktype" value="<bean:write name="linktype"/>"/>
        <input type="hidden" name="linkname" value="<bean:write name="linkname"/>"/>
        <input type="hidden" name="linkurl" value="<bean:write name="linkurl"/>"/>
        <input type="hidden" name="startcount" value="<bean:write name="startcount"/>"/>
      </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
