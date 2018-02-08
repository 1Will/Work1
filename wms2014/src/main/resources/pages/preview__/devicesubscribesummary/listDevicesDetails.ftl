<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../includes/macros2.ftl" />
<@htmlPage title="库存明细查询">
	 <@ww.form name="'listForm'" action="'searchDevicesDetails'" method="'post'">
	 	<#include "searchDevicesDetails.ftl"/>
	 	<@ww.token name="searchDevicesDetailsToken"/>
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" onclick=""/>
        </@buttonBar>
	 	
        <@listTable>	
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>	 	         	
			 	<th>图号</th>
			 	<th>名称</th>
			 	<th>规格型号</th>
			 	<th>种类</th>
			 	<th>明细种类</th>
			 	<th>库存</th>
			 	<th>仓库</th>
			 	<th>库位号</th>
			<tr>
			<tr>
				<td  >3201</td>
				<td  >拉延</td>				
				<td  >模具</td>
				<td  >工具类</td>
				<td  >销类</td>
				<td  >2</td>
				<td  >一号仓库</td>
				<td >100</td>
			</tr>
			<tr>
				<td  >3202</td>
				<td  >拉延</td>
				<td  >模具</td>
				<td  >机械类</td>
				<td  >销类</td>
				<td  >1</td>
				<td  >二号仓库</td>
				<td  >101</td>			
			</tr>
	     	</@listTable>
	     	</table>	    
        </@listTable>   	  	
     </@ww.form>
</@htmlPage>