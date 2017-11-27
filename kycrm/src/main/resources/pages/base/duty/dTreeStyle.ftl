<#--
	Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: dTreeStyle.ftl 2010-01-27 zsz $ -->

<#include "../../includes/macros.ftl" />

<@framePage>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<table>
	<tr>【${action.getText('duty.dept.name')}】</tr>
	<tr width="100%">
	  	<td style="VERTICAL-ALIGN:top;text-align:left" width="40%">
			<SCRIPT LANGUAGE="JavaScript">
				var dt = new dTree('dt','${req.contextPath}/javascripts','createDtreeForm');
				dt.add(0,-1,'${organizationName?if_exists}');
				<#if (allDepts.size() > 0)>
				    <#list allDepts as dept>
				    	<#if dept.step==0>
					  		dt.add(#{dept.id},0,'${dept.name}(${dept.code})',#{dept.id},'dept','${req.contextPath}/duty/listDuties.html?dept.id=#{dept.id}','','DmainFrame');
						<#else>
						<#if dept.parentDept?exists>
							dt.add(#{dept.id},#{dept.parentDept.id},'${dept.name}(${dept.code})',#{dept.id},'dept','${req.contextPath}/duty/listDuties.html?dept.id=#{dept.id}','','DmainFrame');
						</#if>
						</#if>
				    </#list>
			    </#if>
				document.write(dt);
			</SCRIPT>
	  	</td>
	</tr>
	</table>
</@framePage>