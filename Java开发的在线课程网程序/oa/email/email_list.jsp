<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<Script language="JavaScript" src="/public/DatePicker/WdatePicker.js"></Script>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function addRecord(){
  document.pageForm.action='/oaEmailInfoAction.do?method=beforeAdd';
  document.pageForm.submit();
}

function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="/oaEmailInfoAction.do?method=list";
  document.pageForm.submit();
}

function resend(){
	if(num>0){
	  var checkids="";
	  if (num>1){
	    for(i=0;i<num;i++){
	      if (document.pageForm.checkid[i].checked==true){
	        checkids=checkids+document.pageForm.checkid[i].value+",";
	      }
	    }
	  }
	  else{
	   if (document.pageForm.checkid.checked==true)	{
		checkids=document.pageForm.checkid.value;
	   }
	  }
	  if (checkids=="") {
	     alert("您还没有选择要操作的记录呢!")
	  }
	  else{
	    if(confirm("您确定给选中的用户重新发送邮件信息?")){
	      document.pageForm.action='/oaEmailInfoAction.do?method=resend';
	      document.pageForm.submit();
	    }
	  }
	 }
	 else{
	  alert("未找到可操作记录!")
	 }
}
</SCRIPT>
<title>产品管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<body leftMargin=0 topMargin=0>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">邮件管理</TD>
 </TR>
 <tr height="30">
   <td colspan="2">
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>邮件消息</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                  	<td width="80" height="25" align="right">邮件标题:</td>
                    <td><input type="text" name="title" style="width:160px;" value="<bean:write name="title"/>"/></td>
                    <td width="80" height="25" align="right">接收者:</td>
                    <td><input type="text" name="receiver" style="width:100px;" value="<bean:write name="receiver"/>"/></td>
                    <td width="80" height="25" align="right">发送日期:</td>
                    <td><input type="text" name="createdate" readonly="readonly" style="width:80px;" value="<bean:write name="createdate"/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/></td>
                  </tr>
                </table>
              </td>
              <td width="88" class="bg_basecolor"><input type="button" value="搜索" onclick="searchRecord()" class="btn_search" /></td>
              </tr>
        </table>
   </td>
 </tr>
 <TR>
    <TD class="page_blank" colspan="2"></TD>
  </TR>
 <tr>
   <td>
      <INPUT class="btn_all" onclick="setState(true)" type="button" value="全选" name="selectall">
      <INPUT class="btn_none"  onclick="setState(false)" type="button" value="全不选" name="selectnone">
      <INPUT class="btn_add"  onclick="addRecord()" type="button" value="新增" name="btnadd">
      <%-- <INPUT class="btn_del"  onclick="delRecord('/oaEmailInfoAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">--%>
      <INPUT class="btn_download"  onclick="resend()" type="button" value="重新发送" name="btndel">
    </td>
     <TD>
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title">邮件标题</TD>
    <TD class="table_title" width="220">接收者</TD>
    <TD class="table_title" width="70">发送日期</TD>
    <TD class="table_title" width="60">状态</TD>
    <TD class="table_title" width="40">操作</TD>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <tr onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
     <TD align="center">
       <input type="checkbox" name="checkid" value='<bean:write property="emailid" name="model" />' <logic:equal value="1" name="model" property="result">disabled="disabled"</logic:equal>>
     </TD>
     <TD align="left">&nbsp;<bean:write name="model" property="title"/></TD>
     <td align="center"><bean:write name="model" property="receiver.username"/>&lt;<bean:write name="model" property="receiver.email"/>&gt;</td>
     <td align="center"><java2page:write name="model" property="createdate" length="10"/></td>
     <td align="center"><java2code:value codetype="send" property="result" color="red" colorvalue="0"/></td>
     <td align="center">
     	<java2page:button url="oaEmailInfoAction.do" property="emailid" readonly="E" />
     </td>
     </tr>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="oaEmailInfoAction.do?method=list" name="pagelist" />
	  <input type="hidden" id="startcount" name="startcount" value="<bean:write name="startcount"/>" />
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
