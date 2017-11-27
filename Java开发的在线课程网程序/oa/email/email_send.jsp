<%@page import="com.bzt.oa.bo.OaEmailTemplate"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.oa.bo.OaEmailInfo"%>
<%@page import="com.bzt.oa.bo.OaEmailManager"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html:html>

<HEAD>
<TITLE>folder_edit</TITLE>

<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<script type="text/javascript">
	function saveRecord(){
	  obj = document.all("oaEmailInfoActionForm");
	  if(autoCheckForm(obj)==false){
	    return false;
	  }
	  
	  obj.action="/oaEmailInfoAction.do?method=<bean:write name="act"/>";
	  obj.submit();
	}
	
	function selectUser(){
		var url = '/oaEmailInfoAction.do?method=selectUser';
        var retValue=showModalDialog(url,'选择用户',"dialogWidth:800px;dialogHeight:580px;scroll=no;border=thin;help=0;status=no");
        if(retValue != null){
        	//document.getElementById("receivers").value = retValue[0];
        	document.getElementById("receivers").innerHTML = retValue[0];
        	document.getElementById("userids").value = retValue[1];
        }
	}
	function showContent(content){
		document.getElementById('showcontent').innerHTML = content;
	}
	function getTemplate(templateid){
		if(templateid != ''){
			var templatecontent = document.getElementById('tname_'+templateid).value;
			document.getElementById('oaEmailInfo.htmlcontent').value = templatecontent;
			document.getElementById('showcontent').innerHTML = templatecontent;
		}
	}
</script>
</HEAD>

<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">邮件管理</TD>
  </TR>
  <TR>
    <TD vAlign=top align=middle>
      <html:form action="/oaEmailInfoAction.do" method="post" >
       <TABLE width="560" border=0 cellpadding=2 cellspacing=2 align="center">
       	  <tr>
            <td class="table_edit_right" width="80">邮件标题：</td>
            <td class="table_edit_left">
            <input name="oaEmailInfo.title" type="text" size="50" CK_NAME="邮件标题" CK_TYPE="NotEmpty" value='<bean:write name="model" property="title"/>'>*
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">我的邮箱：</td>
            <td class="table_edit_left">
            	<select name="id" CK_NAME="我的邮箱" CK_TYPE="NotEmpty">
            		<%
            			List list = (List)request.getAttribute("list");
            			OaEmailInfo oaEmailInfo = (OaEmailInfo)request.getAttribute("model");
            			if(list != null && list.size() > 0){
            			OaEmailManager oaEmailManager = null;
            			for(int i=0; i<list.size(); i++){
            				oaEmailManager = (OaEmailManager)list.get(i);
            		%>
            		<option value="<%=oaEmailManager.getId() %>" <%if(oaEmailManager.getId().toString().equals(oaEmailInfo.getManagerid())){ %>selected="selected"<%} %>><%=oaEmailManager.getEmail() %></option>
            		<%}} %>
            	</select>
            </td>
          </tr>
          <tr>
            <td class="table_edit_right">接收者：</td>
            <td class="table_edit_left">
              <%-- <input name="receivers" id="receivers" type="text" size="50" CK_NAME="接收者" CK_TYPE="NotEmpty" <logic:notEmpty name="model" property="receiver">value='<bean:write name="model" property="receiver.username"/>'</logic:notEmpty> readonly="readonly" onClick="selectUser()">*--%>
              <span id="receivers"><logic:notEmpty name="model" property="receiver"><bean:write name="model" property="receiver.username"/>&lt<bean:write name="model" property="receiver.email"/>&gt</logic:notEmpty></span>
              <!-- 修改发送失败邮件不能选择用户 -->
              <logic:notEqual value="updateSave" name="act">
              <input type="button" name="button" value="选择" onClick="selectUser()" style="width:46px; height:21px; background:url(/public/images/main/a19.gif) no-repeat; border:0px; cursor:pointer;"/>
              </logic:notEqual>
            </td>
          </tr>
          <%
          List templateList = (List)request.getAttribute("templateList");
          if(templateList != null && templateList.size() > 0){
          %>
          <tr>
            <td class="table_edit_right">邮件模板：</td>
            <td class="table_edit_left">
            	<select name="template" onchange="getTemplate(this.value)">
            		<option value="">请选择...</option>
            		<%
            			OaEmailTemplate oet = null;
            			for(int i=0; i<templateList.size(); i++){
            				oet = (OaEmailTemplate)templateList.get(i);
            		%>
            		<option value="<%=oet.getTemplateid() %>"><%=oet.getTemplatename() %></option>
            		<input type="hidden" name="tname_<%=oet.getTemplateid() %>" id="tname_<%=oet.getTemplateid() %>" value="<%=oet.getHtmlcontent() %>"/>
            		<%} %>
            	</select>
            </td>
          </tr>
          <%} %>
            <tr>
             <td class="table_edit_right">邮件内容：</td>
            <td class="table_edit_left">
               <textarea rows="10" cols="60" name="oaEmailInfo.htmlcontent" id="oaEmailInfo.htmlcontent" CK_NAME="邮件内容" CK_TYPE="NotEmpty" onblur="showContent(this.value)"><bean:write name="model" property="htmlcontent"/></textarea>
            </td>
           </tr>
		  <tr>
             <td class="table_edit_right">预览效果：</td>
            <td class="table_edit_left" id="showcontent">
               <bean:write name="model" property="htmlcontent" filter="false"/>
            </td>
           </tr>
          <tr>
              <td colspan="2" height="40" align="center">
              	 <logic:notEqual value="1" name="model" property="result">
                 <input type="button" value="发送" id="btnsave" name="btnsave" class="btn_save" onClick="saveRecord()">
                 </logic:notEqual>
              <%
              	if(!"1".equals(request.getParameter("tag"))){
              %>
              &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" value="返回" name="btnreturn" class="btn_cancel" onClick="history.go(-1);">
              <%} %>
         </td>
        </tr>

       </TABLE>
       <input type="hidden" name="oaEmailInfo.emailid" value="<bean:write property="emailid"  name="model"/>"/>
       <input type="hidden" name="oaEmailInfo.createdate" value="<bean:write property="createdate"  name="model"/>"/>
       <input type="hidden" name="oaEmailInfo.userid" value="<bean:write property="userid"  name="model"/>"/>
       <input type="hidden" name="oaEmailInfo.unitid" value="<bean:write property="unitid"  name="model"/>"/>
       <input type="hidden" name="oaEmailInfo.result" value="<bean:write property="result"  name="model"/>"/>
       
       <input type="hidden" name="userids" id="userids" CK_NAME="接收者" CK_TYPE="NotEmpty" <logic:notEmpty name="model" property="receiver">value='<bean:write name="model" property="receiver.userid"/>'</logic:notEmpty>/>
       
       <input type="hidden" name="receiver" value="<bean:write name="receiver"/>"/>
       <input type="hidden" name="title" value="<bean:write name="title"/>"/>
       <input type="hidden" name="createdate" value="<bean:write name="createdate"/>"/>
       <input type="hidden" name="startcount" value="<bean:write name="startcount"/>"/>
       
       <input type="hidden" name="tag" value="<%=request.getParameter("tag") %>"/>
      </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
