 <#include "../../includes/macros2.ftl" />
 <#include "/javascripts/selectAll.js" />
 <@framePage title="供应商级别变更历史">
 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
 <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
			<th>供应商类别</th>
			<th>供应商编号</th>
			<th>公司名称</th>
			<th>产品质量</th>
			<th>交货期</th>
			<th>交货量</th>
			<th>工作质量</th>
			<th>价格</th>
			<th>进货费用水平</th>
			<th>信用度</th>
			<th>当前状态</th>
			</tr>
			<tr>
		    <td>工装供应商</td>
		    <td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/pro/supplier/editSupplierLevel.html');return false;">GYS001</a></td>
		    <td>合肥永君有限公司</td>
		    <td>优秀</td>
		    <td>2个月</td>
		    <td>2500千克</td>
		    <td>认真</td>
		    <td>一般</td>
		    <td>一般</td>
		    <td>良好</td>
		    <td>未审批</td>
		    </tr>			
	     	</@listTable>
	     	</table>
	     	 <@buttonBar>
     	<@vbutton value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/pro/supplier/editSupplierLevel.html')" />
     	<@vbutton value="删除" class="button" onclick="confirm('确认删除吗?')"/>
     </@buttonBar>
  </@ww.form> 
</@framePage>