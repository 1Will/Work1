<%@page import="com.util.search.PageList"%>
<%@ page contentType="text/html;charset=utf-8"%>
<div class="page">
    <ul>
      <%
      PageList page_list = (PageList)request.getAttribute("pagelist");
      %>
      <%if(page_list.isHasPreviousPage()){ %>
      <li><a href="javascript:gotoPageSize(<%=page_list.getStartOfPreviousPage() %>)">上一页</a></li>
      <%} %>
      <%
      if(page_list.getTotalPages() > 10){ 
      	  if(page_list.getCurPage() > 5){
      		  int _start = Long.valueOf(page_list.getCurPage()).intValue() - 5;
      		  int _end = _start + 9;
      		  if(page_list.getTotalPages() < _end){
      			_end = Long.valueOf(page_list.getTotalPages()).intValue();
      		  }
      		for(int i=_start; i<=_end; i++){
      %>
      <li><a href="javascript:gotoPageSize(<%=(i-1)*page_list.getPageSize() %>)" <%if(page_list.getCurPage() == i){ %>class="curss"<%} %>><%=i %></a></li>
      <%
      		}
      }else{ 
    	  for(int i=1; i<=10; i++){
      %>
      <li><a href="javascript:gotoPageSize(<%=(i-1)*page_list.getPageSize() %>)" <%if(page_list.getCurPage() == i){ %>class="curss"<%} %>><%=i %></a></li>
      <%
    	  }
      }}else{ 
          for(int i=1; i<=page_list.getTotalPages(); i++){
      %>
      <li><a href="javascript:gotoPageSize(<%=(i-1)*page_list.getPageSize() %>)" <%if(page_list.getCurPage() == i){ %>class="curss"<%} %>><%=i %></a></li>
      <%}} %>
      <%if(page_list.isHasNextPage()){ %>
      <li><a href="javascript:gotoPageSize(<%=page_list.getStartOfNextPage() %>)">下一页</a></li>
      <%} %>
    </ul>
</div>
<script type="text/javascript">
function gotoPageSize(size){
	document.getElementById('startcount').value = size;
	document.searchForm.action = "/search.action";
	document.searchForm.submit();
}
</script>