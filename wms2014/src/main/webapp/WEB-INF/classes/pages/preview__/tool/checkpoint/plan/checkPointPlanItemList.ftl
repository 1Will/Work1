 <#include "../../../../includes/eam2008.ftl" />
 <#include "/javascripts/selectAll.js" />
	<@framePage title="${action.getText('')}"> 
	<@ww.form name="'listForm'" action="'searchProducts'" method="'post'">  
	<@inputTable>       
		<tr>   
		<@ww.select label="'点检日期(周)'"
	                name="device"
	                headerKey="1" 
	                headerValue="Select Month"
	                list="{
	                    	'第一周',		
	                    	'第二周', 
	                    	'第三周',
	                    	'第四周'
	                    }"
	                 value="selectedDevice"
	      />
	    </tr>  
	 </@inputTable>
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th width="30%" >部位</th>          
            	<#list ["1", "2", "3", "4", "5", "6", "7"] as x>	  	
            	<th>${x}</th>
            	</#list>
			<tr>
			<tr>
				<td style="text-align:left">
					现场
				</td>
				<#list ["1", "2", "3", "4", "5", "6", "7"] as x>	  	
            	<td></td>
            	</#list>
			</tr>
			<tr>
				<td style="text-align:left">
					天车主体结构
				</td>	
				<#list ["1", "2", "3", "4", "5", "6", "7"] as x>	  	
            	<td/>
            	</#list>			
			</tr>		
			<tr>
				<td style="text-align:left">
					缓冲装置
				</td>			
				<#list ["1", "2", "3", "4", "5", "6", "7"] as x>	  	
            	<td/>
            	</#list>	
			</tr>
			<tr>
				<td style="text-align:left">
					检查导轨
				</td>		
				<#list ["1", "2", "3", "4", "5", "6", "7"] as x>	  	
            	<td/>
            	</#list>		
			</tr>
			<tr>
				<td style="text-align:left">
					检查安全装置（急停开关、光电开关、接近开关、飞轮制动器、安全插销、安全信号灯、指示灯)
				</td>		
				<#list ["1", "2", "3", "4", "5", "6", "7"] as x>	  	
            	<td/>
            	</#list>		
			</tr>
			<tr>
				<td style="text-align:left">
					检查储气罐、油水分离器
				</td>		
				<#list ["1", "2", "3", "4", "5", "6", "7"] as x>	  	
            	<td/>
            	</#list>		
			</tr>
			<tr>
				<td style="text-align:left">
					检查润滑及滤油器情况
				</td>		
				<#list ["1", "2", "3", "4", "5", "6", "7"] as x>	  	
            	<td/>
            	</#list>		
			</tr>
			<tr>
				<td style="text-align:left">
					检查各操作按钮、急停开关
				</td>		
				<#list ["1", "2", "3", "4", "5", "6", "7"] as x>	  	
            	<td/>
            	</#list>		
			</tr>
			<tr>
				<td style="text-align:left">
					检查离合器制动器性能
				</td>		
				<#list ["1", "2", "3", "4", "5", "6", "7"] as x>	  	
            	<td/>
            	</#list>		
			</tr>
			<tr>
				<td style="text-align:left">
					连接及连接器螺栓、外露盖板螺栓
				</td>		
				<#list ["1", "2", "3", "4", "5", "6", "7"] as x>	  	
            	<td/>
            	</#list>		
			</tr>
			<tr>
				<td style="text-align:left">
					检查离合器制动器性能
				</td>		
				<#list ["1", "2", "3", "4", "5", "6", "7"] as x>	  	
            	<td/>
            	</#list>		
			</tr>
			<tr >
				<td style="text-align:left">
					运行台时(时)
				</td>		
				<#list ["1", "2", "3", "4", "5", "6", "7"] as x>	  	
            	<td/>
            	</#list>		
			</tr>
			<tr>
				<td style="text-align:left">
					故障台时(时)
				</td>		
				<#list ["1", "2", "3", "4", "5", "6", "7"] as x>	  	
            	<td/>
            	</#list>		
			</tr>
			<tr>
				<td style="text-align:left">
					停机台时(时)
				</td>		
				<#list ["1", "2", "3", "4", "5", "6", "7"] as x>	  	
            	<td/>
            	</#list>		
			</tr>
			<tr>
				<td style="text-align:left">
					点检人
				</td>		
				<#list ["1", "2", "3", "4", "5", "6", "7"] as x>	  	
            	<td/>
            	</#list>		
			</tr>
			<tr>
				<td style="text-align:left">
					确认人
				</td>		
				<#list ["1", "2", "3", "4", "5", "6", "7"] as x>	  	
            	<td/>
            	</#list>		
			</tr>
			<tr>
				<td style="text-align:left">
					维修记录
				</td>		
				<#list ["1", "2", "3", "4", "5", "6", "7"] as x>	  	
            	<td/>
            	</#list>		
			</tr>
			<#--
			<#list ["1", "2"] as x>			
			<tr>
			<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editCheckPointRuleItem.html',600,300); return false;">${x}</a></td>				
				<td>
				<table  border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
					<tr><td>x</td></tr>
					<tr><td>y</td></tr>
				</table>
				</td>
			</tr>
			</#list>
			-->
			<tr>
	     	</@listTable>
	     	</table>
	     	<#--
	     	<@buttonBar>
	    		<input type="button" name="new"value="保存" class="button" onclick="#"/>
	    	</@buttonBar>  
	    	-->
	    	<script>
	    		  function change(obj) {
	    		  alert(obj.activeRow);
				  	if (!document.getElementsByTagName) return; 
				  	var anchors = document.getElementsByTagName("a"); 
				  	for (var i=0; i<anchors.length; i++) { 
				  		anchors[i].style.color="black";
				  		anchors[i].style.fontWeight="normal";
				  	}
				  	obj.style.color="red";
				  	obj.style.fontWeight="bold";
				  	return false;
				  }
	    	</script>
	    </@ww.form>
	    <script language="javascript" type="text/JavaScript">
	      var item = '${req.getParameter('item')?if_exists}';
		  var view = '${req.getParameter('view')?if_exists}';
		  if (item==1) {
		    __disableAllElements__(document.forms[0], new Array(""));
		  }
		  
	    </script>
	</@framePage>