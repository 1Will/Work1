<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@framePage title="润滑计划列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 <#--
	 	<#if req.getParameter("selector")?exists>
	 		<@inputTable>
	 		<tr>
	        	<@ww.textfield label="'润滑计划编号'" name="'area.code'" value="" cssClass="'underline'" />
	        	<@ww.textfield label="'润滑计划名称'" name="'area.code'" value="" cssClass="'underline'" />
	        </tr>	      
	        <tr>
				 <@pp.dateRanger label="'计划润滑日期'" name="'accidentOccurDateTm'" 
		       value="'${req.getParameter('accidentOccurDateTm_start')?if_exists}|${req.getParameter('accidentOccurDateTm_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/> 
	        	<@dept0/>
	        </tr>
	        </@inputTable>
	        <@buttonBar>
	        	<@redirectButton value="${action.getText('search')}" url="#"/>
	        	<@redirectButton value="${action.getText('new')}" url="editSmoothPlanItem.html"/>
        	</@buttonBar>
     </#if>
     -->
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
				<th>责任人</th>
				<th>计划执行人</th>
				<th>计划执行日期</th>
				<th>执行结果</th>
			</tr>				
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td>
				<#--
				<a href="#" onclick="popupModalDialog('${req.contextPath}/popup/smoothRuleSelector.html',800,500);return false;">
				</a>
				-->
				1
				</td>
				<td style="text-align:left">机床链条</td>
				<td style="text-align:left">上润滑油</td>
				<td style="text-align:left">手工打腊</td>	
				<td style="text-align:left">二号润滑油</td>	
				<td style="text-align:left">
				  <input type="text" name="dutyPeople.name" 
		        		 class="underline"  value="20ml"  maxlength="150" size="10" disabled="false"/>
				</td>			
				<td style="text-align:left">何超</td>
				<td>
					<input type="text" name="dutyPeople.name" 
		        			class="underline"  value=""  maxlength="150" size="10" disabled="false"/>
		        		<label id=""></label>
			    		<a onClick=";">
			        		<img id="" src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
			        	</a>
				</td>
				<td>
				     <input type="text" name="repair.actual.time0" size="15" id="repair.actual.time0"  class="underline" />
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
		  		<td style="text-align:left">已完成</td>
			</tr>		
	     	</@listTable>
            </table>
            <@buttonBar>
            		<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/popup/smoothRuleSelector.html',800,500);"/>
		    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>  
     </@ww.form>
</@framePage>