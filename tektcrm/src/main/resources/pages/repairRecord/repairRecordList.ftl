<#include "../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('售后管理查询页面')}">
	<@ww.form name="'listForm'" namespace="'/repairRecord'" action="'searchRepairRecord'" method="'post'">
		<@ww.token name="searchRepairRecordToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#include "./repairRecordSearcher.ftl" />
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'"/>
        </@buttonBar>
         <#assign itemNo=1/>
        <@list title="${action.getText('售后管理列表')}" 
            includeParameters="personnelFiles.name|workNum|airtypeNum|branch.id|repirType.id|faultType.id|repairRecord.receiveDate_start|repairRecord.receiveDate_end|repairRecord.finishDate_start|repairRecord.finishDate_end
            |repairRecord.leaveDate_start|repairRecord.leaveDate_end|repairRecord.arriveDate_start|repairRecord.arriveDate_end|repairRecord.startDate_start|repairRecord.startDate_end|repairRecord.endDate_start|repairRecord.endDate_end|"
             fieldMap="like:personnelFiles.name|workNum|airtypeNum,date:repairRecord.receiveDate_start|repairRecord.receiveDate_end|repairRecord.finishDate_start|repairRecord.finishDate_end
            |repairRecord.leaveDate_start|repairRecord.leaveDate_end|repairRecord.arriveDate_start|repairRecord.arriveDate_end|repairRecord.startDate_start|repairRecord.startDate_end|repairRecord.endDate_start|repairRecord.endDate_end|"
            >
          	<@vcolumn title="${action.getText('序号')}" property="num" sortable="desc" >
				<@alignLeft/>
            </@vcolumn>
           <@vcolumn title="${action.getText('报告人')}" property="personnelFiles.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('接收日期')}" property="receiveDate" sortable="desc" format="yyyy-MM-dd">
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('工作单号')}" property="workNum" sortable="desc" >
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('兵种')}" property="branch.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('联系人')}" property="contactPerson" sortable="desc" >
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('用户地址')}" property="address" sortable="desc" >
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('空调型号')}" property="airtypeNum" sortable="desc" >
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('台数')}" property="airCount" sortable="desc" >
     			<@alignRight/>
            </@vcolumn>
              <@vcolumn title="${action.getText('故障情况')}" property="faultDesc" sortable="desc" >
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('维修类型')}" property="repirType.name" sortable="desc" >
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('完结日期')}" property="finishDate" sortable="desc" format="yyyy-MM-dd" >
     			<@alignLeft/>
            </@vcolumn>
              <@vcolumn title="${action.getText('处理结果')}" property="repairResult" sortable="desc" >
     			<@alignLeft/>
            </@vcolumn>
               <@vcolumn title="${action.getText('故障分类')}" property="faultTypeDesc" sortable="desc" >
     			<@alignLeft/>
            </@vcolumn>
              <@vcolumn title="${action.getText('备件费用')}" property="partFee" sortable="desc" >
     			<@alignRight/>
            </@vcolumn>
              <@vcolumn title="${action.getText('出发时间')}" property="leaveDate" sortable="desc" format="yyyy-MM-dd HH:mm" >
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('到达时间')}" property="arriveDate" sortable="desc" format="yyyy-MM-dd HH:mm" >
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('出发地点')}" property="leavePlace" sortable="desc" >
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('到达地点')}" property="arrivePlace" sortable="desc" >
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('备注')}" property="mark" sortable="desc" >
     			<@alignLeft/>
            </@vcolumn>
        </@list>
    </@ww.form>

</@htmlPage>
