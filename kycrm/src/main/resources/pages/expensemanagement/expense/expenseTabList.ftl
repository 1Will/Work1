<#include "../../includes/hco2011.ftl" />

<@framePage title="">
	<@ww.form name="'listForm'" action="'searchExpense'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'parentEP.id'" value="'${req.getParameter('parentEP.id')?if_exists}'"/>
        <@list title="" 
            includeParameters="expense.starTime_start|expense.starTime_end|expense.endTime_start|expense.endTime_end|readOnly|onlyInvalid|onlyValid|parentEP.id|" 
        	fieldMap="like:expense.code|" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="expenseIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
           </#if>
           <@vcolumn title="${action.getText('水电费编码')}" property="code" sortable="desc">
    				${object.code?if_exists}
                </a>
     			<@alignLeft/>
            </@vcolumn>
		
			<@vcolumn title="${action.getText('客户名称')}" property="customerInfo.name" sortable="desc">
				<a href="javascript:customer_OpenDialog(#{object.customerInfo.id?if_exists},${req.getParameter('readOnly')?if_exists})" >${object.customerInfo.name?if_exists}</a>
				<@alignLeft/>
			</@vcolumn>
			
			<@vcolumn title="${action.getText('开始时间')}" property="startTime" format="yyyy-MM-dd" sortable="desc">
				<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('结束时间')}" property="endTime" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>

			<@vcolumn title="${action.getText('房间数')}" property="sumHouse" sortable="desc">
				<a href="javascript:houses_OpenDialog(#{object.customerInfo.id?if_exists})" >${object.sumHouse?if_exists}</a>
				<@alignRight/>
			</@vcolumn>

			<@vcolumn title="${action.getText('总面积')}" property="sumSquare" sortable="desc">
				<@alignRight/>
			</@vcolumn>

			<@vcolumn title="${action.getText('水费')}" property="waterMoney" sortable="desc">
				<a href="javascript:water_OpenDialog(#{object.customerInfo.id?if_exists},${(object.startTime?string('yyyy-MM-dd'))?if_exists},${(object.endTime?string('yyyy-MM-dd'))?if_exists})" >#{object.waterMoney?if_exists}</a>
				<@alignRight/>
			</@vcolumn>

			<@vcolumn title="${action.getText('电费')}" property="electricMoney" sortable="desc">
				<a href="javascript:electric_OpenDialog(#{object.customerInfo.id?if_exists},${(object.startTime?string('yyyy-MM-dd'))?if_exists},${(object.endTime?string('yyyy-MM-dd'))?if_exists})" >#{object.electricMoney?if_exists}</a>
				<@alignRight/>
			</@vcolumn>

			<@vcolumn title="${action.getText('空调费')}" property="airMoney" sortable="desc">
				<a href="javascript:air_OpenDialog(#{object.customerInfo.id?if_exists},${(object.startTime?string('yyyy-MM-dd'))?if_exists},${(object.endTime?string('yyyy-MM-dd'))?if_exists})" >#{object.airMoney?if_exists}</a>
				<@alignRight/>
			</@vcolumn>

			<@vcolumn title="${action.getText('网络费')}" property="netMoney" sortable="desc">
				<@alignRight/>
			</@vcolumn>

			<@vcolumn title="${action.getText('物业费')}" property="propertyMoney" sortable="desc">
				<@alignRight/>
			</@vcolumn>

			<@vcolumn title="${action.getText('总金额')}" property="sum" sortable="desc">
				<@alignRight/>
			</@vcolumn>
<#--
			<@vcolumn title="${action.getText('状态')}" property="state.name" sortable="desc">
				<@alignRight/>
			</@vcolumn>
-->
        </@list>
    </@ww.form>
</@framePage>
<script>
     //弹出客户档案查询模态窗体
	function customer_OpenDialog(id){
	   var url = "${req.contextPath}/customerRelationship/editCustomerInfo.html?readOnly=${req.getParameter('readOnly')?if_exists}&customerInfo.id="+id+"&popWindowFlag=popWindowFlag&notNewFlag=notNewFlag";
	   openNewWindow(url);
	 }
	 
	//弹出房间查询模态窗体
	function houses_OpenDialog(id){
	   var url = "${req.contextPath}/house/listHouseWindow.html?customerInfo.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}";
		openNewWindow(url);
	 }
	 //水费
	 
	 function water_OpenDialog(id,startTime,endTime){
	   var url = "${req.contextPath}/fee/listWaterHouseFee.html?customerInfo.id="+id+"&startTime="+startTime+"&endTime="+endTime+"&readOnly=${req.getParameter('readOnly')?if_exists}";
		openNewWindow(url);
	 }
	 
	 function electric_OpenDialog(id,startTime,endTime){
	   var url = "${req.contextPath}/fee/listElectricHouseFee.html?customerInfo.id="+id+"&startTime="+startTime+"&endTime="+endTime+"&readOnly=${req.getParameter('readOnly')?if_exists}";
		openNewWindow(url);
	 }
	 
	 function air_OpenDialog(id,startTime,endTime){
		var url = "${req.contextPath}/fee/listAirHouseFee.html?customerInfo.id="+id+"&startTime="+startTime+"&endTime="+endTime+"&readOnly=${req.getParameter('readOnly')?if_exists}";
		openNewWindow(url);
	 }
	 
</script>