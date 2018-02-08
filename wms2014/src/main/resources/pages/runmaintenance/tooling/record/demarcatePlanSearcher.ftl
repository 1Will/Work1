<@inputTable>
		<tr>
			<@ww.textfield label="'标定计划编号'" name="'planNo'" value="'${req.getParameter('planNo')?if_exists}'" cssClass="'underline'" />				       
			<@ww.textfield label="'标定计划名称'" name="'planName'" value="'${req.getParameter('planName')?if_exists}'" cssClass="'underline'" />				       		 
		</tr>
		<tr>
	       <@ww.select label="'部门'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'所有',
	                    			'机动部', 
	                    			'生产部'
	                    	  	  }"
	                    	value="selectedDevice"
	        />
	        <@ww.select label="'标定计划状态'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'所有',
	                    			'未实施', 
	                    			'实施中'
	                    	  	  }"
	                    	value="selectedDevice"
	        />   		
		   <@pp.dateRanger label="'计划标定日期'" 
			                name="'procExecTime'" 
		                    value="'${req.getParameter('procExecTime_start')?if_exists}|${req.getParameter('procExecTime_end')?if_exists}'"
		                    cssClass="'underline'" dateFormat="date"/>       	
		</tr>		
</@inputTable>  