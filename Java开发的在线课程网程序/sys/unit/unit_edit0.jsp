<%@page import="com.bzt.gpw.bo.GpwAreaInfo"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.util.string.encode.Encode"%>
<%@page import="com.bzt.sys.bo.SysUnitInfo"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>

<HEAD>
<TITLE>学校信息</TITLE>
<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>

<SCRIPT language=javascript>
function saveRecord(){
  obj = document.all("sysUnitInfoActionForm");
  if(autoCheckForm(obj)==false){
    return false;
  }
  
  var domain = document.getElementById("sysUnitInfo.domain").value;
  if(domain != ''){
	  var regexp=/^[a-zA-Z0-9_-]+$/;
	  if(!regexp.test(domain)){
		  alert("微课联盟学校首页名称格式不对！");
		  return false;
	  }else{
		  var flag = true;
		  var unitid = '<bean:write property="unitid" name="model"/>';
		  new Ajax.Request(
				"/sysUnitInfoAction.do?method=checkDomain&domain=" + domain + "&unitid=" + unitid + "&ram=" + Math.random(),
				{
				method:"get",
				asynchronous:false,//true为异步请求
				onComplete:function(xhr){
					var responseObj = xhr.responseText;
					if(responseObj != '0'){
						alert("微课联盟学校首页名称已存在，请换个名称试试！");
						flag = false;
					}
				}
				}
			);
		  if(!flag){
			  return false; 
		  }
	  }
  }
  
  	var utype = document.getElementById('utype').value;
	if(utype == '2010'){
		if(!(document.getElementById('schooltype2010_0').checked || document.getElementById('schooltype2010_1').checked || document.getElementById('schooltype2010_2').checked || document.getElementById('schooltype2010_3').checked)){
			alert("请选择学校性质!");
			return false;
		}
	}
	if(utype == '2020'){
		if(!(document.getElementById('schooltype2020_1').checked || document.getElementById('schooltype2020_2').checked)){
			alert("请选择学校性质!");
			return false;
		}
	}

  document.getElementById("btnsave").disabled = true;
  document.getElementById("sysUnitInfo.descript").value=document.getElementById("editor0").contentWindow.getHTML();
  obj.action='sysUnitInfoAction0.do?method=<bean:write name="act"/>';
  obj.submit();
}
function uploadPhoto(id){
  var retValue=showModalDialog('/sysImageUploadAction.do?method=uploadimage&savepath=unit&pathtype=ID','缩略图',"dialogWidth:350px;dialogHeight:200px;scroll=no;border=thin;help=0;status=no");
  if(retValue!=null){
     if(retValue[0] != ''){
  	 	alert(retValue[0]);
  		return false;
  	 }
     document.getElementById(id).src="/upload_dir/"+retValue[1];
     document.getElementById('sysUnitInfo.'+id).value="/upload_dir/"+retValue[1];
  }
}
function selectArea(areano, tag){
	if(areano != ''){
		new Ajax.Request(
		"/sysUnitInfoAction.do?method=selectArea&areano=" + areano + "&tag=" + tag + "&ram=" + Math.random(),
		{
		method:"get",
		asynchronous:false,//true为异步请求
		onComplete:function(xhr){
			var responseObj = xhr.responseText;
			if(tag == '0'){
				var str = responseObj.split(";");
				document.getElementById('areano2').innerHTML = str[0];
				document.getElementById('areano3').innerHTML = str[1];
			}else{
				document.getElementById('areano3').innerHTML = responseObj;
			}
		}
		}
	  );
	}
}
function changeSchoolType(unittype){
	document.getElementById("utype").value = unittype;
	if(unittype == '2010'){
		document.getElementById("schooltype_2010").style.display = "";
		document.getElementById("schooltype_2020").style.display = "none";
	}else if(unittype == '2020'){
		document.getElementById("schooltype_2010").style.display = "none";
		document.getElementById("schooltype_2020").style.display = "";
	}else{
		document.getElementById("schooltype_2010").style.display = "none";
		document.getElementById("schooltype_2020").style.display = "none";
	}
}
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0>
<%SysUnitInfo model = (SysUnitInfo)request.getAttribute("model"); %>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">学校信息</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/sysUnitInfoAction0.do" method="post" >
       <TABLE width="100%" border="0" align=center border=0>
       		<logic:equal value="1" name="model" property="product">
             <tr>
              <td class="table_edit_right" width="25%">&nbsp;</td>
              <td class="table_edit_left" width="75%" style="color:green;">当前学校有意向建立'微课平台'来发布和管理学校自己的微课。</td>
            </tr>
            </logic:equal>
            <%-- 
            <tr>
              <td class="table_edit_right">所属地区:</td>
              <td class="table_edit_left"><java2code:option codetype="area" name="sysUnitInfo.area" property="area"></java2code:option></td>
            </tr>
            --%>
            <tr>
              <td class="table_edit_right">学校名称:</td>
              <td class="table_edit_left"><input type="text" class="input" CK_NAME="学校名称" CK_TYPE="NotEmpty" size="40" name="sysUnitInfo.unitname" value="<bean:write property="unitname"  name="model"/>">*</td>
            </tr>
              <tr>
              <td class="table_edit_right">学校简称:</td>
              <td class="table_edit_left"><input type="text" class="input" CK_NAME="学校简称" CK_TYPE="NotEmpty" size="40" name="sysUnitInfo.shortname" value="<bean:write property="shortname"  name="model"/>">*</td>
            </tr>
              <tr>
              <td class="table_edit_right">英文名称:</td>
              <td class="table_edit_left"><input type="text" class="input" size="40" name="sysUnitInfo.enname" value="<bean:write property="enname"  name="model"/>"></td>
            </tr>
            <tr>
              <td class="table_edit_right">学校网站:</td>
              <td class="table_edit_left">
                <input type="text" class="input" size="40" name="sysUnitInfo.homepage"  value="<bean:write property="homepage"  name="model"/>">
              </td>
            </tr>
             <tr>
              <td class="table_edit_right"></td>
              <td class="table_edit_left">
                <font color="red"> (请以http://开头,例:http://www.edutech.com.cn)</font>
              </td>
            </tr>
            <tr>
              <td class="table_edit_right">微课首页:</td>
              <td class="table_edit_left">
                http://www.wkmk.cn/<input type="text" class="input" size="20" maxlength="20" name="sysUnitInfo.domain" id="sysUnitInfo.domain" value="<bean:write property="domain"  name="model"/>">
                <font id="tip_div" color="green">由大小写英文字母、数字、"_"、"-"组成。</font>
                <font id="error_div" style="color:red;display:none;">当前微课联盟学校首页名称已被占用!</font>
              </td>
            </tr>
             <tr>
              <td class="table_edit_right"></td>
              <td class="table_edit_left">
                <font color="green"> (可自定义微课联盟学校首页访问地址：如：师科阳光=http://www.wkmk.cn/skyg)</font>
              </td>
            </tr>
            <%-- 
            <tr>
              <td class="table_edit_right">学校类别:</td>
              <td class="table_edit_left"><java2code:option codetype="unittype" name="sysUnitInfo.type" property="type"></java2code:option></td>
            </tr>
            <tr>
              <td class="table_edit_right">学校性质:</td>
              <td class="table_edit_left"><java2code:option codetype="ctype" name="sysUnitInfo.ctype" property="ctype"></java2code:option></td>
            </tr>
            --%>
            <tr>
              <td class="table_edit_right">学校类型:</td>
              <td class="table_edit_left">
              <java2code:option codetype="unittype" name="sysUnitInfo.type" property="type" onchange="changeSchoolType(this.value)"></java2code:option>
              <input type="hidden" name="utype" id="utype" value="<bean:write property="type"  name="model"/>"/>
              </td>
            </tr>
            <%
            SysUnitInfo sui = (SysUnitInfo)request.getAttribute("model");
            String schooltype = Encode.nullToBlank(sui.getSchooltype());
            %>
            <tr id="schooltype_2010" <%if(!"2010".equals(sui.getType())){ %>style="display:none;"<%} %>>
              <td class="table_edit_right">学校性质:</td>
              <td class="table_edit_left">
              	<input type="checkbox" name="schooltype2010" value="0" id="schooltype2010_0" <%if(schooltype.indexOf("0") != -1){ %>checked="checked"<%} %>/>幼教&nbsp;&nbsp;&nbsp;
              	<input type="checkbox" name="schooltype2010" value="1" id="schooltype2010_1" <%if(schooltype.indexOf("1") != -1){ %>checked="checked"<%} %>/>小学&nbsp;&nbsp;&nbsp;
				<input type="checkbox" name="schooltype2010" value="2" id="schooltype2010_2" <%if(schooltype.indexOf("2") != -1){ %>checked="checked"<%} %>/>初中&nbsp;&nbsp;&nbsp;
				<input type="checkbox" name="schooltype2010" value="3" id="schooltype2010_3" <%if(schooltype.indexOf("3") != -1){ %>checked="checked"<%} %>/>高中
              </td>
            </tr>
            <tr id="schooltype_2020" <%if(!"2020".equals(sui.getType())){ %>style="display:none;"<%} %>>
              <td class="table_edit_right">学校性质:</td>
              <td class="table_edit_left">
              	<input type="checkbox" name="schooltype2020" value="1" id="schooltype2020_1" <%if(schooltype.indexOf("1") != -1){ %>checked="checked"<%} %>/>中职&nbsp;&nbsp;&nbsp;
				<input type="checkbox" name="schooltype2020" value="2" id="schooltype2020_2" <%if(schooltype.indexOf("2") != -1){ %>checked="checked"<%} %>/>高职
              </td>
            </tr>
             <tr>
              <td class="table_edit_right">联&nbsp;系&nbsp;人:</td>
              <td class="table_edit_left">
                <input type="text" size="20" name="sysUnitInfo.linkman" class="input" CK_NAME="联系人" CK_TYPE="NotEmpty" value="<bean:write property="linkman"  name="model"/>">*
              </td>
            </tr>
             <tr>
              <td class="table_edit_right">职&nbsp;&nbsp;务:</td>
              <td class="table_edit_left">
                <input type="text" size="20" name="sysUnitInfo.job" class="input" CK_NAME="职务" CK_TYPE="NotEmpty" value="<bean:write property="job"  name="model"/>">*
              </td>
            </tr>
            <tr>
              <td class="table_edit_right">联系电话:</td>
              <td class="table_edit_left">
                <input type="text" size="40" name="sysUnitInfo.telephone" class="input" CK_NAME="联系电话" CK_TYPE="NotEmpty,Telphone" value="<bean:write property="telephone"  name="model"/>">*
              </td>
            </tr>
            <tr>
              <td class="table_edit_right">手机号码:</td>
              <td class="table_edit_left">
                <input type="text" size="40" name="sysUnitInfo.mobile" class="input" value="<bean:write property="mobile"  name="model"/>">
              </td>
            </tr>
            <tr>
              <td class="table_edit_right">传真号码:</td>
              <td class="table_edit_left">
                <input type="text" size="40" name="sysUnitInfo.fax" class="input" value="<bean:write property="fax"  name="model"/>">
              </td>
            </tr>
            <tr>
              <td class="table_edit_right">电子邮件:</td>
              <td class="table_edit_left">
                <input type="text" size="40" name="sysUnitInfo.email" class="input" CK_NAME="电子邮件" CK_TYPE="EMail" value="<bean:write property="email"  name="model"/>">
              </td>
            </tr>
            <tr>
              <td class="table_edit_right">QQ号码:</td>
              <td class="table_edit_left">
                <input type="text" size="40" name="sysUnitInfo.qq" class="input" value="<bean:write property="qq"  name="model"/>">
              </td>
            </tr>
           <tr>
              <td class="table_edit_right">邮政编码:</td>
              <td class="table_edit_left">
                <input type="text" size="15" name="sysUnitInfo.postcode" class="input" maxlength="6" CK_NAME="邮政编码" CK_TYPE="NotEmpty,Postcode" value="<bean:write property="postcode"  name="model"/>">*
              </td>
            </tr>
            <tr>
              <td class="table_edit_right">所属地区:</td>
              <td class="table_edit_left">
                <span id="areano1" style="float:left;">
				<select name="areano1" onchange="selectArea(this.value, '0')" style="padding:3px;">
				  <%
				  	String areano = "";
				  	if(sui.getArea() != null) areano = sui.getArea();
				  	List arealist1 = (List)request.getAttribute("arealist1");
				    GpwAreaInfo areaInfo = null;
				  	for(int i=0; i<arealist1.size(); i++){
				  		areaInfo = (GpwAreaInfo)arealist1.get(i);
				  %>
				  	<option value="<%=areaInfo.getAreano() %>" <%if(areano.substring(0, 2).equals(areaInfo.getRgno().substring(0, 2))){ %>selected="selected"<%} %>><%=areaInfo.getAreaname() %></option>
				  <%} %>
				  </select>
				  </span>
				  <span id="areano2" style="float:left;margin-left:8px;">
					  <select name="areano2" onchange="selectArea(this.value, '1')" style="padding:3px;">
						  <%
						  	List arealist2 = (List)request.getAttribute("arealist2");
						    List arealist3 = (List)request.getAttribute("arealist3");
						  	for(int i=0; i<arealist2.size(); i++){
						  		areaInfo = (GpwAreaInfo)arealist2.get(i);
						  %>
						    <%if(arealist3 != null && arealist3.size() > 0){ %>
						  	<option value="<%=areaInfo.getAreano() %>" <%if(areano.substring(0, 4).equals(areaInfo.getRgno().substring(0, 4))){ %>selected="selected"<%} %>><%=areaInfo.getAreaname() %></option>
						  	<%}else{ %>
						  	<option value="<%=areaInfo.getAreano() %>" <%if(areano.equals(areaInfo.getRgno())){ %>selected="selected"<%} %>><%=areaInfo.getAreaname() %></option>
						  	<%} %>
						  <%} %>
					  </select>
				  </span>
				  <span id="areano3" style="float:left;margin-left:8px;">
					  <%
				  	  if(arealist3 != null && arealist3.size() > 0){
				  	  %>
					  <select name="sysUnitInfo.area" style="padding:3px;">
		         	 	 <%
						  	for(int i=0; i<arealist3.size(); i++){
						  		areaInfo = (GpwAreaInfo)arealist3.get(i);
						  %>
						  	<option value="<%=areaInfo.getRgno() %>" <%if(areano.equals(areaInfo.getRgno())){ %>selected="selected"<%} %>><%=areaInfo.getAreaname() %></option>
						  <%} %>
		         	  </select>
		         	  <%} %>
				</span>
              </td>
            </tr>
            <tr>
              <td class="table_edit_right">联系地址:</td>
              <td class="table_edit_left">
                <input type="text" size="40" name="sysUnitInfo.address" class="input" CK_NAME="联系地址" CK_TYPE="NotEmpty" value="<bean:write property="address"  name="model"/>">*
              </td>
            </tr>
            <logic:equal value="updateSave" name="act">
            <tr>
              <td class="table_edit_right">计划上传微课数:</td>
              <td class="table_edit_left"><bean:write property="wkuploads"  name="model"/></td>
            </tr>
            <tr>
              <td class="table_edit_right">微课建设情况:</td>
              <td class="table_edit_left"><bean:write property="wkconstruction"  name="model"/></td>
            </tr>
            </logic:equal>
              <tr>
              <td class="table_edit_right">ICP备案:</td>
              <td class="table_edit_left">
                <input type="text" size="40" name="sysUnitInfo.icp" class="input" value="<bean:write property="icp"  name="model"/>">
              </td>
            </tr>
            <tr>
              <td class="table_edit_right">排&nbsp;&nbsp;序:</td>
              <td class="table_edit_left">
                <input type="text" size="15" name="sysUnitInfo.orderindex" class="input" maxlength="6" CK_NAME="排序" CK_TYPE="NotEmpty" value="<bean:write property="orderindex"  name="model"/>">*
              </td>
            </tr>
            <tr>
              <td class="table_edit_right">被赞次数:</td>
              <td class="table_edit_left">
                <bean:write property="praise"  name="model"/>次
              </td>
            </tr>
            <tr>
            <td class="table_edit_right">是否推荐:</td>
            <td class="table_edit_left">
            	<select name="sysUnitInfo.recommand">
            		<option value="0" <logic:equal value="0" name="model" property="recommand">selected="selected"</logic:equal>>否</option>
            		<option value="1" <logic:equal value="1" name="model" property="recommand">selected="selected"</logic:equal>>是</option>
            	</select>
            </td>
          </tr>
            <logic:equal value="updateSave" name="act">
          <tr>
            <td class="table_edit_right">学校状态:</td>
            <td class="table_edit_left">
            	<select name="sysUnitInfo.state">
            		<option value="1" <logic:equal value="0" name="model" property="state">selected="selected"</logic:equal>>待审核</option>
            		<option value="1" <logic:equal value="1" name="model" property="state">selected="selected"</logic:equal>>正常</option>
            		<option value="2" <logic:equal value="2" name="model" property="state">selected="selected"</logic:equal>>禁用</option>
            	</select>
            </td>
          </tr>
          </logic:equal>
          <logic:notEqual value="updateSave" name="act">
          <input type="hidden" name="sysUnitInfo.state" value="<bean:write property="state" name="model"/>"/>
          </logic:notEqual>
            <tr>
              <td class="table_edit_right">缩略图:</td>
              <td class="table_edit_left">
                <img src="<bean:write property="sketch"  name="model"/>" title="点击修改图片,建议图片大小:220*125" width="220" height="125" border="1" id=sketch onclick="uploadPhoto('sketch')">&nbsp;<font color="#dd3333">建议图片大小:220*125</font>
                <input type="hidden" id="sysUnitInfo.sketch" name="sysUnitInfo.sketch"  value='<bean:write property="sketch"  name="model"/>'>
              </td>
            </tr>
            <tr>
              <td class="table_edit_right">LOGO图片:</td>
              <td class="table_edit_left">
                <img src="<bean:write property="logo"  name="model"/>" title="点击修改图片,建议图片大小:198*63" width="198" height="63" border="1" id=logo onclick="uploadPhoto('logo')">&nbsp;<font color="#dd3333">建议图片大小:198*63</font>
                <input type="hidden" id="sysUnitInfo.logo" name="sysUnitInfo.logo"  value='<bean:write property="logo"  name="model"/>'>
              </td>
            </tr>
            <tr>
              <td class="table_edit_right">banner图片:</td>
              <td class="table_edit_left">
                <img src="<bean:write property="banner"  name="model"/>" title="点击修改图片,建议图片大小:650*340" width="325" height="170" border="1" id=banner onclick="uploadPhoto('banner')">&nbsp;<font color="#dd3333">建议图片大小:650*340</font>
                <input type="hidden" id="sysUnitInfo.banner" name="sysUnitInfo.banner"  value='<bean:write property="banner"  name="model"/>'>
              </td>
            </tr>
            <tr>
              <td class="table_edit_right">学校简介:</td>
              <td class="table_edit_left">
                <textarea name="sysUnitInfo.keywords" style="width:550px;height:120px;" wrap="physical"><bean:write property="keywords"  name="model"/></textarea>
              </td>
            </tr>
            <tr>
             <td class="table_edit_right">详细描述：</td>
            <td class="table_edit_left" colspan="2">
               <IFRAME ID="editor0" src="/ewebeditor/ewebeditor.htm?id=sysUnitInfo.descript&style=blue&cusdir=<%=Encode.nullToBlank(session.getAttribute("cusdir")) %>" frameborder="0" scrolling="no" width="550" height="300"></IFRAME>
               <input type="hidden" name="sysUnitInfo.descript" id="sysUnitInfo.descript" value="<%=Encode.convertQuot(Encode.nullToBlank(model.getDescript()))%>"/>
            </td>
           </tr>
             <tr height="40">
                <td colspan="2" align="center">
                    <input type="button" value="&nbsp;保&nbsp;存&nbsp;" id="btnsave" name="btnsave" class="btn_save" onClick="saveRecord()">
                    <input type="button" value="&nbsp;返&nbsp;回&nbsp;" name="btnreturn" class="btn_cancel" onClick="javascript:history.go(-1)">
                </td>
              </tr>
       </table>
        <input type="hidden" name="sysUnitInfo.unitid" value="<bean:write property="unitid"  name="model"/>"/>
        <input type="hidden" name="sysUnitInfo.unitno" value="<bean:write property="unitno"  name="model"/>"/>
        <input type="hidden" name="sysUnitInfo.wkuploads" value="<bean:write property="wkuploads"  name="model"/>"/>
        <input type="hidden" name="sysUnitInfo.wkconstruction" value="<bean:write property="wkconstruction"  name="model"/>"/>
        <input type="hidden" name="sysUnitInfo.parentno" value="<bean:write property="parentno"  name="model"/>"/>
        <input type="hidden" name="sysUnitInfo.createdate" value="<bean:write property="createdate"  name="model"/>"/>
        <input type="hidden" name="sysUnitInfo.updatetime" value="<bean:write property="updatetime"  name="model"/>"/>
        <input type="hidden" name="sysUnitInfo.hits" value="<bean:write property="hits"  name="model"/>"/>
        <input type="hidden" name="sysUnitInfo.product" value="<bean:write property="product"  name="model"/>"/>
        <input type="hidden" name="sysUnitInfo.praise" value="<bean:write property="praise"  name="model"/>"/>
        <input type="hidden" name="sysUnitInfo.ctype" value="<bean:write property="ctype"  name="model"/>"/>
        
        <input type="hidden" name="startcount" value="<bean:write name="startcount"/>">
        <input type="hidden" name="unitname" value="<bean:write name="unitname"/>">
       </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
