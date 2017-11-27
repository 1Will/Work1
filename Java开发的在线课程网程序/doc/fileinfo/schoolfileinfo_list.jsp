<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.bo.SysUnitInfo"%>
<%@page import="com.bzt.sys.bo.SysUserInfo"%>
<%@page import="com.bzt.doc.bo.DocFileInfo"%>
<%@page import="com.util.search.PageList"%>
<%@page import="java.util.List"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<title>文档管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="/schoolDocFileInfoAction.do?method=list";
  document.pageForm.submit();
}

function getFilmDiscuss(filmid){
	var diag = new top.Dialog();
	diag.Title = "文档评论";
	diag.URL = '/docFileDiscussAction.do?method=list&ftag=1&filmid=' + filmid;
	diag.Width = 800;
	diag.Height = 520;
	diag.CancelEvent = function(){
		diag.close();
	};
	diag.show();
}
function preview(fileid){
	var diag = new top.Dialog();
	diag.Title = "在线预览";
	diag.URL = '/docFileInfoAction.do?method=preview&fileid=' + fileid;
	diag.Width = 800;
	diag.Height = 520;
	diag.CancelEvent = function(){
		diag.close();
	};
	diag.show();
}
function changeCity(temp){
  var city=document.getElementById("city");
  city.options.length=0;
  city.options.add(new Option("请选择",""));
  if(temp!=""){
   $.ajax({
        type:"post",
        url:"schoolDocFileInfoAction.do?method=changeCity",
        data:"parentno="+temp,
        success:function(msg){
           if(msg!=""){
         var arr=msg.split(";");
         for(var i=0;i<arr.length;i++){
                 var str=arr[i].split("_");
            city.options.add(new Option(str[1],str[0]));
          }
        }
        }
    });
  }
}
function changeCounty(temp){
 var county=document.getElementById("county");
  county.options.length=0;
  county.options.add(new Option("请选择",""));
  if(temp!=""){
   $.ajax({
        type:"post",
        url:"schoolDocFileInfoAction.do?method=changeCity",
        data:"parentno="+temp,
        success:function(msg){
           if(msg!=""){
         var arr=msg.split(";");
         for(var i=0;i<arr.length;i++){
                 var str=arr[i].split("_");
            county.options.add(new Option(str[1],str[0]));
          }
        }
        }
    });
  }
}
function changeSchool(){
  var province=document.getElementById("province").value;
  var city=document.getElementById("city").value;
  var county=document.getElementById("county").value;
  var school=document.getElementById("school");
  school.options.length=0;
  school.options.add(new Option("请选择",""));
  $.ajax({
     type:"post",
     url:"schoolDocFileInfoAction.do?method=changeSchool",
     data:"province="+province+"&city="+city+"&county="+county,
     success:function(msg){
      if(msg!=""){
       var arr=msg.split(";");
       for(var i=0;i<arr.length;i++){
                 var str=arr[i].split("_");
            school.options.add(new Option(str[1],str[0]));
          }
      }
     }
  });
}

