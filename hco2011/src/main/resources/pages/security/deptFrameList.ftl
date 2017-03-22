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

<#-- $Id: agencyDTree.ftl 2010-01-26 wliu $ -->

<#include "../includes/macros.ftl" />

<@framePage>
<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
<table>
<tr width="100%">
  	<td style="VERTICAL-ALIGN:top;text-align:left" width="40%">
		<SCRIPT LANGUAGE="JavaScript">
			var dt = new dTree('dt','${req.contextPath}/javascripts','agencyForm');
			dt.add(0,-1,'${currentOrgName}');
			<#assign instStep=0>
			<#if (allInsts.size() > 0)>
				<#list allInsts as instList>
					<#if instStep==0>
					    <#list instList as inst>
							dt.add(#{inst.id},0,'${inst.name}${action.getText('(机构)')}','','','listDeptFrame.html?inst.id=#{inst.id}','','mainFrame');	
					    </#list>
					<#else>
					    <#list instList as inst>
					      dt.add(#{inst.id},#{inst.parentAgency.id},'${inst.name}${action.getText('(机构)')}','','','listDeptFrame.html?inst.id=#{inst.id}','','mainFrame');	
					    </#list>
				  	</#if>
					<#assign instStep=instStep+1/>
				</#list>
			</#if>
			
			var nodeId = #{count}+1;
			<#assign deptStep=0>
			<#list allDepts as deptList>
				<#if deptStep==0>
				    <#list deptList as dept>
						dt.add(#{dept.id}+nodeId,#{dept.inst.id},'${dept.name}${action.getText('(部门)')}','','','listDeptFrame.html?deptFrame.id=#{dept.id}','','mainFrame');					
				    </#list>
				<#else>
				    <#list deptList as dept>
					dt.add(#{dept.id}+nodeId,#{dept.parentDept.id}+nodeId,'${dept.name}${action.getText('(部门)')}','','','listDeptFrame.html?deptFrame.id=#{dept.id}','','mainFrame');			      	
				    </#list>
			  	</#if>
				<#assign deptStep=deptStep+1/>
			</#list>

			document.write(dt);
		</SCRIPT>
  	</td>
</tr>
</table>
</@framePage>