<@inputTable>
	 		<tr>
	 			<@ww.textfield label="'清洗计划编号'" name="'device.code'" value="" cssClass="'underline'" required="false"/>
	 			<@ww.textfield label="'清洗计划名称'" name="'device.code'" value="" cssClass="'underline'" required="false"/>
	 		</tr>
	 		<tr>
	 			<@ww.textfield label="'编制人'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
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
			    <@pp.dateRanger label="'清洗计划日期'" name="'err.date'"
			     			value="" cssClass="'underline'" size="15"/>			
			</tr>
</@inputTable> 