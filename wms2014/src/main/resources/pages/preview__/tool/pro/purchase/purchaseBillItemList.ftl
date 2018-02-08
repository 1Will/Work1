<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="采购单明细列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
              <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>项目号</th>
			 	<th>工装名称</th>
				<th>工装型号</th>
				<th>工装类别</th>
				<th>数量</th>				
				<th>单价</th>
				<th>总价</th>
				<th>要求交货日期</th>
				<th>备注</th>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editPurchaseBillItem.html', 750, 300); return false;">JH-021</a></td>
				<td style="text-align:left">轴承</td>
				<td style="text-align:left">TX-32</td>
				<td style="text-align:left">A</td>
				<td style="text-align:right">10</td>
				<td style="text-align:right">100</td>
				<td style="text-align:right">750</td>
				<td>2008-12-23</td>
				<td style="text-align:left">...</td>
			</tr>
	     	</@listTable>
	     	</table>	    
	     	<@buttonBar>
	    		<@vbutton class="button" value="${action.getText('new')}" onclick="popupModalDialog('${req.contextPath}/popup/editPurchaseBillItem.html', 750, 300)"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>  	
     </@ww.form>
</@framePage>