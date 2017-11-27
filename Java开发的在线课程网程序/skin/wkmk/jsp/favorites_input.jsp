<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../../public/jsp/taglibs.jsp"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.bo.SysUserFavorites"%>
<html:html>
<HEAD>
<TITLE>新建收藏夹</TITLE>
<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript"  src="/public/js/comm.js"></Script>
<script type="text/javascript" src="/public/js/dialog/drag.js"></script>
<script type="text/javascript" src="/public/js/dialog/dialog.js"></script>
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<SCRIPT language=javascript>
  function saveRecord(){
    var form=document.getElementById("form");
    var name=document.getElementById("name");
    var columnumber=document.getElementById("columnnum");
    var parentno=document.getElementById("parentno");
    var inner=document.getElementById("inner");
    var tempvalue=parentno.value+columnumber.value;
    var flag=false; 
    if(name.value==""){
      inner.innerHTML="栏目名称不能为空！";
      return false;
    }
    if(name.value.length>25){
      inner.innerHTML="栏目名称长度不能超过25个字！";
      return false; 
    }
     if(isNaN(columnumber.value)){
       inner.innerHTML="编号只能为数字！";
       return false; 
      }
    if(columnumber.value.length<4||columnumber.value.length>4){
       inner.innerHTML="请填写4位数字编号！";
      return false;
      }
     $.ajax({
		url:"/v.bo?method=isSysUserFavorites",
		data:"fno="+tempvalue,
		async: false,
		success:function(data){
		 if(data!='ok'){	
		  inner.innerHTML=data;
		  flag=true;
		   }
	  }
      }
    );
     if(flag) return false;
    
      document.getElementById("btnsave").disabled = true;
      form.action="/v.bo?method=addSaveSysUserFavorites";
      form.submit();
  }
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">我的收藏夹</TD>
  </TR>
  <TR>
    <TD vAlign=top align=middle>
    <form id="form" action="" method="post"> 
     <input type="hidden"  name="filetype" value="${filetype }" />
      <input type="hidden" name="fileid" value="${fileid }" />
      <div id="inner" style="color: red;"></div>
       <TABLE width="100%" align=center border="0">
             <tr>
             <td class="table_edit_right">请选择父栏目</td>      
             <td class="table_edit_left" width="75%" colspan="3">
             <select name="parentno" id="parentno">
             <option value="0000">我的收藏夹</option>
             <%
               List list=(List)request.getAttribute("favoritesList");
               for(int i=0;i<list.size();i++){
               SysUserFavorites model=(SysUserFavorites)list.get(i);
              %>
              <option value="<%=model.getFno()%>"><%for(int j=0;j<(model.getFno().length()-4)/2;j++){%>&nbsp;<%}%>├<%=model.getName()%></option>
               <%}%>
             </select>
             </td>    
              </tr>
          <tr>
            <td class="table_edit_right" width="25%">栏目名称：</td>
            <td class="table_edit_left" width="75%" colspan="3"><input id="name" type="text" CK_NAME="栏目名称" CK_TYPE="NotEmpty,MaxLen_25" name="name" size="25" title="最多为25个汉字" lenght="25" class=input value="">&nbsp;*</td>
          </tr>
          <tr>
            <td class="table_edit_right">栏目编号：</td>
            <td class="table_edit_left" colspan="3"><a id="showparentno"></a><input  type="text"  CK_NAME="栏目编号" CK_TYPE="NotEmpty" name="columnnum" id="columnnum" class=input title="请输入4位数字的编号" value="" maxlength="4" size="4" >&nbsp;*
            </td>
          </tr>
          <tr>
              <td colspan="4" height="40" align="center">
                 <input type="button" value="保存" id="btnsave" name="btnsave" class=btn_save onClick="javascript:saveRecord()">
              &nbsp;&nbsp;&nbsp;&nbsp;
           </tr>
           </TABLE>
          </form> 
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
