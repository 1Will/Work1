<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<script>
window.onload = function () {
			document.forms["listForm"]["workstatus"].value='未审批';
 }
 	
</script>

<@htmlPage title="我的审批查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
	 	<@inputTable>		
	 		<tr>
	 			<@docTypeSelector0/>
	 			<@ww.textfield label="'文档编号'" name="'area.code'" value="''" cssClass="'underline'"  />
	 			<@ww.textfield label="'文档名称'" name="'area.code'" value="''" cssClass="'underline'"  />
	 		</tr>
		    <tr>
		     	<@dept0/>
		     	<@ww.textfield label="'提交人'" name="'area.code'" value="''" cssClass="'underline'"  />
		     	<@workstatus0/>
			</tr>	
			<tr>
				<@pp.datePicker label="'从提交时间'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15"/>
	     			
	    		<@pp.datePicker label="'至提交时间'" name="'time1'"
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
			 	<th>部门</th>
			 	<th>提交人</th>
			 	<th>提交日期</th>
			 	<th>工作状态</th>
			<tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editMyWork.html">申购计划</a></td>
				<td style="text-align:left">20070923</td>
				<td style="text-align:left">机动部</td>
				<td style="text-align:left">system</td>
				<td >2008-7-25</td>
				<td style="text-align:left">未审批</td>
			</tr>			
	     	</@listTable>
	     	</table>
     <@buttonBar>
     	<@vbutton value="审批" class="button" onclick="javascript:void(0)"/>
     </@buttonBar>
     </@ww.form>
</@htmlPage>