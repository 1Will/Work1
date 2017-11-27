<%@page import="com.bzt.sys.util.Constants"%>
<%@page import="com.util.string.encode.Encode"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.bo.SysUserInfo"%>
<%@page import="com.bzt.gpw.bo.GpwAreaInfo"%>
<%@page import="com.bzt.vod.bo.VodFilmType"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<TITLE>管理员设置</TITLE>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript"  src="/public/DatePicker/WdatePicker.js"></Script>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>
<SCRIPT language=javascript>

var reFlag = false;

function saveRecord(){
  obj = document.all("sysUserInfoActionForm");
  if(autoCheckForm(obj)==false){
    return false;
  }
  if(document.getElementById('password').value!=document.getElementById('passwordagain').value){
    alert("两次输入的密码不一致，请重试!");
    return false;
  }
  
  if(reFlag){
 		document.all("sysUserInfo.loginname").focus();
 		return false;
 	} 

    var area1_obj=document.getElementById("areano1");
var index1 = area1_obj.selectedIndex;
var value1 = area1_obj.options[index1].value;

var area2_obj=document.getElementById("areano2");
if(area2_obj =="" || typeof(area2_obj) == "undefined" || area2_obj == null){
    value2="";
}else {
    var index2 = area2_obj.selectedIndex;
    var value2 = area2_obj.options[index2].value;
}
var area3_obj=document.getElementById("areano3");
if(area3_obj =="" || typeof(area3_obj) == "undefined" || area3_obj == null){
    value3="";
}else {
    var index3 = area3_obj.selectedIndex;
    var value3 = area3_obj.options[index3].value;
}

if(value3.length != 0){
    document.getElementById("sysUserInfo.nativeplace1").value=value3;
}else {
    if(value2.length != 0){
        document.getElementById("sysUserInfo.nativeplace1").value=value2;
    }else {
        document.getElementById("sysUserInfo.nativeplace1").value=value1;
    }
}
    	
  document.getElementById("btnsave").disabled = true;
  document.getElementById("sysUserInfo.htmlcontent").value = document.getElementById("editor0").contentWindow.getHTML();
  obj.action="/sysUserInfoAction.do?method=<bean:write name="act"/>";
  obj.submit();
}

function uploadPhoto(){
	var diag = new top.Dialog();
	diag.Title = "上传图片";
	diag.URL = '/sysImageUploadAction.do?method=uploadimageframe&savepath=user&pathtype=ID';
	diag.Width = 350;
	diag.Height = 180;
	diag.CancelEvent = function(){
		if(diag.innerFrame.contentWindow.document.getElementById('uploadimageurl')){
			var uploadtip = diag.innerFrame.contentWindow.document.getElementById('uploadtip').value;
			if(uploadtip != ''){
		  	 	alert(uploadtip);
		  		return false;
		  	}else{
		  		var uploadimageurl = diag.innerFrame.contentWindow.document.getElementById('uploadimageurl').value;
				document.sysUserInfoActionForm.topreview.src="/upload_dir/"+uploadimageurl;
			    document.getElementById('sysUserInfo.photo').value="/upload_dir/"+uploadimageurl;
		  	}
		}
		diag.close();
	};
	diag.show();
}

function checkCode(value){
  if(value != "" && value != "<bean:write property="loginname"  name="model"/>"){
   var url = "/sysUserInfoAction.do?method=checkLoginname&loginname="+value+"&ram=" + Math.random();
   var myAjax = new Ajax.Request(url,{method: 'get',parameters: '',onComplete: showResponse});
  }
}

function showResponse(originalRequest)
{
  var value = originalRequest.responseText;
  if(value == '1'){
    reFlag = true;
    document.getElementById("trid").style.display="";
  }else{
    reFlag = false;
    document.getElementById("trid").style.display="none";
  }
}

