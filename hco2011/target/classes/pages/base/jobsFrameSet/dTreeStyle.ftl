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

<#-- $Id: dTreeStyle.ftl 2010-01-20 wliu $ -->

<#include "../../includes/hco2011.ftl" />

<@framePage  title="123">
<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<table>
	<tr>${action.getText('job.type2')}</tr>
	<tr width="100%">
	  	<td style="VERTICAL-ALIGN:top;text-align:left" width="40%">
			<SCRIPT LANGUAGE="JavaScript">
				var dt = new dTree('dt','${req.contextPath}/javascripts','testForm');
				dt.add(0,-1,'${orgName}');
				<#if allJobType?exists>
					<#if (allJobType.size() > 0)>
				    <#list allJobType as jobType>
					  dt.add(#{jobType.id},0,'${jobType.name}(${jobType.code})',#{jobType.id},'jobType','${req.contextPath}/jobs/listAllJob.html?jobsType=#{jobType.id}','','DmainFrame');
				    </#list>
				    </#if>
			    </#if>
				document.write(dt);
			</SCRIPT>
	  	</td>
	</tr>
	</table>
</@framePage>