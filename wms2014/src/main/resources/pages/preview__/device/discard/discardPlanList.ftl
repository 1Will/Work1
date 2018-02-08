<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="设备报废查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
	 		<tr>
			 	<@ww.textfield label="'${action.getText('device.code')}'" name="'area.code'" value="" cssClass="'underline'"  />
			 	<@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'"  />
	        </tr>	
	        <tr>
	        	<@ww.textfield label="'设备型号'" name="'area.code'" value="" cssClass="'underline'"  />
			 	<@ww.textfield label="'设备规格'" name="'area.code'" value="" cssClass="'underline'"  />
			 	<@ww.textfield label="'设备类别'" name="'area.code'" value="" cssClass="'underline'"  />
	        </tr>       
	        <tr>
	        	<@ww.textfield label="'使用部门'" name="'area.code'" value="" cssClass="'underline'"  />
	        </tr>	       
        </@inputTable>
        <@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>设备编号</th>
				<th>资产编号</th>
				<th>设备名称</th>
				<th>设备型号</th>
				<th>设备规格</th>
				<th>设备类别</th>
				<th>使用部门</th>
				<th>工单编号</th>
				<th>计划报废日期</th>
				<th>实际报废日期</th>
				<th>状态</th>
				<th>备注</th>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editDiscardPlan.html">07010102-02</a></td>
				<td style="text-align:left">精密普通机床</td>
				<td style="text-align:left">CSM6105</td>
				<td style="text-align:left">500mm*1500mm</td>
				<td style="text-align:left">普通车床</td>
				<td style="text-align:left">总装一厂</td>
				<td style="text-align:left">总装一厂</td>
				<td style="text-align:left">08021102392</td>
				<td>2007-09-22 12:30</td>
				<td>2007-09-25 12:30</td>
				<td style="text-align:left">已签字</td>
				<td style="text-align:left">...</td>
			</tr>			
	     	</@listTable>
	     	</table>	    
	     	<@buttonBar>
	    		<@redirectButton value="${action.getText('new')}" url="listDiscards.html"/>
	    		<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    	</@buttonBar>  	
     </@ww.form>
</@htmlPage>