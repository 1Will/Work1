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
	obj = document.all("docFileErrorActionForm");
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

  	document.getElementById("vodFilmInfo.descript").value = document.getElementById("editor0").contentWindow.getHTML();
  	obj.action="/docFileErrorAction.do?method=<bean:write name="act"/>&type=2";
  	obj.submit();
}
function returnback(){
	obj = document.all("docFileErrorActionForm");
	obj.action='/docFileErrorAction.do?method=list&type=2';
    obj.submit();
}
function noTreatment(){
  obj=document.all("docFileErrorActionForm");
  obj.action='/docFileErrorAction.do?method=noTreatment&type=2';
  obj.submit();
}

function uploadPhoto(){
	var diag = new top.Dialog();
	diag.Title = "上传图片";
	diag.URL = '/sysImageUploadAction.do?method=uploadimageframe&savepath=vod&pathtype=ID&sketch=vod';
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
				document.docFileErrorActionForm.topreview.src="/upload_dir/"+uploadimageurl;
			    document.getElementById('vodFilmInfo.sketch').value=uploadimageurl;
		  	}
		}
		diag.close();
	};
	diag.show();
}
function selectUser(){
	var userid = document.getElementById('userid').value;
	var diag = new top.Dialog();
	diag.Title = "选择用户";
	diag.URL = '/vodUserFilmAction.do?method=selectUserList&userid=' + userid;
	diag.Width = 800;
	diag.Height = 520;
	diag.CancelEvent = function(){
		if(diag.innerFrame.contentWindow.document.getElementById('id')){
			var id = diag.innerFrame.contentWindow.document.getElementById('id').value;
			var name = diag.innerFrame.contentWindow.document.getElementById('name').value;
			if(userid == ''){
				document.getElementById('actors').value = name;
				document.getElementById('userid').value = id;
			}else{
				document.getElementById('userid').value = userid + id;
				var actors = document.getElementById('actors');
				actors.value = actors.value + name;
			}
			document.getElementById('actors').readOnly = true;
			document.getElementById('useridupdate').value = "1";
		}
		diag.close();
	};
	diag.show();
}
function deleteUser(){
	document.getElementById('actors').value = '';
	document.getElementById('userid').value = '';
	document.getElementById('actors').readOnly = false;
	document.getElementById('useridupdate').value = "1";
}
</script>
<%@ include file="/edu/select/select_js.jsp"%>
<%
VodFilmInfo model=(VodFilmInfo)request.getAttribute("model");
%>
</HEAD>

