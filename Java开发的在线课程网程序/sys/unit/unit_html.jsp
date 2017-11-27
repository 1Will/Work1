<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>

<HEAD>
<TITLE>站点信息</TITLE>
<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<script type="text/javascript">
function initHtml(){
	var str="请谨慎此操作，真的要继续吗？";
	if(confirm(str)){
		document.location = '/initHtml.do';
	}
}
</script>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title" style="color:green;">站点静态页面重新初始化!</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
       <input type="button" value="&nbsp;确&nbsp;定&nbsp;" id="btnsave" name="btnsave" class="btn_save" onClick="initHtml()">
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
