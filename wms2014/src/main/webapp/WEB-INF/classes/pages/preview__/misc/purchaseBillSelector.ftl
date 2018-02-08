<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="采购单查询列表">

	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
		<tr>
			<@ww.textfield label="'采购单编号'" name="'area.code'" value="" cssClass="'underline'" />
			<@ww.textfield label="'采购单名称'" name="'area.code'" value="" cssClass="'underline'" />
		</tr>
			<@ww.textfield label="'采购计划编号'" name="'area.code'" value="" cssClass="'underline'" />
			<@ww.textfield label="'申购单编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
		</tr>
		<tr>
			<@pp.datePicker label="'从采购时间'" name="'repair.estimate.time0'"
		     			value="" cssClass="'underline'" size="15"/>
		     			
		    <@pp.datePicker label="'至采购时间'" name="'time1'"
		     			value="" cssClass="'underline'" size="15"/>
		</tr>
	</@inputTable>  
	 	
           <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th>选择采购单</th>
			 	<th>采购单编号</th>
			 	<th>采购单名称</th>
			 	<th>设备名称</th>
			 	<th>设备类型</th>
			 	<th>设备规格</th>
			 	<th>设备数量</th>
			 	<th>设备单价</th>
			 	<th>采购总额</th>
			 	<th>采购购人</th>
			 	<th>采购日期</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">200521154654</td>
				<td style="text-align:left">申购机床</td>
				<td style="text-align:left">普通机床</td>
				<td style="text-align:left">TX-32</td>
				<td style="text-align:left">A</td>
				<td style="text-align:right">10</td>
				<td style="text-align:right">100</td>
				<td style="text-align:right">100</td>
				<td style="text-align:left">Tom</td>
				<td >2007-07-04</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">200521154654</td>
				<td style="text-align:left">申购机床</td>
				<td style="text-align:left">普通机床</td>
				<td style="text-align:left">TX-32</td>
				<td style="text-align:left">B</td>
				<td style="text-align:right">10</td>
				<td style="text-align:right">100</td>
				<td style="text-align:right">100</td>
				<td style="text-align:left">Tom</td>
				<td >2007-07-04</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">200521154654</td>
				<td style="text-align:left">申购机床</td>
				<td style="text-align:left">普通机床</td>
				<td style="text-align:left">TX-32</td>
				<td style="text-align:left">C</td>
				<td style="text-align:right">10</td>
				<td style="text-align:right">100</td>
				<td style="text-align:right">100</td>
				<td style="text-align:left">Tom</td>
				<td >2007-07-04</td>
			</tr>
	     	</@listTable>
	     	</table>
	     	<@buttonBar>
	     		<@vbutton class="button" value="选择" onclick="javascript:window.close()"/>	
	     		<@vbutton class="button" value="关闭" onclick="javascript:window.close()"/>	
	     	</@buttonBar>
     </@ww.form>
</@htmlPage>