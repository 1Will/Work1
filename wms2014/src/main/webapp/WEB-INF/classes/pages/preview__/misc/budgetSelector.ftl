<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="年度预算选择列表">

	 <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@inputTable>
            <tr>
                <@ww.textfield label="'预算编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'预算名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true" />
            </tr>
            <tr>
                <@ww.textfield label="'预算部门'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
        </@inputTable>
 
       	<@buttonBar>																
       		<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>
         <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th></th>
			 	<th>预算编号</th>
			 	<th>预算名称</th>
			 	<th>预算制定人</th>
			 	<th>预算制定日期</th>
			 	<th>预算部门</th>
			 	<th>预算说明</th>
			 	<th>预算状况</th>
			 	<th>预算审核</th>
			 	<th>预算费用</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">08072001</td>
				<td style="text-align:left">耗材添加</td>
				<td style="text-align:left">System</td>
				<td>2008-8-23</td>
				<td style="text-align:left">乘务车1部</td>
				<td style="text-align:left">购买耗材</td>
				<td style="text-align:left">制定完成</td>
				<td style="text-align:left">已通过</td>	
				<td style="text-align:right">200000</td>	
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">08072001</td>
				<td style="text-align:left">办公材料</td>
				<td style="text-align:left">System</td>
				<td>2008-8-23</td>
				<td style="text-align:left">乘务车3部</td>
				<td style="text-align:left">办公用品</td>
				<td style="text-align:left">制定完成</td>
				<td style="text-align:left">未通过</td>	
				<td style="text-align:right">80000</td>	
			</tr>		
	      </@listTable>
	     </table>
     
     <@buttonBar>
	     		<input type="button" value="选择" class="button" onclick="javascript:void(0);"/>
	     		<input type="button" value="关闭" class="button" onclick="window.close();"/>
	 </@buttonBar>
	 </@ww.form>
     
</@htmlPage>
