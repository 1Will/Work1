<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.gpw.bo.GpwAreaInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-用户注册</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/reg.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<Script language="JavaScript"  src="/skin/wkmk/js/ThickBox.js"></Script>
<Script language="JavaScript"  src="/skin/wkmk/js/readme.js"></Script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %>">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %>">
<script type="text/javascript">
function $$get(obj){
	return document.getElementById(obj);
}

var ltemp,utemp,ptemp,reptemp,mtemp,etemp,jtemp;
function register(){
	if(!utemp){
		checkValue($$get("username").value, 'username');
	}
	if(!ltemp){
		checkValue($$get("loginname").value, 'loginname');
	}
	if(!ptemp){
		checkValue($$get("password").value, 'password');
	}
	if(!reptemp){
		checkValue($$get("repassword").value, 'repassword');
	}
	if(!mtemp){
		checkValue($$get("mobile").value, 'mobile');
	}
	if(!etemp){
		checkValue($$get("email").value, 'email');
	}
	if(!jtemp){
		checkValue($$get("job").value, 'job');
	}
	if(ltemp && utemp && ptemp && reptemp && mtemp && etemp && jtemp){
		var unitid = $$get('unitid').value;
		if(unitid == ''){
			$$get("school_tip").style.display = '';
			return;
		}
		if(!$$get("readme").checked){
			$$get("protocol_tip").style.display = '';
			return;
		}
		document.registerForm.action = '/v.bo?method=addRegister';
		document.registerForm.submit();
	}
}
function checkValue(value, tag){
	if(tag == 'username'){
		if(value == ""){
			$$get(tag + "_tip").className = 'explain0';
			utemp = false;
		}else{
			$$get(tag + "_tip").innerHTML = "真实姓名可用！";
			$$get(tag + "_tip").className = 'explain1';
			utemp = true;
		}
	}
	if(tag == 'loginname'){
		var regexp=/^[a-zA-Z0-9_-]+$/;
		if(regexp.test(value) && value.length >= 6 && value.length <= 20){
			var url = '/v.bo?method=checkLoginname&loginname='+value+'&ram=' + Math.random();
			$.ajax({
			  type: 'post',
			  url: url,
			  async: false,
			  dataType:'text',
			  success: function(data){
			  	if(data == '1'){
			  		$$get(tag + "_tip").innerHTML = "当前用户名已被注册，请重试";
					$$get(tag + "_tip").className = 'explain0';
		  			ltemp = false;
			  	}else{
			  		$$get(tag + "_tip").innerHTML = "当前用户名可用！";
					$$get(tag + "_tip").className = 'explain1';
			  	  	ltemp = true;
			  	}
			  }
			});
		}else{
			$$get(tag + "_tip").innerHTML = "格式错误，6-20位字符，数字、字母、-、下划线(_)组成";
			$$get(tag + "_tip").className = 'explain0';
  			ltemp = false;
		}
	}
	if(tag == 'password'){
		if(value.length >= 6 && value.length <= 25){
			$$get(tag + "_tip").innerHTML = "密码可用！";
			$$get(tag + "_tip").className = 'explain1';
	  	  	ptemp = true;
		}else{
			$$get(tag + "_tip").innerHTML = "错误，6-25位字符,建议由字母/数字/符号两种以上组合";
			$$get(tag + "_tip").className = 'explain0';
  			ptemp = false;
		}
	}
	if(tag == 'repassword'){
		if($$get('repassword').value == '' || $$get('password').value != $$get('repassword').value){
			$$get(tag + "_tip").innerHTML = "两次密码输入不一致";
			$$get(tag + "_tip").className = 'explain0';
  			reptemp = false;
		}else{
			$$get(tag + "_tip").innerHTML = "密码一致可用！";
			$$get(tag + "_tip").className = 'explain1';
	  	  	reptemp = true;
		}
	}
	if(tag == 'mobile'){
		var regexp=/^1\d{10}$/;
		if(!regexp.test(value)){
			$$get(tag + "_tip").innerHTML = "手机号格式不对";
			$$get(tag + "_tip").className = 'explain0';
  			mtemp = false;
		}else{
			$$get(tag + "_tip").innerHTML = "手机号码可用！";
			$$get(tag + "_tip").className = 'explain1';
	  	  	mtemp = true;
		}
	}
	if(tag == 'email'){
		var regexp=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if(!regexp.test(value)){
			$$get(tag + "_tip").innerHTML = "电子邮件格式不对";
			$$get(tag + "_tip").className = 'explain0';
  			etemp = false;
		}else{
			$$get(tag + "_tip").innerHTML = "电子邮件可用！";
			$$get(tag + "_tip").className = 'explain1';
	  	  	etemp = true;
		}
	}
	if(tag == 'job'){
		if(value == ""){
			$$get(tag + "_tip").className = 'explain0';
			jtemp = false;
		}else{
			$$get(tag + "_tip").innerHTML = "格式正确！";
			$$get(tag + "_tip").className = 'explain1';
			jtemp = true;
		}
	}
}
function selectSchool(){
	var retValue=showModalDialog('/v.bo?method=selectSchoolMain','选择学校',"dialogWidth:800px;dialogHeight:600px;scroll=no;border=thin;help=0;status=no");
  	if(retValue!=null){
  		$$get("unitid").value=retValue[0];
  		$$get("unitname").value=retValue[1];
	}
	var unitid = $$get("unitid").value;
    if(unitid != null && unitid != ""){
    	$.ajax({
			  type: 'post',
			  url: "/v.bo?method=selectVodFilmTypes&unitid=" + unitid + "&ram=" + Math.random(),
			  async: false,
			  dataType:'text',
			  success: function(data){
				  $("#subject").empty();
	              $("#subject").append(data);
			  }
			});
     }
}
function selectArea(type, areano){
	$.ajax({
        type: "post",
        url: "/v.bo?method=selectArea&areano=" + areano + "&tag=" + type + "&ram=" + Math.random(),
        async: false,
        dataType: "text",
        success: function(data){
        	if(type == '0'){
                var str = data.split(";");
                $("#areano2").html(str[0]);
                if(str[1] == ''){
                	$$get("areano3").style.display = "none";
                }else{
                	$$get("areano3").style.display = "inline";
                	$("#areano3").html(str[1]);
                }
            }else{
            	if(data == ''){
            		$$get("areano3").style.display = "none";
                }else{
                	$$get("areano3").style.display = "inline";
                	$("#areano3").html(data);
                }
            }
        }
	});
}
function agreeonProtocol() {
	if(!$$get("readme").checked){
		$$get("protocol_tip").style.display = '';
		return;
	}else{
		$$get("protocol_tip").style.display = 'none';
		return;
	}
}
</script>
</head>

