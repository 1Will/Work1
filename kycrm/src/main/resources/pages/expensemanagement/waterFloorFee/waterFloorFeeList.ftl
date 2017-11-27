<#include "../../includes/hco2011.ftl" />

<@framePage title="">
	<link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
	<script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
	<script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
	<script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script>
	<@ww.form name="'listForm'" action="'saveWaterFloorFee'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchWaterFloorFeeToken"/>
		<@ww.hidden name="'saveAll'" value="'saveAll'"/>
		<@ww.hidden name="'building.id'" value="'${req.getParameter('building.id')?if_exists}'"/>
		<@ww.hidden name="'waterFee.id'" value="'${req.getParameter('waterFee.id')?if_exists}'"/>
		<@ww.hidden name="'pagingPage'" value="'${req.getParameter('pagingPage')?if_exists}'"/>
		<@list title="${action.getText('水费列表')}" includeParameters="waterFee.id|building.name|readOnly|onlyInvalid|onlyValid|pagingPage|"  fieldMap="like:" >
			<#if !(action.isReadOnly())>
				<@vlh.checkbox property="id" name="waterFloorFeeIds">
				<@vlh.attribute name="width" value="30" />
			</@vlh.checkbox>
			</#if>
			<input  type ="hidden" name ="ids" value="#{object.id}">
			<@vcolumn title="${action.getText('楼层编码')}" property="floor.code" sortable="desc">
				<@alignLeft/>
				<@vlh.attribute name="width" value="100" />
			</@vcolumn>

			<@vcolumn title="${action.getText('楼层名称')}" property="floor.name" sortable="desc">
				<@alignLeft/>
				<@vlh.attribute name="width" value="100" />
			</@vcolumn>
			<@vcolumn title="${action.getText('开始时间')}" property="starTime" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft />
     			<@vlh.attribute name="width" value="180" />
            </@vcolumn>
            
            <@vcolumn title="${action.getText('结束时间')}" property="endTime" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft/>
     			<@vlh.attribute name="width" value="180" />
            </@vcolumn>
            <#--
			<@vcolumn title="${action.getText('开始时间')}" property="starTime" format="yyyy-MM-dd" >
			<input style="text-align:left; vertical-align:middle; border:none;width: 150; height: 99%" type="text" name="starTime#{object.id}" value="${object.starTime?string('yyyy-MM-dd')?if_exists}" readonly="readonly" id="starTime#{object.id}" />
			<script language="javascript">
				Calendar.setup({
					formName:"listForm",
					inputField:"starTime#{object.id}",
					button:"starTime#{object.id}_trigger",
					ifFormat:"%Y-%m-%d",
					showsTime:false,
					timeFormat:"24",
					showOthers:true
				});
			</script>
			<@alignLeft/>
			<@vlh.attribute name="width" value="165" />
		</@vcolumn>

			<@vcolumn title="${action.getText('结束时间')}" property="endTime" format="yyyy-MM-dd" >
				<input style="text-align:left; vertical-align:middle; border:none;width: 150; height: 99%" type="text" name="endTime#{object.id}" value="${object.endTime?string('yyyy-MM-dd')?if_exists}" readonly="readonly" id="endTime#{object.id}" />
				<script language="javascript">
					Calendar.setup({
						formName:"listForm",
						inputField:"endTime#{object.id}",
						button:"endTime#{object.id}_trigger",
						ifFormat:"%Y-%m-%d",
						showsTime:false,
						timeFormat:"24",
						showOthers:true
					});
				</script>
     			<@alignLeft/>
     			<@vlh.attribute name="width" value="165" />
            </@vcolumn>
            -->
          <@vcolumn title="${action.getText('水价(元/吨)')}" property="floor.building.waterPrice" >
				<@alignRight/>
				<@vlh.attribute name="width" value="100" />
			</@vcolumn>
            <@vcolumn title="${action.getText('上次水示数')}" >
            	<input  style="text-align:right; vertical-align:middle; border:none;width: 110; height: 99%" type ="text" name ="lastWater#{object.id}" value="#{object.lastWater}" onblur ="getSum()">
            	<@vlh.attribute name="width" value="120" />
            </@vcolumn>
            
            <@vlh.column title="${action.getText('本次水示数')}" >
            	<input style="text-align:right; vertical-align:middle; border:none;width: 110; height: 99%"  type ="text" name ="thisWater#{object.id}" value="#{object.thisWater}" onblur ="getSum()">
            	<@vlh.attribute name="width" value="120" />
            </@vlh.column>
            
            <@vlh.column title="${action.getText('公摊水数')}" >
            	<input style="text-align:right; vertical-align:middle; border:none;width: 110; height: 99%"  type ="text" name ="shareWater#{object.id}" value="#{object.shareWater}" onblur ="getSum()">
            	<@vlh.attribute name="width" value="120" />
            </@vlh.column>
            
             <@vcolumn title="${action.getText('实用水数')}" >
            	<input style="text-align:right; vertical-align:middle; border:none;width: 110; height: 99%"  type ="text" name ="sumWater#{object.id}" value="#{object.sumWater}" readonly="readonly">
            	<@vlh.attribute name="width" value="120" />
            </@vcolumn>
            
               <@vcolumn title="${action.getText('备注')}" >
            	<input style="text-align:left; vertical-align:middle; border:none;width: 180; height: 99%"  type ="text" name ="outLine#{object.id}" value="${object.outLine}"
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
        		var lastName = 'lastWater'+ids[i].value;
        		var thisName = 'thisWater'+ids[i].value;
        		var sumName = 'sumWater'+ids[i].value;
            	var lastWater = getObjByName(lastName).value;
            	var thisWater = getObjByName(thisName).value;
            	getObjByName(sumName).value =thisWater - lastWater;
        	}
        }
</script>
</@framePage>
