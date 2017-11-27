<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.gpw.bo.GpwAreaInfo"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>统一用户注册</title>
<link href="/public/css/register.css" rel="stylesheet" type="text/css" />
<SCRIPT language="javascript" src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript" src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript" src="/public/DatePicker/WdatePicker.js"></Script>
<Script language="JavaScript" src="/public/js/prototype.js"></Script>
<script type="text/javascript">
var reFlag = false;

function $get(obj){
	return document.getElementById(obj);
}

function reset(){
	obj = document.sysUserInfoActionForm;
	obj.reset();
}

function register(){
	obj = document.sysUserInfoActionForm;
	if(autoCheckForm(obj)==false){
		return false;
	}
	
	if($get('sysUserInfo.password').value != $get('passwordagain').value){
	  $get('passwordagain').select();
      $get('rg4-1').style.display = 'none';
      $get('rg4-2').style.display = '';
      return false;
    }
  
    if(reFlag){
 	  $get('sysUserInfo.loginname').select();
      $get('rg1-1').style.display = 'none';
      $get('rg1-2').style.display = '';
 	  return false;
 	}
	
	var flag = false;
	var nativeplace1 = document.getElementById('sysUserInfo.nativeplace1').value;
    if(nativeplace1 == '') flag = true;
    if(flag){
      alert("请选择籍贯!");
 	  return false;
    }
    
    flag = false;
    var validate = document.getElementById('validate');
    new Ajax.Request(
		"/register.do?method=checkCode&code=" + validate.value + "&ram=" + Math.random(),
		{
		method:"get",
		asynchronous:false,//true为异步请求
		onComplete:function(xhr){
			var responseObj = xhr.responseText;
			if(responseObj != 'ok'){
				flag = true;
			}
		}
		}
	);
	if(flag){
		validate.select();
        $get('rg8-1').style.display = 'none';
        $get('rg8-2').style.display = '';
		return false;
	}
	
	var xieyi = document.getElementById("xieyi");
	if(!xieyi.checked){
		alert("您还没有同意服务条款！");
		return false;
	}
    
    obj.action="/register.do?method=sr";
    obj.submit();
}

function checkLoginname(value){
  if(value != ""){
  	  var regexp=/^[a-zA-Z0-9_-]+$/;
  	  if(!regexp.test(value)){
		  reFlag = true;
	  }else{
	  	  new Ajax.Request(
			"/register.do?method=checkLoginname&loginname=" + value + "&ram=" + Math.random(),
			{
			method:"get",
			asynchronous:false,//true为异步请求
			onComplete:function(xhr){
				var responseObj = xhr.responseText;
				if(responseObj != '0'){
					reFlag = true;
				}else{
					reFlag = false;
				}
			}
			}
		);
	  }
  }
  if(reFlag){
  	$get('rg1-1').style.display = 'none';
    $get('rg1-2').style.display = '';
  }else{
  	$get('rg1-1').style.display = '';
    $get('rg1-2').style.display = 'none';
  }
}

function checkValue(value, tag){
	if(value != ""){
		$get(tag + '-1').style.display = '';
		$get(tag + '-2').style.display = 'none';
	}else{
		$get(tag + '-1').style.display = 'none';
		$get(tag + '-2').style.display = '';
	}
}

function addRow(obj){
	var rowcount = document.getElementById('rowcount');
	if(rowcount.value == '5'){
		alert("选择学科年级不能超过5个!");
		return false;
	}
	var newrowcount = parseInt(rowcount.value)+1;
	document.getElementById('rowcount').value = newrowcount;
	
	var table = document.getElementById("t_table");
    var row = table.insertRow(table.rows.length);
  	row.insertCell(row.cells.length).innerHTML='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学&nbsp;&nbsp;&nbsp;&nbsp;科：&nbsp;<input name="subjectname'+newrowcount+'" id="subjectname'+newrowcount+'" readonly="readonly" onclick="selectSubject(0, '+newrowcount+')" type="text" value="" style="width:70px; height:24px; background:url(/public/images/register/z19.jpg) no-repeat; line-height:24px; padding-left:5px; border:none;" />&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;级：&nbsp;<input name="gradename'+newrowcount+'" id="gradename'+newrowcount+'" readonly="readonly" onclick="selectSubject(1, '+newrowcount+')" type="text" value="" style="width:100px; height:24px; background:url(/public/images/register/z19.jpg) no-repeat; line-height:24px; padding-left:5px; border:none;" />';
    row.insertCell(row.cells.length).innerHTML='<INPUT class="btn_del" onClick="delRow(this)" readonly type="button" value="删除" name="btnselect">';
	row.cells[0].style.width="370";
	row.cells[0].style.color="#000";
	//row.cells[0].style["padding-left"]="42px";
}

