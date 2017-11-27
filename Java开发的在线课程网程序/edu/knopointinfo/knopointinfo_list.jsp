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
    document.pageForm.action="eduKnopointInfoAction.do?method=beforeAdd";
    document.pageForm.submit();
   }
   
function play(){
	  	<logic:present name="reloadtree">
	    parent.tree.location ='/eduKnopointInfoAction.do?method=tree';
	  	</logic:present>  
	}

  function searchRecord(){
  document.pageForm.startcount.value = "0";
   document.pageForm.action="eduKnopointInfoAction.do?method=list";
   document.pageForm.submit();
  }

</script>
<title>资源目录</title>
  </head>
  <body onload="play();">
   <form name="pageForm" method="post">
   <input type="hidden" name="subjectid" value="${subjectid }"/>
   <input type="hidden" name="gradetype" value="${gradetype }"/>
   <input type="hidden" name="parentno" value="${parentno }"/>
  <table width="98%" align="center">
    <tr>
     <td class="page_title" colspan="2">知识点目录</td>
    </tr>
    <tr height="30">
      <td> 
       <table class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
        <tr>
         <td>查询>>知识点目录</td>
        </tr>
       </table>
       <table class="table_search" width="100%" cellSpacing=1 cellPadding=1 >
         <tr>
          <td class="bg_basecolor" align="left">
            <table cellpadding="0" cellspacing="0">
              <tr>
                <td width="80" height="25" align="right">知识点名称：</td>
                <td><input type="text" size="20" name="title"  value="${title }"/> </td>
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
      <INPUT class="btn_del"  onclick="delRecord('eduKnopointInfoAction.do?method=delBatchRecord&subjectid=${subjectid}&gradetype=${gradetype }&parentno=${parentno }')" type="button" value="删除" name="btndel">
    </td>
    <td align="right">
    </td>
    </tr>
  </table>
  <TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title">知识点名称</TD>
    <TD class="table_title">知识点编号</TD>
    <TD class="table_title" width="100">类型</TD>
    <TD class="table_title" width="100">知识点类型</TD>
    <TD class="table_title">状态</TD>
    <TD class="table_title" width="70">操作</TD>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <tr onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
        <TD align="center"><input type="checkbox" name="checkid" value="<bean:write property="knopointid" name="model"/>" <bean:write property="flags" name="model"/> /> </TD>
        <td align="left">&nbsp;<bean:write property="title" name="model" /></a></td>
         <td align="center">&nbsp;<bean:write property="knopointno" name="model" /></td>
         <td>
         <logic:equal value="0" name="model" property="type">知识点大纲</logic:equal>
         <logic:equal value="1" name="model" property="type">具体知识点</logic:equal>
         </td>
         <td>
         <logic:equal value="1" name="model" property="gradetype">小学知识点</logic:equal>
         <logic:equal value="2" name="model" property="gradetype">初中知识点</logic:equal>
         <logic:equal value="3" name="model" property="gradetype">高中知识点</logic:equal>
         </td>
         <td>
         <logic:equal value="1" name="model" property="status">正常 </logic:equal>
         <logic:equal value="0" name="model" property="status">禁用</logic:equal>
         </td>
         <td align="center">
         <java2page:button url="eduKnopointInfoAction.do" property="knopointid" readonly="E"/>
         </td>
     </tr>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="eduKnopointInfoAction.do?method=list" name="pagelist" />
        <input type="hidden" name="startcount" id="startcount" value="<bean:write name="startcount"/>">
    </TD>
  </TR>
  </table>
  </form>
  </body>
</html>
