<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="申购单查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
	 	<@inputTable>
		<tr>
			<@ww.textfield label="'申购单编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>

			<@ww.textfield label="'申购单名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
				

	   </tr>
	   <tr>
	      <@pp.dateRanger label="'申购日期'" name="'cardCreatedTime'" 
		  				value="''" cssClass="'underline'" dateFormat="date"/>
	     <@ww.select label="'状态'"
	                name="device"
	                headerKey="1" 
	                headerValue="Select Month"
	                list="{
	                    	'所有',		
	                    	'已审批', 
	                    	'未审批'
	                    }"
	                 value="selectedDevice"
	      		/>  
		</tr>			
	</@inputTable>  

         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>      
           <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>申购单编号</th>
			 	<th>申购单名称</th>
			 	<th>申购总价</th>
			 	<th>申购人</th>
			 	<th>申购时间</th>
			 	<th>申购原因</th>
			 	<th>备注</th>
			 	<th>状态</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editRequestBill.html">08072001</a></td>
				<td style="text-align:left">申购轴承</td>
				<td style="text-align:right">100000</td>
				<td style="text-align:left">system</td>
				<td>2007-08-12</td>		
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">已审批</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editRequestBill.html">08072002</a></td>
				<td style="text-align:left">申购轴承TXA</td>
				<td style="text-align:right">100000</td>
				<td style="text-align:left">sa</td>
				<td>2007-08-12</td>	
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">未审批</td>	
			</tr>
	     	</@listTable>
	     </table>
     <@buttonBar>
     	<@redirectButton value="新建" url="${req.contextPath}/preview/tool/purchase/editRequestBill.html"/>
     	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
     </@buttonBar>
     </@ww.form>
</@htmlPage>