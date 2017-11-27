<%@ page contentType="text/html;charset=utf-8"%>
<div id="page" style="padding-top:0px;">
<span>共&nbsp;<bean:write name="pagelist" property="totalCount" />&nbsp;条记录&nbsp;&nbsp;</span>
<logic:equal name="pagelist" property="hasPreviousPage" value="true">
 <a href="javascript:toPage('0');" class="one">首页</a><a href="javascript:toPage('<bean:write name="pagelist" property="startOfPreviousPage" />');" class="one">上一页</a>
</logic:equal>
<logic:equal name="pagelist" property="hasPreviousPage" value="false">
 <span class="disabled">首页</span><span class="disabled">上一页</span>
</logic:equal>
<logic:equal name="pagelist" property="hasNextPage" value="true">
 <a href="javascript:toPage('<bean:write name="pagelist" property="startOfNextPage" />');" class="one">下一页</a><a href="javascript:toPage('<bean:write name="pagelist" property="startOfLastPage" />')" class="one" style="margin-right:10px;">末页</a>
</logic:equal>
<logic:equal name="pagelist" property="hasNextPage" value="false">
 <span class="disabled">下一页</span><span class="disabled" style="margin-right:10px;">末页</span>
</logic:equal>
<span>&nbsp;第&nbsp;<bean:write name="pagelist" property="curPage" />&nbsp;页，共&nbsp;<bean:write name="pagelist" property="totalPages" />&nbsp;页&nbsp;&nbsp;转到</span><span><input name="gotopagesize" id="gotopagesize" type="text" value="" class="two" style="text-align:left;"/></span><a class="two" style="cursor:pointer;background-color:#40a8ff;color:#fff !important;" onclick="GotoPage(document.getElementById('gotopagesize').value)">确定</a>
</div>
