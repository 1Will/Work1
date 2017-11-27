<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html>
  <head>
    <title>栏目管理</title>
    <script language="JavaScript" src="/public/js/tree/mztree/mztree.js"></script>
	<link href="/public/css/tree/mztree/mztree.css" rel="stylesheet" type="text/css">
  </head>
  <body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
    <SCRIPT LANGUAGE="JavaScript">
    var tree = new MzTreeView("tree");
    tree.icons["property"] = "property.gif";
    tree.icons["css"] = "collection.gif";
    tree.icons["book"]  = "book.gif";
    tree.iconsExpand["book"] = "bookopen.gif"; //展开时对应的图片
    tree.setIconPath("/public/images/tree/mztree/"); //可用相对路径
    tree.nodes['-1_0000'] = 'text:学科年级';
 	<bean:write name="treenode" filter="false"/>//树节点
    tree.setURL("<bean:write name="rooturl"/>");
    tree.setTarget("mainRight");
    document.write(tree.toString());    
    </SCRIPT>
  </body>
</html>
