<#include "../../../../includes/eam2008.ftl" />
<@htmlPage title="点检计划维护">
	<@ww.form  namespace="'/runmaintenance/checkPoint'" name="'rule'" action="''" method="'post'">
		 <@ww.token name="saveRuleToken"/>
		 <@buttonBar>
        	<input type="button" class="button" name="selectRule" value="选择点检标准" onclick="checkPointRule_openDialog()" />
    	</@buttonBar>
		 <@inputTable> 
		 	<tr>
               <@ww.textfield label="'点检计划编号'" name="'ruleNo'" value="" cssClass="'underline'"/>
               <@ww.textfield label="'点检计划名称'" name="'name'" value="" cssClass="'underline'"/>
            </tr>
            <tr>
               <@ww.textfield label="'工装编号'" name="'deviceNo'" value="'${req.getParameter('deviceNo')?if_exists}'" cssClass="'underline'"/>
               <@ww.textfield label="'工装名称'" name="'deviceName'" value="'${req.getParameter('deviceName')?if_exists}'" cssClass="'underline'"/>
            </tr>
            <tr>
    	       <@ww.select label="'分类'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'工资', 
	                    			'设备',
	                    			'其它'
	                    	  	  }"
	                    	value="selectedDevice"
	           />
	           <@ww.select label="'部门'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'机动部', 
	                    			'生产部'
	                    	  	  }"
	                    	value="selectedDevice"
	           />
            </tr> 
            <tr>
               <@pp.dateRanger label="'计划日期'" name="'scheduleTime'" 
		            value="'${req.getParameter('scheduleTime_start')?if_exists}|${req.getParameter('scheduleTime_end')?if_exists}'"
		            cssClass="'underline'" dateFormat="date"/>  
            </tr>
		 </@inputTable>	 
		 <@buttonBar>
		 	<@vsubmit value="'${action.getText('save')}'" />
        	<@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/tooling/checkpoint/listCheckPointPlan.html"/>	
        </@buttonBar>
	</@ww.form>
</@htmlPage>