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
<#-- $Id: instToDepts.ftl  2009-11-03 11:00:50Z wliu $-->
<#include "../../../includes/hco2011.ftl" />

<@framePage>
	<@ww.form name="'listForm'" action="'listDept'" method="'post'">
        <@ww.token name="listDeptToken"/>
        
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@list title="" excel=false setupTable=false
				includeParameters="institution.id" 
        		fieldMap="like:" >
             <@vcolumn title="${action.getText('deparment.code')}" property="code" sortable="desc"/>  
             <@vcolumn title="${action.getText('department.name')}" property="name" sortable="desc" />
             <#if object.parentDept?exists>
               <#assign parentDept="${object.parentDept.name}"/>
             <#else>
               <#assign parentDept="无"/>
             </#if>
             <@vcolumn title="${action.getText('parent.department')}" property="parentDept.name" sortable="desc">
               ${parentDept}
             </@vcolumn> 
             <@vcolumn title="${action.getText('department.leader')}" property="leader" sortable="desc"/>
		</@list>
    </@ww.form>

</@framePage>