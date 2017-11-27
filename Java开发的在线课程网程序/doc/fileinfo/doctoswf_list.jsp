<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function searchRecord(){
  document.pageForm.action="/docFileInfoAction.do?method=docToSwfList";
  document.pageForm.submit();
}

function reVodToFlv(fileid){
  if(confirm("确定从新转换当前文档?")){
    document.pageForm.action="/docFileInfoAction.do?method=reAllDocToSwf&fileid=" + fileid;
    document.pageForm.submit();
  }
}

</SCRIPT>
<title>文档管理</title>
<base target="_self"/>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<body>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">文库转码中的文件</TD>
 </TR>
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>文档</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">文档名称:</td>
                    <td><input type="text" style="width:230px;" name="title" value="<bean:write name="title"/>"/></td>
                    <td width="80" height="25" align="right">转换状态:</td>
                    <td>
                    <select name="swfstatus">
                    <option value="">请选择..</option>
                    <option value="1" <logic:equal value="1" name="swfstatus">selected="selected"</logic:equal>>转换中</option>
                    <option value="0" <logic:equal value="0" name="swfstatus">selected="selected"</logic:equal>>转换失败</option>
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
      <INPUT class="btn_check" onclick="delRecord0('/docFileInfoAction.do?method=reAllDocToSwf','确定从新转换选中的视频文件?')" type="button" value="重新转换" name="selectall">
      <logic:equal value="skyg" name="s_sysuserinfo" property="loginname">
      <INPUT class="btn_del"  onclick="delRecord('docFileInfoAction.do?method=delBatchRecord1')" type="button" value="删除" name="btndel">
      </logic:equal>
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title">文档名称</TD>
    <TD class="table_title" width="80">文档大小</TD>
    <TD class="table_title" width="120">上传时间</TD>
    <TD class="table_title" width="80">状态</TD>
    <logic:equal value="skyg" name="s_sysuserinfo" property="loginname">
    <TD class="table_title" width="60">操作</TD>
    </logic:equal>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
     	<TD align="center"><input type="checkbox" name="checkid" value="<bean:write property="fileid" name="model"/>"></TD>
        <TD align="left">&nbsp;<bean:write property="title" name="model" /></td>
        <td align="center"><bean:write property="flags" name="model"/></td>
        <td align="center"><bean:write property="createdate" name="model"/></td>
        <td align="center" style="color:green;">
           <logic:equal value="2" name="model" property="convertstatus"><a style="color:red;">转换失败</a></logic:equal>
     	   <logic:equal value="0" name="model" property="convertstatus">转换中</logic:equal>
     	   <logic:equal value="9" name="model" property="convertstatus">等待转换</logic:equal>
        </td>
        <logic:equal value="skyg" name="s_sysuserinfo" property="loginname">
        <td align="center">
	       <a href="/docFileInfoAction.do?method=downloads&fileid=<bean:write property="fileid" name="model"/>" target="_blank" title="下载源文件"><img border="0" src="/public/images/main/qi.gif"/></a>
	       <a href="/docFileInfoAction.do?method=beforeUpload&fileid=<bean:write property="fileid" name="model"/>" title="替换源文件"><img border="0" src="/public/images/main/ts.png"/></a>
	    </td>
	    </logic:equal>
     </TR>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="docFileInfoAction.do?method=docToSwfList" name="pagelist" />
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
