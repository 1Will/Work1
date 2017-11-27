<#include "../../includes/hco2011.ftl" />

<@framePage title="">
	<@ww.form name="'listForm'" action="'searchRent'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'parentRent.id'" value="'${req.getParameter('parentRent.id')?if_exists}'"/>
		<@ww.token name="searchRentToken"/>
        <@list title="" 
            includeParameters="rent.code|rent.starTime_start|rent.starTime_end|rent.endTime_start|rent.endTime_end|parentRent.id|readOnly|onlyInvalid|onlyValid|" 
        	fieldMap="like:rent.code|" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="rentIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
           </#if>
           <@vcolumn title="${action.getText('房租编码')}" property="code" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
			<@vcolumn title="${action.getText('企业名称')}" property="customerInfo.name" sortable="desc">
				<a href="javascript:customer_OpenDialog(#{object.customerInfo.id?if_exists},${req.getParameter('readOnly')?if_exists})" >${object.customerInfo.name?if_exists}</a>
				<@alignRight/>
			</@vcolumn>
		
			<@vcolumn title="${action.getText('开始时间')}" property="startTime" format="yyyy-MM-dd" sortable="desc">
				<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('结束时间')}" property="endTime" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>

			<@vcolumn title="${action.getText('房间数')}" property="sumHouse" sortable="desc">
				<a href="javascript:houses_OpenDialog(#{object.customerInfo.id?if_exists},${req.getParameter('readOnly')?if_exists})" >${object.sumHouse?if_exists}</a>
				<@alignRight/>
			</@vcolumn>

			<@vcolumn title="${action.getText('总面积')}" property="sumSquare" sortable="desc">
				<@alignRight/>
			</@vcolumn>
			
			<@vcolumn title="${action.getText('已交金额')}" property="hasSum" sortable="desc">
				<@alignRight/>
			</@vcolumn>

			<@vcolumn title="${action.getText('总金额')}" property="sum" sortable="desc">
				<@alignRight/>
			</@vcolumn>

			<@vcolumn title="${action.getText('状态')}" property="state.name" sortable="desc">
				<@alignRight/>
			</@vcolumn>
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
</script>