</SCRIPT>
</head>
<body leftMargin=0 topMargin=0>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">文档管理</TD>
 </TR>
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>文档信息</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                        <tr>
                  <td width="80" height="25" align="right">省:</td>
                    <td>
                    <select name="province" id="province" onchange="changeCity(this.value)" >
                    <option value="">请选择</option>
                    <c:forEach items="${provinceList }" var="p">
                    <option value="${p.areano }" ${province eq p.areano?"selected":""}>${p.areaname }</option>
                    </c:forEach>
                    </select>
                                                    市:
                    <select name="city" id="city" onchange="changeCounty(this.value)" >
                    <option value="">请选择</option>
                    <c:forEach items="${citylist }" var="c">
                    <option value="${c.areano }" ${city eq c.areano?"selected":"" }>${c.areaname }</option>
                    </c:forEach>
                    </select>
                                                   县(区):
                    <select name="county" id="county">
                    <option value="">请选择</option>
                    <c:forEach items="${countylist }" var="t">
                    <option value="${t.areano }" ${county eq t.areano?"selected":"" }>${t.areaname }</option>
                    </c:forEach>
                    </select>
                    </td>
                    <td>
                    </td>
                    <td>
                    <input type="button" value="筛选学校 " class="btn_search" onclick="changeSchool()"/>
                    </td>
                 </tr>
                  <tr>
                    <td width="80" height="25" align="right" >学校:</td>
                    <td colspan="5">
                    <select  id="school" name="unitid" >
                    	<option value="">请选择..</option>
                    	<c:forEach items="${slist}" var="s">
                    	<option value="${s.unitid }" ${unitid eq s.unitid?"selected":"" }>${s.shortname}</option>
                    	</c:forEach>
                    </select>
                    </td>
                  </tr>
                  <tr>
                    <td width="80" height="25" align="right">文档名称:</td>
                    <td>
                    <input type="text" style="width:200px;" name="title" value="<bean:write name="title"/>"/>
                   	 上传者:<input type="text" style="width:80px;" name="username" value="<bean:write name="username"/>"/>
                    </td>
                    <td width="80" height="25" align="right">是否推荐:</td>
                    <td><java2code:option name="recommand" codetype="boolean" valuename="recommand" ckname="推荐"/></td>
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
      <INPUT class="btn_del"  onclick="delRecord('/schoolDocFileInfoAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
      <INPUT class="btn_recommend"  onclick="delRecord0('/schoolDocFileInfoAction.do?method=recommand','确定推荐所有选中文档？')" type="button" value="推荐" name="btndel">
      <INPUT class="btn_batchdown"  onclick="delRecord0('/schoolDocFileInfoAction.do?method=delRecommand','确定取消推荐所有选中文档？')" type="button" value="取消推荐" name="btndel">
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title">文档名称<a style="color:#40A9FF;font-weight:normal;">(点击文档名称在线预览)</a></TD>
    <TD class="table_title" width="70">上传者</TD>
    <TD class="table_title" width="60">浏览次数</TD>
    <TD class="table_title" width="60">是否推荐</TD>
    <TD class="table_title" width="60">操作</TD>
</tr>
<!--循环列出所有数据-->
<%
PageList pagelist = (PageList)request.getAttribute("pagelist");
List alllist =pagelist.getDatalist();
if(alllist != null && alllist.size() >0) {
	Object[] obj=null;
	DocFileInfo doc = null;
	SysUserInfo userinfo = null;
    for(int i=0;i<alllist.size();i++){
		obj=(Object[])alllist.get(i);
    	doc = (DocFileInfo)obj[0];
    	userinfo = (SysUserInfo)obj[1];
%>
     <tr onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
        <TD align="center">
       <input type="checkbox" name="checkid" value='<%=doc.getFileid()%>'>
     </TD>
     <TD align="left" style="white-space:nowrap;overflow:hidden;" title="<%=doc.getTitle()%>"><a href="/<%=Constants.getDefaultTemplate() %>-doc-<%=doc.getUnitid()%>-<%=doc.getFileid()%>.htm" target="_blank"><%=doc.getTitle()%></a></TD>
     <td align="center"><%=userinfo.getUsername()%></td>
     <td align="center"><%=doc.getDownloads()%></td>
     <td align="center"><% if(doc.getRecommand().equals("0") ){%>否<%} else {%>是<%}%></td>
     <td align="center">
       <img border="0" src="/public/images/main/edit.gif" onclick="javascript:editThisRecord('schoolDocFileInfoAction.do','<%=doc.getFileid()%>')" style="cursor:pointer;" title="修改" alt="修改">
       <a style="cursor:pointer;" onclick="getFilmDiscuss('<%=doc.getFileid()%>')" title="文档评论管理"><img border="0" src="/public/images/main/icon6.gif"/></a>
     </td>
     </tr>


 <% } }%>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="schoolDocFileInfoAction.do?method=list" name="pagelist" />
	  <input type="hidden" id="startcount" name="startcount" value="<bean:write name="startcount"/>" />
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
