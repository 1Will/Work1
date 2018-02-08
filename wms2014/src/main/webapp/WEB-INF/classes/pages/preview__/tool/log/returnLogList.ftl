<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="工装归还查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
	 	<@inputTable>
	 	<tr>
	 		<@ww.textfield label="'工装编号'" name="'area.code'" value="" cssClass="'underline'"/>
	 		<@ww.textfield label="'工装名称'" name="'area.code'" value="" cssClass="'underline'"/>
	 	</tr>
		<tr>
			<@ww.textfield label="'工装图号'" name="'area.code'" value="" cssClass="'underline'"/>
			<@ww.textfield label="'领用记录编号'" name="'area.code'" value="" cssClass="'underline'"/>
		</tr>
		<tr>
			<@ww.textfield label="'领用人'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
			<@ww.textfield label="'归还人'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
		</tr>
		<tr>				
			<@pp.datePicker label="'从归还时间'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15"/>
	     	<@pp.datePicker label="'至归还时间'" name="'repair.estimate.time1'"
	     			value="" cssClass="'underline'" size="15"/>
	     	<@status/>
		</tr>	
		
	</@inputTable>  

         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>领用记录编号</th>
			 	<th>工装编号</th>
			 	<th>工装名称</th>
			 	<th>工装图号</th>
			 	<th>领用人</th>
			 	<th>领用时间</th>
			 	<th>使用设备</th>
			 	<th>生产数量</th>
			 	<th>归还人</th>
			 	<th>归还时间</th>
			 	<th>损坏状态</th>
			 	<th>验收人</th>
			 	<th>验收日期</th>
			 	<th>保管员</th>
			 	<th>保管日期</th>
			 	<th>状态</th>
			<tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editReturnLog.html">JAC-LY-2008072911</a></td>
				<td style="text-align:left">2008072911</td>
				<td style="text-align:left">测量工具</td>
				<td style="text-align:right">2008072911</td>
				<td style="text-align:left">sa</td>
				<td >2008-7-23</td>
				<td>轴承</td>
				<td style="text-align:right">100</td>
				<td style="text-align:left">sa1</td>
				<td >2008-7-30</td>
				<td style="text-align:left">无</td>
				<td style="text-align:left">sa</td>
				<td >2008-7-20</td>
				<td style="text-align:left">system</td>
				<td >2008-7-23</td>
				<td style="text-align:left">已审批</td>
			</tr>			
	     	</@listTable>
	     	</table>
     
     <@buttonBar>
     	<@redirectButton value="新建" url="editReturnLog.html"/>
     	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
     </@buttonBar>
     </@ww.form>
</@htmlPage>