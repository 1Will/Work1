<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="历史验收单"> 
 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">	      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            	<th>验收单编号</th>
			 	<th>采购单编号</th>
			 	<th>验收人</th>
			 	<th>验收时间</th>
			 	<th>状态</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editReceiveBill.html">08072001</a></td>
				<td style="text-align:left">08122001</a></td>
				<td style="text-align:left">system</td>
				<td>2007-11-12</td>	
				<td style="text-align:left">审批中</td>	
			</tr>			
	     	</@listTable>
	     	</table>
     
     
     <@buttonBar>
     	<@vbutton value="关闭" class="button" onclick="javascript:window.close();"/>
     </@buttonBar>
      </@ww.form>
</@htmlPage>