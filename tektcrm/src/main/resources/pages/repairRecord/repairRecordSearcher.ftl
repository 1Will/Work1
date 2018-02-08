<@inputTable>
	<@ww.hidden name="'cbv'" value=""/>
	
	<!--重新排版wclin 11.7.6____________________________________________________________________________________-->
	<tr>
	<!--售后管理报告人-->	
	<@ww.textfield label="'${action.getText('报告人')}'" name="'personnelFiles.name'" value="'${req.getParameter('personnelFiles.name')?if_exists}'" cssClass="'underline'"/>
		<!--工作单号-->	
	<@ww.textfield label="'${action.getText('工作单号')}'" name="'workNum'" value="'${req.getParameter('workNum')?if_exists}'" cssClass="'underline'"/>
		<!--空调型号-->	
	<@ww.textfield label="'${action.getText('空调型号')}'" name="'airtypeNum'" value="'${req.getParameter('airtypeNum')?if_exists}'" cssClass="'underline'"/>
	</tr>
	<tr>
		<!--兵种-->
	<@ww.select 
	    		label="'${action.getText('兵种')}'"
				required="false"
				name="'branch.id'" 
				value="${req.getParameter('branch.id')?if_exists}" 
				listKey="id"
				listValue="name"
			    list="allBranch"
			    emptyOption="false" 
		    	disabled="false"/>
		    	<script language="javascript">
			<#if req.getParameter('branch.id')?exists>
				getObjByName('branch.id').value = '${req.getParameter('branch.id')?if_exists}';
			</#if>
		</script>
		<!--维修类型-->
	<@ww.select 
	    		label="'${action.getText('维修类型')}'"
				required="false"
				name="'repirType.id'" 
				value="${req.getParameter('repirType.id')?if_exists}" 
				listKey="id"
				listValue="name"
			    list="allRepirType"
			    emptyOption="false" 
		    	disabled="false"/>
		    	<script language="javascript">
			<#if req.getParameter('repirType.id')?exists>
				getObjByName('repirType.id').value = '${req.getParameter('repirType.id')?if_exists}';
			</#if>
		</script>
			<!--故障分类-->
	<@ww.select 
	    		label="'${action.getText('故障分类')}'"
				required="false"
				name="'faultType.id'" 
				value="${req.getParameter('faultType.id')?if_exists}" 
				listKey="id"
				listValue="name"
			    list="allFaultType"
			    emptyOption="false" 
		    	disabled="false"/>
		    	<script language="javascript">
			<#if req.getParameter('faultType.id')?exists>
				getObjByName('faultType.id').value = '${req.getParameter('faultType.id')?if_exists}';
			</#if>
		</script>
		
	
	</tr>
	<tr>
	<!--接收日期-->
	<@pp.dateRanger label="'${action.getText('接收日期')}'" name="'repairRecord.receiveDate'" 
            value="'${req.getParameter('repairRecord.receiveDate_start')?if_exists}|${req.getParameter('repairRecord.receiveDate_end')?if_exists}'"
             cssClass="'underline'" maxlength="10" />
        <script language="javascript">
        	<#if first>
	        	if(getObjByName("repairRecord.receiveDate_start").value==""){
		        	var date = new Date();
					getObjByName("repairRecord.receiveDate_end").value = date.format("yyyy-MM-dd");
				}
			</#if>
		</script>
		<!--完结日期-->
	<@pp.dateRanger label="'${action.getText('完结日期')}'" name="'repairRecord.finishDate'" 
            value="'${req.getParameter('repairRecord.finishDate_start')?if_exists}|${req.getParameter('repairRecord.finishDate_end')?if_exists}'"
             cssClass="'underline'" maxlength="10" />
        <script language="javascript">
        	<#if first>
	        	if(getObjByName("repairRecord.finishDate_start").value==""){
		        	var date = new Date();
					getObjByName("repairRecord.finishDate_end").value = date.format("yyyy-MM-dd");
				}
			</#if>
		</script>
	
	<!--出发时间-->
	<@pp.dateRanger label="'${action.getText('出发时间')}'" name="'repairRecord.leaveDate'" 
            value="'${req.getParameter('repairRecord.leaveDate_start')?if_exists}|${req.getParameter('repairRecord.leaveDate_end')?if_exists}'"
             cssClass="'underline'" maxlength="10" />
        <script language="javascript">
        	<#if first>
	        	if(getObjByName("repairRecord.leaveDate_start").value==""){
		        	var date = new Date();
					getObjByName("repairRecord.leaveDate_end").value = date.format("yyyy-MM-dd");
				}
			</#if>
		</script>
		</tr>
	<tr>
		<!--到达时间-->
	<@pp.dateRanger label="'${action.getText('到达时间')}'" name="'repairRecord.arriveDate'" 
            value="'${req.getParameter('repairRecord.arriveDate_start')?if_exists}|${req.getParameter('repairRecord.arriveDate_end')?if_exists}'"
             cssClass="'underline'" maxlength="10" />
        <script language="javascript">
        	<#if first>
	        	if(getObjByName("repairRecord.arriveDate_start").value==""){
		        	var date = new Date();
					getObjByName("repairRecord.arriveDate_end").value = date.format("yyyy-MM-dd");
				}
			</#if>
		</script>
		
		<!--开工时间-->
	<@pp.dateRanger label="'${action.getText('开工时间')}'" name="'repairRecord.startDate'" 
            value="'${req.getParameter('repairRecord.startDate_start')?if_exists}|${req.getParameter('repairRecord.startDate_end')?if_exists}'"
             cssClass="'underline'" maxlength="10" />
        <script language="javascript">
        	<#if first>
	        	if(getObjByName("repairRecord.startDate_start").value==""){
		        	var date = new Date();
					getObjByName("repairRecord.startDate_end").value = date.format("yyyy-MM-dd");
				}
			</#if>
		</script>
		<!--完成时间-->
	<@pp.dateRanger label="'${action.getText('完成时间')}'" name="'repairRecord.endDate'" 
            value="'${req.getParameter('repairRecord.endDate_start')?if_exists}|${req.getParameter('repairRecord.endDate_end')?if_exists}'"
             cssClass="'underline'" maxlength="10" />
        <script language="javascript">
        	<#if first>
	        	if(getObjByName("repairRecord.endDate_start").value==""){
		        	var date = new Date();
					getObjByName("repairRecord.endDate_end").value = date.format("yyyy-MM-dd");
				}
			</#if>
		</script>
	</tr>
	<tr>
	</tr>
</@inputTable>
