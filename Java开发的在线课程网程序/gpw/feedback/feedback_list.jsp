<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<Script language="JavaScript"  src="/public/DatePicker/WdatePicker.js"></Script>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="gpwFeedbackInfoAction.do?method=list";
  document.pageForm.submit();
}
</SCRIPT>
<title>角色管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<body >
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">意见反馈</TD>
 </TR>
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>意见反馈</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">反馈内容:</td>
                    <td><input type="text" value='<bean:write name="content" />' size="30" name="content"/></td>
                    <td width="80" height="25" align="right">反馈时间:</td>
                    <td><input type="text" value='<bean:write name="createdate" />' size="10" name="createdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/></td>
                    <td width="80" height="25" align="right">是否显示:</td>
                    <td><java2code:option name="state" codetype="boolean" valuename="state" ckname="是否显示"/></td>
                    <td width="80" height="25" align="right">是否回复:</td>
                    <td>
                    <select name="isreply">
                    	<option value="">请选择...</option>
                    	<option value="1" <logic:equal value="1" name="isreply">selected="selected"</logic:equal>>已回复</option>
                    	<option value="0" <logic:equal value="0" name="isreply">selected="selected"</logic:equal>>未回复</option>
                    </select>
                    </td>
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
      <INPUT class="btn_del"  onclick="delRecord('gpwFeedbackInfoAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr class="table_title">
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title" width="100">反馈时间</TD>
    <TD class="table_title">反馈内容</TD>
    <TD class="table_title" width="60">是否显示</TD>
    <TD class="table_title" width="60">是否回复</TD>
    <TD width="50" class="table_title">操作</TD>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
     <TD align="center"><input type="checkbox" name="checkid" value="<bean:write property="feedbackid" name="model"/>" <bean:write property="flags" name="model"/>></TD>
     <TD align="center"><java2page:write name="model" property="createdate" length="10"/>
     <TD align="left"><bean:write name="model" property="content"/></TD>
     <TD align="center"><java2code:value codetype="boolean" property="state" color="red" colorvalue="0"/></TD>
     <TD align="center">
       <logic:equal value="0" name="model" property="isreply">未回复</logic:equal>
       <logic:equal value="1" name="model" property="isreply">已回复</logic:equal>
     </TD>
     <td>
        <java2page:button url="/gpwFeedbackInfoAction.do" property="feedbackid" readonly="E"/>
      </TD>
     </TR>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0  align="center">
  <TR>
    <TD align=center>
       <java2page:pager url="gpwFeedbackInfoAction.do?method=list" name="pagelist"/>
       <input type="hidden" name="startcount" value="<bean:write name="startcount"/>" />
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
