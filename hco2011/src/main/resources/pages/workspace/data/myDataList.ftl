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

<@htmlPage title="${action.getText('我的数据')}">

  <@ww.form namespace="'/workspace/data'" name="'dataListFrom'" action="'searchMyData'" method="'post'">
     <@ww.token name="searchDataToken"/>
     <#include "./myDataSearcher.ftl" />
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
     
         <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" />
        </@buttonBar>
         <@list title="${action.getText('我的数据')}" includeParameters="data.year|type" fieldMap="like:data.year|type" >
         <@vcolumn title="${action.getText('data.month')}" property="month">
              <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('data.contractManagementNum')}" property="contractManagementNum" >
                <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('data.contractManagementMoney')}" property="contractManagementMoney">
              <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('data.financialManagementNum')}" property="financialManagementNum">
              <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('data.financialManagementMoney')}" property="financialManagementMoney" >
                <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('data.billingRecordNum')}" property="billingRecordNum">
              <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('data.billingRecordMoney')}" property="billingRecordMoney">
              <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('data.actualDaily')}" property="actualDaily" >
                <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('data.actualWeekly')}" property="actualWeekly">
              <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('data.projectNum')}" property="projectNum">
              <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('data.backvisitNum')}" property="backvisitNum" >
                <@alignRight/>
            </@vcolumn>
         </@list>
      
    </@ww.form>
</@htmlPage>
