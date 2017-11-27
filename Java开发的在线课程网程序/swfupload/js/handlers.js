var formChecker = null;
function swfUploadLoaded() {
	var btnsave = document.getElementById("btnsave");
	btnsave.disabled = true;
	
	formChecker = window.setInterval(validateForm, 1000);
	validateForm();
}

function validateForm() {
	var txtFileName = document.getElementById("txtFileName");
	
	var isValid = true;
	if (txtFileName.value === "") {
		isValid = false;
	}
	
	document.getElementById("btnsave").disabled = !isValid;

}

// Called by the submit button to start the upload
function doSubmit(e) {
	if (formChecker != null) {
		clearInterval(formChecker);
		formChecker = null;
	}
	
	e = e || window.event;
	if (e.stopPropagation) {
		e.stopPropagation();
	}
	e.cancelBubble = true;
	
	try {
		swfu.startUpload();
	} catch (ex) {

	}
	return false;
}

 // Called by the queue complete handler to submit the form
function uploadDone() {
	try {
		document.forms[0].submit();
	} catch (ex) {
		alert("Error submitting form");
	}
}

function fileDialogStart() {
	var txtFileName = document.getElementById("txtFileName");
	txtFileName.value = "";

	this.cancelUpload();
}

function fileQueueError(file, errorCode, message)  {
	try {
		// Handle this error separately because we don't want to create a FileProgress element for it.
		switch (errorCode) {
		case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
			//alert("涓娄紶鏂囦欢鏁伴噺涓嶈兘瓒呰绷"+swfu.settings.file_upload_limit+"涓?");
			alert("\u4E0A\u4F20\u6587\u4EF6\u6570\u91CF\u4E0D\u80FD\u8D85\u8FC7"+swfu.settings.file_upload_limit+"\u4E2A.");
			return;
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			//alert("涓娄紶鏂囦欢澶у皬涓嶈兘瓒呰绷"+swfu.settings.file_size_limit+".");
			alert("\u4E0A\u4F20\u6587\u4EF6\u5927\u5C0F\u4E0D\u80FD\u8D85\u8FC7"+swfu.settings.file_size_limit+".");
			return;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			//alert("涓嶈兘涓娄紶绌烘枃浠?");
			alert("\u4E0D\u80FD\u4E0A\u4F20\u7A7A\u6587\u4EF6.");
			return;
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
			//alert("涓嶅厑璁镐笂浼?+file.type+"绫诲瀷镄勬枃浠?");
			alert("\u4E0D\u5141\u8BB8\u4E0A\u4F20"+file.type+"\u7C7B\u578B\u7684\u6587\u4EF6.");
			return;
		default:
			//alert("鏂囦欢涓娄紶鍑洪敊.");
			alert("\u6587\u4EF6\u4E0A\u4F20\u51FA\u9519.");
			return;
		}
	} catch (e) {
	}
}

function fileQueued(file) {
	try {
		var fname = file.name;
		var txtFileName = document.getElementById("txtFileName");
		txtFileName.value = fname;
		document.getElementById('eduResInfo.title').value = fname.substring(0, fname.lastIndexOf("."));
	} catch (e) {
	}

}
function fileDialogComplete(numFilesSelected, numFilesQueued) {
	validateForm();
}

function uploadProgress(file, bytesLoaded, bytesTotal) {
	this.setButtonDisabled(true);//寮€濮嬩笂浼犲悗涓嶈兘鍐嶉€夋嫨鏂囦欢
	try {
		var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);
		//璁＄畻鏂囦欢镐诲ぇ灏忓拰宸蹭笂浼犲ぇ灏?
		var bt = "";
		if(bytesTotal < 800*1024) bt = (Math.floor((bytesTotal/1024)*100)/100) + "KB";
	    if(bytesTotal >= 800*1024) bt = (Math.floor((bytesTotal/(1024*1024))*100)/100) + "MB";
	    var bl = "";
		if(bytesLoaded < 800*1024) bl = (Math.floor((bytesLoaded/1024)*100)/100) + "KB";
	    if(bytesLoaded >= 800*1024) bl = (Math.floor((bytesLoaded/(1024*1024))*100)/100) + "MB";

		file.id = "singlefile";	// This makes it so FileProgress only makes a single UI element, instead of one for each file
		var progress = new FileProgress(file, this.customSettings.progress_target);
		progress.setProgress(percent);
		//progress.setStatus("姝ｅ湪涓娄紶鏂囦欢,镐诲ぇ灏?+bt+",宸蹭笂浼?+bl+",瀹屾垚姣旗巼:"+percent+"%");
		progress.setStatus("\u6B63\u5728\u4E0A\u4F20\u6587\u4EF6,\u603B\u5927\u5C0F"+bt+",\u5DF2\u4E0A\u4F20"+bl+",\u5B8C\u6210\u6BD4\u7387\:"+percent+"%");
	} catch (e) {
	}
}

