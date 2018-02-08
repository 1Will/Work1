<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="申购单查询列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
	 	<@inputTable>
		<tr>
			<@ww.textfield label="'申购单编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>

			<@ww.textfield label="'申购单名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
	   </tr>
	   <tr>
	      <@pp.datePicker label="'从申购时间'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15"/>
	      <@pp.datePicker label="'至申购时间'" name="'repair.estimate.time1'"
	     			value="" cssClass="'underline'" size="15"/>
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
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/lidtRequestBillSelectorItem.html', 600, 350); return false;">08072001</a></td>
				<td style="text-align:left">申购轴承</td>
				<td style="text-align:right">100000</td>
				<td style="text-align:left">system</td>
				<td>2007-08-12</td>		
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/lidtRequestBillSelectorItem.html', 600, 350); return false;">08072002</a></td>
				<td style="text-align:left">申购轴承TXA</td>
				<td style="text-align:right">100000</td>
				<td style="text-align:left">sa</td>
				<td>2007-08-12</td>	
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>
			</tr>
	     	</@listTable>
	     </table>
     <@buttonBar>
     	<@vbutton value="选择" class="button" onclick="javascript:void(0);"/>
     	<@vbutton value="关闭" class="button" onclick="javascript:window.close();"/>
     </@buttonBar>
     </@ww.form>
</@htmlPage>