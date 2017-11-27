	<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
 <%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<script type="text/javascript">
var num=<bean:write name="pagelist" property="pageCount" />
   function addRecord(){
    document.pageForm.action="eduColumnInfoAction.do?method=beforeAdd";
    document.pageForm.submit();
   }
   
function play(){
	  	<logic:present name="reloadtree">
	    parent.tree.location ='/eduColumnInfoAction.do?method=tree&subjectid=${subjectid}';
	  	</logic:present>  
	}

  function searchRecord(){
  document.pageForm.startcount.value = "0";
   document.pageForm.action="eduColumnInfoAction.do?method=list";
   document.pageForm.submit();
  }
 function addknopoint(columnid){
   var diag=new top.Dialog();
   diag.Title="知识点管理";
   diag.URL='/eduColumnInfoAction.do?method=addknopoint&columnid='+columnid;
   diag.Width=550;
   diag.Height=500;
   diag.CancelEvent=function(){
     document.pageForm.action="eduColumnInfoAction.do?method=list";
     document.pageForm.submit();
     diag.close();
    };
    diag.show();
 }

</script>
<title>资源目录</title>
  </head>
  <body onload="play();">
   <form name="pageForm" method="post">
   <input type="hidden" name="subjectid" value="${subjectid }"/>
   <input type="hidden" name="parentno" value="${parentno }"/>
   <input type="hidden" name="versionid" value="${versionid }"/>
  <table width="98%" align="center">
    <tr>
     <td class="page_title" colspan="2">资源目录</td>
    </tr>
    <tr height="30">
      <td> 
       <table class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
        <tr>
         <td>查询>>资源目录</td>
        </tr>
       </table>
       <table class="table_search" width="100%" cellSpacing=1 cellPadding=1 >
         <tr>
          <td class="bg_basecolor" align="left">
            <table cellpadding="0" cellspacing="0">
              <tr>
                <td width="80" height="25" align="right">栏目名称：</td>
                <td><input type="text" size="20" name="columname"  value="${columname }"/> </td>
              </tr>
            </table>
          </td>
          <td width="88" class="bg_basecolor"><input type="button" value="搜索" onclick="searchRecord()" class="btn_search" /> </td>
         </tr>
       </table>
      </td>
    </tr>
    <tr>
      <td class="page_blank"></td>
    </tr>
    <tr>
    <td>
     <INPUT class="btn_all" onclick="setState(true)" type="button" value="全选" name="selectall">
      <INPUT class="btn_none"  onclick="setState(false)" type="button" value="全不选" name="selectnone">
      <INPUT class="btn_add"  onclick="addRecord()" type="button" value="新增" name="btnadd">
      <INPUT class="btn_del"  onclick="delRecord('eduColumnInfoAction.do?method=delBatchRecord&subjectid=${subjectid}&versionid=${versionid }&parentno=${parentno }')" type="button" value="删除" name="btndel">
    </td>
    <td align="right">
    </td>
    </tr>
  </table>
  <TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title">栏目名称</TD>
    <TD class="table_title">栏目编号</TD>
    <TD class="table_title" width="100">栏目类型</TD>
    <TD class="table_title" width="100">是否关联知识点</TD>
    <TD class="table_title">状态</TD>
    <TD class="table_title" width="70">操作</TD>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <tr onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
        <TD align="center"><input type="checkbox" name="checkid" value="<bean:write property="columnid" name="model"/>" <bean:write property="flags" name="model"/> /> </TD>
        <td align="left">&nbsp;<bean:write property="columnname" name="model" /></a></td>
         <td align="center">&nbsp;<bean:write property="columnno" name="model" /></td>
         <td>
         <logic:equal value="3" name="model" property="columntype">综合</logic:equal>
         <logic:equal value="2" name="model" property="columntype">节</logic:equal>
         <logic:equal value="1" name="model" property="columntype">课</logic:equal>
         <logic:equal value="0" name="model" property="columntype">章</logic:equal>
         </td>
         <td><bean:write property="temp" name="model" /></td>
         <td>
         <logic:equal value="1" name="model" property="status">正常 </logic:equal>
         <logic:equal value="0" name="model" property="status">禁用</logic:equal>
         </td>
         <td align="center">
         <java2page:button url="eduColumnInfoAction.do" property="columnid" readonly="E"/>
         <a title="知识点管理" style="cursor:pointer;" onclick="addknopoint(<bean:write property="columnid" name="model" />);"> <img title="知识点管理" border="0" src="/public/images/main/bianhao.gif"/></a>
         </td>
     </tr>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="eduColumnInfoAction.do?method=list" name="pagelist" />
        <input type="hidden" name="startcount" id="startcount" value="<bean:write name="startcount"/>">
    </TD>
  </TR>
  </table>
  </form>
  </body>
</html>
