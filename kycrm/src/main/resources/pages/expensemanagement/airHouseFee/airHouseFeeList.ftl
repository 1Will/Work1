<#include "../../includes/hco2011.ftl" />

<@framePage title="${action.getText('空调费')}">
	<@ww.form name="'listForm'" action="'saveAirHouseFee'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#if airFee?exists >
			<@ww.hidden name="'airFee.id'" value="'${airFee.id?if_exists}'"/>
		</#if>
		<@ww.token name="searchAirFeeToken"/>
        <@list title="${action.getText('空调费列表')}" 
            includeParameters="house.code|house.name|airFee.id|airFee.starTime_start|airFee.starTime_end|airFee.endTime_start|airFee.endTime_end|readOnly|onlyInvalid|onlyValid|" 
        	fieldMap="like:house.code|house.name|" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="airHouseFeeIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
           </#if>
           <input  type ="hidden" name ="ids" value="#{object.id}">
           <@vcolumn title="${action.getText('房间编码')}" property="house.code" sortable="desc">
				<a href="javascript:openHouse('#{object.house.id}')" title ="查看房间信息">${object.house.code}</a>
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('客户名称')}" property="customerInfo.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('开始时间')}" property="starTime" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('结束时间')}" property="endTime" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('计算天数')}" property="factDay"  sortable="desc">
     			<@alignRight/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('使用天数')}" property="useDay"  sortable="desc">
     			<@alignRight/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('开放天数')}" property="openDays"  sortable="desc">
     			<@alignRight/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('上次示数')}" property="lastAir" >
            	<input  style="text-align:right; vertical-align:middle; border:none;width: 100; height: 99%" type ="text" name ="lastAir#{object.id}" value="#{object.lastAir?if_exists}" onblur ="getSum()">
            	<@vlh.attribute name="width" value="110" />
            </@vcolumn>
            
            <@vcolumn title="${action.getText('本次示数')}" property="thisAir" >
            	<input  style="text-align:right; vertical-align:middle; border:none;width: 100; height: 99%" type ="text" name ="thisAir#{object.id}" value="#{object.thisAir?if_exists}" onblur ="getSum()">
            	<@vlh.attribute name="width" value="110" />
            </@vcolumn>
            
             <@vcolumn title="${action.getText('实用数')}" property="sumAir" >
            	<input  style="text-align:right; vertical-align:middle; border:none;width: 100; height: 99% ;color: grey" type ="text" name ="sumAir#{object.id}" value="#{object.sumAir?if_exists}" readonly="readonly" onblur ="getSum()">
            	<@vlh.attribute name="width" value="110" />
            </@vcolumn>
            
             <@vcolumn title="${action.getText('空调费')}" >
            	<input  style="text-align:right; vertical-align:middle; border:none;width: 100; height: 99% ;color: grey" type ="text" name ="sumFee#{object.id}" value="#{object.sumFee?if_exists}" readonly="readonly" onblur ="getSum()">
            	<@vlh.attribute name="width" value="110" />
            </@vcolumn>
            <@vcolumn title="${action.getText('备注')}" >
            	<input style="text-align:left; vertical-align:middle; border:none;width: 188; height: 99%"  type ="text" name ="outLine#{object.id}" value="${object.outLine?if_exists}"
            	<@vlh.attribute name="width" value="200" />
            </@vcolumn>
        </@list>
        <@buttonBar>
		<#if !(action.isReadOnly())>
			<#if !req.getParameter('customerInfo.id')?exists>
				<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
			</#if>
		</#if>
    </@buttonBar>
    </@ww.form>
    <script > 
		function getSum(){
			var ids = document.getElementsByName("ids");
			for(var i =0;i<ids.length;i++){
				var lastName = 'lastAir'+ids[i].value;
				var thisName = 'thisAir'+ids[i].value;
				var sumName = 'sumAir'+ids[i].value;
				var lastAir = getObjByName(lastName).value;
				var thisAir = getObjByName(thisName).value;
				getObjByName(sumName).value =thisAir - lastAir;
			}
		}
		
		function openHouse(id){
			var url ="${req.contextPath}/house/editHouse.html?house.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}"
			openNewWindow(url);
		}
</script>
</@framePage>
