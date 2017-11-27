<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.bzt.vod.bo.VodFilmInfo"%>
<%@page import="com.util.string.encode.Encode"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.vod.bo.VodFilmType"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html:html>

<HEAD>
<TITLE>添加视频</TITLE>

<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>

<script type="text/javascript">
function saveRecord(){
  obj = document.all("gpwApplyLinkActionForm");
  if(autoCheckForm(obj)==false){
    return false;
  }
  obj.action="/gpwApplyLinkAction.do?method=<bean:write name="act"/>";
  obj.submit();
}
function uploadPhoto(){
	var diag = new top.Dialog();
	diag.Title = "上传图片";
	diag.URL = '/sysImageUploadAction.do?method=uploadimageframe&savepath=applylink&pathtype=ID';
	diag.Width = 350;
	diag.Height = 180;
	diag.CancelEvent = function(){
		if(diag.innerFrame.contentWindow.document.getElementById('uploadimageurl')){
			var uploadtip = diag.innerFrame.contentWindow.document.getElementById('uploadtip').value;
			if(uploadtip != ''){
		  	 	alert(uploadtip);
		  		return false;
		  	}else{
		  		var uploadimageurl = diag.innerFrame.contentWindow.document.getElementById('uploadimageurl').value;
				document.gpwApplyLinkActionForm.topreview.src="/upload_dir/"+uploadimageurl;
			    document.getElementById('gpwApplyLink.linklogo').value="/upload_dir/"+uploadimageurl;
		  	}
		}
		diag.close();
	};
	diag.show();
}
</script>
</HEAD>

<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">申请链接</TD>
  </TR>
  <TR>
    <TD vAlign=top align=middle>
      <html:form action="/gpwApplyLinkAction.do" method="post">
       <TABLE width="100%" border=0 cellpadding=6 cellspacing=1 align="center" bgcolor="#cccccc">
		  <tr>
            <td class="table_edit_right" width="80">申请链接名称：</td>
            <td class="table_edit_left"><input type="text" style="width:317px;" name="gpwApplyLink.linkname" CK_NAME="申请链接名称" CK_TYPE="NotEmpty" value="<bean:write name="model" property="linkname"/>"></td>
          </tr>
          <tr>
            <td class="table_edit_right" width="80">申请链接地址：</td>
            <td class="table_edit_left"><input type="text" style="width:317px;" name="gpwApplyLink.linkurl" CK_NAME="申请链接地址" CK_TYPE="NotEmpty" value="<bean:write name="model" property="linkurl"/>"></td>
          </tr>
          <tr>
            <td class="table_edit_right">链接类型：</td>
            <td class="table_edit_left"><java2code:option  name="gpwApplyLink.linktype" codetype="applylink" property="linktype"/></td>
          </tr>
          <tr>
            <td class="table_edit_right">链接图片：</td>
            <td class="table_edit_left" >
            <table>
            	<tr>
            		<td><img src="<bean:write name="model" property="linklogo"/>" width="190" height="60" border="1" onclick="uploadPhoto()" id=topreview></td>
            	</tr>
            	<tr>
            		<td><input type="hidden" name="gpwApplyLink.linklogo" id="gpwApplyLink.linklogo" value="<bean:write name="model" property="linklogo"/>">(建议缩略图大小：190*60)</td>
            	</tr>
            </table>
            </td>
           </tr>
           <tr>
            <td class="table_edit_right">链接描述：</td>
            <td class="table_edit_left">
              <textarea style="width:550px;height:80px;" id="gpwApplyLink.descript" name="gpwApplyLink.descript" class="inputtextarea" cols="50" wrap="physical"><bean:write property="descript"  name="model"/></textarea></td>
            </td>
          </tr>
          <tr>
            <td class="table_edit_right" width="80">联系人：</td>
            <td class="table_edit_left"><input type="text" style="width:317px;" name="gpwApplyLink.linkman" CK_NAME="联系人" CK_TYPE="NotEmpty" value="<bean:write name="model" property="linkman"/>"></td>
          </tr>
          <tr>
            <td class="table_edit_right" width="80">联系电话：</td>
            <td class="table_edit_left"><input type="text" style="width:317px;" name="gpwApplyLink.telephone" CK_NAME="联系电话" CK_TYPE="NotEmpty" value="<bean:write name="model" property="telephone"/>"></td>
          </tr>
          <tr>
            <td class="table_edit_right" width="80">联系邮箱：</td>
            <td class="table_edit_left"><input type="text" style="width:317px;" name="gpwApplyLink.email" CK_NAME="联系邮箱" CK_TYPE="NotEmpty" value="<bean:write name="model" property="email"/>"></td>
          </tr>
           <tr>
            <td class="table_edit_right">状态：</td>
            <td class="table_edit_left">
              <select name="gpwApplyLink.status">
              	<option value="0" <logic:equal value="0" name="model" property="status">selected="selected"</logic:equal>>未审核</option>
              	<option value="1" <logic:equal value="1" name="model" property="status">selected="selected"</logic:equal>>审核通过</option>
              </select>
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">排序：</td>
            <td class="table_edit_left">
              	<input type="text" style="width:50px;" maxlength="1" name="gpwApplyLink.orderindex" CK_NAME="排序" CK_TYPE="NotEmpty,Number" value="<bean:write name="model" property="orderindex"/>" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> &nbsp;(数字越大越靠前。)
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">申请时间：</td>
            <td class="table_edit_left"><bean:write name="model" property="createdate"/></td>
          </tr>
          <tr>
            <td class="table_edit_right">WKMK图片位置描述：</td>
            <td class="table_edit_left"><bean:write name="model" property="wkmklocation"/></td>
          </tr>
          <tr>
            <td class="table_edit_right">WKMK图片尺寸大小：</td>
            <td class="table_edit_left"><bean:write name="model" property="wkmklogosize"/></td>
          </tr>
          <tr>
          	<td colspan="2" height="40" class="table_edit_left" style="text-align:center;">
          		<input type="button" value="保存" id="btnsave" name="btnsave" class="btn_save" onClick="saveRecord()">
              &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" value="返回" name="btnreturn" class="btn_cancel" onClick="history.go(-1);">
          	</td>
          </tr>
       </TABLE>
       <input type="hidden" name="gpwApplyLink.applyid" value="<bean:write name="model" property="applyid"/>">
       <input type="hidden" name="gpwApplyLink.createdate" value="<bean:write name="model" property="createdate"/>">
       <input type="hidden" name="gpwApplyLink.wkmklocation" value="<bean:write name="model" property="wkmklocation"/>">
       <input type="hidden" name="gpwApplyLink.wkmklogosize" value="<bean:write name="model" property="wkmklogosize"/>">

       <input type="hidden" name="linktype" value="<bean:write name="linktype"/>">
       <input type="hidden" name="linkname" value="<bean:write name="linkname"/>">
       <input type="hidden" name="status" value="<bean:write name="status"/>">
       <input type="hidden" name="startcount" value="<bean:write name="startcount"/>">
      </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>