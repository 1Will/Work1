<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="设备润滑实施查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
	 		<tr>
			 	<@ww.textfield label="'润滑实施编号'" name="'area.code'" value="" cssClass="'underline'" />
			 	<@ww.textfield label="'润滑实施名称'" name="'area.code'" value="" cssClass="'underline'" />
	        </tr>
	        <tr>
	 			<@ww.textfield label="'润滑计划编号'" name="'device.code'" value="" cssClass="'underline'" />
	 			<@ww.textfield label="'润滑计划名称'" name="'device.code'" value="" cssClass="'underline'" />
	        </tr>
	 		<tr>
			 	<@ww.textfield label="'设备编号'" name="'area.code'" value="" cssClass="'underline'" />
			 	<@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'" />
	        </tr>	      
	         <tr>
				<@pp.datePicker label="'计划开始时间'" name="'repair.estimate.time0'"
			     			value="" cssClass="'underline'" size="15"/>
			     			
			    <@pp.datePicker label="'计划完成时间'" name="'repair.estimate.time1'"
			     			value="" cssClass="'underline'" size="15"/>
			</tr>
			<tr>
	        	<@dept0/>
	        </tr>
        </@inputTable>
        <@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
                <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
                <th>润滑实施编号</th>
                <th>润滑实施名称</th>
			 	<th>润滑标准编号</th>
				<th>润滑计划编号</th>
				<th>设备编号</th>
				<th>设备名称</th>
				<th>实施部门</th>
	  			<th>实施时间</th>
	  			<th>实施负责人</th>
			<tr>
			    <td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editSmoothPlanImpls.html">07010102-02</a></td>
				<td style="text-align:left">精密机床润滑</td>
				<td style="text-align:left">07010102-02</td>
				<td style="text-align:left">CSM6105</td>
				<td style="text-align:left">123--059</td>
				<td style="text-align:left">普通车床</td>
				<td style="text-align:left">总装一厂</td>
				<td>2007-09-25 12:30</td>
				<td style="text-align:left">system</td>
			</tr>			
	     	</@listTable>
	     	</table>	    
	     	<@buttonBar>
	    		<@redirectButton value="${action.getText('new')}" url="editSmoothPlanImpls.html"/>
	    		<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    	</@buttonBar>  	
     </@ww.form>
</@htmlPage>