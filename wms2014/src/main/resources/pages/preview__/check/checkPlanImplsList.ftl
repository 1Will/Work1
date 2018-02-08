<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="设备定期检查实施查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
	<@inputTable>
	 		<tr>
	 			<@ww.textfield label="'定期检查实施编号'" name="'device.code'" value="" cssClass="'underline'" />
	 			<@ww.textfield label="'定期检查实施名称'" name="'device.code'" value="" cssClass="'underline'"/>	       	
	        </tr>
	        <tr>
	 			<@ww.textfield label="'定期检查计划编号'" name="'device.code'" value="" cssClass="'underline'" />
	 			<@ww.textfield label="'定期检查计划名称'" name="'device.code'" value="" cssClass="'underline'"/>	       	
	        </tr>
	         <tr>
	        	<@ww.textfield label="'设备编号'" name="'device.code'" value="" cssClass="'underline'"  />
	        	<@ww.textfield label="'设备名称'" name="'device.code'" value="" cssClass="'underline'"  />
	        </tr>
	          <tr>
				<@pp.datePicker label="'从实施时间'" name="'repair.estimate.time0'"
			     			value="" cssClass="'underline'" size="15"/>
			     			
			    <@pp.datePicker label="'至实施时间'" name="'repair.estimate.time1'"
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
	  				<th>定期检查实施编号</th>
	  				<th>定期检查实施名称</th>
	  				<th>设备编号</th>
	  				<th>设备名称</th>
	  				<th>检查计划编号</th>
	  				<th>检查时间</th>
	  				<th>部门</th>
	  				<th>检查负责人</th>
	  				<th>状态</th>	  				
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editCheckPlanImpls.html">20070801003</a></td>
	  				<td style="text-align:left">车检</td>
	  				<td style="text-align:left">J-89</td>
	  				<td style="text-align:left">...</td>
	  				<td style="text-align:left">JAC-JC-98</td>
	  				<td style="text-align:left">每星期一上午8：00</td>
	  				<td style="text-align:left">总装二厂</td>
	  				<td style="text-align:left">System</td>
	  				<td style="text-align:left">待实施</td>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editCheckPlanImpls.html">20070801009</a></td>
	  				<td style="text-align:left">...</td>
	  				<td style="text-align:left">JAC-07</td>
	  				<td style="text-align:left">...</td>
	  				<td style="text-align:left">JAC-JC-99</td>
	  				<td style="text-align:left">每月一号</td>
	  				<td style="text-align:left">总装三厂</td>
	  				<td style="text-align:left">System</td>
	  				<td style="text-align:left">已实施</td>
	  			</tr>
	  		</@listTable>
        </table>
     	<@buttonBar>
	    	<@redirectButton value="${action.getText('new')}" url="editCheckPlanImpls.html"/>
			<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    </@buttonBar>
      </@ww.form>
</@htmlPage>