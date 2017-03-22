<#include "../../includes/macros.ftl" />

<@framePage>
<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
<table>
<tr width="100%">
  	<td style="VERTICAL-ALIGN:top;text-align:left" width="40%">
		<SCRIPT LANGUAGE="JavaScript">
			var dt = new dTree('dt','${req.contextPath}/javascripts','addForm');
			dt.add(0,-1,'【组织机构】<a href="editInstitution.html?root=root&readOnly=${req.getParameter('readOnly')?if_exists}" target="DmainFrame">新建机构</a>');
			<#assign instStep=0>
			<#list allInstsSortByStep as instList>
				<#if instStep==0>
				    <#list instList as inst>
						dt.add(#{inst.id},0,'${inst.name}(单位)',#{inst.id},'comment','editInstitution.html?institution.id=#{inst.id}&readOnly=${req.getParameter('readOnly')?if_exists}','','DmainFrame');	
				    </#list>
				<#else>
				    <#list instList as inst>
				      dt.add(#{inst.id},#{inst.parentInst.id},'${inst.name}(单位)',#{inst.id},'comment','editInstitution.html?institution.id=#{inst.id}&readOnly=${req.getParameter('readOnly')?if_exists}','','DmainFrame');	
				    </#list>
			  	</#if>
				<#assign instStep=instStep+1/>
			</#list>
			var nodeId = #{count}+1;
			<#assign deptStep=0>
			<#list allDeptSortByStep as deptList>
				<#if deptStep==0>
				    <#list deptList as dept>
						dt.add(#{dept.id}+nodeId,#{dept.inst.id},'${dept.name}(部门)',#{dept.id},'dept','editDepartment.html?department.id=#{dept.id}&readOnly=${req.getParameter('readOnly')?if_exists}','','DmainFrame');
				    </#list>
				<#else>
				    <#list deptList as dept>
				      	dt.add(#{dept.id}+nodeId,#{dept.parentDept.id}+nodeId,'${dept.name}(部门)',#{dept.id},'dept','editDepartment.html?department.id=#{dept.id}&readOnly=${req.getParameter('readOnly')?if_exists}','','DmainFrame');
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