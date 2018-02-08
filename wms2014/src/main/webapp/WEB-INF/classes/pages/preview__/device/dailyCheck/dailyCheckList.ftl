<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="设备日常检查查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
	 		<tr>
			 	<@ww.textfield label="'设备编号'" name="'area.code'" value="" cssClass="'underline'"  />
			 	<@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'"  />
	        </tr>	   
	        <tr>
	            <@dept0/>
	        	<@pp.dateRanger label="'检查时间'" name="'cardCreatedTime'" 
		  				value="''" cssClass="'underline'" dateFormat="date"/>
		  	</tr>
        </@inputTable>
        <@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="${action.getText('new')}" url="editDeviceDailyCheck.html"/>
        </@buttonBar>
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>设备编号</th>
				<th>设备名称</th>
				<th>部门</th>
				<th>检查时间</th>
				<th>检查人</th>
				<th>检查结果</th>
				<th>处理结果</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true" ></td>
				<td style="text-align:left"><a href="editDeviceDailyCheck.html">07010102-02</a></td>
				<td style="text-align:left">废料输送装置</td>
				<td style="text-align:left">冲焊一厂</td>
				<td>2007-01-23</td>
				<td style="text-align:left">sa</td>
				<td style="text-align:left">传送带坏</td>
				<td style="text-align:left">更换</td>
			</tr>	
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editDeviceDailyCheck.html">07010102-02</a></td>
				<td style="text-align:left">冲压机器人</td>
				<td style="text-align:left">冲焊一厂</td>
				<td>2007-01-23</td>
				<td style="text-align:left">sa</td>
				<td style="text-align:left">轴坏</td>
				<td style="text-align:left">送修</td>
			</tr>		
	     	</@listTable>
	     	<@buttonBar>
	    		<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    		<@vbutton value="打印" class="button" />
	    	</@buttonBar>  	
     </@ww.form>
</@htmlPage>