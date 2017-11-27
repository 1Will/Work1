<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html:html>

<HEAD>
<TITLE>上传资源</TITLE>

<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<link href="/public/css/photo/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/swfupload/js/swfupload/swfupload.js"></script>
<script type="text/javascript" src="/swfupload/js/swfupload/swfupload.queue.js"></script>
<script type="text/javascript" src="/swfupload/js/swfupload/handlerssk_menu.js"></script>
<script type="text/javascript">
	var swfu;
	
	window.onload = function () {
		swfu = new SWFUpload({
			upload_url: "/fileUploadAction.do?ftag=menu&unitid=<%=request.getAttribute("typeno") %>",
			
			// File Upload Settings
			file_size_limit : "10 MB",	// 100MB
			file_types : "*.jpg;*.gif;*.png;",
			file_types_description : "图片",
			file_upload_limit : "20",
							
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

<script language="javascript">
var s_rownum = 2;
var cur_rownum = 1;

//function doSave(){
//	obj = document.sysMenuInfoActionForm;
//	obj.action = "sysMenuInfoAction.do?method=addSave";
//	obj.submit();
//}
</script>

</HEAD>

<BODY leftMargin=0 topMargin=0 scroll=auto>
<table align="center" width="99%" border="0" cellpadding="0" cellspacing="1" bgcolor="white">
  <tr><td style="font-size:22px;font-weight:bold;color:red;text-align:center;">
  上传图片
  </td></tr>
</table>
<form action="/sysMenuInfoAction.do?method=addSave" name="sysMenuInfoActionForm" id="sysMenuInfoActionForm" method="post" enctype="multipart/form-data">
<input type="hidden" name="rownum" value=""/>
<table id="infoTable" align="center" width="99%" border="0" cellpadding="0" cellspacing="1" bgcolor="#cccccc">
  <tr>
    <td width="60%" height="32" background="/public/images/photo/d11.gif" >&nbsp;你选择的图片</td>
    <td width="16%" align="center" background="/public/images/photo/d11.gif" >图片大小</td>
    <td width="14%" align="center" background="/public/images/photo/d11.gif" >状态</td>
    <td width="10%" align="center" background="/public/images/photo/d11.gif">功能</td>
  </tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" id="table_uploadbtn" style="background-color:#FFFFFF;">
<tr>
    <td height="80" colspan="4" align="center" style="padding-left:10px;"></td>
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
<input type="hidden" name="typeno" value="<bean:write name="typeno"/>">
</form>
</div><!--#tx_word-->

<div id="notice" style="display:block;width:560px;">
<span class="notice01" style="width:560px;">图片上传须知</span>
<span class="notice02" style="width:560px;">为了保证图片能正常显示，我们支持jpg,gif,png格式的图片上传 </span>
</div>
</BODY>
</html:html>
