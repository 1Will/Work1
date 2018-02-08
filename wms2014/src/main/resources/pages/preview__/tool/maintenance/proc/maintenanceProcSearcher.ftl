<@inputTable>
	 		<tr>
	 			<@ww.textfield label="'保养实施编号'" name="'tool.code'" value="" cssClass="'underline'"  />
	 			<@ww.textfield label="'保养实施名称'" name="'tool.code'" value="" cssClass="'underline'"  />
	 		</tr>
	 		<tr>
	 			<@ww.textfield label="'工装编号'" name="'tool.code'" value="" cssClass="'underline'"  />
	 			<@ww.textfield label="'工装名称'" name="'tool.code'" value="" cssClass="'underline'"  />
				<@ww.textfield label="'工装图号'" name="'tool.code'" value="" cssClass="'underline'"  />
	 		</tr>
	 		<tr>
	 			<@pp.dateRanger label="'实施时间'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15"/>
	 			 <@ww.select label="'部门'"
	                    	name="area.code"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'所有', '机动部','焊冲一厂','发动机部','技术部','三现办','综合部','总装','塑料件','涂装'	                    	  	  
	                    	}"
	                    	value="selectedPeople"
	                    	required="false"
	        		/>
	 			<@workflow/>
	 		</tr>
</@inputTable>