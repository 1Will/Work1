<@inputTable>
 		<tr>
 			<@ww.textfield label="'库位编码'" name="'device.code'" value="" cssClass="'underline'" required="false"/>
 			<@ww.select label="'仓库'"
	                    	name="area.code"
	                    	headerValue=""
	                    	list="{
	                    		'所有',
	                    		'一号仓库',
	                    		'二号仓库',
	                    		'三号仓库'	                    	  	  
	                    	}"
	                    	value="selectedPeople"
	                    	required="false"
	        />
	        <@ww.select label="'库区'"
	                    	name="area.code"
	                    	headerValue=""
	                    	list="{
	                    		'所有',
	                    		'收货库区',
	                    		'存货库区',
	                    		'拣货库区'	                    	  	  
	                    	}"
	                    	value="selectedPeople"
	                    	required="false"
	        />
		</tr>
 		<tr>
 			<@ww.select label="'库位类型'"
	                    	name="area.code"
	                    	headerValue=""
	                    	list="{
	                    		'所有',
	                    		'收获',
	                    		'存货',
	                    		'发货',
	                    		'质损',
	                    		'补货墙'	                    	  	  
	                    	}"
	                    	value="selectedPeople"
	                    	required="false"
	        />
 			<@ww.select label="'承载类型'"
	                    	name="area.code"
	                    	headerValue=""
	                    	list="{
	                    		'所有',
	                    		'拣货货架',
	                    		'存货货架',
	                    		'平库'	                    	  	  
	                    	}"
	                    	value="selectedPeople"
	                    	required="false"
	        />
	        <@ww.select label="'状态'"
	                    	name="area.code"
	                    	headerValue=""
	                    	list="{
	                    		'所有',
	                    		'已用',
	                    		'未用'	                    	  	  
	                    	}"
	                    	value="selectedPeople"
	                    	required="false"
	        />
		</tr>
		<tr>
		<td></td><td>
	<input type="checkbox" name="checkbox" value="true"/>仅查询失效</td>
		</tr>
</@inputTable> 