function delRow(obj){
	var row = obj.parentNode.parentNode;
	row.parentNode.removeChild(row);
	document.getElementById('rowcount').value = parseInt(document.getElementById('rowcount').value)-1
}

function selectSubject(flag, currowcount){
	var subjectid = $get('subjectid'+currowcount).value;
	if(flag == 1 && subjectid == ''){
		alert("请选择所属学科!");
		return false;
	}
	
	var unitid = $get('sysUserInfo.unitid').value;
	var objid = '0';
	if(flag == 1) objid = subjectid;
	
	var url = '/register.do?method=selectSubject&flag=' + flag + '&objid=' + objid + '&unitid=' + unitid;
	var retValue=showModalDialog(url,'选择学科版本',"dialogWidth:300px;dialogHeight:385px;scroll=auto;border=thin;help=0;status=no");
	if(retValue != null){
		if(flag == 0){
		  $get('subjectname'+currowcount).value = retValue[0];
		  $get('subjectid'+currowcount).value = retValue[1];
		  //修改时把子集去掉
		  $get('gradename'+currowcount).value = '';
		  $get('gradeid'+currowcount).value = '';
		}
		if(flag == 1){
		  $get('gradename'+currowcount).value = retValue[0];
		  $get('gradeid'+currowcount).value = retValue[1];
		}
	}
}
function selectUnit(area){
  new Ajax.Request(
	"/register.do?method=selectUnit&area=" + area + "&ram=" + Math.random(),
	{
	method:"get",
	asynchronous:false,//true为异步请求
	onComplete:function(xhr){
		var responseObj = xhr.responseText;
		document.getElementById('selectunit').innerHTML = responseObj;
	}
	}
  );
}
function selectArea(areano, tag){
  if(areano != ''){
  new Ajax.Request(
	"/register.do?method=selectArea&areano=" + areano + "&tag=" + tag + "&ram=" + Math.random(),
	{
	method:"get",
	asynchronous:false,//true为异步请求
	onComplete:function(xhr){
		var responseObj = xhr.responseText;
		if(tag == '0'){
			var str = responseObj.split(";");
			document.getElementById('sysUserInfo.nativeplace2').innerHTML = str[0];
			document.getElementById('sysUserInfo.nativeplace3').innerHTML = str[1];
		}else{
			document.getElementById('sysUserInfo.nativeplace3').innerHTML = responseObj;
		}
	}
	}
  );
  }
}
</script>
</head>

