<@inputTable>
	 		<tr>
	 		    <@ww.textfield label="'保养标准编号'" name="'area.code'" value="" cssClass="'underline'"  />
	 		     <@ww.textfield label="'保养标准名称'" name="'area.code'" value="" cssClass="'underline'"  />
	 					</tr>
	 			<tr>
	 			<@ww.textfield label="'工装编号'" name="'tool.code'" value="" cssClass="'underline'"  />
	 			<@ww.textfield label="'工装名称'" name="'tool.code'" value="" cssClass="'underline'"  />
				<@ww.textfield label="'工装图号'" name="'tool.code'" value="" cssClass="'underline'"  />
	 		</tr>
	 		<tr>
	 			<@ww.select label="'保养分类'"
	                    	name="level"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'所有',
	                    	       '一保',
	                    	       '二保' 
	                    	  	  }"
	                    	value="selectedSuppliers"
	                    	/>
	 			 <@ww.select label="'所属部门'"
	                    	name="area.code"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'所有', '机动部','焊冲一厂','发动机部','技术部','三现办','综合部','总装','塑料件','涂装'	                    	  	  
	                    	}"
	                    	value="selectedPeople"
	                    	required="false"
	        		/>
	 			<@workflow/>
        </@inputTable>