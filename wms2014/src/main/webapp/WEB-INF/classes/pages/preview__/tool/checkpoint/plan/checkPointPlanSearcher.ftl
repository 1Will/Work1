<@inputTable>
	<@ww.hidden name="'invalid'" value="false"/>
     <tr>
        <@ww.textfield label="'点检计划编号'" name="'deviceNo'" value="''" cssClass="'underline'"/>
        <@ww.textfield label="'点检计划名称'" name="'installPlace'" value="''" cssClass="'underline'"/>
     </tr>
    <tr>
        <@ww.textfield label="'设备编号'" name="'deviceNo'" value="''" cssClass="'underline'"/>
        <@ww.textfield label="'设备名称'" name="'installPlace'" value="''" cssClass="'underline'"/>
     </tr>  
    <tr>      
		<@ww.select label="'部门'"
	                name="device"
	                headerKey="1" 
	                headerValue="Select Month"
	                list="{
	                    	'所有',		
	                    	'冲焊一厂', 
	                    	'总装一厂'
	                    }"
	                 value="selectedDevice"
	                    	
	      />  
	       <@pp.dateRanger label="'计划执行时间'" name="'cardCreatedTime'" 
		  value="''" cssClass="'underline'" dateFormat="date"/> 
    </tr>
	</@inputTable>
