<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />


<@htmlPage title="设备定期检查计划查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 
	 	<@inputTable>
	 		<tr>
	 			<@ww.textfield label="'定期检查计划编号'" name="'device.code'" value="" cssClass="'underline'"  />	       	
	        	<@ww.textfield label="'定期检查计划名称'" name="'device.code'" value="" cssClass="'underline'"  />
	        </tr>
	        <tr>
	        	<@ww.textfield label="'设备编号'" name="'device.code'" value="" cssClass="'underline'"  />
	        	<@ww.textfield label="'设备名称'" name="'device.code'" value="" cssClass="'underline'"  />
	        </tr>
	         <tr>
				<@pp.datePicker label="'检查开始时间'" name="'repair.estimate.time0'"
			     			value="" cssClass="'underline'" size="15"/>
			     			
			    <@pp.datePicker label="'检查结束时间'" name="'repair.estimate.time1'"
			     			value="" cssClass="'underline'" size="15"/>
			</tr>
	        <tr>
	          <@dept0/>
	          <@workflow/>
	        </tr>
        </@inputTable> 
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>
        <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
	        <@listTable>
	  			<tr>
	  				<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	  				<th>定期检查计划编号</th>
	  				<th>定期检查计划名称</th>
	  				<th>检查时间</th>
	  				<th>设备编号</th>
	  				<th>设备名称</th>
	  				<th>检查部位</th>
	  				<th>检查负责人</th>
	  				<th>检查创建时间</th>
	  				<th>检查创建人</th>
	  				<th>状态</th>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editCheckPlan.html">JAC-JC-98</a></td>
	  				<td style="text-align:left">机床定检</td>
	  				<td>8月2号</td>
	  				<td style="text-align:left">JAC-055</td>
	  				<td style="text-align:left">机床</td>
	  				<td style="text-align:left">轴承</td>
	  				<td style="text-align:left">Tom</td>
	  				<td>2007-5-1</td>
	  				<td style="text-align:left">System</td>
	  				<td style="text-align:left">已审核</td>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editCheckPlan.html">JAC-JC-99</a></td>
	  				<td style="text-align:left">模具定检</td>
	  				<td>8月3号</td>
	  				<td style="text-align:left">JAC-055</td>
	  				<td style="text-align:left">模具</td>
	  				<td style="text-align:left">接合口</td>
	  				<td style="text-align:left">Tom</td>
	  				<td>2007-8-23</td>
	  				<td style="text-align:left">System</td>
	  				<td style="text-align:left">待审核</td>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editCheckPlan.html">JAC-JC-100</a></td>
	  				<td style="text-align:left">主控机定检</td>
	  				<td>8月20号</td>
	  				<td style="text-align:left">JAC-531</td>
	  				<td style="text-align:left">主控机</td>
	  				<td style="text-align:left">系统程序</td>
	  				<td style="text-align:left">Tom</td>
	  				<td>2007-5-1</td>
	  				<td style="text-align:left">System</td>
	  				<td style="text-align:left">已审核</td>
	  			</tr>
	  		</@listTable>
        </table>
     	<@buttonBar>
	    	<@redirectButton value="${action.getText('new')}" url="editCheckPlan.html"/>
	    	<@redirectButton value="生成下月计划" url="#"/>
	    	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    </@buttonBar>
      </@ww.form>
</@htmlPage>