function uploadSuccess(file, serverData,falg) {
	try {
	    document.getElementById("filesize").value=file.size;
	    document.getElementById("filename").value=file.name;
	    document.getElementById("filepath").value=serverData;
		var progress = new FileProgress(file, this.customSettings.progress_target);
		progress.setComplete();
		//progress.setStatus("涓娄紶瀹屾垚.");
		progress.setStatus("\u4E0A\u4F20\u5B8C\u6210.");
		progress.toggleCancel(false);
		
		if (serverData === " ") {
			this.customSettings.upload_successful = false;
		} else {
			this.customSettings.upload_successful = true;
			document.getElementById("hidFileID").value = serverData;
		}
	} catch (e) {
	}
}

function uploadComplete(file) {
	try {
		if (this.customSettings.upload_successful) {
			this.setButtonDisabled(true);
			uploadDone();
		} else {
			file.id = "singlefile";	// This makes it so FileProgress only makes a single UI element, instead of one for each file
			var progress = new FileProgress(file, this.customSettings.progress_target);
			progress.setError();
			//progress.setStatus("鏂囦欢涓娄紶鍑洪敊!");
			progress.setStatus("\u6587\u4EF6\u4E0A\u4F20\u51FA\u9519\!");
			progress.toggleCancel(false);
			
			var txtFileName = document.getElementById("txtFileName");
			txtFileName.value = "";
			validateForm();

			//alert("鏂囦欢涓娄紶鍙戠敓阌栾,杩炰笉涓婃湇锷″櫒.");
		}
	} catch (e) {
	}
}

function uploadError(file, errorCode, message) {
	try {
		if (errorCode === SWFUpload.UPLOAD_ERROR.FILE_CANCELLED) {
			// Don't show cancelled error boxes
			return;
		}
		
		var txtFileName = document.getElementById("txtFileName");
		txtFileName.value = "";
		validateForm();
		
		// Handle this error separately because we don't want to create a FileProgress element for it.
		switch (errorCode) {
		case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:
			//alert("鏂囦欢涓娄紶鍑洪敊.");
			alert("\u6587\u4EF6\u4E0A\u4F20\u51FA\u9519.");
			return;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
			//alert("涓娄紶鏂囦欢鏁伴噺涓嶈兘瓒呰绷"+swfu.settings.file_upload_limit+"涓?");
			alert("\u4E0A\u4F20\u6587\u4EF6\u6570\u91CF\u4E0D\u80FD\u8D85\u8FC7"+swfu.settings.file_upload_limit+"\u4E2A.");
			return;
		case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
		case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
			break;
		default:
			//alert("鏂囦欢涓娄紶鍑洪敊.");
			alert("\u6587\u4EF6\u4E0A\u4F20\u51FA\u9519.");
			return;
		}

		file.id = "singlefile";	// This makes it so FileProgress only makes a single UI element, instead of one for each file
		var progress = new FileProgress(file, this.customSettings.progress_target);
		progress.setError();
		progress.toggleCancel(false);

		switch (errorCode) {
		case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
			//progress.setStatus("鏂囦欢涓娄紶鍑洪敊.");
			progress.setStatus("\u6587\u4EF6\u4E0A\u4F20\u51FA\u9519.");
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
			//progress.setStatus("鏂囦欢涓娄紶澶辫触.");
			progress.setStatus("\u6587\u4EF6\u4E0A\u4F20\u5931\u8D25.");
			break;
		case SWFUpload.UPLOAD_ERROR.IO_ERROR:
			//progress.setStatus("鏂囦欢涓娄紶鍑洪敊.");
			progress.setStatus("\u6587\u4EF6\u4E0A\u4F20\u51FA\u9519.");
			break;
		case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
			//progress.setStatus("鏂囦欢涓娄紶鍑洪敊.");
			progress.setStatus("\u6587\u4EF6\u4E0A\u4F20\u51FA\u9519.");
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
			//progress.setStatus("鏂囦欢涓娄紶宸插彇娑?");
			progress.setStatus("\u6587\u4EF6\u4E0A\u4F20\u5DF2\u53D6\u6D88.");
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
			//progress.setStatus("鏂囦欢涓娄紶宸插仠姝?");
			progress.setStatus("\u6587\u4EF6\u4E0A\u4F20\u5DF2\u505C\u6B62.");
			break;
		}
	} catch (ex) {
	}
}
