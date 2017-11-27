<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.edu.bo.EduKnopointInfo"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base target="_self"/>
<%@ include file="/public/jsp/style.jsp"%>
<script language=javascript src="/public/js/comm.js"></script>
<script type="text/javascript">
var num=<bean:write name="size"/>;

function searchRecord(){
	document.pageForm.action="/eduSelectAction.do?method=selectKnopoint";
	document.pageForm.submit();
}

function selectRecord(url){
	if(num>0){
		var str="确认选择以下选中知识点？";
		var checkids="";
		if (num>1){
			for(i=0;i<num;i++){
				if (document.pageForm.checkid[i].checked==true){
					checkids=checkids+document.pageForm.checkid[i].value+",";
				}
			}
		}else{
			if (document.pageForm.checkid.checked==true)	{
				checkids=document.pageForm.checkid.value;
			}
		}
		if (checkids=="") {
			try{
				top.Dialog.confirm("确认清空知识点选择？",function(){
					document.pageForm.action=url;
	   				document.pageForm.submit();
				});
	  	  	}catch(e){
		  	  	if(confirm("确认清空知识点选择？")){
					document.pageForm.action=url;
					document.pageForm.submit();
				}
	  	  	}
			/*
			try{
	  		  	top.Dialog.alert("您还没有选择要操作的记录呢!");
	  	  	}catch(e){
	  	  		alert("您还没有选择要操作的记录呢!");
	  	  	}
	  	  	*/
		}else{
			var ckids = checkids.split(",");
			if(ckids.length > 6){
				try{
		  		  	top.Dialog.alert("知识点选择不能超过5个!");
		  	  	}catch(e){
		  	  		alert("知识点选择不能超过5个!");
		  	  	}
		  	  	return false;
			}
			document.pageForm.action=url;
			document.pageForm.submit();
			/*
			try{
				top.Dialog.confirm(str,function(){
					document.pageForm.action=url;
	   				document.pageForm.submit();
				});
	  	  	}catch(e){
		  	  	if(confirm(str)){
					document.pageForm.action=url;
					document.pageForm.submit();
				}
	  	  	}
	  	  	*/
		}
	}else{
		try{
  		  	top.Dialog.alert("未找到可操作记录!");
  	  	}catch(e){
  	  		alert("未找到可操作记录!");
  	  	}
	}
}
</script>
</head>

<body>
<FORM name="pageForm" method=post>
<table width="100%" align="center">
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>知识点</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">名称:</td>
                    <td><input type="text" value='<bean:write name="title" />' size="30" name="title"/></td>
                  </tr>
                </table>
              </td>
              <td width="88" class="bg_basecolor"><input type="button" value="搜索" onclick="searchRecord()" class="btn_search" /></td>
              </tr>
        </table>
   </td>
 </tr>
 <TR>
    <TD class="page_blank"></TD>
  </TR>
 <tr>
   <td>
      <INPUT class="btn_all" onclick="setState(true)" type="button" value="全选" name="selectall">
      <INPUT class="btn_none"  onclick="setState(false)" type="button" value="全不选" name="selectnone">
      <INPUT class="btn_add"  onclick="selectRecord('/eduSelectAction.do?method=selectDeal&tag=knopoint')" type="button" value="确认选择" name="btnadd">
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
	<tr class="table_title">
	    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
	    <TD class="table_title">知识点名称</TD>
	</tr>
	<%
	String knopointid = (String)request.getAttribute("knopointid");
	List knopointList = (List)request.getAttribute("knopointList");
	EduKnopointInfo model = null;
	int temp = 0;
	for(int i=0, size=knopointList.size(); i<size; i++){
		model = (EduKnopointInfo)knopointList.get(i);
		temp = model.getKnopointno().length() - 8;
	%>
	<TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
      	<TD align="center">
      		<%if("1".equals(model.getType())){ %>
      		<input type="checkbox" name="checkid" value="<%=model.getKnopointid() %>" <%if(knopointid.indexOf(model.getKnopointid().toString()) != -1){ %>checked="checked"<%} %>>
      		<%}else{ %>
      		&nbsp;
      		<%} %>
      	</TD>
      	<TD align="left"><%for(int m=0; m<temp; m++){%>&nbsp;<%}%><%=model.getTitle() %></TD>
	</TR>
	<%} %>
</TABLE>
<input type="hidden" name="subjectid" value="<bean:write name="subjectid"/>" />
<input type="hidden" name="gradeid" value="<bean:write name="gradeid"/>" />
</FORM>
</body>
</html>