<body>
<div id="top">
<span style="padding-left:140px; float:left;"><img src="/public/images/register/logo.jpg" /></span>
<span style="float:right; padding-right:120px; padding-top:10px;"><a href="/index.jsp">首页</a> | <a href="http://www.edutech.com.cn" target="_blank">帮助</a></span>
</div><!--#top-->
<html:form action="/sysUserInfoAction.do" method="post">
<div id="center"> 
	<div id="list_top">
	  <span style="border-top:#8bb8d5 1px solid; background-color:#FFFFFF; line-height:1px;*line-height:4px; float:left; width:740px;">&nbsp;
	  <span style="width:5px; position:absolute; left:0px; top:0px;"><img src="/public/images/register/z16.jpg" /></span>
	  <span style="width:5px; position:absolute; right:0px; top:0px;"><img src="/public/images/register/z15.jpg" /></span>
	  </span>  
	</div><!--#list_top-->
	<div id="list">
		<div id="list01">
		<h3 style="background:url(/public/images/register/z04.jpg) no-repeat 10px 1px;position:relative;">填写注册信息<span style="padding-right:5px; float:right; font-weight:normal; font-size:12px; position:absolute; right:0px; top:0px;">请注意：带有<a style="color:red;">*</a>的项目必须填写。</span></h3>
		<ul>
			<li>
				<dt>用&nbsp;户&nbsp;名：</dt>
				<dd style="width:163px;"><input name="sysUserInfo.loginname" id="sysUserInfo.loginname" CK_NAME="用户名" CK_TYPE="NotEmpty" maxlength="25" type="text" value="" onblur="checkLoginname(this.value)" style="width:153px; height:24px; background:url(/public/images/register/z19.jpg) no-repeat; line-height:24px; padding-left:5px; border:none;" /></dd>
				<dd style="color:red;margin-left:-6px;margin-right:5px;">*</dd>
				<dd class="one"  id="rg1-1">英文、数字、下划线"_"及"-" 组成!</dd>
				<dd class="two"  id="rg1-2" style="display:none;">当前用户名格式错误或已被占用！</dd>
			</li>
			<li>
				<dt>真实姓名：</dt>
				<dd style="width:163px;"><input name="sysUserInfo.username" CK_NAME="真实姓名" CK_TYPE="NotEmpty" maxlength="25" type="text" value="" onblur="checkValue(this.value, 'rg2')" style="width:153px; height:24px; background:url(/public/images/register/z19.jpg) no-repeat; line-height:24px; padding-left:5px; border:none;" /></dd>
				<dd style="color:red;margin-left:-6px;margin-right:5px;">*</dd>
				<dd class="one" id="rg2-1">真实姓名有效才可以注册成功！</dd>
				<dd class="two" id="rg2-2" style="display:none;">请输入您的真实姓名！</dd>
			</li>
			<li>
				<dt>密&nbsp;&nbsp;&nbsp;&nbsp;码：</dt>
				<dd style="width:163px;"><input name="sysUserInfo.password" id="sysUserInfo.password" CK_NAME="密码" CK_TYPE="NotEmpty" maxlength="25" type="password" value="" onblur="checkValue(this.value, 'rg3')" style="width:153px; height:24px; background:url(/public/images/register/z19.jpg) no-repeat; line-height:24px; padding-left:5px; border:none;" />
				</dd>
				<dd style="color:red;margin-left:-6px;margin-right:5px;">*</dd>
				<dd class="one" id="rg3-1">请输入密码！</dd>
				<dd class="two" id="rg3-2" style="display:none;">密码不能为空！</dd>
			</li>
			<li>
				<dt>确认密码：</dt>
				<dd style="width:163px;"><input name="passwordagain" id="passwordagain" type="password" value="" onblur="checkValue(this.value, 'rg4')" style="width:153px; height:24px; background:url(/public/images/register/z19.jpg) no-repeat; line-height:24px; padding-left:5px; border:none;" />
				</dd>
				<dd style="color:red;margin-left:-6px;margin-right:5px;">*</dd>
				<dd class="one" id="rg4-1">请再次输入密码,确保没有输入错误！</dd>
				<dd class="two" id="rg4-2" style="display:none;">两次密码输入不一致！</dd>
			</li>
		</ul>
		</div><!--#list01-->
	
		<div id="list02">
		<h3 style="background:url(/public/images/register/z01.jpg) no-repeat 10px 1px;">安全信息</h3>
		<ul>
			<li>
				<dt>密码问题：</dt>
				<dd style="width:163px;"><input name="sysUserInfo.pwdquestion" CK_NAME="密码问题" CK_TYPE="NotEmpty" maxlength="50" type="text" value="" onblur="checkValue(this.value, 'rg5')" style="width:153px; height:24px; background:url(/public/images/register/z19.jpg) no-repeat; line-height:24px; padding-left:5px; border:none;" /></dd>
				<dd style="color:red;margin-left:-6px;margin-right:5px;">*</dd>
				<dd class="one" id="rg5-1">请输入密码保护问题以便找回密码！</dd>
				<dd class="two" id="rg5-2" style="display:none;">密码保护问题不能为空！</dd>
			</li>
			<li>
				<dt>问题答案：</dt>
				<dd style="width:163px;"><input name="sysUserInfo.pwdanswer" CK_NAME="问题答案" CK_TYPE="NotEmpty" maxlength="50" type="text" value="" onblur="checkValue(this.value, 'rg6')" style="width:153px; height:24px; background:url(/public/images/register/z19.jpg) no-repeat; line-height:24px; padding-left:5px; border:none;" />
				</dd>
				<dd style="color:red;margin-left:-6px;margin-right:5px;">*</dd>
				<dd class="one" id="rg6-1">请输入密码保护问题的答案！</dd>
				<dd class="two" id="rg6-2" style="display:none;">密码保护问题的答案不能为空！</dd>
			</li>
			<li>
				<dt>邮&nbsp;&nbsp;&nbsp;&nbsp;箱：</dt>
				<dd style="width:163px;"><input name="sysUserInfo.email" CK_NAME="邮箱" CK_TYPE="NotEmpty,EMail" maxlength="50" type="text" value="" onblur="checkValue(this.value, 'rg7')" style="width:153px; height:24px; background:url(/public/images/register/z19.jpg) no-repeat; line-height:24px; padding-left:5px; border:none;" />
				</dd>
				<dd style="color:red;margin-left:-6px;margin-right:5px;">*</dd>
				<dd class="one" id="rg7-1">请输入您的有效邮箱以便接收信息！</dd>
				<dd class="two" id="rg7-2" style="display:none;">邮箱不能为空或格式输入错误！</dd>
			</li>
			<li>
				<dt>验&nbsp;证&nbsp;码：</dt>
				<dd style="width:223px;"><input name="validate" id="validate" CK_NAME="验证码" CK_TYPE="NotEmpty" type="text" value="" onblur="checkValue(this.value, 'rg8')" maxlength="4" style="float:left; width:153px; height:24px; background:url(/public/images/register/z19.jpg) no-repeat; line-height:24px; padding-left:5px; border:none;" /><span style="float:left;margin-left:8px;"><img src="/sys/admin/code.jsp" width="50" height="23" /></span>
				</dd>
				<dd style="color:red;margin-left:-6px;margin-right:5px;">*</dd>
				<dd class="one" id="rg8-1" style="width:140px;">请输入右侧的验证码！</dd>
				<dd class="two" id="rg8-2" style="width:140px;display:none;">验证码输入错误！</dd>
			</li>
			
		</ul>
		</div><!--#list02-->
	
		<div id="list03">
		<h3 style="background:url(/public/images/register/z10.jpg) no-repeat 10px 1px;">扩展信息</h3>
		<ul>
			<li>
				<dt>性&nbsp;&nbsp;&nbsp;&nbsp;别：</dt>
				<dd style="width:163px; color:#666666;">
				 <input type="radio" name="sysUserInfo.sex" value="0" checked="checked"/>男&nbsp;<input type="radio" name="sysUserInfo.sex" value="1" />女</dd>
			</li>
			<li>
				<dt>出生日期：</dt>
				<dd style="width:163px;"><input name="sysUserInfo.birthday" type="text" value="" CK_NAME="出生日期" CK_TYPE="NotEmpty" style="width:153px; height:24px; background:url(/public/images/register/z19.jpg) no-repeat; line-height:24px; padding-left:5px; border:none;" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
				</dd>
				<dd style="color:red;margin-left:-6px;margin-right:5px;">*</dd>
				<dd class="one" id="rg9-1">请输入您的出生年月日！</dd>
				<dd class="two" id="rg9-2" style="display:none;">出生日期不能为空！</dd>
			</li>
			<li>
				<dt>联系电话：</dt>
				<dd style="width:163px;"><input name="sysUserInfo.telephone" CK_NAME="联系电话" CK_TYPE="NotEmpty,Telphone" maxlength="12" type="text" value="" onblur="checkValue(this.value, 'rg10')" style="width:153px; height:24px; background:url(/public/images/register/z19.jpg) no-repeat; line-height:24px; padding-left:5px; border:none;" />
				</dd>
				<dd style="color:red;margin-left:-6px;margin-right:5px;">*</dd>
				<dd class="one" id="rg10-1">请输入您的联系电话！</dd>
				<dd class="two" id="rg10-2" style="display:none;">联系电话不能为空或格式输入错误！</dd>
			</li>
			<li>
				<dt>手&nbsp;机&nbsp;号：</dt>
				<dd style="width:163px;"><input name="sysUserInfo.mobile" CK_NAME="手机号" CK_TYPE="MobileNO" maxlength="11" type="text" value="" style="width:153px; height:24px; background:url(/public/images/register/z19.jpg) no-repeat; line-height:24px; padding-left:5px; border:none;" />
				</dd>
				<dd style="color:red;margin-left:-6px;margin-right:5px;">&nbsp;</dd>
				<dd class="one" id="rg11-1">请输入您的手机号！</dd>
			</li>
			<li>
				<dt>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;QQ：</dt>
				<dd style="width:163px;"><input name="sysUserInfo.qq" maxlength="15" type="text" value="" style="width:153px; height:24px; background:url(/public/images/register/z19.jpg) no-repeat; line-height:24px; padding-left:5px; border:none;" />
				</dd>
				<dd style="color:red;margin-left:-6px;margin-right:5px;">&nbsp;</dd>
				<dd class="one" id="rg11-1">请输入您的QQ号！</dd>
			</li>
			<li>
				<dt>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MSN：</dt>
				<dd style="width:163px;"><input name="sysUserInfo.msn" maxlength="60" type="text" value="" style="width:153px; height:24px; background:url(/public/images/register/z19.jpg) no-repeat; line-height:24px; padding-left:5px; border:none;" />
				</dd>
				<dd style="color:red;margin-left:-6px;margin-right:5px;">&nbsp;</dd>
				<dd class="one" id="rg11-1">请输入您的MSN号！</dd>
			</li>
			<li>
			<table width="100%" id="t_table">
			  <tr>
			    <td style="width:370px;color:#000;">
			     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			      学&nbsp;&nbsp;&nbsp;&nbsp;科：
			      <input name="subjectname1" id="subjectname1" readonly="readonly" onclick="selectSubject(0, 1)" type="text" value="" style="width:70px; height:24px; background:url(/public/images/register/z19.jpg) no-repeat; line-height:24px; padding-left:5px; border:none;" />
				  年&nbsp;&nbsp;&nbsp;&nbsp;级：
				  <input name="gradename1" id="gradename1" readonly="readonly" onclick="selectSubject(1, 1)" type="text" value="" style="width:100px; height:24px; background:url(/public/images/register/z19.jpg) no-repeat; line-height:24px; padding-left:5px; border:none;" />
			    </td>
			    <td><INPUT style="float:left" onClick="addRow(this)" readonly type="button" value="添加" name="btnselect"></td>
			  </tr>
			</table>
			</li>
			<li>
				<dt>民&nbsp;&nbsp;&nbsp;&nbsp;族：</dt>
				<dd>
				  <java2code:option name="sysUserInfo.nation" codetype="nation"></java2code:option>
				</dd>
				<dd style="color:red;margin-left:2px;">*</dd>
			</li>
			<li>
				<dt>籍&nbsp;&nbsp;&nbsp;&nbsp;贯：</dt>
				<dd>
				  <select name="sysUserInfo.nativeplace1" id="sysUserInfo.nativeplace1" onchange="selectArea(this.value, '0')">
				  	<option value="">请选择..</option>
				  <%
				  	List arealist = (List)request.getAttribute("arealist");
				  	for(int i=0; i<arealist.size(); i++){
				  		GpwAreaInfo areaInfo = (GpwAreaInfo)arealist.get(i);
				  %>
				  	<option value="<%=areaInfo.getAreano() %>"><%=areaInfo.getAreaname() %></option>
				  <%} %>
				  </select>
				  </dd>
				  <dd id="sysUserInfo.nativeplace2">
					  <select name="sysUserInfo.nativeplace2">
						  <option value="">请选择..</option>
					  </select>
				  </dd>
				  <dd id="sysUserInfo.nativeplace3">
					  <select name="sysUserInfo.nativeplace3">
		         	 	 <option value="">请选择..</option>
		         	  </select>
				</dd>
				<dd style="color:red;margin-left:2px;">*</dd>
			</li>
			<li>
				<dt>邮&nbsp;&nbsp;&nbsp;&nbsp;编：</dt>
				<dd style="width:163px;"><input name="sysUserInfo.postcode" CK_NAME="邮编" CK_TYPE="Postcode" maxlength="6" type="text" value="" style="width:153px; height:24px; background:url(/public/images/register/z19.jpg) no-repeat; line-height:24px; padding-left:5px; border:none;" />
				</dd>
				<dd style="color:red;margin-left:-6px;margin-right:5px;">&nbsp;</dd>
				<dd class="one">请输入您所在地的邮政编码！</dd>
				<dd class="two" style="display:none;">邮政编码格式输入错误！</dd>
			</li>
			<li>
				<dt>联系地址：</dt>
				<dd style="width:163px;"><input name="sysUserInfo.address" CK_NAME="联系地址" CK_TYPE="MaxLen_100" maxlength="100" type="text" value="" style="width:153px; height:24px; background:url(/public/images/register/z19.jpg) no-repeat; line-height:24px; padding-left:5px; border:none;" />
				</dd>
				<dd style="color:red;margin-left:-6px;margin-right:5px;">&nbsp;</dd>
				<dd class="one">请输入您现在的联系地址！</dd>
			</li>
			<li>
				<dt>所在学校：</dt>
				<logic:present name="unitInfo">
				<dd  class="show">
					<bean:write name="unitInfo" property="unitname"/>
				</dd>
				<input type="hidden" name="sysUserInfo.unitid" id="sysUserInfo.unitid" value="<bean:write name="unitInfo" property="unitid"/>"/>
				</logic:present>
			</li>
		</ul>
		</div><!--#list03-->
	
		<div id="list04">
		<h3 style="background:url(/public/images/register/z11.jpg) no-repeat 10px 1px;">服务条款</h3>
		<ul>
			<span style="width:600px; padding:0 34px; line-height:18px; padding-bottom:8px;">我已经阅读并同意锦州市第四中学(以下简称“锦州四中”)SSO的服务条款和隐私权政策。我对帐户的使用及锦州四中SSO对帐户信息的披露将接受锦州四中SSO服务条款的约束并遵守中华人民共和国法律。</span>
			<span style="width:600px; padding:0 34px;">
			  <textarea name="textarea2" style="width:600px; height:100px; font-size:12px; color:#666666; padding:10px; line-height: 20px;">一、遵守中华人民共和国有关法律、法规，承担一切因您的行为而直接或间接引起的法律责任。
