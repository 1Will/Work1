<@inputTable>
    <tr>
        <@ww.textfield label="'图号'" name="'deviceNo'" value="''" cssClass="'underline'"/>
        <@ww.textfield label="'名称'" name="'name'" value="''" cssClass="'underline'"/>
	</tr>
    <tr>
       <@ww.textfield label="'规格型号'" name="'specification'" value="''" cssClass="'underline'"/>
       <@ww.select label="'种类'"
	            name="'Category'"
	            headerKey="1" 
	            headerValue="Select Month"
	            list="{
	                   '所有',
	                   '机械类', 
	                   '工具类', 
	                   '润滑类',
	                   '电器类'
	                   }"
		         value="selectedDevice"
		        />                    
	</tr>
    <tr>
    	<@ww.select label="'明细种类'"
		            name="'toolCategory'"
		            headerKey="1" 
		            headerValue="Select Month"
		            list="{
		                   '所有',
		                   '分度类', 
		                    '销类', 
		                    '法兰类',
		                    '顶尖'
		                   }"
		            value="selectedDevice"
		        	/> 
  		<@ww.select label="'仓库'"
            name="'stock'"
            headerKey="1" 
            headerValue="Select"
            list="{ 
            	   '所有',   
                   '一号仓库', 
                   '二号仓库'
                   }"
	         value="'${stock?if_exists}'"
	        />   		        	
    </tr>
</@inputTable>