<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.bzt.gpw.bo.GpwAreaInfo"%>
<%@page import="java.util.List"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<TITLE>在线完善个人信息</TITLE>
<%@ include file="/public/jsp/style.jsp"%>
<%@ include file="/public/jsp/meta.jsp"%>
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
  
  document.getElementById("btnsave").disabled = true;
  obj.action="/initSite.do?method=saveInitUser";
  obj.submit();
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
	
	var url = '/initSite.do?method=selectSubject&flag=' + flag + '&objid=' + objid;
	var retValue=showModalDialog(url,'选择<bean:write name="SC_szxysubjectname"/><bean:write name="SC_szxyversionname"/>',"dialogWidth:300px;dialogHeight:350px;scroll=auto;border=thin;help=0;status=no");
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
	"/initSite.do?method=selectArea&areano=" + areano + "&tag=" + tag + "&ram=" + Math.random(),
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
/*屏蔽所有的js错误*/ 
function killerrors() {
	return true; 
} 
window.onerror = killerrors; 
</SCRIPT>
</HEAD>
<BODY>
<TABLE class="page_maintable" width="100%">
  <TR>
    <TD class="page_title">在线完善个人信息</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/initSite.do" method="post" >
       <TABLE width="450" align=center border="0">
          <tr>
            <td class="table_edit_right" colspan="2">
			<table width="100%" id="t_table">
			  <tr>
			    <td style="width:330px;color:#000;">
			      &nbsp;学&nbsp;&nbsp;&nbsp;&nbsp;科：&nbsp;<input name="subjectname1" id="subjectname1" readonly="readonly" onclick="selectSubject(0, 1)" type="text" value="" class=input style="width:70px;"/>
				  年&nbsp;&nbsp;&nbsp;&nbsp;级：&nbsp;<input name="gradename1" id="gradename1" readonly="readonly" onclick="selectSubject(1, 1)" CK_NAME="<bean:write name="SC_szxysubjectname"/><bean:write name="SC_szxygradename"/>" CK_TYPE="NotEmpty" type="text" value="" class=input style="width:100px;"/>
			    </td>
			    <td><INPUT onClick="addRow(this)" readonly type="button" value="添加" name="btnselect"></td>
			  </tr>
			</table>
			</td>
		  </tr>
		  <%-- 
          <tr>
            <td class="table_edit_right">籍&nbsp;&nbsp;&nbsp;&nbsp;贯：</td>
            <td class="table_edit_left" colspan="2">
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
            </td>
          </tr>
          --%>
          <tr>
                <td colspan="3" align="center">
                  <input type="button" value="保存" id="btnsave" name="btnsave" class="btn_save" onClick="javascript:saveRecord()">
                </td>
           </tr>
       </TABLE>
          <!-- 隐藏域放这是供选择学科年级调用 -->
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
          <input type="hidden" name="rowcount" id="rowcount" value="<bean:write name="rowcount"/>"/>
          
          <input type="hidden" name="sysUserInfo.nativeplace1" value="0001">
          <input type="hidden" name="sysUserInfo.nativeplace2" value="000101">
          <input type="hidden" name="sysUserInfo.nativeplace3" value="00010113">
          
          <input type="hidden" name="redirecturl" value="<bean:write name="redirecturl"/>"/>
          <input type="hidden" name="loginname" value="<bean:write name="loginname"/>"/>
      </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
