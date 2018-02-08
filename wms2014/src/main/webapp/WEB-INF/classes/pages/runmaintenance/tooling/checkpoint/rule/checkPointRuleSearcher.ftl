<@inputTable>
	<@ww.hidden name="'invalid'" value="true"/>
	<@ww.hidden name="'unSubmit'" value=""/>
    <tr>
        <@ww.textfield label="'点检标准编号'" name="'ruleNo'" value="'${req.getParameter('ruleNo')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'点检标准名称'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
    </tr>
    <tr>
    	<@ww.select label="'分类'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'所有',
	                    			'工装', 
	                    			'设备',
	                    			'其它'
	                    	  	  }"
	                    	value="selectedDevice"
	    />
	    <@ww.select label="'部门'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'所有',
	                    			'机动部', 
	                    			'生产部'
	                    	  	  }"
	                    	value="selectedDevice"
	    />
    </tr> 
</@inputTable>