二、注册帐号和发表文章请注意以下几条规定，如有违反，锦州四中管理员有权删除。
不得发布违反中华人民共和国宪法和法律、违反四项基本原则的言论；
不得发布煽动颠覆国家政权的言论；
不得发布暴力、色情、迷信的言论；
不得泄露国家和军事秘密；
请勿张贴未经公开报道、未经证实的消息；
请勿张贴宣传种族歧视、破坏民族团结的言论和消息；
请注意使用文明用语，请勿张贴侮辱、诽谤、谩骂他人的言论；
请勿张贴与主题无关的消息和言论；
未经锦州四中管理员同意，请勿张贴任何形式的广告；
请勿在帖子中（标题和内容）中加入各种奇形怪状的符号；
请勿张贴含有法律、法规禁止的其他内容；
每条贴子正文请勿超过5000字，太长文章可以分若干贴连续发布。
三、帐号注册注意事项：
请勿以党和国家领导人或其他名人的真实姓名、字号、艺名、笔名注册；
请勿以国家机构或其他机构的名称注册；
请勿注册和其他网友之名相近、相仿的名字；
请勿注册不文明、不健康之帐号；
请勿注册易产生歧义、引起他人误解之帐号；
请勿注册图形符号帐号。
四、发贴填写注意事项：
发贴内容规定与上贴规定一致，要积极健康；
发贴中不能超链接其他网站、文章和音乐；
发贴中链接图片不能超过一张；
五、请注意版权问题：	
凡转贴文章，应注明原始出处、作者姓名和时间，否则锦州四中版主和管理员有权删除；
转贴文章时请注意作者和原发表单位的版权声明，并承担由此产生的版权责任；
您在本论坛上发表的文字，锦州四中有权转载或者引用。
六、锦州四中拥有管理页面和帐号的一切权利。请网友服从管理，如对管理有意见请及时向锦州四中管理员反映。
七、锦州四中免责声明	
锦州四中作为建立在中华人民共和国境内的网站，拥护和遵守中华人民共和国一切法规和制度，由于网络发布信息的不可控性，除锦州四中注明之服务条款外，其他一切因使用其论坛而引致之任何意外、疏忽、合约毁坏、诽谤、版权或知识产权侵犯及其所造成的损失 ( 包括因下载而感染电脑病毒 ) ，锦州四中概不负责，亦不承担任何法律责任。
任何透过此网页而连接及得到之资讯，产品及服务，锦州四中概不负责，亦不负任何法律责任。		
锦州四中内容绝大多数都来自互联网，如果任何单位或个人认为锦州四中的内容可能涉嫌侵犯其合法权益，请及时向锦州四中反馈，锦州四中将会在收到反馈后，尽快移除被控侵权内容，但不承担任何相关法律责任和经济责任。
以上声明之解释权归锦州四中所有</textarea>
			</span>
			<span style="width:600px; padding:8px 34px 0px 34px;">
			<input type="checkbox" name="xieyi" id="xieyi" value="checkbox" checked="checked"/>&nbsp;我同意
			</span>
			<span style="width:668px; text-align:center;"><a style="cursor:pointer;" onclick="register()"><img src="/public/images/register/z12.jpg" border="0" /></a>
			<a style="cursor:pointer;" onclick="reset()"><img src="/public/images/register/z13.jpg" border="0" /></a>
			</span>
		</ul>
		</div><!--#list04-->
	
	
	</div><!--#list-->



	<div id="list_bottom" style=" float:left;">
	  <span style="border-bottom:#8bb8d5 1px solid; background-color:#FFFFFF; line-height:1px;*line-height:3px; float:left; width:740px;">&nbsp;
	  <span style="width:5px; position:absolute; left:0px; bottom:0px;*bottom:0px;"><img src="/public/images/register/z17.jpg" /></span>
	  <span style="width:5px; position:absolute; right:0px; bottom:0px;*bottom:0px;"><img src="/public/images/register/z18.jpg" /></span>
	  </span>  
	</div><!--#list_bottom-->
</div><!--#center-->
<input type="hidden" name="sysUserInfo.flag" value="<bean:write name="model" property="flag"/>"/>
<input type="hidden" name="rowcount" id="rowcount" value="1"/>

<input type="hidden" name="subjectid1" id="subjectid1" value=""/>
<input type="hidden" name="gradeid1" id="gradeid1" value=""/>
<input type="hidden" name="subjectid2" id="subjectid2" value=""/>
<input type="hidden" name="gradeid2" id="gradeid2" value=""/>
<input type="hidden" name="subjectid3" id="subjectid3" value=""/>
<input type="hidden" name="gradeid3" id="gradeid3" value=""/>
<input type="hidden" name="subjectid4" id="subjectid4" value=""/>
<input type="hidden" name="gradeid4" id="gradeid4" value=""/>
<input type="hidden" name="subjectid5" id="subjectid5" value=""/>
<input type="hidden" name="gradeid5" id="gradeid5" value=""/>
</html:form>
<%@ include file="/sys/comm/footer.jsp"%>
</body>
</html:html>
