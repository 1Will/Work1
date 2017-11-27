<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html>

<HEAD>
<TITLE>影片上传</TITLE>
<base target="_self"/>
<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<link rel="stylesheet" href="/swfupload/css/default.css" type="text/css" />
<script type="text/javascript" src="/swfupload/js/swfupload/swfupload.js"></script>
<script type="text/javascript" src="/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="/swfupload/js/handlers.js"></script>
<script type="text/javascript">
	var swfu;

	window.onload = function () {
		swfu = new SWFUpload({
			// Backend settings
			upload_url: "/fileUploadAction.do?ftag=doc&unitid=<%=session.getAttribute("s_unitid") %>",
			use_query_string : false,

			file_size_limit : "50 MB",
			file_types : "*.doc;*.docx;*.ppt;*.pptx;*.xls;*.xlsx;*.pdf;*.txt;*.pot;*.pps;*.rtf;",
			file_types_description : "文档",
			file_upload_limit : "1",
			file_queue_limit : "1",
               
			swfupload_loaded_handler : swfUploadLoaded,
			
			file_dialog_start_handler: fileDialogStart,
			file_queued_handler : fileQueued,
			file_queue_error_handler : fileQueueError,
			file_dialog_complete_handler : fileDialogComplete,
			
			upload_progress_handler : uploadProgress,
			upload_error_handler : uploadError,
			upload_success_handler : uploadSuccess,
			upload_complete_handler : uploadComplete,

			button_image_url : "/swfupload/images/select.png",
			button_placeholder_id : "spanButtonPlaceholder",
			button_width: 80,
			button_height: 22,
			
			flash_url : "/swfupload/js/swfupload/swfupload.swf",

			custom_settings : {
				progress_target : "fsUploadProgress",
				upload_successful : false
			},
			
			debug: false
		});

	};
	
	function startUploadFile(){
	  	obj = document.fileForm;
		  	if(autoCheckForm(obj)==false){
		    return false;
	  	}
	  	document.getElementById('fade').style.display = 'block';
	  	swfu.startUpload();
	}	
</script>
</HEAD>

<BODY leftMargin=0 topMargin=0 scroll=auto>
<%@ include file="../../edu/res/tip.jsp"%>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">替换文库文档源文件</TD>
  </TR>
  <TR>
    <TD vAlign=top align=middle>
      <form action="/docFileInfoAction.do?method=uploadSave" name="fileForm" id="fileForm" method="post" enctype="multipart/form-data">
       <TABLE width="100%" border=0 cellpadding=6 cellspacing=1 align="center" bgcolor="#cccccc">
          <tr>
            <td class="table_edit_right">选择文档：</td>
            <td class="table_edit_left">
            	<input type="text" id="txtFileName" disabled="true" style="width:300px;background-color:#f5f5f5;color:#777777;margin-right:3px;display:block;float:left;" /><div id="spanButtonPlaceholder" style="overflow:hidden;width:80px;height:22px;float:left;display:block;"></div>请上传小于<a style="color:red">50MB</a>的文档！
            	<div class="flash" id="fsUploadProgress"></div>
            </td>
          </tr>
		  <tr style="display:none;">
            <td class="table_edit_left"><input type="text" style="width:317px;" name="title" id="eduResInfo.title"></td>
          </tr>
          <tr>
          	<td colspan="2" height="40" class="table_edit_left" style="text-align:center;">
          		<input type="button" value="上传" id="btnsave" name="btnsave" class="btn_upload" onClick="startUploadFile()">
          	</td>
          </tr>
       </TABLE>
       <input type="hidden" name="fileid" value='<bean:write name="fileid"/>'>
       
       <!-- swf上传组件返回值 -->
       <input type="hidden" name="filesize" id="filesize" value="" />
	   <input type="hidden" name="filename" id="filename" value="" />
	   <input type="hidden" name="filepath" id="filepath" value="" />
      </form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html>
