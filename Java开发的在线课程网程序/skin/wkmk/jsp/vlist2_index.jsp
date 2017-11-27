<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.edu.bo.EduKnopointInfo"%>
<%@page import="java.util.Map"%>
<%@page import="com.bzt.edu.bo.EduGradeInfo"%>
<%@page import="com.bzt.edu.bo.EduSubjectInfo"%>
<%@page import="com.bzt.edu.bo.EduXueduanInfo"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-微课</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/beikestyle.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/page.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %>">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %>">
<script type="text/javascript">
function SetCwinHeight(){
	var iframeid=document.getElementById("listFrame");
	if (document.getElementById){
	   if (iframeid && !window.opera){
		   if (iframeid.contentDocument && iframeid.contentWindow.document.documentElement.scrollHeight){
		     var height1 = iframeid.contentWindow.document.documentElement.scrollHeight
		   	 iframeid.height = height1>70?height1:70;
		   }else if(iframeid.Document && iframeid.Document.body.scrollHeight){
		     var height2 = iframeid.Document.body.scrollHeight;
		  	 iframeid.height = height2>70?height2:70;
		   }
	   }
	}
}
function gotoUrl0(xueduan, cxueduan, orderbytype){
	document.pageForm.action = '/v-vlist-x' + xueduan + '-cx' + cxueduan + '-s0-g0-' + orderbytype + '.htm';
	document.pageForm.submit();
}
function gotoUrl(xueduan, cxueduan, subject, grade, version, orderbytype){
	document.pageForm.action = '/v-vlist1-x' + xueduan + '-cx' + cxueduan + '-s' + subject + '-g' + grade + '-v' + version + '-c0-' + orderbytype + '.htm';
	document.pageForm.submit();
}
</script>
</head>

