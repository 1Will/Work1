 <#include "../../includes/macros2.ftl" />
 <#include "/javascripts/selectAll.js" />
<@framePage title="供应商附加信息">
 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
 <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
              <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
                <th>供应商编号</th>
                <th>供应商名称</th>
			 	<th>主要联系人</th>
			 	<th>联系电话</th>
			 	<th>传真号码</th>
			 	<th>网址</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/pro/supplier/editSupplier.html',600,300);return false;">GYS001</a></td>
				<td style="text-align:left">合肥永君数码</td>
				<td style="text-align:left">ls</td>
				<td>1568879</td>
				<td>54546546</td>
				<td style="text-align:left">....</td>
			</tr>
	     	</@listTable>
	     	</table>  
	     	<@buttonBar>
     	<@vbutton value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/pro/supplier/editSupplier.html')" />
     	<@vbutton value="删除" class="button" onclick="confirm('确认删除吗？')"/>
     </@buttonBar>
    </@ww.form> 
 </@framePage>