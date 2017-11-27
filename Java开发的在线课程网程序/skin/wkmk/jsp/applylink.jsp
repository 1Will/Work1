<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-申请链接</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %>">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %>">
<script type="text/javascript">
function $$get(obj){
	return document.getElementById(obj);
}

var FileObj,FileExt;
var AllImgExt=".jpg|.jpeg|.gif|.png|"

function CheckProperty(obj){
	var ErrMsg="";
	FileObj=obj;
	if(obj.value=="")
	   return false;
	
	FileExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
	if(AllImgExt!=0&&AllImgExt.indexOf(FileExt+"|")==-1){
	    alert("不允许上传"+FileExt+"类型的文件,请上传 "+AllImgExt+" 类型的文件。");
	    return false;
	}
	return true;
}

function CheckUploadFile(theobj){
	document.getElementById('linklogo').value=theobj.value;
	if (!CheckProperty(theobj)){
		document.getElementById('linklogo').value = "";
	}
}

var temp1,temp2,temp3,temp4,temp5;
function applyLink(){
	if(!temp1){
		checkValue($$get("linkname").value, 'linkname');
	}
	if(!temp2){
		checkValue($$get("linkurl").value, 'linkurl');
	}
	if(!temp3){
		checkValue($$get("linkman").value, 'linkman');
	}
	if(!temp4){
		checkValue($$get("telephone").value, 'telephone');
	}
	if(!temp5){
		checkValue($$get("email").value, 'email');
	}
	if(temp1 && temp2 && temp3 && temp4 && temp5){
		var linklogo = $$get("linklogo").value;
		if(linklogo == ""){
			$$get("linklogo_tip").className = 'explain color_8';
			return;
		}else{
			$$get("linklogo_tip").className = 'explain';
		}
		
		var wkmklogosize1 = $$get("wkmklogosize1").value;
		if(wkmklogosize1 == ''){
			$$get("wkmklogosize1").style.border = "1px solid #c51e32";
			return;
		}
		var wkmklogosize2 = $$get("wkmklogosize2").value;
		if(wkmklogosize2 == ''){
			$$get("wkmklogosize2").style.border = "1px solid #c51e32";
			return;
		}
		document.applylinkForm.action = '/v.bo?method=addApplyLink';
		document.applylinkForm.submit();
	}
}
function checkValue(value, tag){
	if(tag == 'linkname'){
		if(value == ""){
			$$get(tag + "_tip").className = 'explain color_8';
			temp1 = false;
		}else{
			$$get(tag + "_tip").className = 'explain';
			temp1 = true;
		}
	}
	if(tag == 'linkurl'){
		if(value == ""){
			$$get(tag + "_tip").className = 'explain color_8';
			temp2 = false;
		}else{
			$$get(tag + "_tip").className = 'explain';
			temp2 = true;
		}
	}
	if(tag == 'linkman'){
		if(value == ""){
			$$get(tag + "_tip").className = 'explain color_8';
			temp3 = false;
		}else{
			$$get(tag + "_tip").className = 'explain';
			temp3 = true;
		}
	}
	if(tag == 'telephone'){
		if(value == ""){
			$$get(tag + "_tip").className = 'explain color_8';
			temp4 = false;
		}else{
			$$get(tag + "_tip").className = 'explain';
			temp4 = true;
		}
	}
	if(tag == 'email'){
		if(value == ""){
			$$get(tag + "_tip").className = 'explain color_8';
			temp5 = false;
		}else{
			$$get(tag + "_tip").className = 'explain';
			temp5 = true;
		}
	}
}
function  setWkmkLogoSize(value){
	if(value == '1'){
		$$get("wkmklogosize1").value = "170";
		$$get("wkmklogosize1").style.border = "1px solid #ccc";
		$$get("wkmklogosize2").value = "60";
		$$get("wkmklogosize2").style.border = "1px solid #ccc";
	}
	if(value == '2'){
		$$get("wkmklogosize1").value = "200";
		$$get("wkmklogosize1").style.border = "1px solid #ccc";
		$$get("wkmklogosize2").value = "80";
		$$get("wkmklogosize2").style.border = "1px solid #ccc";
	}
}
</script>
</head>

