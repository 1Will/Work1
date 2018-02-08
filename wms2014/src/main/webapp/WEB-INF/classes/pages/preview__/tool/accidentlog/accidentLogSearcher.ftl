<@inputTable>
	 		<tr>
	 			<@ww.textfield label="'事故编号'" name="'device.code'" value="" cssClass="'underline'" required="false"/>
	 			<@ww.textfield label="'事故名称'" name="'device.code'" value="" cssClass="'underline'" required="false"/>
	 		</tr>
	 		<tr>
				<@ww.textfield label="'工装编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>				       
				<@ww.textfield label="'工装名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
				<@ww.textfield label="'工装图号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>		
			</tr>
			<tr>
			    <@ww.select label="'部门'"
	                    	name="area.code"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'所有', '机动部','焊冲一厂','发动机部','技术部','三现办','综合部','总装','塑料件','涂装'	                    	  	  
	                    	}"
	                    	value="selectedPeople"
	                    	required="false"
	        		/>
			    <@pp.dateRanger label="'事故发生日期'" name="'err.date'"
			     			value="" cssClass="'underline'" size="15"/>			
			</tr>
</@inputTable> 