<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../js/common/tag.jsp"%>
<html>
<head>
<%@ include file="../../js/common/jqueryJs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/common/dropzone.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/common/dropzone.css">
</head>
<body style="margin: 0px;" class="easyui-layout">

<span style="color:#2598f7;margin-left: 20px;">(上传大文件可能需要花费一些时间，请耐心等待！)</span>
<div class="dropzone" id="dropzone"> </div>
<script>
var ii = 0;
    $(".dropzone").dropzone({
        url: contextPath + '/common/addAttach',
        addRemoveLinks: true,
        dictRemoveLinks: "x",
        dictCancelUpload: "x",
        maxFiles: 10,
        maxFilesize: 20,
        acceptedFiles: ".jpg,.doc,.docx,.png,.jpeg,.gif,.exe",
        init: function() {
             this.on("success", function(file,responseText) {
					alert(responseText);
                console.log("File " + file.name + "uploaded");
            }); 
            this.on("removedfile", function(file) {
                console.log("File " + file.name + "removed");
            });
        }
    });

</script>
</body>
</html>