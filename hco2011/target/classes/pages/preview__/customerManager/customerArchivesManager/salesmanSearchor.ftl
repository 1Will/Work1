<#--
	Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
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

<#-- $Id: salesmanSearchor.ftl 2009-11-26 10:32:35Z wliu $ -->

<#include "../../../includes/macros.ftl" />

<@htmlPage title="业务员信息管理">
	<@ww.form name="'listForm'" action="'listSalesMan_'" method="'post'">
		<@ww.token name="listSalesManToken"/>
		<@inputTable>
		<tr>
		    <@ww.textfield label="'业务员编码'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
		    <@ww.textfield label="'业务员姓名'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/> 
	 	</tr>
		</@inputTable>
		<@buttonBar> 
	 		 <@vsubmit value="'${'查询'}'" />   
        </@buttonBar>
        
        <@listTable>
        	<tr>
			 	<th>业务员编码</th>
			 	<th>业务员姓名</th>
			</tr>
			<tr>
			 	<td style="text-align:left"><a href="javascript: returnDialog(new Array('salesman_001', '小朱'));">salesman_001</a></td>
				<td style="text-align:left">小朱</td>
			</tr>
		</@listTable>
    </@ww.form>
</@htmlPage>