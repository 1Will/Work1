<%@ page contentType = "text/html;charset=utf-8"%>
<html>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>插入图片</title>
<script language="javascript">
  var ImgObj=new Image();      //建立一个图像对象
  var FileObj,ImgFileSize,ImgWidth,ImgHeight,FileExt;//全局变量 图片相关属性


  var AllImgExt=".jpg|.jpeg|.gif|.png|"//全部图片格式类型
  var AllowImgFileSize=300;    //允许上传图片文件的大小  单位：KB
  var AllowImgWidth=1024;      //允许上传的图片的宽度　单位：px(像素)
  var AllowImgHeight=2000;      //允许上传的图片的高度　单位：px(像素)

function CheckProperty(obj)    //检测图像属性
{
 var ErrMsg="";
  FileObj=obj;
  if(obj.value=="")
   return false;

  FileExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
  if(AllImgExt!=0&&AllImgExt.indexOf(FileExt+"|")==-1)  //判断文件类型是否允许上传
  {
    alert("不允许上传"+FileExt+"类型的文件,请上传 "+AllImgExt+" 类型的文件。");
    return false;
  }
  else{
    ImgObj.src=obj.value;

    FileObj=obj;
    if(ImgObj.readyState!="complete")  //如果图像是未加载完成进行循环检测
    {
     setTimeout("CheckProperty(FileObj)",500);
    }

    ImgFileSize=Math.round(ImgObj.fileSize/1024*100)/100;//取得图片文件的大小
    ImgWidth=ImgObj.width      //取得图片的宽度
    ImgHeight=ImgObj.height;    //取得图片的高度


    if(AllowImgWidth!=0&&AllowImgWidth<ImgWidth)
      ErrMsg+="\n图片宽度超过限制。请上传宽度小于"+AllowImgWidth+"px的文件，当前图片宽度为"+ImgWidth+"px";

    if(AllowImgHeight!=0&&AllowImgHeight<ImgHeight)
      ErrMsg+="\n图片高度超过限制。请上传高度小于"+AllowImgHeight+"px的文件，当前图片高度为"+ImgHeight+"px";

    if(AllowImgFileSize!=0&&AllowImgFileSize<ImgFileSize)
      ErrMsg+="\n图片文件大小超过限制。请上传小于"+AllowImgFileSize+"KB的文件，当前文件大小为"+ImgFileSize+"KB";

    if(ErrMsg!=""){
      alert(ErrMsg);
      return false;
    }
    else{
      return true;
    }
  }
}

ImgObj.onerror=function(){
  ErrMsg='\n图片格式不正确或者图片已损坏!';
}

function CheckUploadFile(theobj){
//检测文件属性
if (!CheckProperty(theobj)){
    theobj.focus();
    document.pageForm.btnsave.disabled=true;
}
else{
     //预览
     document.pageForm.topreview.src=theobj.value;
     document.pageForm.btnsave.disabled=false;
  }
}

function SubmitForm(){
    document.pageForm.action="sysImageUploadAction.do?method=uploadimagedeal&savepath=<bean:write name="savepath"/>&filename=<bean:write name="filename"/>&sketch=<bean:write name="sketch"/>&pathtype=<bean:write name="pathtype"/>";
    document.pageForm.submit();
}
</script>
<body leftmargin="0" topmargin="20">
<table class="bg_basecolor" width="100%" valign="middle" border="0" cellpadding="0" cellspacing="0" align="center" height="100%">
  <tr>
    <td >
      <form method="POST" ENCTYPE="multipart/form-data" name="pageForm">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr  height="90">
          <td width="20%" align="center">预览</td>
          <td width="80%">
           <img src="/public/images/upload/preview_img.gif" width="180" height="70" border="1" id=topreview>
           </td>
        </tr>
        <tr>
          <td align="center">来源</td>
          <td ><input type="FILE" class="file" size="38" name="thefile" style="cursor:hand" onchange="javascript:CheckUploadFile(this)"></td>
        </tr>
         <tr align="center" height="30">
            <td colspan="2" >
             <input type="button" name="btnsave"  disabled="disabled" value="上载图片" class="button" onclick="javascript:return SubmitForm()">
             &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="Button" value="关闭窗口" class="button" onclick="top.Dialog.close();">
             </td>
          </tr>
      </table>
       </form>
    </td>
  </tr>
</table>
</html>
