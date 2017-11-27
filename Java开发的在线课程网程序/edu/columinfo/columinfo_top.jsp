<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html>
  <head>
    <title>toptree</title>
    <script language="JavaScript" src="/public/js/tree/mztree/mztree.js"></script>
    <script type="text/javascript">
     function jump(value){
      var tree=document.getElementById("tree");
      tree.src="eduColumnInfoAction.do?method=tree&subjectid="+value;     
     }
    </script>
  </head>
  <body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
  <table class="">
  <tr>
  <c:forEach items="${subjectlist }" var="sub">
  <td ><a  onclick="jump(${sub.subjectid});">${sub.subjectname}</a> </td>
  </c:forEach>
  </tr>
  </table>
  </body>
</html>