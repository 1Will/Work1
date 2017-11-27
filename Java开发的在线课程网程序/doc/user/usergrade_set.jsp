<%@page import="com.util.string.encode.Encode"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.bo.SysUserInfo"%>
<%@page import="com.bzt.gpw.bo.GpwAreaInfo"%>
<%@page import="com.bzt.vod.bo.VodFilmType"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<TITLE>用户管理</TITLE>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript"  src="/public/DatePicker/WdatePicker.js"></Script>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>
<SCRIPT language=javascript>
function saveRecord(){
  	obj = document.all("docUserGradeActionForm");
  	document.getElementById("btnsave").disabled = true;
  	obj.action="docUserGradeAction.do?method=updateSave";
  	obj.submit();
}

function addRow(obj){
	var rowcount = document.getElementById('rowcount');
	if(rowcount.value == '10'){
		alert('选择学科年级不能超过10个!');
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
  	row.insertCell(row.cells.length).innerHTML='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学科：&nbsp;<input name="subjectname'+newrowcount+'" id="subjectname'+newrowcount+'" readonly="readonly" onclick="selectAllSubject('+newrowcount+')" type="text" value="" class=input style="width:70px;"/>&nbsp;&nbsp;年级：&nbsp;<input name="gradename'+newrowcount+'" id="gradename'+newrowcount+'" readonly="readonly" onclick="selectAllGrade('+newrowcount+')" type="text" value="" class=input style="width:80px;"/>';
    row.insertCell(row.cells.length).innerHTML='<INPUT onClick="delRow(this)" readonly type="button" value="删除" name="btnselect">';
	row.cells[0].style.width="300";
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
function delData(){
	document.getElementById('subjectid1').value = "";
	document.getElementById('subjectname1').value = "";
	document.getElementById('gradeid1').value = "";
	document.getElementById('gradename1').value = "";
}
</SCRIPT>
<%@ include file="/edu/select/select_js.jsp"%>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable" width="100%">
  <TR>
    <TD class="page_title"><logic:equal value="1" name="type">文档</logic:equal><logic:equal value="2" name="type">微课</logic:equal>转移权限设置</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/docUserGradeAction.do" method="post" >
       <TABLE width="100%" align=center border="0">
          	<!-- 隐藏域供调用 -->
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
		  	<input type="hidden" name="subjectid6" id="subjectid6" value=""/>
		  	<input type="hidden" name="gradeid6" id="gradeid6" value=""/>
		  	<input type="hidden" name="subjectid7" id="subjectid7" value=""/>
		  	<input type="hidden" name="gradeid7" id="gradeid7" value=""/>
		  	<input type="hidden" name="subjectid8" id="subjectid8" value=""/>
		  	<input type="hidden" name="gradeid8" id="gradeid8" value=""/>
		  	<input type="hidden" name="subjectid9" id="subjectid9" value=""/>
		  	<input type="hidden" name="gradeid9" id="gradeid9" value=""/>
		  	<input type="hidden" name="subjectid10" id="subjectid10" value=""/>
		  	<input type="hidden" name="gradeid10" id="gradeid10" value=""/>
			<input type="hidden" name="rowcount" id="rowcount" value="${rowcount }"/>
          <tr>
            <td colspan="3">
			<table width="100%" id="t_table">
			<%
			  List usergradelist = (List)request.getAttribute("usergradelist");
			  if(usergradelist != null && usergradelist.size() > 0){
			  %>
			  <logic:iterate id="teaching" name="usergradelist" scope="request" indexId="ii">
			  <tr>
			    <td style="width:300px;color:#000;">
			      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学科：&nbsp;<input name="subjectname<%=ii+1 %>" id="subjectname<%=ii+1 %>" readonly="readonly" onclick="selectAllSubject(<%=ii+1 %>)" type="text" value='<bean:write name="teaching" property="flags"/>' class=input style="width:70px;"/>
				  &nbsp;年级：&nbsp;<input name="gradename<%=ii+1 %>" id="gradename<%=ii+1 %>" readonly="readonly" onclick="selectAllGrade(<%=ii+1 %>)" type="text" value='<bean:write name="teaching" property="flago"/>' class=input style="width:80px;"/>
			    </td>
			    <td><%if(ii == 0){ %><INPUT onClick="addRow(this)" readonly type="button" value="添加" name="btnselect"><INPUT onClick="delData()" readonly type="button" value="清空" name="btnselect"><%}else{ %><INPUT onClick="delRow(this, <%=ii+1 %>)" readonly type="button" value="删除" name="btnselect"><%} %></td>
			  </tr>
			  <script language=javascript>
			  	document.getElementById('subjectid<%=ii+1 %>').value = '<bean:write name="teaching" property="subjectid"/>';
			  	document.getElementById('gradeid<%=ii+1 %>').value = '<bean:write name="teaching" property="gradeid"/>';
			  </script>
			  </logic:iterate>
			  <%}else{ %>
			  <tr>
			    <td style="width:300px;color:#000;">
			      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学科：&nbsp;<input name="subjectname1" id="subjectname1" readonly="readonly" onclick="selectAllSubject(1)" type="text" value="" class=input style="width:70px;"/>
				  &nbsp;年级：&nbsp;<input name="gradename1" id="gradename1" readonly="readonly" onclick="selectAllGrade(1)" type="text" value="" class=input style="width:80px;"/>
			    </td>
			    <td><INPUT onClick="addRow(this)" readonly type="button" value="添加" name="btnselect"><INPUT onClick="delData()" readonly type="button" value="清空" name="btnselect"></td>
			  </tr>
			  <%} %>
			</table>
			</td>
		  </tr>
          <tr>
                <td colspan="3" align="center">
                  <input type="button" value="保存" id="btnsave" name="btnsave" class="btn_save" onClick="javascript:saveRecord()">
                </td>
           </tr>
       </TABLE>
       <input type="hidden" name="userid" value="${userid }"/>
       <input type="hidden" name="type" value="${type }"/>
      </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
