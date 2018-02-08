<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="设备保养计划选择">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
	 		<tr>
	 		<@ww.textfield label="'保养计划编号'" name="'area.code'" value="" cssClass="'underline'"  />
	 			<@ww.textfield label="'保养计划名称'" name="'area.code'" value="" cssClass="'underline'"  />
	 		</tr>
	 		<tr>
			 	<@ww.textfield label="'${action.getText('device.code')}'" name="'area.code'" value="" cssClass="'underline'"  />
			 	<@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'"  />
	         </tr>
			 <tr>       
	        	<@pp.datePicker label="'从保养计划时间'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"/>
			 	<@pp.datePicker label="'至保养计划时间'" name="'repair.actual.time1'"
	     			value="" cssClass="'underline'" size="15"/>
	        </tr>	 
	        	<tr>
	      <@dept0 />
	      <@workflow />
	 	</tr>      
        </@inputTable>
        <@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
               <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
                <th>保养计划编号</th>
                <th>保养计划名称</th>
			 	<th>设备编号</th>
				<th>设备名称</th>
				<th>部门</th>
				<th>保养级别</th>
				<th>保养负责人</th>
				<th>保养计划创建时间</th>
				<th>保养计划创建人</th>
				<th>保养计划执行时间</th>
				<th>备注</th>
				<th>状态</th>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editMaintenancePlan.html">07010102-02</a></td>
				<td style="text-align:left">设备保养计划</td>
				<td style="text-align:left">byjh001</td>
				<td style="text-align:left">精密普通机床</td>
				<td style="text-align:left">总装一厂</td>
				<td style="text-align:left">一保</td>
				<td style="text-align:left">system</td>
				<td>07-05-03</td>
				<td style="text-align:left">system</td>
				<td>07-05-03</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">提交</td>
			</tr>			
	     	</@listTable>
	     	</table>	    
	     	<@buttonBar>
	    		<@redirectButton  value="选择" url="#"/>
	    		<@vbutton value="关闭" class="button" onclick="javascript:window.close();"/>
	    	</@buttonBar>  
     </@ww.form>
</@htmlPage>