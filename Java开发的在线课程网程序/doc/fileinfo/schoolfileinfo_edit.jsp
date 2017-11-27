<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@page import="com.bzt.doc.bo.DocFileInfo"%>
<%@page import="com.util.string.encode.Encode"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.vod.bo.VodFilmType"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html:html>

<HEAD>
<TITLE>文档修改</TITLE>

<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>

<script type="text/javascript">
function saveRecord(){
	obj = document.all("docFileInfoActionForm");
  	if(autoCheckForm(obj)==false){
    	return false;
  	}

  	var flag = '<bean:write name="unittype"/>';
	if(flag == "2010"){
		if(document.getElementById("columnid").value == ""){
			top.Dialog.alert("请选择教材目录章节!");
	    	return false;
	  	}
	}else{
		if(document.getElementById("typeid").value == ""){
			top.Dialog.alert("请选择分类!");
	    	return false;
	  	}
	}

  	obj.action="/schoolDocFileInfoAction.do?method=<bean:write name="act"/>";
  	obj.submit();
}
function returnback(){
	obj = document.all("docFileInfoActionForm");
	obj.action='/schoolDocFileInfoAction.do?method=list';
    obj.submit();
}
function showDialog(){
	var diag = new top.Dialog();
	diag.Title = "上传图片";
	diag.URL = '/sysImageUploadAction.do?method=uploadimageframe&pathtype=doc&sketch=ID';
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
		  		document.docFileInfoActionForm.topreview.src=uploadimageurl;
			    document.getElementById('docFileInfo.sketch').value=uploadimageurl;
		  	}
		}
		diag.close();
	};
	diag.show();
}
</script>
<%@ include file="/edu/select/select_js.jsp"%>
<%
DocFileInfo model=(DocFileInfo)request.getAttribute("model");
%>
</HEAD>