function selectArea(areano, tag){
  if(areano != ''){
  new Ajax.Request(
	"/sysUserInfoAction.do?method=selectArea&areano=" + areano + "&tag=" + tag + "&ram=" + Math.random(),
	{
	method:"get",
	asynchronous:false,//true为异步请求
	onComplete:function(xhr){
		var responseObj = xhr.responseText;
		if(tag == '0'){
			var str = responseObj.split(";");
			document.getElementById('sysUserInfo.areano2').innerHTML = str[0];
			document.getElementById('sysUserInfo.areano3').innerHTML = str[1];
		}else{
			document.getElementById('sysUserInfo.areano3').innerHTML = responseObj;
		}
	}
	}
  );
  }
}
function applyVip(userid){
	window.location.href = "/sysVipCardAction.do?method=applyVip&userid="+userid;
}

function addRow(obj){
	var rowcount = document.getElementById('rowcount');
	if(rowcount.value == '10'){
		alert('选择学科年级版本不能超过10个!');
		return false;
	}
	var currowcount = parseInt(rowcount.value);
	if(currowcount == 0){
		currowcount = 1;
	}
	var newrowcount = currowcount+1;
	document.getElementById('rowcount').value = newrowcount;
	
	var table = document.getElementById("t_table");
    var row = table.insertRow(table.rows.length);
  	row.insertCell(row.cells.length).innerHTML='&nbsp;&nbsp;&nbsp;&nbsp;学科：&nbsp;<input name="subjectname'+newrowcount+'" id="subjectname'+newrowcount+'" readonly="readonly" onclick="selectUserSubject('+newrowcount+')" type="text" value="" class=input style="width:70px;"/>&nbsp;&nbsp;年级：&nbsp;<input name="gradename'+newrowcount+'" id="gradename'+newrowcount+'" readonly="readonly" onclick="selectUserGrade('+newrowcount+')" type="text" value="" class=input style="width:80px;"/>&nbsp;&nbsp;版本：&nbsp;<input name="versionname'+newrowcount+'" id="versionname'+newrowcount+'" readonly="readonly" onclick="selectUserVersion('+newrowcount+')" type="text" value="" class=input style="width:100px;"/>';
    row.insertCell(row.cells.length).innerHTML='<INPUT onClick="delRow(this)" readonly type="button" value="删除" name="btnselect">';
	row.cells[0].style.width="430";
	row.cells[0].style.color="#000";
	//row.cells[0].style["padding-left"]="42px";
}
function delRow(obj, currowcount){
	var row = obj.parentNode.parentNode;
	row.parentNode.removeChild(row);
	document.getElementById('rowcount').value = parseInt(document.getElementById('rowcount').value)-1
	document.getElementById('subjectid'+currowcount).value = '';
	document.getElementById('gradeid'+currowcount).value = '';
	document.getElementById('versionid'+currowcount).value = '';
}
function delData(){
	document.getElementById('subjectid1').value = "";
	document.getElementById('subjectname1').value = "";
	document.getElementById('gradeid1').value = "";
	document.getElementById('gradename1').value = "";
	document.getElementById('versionid1').value = "";
	document.getElementById('versionname1').value = "";
}
</SCRIPT>
<%@ include file="/edu/select/select_js.jsp"%>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<%SysUserInfo sysUserInfo = (SysUserInfo)request.getAttribute("model"); %>
<TABLE class="page_maintable" width="100%">
  <TR>
    <TD class="page_title">用户管理</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/sysUserInfoAction.do" method="post" >
       <TABLE width="650" align=center border="0">
       <logic:equal value="addSave" name="act">
          <tr id="trid" style="display:none;">
          	<td class="table_edit_right" width="65"></td>
          	<td class="table_edit_left"><font color="red">此登录名已被其他用户注册!</font></td>
          </tr>
          <tr>
            <td class="table_edit_right" width="65">登&nbsp;录&nbsp;名：</td>
            <td class="table_edit_left"> <input type="text" CK_NAME="登录名" CK_TYPE="NotEmpty,MaxLen_25" onblur="checkCode(this.value)" <logic:equal value="updateSave" name="act">readonly="readonly"</logic:equal> id="sysUserInfo.loginname" name="sysUserInfo.loginname" size="25" class=input value='<bean:write property="loginname"  name="model"/>'>&nbsp;*</td>
           </tr>
       </logic:equal>
       <logic:equal value="updateSave" name="act">
           <tr>
            <td class="table_edit_right" width="65">登录名：</td>
            <td class="table_edit_left">
            <input type="hidden" name="sysUserInfo.loginname" value='<bean:write property="loginname" name="model"/>'/>
            <bean:write property="loginname"  name="model"/>
            <!-- 
            <logic:equal value="0" name="model" property="temppass">&nbsp;&nbsp;<INPUT class="btn_allrevocation" onclick="applyVip('<bean:write property="userid" name="model"/>')" type="button" value="申请VIP" name="btndel"></logic:equal>
            <logic:notEqual value="0" name="model" property="temppass">&nbsp;&nbsp;<INPUT class="btn_recommend" style="font-weight:bold;" type="button" value="VIP" name="btndel"></logic:notEqual>
             -->
            </td>
           </tr>
       </logic:equal>
           <!-- 
           <tr>
            <td class="table_edit_right">IM账号：</td>
            <td class="table_edit_left"> <bean:write property="stno"  name="model"/></td>
          </tr>
           -->
          <tr>
            <td class="table_edit_right">真实姓名：</td>
            <td class="table_edit_left"> <input type="text" CK_NAME="真实姓名" CK_TYPE="NotEmpty,MaxLen_25" id="sysUserInfo.username" name="sysUserInfo.username" size="25" class=input value='<bean:write property="username"  name="model"/>'>&nbsp;*</td>
          </tr>
          <tr>
            <td class="table_edit_right" width="65">头像：</td>
            <td class="table_edit_left" width="120">
              <img src="<bean:write property="photo"  name="model"/>" alt="点击修改照片" width="200" height="130" border="1" id=topreview onclick="uploadPhoto()">
              <input type="hidden" id="sysUserInfo.photo" name="sysUserInfo.photo"  value='<bean:write property="photo"  name="model"/>'>
            </td>
           </tr>
		   <logic:notEqual value="addSave" name="act">
           <tr>
            <td class="table_edit_right">用户密码：</td>
            <td class="table_edit_left"> <input type="password" CK_NAME="用户密码" CK_TYPE="MaxLen_25" id="password" name="password" size="25" class=input value=''>&nbsp;</td>
          </tr>
          <tr>
            <td class="table_edit_right">确认密码：</td>
            <td class="table_edit_left"> <input type="password" CK_NAME="确认密码" CK_TYPE="MaxLen_25" id="passwordagain" name="passwordagain" size="25" class=input value=''>&nbsp;</td>
          </tr>
          <tr>
            <td class="table_edit_right">&nbsp;</td>
            <td class="table_edit_left">(<font color="red">如果不修改,请保持用户密码和确认密码为空)</font>&nbsp;&nbsp;</td>
           </tr>
          </logic:notEqual>
          <logic:equal value="addSave" name="act">
          <tr>
            <td class="table_edit_right">用户密码：</td>
            <td class="table_edit_left"> <input type="password" CK_NAME="用户密码" CK_TYPE="NotEmpty,MaxLen_25" id="password" name="password" size="25" class=input value=''>&nbsp;*</td>
          </tr>
          <tr>
            <td class="table_edit_right">确认密码：</td>
            <td class="table_edit_left"> <input type="password" CK_NAME="确认密码" CK_TYPE="NotEmpty,MaxLen_25" id="passwordagain" name="passwordagain" size="25" class=input value=''>&nbsp;*</td>
          </tr>
          <tr>
            <td align="right" >&nbsp;&nbsp;&nbsp;&nbsp;</td>
           </tr>
          </logic:equal>
          <tr>
            <td class="table_edit_right">用户类型：</td>
            <td class="table_edit_left">
              <select name="sysUserInfo.usertype">
              	<!-- <option value="0">系统用户</option> -->
              	<option value="5" <logic:equal value="5" name="model" property="usertype">selected="selected"</logic:equal>>微课教师</option>
              	<option value="6" <logic:equal value="6" name="model" property="usertype">selected="selected"</logic:equal>>微课专家</option>
              	<option value="7" <logic:equal value="7" name="model" property="usertype">selected="selected"</logic:equal>>微课大使</option>
              	<option value="2" <logic:equal value="2" name="model" property="usertype">selected="selected"</logic:equal>>微课学生</option>
              </select>
            </td>
          </tr>
           <tr>
            <td class="table_edit_right">性别：</td>
            <td class="table_edit_left">
              <java2code:option  name="sysUserInfo.sex" codetype="sex" property="sex"/>
            </td>
          </tr>
		  <tr>
            <td class="table_edit_right">出生日期：</td>
            <td class="table_edit_left">
              <input id="sysUserInfo.birthday" name="sysUserInfo.birthday"  readonly="readonly" type="text" size="11" class=input value="<bean:write property="birthday"  name="model"/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})">(日期格式：2008-08-08)&nbsp;  
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">职务：</td>
            <td class="table_edit_left"><input type="text" name="sysUserInfo.job" size="25" lenght="25" class=input value="<bean:write property="job"  name="model"/>"></td>
          </tr>
           <tr>
            <td class="table_edit_right">邮件地址：</td>
            <td class="table_edit_left"><input id="sysUserInfo.email" name="sysUserInfo.email" CK_NAME="邮件地址" CK_TYPE="EMail" type="text" size="40" class=input value='<bean:write property="email"  name="model"/>'></td>
          </tr>
          <tr>
            <td class="table_edit_right">QQ：</td>
            <td class="table_edit_left"><input id="sysUserInfo.qq" name="sysUserInfo.qq" type="text" size="25" class=input value='<bean:write property="qq"  name="model"/>'></td>
          </tr>
          <tr>
            <td class="table_edit_right">MSN：</td>
            <td class="table_edit_left"><input id="sysUserInfo.msn" name="sysUserInfo.msn" type="text" size="25" class=input value='<bean:write property="msn"  name="model"/>'></td>
          </tr>
           <tr>
            <td class="table_edit_right">联系电话：</td>
            <td class="table_edit_left"><input id="sysUserInfo.telephone" name="sysUserInfo.telephone" type="text" size="25" class=input value='<bean:write property="telephone"  name="model"/>'></td>
          </tr>
          <tr>
            <td class="table_edit_right">手机号：</td>
            <td class="table_edit_left"><input id="sysUserInfo.mobile" name="sysUserInfo.mobile" type="text" size="25" class=input value='<bean:write property="mobile"  name="model"/>'></td>
          </tr>
          <%-- 
          <tr>
            <td class="table_edit_right">积&nbsp;分：</td>
            <td class="table_edit_left"><input id="sysUserInfo.jifen" name="sysUserInfo.jifen" CK_NAME="积分" CK_TYPE="NotEmpty,Number" type="text" size="10" class=input value='<bean:write property="jifen"  name="model"/>' style="background-color:#ddd;" readonly="readonly">*</td>
          </tr>
          <tr>
            <td class="table_edit_right">财&nbsp;富：</td>
            <td class="table_edit_left"><input id="sysUserInfo.caifu" name="sysUserInfo.caifu" CK_NAME="财富" CK_TYPE="NotEmpty,Number" type="text" size="10" class=input value='<bean:write property="caifu"  name="model"/>' style="background-color:#ddd;" readonly="readonly">*</td>
          </tr>
          --%>
		  <logic:notEmpty name="unitid">
		  <tr>
            <td class="table_edit_right">推荐：</td>
            <td class="table_edit_left"><java2code:option name="sysUserInfo.recommand" codetype="boolean" property="recommand"></java2code:option></td>
          </tr>
          <tr>
            <td class="table_edit_right">推荐排序：</td>
            <td class="table_edit_left"><input type="text" name="sysUserInfo.recommandno" CK_NAME="推荐排序" CK_TYPE="NotEmpty,Number" size="6" lenght="6" maxlength="4" class=input value="<bean:write property="recommandno"  name="model"/>">&nbsp;*(总首页用户排序，越大越靠前)</td>
          </tr>
		  <%
		  if(Constants.getDefaultUnitid().equals(sysUserInfo.getUnitid())){
		  %>
		  <tr>
            <td class="table_edit_right">排序：</td>
            <td class="table_edit_left"><input type="text" name="sysUserInfo.orderindex" CK_NAME="排序" CK_TYPE="NotEmpty,Number" size="6" lenght="6" maxlength="4" class=input value="<bean:write property="orderindex"  name="model"/>">&nbsp;*(学校首页用户排序，越大越靠前)</td>
          </tr>
          <%}else{ %>
          <input type="hidden" name="sysUserInfo.orderindex" value="<bean:write property="orderindex" name="model"/>">
          <%} %>
          </logic:notEmpty>
          <logic:empty name="unitid">
          <tr>
            <td class="table_edit_right">排序：</td>
            <td class="table_edit_left">
            <input type="text" name="sysUserInfo.orderindex" CK_NAME="排序" CK_TYPE="NotEmpty,Number" size="6" lenght="6" maxlength="4" class=input value="<bean:write property="orderindex"  name="model"/>">&nbsp;*(学校首页用户排序，越大越靠前)
            <input type="hidden" name="sysUserInfo.recommand" value='<bean:write property="recommand"  name="model"/>'/>
            <input type="hidden" name="sysUserInfo.recommandno" value='<bean:write property="recommandno"  name="model"/>'/>
            </td>
          </tr>
          </logic:empty>
          <tr>
            <td class="table_edit_right">学校领导：</td>
            <td class="table_edit_left"><java2code:option name="sysUserInfo.leader" codetype="leader" property="leader"></java2code:option></td>
          </tr>
          <tr>
            <td class="table_edit_right">民族：</td>
            <td class="table_edit_left"><java2code:option name="sysUserInfo.nation" codetype="nation" property="nation"></java2code:option></td>
          </tr>
          <tr>
            <td class="table_edit_right">籍贯：</td>
            <td class="table_edit_left">
			<span id="sysUserInfo.areano1" style="float:left;">
				<select id="areano1" name="areano1" onchange="selectArea(this.value, '0')" style="padding:3px;">
				  <%
                    
				  	String areano = "";
				  	if(sysUserInfo.getNativeplace1() != null) areano = sysUserInfo.getNativeplace1();
				  	List arealist1 = (List)request.getAttribute("arealist1");
				    GpwAreaInfo areaInfo = null;
				  	for(int i=0; i<arealist1.size(); i++){
				  		areaInfo = (GpwAreaInfo)arealist1.get(i);
				  %>
				  	<option value="<%=areaInfo.getRgno() %>" <%if(areano.substring(0, 2).equals(areaInfo.getRgno().substring(0, 2))){ %>selected="selected"<%} %>><%=areaInfo.getAreaname() %></option>
				  <%} %>
				  </select>
				  </span>
				  <span id="sysUserInfo.areano2" style="float:left;margin-left:8px;">
					  <select id="areano2" name="areano2" onchange="selectArea(this.value, '1')" style="padding:3px;">
						  <%
						  	List arealist2 = (List)request.getAttribute("arealist2");
						    List arealist3 = (List)request.getAttribute("arealist3");
						  	for(int i=0; i<arealist2.size(); i++){
						  		areaInfo = (GpwAreaInfo)arealist2.get(i);
						  %>
						    <%if(arealist3 != null && arealist3.size() > 0){ %>
						  	<option value="<%=areaInfo.getRgno() %>" <%if(areano.substring(0, 4).equals(areaInfo.getRgno().substring(0, 4))){ %>selected="selected"<%} %>><%=areaInfo.getAreaname() %></option>
						  	<%}else{ %>
						  	<option value="<%=areaInfo.getRgno() %>" <%if(areano.equals(areaInfo.getRgno())){ %>selected="selected"<%} %>><%=areaInfo.getAreaname() %></option>
						  	<%} %>
						  <%} %>
					  </select>
				  </span>
				  <span id="sysUserInfo.areano3" style="float:left;margin-left:8px;">
					  <%
				  	  if(arealist3 != null && arealist3.size() > 0){
				  	  %>
					  <select id="areano3" name="areano3" style="padding:3px;">
		         	 	 <%
						  	for(int i=0; i<arealist3.size(); i++){
						  		areaInfo = (GpwAreaInfo)arealist3.get(i);
						  %>
						  	<option value="<%=areaInfo.getRgno() %>" <%if(areano.equals(areaInfo.getRgno())){ %>selected="selected"<%} %>><%=areaInfo.getAreaname() %></option>
						  <%} %>
		         	  </select>
		         	  <%} %>
				</span>

            </td>
          </tr>
          <logic:equal value="2010" name="unitInfo" property="type">
          	<!-- 隐藏域供调用 -->
			<input type="hidden" name="subjectid1" id="subjectid1" value=""/>
		  	<input type="hidden" name="gradeid1" id="gradeid1" value=""/>
		  	<input type="hidden" name="versionid1" id="versionid1" value=""/>
		  	<input type="hidden" name="subjectid2" id="subjectid2" value=""/>
		  	<input type="hidden" name="gradeid2" id="gradeid2" value=""/>
		  	<input type="hidden" name="versionid2" id="versionid2" value=""/>
		  	<input type="hidden" name="subjectid3" id="subjectid3" value=""/>
		  	<input type="hidden" name="gradeid3" id="gradeid3" value=""/>
		  	<input type="hidden" name="versionid3" id="versionid3" value=""/>
		  	<input type="hidden" name="subjectid4" id="subjectid4" value=""/>
		  	<input type="hidden" name="gradeid4" id="gradeid4" value=""/>
		  	<input type="hidden" name="versionid4" id="versionid4" value=""/>
		  	<input type="hidden" name="subjectid5" id="subjectid5" value=""/>
		  	<input type="hidden" name="gradeid5" id="gradeid5" value=""/>
		  	<input type="hidden" name="versionid5" id="versionid5" value=""/>
		  	<input type="hidden" name="subjectid6" id="subjectid6" value=""/>
		  	<input type="hidden" name="gradeid6" id="gradeid6" value=""/>
		  	<input type="hidden" name="versionid6" id="versionid6" value=""/>
		  	<input type="hidden" name="subjectid7" id="subjectid7" value=""/>
		  	<input type="hidden" name="gradeid7" id="gradeid7" value=""/>
		  	<input type="hidden" name="versionid7" id="versionid7" value=""/>
		  	<input type="hidden" name="subjectid8" id="subjectid8" value=""/>
		  	<input type="hidden" name="gradeid8" id="gradeid8" value=""/>
		  	<input type="hidden" name="versionid8" id="versionid8" value=""/>
		  	<input type="hidden" name="subjectid9" id="subjectid9" value=""/>
		  	<input type="hidden" name="gradeid9" id="gradeid9" value=""/>
		  	<input type="hidden" name="versionid9" id="versionid9" value=""/>
		  	<input type="hidden" name="subjectid10" id="subjectid10" value=""/>
		  	<input type="hidden" name="gradeid10" id="gradeid10" value=""/>
		  	<input type="hidden" name="versionid10" id="versionid10" value=""/>
			<input type="hidden" name="rowcount" id="rowcount" value="${rowcount }"/>
          <tr>
            <td colspan="3">
			<table width="100%" id="t_table">
			<%
			  List teachinglist = (List)request.getAttribute("teachinglist");
			  if(teachinglist != null && teachinglist.size() > 0){
			  %>
			  <logic:iterate id="teaching" name="teachinglist" scope="request" indexId="ii">
			  <tr>
			    <td style="width:430px;color:#000;">
			      &nbsp;&nbsp;&nbsp;&nbsp;学科：&nbsp;<input name="subjectname<%=ii+1 %>" id="subjectname<%=ii+1 %>" readonly="readonly" onclick="selectUserSubject(<%=ii+1 %>)" type="text" value='<bean:write name="teaching" property="flags"/>' class=input style="width:70px;"/>
				  &nbsp;年级：&nbsp;<input name="gradename<%=ii+1 %>" id="gradename<%=ii+1 %>" readonly="readonly" onclick="selectUserGrade(<%=ii+1 %>)" type="text" value='<bean:write name="teaching" property="flago"/>' class=input style="width:80px;"/>
				  &nbsp;版本：&nbsp;<input name="versionname<%=ii+1 %>" id="versionname<%=ii+1 %>" readonly="readonly" onclick="selectUserVersion(<%=ii+1 %>)" type="text" value='<bean:write name="teaching" property="flag"/>' class=input style="width:100px;"/>
			    </td>
			    <td><%if(ii == 0){ %><INPUT onClick="addRow(this)" readonly type="button" value="添加" name="btnselect"><INPUT onClick="delData()" readonly type="button" value="清空" name="btnselect"><%}else{ %><INPUT onClick="delRow(this, <%=ii+1 %>)" readonly type="button" value="删除" name="btnselect"><%} %></td>
			  </tr>
			  <script language=javascript>
			  	document.getElementById('subjectid<%=ii+1 %>').value = '<bean:write name="teaching" property="subjectid"/>';
			  	document.getElementById('gradeid<%=ii+1 %>').value = '<bean:write name="teaching" property="gradeid"/>';
			  	document.getElementById('versionid<%=ii+1 %>').value = '<bean:write name="teaching" property="versionid"/>';
			  </script>
			  </logic:iterate>
			  <%}else{ %>
			  <tr>
			    <td style="width:430px;color:#000;">
			      &nbsp;&nbsp;&nbsp;&nbsp;学科：&nbsp;<input name="subjectname1" id="subjectname1" readonly="readonly" onclick="selectUserSubject(1)" type="text" value="" class=input style="width:70px;"/>
				  &nbsp;年级：&nbsp;<input name="gradename1" id="gradename1" readonly="readonly" onclick="selectUserGrade(1)" type="text" value="" class=input style="width:80px;"/>
				  &nbsp;版本：&nbsp;<input name="versionname1" id="versionname1" readonly="readonly" onclick="selectUserVersion(1)" type="text" value="" class=input style="width:100px;"/>
			    </td>
			    <td><INPUT onClick="addRow(this)" readonly type="button" value="添加" name="btnselect"><INPUT onClick="delData()" readonly type="button" value="清空" name="btnselect"></td>
			  </tr>
			  <%} %>
			</table>
			</td>
		  </tr>
		  <tr>
		    <td colspan="3" style="padding-left:42px;color:green;">说明：在‘上传微课’和‘上传文档’选择教材目录时，将会根据个人设置的教学设置（学科-年级-版本）自动关联，如果没有设置则显示当前单位所有学科年级版本。</td>
		  </tr>
		  </logic:equal>
          <tr>
            <td class="table_edit_right">邮编：</td>
            <td class="table_edit_left"><input id="sysUserInfo.postcode" name="sysUserInfo.postcode" CK_NAME="邮编" CK_TYPE="Postcode" type="text" size="6" maxlength="6" class="input" value='<bean:write property="postcode"  name="model"/>'></td>
          </tr>
           <tr>
            <td class="table_edit_right">联系地址：</td>
            <td class="table_edit_left"><input id="sysUserInfo.address" name="sysUserInfo.address" type="text" size="50" class="input" value='<bean:write property="address"  name="model"/>'></td>
          </tr>
           <tr>
            <td class="table_edit_right">状态：</td>
            <td class="table_edit_left">
              <select name="sysUserInfo.state">
              	<option value="1" <logic:equal value="1" name="model" property="state">selected="selected"</logic:equal>>正常</option>
              	<option value="2" <logic:equal value="2" name="model" property="state">selected="selected"</logic:equal>>禁用</option>
              </select>
             </td>
          </tr>
           <tr>
            <td class="table_edit_right">外部链接：</td>
            <td class="table_edit_left">
            	<input type="text" name="sysUserInfo.linkurl" size="50" class=input value="<bean:write property="linkurl"  name="model"/>">
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">&nbsp;</td>
            <td class="table_edit_left" style="color:green;">
            	说明："外部链接"可以输入教师的博客、微课或个人主页等地址的任何一种。
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">个人简介：</td>
            <td class="table_edit_left">
              <textarea style="width:550px;height:80px;" id="sysUserInfo.descript" name="sysUserInfo.descript" class="inputtextarea" cols="50" wrap="physical"><bean:write property="descript"  name="model"/></textarea></td>
           </tr>
           <tr>
            <td class="table_edit_right">详细描述：</td>
            <td class="table_edit_left">
               <IFRAME ID="editor0" src="/ewebeditor/ewebeditor.htm?id=sysUserInfo.htmlcontent&style=blue&cusdir=<%=Encode.nullToBlank(session.getAttribute("cusdir")) %>" frameborder="0" scrolling="no" width="550" height="300"></IFRAME>
               <input type="hidden" name="sysUserInfo.htmlcontent" id="sysUserInfo.htmlcontent" value="<%=Encode.convertQuot(Encode.nullToBlank(sysUserInfo.getHtmlcontent()))%>"/>
            </td>
          </tr>
           <!-- 
           <tr>
            <td class="table_edit_right">网盘空间：</td>
            <td class="table_edit_left"><input id="sysUserInfo.networkDiskSize" name="sysUserInfo.networkDiskSize" type="text" size="10" CK_NAME="网盘空间" CK_TYPE="NotEmpty,Number" maxlength="10" class="input" value='<bean:write property="networkDiskSize"  name="model"/>'>M</td>
          </tr>
		   -->
          <tr>
                <td colspan="3" align="center">
                  <input type="button" value="保存" id="btnsave" name="btnsave" class="btn_save" onClick="javascript:saveRecord()">
                  <input type="button" value="返回" name="btnreturn" class="btn_cancel" onClick="javascript:history.go(-1)">
                </td>
           </tr>
           <logic:present name="unitInfo">
           <tr>
            <td class="table_edit_right" style="font-weight:bold;">所在单位：</td>
            <td class="table_edit_left" style="background-color:#ccc;"><bean:write property="unitname"  name="unitInfo"/></td>
          </tr>
          </logic:present>
       </TABLE>
       
       <input type="hidden" name="sysUserInfo.userid" value='<bean:write property="userid"  name="model"/>'/>
       <input type="hidden" name="sysUserInfo.stno" value='<bean:write property="stno"  name="model"/>'/>
       <input type="hidden" name="sysUserInfo.createdate" value='<bean:write property="createdate" name="model"/>'/>
       <input type="hidden" name="sysUserInfo.password" value='<bean:write property="password"  name="model"/>' />
       <input type="hidden" name="sysUserInfo.unitid" id="sysUserInfo.unitid" value='<bean:write property="unitid"  name="model"/>' />
       
       <input type="hidden" name="sysUserInfo.identitycard" value='<bean:write property="identitycard"  name="model"/>' />
       <input type="hidden" name="sysUserInfo.nickname" value='<bean:write property="nickname"  name="model"/>' />
       <input type="hidden" name="sysUserInfo.pwdquestion" value='<bean:write property="pwdquestion"  name="model"/>' />
       <input type="hidden" name="sysUserInfo.pwdanswer" value='<bean:write property="pwdanswer"  name="model"/>' />
       <input type="hidden" name="sysUserInfo.temppass" value='<bean:write property="temppass"  name="model"/>' />
       <input type="hidden" name="sysUserInfo.uuid" value='<bean:write property="uuid"  name="model"/>' />
       <input type="hidden" name="sysUserInfo.flag" value='<bean:write property="flag"  name="model"/>' />
       <input type="hidden" name="sysUserInfo.networkDiskSize" value='<bean:write property="networkDiskSize"  name="model"/>' />
       <input type="hidden" id="sysUserInfo.nativeplace1" name="sysUserInfo.nativeplace1" value='<bean:write property="nativeplace1"  name="model"/>' />
       <input type="hidden" id="sysUserInfo.nativeplace2" name="sysUserInfo.nativeplace2" value='<bean:write property="nativeplace2"  name="model"/>' />
       <input type="hidden" id="sysUserInfo.nativeplace3" name="sysUserInfo.nativeplace3" value='<bean:write property="nativeplace3"  name="model"/>' />
       <input type="hidden" name="sysUserInfo.penid" value='<bean:write property="penid"  name="model"/>' />
       <input type="hidden" name="sysUserInfo.penname" value='<bean:write property="penname"  name="model"/>' />
       
       <input type="hidden" name="unitid" value='<bean:write name="unitid"/>'/>
       <input type="hidden" name="loginname" value='<bean:write name="loginname"/>'/>
       <input type="hidden" name="username" value='<bean:write name="username"/>'/>
       <input type="hidden" name="usertype" value='<bean:write name="usertype"/>'/>
       <input type="hidden" name="sex" value='<bean:write name="sex"/>'/>
       <input type="hidden" name="temppass" value='<bean:write name="temppass"/>'/>
       <input type="hidden" name="startcount" value='<bean:write name="startcount"/>' />
        </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
