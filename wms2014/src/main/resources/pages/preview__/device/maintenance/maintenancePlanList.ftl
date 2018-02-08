<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />

<@htmlPage title="设备保养计划查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>	
	 		<tr>
			 	<@ww.textfield label="'保养计划编号'" name="'area.code'" value="" cssClass="'underline'"  />
			 	<@ww.textfield label="'保养计划名称'" name="'area.code'" value="" cssClass="'underline'"  />
	         </tr> 		
			 <tr>       
			 	<@pp.dateRanger label="'计划执行日期'" name="'accidentOccurDateTm'" 
		       value="'${req.getParameter('accidentOccurDateTm_start')?if_exists}|${req.getParameter('accidentOccurDateTm_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/> 	      
		       <@dept0 />
		       <@ww.select label="'类别'"
		                    name="device"
		                   	headerKey="1" 
		                    headerValue="Select Month"
		                    list="{
		                    		'所有',
		                    		'一保', 
		                    		'二保'
		                    	  }"
		                    value="selectedDevice"
		                    	
		        	/> 
	        </tr>	 
        </@inputTable>
        <@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="${action.getText('new')}" url="editMaintenancePlan.html"/>
        </@buttonBar>
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
               <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
                <th>保养计划编号</th>
				<th>保养计划名称</th>
				<th>部门</th>
				<th>类别</th>
				<th>编制人</th>
				<th>编制日期</th>		
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editMaintenancePlan.html">123--459</a></td>
				<td style="text-align:left">压力机保养</td>
				<td style="text-align:left">冲压一厂</td>
				<td style="text-align:left">一保</td>
				<td style="text-align:left">张辉</td>
				<td>2007-09-22</td>
			</tr>	
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editMaintenancePlan.html">123--460</a></td>
				<td style="text-align:left">机器人保养</td>
				<td style="text-align:left">机动部</td>
				<td style="text-align:left">一保</td>
				<td style="text-align:left">张辉</td>
				<td>2007-09-22</td>
			</tr>		
	     	</@listTable>
	     	</table>	    
	     	<@buttonBar>
	    		<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    	</@buttonBar>
	    	
	    	<script>
	    		function popup() {
	    			popupModalDialog("listMaintenanceRules.html?selector=1", 1024, 800);
	    		}
	    	</script>
     </@ww.form>
</@htmlPage>