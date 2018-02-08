<@inputTable>
		<tr>
			<@ww.textfield label="'领用单编号'" name="'area.code'" value="" cssClass="'underline'" />
	 		<@ww.textfield label="'领用单名称'" name="'area.code'" value="" cssClass="'underline'"/>
		</tr>
		<tr>
	 		<@ww.textfield label="'工装编号'" name="'area.code'" value="" cssClass="'underline'" />
	 		<@ww.textfield label="'工装名称'" name="'area.code'" value="" cssClass="'underline'"/>
	 		<@ww.textfield label="'工装图号'" name="'area.code'" value="" cssClass="'underline'"/>
	 	</tr>
		
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
	     	<@ww.select label="'部门'"
	                    	name="area.code"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'所有', '机动部','焊冲一厂','发动机部','技术部','三现办','综合部','总装','塑料件','涂装'	                    	  	  
	                    	}"
	                    	value="selectedPeople"
	                    	required="false"
	        		/>
	    </tr>
	    <tr>
	    	<@ww.select label="'状态'"
	                    	name="state"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'所有',
	                    			'领用',
	                    			'归还' 
	                    	  	  }"
	                    	value="selectedDevice"
	        	/>  
	        <@pp.dateRanger label="'领用时间'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15"/> 
	      	
	 	</tr>    	
	 	
	</@inputTable>  
	