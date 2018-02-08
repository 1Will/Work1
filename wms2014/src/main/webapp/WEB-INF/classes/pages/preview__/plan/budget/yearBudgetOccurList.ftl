<#include "/pages/includes/eam2008.ftl" />

<@framePage title="发生详细">
     <@ww.form name="'yearBudgetInputDetail'" action="''" method="'post'">
	        <@listTable>
	            <tr>
	                <th><input type="checkbox" name="checkbox"  onClick="selectedAll()" value="true"></th>
	                <th>序号</th>
	                <th>预算分类</th>	                
	                <th>名称</th>
	                <th>预算费用</th>
	                <th>已发生费用</th>
	                <th>预发生费用</th>
	                <th>执行状态</th>
	                <th>备注</th>
	            </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td>1</td>
		            <td>模具复制</td>
		            <td>模具复制</td>
		            <td>600000</td>
		            <td>200000</td>
		            <td>100000</td>
		            <td>预算执行50%</td>
		            <td>.....</td>
		        </tr>
		         <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td>2</td>
		            <td>维修与保养</td>
		            <td>维修与保养</td>
		            <td>400000</td>
		            <td>100000</td>
		            <td>100000</td>
		            <td>预算执行50%</td>
		            <td>.....</td>
		        </tr>
	      </@listTable>
	      <@buttonBar>
	         <@vbutton name="new"  class="button" value="${action.getText('new')}" onclick="open_detailDialog();return false;"/>         
	         <@vsubmit name="'delete'" value="'${action.getText('delete')}'" />	   
          </@buttonBar>  
          <script language="javascript">
	       function open_detailDialog() {
	      		var url = '${req.contextPath}/preview/plan/budget/editYearBudgetDetail.html';	      		
	      		popupModalDialog(url, 800,400);  
	      		self.location.reload();	
	      	}
	    </script>      
	 </@ww.form>
</@framePage>