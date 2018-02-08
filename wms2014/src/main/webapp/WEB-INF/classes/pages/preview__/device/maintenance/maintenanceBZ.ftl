<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="保养标准选择">
<@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
	 		<tr>
	 		    <@ww.textfield label="'保养标准编号'" name="'area.code'" value="" cssClass="'underline'"  />
	 		     <@ww.textfield label="'保养标准名称'" name="'area.code'" value="" cssClass="'underline'"  />
	 					</tr>
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
	                    	       '二保' 
	                    	  	  }"
	                    	value="selectedSuppliers"
	                    	/>
	              <@status />
	            </tr>
        </@inputTable>
        <@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>
        <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
	        <@listTable>
	  		  <tr>
               <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
                <th>保养标准编号</th>
                <th>保养标准名称</th>
                <th>保养分类</th>
			 	<th>设备编号</th>
				<th>设备名称</th>
				<th>部门</th>
				<th>标准创建时间</th>
				<th>标准创建人</th>
				<th>状态</th>
			<tr>
				<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left">07010102-02</td>
				<td style="text-align:left">轮轴保养</td>
				<td style="text-align:left">一保</td>
				<td style="text-align:left">by001</td>
				<td style="text-align:left">普通车床</td>
				<td style="text-align:left">总装一厂</td>
				<td>2007-08-12</td>		
				<td style="text-align:left">system</td>
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