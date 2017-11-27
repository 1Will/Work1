<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
   shall not disclose such Confidential Information and shall use it only in
   accordance with the terms of the license agreement you entered into with
   YongJun.
   
   YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
   SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
   WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
   NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
   LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
   DERIVATIVES.
 -->
<#--$Id: roleList.ftl 6148 2007-07-31 01:41:34Z qsun $ -->
<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('我的团队')}">

  <@ww.form namespace="'/workspace/data'" name="'dataListFrom'" action="'searchData'" method="'post'">
     <@ww.token name="searchDataToken"/>
     <#include "./dataSearcher.ftl" />
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
     
         <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" />
        </@buttonBar>
         <@list title="${action.getText('我的团队')}" includeParameters="data.year|type|personnelFiles.name|data.month|" fieldMap="like:data.year|type|personnelFiles.name|data.month|" >
        	
        	<@vcolumn title="${action.getText('personnelFiles.name')}" property="personnelFiles.name" sortable="desc">
                <@alignLeft/>
            </@vcolumn>
         	<@vcolumn title="${action.getText('data.month')}" property="month" sortable="desc">
              <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('data.contractManagementNum')}" property="contractManagementNum" sortable="desc">
                <a href="javascript:listContract_OpenDialog(#{object.personnelFiles.id?if_exists},'${object.month?if_exists}')" >${object.contractManagementNum?if_exists}</a>
                <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('data.contractManagementMoney')}" property="contractManagementMoney" sortable="desc">
              <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('data.financialManagementNum')}" property="financialManagementNum" sortable="desc">
            <a href="javascript:listFinancial_OpenDialog(#{object.personnelFiles.id?if_exists},'${object.month?if_exists}')" >${object.financialManagementNum?if_exists}</a>
              <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('data.financialManagementMoney')}" property="financialManagementMoney" sortable="desc">
                <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('data.billingRecordNum')}" property="billingRecordNum" sortable="desc">
            <a href="javascript:billingRecord_OpenDialog(#{object.personnelFiles.id?if_exists},'${object.month?if_exists}')" >${object.billingRecordNum?if_exists}</a>
              <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('data.billingRecordMoney')}" property="billingRecordMoney" sortable="desc">
              <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('data.actualDaily')}" property="actualDaily" sortable="desc">
              <a href="javascript:listDaily_OpenDialog(#{object.personnelFiles.id?if_exists},'${object.month?if_exists}')" >${object.actualDaily?if_exists}</a>
                <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('data.actualWeekly')}" property="actualWeekly"sortable="desc">
             <a href="javascript:listWeek_OpenDialog(#{object.personnelFiles.id?if_exists},'${object.month?if_exists}')" >${object.actualWeekly?if_exists}</a>
              <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('data.projectNum')}" property="projectNum" sortable="desc">
            <a href="javascript:listProject_OpenDialog(#{object.personnelFiles.id?if_exists},'${object.month?if_exists}')" >${object.projectNum?if_exists}</a>
              <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('data.backvisitNum')}" property="backvisitNum" sortable="desc">
            <a href="javascript:backvisit_OpenDialog(#{object.personnelFiles.id?if_exists},'${object.month?if_exists}')" >${object.backvisitNum?if_exists}</a>
                <@alignRight/>
            </@vcolumn>
         </@list>
      
    </@ww.form>
</@htmlPage>
<script language="javascript">
   //弹出合同模态窗体
	function listContract_OpenDialog(id,month){
	  
			 month=month.replace('年','-').replace('月','');
	   var url = "${req.contextPath}/contractManagement/listContractManagementAction.html?readOnly=${req.getParameter('readOnly')?if_exists}&month="+month+"&personnelFiles.id="+id; 
	   openNewWindow(url);
	 }
	 
	  //弹收款模态窗体
	function listFinancial_OpenDialog(id,month){
			 month=month.replace('年','-').replace('月','');
	   var url = "${req.contextPath}/financialManagement/listFinancialManagement.html?readOnly=${req.getParameter('readOnly')?if_exists}&month="+month+"&personnelFiles.id="+id; 
	   openNewWindow(url);
	 }

    //弹出周报模态窗体
	function billingRecord_OpenDialog(id,month){
			 month=month.replace('年','-').replace('月','');
	   var url = "${req.contextPath}/contractManagement/listBillingRecord.html?readOnly=${req.getParameter('readOnly')?if_exists}&month="+month+"&personnelFiles.id="+id; 
	   openNewWindow(url);
	 }
     //弹出日报模态窗体
    
	function listDaily_OpenDialog(id,month){
			 month=month.replace('年','-').replace('月','');
	   var url = "${req.contextPath}/workReport/listDaily.html?readOnly=${req.getParameter('readOnly')?if_exists}&month="+month+"&personnelFiles.id="+id; 
	   openNewWindow(url);
	 }
	   //弹出周报模态窗体
    
	function listWeek_OpenDialog(id,month){
			 month=month.replace('年','-').replace('月','');
	   var url = "${req.contextPath}/workReport/listWeekly.html?readOnly=${req.getParameter('readOnly')?if_exists}&month="+month+"&personnelFiles.id="+id; 
	   openNewWindow(url);
	 }
	  //弹出项目数模态窗体
    
	function listProject_OpenDialog(id,month){
			 month=month.replace('年','-').replace('月','');
	   var url = "${req.contextPath}/projectInfo/listProjectInfo.html?readOnly=${req.getParameter('readOnly')?if_exists}&month="+month+"&personnelFiles.id="+id; 
	   openNewWindow(url);
	 }
	   //弹出回复数模态窗体
    
	function backvisit_OpenDialog(id,month){
			 month=month.replace('年','-').replace('月','');
	   var url = "${req.contextPath}/backvisit/listBackVisit.html?readOnly=${req.getParameter('readOnly')?if_exists}&month="+month+"&personnelFiles.id="+id; 
	   openNewWindow(url);
	 }
	
</script>