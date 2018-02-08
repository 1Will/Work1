<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@framePage title="润滑润滑实施列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            	<th>项目号</th>
			 	<th>润滑部位</th>
				<th>润滑标准</th>
				<th>润滑方法</th>
				<th>润滑材质</th>
		  		<th>计划润滑计量</th>
		  		<th>实际润滑计量</th>
				<th>责任人</th>
				<th>计划执行人</th>
				<th>计划执行日期</th>
				<th>实际执行人</th>
				<th>实际执行日期</th>
				<th>执行结果</th>
			</tr>				
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td>1</td>
				<td style="text-align:left">机床链条</td>
				<td style="text-align:left">上润滑油</td>
				<td style="text-align:left">手工打腊</td>	
				<td style="text-align:left">二号润滑油</td>	
				<td style="text-align:left">20ml</td>	
				<td style="text-align:left">
				  <input type="text" name="dutyPeople.name" 
		        		 class="underline"  value="20ml"  maxlength="150" size="10" disabled="false"/>
				</td>			
				<td style="text-align:left">何超</td>
				<td style="text-align:left">何超</td>
				<td >2007-01-23</td>
								<td>
					<input type="text" name="dutyPeople.name" 
		        			class="underline"  value="何超"  maxlength="150" size="10" disabled="false"/>
		        		<label id=""></label>
			    		<a onClick=";">
			        		<img id="" src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
			        	</a>
				</td>
				<td>
				     <input type="text" name="repair.actual.time0" size="15" id="repair.actual.time0"  value="2007-01-23" class="underline" />
				    <link rel="stylesheet" href="/eam2008/stylesheets/calendar.css" type="text/css"/>
				    <script language="javascript" src="/eam2008/javascripts/calendar.js"></script>
				    <script language="javascript" src="/eam2008/javascripts/lang/calendar.js"></script>
				    <script language="javascript" src="/eam2008/javascripts/calendar-setup.js"></script>
				    <img id="area_repair.actual.time0_trigger" src="/eam2008/images/calendar/calendar.gif" align=absMiddle border=0 />
					<script language="javascript">
					  Calendar.setup({
					        formName       :    "area",
					        inputField     :    "repair.actual.time0",
					        button         :    "area_repair.actual.time0_trigger",
					                                ifFormat       :  "%Y-%m-%d",
					                                    showsTime      :   false,
					                timeFormat     :    "24",
					        showOthers     :   true
					    });
					</script>
				</td>
		  		<td style="text-align:left">
					<select>
					  <option value="1"></option>
					  <option value="1">未完成</option>
					  <option value="1">已完成</option>
					</select>
		  		</td>
			</tr>		
	     	</@listTable>
	     	</table>	
	     	<@buttonBar>
	     	
	    		<@vbutton class="button" value="保存"/>
	    	</@buttonBar>  	
     </@ww.form>
</@framePage>