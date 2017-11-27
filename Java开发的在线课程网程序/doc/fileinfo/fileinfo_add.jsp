<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@page import="com.bzt.vod.bo.VodFilmType"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html:html>

<HEAD>
<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<script type="text/javascript" src="/swfupload/js/swfupload/swfupload.js"></script>
<script type="text/javascript" src="/swfupload/js/swfupload/swfupload.queue.js"></script>
<script type="text/javascript" src="/swfupload/js/swfupload/handlerssk_vod.js"></script>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>
<script type="text/javascript">
	var swfu;
	window.onload = function () {
		swfu = new SWFUpload({
			upload_url: "/fileUploadAction.do?ftag=doc&unitid=<%=session.getAttribute("s_unitid") %>",
			
			// File Upload Settings
			file_size_limit : "200 MB",	// 50MB
			//file_types : "*.mp4;",
			file_types : "*.doc;*.docx;*.ppt;*.pptx;*.xls;*.xlsx;*.pdf;*.txt;*.pot;*.pps;*.rtf;",
			file_types_description : "文档",
			file_upload_limit : "50",
							
			file_queue_error_handler : fileQueueError,
			file_dialog_complete_handler : fileDialogComplete,
			file_queued_handler : fileQueued,
			upload_progress_handler : uploadProgress,
			upload_error_handler : uploadError,
			upload_success_handler : uploadSuccess,
			upload_complete_handler : uploadComplete,
			

			// Button Settings
			button_image_url : "/public/images/photo/btn_02.jpg",
			button_placeholder_id : "spanButtonPlaceholder",
			button_width: 156,
			button_height: 36,
			button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 10pt; }',
			button_text_top_padding: 0,
			button_text_left_padding: 18,
			button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
			button_cursor: SWFUpload.CURSOR.HAND,
			button_action : SWFUpload.BUTTON_ACTION.SELECT_FILES, //SWFUplaod.BUTTON_ACTION.SELECT_FILE ,SWFUpload.BUTTON_ACTION.SELECT_FILES
			
			// Flash Settings
			flash_url : "/swfupload/js/swfupload/swfupload.swf",
			prevent_swf_caching : true,

			//custom_settings : {
			//	upload_target : "divFileProgressContainer"
			//},
			// Debug Settings
			debug: false  //是否显示调试窗口
		});
	};
	function startUploadFile(){
		swfu.startUpload();
	}			
</script> 
<script type="text/javascript">
var s_rownum = 2;
var cur_rownum = 1;

function saveRecord(){
  	obj = document.all("docFileInfoActionForm");
  	if(autoCheckForm(obj)==false){
    	return false;
  	}

  	var flag = '<bean:write name="s_sysunitinfo" property="type"/>';
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

  document.getElementById("btnsave").disabled = true;
  obj.action="/docFileInfoAction.do?method=addSave";
  obj.submit();
}
</script>
<%@ include file="/edu/select/select_js.jsp"%>
</HEAD>

