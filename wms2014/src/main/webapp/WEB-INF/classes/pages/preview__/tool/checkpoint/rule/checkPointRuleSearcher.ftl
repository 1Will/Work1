<@inputTable>
	<@ww.hidden name="'invalid'" value="false"/>
	 <tr>
        <@ww.textfield label="'点检标准编号'" name="'deviceNo'" value="''" cssClass="'underline'"/>
        <@ww.textfield label="'点检标准名称'" name="'installPlace'" value="''" cssClass="'underline'"/>
     </tr>   
     <#--
    <tr>
    	<@ww.textfield label="'工装名称'" name="'name'" value="''" cssClass="'underline'"/>
    	<@ww.select label="'部门'"
	                name="device"
	                headerKey="1" 
	                headerValue="Select Month"
	                list="{
	                    	'所有',		
	                    	'冲焊一厂', 
	                    	'总装一厂'
	                    }"
	                 value="selectedDevice"
	                    	
	      />  

    </tr>
    -->
    <#--
    <tr>
    	  <@ww.select label="'点检分类'"
	                name="device"
	                headerKey="1" 
	                headerValue="Select Month"
	                list="{
	                    	'所有',		
	                    	'日常点检', 
	                    	'定期点检',
	                    	'精密点检',
	                    	'技术诊断和倾向性诊断',
	                    	'精度测试检查'
	                    }"
	                 value="selectedDevice"
	                    	
	      /> 	    
    </tr>
    -->
	</@inputTable>