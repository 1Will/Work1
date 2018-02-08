<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="设备封存查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
	 		<tr>
			 	<@ww.textfield label="'${action.getText('device.code')}'" name="'area.code'" value="" cssClass="'underline'"  />
			 	<@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'"  />
	        </tr>
	        <tr>
	        	<@ww.textfield label="'封存编号'" name="'area.code'" value="" cssClass="'underline'"  />
	        	<@ww.textfield label="'封存人'" name="'area.code'" value="" cssClass="'underline'"  />
	        </tr>
	        <tr>
	        	<@pp.datePicker label="'从封存时间'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15"/>
                <@pp.datePicker label="'至封存时间'" name="'repair.estimate.time1'"
	     			value="" cssClass="'underline'" size="15"/>
	     		<@status/>
	        </tr>
        </@inputTable> 
        <@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>封存编号</th>		
			 	<th>设备编号</th>
			 	<th>设备名称</th>
			 	<th>封存备注</th>
			 	<th>封存时间</th>
			 	<th>封存人</th>
			 	<th>状态</th>
			 </tr>
			 <tr>
			 	<td><input type="checkbox" name="checkbox" value="true"></td>
			 	<td style="text-align:left"><a href="editDisabledDevice.html">0001</a></td>
			 	<td style="text-align:left">JAC-023</td>
			 	<td style="text-align:left">机床</td>
			 	<td style="text-align:left">封存</td>
			 	<td>2007-02-12</td>
			 	<td style="text-align:left">system</td>
			 	<td style="text-align:left">提交</td>	
			 </tr>
	     	</@listTable>
	     	</table>   
	     	<@buttonBar>
	     		<@redirectButton value="新建" url="editDisabledDevice.html"/>
	     		<input type="button" value="删除" class="button" onclick="javascript:window.close();"/>
	     	</@buttonBar>  	
     </@ww.form>
</@htmlPage>