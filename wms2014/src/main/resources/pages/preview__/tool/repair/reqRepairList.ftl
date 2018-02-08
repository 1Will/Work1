<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="维修申请查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
	 		<tr>
			 	<@ww.textfield label="'维修申请编号'" name="'area.code'" value="" cssClass="'underline'" />
			 	<@ww.textfield label="'维修申请名称'" name="'area.code'" value="" cssClass="'underline'"/>
	        </tr>
	        <tr>
			 	<@ww.textfield label="'工装编号'" name="'area.code'" value="" cssClass="'underline'" />
			 	<@ww.textfield label="'工装名称'" name="'area.code'" value="" cssClass="'underline'"/>
			 	<@ww.textfield label="'工装图号'" name="'area.code'" value="" cssClass="'underline'"/>
	        </tr>
	        <tr>
			 	<@pp.datePicker label="'从申请时间'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15"/>
	     		<@pp.datePicker label="'至申请时间'" name="'repair.estimate.time1'"
	     			value="" cssClass="'underline'" size="15"/>
	        </tr>
	        <tr>
	        	<@dept0/>
	        	<@workflow/>
	        </tr>
        </@inputTable> 
        <@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
		 	<th  >维修申请编号</th>
		 	<th>维修申请名称</th>
		 	<th >工装编号</th>
		 	<th >工装名称</th>
		 	<th >工装图号</th>
		 	<th>部门</th>
		 	<th>维修原因</th>
		 	<th>申请人</th>
		 	<th>申请日期</th>
		 	<th>状态</th>
		 	</tr>
			<tr>
				<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="${req.contextPath}/preview/tool/repair/editReqRepair.html">20080809110401000</a></td>						
				<td style="text-align:left">冲头维修申请</td>
				<td style="text-align:left">JAC-021</td>
				<td style="text-align:left">冲头</td>
				<td style="text-align:left">JL-0210923</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">sa</td>
				<td>2007-9-24</td>
				<td style="text-align:left">未审批</td>
			</tr>				
	     	</@listTable>
	     	</table>
	     	 <@buttonBar>
	    		<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/preview/tool/repair/editReqRepair.html"/>
	    		<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    	</@buttonBar>
	    	
	    	
     </@ww.form>
</@htmlPage>