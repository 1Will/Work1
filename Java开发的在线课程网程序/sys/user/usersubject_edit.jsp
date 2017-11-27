<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<TITLE>用户管理</TITLE>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<SCRIPT language=javascript>
var reFlag = false;
function saveRecord(){
  obj = document.all("sysUserInfoActionForm");
  if(autoCheckForm(obj)==false){
    return false;
  }
  document.getElementById("btnsave").disabled = true;
  obj.action="sysUserInfoAction.do?method=userSubjectDeal";
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
	
	var url = '/sysUserInfoAction.do?method=selectSubject&flag=' + flag + '&objid=' + objid;
	var retValue=showModalDialog(url,'选择<bean:write name="SC_szxysubjectname"/>ean:write name="SC_szxysubjectname"/><bean:write name="SC_szxyversionname"/>',"dialogWidth:300px;dialogHeight:350px;scroll=auto;border=thin;help=0;status=no");
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
/*屏蔽所有的js错误*/ 
function killerrors() {
	return true; 
} 
window.onerror = killerrors; 
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable" width="100%">
  <TR>
    <TD class="page_title">设置<bean:write name="SC_szxysubjectname"/></TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/sysUserInfoAction.do" method="post" >
       <TABLE width="100%" align=center border="0">
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
			      <bean:write name="SC_szxysubjectname"/>：&nbsp;<input name="subjectname<%=ii+1 %>" id="subjectname<%=ii+1 %>" readonly="readonly" onclick="selectSubject(0, <%=ii+1 %>)" type="text" value='<bean:write name="teaching" property="flags"/>' class=input style="width:70px;"/>
				  <bean:write name="SC_szxygradename"/>：&nbsp;<input name="gradename<%=ii+1 %>" id="gradename<%=ii+1 %>" readonly="readonly" onclick="selectSubject(1, <%=ii+1 %>)" <%if(ii == 0){ %>CK_NAME="<bean:write name="SC_szxysubjectname"/><bean:write name="SC_szxygradename"/>" CK_TYPE="NotEmpty"<%} %> type="text" value='<bean:write name="teaching" property="flago"/>' class=input style="width:100px;"/>*
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
		  <tr><td colspan="3" height="10"></td></tr>
          <tr>
                <td colspan="3" align="center">
                  <input type="button" value="保存" id="btnsave" name="btnsave" class="btn_save" onClick="javascript:saveRecord()">
                </td>
           </tr>
       </TABLE>
       <input type="hidden" name="sysUserInfo.userid" value="<bean:write property="userid"  name="model"/>"/>
       <input type="hidden" name="rowcount" id="rowcount" value="<bean:write name="rowcount"/>"/>
      </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
