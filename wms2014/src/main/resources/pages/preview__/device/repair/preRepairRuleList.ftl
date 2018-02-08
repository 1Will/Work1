<#--$Id: extInfoList.ftl 6197 2007-08-09 10:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="预防性维修标准查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 	<@inputTable>	 		
	 		<tr>
	 			<@ww.textfield label="'设备编号'" name="'device.code'" value="" cssClass="'underline'"  />
	 			<@ww.textfield label="'设备名称'" name="'device.code'" value="" cssClass="'underline'"  />
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
	        		<th>项目号</th>
	        		<th>设备编号</th>
	        		<th>设备名称</th>
	        		<th>部门</th>
	        	</tr>
	        	<tr>
	        		<td><input type="checkbox" name="checkbox" value="true"></td>
	        		<td style="text-align:right"><a href="editPreRepairRule.html" >1</a></td>
	        		<td style="text-align:left">JAC-2131</td>
	        		<td style="text-align:left">机床</td>
	        		<td style="text-align:left">机动部</td>
	        	</tr>
	        	<tr>
	        		<td><input type="checkbox" name="checkbox" value="true"></td>
	        		<td style="text-align:right"><a href="editPreRepairRule.html">2</a></td>
	        		<td style="text-align:left">JAC-2145</td>
	        		<td style="text-align:left">压缩机</td>
	        		<td style="text-align:left">机动部</td>
	        	</tr>
	        </@listTable>
	    </table>
	    <@buttonBar>
	    	<@redirectButton value="${action.getText('new')}" url="listPreRepairRule.html"/>
	    	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    </@buttonBar>
	 </@ww.form>
</@htmlPage>