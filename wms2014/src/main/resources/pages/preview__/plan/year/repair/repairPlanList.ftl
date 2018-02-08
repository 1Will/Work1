<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="大项修计划查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
	 	<@inputTable>
		<tr>
			<@ww.textfield label="'大项修计划编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
			<@ww.textfield label="'大项修计划名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
			
	       </tr>
	       <tr>
	       <@ww.select label="'年度'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'${action.getText('全部')}', 
	                    			'${action.getText('2007')}', 
	                    			'${action.getText('2006')}', 
	                    			'${action.getText('2005')}'
	                    	  	  }"
	                    	value="selectedDevice"
	        	/>
	  	<@dept0/>	     
	 </tr>
	 <tr>
		 <@ww.select label="'计划制作人'"
		                    	name="device"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{'${action.getText('全部')}', 
		                    			'${action.getText('System')}', 
		                    			'${action.getText('csl')}', 
		                    			'${action.getText('sq')}',
		                    			'${action.getText('wzou')}',
		                    			'${action.getText('zbz')}',
		                    			'${action.getText('mwei')}'
		                    	  	  }"
		                    	value="selectedDevice"
		        	/>
	 	<@pp.dateRanger label="'计划建立日期'" name="'cardCreatedTime'" 
		  value="''" cssClass="'underline'" dateFormat="date"/> 
		  <#--<@ww.select label="'执行结果'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'${action.getText('全部')}', 
	                    			'${action.getText('已完成')}', 
	                    			'${action.getText('未完成')}'
	                    	  	  }"
	                    	value="selectedDevice"
	        	/>-->
	 </tr>	
	 </@inputTable> 
	    <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>大项修计划编号</th>
			 	<th>大项修计划名称</th>
			 	<th>部门</th>
			 	<th>年度</th>
			 	<th>计划制作人</th>
			 	<th>计划建立时间</th>
			</tr>

			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editRepairPlan.html">2008072911</a></td>
				<td style="text-align:left">机床修理</td>
				<td style="text-align:left">机动部</td>
				<td style="text-align:left">2008</td>
				<td style="text-align:left">system</td>
				<td>2007-08-11</td>

			</tr>

			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editRepairPlan.html">2008072919</a></td>
				<td style="text-align:left">螺杆修理</td>
				<td style="text-align:left">机动部</td>
				<td style="text-align:left">2008</td>
				<td style="text-align:left">system</td>
				<td>2007-01-11</td>
			</tr>
	     	</@listTable>
	     	</table>
     
     <@buttonBar>
     	<@redirectButton value="新建" url="${req.contextPath}/plan/year/repair/editRepairPlan.html"/>
     	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
     </@buttonBar>
     </@ww.form>
</@htmlPage>