<#include "../../includes/hco2011.ftl" />

<@framePage title="">
	<@ww.form name="'listForm'" action="'saveElectricFloorFee'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchElectricFloorFeeToken"/>
		<@ww.hidden name="'saveAll'" value="'saveAll'"/>
		<@ww.hidden name="'building.id'" value="'${req.getParameter('building.id')?if_exists}'"/>
		<@ww.hidden name="'electricFee.id'" value="'${req.getParameter('electricFee.id')?if_exists}'"/>
		<@ww.hidden name="'pagingPage'" value="'${req.getParameter('pagingPage')?if_exists}'"/>
        <@list title="${action.getText('电费列表')}" 
            includeParameters="electricFee.id|building.name|readOnly|onlyInvalid|onlyValid|pagingPage|" 
        	fieldMap="like:" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="electricFloorFeeIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
            <input  type ="hidden" name ="ids" value="#{object.id}">
           <@vcolumn title="${action.getText('楼层编码')}" property="floor.code" sortable="desc">
     			<@alignLeft/>
     			<@vlh.attribute name="width" value="80" />
            </@vcolumn>
            
            <@vcolumn title="${action.getText('开始时间')}" property="starTime" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('结束时间')}" property="endTime" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('A表倍率')}" property="floor.aeMeter.rate">
				<input style="text-align:right; vertical-align:middle; border:none;width: 40; height: 99% ;color: grey"  type ="text" name ="aeMeter#{object.id}" value="<#if object.floor.aeMeter?exists>#{object.floor.aeMeter.rate}</#if>" readonly="readonly">
            	<@vlh.attribute name="width" value="52" />
            </@vcolumn>
            
            <@vcolumn title="${action.getText('A上次电示数')}" >
            	<input  style="text-align:right; vertical-align:middle; border:none;width: 80; height: 99%" type ="text" name ="alastElectric#{object.id}" value="#{object.lastElectricA?if_exists}" onblur ="getSum()">
            	<@vlh.attribute name="width" value="100" />
            </@vcolumn>
            
            <@vlh.column title="${action.getText('A本次电示数')}" >
            	<input style="text-align:right; vertical-align:middle; border:none;width: 80; height: 99%"  type ="text" name ="athisElectric#{object.id}" value="#{object.thisElectricA?if_exists}" onblur ="getSum()">
            	<@vlh.attribute name="width" value="100" />
            </@vlh.column>
            
             <@vcolumn title="${action.getText('A实用电数')}" >
            	<input style="text-align:right; vertical-align:middle; border:none;width: 80; height: 99%;color: green"  type ="text" name ="asumElectric#{object.id}" value="#{object.sumElectricA?if_exists}" readonly="readonly">
            	<@vlh.attribute name="width" value="100" />
            </@vcolumn>
                        
            <@vcolumn title="${action.getText('B表倍率')}" property="floor.beMeter.rate">
				<input style="text-align:right; vertical-align:middle; border:none;width: 40; height: 99% ;color: grey"  type ="text" name ="beMeter#{object.id}" value="<#if object.floor.beMeter?exists>#{object.floor.beMeter.rate}</#if>" readonly="readonly">
            	<@vlh.attribute name="width" value="52" />
            </@vcolumn>
            
            <@vcolumn title="${action.getText('B上次电示数')}" >
            	<input  style="text-align:right; vertical-align:middle; border:none;width: 80; height: 99%" type ="text" name ="blastElectric#{object.id}" value="#{object.lastElectricB?if_exists}" onblur ="getSum()">
            	<@vlh.attribute name="width" value="100" />
            </@vcolumn>
            
            <@vlh.column title="${action.getText('B本次电示数')}" >
            	<input style="text-align:right; vertical-align:middle; border:none;width: 80; height: 99%"  type ="text" name ="bthisElectric#{object.id}" value="#{object.thisElectricB?if_exists}" onblur ="getSum()">
            	<@vlh.attribute name="width" value="100" />
            </@vlh.column>
            
             <@vcolumn title="${action.getText('B实用电数')}" >
            	<input style="text-align:right; vertical-align:middle; border:none;width: 80; height: 99%;color: green"  type ="text" name ="bsumElectric#{object.id}" value="#{object.sumElectricB?if_exists}" readonly="readonly">
            	<@vlh.attribute name="width" value="100" />
            </@vcolumn>

			<@vcolumn title="${action.getText('总电数')}" >
				<input style="text-align:right; vertical-align:middle; border:none;width: 80; height: 99% ;color: green"  type ="text" name ="sumElectric#{object.id}" value="#{object.sumElectric?if_exists}" readonly="readonly">
				<@vlh.attribute name="width" value="100" />
			</@vcolumn>
			 <@vcolumn title="${action.getText('备注')}" >
            	<input style="text-align:left; vertical-align:middle; border:none;width: 188; height: 99%"  type ="text" name ="outLine#{object.id}" value="${object.outLine?if_exists}"
            	<@vlh.attribute name="width" value="200" />
            </@vcolumn>
        </@list>
        
        <@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return true;'"/>
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
</script>
</@framePage>
