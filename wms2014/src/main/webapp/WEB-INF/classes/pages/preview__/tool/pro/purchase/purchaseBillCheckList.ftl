<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="设备验收标准">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
              <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>项目号</th>
			 	<th>验收内容</th>
				<th>验收内容标准状态</th>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editPurchaseBillCheck.html', 600, 250); return false;">0200</a></td>
				<td style="text-align:left">机床外观</td>
				<td style="text-align:left">不能有任何掉漆</td>		
			</tr>			
	     	</@listTable>
	     	</table>	    
	     	<@buttonBar>
	    		<@vbutton class="button" value="${action.getText('new')}" onclick="popupModalDialog('${req.contextPath}/popup/editPurchaseBillCheck.html',600, 250)"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>   	
     </@ww.form>
</@framePage>