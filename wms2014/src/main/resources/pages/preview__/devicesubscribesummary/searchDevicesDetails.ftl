<@inputTable>
    <tr>
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