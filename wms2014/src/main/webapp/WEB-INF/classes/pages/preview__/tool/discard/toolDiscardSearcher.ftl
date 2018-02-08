<@inputTable>
	<@ww.hidden name="'invalid'" value="false"/>
	 <tr>
        <@ww.textfield label="'报废编号'" name="'deviceNo'" value="''" cssClass="'underline'"/>
        <@ww.textfield label="'报废名称'" name="'installPlace'" value="''" cssClass="'underline'"/>
     </tr>
    <tr>
        <@ww.textfield label="'工装编号'" name="'deviceNo'" value="''" cssClass="'underline'"/>
        <@ww.textfield label="'工装图号'" name="'installPlace'" value="''" cssClass="'underline'"/>
        

     </tr>
    <tr>
    	<@ww.textfield label="'工装名称'" name="'name'" value="''" cssClass="'underline'"/>
    	<@ww.select label="'工装类别'"
	                name="device"
	                headerKey="1" 
	                headerValue="Select Month"
	                list="{
	                    	'全部',		
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
    	<@pp.dateRanger label="'报废时间'" name="'cardCreatedTime'" 
		  value="''"
		  cssClass="'underline'" dateFormat="date"/> 
	</tr>

	</@inputTable>