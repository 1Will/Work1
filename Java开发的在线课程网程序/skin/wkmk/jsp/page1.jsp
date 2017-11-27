<%@ page contentType="text/html;charset=utf-8"%>
<div class="page1">
  <logic:equal name="pagelist" property="hasPreviousPage" value="true">
  <a href="javascript:toPage('<bean:write name="pagelist" property="startOfPreviousPage" />')" class="left_ico"></a>
  </logic:equal>
  <a class="curss"><bean:write name="pagelist" property="curPage" /></a>
  <span class="float1">/</span>
  <a><bean:write name="pagelist" property="totalPages" /></a>
  <logic:equal name="pagelist" property="hasNextPage" value="true">
  <a href="javascript:toPage('<bean:write name="pagelist" property="startOfNextPage" />')" class="right_ico"></a>
  </logic:equal>
</div>
