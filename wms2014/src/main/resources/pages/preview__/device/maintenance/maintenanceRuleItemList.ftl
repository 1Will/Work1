<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="保养标准列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
                <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
                <th>项目号</th>
                <th>保养类别</th>
                <th>保养台时阀值(时)</th>
                <th>文档名</th>
			 	<th>保养文档</th>
				<th>备注</th>
			<tr>
				<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="return popup();">JAC-001</a></td>
				<td>一保</td>
				<td>100</td>
				<td style="text-align:left">机器人一保文档</td>
				<td style="text-align:left"><a href="#">TPM一保文档</td>
				<td style="text-align:left">...</td>
			</tr>	
			
			<tr>
				<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="return popup();">JAC-001</a></td>
				<td>二保</td>
				<td>200</td>
				<td style="text-align:left">机器人二保文档</td>
				<td style="text-align:left"><a href="#">TPM二保文档</td>
				<td style="text-align:left">...</td>
			</tr>			
	     	</@listTable>
	     	</table>	    
	     	<@buttonBar>
	    		<input type="button" value="新建" class="button" onclick="return popup();"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>  	
	    	
	    	<script>
	    		function popup() {
	    			popupModalDialog('${req.contextPath}/popup/editMaintenanceRuleItem.html',800,400);
	    			return false;
	    		}
	    	</script>
     </@ww.form>
</@framePage>