<#include "/pages/includes/eam2008.ftl" />

<@framePage title="预算详细列表">
     <@ww.form name="'yearBudgetInputDetail'" action="''" method="'post'">
	        
 <@listTable>
	            <tr>
	                <th><input type="checkbox" name="checkbox"  onClick="selectedAll()" value="true"></th>
	                <th>序号</th>
	                <th>种类名称</th>	                
	                <th>单价</th>
	                <th>数量</th>
	                <th>金额（元）</th>
	            </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">1</a></td>
		            <td>冲头（异型）</td>
		            <td>800</td>
		            <td>40</td>
		            <td>32000</td>
		        </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">2</a></td>
		            <td>冲头及冲套（标准)</td>
		            <td>50</td>
		            <td>973</td>
		            <td>48650</td>
		        </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">3</a></td>
		            <td>冲头固定座</td>
		            <td>150</td>
		            <td>300</td>
		            <td>45000</td>
		        </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">4</a></td>
		            <td>导柱导套</td>
		            <td>350</td>
		            <td>40</td>
		            <td>14000</td>
		        </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">5</a></td>
		            <td>斜锲</td>
		            <td>3000</td>
		            <td>3</td>
		            <td>9000</td>
		       </tr>
		       <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">6</a></td>
		            <td>悬吊凸轮</td>
		            <td>5000</td>
		            <td>3</td>
		            <td>15000</td>
		       </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">7</a></td>
		            <td>耐磨板</td>
		            <td>100</td>
		            <td>40</td>
		            <td>4000</td>
		       </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">8</a></td>
		            <td>弹簧</td>
		            <td>300</td>
		            <td>300</td>
		            <td>90000</td>
		       </tr>
		       <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">9</a></td>
		            <td>气缸</td>
		            <td>5000</td>
		            <td>3</td>
		            <td>15000</td>
		       </tr>
		       <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">10</a></td>
		            <td>聚胺脂</td>
		            <td>200</td>
		            <td>5</td>
		            <td>1000</td>
		     </tr>
		     <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">11</a></td>
		            <td>弹顶销</td>
		            <td>200</td>
		            <td>20</td>
		            <td>4000</td>
		     </tr>
		     <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">12</a></td>
		            <td>弹簧导向销</td>
		            <td>180</td>
		            <td>20</td>
		            <td>3600</td>
		     </tr>
		        <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">13</a></td>
		            <td>卸料定位销</td>
		            <td>100</td>
		            <td>30</td>
		            <td>3000</td>
		     </tr>
		     <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">14</a></td>
		            <td>内六角螺丝</td>
		            <td>5</td>
		            <td>1000</td>
		            <td>5000</td>
		     </tr>
		     <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">15</a></td>
		            <td>定位销</td>
		            <td>10</td>
		            <td>365</td>
		            <td>3650</td>
		     </tr>
		     <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">16</a></td>
		            <td>快换冲头</td>
		            <td>130</td>
		            <td>30</td>
		            <td>3900</td>
		    </tr>
		    <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">17</a></td>
		            <td>冲头座垫片</td>
		            <td>5</td>
		            <td>200</td>
		            <td>1000</td>
		     </tr>
		     <tr>
		            <td><input type="checkbox" name="checkbox" value="true"></td>
		            <td><a href="#" onclick="open_detailDialog();return false;">18</a></td>
		            <td>吊棒</td>
		            <td>100</td>
		            <td>1000</td>
		            <td>100000</td>
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