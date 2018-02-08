<#include "/pages/includes/eam2008.ftl" />

<@framePage title="详细列表">
     <@ww.form name="'yearBudgetInputDetail'" action="''" method="'post'">
	        
 <@listTable>
	            <tr>
	                <th><input type="checkbox" name="checkbox"  onClick="selectedAll()" value="true"></th>
	                <th>序号</th>
	                <th>品名</th>	                
	                <th>规格</th>
	                <th>库存</th>
	                <th>需求量（个）</th>
	                <th>使用模具</th>
	            </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td>1</td>
		            <td>冲头座垫片</td>
		            <td>φ10*3</td>
		            <td>5</td>
		            <td>23</td>
		            <td></td>
		        </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td>2</td>
		            <td>冲头座垫片</td>
		            <td>φ10*2</td>
		            <td>4</td>
		            <td>24</td>
		            <td></td>
		        </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td>3</td>
		            <td>冲头座垫片</td>
		            <td>φ13*2</td>
		            <td>10</td>
		            <td>21</td>
		            <td></td>
		        </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td>4</td>
		            <td>冲头座垫片</td>
		            <td>φ13*3</td>
		            <td>8</td>
		            <td>20</td>
		            <td></td>
		        </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td>5</td>
		            <td>冲头座垫片</td>
		            <td>φ16*3</td>
		            <td>6</td>
		            <td>20</td>
		            <td></td>
		        </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td>6</td>
		            <td>冲头座垫片</td>
		            <td>φ16*2</td>
		            <td>14</td>
		            <td>17</td>
		            <td></td>
		        </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td>7</td>
		            <td>冲头座垫片</td>
		            <td>φ20*3</td>
		            <td>17</td>
		            <td>11</td>
		            <td></td>
		        </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td>8</td>
		            <td>冲头座垫片</td>
		            <td>φ20*2</td>
		            <td>2</td>
		            <td>17</td>
		            <td></td>
		        </tr>
		         <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td>9</td>
		            <td>冲头座垫片</td>
		            <td>φ25*3</td>
		            <td>0</td>
		            <td>28</td>
		            <td></td>
		        </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td>10</td>
		            <td>冲头座垫片</td>
		            <td>φ25*2</td>
		            <td>11</td>
		            <td>19</td>
		            <td></td>
		        </tr>
	      </@listTable>
	      <@buttonBar>
	         <@vbutton name="new"  class="button" value="${action.getText('new')}" />         
	         <@vsubmit name="'delete'" value="'${action.getText('delete')}'" />	   
          </@buttonBar> 
          <script language="javascript">
	       function open_detailDialog() {
	      		var url = '${req.contextPath}/preview/plan/budget/editYearBudgetToolDetail.html';	      		
	      		popupModalDialog(url, 800,400);  
	      		self.location.reload();	
	      	}
	    </script>            
	 </@ww.form>
</@framePage>