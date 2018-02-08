<@inputTable>
    <tr>
        <@ww.textfield label="'点检计划编号'" name="'planNo'" value="'${req.getParameter('planNo')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'点检计划名称'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>

    </tr>
    <tr>
    	<@ww.textfield label="'工装编号'" name="'deviceNo'" value="'${req.getParameter('deviceNo')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'工装名称'" name="'deviceName'" value="'${req.getParameter('deviceName')?if_exists}'" cssClass="'underline'"/>
    </tr>
    <tr>
        <@pp.dateRanger label="'计划日期'" name="'scheduleTime'" 
		  value="'${req.getParameter('scheduleTime_start')?if_exists}|${req.getParameter('scheduleTime_end')?if_exists}'"
		  cssClass="'underline'" dateFormat="date"/>  
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
    </tr>
</@inputTable>