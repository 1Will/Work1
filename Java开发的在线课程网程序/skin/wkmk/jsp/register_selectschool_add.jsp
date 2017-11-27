<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.gpw.bo.GpwAreaInfo"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>创建学校</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function $$get(obj){
	return document.getElementById(obj);
}

var utemp,stemp,atemp;
var htemp=true;
function addApply(){
	if(!utemp){
		checkValue($$get("unitname").value, 'unitname');
	}
	if(!stemp){
		checkValue($$get("shortname").value, 'shortname');
	}
	if(!atemp){
		checkValue($$get("address").value, 'address');
	}
	if(utemp && stemp && atemp && htemp){
		var utype = $$get('utype').value;
	    if(utype == '2010'){
	        if(!($$get('schooltype2010_1').checked || $$get('schooltype2010_2').checked || $$get('schooltype2010_3').checked)){
	            alert("请选择申请学校的学校性质!");
	            return false;
	        }
	    }
	    if(utype == '2020'){
	        if(!($$get('schooltype2020_1').checked || $$get('schooltype2020_2').checked)){
	            alert("请选择申请学校的学校性质!");
	            return false;
	        }
	    }
		document.registerForm.action = '/v.bo?method=selectSchoolAddDeal';
		document.registerForm.submit();
	}
}
function checkValue(value, tag){
	if(tag == 'unitname'){
		if(value == ""){
			$$get(tag + "_tip").className = 'explain0';
			utemp = false;
		}else{
			$$get(tag + "_tip").innerHTML = "学校名称可用！";
			$$get(tag + "_tip").className = 'explain1';
			utemp = true;
		}
	}
	if(tag == 'shortname'){
		if(value == ""){
			$$get(tag + "_tip").className = 'explain0';
			stemp = false;
		}else{
			$$get(tag + "_tip").innerHTML = "学校简称可用！";
			$$get(tag + "_tip").className = 'explain1';
			stemp = true;
		}
	}
	/*
	if(tag == 'homepage'){
		if(value == ""){
			$$get(tag + "_tip").className = 'explain0';
			htemp = false;
		}else{
			$$get(tag + "_tip").innerHTML = "学校网址可用！";
			$$get(tag + "_tip").className = 'explain1';
			htemp = true;
		}
	}
	*/
	if(tag == 'address'){
		if(value == ""){
			$$get(tag + "_tip").className = 'explain0';
			atemp = false;
		}else{
			$$get(tag + "_tip").innerHTML = "联系地址可用！";
			$$get(tag + "_tip").className = 'explain1';
			atemp = true;
		}
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
function changeSchoolType(unittype){
    document.getElementById("utype").value = unittype;
    if(unittype == '2010'){
        document.getElementById("schooltype_2010").style.display = "inline";
        document.getElementById("schooltype_2020").style.display = "none";
    }else if(unittype == '2020'){
        document.getElementById("schooltype_2010").style.display = "none";
        document.getElementById("schooltype_2020").style.display = "inline";
    }else{
        document.getElementById("schooltype_2010").style.display = "none";
        document.getElementById("schooltype_2020").style.display = "none";
    }
}
function returnBack(){
    document.registerForm.action='v.bo?method=selectSchoolList';
    document.registerForm.submit();
}
</script>
</head>

<body>
  <form name="registerForm" method="post">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <th height="80"align="center" valign="middle" style="font-weight:normal;font-size:24px;">创建学校</th>
    </tr>
  <tr>
    <td width="62%" height="50" valign="middle" class="pad_1">
      <span class="color_8 float1 mar_8">*</span>
      <label class="w100 mar_8">学校名称</label>
      <input type="text" value="" name="unitname" id="unitname" class="reg_input" onblur="checkValue(this.value, 'unitname')"/>
      <span class="explain" id="unitname_tip">请填包含省市区完整的全称，如：山东省潍坊市昌乐县昌乐一中</span>
    </td>
  </tr>
  <tr>
    <td height="50" class="pad_1">
      <span class="color_8 float1 mar_8">*</span>
      <label class="w100 mar_8">学校简称</label>
      <input type="text" value="" name="shortname" id="shortname" class="reg_input" onblur="checkValue(this.value, 'shortname')"/>
      <span class="explain" id="shortname_tip">请填写学校简称，如：昌乐一中</span>
    </td>
  </tr>
  <tr>
    <td height="50" class="pad_1">
      <span class="color_8 float1 mar_8">&nbsp;</span>
      <label class="w100 mar_8">学校网站</label>
      <input type="text" value="" name="homepage" id="homepage" class="reg_input" onblur="checkValue(this.value, 'homepage')"/>
      <span class="explain" id="homepage_tip">如：http://www.wkmk.cn</span>
      </td>
  </tr>
  <tr>
    <td height="50" class="pad_1">
      <span class="color_8 float1 mar_8">&nbsp;</span>
      <label class="w100 mar_8">计划上传<br />微课数</label>
      <input type="text" value="" name="wkuploads" class="reg_input" maxlength="5" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
  </tr>
  <tr>
    <td height="50" class="pad_1">
      <span class="color_8 float1 mar_8">&nbsp;</span>
      <label class="w100 mar_8">微课建设<br />情况</label>
      <input type="text" value="" name="wkconstruction" class="reg_input" maxlength="100"/></td>
  </tr>
  <tr>
    <td height="50" class="pad_1">
      <span class="color_8 float1 mar_8">&nbsp;</span>
      <label class="w100 mar_8">学校类型</label>
      <span class="radio_m"><input name="unittype" type="radio" value="2010" class="mar_3" checked="checked" onclick="changeSchoolType('2010')"/>基础教育</span>
      <span class="radio_m"><input name="unittype" type="radio" value="2020" class="mar_3" onclick="changeSchoolType('2020')"/>职业教育</span>
      <span class="radio_m"><input name="unittype" type="radio" value="2030" class="mar_3" onclick="changeSchoolType('2030')"/>高等教育</span>
      <span class="radio_m"><input name="unittype" type="radio" value="2040" class="mar_3" onclick="changeSchoolType('2040')"/>继续教育</span>
    </td>
  </tr>
  <tr id="schooltype_2010">
    <td height="50" class="pad_1">
      <span class="color_8 float1 mar_8">&nbsp;</span>
      <label class="w100 mar_8">学校性质</label>
      <span class="radio_m"><input name="schooltype2010" type="checkbox" value="1" id="schooltype2010_1" class="mar_3" />小学</span>
      <span class="radio_m"><input name="schooltype2010" type="checkbox" value="2" id="schooltype2010_2" class="mar_3" />初中</span>
      <span class="radio_m"><input name="schooltype2010" type="checkbox" value="3" id="schooltype2010_3" class="mar_3" />高中</span>
    </td>
  </tr>
  <tr id="schooltype_2020" style="display:none;">
    <td height="50" class="pad_1">
      <span class="color_8 float1 mar_8">&nbsp;</span>
      <label class="w100 mar_8">学校性质</label>
      <span class="radio_m"><input name="schooltype2020" type="checkbox" value="1" id="schooltype2020_1" class="mar_3" />中职</span>
      <span class="radio_m"><input name="schooltype2020" type="checkbox" value="2" id="schooltype2020_2" class="mar_3" />高职</span>
    </td>
  </tr>
  <tr>
     <td height="50" class="pad_1">
      <span class="color_8 float1 mar_8">*</span>
      <label class="w100 mar_8">所属地区</label>
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
      <label class="w100 mar_8">联系地址</label>
      <input type="email" value="" name="address" id="address" class="reg_input" onblur="checkValue(this.value, 'address')"/>
      <span class="explain" id="address_tip">请填写学校详细地址，包含省市区街道门牌号</span>
    </td>
  </tr>
  <tr>
    <td height="50">
      <span class="color_8 float1 mar_8">&nbsp;</span>
      <label class="w100 mar_8" style="width:123px;"></label>
      <input name="product" type="checkbox" value="1" />
      <span class="color_7">学校是否由意向建立“微课平台”来发布和管理学校自己的微课</span>
    </td>
  </tr>
  <tr>
    <td><input type="button" value="立即创建" class="sub_re" onClick="addApply()"/><input type="button" value="返回" class="sub_re" onClick="returnBack()"/></td>
  </tr>
 </table> 
 <input type="hidden" name="utype" id="utype" value="2010"/>
 <input type="hidden" name="unittype" value="<bean:write name="unittype"/>"/>
 <input type="hidden" name="unitname" value="<bean:write name="unitname"/>"/>
 <input type="hidden" name="startcount" value="<bean:write name="startcount"/>"/>
 </form>
</body>
</html>