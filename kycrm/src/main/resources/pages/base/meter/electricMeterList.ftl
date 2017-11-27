<#include "../../includes/hco2011.ftl" />

<@htmlPage title="大楼">
	<@ww.form name="'listForm'" namespace="'/fee'" action="'searchElectricMeter'" method="'post'">
		<@ww.token name="seacherElectricMeterToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#include "./electricMeterSearcher.ftl" />
        <@buttonBar>
        <@vsubmit value="'${action.getText('search')}'" onclick="'checkInvalidParms()'" />
		<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/fee/editElectricMeter.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
        </@buttonBar>
		<@list title="${action.getText('电表列表')}" includeParameters="electricMeter.code|electricMeter.name|onlyInvalid|onlyValid" fieldMap="like:electricMeter.code|electricMeter.name|" >
			<@vlh.checkbox property="id" name="electricMeterIds">
                <@vlh.attribute name="width" value="30" />
			</@vlh.checkbox>
			<@vlh.column title="${action.getText('电表编码')}"  property="code" sortable="desc">
				 <a href="${req.contextPath}/fee/editElectricMeter.html?electricMeter.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
				<@alignLeft/>
			</@vlh.column>
			
			<@vlh.column title="${action.getText('电表名称')}"  property="name" sortable="desc">
				<@alignLeft/>
			</@vlh.column>
			
			<@vcolumn title="${action.getText('电表倍率')}" property="rate" sortable="desc">
				<@alignLeft/>
            </@vcolumn>
        </@list>
    </@ww.form>
    
</@htmlPage>
<script language="javascript">
</script>