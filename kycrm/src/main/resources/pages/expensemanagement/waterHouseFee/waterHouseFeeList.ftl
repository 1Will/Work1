<#include "../../includes/hco2011.ftl" />

<@framePage title="">
	<@ww.form name="'listForm'" action="'saveWaterHouseFee'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchWaterHouseFeeToken"/>
		<@ww.hidden name="'saveAll'" value="'saveAll'"/>
		<@ww.hidden name="'building.id'" value="'${req.getParameter('building.id')?if_exists}'"/>
		<@ww.hidden name="'waterFee.id'" value="'${req.getParameter('waterFee.id')?if_exists}'"/>
		<@ww.hidden name="'pagingPage'" value="'${req.getParameter('pagingPage')?if_exists}'"/>
        <@list title="${action.getText('水费列表')}" 
            includeParameters="waterFee.id|building.name|readOnly|onlyInvalid|onlyValid|pagingPage|" 
        	fieldMap="like:" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="waterHouseFeeIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
            <input  type ="hidden" name ="ids" value="#{object.id}">
            
           <@vcolumn title="${action.getText('房源编码')}" property="house.code" sortable="desc">
				<a href="javascript:openHouse('#{object.house.id}')" title ="查看房间信息">${object.house.code}</a>
     			<@alignLeft/>
            </@vcolumn>
                        
            <@vcolumn title="${action.getText('客户名称')}" property="customerInfo.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             
            <@vcolumn title="${action.getText('房间面积')}" property="house.square" >
     			<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('开始时间')}" property="starTime" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('结束时间')}" property="endTime" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('天数')}" property="days" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
            
            <@vcolumn title="${action.getText('上次水示数')}" >
            	<input  style="text-align:right; vertical-align:middle; border:none;width: 100; height: 99%" type ="text" name ="lastWater#{object.id}" value="#{object.lastWater?if_exists}" onblur ="getSum(#{object.house.building.waterPrice})">
            	<@vlh.attribute name="width" value="110" />
            </@vcolumn>
            
            <@vlh.column title="${action.getText('本次水示数')}" >
            	<input style="text-align:right; vertical-align:middle; border:none;width: 100; height: 99%"  type ="text" name ="thisWater#{object.id}" value="#{object.thisWater?if_exists}" onblur ="getSum(#{object.house.building.waterPrice})">
            	<@vlh.attribute name="width" value="110" />
            </@vlh.column>
                        
            <@vlh.column title="${action.getText('公摊水数')}" >
            	<input style="text-align:right; vertical-align:middle; border:none;width: 100; height: 99% ;color: grey"  type ="text" name ="shareWater#{object.id}" value="#{object.shareWater?if_exists}" readonly="readonly" onblur ="getSum(#{object.house.building.waterPrice})">
            	<@vlh.attribute name="width" value="110" />
            </@vlh.column>
            
             <@vcolumn title="${action.getText('实用水数')}" >
            	<input style="text-align:right; vertical-align:middle; border:none;width: 100; height: 99% ;color: grey"  type ="text" name ="sumWater#{object.id}" value="#{object.sumWater?if_exists}" readonly="readonly">
            	<@vlh.attribute name="width" value="110" />
            </@vcolumn>
             <@vcolumn title="${action.getText('水价(元/吨)')}" property="house.building.waterPrice" >
     			<@alignRight/>
            </@vcolumn>
             <@vcolumn title="${action.getText('水费')}" >
            	<input style="text-align:right; vertical-align:middle; border:none;width: 100; height: 99% ;color: grey"  type ="text" name ="sumFee#{object.id}" value="#{object.sumFee?if_exists}" readonly="readonly">
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
				<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return true;'"/>
			</#if>
		</#if>
    </@buttonBar>
    </@ww.form>
<script > 
        function getSum(price){
        	var ids = document.getElementsByName("ids");
        	for(var i =0;i<ids.length;i++){
        		var lastName = 'lastWater'+ids[i].value;
        		var thisName = 'thisWater'+ids[i].value;
        		var shareName = 'shareWater'+ids[i].value;
        		var sumName = 'sumWater'+ids[i].value;
        		var fee = 'sumFee'+ids[i].value;
            	var lastWater = parseFloat(getObjByName(lastName).value);
            	var thisWater = parseFloat(getObjByName(thisName).value);
            	var shareWater = parseFloat(getObjByName(shareName).value);
            	getObjByName(sumName).value = toDecimal((thisWater - lastWater+shareWater));
            	getObjByName(fee).value =toDecimal((price*(thisWater - lastWater+shareWater)));
			}
        }
        
		function openHouse(id){
			var url ="${req.contextPath}/house/editHouse.html?house.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}"
			openNewWindow(url);
		}
</script>
</@framePage>
