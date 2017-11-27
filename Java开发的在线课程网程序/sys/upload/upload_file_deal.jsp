<%@ page contentType = "text/html;charset=utf-8"%>
<script language="javascript">
  var retValue = new Array();
  retValue[0] ="<%=(String)request.getAttribute("uploadfileerror")%>";
  retValue[1] ="<%=(String)request.getAttribute("upload_filepath")%>";
  retValue[2] ="<%=(String)request.getAttribute("upload_filename")%>";
  retValue[3] ="<%=(String)request.getAttribute("upload_fileext")%>";
  retValue[4] ="<%=(String)request.getAttribute("upload_filesize")%>";
  //alert("retValue[2] = "+retValue[2]);
  window.parent.window.returnValue=retValue;
  window.parent.window.close();
</script>
