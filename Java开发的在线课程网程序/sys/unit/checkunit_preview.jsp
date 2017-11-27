<%@page import="com.bzt.sys.bo.SysUnitInfo"%>
<%@ page contentType="text/html;charset=utf-8"%>
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
  obj.action='sysUnitInfoAction.do?method=check';
  obj.submit();
}
</SCRIPT>
<style type="text/css">
/*--友好表格样式--*/
.sAdminNice{width:600px;margin:0 auto;color:#666; font-size:12px; border-collapse:collapse; background-color:#fff;/*细线表格代码*/}
.sAdminNice td{border:1px solid #d7ebff;/*细线表格线条颜色*/ padding:8px;}
.sAdminNice th{padding:8px;background:#f7fcff;font-size:14px;border:1px solid #d7ebff;}
.sAdminNice thead, .sAdminNice th{text-align:left;}
</style>
</HEAD>
<BODY leftMargin=0 topMargin=0>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">单位信息</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/sysUnitInfoAction.do" method="post" >
       <TABLE width="600" border="0" align=center border=0 class="sAdminNice">
             <tr>
              <td class="table_edit_right" width="80">访问网址:</td>
              <td class="table_edit_left"><bean:write property="homepage"  name="model"/></td>
            </tr>
            <tr>
              <td class="table_edit_right">单位名称:</td>
              <td class="table_edit_left"><bean:write property="unitname"  name="model"/></td>
            </tr>
              <tr>
              <td class="table_edit_right">单位简称:</td>
              <td class="table_edit_left"><bean:write property="shortname"  name="model"/></td>
            </tr>
            <tr>
              <td class="table_edit_right">单位类型:</td>
              <td class="table_edit_left"><java2code:value codetype="unittype" property="type"></java2code:value></td>
            </tr>
            <%
            SysUnitInfo sui = (SysUnitInfo)request.getAttribute("model");
            if("2010".equals(sui.getType()) || "2020".equals(sui.getType())){
            %>
            <tr>
              <td class="table_edit_right">单位性质:</td>
              <td class="table_edit_left">
              <%if("2010".equals(sui.getType())){ %>
              <%if(sui.getSchooltype().indexOf("0") != -1){ %>幼教,<%} %>
              <%if(sui.getSchooltype().indexOf("1") != -1){ %>小学,<%} %>
              <%if(sui.getSchooltype().indexOf("2") != -1){ %>初中,<%} %>
              <%if(sui.getSchooltype().indexOf("3") != -1){ %>高中<%} %>
              <%} %>
              <%if("2020".equals(sui.getType())){ %>
              <%if(sui.getSchooltype().indexOf("1") != -1){ %>中职,<%} %>
              <%if(sui.getSchooltype().indexOf("2") != -1){ %>高职<%} %>
              <%} %>
              </td>
            </tr>
            <%} %>
             <tr>
              <td class="table_edit_right">联&nbsp;系&nbsp;人:</td>
              <td class="table_edit_left"><bean:write property="linkman"  name="model"/></td>
            </tr>
            <tr>
              <td class="table_edit_right">职&nbsp;&nbsp;务:</td>
              <td class="table_edit_left"><bean:write property="job"  name="model"/></td>
            </tr>
            <tr>
              <td class="table_edit_right">联系电话:</td>
              <td class="table_edit_left"><bean:write property="telephone"  name="model"/></td>
            </tr>
            <tr>
              <td class="table_edit_right">手机号码:</td>
              <td class="table_edit_left"><bean:write property="mobile"  name="model"/></td>
            </tr>
            <tr>
              <td class="table_edit_right">电子邮件:</td>
              <td class="table_edit_left"><bean:write property="email"  name="model"/></td>
            </tr>
            <tr>
              <td class="table_edit_right">QQ号码:</td>
              <td class="table_edit_left"><bean:write property="qq"  name="model"/></td>
            </tr>
           <tr>
              <td class="table_edit_right">邮政编码:</td>
              <td class="table_edit_left"><bean:write property="postcode"  name="model"/></td>
            </tr>
            <tr>
              <td class="table_edit_right">所属地区:</td>
              <td class="table_edit_left"><bean:write property="area" name="model"/></td>
            </tr>
            <tr>
              <td class="table_edit_right">联系地址:</td>
              <td class="table_edit_left"><bean:write property="address"  name="model"/></td>
            </tr>
            <tr>
              <td class="table_edit_right">计划上传微课数:</td>
              <td class="table_edit_left"><bean:write property="wkuploads"  name="model"/></td>
            </tr>
            <tr>
              <td class="table_edit_right">微课建设情况:</td>
              <td class="table_edit_left"><bean:write property="wkconstruction"  name="model"/></td>
            </tr>
             <tr height="40">
                <td colspan="2" align="center">
                    <input type="button" value="&nbsp;审&nbsp;核&nbsp;" id="btnsave" name="btnsave" class="btn_save" onClick="saveRecord()">
                    <input type="button" value="&nbsp;返&nbsp;回&nbsp;" name="btnreturn" class="btn_cancel" onClick="javascript:history.go(-1)">
                </td>
              </tr>
       </table>
         <input type="hidden" name="unitid" value="<bean:write property="unitid" name="model"/>"/>
       </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
