<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />


<@htmlPage title="设备检查计划选择">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 
	 	<@inputTable>
	 		<tr>
	 			<@ww.textfield label="'检查计划编号'" name="'device.code'" value="" cssClass="'underline'"  />	       	
	        	<@ww.textfield label="'检查计划名称'" name="'device.code'" value="" cssClass="'underline'"  />
	        </tr>
	        <tr>
	 			<@ww.textfield label="'设备编号'" name="'device.code'" value="" cssClass="'underline'"  />	       	
	        	<@ww.textfield label="'设备名称'" name="'device.code'" value="" cssClass="'underline'"  />
	        </tr>
	        <tr>
				<@pp.datePicker label="'从计划执行时间'" name="'repair.estimate.time0'"
			     			value="" cssClass="'underline'" size="15"/>
			     			
			    <@pp.datePicker label="'至计划执行时间'" name="'repair.estimate.time1'"
			     			value="" cssClass="'underline'" size="15"/>
			</tr>
	        <tr>
	        	<@dept0/>
	        </tr>
        </@inputTable> 
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="listCheckPointPlans.html"/>
        </@buttonBar>
        <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
	        <@listTable>
	  			<tr>
	  				<th></th>
	  				<th>检查计划编号</th>
	  				<th>检查计划名称</th>
	  				<th>设备编号</th>
	  				<th>设备名称</th>
	  				<th>计划执行时间</th>	  
	  				<th>检查计划内容</th>	
	  				<th>计划实施要求</th>		
	  				<th>计划创建时间</th>
	  				<th>计划创建人</th>
	  				<th>部门</th>
	  			</tr>
	  			<tr>

	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left">JAC-0293-01</td>
	  				<td style="text-align:left">机床检查</td>
	  				<td style="text-align:left">20039</td>
	  				<td style="text-align:left">机床</td>
	  				<td>2005-5-1 12:00</td>
	  				<td>...</td>
	  				<td>...</td>
	  				<td>2005-5-1 12:00</td>
	  				<td style="text-align:left">system</td>
	  				<td style="text-align:left">机动部</td>
	  			</tr>
	  		</@listTable>
        </table>
     	<@buttonBar>
	    	<@vbutton value="选择" class="button" onclick="javascript:void(0);"/>
	    	<@vbutton value="关闭" class="button" onclick="javascript:window.close();"/>
	    </@buttonBar>
      </@ww.form>
</@htmlPage>