<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="设备操作证查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
	        <tr>
	        	<@ww.textfield label="'设备编号'" name="'area.code'" value="" cssClass="'underline'"  />
			 	<@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'"  />
	        </tr> 
	        <tr>
	        	<@ww.textfield label="'人员代码'" name="'area.code'" value="" cssClass="'underline'"  />
	        	<@ww.textfield label="'人员名称'" name="'area.code'" value="" cssClass="'underline'"  />
	        </tr>        
	        <tr>
	        	<@ww.textfield label="'操作证编号'" name="'area.code'" value="" cssClass="'underline'"  />
	        	<@ww.textfield label="'操作证名称'" name="'area.code'" value="" cssClass="'underline'"  />
	        </tr>	 
        </@inputTable>
        <@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="${action.getText('new')}" url="editOperate.html"/>
        </@buttonBar>
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>操作证编号</th>
			 	<th>操作证名称</th>
				<th>设备编号</th>
				<th>设备名称</th>
				<th>人员代码</th>
				<th>人员名称</th>
				<th>测试日期</th>
				<th>主考人</th>
				<th>成绩</th>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editOperate.html">07010102-02</a></td>
				<td style="text-align:left">精密机器操作证</td>
				<td style="text-align:left">CSM6105</td>
				<td style="text-align:left">精密普通机床</td>
				<td style="text-align:left">sa</td>
				<td style="text-align:left">sa</td>
				<td>2007-09-22 12:30</td>
				<td style="text-align:left">张辉</td>
				<td style="text-align:left">6</td>
			</tr>
			
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editOperate.html">07010102-03</a></td>
				<td style="text-align:left">精密机器操作证</td>
				<td style="text-align:left">CSM6106</td>
				<td style="text-align:left">精密普通机床</td>
				<td style="text-align:left">sa</td>
				<td style="text-align:left">sa</td>
				<td>2007-09-22 12:30</td>
				<td style="text-align:left">何超</td>
				<td style="text-align:left">5</td>
			</tr>				
	     	</@listTable>
	     	</table>	    
	     	<@buttonBar>
	    		<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    		<@vbutton value="打印" class="button" />
	    	</@buttonBar>  	
     </@ww.form>
</@htmlPage>