<BODY leftMargin=0 topMargin=0 scroll=auto>
<form action="/docFileInfoAction.do?method=addSave" name="docFileInfoActionForm" id="docFileInfoActionForm" method="post" enctype="multipart/form-data">
<TABLE class="page_maintable" style="height:20px;">
  <TR>
    <TD class="page_title" >上传文档</TD>
  </TR>
  <tr>
  <td>
  
    <input type="hidden" name="rownum" id="rownum" value=""/>
  	<table id="infoTable" align="center"  width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#cccccc">
	  <tr>
	    <td width="60%" height="32" background="/public/images/photo/d11.gif" >&nbsp;你选择的文档</td>
	    <td width="16%" align="center" background="/public/images/photo/d11.gif" >文档大小</td>
	    <td width="14%" align="center" background="/public/images/photo/d11.gif" >状态</td>
	    <td width="10%" align="center" background="/public/images/photo/d11.gif">功能</td>
	  </tr>
	  
	</table>

	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="table_uploadbtn" style="background-color:#FFFFFF;">
	<tr>
	    <td height="40" colspan="4" align="center" style="padding-left:10px;"></td>
	  </tr>
	  <tr>
	    <td height="25" colspan="4" align="center" style="padding-left:10px;">
	    <table width="100%" align="center">
	    <tr>
	    <td align="center" width="50%" id="td_btnxz">
	    <span id="spanButtonPlaceholder" ></span>
	    </td>
	    <td align="right" width="50%" id="td_btnsc" style="display:none;"><span style="float:left;width:156px;"><img src="/public/images/photo/d06.gif" border="0" onclick="startUploadFile();" style="cursor:pointer;"/></span>
	    </td>
	    </tr>
	    </table>
	    </td>
	  </tr>
	</table>
  </td>
  </tr>
  <TR>
    <TD vAlign=top align=middle>
       <TABLE width="100%" border=0 cellpadding=6 cellspacing=1 align="center" bgcolor="#cccccc">
		  <tr>
            <td class="table_edit_right">文档标签：</td>
            <td class="table_edit_left"><input type="text" style="width:317px;height:30px;line-height:30px;" name="keywords" value="" ></td>
          </tr>
          <logic:present name="typeList">
          <tr>
            <td class="table_edit_right">文档分类：</td>
            <td class="table_edit_left">
                <select name="ptypeid" onchange="selectType('1', this.value)" style="float:left;margin-right:10px;height:30px;line-height:30px;">
                  <option value="">请选择</option>
                  <%
                  List lst = (List)request.getAttribute("typeList");
                  VodFilmType vft = null;
		          for(int i=0;i<lst.size();i++) {
                      vft = (VodFilmType)lst.get(i);
                 %>
                    <option value="<%=vft.getTypeid()%>"><%=vft.getTypename()%></option>
                 <%}%>
                </select>
                <div id="sectype" style="display:none" ></div>
                <input type="hidden" id="typeid" name="typeid" value="">
            </td>
          </tr>
          </logic:present>
          <logic:notPresent name="typeList">
          <tr>
            <td class="table_edit_right">教材目录：</td>
            <td class="table_edit_left">
                                学科：<input type="text" style="width:100px;background-color:#f7f8f9;color:#888;height:30px;line-height:30px;" readonly="readonly" name="subjectname" id="subjectname" value="${subjectname }" onclick="selectSubject()"/>
                                年级：<input type="text" style="width:100px;background-color:#f7f8f9;color:#888;height:30px;line-height:30px;" readonly="readonly" name="gradename" id="gradename" value="${gradename }" onclick="selectGrade()"/>
                                版本：<input type="text" style="width:200px;background-color:#f7f8f9;color:#888;height:30px;line-height:30px;" readonly="readonly" name="versionname" id="versionname" value="${versionname }" onclick="selectVersion()"/>
            <br/><br/>
                                 章节：<input type="text" style="width:480px;background-color:#f7f8f9;color:#888;height:30px;line-height:30px;" readonly="readonly" name="columnname" id="columnname" value="点击选择章节目录" onclick="selectColumn()"/>
            <input type="hidden" name="subjectid" id="subjectid" value="${subjectid }"/>
            <input type="hidden" name="gradeid" id="gradeid" value="${gradeid }"/>
            <input type="hidden" name="versionid" id="versionid" value="${versionid }"/>
            <input type="hidden" name="columnid" id="columnid" value=""/>
            </td>
          </tr>
          <tr id="knopoint_div" style="${knopointStyle}">
            <td class="table_edit_right">知识点：</td>
            <td class="table_edit_left">
              <input type="text" style="width:315px;background-color:#f7f8f9;color:#888;height:30px;line-height:30px;" readonly="readonly" name="knopointname" id="knopointname" value="点击选择知识点" onclick="selectKnopoint()"/>（选填）
              <input type="hidden" name="knopointid" id="knopointid" value=""/>
              <input type="hidden" name="knopointidupdate" id="knopointidupdate" value="0"/>
            </td>
          </tr>
          </logic:notPresent>
          <tr>
            <td class="table_edit_right">文档类别：</td>
            <td class="table_edit_left" >
                <select name="restype" style="height:30px; line-height:30px; width:80px;">
            <%
                String[] typeids = Constants.getCodeTypeid("restype");
                String[] typenames = Constants.getCodeTypename("restype");
                for(int i=0, size=typeids.length; i<size; i++){
                %>
                    <option value="<%=typeids[i] %>"><%=typenames[i] %></option>
                <%} %>
                </select>
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">积分：</td>
            <td class="table_edit_left">
            	<input type="text" style="width:80px;height:30px;line-height:30px;" name="caifu" CK_NAME="积分" CK_TYPE="NotEmpty,MaxLen_11,Int" value="0">(文档被下载将获得的积分财富奖励值，详情查看积分规则)
            </td>
          </tr>
          <tr>
          	<td colspan="2" height="40" class="table_edit_left" style="text-align:center;"><input type="button" value="保存" id="btnsave" name="btnsave" class="btn_save" onClick="saveRecord()"></td>
          </tr>
       </TABLE>
      
    </TD>
    </TR>
</TABLE>
</form>
</BODY>
</html:html>
<script language="javascript">
var btnsave = document.getElementById("btnsave");
btnsave.disabled = true;
</script>