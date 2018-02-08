<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="${action.getText('repair.plan.title')}">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
	 		<tr>
			 	<@ww.textfield label="'${action.getText('device.code')}'" name="'area.code'" value="" cssClass="'underline'" required="true"/>
	        </tr>
        </@inputTable> 
        <@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
             <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
		 	<th  >${action.getText('repair.plan.sn')}</th>
		 	<th  >${action.getText('repair.estimate.time0')}</th>
		 	<th  >${action.getText('repair.estimate.time1')}</th>
		 	<th  >${action.getText('repair.actual.time0')}</th>
		 	<th  >${action.getText('repair.actual.time1')}</th>
		 	<th  >${action.getText('repair.application.creater')}</th>
		 	<th  >${action.getText('repair.application.create.time')}</th>
		 	</tr>
			<tr>
			    <td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="${req.contextPath}/device/repair/editRepairPlan.html">20080809110401000</a></td>
				<td>2008-08-12 11:30</td>
				<td>2008-08-13 21:30</td>
				<td></td>
				<td></td>
				<td style="text-align:left">sa</td>
				<td>2008-08-11 12:00</td>				
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="${req.contextPath}/device/repair/editRepairPlan.html">20080809110401050</a></td>
				<td>2008-07-12 11:30</td>
				<td>2008-07-13 21:30</td>
				<td></td>
				<td></td>
				<td style="text-align:left">sa</td>
				<td>2008-07-11 12:00</td>				
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="${req.contextPath}/device/repair/editRepairPlan.html">20080809110401030</a></td>
				<td>2008-05-12 11:30</td>
				<td>2008-05-13 21:30</td>
				<td></td>
				<td></td>
				<td style="text-align:left">sa</td>
				<td>2008-05-11 12:00</td>				
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="${req.contextPath}/device/repair/editRepairPlan.html">20080809110401080</a></td>
				<td>2007-08-12 11:30</td>
				<td>2007-08-13 21:30</td>
				<td></td>
				<td></td>
				<td style="text-align:left">sa</td>
				<td>2007-08-11 12:00</td>				
			</tr>
			<tr><td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="${req.contextPath}/device/repair/editRepairPlan.html">20080809110401070</a></td>
				<td>2008-08-1 11:30</td>
				<td>2008-08-1 21:30</td>
				<td></td>
				<td></td>
				<td style="text-align:left">sa</td>
				<td>2008-08-2 12:00</td>				
			</tr>
	     	</@listTable>
	     	</table>
	     	 <@buttonBar>
	    		<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/device/repair/editRepairPlan.html"/>
	    		<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    	</@buttonBar>
	    	
	    	
     </@ww.form>
</@htmlPage>