<#include "/pages/includes/eam2008.ftl" />

<@framePage title="计划详细列表">
     <@ww.form name="'yearBudgetInputDetail'" action="''" method="'post'">
	        <@listTable>
	            <tr>
	                <th><input type="checkbox" name="checkbox"  onClick="selectedAll()" value="true"></th>
	                <th>序号</th>
	                <th>计划分类</th>	                
	                <th>计划名称</th>
	                <th>金额</th>
	            </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">1</a></td>
		            <td>工装制造</td>
		            <td>模具制造</td>
		            <td>150000</td>
		        </tr>
		         <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">2</a></td>
		            <td>备用品购买</td>
		            <td>定位销购买</td>
		            <td>75000</td>
		        </tr>
		         <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">3</a></td>
		            <td>工装复制</td>
		            <td>检具复制</td>
		            <td>600000</td>
		        </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">4</a></td>
		            <td>技术改造</td>
		            <td>技术改造</td>
		            <td>600000</td>
		       </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">5</a></td>
		            <td>工装维修</td>
		            <td>工装维修</td>
		            <td>600000</td>
		        </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">6</a></td>
		            <td>工装保养</td>
		            <td>工装保养</td>
		            <td>600000</td>
		       </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">7</a></td>
		            <td>工装整改</td>
		            <td>工装整改</td>
		            <td>600000</td>
		       </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">8</a></td>
		            <td>预防性维修</td>
		            <td>预防性维修</td>
		            <td>600000</td>
		        </tr>
	      </@listTable>
	      <@buttonBar>
	         <@vbutton name="new"  class="button" value="${action.getText('new')}" onclick="open_detailDialog();return false;"/>         
	         <@vsubmit name="'delete'" value="'${action.getText('delete')}'" />	   
          </@buttonBar>  
          <script language="javascript">
	       function open_detailDialog() {
	      		var url = '${req.contextPath}/preview/plan/budget/editQuarterPlanDetail.html';	      		
	      		popupModalDialog(url, 800,400);  
	      		self.location.reload();	
	      	}
	    </script>      
	 </@ww.form>
</@framePage>