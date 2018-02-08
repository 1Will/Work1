<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="我的审批查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
	 	<@inputTable>		
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
			 	<th>工作编号</th>
			 	<th>工作名称</th>
			 	<th>提交部门</th>
			 	<th>提交人</th>
			 	<th>提交日期</th>
			 	<th>审核日期</th>
			 	<th>状态</th>
			<tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editMyWarnning.html">2008072911</a></td>
				<td style="text-align:left">执行机床检修</td>
				<td style="text-align:left">机动部</td>
				<td style="text-align:left">sa</td>
				<td >2008-7-25</td>
				<td >2008-7-28</td>
				<td style="text-align:left">未审批</td>
			</tr>			
	     	</@listTable>
	     	</table>       
     </@ww.form>
</@htmlPage>