<body>
<%@ include file="top.jsp"%>
<form name="pageForm" method="post">
<div id="tab_nav">
<div class="clearfix">
  <h2>全部微课</h2>
  <%
  String xueduan = (String)request.getAttribute("xueduan");
  String cxueduan = (String)request.getAttribute("cxueduan");
  String subject = (String)request.getAttribute("subject");
  String grade = (String)request.getAttribute("grade");
  String column = (String)request.getAttribute("column");
  List subjectList = (List)request.getAttribute("subjectList");
  List gradeList = (List)request.getAttribute("gradeList");
  %>
  <dl class="dl_tab">
    <dt>学段：</dt>
    <dd>
      <a href="javascript:gotoUrl0('0', '0', '${orderbytype }')" <%if("0".equals(xueduan)){ %>class="curr_all"<%} %>>全部</a>
      <a href="javascript:gotoUrl('1', '0', '0', '0', '0', '${orderbytype }')" <%if("1".equals(xueduan)){ %>class="curr_all"<%} %>>小学教育</a>
      <a href="javascript:gotoUrl('2', '0', '0', '0', '0', '${orderbytype }')" <%if("2".equals(xueduan)){ %>class="curr_all"<%} %>>初中教育</a>
      <a href="javascript:gotoUrl('3', '0', '0', '0', '0', '${orderbytype }')" <%if("3".equals(xueduan)){ %>class="curr_all"<%} %>>高中教育</a>
      <a href="javascript:gotoUrl0('4', '41', '${orderbytype }')" <%if("41".equals(cxueduan)){ %>class="curr_all"<%} %>>中职教育</a>
      <a href="javascript:gotoUrl0('4', '42', '${orderbytype }')" <%if("42".equals(cxueduan)){ %>class="curr_all"<%} %>>高职教育</a>
      <a href="javascript:gotoUrl0('5', '0', '${orderbytype }')" <%if("5".equals(xueduan)){ %>class="curr_all"<%} %>>高等教育</a>
      <a href="javascript:gotoUrl0('6', '0', '${orderbytype }')" <%if("6".equals(xueduan)){ %>class="curr_all"<%} %>>继续教育</a>
    </dd>
  </dl>
  <dl class="dl_tab">
    <dt>学科：</dt>
    <dd>
      <a href="javascript:gotoUrl('${xueduan }', '${cxueduan }', '0', '0', '0', '${orderbytype }')" <%if("0".equals(subject)){ %>class="curr_all"<%} %>>全部</a>
      <%
      EduSubjectInfo esi = null;
      for(int i=0, size=subjectList.size(); i<size; i++){
    	  esi = (EduSubjectInfo)subjectList.get(i);
	  %>
	  <a href="javascript:gotoUrl('${xueduan }', '${cxueduan }', '<%=esi.getSubjectid() %>', '0', '0', '${orderbytype }')" <%if(esi.getSubjectid().toString().equals(subject)){ %>class="curr_all"<%} %>><%=esi.getSubjectname() %></a>
	  <%} %>
    </dd>
  </dl>
  <dl class="dl_tab" style="border-bottom: none;">
    <dt>年级：</dt>
    <dd>
      <a href="javascript:gotoUrl('${xueduan }', '${cxueduan }', '${subject }', '0', '0', '${orderbytype }')" <%if("0".equals(grade)){ %>class="curr_all"<%} %>>全部</a>
      <%
      EduGradeInfo egi = null;
      for(int i=0, size=gradeList.size(); i<size; i++){
    	  egi = (EduGradeInfo)gradeList.get(i);
	  %>
	  <a href="javascript:gotoUrl('${xueduan }', '${cxueduan }', '${subject }', '<%=egi.getGradeid() %>', '0', '${orderbytype }')" <%if(egi.getGradeid().toString().equals(grade)){ %>class="curr_all"<%} %>><%=egi.getGradename() %></a>
	  <%} %>
	  <a>|</a><a href="javascript:gotoUrl('${xueduan }', '${cxueduan }', '${subject }', '00', '0', '${orderbytype }')" <%if("00".equals(grade)){ %>class="curr_all"<%} %>>知识点</a>
    </dd>
  </dl>
  <div class="list clearfix">
    <div class="list_right">
      <div class="slide fl">         
        <!-- 添加滚动条开始-->
        <div class="Scroller-Container">
          <div class="slideContent tree-list">
            <h3>
              <li id="ta1"><a href="javascript:gotoUrl('${xueduan }', '${cxueduan }', '${subject }', '00', '0', '${orderbytype }')" class="link">知识点</a></li>
            </h3>
            <div class="tw_mf_tree float1" id="co1">
              <ul style="-moz-user-select: none;" class="ztree">
                <%
                List parentList = (List)request.getAttribute("parentList");
                Map map1 = (Map)request.getAttribute("map1");
                Map map2 = (Map)request.getAttribute("map2");
                List list1 = null;
                List list2 = null;
                EduKnopointInfo eki = null;
                int temp = 0;
                for(int i=0, size=parentList.size(); i<size; i++){
                	eki = (EduKnopointInfo)parentList.get(i);
                	temp++;
                %>
                <li>
                  <a href="javascript:gotoLink('<%=temp %>', '<%=eki.getKnopointid() %>')"><span class="leave" id="k_li_<%=temp %>" <%if(eki.getKnopointid().toString().equals(column)){ %>style="#40a8ff"<%} %>><%=eki.getTitle() %></span></a>
                  <%
                  list1 = (List)map1.get(eki.getKnopointno());
                  if(list1 != null && list1.size() > 0){
                  %>
                  <ul class="ztree_c">
                    <%
                    for(int m=0, n=list1.size(); m<n; m++){
                    	eki = (EduKnopointInfo)list1.get(m);
                    	temp++;
                    %>
                    <li>
                      <a href="javascript:gotoLink('<%=temp %>', '<%=eki.getKnopointid() %>')" class="level1" id="k_li_<%=temp %>" <%if(eki.getKnopointid().toString().equals(column)){ %>style="#40a8ff"<%} %>><span class="font_3"><%=eki.getTitle() %></span></a>
                      <%
                      list2 = (List)map2.get(eki.getParentno());
                      if(list2 != null && list2.size() > 0){
                      %>
                      <ul class="ztree_t">
                        <%
	                    for(int x=0, y=list2.size(); x<y; x++){
	                    	eki = (EduKnopointInfo)list2.get(x);
	                    	temp++;
	                    %>
                        <li><a href="javascript:gotoLink('<%=temp %>', '<%=eki.getKnopointid() %>')" class="level1" id="k_li_<%=temp %>" <%if(eki.getKnopointid().toString().equals(column)){ %>style="#40a8ff"<%} %>><span><%=eki.getTitle() %></span></a></li>
                        <%} %>
                      </ul>
                      <%} %>
                    </li>
                    <%} %>
                  </ul>
                  <%} %>
                </li>
                <%} %>
              </ul>
            </div>
          </div>
        </div>
        <!-- 添加滚动条结束-->
      </div>
    </div>
    <iframe id="listFrame" name="listFrame" src="/v.bo?method=vlist2f&xueduan=${xueduan }&subject=${subject }&column=${column }&orderbytype=${orderbytype }" width="900" height="70" onload="SetCwinHeight()" frameborder="0" scrolling=no></iframe>
  </div>
</div>
</div>
</form>
<script type="text/javascript">
function gotoLink(id, knopointid){
	var count = <%=temp %>;
	for(var i=1; i<=count; i++){
		document.getElementById('k_li_'+i).style.color = '#444444';
	}
	if(document.getElementById('k_li_'+id)){
		document.getElementById('k_li_'+id).style.color = '#40a8ff';
	}
	document.getElementById('listFrame').src = '/v.bo?method=vlist2f&xueduan=${xueduan }&subject=${subject }&orderbytype=${orderbytype }&column='+knopointid;
}
</script>
<%@ include file="footer.jsp"%>
</body>
</html>