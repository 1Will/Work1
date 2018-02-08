<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="工装领用选择列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
	 	<@inputTable>
	 	<tr>
	 		<@ww.textfield label="'领用记录编号'" name="'area.code'" value="" cssClass="'underline'"/>
	 		<@ww.textfield label="'领用记录名称'" name="'area.code'" value="" cssClass="'underline'"/>
	 	</tr>
	 	<tr>
	 		<@ww.textfield label="'工装编号'" name="'area.code'" value="" cssClass="'underline'"/>
	 		<@ww.textfield label="'工装名称'" name="'area.code'" value="" cssClass="'underline'"/>
		</tr>
		<tr>
			<@pp.datePicker label="'从领用时间'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15"/>
	     	<@pp.datePicker label="'至领用时间'" name="'repair.estimate.time1'"
	     			value="" cssClass="'underline'" size="15"/>
		</tr>	
		<tr>
			<@ww.textfield label="'领用人'" name="'area.code'" value="" cssClass="'underline'"/>
		</tr>
		
	</@inputTable>  

         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th></th>
			 	<th>领用记录编号</th>
			 	<th>领用记录名称</th>
			 	<th>工装编号</th>
			 	<th>工装名称</th>
			 	<th>工装图号</th>
			 	<th>领用人</th>
			 	<th>领用时间</th>
			 	<th>使用设备</th>
			 	<th>生产数量</th>
			 	<th>验收人</th>
			 	<th>验收日期</th>
			 	<th>保管员</th>
			 	<th>保管日期</th>
			<tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">JAC-LY-2008072911</td>
				<td style="text-align:left">2008072911</td>
				<td style="text-align:left">2008072911</td>
				<td style="text-align:left">测量工具</td>
				<td style="text-align:right">2008072911</td>
				<td style="text-align:left">sa</td>
				<td >2008-7-23</td>
				<td>轴承</td>
				<td style="text-align:right">100</td>
				<td style="text-align:left">sa</td>
				<td >2008-7-23</td>
				<td style="text-align:left">system</td>
				<td >2008-7-23</td>
			</tr>			
	     	</@listTable>
	     	</table>
     
     <@buttonBar>
     	<@vbutton value="选择" class="button" onclick="javascript:void(0);"/>
     	<@vbutton value="关闭" class="button" onclick="javascript:window.close();"/>
     </@buttonBar>
     
     </@ww.form>
</@htmlPage>