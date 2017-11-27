<%@page import="com.bzt.sys.util.Constants"%>
<%@page import="com.bzt.sys.bo.SysUnitInfo"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>

<HEAD>
<TITLE>管理员设置</TITLE>
<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />;

function addRecord(){
  document.pageForm.action="/sysUserInfoAction.do?method=beforeAdd";
  document.pageForm.submit();
}
function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="/sysUserInfoAction.do?method=list";
  document.pageForm.submit();
}
function doEdit(userid){
  document.pageForm.action="/sysUserInfoAction.do?method=beforeUpdate&&objid="+userid;
  document.pageForm.submit();
}
function addUserRole(userid){
  var path = "sysUserInfoAction.do?method=main&userid="+userid;
  var result =window.showModalDialog(path,"角色权限","dialogWidth:400px;dialogheight:550px;toolbar:no;status:no;toolbar:no;")
}
function addUserGroup(userid){
  var path = "sysUserGroupMemberAction0.do?method=usermain&userid="+userid;
  var result =window.showModalDialog(path,"所属机构","dialogWidth:400px;dialogheight:550px;toolbar:no;status:no;toolbar:no;")
}
function batchAddRecord(){
  document.pageForm.action="/sysUserInfoAction.do?method=selectFile";
  document.pageForm.submit();
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

</HEAD>

<BODY leftMargin=0 topMargin=0 scroll=auto>
<FORM name="pageForm" method=post>
<TABLE width="98%" align="center">
  <TR>
    <TD class="page_title">用户管理</TD>
  </TR>
  <TR>
    <TD height="30">
      <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
               <td >查询>>用户信息</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <%
                  String isadmin = (String)request.getAttribute("isadmin");
                  if("yes".equals(isadmin)){
                  %>
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
                     <td width="80" height="25" align="right">所属学校:</td>
                     <td >
                     <select id="school" name="unitid">
                     <option value="">请选择...</option>
                     <c:forEach items="${slist}" var="s">
                     <option value="${s.unitid }" ${unitid eq s.unitid?"selected":"" }>${s.shortname}</option>
                     </c:forEach>
                     </select>
                     </td>
                     <td width="80" align="right">用户类型:</td>
                     <td>
                     <select name="usertype">
                     	<option value="">请选择...</option>
                     	<option value="5" <logic:equal value="5" name="usertype">selected="selected"</logic:equal>>微课教师</option>
                     	<option value="6" <logic:equal value="6" name="usertype">selected="selected"</logic:equal>>微课专家</option>
                     	<option value="7" <logic:equal value="7" name="usertype">selected="selected"</logic:equal>>微课大使</option>
                     	<option value="2" <logic:equal value="2" name="usertype">selected="selected"</logic:equal>>微课学生</option>
                     </select>
                     </td>
                  </tr> 
                  <tr>
                     <td width="80" height="25" align="right">登录名:</td>
                     <td><input type="text" value='<bean:write name="loginname" />'  size="15" name="loginname"/></td>
                     <td width="80" align="right">真实姓名:</td>
                     <td><input type="text" value='<bean:write name="username" />'  size="15" name="username"/></td>
                     <td width="60" align="right">性别:</td>
                     <td><java2code:option name="sex" codetype="sex" valuename="sex" ckname="性别"/></td>
                     <!-- 
                     <td width="80" align="right">是否会员:</td>
                     <td><java2code:option name="temppass" codetype="boolean" valuename="temppass" ckname="是否会员"/></td>
                      -->
                  </tr> 
                  <%}else{ %>          
                  <tr>
                     <td width="80" height="25" align="right">登录名:</td>
                     <td><input type="text" value='<bean:write name="loginname" />'  size="15" name="loginname"/></td>
                     <td width="100" align="right">真实姓名:</td>
                     <td><input type="text" value='<bean:write name="username" />'  size="15" name="username"/></td>
                     <td width="80" align="right">性别:</td>
                     <td><java2code:option name="sex" codetype="sex" valuename="sex" ckname="性别"/></td>
                     <td width="80" align="right">用户类型:</td>
                     <td>
                     <select name="usertype">
                     	<option value="">请选择...</option>
                     	<option value="5" <logic:equal value="5" name="usertype">selected="selected"</logic:equal>>微课教师</option>
                     	<option value="6" <logic:equal value="6" name="usertype">selected="selected"</logic:equal>>微课专家</option>
                     	<option value="7" <logic:equal value="7" name="usertype">selected="selected"</logic:equal>>微课大使</option>
                     </select>
                     </td>
                     <!-- 
                     <td width="80" align="right">是否会员:</td>
                     <td><java2code:option name="temppass" codetype="boolean" valuename="temppass" ckname="是否会员"/></td>
                      -->
                  </tr>
                  <%} %>      
                </table>
              </td>
              <td width="88" class="bg_basecolor"><input type="button" value="搜索" onclick="searchRecord()" class="btn_search" /></td>
            </tr>
        </table>
    </TD>
  </TR>
  <TR>
    <TD class="page_blank"></TD>
  </TR>
  <TR>
     <TD vAlign=top align="left">
       <INPUT class="btn_all" onClick="setState(true)" type="button" value="全选" name="selectall">
       <INPUT class="btn_none"  onclick="setState(false)" type="button" value="全不选" name="selectnone">
       <%if("yes".equals(isadmin)){ %><%}%>
       <INPUT class="btn_add"  onclick="addRecord()" type="button" value="新增" name="btnadd">
       
       <INPUT class="btn_del"  onclick="delRecord('sysUserInfoAction.do?method=delBatchRecord')" type="button" value="禁用" name="btndel">
       <!--  -->
       <logic:equal value="1" name="isAdmin"></logic:equal>
       <INPUT class="btn_download"  onclick="batchAddRecord()" type="button" value="批量导入" name="btndel">
      </TD>
   </TR>
</table>

 <TABLE class="page_table" cellSpacing=1 cellPadding=1 width="98%" align="center">
	<tr>
      <TD align="center" class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
      <TD class="table_title">登录名</TD>
      <TD align="center" class="table_title" width="100">真实姓名</TD>
      <TD align="center" class="table_title" width="50">性别</TD>
      <TD align="center" class="table_title" width="70">用户类型</TD>
      <%if("yes".equals(isadmin)){ %>
      <TD align="center" class="table_title" width="80">是否推荐</TD>
      <TD align="center" class="table_title" width="60">推荐排序</TD>
      <%}else{ %>
      <TD align="center" class="table_title" width="130">注册时间</TD>
      <TD align="center" class="table_title" width="40">排序</TD>
      <%} %>
	  <TD align="center" class="table_title" width="40">状态</TD>
	  <!-- <TD align="center" class="table_title" width="60">是否会员</TD> -->
      <TD align="center" width="50" class="table_title">操作</TD>
	</tr>
	<!--循环列出所有数据-->
    <logic:iterate id="model" property="datalist" name="pagelist" >
    <TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
      <TD align="center"><input type="checkbox" name="checkid" value='<bean:write property="userid" name="model"/>'></TD>
      <TD align="left">&nbsp;<bean:write name="model" property="loginname"/></TD>
      <TD align="center"><bean:write name="model" property="username"/></TD>
      <TD align="center"><java2code:value codetype="sex" property="sex"/></TD>
      <TD align="center"><java2code:value codetype="usertype" property="usertype"/></TD>
      <%if("yes".equals(isadmin)){ %>
      <TD align="center">
      <logic:equal value="1" name="model" property="recommand"><font color="green">已推荐</font></logic:equal>
      <logic:notEqual value="1" name="model" property="recommand"><font>未推荐</font></logic:notEqual>
      </TD>
	  <TD align="center"><bean:write name="model" property="recommandno"/></TD>
      <%}else{ %>
      <TD align="center"><bean:write name="model" property="createdate"/></TD>
	  <TD align="center"><bean:write name="model" property="orderindex"/></TD>
      <%} %>
	  <TD align="center"><java2code:value codetype="state" property="state" color="red" colorvalue="2"/></TD>
	  <!-- 
	  <TD align="center">
       <logic:equal value="0" name="model" property="temppass">否</logic:equal>
       <logic:notEqual value="0" name="model" property="temppass"><font color="red">是</font></logic:notEqual>
       </TD>
        -->
      <td align="center">
        <a style="cursor:pointer;" onclick="doEdit('<bean:write property="userid" name="model"/>')" title="编辑"><img alt="编辑" border="0" src="/public/images/main/edit.gif"/></a>
        <%if("yes".equals(isadmin) ){ %>  
        <a style="cursor:pointer;" onclick="addUserRole('<bean:write name="model" property="userid"/>')" title="人员角色"><img alt="人员角色" border="0" src="/public/images/main/icon2.gif"/></a>
        <%} %>
        <%-- <a style="cursor:pointer;" onclick="addUserGroup('<bean:write name="model" property="userid"/>')" title="所属机构"><img border="0" src="/public/images/main/icon1.gif"/></a>--%>
      </td>
     </TR>
     </logic:iterate>
 </TABLE>
 <TABLE width="98%">
  <TR>
    <TD vAlign=top borderColor=#ffffff align="center">
       <java2page:pager url="sysUserInfoAction.do?method=list" name="pagelist"/>
       <input type="hidden" id="startcount" name="startcount" value="<bean:write name="startcount"/>" />
   </TD>
  </TR>
  <!--page end-->
</TABLE>

</form>
</BODY>
</html:html>


