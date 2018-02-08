<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="采购计划查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
	 	<@inputTable>
		<tr>
			<@ww.textfield label="'采购计划编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>


			<@ww.textfield label="'采购计划名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
		</tr>
		<tr>
            <@dept0 />
		    <@pp.datePicker readonly="true" label="'年度'" size="6" name="'month'" required="false" cssClass="'underline'" dateFormat="'%Y'"/> 
		</tr>	
	</@inputTable>  

         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>采购计划编号</th>
			 	<th>采购计划名称</th>
			 	<th>部门</th>
			 	<th>年度</th>
			 	<th>编制人</th>
			 	<th>编制日期</th>
			<tr>

			<tr>

			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editPurchasePlan.html">2008072911</a></td>
				<td style="text-align:left">机动部2008采购计划</td>
				<td style="text-align:left">机动部</td>
				<td>2008</td>
				<td style="text-align:left">system</td>
				<td>2007-08-11</td>

			</tr>

			<tr>

			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editPurchasePlan.html">2008072919</a></td>
				<td style="text-align:left">生产部2008采购计划</td>
				<td style="text-align:left">生产部</td>
				<td>2008</td>
				<td style="text-align:left">system</td>
				<td>2007-01-11</td>
			</tr>
	     	</@listTable>
	     	</table>
     
     <@buttonBar>
     	<@redirectButton value="新建" url="${req.contextPath}/preview/plan/year/purchase/editPurchasePlan.html"/>
     	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
     </@buttonBar>
     </@ww.form>
</@htmlPage>