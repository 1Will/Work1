<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="采购进度">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
			 	<th>时间</th>
			 	<th>进度</th>
			 	<th>备注</th>
			<tr>
			<tr>
				<td>2007-02-12</td>
				<td>50%</td>
				<td>...</td>
			</tr>			
	     	</@listTable>
	     	</table>	     	
     </@ww.form>
</@framePage>