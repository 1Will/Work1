<@inputTable>
	<tr>
		<@ww.textfield label="'工号'" name="'code'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'姓名'" name="'name'" value="''" cssClass="'underline'"/>
		<@ww.select 
    		label="'性别'"
			required="false"
			name="'sex'" 
			value="selectedSex" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
		    	'所有',
				'男',
				'女'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
	</tr>
	<tr>
		<@ww.select 
    		label="'公司'"
			required="false"
			name="'inst'" 
			value="selectedInst" 
			headerKey="1"
			headerValue="selectedInst"
		    list="{
		    	'所有',
				'永君(中国)有限公司合肥分公司',
				'永君(中国)有限公司南京分公司',
				'永君(中国)有限公司上海分公司',
				'永君(中国)有限公司大连分公司'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
	    	
	    	<@ww.select 
    		label="'部门'"
			required="false"
			name="'dept'" 
			value="selectedDept" 
			headerKey="1"
			headerValue="selectedDept"
		    list="{
		    	'所有',
				'软件研发部',
				'网络事业部',
				'系统集成部',
				'电子安防部'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
	    	<@ww.select 
    		label="'职务'"
			required="false"
			name="'duty'" 
			value="selectedDuty" 
			headerKey="1"
			headerValue="selectedDuty"
		    list="{
		    	'所有',
				'部门经理',
				'项目组组长',
				'配置组组长',
				'ORACLE DBA',
				'程序员'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
	</tr>
</@inputTable>