<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">微课报错</TD>
  </TR>
  <TR>
    <TD vAlign=top align=middle>
      <html:form action="/docFileErrorAction.do" method="post">
       <TABLE width="100%" border=0 cellpadding=6 cellspacing=1 align="center" bgcolor="#cccccc">
		  <tr>
            <td class="table_edit_right" width="80">微课名称：</td>
            <td class="table_edit_left"><input type="text" style="width:317px;height:30px;line-height:30px;" name="vodFilmInfo.title" CK_NAME="微课名称" CK_TYPE="NotEmpty" value="<bean:write name="model" property="title"/>"></td>
          </tr>
          <tr>
            <td class="table_edit_right" width="80">微课标签：</td>
            <td class="table_edit_left"><input type="text" style="width:317px;height:30px;line-height:30px;" name="vodFilmInfo.keywords" value="<bean:write name="model" property="keywords"/>"></td>
          </tr>
          <tr>
            <td class="table_edit_right">主讲教师：</td>
            <td class="table_edit_left">
            	<input type="text" style="width:120px;height:30px;line-height:30px;" id="actors" name="vodFilmInfo.actors" <logic:notEmpty name="userid">readonly="readonly"</logic:notEmpty> value="<bean:write name="model" property="actors"/>" CK_NAME="主讲教师" CK_TYPE="NotEmpty">
            	<input type="hidden" name="userid" id="userid" value="<bean:write name="userid"/>"/>
            	<input type="hidden" name="useriddupdate" id="useridupdate" value="0"/>
            	<input type="button" value="选择用户" name="btn_allauthorize" class="btn_allauthorize" onClick="selectUser()">
            	<input type="button" value="清空用户" name="btn_alldel" class="btn_alldel" onClick="deleteUser()">
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">缩&nbsp;略&nbsp;图：</td>
            <td class="table_edit_left" >
            <table>
            	<tr>
            		<%if(model.getSketch().startsWith("http://")){ %>
            		<td><img src="<bean:write name="model" property="sketch"/>" width="220" height="125" border="1" onclick="uploadPhoto()" id=topreview></td>
            		<%}else{ %>
            		<td><img src="/upload_dir/<bean:write name="model" property="sketch"/>" width="220" height="125" border="1" onclick="uploadPhoto()" id=topreview></td>
            		<%} %>
            	</tr>
            	<tr>
            		<td><input type="hidden" name="vodFilmInfo.sketch" id="vodFilmInfo.sketch" value="<bean:write name="model" property="sketch"/>">(建议缩略图大小：220*125)</td>
            		<td></td>
            	</tr>
            </table>
            </td>
           </tr>
           <logic:notEqual value="2010" name="unittype">
           <tr>
            <td class="table_edit_right">微课分类：</td>
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
            <td class="table_edit_right">推荐：</td>
            <td class="table_edit_left">
              <select name="vodFilmInfo.recommandtype" style="height:30px;line-height:30px;">
                   <option value="0" <logic:equal value="0" name="model" property="recommandtype">selected="selected"</logic:equal>>否</option>
                   <option value="1" <logic:equal value="1" name="model" property="recommandtype">selected="selected"</logic:equal>>是</option>
              </select>
              	&nbsp;&nbsp;推荐编号：<input type="text" style="width:50px;height:30px;line-height:30px;" maxlength="1" name="vodFilmInfo.homescroll" CK_NAME="推荐编号" CK_TYPE="NotEmpty,Number" value="<bean:write name="model" property="homescroll"/>" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> &nbsp;(请输入1-9之间的数字，越大越靠前。)
            </td>
          </tr>
          <logic:present name="arealist">
          <tr>
            <td class="table_edit_right">微课大赛：</td>
            <td class="table_edit_left">
              <logic:iterate id="vodContestInfo" name="arealist" >
                <input type="checkbox" name="areabox" id="areabox" checked="checked" disabled="disabled" value="<bean:write name="vodContestInfo" property="contestid"/>"><bean:write name="vodContestInfo" property="title"/></br>
              </logic:iterate>
            </td>
          </tr>
          </logic:present>
           <tr>
            <td class="table_edit_left" colspan="2">
              <input type="hidden" name="vodFilmInfo.descript" id="vodFilmInfo.descript" value="<%=Encode.convertQuot(Encode.nullToBlank(model.getDescript()))%>"/>
              <IFRAME ID="editor0" src="/ewebeditor/ewebeditor.htm?id=vodFilmInfo.descript&style=blue&cusdir=<%=Encode.nullToBlank(session.getAttribute("cusdir")) %>" frameborder="0" scrolling="no" width="100%" height="300"></IFRAME>
           </td>
        </TR>
         <tr>
          <td class="table_edit_right">报错描述</td>
          <td class="table_edit_left">
         ${errormodel.descript }
          </td>
          </tr>
          <tr>
          <td class="table_edit_right">联系方式</td>
          <td class="table_edit_left">
           ${errormodel.contact }
          </td>
          </tr>
          <tr>
          	<td colspan="2" height="40" class="table_edit_left" style="text-align:center;">
          		<input type="button" value="已处理" id="btnsave" name="btnsave" class="btn_save" onClick="saveRecord()">
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" value="不予处理" id="btnsave" name="btnsave" class="btn_save" onClick="noTreatment()">
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" value="返回" name="btnreturn" class="btn_cancel" onClick="returnback()">
          	</td>
          </tr>
       </TABLE>
       <input type="hidden" name="errorid" value="${errormodel.errorid}"/>
       <input type="hidden" name="vodFilmInfo.filmid" value="<bean:write name="model" property="filmid"/>">
       <input type="hidden" name="vodFilmInfo.createdate" value="<bean:write name="model" property="createdate"/>">
       <input type="hidden" name="vodFilmInfo.updatetime" value="<bean:write name="model" property="updatetime"/>">
       <input type="hidden" name="vodFilmInfo.counts" value="<bean:write name="model" property="counts"/>">
       <input type="hidden" name="vodFilmInfo.collects" value="<bean:write name="model" property="collects"/>">
       <input type="hidden" name="vodFilmInfo.collectcaifu" value="<bean:write name="model" property="collectcaifu"/>">
       <input type="hidden" name="vodFilmInfo.totalcaifu" value="<bean:write name="model" property="totalcaifu"/>">
       <input type="hidden" name="vodFilmInfo.unitid" value="<bean:write name="model" property="unitid"/>">
       <input type="hidden" name="vodFilmInfo.userid" value="<bean:write name="model" property="userid"/>">
       <input type="hidden" name="vodFilmInfo.state" value="<bean:write name="model" property="state"/>">
       <input type="hidden" name="vodFilmInfo.score" value="<bean:write name="model" property="score"/>">
       <input type="hidden" name="vodFilmInfo.scoreusers" value="<bean:write name="model" property="scoreusers"/>">
       <input type="hidden" name="vodFilmInfo.linkurl" value="<bean:write name="model" property="linkurl"/>">
       <input type="hidden" name="vodFilmInfo.filmtype" value="<bean:write name="model" property="filmtype"/>">
       <input type="hidden" name="vodFilmInfo.permission" value="<bean:write name="model" property="permission"/>">
       <input type="hidden" name="vodFilmInfo.contest" value="<bean:write name="model" property="contest"/>">
       <input type="hidden" name="vodFilmInfo.theyear" value="<bean:write name="model" property="theyear"/>">
       <input type="hidden" name="vodFilmInfo.rank" value="<bean:write name="model" property="rank"/>">
       <input type="hidden" name="vodFilmInfo.phoneimg" value="<bean:write name="model" property="phoneimg"/>">

       <input type="hidden" name="unitid" value="<bean:write name="unitid"/>">
       <input type="hidden" name="title" value="<bean:write name="title"/>">
       <input type="hidden" name="actors" value="<bean:write name="actors"/>">
       <input type="hidden" name="recommandtype" value="<bean:write name="recommandtype"/>">
      </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
