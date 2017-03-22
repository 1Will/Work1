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
<#--$Id: setupTable.ftl 8610 2007-11-25 02:33:00Z qsun $ -->
<#include "../includes/hco2011.ftl" />

<@htmlPage >
 <base target="_self">
 <@ww.form namespace="'/popup'" name="'table'" action="'saveTable'" method="'post'" validate="true">
 <@ww.token name="saveTableToken"/>
    <@inputTable>
        <@ww.hidden name="'page'" value="'${tableInfo.page}'"/>
        <@ww.hidden name="'tableName'" value="'${tableInfo.name}'"/>
        <tr>
            <@ww.textfield label="'${action.getText('tableInfo.numberPerPage')}'"  name="'tableInfo.numberPerPage'"
                value="'#{tableInfo.numberPerPage}'" cssClass="'underline'" required="true"/>
            <@ww.checkbox label="'${action.getText('tableInfo.exportAll')}'" name="'exportAll'"
                value="'${tableInfo.exportAll?string}'" fieldValue="'true'"/>
        </tr>
    </@inputTable>
        <@titleBar title="${action.getText('tableInfo.visibleColumns')}"/>
        <@listTable>
            <tr>
                <th/>
                <#list targets as target>
                    <th>${target.label}</th>
                </#list>
            </tr>
            <#list columns as column>
                <tr>
                    <td>${column}</td>
                    <#list targets as target>
                        <td>
                            <input type="checkbox" name="visibleColumns"
                                value="${target.value}~~${column}" <#if action.isVisible(target.value, column)>checked</#if> width="100" />
                        </td>
                    </#list>
                </tr>
            </#list>
        </@listTable>
      <@buttonBar>
             <@vsubmit name="'save'" value="'${action.getText('save')}'" />
             <@vbutton class="button" value="${action.getText('back')}" onclick="javascript:returnDialog(null);"/>
       </@buttonBar>
 </@ww.form>
</@htmlPage>

