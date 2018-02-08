<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="设备保养标准查询">


	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>	 		
	 					<tr>
			 	<@ww.textfield label="'${action.getText('device.code')}'" name="'area.code'" value="" cssClass="'underline'"  />
			 	<@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'"  />
			 	</tr>
			 	<tr>
			 	<@dept0 />
			     <@ww.select label="'保养分类'"
	                    	name="level"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    	       '一保',
	                    	       '二保' ,
	                    	       '日常'	                    	       
	                    	  	  }"
	                    	value="selectedSuppliers"
	                    	/>
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
			<tr>
				<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editMaintenanceRule.html">1</a></td>
				<td style="text-align:left">123--059</td>
				<td style="text-align:left">机器人</td>
				<td style="text-align:left">冲压一厂</td>
			</tr>	
			<tr>
				<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editMaintenanceRule.html">2</a></td>
				<td style="text-align:left">123--059</td>
				<td style="text-align:left">机器人</td>
				<td style="text-align:left">冲压一厂</td>
			</tr>	
			<tr>
				<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editMaintenanceRule.html">3</a></td>
				<td style="text-align:left">123--060</td>
				<td style="text-align:left">机器人</td>
				<td style="text-align:left">冲压一厂</td>
			</tr>	
	  		</@listTable>
        </table>
        	<@buttonBar>
        		<#if req.getParameter("selector")?exists>
        			<@vbutton value="选择" class="button" onclick="javascript:window.close();"/>
        		<#else>
	    			<@redirectButton  value="${action.getText('new')}" url="${req.contextPath}/device/maintenance/editMaintenanceRule.html"/>
	    		</#if>
	    		
	    		<#if !req.getParameter("selector")?exists>
	    			<@vbutton value="删除" class="button" onclick="confirm('确认删除?')"/>
	    		</#if>
	    	</@buttonBar>  	
     </@ww.form>
</@htmlPage>