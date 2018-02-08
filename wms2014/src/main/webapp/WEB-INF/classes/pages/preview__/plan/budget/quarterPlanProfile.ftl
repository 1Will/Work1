<#include "/pages/includes/eam2008.ftl" />
<@htmlPage title="季度计划维护">
     <@ww.form  name="'yearBudget'" action="''" method="'post'" validate="true">   
         <@inputTable>
           <tr>
                <@ww.textfield label="'计划编号'" name="'area.code'" value="2008072911-1" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'计划名称'" name="'area.code'" value="'2008冲焊一厂第一季度计划'" cssClass="'underline'" required="true"/>
           </tr>
            <tr>	
             <@ww.select label="'分类'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{ '', 
	                    			'${action.getText('2007')}', 
	                    			'${action.getText('2006')}', 
	                    			'${action.getText('2005')}'
	                    	  	  }"
	                    	value="selectedDevice"
	         />	       
			 <@ww.select label="'年度'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{ '', 
	                    			'${action.getText('2007')}', 
	                    			'${action.getText('2006')}', 
	                    			'${action.getText('2005')}'
	                    	  	  }"
	                    	value="selectedDevice"
	         />	
	       </tr>
           <tr>	       
	         <@ww.select label="'季度'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{ '', 
	                    			'${action.getText('第一季度')}', 
	                    			'${action.getText('第二季度')}',
	                    			'${action.getText('第三季度')}',
	                    			'${action.getText('第四季度')}'
	                    	  	  }"
	                    	value="selectedDevice"
             />	       
	         <@ww.select label="'部门'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{ '', 
	                    			'${action.getText('冲压一车间')}', 
	                    			'${action.getText('冲压二车间')}',
	                    			'${action.getText('冲焊一车间')}'
	                    	  	  }"
	                    	value="selectedDevice"
             />
           </tr>
           <tr>
              <@ww.select label="'计划状态'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{ '', 
	                    			'${action.getText('计划中')}', 
	                    			'${action.getText('计划外')}'
	                    	  	  }"
	                    	value="selectedDevice"
              />
              <@ww.textfield label="'预算费用'" name="'area.code'" value="" cssClass="'underline'" required="true" readonly="true"/>
           </tr>
           <tr>
                <@ww.textfield label="'已发生费用'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            	<@ww.textfield label="'计划制定人'" name="'area.code'" value="'System'" cssClass="'underline'" required="true"/>
           </tr>
           <tr>
            	<@pp.datePicker label="'计划制定日期'" name="'repair.estimate.time0'"
	     			value="'2007-02-25'" cssClass="'underline'" size="15"/>
           </tr>
         </@inputTable> 
         <@buttonBar>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" />	     
	         <@vsubmit name="'submitDoc'" value="'${action.getText('submit')}'"/>     
	         <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/preview/plan/budget/listQuarterPlan.html"/>
         </@buttonBar>
     </@ww.form>
       <script language="JavaScript" type="text/JavaScript"> 
		window.onload = function () {
	 		document.all.frame.src='${req.contextPath}/preview/plan/budget/listQuarterPlanDetail.html';
	 		document.getElementById("yearBudgetDetail").className = "selectedtab";
	 	}
	 </script>
     <ul id="beautytab">
			<li><a id="yearBudgetDetail"   onclick="activeTab(this);" href='${req.contextPath}/preview/plan/budget/listQuarterPlanDetail.html' target="frame" class="selectedtab">计划明细列表</a></li>
			<li><a id="yearBudgetOccur"   onclick="activeTab(this);" href='${req.contextPath}/preview/plan/budget/listYearBudgetOccur.html' target="frame" >发生详细</a></li>
     </ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
</@htmlPage>