<body style="background:#f7f7f7;">
<%@ include file="top.jsp"%>

<div class="view_main">
	<div class="view_left">
		<ul>
		<a href="/v-aboutus-12-0.htm"><li>关于我们</li></a>
		<a href="/v-aboutgroup-12-0.htm"><li>团队介绍</li></a>
		<a href="/v-job-12-0.htm"><li>人才招聘</li></a>
		<a href="/v-contact-12-0.htm"><li>联系我们</li></a>
		<a href="/feedback.htm"><li>意见反馈</li></a>
		<a href="/v-link-12-0.htm"><li>友情链接</li></a>
		<a href="/v-applylink-12-0.htm"><li class="hover" style="border-bottom:none;">申请链接</li></a>
		</ul>
	</div>
	<form name="applylinkForm" method="post" enctype="multipart/form-data">
	<div class="view_right">
		<div class="view_right_01 mar_13">
		<h3>申请链接</h3>
		<logic:notPresent name="addOk">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="float1">
		  <tr>
		    <td width="10%" height="50" align="right">
		      <span class="color_8">*</span>
		      <label class="mar_13_1">网站名称</label>
		    </td>
		    <td height="50" colspan="2" align="left">
		      <input type="text" name="linkname" id="linkname" class="reg_input" style="width:300px" value="" onblur="checkValue(this.value, 'linkname')"/>
		      <span class="explain" id="linkname_tip">请填写贵机构（学校或公司）全称</span>
		    </td>
		  </tr>
		  <tr>
		    <td width="10%" height="50" align="right">
		      <span class="color_8">*</span>
		      <label class="mar_13_1">网站网址</label>
		    </td>
		    <td height="50" colspan="2" align="left">
		      <input type="text" name="linkurl" id="linkurl" class="reg_input" style="width:300px" value="" onblur="checkValue(this.value, 'linkurl')"/>
			  <span class="explain" id="linkurl_tip">请填写贵机构网站首页网址</span>
			</td>
		  </tr>
		  <tr>
		    <td height="50" align="right">
		      <span class="color_8">*</span>
		      <label class="mar_13_1">网站类型</label>
		    </td>
		    <td colspan="2">
		      <select class="select_1" name="linktype" style="width:123px;">
		        <%
		        String[] applylinkid = Constants.getCodeTypeid("applylink");
		        String[] applylinkname = Constants.getCodeTypename("applylink");
		        for(int i=0, size=applylinkid.length; i<size; i++){
		        %>
		        <option value="<%=applylinkid[i] %>"><%=applylinkname[i] %></option>
		        <%} %>
		      </select>
		      <span class="explain">请正确选择您网站的类型</span>
		    </td>
		  </tr>
		  <tr>
		    <td height="50" align="right" valign="top" class="pad_3">
		      <label class="mar_13_1">网站简介</label>
		    </td>
		    <td colspan="2" valign="top" class="pad_3">
		      <textarea name="descript" style="width:305px;height:100px;float:left;margin-bottom:10px;"></textarea>
		      <span class="explain">不超过500个汉字</span></td>
		    </tr>
		  <tr>
		    <td rowspan="2" align="right" valign="top" class="pad_3"><span class="color_8">*</span>
		      <label class="mar_13_1">logo上传</label>
		    </td>
		    <td height="30" colspan="2" class="pad_3">
		      <input type="text" name="linklogo" id="linklogo" class="reg_input" style="width:225px;" readonly="readonly" value=""/>      
		      <input type="file" name="thefile" style="width:70px;height:30px;padding-left:5px;padding-top:3px;float:left;" onchange="CheckUploadFile(this)">
		      <span class="explain" id="linklogo_tip">请选择本地logo图片文件</span>
		    </td>
		    </tr>
		  <tr>
		    <td height="28" colspan="2" valign="top">注：在‘友情链接’页面呈现贵机构logo图片，尺寸要求190*60</td>
		  </tr>
		  <tr>
		    <td height="50" align="right"><span class="color_8">*</span>
		      <label class="mar_13_1">联系姓名</label></td>
		    <td colspan="2">
		      <input type="text" name="linkman" id="linkman" maxlength="20" class="reg_input" value="" onblur="checkValue(this.value, 'linkman')"/>      
		      <span class="explain" id="linkman_tip">请填写您的真实姓名</span>
		    </td>
		  </tr>
		  <tr>
		    <td align="right" class="pad_3"><span class="color_8">*</span>
		      <label class="mar_13_1">联系电话</label></td>
		    <td height="25" colspan="2" valign="top" class="pad_3">
		      <input type="text" name="telephone" id="telephone" maxlength="12" class="reg_input" value="" onblur="checkValue(this.value, 'telephone')"/>      
		      <span class="explain" id="telephone_tip">请填写您的联系电话方便我们与您取得联系</span>
		    </td>
		  </tr>
		  <tr>
		    <td height="50" align="right" class="pad_3"><span class="color_8">*</span>
		      <label class="mar_13_1">联系邮箱</label></td>
		    <td colspan="2" class="pad_3">
		      <input type="text" name="email" id="email" maxlength="50" class="reg_input" value="" onblur="checkValue(this.value, 'email')"/>      
		      <span class="explain" id="email_tip">请填写您的邮箱地址方便我们发送相关资料</span>
		    </td>
		  </tr>
		  <tr>
		    <td height="30" align="right" class="pad_3"><label class="mar_13_1">我们的放置</label></td>
		    <td colspan="2" class="pad_3"><input type="text" name="wkmklocation" class="reg_input" maxlength="200"/>
		      <span class="explain">如建立链接，会把微课慕课网链接放在贵站何处，请简单说明</span>
		    </td>
		  </tr>
		  <tr>
		    <td height="60" colspan="3">
		      <label class="float1" style="margin:0 23px;">请选择或者输入您需要我们提供的logo尺寸</label>
		      <input type="text" name="wkmklogosize1" id="wkmklogosize1" class="reg_input" style="width:50px;" value="170" maxlength="3"/>
		      <span class="float1" style="margin:0 10px;">*</span>
		      <input type="text" name="wkmklogosize2" id="wkmklogosize2" class="reg_input" style="width:50px;" value="60" maxlength="3"/>
		    </td>
		  </tr>
		  <tr>
		    <td height="130" colspan="2" align="left" valign="top" class="pad_3">
		      <div class="float1" style="margin:0 23px;">
		        <input name="wkmklogosize" type="radio" value="1" checked="checked" class="float1" onclick="setWkmkLogoSize(1)"/>
		        <span class="float1" style="margin:0 10px;">170*60</span>
		        <img src="/skin/wkmk/images/link_logo1.png" />
		      </div>
		      <div class="float1">
		        <input name="wkmklogosize" type="radio" value="2" class="float1" onclick="setWkmkLogoSize(2)"/>
		        <span class="float1" style="margin:0 10px;">200*80</span>
		        <img src="/skin/wkmk/images/link_logo2.png" />
		      </div>
		    </td>
		  </tr> 
		  <tr>
		    <td colspan="2"><input class="sub_re" type="button" value="提交" style="width:200px" onclick="applyLink()"></td>
		    </tr> 
		</table>
		</logic:notPresent>
		<logic:present name="addOk">
		<div>
			<p style="font-size:20px;padding-top:130px;padding-left:260px;">提交成功</p>
			<p style="font-size:12px;padding-top:10px;padding-left:180px;padding-bottom:150px;">您的申请链接已成功提交，请等候我们人员与您取得联系！</p>
		</div>
		</logic:present>
		</div>
	</div>
	<input type="hidden" name="hiddenCode" value="<%=request.getAttribute("hiddenCode") %>"/>
	</form>
</div>

<%@ include file="footer.jsp"%>
</body>
</html>