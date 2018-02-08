<#--$Id: extInfoList.ftl 6197 2007-08-09 10:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="工装润滑计划查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 
	 	<@inputTable>
	 		<tr>
			 	<@ww.textfield label="'润滑计划编号'" name="'area.code'" value="" cssClass="'underline'"/>
			 	<@ww.textfield label="'润滑计划名称'" name="'area.code'" value="" cssClass="'underline'" />
	        </tr>	
	        <tr>
	        	<@ww.textfield label="'工装编号'" name="'area.code'" value="" cssClass="'underline'" />
	        	<@ww.textfield label="'工装名称'" name="'area.code'" value="" cssClass="'underline'" />
	        </tr>       
	        <tr>
				<@pp.datePicker label="'计划开始时间'" name="'repair.estimate.time0'"
			     			value="" cssClass="'underline'" size="15"/>
			     			
			    <@pp.datePicker label="'计划完成时间'" name="'repair.estimate.time1'"
			     			value="" cssClass="'underline'" size="15"/>
			</tr>
	        <tr>
	        	<@dept0/>
	        	<@status/>
	        </tr>
        </@inputTable> 
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="listSmoothPlans.html"/>
        </@buttonBar>
         <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
	        <@listTable>
	  			<tr>
	  			    <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	  				<th>润滑计划编号</th>
	  				<th>润滑计划名称</th>
	  				<th>工装编号</th>
	  				<th>工装名称</th>
	  				<th>润滑计划内容</th>
	  				<th>计划实施要求</th>
	  				<th>计划创建时间</th>
	  				<th>计划创建人</th>
	  				<th>部门</th>
	  				<th>状态</th>
	  			</tr>
	  			<tr>
	  			<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editSmoothPlan.html">JAC-0982</a></td>
	  				<td style="text-align:left">062</td>
	  				<td style="text-align:left">机床</td>
	  				<td style="text-align:left">轴承</td>
	  				<td style="text-align:left">...</td>
	  				<td style="text-align:left">...</td>
	  				<td>2005-5-1 12:00</td>
	  				<td style="text-align:left">system</td>
	  				<td style="text-align:left">机动部</td>
	  				<td style="text-align:left">未开始</td>
	  			</tr>
	  		</@listTable>
        </table>
     	<@buttonBar>
	    	<@redirectButton value="${action.getText('new')}" url="editSmoothPlan.html"/>
	    	<@redirectButton value="生成下月计划" url="#"/>
	    	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    </@buttonBar>
      </@ww.form>
</@htmlPage>