<#--
  Copyright (c) 2001-2005 vTradEx Information Technology Co.,Ltd. All Rights Reserved.
    This software is the confidential and proprietary information of vTradEx
  Information Technology Co.,Ltd. ("Confidential Information").  You shall not
  disclose such Confidential Information and shall use it only in
  accordance with the terms of the license agreement you entered into
  with vTradEx.
    VTRADEX MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
  SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
  IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
  PURPOSE, OR NON-INFRINGEMENT. VTRADEX SHALL NOT BE LIABLE FOR ANY DAMAGES
  SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
  THIS SOFTWARE OR ITS DERIVATIVES.
 -->
<#--$Id: demoSearch.ftl 6888 2007-09-05 13:24:56Z qsun $ -->
<@inputTable>
    <tr>
        <@ww.textfield label="'${action.getText('area.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('area.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
        <@ww.checkbox label="'${action.getText('invalid')}'"  name="'invalid'"  fieldValue="'true'" />
    </tr>
</@inputTable>
