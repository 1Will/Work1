<@inputTable>
	 		<tr>
	 			<@ww.textfield label="'故障编号'" name="'device.code'" value="" cssClass="'underline'" required="false"/>
	 			<@ww.textfield label="'故障名称'" name="'device.code'" value="" cssClass="'underline'" required="false"/>
	 		</tr>
	 		<tr>
			<@ww.textfield label="'工装编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>				       
			<@ww.textfield label="'工装名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
			<@ww.textfield label="'工装图号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>				       
			 
			</tr>
			<tr>		
			    <@pp.dateRanger label="'故障发生日期'" name="'err.date'"
			     			value="" cssClass="'underline'" size="15"/>
			    <@ww.select label="'状态'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'所有 ',
	                    			'待解决', 
	                    			'已解决'
	                    	  	  }"
	                    	value="selectedDevice"
	        	/>  			
			</tr>
</@inputTable> 