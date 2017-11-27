<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('水电收费')}">
	<@ww.form name="'listForm'" action="'searchExpense'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchExpenseToken"/>
		<#include "./expenseSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms()'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/fee/editExpense.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
       		    <@vbutton value="${action.getText('计算水电费')}" class="button" onclick ="createWaterAndEleFee()"/>
       		    
       		</#if>
        </@buttonBar>
        <@list title="${action.getText('水电费列表')}" 
            includeParameters="expense.code|expense.starTime_start|expense.starTime_end|expense.endTime_start|expense.endTime_end|readOnly|onlyInvalid|onlyValid|" 
        	fieldMap="like:expense.code|" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="expenseIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
           </#if>
           <@vcolumn title="${action.getText('水电费编码')}" property="code" sortable="desc">
				<a href="editExpense.html?expense.id=#{object.id?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">
    				${object.code?if_exists}
                </a>
     			<@alignLeft/>
            </@vcolumn>
		
			<@vcolumn title="${action.getText('开始时间')}" property="startTime" format="yyyy-MM-dd" sortable="desc">
				<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('结束时间')}" property="endTime" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>

			<@vcolumn title="${action.getText('房间数')}" property="sumHouse" sortable="desc">
				<@alignRight/>
			</@vcolumn>

			<@vcolumn title="${action.getText('总面积')}" property="sumSquare" sortable="desc">
				<@alignRight/>
			</@vcolumn>

			<@vcolumn title="${action.getText('企业数')}" property="sumCustomer" sortable="desc">
				<@alignRight/>
			</@vcolumn>

			<@vcolumn title="${action.getText('水费')}" property="waterMoney" sortable="desc">
				<@alignRight/>
			</@vcolumn>

			<@vcolumn title="${action.getText('电费')}" property="electricMoney" sortable="desc">
				<@alignRight/>
			</@vcolumn>

			<@vcolumn title="${action.getText('空调费')}" property="airMoney" sortable="desc">
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

			<@vcolumn title="${action.getText('状态')}" property="state.name" sortable="desc">
				<@alignRight/>
			</@vcolumn>
        </@list>
         <@buttonBar>
            <#assign confirmMessage = "${action.getText('确认删除水电费信息？')}" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"expenseIds\", \"${confirmMessage}\");'"/>
            </@vsubmit>
        </@buttonBar>
    </@ww.form>
</@htmlPage>
<script type='text/javascript' src='${req.contextPath}/dwr/interface/AutoWaterAndEleFee.js'></script>
<script language="JavaScript" type="text/JavaScript"> 
	function createWaterAndEleFee(){
	var flag = confirm("请确定水费、电费是否已创建，如创建将开始计算公摊");
		if(flag){
		DWREngine.setAsync(false); 
		AutoWaterAndEleFee.autoWaterAndEleFee(refreshPage);
		DWREngine.setAsync(true); 
		}
	}
	function refreshPage(result){
	    if(result*1==0){
	    alert("水电费生成成功！")
	    }else if(result*1==2){
	    alert("水费和电费结束时间不一致")
	    }else if(result*1==3){
	    alert("不存在客户")
	    }else{
	    alert("水费或者电费不存在");
	    }
		location.reload();
	}
</script>