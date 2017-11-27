<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html>
<%@ include file="../../public/jsp/style.jsp"%>
<head>
<%@ include file="../../public/jsp/meta.jsp"%>
<title>页面框架</title>
</head>
    <script type="text/javascript">
     function jump(value){
      var tree=document.getElementById("tree");
      var tds=document.getElementsByTagName("td");
      var td=document.getElementById(value);
      tree.src="eduColumnInfoAction.do?method=iframe&subjectid="+value;
      for(var i=0;i<tds.length;i++){
         tds[i].style.backgroundColor="";  
      }
      td.style.backgroundColor="#40a9ff";     
     }
    </script>
  </head>
  <body >
  <table style="height: 10%">
  <tr>
  <c:forEach items="${subjectlist }" var="sub">
  <td  id="${sub.subjectid }" style="text-align: center;width: 80px; font-size: 14px; font-weight: bold;"><a style="cursor: pointer;" onclick="jump(${sub.subjectid});">${sub.subjectname}</a> </td>
  </c:forEach>
  </tr>
  </table>
   <iframe name="tree" id="tree"  src="eduColumnInfoAction.do?method=iframe" width="100%" height="100%;" scrolling="no" frameborder="0"></iframe>
</body>
</html>