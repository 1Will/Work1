<@inputTable>
		<tr>
			 <@ww.select label="'工装类别'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'所有',
	                    			'模具', 
	                    			'夹具', 
	                    			'工位器具',
	                    			'检具',
	                    			'辅具'
	                    	  	  }"
	                    	value="selectedDevice"
	        	/>  	
		</tr>
		<tr>
			<@ww.textfield label="'工装编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>				       
			<@ww.textfield label="'工装名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>				       
			 
		</tr>
		<tr>		
			<@ww.textfield label="'工装图号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>				      
			<@pp.datePicker label="'标定日期'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15"/>
	    </tr>
		
</@inputTable>  