<%
List vodFilmLetList = (List)request.getAttribute("vodFilmLetList");
if(vodFilmLetList != null && vodFilmLetList.size() > 1){
%>
<%@ page contentType="text/html;charset=utf-8"%>
<div class="catalog back_1">
  <h3>课程目录</h3>
  <ul class="clearfix">
    <%
	VodFilmLet vodFilmLet = (VodFilmLet)request.getAttribute("vodFilmLet");
	VodFilmLet let = null;
	for(int i=0, size=vodFilmLetList.size(); i<size; i++){
		let = (VodFilmLet)vodFilmLetList.get(i);
	%>
    <li><a href="/v-play1-<%=let.getUnitid() %>-<%=let.getFilmletid() %>.htm" target="_blank" title="<%=let.getFilmletname() %>" <%if(let.getFilmletid().intValue() == vodFilmLet.getFilmletid().intValue()){%>class="color_6"<%} %>><%=i+1 %>.<%=SubStringDirectiveModel.subString(let.getFilmletname(), 10, "...") %></a></li>
    <%} %>
  </ul>
</div>
<%}%>
