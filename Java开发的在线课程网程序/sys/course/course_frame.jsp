<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.bzt.sys.bo.SysCourseTable"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.edu.bo.EduSubjectVersion"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>

<SCRIPT language=javascript>
function saveRecord(){
  obj = document.sysCourseTableActionForm;
  document.getElementById("btnsave").disabled = true;
  obj.action="sysCourseTableAction.do?method=deal";
  obj.submit();
}
function deleteRecord(){
	obj = document.sysCourseTableActionForm;
    document.getElementById("btnsave").disabled = true;
    obj.action="sysCourseTableAction.do?method=deleteRecord";
    obj.submit();
}
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=no>
<%SysCourseTable table = (SysCourseTable)request.getAttribute("model"); %>
<TABLE width="380" height="230">
  <TR>
    <TD class="page_title">课程表设置</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/sysCourseTableAction.do" method="post" >
       <TABLE width="380" align=center border=0>
          <tr>
            <td class="table_edit_right" width="60">星期：</td>
            <td class="table_edit_left"><%=Constants.getCodeTypevalue("week", table.getWeek()) %></td>
          </tr>
          <tr>
            <td class="table_edit_right" width="60">课时：</td>
            <td class="table_edit_left">第<bean:write name="model" property="orderindex"/>节课</td>
          </tr>
          <tr>
            <td class="table_edit_right" width="60">课程：</td>
            <td class="table_edit_left">
             <%
             List subjectlist = (List)request.getAttribute("subjectlist");
  		     if(subjectlist != null && subjectlist.size() > 0){
  		     EduSubjectVersion esv = null;
  		     for(int i=0; i<subjectlist.size(); i++){
  		     	esv = (EduSubjectVersion)subjectlist.get(i);
  		     	if(i == 0 && (table.getCourseid() == null || "".equals(table.getCourseid()))){
  		     		table.setCourseid(esv.getId().toString());
  		     	}
             %>
             <input type="radio" name="sysCourseTable.courseid" value="<%=esv.getId() %>" <%if(esv.getId().toString().equals(table.getCourseid())){ %>checked="checked"<%} %>><%=esv.getName() %>
              <%}} %>
              <input type="radio" name="sysCourseTable.courseid" value="0" <%if("0".equals(table.getCourseid())){ %>checked="checked"<%} %>>值班
            </td>
          </tr>
          <%-- 
          <tr>
            <td class="table_edit_right" width="60">课程：</td>
            <td class="table_edit_left">
             <select name="sysCourseTable.courseid" style="width:60px;">
             <%
             List subjectlist = (List)request.getAttribute("subjectlist");
  		     if(subjectlist != null && subjectlist.size() > 0){
  		     EduSubjectVersion esv = null;
  		     for(int i=0; i<subjectlist.size(); i++){
  		     	esv = (EduSubjectVersion)subjectlist.get(i);
             %>
                <option value="<%=esv.getId() %>" <%if(esv.getId().toString().equals(table.getCourseid())){ %>selected<%} %>><%=esv.getName() %></option>
              <%}} %>
              </select>
            </td>
          </tr>
          --%>
          <tr>
            <td class="table_edit_right" width="60">班级：</td>
            <td class="table_edit_left"><input type="text" style="width:200px;" name="sysCourseTable.classname" value="<bean:write name="model" property="classname"/>"/></td>
          </tr>
          <tr>
            <td class="table_edit_right" width="60">教室：</td>
            <td class="table_edit_left"><input type="text" style="width:200px;" name="sysCourseTable.roomname" value="<bean:write name="model" property="roomname"/>"/></td>
          </tr>
          <tr>
                <td colspan="2" align="center">
                    <input type="button" value="保存" id="btnsave" name="btnsave" class="btn_save" onClick="javascript:saveRecord()">
                  <input type="button" value="删除" name="btnreturn" class="btn_cancel" onClick="javascript:deleteRecord()">
                    </td>
              </tr>
       </TABLE>
       <input type="hidden" name="sysCourseTable.tableid" value="<bean:write property="tableid" name="model"/>"/>
       <input type="hidden" name="sysCourseTable.week" value="<bean:write property="week" name="model"/>"/>
       <input type="hidden" name="sysCourseTable.noon" value="<bean:write property="noon" name="model"/>"/>
       <input type="hidden" name="sysCourseTable.orderindex" value="<bean:write property="orderindex" name="model"/>"/>
       <input type="hidden" name="sysCourseTable.userid" value="<bean:write property="userid" name="model"/>"/>
       <input type="hidden" name="sysCourseTable.username" value="<bean:write property="username" name="model"/>"/>
       <input type="hidden" name="sysCourseTable.unitid" value="<bean:write property="unitid" name="model"/>"/>
       </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
