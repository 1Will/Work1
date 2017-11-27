<#include "../../includes/hco2011.ftl" />

<@framePage title="">
	<@ww.form name="'listForm'" action="'saveElectricHouseFee'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchElectricHouseFeeToken"/>
		<@ww.hidden name="'saveAll'" value="'saveAll'"/>
		<@ww.hidden name="'building.id'" value="'${req.getParameter('building.id')?if_exists}'"/>
		<@ww.hidden name="'electricFee.id'" value="'${req.getParameter('electricFee.id')?if_exists}'"/>
		<@ww.hidden name="'pagingPage'" value="'${req.getParameter('pagingPage')?if_exists}'"/>
        <@list title="${action.getText('电费列表')}" 
            includeParameters="electricFee.id|building.name|readOnly|onlyInvalid|onlyValid|pagingPage|" 
        	fieldMap="like:" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="electricHouseFeeIds">
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
            
            <@vcolumn title="${action.getText('天数')}" property="days" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('A表倍率')}" property="house.aeMeter.rate"><input style="text-align:right; vertical-align:middle; border:none;width: 80%; height: 99%;color: grey"  type ="text" name ="aeMeter#{object.id}" value="<#if object.house.aeMeter?exists>#{object.house.aeMeter.rate}</#if>" readonly="readonly"><@vlh.attribute name="width" value="30" /></@vcolumn>
            
            <@vcolumn title="${action.getText('A上次电示数')}" >
            	<input  style="text-align:right; vertical-align:middle; border:none;width: 85%; height: 99%" type ="text" name ="alastElectric#{object.id}" value="#{object.lastElectricA?if_exists}" onblur ="getSum()">
            	<@vlh.attribute name="width" value="72" />
            </@vcolumn>
            
            <@vcolumn title="${action.getText('A本次电示数')}" >
            	<input style="text-align:right; vertical-align:middle; border:none;width: 85%; height: 99%"  type ="text" name ="athisElectric#{object.id}" value="#{object.thisElectricA?if_exists}" onblur ="getSum()">
            	<@vlh.attribute name="width" value="72" />
            </@vcolumn>
            
             <@vcolumn title="${action.getText('A实用电数')}" >
            	<input style="text-align:right; vertical-align:middle; border:none;width: 85%; height: 99% ;color: grey"  type ="text" name ="asumElectric#{object.id}" value="#{object.sumElectricA?if_exists}" readonly="readonly">
            	<@vlh.attribute name="width" value="72" />
            </@vcolumn>
            
            <@vcolumn title="${action.getText('B表倍率')}" >
            	<input style="text-align:right; vertical-align:middle; border:none;width: 80%; height: 99% ;color: grey"  type ="text" name ="beMeter#{object.id}" value="<#if object.house.beMeter?exists>#{object.house.beMeter.rate}</#if>" readonly="readonly">
                 <@vlh.attribute name="width" value="30" />
            </@vcolumn>
            
            <@vcolumn title="${action.getText('B上次电示数')}" >
            	<input  style="text-align:right; vertical-align:middle; border:none;width: 85%; height: 99%" type ="text" name ="blastElectric#{object.id}" value="#{object.lastElectricB?if_exists}" onblur ="getSum()">
            	<@vlh.attribute name="width" value="72" />
            </@vcolumn>
            
            <@vlh.column title="${action.getText('B本次电示数')}" >
            	<input style="text-align:right; vertical-align:middle; border:none;width: 85%; height: 99%"  type ="text" name ="bthisElectric#{object.id}" value="#{object.thisElectricB?if_exists}" onblur ="getSum()">
            	<@vlh.attribute name="width" value="72" />
            </@vlh.column>
            
             <@vcolumn title="${action.getText('B实用电数')}" >
            	<input style="text-align:right; vertical-align:middle; border:none;width: 85%; height: 99% ;color: grey"  type ="text" name ="bsumElectric#{object.id}" value="#{object.sumElectricB?if_exists}" readonly="readonly">
            	<@vlh.attribute name="width" value="72" />
            </@vcolumn>
            
             <@vcolumn title="${action.getText('总电数')}" >
            	<input style="text-align:right; vertical-align:middle; border:none;width: 85%; height: 99% ;color: grey"  type ="text" name ="sumElectric#{object.id}" value="#{object.sumElectric?if_exists}" readonly="readonly">
            	<@vlh.attribute name="width" value="72" />
            </@vcolumn>
            
             <@vcolumn title="${action.getText('公摊电费')}" >
            	<input style="text-align:right; vertical-align:middle; border:none;width: 85%; height: 99% ;color: grey"  type ="text" name ="shareFee#{object.id}" value="#{object.shareFee?if_exists}" readonly="readonly">
            	<@vlh.attribute name="width" value="72" />
            </@vcolumn>
            
             <@vcolumn title="${action.getText('总电费')}" >
            	<input style="text-align:right; vertical-align:middle; border:none;width: 85%; height: 99% ;color: grey"  type ="text" name ="sumFee#{object.id}" value="#{object.sumFee?if_exists}" readonly="readonly">
            	<@vlh.attribute name="width" value="72" />
            </@vcolumn>
            <@vcolumn title="${action.getText('备注')}" >
            	<input style="text-align:left; vertical-align:middle; border:none;width:90%; height: 99%"  type ="text" name ="outLine#{object.id}" value="${object.outLine?if_exists}"
            	<@vlh.attribute name="width" value="162" />
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
        		//A
        		var lastNameA = 'alastElectric'+ids[i].value;
        		var thisNameA = 'athisElectric'+ids[i].value;
        		var sumNameA = 'asumElectric'+ids[i].value;
            	var lastElectricA = getObjByName(lastNameA).value;
            	var thisElectricA = getObjByName(thisNameA).value;
            	getObjByName(sumNameA).value =toDecimal(thisElectricA - lastElectricA);
            	//B
        		var lastNameB = 'blastElectric'+ids[i].value;
        		var thisNameB = 'bthisElectric'+ids[i].value;
        		var sumNameB = 'bsumElectric'+ids[i].value;
            	var lastElectricB = getObjByName(lastNameB).value;
            	var thisElectricB = getObjByName(thisNameB).value;
            	getObjByName(sumNameB).value =toDecimal(thisElectricB - lastElectricB);
            	//总电
        		var sumName = 'sumElectric'+ids[i].value;
        		var aeMeter = getObjByName('aeMeter'+ids[i].value).value;
        		var beMeter = getObjByName('beMeter'+ids[i].value).value;
        		if(!isNaN(aeMeter) && !isNaN(beMeter)){
	            	getObjByName(sumName).value =toDecimal(beMeter*(thisElectricB - lastElectricB))+toDecimal(aeMeter*(thisElectricA - lastElectricA));
	            	continue;
        		}
        		if(!isNaN(aeMeter)){
	            	getObjByName(sumName).value =toDecimal(thisElectricB - lastElectricB)+toDecimal(aeMeter*(thisElectricA - lastElectricA));
	            	continue;
        		}
        		if(!isNaN(beMeter)){
	            	getObjByName(sumName).value =toDecimal(beMeter*(thisElectricB - lastElectricB))+toDecimal(thisElectricA - lastElectricA);
	            	continue;
        		}
            	getObjByName(sumName).value =toDecimal(thisElectricB - lastElectricB)+toDecimal(thisElectricA - lastElectricA);
        	}
        }
        
		function storeValidation(){
			getSum();
			return true;
		}
		
		function openHouse(id){
			var url ="${req.contextPath}/house/editHouse.html?house.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}"
			openNewWindow(url);
		}
</script>
</@framePage>
