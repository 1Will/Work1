<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<script>
window.onload = function () {
			document.forms["listForm"]["workstatus"].value='未审批';
 }
 	
</script>

<@htmlPage title="我的提醒查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
	 	<@inputTable>		
	 		<tr>
	 			<@docTypeSelector0/>
	 			<@ww.textfield label="'文档编号'" name="'area.code'" value="''" cssClass="'underline'"  />
	 			<@ww.textfield label="'文档名称'" name="'area.code'" value="''" cssClass="'underline'"  />
	 		</tr>
		    <tr>
		    	<@ww.select label="'提醒类型'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'${action.getText('所有')}', 
	                    			'${action.getText('即将发生')}', 
	                    			'${action.getText('实施报告未提交')}',
	                    			'${action.getText('待审批')}'
	                    	  	  }"
	                    	value="selectedDevice"
				/>
		     	<@dept0/>
		     	
		     	<@ww.select label="'状态'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'${action.getText('所有')}', 
	                    			'${action.getText('未读')}', 
	                    			'${action.getText('已读')}',
	                    			'${action.getText('已完成')}'
	                    	  	  }"
	                    	value="selectedDevice"
				/>
			</tr>	
			<tr>
				<@pp.datePicker label="'从提醒时间'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15"/>
	     			
	    		<@pp.datePicker label="'至提醒时间'" name="'time1'"
	     			value="" cssClass="'underline'" size="15"/>
			</tr>
		
	</@inputTable>  

         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>文档类型</th>
			 	<th>文档编号</th>
			 	<th>文档名称</th>
			 	<th>提醒类型</th>
			 	<th>提醒时间</th>
			 	<th>部门</th>
			 	<th>状态</th>
			<tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editMyWarnning.html">维修实施</a></td>
				<td style="text-align:left">20070923</td>
				<td style="text-align:left">机床维修实施报告</td>
				<td style="text-align:left">实施报告未提交</td>
				<td >2008-7-25</td>
				<td style="text-align:left">机动部</td>
				<td style="text-align:left">未读</td>
			</tr>			
	     	</@listTable>
	     	</table>
     <@buttonBar>
     	<@vbutton value="阅读" class="button" onclick="javascript:void(0)"/>
     </@buttonBar>
     </@ww.form>
</@htmlPage>