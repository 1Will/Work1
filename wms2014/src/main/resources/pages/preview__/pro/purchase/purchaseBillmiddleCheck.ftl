<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="采购单明细列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
              <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>序号</th>
			 	<th>检查日期</th>
				<th>状况</th>
				<th>解决方式</th>
				<th>备注</th>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editPurchaseBillmiddleCheck.html', 750, 300); return false;">1</a></td>
					
				<td style="text-align:left">...</td>
			</tr>
	     	</@listTable>
	     	</table>	    
	     	<@buttonBar>
	    		<@vbutton class="button" value="${action.getText('new')}" onclick="popupModalDialog('${req.contextPath}/popup/editPurchaseBillmiddleCheck.html', 750, 300)"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>  	
     </@ww.form>
</@framePage>