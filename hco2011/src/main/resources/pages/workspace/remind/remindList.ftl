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

<@htmlPage title="${action.getText('remindSearch')}">

  <@ww.form namespace="'/remind'" name="'listRemind'" action="'searchRemind'" method="'post'">
     <@ww.token name="searchRemindToken"/>
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'${action.getText('remind.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
                <@ww.textfield label="'${action.getText('remind.type')}'" name="'type'" value="'${req.getParameter('type')?if_exists}'" cssClass="'underline'"/>
            </tr>
        </@inputTable>
         <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" />
        </@buttonBar>
         <@list title="${action.getText('remind.list')}" includeParameters="code|type" fieldMap="like:code|type" >
            <@vcolumn title="${action.getText('remind.code')}">
                <a href="editRemind.html?remind.id=#{object.id}">${object.code}</a>
                <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('remind.type')}" property="type">
              <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('remind.days')}" property="days">
              <@alignLeft/>
            </@vcolumn>
         </@list>
      
    </@ww.form>
</@htmlPage>
