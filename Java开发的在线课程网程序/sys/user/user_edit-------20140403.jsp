<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.bo.SysUserInfo"%>
<%@page import="com.bzt.gpw.bo.GpwAreaInfo"%>
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
  
  var flag = false;
  var nativeplace1 = document.getElementById('sysUserInfo.nativeplace1').value;
  if(nativeplace1 == '') flag = true;
  if(flag){
      alert("请选择籍贯!");
 	  return false;
  }
  
  document.getElementById("btnsave").disabled = true;
  obj.action="/sysUserInfoAction.do?method=<bean:write name="act"/>";
  obj.submit();
}

function uploadPhoto(){
  var retValue=showModalDialog('/sysImageUploadAction.do?method=uploadimage&savepath=user&pathtype=ID','缩略图',"dialogWidth:350px;dialogHeight:200px;scroll=no;border=thin;help=0;status=no");
  if(retValue!=null){
     if(retValue[0] != ''){
  	 	alert(retValue[0]);
  		return false;
  	 }
     document.sysUserInfoActionForm.topreview.src="/upload_dir/"+retValue[1];
     document.getElementById('sysUserInfo.photo').value="/upload_dir/"+retValue[1];
  }
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

function addRow(obj){
	var rowcount = document.getElementById('rowcount');
	if(rowcount.value == '5'){
		alert('选择<bean:write name="SC_szxysubjectname"/><bean:write name="SC_szxygradename"/>不能超过5个!');
		return false;
	}
	var newrowcount = parseInt(rowcount.value)+1;
	document.getElementById('rowcount').value = newrowcount;
	
	var table = document.getElementById("t_table");
    var row = table.insertRow(table.rows.length);
  	row.insertCell(row.cells.length).innerHTML='学&nbsp;&nbsp;&nbsp;&nbsp;科：&nbsp;<input name="subjectname'+newrowcount+'" id="subjectname'+newrowcount+'" readonly="readonly" onclick="selectSubject(0, '+newrowcount+')" type="text" value="" class=input style="width:70px;"/>&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;级：&nbsp;<input name="gradename'+newrowcount+'" id="gradename'+newrowcount+'" readonly="readonly" onclick="selectSubject(1, '+newrowcount+')" type="text" value="" class=input style="width:100px;"/>';
    row.insertCell(row.cells.length).innerHTML='<INPUT onClick="delRow(this)" readonly type="button" value="删除" name="btnselect">';
	row.cells[0].style.width="330";
	row.cells[0].style.color="#000";
	//row.cells[0].style["padding-left"]="42px";
}

function delRow(obj, currowcount){
	var row = obj.parentNode.parentNode;
	row.parentNode.removeChild(row);
	document.getElementById('rowcount').value = parseInt(document.getElementById('rowcount').value)-1
	document.getElementById('subjectid'+currowcount).value = '';
	document.getElementById('gradeid'+currowcount).value = '';
}

function selectSubject(flag, currowcount){
	var subjectid = document.getElementById('subjectid'+currowcount).value;
	if(flag == 1 && subjectid == ''){
		alert('请选择所属<bean:write name="SC_szxysubjectname"/>!');
		return false;
	}
	
	var objid = '0';
	if(flag == 1) objid = subjectid;
	
	var url = '/sysUserInfoAction.do?method=selectSubject&flag=' + flag + '&objid=' + objid;
	var retValue=showModalDialog(url,'选择<bean:write name="SC_szxysubjectname"/><bean:write name="SC_szxyversionname"/>',"dialogWidth:300px;dialogHeight:385px;scroll=auto;border=thin;help=0;status=no");
	if(retValue != null){
		if(flag == 0){
		  document.getElementById('subjectname'+currowcount).value = retValue[0];
		  document.getElementById('subjectid'+currowcount).value = retValue[1];
		  //修改时把子集去掉
		  document.getElementById('gradename'+currowcount).value = '';
		  document.getElementById('gradeid'+currowcount).value = '';
		}
		if(flag == 1){
		  document.getElementById('gradename'+currowcount).value = retValue[0];
		  document.getElementById('gradeid'+currowcount).value = retValue[1];
		}
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
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable" width="100%">
  <TR>
    <TD class="page_title">用户管理</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/sysUserInfoAction.do" method="post" >
       <TABLE width="450" align=center border="0">
          <tr id="trid" style="display:none;">
          	<td class="table_edit_right" width="65"></td>
          	<td class="table_edit_left"><font color="red">此登录名已被其他用户注册!</font></td>
          </tr>
          <tr>
            <td class="table_edit_right" width="65">登&nbsp;录&nbsp;名：</td>
            <td class="table_edit_left"><bean:write property="loginname" name="model"/></td>
            <td class="table_edit_left" width="120" rowspan="5" >
              <img src="<bean:write property="photo"  name="model"/>" alt="点击修改照片" width="90" height="120" border="1" id=topreview onclick="uploadPhoto()">
              <input type="hidden" id="sysUserInfo.photo" name="sysUserInfo.photo"  value='<bean:write property="photo"  name="model"/>'>
            </td>
           </tr>
          <tr>
            <td class="table_edit_right">真实姓名：</td>
            <td class="table_edit_left"><bean:write property="username" name="model"/></td>
          </tr>
           <tr>
            <td class="table_edit_right">性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
            <td class="table_edit_left">
              <java2code:option  name="sysUserInfo.sex" codetype="sex" property="sex"/>
            </td>
          </tr>
		  <tr>
            <td class="table_edit_right">出生日期：</td>
            <td class="table_edit_left" colspan="2">
              <input id="sysUserInfo.birthday" name="sysUserInfo.birthday"  readonly="readonly" type="text" size="11" class=input value="<bean:write property="birthday"  name="model"/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})">(日期格式：2008-08-08)&nbsp;  
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">QQ：</td>
            <td class="table_edit_left" colspan="2"><input id="sysUserInfo.qq" name="sysUserInfo.qq" type="text" size="25" class=input value='<bean:write property="qq"  name="model"/>'></td>
          </tr>
          <tr>
            <td class="table_edit_right">MSN：</td>
            <td class="table_edit_left" colspan="2"><input id="sysUserInfo.msn" name="sysUserInfo.msn" type="text" size="25" class=input value='<bean:write property="msn"  name="model"/>'></td>
          </tr>
          <tr>
            <td class="table_edit_right">邮件地址：</td>
            <td class="table_edit_left" colspan="2"><input id="sysUserInfo.email" name="sysUserInfo.email" CK_NAME="邮件地址" CK_TYPE="EMail" type="text" size="40" class=input value='<bean:write property="email"  name="model"/>'></td>
          </tr>
           <tr>
            <td class="table_edit_right">联系电话：</td>
            <td class="table_edit_left" colspan="2"><input id="sysUserInfo.telephone" name="sysUserInfo.telephone" type="text" size="25" class=input value='<bean:write property="telephone"  name="model"/>'></td>
          </tr>
          <tr>
            <td class="table_edit_right">手&nbsp;机&nbsp;号：</td>
            <td class="table_edit_left" colspan="2"><input id="sysUserInfo.mobile" name="sysUserInfo.mobile" type="text" size="25" class=input value='<bean:write property="mobile"  name="model"/>'></td>
          </tr>
           <logic:notPresent name="teachinglist" scope="request">
          <tr>
            <td colspan="3">
			<table width="100%" id="t_table">
			  <tr>
			    <td style="width:330px;color:#000;">
			      &nbsp;学&nbsp;&nbsp;&nbsp;&nbsp;科：&nbsp;<input name="subjectname1" id="subjectname1" readonly="readonly" onclick="selectSubject(0, 1)" type="text" value="" class=input style="width:70px;"/>
				  年&nbsp;&nbsp;&nbsp;&nbsp;级：&nbsp;<input name="gradename1" id="gradename1" readonly="readonly" onclick="selectSubject(1, 1)" CK_NAME="<bean:write name="SC_szxysubjectname"/><bean:write name="SC_szxygradename"/>" CK_TYPE="NotEmpty" type="text" value="" class=input style="width:100px;"/>*
			    </td>
			    <td><INPUT onClick="addRow(this)" readonly type="button" value="添加" name="btnselect"></td>
			  </tr>
			</table>
			</td>
		  </tr>
		  </logic:notPresent>
		  <!-- 隐藏域放这是供下面调用 -->
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
		  <logic:present name="teachinglist" scope="request">
          <tr>
            <td colspan="3">
			<table width="100%" id="t_table">
			<%
			  List teachinglist = (List)request.getAttribute("teachinglist");
			  if(teachinglist != null && teachinglist.size() > 0){
			  %>
			  <logic:iterate id="teaching" name="teachinglist" scope="request" indexId="ii">
			  <tr>
			    <td style="width:330px;color:#000;">
			      学&nbsp;&nbsp;&nbsp;&nbsp;科：&nbsp;<input name="subjectname<%=ii+1 %>" id="subjectname<%=ii+1 %>" readonly="readonly" onclick="selectSubject(0, <%=ii+1 %>)" type="text" value='<bean:write name="teaching" property="flags"/>' class=input style="width:70px;"/>
				  年&nbsp;&nbsp;&nbsp;&nbsp;级：&nbsp;<input name="gradename<%=ii+1 %>" id="gradename<%=ii+1 %>" readonly="readonly" onclick="selectSubject(1, <%=ii+1 %>)" <%if(ii == 0){ %>CK_NAME="<bean:write name="SC_szxysubjectname"/><bean:write name="SC_szxygradename"/>" CK_TYPE="NotEmpty"<%} %> type="text" value='<bean:write name="teaching" property="flago"/>' class=input style="width:100px;"/>*
			    </td>
			    <td><%if(ii == 0){ %><INPUT onClick="addRow(this)" readonly type="button" value="添加" name="btnselect"><%}else{ %><INPUT onClick="delRow(this, <%=ii+1 %>)" readonly type="button" value="删除" name="btnselect"><%} %></td>
			  </tr>
			  <script language=javascript>
			  	document.getElementById('subjectid<%=ii+1 %>').value = '<bean:write name="teaching" property="subjectid"/>';
			  	document.getElementById('gradeid<%=ii+1 %>').value = '<bean:write name="teaching" property="gradeid"/>';
			  </script>
			  </logic:iterate>
			  <%}else{ %>
			  <tr>
			    <td style="width:330px;color:#000;">
			      &nbsp;学&nbsp;&nbsp;&nbsp;&nbsp;科：&nbsp;<input name="subjectname1" id="subjectname1" readonly="readonly" onclick="selectSubject(0, 1)" type="text" value="" class=input style="width:70px;"/>
				  年&nbsp;&nbsp;&nbsp;&nbsp;级：&nbsp;<input name="gradename1" id="gradename1" readonly="readonly" onclick="selectSubject(1, 1)" CK_NAME="学科年级" CK_TYPE="NotEmpty" type="text" value="" class=input style="width:100px;"/>*
			    </td>
			    <td><INPUT onClick="addRow(this)" readonly type="button" value="添加" name="btnselect"></td>
			  </tr>
			  <%} %>
			</table>
			</td>
		  </tr>
		  </logic:present>
          <tr>
            <td class="table_edit_right">民&nbsp;&nbsp;&nbsp;&nbsp;族：</td>
            <td class="table_edit_left" colspan="2"><java2code:option name="sysUserInfo.nation" codetype="nation" property="nation"></java2code:option></td>
          </tr>
          <tr>
            <td class="table_edit_right">籍&nbsp;&nbsp;&nbsp;&nbsp;贯：</td>
            <td class="table_edit_left" colspan="2">
            <logic:equal value="addSave" name="act">
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
				  <span id="sysUserInfo.nativeplace2">
					  <select name="sysUserInfo.nativeplace2">
						  <option value="">请选择..</option>
					  </select>
				  </span>
				  <span id="sysUserInfo.nativeplace3">
					  <select name="sysUserInfo.nativeplace3">
		         	 	 <option value="">请选择..</option>
		         	  </select>
				</span>
			</logic:equal>
			<logic:equal value="updateSave" name="act">
			<select name="sysUserInfo.nativeplace1" id="sysUserInfo.nativeplace1" onchange="selectArea(this.value, '0')">
				  <%
				  	SysUserInfo sysUserInfo = (SysUserInfo)request.getAttribute("model");
				  	List arealist1 = (List)request.getAttribute("arealist1");
				  	for(int i=0; i<arealist1.size(); i++){
				  		GpwAreaInfo areaInfo = (GpwAreaInfo)arealist1.get(i);
				  %>
				  	<option value="<%=areaInfo.getAreano() %>" <%if(sysUserInfo.getNativeplace1().equals(areaInfo.getAreano())){ %>selected="selected"<%} %>><%=areaInfo.getAreaname() %></option>
				  <%} %>
				  </select>
				  <span id="sysUserInfo.nativeplace2">
					  <select name="sysUserInfo.nativeplace2" onchange="selectArea(this.value, '1')">
						  <%
						  	List arealist2 = (List)request.getAttribute("arealist2");
						  	for(int i=0; i<arealist2.size(); i++){
						  		GpwAreaInfo areaInfo = (GpwAreaInfo)arealist2.get(i);
						  %>
						  	<option value="<%=areaInfo.getAreano() %>" <%if(sysUserInfo.getNativeplace2().equals(areaInfo.getAreano())){ %>selected="selected"<%} %>><%=areaInfo.getAreaname() %></option>
						  <%} %>
					  </select>
				  </span>
				  <span id="sysUserInfo.nativeplace3">
					  <select name="sysUserInfo.nativeplace3">
		         	 	 <%
						  	List arealist3 = (List)request.getAttribute("arealist3");
						  	for(int i=0; i<arealist3.size(); i++){
						  		GpwAreaInfo areaInfo = (GpwAreaInfo)arealist3.get(i);
						  %>
						  	<option value="<%=areaInfo.getAreano() %>" <%if(sysUserInfo.getNativeplace3().equals(areaInfo.getAreano())){ %>selected="selected"<%} %>><%=areaInfo.getAreaname() %></option>
						  <%} %>
		         	  </select>
				</span>
			</logic:equal>
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">邮&nbsp;&nbsp;&nbsp;&nbsp;编：</td>
            <td class="table_edit_left" colspan="2"><input id="sysUserInfo.postcode" name="sysUserInfo.postcode" CK_NAME="邮编" CK_TYPE="Postcode" type="text" size="6" maxlength="6" class="input" value='<bean:write property="postcode"  name="model"/>'></td>
          </tr>
           <tr>
            <td class="table_edit_right">联系地址：</td>
            <td class="table_edit_left" colspan="2"><input id="sysUserInfo.address" name="sysUserInfo.address" type="text" size="50" class="input" value='<bean:write property="address"  name="model"/>'></td>
          </tr>
          <!-- 
           <tr>
            <td class="table_edit_right">状&nbsp;&nbsp;&nbsp;&nbsp;态：</td>
            <td class="table_edit_left" colspan="2">
              <java2code:option  name="sysUserInfo.state" codetype="state" property="state"/>
             </td>
          </tr>
           -->
          <tr>
            <td class="table_edit_right">个人简介：</td>
            <td class="table_edit_left" colspan="2">
              <textarea rows="5" id="sysUserInfo.descript" name="sysUserInfo.descript" class="inputtextarea" cols="50" wrap="physical"><bean:write property="descript"  name="model"/></textarea></td>
           </tr>
           <%-- 
           <tr>
            <td class="table_edit_right">网盘空间：</td>
            <td class="table_edit_left" colspan="2"><input id="sysUserInfo.networkDiskSize" name="sysUserInfo.networkDiskSize" type="text" size="10" CK_NAME="网盘空间" CK_TYPE="NotEmpty,Number" maxlength="10" class="input" value='<bean:write property="networkDiskSize"  name="model"/>'>M</td>
          </tr>
		  --%>
          <tr>
                <td colspan="3" align="center">
                  <input type="button" value="保存" id="btnsave" name="btnsave" class="btn_save" onClick="javascript:saveRecord()">
                  <input type="button" value="返回" name="btnreturn" class="btn_cancel" onClick="javascript:history.go(-1)">
                </td>
           </tr>
       </TABLE>
       
       <input type="hidden" name="sysUserInfo.userid" value='<bean:write property="userid"  name="model"/>'/>
       <input type="hidden" name="sysUserInfo.stno" value='<bean:write property="stno"  name="model"/>'/>
       <input type="hidden" name="sysUserInfo.createdate" value='<bean:write property="createdate" name="model"/>'/>
       <input type="hidden" name="sysUserInfo.password" value='<bean:write property="password"  name="model"/>' />
       <input type="hidden" name="sysUserInfo.state" value='<bean:write property="state"  name="model"/>' />
       <input type="hidden" name="sysUserInfo.unitid" id="sysUserInfo.unitid" value='<bean:write property="unitid"  name="model"/>' />
       
       <input type="hidden" name="sysUserInfo.identitycard" value='<bean:write property="identitycard"  name="model"/>' />
       <input type="hidden" name="sysUserInfo.nickname" value='<bean:write property="nickname"  name="model"/>' />
       <input type="hidden" name="sysUserInfo.pwdquestion" value='<bean:write property="pwdquestion"  name="model"/>' />
       <input type="hidden" name="sysUserInfo.pwdanswer" value='<bean:write property="pwdanswer"  name="model"/>' />
       <input type="hidden" name="sysUserInfo.temppass" value='<bean:write property="temppass"  name="model"/>' />
       <input type="hidden" name="sysUserInfo.uuid" value='<bean:write property="uuid"  name="model"/>' />
       <input type="hidden" name="sysUserInfo.usertype" value='<bean:write property="usertype"  name="model"/>' />
       
       <input type="hidden" name="sysUserInfo.loginname" value='<bean:write property="loginname"  name="model"/>' />
       <input type="hidden" name="sysUserInfo.username" value='<bean:write property="username"  name="model"/>' />
       <input type="hidden" name="sysUserInfo.networkDiskSize" value='<bean:write property="networkDiskSize"  name="model"/>' />
       
       <input type="hidden" name="loginname" value='<bean:write name="loginname"/>'/>
       <input type="hidden" name="username" value='<bean:write name="username"/>'/>
       <input type="hidden" name="sex" value='<bean:write name="sex"/>'/>
       <input type="hidden" name="startcount" value='<bean:write name="startcount"/>' />
       
       <input type="hidden" name="rowcount" id="rowcount" value="<bean:write name="rowcount"/>"/>
        </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
