<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="实施费用列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            	<tr>
            		<th>项目号</th>
	        		<th>维修部位</th>
	        		<th>维修内容</th>
	        		<th>维修日期</th>
            	</tr>
            	<tr>
            		<td >1</td>
            		<td style="text-align:left">齿轮</td>
            		<td style="text-align:left">检查并进行预防性更换</td>
            		<td>2007-01-12</td>
            	</tr>
            	<tr>
            		<td>2</td>
            		<td style="text-align:left">齿轮</td>
            		<td style="text-align:left">检查并进行预防性更换</td>
            		<td>2007-02-12</td>
            	</tr>
            	<tr>
            		<td>3</td>
            		<td style="text-align:left">齿轮</td>
            		<td style="text-align:left">检查并进行预防性更换</td>
            		<td>2006-02-12</td>
            	</tr>
            </@listTable>
            </table>
     </@ww.form>
</@framePage>
    