<body style="background:#f8f8f8">
<div id="header">
  <div class="logo">
    <a href="/index.html"></a>
  </div>
  <div class="seach">
    <div class="login">
      <a href="/index.html">首页</a>
      <span class="mar_1">|</span>
      <a href="/feedback.htm" style="padding-left:10px;">意见反馈</a>
    </div>
  </div>  
</div>
<div id="registered">
 <form name="registerForm" method="post">
 <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <th height="80" colspan="2" align="left" valign="top"><h2 class="reg_h2">用户注册</h2></th>
    </tr>
  <tr>
    <td width="62%" height="50" valign="middle" class="pad_1">
      <span class="color_8 float1 mar_8">*</span>
      <label class="w100 mar_8">真实姓名</label>
      <input type="text" value="" name="username" id="username" maxlength="20" class="reg_input" onblur="checkValue(this.value, 'username')"/>
      <span class="explain" id="username_tip">请填写您的真实姓名</span>
    </td>
    <td width="38%" rowspan="15" align="center" valign="top">
      <img src="/skin/wkmk/images/process.jpg" />
    </td>
  </tr>
  <tr>
    <td height="50" class="pad_1">
      <span class="color_8 float1 mar_8">*</span>
      <label class="w100 mar_8">用&nbsp;户&nbsp;名</label>
      <input type="text" value="" name="loginname" id="loginname" maxlength="20" class="reg_input" onblur="checkValue(this.value, 'loginname')"/>
      <span class="explain" id="loginname_tip">6-20位字符，数字、字母、-、下划线(_)组成</span>
    </td>
  </tr>
  <tr>
    <td height="50" class="pad_1">
      <span class="color_8 float1 mar_8">*</span>
      <label class="w100 mar_8">用户密码</label>
      <input type="password" value="" name="password" id="password" maxlength="25" class="reg_input" onblur="checkValue(this.value, 'password')"/>
      <span class="explain" id="password_tip">6-25位字符,建议由字母/数字/符号两种以上组合</span>
    </td>
  </tr>
  <tr>
    <td height="50" class="pad_1">
       <span class="color_8 float1 mar_8">*</span>
       <label class="w100 mar_8">确认密码</label>
      <input type="password" value="" name="repassword" id="repassword" maxlength="25" class="reg_input" onblur="checkValue(this.value, 'repassword')"/>
      <span class="explain" id="repassword_tip">请再次输入密码</span>
    </td>
  </tr>
  <tr>
    <td height="50" class="pad_1">
       <span class="color_8 float1 mar_8">*</span>
      <label class="w100 mar_8">用户性别</label>
      <span class="radio_m"><input name="sex" type="radio" value="0" class="mar_3" checked="checked"/>男</span>
      <span class="radio_m"><input name="sex" type="radio" value="1" class="mar_3" />女</span>
    </td>
  </tr>
  <tr>
    <td height="50" class="pad_1">
      <span class="color_8 float1 mar_8">*</span>
      <label class="w100 mar_8">手机号码</label>
      <input type="text" value="" name="mobile" id="mobile" maxlength="11" class="reg_input" onblur="checkValue(this.value, 'mobile')"/>
      <span class="explain" id="mobile_tip">请填写真实手机号码</span>
    </td>
  </tr>
  <tr>
    <td height="50" class="pad_1">
      <span class="color_8 float1 mar_8">*</span>
      <label class="w100 mar_8">电子邮箱</label>
      <input type="email" value="" name="email" id="email" class="reg_input" onblur="checkValue(this.value, 'email')"/>
      <span class="explain" id="email_tip">请填写真实邮箱号码，找回密码时需要</span>
    </td>
  </tr>
  <tr>
    <td height="50" class="pad_1">
      <span class="color_8 float1 mar_8">*</span>
      <label class="w100 mar_8">&nbsp;&nbsp;&nbsp;职&nbsp;&nbsp;&nbsp;务</label>
      <input type="email" value="" name="job" id="job" maxlength="25" class="reg_input" onblur="checkValue(this.value, 'job')"/>
      <span class="explain" id="job_tip">请填写您现任职务</span>
    </td>
  </tr>
  <tr>
     <td height="50" class="pad_1">
      <span class="color_8 float1 mar_8">*</span>
      <label class="w100 mar_8">&nbsp;&nbsp;&nbsp;籍&nbsp;&nbsp;&nbsp;贯</label>
      <select name="areano1" onchange="selectArea('0', this.value)" class="select_1">
        <%
        List arealist1 = (List)request.getAttribute("arealist1");
        GpwAreaInfo areaInfo = null;
        	for(int i=0; i<arealist1.size(); i++){
            	areaInfo = (GpwAreaInfo)arealist1.get(i);
        %>
            <option value="<%=areaInfo.getAreano() %>"><%=areaInfo.getAreaname() %></option>
        <%} %>
      </select>
      <select name="areano2" id="areano2" onchange="selectArea('1', this.value)" class="select_1">
        <%
        List arealist2 = (List)request.getAttribute("arealist2");
        for(int i=0; i<arealist2.size(); i++){
        	areaInfo = (GpwAreaInfo)arealist2.get(i);
        %>
            <option value="<%=areaInfo.getAreano() %>"><%=areaInfo.getAreaname() %></option>
        <%} %>
      </select>
      <select name="areano3" id="areano3" class="select_1" style="display:none;">
        <option value="">请选择</option>
      </select>
    </td>
  </tr>
  <tr>
    <td height="50" class="pad_1">
      <span class="color_8 float1 mar_8">*</span>
      <label class="w100 mar_8">所在学校</label>
      <input type="email" value="" name="unitname" id="unitname" readonly="readonly" class="reg_input" onclick="selectSchool()"/>
      <input type="button" value="选择学校" class="button_1" onclick="selectSchool()"/>
      <span class="explain0" id="school_tip" style="display:none;">请选择所在学校</span>
      <input name="unitid" id="unitid" type="hidden" value="" />
    </td>
  </tr>
  <tr>
     <td height="50" class="pad_1">
      <span class="color_8 float1 mar_8">*</span>
      <label class="w100 mar_8">学科专业</label>
      <select name="subject" id="subject" class="select_1">
        <option value="">请选择学科专业</option>
      </select>
      <span>注：请先选择学校再选择学科专业</span>
    </td>
  </tr>
  <tr>
    <td height="50">
      <span class="color_8 float1 mar_8">&nbsp;</span>
      <label class="w100 mar_8" style="width:123px;"></label>
      <input name="readme" id="readme" type="checkbox" value="1" checked="checked" onclick="agreeonProtocol()"/>
      <span class="color_7">我已阅读并同意<a id="protocol" style="cursor:pointer;">《微课慕课网用户注册协议》</a></span>
      <span id="protocol_tip" style="color:#ff3333;display:none;">请同意注册协议！</span>
    </td>
  </tr>
  <tr>
    <td><input type="button" value="立即注册" class="sub_re" onclick="register()"/></td>
  </tr>
 </table>
 </form>
</div>
<div id="login_footer">
  <span>微课慕课网 版权所有@2014-2015</span>
  <span>Tel:010-8345-6666</span>
  <span>京ICP备09025234号
</div>
</body>
</html>