<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.gpw.bo.GpwAreaInfo"%>
<html:html>

<HEAD>
<TITLE>学校管理</TITLE>

<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<script type=text/javascript src="/skin/vod003/js/jquery-1.8.0.min.js"></script>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />;
function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="sysUnitInfoAction0.do?method=list";
  document.pageForm.submit();
}

function changeArea(type, areano){
	if(type == '1'){
		if(areano != ''){
			$.ajax({
		        type: "get",
		        async: false,
		        url: "/sysUnitInfoAction.do?method=getArea&areano=" + areano + "&ram=" + Math.random(),
		        dataType: "text",
		        success: function(data){
		        	if(data != ''){
		        		$("#areano2").html(data);
					}else{
						$("#areano2").html("<option value=''>请选择</option>");
					}
		        }
			});
		}else{
			$("#areano2").html("<option value=''>请选择</option>");
		}
		$("#areano3").html("<option value=''>请选择</option>");
	}
	if(type == '2'){
		if(areano != ''){
			$.ajax({
		        type: "get",
		        async: false,
		        url: "/sysUnitInfoAction.do?method=getArea&areano=" + areano + "&ram=" + Math.random(),
		        dataType: "text",
		        success: function(data){
		        	if(data != ''){
		        		$("#areano3").html(data);
					}else{
						$("#areano3").html("<option value=''>请选择</option>");
					}
		        }
			});
		}else{
			$("#areano3").html("<option value=''>请选择</option>");
		}
	}
}

</SCRIPT>
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0>
<%@ include file="/edu/res/tip.jsp"%>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">单位数据去重管理</TD>
 </TR>
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>单位管理</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">单位性质：</td>
                    <td><java2code:option name="unittype" codetype="unittype" valuename="unittype" ckname="单位性质"/></td>
                    <td width="80" height="25" align="right">单位名称：</td>
                    <td><input type="text" value='<bean:write name="unitname" />'  size="30" name="unitname"/></td>
                  </tr>

                  <tr>
		            <td  width="80" height="25" align="right">学校地区：</td>
		            <td colspan="3">
					<span>
			        <select name="areano1" onchange="changeArea('1', this.value)">
			          <option value="">请选择</option>
			          <%
			          String areano1 = (String)request.getAttribute("areano1");
			          List arealist1 = (List)request.getAttribute("arealist1");
			          GpwAreaInfo gai = null;
			          for(int i=0, size=arealist1.size(); i<size; i++){
			        	  gai = (GpwAreaInfo)arealist1.get(i);
			          %>
			          <option value="<%=gai.getAreano() %>" <%if(gai.getAreano().equals(areano1)){ %>selected="selected"<%} %>><%=gai.getAreaname() %></option>
			          <%} %>
			        </select>
			        <select name="areano2" id="areano2" style="width:150px" onchange="changeArea('2', this.value)">
			          <option value="">请选择</option>
			          <%
			          List arealist2 = (List)request.getAttribute("arealist2");
			          String areano2 = (String)request.getAttribute("areano2");
			          if(arealist2 != null && arealist2.size() > 0){
			        	  
			          for(int i=0, size=arealist2.size(); i<size; i++){
			        	  gai = (GpwAreaInfo)arealist2.get(i);
			          %>
			          <option value="<%=gai.getAreano() %>" <%if(gai.getAreano().equals(areano2)){ %>selected="selected"<%} %>><%=gai.getAreaname() %></option>
			          <%}} %>
			        </select>
			        <select name="areano3" id="areano3" onchange="changeArea('3', this.value)">
			          <option value="">请选择</option>
			          <%
			          List arealist3 = (List)request.getAttribute("arealist3");
			          String areano3 = (String)request.getAttribute("areano3");
			          if(arealist3 != null && arealist3.size() > 0){
			          for(int i=0, size=arealist3.size(); i<size; i++){
			        	  gai = (GpwAreaInfo)arealist3.get(i);
			          %>
			          <option value="<%=gai.getAreano() %>" <%if(gai.getAreano().equals(areano3)){ %>selected="selected"<%} %>><%=gai.getAreaname() %></option>
			          <%}} %>
			        </select>
			        </span>
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
  <logic:equal value="skyg" name="s_sysuserinfo" property="loginname">
 <tr>
   <td>
      <INPUT class="btn_all" onclick="setState(true)" type="button" value="全选" name="selectall">
      <INPUT class="btn_none"  onclick="setState(false)" type="button" value="全不选" name="selectnone">
      <INPUT class="btn_del"  onclick="delRecord('sysUnitInfoAction0.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
    </td>
     <TD align="right">
     </TD>
 </tr>
 </logic:equal>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title">单位名称</TD>
    <TD class="table_title" width="80">负责人</TD>
    <TD class="table_title" width="80">职务</TD>
    <TD class="table_title" width="100">联系电话</TD>
    <TD class="table_title" width="60">状态</TD>
    <TD class="table_title" width="100">操作</TD>
</tr>
<!--循环列出所有数据-->
  <%Integer startcount = (Integer)request.getAttribute("startcount"); %>
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <TR onMouseover="chgTRbg(this,'on')" class="table_list" onMouseout="chgTRbg(this,'off')" bgcolor="#ffffff">
     <TD align="center">
       <input type="checkbox" name="checkid" value="<bean:write property="unitid" name="model"/>" <logic:equal value="1" name="model" property="state">disabled="disabled"</logic:equal>>
     </TD>
     <TD align="left"><bean:write name="model" property="unitname"/></TD>
     <TD align="center"><bean:write name="model" property="linkman"/></TD>
     <TD align="center"><bean:write name="model" property="job"/></TD>
     <TD align="center"><bean:write name="model" property="telephone"/></TD>
     <TD align="center">
     	<logic:equal value="0" name="model" property="state"><FONT color="red">未审核</FONT></logic:equal>
     	<logic:equal value="1" name="model" property="state">正常</logic:equal>
     	<logic:equal value="2" name="model" property="state"><FONT color="gray">禁用</FONT></logic:equal>
     </TD>
      <td align="center">
        <java2page:button url="sysUnitInfoAction0.do" property="unitid" readonly="E"/>
      </td>
     </TR>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="sysUnitInfoAction0.do?method=list" name="pagelist" />
      <input type="hidden" name="startcount" id="startcount" value="<bean:write name="startcount"/>">
    </TD>
  </TR>
</TABLE>
</FORM>
</BODY>
</html:html>
