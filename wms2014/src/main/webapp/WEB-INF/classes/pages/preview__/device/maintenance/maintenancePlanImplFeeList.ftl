<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="保养实施费用明细">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	 <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
             <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>编号</th>
			 	<th>费用项目</th>
				<th>费用额</th>
				<th>备注</th>				
			<tr>
			<tr>
			<td><input type="checkbox" name="checkbox" value="true"></td>				
				<td style="text-align:left">021</td>
				<td style="text-align:left">abc</td>
				<td style="text-align:right">123</td>
				<td style="text-align:left">CSM6105</td>				
			</tr>			
	     	</@listTable>
	     	</table>	    
	     <@buttonBar>
	    		<@redirectButton value="${action.getText('new')}" url="editMaintenancePlanFee.html"/>
	    		<@redirectButton value="${action.getText('invalid')}" url="listMaintenancePlanFees.html"/>
	    </@buttonBar>  	
     </@ww.form>
</@framePage>