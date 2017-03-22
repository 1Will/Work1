<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
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
<#include "../../../includes/macros.ftl" />
<@htmlPage title="选择客户页面">
	<#include "./customerSearcher.ftl" />
    <@buttonBar>
		<@vbutton value="${'查询'}"/>
    </@buttonBar>
    <@listTable>
    	<tr>
    		<th>客户编码</th>
		 	<th>客户名称</th>
		</tr>
    	<tr>
    		<td>
    			<a href="javascript:window.close();">
            	0001
            	</a>
            </td>
    		<td>
	    		丰登钢塑实业
            </td>
		</tr>
		<tr>
    		<td>
    			<a href="javascript:window.close();">
            	0002
            	</a>
            </td>
    		<td>
	    		丰宝水泥集团
            </td>
		</tr>
		<tr>
    		<td>
    			<a href="javascript:window.close();">
            	0003
            	</a>
            </td>
    		<td>
	    		金鑫矿业
            </td>
		</tr>
    </@listTable>
</@htmlPage>