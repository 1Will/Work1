<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="年度预算查询">
	 <@ww.form name="'listForm'" action="''" method="'post'">
	 	
	 	<@inputTable>
			<tr>
			 <@ww.textfield label="'预算编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
			 <@ww.textfield label="'预算名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>			
	        </tr>
	        <tr>	
			<@pp.datePicker readonly="true" label="'年度'" size="6" name="'month'" required="false" cssClass="'underline'" dateFormat="'%Y'"/> 
	       
	       <@dept0/>
	        </tr>
	       
	       <@ww.select label="'预算分类'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'${action.getText('所有')}',
	                    			'${action.getText('维修')}', 
	                    			'${action.getText('办公')}', 
	                    			'${action.getText('采购')}'
	                    	  	  }"
	                    	value="selectedDevice"
	        />
		</tr>	
	    </@inputTable>  
		<@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>      
           <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
                <th><input type="checkbox" name="checkbox"  onClick="selectedAll()" value="true"></th>
			 	<th>预算编号</th>
			 	<th>预算名称</th>
			 	<th>部门</th>
			 	<th>预算分类</th>
			 	<th>费用</th>
			 	<th>制定人</th>
			 	<th>制定时间</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editBudgetPlan.html">2008072911</a></td>
				<td style="text-align:left">2008年度乘用车制造公司设备预算</td>
				<td style="text-align:left">机动部</td>
				<td style="text-align:left">采购</td>
				<td style="text-align:right">100000</td>
				<td style="text-align:left">system</td>
				<td>2007-8-21</td>
			</tr>			
	     	</@listTable>
	    </table>
     
     <@buttonBar>
     	<@redirectButton value="新建" url="${req.contextPath}/preview/plan/budget/editBudgetPlan.html"/>	
     	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
     </@buttonBar>
     </@ww.form>
</@htmlPage>