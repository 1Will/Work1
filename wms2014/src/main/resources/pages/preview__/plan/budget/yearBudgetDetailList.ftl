<#include "/pages/includes/eam2008.ftl" />

<@framePage title="预算明细列表">
     <@ww.form name="'yearBudgetInputDetail'" action="''" method="'post'">
	        <@listTable>
	            <tr>
	                <th><input type="checkbox" name="checkbox"  onClick="selectedAll()" value="true"></th>
	                <th>序号</th>
	                <th>预算分类</th>	                
	                <th>名称</th>
	                <th>金额</th>
	                <th>申报原因</th>
	            </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">1</a></td>
		            <td>工装制造</td>
		            <td>工装制造</td>
		            <td>150000</td>
		            <td>水车、工具柜等</td>
		        </tr>
		         <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">2</a></td>
		            <td>备用品购买</td>
		            <td>备用品购买</td>
		            <td>75000</td>
		            <td>托具及工位货架、枪头枪管等制作、滑撬配件</td>
		        </tr>
		         <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">3</a></td>
		            <td>工装复制</td>
		            <td>工装复制</td>
		            <td>600000</td>
		            <td>瑞风一、二代总成工位器具制作</td>
		        </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">4</a></td>
		            <td>技术改造</td>
		            <td>技术改造</td>
		            <td>600000</td>
		            <td>瑞风二代新车型及瑞风一代</td>
		       </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">5</a></td>
		            <td>工装维修</td>
		            <td>工装维修</td>
		            <td>600000</td>
		            <td>配件货架</td>
		        </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">6</a></td>
		            <td>工装保养</td>
		            <td>工装保养</td>
		            <td>600000</td>
		            <td>纵梁磨损较快，需要进行保养</td>
		       </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">7</a></td>
		            <td>工装整改</td>
		            <td>工装整改</td>
		            <td>600000</td>
		            <td>进行整改</td>
		       </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">8</a></td>
		            <td>预防性维修</td>
		            <td>预防性维修</td>
		            <td>600000</td>
		            <td>对模具进行预防性维修</td>
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