<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">修改文档</TD>
  </TR>
  <TR>
    <TD vAlign=top align=middle>
      <html:form action="/schoolDocFileInfoAction.do" method="post">
       <TABLE width="100%" border=0 cellpadding=6 cellspacing=1 align="center" bgcolor="#cccccc">
          <tr>
            <td class="table_edit_right">文档名称：</td>
            <td class="table_edit_left"><input type="text" style="width:317px;height:30px;line-height:30px;" name="docFileInfo.title" value="<bean:write name="model" property="title"/>" CK_NAME="文档名称" CK_TYPE="NotEmpty"></td>
          </tr>
          <tr>
            <td class="table_edit_right">文档标签：</td>
            <td class="table_edit_left"><input type="text" style="width:317px;height:30px;line-height:30px;" name="docFileInfo.keywords" value="<bean:write name="model" property="keywords"/>" CK_NAME="文档标签" ></td>
          </tr>
         <logic:notEqual value="2010" name="unittype">
           <tr>
            <td class="table_edit_right">文档分类：</td>
            <td class="table_edit_left">
                <select name="ptypeid" onchange="selectType('1', this.value)" style="float:left;margin-right:10px;height:30px;line-height:30px;">
                  <option value="">请选择</option>
                  <%
                  List lst = (List)request.getAttribute("typeList");
                  String oldtypeid = (String)request.getAttribute("oldtypeid");
                  List lst2 = (List)request.getAttribute("typeList2");
                  VodFilmType vft = null;
		          for(int i=0;i<lst.size();i++) {
                      vft = (VodFilmType)lst.get(i);
                 %>
                    <option value="<%=vft.getTypeid()%>" <%if(vft.getTypeno().equals(oldtypeid)){ %>selected="selected"<%} %>><%=vft.getTypename()%></option>
                 <%}%>
                </select>
                <div id="sectype" style="<bean:write name='style'/>" >
                <select name="sectypeid" onchange="selectType('2', this.value)" style="height:30px;line-height:30px;">
                    <option value="">请选择</option>
                    <%
                    VodFilmType vft2 = null;
		            for(int i=0;i<lst2.size();i++) {
                        vft2 = (VodFilmType)lst2.get(i);
                    %>
                    <option value="<%=vft2.getTypeid()%>" <%if(vft2.getTypeno().equals(model.getVodFilmType().getTypeno())){ %>selected="selected"<%} %>><%=vft2.getTypename()%></option>
                    <%}%>
                </select>
                </div>
                <input type="hidden" id="typeid" name="typeid" value="${model.vodFilmType.typeid }">
            </td>
          </tr>
          </logic:notEqual>
          <logic:equal value="2010" name="unittype">
          <tr>
            <td class="table_edit_right">教材目录：</td>
            <td class="table_edit_left">
                                学科：<input type="text" style="width:100px;background-color:#f7f8f9;color:#888;height:30px;line-height:30px;" readonly="readonly" name="subjectname" id="subjectname" value="${subjectInfo.subjectname }" onclick="selectSubject()"/>
                                年级：<input type="text" style="width:100px;background-color:#f7f8f9;color:#888;height:30px;line-height:30px;" readonly="readonly" name="gradename" id="gradename" value="${gradeInfo.gradename }" onclick="selectGrade()"/>
                                版本：<input type="text" style="width:200px;background-color:#f7f8f9;color:#888;height:30px;line-height:30px;" readonly="readonly" name="versionname" id="versionname" value="${versionInfo.versionname }" onclick="selectVersion()"/>
            <br/><br/>
                                 章节：<input type="text" style="width:480px;background-color:#f7f8f9;color:#888;height:30px;line-height:30px;" readonly="readonly" name="columnname" id="columnname" value="${columnInfo.columnname }" onclick="selectColumn()"/>
            <input type="hidden" name="subjectid" id="subjectid" value="${subjectInfo.subjectid }"/>
            <input type="hidden" name="gradeid" id="gradeid" value="${gradeInfo.gradeid }"/>
            <input type="hidden" name="versionid" id="versionid" value="${versionInfo.versionid }"/>
            <input type="hidden" name="columnid" id="columnid" value="${columnInfo.columnid }"/>
            </td>
          </tr>
          <tr id="knopoint_div">
            <td class="table_edit_right">知识点：</td>
            <td class="table_edit_left">
              <input type="text" style="width:315px;background-color:#f7f8f9;color:#888;height:30px;line-height:30px;" readonly="readonly" name="knopointname" id="knopointname" value="${knopointnames }" onclick="selectKnopoint()"/>（选填）
              <input type="hidden" name="knopointid" id="knopointid" value="${knopointids }"/>
              <input type="hidden" name="knopointidupdate" id="knopointidupdate" value="0"/>
            </td>
          </tr>
          </logic:equal>
          <tr>
            <td  class="table_edit_right">缩&nbsp;略&nbsp;图：</td>
            <td class="table_edit_left" >
                 <img src="<bean:write property="sketch"  name="model"/>" alt="点击修改图片,建议图片格式(182*234)" width="182" height="234" border="1" id=topreview onclick="showDialog()"/>
                <input type="hidden" name="docFileInfo.sketch"  id="docFileInfo.sketch"   value='<bean:write property="sketch"  name="model"/>'/>
                <br />提示：<font color="red">建议图片尺寸 width=182;height=234</font>;
            </td>
          </tr>
           <tr>
            <td class="table_edit_right">推荐：</td>
            <td class="table_edit_left">
              <select name="docFileInfo.recommand" style="height:30px;line-height:30px;">
                   <option value="0" <logic:equal value="0" name="model" property="recommand">selected="selected"</logic:equal>>否</option>
                   <option value="1" <logic:equal value="1" name="model" property="recommand">selected="selected"</logic:equal>>是</option>
              </select>
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">文档类别：</td>
            <td class="table_edit_left">
            	 <select name="docFileInfo.restype" style="height:30px; line-height:30px; width:80px;">
            <%
                String[] typeids = Constants.getCodeTypeid("restype");
                String[] typenames = Constants.getCodeTypename("restype");
                for(int i=0, size=typeids.length; i<size; i++) {
                %>
                    <option value="<%=typeids[i] %>" <%if(model.getRestype().equals(typeids[i]) ) { %>  selected = "selected" <%}%>><%=typenames[i] %></option>
                <% } %>
                </select>
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">积分：</td>
            <td class="table_edit_left">
            	<input type="text" style="width:80px;height:30px;line-height:30px;" name="docFileInfo.caifu" CK_NAME="积分" CK_TYPE="NotEmpty,MaxLen_11,Int" value="<bean:write name="model" property="caifu"/>">(文档被下载将获得的积分财富奖励值，详情查看积分规则)
            </td>
          </tr>
          <tr>
          	<td colspan="2" height="40" class="table_edit_left" style="text-align:center;">
          		<input type="button" value="保存" id="btnsave" name="btnsave" class="btn_save" onClick="saveRecord()">
              &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" value="返回" name="btnreturn" class="btn_cancel" onClick="returnback()"">
          	</td>
          </tr>
       </TABLE>
       <input type="hidden" name="docFileInfo.fileid" value='<bean:write name="model" property="fileid"/>'>
       <input type="hidden" name="docFileInfo.filename" value='<bean:write name="model" property="filename"/>'>
       <input type="hidden" name="docFileInfo.filepath" value='<bean:write name="model" property="filepath"/>'>
       <input type="hidden" name="docFileInfo.pdfpath" value='<bean:write name="model" property="pdfpath"/>'>
       <input type="hidden" name="docFileInfo.swfpath" value='<bean:write name="model" property="swfpath"/>'>
       <input type="hidden" name="docFileInfo.filesize" value='<bean:write name="model" property="filesize"/>'>
       <input type="hidden" name="docFileInfo.fileext" value='<bean:write name="model" property="fileext"/>'>
       <input type="hidden" name="docFileInfo.pagenum" value='<bean:write name="model" property="pagenum"/>'>
       <input type="hidden" name="docFileInfo.hits" value='<bean:write name="model" property="hits"/>'>
       <input type="hidden" name="docFileInfo.downloads" value='<bean:write name="model" property="downloads"/>'>
       <input type="hidden" name="docFileInfo.collects" value='<bean:write name="model" property="collects"/>'>
       <input type="hidden" name="docFileInfo.score" value='<bean:write name="model" property="score"/>'>
       <input type="hidden" name="docFileInfo.scoreusers" value='<bean:write name="model" property="scoreusers"/>'>
       <input type="hidden" name="docFileInfo.createdate" value='<bean:write name="model" property="createdate"/>'>
       <input type="hidden" name="docFileInfo.updatetime" value='<bean:write name="model" property="updatetime"/>'>
       <input type="hidden" name="docFileInfo.orderindex" value='<bean:write name="model" property="orderindex"/>'>

       <input type="hidden" name="docFileInfo.htmlpath" value='<bean:write name="model" property="htmlpath"/>'>
       <input type="hidden" name="docFileInfo.cancomment" value='<bean:write name="model" property="cancomment"/>'>
       <input type="hidden" name="docFileInfo.recommandno" value='<bean:write name="model" property="recommandno"/>'>

       <input type="hidden" name="docFileInfo.unitid" value='<bean:write name="model" property="unitid"/>'>
       <input type="hidden" name="docFileInfo.userid" value='<bean:write name="model" property="userid"/>'>
       <input type="hidden" name="docFileInfo.totalcaifu" value='<bean:write name="model" property="totalcaifu"/>'>
       <input type="hidden" name="docFileInfo.collectcaifu" value='<bean:write name="model" property="collectcaifu"/>'>
       <input type="hidden" name="docFileInfo.downloadcaifu" value='<bean:write name="model" property="downloadcaifu"/>'>
       <input type="hidden" name="docFileInfo.status" value='<bean:write name="model" property="status"/>'>

       <input type="hidden" name="docFileInfo.convertstatus" value='<bean:write name="model" property="convertstatus"/>'>
       <input type="hidden" name="docFileInfo.md5code" value='<bean:write name="model" property="md5code"/>'>

       <input type="hidden" name="unitid" value="<bean:write name="unitid"/>">
       <input type="hidden" name="title" value="<bean:write name="title"/>">
       <input type="hidden" name="username" value="<bean:write name="username"/>">
       <input type="hidden" name="recommand" value="<bean:write name="recommand"/>">
       <input type="hidden" name="startcount" value="<bean:write name="startcount"/>">
      </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
