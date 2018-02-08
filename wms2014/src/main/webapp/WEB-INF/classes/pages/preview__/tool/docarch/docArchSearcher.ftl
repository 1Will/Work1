<@inputTable>
	 <tr>
        <@ww.textfield label="'名称'" name="'deviceNo'" value="''" cssClass="'underline'"/>
        <@ww.select label="'种类'"
		                    name="device"
		                   	headerKey="1" 
		                    headerValue="Select Month"
		                    list="{
		                    		'全部',	
		                    		'程序文件', 
		                    		'制度文件'
		                    	  }"
		                    value="selectedDevice"
		                    required="false"
		                    	
		        	/> 
     </tr>
</@inputTable>