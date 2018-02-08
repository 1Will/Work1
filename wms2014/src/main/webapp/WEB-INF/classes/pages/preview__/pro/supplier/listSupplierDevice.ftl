<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="${action.getText('extInfo.maintain')}">
 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
       <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
              <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
                <th>项目号</th>
                <th>产品类别</th>
			 	<th>产品名称</th>
			 	<th>订货日期</th>
			 	<th>保质期</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/pro/supplier/editSupplierDevice.html',600,300);return false;">CP001</a></td>
				<td>...</td>
				<td>...</td>
				<td>...</td>
				<td>...</td>
			</tr>
	     	</@listTable>
	     	</table>  
<@buttonBar>
  <@vbutton  name="a" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/pro/supplier/editSupplierDevice.html')"/>
  	<@vbutton value="删除" class="button" onclick="confirm('确认删除吗?')"/>
</@buttonBar> 
 </@ww.form> 	
</@framePage>